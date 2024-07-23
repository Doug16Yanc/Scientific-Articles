package douglas.Scientific.papers.service;

import douglas.Scientific.papers.domain.OrderEntity;
import douglas.Scientific.papers.domain.OrderPaper;
import douglas.Scientific.papers.dto.OrderCreatedEvent;
import douglas.Scientific.papers.dto.OrderResponse;
import douglas.Scientific.papers.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public OrderService(OrderRepository orderRepository, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void saveOrder(OrderCreatedEvent event) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setOrderId(event.requestCode());
        orderEntity.setResearcherId(event.researcherCode());
        orderEntity.setArticles(getOrderPapers(event));
    }

    public Page<OrderResponse> findAllByResearcherId(Long researcherId, PageRequest pageRequest) {
        var orders = orderRepository.findAllByResearcherId(researcherId, pageRequest);

        return orders.map(OrderResponse::fromEntity);
    }

    private static List<OrderPaper> getOrderPapers(OrderCreatedEvent event) {
        return event.papers().stream()
                .map(i -> new OrderPaper(i.paperTitle(), i.fileType(), i.knowledgeArea()))
                .toList();
    }
}

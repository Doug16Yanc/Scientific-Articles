package douglas.Scientific.papers.service;

import douglas.Scientific.papers.domain.OrderEntity;
import douglas.Scientific.papers.domain.OrderPaper;
import douglas.Scientific.papers.dto.OrderCreatedEvent;
import douglas.Scientific.papers.dto.OrderPaperEvent;
import douglas.Scientific.papers.repository.OrderRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    public OrderService(OrderRepository orderRepository, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void saveOrder(OrderCreatedEvent event) {
        var orderEntity = new OrderEntity();

        orderEntity.setOrderId(event.requestCode());
        orderEntity.setResearcherId(event.researcherCode());
        orderEntity.setArticles(getOrderPapers(event));
        orderEntity.setTotalPrice(getTotal(event));
    }

    private BigDecimal getTotal(OrderCreatedEvent event) {
        return event.papers()
                .stream()
                .map(OrderPaperEvent::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    private static List<OrderPaper> getOrderPapers(OrderCreatedEvent event) {
        return event.papers().stream()
                .map(i -> new OrderPaper(i.paperTitle(), i.knowledgeArea(), i.price()))
                .toList();
    }
}

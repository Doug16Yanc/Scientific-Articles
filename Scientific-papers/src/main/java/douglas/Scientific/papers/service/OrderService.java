package douglas.Scientific.papers.service;

import douglas.Scientific.papers.domain.OrderEntity;
import douglas.Scientific.papers.domain.OrderPaper;
import douglas.Scientific.papers.dto.OrderCreatedEvent;
import douglas.Scientific.papers.dto.OrderPaperEvent;
import douglas.Scientific.papers.dto.OrderResponse;
import douglas.Scientific.papers.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.bson.Document;

import javax.swing.text.BadLocationException;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import java.math.BigDecimal;
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

        if(limitQuantityArticles(orderEntity.getResearcherId()).size() <= 3) {

            orderEntity.setOrderId(event.requestCode());
            orderEntity.setResearcherId(event.researcherCode());
            orderEntity.setArticles(getOrderPapers(event));
            orderEntity.setTotalPrice(getTotal(event));
        } else{
            throw new IllegalArgumentException("The maximum quantity is three.");
        }
    }

    public List<List<OrderPaperEvent>> limitQuantityArticles(Long researcherId) {
        List<OrderCreatedEvent> orders = orderRepository.findByResearcherId(researcherId);

        return orders.stream().map(order -> {
            List<OrderPaperEvent> limited = order.papers().stream().limit(3)
                    .collect(Collectors.toList())
            ;return limited;
        }).collect(Collectors.toList());
    }

    public Page<OrderResponse> findAllByResearcherId(Long researcherId, PageRequest pageRequest) {
        var orders = orderRepository.findAllByResearcherId(researcherId, pageRequest);

        return orders.map(OrderResponse::fromEntity);
    }

    public BigDecimal findTotalOnOrderByResearcher(Long researcherId) throws BadLocationException {
        var aggregations = newAggregation(match(Criteria.where("researcherId").is(researcherId)),
                group().sum("totalPrice").as("totalPrice"));

        var response = mongoTemplate.aggregate(aggregations, "orders", Document.class);

        return new BigDecimal(response.getUniqueMappedResult().get("total").toString());
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

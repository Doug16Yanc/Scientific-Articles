package douglas.Scientific.papers.dto;

import douglas.Scientific.papers.domain.OrderEntity;
import douglas.Scientific.papers.domain.OrderPaper;

import java.util.List;

public record OrderResponse(Long orderId,
                            Long researcherId,
                            List<OrderPaper> papers) {
    public static OrderResponse fromEntity(OrderEntity orderEntity) {
        return new OrderResponse(orderEntity.getOrderId(), orderEntity.getResearcherId(), orderEntity.getArticles());
    }
}

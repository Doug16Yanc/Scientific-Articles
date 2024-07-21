package douglas.Scientific.papers.dto;

import douglas.Scientific.papers.domain.OrderEntity;

import java.math.BigDecimal;

public record OrderResponse(Long orderId,
                            Long researcherId,
                            String researcherName,
                            BigDecimal total) {
    public static OrderResponse fromEntity(OrderEntity orderEntity) {
        return new OrderResponse(orderEntity.getOrderId(), orderEntity.getResearcherId(), orderEntity.getResearcherName(), orderEntity.getTotalPrice());
    }
}

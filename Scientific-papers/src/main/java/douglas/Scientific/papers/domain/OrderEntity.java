package douglas.Scientific.papers.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.List;

@Document(collation = "tb_orders")
public class OrderEntity {

    @MongoId
    private Long orderId;

    @Indexed(name = "researcher_id_index")
    private Long researcherId;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal totalPrice;

    private List<OrderPaper> articles;

    public OrderEntity() {}

    public OrderEntity(Long orderId, Long researcherId, BigDecimal totalPrice, List<OrderPaper> items) {
        this.orderId = orderId;
        this.researcherId = researcherId;
        this.totalPrice = totalPrice;
        this.articles = articles;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getResearcherId() {
        return researcherId;
    }

    public void setResearcherId(Long researcherId) {
        this.researcherId = researcherId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderPaper> getArticles() {
        return articles;
    }

    public void setArticles(List<OrderPaper> articles) {
        this.articles = articles;
    }
}


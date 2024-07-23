package douglas.Scientific.papers.domain;

import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Document(collection = "orders")
public class OrderEntity {

    @MongoId
    private Long orderId;

    @Indexed(name = "researcher_id_index")
    private Long researcherId;

    private String researcherName;

    private List<OrderPaper> articles;

    public OrderEntity() {}

    public OrderEntity(Long orderId, Long researcherId, String researcherName, List<OrderPaper> articles) {
        this.orderId = orderId;
        this.researcherId = researcherId;
        this.researcherName = researcherName;
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

    public String getResearcherName() {
        return researcherName;
    }

    public void setResearcherName(String researcherName) {
        this.researcherName = researcherName;
    }

    public List<OrderPaper> getArticles() {
        return Collections.unmodifiableList(articles);
    }

    public void setArticles(List<OrderPaper> articles) {
        this.articles = articles;
    }
}


package douglas.Scientific.papers.domain;

import douglas.Scientific.papers.domain.enums.FileType;
import douglas.Scientific.papers.domain.enums.KnowledgeArea;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

public class OrderPaper {
    private String paperTitle;
    private String paperAbstract;
    private FileType fileType;
    private KnowledgeArea knowledgeArea;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;

    public OrderPaper(String s, KnowledgeArea knowledgeArea, BigDecimal price) {

    }

    public OrderPaper(String paperTitle, String paperAbstract, FileType fileType, KnowledgeArea knowledgeArea, BigDecimal price) {
        this.paperTitle = paperTitle;
        this.paperAbstract = paperAbstract;
        this.fileType = fileType;
        this.knowledgeArea = knowledgeArea;
        this.price = price;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getPaperAbstract() {
        return paperAbstract;
    }

    public void setPaperAbstract(String paperAbstract) {
        this.paperAbstract = paperAbstract;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public KnowledgeArea getKnowledgeArea() {
        return knowledgeArea;
    }

    public void setKnowledgeArea(KnowledgeArea knowledgeArea) {
        this.knowledgeArea = knowledgeArea;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

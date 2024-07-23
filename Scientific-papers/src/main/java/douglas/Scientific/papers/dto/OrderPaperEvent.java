package douglas.Scientific.papers.dto;

import douglas.Scientific.papers.domain.enums.FileType;
import douglas.Scientific.papers.domain.enums.KnowledgeArea;

import java.math.BigDecimal;

public record OrderPaperEvent(
        String paperTitle,
        FileType fileType,
        KnowledgeArea knowledgeArea) {
}

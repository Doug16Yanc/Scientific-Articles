package douglas.Scientific.papers.dto;

import java.util.List;

public record OrderCreatedEvent(
        Long requestCode,
        Long researcherCode,
        List<OrderPaperEvent> papers
){
}

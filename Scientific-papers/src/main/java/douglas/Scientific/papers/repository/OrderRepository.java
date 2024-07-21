package douglas.Scientific.papers.repository;

import douglas.Scientific.papers.domain.OrderEntity;
import douglas.Scientific.papers.dto.OrderCreatedEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {

    List<OrderCreatedEvent> findByResearcherId(Long researcherId);
    Page<OrderEntity> findAllByResearcherId(Long researcherId, PageRequest pageRequest);
}

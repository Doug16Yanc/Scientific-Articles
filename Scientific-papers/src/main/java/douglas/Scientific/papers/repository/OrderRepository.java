package douglas.Scientific.papers.repository;

import douglas.Scientific.papers.domain.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {

    Page<OrderEntity> findAllByResearcherId(Long researcherI, PageRequest pageRequest);
}

package pet.store.spring.dao.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pet.store.spring.dao.model.classes.PetDaoEntityC;

@Repository
public interface PetsDaoRepositoryI extends PagingAndSortingRepository<PetDaoEntityC, Long> {
}
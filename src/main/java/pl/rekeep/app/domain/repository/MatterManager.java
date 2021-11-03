package pl.rekeep.app.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.rekeep.app.domain.Matter;

@Repository
public interface MatterManager extends PagingAndSortingRepository<Matter, Long> {
}

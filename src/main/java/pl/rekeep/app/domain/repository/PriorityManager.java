package pl.rekeep.app.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.rekeep.app.domain.Priority;

@Repository
public interface PriorityManager extends PagingAndSortingRepository<Priority, Long> {
}

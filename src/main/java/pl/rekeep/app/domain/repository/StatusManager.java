package pl.rekeep.app.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.rekeep.app.domain.Status;

@Repository
public interface StatusManager extends PagingAndSortingRepository<Status, Long> {
}

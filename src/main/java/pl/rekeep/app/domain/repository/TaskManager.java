package pl.rekeep.app.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.rekeep.app.domain.Task;

import java.util.List;

@Repository
public interface TaskManager extends PagingAndSortingRepository<Task, Long> {

    List<Task> findByUserLoginAndMatterId(String userLogin, Long id);
}

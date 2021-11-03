package pl.rekeep.app.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.rekeep.app.domain.Department;

@Repository
public interface DepartmentManager extends PagingAndSortingRepository<Department, Long> {
}

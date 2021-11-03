package pl.rekeep.app.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.rekeep.app.domain.ZupTask;

import java.util.Optional;

@Repository
public interface ZupTaskManager extends PagingAndSortingRepository<ZupTask, String> {
    Optional<ZupTask> findById(String s);

    @Query(value = "select ID_ZG from  (select * from hd_zgloszenia where uid_zglosz = ?1 and " +
            "fk_id_te = 66 and fk_id_dz = 1 and " +
            "fk_id_st = 1 and  substr(naglowek, -36) = ?2 order by id_zg desc) " +
            "where rownum = 1", nativeQuery = true)
    Optional<Long> findTaskIdByParams(String userName, String uuid);

}

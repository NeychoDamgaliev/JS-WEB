package residentevil.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import residentevil.web.domain.entities.Capital;

import java.util.List;

/**
 * Created by Neycho Damgaliev on 3/16/2019.
 */
@Repository
public interface CapitalRepository extends JpaRepository<Capital, String> {
    @Query("SELECT c FROM Capital c ORDER BY c.name")
    List<Capital> findAllOrOrderByName();
}

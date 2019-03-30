package residentevil.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import residentevil.web.domain.entities.Role;

/**
 * Created by Neycho Damgaliev on 3/23/2019.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByAuthority(String authority);
}

package residentevil.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import residentevil.web.domain.entities.User;

import java.util.Optional;

/**
 * Created by Neycho Damgaliev on 3/23/2019.
 */

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByUsername(String username);
}

package exodia.repository;

import exodia.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Neycho Damgaliev on 3/5/2019.
 */
@Repository
public interface UserRepository  extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username);
}

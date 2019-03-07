package exodia.repository;

import exodia.domain.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Neycho Damgaliev on 3/6/2019.
 */

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {
}

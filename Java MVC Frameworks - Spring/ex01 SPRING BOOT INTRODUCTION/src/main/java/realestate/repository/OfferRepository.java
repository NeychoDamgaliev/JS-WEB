package realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import realestate.domain.entities.Offer;

/**
 * Created by Neycho Damgaliev on 3/3/2019.
 */


public interface OfferRepository extends JpaRepository<Offer, String> {

}

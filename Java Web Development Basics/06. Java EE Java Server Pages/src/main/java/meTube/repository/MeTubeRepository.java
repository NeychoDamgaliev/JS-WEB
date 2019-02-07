package meTube.repository;

import meTube.domain.entities.Tube;

import java.util.List;
import java.util.Optional;

/**
 * Created by Neycho Damgaliev on 2/3/2019.
 */
public interface MeTubeRepository extends GenericRepository<Tube, String> {

    Tube findByName(String name);

}

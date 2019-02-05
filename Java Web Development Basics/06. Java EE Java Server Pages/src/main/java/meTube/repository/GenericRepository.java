package meTube.repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Neycho Damgaliev on 2/3/2019.
 */
public interface GenericRepository <Entity,Id> {

    Entity save(Entity entity);

    List<Entity> findAll();

    Optional<Entity> findById(Id id);

}

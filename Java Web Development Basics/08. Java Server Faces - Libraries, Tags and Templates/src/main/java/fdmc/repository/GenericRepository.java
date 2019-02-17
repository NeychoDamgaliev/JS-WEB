package fdmc.repository;

import java.util.List;

/**
 * Created by Neycho Damgaliev on 2/10/2019.
 */
public interface GenericRepository<Entity, ID> {

    Entity save(Entity entity);

    List<Entity> findAll();

    Entity findById(ID id);

//    void remove(ID id);
}

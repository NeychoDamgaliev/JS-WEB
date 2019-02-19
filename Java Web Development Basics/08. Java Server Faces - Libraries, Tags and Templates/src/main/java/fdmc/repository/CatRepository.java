package fdmc.repository;

import fdmc.domain.entities.Cat;

import java.util.List;

/**
 * Created by Neycho Damgaliev on 2/17/2019.
 */
public interface CatRepository extends GenericRepository<Cat, String> {

    List<Cat> findAllCatsOrdered(String prop, String dir);
}

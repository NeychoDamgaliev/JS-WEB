package chushka.repository;

import java.util.List;

/**
 * Created by Neycho Damgaliev on 2/2/2019.
 */
public interface GenericRepository <E,K> {

    E save(E entity);

    E findById(K id);

    List<E> findAll();
}

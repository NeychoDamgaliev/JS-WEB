package meTube.repository;

import meTube.domain.entities.Tube;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

/**
 * Created by Neycho Damgaliev on 2/3/2019.
 */
public class MeTubeRepositoryImpl implements MeTubeRepository {

    private final EntityManager entityManager;

    public MeTubeRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("meTube")
                .createEntityManager();
    }


    @Override
    public Tube findByName(String name) {
//        this.entityManager.getTransaction().begin();

        List<Tube> tubes = entityManager.createQuery(
                "SELECT t " +
                        "FROM tubes t " +
                          "WHERE t.name = :name", Tube.class)
                .setParameter("name", name)
                .getResultList();

        if(tubes.size() != 0) {
            return tubes.get(0);
        }
//        this.entityManager.getTransaction().commit();
        return null;
    }

    @Override
    public Tube save(Tube tube) {
        entityManager.getTransaction().begin();

            this.entityManager.persist(tube);

        entityManager.getTransaction().commit();
        return tube;
    }

    @Override
    public List<Tube> findAll() {
//        this.entityManager.getTransaction().begin();
        try {
            List<Tube> tubes = this.entityManager
                    .createQuery(
                            "SELECT t " +
                                    "FROM tubes t", Tube.class
                    ).getResultList();

//        this.entityManager.getTransaction().commit();

            return tubes;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Optional<Tube> findById(String tubeId) {
//        this.entityManager.getTransaction().begin();

        Optional<Tube> tube = Optional.ofNullable(entityManager.createQuery(
                "SELECT t " +
                        "FROM tubes t " +
                        "WHERE t.id = :tubeId", Tube.class)
                .setParameter("tubeId", tubeId)
                .getSingleResult());

//        this.entityManager.getTransaction().commit();
        return tube;
    }
}

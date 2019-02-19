package fdmc.repository;

import fdmc.domain.entities.Cat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Neycho Damgaliev on 2/17/2019.
 */
public class CatRepositoryImpl implements CatRepository {

    private final EntityManager entityManager;

    @Inject
    public CatRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Cat save(Cat cat) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cat);
            return cat;
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public List<Cat> findAll() {
        try {
            entityManager.getTransaction().begin();
            List<Cat> cats = entityManager.createQuery(
                    "SELECT c FROM Cat c",
                    Cat.class
            ).getResultList();

            return cats;
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public Cat findById(String id) {
        try {
            entityManager.getTransaction().begin();
            Cat cat = entityManager.createQuery(
                    "SELECT c FROM Cat c WHERE c.id = :id",
                    Cat.class)
                    .setParameter("id",id)
                    .getSingleResult();

            return cat;
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.getTransaction().commit();
        }
    }


    @Override
    public List<Cat> findAllCatsOrdered(String prop, String dir) {
        try {
            entityManager.getTransaction().begin();
            List<Cat> cats = entityManager.createQuery(
                    "SELECT c FROM Cat c ORDER BY " + prop + " " + dir,Cat.class)
                    .getResultList();

            return cats;
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.getTransaction().commit();
        }
    }
//    @Override
//    public void remove(String s) {
//
//    }
}

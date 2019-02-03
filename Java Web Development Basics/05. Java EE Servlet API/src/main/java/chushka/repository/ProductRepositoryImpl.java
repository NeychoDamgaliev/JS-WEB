package chushka.repository;

import chushka.domain.entities.Product;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Neycho Damgaliev on 2/2/2019.
 */
public class ProductRepositoryImpl implements ProductRepository {

    private EntityManager em;


    public ProductRepositoryImpl() {
        this.em = Persistence.createEntityManagerFactory("chushkaApp")
        .createEntityManager();
    }

    @Override
    public Product save(Product entity) {
        this.em.getTransaction().begin();

            this.em.persist(entity);
        this.em.getTransaction().commit();
        return entity;
    }

    @Override
    public Product findById(String id) {
        this.em.getTransaction().begin();

        Product product = this.em
                .createQuery("select p from products p where p.id = :id",Product.class)
                .setParameter("id", id)
                .getSingleResult();

        this.em.getTransaction().commit();
        return product;
    }

    @Override
    public List<Product> findAll() {
        this.em.getTransaction().begin();

            List<Product> products = this.em
                    .createQuery("SELECT p FROM products p",Product.class)
                    .getResultList();
        this.em.getTransaction().commit();
        return products;
    }

    @Override
    public Product findByName(String name) {
        this.em.getTransaction().begin();

        Product product = this.em.createQuery("SELECT p FROM products p WHERE p.name = :name", Product.class)
                .setParameter("name",name)
                .getSingleResult();

        this.em.getTransaction().commit();

        return product;
    }
}

package chushka.repository;

import chushka.domain.entities.Product;

/**
 * Created by Neycho Damgaliev on 2/2/2019.
 */
public interface ProductRepository extends GenericRepository<Product, String> {

    Product findByName(String name);
}

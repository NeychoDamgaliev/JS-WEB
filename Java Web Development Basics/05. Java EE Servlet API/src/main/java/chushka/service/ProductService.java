package chushka.service;

import chushka.domain.models.service.ProductServiceModel;

import java.util.List;

/**
 * Created by Neycho Damgaliev on 2/2/2019.
 */
public interface ProductService {

    void saveProduct(ProductServiceModel productServiceModel);

    List<ProductServiceModel> findAllProducts();

    ProductServiceModel findProductByName(String name);
}

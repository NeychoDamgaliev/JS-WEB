package chushka.service;

import chushka.domain.entities.Product;
import chushka.domain.entities.Type;
import chushka.domain.models.service.ProductServiceModel;
import chushka.repository.ProductRepository;
import chushka.utils.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 2/2/2019.
 */
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProduct(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        product.setType(Type.valueOf(productServiceModel.getType()));
        this.productRepository.save(product);
    }

    @Override
    public List<ProductServiceModel> findAllProducts() {
        return this.productRepository.findAll()
                .stream().map(p-> {
                    ProductServiceModel psm = this.modelMapper.map(p,ProductServiceModel.class);
                    psm.setType(p.getType().name());
                    return psm;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel findProductByName(String name) {
        Product productByName = this.productRepository.findByName(name);
        ProductServiceModel psm = this.modelMapper.map(productByName,ProductServiceModel.class);
        psm.setType(productByName.getType().name());

        return psm;
    }
}

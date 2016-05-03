package shop.repositories;

import org.springframework.data.repository.CrudRepository;
import shop.domain.product.Product;
import shop.domain.product.ProductCode;

/**
 * Created by Nirav on 03/05/2016.
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {

    Product findByProductCode(ProductCode code);
}

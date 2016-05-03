package shop.hateoas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import shop.domain.product.Product;

import java.util.List;

/**
 * Created by Nirav on 03/05/2016.
 */
public class ProductRepresentation extends ResourceSupport {

    private Iterable<Product> products;

    @JsonCreator
    public ProductRepresentation(@JsonProperty(value = "products") Iterable<Product> products) {
        this.products = products;
    }

    public Iterable<Product> getProducts() {
        return products;
    }

    public void setProducts(Iterable<Product> products) {
        this.products = products;
    }
}

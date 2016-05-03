package shop.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.domain.product.Product;
import shop.hateoas.ProductRepresentation;
import shop.services.ProductService;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nirav on 03/05/2016.
 */
@RestController
@RequestMapping("/products")
public class ProductsResource extends BaseResource {

    private ProductService service;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<ProductRepresentation> get() {
        ProductRepresentation rep = new ProductRepresentation(service.getAllProducts());
        return new ResponseEntity(rep, HttpStatus.OK);
    }

    @Autowired
    public void setService(ProductService service) {
        this.service = service;
    }
}

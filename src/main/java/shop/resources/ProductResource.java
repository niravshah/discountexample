package shop.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.domain.product.Product;
import shop.hateoas.ProductRepresentation;
import shop.services.ProductService;

import java.util.Arrays;

/**
 * Created by Nirav on 03/05/2016.
 */
@RestController
@RequestMapping("/product")
public class ProductResource extends BaseResource {

    private ProductService service;

    @RequestMapping(path = "/{product}",method = RequestMethod.GET)
    public HttpEntity<ProductRepresentation> getProduct(@PathVariable(value = "product") String code){
        Product prod =  service.findByProductCode(code);
        if(prod != null) {
            ProductRepresentation rep = new ProductRepresentation(Arrays.asList(prod));
            return new ResponseEntity(rep, HttpStatus.OK);
        }else{
            return new ResponseEntity<ProductRepresentation>(HttpStatus.NOT_FOUND);
        }
    }

    @Autowired
    public void setService(ProductService service) {
        this.service = service;
    }
}

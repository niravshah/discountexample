package shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.domain.cart.ShoppingCart;
import shop.domain.product.Product;
import shop.domain.product.ProductCode;
import shop.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nirav on 26/04/2016.
 */
@Service
public class ProductService {

    private ProductRepository repo;

    private static List<Product> allProducts;

    public ProductService(){
        allProducts = new ArrayList<>();
        for(ProductCode code : ProductCode.values()){
            allProducts.add(new Product(new BigDecimal(10.00),code));
        }
    }


    public Iterable<Product> getAllProducts(){
        return repo.findAll();
    }

    public Product findByProductCode(String code){
        for(ProductCode pc : ProductCode.values()) {
            if (pc.name().equals(code)) {
                return repo.findByProductCode(pc);
            }
        }
        return null;
    }

    public Product getProductByCode(String code){
        for(ProductCode pc : ProductCode.values()){
            if(pc.name().equals(code)){
                for(Product product: allProducts){
                    if(product.getProductCode().equals(pc)){
                        return product;
                    }
                }
            }
        }
        return null;
    }

    public ShoppingCart addProductToCart(String code, Integer qty, ShoppingCart cart){
        Product product = getProductByCode(code);
        if(product != null) {
            cart.addNewOrderLine(product, qty);
            return cart;
        }else{
            return null;
        }
    }

    @Autowired
    public void setRepo(ProductRepository repo) {
        this.repo = repo;
    }
}

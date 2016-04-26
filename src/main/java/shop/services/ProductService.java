package shop.services;

import org.springframework.stereotype.Service;
import shop.domain.cart.ShoppingCart;
import shop.domain.product.Product;
import shop.domain.product.ProductCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nirav on 26/04/2016.
 */
@Service
public class ProductService {

    private static List<Product> allProducts;

    public ProductService(){
        allProducts = new ArrayList<>();
        for(ProductCode code : ProductCode.values()){
            allProducts.add(new Product(new BigDecimal(10.00),code));
        }
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
}

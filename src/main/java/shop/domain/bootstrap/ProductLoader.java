package shop.domain.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import shop.domain.product.Product;
import shop.domain.product.ProductCode;
import shop.repositories.ProductRepository;

import java.math.BigDecimal;

/**
 * Created by Nirav on 03/05/2016.
 */
@Component
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {

    private ProductRepository repo;

    @Autowired
    public void setRepo(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for(ProductCode code : ProductCode.values()){
            repo.save(new Product(new BigDecimal(10.00),code));
        }
    }
}

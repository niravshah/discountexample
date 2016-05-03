package shop.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import shop.services.ProductService;

/**
 * Created by Nirav on 03/05/2016.
 */
@Profile("MockService")
@Configuration
public class MockServicesConfig {

    @Bean
    @Primary
    public ProductService getProductService(){
        return Mockito.mock(ProductService.class);
    }
}

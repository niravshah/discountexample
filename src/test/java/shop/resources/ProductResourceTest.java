package shop.resources;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import shop.Application;
import shop.domain.product.Product;
import shop.domain.product.ProductCode;
import shop.services.ProductService;

import java.math.BigDecimal;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.mockito.Matchers.any;

/**
 * Created by Nirav on 03/05/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@ActiveProfiles("MockService")
public class ProductResourceTest {

    @Autowired
    private ProductService service;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void validProduct() {
        Mockito.when(service.findByProductCode("Widget")).thenReturn(new Product(new BigDecimal(100), ProductCode.Widget));
        when().get("/api/product/Widget").then().statusCode(200).contentType(ContentType.JSON).body("products[0].cost", Matchers.equalTo(100));
    }

    @Test
    public void verifyHttpNotFoundForANonExistentProduct(){
        Mockito.when(service.findByProductCode(any())).thenReturn(null);
        when().get("/api/product/Baz").then().statusCode(404);
    }

}

package shop.resources;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import shop.Application;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Nirav on 26/04/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class CheckoutResourceTest {

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() {

        RestAssured.port = port;

    }

    @Test
    public void checkout() {
        String product = "Widget";
        Response post = given()
                .param("product", product)
                .param("qty", 10)
                .post("/api/cart");

        String jsessionId = post.getCookie("JSESSIONID");

        given()
                .cookie("JSESSIONID=" + jsessionId)
                .then()
                .get("/api/checkout")
                .then()
                .contentType(JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("message", Matchers.is(CheckoutResource.MESSAGE))
                .body("cart", is(not(empty())))
                .body("cart[0].product.productCode", Matchers.is(product))
                .body("qualifyingDiscounts.product_line_discounts[0].discountType",Matchers.is("THREE_FOR_TWO"));

    }
}

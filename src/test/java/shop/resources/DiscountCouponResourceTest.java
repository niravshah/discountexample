package shop.resources;

import com.jayway.restassured.RestAssured;
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

import java.util.HashMap;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Nirav on 26/04/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class DiscountCouponResourceTest {

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void addDiscountCode() {

        String testCode = "ORBIT50";
        HashMap cartLink =
                given().
                        param("code", testCode).
                        when().
                        post("/api/discount").
                        then().
                        contentType(JSON).
                        statusCode(HttpStatus.SC_OK).
                        body("message", Matchers.is(String.format(DiscountCouponResource.ADD_MESSAGE, testCode))).
                        body("coupons", Matchers.contains("ORBIT50")).
                        extract().
                        path("_links");

        assertThat(cartLink.size(), equalTo(4));

    }
}

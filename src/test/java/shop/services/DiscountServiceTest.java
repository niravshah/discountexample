package shop.services;

import com.jayway.restassured.RestAssured;
import org.hamcrest.number.BigDecimalCloseTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import shop.Application;
import shop.domain.discounts.CartDiscount;
import shop.domain.discounts.ProductLineDiscount;
import shop.domain.order.OrderLine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static shop.domain.order.OrderLineBuilder.orderLineBuilder;
import static shop.domain.product.ProductBuilder.productBuilder;

/**
 * Created by Nirav on 26/04/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class DiscountServiceTest {

    @Autowired
    private DiscountService service;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void testProductLevelDiscount() {
        OrderLine orderLine = orderLineBuilder().product(productBuilder().cost(new BigDecimal("20")).build()).quantity(20).build();
        List<OrderLine> lines = new ArrayList<>();
        lines.add(orderLine);
        List<ProductLineDiscount> productLevelDiscount = service.applyProductLevelDiscounts(lines);
        assertThat(productLevelDiscount.size(),  equalTo(1));
        ProductLineDiscount productLineDiscount = productLevelDiscount.get(0);
        assertThat(productLineDiscount.getDiscountType(), equalTo("THREE_FOR_TWO"));
        assertThat(productLineDiscount.getDiscountQuantity(), equalTo(6));
    }



    @Test
    public void testCartLevelDiscountWithoutCoupons(){
        CartDiscount cartDiscount = service.applyCartLevelDiscounts(new BigDecimal(80.00), new ArrayList<>());
        assertThat(cartDiscount, is(not(nullValue())));
        assertThat(cartDiscount.getDiscountType(), equalTo("TEN_PERCENT_OFF_FIFTY"));
        assertThat(cartDiscount.getDiscountTotalBy(), BigDecimalCloseTo.closeTo(new BigDecimal(8), new BigDecimal(0.1)));
    }

    @Test
    public void testCartLevelDiscountWithCoupons(){
        List<String> list = new ArrayList<>();
        list.add("ORBIT50");
        CartDiscount cartDiscount = service.applyCartLevelDiscounts(new BigDecimal(80.00), list);
        assertThat(cartDiscount, is(not(nullValue())));
        assertThat(cartDiscount.getDiscountType(), equalTo("COUPON_ORBIT_50"));
        assertThat(cartDiscount.getDiscountTotalBy(), BigDecimalCloseTo.closeTo(new BigDecimal(40), new BigDecimal(0.1)));
    }
}

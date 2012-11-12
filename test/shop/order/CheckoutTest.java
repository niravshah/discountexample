package shop.order;

import org.junit.Test;

import java.math.BigDecimal;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static shop.order.OrderBuilder.orderBuilder;
import static shop.order.OrderLineBuilder.orderLineBuilder;
import static shop.product.ProductBuilder.productBuilder;

public class CheckoutTest {

    @Test
    public void shouldCalculateOrderTotal(){
        // Given
        Order order = orderBuilder().orderLines(newArrayList(
                orderLineBuilder().product(productBuilder().cost(new BigDecimal("20")).build()).quantity(2).build(),
                orderLineBuilder().product(productBuilder().cost(new BigDecimal("60")).build()).quantity(1).build())
        ).build();

        // When
        BigDecimal orderTotal = new Checkout(order).total();


        // Then
        assertThat(orderTotal, is(new BigDecimal("100")));
    }

    @Test
    public void shouldDiscountTotalBy10PercentWhenOrderTotalAtLeast50(){
        // Given

        // When


        // Then
    }

    @Test
    public void shouldDiscountTotalThreeForTwo(){
        // Given

        // When


        // Then
    }

    @Test
    public void shouldDiscountTotalWithVoucher(){
        // Given

        // When


        // Then
    }




}

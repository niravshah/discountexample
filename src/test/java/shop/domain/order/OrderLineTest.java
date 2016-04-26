package shop.domain.order;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static shop.domain.order.OrderLineBuilder.orderLineBuilder;
import static shop.domain.product.ProductBuilder.productBuilder;

public class OrderLineTest {
    @Test
    public void shouldCalculateCostOfTwoProducts() {
        // Given
        OrderLine orderLine = orderLineBuilder().product(productBuilder().cost(new BigDecimal("20")).build()).quantity(2).build();

        // When
        BigDecimal total = orderLine.getTotal();

        // Then
        assertThat(total, is(new BigDecimal("40")));
    }
}

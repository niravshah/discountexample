package shop.domain.order;

import java.math.BigDecimal;

public class Checkout {
    private final Order order;

    public Checkout(Order order) {
        this.order = order;
    }

    public BigDecimal total() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderLine orderLine : order.getOrderLines()) {
            total = total.add(orderLine.getTotal());
        }
        return total;
    }
}

package shop.order;

import java.util.List;

public class Order {
    private final List<OrderLine> orderLines;

    public Order(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }
}

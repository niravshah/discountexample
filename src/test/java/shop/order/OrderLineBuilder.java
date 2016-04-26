package shop.order;

import shop.product.Product;
import util.generator.Random;

import static shop.product.ProductBuilder.productBuilder;

public class OrderLineBuilder {


    private Product product = productBuilder().build();
    private Integer quantity = Random.integer.next();

    public static OrderLineBuilder orderLineBuilder() {
        return new OrderLineBuilder();

    }

    public OrderLine build() {
        return new OrderLine(product, quantity);
    }

    public OrderLineBuilder product(Product product) {
        this.product = product;
        return this;
    }

    public OrderLineBuilder quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}

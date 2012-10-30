package shop.order;

import shop.product.Product;

import java.math.BigDecimal;

public class OrderLine {

    private final Product product;
    private final Integer quantity;

    public OrderLine(Product product, Integer quantity) {
        this.quantity = quantity;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getTotal() {
        return product.getCost().multiply(new BigDecimal(quantity));
    }
}

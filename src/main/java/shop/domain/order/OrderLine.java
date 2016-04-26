package shop.domain.order;

import shop.domain.product.Product;

import java.math.BigDecimal;

public class OrderLine {

    private final Product product;
    private final Integer quantity;
    private Integer discountQty=0;


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

    public void setDiscountQty(Integer discountQty) {
        this.discountQty = discountQty;
    }
}

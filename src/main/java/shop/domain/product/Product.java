package shop.domain.product;

import java.math.BigDecimal;

public class Product {
    private final BigDecimal cost;
    private final ProductCode productCode;

    public Product(BigDecimal cost, ProductCode productCode) {

        this.cost = cost;
        this.productCode = productCode;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public ProductCode getProductCode() {
        return productCode;
    }
}

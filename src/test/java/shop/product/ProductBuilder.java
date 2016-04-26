package shop.product;

import util.generator.Random;

import java.math.BigDecimal;

public class ProductBuilder {


    private BigDecimal cost = Random.bigDecimal.next();
    private ProductCode productCode = Random.values(ProductCode.values()).next();

    public Product build() {
        return new Product(cost, productCode);
    }

    public static ProductBuilder productBuilder() {
        return new ProductBuilder();
    }

    public ProductBuilder cost(BigDecimal cost) {
        this.cost = cost;
        return this;
    }

    public ProductBuilder productCode(ProductCode productCode) {
        this.productCode = productCode;
        return this;
    }
}

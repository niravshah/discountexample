package shop.domain.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private final BigDecimal cost;
    private final ProductCode productCode;

    protected Product(){
        this.id = null;
        this.cost = null;
        this.productCode = null;
    }
    public Product(BigDecimal cost, ProductCode productCode) {
        this.cost = cost;
        this.productCode = productCode;
    }


    public Integer getId() {
        return id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public ProductCode getProductCode() {
        return productCode;
    }


}

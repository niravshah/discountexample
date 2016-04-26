package shop.domain.discounts.impl;

import shop.domain.discounts.CartDiscount;

import java.math.BigDecimal;

/**
 * Created by Nirav on 26/04/2016.
 */
public class PercentDiscount extends AbstractDiscount implements CartDiscount {

    private BigDecimal discountTotalBy;
    private BigDecimal percentDiscount;

    public PercentDiscount(String discountType, BigDecimal dTotal, BigDecimal percentDiscount) {
        this.setDiscountType(discountType);
        this.discountTotalBy = dTotal.multiply(percentDiscount);
        this.percentDiscount = percentDiscount;

    }

    public void setDiscountTotalBy(BigDecimal discountTotalBy) {
        this.discountTotalBy = discountTotalBy;
    }

    public BigDecimal getDiscountTotalBy() {
        return discountTotalBy;
    }

    public BigDecimal getPercentDiscount() {
        return percentDiscount;
    }

    public void setPercentDiscount(BigDecimal percentDiscount) {
        this.percentDiscount = percentDiscount;
    }
}

package shop.domain.discounts.impl;

import shop.domain.discounts.CartDiscount;

import java.math.BigDecimal;

/**
 * Created by Nirav on 26/04/2016.
 */
public class CouponDiscount extends AbstractDiscount implements CartDiscount {
    private BigDecimal discountedTotal;
    private BigDecimal percentDiscount;

    public CouponDiscount(BigDecimal discountedTotal, BigDecimal percentDiscount) {
        this.discountedTotal = discountedTotal;
        this.percentDiscount = percentDiscount;
    }

    public BigDecimal getDiscountTotalBy() {
        return discountedTotal;
    }

    public void setDiscountedTotal(BigDecimal discountedTotal) {
        this.discountedTotal = discountedTotal;
    }

    public BigDecimal getPercentDiscount() {
        return percentDiscount;
    }

    public void setPercentDiscount(BigDecimal percentDiscount) {
        this.percentDiscount = percentDiscount;
    }
}

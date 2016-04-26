package shop.domain.discounts.impl;

import shop.domain.discounts.Discount;

/**
 * Created by Nirav on 26/04/2016.
 */
public abstract class AbstractDiscount implements Discount {

    private String discountType;

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }


}

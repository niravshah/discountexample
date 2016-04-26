package shop.domain.discounts.impl;

import shop.domain.discounts.ProductLineDiscount;
import shop.domain.order.OrderLine;

/**
 * Created by Nirav on 26/04/2016.
 */
public class MultibuyDiscount extends AbstractDiscount implements ProductLineDiscount {

    private OrderLine line;
    private Integer discountQuantity;
    private String discountType;


    public MultibuyDiscount(OrderLine line, Integer qty, String discountType) {
        this.setLine(line);
        this.setDiscountQuantity(qty);
        this.setDiscountType(discountType);
    }

    public OrderLine getLine() {
        return line;
    }

    public void setLine(OrderLine line) {
        this.line = line;
    }


    public Integer getDiscountQuantity() {
        return discountQuantity;
    }

    public void setDiscountQuantity(Integer discountQuantity) {
        this.discountQuantity = discountQuantity;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    @Override
    public String getDiscountType() {
        return discountType;
    }
}

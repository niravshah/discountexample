package shop.domain.discounts;

import shop.domain.order.OrderLine;

/**
 * Created by Nirav on 26/04/2016.
 */
public interface ProductLineDiscount extends Discount {

    OrderLine getLine();
    Integer getDiscountQuantity();

}
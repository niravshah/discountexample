package shop.domain.discounts;

import java.math.BigDecimal;

/**
 * Created by Nirav on 26/04/2016.
 */
public interface CartDiscount extends Discount {
    BigDecimal getDiscountTotalBy();
}

package shop.domain.discounts.impl;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Nirav on 26/04/2016.
 */
public class CartDiscountInput {


    public BigDecimal cartTotal;
    public List<String> codes;

    public CartDiscountInput(BigDecimal cartTotal, List<String> codes) {
        this.cartTotal = cartTotal;
        this.codes = codes;
    }

    public BigDecimal getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(BigDecimal cartTotal) {
        this.cartTotal = cartTotal;
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }
}

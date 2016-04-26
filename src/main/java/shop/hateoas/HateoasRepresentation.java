package shop.hateoas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import shop.domain.discounts.CartDiscount;
import shop.domain.discounts.ProductLineDiscount;
import shop.domain.order.OrderLine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nirav on 26/04/2016.
 */
public class HateoasRepresentation extends ResourceSupport {

    String message;
    List<OrderLine> cart;
    List<String> coupons;
    Map<String, Object> qualifyingDiscounts = new HashMap<String, Object>();
    BigDecimal cartTotal;

    @JsonCreator
    public HateoasRepresentation(@JsonProperty("message") String msg,
                                 @JsonProperty("cart") List<OrderLine> cart,
                                 @JsonProperty("discounts") List<ProductLineDiscount> discount,
                                 @JsonProperty("cartDiscount") CartDiscount cartdiscount,
                                 @JsonProperty("cartToal") BigDecimal cTotal,
                                 @JsonProperty("coupons") List<String> coupons) {
        this.message = msg;
        this.cart = cart;

        this.getQualifyingDiscounts().put("product_line_discounts", discount);
        this.getQualifyingDiscounts().put("cart_discounts", cartdiscount);

        this.cartTotal = cTotal;
        this.coupons = coupons;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OrderLine> getCart() {
        return cart;
    }

    public void setCart(List<OrderLine> cart) {
        this.cart = cart;
    }

    public BigDecimal getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(BigDecimal cartTotal) {
        this.cartTotal = cartTotal;
    }

    public List<String> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<String> coupons) {
        this.coupons = coupons;
    }

    public Map<String, Object> getQualifyingDiscounts() {
        return qualifyingDiscounts;
    }

    public void setQualifyingDiscounts(Map<String, Object> qualifyingDiscounts) {
        this.qualifyingDiscounts = qualifyingDiscounts;
    }
}

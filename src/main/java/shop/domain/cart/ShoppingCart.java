package shop.domain.cart;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import shop.domain.discounts.CartDiscount;
import shop.domain.discounts.ProductLineDiscount;
import shop.domain.order.Order;
import shop.domain.order.OrderLine;
import shop.domain.product.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nirav on 26/04/2016.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class ShoppingCart extends Order implements Serializable {

    private BigDecimal cartDiscountBy = new BigDecimal(0);
    private List<String> discountCoupons = new ArrayList<>();
    private List<ProductLineDiscount> productLevelDiscounts = new ArrayList();
    private CartDiscount cartDiscount;

    public ShoppingCart() {
        super(new ArrayList<>());
    }


    public void addNewOrderLine(Product product, Integer qty) {
        OrderLine currOrderLine = null;
        for (OrderLine line : getOrderLines()) {
            if (line.getProduct().equals(product)) {
                qty += line.getQuantity();
                currOrderLine = line;
            }
        }

        if (currOrderLine != null) getOrderLines().remove(currOrderLine);
        getOrderLines().add(new OrderLine(product, qty));
    }

    public BigDecimal getCartTotal() {
        List<OrderLine> lines = this.getOrderLines();
        BigDecimal cartTotal = new BigDecimal(0);
        for (OrderLine line : lines) {
            cartTotal = cartTotal.add(line.getTotal());
        }
        cartTotal = cartTotal.subtract(cartDiscountBy);
        return cartTotal;
    }

    public BigDecimal getCartDiscountBy() {
        return cartDiscountBy;
    }

    public void setCartDiscountBy(BigDecimal cartDiscountBy) {
        this.cartDiscountBy = cartDiscountBy;
    }

    public List<ProductLineDiscount> getProductLevelDiscounts() {
        return productLevelDiscounts;
    }

    public void setProductLevelDiscounts(List<ProductLineDiscount> productLevelDiscounts) {
        this.productLevelDiscounts = productLevelDiscounts;
    }

    public CartDiscount getCartDiscount() {
        return cartDiscount;
    }

    public void setCartDiscount(CartDiscount cartDiscount) {
        this.cartDiscount = cartDiscount;
    }

    public List<String> getDiscountCoupons() {
        return discountCoupons;
    }

    public void setDiscountCoupons(List<String> discountCoupons) {
        this.discountCoupons = discountCoupons;
    }

    public void addDiscountCoupon(String discountCode){
        this.getDiscountCoupons().add(discountCode);
    }

}

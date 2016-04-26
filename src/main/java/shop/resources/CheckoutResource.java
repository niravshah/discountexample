package shop.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import shop.domain.cart.ShoppingCart;
import shop.domain.discounts.CartDiscount;
import shop.domain.discounts.ProductLineDiscount;
import shop.hateoas.HateoasRepresentation;
import shop.services.DiscountService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Nirav on 26/04/2016.
 */
@RestController
@RequestMapping("/checkout")
public class CheckoutResource extends BaseResource {

    public static final String MESSAGE = "Checkout";

    @Autowired
    private ShoppingCart cart;

    @Autowired
    private DiscountService service;


    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<HateoasRepresentation> checkout() {

        List<ProductLineDiscount> discounts = service.applyProductLevelDiscounts(cart.getOrderLines());
        cart.setProductLevelDiscounts(discounts);

        CartDiscount cartDiscount = service.applyCartLevelDiscounts(cart.getCartTotal(), cart.getDiscountCoupons());
        if (cartDiscount != null) {
            cart.setCartDiscountBy(cartDiscount.getDiscountTotalBy());
            cart.setCartDiscount(cartDiscount);
        }

        HateoasRepresentation rep = getHateoasRepresentation(MESSAGE, cart);
        addHomeLink(rep);
        addCartLink(rep);
        addDiscountCouponLink(rep);
        rep.add(linkTo(methodOn(ShoppingCartResource.class).get()).withSelfRel());
        return new ResponseEntity<>(rep, HttpStatus.OK);
    }


}

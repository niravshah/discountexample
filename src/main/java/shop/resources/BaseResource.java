package shop.resources;

import org.springframework.hateoas.ResourceSupport;
import shop.domain.cart.ShoppingCart;
import shop.hateoas.HateoasRepresentation;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Nirav on 26/04/2016.
 */
public abstract class BaseResource {

    public HateoasRepresentation getHateoasRepresentation(String message, ShoppingCart cart) {
        return new HateoasRepresentation(message
                , cart.getOrderLines()
                , cart.getProductLevelDiscounts()
                , cart.getCartDiscount()
                , cart.getCartTotal()
                , cart.getDiscountCoupons());
    }

    public void addHomeLink(ResourceSupport rep) {
        rep.add(linkTo(methodOn(HomeResource.class).home()).withRel("home"));
    }
}

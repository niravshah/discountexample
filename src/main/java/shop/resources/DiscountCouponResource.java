package shop.resources;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.domain.cart.ShoppingCart;
import shop.hateoas.HateoasRepresentation;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Nirav on 26/04/2016.
 */
@RestController
@RequestMapping("/discount")
@Api(value = "ade-3", description = "Discount Coupon Resource")
public class DiscountCouponResource extends BaseResource {

    public static String GET_MESSAGE = "Current Discount Codes applied to your cart : %s";
    public static String ADD_MESSAGE = "Discount Code : %s : added to the cart";

    @Autowired
    public ShoppingCart cart;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get Applied Discount Coupons", nickname = "Get Discounts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = HateoasRepresentation.class)})
    public HttpEntity<HateoasRepresentation> get() {

        String currentCoupons = cart.getDiscountCoupons().toString();
        HateoasRepresentation rep = getHateoasRepresentation(String.format(GET_MESSAGE, currentCoupons), cart);
        addHomeLink(rep);
        addCartLink(rep);
        rep.add(linkTo(methodOn(DiscountCouponResource.class).get()).withSelfRel());
        return new ResponseEntity(rep, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Apply New Discount Coupon", nickname = "Apply Coupon")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = HateoasRepresentation.class)})
    public HttpEntity<HateoasRepresentation> add(@RequestParam("code")
                                                 @ApiParam(name = "code", required = true,value = "Discount Code to Apply")
                                                         String discountCode) {
        cart.addDiscountCoupon(discountCode);
        HateoasRepresentation rep = getHateoasRepresentation(String.format(ADD_MESSAGE, discountCode), cart);
        addHomeLink(rep);
        addProductLinks(rep);
        addCartLink(rep);
        rep.add(linkTo(methodOn(DiscountCouponResource.class).add(discountCode)).withSelfRel());
        return new ResponseEntity(rep, HttpStatus.OK);
    }
}

package shop.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import shop.domain.cart.ShoppingCart;
import shop.hateoas.HateoasRepresentation;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Nirav on 26/04/2016.
 */
@RestController
@RequestMapping("/")
@Api(value = "ade-1", description = "Api Home")
public class HomeResource extends BaseResource {

    public static String MESSAGE = "Welcome! Please navigate using the Links below. All qualifying discounts applied at checkout";

    @Autowired
    private ShoppingCart cart;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Api Home", nickname = "Api Home")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = HateoasRepresentation.class)})
    public HttpEntity<HateoasRepresentation> home(){
        HateoasRepresentation rep = getHateoasRepresentation(MESSAGE, cart);
        addCartLink(rep);
        addDiscountCouponLink(rep);
        rep.add(linkTo(methodOn(HomeResource.class).home()).withSelfRel());
        return new ResponseEntity<>(rep, HttpStatus.OK);
    }

}
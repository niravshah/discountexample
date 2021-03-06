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
import shop.services.ProductService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Nirav on 26/04/2016.
 */
@RestController
@RequestMapping("/cart")
@Api(value = "ade-2", description = "Shopping Cart")
public class ShoppingCartResource extends BaseResource {

    public static String GET_MESSAGE = "Cart Contents";
    public static String ADD_MESSAGE = "Product %s added to cart";

    @Autowired
    private ShoppingCart cart;

    @Autowired
    private ProductService service;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get Shopping Cart", nickname = "Get Cart")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = HateoasRepresentation.class)})
    public HttpEntity<HateoasRepresentation> get() {
        HateoasRepresentation rep = getHateoasRepresentation(GET_MESSAGE, cart);
        rep.add(linkTo(methodOn(ShoppingCartResource.class).get()).withSelfRel());
        addHomeLink(rep);
        addProductLinks(rep);
        return new ResponseEntity<>(rep, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Add Product to Cart", nickname = "Add Product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = HateoasRepresentation.class)})
    public HttpEntity<HateoasRepresentation> add(@RequestParam(value = "product") @ApiParam(name="product", value = "Product Code to Add", required = true)String product,
                                                 @ApiParam(name="qty", value = "Quantity",defaultValue = "1")@RequestParam(value = "qty", defaultValue = "1", required = false) Integer qty) {
        service.addProductToCart(product, qty, cart);
        HateoasRepresentation rep = getHateoasRepresentation(String.format(ADD_MESSAGE, product), cart);
        addHomeLink(rep);
        addProductLinks(rep);
        rep.add(linkTo(methodOn(ShoppingCartResource.class).add(product,1)).withSelfRel());
        return new ResponseEntity<>(rep, HttpStatus.OK);
    }
}

package shop.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.domain.discounts.CartDiscount;
import shop.domain.discounts.Discount;
import shop.domain.discounts.ProductLineDiscount;
import shop.domain.discounts.impl.AbstractDiscount;
import shop.domain.discounts.impl.CartDiscountInput;
import shop.domain.order.OrderLine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nirav on 26/04/2016.
 */
@Service
public class DiscountService {

    private final KieContainer kieContainer;

    @Autowired
    public DiscountService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public List<ProductLineDiscount> applyProductLevelDiscounts(List<OrderLine> lines) {
        KieSession kieSession = kieContainer.newKieSession("DiscountRulesSession");
        List<ProductLineDiscount> discounts = new ArrayList<>();

        for (OrderLine line : lines) {
            kieSession.insert(line);
            kieSession.fireAllRules();
            ProductLineDiscount discount = (ProductLineDiscount) findDiscounts(kieSession);
            if (discount != null) {
                line.setDiscountQty(discount.getDiscountQuantity());
                discounts.add(discount);
            }
        }
        kieSession.dispose();
        return discounts;
    }

    public CartDiscount applyCartLevelDiscounts(BigDecimal cartTotal, List<String> discountCoupons) {
        KieSession kieSession = kieContainer.newKieSession("CartDiscountRulesSession");
        kieSession.insert(new CartDiscountInput(cartTotal, discountCoupons));
        kieSession.fireAllRules();
        CartDiscount discount = (CartDiscount) findDiscounts(kieSession);
        kieSession.dispose();
        return discount;
    }


    private Discount findDiscounts(KieSession kieSession) {
        ObjectFilter discountFilter = new ObjectFilter() {
            @Override
            public boolean accept(Object object) {
                if (AbstractDiscount.class.equals(object.getClass().getSuperclass())) return true;
                return false;
            }
        };

        List<Discount> facts = new ArrayList<>();
        for (FactHandle handle : kieSession.getFactHandles(discountFilter)) {
            facts.add((Discount) kieSession.getObject(handle));
        }
        if (facts.size() == 0) {
            return null;
        }
        //return the first eligible discount at product line to avoid the same product line being discounted multiple times.
        return facts.get(0);
    }

}

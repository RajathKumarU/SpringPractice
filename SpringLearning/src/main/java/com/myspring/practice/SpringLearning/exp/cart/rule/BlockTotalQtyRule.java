package com.myspring.practice.SpringLearning.exp.cart.rule;

import com.myspring.practice.SpringLearning.exp.cart.Item;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BlockTotalQtyRule implements RestrictionRule {

    private final int totalQtyThresholdPerProduct;

    @Override
    public boolean isMet(List<Item> cartItems) {

        if(cartItems == null || cartItems.isEmpty()){
            return true;
        }

        Map<String, Integer> productQuantities = cartItems.stream()
                .collect(Collectors.groupingBy(
                        Item::productId, Collectors.summingInt(Item::qty)
                ));

        return productQuantities.values().stream()
                .noneMatch(qty -> qty > totalQtyThresholdPerProduct);
    }
}

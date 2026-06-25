package com.myspring.practice.SpringLearning.exp.cart.rule;

import com.myspring.practice.SpringLearning.exp.cart.Item;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BlockCategoryQtyRule implements RestrictionRule {

    private final String restrictedCategory;
    private final int categoryThresholdQty;

    @Override
    public boolean isMet(List<Item> cartItems) {
        if (cartItems == null || cartItems.isEmpty()) return true;

        int catTotal = cartItems.stream()
                .filter(a -> a.category().toLowerCase().equalsIgnoreCase(restrictedCategory))
                .mapToInt(Item::qty)
                .sum();

        return catTotal <= categoryThresholdQty;
    }
}

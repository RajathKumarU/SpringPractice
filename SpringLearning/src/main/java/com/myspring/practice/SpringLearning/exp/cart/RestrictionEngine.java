package com.myspring.practice.SpringLearning.exp.cart;

import com.myspring.practice.SpringLearning.exp.cart.rule.RestrictionRule;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RestrictionEngine {

    private final List<RestrictionRule> rules;

    public RestrictionStatus evaluateCart(List<Item> cartItems) {
        if(cartItems == null || cartItems.isEmpty()) return RestrictionStatus.MET;

        for(RestrictionRule rule : rules){
            if(!rule.isMet(cartItems)) return RestrictionStatus.BREACHED;
        }

        return RestrictionStatus.MET;
    }
}

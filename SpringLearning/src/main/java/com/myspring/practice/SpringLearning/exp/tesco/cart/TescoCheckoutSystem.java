package com.myspring.practice.SpringLearning.exp.tesco.cart;

import com.myspring.practice.SpringLearning.exp.tesco.cart.rule.BlockCategoryQtyRule;
import com.myspring.practice.SpringLearning.exp.tesco.cart.rule.BlockTotalQtyRule;
import com.myspring.practice.SpringLearning.exp.tesco.cart.rule.RestrictionRule;

import java.util.List;
import java.util.logging.Logger;

public class TescoCheckoutSystem {

    public static final Logger LOGGER = Logger.getLogger(TescoCheckoutSystem.class.getName());

    static void main() {
        // Initialize Cart
        List<Item> cart = List.of(
                new Item("1", "Paracetamol", 3),
                new Item("2", "analgesic", 3),
                new Item("3", "chocolate", 7),
                new Item("4", "Paracetamol", 2)
        );

        // Bootstrap the Engine with Rules (In a real app, this would be injected via Spring/Guice)
        List<RestrictionRule> activeRules = List.of(
                new BlockTotalQtyRule(10),
                new BlockCategoryQtyRule("Paracetamol", 5)
        );

        RestrictionEngine engine = new RestrictionEngine(activeRules);

        // Evaluate
        RestrictionStatus status = engine.evaluateCart(cart);
        LOGGER.info("Cart Restriction Status: " + status);
    }
}

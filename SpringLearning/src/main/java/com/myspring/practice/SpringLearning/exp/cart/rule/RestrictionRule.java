package com.myspring.practice.SpringLearning.exp.cart.rule;

import com.myspring.practice.SpringLearning.exp.cart.Item;
import java.util.List;

public interface RestrictionRule {

    boolean isMet(List<Item> cartItems);
}

package com.myspring.practice.SpringLearning.exp.tesco.cart.rule;

import com.myspring.practice.SpringLearning.exp.tesco.cart.Item;
import java.util.List;

public interface RestrictionRule {

    boolean isMet(List<Item> cartItems);
}

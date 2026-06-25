package com.myspring.practice.SpringLearning.exp.cart;

public record Item (String productId, String category, int qty) {
    public Item {
        if (qty < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (productId == null || category == null) {
            throw new IllegalArgumentException("ProductId and Category cannot be null");
        }
    }
}

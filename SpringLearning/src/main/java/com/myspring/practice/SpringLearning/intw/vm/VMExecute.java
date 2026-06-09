package com.myspring.practice.SpringLearning.intw.vm;

public class VMExecute {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        machine.restock("ABC", 10, 5);
        machine.restock("XYZ", 5, 20);


        System.out.println("AddToCart: " + machine.addToCart("ABC", 5));
        System.out.println("AddToCart: " + machine.addToCart("XYZ", 4));

        System.out.println("Checkout: " + machine.checkout(150));

        System.out.println("AddToCart: " + machine.addToCart("XYZ", 2));
        System.out.println("Checkout: " + machine.checkout(150));
    }
}

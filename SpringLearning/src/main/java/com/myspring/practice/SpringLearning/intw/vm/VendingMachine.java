package com.myspring.practice.SpringLearning.intw.vm;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private Map<String, Item> itemsAvl;
    private Map<String, Integer> saleQty;
    private Map<String,Item> cart;

    public VendingMachine(){
        itemsAvl = new HashMap<>();
        saleQty = new HashMap<>();
        cart = new HashMap<>();

        // Initialise with some inventory
    }

    public void restock(String name, int qty, int price) {

        // IF exists, updateQty. else insert new record
        if(itemsAvl.containsKey(name)){
            Item item = itemsAvl.get(name);
            item.setQty(item.getQty() + qty);
        } else {
            Item item = new Item(name, qty, price);
            itemsAvl.put(name, item);
        }

        // Could check bounds if there is limit. for now out of scope

    }

    public boolean addToCart(String name, int qty) {
        // Check if qty is available, else throw error/do not continue

        if(itemsAvl.containsKey(name)){
            Item item = itemsAvl.get(name);
            int avlQty = item.getQty();
            double price = item.getPrice();

            if(avlQty < qty){
                return false;
            } else {
                Item newItem = new Item(name, qty, price);
                cart.put(name, newItem);
                return true;
            }
        }

        return false;
    }

    //InsertMoney
    private double payPossible(double amt) {
        double totalAmt = 0;
        for(Map.Entry<String, Item> entry : cart.entrySet()){
            String iName = entry.getKey();
            Item item = entry.getValue();
            int qty = item.getQty();
            double price = item.getPrice();

            double total =  qty * price;
            totalAmt += total;
        }

        return amt-totalAmt;
    }

    private boolean inventoryAvailable() {
        // Check if cartQty is available in Inventory

        if(cart.isEmpty()) {
            return false;
        }

        for(Map.Entry<String, Item> entry : cart.entrySet()){
            String iName = entry.getKey();
            Item cartItem = entry.getValue();
            Item invItem = itemsAvl.get(iName);

            int avlInvQty = invItem != null? invItem.getQty(): 0;
            int cartQty = cartItem != null? cartItem.getQty(): 0;

            if(avlInvQty < cartQty){
                return false;
            }
        }

        return true;
    }

    public boolean checkout(double amt) {
        double change = payPossible(amt);
        if(change < 0){
            return false;
            // Could throw exceptioncheckout
        }

        if (inventoryAvailable()) {
            // Check and reduce inventory and empty cart at the end;
            for(Map.Entry<String, Item> entry : cart.entrySet()){
                String cName = entry.getKey();
                Item cItem = entry.getValue();
                int qty = cItem.getQty();

                Item invItem = itemsAvl.get(cName);
                String iname = invItem.getName();
                int diffQty = invItem.getQty() - qty;
                if(diffQty == 0) {
                    itemsAvl.remove(iname);
                    System.out.println("Removed item" + iname);
                }
                else {
                    invItem.setQty(diffQty);
                    System.out.println("Removed Qty" + diffQty);
                }

                // update saleQty
                saleQty.put(cName, saleQty.getOrDefault(cName, 0) + qty);
            }

            // Clear cart
            cart = new HashMap<>();

            // find balanceAmt and dispense item & change
            dispenseChange(change);

            return true;
        } else {
            return false;
        }
    }

    public void dispenseChange(double amt) {
        System.out.println("ChangeDispensed: " + amt);
    }


}

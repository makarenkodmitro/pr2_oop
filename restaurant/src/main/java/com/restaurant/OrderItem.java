package com.restaurant;

// Клас для позиції в замовленні
public class OrderItem {
    private String dish;
    private int quantity;

    public OrderItem(String dish, int quantity) {
        this.dish = dish;
        this.quantity = quantity;
    }

    public void displayItem() {
        System.out.println(dish + " x" + quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDish() {
        return dish;
    }

}
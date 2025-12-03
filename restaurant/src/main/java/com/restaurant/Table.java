package com.restaurant;

// Клас для столика в ресторані
public class Table {
    private int number;
    private Order currentOrder;

    public Table(int number) {
        this.number = number;
    }

    public void setOrder(Order order) {
        this.currentOrder = order;
    }

    public Order getOrder() {
        return currentOrder;
    }

    public int getNumber() {
        return number;
    }
}
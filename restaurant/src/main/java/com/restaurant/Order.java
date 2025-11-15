package com.restaurant;

import com.google.inject.Inject;

// Клас для замовлення
public class Order {
    private static int nextId = 1;
    private int id;
    private String status;
    private OrderItem[] items;
    private int itemCount;
    private OrderService orderService;

    @Inject
    public Order(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setupOrder() {
        this.id = nextId++;
        this.status = "Створене";
        this.items = new OrderItem[10];
        this.itemCount = 0;
    }

    // @Inject
    // public void setOrderService(OrderService orderService) {
    //     this.orderService = orderService;
    // }


    public void addItem(String dish, int quantity) {
        if (itemCount < items.length) {
            OrderItem item = new OrderItem(dish, quantity);
            
            items[itemCount++] = item;
            orderService.saveOrderItem(item);

            System.out.println("Додано до замовлення: " + dish + " x" + quantity);
        }


    }

    public void changeStatus(String status) {
        this.status = status;
        System.out.println("Замовлення #" + id + " змінило статус на: " + status);
    }

    public void displayOrder() {
        System.out.println("Замовлення #" + id + " (" + status + "):");
        for (int i = 0; i < itemCount; i++) {
            items[i].displayItem();
        }
    }
}
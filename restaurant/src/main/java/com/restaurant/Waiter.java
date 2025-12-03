package com.restaurant;

// Клас офіціанта
public class Waiter extends ServiceParticipant implements Booking {
    private int id;

    public Waiter(String name, int id) {
        super(name);
        this.id = id;
    }

    @Override
    public void makeOrder() {
        // Не використовується офіціантом
    }

    @Override
    public void confirmOrder() {
        System.out.println("Офіціант " + name + " підтверджує замовлення");
    }

    public void checkAllergies() {
        System.out.println("Офіціант " + name + " перевіряє алергени");
    }

    @Override
    public void markReady() {
        // Не використовується офіціантом
    }

    @Override
    public void displayInfo() {
        System.out.println("Офіціант: " + name + ", ID: " + id);
    }
}
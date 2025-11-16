package com.restaurant;

// Клас шеф-кухаря
public class Chef extends ServiceParticipant implements Booking {
    private String specialization;

    public Chef(String name, String specialization) {
        super(name);
        this.specialization = specialization;
    }

    @Override
    public void makeOrder() {
        // Не використовується шеф-кухарем
    }

    @Override
    public void confirmOrder() {
        // Не використовується шеф-кухарем
    }

    @Override
    public void markReady() {
        System.out.println("Шеф-кухар " + name + " позначає замовлення як готове");
    }

    @Override
    public void displayInfo() {
        System.out.println("Шеф-кухар: " + name + ", Спеціалізація: " + specialization);
    }
}
package com.restaurant;

import com.google.inject.Guice;
import com.google.inject.Injector;

// Головний клас для демонстрації роботи системи
public class Restaurant {
    public static void main(String[] args) {
        // Створення об'єктів
        Customer customer = new Customer("Іван");
        Waiter waiter = new Waiter("Марія", 101);
        Chef chef = new Chef("Петро", "Українська кухня");
        Table table = new Table(1);
        PaymentService paymentService = new PaymentService();
        
        System.out.println("\n=== Демонстрація роботи ресторану ===\n");
        
        // Показ інформації про учасників
        customer.displayInfo();
        waiter.displayInfo();
        chef.displayInfo();
        
        System.out.println("\n--- Процес обслуговування ---\n");
        
        // Призначення столика
        customer.setTable(table);
        
        // Процес замовлення
        customer.scanMenu();
        customer.makeOrder();
        
        Injector injector = Guice.createInjector(new RestaurantModule());
        Order order = injector.getInstance(Order.class);
        order.setupOrder();

        table.setOrder(order);
        
        // Додавання страв
        order.addItem("Борщ", 1);
        order.addItem("Вареники", 2);
        
        // Підтвердження замовлення
        waiter.confirmOrder();
        waiter.checkAllergies();
        order.changeStatus("Підтверджене");
        
        // Приготування
        order.changeStatus("Готується");
        chef.markReady();
        order.changeStatus("Готове");
        
        // Оплата та оцінка
        order.displayOrder();
        customer.pay(paymentService);
        order.changeStatus("Оплачене");
        customer.rateService(5);
        order.changeStatus("Завершене");
        
        System.out.println("\n=== Демонстрацію завершено ===");
    }
}
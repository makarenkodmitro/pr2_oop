package com.restaurant;

// Клас відвідувача ресторану
public class Customer extends ServiceParticipant implements Booking {
    private Table table;

    public Customer(String name) {
        super(name);
    }

    public void scanMenu() {
        System.out.println("Відвідувач " + name + " сканує QR-код меню");
    }

    @Override
    public void makeOrder() {
        System.out.println("Відвідувач " + name + " робить замовлення");
    }

    @Override
    public void confirmOrder() {
        // Не використовується відвідувачем
    }

    @Override
    public void markReady() {
        // Не використовується відвідувачем
    }

    public void pay(PaymentService paymentService) {
        System.out.println("Відвідувач " + name + " оплачує замовлення");
        paymentService.processPayment();
    }

    public void rateService(int rating) {
        System.out.println("Відвідувач " + name + " оцінює сервіс на " + rating + " зірок");
    }

    @Override
    public void displayInfo() {
        System.out.println("Відвідувач: " + name);
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
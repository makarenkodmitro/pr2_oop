package com.restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.inject.Inject;

/**
 * Сервіс для роботи з відомостями про замовлення в базі даних
 */

public class OrderService {
    private Connection connection;

    /**
     * Конструктор з впровадженням залежності від драйвера бази даних.
     *
     * @param connection з'єднання з базою даних
     */
    @Inject
    public OrderService(Connection connection) {
        this.connection = connection;
    }

    protected Connection getConnection() {
        return connection;
    }
    /**
     * Метод для збереження об'єкта OrderItem в базі даних.
     *
     * Цей метод приймає об'єкт OrderItem, створює SQL-запит для вставки даних
     * про зарплату в базу даних і виконує цей запит.
     *
     * @param orderitem об'єкт OrderItem, який містить дані про позицію замовлення
     * @throws RuntimeException якщо виникає помилка під час збереження даних
     */
    public void saveOrderItem(OrderItem orderitem) {
        String sql = "INSERT INTO orderitems (amount, order_date) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderitem.getQuantity());
            statement.setString(2, orderitem.getDish());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save orderitem", e);
        }
    }
}
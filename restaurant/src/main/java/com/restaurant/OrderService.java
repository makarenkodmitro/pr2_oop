package com.restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "INSERT INTO orderitems (quantity, dish) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderitem.getQuantity());
            statement.setString(2, orderitem.getDish());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save orderitem", e);
        }
    }

        public List<OrderItem> getAllOrderItems() {
        List<OrderItem> orderitems = new ArrayList<>();
        String sql = "SELECT quantity, dish FROM orderitems";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                int quantity = resultSet.getInt("quantity");
                String dish = resultSet.getString("dish");
                orderitems.add(new OrderItem(dish, quantity));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не вдалося отримати позиції замовлення", e);
        }

        return orderitems;
    }
}
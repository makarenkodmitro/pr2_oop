package com.restaurant;

import java.util.List;

import com.google.inject.Inject;

/**
 * Контролер для управління замовленнями.
 * Реалізує патерн MVC як проміжну ланку між View та Model.
 */
public class RestaurantController {
    private final OrderService orderService;

    /**
     * Конструктор з впровадженням залежності OrderService (Model).
     *
     * @param OrderService модель для роботи із позиціями замовлень
     */
    @Inject
    public RestaurantController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Отримує всі позиції замовлень з бази даних.
     *
     * @return список позицій замовлення
     */
    public List<OrderItem> getAllOrderItems() {
        return orderService.getAllOrderItems();
    }   
}

package com.restaurant;

import com.google.inject.Inject;
import com.restaurant.webserver.HttpContext;
import com.restaurant.webserver.WebServer;

public class RestaurantWebView {
    private final RestaurantController controller;
    private final WebServer server;

    /**
     * Конструктор для створення веб-сервера.
     *
     * @param controller контролер для обробки даних
     */
    @Inject
    public RestaurantWebView(RestaurantController controller, WebServer server) {
        this.controller = controller;
        this.server = server;
        setupRoutes();
    }

    /**
     * Налаштування маршрутів веб-додатку.
     */
    private void setupRoutes() {
        // CORS configuration для роботи з Live Server
        server.before(context -> {
            context.header("Access-Control-Allow-Origin", "*");
            context.header("Access-Control-Allow-Methods", "GET, OPTIONS");
            context.header("Access-Control-Allow-Headers", "Content-Type");
        });

        // OPTIONS request для CORS preflight
        server.options("/*", context -> {
            context.status(200);
        });

        // API для отримання всіх позицій замовлення
        server.get("/api/orderitems", this::getAllOrderItems);
    }

    /**
     * Обробник GET-запиту для отримання всіх позицій замовлення.
     */
    private void getAllOrderItems(HttpContext context) {
        try {
            context.json(controller.getAllOrderItems());
        } catch (Exception e) {
            context.status(500).result("Помилка при отриманні даних: " + e.getMessage());
        }
    }

    /**
     * Запускає веб-сервер на вказаному порту.
     *
     * @param port порт для запуску сервера
     */
    public void start(int port) {
        server.start(port);
        System.out.println("──────────────────────────────────────────────");
        System.out.println("Restaurant Web Server запущено!\n");
        System.out.println("REST API: http://localhost:" + port + "\n");
        System.out.println("Відкрийте src/main/resources/index.html");
        System.out.println("за допомогою Live Server (VS Code extension)\n");
        System.out.println("Endpoint:");
        System.out.println("   GET  /api/orderitems - перегляд позицій замовлення");
        System.out.println("──────────────────────────────────────────────");
    }

    /**
     * Зупиняє веб-сервер.
     */
    public void stop() {
        server.stop();
    }   
}
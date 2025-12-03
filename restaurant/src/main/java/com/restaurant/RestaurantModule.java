package com.restaurant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import com.restaurant.webserver.JavalinWebServer;
import com.restaurant.webserver.WebServer;


import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;


public class RestaurantModule extends AbstractModule {


    @Override
    protected void configure() {
        bind(String.class)
            .annotatedWith(Names.named("JDBC URL"))
            .toInstance("jdbc:sqlite:target/restaurant.db");
    }


    @Provides
    @Singleton
    Connection provideConnection(@Named("JDBC URL") String url) {
        try {
            Connection connection = DriverManager.getConnection(url);
            createTableIfNotExists(connection);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }


    private void createTableIfNotExists(Connection connection) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS orderitems (" +
                                "quantity INT NOT NULL, " +
                                "dish TEXT NOT NULL)";


        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create table", e);
        }
    }

    @Provides
    @Singleton
    WebServer provideWebServer() {
        return new JavalinWebServer();
    }
}
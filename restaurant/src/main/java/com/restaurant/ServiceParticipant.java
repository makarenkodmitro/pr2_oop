package com.restaurant;

// Абстрактний клас для всіх учасників обслуговування
public abstract class ServiceParticipant {
    protected String name;

    public ServiceParticipant(String name) {
        this.name = name;
    }

    public abstract void displayInfo();
}
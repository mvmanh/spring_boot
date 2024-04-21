package com.mvm.app;

public class Product {
    private Long id;

    private String name;
    private double price;
    private String color;

    public Product() {
        // Default constructor required by JPA
    }

    public Product(String name, double price, String color) {
        this.name = name;
        this.price = price;
        this.color = color;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

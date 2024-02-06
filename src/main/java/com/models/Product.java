package com.models;

public class Product {
    public Product(int id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    private int id; 
    private String name; 
    private int price; 
    private int stock; 

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public int getPrice() {
        return this.price;
    }
    public int getStock() {
        return this.stock;
    }
}

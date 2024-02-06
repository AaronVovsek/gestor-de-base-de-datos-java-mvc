package com.views;

import java.util.List;
import java.util.Scanner;

import com.models.Product;

public class ProductView {
    Scanner scanner;

    public ProductView(){
        scanner = new Scanner(System.in);
    }

    public void displayProducts(List<Product> products) {
        System.out.println();
        System.out.println("ID   | Name              | Price   | Stock");
        System.out.println("------------------------------------------------");

        for (Product product : products) {
            System.out.print(product.getId());
            printSpaces(4 - String.valueOf(product.getId()).length());

            System.out.print("| " + product.getName());
            printSpaces(20 - product.getName().length());

            System.out.print("| $" + product.getPrice());
            printSpaces(9 - String.valueOf(product.getPrice()).length());

            System.out.print("| " + product.getStock());
            printSpaces(9 - String.valueOf(product.getStock()).length());

            System.out.println();
        }
        System.out.println("------------------------------------------------");
        System.out.println();
    }
    
    public int displayActions(){
        System.out.println("1 - Add product");
        System.out.println("2 - Edit product");
        System.out.println("3 - Delete product");
        System.out.println("4 - Exit");

        return scanner.nextInt();
    }

    private void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }
    
}

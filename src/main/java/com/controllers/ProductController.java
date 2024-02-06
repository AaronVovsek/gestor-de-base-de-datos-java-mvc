package com.controllers;
import java.util.List;
import java.util.Scanner;
import com.models.Product;
import com.models.interfaces.IProductRepository;
import com.views.ProductView;

public class ProductController {
    private IProductRepository productRepository;
    private ProductView productView;
    private Scanner scanner;

    public ProductController(IProductRepository productRepository, ProductView productView) {
        this.productRepository = productRepository;
        this.productView = productView;
        this.scanner = new Scanner(System.in);
        startApplication();
    }

    public void startApplication() {
        int choice;

        do {
            displayAllProducts();
            choice = productView.displayActions();

            switch (choice) {
                case 1:
                    // Add product
                    addProduct();
                    break;
                case 2:
                    // Edit product
                    editProduct();
                    break;
                case 3:
                    // Delete product
                    deleteProduct();
                    break;
                case 4:
                    // Exit
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number from 1 to 4.");
            }
        } while (choice != 4);
    }

    private void displayAllProducts() {
        List<Product> products = productRepository.getProducts();
        productView.displayProducts(products);
    }

    private void addProduct() {
        // Logic to add a new product
        System.out.println("Enter details for the new product:");
        System.out.print("Id: ");
        int productId = scanner.nextInt();

        System.out.print("Name: ");
        String productName = scanner.next();

        System.out.print("Price: ");
        int productPrice = scanner.nextInt();

        System.out.print("Stock: ");
        int productStock = scanner.nextInt();

        // Create a new Product object with the provided information
        Product newProduct = new Product(productId, productName, productPrice, productStock);

        // Add the new product to the repository
        productRepository.createProduct(newProduct);
    }

    private void editProduct() {
        // Logic to edit an existing product
        System.out.println("Enter details for the product to edit:");
        System.out.print("Id: ");
        int productId = scanner.nextInt();

        System.out.print("Name: ");
        String productName = scanner.next();

        System.out.print("Price: ");
        int productPrice = scanner.nextInt();

        System.out.print("Stock: ");
        int productStock = scanner.nextInt();

        // Create a new Product object with the provided information
        Product updatedProduct = new Product(productId, productName, productPrice, productStock);

        // Update the existing product in the repository
        productRepository.updateProduct(updatedProduct);
    }

    private void deleteProduct() {
        // Logic to delete an existing product
        System.out.println("Enter the ID of the product to delete:");
        System.out.print("Id: ");
        int productId = scanner.nextInt();

        System.out.println("Are you sure you want to delete this product? ID:" + productId);
        System.out.println("1-Yes");
        System.out.println("2-No");

        if(scanner.nextInt() != 1) return;

        // Delete the product from the repository
        productRepository.deleteProduct(productId);
    }
}


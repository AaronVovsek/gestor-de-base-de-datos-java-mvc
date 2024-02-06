package com.models.interfaces;
import java.util.List;

import com.models.Product;


public interface IProductRepository {
    Product getProduct(int id);
    List<Product> getProducts();
    void createProduct(Product product);
    void updateProduct (Product product);
    void deleteProduct (int id); 
}

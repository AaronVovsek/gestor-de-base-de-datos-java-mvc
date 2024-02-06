package com;
import com.controllers.*;
import com.models.interfaces.IProductRepository;
import com.models.repositories.ProductRepository;
import com.views.ProductView;

public class App 
{
    public static void main( String[] args )
    {
        ConnectionConfig conn = new ConnectionConfig();
        IProductRepository productRepository = new ProductRepository(conn.getConexion());
        ProductView productView = new ProductView();

        new ProductController(productRepository, productView);
        conn.Close();
    }
}
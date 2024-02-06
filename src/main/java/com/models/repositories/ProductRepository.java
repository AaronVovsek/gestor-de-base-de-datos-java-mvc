package com.models.repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.models.*;
import com.models.interfaces.*;

public class ProductRepository implements IProductRepository {
    final String INSERT = "insert into products(id, name, price, stock) values(?,?,?,?) ";
    final String GETALL = "select * from products ";
    final String GETONE = "select * from products where id =?";
    final String DELETE = "delete from products where id =?";
    final String UPDATE = "update products set name=?, price=?, stock=? where id = ?";

    private Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    public Product getProduct(int id) {
        Product p = null;
        try {
            PreparedStatement statement = connection.prepareStatement(GETONE);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if (set.next()) {

                String name = set.getString("name");
                int price = set.getInt("price");
                int stock = set.getInt("stock");
                p = new Product(id, name, price, stock);
            } else {
                return null;
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;

    }

    public List<Product> getProducts() {
        List<Product> list = new ArrayList<Product>();
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(GETALL);

            while (set.next()) {
                int id = set.getInt("id");
                String name = set.getString("name");
                int price = set.getInt("price");
                int stock = set.getInt("stock");
                Product p = new Product(id, name, price, stock);
                list.add(p);
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
    }

    public void createProduct(Product product) {
        PreparedStatement statement = null;
        if (getProduct(product.getId()) != null) {
            System.out.println("Product already exists");
            return;
        }
        try {
            statement = connection.prepareStatement(INSERT);
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getPrice());
            statement.setInt(4, product.getStock());
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateProduct(Product product) {
        PreparedStatement statement = null;
        if (getProduct(product.getId()) == null) {
            System.out.println("Product does not exist");
            return;
        }
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setInt(3, product.getStock());
            statement.setInt(4, product.getId());
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                statement.close();
                System.out.println("Updated successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteProduct(int id) {
        try {
            if (getProduct(id) == null) {
                System.out.println("Product does not exist");
                return;
            }
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            statement.executeUpdate();

            System.out.println("Deleted successfully");
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfig {
    private Connection connection=null;
    public ConnectionConfig(){
        try {
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/javabootcamp", "root", "");
        } catch (Exception e) {
            System.out.println("Connection error ocurren while connecting to database");
        }
    }
    public void Close(){
        if (connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public Connection getConexion(){
        return this.connection;
    }
}

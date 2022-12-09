package com.project4;

import java.sql.Connection;
import java.sql.DriverManager;


public class StudentDatabase implements TableInterface, StudentDatabaseInterface{
    
    String url, username, password;
    Connection connection;
    
    //Constructor to get the Connection established
    StudentDatabase(String url, String username, String password){

        this.url = url;
        this.username = username;
        this.password = password;
        this.connection = getConnection(url, username, password);
    }

    public Connection getConnection(String url, String username, String password){

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("\nConnection established");
        } catch (Exception e) { System.out.println(e);}

        return connection;
    }

}

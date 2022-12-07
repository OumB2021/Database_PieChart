package com.project4;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;



public class classTest {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/world";
        String username = "root";
        String password = "Jkjkjk94+";
        String query = "SELECT * FROM world.country";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(query);
        
        String data = "";
        int count = 0;
        while(set.next() && count < 15){
            data = set.getInt(8) + " : " + set.getString(2);
            System.out.println(data);
            count++;
        }
        

        statement.close();
        connection.close();
    }
} //end of classTest

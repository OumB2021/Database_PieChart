package com.project4;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;



public class classTest {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/Sample?allowLoadLocalInfile=true";
        String username = "root";
        String password = "Jkjkjk94+";

        //Class.forName("com.mysql.cj.jdbc.Driver");
        
        StudentDatabase DB = new StudentDatabase(url, username, password);
        //Connection connection = DB.getConnection();

        String filename = "C:/Users/baarr/OneDrive/Bureau/Java/Project 4/demo/ScheduleSpring2022.txt";
        String ddlCreateTable, tableName;
        
        tableName = "Sample.Schedule";
        ddlCreateTable = StudentDatabaseInterface.ddlCreateTableSchedule;

        //System.out.println(TableInterface.LoadDataInFileTable(filename, tableName));

        StudentDatabase.Schedule schedule = DB.new Schedule(ddlCreateTable, filename, tableName);

        ResultSet resultSet = schedule.getResultSet();
        
    }
} //end of classTest

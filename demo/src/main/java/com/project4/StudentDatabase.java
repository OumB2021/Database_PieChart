package com.project4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentDatabase implements TableInterface, StudentDatabaseInterface{
    
    String url, username, password;
    public Connection connection;
    
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

    //------------------------------------------------------------------------------------------------
    // Inner class Implementation

    class Schedule {
    
        String ddlCreateTable, ddlPopulateTable;
        String ddlUpdateCourseInstructor, ddlUpdateInstructor;
        String filename, nameTable;
    
        Schedule (String ddlCreateTable, String filename, String nameTable) throws SQLException{
            
            this.ddlCreateTable = ddlCreateTable;
            this.filename = filename;
            this.nameTable = nameTable;
    
            this.ddlPopulateTable = TableInterface.LoadDataInFileTable(filename, nameTable);
    
            //Create the table
            TableInterface.dropTable(connection, nameTable);
            TableInterface.createTable(connection, ddlCreateTable);
            System.out.println("\nTable created");

            //Insert values into the table
            TableInterface.setLocalInFileLoading(connection);
            TableInterface.populateTable(connection, ddlPopulateTable);
            System.out.println("\nTable populated");

            ResultSet resultSet = TableInterface.getTable(connection, nameTable);
            System.out.println("\nQuery executed on Schedule successfully");
        }


        public void ddlUpdateCourseInstructor(String courseID, String sectionNumber, String name) throws SQLException{

            this.ddlUpdateCourseInstructor = StudentDatabaseInterface.ddlUpdateCourseInstructor(courseID, sectionNumber, name);
            System.out.println("\nCourse Instructor's name changed successfully");

            TableInterface.updateField(connection, ddlUpdateCourseInstructor);
        }

        public void ddlUpdateInstructor(String instructorID, String name) throws SQLException{

            this.ddlUpdateInstructor = StudentDatabaseInterface.ddlUpdateInstructor(instructorID, name);
            System.out.println("\nInstructor's name changed successfully");

            TableInterface.updateField(connection, ddlUpdateInstructor);
        }

    }

    class Courses{
        
    }

}




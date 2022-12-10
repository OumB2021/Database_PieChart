package com.project4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


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

    public Connection getConnection(){return this.connection;}

    //------------------------------------------------------------------------------------------------
    // Inner class Implementation

    class Schedule {
    
        String ddlCreateTable, ddlPopulateTable;
        String ddlUpdateCourseInstructor, ddlUpdateInstructor;
        String filename, nameTable;

        ResultSet resultSet;
    
        Schedule (String ddlCreateTable, String filename, String nameTable) throws SQLException{
            
            this.ddlCreateTable = ddlCreateTable;
            this.filename = filename;
            this.nameTable = nameTable;
            
            //TableInterface.setLocalInFileLoading(connection);
            this.ddlPopulateTable = TableInterface.LoadDataInFileTable(filename, nameTable);
    
            //Create the table
            TableInterface.dropTable(connection, nameTable);
            TableInterface.createTable(connection, ddlCreateTable);
            System.out.println("\nTable Schedule created");

            //Insert values into the table
            TableInterface.setLocalInFileLoading(connection);
            TableInterface.populateTable(connection, ddlPopulateTable);
            System.out.println("\nTable Schedule populated");

            this.resultSet = TableInterface.getTable(connection, nameTable);
            System.out.println("\nQuery executed on Schedule successfully");
            System.out.println("----------------------------------------------------------------");
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

        public ResultSet getResultSet() throws SQLException{return this.resultSet;}

    }

    class Courses {

        String ddlCreateTable, ddlPopulateTable;
        String nameToTable, nameFromTable;

        ResultSet resultSet;

        Courses(String ddlCreateTable, String nameToTable, String nameFromTable) throws SQLException{

            this.ddlCreateTable = ddlCreateTable;
            this.nameToTable = nameToTable;
            this.nameFromTable = nameFromTable;
        
            this.ddlPopulateTable = StudentDatabaseInterface.ddlInsertTableCourses(nameToTable, nameFromTable);
            
            //Create the table
            TableInterface.dropTable(connection, nameToTable);
            TableInterface.createTable(connection, ddlCreateTable);
            System.out.println("\nTable Courses created");

            //Insert values into the table
            TableInterface.insertFromSelect(connection, ddlPopulateTable);
            System.out.println("\nTable Courses populated");
            
            this.resultSet = TableInterface.getTable(connection, nameToTable);
            System.out.println("\nQuery executed on Courses successfully");
            System.out.println("----------------------------------------------------------------");
        }

        public ResultSet getResultSet() throws SQLException{return this.resultSet;}

    }

    class Students {

        String ddlCreateTable, ddlPopulateTable, nameTable;

        ResultSet resultSet;

        Students(String ddlCreateTable, String nameTable) throws SQLException{

            this.ddlCreateTable = ddlCreateTable;
            this.nameTable = nameTable;
        
            this.ddlPopulateTable = StudentDatabaseInterface.ddlInsertIntoStudents;

            //Create the table
            TableInterface.dropTable(connection, nameTable);
            TableInterface.createTable(connection, ddlCreateTable);
            System.out.println("\nTable Students created");

            //Insert values into the table
            TableInterface.populateTable(connection, ddlPopulateTable);
            System.out.println("\nTable Students populated");
            
            this.resultSet = TableInterface.getTable(connection, nameTable);
            System.out.println("\nQuery executed on Students successfully");
            System.out.println("----------------------------------------------------------------");
        }

        public ResultSet getResultSet() throws SQLException{return this.resultSet;}
    }

    class Classes {

        String ddlCreateTable, ddlPopulateTable, nameTable;
        ResultSet resultSet;

        Classes(String ddlCreateTable, String nameTable) throws SQLException{

            this.ddlCreateTable = ddlCreateTable;
            this.nameTable = nameTable;

            this.ddlPopulateTable = StudentDatabaseInterface.ddlInsertTableClasses(nameTable);

            //Create the table
            TableInterface.dropTable(connection, nameTable);
            TableInterface.createTable(connection, ddlCreateTable);
            System.out.println("\nTable Classes created");
            
            //Insert values into the table
            TableInterface.populateTable(connection, ddlPopulateTable);
            
            // Get the grades in the table
            for (int i = 1; i <= 30; i++){
                ddlPopulateTable = "UPDATE " + nameTable +
                                   " set Grade = '" + getGrade() + "' WHERE EmplId = " + Integer.toString(i);
                
                TableInterface.updateField(connection, ddlPopulateTable);
            }

            System.out.println("\nTable classes populated");
            
            this.resultSet = TableInterface.getTable(connection, nameTable);
            System.out.println("\nQuery executed on Classes successfully");
            System.out.println("----------------------------------------------------------------");
        }

        // Random generate the 
        public Character getGrade(){
        
            Character [] grades = {'A', 'B', 'C', 'D', 'W'};
    
            Random rand = new Random();
            int randomNumber = rand.nextInt(grades.length - 1);
    
            return grades[randomNumber];
        }

        public ResultSet getResultSet() throws SQLException{return this.resultSet;}
    }

    class AggregateGrades {

        String ddlCreateTable, ddlPopulateTable, nameTable, nameFromTable;
        ResultSet resultSet;

        AggregateGrades(String ddlCreateTable, String nameTable, String nameFromTable) throws SQLException{

            this.ddlCreateTable = ddlCreateTable;
            this.nameTable = nameTable;
            this.nameFromTable = nameFromTable;

            this.ddlPopulateTable = StudentDatabaseInterface.ddlInsertTableAggregateGrades(nameTable, nameFromTable);

            //Create the table
            TableInterface.dropTable(connection, nameTable);
            TableInterface.createTable(connection, ddlCreateTable);
            System.out.println("\nTable Aggregate Grades created");

            //Insert values into the table
            TableInterface.insertFromSelect(connection, ddlPopulateTable);
            System.out.println("\nTable Aggregate Grades populated");
            
            this.resultSet = TableInterface.getTable(connection, nameTable);
            System.out.println("\nQuery executed on Aggregate Grades successfully");
            System.out.println("----------------------------------------------------------------");
        }

        public Map <Character, Integer> getGrades (String nameTable){

            Map <Character, Integer> grades = new HashMap<Character, Integer>();

            try {
                ResultSet resultSet = TableInterface.getTable(connection, nameTable);
                while (resultSet.next()){
                    grades.put(resultSet.getString("grade").charAt(0),
                    resultSet.getInt("NumberOfStudents"));
                }
            } catch(SQLException e){System.out.print(e);}

            return grades;
        }

        public ResultSet getResultSet() throws SQLException{return this.resultSet;}
    }

}




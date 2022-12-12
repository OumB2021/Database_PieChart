package com.project4;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class classTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/Sample?allowLoadLocalInfile=true";
        String username = "root";
        String password = "Jkjkjk94+";
        
        // Establish connection
        StudentDatabase DB = new StudentDatabase(url, username, password);

        // Creates variables
        String ddlCreateTable, tableName, filename, fromTable;
        ResultSet resultSet;

        // Create table

        // Creates and insert values into the schedule table
        filename = "C:/Users/baarr/OneDrive/Bureau/Java/Project 4/demo/ScheduleSpring2022.txt";
        tableName = "Schedule";
        ddlCreateTable = StudentDatabaseInterface.ddlCreateTableSchedule;
        StudentDatabase.Schedule schedule = DB.new Schedule(ddlCreateTable, filename, tableName);

        // Creates and insert values into the courses table
        fromTable = "Schedule";
        tableName = "Courses";
        ddlCreateTable = StudentDatabaseInterface.ddlCreateTableCourses;
        StudentDatabase.Courses courses = DB.new Courses(ddlCreateTable, tableName, fromTable);

        // Creates and insert values into the Students table
        tableName = "Students";
        ddlCreateTable = StudentDatabaseInterface.ddlCreateTableStudents;
        StudentDatabase.Students student = DB.new Students(ddlCreateTable, tableName);
        
        resultSet = student.getResultSet();
        printTable(resultSet);

        // Creates and insert values into the Classes table
        tableName = "Classes";
        ddlCreateTable = StudentDatabaseInterface.ddlCreateTableClasses;
        StudentDatabase.Classes classes = DB.new Classes(ddlCreateTable, tableName);

        resultSet = student.getResultSet();
        printTable(resultSet);

        // Creates and insert values into the AggregateGrades table
        fromTable = tableName;
        tableName = "AggregateGrades";
        ddlCreateTable = StudentDatabaseInterface.ddlCreateTableAggregateGrades;
        StudentDatabase.AggregateGrades grades = DB.new AggregateGrades(ddlCreateTable, tableName, fromTable);
        
        resultSet = grades.getResultSet();
        printTable(resultSet);

        // Update grade of student #10
        classes.UpdateGrade(10, 'A');


    }

    static void printTable(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            
            System.out.println("");
        }
    }

} //end of classTest

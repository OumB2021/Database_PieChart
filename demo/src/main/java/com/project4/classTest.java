package com.project4;

// import java.sql.Statement;
import java.io.IOException;
// import java.sql.Connection;
// import java.sql.DriverManager;
import java.sql.SQLException;
// import java.sql.ResultSet;



public class classTest {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/Sample?allowLoadLocalInfile=true";
        String username = "root";
        String password = "Jkjkjk94+";
        
        // Establish connection
        StudentDatabase DB = new StudentDatabase(url, username, password);

        // Creates variables
        String ddlCreateTable, tableName, filename, fromTable;

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
        
        // Creates and insert values into the Classes table
        tableName = "Classes";
        ddlCreateTable = StudentDatabaseInterface.ddlCreateTableClasses;
        StudentDatabase.Classes classes = DB.new Classes(ddlCreateTable, tableName);
    }
} //end of classTest

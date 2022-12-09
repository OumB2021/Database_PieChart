package com.project4;

interface StudentDatabaseInterface {
    
    String ddlCreateTableSchedule = "CREATE TABLE Schedule(" +
                                    "CourseId CHAR(12) NOT NULL UNIQUE, " +
                                    "SectionNumber VARCHAR(10) NOT NULL UNIQUE, " +
                                    "Title VARCHAR(64), " +
                                    "Year INT, " +
                                    "Semester CHAR(6), " +
                                    "Instructor VARCHAR(30), " +
                                    "Department CHAR(16), " +
                                    "Program VARCHAR(50), " +
                                    "PRIMARY KEY (courseId, SectionNumber))";
    
    String ddlCreateTableStudents = "CREATE TABLE Students(" +
                                    "EmplID INT PRIMARY KEY," +
                                    "Name VARCHAR(32) NOT NULL" +
                                    "Gender ENTER CHECK (Gender ='F' OR Gender ='M' OR Gender ='U')," +
                                    "Dob DATE)";
    
    String ddlCreateTableCourses =  "CREATE TABLE Courses(" +
                                    "CourseId CHAR(12) PRIMARY KEY, " +
                                    "Title VARCHAR(64), " +
                                    "Department CHAR(16), " +
                                    "Program VARCHAR(48))";
    
    String ddlCreateTableClasses = "CREATE TABLE Classes(" +
                                   "emplID    
    
    String ddlCreateTableAggregateGrades = "CREATE TABLE AggregateGrades(Grade CHAR, NumberOfStudents INT)";

    //String ddlInsertIntoStudents = "INSERT INTO Students VALUES"
}

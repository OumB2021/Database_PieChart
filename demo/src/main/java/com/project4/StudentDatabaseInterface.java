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
                                    "EmplId INT PRIMARY KEY, " +
                                    "Name VARCHAR(20) NOT NULL, " +
                                    "Gender CHAR(1) CHECK (Gender = 'F' OR Gender = 'M' OR Gender = 'U'), " +
                                    "Dob DATE)";
    
    String ddlCreateTableCourses =  "CREATE TABLE Courses(" +
                                    "CourseId CHAR(12) PRIMARY KEY, " +
                                    "Title VARCHAR(64), " +
                                    "Department CHAR(16), " +
                                    "Program VARCHAR(48))";
    
    String ddlCreateTableClasses =  "CREATE TABLE Classes(" +
                                    "emplId INT, " +
                                    "CourseId CHAR(12), " +
                                    "SectionNumber VARCHAR(10), " +
                                    "Year INT, " +
                                    "Semester CHAR(8), " +
                                    "Grade CHAR(1) CHECK (Grade = 'A' OR Grade = 'B' OR Grade = 'C' OR Grade = 'D' OR Grade = 'W'), " +
                                    "PRIMARY KEY(EmplId, courseId, SectionNumber))";
    
    String ddlCreateTableAggregateGrades = "CREATE TABLE AggregateGrades(Grade CHAR, NumberOfStudents INT)";

    String ddlInsertIntoStudents = "INSERT INTO Students VALUES (0001, 'Student-1', 'M', NULL), " +
                                   "(0002, 'Student-2', 'F', NULL), " +
                                   "(0003, 'Student-3', 'M', NULL), " +
                                   "(0004, 'Student-4', 'F', NULL), " +
                                   "(0005, 'Student-5', 'M', NULL), " +
                                   "(0006, 'Student-6', 'F', NULL), " +
                                   "(0007, 'Student-7', 'M', NULL), " +
                                   "(0008, 'Student-8', 'F', NULL), " +
                                   "(0009, 'Student-9', 'U', NULL), " +
                                   "(0010, 'Student-10', 'U', NULL), " +
                                   "(0011, 'Student-11', 'M', NULL), " +
                                   "(0012, 'Student-12', 'F', NULL), " +
                                   "(0013, 'Student-13', 'U', NULL), " +
                                   "(0014, 'Student-14', 'M', NULL), " +
                                   "(0015, 'Student-15', 'F', NULL), " +
                                   "(0016, 'Student-16', 'U', NULL), " +
                                   "(0017, 'Student-17', 'M', NULL), " +
                                   "(0018, 'Student-18', 'F', NULL), " +
                                   "(0019, 'Student-19', 'U', NULL), " +
                                   "(0020, 'Student-20', 'M', NULL), " +
                                   "(0021, 'Student-21', 'U', NULL), " +
                                   "(0022, 'Student-22', 'M', NULL), " +
                                   "(0023, 'Student-23', 'U', NULL), " +
                                   "(0024, 'Student-24', 'M', NULL), " +
                                   "(0025, 'Student-25', 'F', NULL), " +
                                   "(0026, 'Student-26', 'M', NULL), " +
                                   "(0027, 'Student-27', 'F', NULL), " +
                                   "(0028, 'Student-28', 'M', NULL), " +
                                   "(0029, 'Student-29', 'U', NULL), " +
                                   "(0030, 'Student-30', 'F', NULL)";
    
    String sqlAggregateGrades = "SELECT Grade, count(Grade) FROM Classes GROUP BY Grade";

    // Updat the name of the professor for a specific class
    static String ddlUpdateCourseInstructor (String CourseId, String sectionNumber, String profName){

        return "UPDATE SCHEDULE" +
               " SET Instructor = " + profName +
               " WHERE CourseID = " + CourseId + " AND SectionNumber = " + sectionNumber;
    }

    // Update the name of the professor for all general cases
    static String ddlUpdateInstructor(String nameInstructor, String nameNewInstructor){

        return "UPDATE Schedule" +
               "SET Instructor = " + nameInstructor +
               "WHERE CourseId = " + nameNewInstructor;
    }

    // Insert values into the Courses table using the data in the Schedule table.
    static String ddlInsertTableCourses(String nameToTable, String nameFromTable){

        return "INSERT INTO " + nameToTable +
               " (CourseId, Title, Department, Program) " +
               "SELECT CourseId, Title, Department, Program " + 
               "FROM " + nameFromTable;
    }

    static String ddlInsertTableClasses(String tableName){

        String tableSchedule = "Sample.Schedule";
        String tableStudents = "Sample.Students";

        return "INSERT INTO " + tableName + 
               " (emplId, CourseId, SectionNumber, Year, Semester) " +
               "SELECT EmplId, CourseId, SectionNumber, Year, Semester FROM " + tableStudents + ", " + tableSchedule +
               " where courseId = '22000 C'";
    }
    
    static String ddlInsertTableAggregateGrades(String nameToTable, String nameFromTable){

        return "INSERT INTO " + nameToTable +
               " SELECT Grade, count(Grade) FROM " + nameFromTable +
               " Group BY Grade ORDER BY Grade";
    }

}

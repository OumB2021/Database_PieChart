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
                                    "EmplId INT PRIMARY KEY," +
                                    "Name VARCHAR(32) NOT NULL" +
                                    "Gender ENTER CHECK (Gender ='F' OR Gender ='M' OR Gender ='U')," +
                                    "Dob DATE)";
    
    String ddlCreateTableCourses =  "CREATE TABLE Courses(" +
                                    "CourseId CHAR(12) PRIMARY KEY, " +
                                    "Title VARCHAR(64), " +
                                    "Department CHAR(16), " +
                                    "Program VARCHAR(48))";
    
    String ddlCreateTableClasses =  "CREATE TABLE Classes(" +
                                    "emplId INT REFERENCES Student (EmplId), " +
                                    "CourseId CHAR(12) REFERENCES Schedule(CourseId)," +
                                    "SectionNumber VARCHAR(10) REFERENCES Schedule(SectionNumber)," +
                                    "Year INT," +
                                    "Semester CHAR(8)," +
                                    "Grade CHAR CHECK(Grade = 'A' OR Grade = 'B' OR Grade = 'C' OR Grade = 'D' OR Grade = 'W'),"+
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
    
    String ddlInsertTableClasses = "INSERT INTO Classes VALUES "

}

package com.project4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

interface TableInterface {
    
    Connection getConnection(String url, String username, String password);
    
    // Creates a new database 
    static void createSchema(Connection connection, String schemaName) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("CREATE SCHEMA IF NOT EXISTS " + schemaName);
        
        try {
            statement.executeUpdate();
        } catch (Exception e) {System.out.println(e);}

        statement.close();
    }

    //Drops a table from the database
    static void dropTable(Connection connection, String schemaName) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("DROP SCHEMA IF EXISTS " + schemaName);
        
        try {
            statement.executeUpdate();
        } catch (Exception e) {System.out.println(e);}
        
        statement.close(); 
    }
    
    //Creates a table in the database
    static void createTable(Connection connection, String ddlCreateTable) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(ddlCreateTable);
        
        try {
            statement.executeUpdate();
        } catch (Exception e) {System.out.println(e);}
    }

    static void setLocalInFileLoading(Connection connection) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("SET GLOBAL local_infile=1");
        
        try {
            statement.executeUpdate();
            System.out.println("\nGlobal local-infile set successfully");
        } catch (Exception e) {System.out.println(e);}
    }

    static String LoadDataInFileTable(String nameFile, String nameTable){

        return "LOAD DATA LOCAL INFILE '" + nameFile + "' INTO TABLE " + nameTable + 
               " COLUMNS TERMINATED BY '\t'" +
               " LINES TERMINATED BY '\n'" +
               " IGNORE 1 LINES";
    }

    static void populateTable(Connection connection, String ddlPopulateTable) throws SQLException{

        PreparedStatement populateTable = connection.prepareStatement(ddlPopulateTable);
        try {
            populateTable.executeUpdate();
        } catch (Exception e) { System.out.println(e);}
    }

    //insert into Table from SELECT given specific fields and conditions
    static void insertFromSelect(Connection connection, String nameToTable, String nameFromTable)  throws SQLException{

        PreparedStatement psInsertFromSelect = connection.prepareStatement("INSERT INTO " + nameToTable + "SELECT * FROM " + nameFromTable);
        try{
            psInsertFromSelect.executeUpdate();
        }

        catch(SQLException e) { System.out.println(e);}
    }

    //insert into Table from SELECT given specific fields and conditions
    static void insertFromSelect(Connection connection, String ddlInsertFromSelect)  throws SQLException{

        PreparedStatement psInsertFromSelect = connection.prepareStatement(ddlInsertFromSelect);
        try{
            psInsertFromSelect.executeUpdate();
        }

        catch(SQLException e) { System.out.println(e);}

    }

     //insert a record in a Table
     static void insertRecord(Connection connection, String ddlInsertRecord)  throws SQLException{

        PreparedStatement psInsertRecord = connection.prepareStatement(ddlInsertRecord);
        try{
            psInsertRecord.executeUpdate();
        }

        catch(SQLException e) { System.out.println(e);}

    }

      //update a field in records
      static void updateField(Connection connection, String ddlUpdateField)  throws SQLException{

        PreparedStatement psUpdateField = connection.prepareStatement(ddlUpdateField);

        try{
            psUpdateField.executeUpdate();
        }

        catch(SQLException e) { System.out.println(e);}

    }

    //delete a record from Table
    static void deleteRecord(Connection connection, String ddlDeleteRecord)  throws SQLException{

        PreparedStatement psDeleteRecord = connection.prepareStatement(ddlDeleteRecord);
        try{
            psDeleteRecord.executeUpdate();
        }

        catch(SQLException e) { System.out.println(e);}

    }

    //get a table
    static ResultSet getTable(Connection connection, String nameToTable) throws SQLException{

        ResultSet RS = null; // To store the table information

        PreparedStatement psGetTable = connection.prepareStatement("SELECT * FROM " + nameToTable);
        try{
            RS = psGetTable.executeQuery();
        }

        catch(SQLException e) { System.out.println(e);}

        return RS;
    }
}

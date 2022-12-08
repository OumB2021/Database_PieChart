package com.project4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

interface TableInterface {
    
    Connection getConnection(String url, String username, String password);
    
    static void createSchema(Connection connection, String schemaName) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("CREATE SCHEMA IF NOT EXISTS " + schemaName);
        
        try {
            statement.executeUpdate();
        } catch (Exception e) {System.out.println(e);}

        statement.close();
    }

    static void dropTable(Connection connection, String schemaName) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("DROP SCHEMA IF EXISTS " + schemaName);
        
        try {
            statement.executeUpdate();
        } catch (Exception e) {System.out.println(e);}
        
        statement.close(); 
    }
    
    static void createTable(Connection connection, String ddlCreateTable) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(ddlCreateTable);
        
        try {
            statement.executeUpdate();
        } catch (Exception e) {System.out.println(e);}
    }

    static void setLocalInFileLoading(Connection connection) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("SET GLOBAL local_infile = 1");
        
        try {
            statement.executeUpdate();
        } catch (Exception e) {System.out.println(e);}
    }

    static String LoadDatainFileTable(String nameFile, String nameTable){
        return "LOAD DATA LOCAL INFILE '" + nameFile + "' INTO TABLE " + nameTable;
    }

    static void populateTable(Connection connection, String ddlPopulateTable) throws SQLException{
        PreparedStatement populateTable = connection.prepareStatement(ddlPopulateTable);
        try {
            populateTable.executeUpdate();
        } catch (Exception e) { System.out.println(e);}
    }
}

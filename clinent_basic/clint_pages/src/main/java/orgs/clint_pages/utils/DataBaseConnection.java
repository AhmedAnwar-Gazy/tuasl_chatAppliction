package orgs.clint_pages.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private Connection connection;

    public DataBaseConnection(){
        String url = "jdbc:sqlite:SqliteToasolDB.db";
        try{
            connection = DriverManager.getConnection(url);
            System.out.println("Connection Successful...!!!");
        }catch (SQLException e){
            System.out.println("Error Occurred During Connect to DataBase.....!!!");
            e.printStackTrace();

        }
    }
    public Connection getConnection(){
        return connection;
    }
    public void closeConnection(){
        try {
            if(connection != null){
                connection.close();
                System.out.println("Connection Has Been Closed");
            }
        }catch (SQLException e){
            System.out.println("Error Occurred During Close Connection");
            e.printStackTrace();
        }
    }


}

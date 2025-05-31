package orgs.clint_pages;


import javafx.application.Application;
import javafx.stage.Stage;
import orgs.clint_pages.utils.DataBaseConnection;
import orgs.clint_pages.utils.Navigation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import static jdk.internal.foreign.SystemLookup.WindowsFallbackSymbols.printf;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        DataBaseConnection db = new DataBaseConnection();
        Connection conn =  db.getConnection();
        String sql = "Create Table If not exists my_table(id Integer primary key,email TEXT)";
        Statement s = conn.createStatement();
        if(s.executeUpdate(sql) > 0){
            System.out.println("Table Created");
        }
        sql = "Insert into my_table (email) values('ahmed anwar@email.com'); ";
        if(s.executeUpdate(sql) > 0){
            System.out.println("One Row Inserted");
        }
        sql = "select id,email from my_table;";
        ResultSet rs = s.executeQuery(sql);
        while (rs.next()){
            int id = rs.getInt("id");
            String email = rs.getString("email");
//            printf("Column getted values: id: %d and email is: %s",id,email);
            System.out.println("your id is : "+id+" and email is : "+email+" ");
        }

        this.primaryStage = stage;
        this.primaryStage.setTitle("Chat Application");

        // Initialize Navigation helper
        Navigation.setMainApp(this); // Pass the instance to the helper
        Navigation.setPrimaryStage(primaryStage); // Pass the stage

        // Load the initial login screen
        Navigation.loadPage("login.fxml");

        primaryStage.setMinWidth(800); // Minimum responsive width
        primaryStage.setMinHeight(600); // Minimum responsive height
        primaryStage.show();

        db.closeConnection();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
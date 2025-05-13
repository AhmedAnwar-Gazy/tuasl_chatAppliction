package orgs.clint_pages;


import javafx.application.Application;
import javafx.stage.Stage;
import orgs.clint_pages.utils.DatabaseConnection;
import orgs.clint_pages.utils.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Chat Application");
        DatabaseConnection.con();
        // Initialize Navigation helper
        Navigation.setMainApp(this); // Pass the instance to the helper
        Navigation.setPrimaryStage(primaryStage); // Pass the stage

        // Load the initial login screen
        Navigation.loadPage("login.fxml");

        primaryStage.setMinWidth(800); // Minimum responsive width
        primaryStage.setMinHeight(600); // Minimum responsive height
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
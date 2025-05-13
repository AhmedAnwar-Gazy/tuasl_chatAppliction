package orgs.clint_pages.controllers;

import orgs.clint_pages.utils.Navigation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button; // Import Button
import javafx.scene.layout.VBox;

public class ChatController {

    @FXML private ListView<String> chatListView;
    @FXML private Label chatTitleLabel;
    @FXML private ScrollPane messageScrollPane;
    @FXML private VBox messageDisplayArea;
    @FXML private TextField messageInputField;
    @FXML private Button settingsButton; // Added FXML annotation
    @FXML private Button sendButton; // Added FXML annotation


    // Dummy data for chat list
    private ObservableList<String> chatItems = FXCollections.observableArrayList(
            "General Chat", "Team Alpha", "Project X Discussion", "Random"
    );

    @FXML
    public void initialize() {
        // Populate the chat list
        chatListView.setItems(chatItems);

        // Add listener to chat list selection
        chatListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                chatTitleLabel.setText(newSelection);
                // Clear previous messages and load messages for the selected chat
                messageDisplayArea.getChildren().clear();
                // Add placeholder message loading logic
                messageDisplayArea.getChildren().add(new Label("Messages for " + newSelection + " would load here."));
                System.out.println("Selected chat: " + newSelection);
            }
        });

        // Select the first chat by default
        if (!chatItems.isEmpty()) {
            chatListView.getSelectionModel().selectFirst();
        }

        // Ensure the scroll pane scrolls to the bottom when new messages are added (basic setup)
        messageDisplayArea.heightProperty().addListener(observable -> messageScrollPane.setVvalue(1D));
    }

    @FXML
    private void handleSettingsButtonAction(ActionEvent event) {
        Navigation.loadPage("settings.fxml");
    }

    @FXML
    private void handleSendButtonAction(ActionEvent event) {
        String messageText = messageInputField.getText().trim();
        if (!messageText.isEmpty()) {
            System.out.println("Sending message: " + messageText);

            // Create a label for the new message (replace with actual message bubble)
            Label newMessageLabel = new Label("You: " + messageText);
            newMessageLabel.setWrapText(true); // Allow text wrapping
            newMessageLabel.setMaxWidth(messageDisplayArea.getWidth() - 30); // Prevent overflow (adjust padding)


            // Add message to the display area
            messageDisplayArea.getChildren().add(newMessageLabel);

            // Clear the input field
            messageInputField.clear();


            // Scroll to bottom (might need slight delay depending on layout)
            messageScrollPane.setVvalue(1.0);
        }
    }
}
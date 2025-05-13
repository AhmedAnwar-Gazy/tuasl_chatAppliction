package orgs.clint_pages.controllers;

import orgs.clint_pages.utils.Navigation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.animation.ScaleTransition;
//import javafx.scene.media.AudioRecorder;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.sound.sampled.*;

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
    @FXML private Button sendVoiceButton; //Added FXML annotation
    @FXML private Button emojiButton; //Added FXML annotation
    @FXML private Button shareButton; //Added FXML annotation
    @FXML private VBox areaOfEmojis; //Added FXML annotation

    private boolean isRecording = false;
    private TargetDataLine line;

    // Define the folder where recordings should be saved
    private final String RECORDING_FOLDER = "C:\\Users\\gam73\\IdeaProjects\\JavaProject\\tuasl_chatAppliction\\clinent_basic\\clint_pages\\src\\main\\resources\\orgs\\clint_pages\\voiceNote";






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

    @FXML
    private void handleSendVoiceButtonAction1(ActionEvent event) {
        System.out.println("Send Voice Button Clicked!");
        // You can add additional logic if needed
    }

    @FXML
    public void handleSendVoiceButtonAction() {
        sendVoiceButton.setOnAction(this::handleSendVoiceButtonAction1);
        sendVoiceButton.setOnMousePressed(event -> {
            animateButton(true); // Start animation
            startRecording(); // Begin recording
        });

        sendVoiceButton.setOnMouseReleased(event -> {
            stopRecording(); // Stop recording when released
            animateButton(false); // Reset animation
        });
    }

    private void animateButton(boolean isClicked) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), sendVoiceButton);
        if (isClicked) {
            scaleTransition.setToX(1.2); // Expand the button
            scaleTransition.setToY(1.2);
        } else {
            scaleTransition.setToX(1.0); // Return to normal size
            scaleTransition.setToY(1.0);
        }
        scaleTransition.play();
    }

    private void startRecording() {
        try {
            // Ensure the directory exists
            File folder = new File(RECORDING_FOLDER);
            if (!folder.exists()) {
                folder.mkdirs(); // Create directory if it doesn’t exist
            }

            // Generate a unique filename based on the current timestamp
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File audioFile = new File(RECORDING_FOLDER, "recording_" + timeStamp + ".wav");

            AudioFormat format = new AudioFormat(16000, 8, 2, true, true);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                System.err.println("Line not supported");
                return;
            }

            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
            isRecording = true;

            System.out.println("Recording started: " + audioFile.getAbsolutePath());

            Thread recordingThread = new Thread(() -> {
                try (AudioInputStream audioStream = new AudioInputStream(line)) {
                    AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, audioFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            recordingThread.start();

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        if (isRecording && line != null) {
            line.stop();
            line.close();
            isRecording = false;
            System.out.println("Recording stopped and saved.");
        }
    }


    @FXML
    private void handleMenuButtonAction(ActionEvent event){

    }

    @FXML
    private void handleShareButtonAction(ActionEvent event){

    }

    @FXML
    private void handleEmojiButtonAction() {
        // Toggle visibility: if visible, hide it; if hidden, show it
        boolean isVisible = areaOfEmojis.isVisible();
        areaOfEmojis.setVisible(!isVisible);

        // Only populate emojis when making it visible
        if (!isVisible) {
            areaOfEmojis.getChildren().clear();

            String[] emojis = {
                    "\uD83D\uDE00", "\uD83D\uDE01", "\uD83D\uDE02", "\uD83D\uDE03", "\uD83D\uDE04"
            };

            for (String emoji : emojis) {
                Label emojiLabel = new Label(emoji);
                emojiLabel.setStyle("-fx-font-size: 30px;");
                areaOfEmojis.getChildren().add(emojiLabel);
            }
        }
    }



}
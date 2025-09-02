import betty.Betty;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Betty betty;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image bettyImage = new Image(this.getClass().getResourceAsStream("/images/ChatBot.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setBetty(Betty b) {
        betty = b;
        // Add initial greeting to chatbot
        DialogBox bettyBox = DialogBox.getBettyDialog(betty.getGreeting(), bettyImage);
        dialogContainer.getChildren().addAll(bettyBox);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = betty.getResponse(input);
        DialogBox userBox = DialogBox.getUserDialog(input, userImage);
        DialogBox bettyBox = DialogBox.getBettyDialog(response, bettyImage);
        // Add dialog box into dialog container
        dialogContainer.getChildren().addAll(userBox, bettyBox);
        // Clear user input after creating dialog box
        userInput.clear();
        // Exit if command is bye
        if (input.equals("bye")) {
            Platform.exit();
        }
    }
}

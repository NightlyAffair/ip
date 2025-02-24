package javafx;

import cheryl.Cheryl;
import cheryl.manager.MainManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/** Controller for the main GUI. */
public class MainWindow extends AnchorPane {
  @FXML private ScrollPane scrollPane;
  @FXML private VBox dialogContainer;
  @FXML private TextField userInput;
  @FXML private Button sendButton;

  private Cheryl cheryl;

  private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
  private Image cherylImage =
      new Image(this.getClass().getResourceAsStream("/images/CherylChatBot.png"));

  @FXML
  public void initialize() {
    // Ensure scrollPane scrolls to the latest message
    scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    dialogContainer.getChildren().add(DialogBox.getCherylDialog(displayMessage(), cherylImage));
    dialogContainer.getChildren().add(DialogBox.getCherylDialog(startingMessage(), cherylImage));
  }

  /** Injects the Duke instance */
  public void setCheryl(Cheryl cheryl) {
    this.cheryl = cheryl;
  }

  public String displayMessage() {
    return "Hello! I'm Cheryl" + "\n" + "What can I do for you?";
  }

  public String startingMessage() {
    return MainManager.options();
  }

  /**
   * Creates two dialog boxes, one echoing user input and the other containing Cheryl's reply and
   * then appends them to the dialog container. Clears the user input after processing.
   */
  @FXML
  private void handleUserInput() {
    String input = userInput.getText();
    String response = cheryl.run(input);
    dialogContainer
        .getChildren()
        .addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getCherylDialog(response, cherylImage));

    userInput.clear();
  }
}

package javafx;

import cheryl.manager.TaskManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class TaskWindow {
  @FXML private ScrollPane scrollPane;
  @FXML private VBox dialogContainer;
  @FXML private TextField userInput;
  @FXML private Button sendButton;

  private TaskManager taskManager;

  private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
  private Image cherylImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

  @FXML
  public void initialize() {
    scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
  }
}

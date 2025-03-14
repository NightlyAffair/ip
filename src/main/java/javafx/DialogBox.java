package javafx;

import java.io.IOException;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
  @FXML private Label dialog;
  @FXML private ImageView displayPicture;

  private DialogBox(String text, Image img) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
      fxmlLoader.setController(this);
      fxmlLoader.setRoot(this);
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    dialog.setText(text);
    displayPicture.setImage(img);

    // Add listener to wait for scene to be set
    sceneProperty()
        .addListener(
            (observable, oldScene, newScene) -> {
              if (newScene != null) {
                setupScaling();
              }
            });
  }

  /** Sets up the scaling behavior for the dialog box */
  private void setupScaling() {
    // Find the ScrollPane in the scene
    ScrollPane scrollPane = (ScrollPane) getScene().lookup("#scrollPane");
    if (scrollPane != null) {
      // Bind the dialog box width to the ScrollPane width
      prefWidthProperty().bind(scrollPane.widthProperty().subtract(20));

      // Bind the label width to leave space for the image
      dialog
          .prefWidthProperty()
          .bind(scrollPane.widthProperty().subtract(displayPicture.getFitWidth() + 60));

      // Make sure the dialog box uses the full width
      HBox.setHgrow(dialog, javafx.scene.layout.Priority.ALWAYS);
    }
  }

  /** Flips the dialog box such that the ImageView is on the left and text on the right. */
  private void flip() {
    ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
    Collections.reverse(tmp);
    getChildren().setAll(tmp);
    setAlignment(Pos.TOP_LEFT);
  }

  public static DialogBox getUserDialog(String text, Image img) {
    return new DialogBox(text, img);
  }

  public static DialogBox getCherylDialog(String text, Image img) {
    var db = new DialogBox(text, img);
    db.flip();
    return db;
  }
}

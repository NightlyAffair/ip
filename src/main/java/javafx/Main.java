package javafx;

import cheryl.Cheryl;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** A GUI for Duke using FXML. */
public class Main extends Application {
  private Cheryl cheryl = new Cheryl();

  @Override
  public void start(Stage stage) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
      AnchorPane ap = fxmlLoader.load();
      Scene scene = new Scene(ap);
      stage.setScene(scene);
      stage.setResizable(true);
      stage.setTitle("Cheryl");
      stage
          .getIcons()
          .add(new Image(this.getClass().getResourceAsStream("/images/CherylChatBot.png")));

      fxmlLoader.<MainWindow>getController().setCheryl(cheryl); // inject the Duke instance
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

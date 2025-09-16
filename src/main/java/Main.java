import java.io.IOException;

import betty.Betty;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main entry point for the JavaFX application
 */
public class Main extends Application {

    private Betty betty = new Betty();
    /**
     * Starts the JavaFX application by setting up the primary stage
     * with the main window defined in the FXML file.
     *
     * @param stage the primary stage provided by the JavaFX runtime
     */
    @Override
    public void start(Stage stage) {
        //Setting up required components
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Betty");
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setBetty(betty); // inject the Betty instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

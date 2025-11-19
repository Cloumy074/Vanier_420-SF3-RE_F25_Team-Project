package vaniercollege;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *  Main program to run / Application entry point.
 */
public class Main extends Application {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/WaveDisplay.fxml"));

        // Initialize Scene
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("WaveSimulator");
        stage.setScene(scene);
        stage.show();
    }
}

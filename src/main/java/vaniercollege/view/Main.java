package vaniercollege.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Application Entrance.
 *
 * @author Yu Duo Zhang (2480549)
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML & Show Scene
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("WaveDisplay.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Wave Simulator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
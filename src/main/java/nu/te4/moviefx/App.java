package nu.te4.moviefx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This is the entry point of the app.
 * @author Adrian Klasson
 */
public class App extends Application {

    private static Scene scene;

    /**
     * Starts the application, is called internally by JavaFX.
     * @param stage The stage (window) to show.
     * @throws IOException If main.fxml is missing.
     */
    @Override
    public void start(Stage stage) throws IOException {
        ConnectionFactory.init();
        
        scene = new Scene(loadFXML("main"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * This is the standard main function called by Java. In JavaFX this is used as a backup.
     * @param args Arguments sent to the program at launch.
     */
    public static void main(String[] args) {
        launch();
    }

}
package nu.te4.moviefx.beans;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A class holding useful methods used by multiple windows.
 * @author Adrian Klasson
 */
public class UtilityBean {
    
    /**
     * Represents a window used by the application.
     */
    public enum Window {

        /**
         * The main window of the application.
         */
        Main("main.fxml", ""),
        
        /**
         * The window where you add filters.
         */
        AddFilter("addFilter.fxml", "Lägg till Filter"),
        
        /**
         * The window where you add movies.
         */
        AddMovie("addMovie.fxml", "Lägg till Film");
        
        /**
         * The filename of the FXML file for the window.
         */
        public final String fxmlFile;
        
        /**
         * The title of the window.
         */
        public final String title;
        
        private Window(String fxmlFile, String title){
            this.fxmlFile = fxmlFile;
            this.title = title;
        }
    }
    
    /**
     * Opens a new window.
     * @param window The window to open.
     */
    public void openWindow(Window window){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(window.fxmlFile));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(window.title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch(IOException ex){
            System.out.println("UtilityBean.openWindow: " + ex.getMessage());
        }
    }
}

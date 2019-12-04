package nu.te4.moviefx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nu.te4.moviefx.entities.Filter;

public class MainController {

    public static ObservableList<Filter> filters;
    
    @FXML
    private ComboBox<?> searchTypeBox;

    @FXML
    private TextField searchQueryField;

    @FXML
    private Button addFilterButton;

    @FXML
    private ListView<?> filterList;

    @FXML
    private Button addMovieButton;

    @FXML
    private TableView<?> movieTable;

    @FXML
    private TableColumn<?, ?> titleColumn;

    @FXML
    private TableColumn<?, ?> budgetColumn;

    @FXML
    private TableColumn<?, ?> revenueColumn;

    @FXML
    private TableColumn<?, ?> lengthColumn;

    @FXML
    private TableColumn<?, ?> gradeColumn;

    @FXML
    private TableColumn<?, ?> releaseDateColumn;

    @FXML
    private TableColumn<?, ?> directorColumn;

    @FXML
    private TableColumn<?, ?> genresColumn;

    @FXML
    void addFilterHandler(MouseEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addFilter.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Lägg till Filter");
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception ex){
            System.out.println("MainController.addFilterHandler: " + ex.getMessage());
        }
    }
    
    @FXML
    void addMovieHandler(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addMovie.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Lägg till Film");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception ex) {
            System.out.println("MainController.addMovieHandler: " + ex.getMessage());
        }
    }

}

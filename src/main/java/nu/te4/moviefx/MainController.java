package nu.te4.moviefx;

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

public class MainController {

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
    void addMovieHandler(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addMovie.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Lägg till Film");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception ex) {
            System.out.println("MainController.addMovieHandler: " + ex.getMessage());
        }
    }

}

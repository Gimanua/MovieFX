package nu.te4.moviefx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Controls the Add Movie window.
 * @author Adrian Klasson
 */
public class AddMovieController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField budgetField;

    @FXML
    private TextField revenueField;

    @FXML
    private Spinner<?> lengthSpinner;

    @FXML
    private Slider gradeSlider;

    @FXML
    private DatePicker releaseDatePicker;

    @FXML
    private ComboBox<?> directorBox;

    @FXML
    private ComboBox<?> genreBox;

    @FXML
    private Button addGenre;

    @FXML
    private ListView<?> genreList;

    @FXML
    private Button addMovieButton;

    @FXML
    private Button cancel;

    @FXML
    void addMovieHandler(MouseEvent event) {
        System.out.println("Add movie logic here!");
    }

    @FXML
    void cancelHandler(MouseEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

}

package nu.te4.moviefx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nu.te4.moviefx.beans.AddMovieBean;

/**
 * Controls the Add Movie window.
 *
 * @author Adrian Klasson
 */
public class AddMovieController {

    private final AddMovieBean addMovieBean = new AddMovieBean();
    
    @FXML
    private TextField titleField;

    @FXML
    private TextField budgetField;

    @FXML
    private TextField revenueField;

    @FXML
    private Spinner hoursSpinner;

    @FXML
    private Spinner minutesSpinner;

    @FXML
    private Spinner secondsSpinner;

    @FXML
    private Slider gradeSlider;

    @FXML
    private DatePicker releaseDatePicker;

    @FXML
    private ComboBox directorBox;

    @FXML
    private ComboBox genreBox;

    @FXML
    private Button addGenre;

    @FXML
    private TableView genreTable;

    @FXML
    private TableColumn genreColumn;

    @FXML
    private TableColumn removeColumn;

    @FXML
    private Button addMovieButton;

    @FXML
    private Button cancel;

    @FXML
    public void initialize() {
        addMovieBean.initializeFields(budgetField, revenueField);
        addMovieBean.initializeSpinners(hoursSpinner, minutesSpinner, secondsSpinner);
        addMovieBean.initializeDirectorBox(directorBox);
        addMovieBean.initializeGenreBox(genreBox);
    }

    @FXML
    void addGenreHandler(MouseEvent event) {
        addMovieBean.addGenre(genreBox.getValue().toString(), genreTable);
    }

    @FXML
    void addMovieHandler(MouseEvent event) {

    }

    @FXML
    void cancelHandler(MouseEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

}

package nu.te4.moviefx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import nu.te4.moviefx.beans.AddMovieBean;
import nu.te4.moviefx.entities.Director;
import nu.te4.moviefx.entities.Genre;

/**
 * Controls the Add Movie window.
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
    private Spinner<Integer> hoursSpinner;

    @FXML
    private Spinner<Integer> minutesSpinner;

    @FXML
    private Spinner<Integer> secondsSpinner;

    @FXML
    private Slider gradeSlider;

    @FXML
    private DatePicker releaseDatePicker;

    @FXML
    private ChoiceBox<Director> directorBox;

    @FXML
    private TextField directorField;

    @FXML
    private ChoiceBox<Genre> genreBox;

    @FXML
    private TextField genreField;

    @FXML
    private ListView<Genre> genreList;
    
    @FXML
    private Button cancelButton;

    @FXML
    void onGenreListKeyPressedHandler(KeyEvent event) {
        addMovieBean.genreListKeyPressed(event, genreList);
    }

    @FXML
    void onAddGenre(MouseEvent event) {
        addMovieBean.addGenre(genreBox, genreList);
    }

    @FXML
    void onAddMovie(MouseEvent event) {
        addMovieBean.addMovie(titleField, budgetField, revenueField, hoursSpinner, minutesSpinner, secondsSpinner, gradeSlider, releaseDatePicker, directorBox, genreList);
    }

    @FXML
    void onAddNewDirector(MouseEvent event) {
        addMovieBean.addNewDirector(directorField, directorBox);
    }

    @FXML
    void onAddNewGenre(MouseEvent event) {
        addMovieBean.addNewGenre(genreField, genreBox);
    }

    @FXML
    void onCancel(MouseEvent event) {
        addMovieBean.cancel(cancelButton);
    }


    @FXML
    public void initialize() {
        addMovieBean.initializeFields(budgetField, revenueField);
        addMovieBean.initializeSpinners(hoursSpinner, minutesSpinner, secondsSpinner);
        addMovieBean.initializeDirectorBox(directorBox);
        addMovieBean.initializeGenreBox(genreBox);
    }
}

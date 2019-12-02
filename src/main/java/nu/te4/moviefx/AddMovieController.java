package nu.te4.moviefx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class AddMovieController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField budgetField;

    @FXML
    private TextField revenueField;

    @FXML
    private Slider lengthSlider;

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

}

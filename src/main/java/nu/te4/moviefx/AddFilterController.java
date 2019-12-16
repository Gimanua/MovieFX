package nu.te4.moviefx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import nu.te4.moviefx.beans.AddFilterBean;
import nu.te4.moviefx.entities.Filter;
import nu.te4.moviefx.entities.filters.TitleFilter;
import nu.te4.moviefx.entities.filters.TitleFilter.FilterChoice;

/**
 * Controls the Add Filter window.
 * @author Adrian Klasson
 */
public class AddFilterController {

    private final AddFilterBean addFilterBean = new AddFilterBean();
    
    @FXML
    private ChoiceBox<String> filterBox;

    @FXML
    private HBox titleFilterForm;

    @FXML
    private ChoiceBox<?> titleFilterChoices;

    @FXML
    private TextField titleFilterField;

    @FXML
    private HBox budgetFilterForm;

    @FXML
    private ChoiceBox<?> budgetFilterChoices;

    @FXML
    private TextField budgetFilterField;

    @FXML
    private HBox revenueFilterForm;

    @FXML
    private ChoiceBox<?> revenueFilterChoices;

    @FXML
    private TextField revenueFilterField;

    @FXML
    private GridPane lengthFilterForm;

    @FXML
    private Spinner lengthFilterHoursSpinner;

    @FXML
    private Spinner lengthFilterMinutesSpinner;

    @FXML
    private Spinner lengthFilterSecondsSpinner;

    @FXML
    private ChoiceBox lengthFilterChoices;

    @FXML
    private HBox ratingFilterForm;

    @FXML
    private ChoiceBox ratingFilterChoices;

    @FXML
    private Slider ratingFilterSlider;

    @FXML
    private HBox releaseDateFilterForm;

    @FXML
    private ChoiceBox releaseDateFilterChoices;

    @FXML
    private DatePicker releaseDateFilterDatePicker;

    @FXML
    private HBox directorFilterForm;

    @FXML
    private ChoiceBox directorFilterChoices;

    @FXML
    private ChoiceBox directorFilterDirectorChoices;

    @FXML
    private Button cancelButton;

    @FXML
    void onAddFilter(MouseEvent event) {

    }

    @FXML
    void onCancel(MouseEvent event) {
        addFilterBean.cancel(cancelButton);
    }

    /**
     * Initalizes this window. This method is called internally by JavaFX.
     */
    @FXML
    public void initialize() {
        addFilterBean.initializeFilterBox(filterBox, titleFilterForm, budgetFilterForm, revenueFilterForm, lengthFilterForm, ratingFilterForm, releaseDateFilterForm, directorFilterForm);
    }
    
    /*
    @FXML
    void addFilterHandler(MouseEvent event) {
        Filter filter;
        String selectedFilterType = filterType.getItems().get(filterType.getSelectionModel().getSelectedIndex()).toString();

        switch (selectedFilterType) {
            case "Titel":
                String filterValue = titleFilterField.getText();
                FilterChoice filterChoice;
                filter = new TitleFilter(titleFilterField.getText(), TitleFilter.FilterChoice.StartsWith);
                break;
            default:
                filter = null;
                break;
        }
        //Add the filter

        //Close the window
    }
    */
}

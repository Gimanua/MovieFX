/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.moviefx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import nu.te4.moviefx.entities.Filter;
import nu.te4.moviefx.entities.filters.TitleFilter;
import nu.te4.moviefx.entities.filters.TitleFilter.FilterChoice;

/**
 *
 * @author Adrian Klasson
 */
public class AddFilterController {

    @FXML
    private ChoiceBox filterType;

    @FXML
    private HBox titleFilterForm;

    @FXML
    private ChoiceBox titleFilterChoices;

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
    private HBox lengthFilterForm;

    @FXML
    private ChoiceBox<?> lengthFilterChoices;

    @FXML
    private Spinner<?> lengthFilterSpinner;

    @FXML
    private HBox ratingFilterForm;

    @FXML
    private ChoiceBox<?> ratingFilterChoices;

    @FXML
    private Slider ratingFilterSlider;

    @FXML
    private HBox releaseDateFilterForm;

    @FXML
    private ChoiceBox<?> releaseDateFilterChoices;

    @FXML
    private DatePicker releaseDateFilterDatePicker;

    @FXML
    private HBox directorFilterForm;

    @FXML
    private ChoiceBox<?> directorFilterChoices;

    @FXML
    private ChoiceBox<?> directorFilterDirectorChoices;

    @FXML
    private Button cancelButton;

    private HBox currentForm;

    @FXML
    public void initialize() {
        initializeFilterType();
    }

    private void initializeFilterType() {
        filterType.getItems().add("Titel");
        filterType.getItems().add("Budget");
        filterType.getItems().add("Inkomst");
        filterType.getItems().add("Längd");
        filterType.getItems().add("Betyg");
        filterType.getItems().add("Lanseringsdatum");
        filterType.getItems().add("Regissör");
        filterType.getItems().add("Genrer");

        ChangeListener<Number> changeListener = new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                String selectedOption = filterType.getItems().get((Integer) newValue).toString();

                if (currentForm != null) {
                    currentForm.setVisible(false);
                }
                switch (selectedOption) {
                    case "Titel":
                        currentForm = titleFilterForm;
                        break;
                    case "Budget":
                        currentForm = budgetFilterForm;
                        break;
                    case "Inkomst":
                        currentForm = revenueFilterForm;
                        break;
                    case "Längd":
                        currentForm = lengthFilterForm;
                        break;
                    case "Betyg":
                        currentForm = ratingFilterForm;
                        break;
                    case "Lanseringsdatum":
                        currentForm = releaseDateFilterForm;
                        break;
                    case "Regissör":
                        currentForm = directorFilterForm;
                        break;
                    case "Genrer":
                        //Add form for this
                        break;
                }
                currentForm.setVisible(true);
            }
        };
        filterType.getSelectionModel().selectedIndexProperty().addListener(changeListener);
    }

    private void initializeTitleFilterForm(){
        titleFilterChoices.getItems().add("innehåller");
        titleFilterChoices.getItems().add("innehåller inte");
        titleFilterChoices.getItems().add("börjar med");
        titleFilterChoices.getItems().add("börjar inte med");
        titleFilterChoices.getItems().add("slutar med");
        titleFilterChoices.getItems().add("slutar inte med");
    }
    
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

        MainController.filters.add(filter);
        //Close the window
    }

    @FXML
    void cancelHandler(MouseEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}

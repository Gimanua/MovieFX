/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.moviefx.beans;

import java.sql.Time;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import nu.te4.moviefx.entities.filters.BudgetFilter;
import nu.te4.moviefx.entities.filters.LengthFilter;
import nu.te4.moviefx.entities.filters.RevenueFilter;
import nu.te4.moviefx.entities.filters.TitleFilter;

/**
 *
 * @author Adrian Klasson
 */
public class AddFilterBean {

    /**
     * Cancels the whole operation and closes the window.
     *
     * @param cancelButton The button used to cancel the operation.
     */
    public void cancel(Button cancelButton) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Adds a filter based on which form was filled in.
     *
     * @param filterBox The choice box containing which type of filter was
     * filled in.
     * @param titleFilterForm The title filter form.
     * @param budgetFilterForm The budget filter form.
     * @param revenueFilterForm The revenue filter form.
     * @param lengthFilterForm The length filter form.
     * @param ratingFilterForm The rating filter form.
     * @param releaseDateFilterForm The release date filter form.
     * @param directorFilterForm The director filter form.
     */
    public void addFilter(ChoiceBox<String> filterBox, HBox titleFilterForm, HBox budgetFilterForm, HBox revenueFilterForm,
            GridPane lengthFilterForm, HBox ratingFilterForm, HBox releaseDateFilterForm, HBox directorFilterForm) {

        String filterName = filterBox.getSelectionModel().getSelectedItem();
        switch (filterName) {
            case "Titel":
                ChoiceBox<String> titleFilterChoices = (ChoiceBox) titleFilterForm.getChildren().get(1);
                TextField titleFilterField = (TextField) titleFilterForm.getChildren().get(2);
                addTitleFilter(titleFilterChoices, titleFilterField);
                break;
            case "Budget":
                ChoiceBox<String> budgetFilterChoices = (ChoiceBox) budgetFilterForm.getChildren().get(1);
                TextField budgetFilterField = (TextField) budgetFilterForm.getChildren().get(2);
                addBudgetFilter(budgetFilterChoices, budgetFilterField);
                break;
            case "Inkomst":
                ChoiceBox<String> revenueFilterChoices = (ChoiceBox) revenueFilterForm.getChildren().get(1);
                TextField revenueFilterField = (TextField) revenueFilterForm.getChildren().get(2);
                addRevenueFilter(revenueFilterChoices, revenueFilterField);
                break;
            case "Längd":
                ChoiceBox<String> lengthFilterChoices = (ChoiceBox) lengthFilterForm.getChildren().get(1);
                Spinner<Integer> lengthHoursSpinner = (Spinner) lengthFilterForm.getChildren().get(2);
                Spinner<Integer> lengthMinutesSpinner = (Spinner) lengthFilterForm.getChildren().get(3);
                Spinner<Integer> lengthSecondsSpinner = (Spinner) lengthFilterForm.getChildren().get(4);
                addLengthFilter(lengthFilterChoices, lengthHoursSpinner, lengthMinutesSpinner, lengthSecondsSpinner);
                break;
            default:
                return;
        }

        Stage stage = (Stage) filterBox.getScene().getWindow();
        stage.close();
    }

    /**
     * Adds a Title Filter.
     *
     * @param titleFilterChoices The choices available for the title filter.
     * @param titleFilterField The field containg the value for the title
     * filter.
     */
    private void addTitleFilter(ChoiceBox<String> titleFilterChoices, TextField titleFilterField) {
        String filterChoiceString = titleFilterChoices.getValue();
        String filterField = titleFilterField.getText();
        if (filterChoiceString.isEmpty() || filterField.isEmpty()) {
            return;
        }

        for (TitleFilter.FilterChoice filterChoice : TitleFilter.FilterChoice.values()) {
            if (filterChoice.toString().equals(filterChoiceString)) {
                MainBean.addFilter(new TitleFilter(filterField, filterChoice));
                break;
            }
        }
    }

    private void addBudgetFilter(ChoiceBox<String> budgetFilterChoices, TextField budgetFilterField) {
        try {
            String filterChoiceString = budgetFilterChoices.getValue();
            long filterField = Long.parseLong(budgetFilterField.getText());

            for (BudgetFilter.FilterChoice filterChoice : BudgetFilter.FilterChoice.values()) {
                if (filterChoice.toString().equals(filterChoiceString)) {
                    MainBean.addFilter(new BudgetFilter(filterField, filterChoice));
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("AddFilterBean.addBudgetFilter: " + ex.getMessage());
        }
    }

    private void addRevenueFilter(ChoiceBox<String> revenueFilterChoices, TextField revenueFilterField) {
        try {
            String filterChoiceString = revenueFilterChoices.getValue();
            long filterField = Long.parseLong(revenueFilterField.getText());

            for (RevenueFilter.FilterChoice filterChoice : RevenueFilter.FilterChoice.values()) {
                if (filterChoice.toString().equals(filterChoiceString)) {
                    MainBean.addFilter(new RevenueFilter(filterField, filterChoice));
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("AddFilterBean.addRevenueFilter: " + ex.getMessage());
        }
    }

    private void addLengthFilter(ChoiceBox<String> lengthFilterChoices, Spinner<Integer> lengthHoursSpinner,
            Spinner<Integer> lengthMinutesSpinner, Spinner<Integer> lengthSecondsSpinner) {
        try {
            String filterChoiceString = lengthFilterChoices.getValue();
            int hours = lengthHoursSpinner.getValue();
            int minutes = lengthMinutesSpinner.getValue();
            int seconds = lengthSecondsSpinner.getValue();

            for (LengthFilter.FilterChoice filterChoice : LengthFilter.FilterChoice.values()) {
                if (filterChoice.toString().equals(filterChoiceString)) {
                    MainBean.addFilter(new LengthFilter(new Time(hours, minutes, seconds), filterChoice));
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("AddFilterBean.addLengthFilter: " + ex.getMessage());
        }
    }

    /**
     * Initializes the filter box.
     *
     * @param filterBox The ChoiceBox containing the filter options.
     * @param titleFilterForm The form for the title filter.
     * @param budgetFilterForm The form for the budget filter.
     * @param revenueFilterForm The form for the revenue filter.
     * @param lengthFilterForm The form for the length filter.
     * @param ratingFilterForm The form for the rating filter.
     * @param releaseDateFilterForm The form for the release date filter.
     * @param directorFilterForm The form for the director filter.
     */
    public void initializeFilterBox(ChoiceBox<String> filterBox, HBox titleFilterForm, HBox budgetFilterForm, HBox revenueFilterForm,
            GridPane lengthFilterForm, Node ratingFilterForm, Node releaseDateFilterForm, Node directorFilterForm) {
        filterBox.getItems().add("Titel");
        filterBox.getItems().add("Budget");
        filterBox.getItems().add("Inkomst");
        filterBox.getItems().add("Längd");
        filterBox.getItems().add("Betyg");
        filterBox.getItems().add("Lanseringsdatum");
        filterBox.getItems().add("Regissör");

        initializeTitleFilterForm((ChoiceBox<String>) titleFilterForm.getChildren().get(1));
        initializeBudgetFilterForm((ChoiceBox<String>) budgetFilterForm.getChildren().get(1));
        initializeRevenueFilterForm((ChoiceBox<String>) revenueFilterForm.getChildren().get(1));
        initializeLengthFilterForm((ChoiceBox<String>) lengthFilterForm.getChildren().get(1), (Spinner)lengthFilterForm.getChildren().get(2),
                (Spinner)lengthFilterForm.getChildren().get(3), (Spinner)lengthFilterForm.getChildren().get(4));

        ChangeListener<Number> changeListener = (ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) -> {
            String selectedOption = filterBox.getItems().get((Integer) newValue);

            titleFilterForm.setVisible(false);
            budgetFilterForm.setVisible(false);
            revenueFilterForm.setVisible(false);
            lengthFilterForm.setVisible(false);
            ratingFilterForm.setVisible(false);
            releaseDateFilterForm.setVisible(false);
            directorFilterForm.setVisible(false);

            switch (selectedOption) {
                case "Titel":
                    titleFilterForm.setVisible(true);
                    break;
                case "Budget":
                    budgetFilterForm.setVisible(true);
                    break;
                case "Inkomst":
                    revenueFilterForm.setVisible(true);
                    break;
                case "Längd":
                    lengthFilterForm.setVisible(true);
                    break;
                case "Betyg":
                    ratingFilterForm.setVisible(true);
                    break;
                case "Lanseringsdatum":
                    releaseDateFilterForm.setVisible(true);
                    break;
                case "Regissör":
                    directorFilterForm.setVisible(true);
                    break;
            }
        };
        filterBox.getSelectionModel().selectedIndexProperty().addListener(changeListener);
    }

    /**
     * Initializes the title filter form.
     *
     * @param titleFilterChoices The choice box containing the choices availble
     * for the title filter.
     */
    private void initializeTitleFilterForm(ChoiceBox<String> titleFilterChoices) {
        titleFilterChoices.getItems().add(TitleFilter.FilterChoice.Contains.toString());
        titleFilterChoices.getItems().add(TitleFilter.FilterChoice.DoesNotContain.toString());
        titleFilterChoices.getItems().add(TitleFilter.FilterChoice.StartsWith.toString());
        titleFilterChoices.getItems().add(TitleFilter.FilterChoice.DoesNotStartWith.toString());
        titleFilterChoices.getItems().add(TitleFilter.FilterChoice.EndsWith.toString());
        titleFilterChoices.getItems().add(TitleFilter.FilterChoice.DoesNotEndWith.toString());
    }

    /**
     * Initializes the budget filter form.
     *
     * @param budgetFilterChoices The choice box containing the choices
     * available for the filter.
     */
    private void initializeBudgetFilterForm(ChoiceBox<String> budgetFilterChoices) {
        budgetFilterChoices.getItems().add(BudgetFilter.FilterChoice.GreaterThan.toString());
        budgetFilterChoices.getItems().add(BudgetFilter.FilterChoice.LessThan.toString());
    }

    /**
     * Initializes the revenue filter form.
     *
     * @param revenueFilterChoices The choice box containing the choices
     * available for the filter.
     */
    private void initializeRevenueFilterForm(ChoiceBox<String> revenueFilterChoices) {
        revenueFilterChoices.getItems().add(RevenueFilter.FilterChoice.GreaterThan.toString());
        revenueFilterChoices.getItems().add(RevenueFilter.FilterChoice.LessThan.toString());
    }

    /**
     * Initializes the length filter form.
     *
     * @param lengthFilterChoices The choice box containing the choices
     * available for the filter.
     */
    private void initializeLengthFilterForm(ChoiceBox<String> lengthFilterChoices, Spinner<Integer> hoursSpinner, Spinner<Integer> minutesSpinner, Spinner<Integer> secondsSpinner) {
        lengthFilterChoices.getItems().add(LengthFilter.FilterChoice.LongerThan.toString());
        lengthFilterChoices.getItems().add(LengthFilter.FilterChoice.ShorterThan.toString());
        hoursSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99));
        minutesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        secondsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.moviefx.beans;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Adrian Klasson
 */
public class AddFilterBean {
    
    /**
     * Cancels the whole operation and closes the window.
     * @param cancelButton The button used to cancel the operation.
     */
    public void cancel(Button cancelButton){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    public void addFilter(ChoiceBox<String> filterBox, HBox titleFilterForm, HBox budgetFilterForm, HBox revenueFilterForm,
            GridPane lengthFilterForm, HBox ratingFilterForm, HBox releaseDateFilterForm, HBox directorFilterForm){
        String filterName = filterBox.getSelectionModel().getSelectedItem();
        if(filterName.equals("Titel")){
            String filterValue = titleFilterForm.getChildren().get(0).;
        }
        else if(filterName.equals("Budget")){
            
        }
    }
    
    
    
    /**
     * Initializes the filter box.
     * @param filterBox The ChoiceBox containing the filter options.
     * @param titleFilterForm The form for the title filter.
     * @param budgetFilterForm The form for the budget filter.
     * @param revenueFilterForm The form for the revenue filter.
     * @param lengthFilterForm The form for the length filter.
     * @param ratingFilterForm The form for the rating filter.
     * @param releaseDateFilterForm The form for the release date filter.
     * @param directorFilterForm  The form for the director filter.
     */
    public void initializeFilterBox(ChoiceBox<String> filterBox, Node titleFilterForm, Node budgetFilterForm, Node revenueFilterForm,
            Node lengthFilterForm, Node ratingFilterForm, Node releaseDateFilterForm, Node directorFilterForm){
        filterBox.getItems().add("Titel");
        filterBox.getItems().add("Budget");
        filterBox.getItems().add("Inkomst");
        filterBox.getItems().add("Längd");
        filterBox.getItems().add("Betyg");
        filterBox.getItems().add("Lanseringsdatum");
        filterBox.getItems().add("Regissör");
        filterBox.getItems().add("Genrer");
        
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
                case "Genrer":
                    //Add form for this
                    break;
            }
        };
        filterBox.getSelectionModel().selectedIndexProperty().addListener(changeListener);
    }
}

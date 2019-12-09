/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.moviefx.beans;

import java.util.function.UnaryOperator;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import nu.te4.moviefx.entities.Genre;

/**
 *
 * @author Adrian Klasson
 */
public class AddMovieBean {
    
    private final DirectorBean directorBean = new DirectorBean();
    private final GenreBean genreBean = new GenreBean();
    
    public void initializeFields(TextField... fields){
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if(change.isContentChange()){
                if(change.getControlNewText().length() > 18)
                    return null;
            }
            return text.matches("[0-9]*") ? change : null;
        };
        
        for(TextField field : fields){
            TextFormatter<String> textFormatter = new TextFormatter<>(filter);
            field.setTextFormatter(textFormatter);
        }
    }
    
    public void initializeSpinners(Spinner hoursSpinner, Spinner minutesSpinner, Spinner secondsSpinner){
        hoursSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 838));
        minutesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        secondsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
    }
    
    public void initializeDirectorBox(ComboBox directorBox){
        directorBox.getItems().addAll(directorBean.getDirectors());
    }
    
    public void initializeGenreBox(ComboBox genreBox){
        genreBox.getItems().addAll(genreBean.getGenres());
    }
    
    public void initializeGenreColumns(TableColumn genreColumn, TableColumn removeColumn){
        //Initialize the columns
    }
    
    public void addGenre(String genre, TableView genreTable){
        // Add the genre and the remove button
    }
    
}

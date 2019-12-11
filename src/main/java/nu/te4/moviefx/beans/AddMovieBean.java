/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.moviefx.beans;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import nu.te4.moviefx.entities.Director;
import nu.te4.moviefx.entities.Genre;
import nu.te4.moviefx.entities.Movie;

/**
 *
 * @author Adrian Klasson
 */
public class AddMovieBean {
    
    private final DirectorBean directorBean = new DirectorBean();
    private final GenreBean genreBean = new GenreBean();
    private final MovieBean movieBean = new MovieBean();
    
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
        hoursSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99));
        minutesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        secondsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
    }
    
    public void initializeDirectorBox(ComboBox directorBox){
        directorBox.getItems().addAll(directorBean.getDirectors());
    }
    
    public void initializeGenreBox(ComboBox genreBox){
        genreBox.getItems().addAll(genreBean.getGenres());
    }
    
    public void addGenre(Object genre, ListView genreList){

        Genre listItem;
        if (genre instanceof Genre)
            listItem = (Genre)genre;
        else
            listItem = new Genre(genre.toString());

        boolean identicalEntry = false;
        for (int i = 0; i < genreList.getItems().size(); i++){
            if(genreList.getItems().get(i).toString().equals(listItem.toString())){
                identicalEntry = true;
                break;
            }
        }

        if(!identicalEntry)
            genreList.getItems().add(listItem);
    }

    public void genreListKeyPressed(KeyEvent e, ListView genreList){
        KeyCode keyCode = e.getCode();
        if (keyCode == KeyCode.DELETE){
            if(genreList.getItems().size() <= 0)
                return;
            int selectedIndex = genreList.getSelectionModel().getSelectedIndex();
            genreList.getItems().remove(selectedIndex);
        }
    }

    public void addMovie(TextField title, TextField budget, TextField revenue, Spinner hours, Spinner minutes, Spinner seconds,
                         Slider grade, DatePicker releaseDate, ComboBox director, ListView genreList){
        String movieTitle = title.getText();
        Long movieBudget = Long.parseLong(budget.getText());
        Long movieRevenue = Long.parseLong(revenue.getText());
        Time movieLength = Time.valueOf(hours.getValue().toString() + ":" + minutes.getValue().toString() + ":" + seconds.getValue().toString());
        double movieGrade = grade.getValue();
        Timestamp movieReleaseDate = Timestamp.from(Date.valueOf(releaseDate.getValue()).toInstant());
        Director movieDirector = (Director)director.getSelectionModel().getSelectedItem();
        List<Genre> movieGenres = new ArrayList();
        genreList.getItems().forEach((genre) -> movieGenres.add((Genre)genre));

        Movie movie = new Movie(movieTitle, movieBudget, movieRevenue, movieLength, movieGrade, movieReleaseDate, movieDirector, movieGenres);
        movieBean.postMovie(movie);
    }
}

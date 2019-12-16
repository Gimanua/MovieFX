package nu.te4.moviefx.beans;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import nu.te4.moviefx.entities.Director;
import nu.te4.moviefx.entities.Genre;
import nu.te4.moviefx.entities.Movie;

/**
 * Handles all the logic for the Add Movie window.
 * @author Adrian Klasson
 */
public class AddMovieBean {
    
    /**
     * Handles all the logic for directors.
     */
    private final DirectorBean directorBean = new DirectorBean();
    
    /**
     * Handles all the logic for genres.
     */
    private final GenreBean genreBean = new GenreBean();
    
    /**
     * Handles all the logic for movies.
     */
    private final MovieBean movieBean = new MovieBean();
    
    /**
     * Initializes all the text fields with the proper input rules.
     * @param fields The fields to initialize with input rules.
     */
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
    
    /**
     * Initializes all the spinners to be Integer spinners.
     * @param hoursSpinner The spinner containing the hours of the movie.
     * @param minutesSpinner The spinner containing the minutes of the movie (after hours).
     * @param secondsSpinner The spinner containing the seconds of the movie (after hours and minutes).
     */
    public void initializeSpinners(Spinner hoursSpinner, Spinner minutesSpinner, Spinner secondsSpinner){
        hoursSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99));
        minutesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        secondsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
    }
    
    /**
     * Initializes the director box with the Directors already available from the database.
     * @param directorBox The ChoiceBox that stores the available director options for the movie.
     */
    public void initializeDirectorBox(ChoiceBox<Director> directorBox){
        directorBox.getItems().addAll(directorBean.getDirectors());
    }
    
    /**
     * Initializes the genre box with the Genres already available from the database.
     * @param genreBox 
     */
    public void initializeGenreBox(ChoiceBox<Genre> genreBox){
        genreBox.getItems().addAll(genreBean.getGenres());
    }
    
    /**
     * Adds a new director to the available director choices.
     * @param directorField The field where you input the director name.
     * @param directorBox The ChoiceBox that stores the available director options for the movie.
     */
    public void addNewDirector(TextField directorField, ChoiceBox<Director> directorBox){
        String directorName = directorField.getText();
        boolean exists = false;
        for (int i = 0; i < directorBox.getItems().size(); i++) {
            if(directorBox.getItems().get(i).getName().equals(directorName)){
                exists = true;
                break;
            }
        }
        if(!exists)
            directorBox.getItems().add(new Director(directorName));
    }
    
    /**
     * Adds a genre from the choicebox to the list of genres for the movie.
     * @param genreBox The choicebox containing the genre to add.
     * @param genreList The list of genres the movie has.
     */
    public void addGenre(ChoiceBox<Genre> genreBox, ListView<Genre> genreList){

        Genre genre = genreBox.getValue();
        boolean exists = false;
        for (int i = 0; i < genreList.getItems().size(); i++){
            if(genreList.getItems().get(i).getName().equals(genre.getName())){
                exists = true;
                break;
            }
        }
        if(!exists)
            genreList.getItems().add(genre);
    }

    /**
     * Adds a new genre to the available genre choices.
     * @param genreField The field where you input the genre name.
     * @param genreBox The ChoiceBox that stores the available genre options for the movie.
     */
    public void addNewGenre(TextField genreField, ChoiceBox<Genre> genreBox){
        String genreName = genreField.getText();
        boolean exists = false;
        for (int i = 0; i < genreBox.getItems().size(); i++) {
            if(genreBox.getItems().get(i).getName().equals(genreName)){
                exists = true;
                break;
            }
        }
        if(!exists)
            genreBox.getItems().add(new Genre(genreName));
    }
    
    /**
     * Cancels the whole operation and closes the window.
     * @param cancelButton The button used to close.
     */
    public void cancel(Button cancelButton){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Check for a Delete key press, if one is detected it removes the selected genre.
     * @param e The key event containing the pressed key.
     * @param genreList The list of genres of this movie.
     */
    public void genreListKeyPressed(KeyEvent e, ListView<Genre> genreList){
        KeyCode keyCode = e.getCode();
        if (keyCode == KeyCode.DELETE){
            if(genreList.getItems().size() <= 0)
                return;
            
            int selectedIndex = genreList.getSelectionModel().getSelectedIndex();
            if(selectedIndex != -1)
                genreList.getItems().remove(selectedIndex);
        }
    }

    /**
     * Adds a movie to the database.
     * @param titleField The field containing the title of the movie.
     * @param budgetField The field containing the budget of the movie.
     * @param revenueField The field containing the revenue of the movie.
     * @param hoursSpinner The spinner containing the hours of the movie.
     * @param minutesSpinner The spinner containing the minutes of the movie (after hours).
     * @param secondsSpinner The spinner containing the seconds of the movie (after hours and minutes).
     * @param gradeSlider The slider containing the grade of the movie.
     * @param releaseDatePicker The date picker containing the release date of the movie.
     * @param directorBox The choice box containg the director of the movie.
     * @param genreList The list of genres of the movie.
     */
    public void addMovie(TextField titleField, TextField budgetField, TextField revenueField, Spinner hoursSpinner, Spinner minutesSpinner, Spinner secondsSpinner,
                         Slider gradeSlider, DatePicker releaseDatePicker, ChoiceBox<Director> directorBox, ListView<Genre> genreList){

        if(titleField.getText().isEmpty() || budgetField.getText().isEmpty() || revenueField.getText().isEmpty() || releaseDatePicker.getValue() == null || directorBox.getValue() == null || genreList.getItems().isEmpty())
            return;

        String movieTitle = titleField.getText();
        Long movieBudget = Long.parseLong(budgetField.getText());
        Long movieRevenue = Long.parseLong(revenueField.getText());
        Time movieLength = Time.valueOf(hoursSpinner.getValue().toString() + ":" + minutesSpinner.getValue().toString() + ":" + secondsSpinner.getValue().toString());
        double movieGrade = gradeSlider.getValue();
        Date movieReleaseDate = Date.valueOf(releaseDatePicker.getValue());
        Director movieDirector = directorBox.getValue();

        List<Genre> movieGenres = new ArrayList();
        genreList.getItems().forEach((genre) -> movieGenres.add(genre));

        Movie movie = new Movie(movieTitle, movieBudget, movieRevenue, movieLength, movieGrade, movieReleaseDate, movieDirector, movieGenres);
        movieBean.postMovie(movie);

        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }
}

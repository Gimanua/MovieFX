package nu.te4.moviefx.beans;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * A class for doing all the logic for the Main window.
 * @author Adrian Klasson
 */
public class MainBean {
    
    /**
     * Handles all the logic regarding movies.
     */
    private final MovieBean movieBean = new MovieBean();
    
    /**
     * Initializes the search by type box with alternatives.
     * @param searchTypeBox The box containing the search type options.
     */
    public void initializeSearchTypes(ChoiceBox<String> searchTypeBox){
        searchTypeBox.getItems().addAll("Titel", "Regissör", "Genre");
    }
    
    /**
     * Initializes the columns of the movie table.
     * @param titleColumn The title column.
     * @param budgetColumn The budget column.
     * @param revenueColumn The revenue column.
     * @param lengthColumn The length column.
     * @param gradeColumn The grade column.
     * @param releaseDateColumn The release date column.
     * @param directorColumn The director column.
     * @param genresColumn The genres column.
     */
    public void initializeColumns(TableColumn titleColumn, TableColumn budgetColumn, TableColumn revenueColumn,
            TableColumn lengthColumn, TableColumn gradeColumn, TableColumn releaseDateColumn, TableColumn directorColumn, TableColumn genresColumn){
        titleColumn.setCellValueFactory(new PropertyValueFactory("title"));
        budgetColumn.setCellValueFactory(new PropertyValueFactory("budget"));
        revenueColumn.setCellValueFactory(new PropertyValueFactory("revenue"));
        lengthColumn.setCellValueFactory(new PropertyValueFactory("length"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory("grade"));
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory("releaseDate"));
        directorColumn.setCellValueFactory(new PropertyValueFactory("director"));
        genresColumn.setCellValueFactory(new PropertyValueFactory("genres"));
    }
    
    /**
     * Loads all the movies from the database onto the table of movies.
     * @param movieTable The table that is used as a container for all the movies.
     */
    public void loadMovies(TableView movieTable){
        movieTable.getItems().addAll(movieBean.getMovies());
    }
    
    /**
     * Reloads all the movies from the database onto the table of movies.
     * @param movieTable The table that is used as a container for all the movies.
     */
    public void reloadMovies(TableView movieTable){
        movieTable.getItems().clear();
        loadMovies(movieTable);
    }
    
}

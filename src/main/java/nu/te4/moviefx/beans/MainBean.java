package nu.te4.moviefx.beans;

import java.util.Map;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import nu.te4.moviefx.entities.Movie;

/**
 * A class for doing all the logic for the Main window.
 * @author Adrian Klasson
 */
public class MainBean {
    
    private final UtilityBean utilityBean = new UtilityBean();
    private final MovieBean movieBean = new MovieBean();
    
    /**
     * Initializes the columns to listen for certain properties from a Movie object.
     * @param columns The columns to initialize.
     */
    public void initializeColumns(Map<TableColumn, String> columns){
        columns.forEach((column, property) -> column.setCellValueFactory(new PropertyValueFactory<Movie, String>(property)));
    }
    
    /**
     * Loads all the movies from the database onto the table of movies.
     * @param movieTable The table that is used as a container for all the movies.
     */
    public void loadMovies(TableView movieTable){
        movieTable.getItems().addAll(movieBean.getMovies());
    }
}

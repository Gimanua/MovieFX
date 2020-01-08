package nu.te4.moviefx.beans;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import nu.te4.moviefx.entities.Filter;
import nu.te4.moviefx.entities.Movie;
import nu.te4.moviefx.entities.filters.DirectorFilter;
import nu.te4.moviefx.entities.filters.GenreFilter;
import nu.te4.moviefx.entities.filters.TitleFilter;

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
     * Holds all the filters.
     */
    private static final ObservableList<Filter> filters = FXCollections.observableArrayList();
    
    private enum SearchType{
        Title("Titel"),
        Director("Regissör"),
        Genre("Genre");
        
        private final String appValue;

        private SearchType(String string){
            this.appValue = string;
        }
        
        @Override
        public String toString() {
            return appValue;
        }
    }
    
    /**
     * Initializes the search by type box with alternatives.
     * @param searchTypeBox The box containing the search type options.
     */
    public void initializeSearchTypes(ChoiceBox<String> searchTypeBox){
        searchTypeBox.getItems().addAll(SearchType.Title.toString(), SearchType.Director.toString(), SearchType.Genre.toString());
    }
    
    /**
     * Initializes the filter list.
     * @param filterList The filter list.
     */
    public void initializeFilterList(ListView<Filter> filterList){
        filterList.setItems(filters);
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
     * @param searchTypeBox The box that is used to choose what you are searching for.
     * @param searchQueryField The field where you specify a search query.
     */
    public void loadMovies(TableView movieTable, ChoiceBox<String> searchTypeBox, TextField searchQueryField){
        List<Movie> movies = movieBean.getMovies();
        for(Filter filter : filters){
            movies.removeIf(movie -> !filter.filter(movie));
        }
        
        String searchQuery = searchQueryField.getText();
        if(!searchQuery.isEmpty()){
            String searchType = searchTypeBox.getValue();
            Filter filter;
            
            if(searchType.equals(SearchType.Title.toString())){
                filter = new TitleFilter(searchQuery, TitleFilter.FilterChoice.Contains);
            }
            else if(searchType.equals(SearchType.Director.toString())){
                filter = new DirectorFilter(searchQuery, DirectorFilter.FilterChoice.DirectedBy);
            }
            else{
                filter = new GenreFilter(searchQuery, GenreFilter.FilterChoice.Has);
            }
            movies.removeIf(movie -> !filter.filter(movie));
        }
        
        movieTable.getItems().addAll(movies);
    }
    
    /**
     * Reloads all the movies from the database onto the table of movies.
     * @param movieTable The table that is used as a container for all the movies.
     * @param searchTypeBox The box that is used to choose what you are searching for.
     * @param searchQueryField The field where you specify a search query.
     */
    public void reloadMovies(TableView movieTable, ChoiceBox<String> searchTypeBox, TextField searchQueryField){
        movieTable.getItems().clear();
        loadMovies(movieTable, searchTypeBox, searchQueryField);
    }
    
    /**
     * Checks if the delete key was pressed, and if so removes the potentially selected filter from the list.
     * @param event The event that fired.
     * @param filterList The list containing all the filters.
     */
    public void filterListKeyPressed(KeyEvent event, ListView<Filter> filterList){
        if(event.getCode() == KeyCode.DELETE){
            int selectedIndex = filterList.getSelectionModel().getSelectedIndex();
            if(selectedIndex != -1)
                filterList.getItems().remove(selectedIndex);
        }
    }
    
    /**
     * Checks if the delete key was pressed, and if so removes the potentially selected movie from the table.
     * @param event The event that fired.
     * @param movieTable The table containing the movies.
     */
    public void movieTableKeyPressed(KeyEvent event, TableView<Movie> movieTable){
        if(event.getCode() == KeyCode.DELETE){
            
            
            int selectedIndex = movieTable.getSelectionModel().getSelectedIndex();
            if(selectedIndex != -1){
                Movie selectedMovie = movieTable.getItems().get(selectedIndex);
                
                if(movieBean.deleteMovie(selectedMovie))
                    movieTable.getItems().remove(selectedIndex);
            }
        }
    }
    
    public static void addFilter(Filter filter) {
        filters.add(filter);
    }
}

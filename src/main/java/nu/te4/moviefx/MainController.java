package nu.te4.moviefx;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import nu.te4.moviefx.beans.MainBean;
import nu.te4.moviefx.beans.UtilityBean;
import nu.te4.moviefx.entities.Filter;

/**
 * Controls the main window.
 * @author Adrian Klasson
 */
public class MainController {

    private final MainBean mainBean = new MainBean();
    private final UtilityBean utilityBean = new UtilityBean();
    
    @FXML
    private ChoiceBox<String> searchTypeBox;

    @FXML
    private TextField searchQueryField;

    @FXML
    private ListView<Filter> filterList;

    @FXML
    private Button addMovieButton;

    @FXML
    private TableView<?> movieTable;

    @FXML
    private TableColumn<?, ?> titleColumn;

    @FXML
    private TableColumn<?, ?> budgetColumn;

    @FXML
    private TableColumn<?, ?> revenueColumn;

    @FXML
    private TableColumn<?, ?> lengthColumn;

    @FXML
    private TableColumn<?, ?> gradeColumn;

    @FXML
    private TableColumn<?, ?> releaseDateColumn;

    @FXML
    private TableColumn<?, ?> directorColumn;

    @FXML
    private TableColumn<?, ?> genresColumn;

    @FXML
    void onAddFilter(MouseEvent event) {
        utilityBean.openWindow(getClass(), UtilityBean.Window.AddFilter);
    }

    @FXML
    void onAddMovie(MouseEvent event) {
        utilityBean.openWindow(getClass(), UtilityBean.Window.AddMovie);
    }
    
    @FXML
    void onFilterListKeyPressed(KeyEvent event) {
        mainBean.filterListKeyPressed(event, filterList);
    }

    @FXML
    void onReload(MouseEvent event) {
        mainBean.reloadMovies(movieTable, searchTypeBox, searchQueryField);
    }
    
    @FXML
    public void initialize(){
        mainBean.initializeSearchTypes(searchTypeBox);
        mainBean.initializeFilterList(filterList);
        mainBean.initializeColumns(titleColumn, budgetColumn, revenueColumn, lengthColumn, gradeColumn, releaseDateColumn, directorColumn, genresColumn);
        mainBean.loadMovies(movieTable, searchTypeBox, searchQueryField);
    }
}

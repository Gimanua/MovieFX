package nu.te4.moviefx;

import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import nu.te4.moviefx.beans.MainBean;
import nu.te4.moviefx.beans.UtilityBean;

/**
 * Controls the main window.
 * @author Adrian Klasson
 */
public class MainController {

    private final MainBean mainBean = new MainBean();
    private final UtilityBean utilityBean = new UtilityBean();
    
    @FXML
    private ComboBox<?> searchTypeBox;

    @FXML
    private TextField searchQueryField;

    @FXML
    private Button addFilterButton;

    @FXML
    private ListView<?> filterList;

    @FXML
    private Button addMovieButton;

    @FXML
    private TableView movieTable;

    @FXML
    private TableColumn titleColumn;

    @FXML
    private TableColumn budgetColumn;

    @FXML
    private TableColumn revenueColumn;

    @FXML
    private TableColumn lengthColumn;

    @FXML
    private TableColumn gradeColumn;

    @FXML
    private TableColumn releaseDateColumn;

    @FXML
    private TableColumn directorColumn;

    @FXML
    private TableColumn genresColumn;

    /**
     * Initializes this window. This method is called internally by JavaFX.
     */
    @FXML
    public void initialize(){
        
        Map<TableColumn, String> columns = new HashMap();
        columns.put(titleColumn, "title");
        columns.put(budgetColumn, "budget");
        columns.put(revenueColumn, "revenue");
        columns.put(lengthColumn, "length");
        columns.put(gradeColumn, "grade");
        columns.put(releaseDateColumn, "releaseDate");
        columns.put(directorColumn, "director");
        columns.put(genresColumn, "genres");
        
        mainBean.initializeColumns(columns);
        mainBean.loadMovies(movieTable);
    }
    
    
    @FXML
    void addFilterHandler(MouseEvent event) {
        utilityBean.openWindow(getClass(), UtilityBean.Window.AddFilter);
    }
    
    @FXML
    void addMovieHandler(MouseEvent event) {
        utilityBean.openWindow(getClass(), UtilityBean.Window.AddMovie);
    }

}

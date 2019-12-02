module nu.te4.moviefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens nu.te4.moviefx to javafx.fxml;
    exports nu.te4.moviefx;
}
module nu.te4.moviefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens nu.te4.moviefx to javafx.fxml;
    opens nu.te4.moviefx.entities to javafx.fxml;
    exports nu.te4.moviefx;
    exports nu.te4.moviefx.entities;
}
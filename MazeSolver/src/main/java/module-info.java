module org.game.mazesolver {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.game.mazesolver to javafx.fxml;
    exports org.game.mazesolver;
}
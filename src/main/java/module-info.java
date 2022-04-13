module jordle.main.jordlefinal {
    requires javafx.controls;
    requires javafx.fxml;


    opens jordle.main.jordlefinal to javafx.fxml;
    exports jordle.main.jordlefinal;
}
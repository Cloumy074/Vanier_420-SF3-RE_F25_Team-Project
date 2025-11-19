module vaniercollege {
    requires javafx.fxml;
    requires javafx.graphics;
    requires static lombok;


    opens vaniercollege to javafx.fxml;
    exports vaniercollege;
}
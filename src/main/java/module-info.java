module vaniercollege {
    requires javafx.fxml;
    requires javafx.controls;
    requires static lombok;


    opens vaniercollege to javafx.fxml;
    exports vaniercollege;
}
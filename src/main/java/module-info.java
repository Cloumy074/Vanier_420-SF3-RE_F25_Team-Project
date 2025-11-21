module vaniercollege.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    exports vaniercollege.view;
    opens vaniercollege.controller to javafx.fxml;
}
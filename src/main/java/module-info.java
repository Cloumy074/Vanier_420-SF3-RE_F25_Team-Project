module vaniercollege.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires javafx.swing;
    requires static lombok;


    exports vaniercollege.view;
    opens vaniercollege.controller to javafx.fxml;
}
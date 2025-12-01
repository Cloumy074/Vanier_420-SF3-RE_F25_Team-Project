module vaniercollege {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires javafx.swing;
    requires static lombok;
    requires static org.apiguardian.api;
    requires static org.junit.jupiter.api;

    exports vaniercollege.view;
    exports vaniercollege.controller;
    exports vaniercollege.model;
    exports vaniercollege.utils;
    opens vaniercollege.controller to javafx.fxml;
}

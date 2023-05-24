module bookmanager {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;
    opens balta.stuermer.adv.swe.controller to javafx.fxml;
    exports balta.stuermer.adv.swe;
    exports balta.stuermer.adv.swe.controller;
    opens balta.stuermer.adv.swe.models to com.google.gson;
    exports balta.stuermer.adv.swe.models;
}
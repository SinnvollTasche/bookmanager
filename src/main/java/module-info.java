module bookmanager {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;
    opens balta.stuermer.adv.swe to javafx.fxml;
    exports balta.stuermer.adv.swe;
}
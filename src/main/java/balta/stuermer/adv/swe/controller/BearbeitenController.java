package balta.stuermer.adv.swe.controller;

import balta.stuermer.adv.swe.models.BearbeitbarBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Map;

public class BearbeitenController {
    BearbeitbarBuilder zuBearbeitendesObjekt;
    @FXML
    public GridPane attributGrid;

    public void setZuBearbeitendesObjekt(final BearbeitbarBuilder zuBearbeitendesObjekt) {
        this.zuBearbeitendesObjekt = zuBearbeitendesObjekt;
        if (attributGrid == null) {
            this.attributGrid = new GridPane();
        }
        int i = 0;
        for (Map.Entry<String, Class<?>> entry : zuBearbeitendesObjekt.getAlleAttribute().entrySet()) {
            TextField eingabeFeld = new TextField();
            eingabeFeld.setOnKeyTyped((actionEvent -> zuBearbeitendesObjekt.setAttribut(entry.getKey(), ((TextField) actionEvent.getTarget()).getCharacters())));
            attributGrid.add(new Label(entry.getKey()), 0, i);
            attributGrid.add(eingabeFeld, 1, i);
            i++;
        }
    }

    @FXML
    private void beiHauptmenuButtonKlick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/hauptmenu.fxml"));
        Scene jetzigeSzene = ((Button)event.getSource()).getScene();
        try {
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOpacity(1);
            stage.setTitle("Hauptmenü");
            stage.setScene(new Scene(root, jetzigeSzene.getWidth(), jetzigeSzene.getHeight()));
            stage.show();
            ((Stage)(jetzigeSzene.getWindow())).close();
        } catch (IOException ex) {
            Dialog<String> dialog = new Dialog<>();
            Window window = dialog.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest(closeEvent -> window.hide());
            dialog.setTitle("Fehler beim Laden des Hauptmenüs");
            dialog.setContentText("Fehler in der hauptmenu.fxml datei\n" + ex.getMessage());
            dialog.show();
        }
    }

    @FXML
    private void beiSpeichereButtonKlick(ActionEvent event) {
        try {
            zuBearbeitendesObjekt.speichere();
            Dialog<String> dialog = new Dialog<>();
            Window window = dialog.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest(closeEvent -> window.hide());
            dialog.setTitle("Erfolgreich gespeichert");
            dialog.show();
        } catch (IOException | IllegalArgumentException ex) {
            Dialog<String> dialog = new Dialog<>();
            Window window = dialog.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest(closeEvent -> window.hide());
            dialog.setTitle("Fehler beim Speichern");
            dialog.setContentText(ex.getMessage());
            dialog.show();
        }
    }

    @FXML
    private void initialize() {

    }
}

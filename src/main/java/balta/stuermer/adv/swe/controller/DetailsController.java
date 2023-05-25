package balta.stuermer.adv.swe.controller;

import balta.stuermer.adv.swe.models.Anzeigbar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class DetailsController {
    Anzeigbar anzuzeigendesObjekt;
    @FXML
    public GridPane attributGrid;

    public void setAnzuzeigendesObjekt(final Anzeigbar anzuzeigendesObjekt) {
        this.anzuzeigendesObjekt = anzuzeigendesObjekt;
        if (attributGrid == null) {
            this.attributGrid = new GridPane();
        }
        int i = 0;
        for (Map.Entry<String, String> entry : anzuzeigendesObjekt.getAlleAttribute().entrySet()) {
            attributGrid.add(new Label(entry.getKey()), 0, i);
            attributGrid.add(new Label(entry.getValue()), 1, i);
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
            System.err.println("Fehler in der hauptmenu.fxml datei");
        }
    }
}

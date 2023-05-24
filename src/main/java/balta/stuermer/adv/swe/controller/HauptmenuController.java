package balta.stuermer.adv.swe.controller;

import balta.stuermer.adv.swe.datenhaltung.Autorspeicherung;
import balta.stuermer.adv.swe.datenhaltung.Buchspeicherung;
import balta.stuermer.adv.swe.datenhaltung.Verlagspeicherung;
import balta.stuermer.adv.swe.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HauptmenuController {
    private String modus = "autor";
    @FXML
    public ListView liste;

    @FXML
    private void beiBuchMenuAuswahl(ActionEvent event) {
        modus = "buch";
        ladeTabelle();
    }

    @FXML
    private void beiAutorMenuAuswahl(ActionEvent event) {
        modus = "autor";
        ladeTabelle();
    }

    @FXML
    private void beiVerlagMenuAuswahl(ActionEvent event) {
        modus = "verlag";
        ladeTabelle();
    }

    @FXML
    private void beiNeuAnlegen(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/bearbeiten.fxml"));
        Scene jetzigeSzene = ((Button)event.getSource()).getScene();
        try {
            Parent root = fxmlLoader.load();
            BearbeitenController bearbeitenController = fxmlLoader.getController();
            if (bearbeitenController == null) {
                bearbeitenController = new BearbeitenController();
                fxmlLoader.setController(bearbeitenController);
            }
            switch (modus) {
                case "buch":
                    bearbeitenController.setZuBearbeitendesObjekt(new BuchBuilder());
                    break;
                case "autor":
                    bearbeitenController.setZuBearbeitendesObjekt(new AutorBuilder());
                    break;
                case "verlag":
                    bearbeitenController.setZuBearbeitendesObjekt(new VerlagBuilder());
                    break;
            }
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOpacity(1);
            stage.setTitle("Bearbeiten");
            stage.setScene(new Scene(root, jetzigeSzene.getWidth(), jetzigeSzene.getHeight()));
            stage.show();
            ((Stage)(jetzigeSzene.getWindow())).close();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Fehler in der bearbeiten.fxml datei");
        }
    }

    private void ladeTabelle() {
        switch (modus) {
            case "buch": {
                ObservableList<Buch> bücher = FXCollections.observableList(Buchspeicherung.getInstanz().findeAlleBuecher());
                liste.setItems(bücher);
                break;
            }
            case "autor": {
                ObservableList<Autor> autoren = FXCollections.observableList(Autorspeicherung.getInstanz().findeAlleAutoren());
                liste.setItems(autoren);
                break;
            }
            case "verlag": {
                ObservableList<Verlag> verlage = FXCollections.observableList(Verlagspeicherung.getInstanz().findeAlleVerlage());
                liste.setItems(verlage);
                break;
            }
        }
    }

    @FXML
    private void initialize() {
        ladeTabelle();
    }
}

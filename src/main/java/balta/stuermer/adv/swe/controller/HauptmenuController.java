package balta.stuermer.adv.swe.controller;

import balta.stuermer.adv.swe.datenhaltung.Ausleihespeicherung;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HauptmenuController {
    private String modus = "buch";
    @FXML
    public ListView liste;
    @FXML
    public TextField suchfeld;

    @FXML
    private void beiBuchMenuAuswahl(ActionEvent event) {
        modus = "buch";
        suchfeld.setText("");
        ladeListe();
    }

    @FXML
    private void beiAutorMenuAuswahl(ActionEvent event) {
        modus = "autor";
        suchfeld.setText("");
        ladeListe();
    }

    @FXML
    private void beiVerlagMenuAuswahl(ActionEvent event) {
        modus = "verlag";
        suchfeld.setText("");
        ladeListe();
    }

    @FXML
    private void beiAusleiheMenuAuswahl(ActionEvent event) {
        modus = "ausleihe";
        suchfeld.setText("");
        ladeListe();
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
                case "ausleihe":
                    bearbeitenController.setZuBearbeitendesObjekt(new AusleiheBuilder());
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

    @FXML
    private void beiAuswahlAusListe(MouseEvent event) {
        Anzeigbar anzuzeigendesObjekt = (Anzeigbar) liste.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/details.fxml"));
        Scene jetzigeSzene = ((ListView<?>) event.getSource()).getScene();
        try {
            Parent root = fxmlLoader.load();
            DetailsController detailsController = fxmlLoader.getController();
            if (detailsController == null) {
                detailsController = new DetailsController();
                fxmlLoader.setController(detailsController);
            }
            detailsController.setAnzuzeigendesObjekt(anzuzeigendesObjekt);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOpacity(1);
            stage.setTitle("Details");
            stage.setScene(new Scene(root, jetzigeSzene.getWidth(), jetzigeSzene.getHeight()));
            stage.show();
            ((Stage)(jetzigeSzene.getWindow())).close();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Fehler in der details.fxml datei");
        }
    }

    private void ladeListe() {
        switch (modus) {
            case "buch": {
                ObservableList<Buch> buchListe;
                if (suchfeld.getText() != null && !suchfeld.getText().equals("")) {
                    buchListe = FXCollections.observableList(Buchspeicherung.getInstanz().findeBuch(suchfeld.getText()));
                } else {
                    buchListe = FXCollections.observableList(Buchspeicherung.getInstanz().findeAlleBuecher());
                }
                liste.setItems(buchListe);
                break;
            }
            case "autor": {
                ObservableList<Autor> autoren;
                if (suchfeld.getText() != null && !suchfeld.getText().equals("")) {
                    autoren = FXCollections.observableList(Autorspeicherung.getInstanz().findeAutor(suchfeld.getText()));
                } else {
                    autoren = FXCollections.observableList(Autorspeicherung.getInstanz().findeAlleAutoren());
                }
                liste.setItems(autoren);
                break;
            }
            case "verlag": {
                ObservableList<Verlag> verlage;
                if (suchfeld.getText() != null && !suchfeld.getText().equals("")) {
                    verlage = FXCollections.observableList(Verlagspeicherung.getInstanz().findeVerlagMitName(suchfeld.getText()));
                } else {
                    verlage = FXCollections.observableList(Verlagspeicherung.getInstanz().findeAlleVerlage());
                }
                liste.setItems(verlage);
                break;
            }
            case "ausleihe": {
                ObservableList<Ausleihe> ausleihen;
                if (suchfeld.getText() != null && !suchfeld.getText().equals("")) {
                    ausleihen = FXCollections.observableList(Ausleihespeicherung.getInstanz().findeAusleiheMitAusleihendem(suchfeld.getText()));
                } else {
                    ausleihen = FXCollections.observableList(Ausleihespeicherung.getInstanz().findeAlleAusleihen());
                }
                liste.setItems(ausleihen);
                break;
            }
        }
    }

    @FXML
    private void initialize() {
        ladeListe();
        suchfeld.setOnKeyTyped(event -> ladeListe());
    }
}

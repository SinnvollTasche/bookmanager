package balta.stuermer.adv.swe.controller;

import balta.stuermer.adv.swe.datenhaltung.*;
import balta.stuermer.adv.swe.models.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.List;

/**
 * Controller für die GUI, die es ermöglicht, Objekt anzuwählen
 * Einziger Zweck sich zu ändern: Hauptmenü-GUI ändert sich
 */
public class HauptmenuController {
    private Modus modus = Modus.BUCH;
    @FXML
    public ListView<Anzeigbar> liste;
    @FXML
    public TextField suchfeld;

    @FXML
    private void beiBuchMenuAuswahl(ActionEvent event) {
        modus = Modus.BUCH;
        suchfeld.setText("");
        ladeListe();
    }

    @FXML
    private void beiAutorMenuAuswahl(ActionEvent event) {
        modus = Modus.AUTOR;
        suchfeld.setText("");
        ladeListe();
    }

    @FXML
    private void beiVerlagMenuAuswahl(ActionEvent event) {
        modus = Modus.VERLAG;
        suchfeld.setText("");
        ladeListe();
    }

    @FXML
    private void beiAusleiheMenuAuswahl(ActionEvent event) {
        modus = Modus.AUSLEIHE;
        suchfeld.setText("");
        ladeListe();
    }

    @FXML
    private void beiNeuAnlegen(ActionEvent event) {
        Scene jetzigeSzene = ((Button)event.getSource()).getScene();
        UIOperationen.wechselZuBearbeiten(jetzigeSzene, modus.getBuilder());
    }

    @FXML
    private void beiAuswahlAusListe(MouseEvent event) {
        Anzeigbar anzuzeigendesObjekt = liste.getSelectionModel().getSelectedItem();
        Scene jetzigeSzene = ((ListView<?>) event.getSource()).getScene();
        UIOperationen.wechselZuDetails(jetzigeSzene, anzuzeigendesObjekt);
    }

    private void ladeListe() {
        if (suchfeld.getText() != null && !suchfeld.getText().equals("")) {
            liste.setItems(FXCollections.observableList((List<Anzeigbar>) modus.getSpeicherung().findeMitSuchbegriff(suchfeld.getText())));
            return;
        }
        liste.setItems(FXCollections.observableList((List<Anzeigbar>) modus.getSpeicherung().findeAlle()));
    }

    @FXML
    private void initialize() {
        ladeListe();
        suchfeld.setOnKeyTyped(event -> ladeListe());
    }

    /**
     * Enum, das den HauptmenuController unterstützt
     * Einziger Zweck sich zu ändern: neues Anzeigbar Objekt
     */
    private enum Modus {
        AUSLEIHE(new AusleiheBuilder(), Ausleihespeicherung.getInstanz()),
        AUTOR(new AutorBuilder(), Autorspeicherung.getInstanz()),
        BUCH(new BuchBuilder(), Buchspeicherung.getInstanz()),
        VERLAG(new VerlagBuilder(), Verlagspeicherung.getInstanz());

        private final BearbeitbarBuilder builder;
        private final Speicherung speicherung;

        Modus(BearbeitbarBuilder builder, Speicherung speicherung) {
            this.builder = builder;
            this.speicherung = speicherung;
        }

        public BearbeitbarBuilder getBuilder() {
            return builder;
        }

        public Speicherung getSpeicherung() {
            return speicherung;
        }
    }
}

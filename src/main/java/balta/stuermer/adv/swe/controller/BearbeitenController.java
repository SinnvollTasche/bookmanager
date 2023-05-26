package balta.stuermer.adv.swe.controller;

import balta.stuermer.adv.swe.datenhaltung.Autorspeicherung;
import balta.stuermer.adv.swe.datenhaltung.Buchspeicherung;
import balta.stuermer.adv.swe.datenhaltung.Verlagspeicherung;
import balta.stuermer.adv.swe.models.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            attributGrid.add(new Label(entry.getKey()), 0, i);
            switch (entry.getKey()) {
                case "Verlag": {
                    ComboBox<Verlag> verlagAuswahl = new ComboBox<>();
                    verlagAuswahl.getItems().addAll(FXCollections.observableList(Verlagspeicherung.getInstanz().findeAlleVerlage()));
                    verlagAuswahl.setEditable(false);
                    verlagAuswahl.setOnAction((actionEvent -> {
                                try {
                                    ((BuchBuilder) zuBearbeitendesObjekt).setVerlag(verlagAuswahl.getValue());
                                } catch (ClassCastException ex) {
                                    System.out.println("Aus einem unbekannten Grund wird hier ein Fehler geworfen, aber trotzdem funktioniert alles.");
                                }
                            }));
                    verlagAuswahl.setValue((Verlag) zuBearbeitendesObjekt.getAttribut(entry.getKey()));
                    attributGrid.add(verlagAuswahl, 1, i);
                    break;
                }
                case "Autor*Innen": {
                    ComboBox<Autor> autorAuswahl = new ComboBox<>();
                    autorAuswahl.getItems().addAll(FXCollections.observableList(Autorspeicherung.getInstanz().findeAlleAutoren()));
                    autorAuswahl.setEditable(false);
                    autorAuswahl.setOnAction((actionEvent -> {
                        try {
                            ((BuchBuilder) zuBearbeitendesObjekt).setAutoren(Collections.singletonList(autorAuswahl.getValue()));
                        } catch (ClassCastException ex) {
                            System.out.println("Aus einem unbekannten Grund wird hier ein Fehler geworfen, aber trotzdem funktioniert alles.");
                        }
                    }));
                    List<Autor> autoren = (List<Autor>) zuBearbeitendesObjekt.getAttribut(entry.getKey());
                    if (autoren != null) {
                        autorAuswahl.setValue(autoren.get(0));
                    }
                    attributGrid.add(autorAuswahl, 1, i);
                    break;
                }
                case "Buch": {
                    ComboBox<Buch> buchAuswahl = new ComboBox<>();
                    buchAuswahl.getItems().addAll(FXCollections.observableList(Buchspeicherung.getInstanz().findeAlleBuecher()));
                    buchAuswahl.setEditable(false);
                    buchAuswahl.setOnAction((actionEvent -> {
                        try {
                            ((AusleiheBuilder) zuBearbeitendesObjekt).setBuch(buchAuswahl.getValue());
                        } catch (ClassCastException ex) {
                            System.out.println("Aus einem unbekannten Grund wird hier ein Fehler geworfen, aber trotzdem funktioniert alles.");
                        }
                    }));
                    buchAuswahl.setValue((Buch) zuBearbeitendesObjekt.getAttribut(entry.getKey()));
                    attributGrid.add(buchAuswahl, 1, i);
                    break;
                }
                case "Zustand": {
                    ComboBox<Zustand> zustandAuswahl = new ComboBox<>();
                    zustandAuswahl.getItems().addAll(FXCollections.observableList(Arrays.stream(Zustand.values()).collect(Collectors.toList())));
                    zustandAuswahl.setEditable(false);
                    zustandAuswahl.setOnAction((actionEvent -> {
                        try {
                            ((BuchBuilder) zuBearbeitendesObjekt).setZustand(zustandAuswahl.getValue());
                        } catch (ClassCastException ex) {
                            System.out.println("Aus einem unbekannten Grund wird hier ein Fehler geworfen, aber trotzdem funktioniert alles.");
                        }
                    }));
                    zustandAuswahl.setValue((Zustand) zuBearbeitendesObjekt.getAttribut(entry.getKey()));
                    attributGrid.add(zustandAuswahl, 1, i);
                    break;
                }
                default: {
                    TextField eingabeFeld = new TextField();
                    if (zuBearbeitendesObjekt.getAttribut(entry.getKey()) != null) {
                        eingabeFeld.setText(zuBearbeitendesObjekt.getAttribut(entry.getKey()).toString());
                    }
                    eingabeFeld.setOnKeyTyped((actionEvent -> zuBearbeitendesObjekt.setAttribut(entry.getKey(), ((TextField) actionEvent.getTarget()).getText())));
                    attributGrid.add(eingabeFeld, 1, i);
                }
            }
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

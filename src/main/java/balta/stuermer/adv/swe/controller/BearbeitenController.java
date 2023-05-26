package balta.stuermer.adv.swe.controller;

import balta.stuermer.adv.swe.datenhaltung.Autorspeicherung;
import balta.stuermer.adv.swe.datenhaltung.Buchspeicherung;
import balta.stuermer.adv.swe.datenhaltung.Verlagspeicherung;
import balta.stuermer.adv.swe.models.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

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
                    ComboBox<Verlag> verlagAuswahl = UIOperationen.generiereComboBox(Verlagspeicherung.getInstanz().findeAlleVerlage(), (Verlag) zuBearbeitendesObjekt.getAttribut(entry.getKey()));
                    verlagAuswahl.setOnAction((actionEvent -> {
                        try {
                            ((BuchBuilder) zuBearbeitendesObjekt).setVerlag(verlagAuswahl.getValue());
                        } catch (ClassCastException ex) {
                            System.out.println("Aus einem unbekannten Grund wird hier ein Fehler geworfen, aber trotzdem funktioniert alles.");
                        }
                    }));
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
                    ComboBox<Buch> buchAuswahl = UIOperationen.generiereComboBox(Buchspeicherung.getInstanz().findeAlleBuecher(),
                            (Buch) zuBearbeitendesObjekt.getAttribut(entry.getKey()));
                    buchAuswahl.setOnAction((actionEvent -> {
                        try {
                            ((AusleiheBuilder) zuBearbeitendesObjekt).setBuch(buchAuswahl.getValue());
                        } catch (ClassCastException ex) {
                            System.out.println("Aus einem unbekannten Grund wird hier ein Fehler geworfen, aber trotzdem funktioniert alles.");
                        }
                    }));
                    attributGrid.add(buchAuswahl, 1, i);
                    break;
                }
                case "Zustand": {
                    ComboBox<Zustand> zustandAuswahl = UIOperationen.generiereComboBox(Arrays.stream(Zustand.values()).collect(Collectors.toList()),
                            (Zustand) zuBearbeitendesObjekt.getAttribut(entry.getKey()));
                    zustandAuswahl.setOnAction((actionEvent -> {
                        try {
                            ((BuchBuilder) zuBearbeitendesObjekt).setZustand(zustandAuswahl.getValue());
                        } catch (ClassCastException ex) {
                            System.out.println("Aus einem unbekannten Grund wird hier ein Fehler geworfen, aber trotzdem funktioniert alles.");
                        }
                    }));
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
        UIOperationen.wechselZuHauptMenu(((Button)event.getSource()).getScene());
    }

    @FXML
    private void beiSpeichereButtonKlick(ActionEvent event) {
        try {
            zuBearbeitendesObjekt.speichere();
            UIOperationen.zeigeDialog("Erfolgreich gespeichert", "");
        } catch (IOException | IllegalArgumentException ex) {
            UIOperationen.zeigeDialog("Fehler beim Speichern", ex.getMessage());
        }
    }

    @FXML
    private void initialize() {

    }
}

package balta.stuermer.adv.swe.controller;

import balta.stuermer.adv.swe.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
    private void beiBearbeitenButtonKlick(ActionEvent event) {
        Scene jetzigeSzene = ((Button)event.getSource()).getScene();
        switch (anzuzeigendesObjekt.getClass().getSimpleName()) {
            case "Buch":
                UIOperationen.wechselZuBearbeiten(jetzigeSzene, new BuchBuilder((Buch) anzuzeigendesObjekt));
                break;
            case "Autor":
                UIOperationen.wechselZuBearbeiten(jetzigeSzene, new AutorBuilder((Autor) anzuzeigendesObjekt));
                break;
            case "Verlag":
                UIOperationen.wechselZuBearbeiten(jetzigeSzene, new VerlagBuilder((Verlag) anzuzeigendesObjekt));
                break;
            case "Ausleihe":
                UIOperationen.wechselZuBearbeiten(jetzigeSzene, new AusleiheBuilder((Ausleihe) anzuzeigendesObjekt));
        }
    }

    @FXML
    private void beiHauptmenuButtonKlick(ActionEvent event) {
        UIOperationen.wechselZuHauptMenu(((Button)event.getSource()).getScene());
    }
}

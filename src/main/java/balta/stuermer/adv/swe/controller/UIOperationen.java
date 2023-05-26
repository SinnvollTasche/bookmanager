package balta.stuermer.adv.swe.controller;

import balta.stuermer.adv.swe.models.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;

/**
 * Klasse, die häufige Operationen für die GUI bereithält
 * Einziger Zweck sich zu ändern: GUI ändert sich
 */
public class UIOperationen {
    private UIOperationen() {}

    /**
     * Zeigt ein Pop-up-Fenster
     * @param titel Titel des Popups
     * @param content Inhalt des Popups
     */
    public static void zeigeDialog(String titel, String content) {
        Dialog<String> dialog = new Dialog<>();
        Window window = dialog.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(closeEvent -> window.hide());
        dialog.setTitle(titel);
        dialog.setContentText(content);
        dialog.show();
    }

    /**
     * Schließt das alte Fenster und öffnet das Hauptmenü
     * @param jetzigeSzene wird benötigt um die Größe des vorherigen Fensters zu übernehmen
     */
    public static void wechselZuHauptMenu(Scene jetzigeSzene) {
        FXMLLoader fxmlLoader = new FXMLLoader(UIOperationen.class.getResource("/hauptmenu.fxml"));
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
            UIOperationen.zeigeDialog("Fehler beim Laden des Hauptmenüs", ex.getMessage());
        }
    }

    /**
     * Schließt das alte Fenster und öffnet das Bearbeiten-Fenster
     * @param jetzigeSzene wird benötigt um die Größe des vorherigen Fensters zu übernehmen
     * @param builder BearbeitbarBuilder, für das Objekt, das bearbeitet werden soll
     */
    public static void wechselZuBearbeiten(Scene jetzigeSzene, BearbeitbarBuilder builder) {
        FXMLLoader fxmlLoader = new FXMLLoader(UIOperationen.class.getResource("/bearbeiten.fxml"));
        try {
            Parent root = fxmlLoader.load();
            BearbeitenController bearbeitenController = fxmlLoader.getController();
            if (bearbeitenController == null) {
                bearbeitenController = new BearbeitenController();
                fxmlLoader.setController(bearbeitenController);
            }
            bearbeitenController.setZuBearbeitendesObjekt(builder);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOpacity(1);
            stage.setTitle("Bearbeiten");
            stage.setScene(new Scene(root, jetzigeSzene.getWidth(), jetzigeSzene.getHeight()));
            stage.show();
            ((Stage)(jetzigeSzene.getWindow())).close();
        } catch (IOException ex) {
            UIOperationen.zeigeDialog("Fehler beim Laden des Bearbeitenfensters", ex.getMessage());
        }
    }

    /**
     * Schließt das alte Fenster und öffnet die Detailansicht
     * @param jetzigeSzene wird benötigt um die Größe des vorherigen Fensters zu übernehmen
     * @param anzuzeigendesObjekt Objekt, dessen Details angezeigt werden sollen
     */
    public static void wechselZuDetails(Scene jetzigeSzene, Anzeigbar anzuzeigendesObjekt) {
        FXMLLoader fxmlLoader = new FXMLLoader(UIOperationen.class.getResource("/details.fxml"));try {
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
            UIOperationen.zeigeDialog("Fehler beim Laden des Detailfensters", ex.getMessage());
        }
    }

    /**
     * generiert eine ComboBox, die die Auswahl aus allen Objekten der übergeben Liste ermöglicht
     * Was bei der Auswahl passiert kann der ComboBox über die onAction Methode übergeben werden
     * @param auswahl Objekte, aus denen ausgewählt werden soll
     * @param aktivesObjekt Vorausgewähltes Objekt (kann null sein um kein vorausgewähltes zu haben)
     * @param <A> einheitliche Klasse, aus der ausgewählt wird
     * @return ComboBox
     */
    public static <A> ComboBox<A> generiereComboBox(List<A> auswahl, A aktivesObjekt) {
        ComboBox<A> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(FXCollections.observableList(auswahl));
        comboBox.setEditable(false);
        comboBox.setValue(aktivesObjekt);
        return comboBox;
    }
}

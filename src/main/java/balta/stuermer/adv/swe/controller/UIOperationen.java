package balta.stuermer.adv.swe.controller;

import balta.stuermer.adv.swe.models.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class UIOperationen {
    private UIOperationen() {}

    public static void zeigeDialog(String titel, String content) {
        Dialog<String> dialog = new Dialog<>();
        Window window = dialog.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(closeEvent -> window.hide());
        dialog.setTitle(titel);
        dialog.setContentText(content);
        dialog.show();
    }

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
}

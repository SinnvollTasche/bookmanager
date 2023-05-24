package balta.stuermer.adv.swe;

import balta.stuermer.adv.swe.datenhaltung.Autorspeicherung;
import balta.stuermer.adv.swe.datenhaltung.Buchspeicherung;
import balta.stuermer.adv.swe.datenhaltung.Exemplarspeicherung;
import balta.stuermer.adv.swe.datenhaltung.Verlagspeicherung;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/hauptmenu.fxml"));
        primaryStage.setTitle("Buchmanager");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    private static void findeSpeicherort(String[] args) {
        // standard Speicherort
        File saveFilesDirectory = Paths.get(System.getProperty("user.home"), "/BaltaStuermerSaveFiles/").toFile();
        // überprüfe, ob über ein Argument ein extra Speicherort gesetzt wurde
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                saveFilesDirectory = new File(args[++i]);
            }
        }

        // sorge dafür, dass alle nötigen Ordner zum Speichern existieren
        if (!saveFilesDirectory.exists()) {
            saveFilesDirectory.mkdirs();
        }

        File speicherortAutoren = Paths.get(saveFilesDirectory.getAbsolutePath(), "/Autoren/").toFile();
        File speicherortBuecher = Paths.get(saveFilesDirectory.getAbsolutePath(), "/Buecher/").toFile();
        File speicherortExemplare = Paths.get(saveFilesDirectory.getAbsolutePath(), "/Exemplare/").toFile();
        File speicherortVerlage = Paths.get(saveFilesDirectory.getAbsolutePath(), "/Verlage/").toFile();

        speicherortAutoren.mkdir();
        speicherortBuecher.mkdir();
        speicherortExemplare.mkdir();
        speicherortVerlage.mkdir();

        Autorspeicherung.getInstanz().setSpeicherort(speicherortAutoren);
        Buchspeicherung.getInstanz().setSpeicherort(speicherortBuecher);
        Exemplarspeicherung.getInstanz().setSpeicherort(speicherortExemplare);
        Verlagspeicherung.getInstanz().setSpeicherort(speicherortVerlage);
    }


    public static void main(String[] args) {
        findeSpeicherort(args);
        launch(args);
    }
}

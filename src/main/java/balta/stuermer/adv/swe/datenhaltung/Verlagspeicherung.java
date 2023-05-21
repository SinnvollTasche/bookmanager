package balta.stuermer.adv.swe.datenhaltung;

import balta.stuermer.adv.swe.models.Verlag;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Verlagspeicherung {
    private final File speicherort;

    public Verlagspeicherung(File speicherort) {
        this.speicherort = speicherort;
    }

    public List<Verlag> findeAlleVerlage() {
        return new ArrayList<>();
    }

    public void speichereNeuenVerlag(Verlag verlag) throws IOException {
        File saveFile = Paths.get(speicherort.getAbsolutePath(), verlag.getId() + ".json").toFile();
        if (!saveFile.createNewFile()) {
            throw new IllegalArgumentException("Verlag existiert bereits!!!");
        }
        schreibeVerlagInDatei(verlag, saveFile);
    }

    public List<Verlag> findeVerlagMitName(String name) {
        List<Verlag> verlage = this.findeAlleVerlage();
        verlage = verlage.stream().filter(a -> a.getName().matches(name)).collect(Collectors.toList());
        return verlage;
    }

    public Verlag findeVerlagMitId(String id) {
        File saveFile = Paths.get(speicherort.getAbsolutePath(), id + ".json").toFile();
        return leseVerlagAusDatei(saveFile);
    }

    public void aktualisiereVerlag(String id, Verlag aktualisierterVerlag) {
        if (!id.equals(aktualisierterVerlag.getId())) {
            throw new IllegalArgumentException("Id darf nicht geändert werden.");
        }
        File saveFile = Paths.get(speicherort.getAbsolutePath(), id + ".json").toFile();
        if (!saveFile.exists()) {
            throw new IllegalArgumentException("Zu ändernder Verlag existiert nicht.");
        }
        schreibeVerlagInDatei(aktualisierterVerlag, saveFile);
    }

    public boolean loescheVerlag(String id) {
        File saveFile = Paths.get(speicherort.getAbsolutePath(), id + ".json").toFile();
        return saveFile.delete();
    }

    private Verlag leseVerlagAusDatei(File f) throws IllegalArgumentException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            while (!br.ready()) {
                stringBuilder.append(br.readLine());
            }
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Speicherort konnte nicht gefunden werden.");
        } catch (IOException ex) {
            throw new IllegalArgumentException("Fehler beim Lesen der Daten des Verlags.");
        }
        return new Gson().fromJson(stringBuilder.toString(), Verlag.class);
    }

    private void schreibeVerlagInDatei(Verlag verlag, File f) {
        String jsonVerlag = new Gson().toJson(verlag);
        try (FileWriter fw = new FileWriter(f)) {
            fw.write(jsonVerlag);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Fehler beim Schreiben der Speicherdaten des Verlags");
        }
    }
}

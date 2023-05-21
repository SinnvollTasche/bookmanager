package balta.stuermer.adv.swe.datenhaltung;

import balta.stuermer.adv.swe.models.Buch;
import balta.stuermer.adv.swe.models.Exemplar;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Exemplarspeicherung {
    private final File speicherort;

    public Exemplarspeicherung(File speicherort) {
        this.speicherort = speicherort;
    }

    public List<Exemplar> findeAlleExemplare(Buch b) {
        File[] files = speicherort.listFiles(pathname -> pathname.getAbsolutePath().endsWith(".json"));
        List<Exemplar> exemplare = new ArrayList<>();
        assert files != null;
        for (File f : files) {
            exemplare.add(leseExemplarAusDatei(f));
        }
        return exemplare.stream().filter(e -> e.getBuch().equals(b)).collect(Collectors.toList());
    }

    public void speichereNeuesExemplar(Exemplar exemplar) throws IOException {
        File saveFile = Paths.get(speicherort.getAbsolutePath(), exemplar.getId() + ".json").toFile();
        if (!saveFile.createNewFile()) {
            throw new IllegalArgumentException("Exemplar existiert bereits!!!");
        }
        schreibeExemplarInDatei(exemplar, saveFile);
    }

    public Exemplar findeExemplarMitID(String id) {
        File saveFile = Paths.get(speicherort.getAbsolutePath(), id + ".json").toFile();
        return leseExemplarAusDatei(saveFile);
    }

    public void aktualisiereExemplar(String id, Exemplar aktualisiertesExemplar) {
        if (!id.equals(aktualisiertesExemplar.getId())) {
            throw new IllegalArgumentException("Id darf nicht geändert werden.");
        }
        File saveFile = Paths.get(speicherort.getAbsolutePath(), id + ".json").toFile();
        if (!saveFile.exists()) {
            throw new IllegalArgumentException("Zu änderndes Exemplar existiert nicht.");
        }
        schreibeExemplarInDatei(aktualisiertesExemplar, saveFile);
    }

    public boolean loescheExemplar(String id) {
        File saveFile = Paths.get(speicherort.getAbsolutePath(), id + ".json").toFile();
        return saveFile.delete();
    }

    private Exemplar leseExemplarAusDatei(File f) throws IllegalArgumentException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {

            while (!br.ready()) {
                stringBuilder.append(br.readLine());
            }
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Speicherort konnte nicht gefunden werden.");
        } catch (IOException ex) {
            throw new IllegalArgumentException("Fehler beim Lesen der Daten des Exemplars.");
        }
        return new Gson().fromJson(stringBuilder.toString(), Exemplar.class);
    }

    private void schreibeExemplarInDatei(Exemplar exemplar, File f) {
        String jsonExemplar = new Gson().toJson(exemplar);
        try (FileWriter fw = new FileWriter(f)) {
            fw.write(jsonExemplar);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Fehler beim Schreiben der Speicherdaten des Exemplars");
        }
    }
}

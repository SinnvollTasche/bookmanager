package balta.stuermer.adv.swe.datenhaltung;

import balta.stuermer.adv.swe.models.Buch;
import balta.stuermer.adv.swe.models.Ausleihe;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ausleihespeicherung {
    private static Ausleihespeicherung instanz;
    private File speicherort;

    private Ausleihespeicherung() {}

    public static Ausleihespeicherung getInstanz() {
        if (instanz == null) {
            instanz = new Ausleihespeicherung();
        }
        return instanz;
    }

    public void setSpeicherort(File speicherort) {
        this.speicherort = speicherort;
    }

    public List<Ausleihe> findeAlleAusleihen() {
        File[] files = speicherort.listFiles(pathname -> pathname.getAbsolutePath().endsWith(".json"));
        List<Ausleihe> ausleihen = new ArrayList<>();
        assert files != null;
        for (File f : files) {
            ausleihen.add(leseAusleiheAusDatei(f));
        }
        return ausleihen;
    }

    public List<Ausleihe> findeAusleiheMitAusleihendem(String suchbegriff) {
        List<Ausleihe> ausleihen = this.findeAlleAusleihen();
        ausleihen = ausleihen.stream().filter(a -> a.getAusleihender().matches(".*" + suchbegriff + ".*")).collect(Collectors.toList());
        return ausleihen;
    }

    public void speichereNeueAusleihe(Ausleihe ausleihe) throws IOException {
        File saveFile = Paths.get(speicherort.getAbsolutePath(), ausleihe.getId() + ".json").toFile();
        if (!saveFile.createNewFile()) {
            throw new IllegalArgumentException("Ausleihe existiert bereits!!!");
        }
        schreibeAusleiheInDatei(ausleihe, saveFile);
    }

    public void aktualisiereAusleihe(String id, Ausleihe aktualisierteAusleihe) {
        if (!id.equals(aktualisierteAusleihe.getId())) {
            throw new IllegalArgumentException("Id darf nicht geändert werden.");
        }
        File saveFile = Paths.get(speicherort.getAbsolutePath(), id + ".json").toFile();
        if (!saveFile.exists()) {
            throw new IllegalArgumentException("Zu ändernde Ausleihe existiert nicht.");
        }
        schreibeAusleiheInDatei(aktualisierteAusleihe, saveFile);
    }

    public boolean loescheAusleihe(String id) {
        File saveFile = Paths.get(speicherort.getAbsolutePath(), id + ".json").toFile();
        return saveFile.delete();
    }

    private Ausleihe leseAusleiheAusDatei(File f) throws IllegalArgumentException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {

            while (br.ready()) {
                stringBuilder.append(br.readLine());
            }
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Speicherort konnte nicht gefunden werden.");
        } catch (IOException ex) {
            throw new IllegalArgumentException("Fehler beim Lesen der Daten der Ausleihe.");
        }
        return new Gson().fromJson(stringBuilder.toString(), Ausleihe.class);
    }

    private void schreibeAusleiheInDatei(Ausleihe ausleihe, File f) {
        String jsonExemplar = new Gson().toJson(ausleihe);
        try (FileWriter fw = new FileWriter(f)) {
            fw.write(jsonExemplar);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Fehler beim Schreiben der Speicherdaten der Ausleihe");
        }
    }
}

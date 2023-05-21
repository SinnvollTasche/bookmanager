package balta.stuermer.adv.swe.datenhaltung;

import balta.stuermer.adv.swe.models.Autor;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class Autorspeicherung {
    private final File speicherort;

    public Autorspeicherung(File speicherort) {
        this.speicherort = speicherort;
    }

    public List<Autor> findeAlleAutoren() {
        File[] files = speicherort.listFiles(pathname -> pathname.getAbsolutePath().endsWith(".json"));
        List<Autor> autoren = new ArrayList<>();
        assert files != null;
        for (File f : files) {
            autoren.add(leseAutorAusDatei(f));
        }
        return autoren;
    }

    public void speichereNeuenAutor(Autor autor) throws IOException {
        File saveFile = Paths.get(speicherort.getAbsolutePath(), autor.getId() + ".json").toFile();
        if (!saveFile.createNewFile()) {
            throw new IllegalArgumentException("Autor existiert bereits!!!");
        }
        schreibeAutorInDatei(autor, saveFile);
    }

    public List<Autor> findeAutor(String name) {
        List<Autor> autoren = this.findeAlleAutoren();
        autoren = autoren.stream().filter(a -> a.getName().matches(name)).collect(Collectors.toList());
        return autoren;
    }

    public Autor findeAutorMitId(String id) {
        File saveFile = Paths.get(speicherort.getAbsolutePath(), id + ".json").toFile();
        return leseAutorAusDatei(saveFile);
    }

    public void aktualisiereAutor(String id, Autor aktualisierterAutor) {
        if (!id.equals(aktualisierterAutor.getId())) {
            throw new IllegalArgumentException("Id darf nicht geändert werden.");
        }
        File saveFile = Paths.get(speicherort.getAbsolutePath(), id + ".json").toFile();
        if (!saveFile.exists()) {
            throw new IllegalArgumentException("Zu ändernder Autor existiert nicht.");
        }
        schreibeAutorInDatei(aktualisierterAutor, saveFile);
    }

    public boolean loescheAutor(String id) {
        File saveFile = Paths.get(speicherort.getAbsolutePath(), id + ".json").toFile();
        return saveFile.delete();
    }

    private Autor leseAutorAusDatei(File f) throws IllegalArgumentException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {

            while (!br.ready()) {
                stringBuilder.append(br.readLine());
            }
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Speicherort konnte nicht gefunden werden.");
        } catch (IOException ex) {
            throw new IllegalArgumentException("Fehler beim Lesen der Daten des Autors.");
        }
        return new Gson().fromJson(stringBuilder.toString(), Autor.class);
    }

    private void schreibeAutorInDatei(Autor autor, File f) {
        String jsonAutor = new Gson().toJson(autor);
        try (FileWriter fw = new FileWriter(f)) {
            fw.write(jsonAutor);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Fehler beim Schreiben der Speicherdaten des Autors");
        }
    }
}

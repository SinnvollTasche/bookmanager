package balta.stuermer.adv.swe.datenhaltung;

import balta.stuermer.adv.swe.models.Verlag;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Verlagspeicherung implements Speicherung {
    private static Verlagspeicherung instanz;
    private File speicherort;

    private Verlagspeicherung() {}

    public static Verlagspeicherung getInstanz() {
        if (instanz == null) {
            instanz = new Verlagspeicherung();
        }
        return instanz;
    }

    public void setSpeicherort(File speicherort) {
        this.speicherort = speicherort;
    }

    @Override
    public List<Verlag> findeAlle() {
        File[] files = speicherort.listFiles(pathname -> pathname.getAbsolutePath().endsWith(".json"));
        List<Verlag> verlage = new ArrayList<>();
        assert files != null;
        for (File f : files) {
            verlage.add(leseVerlagAusDatei(f));
        }
        return verlage;
    }

    public void speichereNeuenVerlag(Verlag verlag) throws IOException {
        File saveFile = Paths.get(speicherort.getAbsolutePath(), verlag.getId() + ".json").toFile();
        if (!saveFile.createNewFile()) {
            throw new IllegalArgumentException("Verlag existiert bereits!!!");
        }
        schreibeVerlagInDatei(verlag, saveFile);
    }

    @Override
    public List<Verlag> findeMitSuchbegriff(String name) {
        List<Verlag> verlage = this.findeAlle();
        verlage = verlage.stream().filter(a -> a.getName().matches(".*" + name + ".*")).collect(Collectors.toList());
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
            while (br.ready()) {
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

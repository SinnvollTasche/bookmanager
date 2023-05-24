package balta.stuermer.adv.swe.datenhaltung;

import balta.stuermer.adv.swe.models.Buch;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Buchspeicherung {
    private static Buchspeicherung instanz;
    private File speicherort;

    private Buchspeicherung() {}

    public static Buchspeicherung getInstanz() {
        if (instanz == null) {
            instanz = new Buchspeicherung();
        }
        return instanz;
    }

    public void setSpeicherort(File speicherort) {
        this.speicherort = speicherort;
    }

    public List<Buch> findeAlleBuecher() {
        File[] files = speicherort.listFiles(pathname -> pathname.getAbsolutePath().endsWith(".json"));
        List<Buch> buecher = new ArrayList<>();
        assert files != null;
        for (File f : files) {
            buecher.add(leseBuchAusDatei(f));
        }
        return buecher;
    }

    public void speichereNeuesBuch(Buch buch) throws IOException {
        File saveFile = Paths.get(speicherort.getAbsolutePath(), buch.getId() + ".json").toFile();
        if (!saveFile.createNewFile()) {
            throw new IllegalArgumentException("Buch existiert bereits!!!");
        }
        schreibeBuchInDatei(buch, saveFile);
    }

    public Buch findeBuchMitId(String id) {
        File saveFile = Paths.get(speicherort.getAbsolutePath(), id + ".json").toFile();
        return leseBuchAusDatei(saveFile);
    }

    public List<Buch> findeBuch(String title) {
        List<Buch> buecher = this.findeAlleBuecher();
        buecher = buecher.stream().filter(a -> a.getTitel().matches(title)).collect(Collectors.toList());
        return buecher;
    }

    public void aktualisiereBuch(String id, Buch aktualisiertesBuch) {
        if (!id.equals(aktualisiertesBuch.getId())) {
            throw new IllegalArgumentException("Id darf nicht geändert werden.");
        }
        File saveFile = Paths.get(speicherort.getAbsolutePath(), id + ".json").toFile();
        if (!saveFile.exists()) {
            throw new IllegalArgumentException("Zu änderndes Buch existiert nicht.");
        }
        schreibeBuchInDatei(aktualisiertesBuch, saveFile);
    }

    public boolean loescheBuch(String id) {
        File saveFile = Paths.get(speicherort.getAbsolutePath(), id + ".json").toFile();
        return saveFile.delete();
    }

    private Buch leseBuchAusDatei(File f) throws IllegalArgumentException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {

            while (br.ready()) {
                stringBuilder.append(br.readLine());
            }
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Speicherort konnte nicht gefunden werden.");
        } catch (IOException ex) {
            throw new IllegalArgumentException("Fehler beim Lesen der Daten des Buchs.");
        }
        return new Gson().fromJson(stringBuilder.toString(), Buch.class);
    }

    private void schreibeBuchInDatei(Buch buch, File f) {
        String jsonBuch = new Gson().toJson(buch);
        try (FileWriter fw = new FileWriter(f)) {
            fw.write(jsonBuch);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Fehler beim Schreiben der Speicherdaten des Buchs");
        }
    }
}

package balta.stuermer.adv.swe.datenhaltung;

import balta.stuermer.adv.swe.models.Autor;
import balta.stuermer.adv.swe.models.Buch;
import balta.stuermer.adv.swe.models.Verlag;
import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Alle Funktionen, die mit der Datenhaltung von Büchern zu tun haben
 * Einziger Zweck sich zu ändern: Speicherung von Büchern ändert sich
 * Clean Architecture Level: Adapter/Plugin
 */
public class Buchspeicherung implements Speicherung {
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

    @Override
    public List<Buch> findeAlle() {
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

    @Override
    public List<Buch> findeMitSuchbegriff(String title) {
        List<Buch> buecher = this.findeAlle();
        buecher = buecher.stream().filter(a -> a.getTitel().matches(".*" + title + ".*")).collect(Collectors.toList());
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
        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonDeserializer<Autor> autorJsonDeserializer = new JsonDeserializer<Autor>() {
            @Override
            public Autor deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

                JsonObject jsonObject = jsonElement.getAsJsonObject();
                return Autorspeicherung.getInstanz().findeAutorMitId(jsonObject.get("id").getAsString());
            }
        };
        gsonBuilder.registerTypeAdapter(Autor.class, autorJsonDeserializer);
        JsonDeserializer<Verlag> verlagJsonDeserializer = new JsonDeserializer<Verlag>() {
            @Override
            public Verlag deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                return Verlagspeicherung.getInstanz().findeVerlagMitId(jsonObject.get("id").getAsString());
            }
        };
        gsonBuilder.registerTypeAdapter(Verlag.class, verlagJsonDeserializer);
        return gsonBuilder.create().fromJson(stringBuilder.toString(), Buch.class);
    }

    private void schreibeBuchInDatei(Buch buch, File f) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonSerializer<Autor> autorJsonSerializer = new JsonSerializer<Autor>() {
            @Override
            public JsonElement serialize(Autor autor, Type type, JsonSerializationContext jsonSerializationContext) {
                JsonObject jsonAutor = new JsonObject();
                jsonAutor.addProperty("id", autor.getId());
                return jsonAutor;
            }
        };
        gsonBuilder.registerTypeAdapter(Autor.class, autorJsonSerializer);
        JsonSerializer<Verlag> verlagJsonSerializer = new JsonSerializer<Verlag>() {
            @Override
            public JsonElement serialize(Verlag verlag, Type type, JsonSerializationContext jsonSerializationContext) {
                JsonObject jsonVerlag = new JsonObject();
                jsonVerlag.addProperty("id", verlag.getId());
                return jsonVerlag;
            }
        };
        gsonBuilder.registerTypeAdapter(Verlag.class, verlagJsonSerializer);
        String jsonBuch = gsonBuilder.create().toJson(buch);
        try (FileWriter fw = new FileWriter(f)) {
            fw.write(jsonBuch);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Fehler beim Schreiben der Speicherdaten des Buchs");
        }
    }
}

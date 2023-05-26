package balta.stuermer.adv.swe.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Objekt zur Repräsentation von Büchern
 * Einziger Zweck sich zu ändern: Buch ändert sich
 */
public class Buch implements Anzeigbar {
    private String id;
    private String titel;
    private String untertitel;
    private List<Autor> autoren;
    private Verlag verlag;
    private int seiten;
    private Zustand zustand;

    /**
     * Konstruktor.
     * @param id Einzigartige Id
     * @param titel Titel des Buches
     * @param untertitel Untertitel des Buches
     * @param autoren Liste von Autoren des Buches
     * @param verlag Verlag
     * @param seiten Seitenanzahl
     * @param zustand Zustandsklasse
     */
    public Buch(String id, String titel, String untertitel, List<Autor> autoren, Verlag verlag, int seiten, Zustand zustand) {
        this.id = id;
        this.titel = titel;
        this.untertitel = untertitel;
        this.autoren = autoren;
        this.verlag = verlag;
        this.seiten = seiten;
        this.zustand = zustand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getUntertitel() {
        return untertitel;
    }

    public void setUntertitel(String untertitel) {
        this.untertitel = untertitel;
    }

    public List<Autor> getAutoren() {
        return autoren;
    }

    public void setAutoren(List<Autor> autoren) {
        this.autoren = autoren;
    }

    public Verlag getVerlag() {
        return verlag;
    }

    public void setVerlag(Verlag verlag) {
        this.verlag = verlag;
    }

    public int getSeiten() {
        return seiten;
    }

    public void setSeiten(int seiten) {
        this.seiten = seiten;
    }

    public Zustand getZustand() {
        return zustand;
    }

    public void setZustand(Zustand zustand) {
        this.zustand = zustand;
    }

    @Override
    public String toString() {
        StringBuilder beschreibung = new StringBuilder();
        beschreibung.append(titel);
        if (autoren == null || autoren.size() == 0) {
            return beschreibung.toString();
        }
        beschreibung.append(" von ").append(autoren.get(0).getName());
        if (autoren.size() > 1) {
            beschreibung.append(" und Co.");
        }
        return beschreibung.toString();
    }

    @Override
    public Map<String, String> getAlleAttribute() {
        Map<String, String> attribute = new HashMap<>();
        attribute.put("Titel", this.titel);
        attribute.put("Untertitel", this.untertitel);
        StringBuilder autorenString = new StringBuilder();
        if (autoren != null) {
            autoren.forEach(autor -> autorenString.append(autor.toString()).append("\n"));
        }
        attribute.put("Autor*Innen", autorenString.toString());
        attribute.put("Seiten", this.seiten + "");
        attribute.put("Verlag", "");
        if (verlag != null) {
            attribute.put("Verlag", this.verlag.toString());
        }
        attribute.put("Zustand", "");
        if (zustand != null) {
            attribute.put("Zustand", zustand.getBeschreibung());
        }
        return attribute;
    }
}

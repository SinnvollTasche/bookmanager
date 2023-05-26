package balta.stuermer.adv.swe.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Objekt zur Repräsentation von Ausleihen
 * Einziger Zweck sich zu ändern: Ausleihe ändert sich
 */
public class Ausleihe implements Anzeigbar {
    private String id;
    private String ausleihender;
    private Buch buch;

    /**
     * Konstruktor.
     * @param id Einzigartige Id
     * @param ausleihender Notiz zur ausleihenden Person
     * @param buch ausgeliehenes Buch
     */
    public Ausleihe(String id, String ausleihender, Buch buch) {
        this.id = id;
        this.ausleihender = ausleihender;
        this.buch = buch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAusleihender() {
        return ausleihender;
    }

    public void setAusleihender(String ausleihender) {
        this.ausleihender = ausleihender;
    }

    public Buch getBuch() {
        return buch;
    }

    public void setBuch(Buch buch) {
        this.buch = buch;
    }

    @Override
    public Map<String, String> getAlleAttribute() {
        Map<String, String> attribute = new HashMap<>();
        attribute.put("Ausleihender", ausleihender);
        attribute.put("Buch", buch.toString());
        return attribute;
    }

    @Override
    public String toString() {
        return buch.toString() + " ausgeliehen von " + ausleihender;
    }
}

package balta.stuermer.adv.swe.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Objekt zur Repräsentation von Verlagen
 * Einziger Zweck sich zu ändern: Verlag ändert sich
 */
public class Verlag implements Anzeigbar {
    private String id;
    private String name;

    public Verlag(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Map<String, String> getAlleAttribute() {
        Map<String, String> attribute = new HashMap<>();
        attribute.put("Name", this.name);
        return attribute;
    }
}

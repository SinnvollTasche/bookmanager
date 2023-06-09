package balta.stuermer.adv.swe.models;

import balta.stuermer.adv.swe.datenhaltung.Autorspeicherung;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Builder für Autoren
 * Einziger Zweck sich zu ändern: Autor ändert sich
 * Entwurfsmuster: Builder
 */
public class AutorBuilder implements BearbeitbarBuilder {
    private String id;
    private String name;

    public AutorBuilder() {

    }

    /**
     * Konstruktor.
     * @param autor alle Werte dieses Autors werden als Default verwendet
     */
    public AutorBuilder(Autor autor){
        setId(autor.getId());
        setName(autor.getName());
    }

    public AutorBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public AutorBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Autor createAutor() {
        return new Autor(id, name);
    }

    @Override
    public Map<String, Class<?>> getAlleAttribute() {
        Map<String, Class<?>> attribute = new HashMap<>();
        attribute.put("Name", String.class);
        return attribute;
    }

    @Override
    public String getAttribut(String attribut) {
        switch (attribut) {
            case "Name": {
                return this.name;
            }
            default:
                return null;
        }
    }

    @Override
    public void setAttribut(String attribut, Object wert) {
        if (attribut.equals("Name")) {
            setName(wert.toString());
        }
    }

    @Override
    public void speichere() throws IOException {
        if (this.id == null || this.id.equals("") || this.id.equals("null")) {
            this.id = UUID.randomUUID().toString();
            Autorspeicherung.getInstanz().speichereNeuenAutor(this.createAutor());
        } else {
            Autorspeicherung.getInstanz().aktualisiereAutor(this.id, this.createAutor());
        }
    }
}
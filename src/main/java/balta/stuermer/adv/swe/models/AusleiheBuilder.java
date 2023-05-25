package balta.stuermer.adv.swe.models;

import balta.stuermer.adv.swe.datenhaltung.Ausleihespeicherung;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AusleiheBuilder implements BearbeitbarBuilder {
    private String id;
    private String ausleihender;
    private Buch buch;

    public AusleiheBuilder() {

    }

    public AusleiheBuilder(Ausleihe ausleihe) {
        id = ausleihe.getId();
        ausleihender = ausleihe.getAusleihender();
        buch = ausleihe.getBuch();
    }

    public AusleiheBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public AusleiheBuilder setAusleihender(String ausleihender) {
        this.ausleihender = ausleihender;
        return this;
    }

    public AusleiheBuilder setBuch(Buch buch) {
        this.buch = buch;
        return this;
    }

    public Ausleihe createAusleihe() {
        return new Ausleihe(id, ausleihender, buch);
    }

    @Override
    public Map<String, Class<?>> getAlleAttribute() {
        Map<String, Class<?>> attribute = new HashMap<>();
        attribute.put("Ausleihender", String.class);
        attribute.put("Buch", Buch.class);
        return attribute;
    }

    @Override
    public void setAttribut(String attribut, Object wert) {
        switch (attribut) {
            case "Ausleihender": {
                setAusleihender(wert.toString());
                break;
            }
        }
    }

    @Override
    public Object getAttribut(String attribut) {
        switch (attribut) {
            case "Ausleihender": {
                return this.ausleihender;
            }
            case "Buch": {
                return this.buch;
            }
            default:
                return null;
        }
    }

    @Override
    public void speichere() throws IOException {
        if (this.id == null || this.id.equals("") || this.id.equals("null")) {
            this.id = UUID.randomUUID().toString();
            Ausleihespeicherung.getInstanz().speichereNeueAusleihe(this.createAusleihe());
        } else {
            Ausleihespeicherung.getInstanz().aktualisiereAusleihe(this.id, this.createAusleihe());
        }
    }
}
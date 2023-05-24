package balta.stuermer.adv.swe.models;

import balta.stuermer.adv.swe.datenhaltung.Autorspeicherung;
import balta.stuermer.adv.swe.datenhaltung.Verlagspeicherung;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VerlagBuilder implements BearbeitbarBuilder {
    private String id;
    private String name;

    public VerlagBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public VerlagBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Verlag createVerlag() {
        return new Verlag(id, name);
    }

    @Override
    public Map<String, Class<?>> getAlleAttribute() {
        Map<String, Class<?>> attribute = new HashMap<>();
        attribute.put("Name", String.class);
        return attribute;
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
            Verlagspeicherung.getInstanz().speichereNeuenVerlag(this.createVerlag());
        } else {
            Verlagspeicherung.getInstanz().aktualisiereVerlag(this.id, this.createVerlag());
        }
    }
}
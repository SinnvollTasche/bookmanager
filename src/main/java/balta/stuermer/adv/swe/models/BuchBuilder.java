package balta.stuermer.adv.swe.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuchBuilder implements BearbeitbarBuilder {
    private String titel;
    private String untertitel;
    private List<Autor> autoren;
    private Verlag verlag;
    private int seiten;

    public BuchBuilder setTitel(String titel) {
        this.titel = titel;
        return this;
    }

    public BuchBuilder setUntertitel(String untertitel) {
        this.untertitel = untertitel;
        return this;
    }

    public BuchBuilder setAutoren(List<Autor> autoren) {
        this.autoren = autoren;
        return this;
    }

    public BuchBuilder addAutor(Autor autor) {
        this.autoren.add(autor);
        return this;
    }

    public BuchBuilder removeAutor(Autor autor) {
        this.autoren.remove(autor);
        return this;
    }

    public BuchBuilder setVerlag(Verlag verlag) {
        this.verlag = verlag;
        return this;
    }

    public BuchBuilder setSeiten(int seiten) {
        this.seiten = seiten;
        return this;
    }

    public Buch createBuch() {
        return new Buch(titel, untertitel, autoren, verlag, seiten);
    }

    @Override
    public Map<String, Class<?>> getAlleAttribute() {
        Map<String, Class<?>> attribute = new HashMap<>();
        attribute.put("Titel", String.class);
        attribute.put("Untertitel", String.class);
        attribute.put("Seiten", Integer.class);
        return attribute;
    }

    @Override
    public void setAttribut(String attribut, Object wert) {
        if (attribut.equals("Titel")) {
            setTitel(wert.toString());
        }
    }

    @Override
    public void speichere() {

    }
}
package balta.stuermer.adv.swe.models;

import balta.stuermer.adv.swe.datenhaltung.Buchspeicherung;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Builder für Bücher
 * Einziger Zweck sich zu ändern: Buch ändert sich
 * Entwurfsmuster: Builder
 */
public class BuchBuilder implements BearbeitbarBuilder {
    private String id;
    private String titel;
    private String untertitel;
    private List<Autor> autoren;
    private Verlag verlag;
    private int seiten;
    private Zustand zustand;

    public BuchBuilder() {

    }

    /**
     * Konstruktor.
     * @param buch alle Werte dieses Buchs werden als Default verwendet
     */
    public BuchBuilder(Buch buch){
        setId(buch.getId());
        setTitel(buch.getTitel());
        setUntertitel(buch.getUntertitel());
        setAutoren(buch.getAutoren());
        setVerlag(buch.getVerlag());
        setSeiten(buch.getSeiten());
        setZustand(buch.getZustand());
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public void setZustand(Zustand zustand) {
        this.zustand = zustand;
    }

    public Buch createBuch() {
        return new Buch(id, titel, untertitel, autoren, verlag, seiten, zustand);
    }

    @Override
    public Map<String, Class<?>> getAlleAttribute() {
        Map<String, Class<?>> attribute = new HashMap<>();
        attribute.put("Titel", String.class);
        attribute.put("Untertitel", String.class);
        attribute.put("Seiten", Integer.class);
        attribute.put("Verlag", Verlag.class);
        attribute.put("Autor*Innen", List.class);
        attribute.put("Zustand", Zustand.class);
        return attribute;
    }

    @Override
    public Object getAttribut(String attribut) {
        switch (attribut) {
            case "Titel": {
                return this.titel;
            }
            case "Untertitel": {
                return this.untertitel;
            }
            case "Seiten": {
                return this.seiten + "";
            }
            case "Autor*Innen": {
                return this.autoren;
            }
            case "Verlag": {
                return this.verlag;
            }
            case "Zustand": {
                return this.zustand;
            }
            default:
                return null;
        }
    }

    @Override
    public void setAttribut(String attribut, Object wert) {
        switch (attribut) {
            case "Titel": {
                setTitel(wert.toString());
                break;
            }
            case "Untertitel": {
                setUntertitel(wert.toString());
                break;
            }
            case "Seiten": {
                setSeiten(Integer.parseInt(wert.toString()));
                break;
            }
        }
    }

    @Override
    public void speichere() throws IOException {
        if (this.id == null || this.id.equals("") || this.id.equals("null")) {
            this.id = UUID.randomUUID().toString();
            Buchspeicherung.getInstanz().speichereNeuesBuch(this.createBuch());
        } else {
            Buchspeicherung.getInstanz().aktualisiereBuch(this.id, this.createBuch());
        }
    }
}
package balta.stuermer.adv.swe.models;

import java.util.Map;

public class ExemplarBuilder implements BearbeitbarBuilder {
    private String isbn;
    private String auflage;
    private Zustand zustand;
    private Buch buch;

    public ExemplarBuilder setISBN(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public ExemplarBuilder setAuflage(String auflage) {
        this.auflage = auflage;
        return this;
    }

    public ExemplarBuilder setZustand(Zustand zustand) {
        this.zustand = zustand;
        return this;
    }

    public ExemplarBuilder setBuch(Buch buch) {
        this.buch = buch;
        return this;
    }

    public Exemplar createExemplar() {
        return new Exemplar(isbn, auflage, zustand, buch);
    }

    @Override
    public Map<String, Class<?>> getAlleAttribute() {
        return null;
    }

    @Override
    public void setAttribut(String attribut, Object wert) {
        if (attribut.equals("ISBN")) {
            setISBN(wert.toString());
        }
    }

    @Override
    public String getAttribut(String attribut) {
        return null;
    }

    @Override
    public void speichere() {

    }
}
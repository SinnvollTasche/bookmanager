package balta.stuermer.adv.swe.models;

import java.util.List;

public class BuchBuilder {
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
}
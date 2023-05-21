package balta.stuermer.adv.swe.models;

import java.util.List;

public class Buch {
    private String id;
    private String titel;
    private String untertitel;
    private List<Autor> autoren;
    private Verlag verlag;
    private int seiten;

    public Buch(String titel, String untertitel, List<Autor> autoren, Verlag verlag, int seiten) {
        this.titel = titel;
        this.untertitel = untertitel;
        this.autoren = autoren;
        this.verlag = verlag;
        this.seiten = seiten;
    }

    public String getTitel() {
        return titel;
    }

    public String getUntertitel() {
        return untertitel;
    }

    public List<Autor> getAutoren() {
        return autoren;
    }

    public Verlag getVerlag() {
        return verlag;
    }

    public int getSeiten() {
        return seiten;
    }
}

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getUntertitel() {
        return untertitel;
    }

    public void setUntertitel(String untertitel) {
        this.untertitel = untertitel;
    }

    public List<Autor> getAutoren() {
        return autoren;
    }

    public void setAutoren(List<Autor> autoren) {
        this.autoren = autoren;
    }

    public Verlag getVerlag() {
        return verlag;
    }

    public void setVerlag(Verlag verlag) {
        this.verlag = verlag;
    }

    public int getSeiten() {
        return seiten;
    }

    public void setSeiten(int seiten) {
        this.seiten = seiten;
    }

    @Override
    public String toString() {
        StringBuilder beschreibung = new StringBuilder();
        beschreibung.append(titel);
        if (autoren == null || autoren.size() == 0) {
            return beschreibung.toString();
        }
        beschreibung.append(" von ").append(autoren.get(0).getName());
        if (autoren.size() > 1) {
            beschreibung.append(" und Co.");
        }
        return beschreibung.toString();
    }
}

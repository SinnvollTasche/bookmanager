package balta.stuermer.adv.swe.models;

public class Exemplar {
    private String id;
    private String ISBN;
    private String Auflage;
    private Zustand zustand;
    private Buch buch;

    public Exemplar(String ISBN, String auflage, Zustand zustand, Buch buch) {
        this.ISBN = ISBN;
        Auflage = auflage;
        this.zustand = zustand;
        this.buch = buch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuflage() {
        return Auflage;
    }

    public void setAuflage(String auflage) {
        Auflage = auflage;
    }

    public Zustand getZustand() {
        return zustand;
    }

    public void setZustand(Zustand zustand) {
        this.zustand = zustand;
    }

    public Buch getBuch() {
        return buch;
    }

    public void setBuch(Buch buch) {
        this.buch = buch;
    }
}

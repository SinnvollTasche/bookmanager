package balta.stuermer.adv.swe.models;

public class Exemplar {
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
}

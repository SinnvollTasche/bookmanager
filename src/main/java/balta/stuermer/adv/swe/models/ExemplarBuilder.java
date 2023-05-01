package balta.stuermer.adv.swe.models;

public class ExemplarBuilder {
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
}
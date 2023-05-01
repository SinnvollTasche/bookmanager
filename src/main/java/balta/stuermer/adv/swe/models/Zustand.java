package balta.stuermer.adv.swe.models;

public enum Zustand {
    NEU(0, "neu"),
    WIENEU(1, "wie neu"),
    SEHRGUT(2, "sehr gut"),
    GUT(3, "gut"),
    VIELGELESEN(4, "vielgelesen"),
    MUMIE(5, "Mumie");


    private final int id;
    private final String beschreibung;

    Zustand(final int id, final String beschreibung) {
        this.id = id;
        this.beschreibung = beschreibung;
    }

    public int getId() {
        return id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public Zustand getById(final int id) {
        Zustand[] zustaende = Zustand.values();
        if (id > zustaende.length || id < 0) {
            throw new IllegalArgumentException(id + " konnte keinem Zustand zugeordnet werden.");
        }
        return zustaende[id];
    }
}

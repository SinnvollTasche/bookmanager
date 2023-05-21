package balta.stuermer.adv.swe.datenhaltung;

import balta.stuermer.adv.swe.models.Autor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Autorspeicherung {
    private File speicherort;

    public Autorspeicherung(File speicherort) {
        this.speicherort = speicherort;
    }

    public List<Autor> findeAlleAutoren() {
        return new ArrayList<>();
    }

    public void speichereNeuenAutor(Autor autor) {

    }

    public Autor findeAutor(String name) {
        return new Autor("");
    }

    public Autor findeAutorMitId(String id) {
        return new Autor("");
    }

    public void aktualisiereAutor(String id, Autor aktualisierterAutor) {

    }

    public void loescheAutor(String id) {

    }
}

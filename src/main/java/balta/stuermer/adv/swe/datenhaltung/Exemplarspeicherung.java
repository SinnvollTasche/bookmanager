package balta.stuermer.adv.swe.datenhaltung;

import balta.stuermer.adv.swe.models.Buch;
import balta.stuermer.adv.swe.models.Exemplar;
import balta.stuermer.adv.swe.models.ExemplarBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Exemplarspeicherung {
    private File speicherort;

    public Exemplarspeicherung(File speicherort) {
        this.speicherort = speicherort;
    }

    public List<Exemplar> findeAlleExemplare(Buch b) {
        return new ArrayList<>();
    }

    public void speichereNeuesExemplar(Exemplar exemplar) {

    }

    public Exemplar findeExemplarMitID(String id) {
        return new ExemplarBuilder().createExemplar();
    }

    public void aktualisiereExemplar(String id, Exemplar aktualisiertesExemplar) {

    }

    public void loescheExemplar(String id) {

    }
}

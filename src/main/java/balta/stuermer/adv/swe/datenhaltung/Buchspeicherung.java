package balta.stuermer.adv.swe.datenhaltung;

import balta.stuermer.adv.swe.models.Buch;
import balta.stuermer.adv.swe.models.BuchBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Buchspeicherung {
    private File speicherort;

    public Buchspeicherung(File speicherort) {
        this.speicherort = speicherort;
    }

    public List<Buch> findeAlleBuecher() {
        return new ArrayList<>();
    }

    public void speichereNeuesBuch(Buch buch) {

    }

    public Buch findeBuchMitId(String id) {
        return new BuchBuilder().createBuch();
    }

    public void aktualisiereBuch(String id, Buch aktualisiertesBuch) {

    }

    public void loescheBuch(String id) {

    }
}

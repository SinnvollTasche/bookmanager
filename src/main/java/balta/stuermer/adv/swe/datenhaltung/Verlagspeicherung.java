package balta.stuermer.adv.swe.datenhaltung;

import balta.stuermer.adv.swe.models.Verlag;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Verlagspeicherung {
    private File speicherort;

    public Verlagspeicherung(File speicherort) {
        this.speicherort = speicherort;
    }

    public List<Verlag> findeAlleVerlage() {
        return new ArrayList<>();
    }

    public void speichereNeuenVerlag(Verlag verlag) {

    }

    public Verlag findeVerlagMitName(String name) {
        return new Verlag("");
    }

    public Verlag findeVerlagMitId(String id) {
        return new Verlag("");
    }

    public void aktualisiereVerlag(String id, Verlag aktualisierterVerlag) {

    }

    public void loescheVerlag(String id) {

    }
}

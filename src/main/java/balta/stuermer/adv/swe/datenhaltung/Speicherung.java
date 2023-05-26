package balta.stuermer.adv.swe.datenhaltung;

import balta.stuermer.adv.swe.models.Anzeigbar;

import java.util.List;

public interface Speicherung {
    List<? extends Anzeigbar> findeAlle();
    List<? extends Anzeigbar> findeMitSuchbegriff(String suchbegriff);
}

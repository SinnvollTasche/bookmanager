package balta.stuermer.adv.swe.datenhaltung;

import balta.stuermer.adv.swe.models.Anzeigbar;

import java.util.List;

/**
 * Interface für Speicherungen
 * Einziger Zweck sich zu ändern: Speicherung ändert sich
 * Clean Architecture Level: Adapter/Plugin
 */
public interface Speicherung {
    List<? extends Anzeigbar> findeAlle();
    List<? extends Anzeigbar> findeMitSuchbegriff(String suchbegriff);
}

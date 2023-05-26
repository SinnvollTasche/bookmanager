package balta.stuermer.adv.swe.models;

import java.util.Map;

public interface Anzeigbar {
    /**
     * Methode, die alle Attributnamen und ihren String-Wert zurückgibt
     * @return Map aller Attributnamen und ihrer String-Werte
     */
    Map<String, String> getAlleAttribute();
}

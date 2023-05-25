package balta.stuermer.adv.swe.models;

import java.io.IOException;
import java.util.Map;

public interface BearbeitbarBuilder {

    Map<String, Class<?>> getAlleAttribute();
    void setAttribut(String attribut, Object wert);
    Object getAttribut(String attribut);
    void speichere() throws IOException;
}

package balta.stuermer.adv.swe.models;

public class Verlag {
    private String id;
    private String name;

    public Verlag(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

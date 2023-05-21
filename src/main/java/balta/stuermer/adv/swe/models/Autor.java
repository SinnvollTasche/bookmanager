package balta.stuermer.adv.swe.models;

public class Autor {
    private String id;
    private String name;

    public Autor(String id, String name) {
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

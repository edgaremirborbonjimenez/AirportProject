package utils;

public enum ECity {
    MEXICO ("Mexico"),
    USA ("USA"),
    BRASIL ("Brasil"),
    GUATEMALA ("Guatemala"),
    SPAIN ("Spain"),
    ROME ("Rome"),;

    private final String name;
    ECity(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                '}';
    }
}

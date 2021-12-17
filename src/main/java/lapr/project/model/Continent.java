package lapr.project.model;

import java.util.HashMap;
import java.util.Map;

public enum Continent {
    EUROPE("Europe"),
    AFRICA("Africa"),
    AMERICA("America"),
    OCEANIA("Oceania"),
    ANTARCTICA("Antarctica"),
    ASIA("Asia");

    private static final Map<String, Continent> continentMap = new HashMap<>();

    static {
        for (Continent e : values()) {
            continentMap.put(e.name, e);
        }
    }

    private final String name;

    Continent(String name) {
        this.name = name;
    }

    public static Continent valueOfName(String name) {
        return continentMap.get(name);
    }

    public String getName() {
        return name;
    }

}

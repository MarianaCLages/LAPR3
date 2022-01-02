package lapr.project.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Continents enumerated.
 */
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

    /**
     * Constructor.
     *
     * @param name the continent's name
     */
    Continent(String name) {
        this.name = name;
    }

    /**
     * Gets the name of a continent from the map.
     *
     * @param name the continent's name
     * @return the continent found
     */
    public static Continent valueOfName(String name) {
        return continentMap.get(name);
    }

    /**
     * Gets the continent's name.
     *
     * @return the continent's name
     */
    public String getName() {
        return name;
    }
}

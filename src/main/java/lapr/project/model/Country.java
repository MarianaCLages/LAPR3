package lapr.project.model;

public class Country {
    private String name;
    private char[] alpha2Code;
    private char[] alpha3Code;
    private double population;
    private Continent continent;

    /**
     * Constructor.
     *
     * @param name       the country's name
     * @param alpha2Code the country's alpha2 code
     * @param alpha3Code the country's alpha3 code
     * @param population the country's population
     * @param continent  the country's continent
     */
    public Country(String name, char[] alpha2Code, char[] alpha3Code, double population, Continent continent) {
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.population = population;
        this.continent = continent;
    }

    /**
     * Gets the country's name.
     *
     * @return the country's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the country's alpha2 code.
     *
     * @return the country's alpha2 code
     */
    public char[] getAlpha2Code() {
        return alpha2Code;
    }

    /**
     * Gets the country's alpha3 code.
     *
     * @return the alpha3 code
     */
    public char[] getAlpha3Code() {
        return alpha3Code;
    }

    /**
     * Gets the country's population.
     *
     * @return the country's population
     */
    public double getPopulation() {
        return population;
    }

    /**
     * Gets the country's continent.
     *
     * @return the country's continent
     */
    public Continent getContinent() {
        return continent;
    }

    /**
     * Returns the textual description of the country in the format: name.
     *
     * @return the country's characteristics
     */
    @Override
    public String toString() {
        return name;
    }
}

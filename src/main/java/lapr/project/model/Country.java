package lapr.project.model;

public class Country {
    private String name;
    private char[] alpha2Code;
    private char[] alpha3Code;
    private double population;
    private Continent continent;

    public Country(String name, char[] alpha2Code, char[] alpha3Code, double population, Continent continent) {
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.population = population;
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public char[] getAlpha2Code() {
        return alpha2Code;
    }

    public char[] getAlpha3Code() {
        return alpha3Code;
    }

    public double getPopulation() {
        return population;
    }

    public Continent getContinent() {
        return continent;
    }

    @Override
    public String toString() {
        return name;
    }
}

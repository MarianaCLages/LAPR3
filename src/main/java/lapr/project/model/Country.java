package lapr.project.model;

public class Country {
    private City capital;
    private String name;
    private char[] alpha2Code;
    private char[] alpha3Code;
    private double population;
    private String continent;

    public Country(City capital, String name, char[] alpha2Code, char[] alpha3Code, double population, String continent) {
        this.capital = capital;
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.population = population;
        this.continent = continent;
    }

}

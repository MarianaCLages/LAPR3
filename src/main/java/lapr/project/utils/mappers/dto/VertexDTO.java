package lapr.project.utils.mappers.dto;

public class VertexDTO {

    private final String identification;
    private final String name;

    /**
     * Constructor.
     *
     * @param identification the vertex identification
     * @param name           the vertex name
     */
    public VertexDTO(String identification, String name) {
        this.identification = identification;
        this.name = name;
    }

    /**
     * Returns the textual description of the vertex info.
     *
     * @return the vertex characteristics
     */
    @Override
    public String toString() {
        return "Local: " + identification + " -> " + name;
    }

    /**
     * Gets the vertex name.
     *
     * @return the vertex name
     */
    public String getName() {
        return name;
    }
}

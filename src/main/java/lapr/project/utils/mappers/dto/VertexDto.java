package lapr.project.utils.mappers.dto;

public class VertexDto {

    private final String identification;
    private final String name;

    public VertexDto(String identification, String name) {
        this.identification = identification;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "identification='" + identification + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }
}

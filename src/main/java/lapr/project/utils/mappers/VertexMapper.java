package lapr.project.utils.mappers;

import lapr.project.model.City;
import lapr.project.shared.graph.Vertex;
import lapr.project.utils.mappers.dto.VertexDto;

import java.util.ArrayList;
import java.util.List;

public class VertexMapper {

    /**
     * Converts the vertex info into DTO.
     *
     * @param vertex the vertex
     * @return the vertex info in DTO
     */
    private VertexDto toDTO(Vertex vertex) {
        if (vertex instanceof City) return new VertexDto("City", vertex.getName());
        else return new VertexDto("Port", vertex.getName());
    }

    /**
     * Converts the vertex list into DTO.
     *
     * @param vList the vertex list
     * @return the vertex list in DTO
     */
    public List<VertexDto> toDTO(List<Vertex> vList) {
        List<VertexDto> vertexDtoList = new ArrayList<>();
        for (Vertex vertex : vList) {
            vertexDtoList.add(this.toDTO(vertex));
        }
        return vertexDtoList;
    }
}
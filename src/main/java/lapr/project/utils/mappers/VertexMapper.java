package lapr.project.utils.mappers;

import lapr.project.model.City;
import lapr.project.shared.graph.Vertex;
import lapr.project.utils.mappers.dto.VertexDTO;

import java.util.ArrayList;
import java.util.List;

public class VertexMapper {

    /**
     * Converts the vertex info into DTO.
     *
     * @param vertex the vertex
     * @return the vertex info in DTO
     */
    private VertexDTO toDTO(Vertex vertex) {
        if (vertex instanceof City) return new VertexDTO("City", vertex.getName());
        else return new VertexDTO("Port", vertex.getName());
    }

    /**
     * Converts the vertex list into DTO.
     *
     * @param vList the vertex list
     * @return the vertex list in DTO
     */
    public List<VertexDTO> toDTO(List<Vertex> vList) {
        List<VertexDTO> vertexDTOList = new ArrayList<>();
        for (Vertex vertex : vList) {
            vertexDTOList.add(this.toDTO(vertex));
        }
        return vertexDTOList;
    }
}
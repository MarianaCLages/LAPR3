package lapr.project.utils.mappers;

import lapr.project.model.City;
import lapr.project.model.Port;
import lapr.project.shared.graph.Vertex;
import lapr.project.utils.mappers.dto.PortDTO;
import lapr.project.utils.mappers.dto.VertexDto;

import java.util.ArrayList;
import java.util.List;

public class VertexMapper {

    private VertexDto toDTO(Vertex vertex) {
        if (vertex instanceof City) return new VertexDto("City", vertex.getName());
        else return new VertexDto("Port", vertex.getName());
    }

    public List<VertexDto> toDTO(List<Vertex> vList) {
        List<VertexDto> vertexDtoList = new ArrayList<>();
        for (Vertex vertex : vList) {
            vertexDtoList.add(this.toDTO(vertex));
        }
        return vertexDtoList;
    }

}
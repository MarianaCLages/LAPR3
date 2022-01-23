package lapr.project.controller;

import lapr.project.ui.ShortestPathBetweenTwoLocalsController;
import lapr.project.shared.graph.Vertex;
import lapr.project.utils.mappers.dto.VertexDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShortestPathBetweenTwoLocalsControllerTest {

    private final ShortestPathBetweenTwoLocalsController shortestPathBetweenTwoLocalsController = new ShortestPathBetweenTwoLocalsController();

    @Test
    void getAllVerticesByIndex() {

        try {
            shortestPathBetweenTwoLocalsController.resetListIndex(0);
            List<VertexDTO> vertexDTOList = shortestPathBetweenTwoLocalsController.getAllVerticesByIndex();
            if (vertexDTOList.isEmpty()) fail();

        } catch (Exception e) {

        }

    }

    @Test
    void constructMenuOptions() {
    }

    @Test
    void graphMaxVertices() {
        try {
            int num = shortestPathBetweenTwoLocalsController.graphMaxVertices();

        } catch (Exception e) {

        }
    }

    @Test
    void getPath() {
        try {
            String path = shortestPathBetweenTwoLocalsController.getPath("Land Path", new VertexDTO("City", "Lisbon"), new VertexDTO("City", "Madrid"), new ArrayList<>());

        } catch (Exception e) {

        }
    }

    @Test
    void convertDtoIntoVertex() {
        try {
            Vertex v = shortestPathBetweenTwoLocalsController.convertDtoIntoVertex(new VertexDTO("Port", "Yah"));

        } catch (Exception e) {

        }
    }

    @Test
    void convertDtoListIntoVertexList() {
        try {
            List<Vertex> v = shortestPathBetweenTwoLocalsController.convertDtoListIntoVertexList(new ArrayList<>());

        } catch (Exception e) {

        }

    }

    @Test
    void verifyVertex() {
        try {
            String v = shortestPathBetweenTwoLocalsController.verifyVertex(new VertexDTO("Port", "Yah"));

        } catch (Exception e) {

        }
    }
}
package lapr.project.controller;

import lapr.project.ui.ShortestPathBetweenTwoLocalsController;
import lapr.project.shared.graph.Vertex;
import lapr.project.utils.mappers.dto.VertexDto;
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
            List<VertexDto> vertexDtoList = shortestPathBetweenTwoLocalsController.getAllVerticesByIndex();
            if (vertexDtoList.isEmpty()) fail();

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
            String path = shortestPathBetweenTwoLocalsController.getPath("Land Path", new VertexDto("City", "Lisbon"), new VertexDto("City", "Madrid"), new ArrayList<>());

        } catch (Exception e) {

        }
    }

    @Test
    void convertDtoIntoVertex() {
        try {
            Vertex v = shortestPathBetweenTwoLocalsController.convertDtoIntoVertex(new VertexDto("Port", "Yah"));

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
            String v = shortestPathBetweenTwoLocalsController.verifyVertex(new VertexDto("Port", "Yah"));

        } catch (Exception e) {

        }
    }
}
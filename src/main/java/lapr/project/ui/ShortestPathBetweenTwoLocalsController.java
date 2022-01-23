package lapr.project.ui;

import lapr.project.controller.App;
import lapr.project.model.City;
import lapr.project.model.Port;
import lapr.project.shared.exceptions.NullVerticesException;
import lapr.project.shared.graph.FreightNetwork;
import lapr.project.shared.graph.Graph;
import lapr.project.shared.graph.ShortPaths;
import lapr.project.shared.graph.Vertex;
import lapr.project.utils.mappers.VertexMapper;
import lapr.project.utils.mappers.dto.VertexDTO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.emptyList;

public class ShortestPathBetweenTwoLocalsController {

    private final Graph<Vertex, Double> graph;
    private final VertexMapper vertexMapper;
    private final FreightNetwork freightNetwork;
    private final LinkedList<List<Vertex>> vertexList;
    private int index = 0;
    private boolean buildGraph = false;
    private List<VertexDTO> vertexDTOList;

    /**
     * Constructor.
     */
    public ShortestPathBetweenTwoLocalsController() {
        freightNetwork = App.getInstance().getCompany().getFreightNetwork();
        this.vertexList = new LinkedList<>();
        this.graph = freightNetwork.getGraph();
        this.vertexMapper = new VertexMapper();
        this.vertexDTOList = new ArrayList<>();
    }

    /**
     * Gets all vertices by its index.
     *
     * @return a list of vertices
     */
    public List<VertexDTO> getAllVerticesByIndex() {

        if (!this.buildGraph) {
            constructList();
            this.buildGraph = true;
        }

        List<Vertex> listByIndex = vertexList.get(index);
        this.vertexDTOList = vertexMapper.toDTO(listByIndex);
        index++;
        return vertexDTOList;
    }

    /**
     * Constructs the list of vertices.
     */
    private void constructList() {
        List<Vertex> auxList = new ArrayList<>();
        int auxIndex = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < (graph.numVertices()/4); j++) {
                auxList.add(graph.vertex(auxIndex));
                auxIndex++;
            }
            vertexList.add(auxList);
            auxList = new ArrayList<>();
        }
    }

    /**
     * Resets the index.
     *
     * @param index the index
     */
    public void resetListIndex(int index) {
        this.index = index;
    }

    /**
     * Constructs the menu options.
     *
     * @return the options list
     */
    public List<String> constructMenuOptions() {
        List<String> options = new ArrayList<>();
        options.add("Land path");
        options.add("Maritime path");
        options.add("Land or sea path");
        return options;
    }

    /**
     * Gets the number of vertices of the graph.
     *
     * @return the number of vertices of the graph
     */
    public int graphMaxVertices() {
        return graph.numVertices();
    }

    /**
     * Gets the path.
     *
     * @param pathOption           path option
     * @param originVertexDTO      the origin vertex
     * @param endVertexDTO         the end vertex
     * @param intermediateVertices the intermediate vertices
     * @return the path (in a string)
     */
    public String getPath(String pathOption, VertexDTO originVertexDTO, VertexDTO endVertexDTO, List<VertexDTO> intermediateVertices) {

        if (pathOption == null) {
            throw new NullPointerException("The entered path is null! There was an error while entering data!");
        } else if (originVertexDTO == null || endVertexDTO == null) {
            throw new NullPointerException("The entered origin vertex or end vertex is null! There was an error while entering data!");
        }

        Vertex originVertex = convertDtoIntoVertex(originVertexDTO);
        Vertex endVertex = convertDtoIntoVertex(endVertexDTO);

        List<Vertex> intermediateVertexList = convertDtoListIntoVertexList(intermediateVertices);

        List<Vertex> vertexList = new ArrayList<>();

        switch (pathOption) {
            case ("Land path"):
                try {
                    vertexList = ShortPaths.landPath(freightNetwork.getGraph(), intermediateVertexList, originVertex, endVertex);
                } catch (NullVerticesException e) {
                    vertexList = emptyList();
                }
                break;
            case ("Maritime path"):
                try {
                    vertexList = ShortPaths.seaPath(freightNetwork.getGraph(), intermediateVertexList, originVertex, endVertex);
                } catch (NullVerticesException e) {
                    vertexList = emptyList();
                }
                break;
            case ("Land or sea path"):

                vertexList = ShortPaths.calculateBestPath(freightNetwork.getGraph(), intermediateVertexList, originVertex, endVertex);

                break;

            default:
                break;
        }


        StringBuilder sb = new StringBuilder();
        sb.append("\n\nPath: \n");

        for (int i = 0; i < vertexList.size(); i++) {
            sb.append(vertexList.get(i).getName());
            if (i != vertexList.size() - 1) {
                sb.append(" -> ");
            }
        }

        if (sb.toString().isEmpty()) {
            throw new NullPointerException();
        }

        return sb.toString();
    }

    /**
     * Converts DTO into vertex.
     *
     * @param vertexDto the vertex DTO
     * @return the vertex (success) or null (failed)
     */
    public Vertex convertDtoIntoVertex(VertexDTO vertexDto) {
        for (Vertex v : graph.vertices()) {
            if (v.getName().equals(vertexDto.getName())) {
                return v;
            }
        }
        return null;
    }

    /**
     * Converts DTO list into vertex list.
     *
     * @param intermediateVertices the intermediate vertices
     * @return the vertex list
     */
    public List<Vertex> convertDtoListIntoVertexList(List<VertexDTO> intermediateVertices) {
        List<Vertex> vertexList = new ArrayList<>();

        for (VertexDTO vDto : intermediateVertices) {
            for (Vertex v : graph.vertices()) {
                if (v.getName().equals(vDto.getName())) {
                    vertexList.add(v);
                }
            }
        }
        return vertexList;
    }

    /**
     * Verifies the vertex (city or port).
     *
     * @param vertexDto the vertex DTO
     * @return "City" (if the vertex is a city) or "Port" (if the vertex is a port)
     */
    public String verifyVertex(VertexDTO vertexDto) {
        for (Vertex v : graph.vertices()) {
            if (v.getName().equals(vertexDto.getName())) {
                if (v instanceof City) return "City";
                else if (v instanceof Port) return "Port";
            }
        }
        return null;
    }
}
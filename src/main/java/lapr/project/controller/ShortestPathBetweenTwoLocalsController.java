package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.shared.graph.FreightNetwork;
import lapr.project.shared.graph.Graph;
import lapr.project.shared.graph.Vertex;
import lapr.project.utils.mappers.VertexMapper;
import lapr.project.utils.mappers.dto.VertexDto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShortestPathBetweenTwoLocalsController {

    private LinkedList<List<Vertex>> vertexesList;
    private int index = 0;
    private boolean buildGraph = false;
    private final Graph<Vertex, Double> graph;
    private final VertexMapper vertexMapper;
    private List<VertexDto> vertexDtoList;

    public ShortestPathBetweenTwoLocalsController() {
        Company company = App.getInstance().getCompany();
        FreightNetwork freightNetwork = company.getFreightNetwork();
        this.vertexesList = new LinkedList<>();
        this.graph = freightNetwork.getGraph();
        this.vertexMapper = new VertexMapper();
        this.vertexDtoList = new ArrayList<>();
    }

    public List<VertexDto> getAllVertexesByIndex() {

        if (!this.buildGraph) {
            constructList();
            this.buildGraph = true;
        }

        List<Vertex> listByIndex = vertexesList.get(index);
        this.vertexDtoList = vertexMapper.toDTO(listByIndex);
        index++;
        return vertexDtoList;
    }

    private void constructList() {
        List<Vertex> auxList = new ArrayList<>();
        int auxIndex = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 35; j++) {
                auxList.add(graph.vertex(auxIndex));
                auxIndex++;
            }
            vertexesList.add(auxList);
            auxList = new ArrayList<>();
        }
    }

    public void resetListIndex(int index) {
        this.index = index;
    }

    public List<String> constructMenuOptions() {
        List<String> options = new ArrayList<>();
        options.add("Land path");
        options.add("Maritime path");
        options.add("Land or sea path");
        return options;
    }

    public int graphMaxVertexes() {
        return graph.numVertices();
    }

    public VertexDto getVertex(String name) {
        for (VertexDto v : vertexDtoList) {
            if (v.getName().equals(name)) {
                return v;
            }
        }
        return null;
    }

    public String funcaoEdu(String pathOption, VertexDto originVertexDto, VertexDto endVertexDto, List<VertexDto> intermediateVertexes) {

        if (pathOption == null) {
            throw new NullPointerException("The entered path is null! There was an error while entering data!");
        } else if (originVertexDto == null || endVertexDto == null) {
            throw new NullPointerException("The entered origin vertex or end vertex is null! There was an error while entering data!");
        }

        Vertex originVertex = convertDtoIntoVertex(originVertexDto);
        Vertex endVertex = convertDtoIntoVertex(endVertexDto);

        List<Vertex> intermediateVertexList = convertDtoListIntoVertexList(intermediateVertexes);

        System.out.println(pathOption + "\n");
        System.out.println(originVertex + "\n");
        System.out.println(endVertex + "\n");
        System.out.println(intermediateVertexList.toString());

        switch (pathOption) {
            case ("Land path"):
                //LandPath
                //return deez
                break;
            case ("Maritime path"):
                //Maritime Path
                //return deez
                break;
            case ("Land or sea path"):
                //Land or sea path
                //return deez
                break;
        }

        return null;
    }

    public Vertex convertDtoIntoVertex(VertexDto vertexDto) {
        for (Vertex v : graph.vertices()) {
            if (v.getName().equals(vertexDto.getName())) {
                return v;
            }
        }
        return null;
    }

    public List<Vertex> convertDtoListIntoVertexList(List<VertexDto> intermediateVertexes) {
        List<Vertex> vertexList = new ArrayList<>();

        for (VertexDto vDto : intermediateVertexes) {
            for (Vertex v : graph.vertices()) {
                if (v.getName().equals(vDto.getName())) {
                    vertexList.add(v);
                }
            }
        }
        return vertexList;
    }

}
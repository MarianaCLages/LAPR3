package lapr.project.ui;

import lapr.project.controller.ShortestPathBetweenTwoLocalsController;
import lapr.project.utils.mappers.dto.VertexDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShorthestPathBetweenTwoLocalsUI implements Runnable {

    public final ShortestPathBetweenTwoLocalsController controller;

    public ShorthestPathBetweenTwoLocalsUI() {
        controller = new ShortestPathBetweenTwoLocalsController();
    }


    @Override
    public void run() {

        String desiredVertex = "0";
        VertexDto desiredVertexDto = null;
        List<VertexDto> desiredVertexes = new ArrayList<>();
        String endVertex;
        boolean optionFlag = false;
        String optionIntermediate;
        VertexDto intermediateVertexDto = null;
        String pathOption = null;
        String intermediateVertex = "0";
        int numberVertexes;
        String verifyMoreVertexes;
        int nextOption = 0;
        List<VertexDto> optionVertexes = new ArrayList<>();
        boolean optionFirstVertex = false;

        do {
            try {
                Object auxObj = Utils.showAndSelectOne(controller.constructMenuOptions(), "\nPlease choose one of the 3 valid paths: ");
                if (auxObj == null) {
                    System.out.println("\nOperation stopped!\n");
                    return;
                } else {
                    pathOption = (String) auxObj;
                    optionFlag = true;
                }

            } catch (Exception ex) {
                System.out.println("\nPlease enter a valid option! (Dont enter a symbol nor random information)");
                optionFlag = false;
            }
        } while (!optionFlag);

        do {
            try {
                for (int index = 0; index < 2; index++) {

                    Object auxObj;

                    if (!optionFirstVertex) {
                        auxObj = Utils.showAndSelectOneWithMessage(controller.getAllVerticesByIndex(), "\nPlease choose the departure vertex from the valid option of the list");
                        optionFirstVertex = true;
                    } else {
                        auxObj = Utils.showAndSelectOneWithMessage(controller.getAllVerticesByIndex(), "\nPlease choose the arrival vertex from the valid option of the list");
                    }

                    if (auxObj == null) {
                        desiredVertex = String.valueOf(36);
                    } else if (auxObj.equals(36)) {
                        desiredVertex = String.valueOf(36);
                    } else {
                        desiredVertexDto = (VertexDto) auxObj;
                        desiredVertex = String.valueOf(0);
                    }

                    nextOption = 1;

                    while (desiredVertex.equals("36")) {
                        auxObj = Utils.showAndSelectOneWithMessage(controller.getAllVerticesByIndex(), "\nPlease choose one valid option of the list");

                        if (auxObj == null) {
                            desiredVertex = String.valueOf(36);
                        } else if (auxObj.equals(36)) {
                            desiredVertex = String.valueOf(36);
                        } else {
                            desiredVertexDto = (VertexDto) auxObj;
                            desiredVertex = String.valueOf(0);
                        }
                        nextOption++;
                        if (nextOption == 3) {
                            controller.resetListIndex(0);
                            nextOption = 0;
                        }
                    }

                    controller.resetListIndex(0);
                    desiredVertexes.add(desiredVertexDto);

                }

                optionFlag = true;

            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
                optionFlag = false;
                controller.resetListIndex(0);
            } catch (IllegalArgumentException ex) {
                optionFlag = false;
                controller.resetListIndex(0);
                System.out.println("\nPlease enter one of the options mention above! (Dont enter a symbol or miss information!)");
            } catch (Exception ex) {
                System.out.println("\nPlease enter one of the options mention above! (Dont enter a symbol or miss information!)");
                optionFlag = false;
                controller.resetListIndex(0);
            }

        } while (!optionFlag);

        controller.resetListIndex(0);

        do {
            try {
                numberVertexes = Utils.readIntegerFromConsole("\nPlease enter the number of intermediate vertexes that you wish to have in the path: ");

                if (numberVertexes < 0 || numberVertexes > controller.graphMaxVertices()) {
                    throw new NullPointerException("\nPlease enter a valid number! You can not have negative vertexes has intermediate points or even more intermediate points than the graph max vertexes! (Graph max vertexes: " + (controller.graphMaxVertices() - 2) + ")");
                }

                for (int indexOption = 0; indexOption < numberVertexes; indexOption++) {

                    Object auxObj = Utils.showAndSelectOneWithMessage(controller.getAllVerticesByIndex(), "\nPlease choose one valid option of the list");

                    if (auxObj == null) {
                        intermediateVertex = String.valueOf(36);
                    } else if (auxObj.equals(36)) {
                        intermediateVertex = String.valueOf(36);
                    } else {
                        intermediateVertex = String.valueOf(0);
                        intermediateVertexDto = (VertexDto) auxObj;
                    }

                    while (intermediateVertex.equals("36")) {
                        auxObj = Utils.showAndSelectOneWithMessage(controller.getAllVerticesByIndex(), "\nPlease choose one valid option of the list");

                        if (auxObj == null) {
                            intermediateVertex = String.valueOf(36);
                        } else if (auxObj.equals(36)) {
                            intermediateVertex = String.valueOf(36);
                        } else {
                            intermediateVertex = String.valueOf(0);
                            intermediateVertexDto = (VertexDto) auxObj;
                        }
                        nextOption++;
                        if (nextOption == 3) {
                            controller.resetListIndex(0);
                            nextOption = 0;
                        }
                    }

                    controller.resetListIndex(0);

                    String typeOfVertex = controller.verifyVertex(desiredVertexDto);

                    if (pathOption.equals("Land path")) {
                        if (typeOfVertex.equals("Port"))
                            throw new NullPointerException("\nPlease only enter Cities! Since you desire a land path, it is necessary to only CHOOSE CITIES!");
                    } else if (pathOption.equals("Maritime path")) {
                        if (typeOfVertex.equals("City"))
                            throw new NullPointerException("\nPlease only enter Ports! Since you desire a maritime path, it is necessary to only CHOOSE PORTS!");
                    }

                    optionVertexes.add(intermediateVertexDto);
                }

                optionFlag = true;

            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
                controller.resetListIndex(0);
                optionFlag = false;
            } catch (IllegalArgumentException ex) {
                System.out.println("\nPlease enter a number!! Dont enter a set of characters,space or any kind of symbol!");
                controller.resetListIndex(0);
                optionFlag = false;
            } catch (Exception ex) {
                System.out.println("\nThere is no possible path between the origin local and arrival local, please enter another path (Please verify the data!)\n");
                controller.resetListIndex(0);
                optionFlag = false;
            }
        } while (!optionFlag);

        VertexDto beginVertex = desiredVertexes.get(0);
        VertexDto endVertexF = desiredVertexes.get(1);

        try {
            if (optionVertexes == null || optionVertexes.isEmpty()) {
                controller.getPath(pathOption, beginVertex, endVertexF, Collections.emptyList());
            } else {
                System.out.println(controller.getPath(pathOption, beginVertex, endVertexF, optionVertexes));
            }
            System.out.println("\nOperation Success!\n");
        } catch (Exception e) {
            System.out.println("\nThere is no possible path between the origin location and arrival location, please enter another path! (Note verify all possible paths before entering data again)\n");
            System.out.println("\nOperation Failed!\n");
            controller.resetListIndex(0);
        }

    }
}
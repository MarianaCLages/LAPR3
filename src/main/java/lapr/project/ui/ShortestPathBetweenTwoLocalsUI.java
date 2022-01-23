package lapr.project.ui;

import lapr.project.utils.mappers.dto.VertexDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShortestPathBetweenTwoLocalsUI implements Runnable {

    public final ShortestPathBetweenTwoLocalsController controller;

    public ShortestPathBetweenTwoLocalsUI() {
        controller = new ShortestPathBetweenTwoLocalsController();
    }


    @Override
    public void run() {

        String desiredVertex = "0";
        VertexDTO desiredVertexDTO = null;
        List<VertexDTO> desiredVertices = new ArrayList<>();
        String endVertex;
        boolean optionFlag = false;
        String optionIntermediate;
        VertexDTO intermediateVertexDTO = null;
        String pathOption = null;
        String intermediateVertex = "0";
        int numberVertices;
        String verifyMoreVertices;
        int nextOption = 0;
        List<VertexDTO> optionVertices = new ArrayList<>();
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
                        auxObj = Utils.showAndSelectOneWithMessage(controller.getAllVerticesByIndex(), "\nPlease choose the departure local from the list below:");
                        optionFirstVertex = true;
                    } else {
                        auxObj = Utils.showAndSelectOneWithMessage(controller.getAllVerticesByIndex(), "\nPlease choose the arrival local from the list below:");
                    }

                    if (auxObj == null) {
                        desiredVertex = String.valueOf(36);
                    } else if (auxObj.equals(36)) {
                        desiredVertex = String.valueOf(36);
                    } else {
                        desiredVertexDTO = (VertexDTO) auxObj;
                        desiredVertex = String.valueOf(0);
                    }

                    nextOption = 1;

                    while (desiredVertex.equals("36")) {
                        auxObj = Utils.showAndSelectOneWithMessage(controller.getAllVerticesByIndex(), "\nPlease choose one valid option of the list:");

                        if (auxObj == null) {
                            desiredVertex = String.valueOf(36);
                        } else if (auxObj.equals(36)) {
                            desiredVertex = String.valueOf(36);
                        } else {
                            desiredVertexDTO = (VertexDTO) auxObj;
                            desiredVertex = String.valueOf(0);
                        }
                        nextOption++;
                        if (nextOption == 3) {
                            controller.resetListIndex(0);
                            nextOption = 0;
                        }
                    }

                    controller.resetListIndex(0);
                    desiredVertices.add(desiredVertexDTO);

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
                numberVertices = Utils.readIntegerFromConsole("\nPlease enter the number of intermediate vertices that you wish to have in the path: ");

                if (numberVertices < 0 || numberVertices > controller.graphMaxVertices()) {
                    throw new NullPointerException("\nPlease enter a valid number! You can not have negative vertices as intermediate points or more intermediate points than the graph max vertices! (Graph max vertexes: " + (controller.graphMaxVertices() - 2) + ")");
                }

                for (int indexOption = 0; indexOption < numberVertices; indexOption++) {

                    Object auxObj = Utils.showAndSelectOneWithMessage(controller.getAllVerticesByIndex(), "\nPlease choose one valid option of the list:");

                    if (auxObj == null) {
                        intermediateVertex = String.valueOf(36);
                    } else if (auxObj.equals(36)) {
                        intermediateVertex = String.valueOf(36);
                    } else {
                        intermediateVertex = String.valueOf(0);
                        intermediateVertexDTO = (VertexDTO) auxObj;
                    }

                    while (intermediateVertex.equals("36")) {
                        auxObj = Utils.showAndSelectOneWithMessage(controller.getAllVerticesByIndex(), "\nPlease choose one valid option of the list:");

                        if (auxObj == null) {
                            intermediateVertex = String.valueOf(36);
                        } else if (auxObj.equals(36)) {
                            intermediateVertex = String.valueOf(36);
                        } else {
                            intermediateVertex = String.valueOf(0);
                            intermediateVertexDTO = (VertexDTO) auxObj;
                        }
                        nextOption++;
                        if (nextOption == 3) {
                            controller.resetListIndex(0);
                            nextOption = 0;
                        }
                    }

                    controller.resetListIndex(0);

                    String typeOfVertex = controller.verifyVertex(desiredVertexDTO);

                    if (pathOption.equals("Land path")) {
                        if (typeOfVertex.equals("Port"))
                            throw new NullPointerException("\nPlease only enter Cities! Since you desire a land path, it is necessary to only CHOOSE CITIES!");
                    } else if (pathOption.equals("Maritime path")) {
                        if (typeOfVertex.equals("City"))
                            throw new NullPointerException("\nPlease only enter Ports! Since you desire a maritime path, it is necessary to only CHOOSE PORTS!");
                    }

                    optionVertices.add(intermediateVertexDTO);
                }

                optionFlag = true;

            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
                controller.resetListIndex(0);
                optionFlag = false;
            } catch (IllegalArgumentException ex) {
                System.out.println("\nPlease enter a number!! Dont enter a set of characters, spaces or any kind of symbol!");
                controller.resetListIndex(0);
                optionFlag = false;
            } catch (Exception ex) {
                System.out.println("\nThere are no possible paths between the departure local and arrival local! Please enter another path (Please verify the data!)\n");
                controller.resetListIndex(0);
                optionFlag = false;
            }
        } while (!optionFlag);

        VertexDTO beginVertex = desiredVertices.get(0);
        VertexDTO endVertexF = desiredVertices.get(1);

        try {
            if (optionVertices == null || optionVertices.isEmpty()) {
                System.out.println("There was no intermedia points, please insert intermedia points!!");
            } else {
                System.out.println(controller.getPath(pathOption, beginVertex, endVertexF, optionVertices));
            }
            System.out.println("\nOperation Success!\n");
        } catch (Exception e) {
            System.out.println("\nThere are no possible paths between the departure location and arrival location! Please enter another path! (Note: verify all possible paths before entering data again)\n");
            System.out.println("\nOperation Failed!\n");
            controller.resetListIndex(0);
        }
    }
}
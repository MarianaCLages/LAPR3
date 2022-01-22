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
                Object auxObj = Utils.showAndSelectOne(controller.constructMenuOptions(), "Please choose one of the 3 valid paths: ");
                if (auxObj == null) {
                    System.out.println("\nOperation stopped!\n");
                    return;
                } else {
                    pathOption = (String) auxObj;
                    optionFlag = true;
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                optionFlag = false;
            }
        } while (!optionFlag);

        do {
            try {
                for (int index = 0; index < 2; index++) {

                    Object auxObj;

                    if (!optionFirstVertex) {
                        auxObj = Utils.showAndSelectOneWithMessage(controller.getAllVertexesByIndex(), "Please choose the first vertex from the valid option of the list");
                        optionFirstVertex = true;
                    } else {
                        auxObj = Utils.showAndSelectOneWithMessage(controller.getAllVertexesByIndex(), "Please choose the second vertex from the valid option of the list");
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
                        auxObj = Utils.showAndSelectOneWithMessage(controller.getAllVertexesByIndex(), "Please choose one valid option of the list");

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
                //System.out.println("Please enter a number!! Dont enter a set of characters,space or any kind of symbol!");
                optionFlag = false;
                controller.resetListIndex(0);
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
                optionFlag = false;
                controller.resetListIndex(0);
            }

        } while (!optionFlag);

        controller.resetListIndex(0);

        do {
            try {
                numberVertexes = Utils.readIntegerFromConsole("Please enter the number of intermediate vertexes that you wish to have in the path: ");

                if (numberVertexes < 0 || numberVertexes > controller.graphMaxVertexes()) {
                    throw new NullPointerException("Please enter a valid number! You can not have negative vertexes has intermediate points or even more intermediate points than the graph max vertexes! (Graph max vertexes: " + (controller.graphMaxVertexes() - 2) + ")");
                }

                for (int indexOption = 0; indexOption < numberVertexes; indexOption++) {

                    Object auxObj = Utils.showAndSelectOneWithMessage(controller.getAllVertexesByIndex(), "Please choose one valid option of the list");

                    if (auxObj == null) {
                        intermediateVertex = String.valueOf(36);
                    } else if (auxObj.equals(36)) {
                        intermediateVertex = String.valueOf(36);
                    } else {
                        intermediateVertex = String.valueOf(0);
                        intermediateVertexDto = (VertexDto) auxObj;
                    }

                    while (intermediateVertex.equals("36")) {
                        auxObj = Utils.showAndSelectOneWithMessage(controller.getAllVertexesByIndex(), "Please choose one valid option of the list");

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
                    optionVertexes.add(intermediateVertexDto);
                }

                optionFlag = true;

            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
                optionFlag = false;
            } catch (IllegalArgumentException ex) {
                System.out.println("Please enter a number!! Dont enter a set of characters,space or any kind of symbol!");
                optionFlag = false;
            } catch (Exception ex) {
                ex.printStackTrace();
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("\nOperation Failed!\n");
        }

    }
}
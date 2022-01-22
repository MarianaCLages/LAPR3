package lapr.project.ui;



import lapr.project.controller.MostEfficientCircuitController;
import lapr.project.shared.graph.Vertex;

import java.util.LinkedList;

public class MostEfficientCircuitUI implements Runnable {


    public MostEfficientCircuitUI(){
        //Empty Constructor
        }

    @Override
    public void run(){

        String designation;
        boolean hasNumber;
        MostEfficientCircuitController mostEfficientCircuitController;

        do {
            hasNumber = false;
            mostEfficientCircuitController = new MostEfficientCircuitController();
            designation = Utils.readLineFromConsole("Which Place?");

            char [] chars = designation.toCharArray();

            for(char c : chars){

                if(Character.isDigit(c))
                    hasNumber = true;
            }

           if(hasNumber){
                System.out.println("Please do not insert numbers!");
            }

        }while (hasNumber);
        LinkedList <Vertex> ls = mostEfficientCircuitController.mostEfficientCircuit(designation);

        if(ls.getFirst() != null) {
            for (Vertex v : ls) {
                System.out.println(v + "\n");
            }
        }
        else {
            System.out.println("There isn't any valid course for that facility, or it doesn't exist in the graph.");
        }
    }
}

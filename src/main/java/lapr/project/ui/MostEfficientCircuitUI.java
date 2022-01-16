package lapr.project.ui;



import lapr.project.controller.MostEfficientCircuitController;
import lapr.project.shared.graph.MostEfficientCircuit;
import lapr.project.shared.graph.Vertex;

import java.util.LinkedList;

public class MostEfficientCircuitUI implements Runnable {


    public MostEfficientCircuitUI(){
        //Empty Constructor
        }

    @Override
    public void run(){

        MostEfficientCircuitController mostEfficientCircuitController = new MostEfficientCircuitController();
        String designation = Utils.readLineFromConsole("Which Place?");

        LinkedList <Vertex> ls = mostEfficientCircuitController.MostEfficientCircuitControllerEfficientCircuit(designation);

        for(Vertex v : ls){
            System.out.println(v + "\n");
        }
    }
}

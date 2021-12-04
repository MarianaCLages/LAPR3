package lapr.project.model.stores;

import lapr.project.model.Facility;
import lapr.project.model.Port;
import lapr.project.model.Position;
import lapr.project.model.Ship;
import lapr.project.shared.tree.TwoDTree;


import java.time.LocalDateTime;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class PortStore {

    private final TwoDTree portList;
    private ArrayList<Facility> tempList;

    /**
     * Constructor.
     */
    public PortStore() {
        portList = new TwoDTree();
        tempList = new ArrayList<>();
    }

    /**
     * Gets the port AVL.
     *
     * @return the port AVL
     */
    public TwoDTree getPortList() {
        return portList;
    }

    /**
     * Adds a new port to the list.
     *
     * @param port the port to be added
     * @return
     */
    public boolean add(Port port) {
        //save(App.getInstance().getDatabaseConnection(),port);
        return tempList.add(port);
        //portList.insert(port);
    }

    /**
     * Fills the port AVL.
     *
     * @return the port AVL filled
     */
    public boolean fillTree() {
        if (tempList.isEmpty()) return false;
        portList.insert(tempList.toArray(new Port[0]));
        /*for (Facility p : tempList) {
            portList.insert((Port) p);
        }*/
        System.out.println(portList.toString());
        return true;
    }

    /**
     * Gets the nearest port of a ship given a specific date.
     *
     * @param ship     the ship
     * @param dateTime the date
     * @return the nearest port of a ship given a specific date
     * @throws IllegalArgumentException
     */
    public Port getNearestNeighbourByTime(Ship ship, LocalDateTime dateTime) throws IllegalArgumentException {

        Position position = ship.getPosDate().getPosition(dateTime);

        return portList.nearestNeighborPort(position);
    }


    public Port getPortById(String id) {

        for(Facility f : tempList){
            if(f.getIdentification().equals(id)){
                return (Port) f;
            }
        }
        return null;
    }


}
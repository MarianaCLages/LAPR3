package lapr.project.model.stores;
import lapr.project.model.*;
import lapr.project.shared.MedianElement;
import lapr.project.shared.tree.TwoDTree;


import java.time.LocalDateTime;



import java.util.ArrayList;


public class PortStore {

    private final TwoDTree portList;
    private ArrayList<Facility> tempList;

    public PortStore() {
        portList = new TwoDTree();
        tempList = new ArrayList<>();
    }

    public void add(Port port) {
    //-    save(App.getInstance().getDatabaseConnection(),port);
        tempList.add(port);
        //portList.insert(port);
    }

    public boolean fillTree(){
        if (tempList.isEmpty()) return false;
        MedianElement median = new MedianElement(tempList.toArray(new Facility[0]));
        portList.insert((Port) median.median());
        for (Facility p:tempList) {
            portList.insert((Port) p);
        }
        return true;
    }

    public TwoDTree getPortList() {
        return portList;
    }

    public Port getNearestNeighbourByTime(Ship ship, LocalDateTime dateTime) throws IllegalArgumentException {

        Position position = ship.getPosDate().getPosition(dateTime);

        return portList.nearestNeighborPort(position);

    }

}
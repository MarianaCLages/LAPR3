package lapr.project.model.stores;
import lapr.project.model.*;
import lapr.project.shared.tree.TwoDTree;
import java.time.LocalDateTime;

public class PortStore {

    private final TwoDTree portList;

    public PortStore() {
        portList = new TwoDTree();
    }

    public void add(Port port) {
        portList.insert(port);
    }

    public TwoDTree getPortList() {
        return portList;
    }

    public Port getNearestNeighbourByTime(Ship ship,LocalDateTime dateTime) throws IllegalArgumentException {

        Position position = ship.getPosDate().getPosition(dateTime);

        return portList.nearestNeighborPort(position);

    }

}


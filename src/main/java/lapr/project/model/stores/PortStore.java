package lapr.project.model.stores;

import lapr.project.model.Port;
import lapr.project.shared.tree.TwoDTree;

public class PortStore {
    private final TwoDTree portList;

    public PortStore() {
        portList = new TwoDTree();
    }

    public void add(Port port) {
        portList.insert(port);
    }

    public TwoDTree getList() {
        return portList;
    }
}

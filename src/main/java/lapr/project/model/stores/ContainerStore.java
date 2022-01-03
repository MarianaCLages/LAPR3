package lapr.project.model.stores;

import lapr.project.model.Container;
import lapr.project.shared.tree.AVL;

public class ContainerStore {

    public AVL<Container> containerByAVL;

    /**
     * Constructor.
     */
    public ContainerStore() {
        containerByAVL = new AVL<>();
    }

    /**
     * Gets the container AVL.
     *
     * @return the container AVL
     */
    public AVL<Container> getContainerByAVL() {
        return containerByAVL;
    }

    /**
     * Adds a container in the AVL.
     *
     * @param container the container to be added
     */
    public void addContainer(Container container) {
        containerByAVL.insert(container);
    }

    /**
     * Writes all the containers.
     *
     * @return all the containers (in a String)
     */
    public boolean writeAllContainers() {

        if (containerByAVL.isEmpty()) {
            return false;
        }

        for (Container container : containerByAVL.inOrder()){
            //EMPTY
        }

            return true;
    }

    /**
     * Returns the textual description of the containers AVL info.
     *
     * @return the container AVL characteristics
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Container c : containerByAVL.inOrder()) {
            sb.append(c.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}

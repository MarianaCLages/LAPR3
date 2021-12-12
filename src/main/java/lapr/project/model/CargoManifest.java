package lapr.project.model;

import lapr.project.shared.tree.AVL;

import java.util.Date;

public class CargoManifest implements Comparable<CargoManifest> {

    String identification;
    private AVL<Container> offloaded;
    private AVL<Container> loaded;
    private Port port;
    private Date date;
    private Ship ship;
    private boolean inTransport;


    public CargoManifest(String identification, Port port, Ship ship, boolean inTransport) {

        this.identification = identification;
        this.port = port;
        offloaded = new AVL<>();
        loaded = new AVL<>();
        this.ship = ship;
        this.inTransport = inTransport;

    }

    /**
     * Constructor.
     *
     * @param identification the cargo manifest's identification
     * @param port           the cargo manifest's port
     * @param date           the cargo manifest's date
     */
    public CargoManifest(String identification, Port port, Date date) {
        this.identification = identification;
        this.port = port;
        offloaded = new AVL<>();
        loaded = new AVL<>();
        this.date = date;
        this.ship = null;
    }

    //Getters

    /**
     * Gets the cargo manifest's identification.
     *
     * @return the cargo manifest's identification
     */
    public String getIdentification() {
        return identification;
    }

    /**
     * Sets the cargo manifest's identification.
     *
     * @param identification the cargo manifest's identification
     */
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    /**
     * Gets the off loaded containers AVL.
     *
     * @return the off loaded containers AVL
     */
    public AVL<Container> getOffloaded() {
        return offloaded;
    }

    /**
     * gets the loaded containers AVL.
     *
     * @return the loaded containers AVL
     */
    public AVL<Container> getLoaded() {
        return loaded;
    }

    /**
     * Gets the cargo manifest's port.
     *
     * @return the cargo manifest's port
     */
    public Port getPort() {
        return port;
    }

    /**
     * Sets the cargo manifest's port.
     *
     * @param port the cargo manifest's port
     */
    public void setPort(Port port) {
        this.port = port;
    }

    public boolean getInTransport() {
        return inTransport;
    }

    //Setters

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * Gets the cargo manifest's date.
     *
     * @return the cargo manifest's date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the cargo manifest's date.
     *
     * @param date the cargo manifest's date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Counts the off loaded and loaded containers in each AVL.
     *
     * @return the amount of off loaded and loaded containers in each AVL
     */
    public int countContainers() {
        int count = 0;

        for (Container container : offloaded.inOrder()) {
            count = count + 1;
        }

        for (Container container : loaded.inOrder()) {
            count = count + 1;
        }

        return count;
    }

    /**
     * Prints the info of the off loaded containers.
     *
     * @return true if it succeeds, false if it doesn't (AVL is empty)
     */
    public boolean offLoadSign() {
        if (offloaded.isEmpty()) {
            return false;
        }

        for (Container container : offloaded.inOrder()) {
            //  System.out.println("Container identifier: " + container.getIdentification() + "; Type: " + container.getContainerType() + "; Position: " + port.getLocation() + "; Load: " + container.getPayload() + "\n");
        }
        return true;
    }

    /**
     * Prints the info of the loaded containers.
     *
     * @return true if it succeeds, false if it doesn't (AVL is empty)
     */
    public boolean loadSign() {
        if (loaded.isEmpty()) {
            return false;
        }

        for (Container container : loaded.inOrder()) {
            //System.out.println("Container identifier: " + container.getIdentification() + "; Type: " + container.getContainerType() + "; Load: " + container.getPayload() + "\n");
        }
        return true;
    }

    /**
     * Returns the textual description of the cargo manifest in the format: identification, port, date.
     *
     * @return the cargo manifest's characteristics
     */
    @Override
    public String toString() {
        return "CargoManifest{" +
                "identification='" + identification + '\'' +
                ", port=" + port +
                ", date=" + date +
                '}';
    }


    /**
     * Compares the identification of two different objects (CargoManifest).
     *
     * @param o the object to compare (CargoManifest)
     * @return 0 if they're equal, -1 or 1 if they're different
     */
    @Override
    public int compareTo(CargoManifest o) {
        return Integer.compare(Integer.parseInt(this.getIdentification()), Integer.parseInt(o.getIdentification()));
    }

    public boolean addContainersOffLoaded(Container container) {

        int x;
        int y;
        int z;
        int capacity = (int) ship.getCapacity() / 3;

        if ( ship.getContainerPositionAVL().isEmpty()) {
            ship.getContainerPositionAVL().insert(container.getPosition());
            offloaded.insert(container);
            return true;
        }


        for (ContainerPosition cp :  ship.getContainerPositionAVL().inOrder()) {

            for (z = 0; z < capacity; z++) {
                for (y = 0; y < capacity; y++) {
                    for (x = 0; x < capacity; x++) {

                        if (container.getPosition().compareTo(cp) != 0) {

                            try {
                                ship.getContainerPositionAVL().find(container.getPosition());
                            } catch (NullPointerException ex) {
                                offloaded.insert(container);
                                ship.getContainerPositionAVL().insert(container.getPosition());
                                return true;
                            }
                        }
                        container.getPosition().setxPos(x);
                    }

                    container.getPosition().setyPos(y);
                }
                container.getPosition().setzPos(z);
            }
        }
        return false;
    }




    public boolean addContainersLoaded(Container container) {
        int x;
        int y;
        int z;
        int capacity = (int) ship.getCapacity() / 3;


        if ( ship.getContainerPositionAVL().isEmpty()) {
            ship.getContainerPositionAVL().insert(container.getPosition());
            loaded.insert(container);
            return true;
        }


        for (ContainerPosition cp :  ship.getContainerPositionAVL().inOrder()) {

            for (z = 0; z < capacity; z++) {
                for (y = 0; y < capacity; y++) {
                    for (x = 0; x < capacity; x++) {

                        if (container.getPosition().compareTo(cp) != 0) {

                            try {
                                ship.getContainerPositionAVL().find(container.getPosition());
                            } catch (NullPointerException ex) {
                                loaded.insert(container);
                                ship.getContainerPositionAVL().insert(container.getPosition());
                                return true;
                            }
                        }
                        container.getPosition().setxPos(x);
                    }

                    container.getPosition().setyPos(y);
                }
                container.getPosition().setzPos(z);
            }
        }
        return false;
    }

}



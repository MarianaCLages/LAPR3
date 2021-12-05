package lapr.project.model;

import lapr.project.shared.tree.AVL;

import java.util.Date;
import java.util.NavigableMap;
import java.util.Objects;

public class CargoManifest implements Comparable<CargoManifest> {

    String identification;
    private AVL<Container> offloaded;
    private AVL<Container> loaded;
    private Port port;
    private Date date;

    //--------------- new parameters
    private Ship ship;
    private boolean inTransport;
    private NavigableMap<PositionXYZ, Container> ContainerMap;

    public CargoManifest(String identification, Port port, Ship ship, boolean inTransport) {

        this.identification = identification;
        this.port = port;
        //offLoaded = new AVL<>();
        loaded = new AVL<>();

        //--------------
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

    //--------------------- new methods
    public Boolean checkContainerInAVL(Container container) {
        return loaded.find(container) != null;
    }

    public boolean addContainerToAVL(Container container) {
        if (loaded.find(container).equals(null)) {
            loaded.insert(container);
            return true;
        }
        return false;
    }

    public boolean addContainerToMap(PositionXYZ rPosXYZ, Container rContainer) {
        if (ContainerMap.containsKey(rPosXYZ) && ContainerMap.containsValue(rContainer)) {
            return false;
        }
        ContainerMap.put(rPosXYZ, rContainer);
        return true;
    }

    public void refreshContainerMap() {
        for (PositionXYZ oPosXYZ : ContainerMap.navigableKeySet()) {

        }
    }

    public PositionXYZ getNextAvailablePositionXYZ() {
        PositionXYZ lPosXYZ = ContainerMap.lastKey();
        int X;
        int Y;
        int Z = -1;

        if (lPosXYZ.getFirst() < ship.getWidth()) {
            X = lPosXYZ.getFirst() + 1;
            Y = lPosXYZ.getSecond();
            Z = lPosXYZ.getThird();

        } else {
            X = 0;

            if (lPosXYZ.getSecond() < ship.getLength()) {
                Y = lPosXYZ.getSecond() + 1;
                Z = lPosXYZ.getThird();
            } else {
                Y = 0;

                if (lPosXYZ.getThird() < ship.getCapacity()) {
                    Z = lPosXYZ.getThird() + 1;
                }
            }
        }

        return new PositionXYZ(X, Y, Z);
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
     * Checks if two objects (CargoManifest) are equal.
     *
     * @param o the object
     * @return true if objects are equal, false if they aren't
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CargoManifest that = (CargoManifest) o;
        return Objects.equals(identification, that.identification) && Objects.equals(offloaded, that.offloaded) && Objects.equals(loaded, that.loaded) && Objects.equals(port, that.port);
    }

    /**
     * Generates a hash code for the cargo manifest values.
     *
     * @return the hash code for the cargo manifest values
     */
    @Override
    public int hashCode() {
        return Objects.hash(port, offloaded, loaded);
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

    public void addContainersOffLoaded(Container container) {
        offloaded.insert(container);
    }

    public void addContainersLoaded(Container container) {
        loaded.insert(container);
    }

}

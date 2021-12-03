package lapr.project.model;

import lapr.project.shared.tree.AVL;

import java.util.Date;
import java.util.Objects;

public class CargoManifest implements Comparable<CargoManifest> {

    String identification;
    private AVL<Container> offloaded;
    private AVL<Container> loaded;
    private Port port;
    private Date date;

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
     * Gets the cargo manifest's date.
     *
     * @return the cargo manifest's date
     */
    public Date getDate() {
        return date;
    }

    //Setters

    /**
     * Sets the cargo manifest's identification.
     *
     * @param identification the cargo manifest's identification
     */
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    /**
     * Sets the cargo manifest's port.
     *
     * @param port the cargo manifest's port
     */
    public void setPort(Port port) {
        this.port = port;
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
            System.out.println("Container identifier: " + container.getIdentification() + "; Type: " + container.getContainerType() + "; Position: " + port.getLocation() + "; Load: " + container.getPayload() + "\n");
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
            System.out.println("Container identifier: " + container.getIdentification() + "; Type: " + container.getContainerType() + "; Load: " + container.getPayload() + "\n");
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
     * Compares two cargo manifest objects.
     *
     * @param o the object (CargoManifest)
     * @return the result of the comparison (0 or 1)
     */
    @Override
    public int compareTo(CargoManifest o) {
        return Integer.compare(Integer.parseInt(this.getIdentification()), Integer.parseInt(o.getIdentification()));
    }
}

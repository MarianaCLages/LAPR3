package lapr.project.model;

import java.util.Objects;

public class Container implements Comparable<Container> {

    private String identification;
    private int payload;
    private int tare;
    private int gross;
    private String isoCode;
    private ContainerPosition position;
    private boolean isRefrigerated;
    private boolean toOffLoad;

    /**
     * Constructor.
     *
     * @param identification the container's identification
     * @param payload        the container's payload
     * @param tare           the container's tare
     * @param gross          the container's gross
     * @param isoCode        the container's ISO Code
     */
    public Container(String identification, int payload, int tare, int gross, String isoCode, boolean isRefrigerated, boolean toOffLoad) {
        this.identification = identification;
        this.payload = payload;
        this.tare = tare;
        this.gross = gross;
        this.isoCode = isoCode;
        this.isRefrigerated = isRefrigerated;
        this.toOffLoad = toOffLoad;
    }

    public Container(String identification, int payload, int tare, int gross, String isoCode){
        this.identification = identification;
        this.payload = payload;
        this.tare = tare;
        this.gross = gross;
        this.isoCode = isoCode;
    }

                     //Getters
    /**
     * Gets the container's identification.
     *
     * @return the container's identification
     */
    public String getIdentification() {
        return identification;
    }

    /**
     * Gets the container's payload.
     *
     * @return the container's payload
     */
    public int getPayload() {
        return payload;
    }

    /**
     * Gets the container's tare.
     *
     * @return the container's tare
     */
    public int getTare() {
        return tare;
    }

    /**
     * Gets the container's gross.
     *
     * @return the container's gross
     */
    public int getGross() {
        return gross;
    }

    /**
     * Gets the container's ISO Code.
     *
     * @return the container's ISO code
     */
    public String getIsoCode() {
        return isoCode;
    }

    /**
     * Gets the container's position.
     *
     * @return the container's position
     */
    public ContainerPosition getPosition() {
        return position;
    }

    public boolean getOffLoad(){
        return toOffLoad;
    }

    public boolean getIsRefrigerated() {
        return isRefrigerated;
    }

    public String getContainerType() {
        if (isRefrigerated) {
            return "Refrigerated";
        } else {
            return "Not refrigerated";
        }
    }

    //Setters
    /**
     * Sets the container's identification.
     *
     * @param identification the container's identification
     */
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    /**
     * Sets the container's payload.
     *
     * @param payload the container's payload
     */
    public void setPayload(int payload) {
        this.payload = payload;
    }

    /**
     * Sets the container's tare.
     *
     * @param tare the container's tare
     */
    public void setTare(int tare) {
        this.tare = tare;
    }

    /**
     * Sets the container's gross.
     *
     * @param gross the container's gross
     */
    public void setGross(int gross) {
        this.gross = gross;
    }

    /**
     * Sets the container's ISO Code.
     *
     * @param isoCode the container's ISO Code
     */
    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    /**
     * Sets the container's position.
     *
     * @param position the container's position
     */
    public void setPosition(ContainerPosition position) {
        this.position = position;
    }

    /**
     * Returns the textual description of the container info in the format: identification, payload, tare, gross, ISO code, position.
     *
     * @return the container's characteristics
     */
    @Override
    public String toString() {
        return "Container{" +
                "identification='" + identification + '\'' +
                ", payload=" + payload +
                ", tare=" + tare +
                ", gross=" + gross +
                ", isoCode='" + isoCode + '\'' +
                ", position=" + position +
                '}';
    }

    /**
     * Checks if two objects (Container) are equal.
     *
     * @param o the object
     * @return true if objects are equal, false if they aren't
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container container = (Container) o;
        return payload == container.payload && tare == container.tare && gross == container.gross && Objects.equals(identification, container.identification) && Objects.equals(isoCode, container.isoCode) && Objects.equals(position, container.position);
    }

    /**
     * Generates a hash code for the container values.
     *
     * @return the hash code for the container values
     */
    @Override
    public int hashCode() {
        return Objects.hash(identification, payload, tare, gross, isoCode, position);
    }

    @Override
    public int compareTo(Container o) {
        return Integer.compare(Integer.parseInt(this.getIdentification()), Integer.parseInt(o.getIdentification()));
    }
}

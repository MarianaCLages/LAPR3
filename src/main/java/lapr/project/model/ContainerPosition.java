package lapr.project.model;

public class ContainerPosition {

    public int xPos;
    public int yPos;
    public int zPos;

    /**
     * Constructor.
     *
     * @param xPos the container position's x coordinate
     * @param yPos the container position's y coordinate
     * @param zPos the container position's z coordinate
     */
    public ContainerPosition(int xPos, int yPos, int zPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
    }

    //Getters

    /**
     * Gets the container position's x coordinate.
     *
     * @return the container position's x coordinate
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * Sets the container position's x coordinate.
     *
     * @param xPos the container position's x coordinate
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * Gets the container position's y coordinate.
     *
     * @return the container position's y coordinate
     */
    public int getyPos() {
        return yPos;
    }

    //Setters

    /**
     * Sets the container position's y coordinate.
     *
     * @param yPos the container position's y coordinate
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * Gets the container position's z coordinate.
     *
     * @return the container position's z coordinate
     */
    public int getzPos() {
        return zPos;
    }

    /**
     * Sets the container position's z coordinate.
     *
     * @param zPos the container position's z coordinate
     */
    public void setzPos(int zPos) {
        this.zPos = zPos;
    }
}

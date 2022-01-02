package lapr.project.model;

public class ContainerPosition implements Comparable<ContainerPosition> {

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

    /**
     * Compares two Container Position objects.
     *
     * @param containerPosition the container position
     * @return 0 if they're equal, -1 or 1 if they're different
     */
    @Override
    public int compareTo(ContainerPosition containerPosition) {

        int dec1 = Integer.compare(this.xPos, containerPosition.xPos);

        if (dec1 == 0) {

            int dec2 = Integer.compare(this.yPos, containerPosition.yPos);

            if (dec2 == 0) {
                int dec3 = Integer.compare(this.zPos, containerPosition.zPos);

                return dec3;
            }
            return dec2;
        }
        return dec1;
    }

    /**
     * Returns the textual description of the container position in the format: xPos, yPos, zPos.
     *
     * @return the container position's characteristics
     */
    @Override
    public String toString() {
        return "ContainerPosition{" +
                "xPos=" + xPos +
                ", yPos=" + yPos +
                ", zPos=" + zPos +
                '}';
    }
}

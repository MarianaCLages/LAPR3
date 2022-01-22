package lapr.project.model;

public class ISODimentions {
    private int width;
    private int length;
    private int height;

    /**
     * Constructor.
     *
     * @param width  the width in the ISO Code
     * @param length the length in the ISO Code
     * @param height the height in the ISO Code
     */
    public ISODimentions(int width, int length, int height) {
        this.width = width;
        this.length = length;
        this.height = height;
    }

    /**
     * Gets the width in the ISO Code
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width in the ISO Code
     *
     * @param width the width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the length in the ISO Code
     *
     * @return the length in the ISO Code
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets the length in the ISO Code
     *
     * @param length the length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Gets the height in the ISO Code
     *
     * @return the height in the ISO Code
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height in the ISO Code
     *
     * @param height the height
     */
    public void setHeight(int height) {
        this.height = height;
    }
}

package lapr.project.model;

public class ISODimentions {
    private int width;
    private int length;
    private int height;

    public ISODimentions(int width, int length, int height) {
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

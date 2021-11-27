package lapr.project.model;

public class ContainerPosition {

    public int xPos;
    public int yPos;
    public int zPos;

    public ContainerPosition(int xPos, int yPos, int zPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
    }

    //Getters
    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getzPos() {
        return zPos;
    }

    //Setters
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setzPos(int zPos) {
        this.zPos = zPos;
    }
}

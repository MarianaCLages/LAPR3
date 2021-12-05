package lapr.project.model;

class PositionXYZ {
    private int X;
    private int Y;
    private int Z;

    public PositionXYZ(int first, int second, int third) {
        this.X = first;
        this.Y = second;
        this.Z = third;
    }

    public int getFirst() {
        return X;
    }

    public int getSecond() {
        return Y;
    }

    public int getThird() {
        return Z;
    }

    public boolean check() {
        if (Z == -1) {
            return false;
        }
        return true;
    }
}


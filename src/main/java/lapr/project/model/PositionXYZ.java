package lapr.project.model;

class PositionXYZ <X, Y, Z> {
    private int X;
    private int Y;
    private int Z;

    public PositionXYZ(X first, Y second, Z third) {
        this.X = (int) first;
        this.Y = (int) second;
        this.Z = (int) third;
    }

    public int getFirst() { return X; }
    public int getSecond() { return Y; }
    public int getThird() { return Z; }

    public boolean check(){
        if (Z == -1){
            return false;
        }
        return true;
    }
}


package lapr.project.model;


import java.util.*;

public class Ship implements Comparable<Ship> {

    //dados estaticos
    int mmsi;
    String name;
    int imo;
    int numGen;
    long genPowerOutput;
    String callSign;
    String vesselType;
    double length;
    double width;
    double capacity;
    double draft;

    //dados dinamicos
    Map<java.util.Date, Position> posDate; // DateTime is the key, which will save the specific position of its time.
    List<Position> Date;
    BinarySearchTree<Position> binaryTreePosition;

    public Ship(int mmsi, String name, int imo, int numGen, long genPowerOutput, String callSign, String vesselType, double length, double width, double capacity, double draft) {
        checkIMO(imo);
        checkMMSI(mmsi);

        this.mmsi = mmsi;
        this.name = name;
        this.imo = imo;
        this.numGen = numGen;
        this.genPowerOutput = genPowerOutput;
        this.callSign = callSign;
        this.vesselType = vesselType;
        this.length = length;
        this.width = width;
        this.capacity = capacity;
        this.draft = draft;

        this.posDate = new HashMap<>();
        Date = new ArrayList<>();
        binaryTreePosition = new BinarySearchTree<>();
    }

    //Getters
    public int getMmsi() {
        return mmsi;
    }

    public String getName() {
        return name;
    }

    public int getImo() {
        return imo;
    }

    public int getNumGen() {
        return numGen;
    }

    public String getCallSign() {
        return callSign;
    }

    public String getVesselType() {
        return vesselType;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getDraft() {
        return draft;
    }

    public long getGenPowerOutput() {
        return genPowerOutput;
    }

    public Map<Date, Position> getPosDate() {
        return posDate;
    }


    public List<Position> getDate() {
        return Date;
    }

    public BinarySearchTree<Position> getBinaryTreePosition() {
        return binaryTreePosition;
    }


    //Setter

    public void setDate(List<Position> date) {
        Date = date;
    }

    public void setMmsi(int mmsi) {
        this.mmsi = mmsi;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImo(int imo) {
        this.imo = imo;
    }

    public void setNumGen(int numGen) {
        this.numGen = numGen;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public void setVesselType(String vesselType) {
        this.vesselType = vesselType;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public void setDraft(long draft) {
        this.draft = draft;
    }

    public void setGenPowerOutput(long genPowerOutput) {
        this.genPowerOutput = genPowerOutput;
    }


    //Checks
    public boolean checkMMSI(int mmsi) {
        if (mmsi < 100000000 || mmsi > 999999999) {
            throw new IllegalArgumentException("MMSI code must have 9 digits!");
        }
        return true;
    }

    public boolean checkIMO(int imo) {
        if (imo < 1000000 || imo > 9999999) {
            throw new IllegalArgumentException("IMO code must have 7 digits!");
        }
        return true;
    }

    public String writeAllPos() {

        String positionalMessage = "Positional Messages:";

        Iterable<Position> itpos = binaryTreePosition.inOrder();
        List<Position> posList = new ArrayList<>();
        itpos.iterator().forEachRemaining(posList::add);


        for (Position pos : posList) {
            positionalMessage = positionalMessage + "\n" + pos.getDateHour() + ": " + pos.toString();
        }

        return positionalMessage;
    }


    public boolean addPosition(Position pos) {

        if (pos == null) return false;
        else {
            this.binaryTreePosition.insert(pos);
            return true;
        }
    }

    public Position getPositionByLocalDateTime(Date dt) {

        Iterable<Position> itpos = binaryTreePosition.inOrder();

        List<Position> posList = new ArrayList<>();
        itpos.iterator().forEachRemaining(posList::add);

        for (Position pos : posList) {
            if (pos.getDateHour().equals(dt)) return pos;
        }

        return null;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "mmsi=" + mmsi +
                ", name='" + name + '\'' +
                ", imo=" + imo +
                ", numGen=" + numGen +
                ", genPowerOutput=" + genPowerOutput +
                ", callSign='" + callSign + '\'' +
                ", vesselType='" + vesselType + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", capacity=" + capacity +
                ", draft=" + draft +
                ", posDate=" + posDate +
                '}';
    }

    @Override
    public int compareTo(Ship o) {

        if(this.mmsi > o.getMmsi()) return 1;
        else if(this.mmsi < o.getMmsi()) return -1;
        else return 0;

    } //Falta implementar isto
}
package lapr.project.model;

import lapr.project.model.stores.PositionTree;
import lapr.project.shared.DistanceCalculation;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

public class Ship implements Comparable<Ship> {


    //dados dinamicos
    private PositionTree posDate;
    //dados estaticos
    private char transcieverClass;
    private String cargo;
    private int mmsi;
    private String name;
    private String imo;
    private int numGen;
    private long genPowerOutput;
    private String callSign;
    private String vesselType;
    private double length;
    private double width;
    private double capacity;
    private double draft;

    /**
     * Constructor.
     *
     * @param mmsi         the ship's MMSI
     * @param name         the ship's name
     * @param imo          the ship's international identification number
     * @param callSign     the ship's call sign
     * @param vesselType   the ship's type
     * @param length       the ship's length
     * @param width        the ship's width
     * @param draft        the ship's draft
     * @param cargo        the ship's cargo
     * @param transcieverClass the ship's transciever class
     * */

    public Ship(int mmsi, String name, String imo, String callSign, String vesselType, double length, double width, double draft, String cargo, char transcieverClass) {
        checkIMO(imo);
        checkMMSI(mmsi);

        this.mmsi = mmsi;
        this.name = name;
        this.imo = imo;
        this.callSign = callSign;
        this.vesselType = vesselType;
        this.length = length;
        this.width = width;
        this.draft = draft;
        this.cargo = cargo;
        this.transcieverClass = transcieverClass;

        this.posDate = new PositionTree();
    }


    /**
     * Constructor.
     *
     * @param mmsi         the ship's identification
     * @param name         the ship's name
     * @param imo          the ship's international identification number
     * @param callSign     the ship's call sign
     * @param vesselType   the ship's type
     * @param length       the ship's length
     * @param width        the ship's width
     * @param draft        the ship's draft
     * */

    public Ship(int mmsi, String name, String imo, int numGen, long genPowerOutput, String callSign, String vesselType, double length, double width, double capacity, double draft) {
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

        this.posDate = new PositionTree();
    }

    /**
     * Constructor.
     *
     * @param mmsi         the ship's identification
     * **/

    public Ship(int mmsi) {
        checkMMSI(mmsi);
        this.mmsi = mmsi;
    }

    /**
     * creates a new position.
     *
     * @return a new position**/

    public Position createPosition(LocalDateTime time, double latitude, double longitude, double heading, double sog, double cog) {
        return new Position(latitude, longitude, heading, sog, cog, time);
    }

    /**
     * inserts a new position to the ship.
     * **/
    public void insertPosition(Position position) {
        posDate.addPosition(position);
    }

    /**
     * gets the ship's identification.
     * @return ship's identification
     * **/
    public int getMmsi() {
        return mmsi;
    }

    /**
     * sets ship's identification.**/
    //Setters
    public void setMmsi(int mmsi) {
        this.mmsi = mmsi;
    }

    /**
     * gets name.
     * @return name**/
    public String getName() {
        return name;
    }

    /**
     * sets name.
     * **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets internation identification number.
     * @return the internation identification number**/
    public String getImo() {
        return imo;
    }

    /**
     * sets internation identification numbe.r**/
    public void setImo(String imo) {
        this.imo = imo;
    }

    /**
     * gets number Generator.
     * @return the number Generator **/
    public int getNumGen() {
        return numGen;
    }

    /**
     * sets number Generator.**/
    public void setNumGen(int numGen) {
        this.numGen = numGen;
    }

    /**
     * gets call sign.
     * @return the call sign**/
    public String getCallSign() {
        return callSign;
    }

    /**
     * sets call sign.
     * **/
    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    /**
     * gets the vessel type.
     * @return the vessel type
     * **/
    public String getVesselType() {
        return vesselType;
    }

    /**
     * sets the vessel type.**/
    public void setVesselType(String vesselType) {
        this.vesselType = vesselType;
    }

    /**
     * gets length.
     * @return the length**/
    public double getLength() {
        return length;
    }

    /**
     * sets length.
     **/
    public void setLength(long length) {
        this.length = length;
    }

    /**
     * gets width.
     * @eturn width **/
    public double getWidth() {
        return width;
    }

    /**
     * sets width.**/
    public void setWidth(long width) {
        this.width = width;
    }

    /**
     * gets capacity.
     * @return capacity**/
    public double getCapacity() {
        return capacity;
    }

    /**
     * sets capacity.
     * @return capacity **/
    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    /**
     * gets the position date.
     * @return the position date**/
    public PositionTree getPosDate() {
        return posDate;
    }

    /**
     * gets the draft.
     * @return the draft**/
    public double getDraft() {
        return draft;
    }

    /**
     * sets the draft.**/
    public void setDraft(long draft) {
        this.draft = draft;
    }

    /**
     * gets the generated power output.
     * @return the generated power output**/
    public long getGenPowerOutput() {
        return genPowerOutput;
    }

    /**
     * sets the generated power output.**/
    public void setGenPowerOutput(long genPowerOutput) {
        this.genPowerOutput = genPowerOutput;
    }

    /**
     * checks if the identification has 9 digits.**/
    //Checks
    public boolean checkMMSI(int mmsi) {
        if (mmsi > 99999999 && mmsi < 1000000000) {
            return true;
        }
        throw new IllegalArgumentException("MMSI code must have 9 digits!");
    }

    /**
     * checks if the ship's international identification number has 7 digits.
     * @return if true of false**/
    public boolean checkIMO(String imo) {
        if (imo.length() != 10 || (!imo.startsWith("IMO") && StringUtils.isNumeric(imo.substring(2, imo.length() - 1)))) {
            throw new IllegalArgumentException("IMO code must have 7 digits!");
        } else return true;
    }

    /**
     * return a String with all the positions of a ship on a certain period of time.*/

    public String writeAllPos(LocalDateTime di, LocalDateTime df) {

        String positionalMessage = "Positional Message:";

        if (di == null || df == null) return positionalMessage;

        Date initiald = java.sql.Timestamp.valueOf(di);
        Date finald = java.sql.Timestamp.valueOf(df);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initiald);

        calendar.add(Calendar.SECOND, -1);
        initiald = calendar.getTime();


        double d = 0;
        List<Position> positionList = new ArrayList<>();


        PositionTree binaryTest = this.getPosDate();
        Iterable<Position> posIterable = binaryTest.getInOrderList();
        Iterator<Position> posIterator = posIterable.iterator();


        while (!initiald.after(finald)) {


            while (posIterator.hasNext()) {


                Position pos = posIterator.next();
                Date posDate = java.sql.Timestamp.valueOf(pos.getDate());

                if (!posDate.before(initiald) && !posDate.after(initiald)) {
                    positionList.add(pos);
                }

            }

            posIterator = posIterable.iterator();

            calendar.add(Calendar.SECOND, 1);
            initiald = calendar.getTime();
        }


        if (positionList.isEmpty()) return positionalMessage;

        for (Position pos : positionList) {
            positionalMessage = positionalMessage + "\n" + pos.toString();
        }


        return positionalMessage;
    }

    /**
     * compares two ships by their mmsi
     * @return if one is greater than the other, or if it's smaller,or if they're equal **/
    @Override
    public int compareTo(Ship o) {
        if (mmsi > o.mmsi) {
            return 1;
        } else if (mmsi < o.mmsi) {
            return -1;
        }
        return 0;
    }

    /**
     * compares if two ships are equal
     * @return true if they are equal, false if not**/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ship)) return false;
        Ship ship = (Ship) o;
        return getMmsi() == ship.getMmsi() && Objects.equals(getImo(), ship.getImo()) && getNumGen() == ship.getNumGen() && getGenPowerOutput() == ship.getGenPowerOutput() && getLength() == ship.getLength() && getWidth() == ship.getWidth() && getCapacity() == ship.getCapacity() && getDraft() == ship.getDraft() && Objects.equals(getName(), ship.getName()) && Objects.equals(getCallSign(), ship.getCallSign()) && Objects.equals(getVesselType(), ship.getVesselType());
    }

    /**
     * returns the hash code.
     * @return the hash code**/
    @Override
    public int hashCode() {
        return Objects.hash(getMmsi(), getName(), getImo(), getNumGen(), getGenPowerOutput(), getCallSign(), getVesselType(), getLength(), getWidth(), getCapacity(), getDraft());
    }

    /**
     * gets the travelled distance.
     * @return the travelled distance.**/
    public double getTravelledDistance() {
        double travelledDistance = 0;

        for (int i = 0; i < this.getPosDate().getInOrderList().size() - 1; i++) {
            travelledDistance += DistanceCalculation.distanceTo(this.getPosDate().getInOrderList().get(i), this.getPosDate().getInOrderList().get(i + 1));
        }
        return travelledDistance;
    }

    /**
     * gets the delta distance.
     * @return the delta distance**/
    public double getDeltaDistance() {
        return DistanceCalculation.distanceTo(this.getPosDate().getSmallestPosition(), this.getPosDate().getBiggestPosition());
    }

    /**
     * gets the total number of movements.
     * @return the total number of movements.**/
    public int getTotalNumberOfMovements() {
        return this.getPosDate().getSize();
    }

    /**
     * Returns the textual description of the ship in the format: cargo, mmsi, name, imo, number of generators, generated power output, call sign, vessel type, length, width, capacity, draft**/
    @Override
    public String toString() {
        return "Ship{" +
                "cargo='" + cargo + '\'' +
                ", mmsi=" + mmsi +
                ", name='" + name + '\'' +
                ", imo='" + imo + '\'' +
                ", numGen=" + numGen +
                ", genPowerOutput=" + genPowerOutput +
                ", callSign='" + callSign + '\'' +
                ", vesselType='" + vesselType + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", capacity=" + capacity +
                ", draft=" + draft +
                '}';
    }
}


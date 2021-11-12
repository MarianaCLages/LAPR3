package lapr.project.model;

import lapr.project.model.stores.PositionTree;
import lapr.project.shared.DistanceCalculation;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

public class Ship implements Comparable<Ship> {
    private PositionTree posDate;
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
     * @param mmsi             the ship's MMSI
     * @param name             the ship's name
     * @param imo              the ship's IMO
     * @param callSign         the ship's call sign
     * @param vesselType       the ship's vessel type
     * @param length           the ship's length
     * @param width            the ship's width
     * @param draft            the ship's draft
     * @param cargo            the ship's cargo
     * @param transceiverClass the ship's transceiver class
     */
    public Ship(int mmsi, String name, String imo, String callSign, String vesselType, double length, double width, double draft, String cargo, char transceiverClass) {
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

        this.posDate = new PositionTree();
    }

    /**
     * Constructor.
     *
     * @param mmsi           the ship's MMSI
     * @param name           the ship's name
     * @param imo            the ship's IMO
     * @param numGen         the ship's number of energy generators
     * @param genPowerOutput the generator's power output
     * @param callSign       the ship's call sign
     * @param vesselType     the ship's vessel type
     * @param length         the ship's length
     * @param width          the ship's width
     * @param capacity       the ship's capacity
     * @param draft          the ship's draft
     */
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
     * @param mmsi the ship's MMSI
     **/
    public Ship(int mmsi) {
        checkMMSI(mmsi);
        this.mmsi = mmsi;
    }

    /**
     * Creates a new position.
     *
     * @return the new position created
     **/
    public Position createPosition(LocalDateTime time, double latitude, double longitude, double heading, double sog, double cog) {
        return new Position(latitude, longitude, heading, sog, cog, time);
    }

    //Getters

    /**
     * Gets the ship's MMSI.
     *
     * @return the ship's MMSI
     **/
    public int getMmsi() {
        return mmsi;
    }

    /**
     * Gets the ship's name.
     *
     * @return the ship's name
     **/
    public String getName() {
        return name;
    }

    /**
     * Gets the ship's IMO.
     *
     * @return the ship's IMO
     **/
    public String getImo() {
        return imo;
    }

    /**
     * Gets the ship's number of energy generators
     *
     * @return the ship's number of energy generators
     **/
    public int getNumGen() {
        return numGen;
    }

    /**
     * Gets the ship's call sign.
     *
     * @return the ship's call sign
     **/
    public String getCallSign() {
        return callSign;
    }

    /**
     * Gets the ship's vessel type.
     *
     * @return the ship's vessel type
     **/
    public String getVesselType() {
        return vesselType;
    }

    /**
     * Gets the ship's length.
     *
     * @return the ship's length
     **/
    public double getLength() {
        return length;
    }

    /**
     * Gets the ship's width.
     *
     * @return the ship's width
     **/
    public double getWidth() {
        return width;
    }

    /**
     * Gets the ship's capacity.
     *
     * @return the ship's capacity
     **/
    public double getCapacity() {
        return capacity;
    }

    /**
     * Gets the position date.
     *
     * @return the position date
     **/
    public PositionTree getPosDate() {
        return posDate;
    }

    /**
     * Gets the ship's draft.
     *
     * @return the ship's draft
     **/
    public double getDraft() {
        return draft;
    }

    /**
     * Gets the generator's power output.
     *
     * @return the generator's power output
     **/
    public long getGenPowerOutput() {
        return genPowerOutput;
    }

    //Setters

    /**
     * Sets the ship's MMSI.
     *
     * @param mmsi the ship's MMSI
     */
    public void setMmsi(int mmsi) {
        this.mmsi = mmsi;
    }

    /**
     * Sets the ship's name.
     *
     * @param name the ship's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the ship's IMO.
     *
     * @param imo the ship's IMO
     */
    public void setImo(String imo) {
        this.imo = imo;
    }

    /**
     * Sets the ship's number of energy generators
     *
     * @param numGen the ship's number of energy generators
     */
    public void setNumGen(int numGen) {
        this.numGen = numGen;
    }

    /**
     * Sets the ship's call sign.
     *
     * @param callSign the ship's call sign
     */
    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    /**
     * Sets the ship's vessel type.
     *
     * @param vesselType the ship's vessel type
     */
    public void setVesselType(String vesselType) {
        this.vesselType = vesselType;
    }

    /**
     * Sets the ship's length.
     *
     * @param length the ship's length
     */
    public void setLength(long length) {
        this.length = length;
    }

    /**
     * Sets the ship's width.
     *
     * @param width the ship's width
     */
    public void setWidth(long width) {
        this.width = width;
    }

    /**
     * Sets the ship's capacity.
     *
     * @param capacity the ship's capacity
     */
    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    /**
     * Sets the ship's draft.
     *
     * @param draft the ship's draft
     */
    public void setDraft(long draft) {
        this.draft = draft;
    }

    /**
     * Sets the generator's power output.
     *
     * @param genPowerOutput the generator's power output
     */
    public void setGenPowerOutput(long genPowerOutput) {
        this.genPowerOutput = genPowerOutput;
    }

    //Checks

    /**
     * Checks the ship's MMSI
     *
     * @param mmsi the ship's MMSI
     * @return true if it is valid, false if it isn't
     */
    public boolean checkMMSI(int mmsi) {
        if (mmsi > 99999999 && mmsi < 1000000000) {
            return true;
        }
        throw new IllegalArgumentException("MMSI code must have 9 digits!");
    }

    /**
     * Checks the ship's IMO.
     *
     * @param imo the ship's IMO
     * @return true if it valid, false if it isn't
     */
    public boolean checkIMO(String imo) {
        if (imo.length() != 10 || (!imo.startsWith("IMO") && StringUtils.isNumeric(imo.substring(2, imo.length() - 1)))) {
            throw new IllegalArgumentException("IMO code must have 7 digits!");
        } else {
            return true;
        }
    }

    /**
     * Inserts a position in the tree.
     *
     * @param position the position to be added
     */
    public void insertPosition(Position position) {
        posDate.addPosition(position);
    }

    /**
     * Writes all the positional messages in a period of time.
     *
     * @param di the initial date
     * @param df the final date
     * @return the positional messages in a period of time
     */
    public String writeAllPos(LocalDateTime di, LocalDateTime df) {

        String positionalMessage = "Positional Message:";

        if (di == null || df == null) return positionalMessage;

        Date initiald = java.sql.Timestamp.valueOf(di);
        Date finald = java.sql.Timestamp.valueOf(df);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initiald);

        calendar.add(Calendar.SECOND, -1);
        initiald = calendar.getTime();

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

        if (positionList.isEmpty()) {
            return positionalMessage;
        }

        for (Position pos : positionList) {
            positionalMessage = positionalMessage + "\n" + pos.toString();
        }
        return positionalMessage;
    }

    /**
     * Gets the ship's travelled distance.
     *
     * @return the ship's travelled distance
     **/
    public double getTravelledDistance() {
        double travelledDistance = 0;

        for (int i = 0; i < this.getPosDate().getInOrderList().size() - 1; i++) {
            travelledDistance += DistanceCalculation.distanceTo(this.getPosDate().getInOrderList().get(i), this.getPosDate().getInOrderList().get(i + 1));
        }
        return travelledDistance;
    }

    /**
     * Gets the ship's delta distance.
     *
     * @return the ship's delta distance
     **/
    public double getDeltaDistance() {
        return DistanceCalculation.distanceTo(this.getPosDate().getSmallestPosition(), this.getPosDate().getBiggestPosition());
    }

    /**
     * Gets the ship's total number of movements.
     *
     * @return the ship's total number of movements
     **/
    public int getTotalNumberOfMovements() {
        return this.getPosDate().getSize();
    }

    /**
     * Compares the MMSI of two different objects (Ship).
     *
     * @param o the object to compare (Ship)
     * @return 0 if they're equal, -1 or 1 if they're different
     */
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
     * Checks if two objects (Ship) are equal.
     *
     * @param o the object
     * @return true if objects are equal, false if they aren't
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ship)) return false;
        Ship ship = (Ship) o;
        return getMmsi() == ship.getMmsi() && Objects.equals(getImo(), ship.getImo()) && getNumGen() == ship.getNumGen() && getGenPowerOutput() == ship.getGenPowerOutput() && getLength() == ship.getLength() && getWidth() == ship.getWidth() && getCapacity() == ship.getCapacity() && getDraft() == ship.getDraft() && Objects.equals(getName(), ship.getName()) && Objects.equals(getCallSign(), ship.getCallSign()) && Objects.equals(getVesselType(), ship.getVesselType());
    }

    /**
     * Generates a hash code for the ship values.
     *
     * @return the hash code for the ship values
     */
    @Override
    public int hashCode() {
        return Objects.hash(getMmsi(), getName(), getImo(), getNumGen(), getGenPowerOutput(), getCallSign(), getVesselType(), getLength(), getWidth(), getCapacity(), getDraft());
    }

    /**
     * Returns the textual description of the ship in the format: MMSI, name, IMO, number of energy generators, generator's power output,
     * call sign, vessel type, length, width, capacity, draft.
     *
     * @return the ship's characteristics
     */
    @Override
    public String toString() {
        return "Ship{" +
                "cargo='" + cargo + '\'' +
                ", MMSI=" + mmsi +
                ", name='" + name + '\'' +
                ", IMO='" + imo + '\'' +
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
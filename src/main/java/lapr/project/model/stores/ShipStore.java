package lapr.project.model.stores;


import lapr.project.model.BinarySearchTree;
import lapr.project.model.Position;
import lapr.project.model.Ship;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShipStore {

    public BinarySearchTree<Ship> shipBinarySearchTree;

    public ShipStore() {
        this.shipBinarySearchTree = new BinarySearchTree<>();
    }

    public Ship createShip(int mmsi, String name, String imo, String callSign, String vesselType, double length, double width, double draft, String cargo, char transceiverClass) {
        return new Ship(mmsi, name, imo, callSign, vesselType, length, width, draft, cargo, transceiverClass);
    }

    public boolean addShip(Ship ship) {
        try {
            shipBinarySearchTree.insert(ship);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean existsShip(int mmsi) {
        try {
            findShip(mmsi);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Ship findShip(int mmsi) {
        Ship ship = new Ship(mmsi);
        return shipBinarySearchTree.find(ship);

    }


    public boolean writeAllShips() {

        if (shipBinarySearchTree.isEmpty()) return false;

        for (Ship s : shipBinarySearchTree.inOrder()) {
            System.out.println(s);
        }

        return true;
    }

    public Ship getShipByMMSI(int mmsi) {

        return findShip(mmsi);
    }

    public List<Integer> getShipListMmsi() {
        List<Integer> shipListMmsi = new ArrayList<>();
        for (Ship ship : shipBinarySearchTree.inOrder()) {
            shipListMmsi.add(ship.getMmsi());
        }
        return shipListMmsi;
    }

    public List<String> getShipListPos() {
        List<String> shipListPos = new ArrayList<>();
        for (Ship ship : shipBinarySearchTree.inOrder()) {
            shipListPos.add(ship.writeAllPos());
        }
        return shipListPos;
    }

    public String getShipSummary(int mmsi) {

        String returnString;

        try {

            StringBuilder sb = new StringBuilder();

            for (Ship s : shipBinarySearchTree.inOrder()) {

                if (mmsi == s.getMmsi()) {

                    sb
                            .append("SHIP SUMMARY :[MMSI : " + s.getMmsi())
                            .append(",Vessel name: " + s.getVesselType())
                            .append(",Start Base  Date Time: " + getFirstDate(s))
                            .append(",End base date time : " + getLastDate(s))
                            .append(",Total movement time: " + differenceBetweenDates(getFirstDate(s), getLastDate(s)))
                            .append(",Total number of movements : " + getTotalNumberOfMovements(s))
                            .append(",Max SOG : " + getMaxSOG(s))
                            .append(",Mean SOG : " + getMeanSOG(s))
                            .append(",Max COG : " + getMaxCOG(s))
                            .append(",Mean COG : " + getMeanCOG(s))
                            .append(",Departure Latitude : " + getDepartureLatitude(s))
                            .append(",Departure Longitude : " + getDepartureLongitude(s))
                            .append(",Arrival Latitude : " + getArrivalLatitude(s))
                            .append(",Arrival Longitude : " + getArrivalLongitude(s))
                            .append(",Travelled Distance : Travelled Distance")
                            .append(",Delta Distance : Delta Distance");

                }
            }

            returnString = sb.toString();

            if (returnString == null || returnString.isEmpty())
                throw new IllegalArgumentException("Invalid Ship, please enter another one");
            else return returnString;

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage()); //Depois fazer um logger
            return null;
        }
    }

    private LocalDateTime getFirstDate(Ship s) {
        return s.getPosDate().getSmallestPosition().getDateHour();
    }

    private LocalDateTime getLastDate(Ship s) {
        return s.getPosDate().getBiggestPosition().getDateHour();
    }

    private long differenceBetweenDates(LocalDateTime first, LocalDateTime second) {

        return Duration.between(first, second).toDays();
    }

    private int getTotalNumberOfMovements(Ship s) {

        return s.getPosDate().getSize();

    }

    private double getMaxSOG(Ship s) {

        double maxSOG = 0;

        for (Position dateTime : s.getPosDate().getOrderList()) {
            if (maxSOG < dateTime.getSog()) maxSOG = dateTime.getSog();
        }

        return maxSOG;
    }

    private double getMeanSOG(Ship s) {

        double meanSOG = 0;
        int count = 0;

        for (Position dateTime : s.getPosDate().getOrderList()) {
            meanSOG += dateTime.getSog();
            count++;
        }

        return (meanSOG / count);
    }

    private double getMaxCOG(Ship s) {

        double maxCOG = 0;

        for (Position dateTime : s.getPosDate().getOrderList()) {
            if (maxCOG < dateTime.getCog()) maxCOG = dateTime.getCog();
        }

        return maxCOG;
    }

    private double getMeanCOG(Ship s) {

        double meanCOG = 0;
        int count = 0;

        for (Position dateTime : s.getPosDate().getOrderList()) {
            meanCOG += dateTime.getCog();
            count++;
        }

        return (meanCOG / count);
    }

    private double getDepartureLatitude(Ship s) {
        return (s.getPosDate().getSmallestPosition().getLatitude());
    }

    private double getDepartureLongitude(Ship s) {
        return (s.getPosDate().getSmallestPosition().getLongitude());
    }

    private double getArrivalLatitude(Ship s) {
        return (s.getPosDate().getBiggestPosition().getLatitude());
    }

    private double getArrivalLongitude(Ship s) {
        return (s.getPosDate().getBiggestPosition().getLongitude());
    }
}

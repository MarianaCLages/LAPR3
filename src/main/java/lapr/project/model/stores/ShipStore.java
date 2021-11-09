package lapr.project.model.stores;


import lapr.project.model.BinarySearchTree;
import lapr.project.model.Position;
import lapr.project.model.Ship;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        } catch (Exception e) {
            return false;
        }
    }

    public Ship findShip(int mmsi) {
        Ship ship = new Ship(mmsi);
        return shipBinarySearchTree.find(ship);

    }

    public List<Ship> transformBSTintoList() {
        Iterable<Ship> ls = shipBinarySearchTree.inOrder();
        List<Ship> lShip = new ArrayList<>();
        ls.iterator().forEachRemaining(lShip::add);

        return lShip;
    }

    public List<Ship> getlShip() {

        return transformBSTintoList();
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

   /* public List<String> getShipListPos() {
        List<String> shipListPos = new ArrayList<>();
        for (Ship ship : shipBinarySearchTree.inOrder()) {
            shipListPos.add(ship.writeAllPos());
        }
        return shipListPos;
    } */

    public String getShipSummaryByMMSI(double mmsi) {


        String returnString;
        List<Ship> lShip = transformBSTintoList();


        try {

            StringBuilder sb = new StringBuilder();

            for (Ship s : lShip) {

                if (mmsi == s.getMmsi()) {

                    sb
                            .append("MMSI : " + s.getMmsi() + "\n")
                            .append(getShipSummaryStructure(s));

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

    public String getShipSummaryByIMO(String imo) {


        String returnString;
        List<Ship> lShip = transformBSTintoList();

        try {

            StringBuilder sb = new StringBuilder();

            for (Ship s : lShip) {

                if (imo.equals(s.getImo())) {

                    sb
                            .append("IMO : " + s.getImo() + "\n")
                            .append(getShipSummaryStructure(s));

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

    public String getShipSummaryByCallSign(String callSign) {


        String returnString;
        List<Ship> lShip = transformBSTintoList();

        try {

            StringBuilder sb = new StringBuilder();

            for (Ship s : lShip) {

                if (callSign.equals(s.getCallSign())) {

                    sb
                            .append("Call Sign : " + s.getCallSign() + "\n")
                            .append(getShipSummaryStructure(s));

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

    public String getShipSummaryStructure(Ship s) {

        Iterable<Position> dateIterable = s.getPosDate().getOrderList();
        List<Position> positionList = new ArrayList<>();
        dateIterable.iterator().forEachRemaining(positionList::add);

        StringBuilder sb = new StringBuilder();

        sb
                .append("Vessel name: " + s.getVesselType() + "\n")
                .append("Start Base date Time: " + getFirstDate(s) + "\n")
                .append("End base date time : " + getLastDate(s) + "\n")
                .append("Total movement time: " + differenceBetweenDates(getFirstDate(s), getLastDate(s)) + " minutes" + "\n")
                .append("Total number of movements : " + s.getTotalNumberOfMovements() + "\n")
                .append("Max SOG : " + getMaxSOG(s) + "\n")
                .append("Mean SOG : " + getMeanSOG(s) + "\n")
                .append("Max COG : " + getMaxCOG(s) + "\n")
                .append("Mean COG : " + getMeanCOG(s) + "\n")
                .append("Departure Latitude : " + getDepartureLatitude(s) + "\n")
                .append("Departure Longitude : " + getDepartureLongitude(s) + "\n")
                .append("Arrival Latitude : " + getArrivalLatitude(s) + "\n")
                .append("Arrival Longitude : " + getArrivalLongitude(s) + "\n")
                .append("Travelled Distance : " + s.getTravelledDistance() + "\n")
                .append("Delta Distance : " + s.getDeltaDistance());

        return sb.toString();

    }

    public LocalDateTime getFirstDate(Ship s) {
        try {
            return s.getPosDate().getSmallestPosition().getDate();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public LocalDateTime getLastDate(Ship s) {
        try {
            return s.getPosDate().getBiggestPosition().getDate();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public long differenceBetweenDates(LocalDateTime first, LocalDateTime second) {

        try {

            Date firstDate = java.util.Date.from(first.atZone(ZoneId.systemDefault()).toInstant());
            Date secondDate = java.util.Date.from(second.atZone(ZoneId.systemDefault()).toInstant());

            long diffInMillies = Math.abs(firstDate.getTime() - secondDate.getTime());
            return (TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS));
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public double getMaxSOG(Ship s) {

        double maxSog = 0;

        for (Position ps1 : s.getPosDate().getOrderList()) {
            if (ps1.getSog() > maxSog) maxSog = ps1.getSog();
        }
        return maxSog;
    }

    public double getMeanSOG(Ship s) {

        try {
            double meanSOG = 0;
            int count = 0;

            for (Position ps1 : s.getPosDate().getOrderList()) {
                meanSOG += ps1.getSog();
                count++;
            }

            if (count == 0) return 0;

            return (meanSOG / count);
        } catch (ArithmeticException | NullPointerException e) {
            return 0;
        }
    }

    public double getMaxCOG(Ship s) {

        double maxCog = 0;

        for (Position ps1 : s.getPosDate().getOrderList()) {
            if (ps1.getCog() > maxCog) maxCog = ps1.getCog();
        }
        return maxCog;
    }

    public double getMeanCOG(Ship s) {

        try {

            double meanCOG = 0;
            int count = 0;

            for (Position ps1 : s.getPosDate().getOrderList()) {
                meanCOG += ps1.getCog();
                count++;
            }

            if (count == 0) return 0;

            return (meanCOG / count);
        } catch (ArithmeticException | NullPointerException e) {
            return 0;
        }
    }

    public BinarySearchTree<Ship> getShipBinarySearchTree() {
        return shipBinarySearchTree;
    }

    public double getDepartureLatitude(Ship s) {
        try {
            return (s.getPosDate().getSmallestPosition().getLatitude());
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            return 0;
        }
    }

    public double getDepartureLongitude(Ship s) {
        try {
            return (s.getPosDate().getSmallestPosition().getLongitude());
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            return 0;
        }
    }

    public double getArrivalLatitude(Ship s) {
        try {
            return (s.getPosDate().getBiggestPosition().getLatitude());
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            return 0;
        }
    }

    public double getArrivalLongitude(Ship s) {
        try {
            return (s.getPosDate().getBiggestPosition().getLongitude());
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            return 0;
        }
    }
}

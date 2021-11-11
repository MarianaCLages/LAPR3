package lapr.project.model.stores;


import lapr.project.model.Position;
import lapr.project.model.Ship;
import lapr.project.shared.BinarySearchTree;
import lapr.project.shared.DistanceCalculation;
import lapr.project.shared.PairOfShips;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShipStore {

    public BinarySearchTree<Ship> shipBinarySearchTree;
    public BinarySearchTree<PairOfShips> pairsOfShipsSearchTree = new BinarySearchTree<>();

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

    public List<PairOfShips> getPairsOfShipsSearchTree() {
        return transformBSTintoListPairsOfShip();
    }

    public List<PairOfShips> transformBSTintoListPairsOfShip() {
        Iterable<PairOfShips> ls = pairsOfShipsSearchTree.inOrder();
        List<PairOfShips> pairsShip = new ArrayList<>();
        ls.iterator().forEachRemaining(pairsShip::add);

        return pairsShip;
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

    }

    public String getShipSummaryByIMO(String imo) {


        String returnString;
        List<Ship> lShip = transformBSTintoList();

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

    }

    public String getShipSummaryByCallSign(String callSign) {


        String returnString;
        List<Ship> lShip = transformBSTintoList();

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

    }

    public String getShipSummaryStructure(Ship s) {

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

    public List<Ship> sortedList() {
        List<Ship> shipList = transformBSTintoList();
        Comparator<Ship> comparator1 = (o1, o2) -> {

            double x1 = o1.getTravelledDistance();
            double x2 = o2.getTravelledDistance();

            double z1 = o1.getPosDate().getSize();
            double z2 = o2.getPosDate().getSize();

            double result1 = x2 - x1;
            double result2 = z2 - z1;

            if (result1 > 0) {
                return 1;
            } else if (result1 < 0) {
                return -1;
            } else {
                if (result2 > 0) {
                    return -1;
                } else if (result2 < 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
        shipList.sort(comparator1);

        return shipList;
    }

    public void getPairOfShipsInsideBST() {

        List<Ship> lShip = transformBSTintoList();

        for (int i = 0; i < lShip.size(); i++) {

            Ship s1 = lShip.get(i);

            for (int j = 1; j < lShip.size(); j++) {

                Ship s2 = lShip.get(j);

                if (!s1.equals(s2)) {
                    if (DistanceCalculation.distanceTo(s1.getPosDate().getSmallestPosition(), s2.getPosDate().getSmallestPosition()) < 5000) {
                        if (DistanceCalculation.distanceTo(s1.getPosDate().getBiggestPosition(), s2.getPosDate().getBiggestPosition()) < 5000) {
                            if (s1.getTravelledDistance() != s2.getTravelledDistance()) {

                                if (s1.getTravelledDistance() >= 10000 && s2.getTravelledDistance() >= 10000) {
                                    PairOfShips pairOfShips = new PairOfShips(s1, s2);
                                    pairsOfShipsSearchTree.insert(pairOfShips);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public String getPairsOfShipsString() {

        getPairOfShipsInsideBST();
        StringBuilder sb = new StringBuilder();

        sb.append("|   Ship 1 MMMSI   \t | \t     Ship 2 MMSI  \t  | \t   DistOrig  \t  | \t  DistDest  \t  |      \t  Movs  \t       |       \t   TravelDist  \t        |  \t       Movs  \t       |           \t TravelDist           \t |\n");

        for (PairOfShips pairOfShips : getPairsOfShipsSearchTree()) {
            sb.append("     " + pairOfShips.getLeft().getMmsi() + "\t\t\t     " + pairOfShips.getRight().getMmsi() + "         \t\t\t " + DistanceCalculation.distanceTo(pairOfShips.getLeft().getPosDate().getSmallestPosition(), pairOfShips.getLeft().getPosDate().getBiggestPosition()) + "     \t\t\t\t" + DistanceCalculation.distanceTo(pairOfShips.getLeft().getPosDate().getSmallestPosition(), pairOfShips.getLeft().getPosDate().getBiggestPosition()) + "        \t\t\t" + pairOfShips.getLeft().getTotalNumberOfMovements() + "                 \t\t" + pairOfShips.getLeft().getTravelledDistance() + "            \t\t" + pairOfShips.getRight().getTotalNumberOfMovements() + "                \t\t" + pairOfShips.getRight().getTravelledDistance() + "\n");
        }

        return sb.toString();
    }

   /* public double[] getTravelledDistanceIntoArray() {

        double[] travelledDistanceIntoArray = new double[50];

        int i = 0;

        for (PairOfShips pairOfShips : getPairsOfShipsSearchTree()) {

            travelledDistanceIntoArray[i] = pairOfShips.getLeft().getTravelledDistance();
            i++;

        }

        return sortTravelledDistanceArray(travelledDistanceIntoArray);

    }

    public double[] sortTravelledDistanceArray(double[] travelledDistanceIntoArray) {

        double aux = 0;

        for (int i = 0; i < travelledDistanceIntoArray.length; i++) {
            for (int j = 1; j < travelledDistanceIntoArray.length; j++) {

                if (travelledDistanceIntoArray[j] > travelledDistanceIntoArray[i]) {
                    aux = travelledDistanceIntoArray[j];
                    travelledDistanceIntoArray[j] = travelledDistanceIntoArray[i];
                    travelledDistanceIntoArray[i] = aux;
                }

            }
        }

        return travelledDistanceIntoArray;
    }*/
}


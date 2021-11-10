package lapr.project.shared;

import lapr.project.model.Position;
import lapr.project.model.Ship;

import java.time.LocalDateTime;
import java.util.*;

public class DistanceCalculation {

    public DistanceCalculation() {

    }

    public static double distanceTo(Position pos, Position pos2) {


        if (pos == null && pos2 == null) return 0;

        double radius = 6371000;


        double latShipRadians = pos.getLatitude() * (3.1416 / 180);
        double latShip2Radians = pos.getLatitude() * (3.1416 / 180);
        double latDiff = (pos2.getLatitude() - pos.getLatitude()) * (3.1416 / 180);
        double lonDiff = (pos2.getLongitude() - pos.getLongitude()) * (3.1416 / 180);

        double aux = Math.sin(latDiff / 2) * Math.sin(latDiff / 2) + Math.cos(latShipRadians) * Math.cos(latShip2Radians) * Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2);

        double c = 2 * Math.atan2(Math.sqrt(aux), Math.sqrt(1 - aux));

        double distance = radius * c;

        return (Math.round(distance * 100) / 100.0);
    }

    public double traveledDistanceBaseDateTime(Ship ship, LocalDateTime localinitiald, LocalDateTime localfinald) {


        if (ship == null || localinitiald == null || localfinald == null || localinitiald.equals(localfinald)) return 0;


        Date initiald = java.sql.Timestamp.valueOf(localinitiald);
        Date finald = java.sql.Timestamp.valueOf(localfinald);

        double d = 0;
        List<Position> positionList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initiald);

        Position sv1;
        Position sv2;

        Iterable<Position> posIterable = ship.getPosDate().getOrderList();
        Iterator<Position> posIterator = posIterable.iterator();


        while (initiald.before(finald)) {


            if (calendar.getTime().getMinutes() == 60) {
                calendar.add(Calendar.HOUR_OF_DAY, 1);
                calendar.getTime().setMinutes(0);
                calendar.getTime().setSeconds(0);
            }
            if (calendar.getTime().getSeconds() == 60) {
                calendar.add(Calendar.MINUTE, 1);
                calendar.getTime().setSeconds(0);
            }


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
        //out of the loop


        int count = 0;
        Position posA[] = new Position[2];

        for (Position pos : positionList) {


            posA[count] = pos;
            count++;

            if (count == 2 && posA[0] != null && posA[1] != null) {

                d = d + distanceTo(posA[0], posA[1]);

                count = 0;
                posA[count] = pos;
                count++;
            }


        }
        return d;
    }

  /* public static double traveledDistance(Ship ship) {

        Iterable<Position> allPos = ship.getBinaryTreePosition().inOrder();
        Iterator<Position> iteratorPos = allPos.iterator();

        double d = 0;
        int count = 0;
        Position posA[] = new Position[2];


        while (iteratorPos.hasNext()) {


            posA[count] = iteratorPos.next();

            count++;

            if (count == 2 && posA[0] != null && posA[1] != null) {

                d = d + distanceTo(posA[0], posA[1]);

                count = 0;
                posA[count] = posA[1];
                count++;
            }


        }
        return d;

    }*/
}

package lapr.project.shared.graph;

public class VertexDistanceCalculator {
    private VertexDistanceCalculator() {
        //
    }

    public static double distanceCalculator(Vertex v1, Vertex v2) {
        if ((v1 == null) || (v2 == null)) {
            return Double.MAX_VALUE;
        }
        if (v1.getLatitude() == 0 && v1.getLongitude() == 0 && v2.getLatitude() == 0 && v2.getLongitude() == 0)
            return 0;
        double radius = 6371000;

        double latShipRadians = v1.getLatitude() * (Math.PI / 180);
        double latShip2Radians = v2.getLatitude() * (Math.PI / 180);
        double latDiff = (v2.getLatitude() - v1.getLatitude()) * (Math.PI / 180);
        double lonDiff = (v2.getLongitude() - v1.getLongitude()) * (Math.PI / 180);

        double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2) + Math.cos(latShipRadians) * Math.cos(latShip2Radians) * Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = radius * c;

        return (Math.round(distance * 100) / 100.0);
    }
}

package lapr.project.model;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.Utils.DataBaseUtils;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;

public class CreateGraph {
    private CreateGraph() {
        //
    }

    public static boolean createGraph(int n, DatabaseConnection connection) {

        FreightNetwork freightNetwork = new FreightNetwork();
        capitals(connection, freightNetwork);
        return true;
    }

    private static boolean capitals(DatabaseConnection connection, FreightNetwork freightNetwork) {
        try {
            Map<City, LinkedList<City>> map = DataBaseUtils.getBorders(connection);
            for (Map.Entry<City, LinkedList<City>> c : map.entrySet()) {
                LinkedList<City> list = c.getValue();
                while (!list.isEmpty()) {

                    freightNetwork.addEdgeAndCalculateWeight(c.getKey(), list.removeFirst());
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

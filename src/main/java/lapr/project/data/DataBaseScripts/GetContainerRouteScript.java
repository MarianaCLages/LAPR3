package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;
import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class GetContainerRouteScript {

    public static String callFunction(String containerID, String clientID, DatabaseConnection databaseConnection) throws SQLException {

        StringBuilder sb = new StringBuilder();
        sb.append("Container Route:");

        try (CallableStatement containerRouteStatement = databaseConnection.getConnection().prepareCall("{? = call fnGetContainerRouteCursor(?,?)}")) {

            containerRouteStatement.registerOutParameter(1, OracleTypes.CURSOR);
            containerRouteStatement.setString(2, clientID);
            containerRouteStatement.setString(3, containerID);
            containerRouteStatement.execute();

            try (ResultSet containerRoute = containerRouteStatement.executeQuery()) {

                try (ResultSet containerRouteCursor = (ResultSet) containerRoute.getObject(1)) {

                    while (containerRouteCursor.next()) {


                        sb.append("\nVehicle Type: ").append(containerRouteCursor.getString(1))
                                .append("\nVehicle Name: ").append(containerRouteCursor.getString("VEHICLENAME"))
                                .append("\nFacility Name: ").append(containerRouteCursor.getString("FACILITYNAME"))
                                .append("\nTrip Start Date: ").append(containerRouteCursor.getObject("TRIPSTARTDATE"))
                                .append("\nTrip End Date: ").append(containerRouteCursor.getObject("TRIPENDDATE"))
                                .append("\nCargo Manifest Date").append(containerRouteCursor.getObject("MANIFESTDATE"))
                                .append("\n\n");

                    }

                    return sb.toString();

                }
            }

        }

    }
}

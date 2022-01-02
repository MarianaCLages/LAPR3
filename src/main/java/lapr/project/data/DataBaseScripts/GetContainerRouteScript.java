package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;
import oracle.jdbc.internal.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class GetContainerRouteScript {

    private GetContainerRouteScript() {
        //EMPTY
    }

    public static String callFunction(String containerID, String clientID, DatabaseConnection databaseConnection) throws SQLException, NullPointerException {

        StringBuilder sb = new StringBuilder();
        sb.append("\nContainer Route:");

        try (CallableStatement containerRouteStatement = databaseConnection.getConnection().prepareCall("{? = call fnGetContainerRouteCursor(?,?)}")) {

            containerRouteStatement.registerOutParameter(1, OracleTypes.CURSOR);
            containerRouteStatement.setString(2, clientID);
            containerRouteStatement.setString(3, containerID);
            containerRouteStatement.execute();

            try (ResultSet containerRoute = (ResultSet) containerRouteStatement.getObject(1)) {

                if (containerRoute == null)
                    throw new NullPointerException("There is no information available for that specific container/client!");

                while (containerRoute.next()) {

                    try (CallableStatement vehicleType = databaseConnection.getConnection().prepareCall("{? = call fnGetVehicleType(?)}")) {

                        vehicleType.registerOutParameter(1, Types.VARCHAR);
                        vehicleType.setString(2, containerRoute.getString("VEHICLE_NAME"));

                        vehicleType.execute();

                        sb.append("\nVehicle Type: ").append(vehicleType.getString(1))
                                .append("\nVehicle Name: ").append(containerRoute.getString("VEHICLE_NAME"))
                                .append("\nFacility Name: ").append(containerRoute.getString("FACILITY_NAME"))
                                .append("\nTrip Start Date: ").append(containerRoute.getObject("TRIP_START_DATE"))
                                .append("\nTrip End Date: ").append(containerRoute.getObject("TRIP_END_DATE"))
                                .append("\nCargo Manifest Date: ").append(containerRoute.getObject("CARGOMANIFEST_DATE"))
                                .append("\n\n");

                    }
                }

                return sb.toString();
            }

        }


    }
}


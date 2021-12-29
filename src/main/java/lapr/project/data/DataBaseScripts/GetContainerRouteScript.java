package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;
import oracle.jdbc.internal.OracleTypes;
import oracle.jdbc.OracleType;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetContainerRouteScript {

    public static String callFunction(String containerID, String clientID, DatabaseConnection databaseConnection) throws SQLException, NullPointerException {

        StringBuilder sb = new StringBuilder();
        sb.append("Container Route:");

        try (CallableStatement containerRouteStatement = databaseConnection.getConnection().prepareCall("{? = call fnGetContainerRouteCursor(?,?)}")) {

            containerRouteStatement.registerOutParameter(1, OracleTypes.CURSOR);
            containerRouteStatement.setString(2, clientID);
            containerRouteStatement.setString(3, containerID);
            containerRouteStatement.execute();
            
            containerRouteStatement.execute();

            try (ResultSet containerRoute = (ResultSet) containerRouteStatement.getObject(1)) {

                if (containerRoute == null)
                    throw new NullPointerException("There is no information available for that specific container/client!");

                while (containerRoute.next()) {

                    sb.append("\nVehicle Type: ").append(containerRoute.getString("VEHICLETYPE"))
                            .append("\nVehicle Name: ").append(containerRoute.getString("VEHICLENAME"))
                            .append("\nFacility Name: ").append(containerRoute.getString("FACILITYNAME"))
                            .append("\nTrip Start Date: ").append(containerRoute.getObject("TRIPSTARTDATE"))
                            .append("\nTrip End Date: ").append(containerRoute.getObject("TRIPENDDATE"))
                            .append("\nCargo Manifest Date").append(containerRoute.getObject("MANIFESTDATE"))
                            .append("\n\n");

                }

                return sb.toString();

            }


        }
    }

}


package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

public class CheckForContainerSituationFunction {


    public static boolean containerSituation(DatabaseConnection connection, String containerId, String clientId) throws IOException {
        String sqlString = "{? = call LAPR3_G096.FNCCHECKFORCONTAINERSITUATION(?,?)}";
        try (CallableStatement cstmt = connection.getConnection().prepareCall(sqlString)) {
            cstmt.registerOutParameter(1, Types.BOOLEAN);
            cstmt.setString(2, containerId);
            cstmt.setString(3, clientId);

            cstmt.execute();
            return cstmt.getBoolean(1);

        } catch (SQLException e) {
            if (e.getErrorCode() == 10){
                // container is not valid
                e.printStackTrace();
                return false;
            }
            if (e.getErrorCode() == 11){
                // Container not leased by client
                e.printStackTrace();
                return false;
            }
            else{
                throw new IOException("A fatal error has occurred while checking for container situation. Please contact the system administrators.");
            }
        }
    }
}
package lapr.project.data.DataBaseScripts;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class CallTheAvailableResourcesFunction {


    public CallTheAvailableResourcesFunction(){}

    public String callFunction(CallableStatement cstmt, CallableStatement cstmt2 ) throws SQLException {

        String resources = new String();


        resources = "CargoManifest Capacity:"+ cstmt.getInt(1) + "%\n";
        resources = resources + "Container Capcity:" +  cstmt2.getInt(1) + "%";



        return resources;
    }
}

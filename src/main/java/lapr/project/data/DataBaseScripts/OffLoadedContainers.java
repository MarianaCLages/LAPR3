package lapr.project.data.DataBaseScripts;

import lapr.project.controller.App;
import lapr.project.data.DatabaseConnection;
import lapr.project.model.*;
import lapr.project.model.stores.ShipStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OffLoadedContainers {



    public OffLoadedContainers(){
    }



    private DatabaseConnection databaseConnection = null;
    private List<Container> containerList = new ArrayList<>();
    private List<CargoManifest> cargoManifests = new ArrayList<>();
    private int countCargos = 0;




    private int countCargoManifestByFacility(Facility  f,Ship s)  {


        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select count(cm.CARGOMANIFESTID) CountCargo from CARGOMANIFEST cm\n" +
                "    where cm.CARGOMANIFESTTYPE = 1\n" +
                "    and cm.IDTRIP = (select t.IDTRIP from TRIP t\n" +
                "        inner join FacilityTrip ft\n" +
                "        on t.IDTRIP = ft.IDTRIP\n" +
                "        where ft.facilityId = (select f.FACILITYID from FACILITY f\n" +
                "            where f.FACILITYID = " +"14635"+"))\n" +
                "    and cm.IDTRIP  = (select t.IDTRIP from TRIP t\n" +
                "        inner join VEHICLE v\n" +
                "        on t.VEHICLEID = v.VEHICLEID\n" +
                "        where v.VEHICLEID = (select s.VEHICLEID from SHIP s\n" +
                "        where s.MMSI =" +366976870+ "))";

        try(PreparedStatement getPreparedStatment = connection.prepareStatement(sqlCommand)){
            try (ResultSet resultSet = getPreparedStatment.executeQuery()){

                if(resultSet.next()){
                    return countCargos += resultSet.getInt("COUNTCARGO");
                }
                else {
                    return 0;
                }
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
            return 0;
        }
    }

    public List<Container> getContainersPerCargoOffLoad(Facility f, Ship s){


        int j = countCargoManifestByFacility(f,s);
        int count = 0;


        while( j != 0){

            CargoManifest cargoManifest = getCargoManifestByShipTrip(s,f,count);

            for(Container c : cargoManifest.getOffLoaded().inOrder()){
                containerList.add(c);
            }

            count++;
            j--;


        }
        return containerList;
    }



    private CargoManifest getCargoManifestByShipTrip(Ship s , Facility f, int j) {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select count(cm.CARGOMANIFESTID) CountCargo from CARGOMANIFEST cm\n" +
                "    where cm.CARGOMANIFESTTYPE = 1\n" +
                "    and cm.IDTRIP = (select t.IDTRIP from TRIP t\n" +
                "        inner join FacilityTrip ft\n" +
                "        on t.IDTRIP = ft.IDTRIP\n" +
                "        where ft.facilityId = (select f.FACILITYID from FACILITY f\n" +
                "            where f.FACILITYID =" + "14635" + "))\n" +
                "    and cm.IDTRIP  = (select t.IDTRIP from TRIP t\n" +
                "        inner join VEHICLE v\n" +
                "        on t.VEHICLEID = v.VEHICLEID\n" +
                "        where v.VEHICLEID = (select s.VEHICLEID from SHIP s\n" +
                "        where s.MMSI = " + 366976870 + "))";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {
                for (int i = 0; i < j; i++){
                    resultSet.next();
                }
                if(resultSet.next()){

                    Port p = (Port) f;
                    String identification = resultSet.getString("CARGOMANIFESTID");
                    Date data = resultSet.getDate("CARGOMANIFESTDATE");

                    return new CargoManifest(identification,p,data);

                }else return null;
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
            return null;
        }
    }


    public void wtv(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;


        System.out.println(countCargoManifestByFacility(null,null));

        for(Container c : getContainersPerCargoOffLoad(null,null)  ){
            System.out.println(c);
        }

    }
}

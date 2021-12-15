/*package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;
import lapr.project.model.Facility;
import lapr.project.model.FacilityLocation;
import lapr.project.ui.FacilityResourcesUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class FacilityResourcesScript  {

    DatabaseConnection  databaseConnection = null;

    public FacilityResourcesScript(){
        //empty constructor
    }

    private String getContinent(String country) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "SELECT * from COUNTRY c where ALPHA2CODE = '" + country+"'";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {


                    return  resultSet.getString("CONTINENT");


                } else {
                    return null;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }return null;
    }

    private Facility getFacility(String id) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "SELECT * from  FACILITY where FACILITYID =" + id;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {


                    String facilityID = resultSet.getString("FACILITYID");
                    String name = resultSet.getString("NAME");
                    double longitude = resultSet.getDouble("LONGITUDE");
                    double latitude = resultSet.getDouble("LATITUDE");
                    int capacity = resultSet.getInt("CAPACITY");
                    String country = resultSet.getString("ALPHA2CODE");
                    String continent = getContinent(country);

                    return new Facility(facilityID,name,continent,country,new FacilityLocation(longitude,latitude),capacity);


                } else {
                    return null;
                }
            }
        }
    }


    public int countContainersByYears(int day,int month, int year,String id)  {

        String sqlCommand = "Select count(*) CargoManifestContainerCount from CARGOMANIFESTCONTAINER cmc\n" +
                "where cmc.CARGOMANIFESTID = (Select cm.CARGOMANIFESTID from CargoManifest cm\n" +
                "    where cm.CARGOMANIFESTDATE = TO_DATE('"+year+"-"+month+"-"+day+"','YYYY-MM-DD')" +
                "    and cm.FACILITYID =" +id+ ")";


        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

            if(resultSet.next())
            return resultSet.getInt("CargoManifestContainerCount");

            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }


    public boolean resources(DatabaseConnection databaseConnection,String id,int month, int year) throws SQLException {

        this.databaseConnection = databaseConnection;
        int count = 0;
        int dayMax;

        Facility f = getFacility(id);

        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
            dayMax = 31;
        }
        else if(month == 4 || month == 6 || month == 9 || month == 11){
            dayMax = 30;
        }
        else{

            if((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))){
                dayMax = 29;
            }
            else{
                dayMax = 28;
            }

        }

        System.out.println("Container Occupation Percentage:");
        for(int i = 1; i <= dayMax; i++){
            count += countContainersByYears(i,month,year,id);

            System.out.println("Day "+i+": " );
            System.out.println("Container Occupation Percentage:"+ ((float) count) / f.getCapacity() * 100 +"%");
        }



        return true;
    }
}
*/
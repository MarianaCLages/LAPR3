package lapr.project.data.Utils;

import lapr.project.controller.App;
import lapr.project.data.CargoManifestStoreData;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ShipStoreData;
import lapr.project.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseUtils {

    private static final ShipStoreData shipStoreData = App.getInstance().getCompany().getShipStoreData();

    public static Port getPort(String facilityID, DatabaseConnection databaseConnection) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "SELECT * FROM FACILITY WHERE FACILITYID = '" + facilityID + "'";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    String continentID = getContinentID(resultSet.getString("ALPHA3CODE"), databaseConnection);

                    String identification = resultSet.getString("FACILITYID");
                    String name = resultSet.getString("NAME");
                    String country = resultSet.getString("ALPHA3CODE");

                    double longitude = resultSet.getDouble("LONGITUDE");
                    double latitude = resultSet.getDouble("LATITUDE");

                    if (latitude < -90) latitude += 90;
                    if (longitude < -180) longitude += 180;

                    return new Port(identification, name, continentID, country, new FacilityLocation(longitude, latitude));

                } else return null;


            }
        }

    }


    public static String getContinentID(String countryID, DatabaseConnection databaseConnection) throws SQLException {

        String sqlCommand = "SELECT CONTINENTID FROM COUNTRY WHERE ALPHA3CODE = '" + countryID + "'";

        return executeQueryAndReturnString(sqlCommand, databaseConnection);


    }

    public static String getContinent(String continentID, DatabaseConnection databaseConnection) throws SQLException {


        String sqlCommand = "SELECT CONTINENTID FROM COUNTRY WHERE ALPHA3CODE = '" + continentID + "'";

        return executeQueryAndReturnString(sqlCommand, databaseConnection);

    }

    public static int countCargoManifestByShip(int mmsi, DatabaseConnection databaseConnection) throws SQLException {

        String sqlCommand = "select count(*) COUNTCARGOMANIFESTS from CARGOMANIFEST cm\n" +
                "inner join SHIP s\n" +
                "on cm.VEHICLEID = s.VEHICLEID\n" +
                "where s.MMSI = " + mmsi;

        return executeQueryAndReturnInteger(sqlCommand, databaseConnection);
    }


    public static String executeQueryAndReturnString(String sqlCommand, DatabaseConnection databaseConnection) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {


                if (resultSet.next()) {

                    return resultSet.getString(1);

                } else return null;


            }
        }
    }

    public static int executeQueryAndReturnInteger(String sqlCommand, DatabaseConnection databaseConnection) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {


                if (resultSet.next()) {

                    return resultSet.getInt(1);

                } else return 0;


            }
        }
    }

    public static CargoManifest getCargoManifestByMmsi(int mmsi, int j, Ship s, DatabaseConnection databaseConnection) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from CARGOMANIFEST cm\n" +
                "inner join SHIP s\n" +
                "on cm.VEHICLEID = s.VEHICLEID\n" +
                "where s.MMSI = " + mmsi;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                for (int i = 0; i < j; i++) {
                    resultSet.next();
                }
                if (resultSet.next()) {

                    String cargoManifestId = resultSet.getString("CARGOMANIFESTID");
                    Port p = null;

                    CargoManifest cargoManifest = new CargoManifest(cargoManifestId, p, s, true);

                    return cargoManifest;
                }
            }
            return null;
        }
    }


    public static int countContainerByCargoManifest(String id, DatabaseConnection databaseConnection) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "Select count(*) COUNTCONTAINERS from CONTAINER c\n" +
                "inner join  CargoManifestContainer cm\n" +
                "where cm.CargoManifestId =" + id;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getInt("COUNTCONTAINERS");
                } else {
                    return 0;
                }
            }
        }
    }

    public static Container getContainerByCargo(String id, int j, DatabaseConnection databaseConnection) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "Select *  from CONTAINER c\n" +
                "inner join  CargoManifestContainer cm\n" +
                "where cm.CargoManifestId =" + id;


        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                for (int i = 0; i < j; i++) {
                    resultSet.next();
                }

                if (resultSet.next()) {


                    String identification = resultSet.getString("CONTAINERID");
                    int payload = resultSet.getInt("PAYLOAD");
                    int tare = resultSet.getInt("TARE");
                    int gross = resultSet.getInt("GROSS");
                    String isoCode = resultSet.getString("ISOCODE");
                    return new Container(identification, payload, tare, gross, isoCode);
                }
            }
            return null;
        }
    }

    public static Ship getShipByMmsi(int mmsi, DatabaseConnection databaseConnection) throws SQLException {
        return (Ship) shipStoreData.getElement(databaseConnection, mmsi);
    }

}


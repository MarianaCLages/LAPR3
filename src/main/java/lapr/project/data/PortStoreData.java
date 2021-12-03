package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.Container;
import lapr.project.model.Facility;
import lapr.project.model.FacilityLocation;
import lapr.project.model.Port;
import lapr.project.model.stores.ContainerStore;
import lapr.project.model.stores.PortStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PortStoreData implements Persistable {

    private static int i = 1;
    private final Set<Port> listPort;

    public PortStoreData() {
        //Empty constructor
        listPort = new HashSet<>();
    }

    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {

        try {
            Connection connection = databaseConnection.getConnection();
        } catch (Exception e) {
            return false;
        }

        Connection connection = databaseConnection.getConnection();
        Port port = (Port) object;

        String sqlCommand = "select * from facility where FACILITYID = '" + port.getIdentification() + "'";

        try (PreparedStatement getContainerPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet portsResultSet = getContainerPreparedStatement.executeQuery()) {
                if (portsResultSet.next()) {
                    sqlCommand = "UPDATE FACILITY SET LONGITUDE = " + port.getLocation().getLongitude() + ", LATITUDE = " + port.getLocation().getLongitude() + ", NAME = '" + port.getName() + "' Where FACILITYID = '" + port.getIdentification() + "'";

                    try (PreparedStatement updatePortPreparedStatement = connection.prepareStatement(sqlCommand)) {
                        updatePortPreparedStatement.executeUpdate();
                        return true;
                    }

                } else {

                    sqlCommand = "select * from COUNTRY where NAME = '" + port.getCountry() + "'";

                    try (PreparedStatement getCountryPreparedStatement = connection.prepareStatement(sqlCommand)) {
                        try (ResultSet resultSetCountry = getCountryPreparedStatement.executeQuery()) {

                            if (resultSetCountry.next()) {

                                String portCountryID = resultSetCountry.getString("COUNTRYID");
                                int facilityType = 1; //Port

                                sqlCommand = "insert into FACILITY(facilityid, facilitytype, countryid, longitude, latitude, name) values ('" + port.getIdentification() + "'," + facilityType + ",'" + portCountryID + "'," + port.getLocation().getLongitude() + "," + port.getLocation().getLatitude() + ",'" + port.getName() + "')";

                                try (PreparedStatement updatePortPreparedStatement = connection.prepareStatement(sqlCommand)) {
                                    updatePortPreparedStatement.executeUpdate();
                                    return true;
                                }

                            } else {

                                sqlCommand = "insert into COUNTRY (COUNTRYID, CONTINENTID, NAME) VALUES ('" + port.getCountry() + "'," + 1 + ",'" + port.getCountry() + "')";

                                try (PreparedStatement updateCOUNTRYPreparedStatement = connection.prepareStatement(sqlCommand)) {
                                    updateCOUNTRYPreparedStatement.executeUpdate();
                                }

                                int facilityType = 1; //Port

                                sqlCommand = "insert into FACILITY(facilityid, facilitytype, countryid, longitude, latitude, name) values ('" + port.getIdentification() + "'," + facilityType + ",'" + port.getCountry() + "'," + port.getLocation().getLongitude() + "," + port.getLocation().getLatitude() + ",'" + port.getName() + "')";

                                try (PreparedStatement insertIntoFacilityPreparedStatement = connection.prepareStatement(sqlCommand)) {
                                    insertIntoFacilityPreparedStatement.executeUpdate();
                                    return true;
                                }

                            }
                        }
                    }
                }


            } catch (SQLException throwables) {
                Logger.getLogger(PortStore.class.getName()).log(Level.SEVERE, null, throwables);
                databaseConnection.registerError(throwables);
                return false;
            }

        } catch (SQLException throwables) {
            Logger.getLogger(PortStore.class.getName()).log(Level.SEVERE, null, throwables);
            databaseConnection.registerError(throwables);
            return false;
        }
    }

    @Override
    public boolean delete(DatabaseConnection databaseConnection, Object object) {

        Connection connection = databaseConnection.getConnection();
        Port port = (Port) object;

        try {
            String sqlCommand;
            sqlCommand = "delete from FACILITY where FACILITYID = '" + port.getIdentification() + "'";
            try (PreparedStatement deletePortPreparedStatement = connection.prepareStatement(sqlCommand)) {
                deletePortPreparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PortStore.class.getName()).log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            return false;
        }
    }

    @Override
    public Object getElement(DatabaseConnection databaseConnection, Object object) {

        Connection connection = databaseConnection.getConnection();
        String portIdentification = (String) object;

        String sqlCommand;
        sqlCommand = "SELECT * from FACILITY where FACILITYID = '" + portIdentification + "'";

        try (PreparedStatement getFacilityPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet portsResultSet = getFacilityPreparedStatement.executeQuery()) {

                if (portsResultSet.next()) {

                    String countryID = portsResultSet.getString("COUNTRYID");

                    String continentID = getContinentID(databaseConnection, countryID);

                   // String countryName = getCountry(databaseConnection, countryID);
                   // String continentName = getContinent(databaseConnection, continentID);
                    String portName = portsResultSet.getString("NAME");

                    double portLongitude = portsResultSet.getDouble("LONGITUDE");
                    double portLatitude = portsResultSet.getDouble("LATITUDE");

                   // return new Port(portIdentification, portName, continentName, countryName, new FacilityLocation(portLongitude, portLatitude));
                    return null;

                } else return null;

            }

        } catch (SQLException throwables) {
            Logger.getLogger(PortStore.class.getName()).log(Level.SEVERE, null, throwables);
            databaseConnection.registerError(throwables);
            return null;
        }
    }

    public String getContinentID(DatabaseConnection databaseConnection, String countryID) {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "SELECT * FROM COUNTRY WHERE COUNTRYID = " + countryID;

        try (PreparedStatement getCountryPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet countryResultSet = getCountryPreparedStatement.executeQuery()) {

                if (countryResultSet.next()) {
                    return countryResultSet.getString("NAME");
                } else return null;

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public String getCountry(DatabaseConnection databaseConnection, int countryID) {

        Connection connection = databaseConnection.getConnection();
        String sqlCommand;

        String countryName = null;

        sqlCommand = "SELECT * from COUNTRY where COUNTRYID = " + countryID;

        try (PreparedStatement getCountryPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet countrySet = getCountryPreparedStatement.executeQuery()) {

                if (countrySet.next()) {

                    return countrySet.getString("NAME");

                } else return null;

            }
        } catch (SQLException e) {
            Logger.getLogger(PortStore.class.getName()).log(Level.SEVERE, null, e);
            databaseConnection.registerError(e);
            return null;
        }

    }

    public String getContinent(DatabaseConnection databaseConnection, int continentID) {

        Connection connection = databaseConnection.getConnection();
        String sqlCommand;

        String continentName = null;

        sqlCommand = "SELECT * from CONTINENT where CONTINENTID = " + continentID;

        try (PreparedStatement getContinentPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet continentSet = getContinentPreparedStatement.executeQuery()) {

                if (continentSet.next()) {

                    return continentSet.getString("NAME");

                } else return null;

            }
        } catch (SQLException e) {
            Logger.getLogger(PortStore.class.getName()).log(Level.SEVERE, null, e);
            databaseConnection.registerError(e);
            return null;
        }

    }

    private void fillPortList() {

        DatabaseConnection databaseConnection = App.getInstance().getDatabaseConnection();

        Connection connection = databaseConnection.getConnection();

        String sqlCommand;

        sqlCommand = "SELECT * from FACILITY where FACILITYTYPE = 1";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    //  listPort.add(new Facility(resultSet.getString("FACILITYID"), resultSet.getString("NAME"), resultSet.getInt("TARE"), resultSet.getInt("GROSS"), resultSet.getString("CONTAINERID")));
                }

            }
        } catch (SQLException e) {
            Logger.getLogger(ContainerStore.class.getName()).log(Level.SEVERE, null, e);
            databaseConnection.registerError(e);
        }

    }


    public Set<Port> getListPort() {

        if (listPort.isEmpty()) fillPortList();

        return listPort;
    }
}

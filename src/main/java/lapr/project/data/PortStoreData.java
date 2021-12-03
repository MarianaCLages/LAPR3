package lapr.project.data;

import lapr.project.model.FacilityLocation;
import lapr.project.model.Port;
import lapr.project.model.stores.PortStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PortStoreData implements Persistable{

    public PortStoreData(){
        //Empty constructor
    }

    private static int i = 1;

    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {
        Connection connection = databaseConnection.getConnection();
        Port port = (Port) object;

        String sqlCommand = "select * from facility where FACILITYID = '" + port.getIdentification() + "'";

        try (PreparedStatement getContainerPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet portsResultSet = getContainerPreparedStatement.executeQuery()) {
                if (portsResultSet.next()) {
                    sqlCommand = "UPDATE FACILITY SET LONGITUDE = " + port.getLocation().getLongitude() + ", LATITUDE = " + port.getLocation().getLongitude() + ", NAME = " + port.getName() + " Where FACILITYID = '" + port.getIdentification() + "'";

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

                                sqlCommand = "insert into FACILITY(facilityid, facilitytype, countryid, longitude, latitude, name) values ('" + port.getIdentification() + "'," + facilityType + "," + portCountryID + "," + port.getLocation().getLongitude() + "," + port.getLocation().getLatitude() + "," + port.getName() + ")";

                                try (PreparedStatement updatePortPreparedStatement = connection.prepareStatement(sqlCommand)) {
                                    updatePortPreparedStatement.executeUpdate();
                                    return true;
                                }

                            } else {
                                sqlCommand = "select MAX(COUNTRYID) as COUNTRYID from COUNTRY";

                                try (PreparedStatement getCountryMaxPreparedStatement = connection.prepareStatement(sqlCommand)) {
                                    try (ResultSet resultSetMaxCountry = getCountryMaxPreparedStatement.executeQuery()) {
                                        resultSetMaxCountry.next();

                                        int countryID = resultSetMaxCountry.getInt(1);
                                        countryID += 1;

                                        if (countryID == 10 || countryID > 10) {
                                            countryID += i;
                                            i++;
                                        }

                                        sqlCommand = "insert into COUNTRY (COUNTRYID, CONTINENTID, NAME) VALUES ('" + countryID + "'," + 1 + ",'" + port.getName() + "')";

                                        try (PreparedStatement updateCOUNTRYPreparedStatement = connection.prepareStatement(sqlCommand)) {
                                            updateCOUNTRYPreparedStatement.executeUpdate();
                                        }

                                        int facilityType = 1; //Port

                                        sqlCommand = "insert into FACILITY(facilityid, facilitytype, countryid, longitude, latitude, name) values ('" + port.getIdentification() + "'," + facilityType + "," + countryID + "," + port.getLocation().getLongitude() + "," + port.getLocation().getLatitude() + ",'" + port.getName() + "')";

                                        try (PreparedStatement insertIntoFacilityPreparedStatement = connection.prepareStatement(sqlCommand)) {
                                            insertIntoFacilityPreparedStatement.executeUpdate();
                                            return true;
                                        }
                                    }
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
                    int countryID = portsResultSet.getInt("COUNTRYID");
                    int continentID = portsResultSet.getInt("CONTINENTID");

                    String countryName = getCountry(databaseConnection, countryID);
                    String continentName = getContinent(databaseConnection, continentID);
                    String portName = portsResultSet.getString("NAME");

                    double portLongitude = portsResultSet.getDouble("LONGITUDE");
                    double portLatitude = portsResultSet.getDouble("LATITUDE");

                    return new Port(countryName, countryName, portIdentification, portName, new FacilityLocation(portLongitude, portLatitude));

                } else return null;
            }
        } catch (SQLException throwables) {
            Logger.getLogger(PortStore.class.getName()).log(Level.SEVERE, null, throwables);
            databaseConnection.registerError(throwables);
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
}

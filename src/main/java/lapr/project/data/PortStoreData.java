/*
package lapr.project.data;

import lapr.project.data.Utils.DataBaseUtils;
import lapr.project.model.Port;
import lapr.project.model.stores.ContainerStore;
import lapr.project.model.stores.PortStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Locale;
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

                            char stringToChar[] = port.getCountry().g;

                            String alpha2Code = Character.toString(stringToChar[0]) + Character.toString(stringToChar[1]);
                            String alpha3Code = alpha2Code + Character.toString(stringToChar[2]);

                            alpha2Code = alpha2Code.toUpperCase(Locale.ROOT);
                            alpha3Code = alpha3Code.toUpperCase(Locale.ROOT);


                            if (resultSetCountry.next()) {

                                sqlCommand = "insert into FACILITY (FACILITYID, ALPHA2CODE, ALPHA3CODE, LONGITUDE, LATITUDE, NAME, CAPACITY) values ('" + port.getIdentification() + "','" + alpha2Code + "','" + alpha3Code + "'," + port.getLocation().getLongitude() + "," + port.getLocation().getLatitude() + ",'" + port.getName() + "','" + 200 + "')";

                                try (PreparedStatement updatePortPreparedStatement = connection.prepareStatement(sqlCommand)) {
                                    updatePortPreparedStatement.executeUpdate();
                                }

                            } else {

                                sqlCommand = "insert into COUNTRY (ALPHA2CODE, ALPHA3CODE, CONTINENT, NAME, CAPITAL, POPULATION) VALUES ('" + alpha2Code + "','" + alpha3Code + "',' " + 1 + "','" + port.getCountry() + "','" + "Invalid" + "','" + 1 + "')";

                                try (PreparedStatement updateCOUNTRYPreparedStatement = connection.prepareStatement(sqlCommand)) {
                                    updateCOUNTRYPreparedStatement.executeUpdate();
                                }

                                sqlCommand = "insert into FACILITY (FACILITYID, ALPHA2CODE, ALPHA3CODE, LONGITUDE, LATITUDE, NAME, CAPACITY) values ('" + port.getIdentification() + "','" + alpha2Code + "','" + alpha3Code + "'," + port.getLocation().getLongitude() + "," + port.getLocation().getLatitude() + ",'" + port.getName() + "','" + 200 + "')";

                                try (PreparedStatement insertIntoFacilityPreparedStatement = connection.prepareStatement(sqlCommand)) {
                                    insertIntoFacilityPreparedStatement.executeUpdate();
                                }

                            }

                            sqlCommand = "insert into PORT (ID, FACILITYID, DOCKINGAREA) values ('" + port.getIdentification() + "','" + port.getIdentification() + "','" + 300 + "')";

                            try (PreparedStatement insertIntoPortPreparedStatement = connection.prepareStatement(sqlCommand)) {
                                insertIntoPortPreparedStatement.executeUpdate();
                                return true;
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

                    return DataBaseUtils.getPort(portIdentification, databaseConnection);

                } else return null;

            }

        } catch (SQLException throwables) {
            Logger.getLogger(PortStore.class.getName()).log(Level.SEVERE, null, throwables);
            databaseConnection.registerError(throwables);
            return null;
        }
    }

    private void fillPortList(DatabaseConnection databaseConnection) {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand;

        sqlCommand = "SELECT * from FACILITY";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                while (resultSet.next()) {


                    String facilityID = resultSet.getString("FACILITYID");

                    listPort.add(DataBaseUtils.getPort(facilityID, databaseConnection));
                }

            }
        } catch (SQLException e) {
            Logger.getLogger(ContainerStore.class.getName()).log(Level.SEVERE, null, e);
            databaseConnection.registerError(e);
        }

    }


    public Set<Port> getListPort(DatabaseConnection databaseConnection) {

        if (listPort.isEmpty()) fillPortList(databaseConnection);

        return listPort;
    }

}
*/

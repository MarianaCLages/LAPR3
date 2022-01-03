package lapr.project.data;

import lapr.project.model.Container;
import lapr.project.model.stores.ContainerStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ContainerStoreData implements Persistable {

    private static int i = 1;

    private final Set<Container> listContainers = new HashSet<>();

    /**
     * Constructor.
     */
    public ContainerStoreData() {
        // Empty constructor
    }

    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {
        Connection connection = databaseConnection.getConnection();
        Container container = (Container) object;

        String sqlCommand;

        sqlCommand = "select * from CONTAINER where CONTAINERID = " + container.getIdentification();

        try (PreparedStatement geContainerPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet addressesResultSet = geContainerPreparedStatement.executeQuery()) {
                if (addressesResultSet.next()) {

                    sqlCommand = "UPDATE CONTAINER SET PAYLOAD = '" + container.getPayload() + "', TARE = '" + container.getTare() + "', GROSS = '" + container.getGross() + "',ISOCODE = '" + container.getIsoCode() + " Where CONTAINERID = '" + container.getIdentification() + "'";

                    try (PreparedStatement saveContainerPreparedStatment = connection.prepareStatement(sqlCommand)) {
                        saveContainerPreparedStatment.executeUpdate();
                        return true;
                    }

                } else {

                    sqlCommand = "select MAX(ID) as ID from ISOCODE";

                    try (PreparedStatement getIsoCodeMaxPreparedStartment = connection.prepareStatement(sqlCommand)) {
                        try (ResultSet resultSetMaxISO = getIsoCodeMaxPreparedStartment.executeQuery()) {
                            resultSetMaxISO.next();

                            int IsoId = resultSetMaxISO.getInt(1);

                            IsoId += 1;

                            if (IsoId == 10 || IsoId > 10) {
                                IsoId += i;
                                i++;
                            }

                            sqlCommand = "insert into ISOCODE (ID, WIDTH, LENGTH, HEIGHT) VALUES ('" + IsoId + "'," + 10 + ",'" + 10 + "'," + 10 + "')";

                            try (PreparedStatement updateISOCODEPreparedStatment = connection.prepareStatement(sqlCommand)) {
                                updateISOCODEPreparedStatment.executeUpdate();
                                return true;
                            }
                        }
                    }
                }
            } catch (SQLException throwables) {
                Logger.getLogger(ContainerStore.class.getName()).log(Level.SEVERE, null, throwables);
                databaseConnection.registerError(throwables);
                return false;
            }
        } catch (SQLException throwables) {
            Logger.getLogger(ContainerStore.class.getName()).log(Level.SEVERE, null, throwables);
            return false;
        }
    }

    @Override
    public Object getElement(DatabaseConnection databaseConnection, Object object) {
        Connection connection = databaseConnection.getConnection();
        String containerIdentification = (String) object;

        String sqlCommand;
        sqlCommand = "SELECT * from CONTAINER where CONTAINERID = '" + containerIdentification + "'";

        try (PreparedStatement getContainerStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet containerResultSet = getContainerStatement.executeQuery()) {
                if (containerResultSet.next()) {
                    int isoCodeId = containerResultSet.getInt("ISOCODE");
                    String containerId = containerResultSet.getString("CONTAINERID");
                    String IsoCodeId = getIsoCode(databaseConnection, isoCodeId);
                    int payload = containerResultSet.getInt("PAYLOAD");
                    int tare = containerResultSet.getInt("TARE");
                    int gross = containerResultSet.getInt("GROSS");

                    return new Container(containerId, payload, tare, gross, IsoCodeId);

                } else return null;
            }
        } catch (SQLException throwables) {
            Logger.getLogger(ContainerStore.class.getName()).log(Level.SEVERE, null, throwables);
            databaseConnection.registerError(throwables);
            return null;
        }
    }


    @Override
    public boolean delete(DatabaseConnection databaseConnection, Object object) {
        Connection connection = databaseConnection.getConnection();
        Container container = (Container) object;

        try {
            String sqlCommand;
            sqlCommand = "delete from CONTAINER where CONTAINERID = '" + container.getIdentification() + "'";
            try (PreparedStatement deleteContainerStatement = connection.prepareStatement(sqlCommand)) {
                deleteContainerStatement.executeUpdate();
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ContainerStore.class.getName()).log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            return false;
        }
    }

    /**
     * Gets the iso code.
     *
     * @param databaseConnection the database connection
     * @param isoCodeId          the iso code ID
     * @return the iso code
     */
    public String getIsoCode(DatabaseConnection databaseConnection, int isoCodeId) {
        Connection connection = databaseConnection.getConnection();

        String sqlCommand;

        sqlCommand = "SELECT * from ISOCODE where ID = " + isoCodeId;

        try (PreparedStatement getIsoCodePreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet isoCodeSet = getIsoCodePreparedStatement.executeQuery()) {
                if (isoCodeSet.next()) {
                    return isoCodeSet.getString("ID");
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ContainerStore.class.getName()).log(Level.SEVERE, null, e);
            databaseConnection.registerError(e);
            return null;
        }
    }

    /**
     * Gets the container list.
     *
     * @param databaseConnection the database connection
     * @return the container list
     */
    public Set<Container> getListContainers(DatabaseConnection databaseConnection) {
        if (listContainers.isEmpty()) {
            fillContainerList(databaseConnection);
        }

        return listContainers;
    }

    /**
     * Fills the container list.
     *
     * @param databaseConnection the database connection
     */
    private void fillContainerList(DatabaseConnection databaseConnection) {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand;

        sqlCommand = "SELECT * from CONTAINER";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    listContainers.add(new Container(resultSet.getString("CONTAINERID"), resultSet.getInt("PAYLOAD"), resultSet.getInt("TARE"), resultSet.getInt("GROSS"), resultSet.getString("CONTAINERID")));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ContainerStore.class.getName()).log(Level.SEVERE, null, e);
            databaseConnection.registerError(e);
        }
    }
}

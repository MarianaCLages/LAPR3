package lapr.project.model.stores;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.Persistable;
import lapr.project.model.*;
import lapr.project.shared.tree.TwoDTree;

import java.sql.*;
import java.time.LocalDateTime;

public class PortStore implements Persistable {

    private final TwoDTree portList;

    public PortStore() {
        portList = new TwoDTree();
    }

    public void add(Port port) {
        portList.insert(port);
    }

    public TwoDTree getPortList() {
        return portList;
    }

    public Port getNearestNeighbourByTime(Ship ship, LocalDateTime dateTime) throws IllegalArgumentException {

        Position position = ship.getPosDate().getPosition(dateTime);

        return portList.nearestNeighborPort(position);

    }


    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {

        Connection connection = databaseConnection.getConnection();
        Port port = (Port) object;

        // saveContinent(databaseConnection, port);
        // saveCountry(databaseConnection,port);

        String sqlCommand = "select * from facility where facilityType = " + port.getClass().getName();

        try (PreparedStatement getContainerPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet portsResultSet = getContainerPreparedStatement.executeQuery()) {

                if (portsResultSet.next()) {

                    sqlCommand = "UPDATE FACILITY SET LONGITUDE = " + port.getLocation().getLongitude() + ", LATITUDE = " + port.getLocation().getLongitude() + " where MMSI = " + port.getName();

                    try (PreparedStatement updatePortPreparedStatement = connection.prepareStatement(sqlCommand)) {
                        updatePortPreparedStatement.executeUpdate();
                        return true;
                    }

                } /*else {

                    int facilityType = 1; //Port

                    sqlCommand = "select MAX(FACILITYID) as facilityid from facility";

                    PreparedStatement saveContainerPreparedStatement = connection.prepareStatement(sqlCommand);
                    resultset = saveContainerPreparedStatement.executeQuery();

                    resultset.next();

                    int idTransportation = resultset.getInt(1) + 1;

                    sqlCommand = "insert into vehicle (vehicleid) values (" + idTransportation + ")";

                    sqlCommand =""

                    return true;

                }

            }/*/

            } catch (SQLException throwables) {
                System.out.println("PORT NAO DA");
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean delete(DatabaseConnection databaseConnection, Object object) {
        return false;
    }
}

   /* private boolean saveContinent(DatabaseConnection databaseConnection, Port port) {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from CONTINENT where NAME = " + port.getContinent();

        try (PreparedStatement getContinentPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getContinentPreparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    //Verificar se é preciso fazer o Update

                    return true;

                } else {

                    sqlCommand = "select MAX(COTINENTID) as continentid from continent";

                    PreparedStatement saveContainerPreparedStatement = connection.prepareStatement(sqlCommand);
                    ResultSet resultset = saveContainerPreparedStatement.executeQuery();

                    resultset.next();

                    int idContinent = resultset.getInt(2) + 1;

                    sqlCommand = "INSERT INTO CONTINENT (CONTINENTID, NAME) VALUES (" + idContinent + ",'" + port.getContinent() + "')";

                    try (PreparedStatement saveContinentPreparedStatement1 = connection.prepareStatement(sqlCommand)) {
                        saveContinentPreparedStatement1.executeUpdate();
                    }

                    return true;

                }

            } catch (SQLException e) {
                System.out.println("NO");
                return false;
            }


        } catch (SQLException throwables) {
            System.out.println("CONTINENT NAO DA");
            return false;
        }

    } */

   /* private boolean saveCountry(DatabaseConnection databaseConnection, Port port) {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from COUNTRY where NAME = " + port.getCountry();

        try (PreparedStatement getCountryPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getCountryPreparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    //Verificar se é preciso fazer o Update

                    sqlCommand = "";

                    return true;

                } else {

                    return true;

                }

            } catch (SQLException e) {
                System.out.println("NO");
                return false;
            }


        } catch (SQLException throwables) {
            System.out.println("CONTINENT NAO DA");
            return false;
        }

    }


    @Override
    public boolean delete(DatabaseConnection databaseConnection, Object object) {
        return false;
    }
}*/



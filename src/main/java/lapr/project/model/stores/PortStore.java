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

        saveContinent(databaseConnection, port);

        String sqlCommand = "select * from facility where shipmmsi = ";

        try (PreparedStatement getContainerPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet addressesResultSet = getContainerPreparedStatement.executeQuery()) {

                return true;

            }

        } catch (SQLException throwables) {
            System.out.println("PORT NAO DA");
            return false;
        }
    }

    private boolean saveContinent(DatabaseConnection databaseConnection, Port port) {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from CONTINENT where NAME = " + port.getContinent();

        try (PreparedStatement getContainerPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet addressesResultSet = getContainerPreparedStatement.executeQuery()) {

                if (addressesResultSet.next()) {


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
}



/*package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.data.CargoManifestStoreData;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ShipStoreData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MatrixFileGenerator {

    private Company company;
    private CargoManifestStoreData cargoManifestStoreData;
    private ShipStoreData shipStoreData;
    private DatabaseConnection databaseConnection;
    private List<CargoManifest> cargoManifestList;

    public MatrixFileGenerator(DatabaseConnection databaseConnection) {
        company = App.getInstance().getCompany();
        this.cargoManifestStoreData = company.getCargoManifestStoreData();
        this.shipStoreData = company.getShipStoreData();
        this.databaseConnection = databaseConnection;
    }

    public Ship getShipByMmsi(int mmsi) throws SQLException {
        return (Ship) shipStoreData.getElement(databaseConnection, mmsi);
        */
        /*
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from SHIP where MMSI = " + mmsi;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    int mmsiS = resultSet.getInt("MMSI");
                    String imo = resultSet.getString("IMO");
                    String callSign = resultSet.getString("CALLSIGN");
                    String name = resultSet.getString("NAME");
                    String vesselType = resultSet.getString("VESSELTYPE");
                    double length = resultSet.getDouble("LENGTH");
                    double width = resultSet.getDouble("WIDTH");
                    double draft = resultSet.getDouble("DRAFT");
                    double capacity = resultSet.getDouble("CAPACITY");

                    Ship ship = new Ship(mmsiS, name, imo, 0, 0, callSign, vesselType, length, width, capacity, draft);

                    return ship;
                } else {
                    return null;
                }
            }
        }
         */
/*
    }


    public int countCargoManifest(int mmsi) throws SQLException {

        Connection connection = databaseConnection.getConnection();


        String sqlCommand = "select count(*) COUNTCARGOMANIFESTS from CARGOMANIFEST cm\n" +
                "inner join SHIP s\n" +
                "on cm.VEHICLEID = s.VEHICLEID\n" +
                "where s.MMSI = " + mmsi;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getInt("COUNTCARGOMANIFESTS");
                } else {
                    return 0;
                }
            }
        }
    }

    public CargoManifest getCargoManifestByMmsi(int mmsi, int j, Ship s) throws SQLException {

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

    public int countContainerByCargoManifest(String id) throws SQLException {

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

    public Container getContainerByCargo(String id, int j) throws SQLException {

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


    public boolean generateMatrixFile(int mmsi) throws SQLException {


        Ship s = getShipByMmsi(mmsi);
        int count = countCargoManifest(mmsi);
        int countContainers;


        if (count == 0) {
            return false;
        }

        while (count != 0) {
            cargoManifestList.add(getCargoManifestByMmsi(mmsi, count, s));
            count--;
        }

        for (CargoManifest cm : cargoManifestList) {

            countContainers = countContainerByCargoManifest(cm.getIdentification());

            while (countContainers != 0) {

                Container c = getContainerByCargo(cm.getIdentification(), countContainers);
                cm.addContainersLoaded(c);
                countContainers--;
            }

        }


        try {

            File myObj = new File("container.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File updated!");
            }


            FileWriter myWriter = new FileWriter(myObj);

            for (CargoManifest cm : cargoManifestList) {
                for (Container container : cm.getOffloaded().inOrder()) {

                    myWriter.write(container.getPosition().xPos + "," + container.getPosition().yPos + "," + container.getPosition().zPos + "," + container.getIdentification() + "\n");

                }

                for (CargoManifest cargoManifest : cargoManifestList) {
                    for (Container container : cargoManifest.getLoaded().inOrder()) {

                        myWriter.write(container.getPosition().xPos + "," + container.getPosition().yPos + "," + container.getPosition().zPos + "," + container.getIdentification() + "\n");

                    }
                    myWriter.close();
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error has occurred!");
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
}
*/
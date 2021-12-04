package lapr.project.data;

import lapr.project.data.Utils.DataBaseUtils;
import lapr.project.model.CargoManifest;
import lapr.project.model.Port;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CargoManifestStoreData implements Persistable {

    private final Set<CargoManifest> listCargoManifest;

    public CargoManifestStoreData() {
        this.listCargoManifest = new HashSet<>();
    }

    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {
        return false;
    }

    @Override
    public boolean delete(DatabaseConnection databaseConnection, Object object) {
        return false;
    }

    @Override
    public Object getElement(DatabaseConnection databaseConnection, Object object) {
        return null;
    }

    public Set<CargoManifest> getListCargoManifest(DatabaseConnection databaseConnection) {

        if (listCargoManifest.isEmpty()) fillCargoManifestList(databaseConnection);

        return listCargoManifest;
    }

    private void fillCargoManifestList(DatabaseConnection databaseConnection) {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand;

        sqlCommand = "SELECT * from CARGOMANIFEST";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String identification = resultSet.getString("CARGOMANIFESTID");

                    Port port = DataBaseUtils.getPort(resultSet.getString("FACILITYID"), databaseConnection);

                    Date date = (Date) resultSet.getObject("CARGOMANIFESTDATE");

                    CargoManifest cargoManifest = new CargoManifest(identification, port, date);
                    listCargoManifest.add(cargoManifest);
                }

            }
        } catch (SQLException e) {
            Logger.getLogger(CargoManifestStoreData.class.getName()).log(Level.SEVERE, null, e);
            databaseConnection.registerError(e);
        }

    }


}
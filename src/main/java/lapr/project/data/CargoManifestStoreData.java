package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.CargoManifest;
import lapr.project.model.Container;
import lapr.project.model.stores.ContainerStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public Set<CargoManifest> getListCargoManifest() {

        if (listCargoManifest.isEmpty()) fillCargoManifestList();

        return listCargoManifest;
    }

    private void fillCargoManifestList() {

        DatabaseConnection databaseConnection = App.getInstance().getDatabaseConnection();

        Connection connection = databaseConnection.getConnection();

        String sqlCommand;

        sqlCommand = "SELECT * from CARGOMANIFEST";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    //  listCargoManifest.add(new CargoManifest(resultSet.getString("CARGOMANIFESTID"),);
                }

            }
        } catch (SQLException e) {
            Logger.getLogger(CargoManifestStoreData.class.getName()).log(Level.SEVERE, null, e);
            databaseConnection.registerError(e);
        }

    }


}
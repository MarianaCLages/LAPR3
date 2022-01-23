package lapr.project.data;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    /**
     * @author nunocastro
     */

    private OracleDataSource oracleDataSource;
    private Connection connection;
    private SQLException error;

    public DatabaseConnection(String url, String username, String password) {
        try {
            oracleDataSource = new OracleDataSource();

            oracleDataSource.setURL(url);

            connection = oracleDataSource.getConnection(username, password);

        } catch (SQLException e) {
            System.out.println("\nThere was an error while trying to connect to the database! Please verify if you have your internet on!");
        }
    }

    public Connection getConnection() {
        try {
            if (connection == null) {
                throw new RuntimeException("Connection does not exit");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public void registerError(SQLException error) {
        this.error = error;
    }

    public SQLException getLastError() {
        SQLException lastError = this.error;
        //Clear after reading.
        registerError(null);
        return lastError;
    }
}
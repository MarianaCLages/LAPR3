package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.model.Company;
import lapr.project.shared.graph.FreightNetwork;
import lapr.project.shared.exceptions.NullVerticesException;

public class CreateFreightNetworkController {

    private final DatabaseConnection connection;
    private final FreightNetwork freightNetwork;

    /**
     * Constructor.
     */
    public CreateFreightNetworkController() {
        Company company = App.getInstance().getCompany();
        this.freightNetwork = company.getFreightNetwork();
        this.connection = App.getInstance().getDatabaseConnection();
    }

    /**
     * Creates the graph.
     *
     * @param n the N closest ports
     * @return true if it succeeds, false if it doesn't
     */
    public boolean createGraph(int n) {
        try {
            return this.freightNetwork.createGraph(n, this.connection);
        } catch (NullVerticesException e) {
            return false;
        }
    }
}

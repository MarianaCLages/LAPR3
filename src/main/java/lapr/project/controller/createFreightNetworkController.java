package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.model.Company;
import lapr.project.model.FreightNetwork;
import lapr.project.shared.exceptions.NullVerticesException;

public class createFreightNetworkController {
    private Company company;
    private DatabaseConnection connection;
    private FreightNetwork freightNetwork;

    public createFreightNetworkController() {
        this.company = App.getInstance().getCompany();
        this.freightNetwork = company.getFreightNetwork();
        this.connection = App.getInstance().getDatabaseConnection();
    }


    public boolean createGraph(int n) {
        try {
            return this.freightNetwork.createGraph(n, this.connection);
        } catch (NullVerticesException e) {
            return false;
        }
    }


}

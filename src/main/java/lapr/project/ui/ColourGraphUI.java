package lapr.project.ui;

import lapr.project.controller.App;
import lapr.project.controller.ColourGraphController;
import lapr.project.model.Company;
import lapr.project.shared.exceptions.SetColoursException;
import lapr.project.shared.graph.FreightNetwork;

public class ColourGraphUI implements Runnable {

    Company company;
    FreightNetwork freightNetwork;
    ColourGraphController controller;

    public ColourGraphUI() {
        this.controller = new ColourGraphController();
        this.company = App.getInstance().getCompany();
        this.freightNetwork = company.getFreightNetwork();
    }

    public void run() {
        try {
            System.out.println(controller.colourGraph(freightNetwork.getGraph()));
        } catch (SetColoursException ex1) {
            System.out.println(ex1.getMessage());
        } catch (Exception ex2) {
            System.out.println("Something went wrong. Please, try again.");
        }
    }
}
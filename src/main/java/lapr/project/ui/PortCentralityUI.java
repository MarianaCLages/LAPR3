/*package lapr.project.ui;

import lapr.project.controller.App;
import lapr.project.controller.PortCentralityController;
import lapr.project.model.Company;
import lapr.project.shared.graph.FreightNetwork;

public class PortCentralityUI implements Runnable {

    Company company;
    FreightNetwork freightNetwork;
    PortCentralityController controller;

    public PortCentralityUI() {
        this.company = App.getInstance().getCompany();
        this.freightNetwork = company.getFreightNetwork();
        this.controller = new PortCentralityController();
    }

    public void run() {
        int numberOfNPorts;

        do {
            try {
                numberOfNPorts = Utils.readIntegerFromConsole("Please type the N number of ports:");
                if (numberOfNPorts < 0)
                    throw new NumberFormatException("Please enter a valid number! (No negative numbers! It must be above 0)");
            } catch (NumberFormatException ex1) {
                System.out.println(ex1.getMessage());
                numberOfNPorts = -1;
            } catch (IllegalArgumentException ex2) {
                System.out.println("Please enter a number! (Don't enter a letter nor a symbol!)");
                numberOfNPorts = -1;
            }
        } while (numberOfNPorts == -1);

        try {
            System.out.println(controller.getCriticalPorts(freightNetwork.getGraph(), numberOfNPorts));
        } catch (Exception exception) {
            System.out.println("Something went wrong. Please, try again.");
        }
    }
}
*/
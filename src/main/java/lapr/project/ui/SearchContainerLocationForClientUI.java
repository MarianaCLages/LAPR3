package lapr.project.ui;

import lapr.project.controller.SearchContainerLocationForClientController;
import lapr.project.model.CargoManifest;
import lapr.project.model.Container;
import lapr.project.model.FacilityLocation;
import lapr.project.model.Position;

import java.util.ArrayList;

public class SearchContainerLocationForClientUI implements Runnable {

    private SearchContainerLocationForClientController ctrl = new SearchContainerLocationForClientController();

    @Override
    public void run() {

        String clientID = null;
        ArrayList<String> lContainer;
        Container rContainer;
        CargoManifest rCargoManifest;

        do try {
            clientID = Utils.readLineFromConsole("Please enter your Client's ID: ");

            if (clientID.isEmpty()) {
                throw new NullPointerException("Invalid Client!!");
            }

        } catch (NullPointerException e) {
            System.out.print("Please enter a valid Client's ID!\n");

        } while (clientID.isEmpty());

        System.out.print("\n\n");

        try {
            lContainer = ctrl.getClientContainers(clientID);
            rContainer = (Container) Utils.selectsObject(lContainer);

            rCargoManifest = ctrl.findContainerVessel(rContainer);
            System.out.println(ctrl.printLocation(rCargoManifest));


        } catch (NullPointerException e2){
            System.out.print("Container is invalid!\n");
        }
    }
}

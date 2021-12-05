package lapr.project.ui;

import lapr.project.controller.SearchContainerLocationForClientController;
import lapr.project.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SearchContainerLocationForClientUI implements Runnable {

    private SearchContainerLocationForClientController ctrl = new SearchContainerLocationForClientController();

    @Override
    public void run() {

        String clientID = null;
        ArrayList<String> lContainer;
        Container rContainer;
        CargoManifest rCargoManifest;
        FacilityLocation rLocation = null;
        Position rPosition = null;

        do try {
            clientID = Utils.readLineFromConsole("Please enter your Client's ID: ");

            if (clientID.isEmpty()) {
                throw new NullPointerException("Invalid Client!!");
            }

        } catch (NullPointerException e) {
            System.out.print("Please enter a valid Client's ID!\n");

        } while(clientID.isEmpty());

        System.out.print("\n\n");

        try{
            lContainer = ctrl.getClientContainers(clientID);
            rContainer = (Container) Utils.selectsObject(lContainer);

            rCargoManifest = ctrl.findContainerVessel(rContainer);

            if (rCargoManifest.getInTransport()){
                System.out.println("Container is aboard the: " + rCargoManifest.getShip().getCallSign());
                System.out.println("Coordinates: \n" +
                        "   Lat : " + rPosition.getLatitude() + "\n" +
                        "   Long: " + rPosition.getLongitude() + "\n");
            } else {
                System.out.println("Container is currently stored in: " + rCargoManifest.getPort().getIdentification());
                System.out.println("Coordinates: \n" +
                        "   Lat : " + rLocation.getLatitude() + "\n" +
                        "   Long: " + rLocation.getLongitude() + "\n");
            }
        } catch (NullPointerException e2){
            System.out.print("Container doesn't exist/is invalid!!");
        }
    }
}

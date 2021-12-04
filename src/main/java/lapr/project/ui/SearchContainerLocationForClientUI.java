package lapr.project.ui;

import lapr.project.controller.SearchContainerLocationForClientController;
import lapr.project.model.*;

import javax.xml.stream.Location;
import java.util.List;
import java.util.NoSuchElementException;

public class SearchContainerLocationForClientUI implements Runnable {

    private SearchContainerLocationForClientController ctrl = new SearchContainerLocationForClientController();

    @Override
    public void run() {

        Client client = null;
        String clientID = null;
        List<Container> lContainer;
        Container rContainer;
        CargoManifest rCargoManifest = null;
        FacilityLocation rLocation = null;
        Position rPosition = null;

        do {
            try {
                clientID = Utils.readLineFromConsole("Please enter your Client's email: ");
                //client = ctrl.getClientFromClientID(clientID);

                if (clientID.equals(null) /*&& client.equals(null)*/) {
                    throw new NoSuchElementException("Invalid Client!!");
                }

            } catch (NullPointerException e) {
                System.out.print("Please enter a valid Client's email adress!\n");

            }
        }while(clientID.isEmpty() && client.equals(null));

        System.out.print("\n\n");

        try{
            lContainer = ctrl.getClientContainers(client);
            rContainer = (Container) Utils.selectsObject(lContainer);

            rCargoManifest = ctrl.findContainerVessel(rContainer);

            if (rCargoManifest.getInTransport()){
                //rPosition = rCargoManifest.getShip().
            } else {
                rLocation = rCargoManifest.getPort().getLocation();
            }
        } catch (NullPointerException e2){
            System.out.print("Container doesn't exist/is invalid!!");
        }

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
    }
}

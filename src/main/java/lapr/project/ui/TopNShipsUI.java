package lapr.project.ui;

import lapr.project.controller.TopNShipsController;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TopNShipsUI implements Runnable {
    TopNShipsController topNShipsController;
    ShipStore shipStore;

    public TopNShipsUI() {
        this.topNShipsController = new TopNShipsController();
        this.shipStore = new ShipStore();
    }

    @Override
    public void run() {

        int n;
        Scanner read = new Scanner(System.in);
        String date;
        String vesselType;

        System.out.println("Please, insert the top-N value: ");
        n = read.nextInt();

        System.out.println("Please, insert the Vessel Type: ");
        vesselType = Utils.readLineFromConsole("");

        System.out.print("Please, insert the initial date (yyyy-mm-dd HH:mm:ss): ");
        date = Utils.readLineFromConsole("");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime datei = LocalDateTime.parse(date, dateFormat);

        System.out.print("Please, insert the final date (yyyy-mm-dd HH:mm:ss): ");
        date = Utils.readLineFromConsole("");

        LocalDateTime datef = LocalDateTime.parse(date, dateFormat);

        List<Ship> lShip = null;

        if (topNShipsController.getShipStore().transformAVLintoListMMSI().size() > 10)
            System.out.println("(This operation might take a while!)\n\n");

        try {
            lShip = topNShipsController.getTopNShips(n, vesselType, datei, datef);
        } catch (IllegalArgumentException ex) {
            System.out.println("Not enough ships for this operation");
        }

        if (lShip == null) {
            System.out.println("Not successful... Please, try again.");
        } else {
            for (Ship s : lShip) {
                System.out.format("MMSI: %d ; Vessel Type: %s ; Travelled Distance: %.2f ; Mean SOG: %.2f\n", s.getMmsi(), s.getVesselType(), s.getTravelledDistance(), shipStore.getMeanSOG(s));
            }
        }
    }
}

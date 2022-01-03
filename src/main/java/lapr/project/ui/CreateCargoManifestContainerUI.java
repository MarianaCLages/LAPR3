package lapr.project.ui;

import lapr.project.controller.CreateACargoManifestForWarehouseFullController;
import lapr.project.shared.exceptions.InvalidCargoManifestException;
import lapr.project.shared.exceptions.InvalidContainerException;

import java.sql.SQLException;

public class CreateCargoManifestContainerUI implements Runnable {


    public CreateCargoManifestContainerUI() {
        //Empty
    }

    private final CreateACargoManifestForWarehouseFullController createACargoManifestForWarehouseFullController = new CreateACargoManifestForWarehouseFullController();

    @Override
    public void run() {

        String cargoManifestID;
        String containerID;

        int xPos;
        int yPos;
        int zPos;

        do {
            try {
                cargoManifestID = Utils.readLineFromConsole("Please enter the cargo manifest ID:");
                if (cargoManifestID == null || cargoManifestID.equals("")) throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter a valid option!");
                cargoManifestID = null;
            }
        } while (cargoManifestID == null);


        do {
            try {
                containerID = Utils.readLineFromConsole("Please enter the container ID:");
                if (containerID == null || containerID.equals("")) throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter a valid option!");
                containerID = null;
            }
        } while (containerID == null);


        do {
            try {
                xPos = Utils.readIntegerFromConsole("Enter the container's x position");
                if (xPos < 0) throw new NullPointerException();
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter a number, not a symbol or a letter!");
                xPos = -99;
            } catch (NullPointerException e) {
                System.out.println("Please enter a positive number! There can not be negative positions!");
                xPos = -99;
            }
        } while (xPos == -99);

        do {
            try {
                yPos = Utils.readIntegerFromConsole("Please enter the container's y position:");
                if (yPos < 0) throw new NullPointerException();
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter a number, not a symbol or a letter!");
                yPos = -99;
            } catch (NullPointerException e) {
                System.out.println("Please enter a positive number! There can not be negative positions!");
                yPos = -99;
            }
        } while (yPos == -99);

        do {
            try {
                zPos = Utils.readIntegerFromConsole("Please enter the container's z position:");
                if (zPos < 0) throw new NullPointerException();
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter a number, not a symbol or a letter!");
                zPos = -99;
            } catch (NullPointerException e) {
                System.out.println("Please enter a positive number! There can not be negative positions!");
                zPos = -99;
            }
        } while (zPos == -99);


        try {
            createACargoManifestForWarehouseFullController.createCMC(cargoManifestID, containerID, xPos, yPos, zPos);
        } catch (InvalidCargoManifestException | InvalidContainerException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e){
            System.out.println("That container already exists inside the specified cargo manifest!");
        }
    }
}

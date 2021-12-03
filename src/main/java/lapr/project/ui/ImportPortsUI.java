package lapr.project.ui;

import lapr.project.controller.ImportPortsController;

import java.io.FileNotFoundException;

public class ImportPortsUI implements Runnable {

    public void run() {

        boolean readOptionBoolean = false;
        String fileName;

        ImportPortsController controller = new ImportPortsController();

        do {
            try {
                String readOption = Utils.readLineFromConsole("Do you wish to import a file (Y - yes/ N - no)");
                if (readOption.equals("Y")) {
                    readOptionBoolean = true;
                    fileName = Utils.readLineFromConsole("Please enter the file path");
                    controller.importPorts(fileName);
                } else if (readOption.equals("N")) {
                    readOptionBoolean = true;
                } else if (!readOption.equals("Y") || !readOption.equals("N")) throw new IllegalArgumentException();

            } catch (IllegalArgumentException ex1) {
                System.out.println("Please enter a valid option! (Y - yes / N - no)");
            } catch (FileNotFoundException ex2) {
                System.out.println("Please enter a valid file! (See the file names before entering one)");
            }
        } while (readOptionBoolean == false);
    }
}

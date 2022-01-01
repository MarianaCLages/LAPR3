package lapr.project.ui;

import lapr.project.controller.CreateFreightNetworkController;
import org.apache.commons.lang3.StringUtils;

public class FreightNetworkUI implements Runnable {

    private final CreateFreightNetworkController controller;

    public FreightNetworkUI() {
        this.controller = new CreateFreightNetworkController();
    }

    @Override
    public void run() {
        boolean readOptionBoolean;

        do {
            try {
                String readOption = Utils.readLineFromConsole("What is your number of connections of a port?");
                if (StringUtils.isNumeric(readOption)) {
                    controller.createGraph(Integer.parseInt(readOption));
                    readOptionBoolean = true;
                } else throw new IllegalArgumentException();

            } catch (IllegalArgumentException ex1) {
                System.out.println("Please enter a valid option!");
                readOptionBoolean = false;
            }
        } while (!readOptionBoolean);
    }
}

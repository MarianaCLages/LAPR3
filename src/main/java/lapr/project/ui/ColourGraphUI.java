package lapr.project.ui;

import lapr.project.controller.ColourGraphController;

public class ColourGraphUI implements Runnable {

    ColourGraphController controller;

    public ColourGraphUI() {
        this.controller = new ColourGraphController();
    }

    public void run() {
        try {
            controller.colourGraph();
        } catch (Exception e) {
            System.out.println("Something went wrong. Please, try again.");
        }
    }
}
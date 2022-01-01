package lapr.project.ui;

import lapr.project.controller.ColourGraphController;
import lapr.project.shared.exceptions.SetColoursException;

public class ColourGraphUI implements Runnable {

    ColourGraphController controller;

    public ColourGraphUI() {
        this.controller = new ColourGraphController();
    }

    public void run() {
        try {
            System.out.println(controller.colourGraph());
        } catch (SetColoursException ex1) {
            System.out.println(ex1.getMessage());
        } catch (Exception ex2) {
            System.out.println("Something went wrong. Please, try again.");
        }
    }
}
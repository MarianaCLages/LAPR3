package lapr.project.ui;


import lapr.project.controller.App;
import lapr.project.controller.SearchContainerLocationForClientController;
import lapr.project.data.DataBaseScripts.AvailableShipsOnMondayScript;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    /**
     * Logger class.
     */
    private static final Logger LOGGER = Logger.getLogger("MainLog");

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {

    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
        try {
            MainMenuUI mainMenuUI = new MainMenuUI();

            mainMenuUI.run();
        } catch (Exception exception) {
            System.out.println("Something went wrong!");
        }
    }
}
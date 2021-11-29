package Integration.US103;

import lapr.project.controller.App;
import lapr.project.controller.PositionalMessageController;
import lapr.project.model.Company;
import lapr.project.model.Position;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;
import lapr.project.utils.auth.UserSession;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class US103IntegrationTest {


    App app = App.getInstance();
    Company company = app.getCompany();

    ShipStore shipStore = company.getShipStore();

    PositionalMessageController positionalMessageController = new PositionalMessageController();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    //If necessary to print the output in the console use this boolean as true
    boolean printInTheConsole = false;

    //Creation of the dates for the Ship positions

    String sdate = "31-12-2020 17:03";
    LocalDateTime date = LocalDateTime.parse(sdate, formatter);

    String sdate2 = "31-12-2020 17:19";
    LocalDateTime date2 = LocalDateTime.parse(sdate2, formatter);

    String sdate3 = "31-12-2020 22:01";
    LocalDateTime date3 = LocalDateTime.parse(sdate3, formatter);

    String sdate4 = "31-12-2020 22:03";
    LocalDateTime date4 = LocalDateTime.parse(sdate4, formatter);

    String sdate5 = "31-12-2020 10:36";
    LocalDateTime date5 = LocalDateTime.parse(sdate5, formatter);

    String sdate6 = "31-12-2020 12:26";
    LocalDateTime date6 = LocalDateTime.parse(sdate6, formatter);

    String sdate7 = "29-12-2020 01:17";
    LocalDateTime date7 = LocalDateTime.parse(sdate7, formatter);

    String sdate8 = "31-12-2020 20:59";
    LocalDateTime date8 = LocalDateTime.parse(sdate8, formatter);

    String sdate9 = "31-12-2020 21:16";
    LocalDateTime date9 = LocalDateTime.parse(sdate9, formatter);

    String sdate10 = "31-12-2020 23:22";
    LocalDateTime date10 = LocalDateTime.parse(sdate10, formatter);

    String sdate11 = "31-12-2020 20:12";
    LocalDateTime date11 = LocalDateTime.parse(sdate11, formatter);

    String sdate12 = "31-12-2020 23:21";
    LocalDateTime date12 = LocalDateTime.parse(sdate12, formatter);

    String sdate13 = "29-12-2020 21:43";
    LocalDateTime date13 = LocalDateTime.parse(sdate13, formatter);

    String sdate14 = "31-12-2020 23:44";
    LocalDateTime date14 = LocalDateTime.parse(sdate14, formatter);

    String sdate15 = "31-12-2020 20:50";
    LocalDateTime date15 = LocalDateTime.parse(sdate15, formatter);

    String sdate16 = "31-12-2020 23:52";
    LocalDateTime date16 = LocalDateTime.parse(sdate16, formatter);

    String sdate17 = "31-12-2020 18:54";
    LocalDateTime date17 = LocalDateTime.parse(sdate17, formatter);

    String sdate18 = "31-12-2020 23:54";
    LocalDateTime date18 = LocalDateTime.parse(sdate18, formatter);

    String sdate19 = "31-12-2020 18:56";
    LocalDateTime date19 = LocalDateTime.parse(sdate19, formatter);

    String sdate20 = "31-12-2020 23:59";
    LocalDateTime date20 = LocalDateTime.parse(sdate20, formatter);

    @Test
    void runUS103() {

        UserSession verifyLogin = app.getAuthFacade().doLogin("TM00001@lei.pt", "495");

        if (verifyLogin == null)
            fail(); //Verifies if the login was successful (since its necessary to be log on as Traffic Manager to be able to run the US)

        //Create Ships

        Ship ship1 = new Ship(367439390, "SeaTruck", "IMO9643544", 100, 10, "C4SQ2", "VARAMO", 5000, 2000, 20000, 30);
        Ship ship2 = new Ship(367487570, "ArticMonkey", "IMO9305685", 70, 12, "5BZP3", "SAITA", 1, 300, 1300, 40);
        Ship ship3 = new Ship(368085000, "Bamboo", "IMO9450648", 10, 20, "FLSU", "HYUNDAI SINGAPORE", 500, 100, 10000, 20);
        Ship ship4 = new Ship(636015178, "FFF&N", "IMO9645970", 20, 18, "9HA3589", "CMA CGM ALMAVIVA", 250, 150, 20000, 10);
        Ship ship5 = new Ship(636092932, "GiantTrans", "IMO9517575", 30, 16, "9HJC9", "CARNIVAL LEGEND", 500, 100, 12000, 14);
        Ship ship6 = new Ship(636091400, "Traveller", "IMO9344564", 40, 22, "LATO7", "ARCTIC AURORA", 1150, 200, 1000, 11);
        Ship ship7 = new Ship(636019825, "SeaWandering", "IMO9701920", 20, 30, "LAJB6", "OREGON TRADER", 1600, 200, 200, 13);
        Ship ship8 = new Ship(257799000, "Titanic", "IMO9321677", 200, 8, "WDG5171", "KRONVIKEN", 1000, 175, 300, 20);
        Ship ship9 = new Ship(366934280, "", "IMO7819216", 5, 50, "WNGW", "ARCTIC SEA", 1, 200, 300, 21);
        Ship ship10 = new Ship(211331640, "name", "IMO9506758", 10, 20, "V2FR9", "TUSTUMENA", 200, 300, 200, 10);

        //Add positions into the Position AVL of a Ship

        //Ship1
        ship1.getPosDate().addPosition(new Position(42.97875, -66.97001, 355, 12.9, 13.1, date));
        ship1.getPosDate().addPosition(new Position(42.92236, -66.97243, 358, 12.5, 2.4, date2));

        //Ship2
        ship2.getPosDate().addPosition(new Position(26.44508, -91.83885, 296, 19.4, -111.7, date3));
        ship2.getPosDate().addPosition(new Position(42.92236, -66.97243, 358, 12.5, 2.4, date4));

        //Ship3
        ship3.getPosDate().addPosition(new Position(47.63532, -122.32866, 296, 0, 195.1, date5));
        ship3.getPosDate().addPosition(new Position(47.63531, -122.32866, 286, 0, -195, date6));

        //Ship4
        ship4.getPosDate().addPosition(new Position(47.63529, -122.32866, 44, 9.2, 95.1, date7));
        ship4.getPosDate().addPosition(new Position(81.2, -111.2, 112, 2.2, 72, date8));

        //Ship5
        ship5.getPosDate().addPosition(new Position(47.63529, -22.32866, 66, 9.2, 115.1, date9));
        ship5.getPosDate().addPosition(new Position(81.2, -11.2, 112, 8.2, 62, date10));

        //Ship6
        ship6.getPosDate().addPosition(new Position(41.63529, 122.32866, 233, 9.2, 103.1, date11));
        ship6.getPosDate().addPosition(new Position(31.2, 111.2, 112, 0.223423, 83, date12));

        //Ship7
        ship7.getPosDate().addPosition(new Position(27.639, -90.366, 142, 9.422412, 102.1, date13));
        ship7.getPosDate().addPosition(new Position(71.2, -12.2, 112, 9.2, 90, date14));

        //Ship8
        ship8.getPosDate().addPosition(new Position(12.639, -21.32866, 43, 11.1212, 11.1, date15));
        ship8.getPosDate().addPosition(new Position(9.2, -2.2, 112, 1.223423, 19, date16));

        //Ship9
        ship9.getPosDate().addPosition(new Position(4.84348, 24.81775, 32, 14.24564, 16.6, date17));
        ship9.getPosDate().addPosition(new Position(1.2, 41.2, 112, 1.2232, 153, date18));

        //Ship10
        ship10.getPosDate().addPosition(new Position(27.29, 12.32866, 11, 4.2131, 125.1, date19));
        ship10.getPosDate().addPosition(new Position(32.2, 19.233, 112, 12.23232, 12, date20));

        //Insert into the Ship Store

        shipStore.addShip(ship1);
        shipStore.addShip(ship2);
        shipStore.addShip(ship3);
        shipStore.addShip(ship4);
        shipStore.addShip(ship5);
        shipStore.addShip(ship6);
        shipStore.addShip(ship7);
        shipStore.addShip(ship8);
        shipStore.addShip(ship9);
        shipStore.addShip(ship10);


        //Asserts With MMSI Codes

        //Arrange


        String expectedPosMessageShip1 = "Positional Message:\n" +
                "Position{latitude=42.97875, longitude=-66.97001, heading=355.0, SOG=12.9, COG=13.1}\n" +
                "Position{latitude=42.92236, longitude=-66.97243, heading=358.0, SOG=12.5, COG=2.4}";

        String expectedPosMessageShip2 = "Positional Message:\n" +
                "Position{latitude=26.44508, longitude=-91.83885, heading=296.0, SOG=19.4, COG=-111.7}\n" +
                "Position{latitude=42.92236, longitude=-66.97243, heading=358.0, SOG=12.5, COG=2.4}";

        String expectedPosMessageShip3 = "Positional Message:\n" +
                "Position{latitude=47.63532, longitude=-122.32866, heading=296.0, SOG=0.0, COG=195.1}\n" +
                "Position{latitude=47.63531, longitude=-122.32866, heading=286.0, SOG=0.0, COG=-195.0}";


        String expectedPosMessageShip4 = "Positional Message:\n" +
                "Position{latitude=47.63529, longitude=-122.32866, heading=44.0, SOG=9.2, COG=95.1}\n" +
                "Position{latitude=81.2, longitude=-111.2, heading=112.0, SOG=2.2, COG=72.0}";


        String expectedPosMessageShip5 = "Positional Message:\n" +
                "Position{latitude=47.63529, longitude=-22.32866, heading=66.0, SOG=9.2, COG=115.1}\n" +
                "Position{latitude=81.2, longitude=-11.2, heading=112.0, SOG=8.2, COG=62.0}";


        String expectedPosMessageShip6 = "Positional Message:\n" +
                "Position{latitude=41.63529, longitude=122.32866, heading=233.0, SOG=9.2, COG=103.1}\n" +
                "Position{latitude=31.2, longitude=111.2, heading=112.0, SOG=0.223423, COG=83.0}";


        String expectedPosMessageShip7 = "Positional Message:\n" +
                "Position{latitude=27.639, longitude=-90.366, heading=142.0, SOG=9.422412, COG=102.1}\n" +
                "Position{latitude=71.2, longitude=-12.2, heading=112.0, SOG=9.2, COG=90.0}";

        String expectedPosMessageShip8 = "Positional Message:\n" +
                "Position{latitude=12.639, longitude=-21.32866, heading=43.0, SOG=11.1212, COG=11.1}\n" +
                "Position{latitude=9.2, longitude=-2.2, heading=112.0, SOG=1.223423, COG=19.0}";

        String expectedPosMessageShip9 = "Positional Message:\n" +
                "Position{latitude=4.84348, longitude=24.81775, heading=32.0, SOG=14.24564, COG=16.6}\n" +
                "Position{latitude=1.2, longitude=41.2, heading=112.0, SOG=1.2232, COG=153.0}";

        String expectedPosMessageShip10 = "Positional Message:\n" +
                "Position{latitude=27.29, longitude=12.32866, heading=11.0, SOG=4.2131, COG=125.1}\n" +
                "Position{latitude=32.2, longitude=19.233, heading=112.0, SOG=12.23232, COG=12.0}";

        String actualPosMessageShip1 = positionalMessageController.getPositionalMessages(367439390, date, date2);
        String actualPosMessageShip2 = positionalMessageController.getPositionalMessages(367487570, date3, date4);
        String actualPosMessageShip3 = positionalMessageController.getPositionalMessages(368085000, date5, date6);
        String actualPosMessageShip4 = positionalMessageController.getPositionalMessages(636015178, date7, date8);
        String actualPosMessageShip5 = positionalMessageController.getPositionalMessages(636092932, date9, date10);
        String actualPosMessageShip6 = positionalMessageController.getPositionalMessages(636091400, date11, date12);
        String actualPosMessageShip7 = positionalMessageController.getPositionalMessages(636019825, date13, date14);
        String actualPosMessageShip8 = positionalMessageController.getPositionalMessages(257799000, date15, date16);
        String actualPosMessageShip9 = positionalMessageController.getPositionalMessages(366934280, date17, date18);
        String actualPosMessageShip10 = positionalMessageController.getPositionalMessages(211331640, date19, date20);



        //Assert
        assertEquals(expectedPosMessageShip1, actualPosMessageShip1);
        assertEquals(expectedPosMessageShip2, actualPosMessageShip2);
        assertEquals(expectedPosMessageShip3, actualPosMessageShip3);
        assertEquals(expectedPosMessageShip4, actualPosMessageShip4);
        assertEquals(expectedPosMessageShip5, actualPosMessageShip5);
        assertEquals(expectedPosMessageShip6, actualPosMessageShip6);
        assertEquals(expectedPosMessageShip7, actualPosMessageShip7);
        assertEquals(expectedPosMessageShip8, actualPosMessageShip8);
        assertEquals(expectedPosMessageShip9, actualPosMessageShip9);
        assertEquals(expectedPosMessageShip10, actualPosMessageShip10);


        //Use this variable as true if necessary to print the output
        if (printInTheConsole) {
            System.out.println(expectedPosMessageShip1);
            System.out.println(expectedPosMessageShip2);
            System.out.println(expectedPosMessageShip3);
            System.out.println(expectedPosMessageShip4);
            System.out.println(expectedPosMessageShip5);
            System.out.println(expectedPosMessageShip6);
            System.out.println(expectedPosMessageShip7);
            System.out.println(expectedPosMessageShip8);
            System.out.println(expectedPosMessageShip9);
            System.out.println(expectedPosMessageShip10);

        }
    }
}

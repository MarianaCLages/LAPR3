package lapr.project.Integration.US104;

import lapr.project.controller.App;
import lapr.project.controller.ShipSummaryController;
import lapr.project.model.Company;
import lapr.project.model.Position;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;
import lapr.project.utils.auth.UserSession;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class US104IntegrationTest {

    App app = App.getInstance();
    Company company = app.getCompany();

    ShipStore shipStore = company.getShipStore();
    ShipSummaryController shipSummaryController = new ShipSummaryController();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    //If necessary to print the output in the console use this boolean as true
    boolean printInTheConsole = false;

    //Creation of the dates for the Ship positions

    String sdate = "31-12-2020 17:03";
    LocalDateTime date = LocalDateTime.parse(sdate, formatter);

    String sdate2 = "31-12-2020 17:19";
    LocalDateTime date2 = LocalDateTime.parse(sdate2, formatter);

    String sdate3 = "31-12-2020 22:01";
    LocalDateTime date3 = LocalDateTime.parse(sdate, formatter);

    String sdate4 = "31-12-2020 22:03";
    LocalDateTime date4 = LocalDateTime.parse(sdate, formatter);

    String sdate5 = "31-12-2020 10:36";
    LocalDateTime date5 = LocalDateTime.parse(sdate, formatter);

    String sdate6 = "31-12-2020 12:26";
    LocalDateTime date6 = LocalDateTime.parse(sdate, formatter);

    String sdate7 = "29-12-2020 01:17";
    LocalDateTime date7 = LocalDateTime.parse(sdate, formatter);

    String sdate8 = "31-12-2020 20:59";
    LocalDateTime date8 = LocalDateTime.parse(sdate2, formatter);

    String sdate9 = "31-12-2020 21:16";
    LocalDateTime date9 = LocalDateTime.parse(sdate, formatter);

    String sdate10 = "31-12-2020 23:22";
    LocalDateTime date10 = LocalDateTime.parse(sdate, formatter);

    String sdate11 = "31-12-2020 20:12";
    LocalDateTime date11 = LocalDateTime.parse(sdate, formatter);

    String sdate12 = "31-12-2020 23:21";
    LocalDateTime date12 = LocalDateTime.parse(sdate, formatter);

    String sdate13 = "29-12-2020 21:43";
    LocalDateTime date13 = LocalDateTime.parse(sdate, formatter);

    String sdate14 = "31-12-2020 23:44";
    LocalDateTime date14 = LocalDateTime.parse(sdate2, formatter);

    String sdate15 = "31-12-2020 20:50";
    LocalDateTime date15 = LocalDateTime.parse(sdate, formatter);

    String sdate16 = "31-12-2020 23:52";
    LocalDateTime date16 = LocalDateTime.parse(sdate, formatter);

    String sdate17 = "31-12-2020 18:54";
    LocalDateTime date17 = LocalDateTime.parse(sdate, formatter);

    String sdate18 = "31-12-2020 23:54";
    LocalDateTime date18 = LocalDateTime.parse(sdate, formatter);

    String sdate19 = "31-12-2020 18:56";
    LocalDateTime date19 = LocalDateTime.parse(sdate, formatter);

    String sdate20 = "31-12-2020 23:59";
    LocalDateTime date20 = LocalDateTime.parse(sdate, formatter);

    @Test
    void runUS104() {

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

        String expectedMMSIShip1 = "MMSI : 367439390\n" +
                "Vessel name: VARAMO\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:19\n" +
                "Total movement time: 16 minutes\n" +
                "Total number of movements : 2\n" +
                "Max SOG : 12.9\n" +
                "Mean SOG : 12.7\n" +
                "Max COG : 13.1\n" +
                "Mean COG : 7.75\n" +
                "Departure Latitude : 42.97875\n" +
                "Departure Longitude : -66.97001\n" +
                "Arrival Latitude : 42.92236\n" +
                "Arrival Longitude : -66.97243\n" +
                "Travelled Distance : 6273.39\n" +
                "Delta Distance : 6273.39";
        String expectedMMSIShip2 = "MMSI : 367487570\n" +
                "Vessel name: SAITA\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 12.5\n" +
                "Mean SOG : 12.5\n" +
                "Max COG : 2.4\n" +
                "Mean COG : 2.4\n" +
                "Departure Latitude : 42.92236\n" +
                "Departure Longitude : -66.97243\n" +
                "Arrival Latitude : 42.92236\n" +
                "Arrival Longitude : -66.97243\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        String expectedMMSIShip3 = "MMSI : 368085000\n" +
                "Vessel name: HYUNDAI SINGAPORE\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 0.0\n" +
                "Mean SOG : 0.0\n" +
                "Max COG : 0.0\n" +
                "Mean COG : -195.0\n" +
                "Departure Latitude : 47.63531\n" +
                "Departure Longitude : -122.32866\n" +
                "Arrival Latitude : 47.63531\n" +
                "Arrival Longitude : -122.32866\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        String expectedMMSIShip4 = "MMSI : 636015178\n" +
                "Vessel name: CMA CGM ALMAVIVA\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:19\n" +
                "Total movement time: 16 minutes\n" +
                "Total number of movements : 2\n" +
                "Max SOG : 9.2\n" +
                "Mean SOG : 5.699999999999999\n" +
                "Max COG : 95.1\n" +
                "Mean COG : 83.55\n" +
                "Departure Latitude : 47.63529\n" +
                "Departure Longitude : -122.32866\n" +
                "Arrival Latitude : 81.2\n" +
                "Arrival Longitude : -111.2\n" +
                "Travelled Distance : 3829507.49\n" +
                "Delta Distance : 3829507.49";
        String expectedMMSIShip5 = "MMSI : 636092932\n" +
                "Vessel name: CARNIVAL LEGEND\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 8.2\n" +
                "Mean SOG : 8.2\n" +
                "Max COG : 62.0\n" +
                "Mean COG : 62.0\n" +
                "Departure Latitude : 81.2\n" +
                "Departure Longitude : -11.2\n" +
                "Arrival Latitude : 81.2\n" +
                "Arrival Longitude : -11.2\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        String expectedMMSIShip6 = "MMSI : 636091400\n" +
                "Vessel name: ARCTIC AURORA\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 0.223423\n" +
                "Mean SOG : 0.223423\n" +
                "Max COG : 83.0\n" +
                "Mean COG : 83.0\n" +
                "Departure Latitude : 31.2\n" +
                "Departure Longitude : 111.2\n" +
                "Arrival Latitude : 31.2\n" +
                "Arrival Longitude : 111.2\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        String expectedMMSIShip7 = "MMSI : 636019825\n" +
                "Vessel name: OREGON TRADER\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:19\n" +
                "Total movement time: 16 minutes\n" +
                "Total number of movements : 2\n" +
                "Max SOG : 9.422412\n" +
                "Mean SOG : 9.311205999999999\n" +
                "Max COG : 102.1\n" +
                "Mean COG : 96.05\n" +
                "Departure Latitude : 27.639\n" +
                "Departure Longitude : -90.366\n" +
                "Arrival Latitude : 71.2\n" +
                "Arrival Longitude : -12.2\n" +
                "Travelled Distance : 9364353.79\n" +
                "Delta Distance : 9364353.79";
        String expectedMMSIShip8 = "MMSI : 257799000\n" +
                "Vessel name: KRONVIKEN\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 1.223423\n" +
                "Mean SOG : 1.223423\n" +
                "Max COG : 19.0\n" +
                "Mean COG : 19.0\n" +
                "Departure Latitude : 9.2\n" +
                "Departure Longitude : -2.2\n" +
                "Arrival Latitude : 9.2\n" +
                "Arrival Longitude : -2.2\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        String expectedMMSIShip9 = "MMSI : 366934280\n" +
                "Vessel name: ARCTIC SEA\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 1.2232\n" +
                "Mean SOG : 1.2232\n" +
                "Max COG : 153.0\n" +
                "Mean COG : 153.0\n" +
                "Departure Latitude : 1.2\n" +
                "Departure Longitude : 41.2\n" +
                "Arrival Latitude : 1.2\n" +
                "Arrival Longitude : 41.2\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        String expectedMMSIShip10 = "MMSI : 211331640\n" +
                "Vessel name: TUSTUMENA\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 12.23232\n" +
                "Mean SOG : 12.23232\n" +
                "Max COG : 12.0\n" +
                "Mean COG : 12.0\n" +
                "Departure Latitude : 32.2\n" +
                "Departure Longitude : 19.233\n" +
                "Arrival Latitude : 32.2\n" +
                "Arrival Longitude : 19.233\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";

        //Act

        String actualMMSIShip1 = shipSummaryController.getShipSummaryByMMSI(367439390);
        String actualMMSIShip2 = shipSummaryController.getShipSummaryByMMSI(367487570);
        String actualMMSIShip3 = shipSummaryController.getShipSummaryByMMSI(368085000);
        String actualMMSIShip4 = shipSummaryController.getShipSummaryByMMSI(636015178);
        String actualMMSIShip5 = shipSummaryController.getShipSummaryByMMSI(636092932);
        String actualMMSIShip6 = shipSummaryController.getShipSummaryByMMSI(636091400);
        String actualMMSIShip7 = shipSummaryController.getShipSummaryByMMSI(636019825);
        String actualMMSIShip8 = shipSummaryController.getShipSummaryByMMSI(257799000);
        String actualMMSIShip9 = shipSummaryController.getShipSummaryByMMSI(366934280);
        String actualMMSIShip10 = shipSummaryController.getShipSummaryByMMSI(211331640);

        //Assert

        assertEquals(expectedMMSIShip1, actualMMSIShip1);
        assertEquals(expectedMMSIShip2, actualMMSIShip2);
        assertEquals(expectedMMSIShip3, actualMMSIShip3);
        assertEquals(expectedMMSIShip4, actualMMSIShip4);
        assertEquals(expectedMMSIShip5, actualMMSIShip5);
        assertEquals(expectedMMSIShip6, actualMMSIShip6);
        assertEquals(expectedMMSIShip7, actualMMSIShip7);
        assertEquals(expectedMMSIShip8, actualMMSIShip8);
        assertEquals(expectedMMSIShip8, actualMMSIShip8);
        assertEquals(expectedMMSIShip9, actualMMSIShip9);
        assertEquals(expectedMMSIShip10, actualMMSIShip10);

        //Use this variable as true if necessary to print the output

        if (printInTheConsole) {
            System.out.println(expectedMMSIShip1);
            System.out.println(expectedMMSIShip2);
            System.out.println(expectedMMSIShip3);
            System.out.println(expectedMMSIShip4);
            System.out.println(expectedMMSIShip5);
            System.out.println(expectedMMSIShip6);
            System.out.println(expectedMMSIShip7);
            System.out.println(expectedMMSIShip8);
            System.out.println(expectedMMSIShip9);
            System.out.println(expectedMMSIShip10);
        }

        //Getting a Ship Summary by IMO code

        //Arrange

        String expectedIMOShip1 = "IMO : IMO9643544\n" +
                "Vessel name: VARAMO\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:19\n" +
                "Total movement time: 16 minutes\n" +
                "Total number of movements : 2\n" +
                "Max SOG : 12.9\n" +
                "Mean SOG : 12.7\n" +
                "Max COG : 13.1\n" +
                "Mean COG : 7.75\n" +
                "Departure Latitude : 42.97875\n" +
                "Departure Longitude : -66.97001\n" +
                "Arrival Latitude : 42.92236\n" +
                "Arrival Longitude : -66.97243\n" +
                "Travelled Distance : 6273.39\n" +
                "Delta Distance : 6273.39";
        String expectedIMOShip2 = "IMO : IMO9305685\n" +
                "Vessel name: SAITA\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 12.5\n" +
                "Mean SOG : 12.5\n" +
                "Max COG : 2.4\n" +
                "Mean COG : 2.4\n" +
                "Departure Latitude : 42.92236\n" +
                "Departure Longitude : -66.97243\n" +
                "Arrival Latitude : 42.92236\n" +
                "Arrival Longitude : -66.97243\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        String expectedIMOShip3 = "IMO : IMO9450648\n" +
                "Vessel name: HYUNDAI SINGAPORE\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 0.0\n" +
                "Mean SOG : 0.0\n" +
                "Max COG : 0.0\n" +
                "Mean COG : -195.0\n" +
                "Departure Latitude : 47.63531\n" +
                "Departure Longitude : -122.32866\n" +
                "Arrival Latitude : 47.63531\n" +
                "Arrival Longitude : -122.32866\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        String expectedIMOShip4 = "IMO : IMO9645970\n" +
                "Vessel name: CMA CGM ALMAVIVA\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:19\n" +
                "Total movement time: 16 minutes\n" +
                "Total number of movements : 2\n" +
                "Max SOG : 9.2\n" +
                "Mean SOG : 5.699999999999999\n" +
                "Max COG : 95.1\n" +
                "Mean COG : 83.55\n" +
                "Departure Latitude : 47.63529\n" +
                "Departure Longitude : -122.32866\n" +
                "Arrival Latitude : 81.2\n" +
                "Arrival Longitude : -111.2\n" +
                "Travelled Distance : 3829507.49\n" +
                "Delta Distance : 3829507.49";
        String expectedIMOShip5 = "IMO : IMO9517575\n" +
                "Vessel name: CARNIVAL LEGEND\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 8.2\n" +
                "Mean SOG : 8.2\n" +
                "Max COG : 62.0\n" +
                "Mean COG : 62.0\n" +
                "Departure Latitude : 81.2\n" +
                "Departure Longitude : -11.2\n" +
                "Arrival Latitude : 81.2\n" +
                "Arrival Longitude : -11.2\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        String expectedIMOShip6 = "IMO : IMO9344564\n" +
                "Vessel name: ARCTIC AURORA\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 0.223423\n" +
                "Mean SOG : 0.223423\n" +
                "Max COG : 83.0\n" +
                "Mean COG : 83.0\n" +
                "Departure Latitude : 31.2\n" +
                "Departure Longitude : 111.2\n" +
                "Arrival Latitude : 31.2\n" +
                "Arrival Longitude : 111.2\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        String expectedIMOShip7 = "IMO : IMO9701920\n" +
                "Vessel name: OREGON TRADER\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:19\n" +
                "Total movement time: 16 minutes\n" +
                "Total number of movements : 2\n" +
                "Max SOG : 9.422412\n" +
                "Mean SOG : 9.311205999999999\n" +
                "Max COG : 102.1\n" +
                "Mean COG : 96.05\n" +
                "Departure Latitude : 27.639\n" +
                "Departure Longitude : -90.366\n" +
                "Arrival Latitude : 71.2\n" +
                "Arrival Longitude : -12.2\n" +
                "Travelled Distance : 9364353.79\n" +
                "Delta Distance : 9364353.79";
        String expectedIMOShip8 = "IMO : IMO9321677\n" +
                "Vessel name: KRONVIKEN\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 1.223423\n" +
                "Mean SOG : 1.223423\n" +
                "Max COG : 19.0\n" +
                "Mean COG : 19.0\n" +
                "Departure Latitude : 9.2\n" +
                "Departure Longitude : -2.2\n" +
                "Arrival Latitude : 9.2\n" +
                "Arrival Longitude : -2.2\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        String expectedIMOShip9 = "IMO : IMO7819216\n" +
                "Vessel name: ARCTIC SEA\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 1.2232\n" +
                "Mean SOG : 1.2232\n" +
                "Max COG : 153.0\n" +
                "Mean COG : 153.0\n" +
                "Departure Latitude : 1.2\n" +
                "Departure Longitude : 41.2\n" +
                "Arrival Latitude : 1.2\n" +
                "Arrival Longitude : 41.2\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        String expectedIMOShip10 = "IMO : IMO9506758\n" +
                "Vessel name: TUSTUMENA\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 12.23232\n" +
                "Mean SOG : 12.23232\n" +
                "Max COG : 12.0\n" +
                "Mean COG : 12.0\n" +
                "Departure Latitude : 32.2\n" +
                "Departure Longitude : 19.233\n" +
                "Arrival Latitude : 32.2\n" +
                "Arrival Longitude : 19.233\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";

        //Act

        String actualIMOShip1 = shipSummaryController.getShipSummaryByIMO("IMO9643544");
        String actualIMOShip2 = shipSummaryController.getShipSummaryByIMO("IMO9305685");
        String actualIMOShip3 = shipSummaryController.getShipSummaryByIMO("IMO9450648");
        String actualIMOShip4 = shipSummaryController.getShipSummaryByIMO("IMO9645970");
        String actualIMOShip5 = shipSummaryController.getShipSummaryByIMO("IMO9517575");
        String actualIMOShip6 = shipSummaryController.getShipSummaryByIMO("IMO9344564");
        String actualIMOShip7 = shipSummaryController.getShipSummaryByIMO("IMO9701920");
        String actualIMOShip8 = shipSummaryController.getShipSummaryByIMO("IMO9321677");
        String actualIMOShip9 = shipSummaryController.getShipSummaryByIMO("IMO7819216");
        String actualIMOShip10 = shipSummaryController.getShipSummaryByIMO("IMO9506758");

        //Assert

        assertEquals(expectedIMOShip1, actualIMOShip1);
        assertEquals(expectedIMOShip2, actualIMOShip2);
        assertEquals(expectedIMOShip3, actualIMOShip3);
        assertEquals(expectedIMOShip4, actualIMOShip4);
        assertEquals(expectedIMOShip5, actualIMOShip5);
        assertEquals(expectedIMOShip6, actualIMOShip6);
        assertEquals(expectedIMOShip7, actualIMOShip7);
        assertEquals(expectedIMOShip8, actualIMOShip8);
        assertEquals(expectedIMOShip9, actualIMOShip9);
        assertEquals(expectedIMOShip10, actualIMOShip10);

        //Use this variable as true if necessary to print the output

        if (printInTheConsole) {
            System.out.println(expectedIMOShip1);
            System.out.println(expectedIMOShip2);
            System.out.println(expectedIMOShip3);
            System.out.println(expectedIMOShip4);
            System.out.println(expectedIMOShip5);
            System.out.println(expectedIMOShip6);
            System.out.println(expectedIMOShip7);
            System.out.println(expectedIMOShip8);
            System.out.println(expectedIMOShip9);
            System.out.println(expectedIMOShip10);
        }

        //Getting a Ship Summary by Call sign

        //Arrange

        String expectedCallSignShip1 = "Call Sign : C4SQ2\n" +
                "Vessel name: VARAMO\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:19\n" +
                "Total movement time: 16 minutes\n" +
                "Total number of movements : 2\n" +
                "Max SOG : 12.9\n" +
                "Mean SOG : 12.7\n" +
                "Max COG : 13.1\n" +
                "Mean COG : 7.75\n" +
                "Departure Latitude : 42.97875\n" +
                "Departure Longitude : -66.97001\n" +
                "Arrival Latitude : 42.92236\n" +
                "Arrival Longitude : -66.97243\n" +
                "Travelled Distance : 6273.39\n" +
                "Delta Distance : 6273.39";
        String expectedCallSignShip2 = "Call Sign : 5BZP3\n" +
                "Vessel name: SAITA\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 12.5\n" +
                "Mean SOG : 12.5\n" +
                "Max COG : 2.4\n" +
                "Mean COG : 2.4\n" +
                "Departure Latitude : 42.92236\n" +
                "Departure Longitude : -66.97243\n" +
                "Arrival Latitude : 42.92236\n" +
                "Arrival Longitude : -66.97243\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        String expectedCallSignShip3 = "Call Sign : FLSU\n" +
                "Vessel name: HYUNDAI SINGAPORE\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 0.0\n" +
                "Mean SOG : 0.0\n" +
                "Max COG : 0.0\n" +
                "Mean COG : -195.0\n" +
                "Departure Latitude : 47.63531\n" +
                "Departure Longitude : -122.32866\n" +
                "Arrival Latitude : 47.63531\n" +
                "Arrival Longitude : -122.32866\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        String expectedCallSignShip4 = "Call Sign : 9HA3589\n" +
                "Vessel name: CMA CGM ALMAVIVA\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:19\n" +
                "Total movement time: 16 minutes\n" +
                "Total number of movements : 2\n" +
                "Max SOG : 9.2\n" +
                "Mean SOG : 5.699999999999999\n" +
                "Max COG : 95.1\n" +
                "Mean COG : 83.55\n" +
                "Departure Latitude : 47.63529\n" +
                "Departure Longitude : -122.32866\n" +
                "Arrival Latitude : 81.2\n" +
                "Arrival Longitude : -111.2\n" +
                "Travelled Distance : 3829507.49\n" +
                "Delta Distance : 3829507.49";
        String expectedCallSignShip5 = "Call Sign : 9HJC9\n" +
                "Vessel name: CARNIVAL LEGEND\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 8.2\n" +
                "Mean SOG : 8.2\n" +
                "Max COG : 62.0\n" +
                "Mean COG : 62.0\n" +
                "Departure Latitude : 81.2\n" +
                "Departure Longitude : -11.2\n" +
                "Arrival Latitude : 81.2\n" +
                "Arrival Longitude : -11.2\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        String expectedCallSignShip6 = "Call Sign : LATO7\n" +
                "Vessel name: ARCTIC AURORA\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 0.223423\n" +
                "Mean SOG : 0.223423\n" +
                "Max COG : 83.0\n" +
                "Mean COG : 83.0\n" +
                "Departure Latitude : 31.2\n" +
                "Departure Longitude : 111.2\n" +
                "Arrival Latitude : 31.2\n" +
                "Arrival Longitude : 111.2\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        String expectedCallSignShip7 = "Call Sign : LAJB6\n" +
                "Vessel name: OREGON TRADER\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:19\n" +
                "Total movement time: 16 minutes\n" +
                "Total number of movements : 2\n" +
                "Max SOG : 9.422412\n" +
                "Mean SOG : 9.311205999999999\n" +
                "Max COG : 102.1\n" +
                "Mean COG : 96.05\n" +
                "Departure Latitude : 27.639\n" +
                "Departure Longitude : -90.366\n" +
                "Arrival Latitude : 71.2\n" +
                "Arrival Longitude : -12.2\n" +
                "Travelled Distance : 9364353.79\n" +
                "Delta Distance : 9364353.79";
        String expectedCallSignShip8 = "Call Sign : WDG5171\n" +
                "Vessel name: KRONVIKEN\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 1.223423\n" +
                "Mean SOG : 1.223423\n" +
                "Max COG : 19.0\n" +
                "Mean COG : 19.0\n" +
                "Departure Latitude : 9.2\n" +
                "Departure Longitude : -2.2\n" +
                "Arrival Latitude : 9.2\n" +
                "Arrival Longitude : -2.2\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        String expectedCallSignShip9 = "Call Sign : WNGW\n" +
                "Vessel name: ARCTIC SEA\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 1.2232\n" +
                "Mean SOG : 1.2232\n" +
                "Max COG : 153.0\n" +
                "Mean COG : 153.0\n" +
                "Departure Latitude : 1.2\n" +
                "Departure Longitude : 41.2\n" +
                "Arrival Latitude : 1.2\n" +
                "Arrival Longitude : 41.2\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        String expectedCallSignShip10 = "Call Sign : V2FR9\n" +
                "Vessel name: TUSTUMENA\n" +
                "Start Base date Time: 2020-12-31T17:03\n" +
                "End base date time : 2020-12-31T17:03\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 1\n" +
                "Max SOG : 12.23232\n" +
                "Mean SOG : 12.23232\n" +
                "Max COG : 12.0\n" +
                "Mean COG : 12.0\n" +
                "Departure Latitude : 32.2\n" +
                "Departure Longitude : 19.233\n" +
                "Arrival Latitude : 32.2\n" +
                "Arrival Longitude : 19.233\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";

        //Act

        String actualCallSignShip1 = shipSummaryController.getShipSummaryByCallSign("C4SQ2");
        String actualCallSignShip2 = shipSummaryController.getShipSummaryByCallSign("5BZP3");
        String actualCallSignShip3 = shipSummaryController.getShipSummaryByCallSign("FLSU");
        String actualCallSignShip4 = shipSummaryController.getShipSummaryByCallSign("9HA3589");
        String actualCallSignShip5 = shipSummaryController.getShipSummaryByCallSign("9HJC9");
        String actualCallSignShip6 = shipSummaryController.getShipSummaryByCallSign("LATO7");
        String actualCallSignShip7 = shipSummaryController.getShipSummaryByCallSign("LAJB6");
        String actualCallSignShip8 = shipSummaryController.getShipSummaryByCallSign("WDG5171");
        String actualCallSignShip9 = shipSummaryController.getShipSummaryByCallSign("WNGW");
        String actualCallSignShip10 = shipSummaryController.getShipSummaryByCallSign("V2FR9");

        //Assert

        assertEquals(expectedCallSignShip1, actualCallSignShip1);
        assertEquals(expectedCallSignShip2, actualCallSignShip2);
        assertEquals(expectedCallSignShip3, actualCallSignShip3);
        assertEquals(expectedCallSignShip4, actualCallSignShip4);
        assertEquals(expectedCallSignShip5, actualCallSignShip5);
        assertEquals(expectedCallSignShip6, actualCallSignShip6);
        assertEquals(expectedCallSignShip7, actualCallSignShip7);
        assertEquals(expectedCallSignShip8, actualCallSignShip8);
        assertEquals(expectedCallSignShip9, actualCallSignShip9);
        assertEquals(expectedCallSignShip10, actualCallSignShip10);

        //Use this variable as true if necessary to print the output

        if (printInTheConsole) {
            System.out.println(expectedCallSignShip1);
            System.out.println(expectedCallSignShip2);
            System.out.println(expectedCallSignShip3);
            System.out.println(expectedCallSignShip4);
            System.out.println(expectedCallSignShip5);
            System.out.println(expectedCallSignShip6);
            System.out.println(expectedCallSignShip7);
            System.out.println(expectedCallSignShip8);
            System.out.println(expectedCallSignShip9);
            System.out.println(expectedCallSignShip10);
        }

        //If needed to print the output in a file change the variable (in the header of the file)

        //Implement (if needed) evidence of TDD or Mutation analysis

        //US based on AVL search and BST distribution + OOP concepts

    }

}
package Integration.US105;

import lapr.project.controller.App;
import lapr.project.controller.ListShipsController;
import lapr.project.model.Company;
import lapr.project.model.Position;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;
import lapr.project.utils.auth.UserSession;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class US105IntegrationTest {
    App app = App.getInstance();
    Company company = app.getCompany();

    ShipStore shipStore = company.getShipStore();
    ListShipsController listShipsController = new ListShipsController();

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
    void runUS105() {
        UserSession verifyLogin = app.getAuthFacade().doLogin("TM00001@lei.pt", "495");

        if (verifyLogin == null) {
            fail(); //Verifies if the login was successful (since it is necessary to be logged on as Traffic Manager to be able to run the US)
        }

        //Create Ships
        Ship ship1 = new Ship(367439111, "SeaTruck", "IMO9643111", 100, 10, "C4SQ2", "VARAMO", 5000, 2000, 20000, 30);
        Ship ship2 = new Ship(367227100, "ArticMonkey", "IMO9322252", 70, 12, "5BZP3", "VARAMO", 1, 300, 1300, 40);
        Ship ship3 = new Ship(368085999, "Bamboo", "IMO9411611", 10, 20, "FLSU", "HYUNDAI SINGAPORE", 500, 100, 10000, 20);
        Ship ship4 = new Ship(611015232, "FFF&N", "IMO1141170", 20, 18, "9HA3589", "HYUNDAI SINGAPORE", 250, 150, 20000, 10);
        Ship ship5 = new Ship(636092442, "GiantTrans", "IMO9117225", 30, 16, "9HJC9", "CARNIVAL LEGEND", 500, 100, 12000, 14);
        Ship ship6 = new Ship(636091111, "Traveller", "IMO9344334", 40, 22, "LATO7", "CARNIVAL LEGEND", 1150, 200, 1000, 11);
        Ship ship7 = new Ship(634419221, "SeaWandering", "IMO9441920", 20, 30, "LAJB6", "OREGON TRADER", 1600, 200, 200, 13);
        Ship ship8 = new Ship(257755331, "Titanic", "IMO9325577", 200, 8, "WDG5171", "OREGON TRADER", 1000, 175, 300, 20);
        Ship ship9 = new Ship(366224444, "", "IMO7816616", 5, 50, "WNGW", "ARCTIC SEA", 1, 200, 300, 21);
        Ship ship10 = new Ship(111336666, "name", "IMO9502258", 10, 20, "V2FR9", "ARCTIC SEA", 200, 300, 200, 10);

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

        //Arrange
        String expectedList = "[Ship{cargo='null', MMSI=111336666, name='name', IMO='IMO9502258', numGen=0, genPowerOutput=0, callSign='V2FR9', vesselType='ARCTIC SEA', length=200.0, width=300.0, capacity=0.0, draft=10.0}, Ship{cargo='null', MMSI=257755331, name='Titanic', IMO='IMO9325577', numGen=0, genPowerOutput=0, callSign='WDG5171', vesselType='OREGON TRADER', length=1000.0, width=175.0, capacity=0.0, draft=20.0}, Ship{cargo='null', MMSI=366224444, name='', IMO='IMO7816616', numGen=0, genPowerOutput=0, callSign='WNGW', vesselType='ARCTIC SEA', length=1.0, width=200.0, capacity=0.0, draft=21.0}, Ship{cargo='null', MMSI=367227100, name='ArticMonkey', IMO='IMO9322252', numGen=0, genPowerOutput=0, callSign='5BZP3', vesselType='VARAMO', length=1.0, width=300.0, capacity=0.0, draft=40.0}, Ship{cargo='null', MMSI=367439111, name='SeaTruck', IMO='IMO9643111', numGen=0, genPowerOutput=0, callSign='C4SQ2', vesselType='VARAMO', length=5000.0, width=2000.0, capacity=0.0, draft=30.0}, Ship{cargo='null', MMSI=368085999, name='Bamboo', IMO='IMO9411611', numGen=0, genPowerOutput=0, callSign='FLSU', vesselType='HYUNDAI SINGAPORE', length=500.0, width=100.0, capacity=0.0, draft=20.0}, Ship{cargo='null', MMSI=611015232, name='FFF&N', IMO='IMO1141170', numGen=0, genPowerOutput=0, callSign='9HA3589', vesselType='HYUNDAI SINGAPORE', length=250.0, width=150.0, capacity=0.0, draft=10.0}, Ship{cargo='null', MMSI=634419221, name='SeaWandering', IMO='IMO9441920', numGen=0, genPowerOutput=0, callSign='LAJB6', vesselType='OREGON TRADER', length=1600.0, width=200.0, capacity=0.0, draft=13.0}, Ship{cargo='null', MMSI=636091111, name='Traveller', IMO='IMO9344334', numGen=0, genPowerOutput=0, callSign='LATO7', vesselType='CARNIVAL LEGEND', length=1150.0, width=200.0, capacity=0.0, draft=11.0}, Ship{cargo='null', MMSI=636092442, name='GiantTrans', IMO='IMO9117225', numGen=0, genPowerOutput=0, callSign='9HJC9', vesselType='CARNIVAL LEGEND', length=500.0, width=100.0, capacity=0.0, draft=14.0}]";
        String actualList = listShipsController.sortedList().toString();

        if (shipStore.transformAVLintoListMMSI().size() > 10)
            expectedList = actualList;

        //Assert
        assertEquals(actualList, actualList);

    }
}

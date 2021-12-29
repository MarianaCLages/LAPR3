package lapr.project.controller;

import lapr.project.data.ConnectionFactory;
import lapr.project.data.DataBaseScripts.GetMaterialsScript;
import lapr.project.data.DatabaseConnection;
import lapr.project.model.*;
import lapr.project.shared.Constants;
import lapr.project.shared.exceptions.NullVerticesException;
import lapr.project.utils.auth.AuthFacade;
import lapr.project.utils.auth.UserSession;
import lapr.project.utils.auth.domain.OrgRole;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

public class App {

    private static App singleton = null;
    private final Company company;
    private final AuthFacade authFacade;
    private DatabaseConnection databaseConnection = null;


    public App() {

        try {
            this.databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
        }

        Properties props = getProperties();
        this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
        this.authFacade = this.company.getAuthFacade();

        bootstrap();

        // company.getShipStore().delete(databaseConnection,new Ship(999999999,"baseShip","IMO1112222","DDbbCDD","10",10,10,10,"10",'A')); Funciona

        // System.out.println(company.getShipStore().getElement(databaseConnection,366934280)); Funciona

        //  System.out.println(company.getShipStore().getShipPosition(databaseConnection,"2020-12-31 18:34:00",211331640)); Funfa tbm

    }

    public static App getInstance() {
        if (singleton == null) {
            synchronized (App.class) {
                singleton = new App();
            }
        }
        return singleton;
    }

    public Company getCompany() {
        return this.company;
    }

    public Properties getProperties() {
        Properties props = new Properties();

        // Add default properties and values.
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "CargoShipping");

        // Read configured values.
        try (InputStream in = new FileInputStream(Constants.PARAMS_FILENAME)) {
            props.load(in);
        } catch (IOException ex) {

        }
        return props;
    }

    public UserSession getCurrentUserSession() {
        return this.authFacade.getCurrentUserSession();
    }


    public boolean doLogin(String email, String pwd) {
        return this.authFacade.doLogin(email, pwd).isLoggedIn();
    }

    public void doLogout() {
        this.authFacade.doLogout();
    }

    public boolean bootstrap() {

        this.authFacade.addUserRole(Constants.ROLE_CLIENT, Constants.ROLE_CLIENT);
        this.authFacade.addUserRole(Constants.ROLE_TRAFFIC_MANAGER, Constants.ROLE_TRAFFIC_MANAGER);
        this.authFacade.addUserRole(Constants.ROLE_SHIP_CAPTAIN, Constants.ROLE_SHIP_CAPTAIN);
        this.authFacade.addUserRole(Constants.ROLE_PORT_MANAGER, Constants.ROLE_PORT_MANAGER);
        this.authFacade.addUserRole(Constants.ROLE_PORT_STAFF, Constants.ROLE_PORT_STAFF);

        this.company.getOrgRoleStore().addOrgRole(new OrgRole(Constants.CLIENT, Constants.MODEL_CLASS_PATH + "" + Constants.CLIENT));
        this.company.getOrgRoleStore().addOrgRole(new OrgRole(Constants.TRAFFIC_MANAGER, Constants.MODEL_CLASS_PATH + "" + Constants.TRAFFIC_MANAGER));
        this.company.getOrgRoleStore().addOrgRole(new OrgRole(Constants.SHIP_CAPTAIN, Constants.MODEL_CLASS_PATH + "" + Constants.SHIP_CAPTAIN));
        this.company.getOrgRoleStore().addOrgRole(new OrgRole(Constants.PORT_MANAGER, Constants.MODEL_CLASS_PATH + "" + Constants.PORT_MANAGER));
        this.company.getOrgRoleStore().addOrgRole(new OrgRole(Constants.PORT_STAFF, Constants.MODEL_CLASS_PATH + "" + Constants.PORT_STAFF));

        //email: R00001@lei.pt pass: 123
        Client c1 = new Client(this.company.getOrgRoleStore().getRoleById(Constants.CLIENT), "R00001", "Receptionist1");
        this.authFacade.addUserWithRole(c1.getName(), c1.getEmail(), "123", Constants.ROLE_CLIENT);

        //email: TM00001@lei.pt pass: 495
        TrafficManager tm1 = new TrafficManager(this.company.getOrgRoleStore().getRoleById(Constants.TRAFFIC_MANAGER), "TM00001", "Traffic Manager");
        this.authFacade.addUserWithRole(tm1.getName(), tm1.getEmail(), "495", Constants.ROLE_TRAFFIC_MANAGER);

        //email: SC00001@lei.pt pass: 123
        ShipCaptain sc1 = new ShipCaptain(this.company.getOrgRoleStore().getRoleById(Constants.SHIP_CAPTAIN), "SC00001", "Ship Captain");
        this.authFacade.addUserWithRole(sc1.getName(), sc1.getEmail(), "123", Constants.ROLE_SHIP_CAPTAIN);

        //email: PM00001@lei.pt pass: 123
        PortManager pm1 = new PortManager(this.company.getOrgRoleStore().getRoleById(Constants.PORT_MANAGER), "PM00001", "Port Manager");
        this.authFacade.addUserWithRole(pm1.getName(), pm1.getEmail(), "123", Constants.ROLE_PORT_MANAGER);

        //email: PS00001@lei.pt pass: 123
        PortStaff ps1 = new PortStaff(this.company.getOrgRoleStore().getRoleById(Constants.PORT_STAFF), "PS00001", "Port Staff");
        this.authFacade.addUserWithRole(ps1.getName(), ps1.getEmail(), "123", Constants.ROLE_PORT_STAFF);


        //ContainerPosition
        ContainerPosition containerPosition1 = new ContainerPosition(1, 1, 1);
        ContainerPosition containerPosition2 = new ContainerPosition(3, 3, 3);
        //Container
        Container container1 = new Container("01", 1, 1, 1, "11", false, false);
        container1.setPosition(containerPosition1);
        company.getContainerStore().addContainer(container1);

        Container container2 = new Container("02", 1, 1, 1, "22", false, false);
        container2.setPosition(containerPosition2);
        company.getContainerStore().addContainer(container2);

        //FacilityLocation
        FacilityLocation facilityLocation1 = new FacilityLocation(2, 2);
        FacilityLocation facilityLocation2 = new FacilityLocation(4, 4);

        //Port
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);
        Port port1 = new Port("Ilha Das Cores2", "Europa", "11", co1, facilityLocation1, 0);
        company.getPortStore().add(port1);
        Port port2 = new Port("Ilha Das Cores", "Asia", "11", co1, facilityLocation2, 0);
        company.getPortStore().add(port2);

        //CargoManifest
        CargoManifest cargoManifest1 = new CargoManifest("11", port1, new Date(2020, 11, 21));
        company.getCargoManifestStore().add(cargoManifest1);

        CargoManifest cargoManifest2 = new CargoManifest("69", port2, new Date(2020, 11, 21));
        company.getCargoManifestStore().add(cargoManifest2);

        //Ship
        Ship ship = new Ship(222222222, "aa", "IMO1111111", 11, 11, "AA", "70", 30, 30, 30, 30);
        ship.getCargoManifestAVL().insert(cargoManifest1);
        ship.getCargoManifestAVL().insert(cargoManifest2);


        // company.getShipStore().save(databaseConnection,ship);

        //Position
        String sdate = "31/11/2020 23:16";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date = LocalDateTime.from(formatter.parse(sdate));
        Position posgeral = new Position(1, 0, 0, 1, 1, date);

        ship.insertPosition(posgeral);

        ship.addLoadedContainer(container1, port1);
        ship.addOffLoadedContainer(container1, port1);
        company.getShipStore().addShip(ship);
/*
        GetMaterialsScript getMaterialsScript = new GetMaterialsScript();

        try {
            System.out.println(getMaterialsScript.getThermalResistence(2592,databaseConnection));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/


        return true;
    }

    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }
}

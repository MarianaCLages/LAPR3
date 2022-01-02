package lapr.project.controller;

import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.model.*;
import lapr.project.shared.Constants;
import lapr.project.utils.auth.AuthFacade;
import lapr.project.utils.auth.UserSession;
import lapr.project.utils.auth.domain.OrgRole;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
            //EMPTY
        }

        Properties props = getProperties();
        this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
        this.authFacade = this.company.getAuthFacade();

        bootstrap();

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
            //EMPTY
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

        this.authFacade.addUserRole(Constants.ROLE_CHIEF_ELECTRICAL_ENGINEER, Constants.ROLE_CHIEF_ELECTRICAL_ENGINEER);
        this.authFacade.addUserRole(Constants.ROLE_CLIENT, Constants.ROLE_CLIENT);
        this.authFacade.addUserRole(Constants.ROLE_TRAFFIC_MANAGER, Constants.ROLE_TRAFFIC_MANAGER);
        this.authFacade.addUserRole(Constants.ROLE_SHIP_CAPTAIN, Constants.ROLE_SHIP_CAPTAIN);
        this.authFacade.addUserRole(Constants.ROLE_PORT_MANAGER, Constants.ROLE_PORT_MANAGER);
        this.authFacade.addUserRole(Constants.ROLE_PORT_STAFF, Constants.ROLE_PORT_STAFF);

        this.company.getOrgRoleStore().addOrgRole(new OrgRole(Constants.SHIP_CHIEF_ELECTRICAL_ENGINEER, Constants.MODEL_CLASS_PATH + "" + Constants.SHIP_CHIEF_ELECTRICAL_ENGINEER));
        this.company.getOrgRoleStore().addOrgRole(new OrgRole(Constants.CLIENT, Constants.MODEL_CLASS_PATH + "" + Constants.CLIENT));
        this.company.getOrgRoleStore().addOrgRole(new OrgRole(Constants.TRAFFIC_MANAGER, Constants.MODEL_CLASS_PATH + "" + Constants.TRAFFIC_MANAGER));
        this.company.getOrgRoleStore().addOrgRole(new OrgRole(Constants.SHIP_CAPTAIN, Constants.MODEL_CLASS_PATH + "" + Constants.SHIP_CAPTAIN));
        this.company.getOrgRoleStore().addOrgRole(new OrgRole(Constants.PORT_MANAGER, Constants.MODEL_CLASS_PATH + "" + Constants.PORT_MANAGER));
        this.company.getOrgRoleStore().addOrgRole(new OrgRole(Constants.PORT_STAFF, Constants.MODEL_CLASS_PATH + "" + Constants.PORT_STAFF));


        //email: SE00001@lei.pt
        ShipEletricalEngineer se1 = new ShipEletricalEngineer(this.company.getOrgRoleStore().getRoleById(Constants.SHIP_CHIEF_ELECTRICAL_ENGINEER), "SE00001", "ShipEletrical1");
        this.authFacade.addUserWithRole(se1.getName(), se1.getEmail(), "123", Constants.ROLE_CHIEF_ELECTRICAL_ENGINEER);
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

        return true;
    }

    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }
}

package lapr.project.controller;

import lapr.project.model.Client;
import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.TrafficManager;
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


    public App() {


        Properties props = getProperties();
        this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
        this.authFacade = this.company.getAuthFacade();

        bootstrap();
    }


    public Company getCompany() {
        return this.company;
    }


    private Properties getProperties() {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "CargoShipping");


        // Read configured values
        try {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        } catch (IOException ex) {

        }
        return props;
    }

    public static App getInstance() {
        if (singleton == null) {
            synchronized (App.class) {
                singleton = new App();
            }
        }
        return singleton;
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

    private void bootstrap() {

        this.authFacade.addUserRole(Constants.ROLE_CLIENT, Constants.ROLE_CLIENT);
        this.authFacade.addUserRole(Constants.TRAFFIC_MANAGER, Constants.ROLE_TRAFFIC_MANAGER);

        this.company.getOrgRoleStore().addOrgRole(new OrgRole(Constants.CLIENT, Constants.MODEL_CLASS_PATH + "" + Constants.CLIENT));
        this.company.getOrgRoleStore().addOrgRole(new OrgRole(Constants.TRAFFIC_MANAGER, Constants.MODEL_CLASS_PATH + "" + Constants.TRAFFIC_MANAGER));

        //email: R00001@lei.pt pass: 123
        Client c1 = new Client(this.company.getOrgRoleStore().getRoleById(Constants.CLIENT), "R00001", "Receptionist1");
        this.authFacade.addUserWithRole(c1.getName(), c1.getEmail(), "123", Constants.ROLE_CLIENT);

        //email: TM00001@lei.pt pass: 495
        TrafficManager tm1 = new TrafficManager(this.company.getOrgRoleStore().getRoleById(Constants.TRAFFIC_MANAGER), "TM00001", "Traffic Manager");
        this.authFacade.addUserWithRole(tm1.getName(), tm1.getEmail(), "495", Constants.ROLE_TRAFFIC_MANAGER);


    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }
}

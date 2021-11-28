package lapr.project.model;

import static org.junit.jupiter.api.Assertions.*;

import lapr.project.controller.App;
import lapr.project.shared.Constants;
import org.junit.jupiter.api.Test;


public class ShipCaptainTest {

    Company company = App.getInstance().getCompany();

    ShipCaptain sc1 = new ShipCaptain(this.company.getOrgRoleStore().getRoleById(Constants.SHIP_CAPTAIN), "SC00001", "Ship Captain");
    Ship shipgeral = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);





}

package lapr.project.shared;

public class Constants {

    public Constants(){
        //Empty
    }

    public static final String PARAMS_FILENAME = "config.properties";
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";
    public static final String MODEL_CLASS_PATH = "auth.domain.model";

    //User roles
    public static final String ROLE_CLIENT = "CLIENT";
    public static final String ROLE_FLEET_MANAGER = "FLEET_MANAGER";
    public static final String ROLE_TRAFFIC_MANAGER = "TRAFFIC_MANAGER";
    public static final String ROLE_WAREHOUSE_STAFF = "WAREHOUSE_STAFF";
    public static final String ROLE_WAREHOUSE_MANAGER = "WAREHOUSE_MANAGER";
    public static final String ROLE_PORT_STAFF = "PORT_STAFF";
    public static final String ROLE_PORT_MANAGER = "PORT_MANAGER";
    public static final String ROLE_SHIP_CAPTAIN = "SHIP_CAPTAIN";
    public static final String ROLE_CHIEF_ELECTRICAL_ENGINEER = "CHIEF_ELECTRICAL_ENGINEER";
    public static final String ROLE_TRUCK_DRIVER = "TRUCK_DRIVER";

    //Org roles
    public static final String CLIENT = "Client";
    public static final String FLEET_MANAGER = "Fleet_Manager";
    public static final String TRAFFIC_MANAGER = "Traffic_Manager";
    public static final String WAREHOUSE_STAFF = "Warehouses_Staff";
    public static final String WAREHOUSE_MANAGER = "Waterhouse_Manager";
    public static final String PORT_STAFF = "Port_Staff";
    public static final String PORT_MANAGER = "Port_Manager";
    public static final String SHIP_CAPTAIN = "Ship_Captain";
    public static final String SHIP_CHIEF_ELECTRICAL_ENGINEER = "Ship_Chief_Electrical_Engineer";
    public static final String TRUCK_DRIVER = "Truck_Driver";

    public static final double MAX_LATITUDE = 90;
    public static final double MAX_LONGITUDE = 180;
    public static final double MAX_HEADING= 359;
    public static final double MIN_HEADING= 0;

    //Container Dimensions by m
    public static final double LENGTH = 5.9;
    public static final double WIDTH = 2.35;
    public static final double HEIGHT = 2.39;
    public static final double AREA = 2 * HEIGHT * LENGTH + 2 * HEIGHT * WIDTH + 2 * WIDTH * LENGTH;

    //Temperature
    public static final double intervalMinusFiveTemperature = 20 - (-5);
    public static final double intervalSevenTemperature = 20 - 7;
    //Material Gross m
    public static final double GROSS = 0.2;
}

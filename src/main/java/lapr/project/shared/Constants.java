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
    //coordinatesLimits
    public static final double MAX_LATITUDE = 90;
    public static final double MAX_LONGITUDE = 180;
    public static final double MAX_HEADING = 359;
    public static final double MIN_HEADING = 0;
    //Container Dimensions by m
    public static final double CONTAINER_LENGTH = 5.9;
    public static final double CONTAINER_WIDTH = 2.35;
    public static final double CONTAINER_HEIGHT = 2.39;
    public static final double CONTAINER_EXTERIOR_MINUS5 = 0.0022;
    public static final double CONTAINER_EXTERIOR_7 = 0.00095;
    public static final double CONTAINER_MID_7 = 0.030;
    public static final double CONTAINER_MID_MINUS5 = 0.000061;
    public static final double CONTAINER_INTERIOR = 0.01175;
    public static final double AREA = 2 * CONTAINER_HEIGHT * CONTAINER_LENGTH + 2 * CONTAINER_HEIGHT * CONTAINER_WIDTH + 2 * CONTAINER_WIDTH * CONTAINER_LENGTH;
    public static final int VOYAGE_TIME = 9000;


    //Temperature
    public static final double INTERVALMINUSFIVETEMPERATURE = 20 - (-5);
    public static final double INTERVALSEVENTEMPERATURE = 20 - 7;


    //Material Gross m
    public static final double GROSS = 0.2;

    //Vessel type 71:
    public static final double C1X71 = 10;
    public static final double C1Y71 = 10;
    public static final double C1Z71 = 10;
    public static final double C1M71 = 2000;
    public static final double C2X71 = 40;
    public static final double C2Y71 = 20;
    public static final double C2Z71 = 20;
    public static final double C2M71 = 5000;
    public static final double C3X71 = 10;
    public static final double C3Y71 = 10;
    public static final double C3Z71 = 10;
    public static final double C3M71 = 2000;
    public static final double C4X71 = 60;
    public static final double C4Y71 = 20;
    public static final double C4Z71 = 20;
    public static final double C4M71 = 8000;
    public static final double C5X71 = 5;
    public static final double C5Y71 = 5;
    public static final double C5Z71 = 20;
    public static final double C5M71 = 1000;
    public static final double MTOTAL71 = 18000;

    //Vessel Type 72:
    public static final double C1X72 = 150;
    public static final double C1Y72 = 50;
    public static final double C1Z72 = 25;
    public static final double C1M72 = 10000;
    public static final double C2X72 = 25;
    public static final double C2Y72 = 25;
    public static final double C2Z72 = 25;
    public static final double C2M72 = 2000;
    public static final double C3X72 = 50;
    public static final double C3Y72 = 25;
    public static final double C3Z72 = 25;
    public static final double C3M72 = 8000;
    public static final double C4X72 = 25;
    public static final double C4Y72 = 25;
    public static final double C4Z72 = 50;
    public static final double C4M72 = 2000;
    public static final double MTOTAL72 = 22000;

    //Vessel Type 74:
    public static final double C1X74 = 250;
    public static final double C1Y74 = 50;
    public static final double C1Z74 = 100;
    public static final double C1M74 = 12000;
    public static final double C2X74 = 100;
    public static final double C2Y74 = 50;
    public static final double C2Z74 = 50;
    public static final double C2M74 = 5000;
    public static final double C3X74 = 25;
    public static final double C3Y74 = 50;
    public static final double C3Z74 = 50;
    public static final double C3M74 = 2000;
    public static final double C4X74 = 50;
    public static final double C4Y74 = 100;
    public static final double C4Z74 = 150;
    public static final double C4M74 = 8000;
    public static final double MTOTAL74 = 27000;
}

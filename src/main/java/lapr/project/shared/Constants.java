package lapr.project.shared;

public class Constants {

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
    public static final double CONTAINER_MID_7 = 0.000028;
    public static final double CONTAINER_MID_MINUS5 = 0.000061;
    public static final double CONTAINER_INTERIOR = 0.01175;
    public static final double AREA = 2 * CONTAINER_HEIGHT * CONTAINER_LENGTH + 2 * CONTAINER_HEIGHT * CONTAINER_WIDTH + 2 * CONTAINER_WIDTH * CONTAINER_LENGTH;

    public static final double THERMALCONDUCTIVITY_CONTAINER_EXTERIOR_MINUS5 = Double.parseDouble(System.getProperty("thermalConductivity.outer.minus5"));
    public static final double THERMALCONDUCTIVITY_CONTAINER_EXTERIOR_7 = Double.parseDouble(System.getProperty("thermalConductivity.outer.7"));
    public static final double THERMALCONDUCTIVITY_CONTAINER_MID_MINUS7 = Double.parseDouble(System.getProperty("thermalConductivity.mid.7"));
    public static final double THERMALCONDUCTIVITY_CONTAINER_MID_MINUS5 = Double.parseDouble(System.getProperty("thermalConductivity.mid.minus5"));
    public static final double THERMALCONDUCTIVITY_CONTAINER_INTERIOR_MINUS5 = Double.parseDouble(System.getProperty("thermalConductivity.inner.minus5"));
    public static final double THERMALCONDUCTIVITY_CONTAINER_INTERIOR_MINUS7 = Double.parseDouble(System.getProperty("thermalConductivity.inner.7"));

    //Temperature
    public static final double intervalMinusFiveTemperature = 20 - (-5);
    public static final double intervalSevenTemperature = 20 - 7;
    //Material Gross m
    public static final double GROSS = 0.2;

    public Constants() {
        //Empty
    }
}

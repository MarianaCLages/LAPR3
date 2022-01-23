-- Tables creation

create table Role
(
    id              varchar2(32),
    roleDescription varchar2(64)
        CONSTRAINT nn_RoleDescription not null,
    constraint pk_Role
        primary key (id)
);

create table ISOCode
(
    id     varchar2(32),
    width  number
        CONSTRAINT nn_Width not null,
    length number
        CONSTRAINT nn_Length not null,
    height number
        CONSTRAINT nn_Height not null,
    constraint pkIsoCode
        primary key (id)
);

create table AppUser
(
    id       varchar2(32),
    userName varchar2(64)
        constraint nn_UserName not null,
    password varchar2(64)
        constraint nn_Password not null,
    constraint pk_User
        primary key (id)
);

create table Container
(
    containerId varchar2(64),
    ISOCode     varchar2(32),
    payload     number
        CONSTRAINT nn_PayLoad not null,
    tare        number
        CONSTRAINT nn_Tare not null,
    gross       number
        CONSTRAINT nn_Gross not null,
    constraint pk_Container
        primary key (containerId),
    constraint fk_IsoCode
        foreign key (ISOCode)
            references ISOCode (id)

);

create table RefrigeratorContainer
(
    containerId   varchar2(64),
    energyConsume number
        CONSTRAINT nn_EnergyConsume not null,
    temperature   number
        CONSTRAINT nn_Temperature not null,

    constraint pk_RefrigeratorContainer
        primary key (containerId),
    constraint fk_RefrigeratorContainerContainerID
        foreign key (containerId)
            references Container (containerId)

);

create table Continent
(
    name varchar2(32),
    constraint pk_Continent
        primary key (name)
);

create table Country
(
    alpha2Code  char(2),
    alpha3Code  char(3),
    continent varchar2(32),
    name        varchar2(32)
        Constraint nn_NameCountry not null,
    capital     varchar2(32)
        CONSTRAINT nn_Capital not null,
    population  number
        CONSTRAINT nn_Population not null,
    constraint pk_Country
        primary key (alpha2Code, alpha3Code),
    constraint fk_CountryContinent
        foreign key (continent)
            references Continent (name)
);

create table VesselType
(
    vesselTypeId varchar2(32),
    vesselType   varchar2(32)
        CONSTRAINT nn_VesselType not null,
    constraint pk_VesselType
        primary key (vesselTypeId)
);

create table Vehicle
(
    vehicleId varchar2(32),
    constraint pk_Vehicle
        primary key (vehicleId)

);

create table CargoManifestType
(
    cargoManifestType varchar2(32),
    type              varchar2(32)
        CONSTRAINT nn_Type not null,
    constraint CargoManifestType_PK
        primary key (CargoManifestType)
);

CREATE table Border
(
    firstCountryAlpha2Code  char(2),
    firstCountryAlpha3Code  char(3),
    secondCountryAlpha2Code char(2),
    secondCountryAlpha3Code char(3),
    constraint pk_Border
        primary key (firstCountryAlpha2Code, firstCountryAlpha3Code, secondCountryAlpha2Code, secondCountryAlpha3Code),
    constraint fk_BorderFirstCountryAlpha2Code
        foreign key (firstCountryAlpha2Code, secondCountryAlpha2Code)
            references COUNTRY,
    constraint fk_BorderFirstCountryAlpha3Code
        foreign key (firstCountryAlpha3Code, secondCountryAlpha3Code)
            references COUNTRY
);

create table City
(
    cityID            varchar2(32),
    countryAlpha2Code char(2),
    countryAlpha3Code char(3),
    name              varchar2(32)
        CONSTRAINT nn_NameCity not null,
    latitude          number
        CONSTRAINT ck_LatitudeCity CHECK (latitude between -90 and 90),
    longitude         number
        CONSTRAINT ck_LongitudeCity CHECK (longitude between -180 and 180),
    constraint pk_City
        primary key (cityID),
    constraint fk_CityCountryID
        foreign key (countryAlpha2Code, countryAlpha3Code)
            references COUNTRY (alpha2Code, alpha3Code)
);

create table Address
(
    idAddress  varchar2(32),
    cityId     varchar2(32),
    street     varchar2(32)
        CONSTRAINT nn_StreetAddress not null,
    postalCode varchar2(32)
        CONSTRAINT nn_PostalCodeAddress not null,
    constraint pk_Address
        primary key (idAddress),
    constraint fk_AddressCityID
        foreign key (cityId)
            references CITY
);

create table Facility
(
    facilityId varchar2(32),
    alpha2Code char(2),
    alpha3Code char(3),
    longitude  number
        CONSTRAINT ck_Longitude CHECK (longitude BETWEEN -180 and 180),
    latitude   number
        CONSTRAINT ck_Latitude CHECK (latitude BETWEEN -90 and 90),
    name       varchar(32)
        CONSTRAINT nn_NameFacility not null,
    capacity   varchar2(32)
        constraint nn_Capacity not null,
    constraint pk_FacilityID
        primary key (facilityId),
    constraint fk_FacilityCountryID
        foreign key (alpha2Code, alpha3Code)
            references Country (alpha2Code, alpha3Code)
);

create table Port
(

    id          varchar2(32),
    facilityID  varchar2(32),
    dockingArea varchar2(32)
        CONSTRAINT nn_DockingArea not null,
    CONSTRAINT pk_IdPortID
        primary key (id),
    CONSTRAINT fk_FacilityPortID
        foreign key (facilityID)
            references FACILITY
);

create table Warehouse
(

    id         varchar2(32),
    facilityID varchar2(32),
    CONSTRAINT pk_IdWarehouseID
        primary key (id),
    CONSTRAINT fk_FacilityWarehouseID
        foreign key (facilityID)
            references FACILITY
);

CREATE table SeaDistance
(
    firstPortID  varchar2(32),
    secondPortID varchar2(32),
    seaDistance  NUMBER
        CONSTRAINT ck_SeaDistance check (seaDistance > 0),

    CONSTRAINT pk_SeaDistance
        primary key (firstPortID, secondPortID),
    CONSTRAINT fk_FirstPortID
        foreign key (firstPortID)
            references Port (id),
    CONSTRAINT fk_SecondPortID
        foreign key (secondPortID)
            references Port (id)
);

create table Employee
(
    id     varchar2(64),
    roleId varchar2(32),
    userId varchar2(32),
    email  varchar2(64)
        CONSTRAINT nn_Email not null,
    name   varchar2(64)
        CONSTRAINT nn_NameEmployee not null,
    constraint pk_Employee
        primary key (id),
    constraint fk_RoleID
        foreign key (roleId)
            references Role,
    constraint fk_UserID
        foreign key (userId)
            references AppUser (id)
);

create table Client
(
    id          varchar2(32),
    userId      varchar2(32),
    addressID   varchar2(32),
    name        varchar2(64)
        CONSTRAINT nn_NameClient not null,
    email       varchar2(32)
        CONSTRAINT nn_EmailClient not null,
    ccNumber    varchar2(32)
        CONSTRAINT nn_CCNumber not null,
    phoneNumber NUMERIC(9)
        CONSTRAINT ck_ClientPhoneNumber CHECK (REGEXP_LIKE(phoneNumber, '^\d{9}$')),
    birthDate   date
        CONSTRAINT nn_BirthDate not null,

    constraint pk_Client
        primary key (id),
    constraint fk_UserIDClient
        foreign key (userId)
            references AppUser (id),
    constraint fk_ClientAddressID
        foreign key (addressID)
            references ADDRESS (idAddress)
);

create table EmployeeFacility
(
    facilityId varchar2(32)
        constraint fk_EmployeeFacilityFacilityID
            references FACILITY (facilityId),
    employeeId varchar2(64)
        constraint fk_EmployeeFacilityEmployeeID
            references EMPLOYEE (id),
    constraint pk_EmployeeFacilityTable
        primary key (facilityId, employeeId)
);

CREATE TABLE MaterialType
(
    id   varchar2(32),
    type varchar2(32)
        CONSTRAINT nn_MaterialType not null,
    CONSTRAINT pk_MaterialType
        primary key (id)
);

CREATE TABLE Material
(

    id                      varchar2(32),
    materialTypeID          varchar2(32),
    proportionalityConstant number
        CONSTRAINT nn_ProportionalityConstant not null,
    temperature             number
        CONSTRAINT nn_TemperatureMaterial not null,
    constraint pk_Material
        primary key (id),
    constraint fk_MaterialTypeID
        foreign key (materialTypeID)
            references MaterialType (id)

);

CREATE TABLE ContainerMaterial
(
    refrigeratedContainerID varchar2(32),
    materialID              varchar2(32),
    wallType                varchar2(32)
        CONSTRAINT nn_WallType not null,
    constraint pk_ContainerMaterial
        primary key (refrigeratedContainerID, materialID),
    constraint fk_Material
        foreign key (materialID)
            references Material (id),
    constraint fk_RefrigeratedContainer
        foreign key (refrigeratedContainerID)
            references RefrigeratorContainer (containerId)
);

create table Ship
(
    mmsi       char(9),
    vehicleId  varchar2(32),
    vesselType varchar2(32),
    imo        char(10)
        CONSTRAINT u_Imo unique,
    callSign   varchar2(32)
        CONSTRAINT u_CallSign unique,
    name       varchar2(32)
        CONSTRAINT nn_NameShip not null,
    length     number
        CONSTRAINT nn_LengthShip not null,
    width      number
        CONSTRAINT nn_WidthShip not null,
    capacity   number
        CONSTRAINT nn_CapacityShip not null,
    draft      number
        CONSTRAINT nn_DraftShip not null,
    constraint fk_ShipTransportID
        foreign key (vehicleId)
            references Vehicle (vehicleId),
    constraint pk_Ship
        primary key (mmsi),
    constraint fk_ShipVesselType
        foreign key (vesselType)
            references VesselType (vesselTypeId)
);

create table Transceiver
(
    transceiverID varchar2(32),
    transceiver   varchar2(32)
        CONSTRAINT nn_Transceiver not null,
    mmsi          char(9),
    class         varchar2(32)
        CONSTRAINT nn_Class not null,
    constraint pk_transceiver
        primary key (transceiverID, mmsi),
    CONSTRAINT fk_MMSI
        foreign key (mmsi)
            references Ship (mmsi)
);

create table PositionalMessage
(
    baseDateTime  date,
    mmsi          char(9),
    transceiverID varchar2(32),
    longitude     number
        CONSTRAINT ck_LongitudePositionalMessage CHECK (longitude between -180 and 180),
    latitude      number
        CONSTRAINT ck_LatitudePositionalMessage CHECK (latitude between -90 and 90),
    SOG           number
        CONSTRAINT nn_SogPositionalMessage not null,
    COG           number
        CONSTRAINT ck_CogPositionalMessage CHECK ( COG between 0 and 359),
    heading       number
        Constraint ck_HeadingPositionalMessage CHECK ( heading between 0 and 359),
    constraint pk_PositionalMessage
        primary key (baseDateTime, mmsi, transceiverID),
    constraint fk_PositionalMessage
        foreign key (mmsi, transceiverID)
            references Transceiver (mmsi, transceiverID)
);


create table Trip
(
    idTrip    varchar2(32),
    vehicleId varchar2(32),
    startDate date
        CONSTRAINT nn_StartDate not null,
    endDate   date
        CONSTRAINT nn_EndDate not null,
    constraint pk_Trip
        primary key (idTrip, vehicleId),
    constraint fk_TripVehicleID
        foreign key (vehicleId)
            references Vehicle (vehicleId)
);

create table FacilityTrip
(
    facilityId    varchar2(32),
    idTrip        varchar2(32),
    vehicleId     varchar2(32),
    deliveryDate  date
        CONSTRAINT nn_DeliveryDate not null,
    departureDate date
        CONSTRAINT nn_DepartureID not null,
    constraint pk_FacilityTrip
        primary key (facilityId, idTrip, vehicleId),
    constraint fk_FacilityTripVehicleId
        foreign key (vehicleId, idTrip)
            references Trip,
    constraint fk_FacilityTripFacilityId
        foreign key (facilityId)
            references Facility (facilityId)
);

create table CargoManifest
(
    cargoManifestId   varchar2(32),
    idTrip            varchar2(32),
    vehicleId         varchar2(32),
    facilityId        varchar2(32),
    cargoManifestType varchar2(32),
    cargoManifestDate date
        CONSTRAINT nn_CargoManifestDate not null,

    constraint pk_CargoManifest
        primary key (cargoManifestId),
    constraint fk_CargoManifestVehicleId
        foreign key (idTrip, vehicleId)
            references Trip (idTrip, vehicleId),
    constraint fk_CargoManifestTypeId
        foreign key (CargoManifestType)
            references CargoManifestType (cargoManifestType),
    constraint fk_CargoManifestFacilityId
        foreign key (facilityId)
            references FACILITY (facilityId)
);

create table CargoManifestContainer
(
    cargoManifestId varchar2(32),
    containerId     varchar2(64),
    xPos            Number
        CONSTRAINT nn_XPos not null,
    yPos            Number
        CONSTRAINT nn_YPos not null,
    zPos            Number
        CONSTRAINT nn_ZPos not null,
    constraint pk_CargoManifestContainer
        primary key (cargoManifestId, containerId),
    constraint fk_CargoManifestContainerCargoManifest
        foreign key (cargoManifestId)
            references CargoManifest (cargoManifestId),
    constraint fk_CargoManifestContainerContainer
        foreign key (containerId)
            references Container (containerId)
);

create table ContainerClient
(
    clientId        varchar2(32),
    containerId     varchar2(64),
    cargoManifestID varchar2(32),
    constraint pk_ContainerClient
        primary key (clientId, containerId, cargoManifestID),
    constraint fk_CargoManifestContainer
        foreign key (containerId, cargoManifestID)
            references CargoManifestContainer (containerId, cargoManifestID),
    constraint fk_ContainerClientClientID
        foreign key (clientId)
            references Client (id)
);

CREATE TABLE AuditTrail
(
    cargoManifestID        varchar2(32),
    containerID            varchar2(32),
    writeOperationsSummary varchar2(255)
        CONSTRAINT nn_WriteOperationsSummary not null,
    operationDate          date
        CONSTRAINT nn_OperationDate not null,
    userLogged             varchar2(32)
        CONSTRAINT nn_UserLogged not null,
    CONSTRAINT pk_AuditTrail
        primary key (cargoManifestID, containerID),
    CONSTRAINT fk_CargoManifestContainerAuditTrail
        foreign key (containerID, cargoManifestID)
            references CargoManifestContainer (containerID, cargoManifestID)
);

create table TripEmployee
(
    idTrip     varchar2(32),
    vehicleId  varchar2(32),
    employeeId varchar2(64),
    constraint pk_TripEmployees
        primary key (idTrip, vehicleId, employeeId),
    constraint fk_TripEmployeesTripId
        foreign key (idTrip, vehicleId)
            references Trip,
    constraint fk_TripEmployeeEmployeeId
        foreign key (employeeId)
            references Employee

);



create table Stock
(

    facilityID  varchar2(32),
    containerID varchar2(32),

    CONSTRAINT pk_Stock
        primary key (facilityID, containerID),
    CONSTRAINT fk_FacilityIDStock
        foreign key (facilityID)
            references FACILITY (facilityId),
    CONSTRAINT fk_ContainerID
        foreign key (containerID)
            references Container (containerId)
);

create table Generator
(
    generatorId  varchar2(32),
    mmsi         char(9),
    generatorPow number
        CONSTRAINT nn_GeneratorPow not null,
    constraint pk_Generator
        primary key (generatorId),
    CONSTRAINT fk_ShipMMSI
        foreign key (mmsi)
            references Ship (mmsi)
);


CREATE TABLE TRUCK
(

    vehicleID varchar2(32),
    name      varchar2(255)
        CONSTRAINT nn_NameTruck not null,

    CONSTRAINT pk_Truck
        primary key (vehicleID),
    CONSTRAINT fk_Truck
        foreign key (vehicleID)
            references VEHICLE (vehicleID)


);

CREATE TABLE TRUCKEMPLOYEE
(

    vehicleID  varchar2(32),
    employeeID varchar2(255),

    CONSTRAINT pk_TruckEmployee
        primary key (vehicleID, employeeID),
    CONSTRAINT fk_TruckEmployeeVehicleID
        foreign key (vehicleID)
            references TRUCK (vehicleID),
    CONSTRAINT fk_TruckEmployeeID
        foreign key (employeeID)
            references EMPLOYEE (ID)


);

CREATE TABLE SHIPEMPLOYEE
(

    mmsi       char(9),
    employeeID varchar2(32),

    CONSTRAINT pk_ShipEmployee
        primary key (employeeID, mmsi),
    CONSTRAINT fk_ShipEmployeeMmsi
        foreign key (mmsi)
            references SHIP (MMSI),
    CONSTRAINT fk_ShipEmployeeID
        foreign key (employeeID)
            references EMPLOYEE (ID)


);

CREATE TABLE PORTWAREHOUSE
(

    portId      varchar2(32),
    warehouseId varchar2(32),

    CONSTRAINT pk_PortWarehouse
        primary key (portId, warehouseId),
    CONSTRAINT fk_PortWarehousePortID
        foreign key (portId)
            references PORT (ID),
    CONSTRAINT fk_PortWarehouseWarehouseID
        foreign key (warehouseId)
            references WAREHOUSE (ID)


)
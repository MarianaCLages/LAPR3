-- Tables creation

create table Role
(
    id              varchar2(32),
    roleDescription varchar2(64)
        CONSTRAINT nnRoleDescription not null,
    constraint pkRole
        primary key (id)
);

create table ISOCode
(
    id     varchar2(32),
    width  number
        CONSTRAINT nnWidth not null,
    length number
        CONSTRAINT nnLength not null,
    height number
        CONSTRAINT nnHeight not null,
    constraint pkIsoCode
        primary key (id)
);

create table AppUser
(
    id       varchar2(32),
    userName varchar2(64)
        constraint nnUserName not null,
    password varchar2(64)
        constraint nnPassword not null,
    constraint pkUser
        primary key (id)
);

create table Container
(
    containerId varchar2(64),
    ISOCode     varchar2(32),
    payload     number
        CONSTRAINT nnPayLoad not null,
    tare        number
        CONSTRAINT nnTare not null,
    gross       number
        CONSTRAINT nnGross not null,
    constraint pkContainer
        primary key (containerId),
    constraint fkIsoCode
        foreign key (ISOCode)
            references ISOCode (id)

);

create table RefrigeratorContainer
(
    containerId   varchar2(64),
    energyConsume number
        CONSTRAINT nnEnergyConsume not null,
    temperature   number
        CONSTRAINT nnTemperature not null,

    constraint pkRefrigeratorContainer
        primary key (containerId),
    constraint fkRefrigeratorContainerContainerID
        foreign key (containerId)
            references Container (containerId)

);

create table Continent
(
    continentId varchar2(32),
    name        varchar2(32)
        CONSTRAINT nnNameContinent not null,
    constraint pkContinent
        primary key (continentId)
);

create table Country
(
    alpha2Code  char(2),
    alpha3Code  char(3),
    continentID varchar2(32),
    capital     varchar2(32)
        CONSTRAINT nnCapital not null,
    population  number
        CONSTRAINT nnPopulation not null,
    constraint pkCountry
        primary key (alpha2Code, alpha3Code),
    constraint fkCountryContinent
        foreign key (continentID)
            references Continent (continentId)
);

create table VesselType
(
    vesselTypeId varchar2(32),
    vesselType   varchar2(32)
        CONSTRAINT nnVesselType not null,
    constraint pkVesselType
        primary key (vesselTypeId)
);

create table Vehicle
(
    vehicleId varchar2(32),
    constraint pkVehicle
        primary key (vehicleId)

);

create table CargoManifestType
(
    cargoManifestType varchar2(32),
    type              varchar2(32)
        CONSTRAINT nnType not null,
    constraint CargoManifestType_PK
        primary key (CargoManifestType)
);

CREATE table Border
(
    firstCountryAlpha2Code  char(2),
    firstCountryAlpha3Code  char(3),
    secondCountryAlpha2Code char(2),
    secondCountryAlpha3Code char(3),
    constraint pkBorder
        primary key (firstCountryAlpha2Code, firstCountryAlpha3Code, secondCountryAlpha2Code, secondCountryAlpha3Code),
    constraint fkBorderFirstCountryAlpha2Code
        foreign key (firstCountryAlpha2Code, secondCountryAlpha2Code)
            references COUNTRY,
    constraint fkBorderFirstCountryAlpha3Code
        foreign key (firstCountryAlpha3Code, secondCountryAlpha3Code)
            references COUNTRY
);

create table City
(
    cityID            varchar2(32),
    countryAlpha2Code char(2),
    countryAlpha3Code char(3),
    name              varchar2(32)
        CONSTRAINT nnNameCity not null,
    constraint pkCity
        primary key (cityID),
    constraint fkCityCountryID
        foreign key (countryAlpha2Code, countryAlpha3Code)
            references COUNTRY (alpha2Code, alpha3Code)
);

create table Address
(
    idAddress  varchar2(32),
    cityId     varchar2(32),
    street     varchar2(32)
        CONSTRAINT nnStreetAddress not null,
    postalCode varchar2(32)
        CONSTRAINT nnPostalCodeAdress not null,
    constraint pkAddress
        primary key (idAddress),
    constraint fkAddressCityID
        foreign key (cityId)
            references CITY
);

create table Facility
(
    facilityId varchar2(32),
    alpha2Code char(2),
    alpha3Code char(3),
    longitude  number
        CONSTRAINT ckLongitude CHECK (longitude BETWEEN -180 and 180),
    latitude   number
        CONSTRAINT ckLatitude CHECK (latitude BETWEEN -90 and 90),
    name       varchar(32)
        CONSTRAINT nnNameFacility not null,
    capacity   varchar2(32)
        constraint nnCapacity not null,
    constraint pkFacilityID
        primary key (facilityId),
    constraint fkFacilityCountryID
        foreign key (alpha2Code, alpha3Code)
            references Country (alpha2Code, alpha3Code)
);

create table Port
(

    id          varchar2(32),
    facilityID  varchar2(32),
    dockingArea varchar2(32)
        CONSTRAINT nnDockingArea not null,
    CONSTRAINT pkIdPortID
        primary key (id),
    CONSTRAINT fkFacilityPortID
        foreign key (facilityID)
            references FACILITY
);

create table Warehouse
(

    id         varchar2(32),
    facilityID varchar2(32),
    portID     varchar2(32),
    CONSTRAINT pkIdWarehouseID
        primary key (id),
    CONSTRAINT fkFacilityWarehouseID
        foreign key (facilityID)
            references FACILITY,
    CONSTRAINT fkPortID
        foreign key (portID)
            references Port
);

CREATE table SeaDistance
(
    firstPortID  varchar2(32),
    secondPortID varchar2(32),
    seaDistance  NUMBER
        CONSTRAINT ckSeaDistance check (seaDistance > 0),

    CONSTRAINT pkSeaDistance
        primary key (firstPortID, secondPortID),
    CONSTRAINT fkFirstPortID
        foreign key (firstPortID)
            references Port (id),
    CONSTRAINT fkSecondPortID
        foreign key (secondPortID)
            references Port (id)
);

create table Employee
(
    id     varchar2(64),
    roleId varchar2(32),
    userId varchar2(32),
    email  varchar2(64)
        CONSTRAINT nnEmail not null,
    name   varchar2(64)
        CONSTRAINT nnNameEmployee not null,
    constraint pkEmployee
        primary key (id),
    constraint fkRoleID
        foreign key (roleId)
            references Role,
    constraint fkUserID
        foreign key (userId)
            references AppUser (id)
);

create table Client
(
    id          varchar2(32),
    userId      varchar2(32),
    addressID   varchar2(32),
    name        varchar2(64)
        CONSTRAINT nnNameClient not null,
    email       varchar2(32)
        CONSTRAINT nnEmailClient not null,
    ccNumber    varchar2(32)
        CONSTRAINT nnCCNumber not null,
    phoneNumber NUMERIC(9)
        CONSTRAINT ckClientPhoneNumber CHECK (REGEXP_LIKE(phoneNumber, '^\d{9}$')),
    birthDate   date
        CONSTRAINT nnBirthDate not null,

    constraint pkClient
        primary key (id),
    constraint fkUserIDClient
        foreign key (userId)
            references AppUser (id),
    constraint fkClientAddressID
        foreign key (addressID)
            references ADDRESS (idAddress)
);

create table EmployeeFacility
(
    facilityId varchar2(32)
        constraint fkEmployeeFacilityFacilityID
            references FACILITY (facilityId),
    employeeId varchar2(64)
        constraint fkEmployeeFacilityEmployeeID
            references EMPLOYEE (id),
    constraint pkEmployeeFacilityTable
        primary key (facilityId, employeeId)
);

CREATE TABLE MaterialType
(
    id   varchar2(32),
    type varchar2(32)
        CONSTRAINT nnMaterialType not null,
    CONSTRAINT pkMaterialType
        primary key (id)
);

CREATE TABLE Material
(

    id             varchar2(32),
    materialTypeID varchar2(32),
    constraint pkMaterial
        primary key (id),
    constraint fkMaterialTypeID
        foreign key (materialTypeID)
            references MaterialType (id)

);

CREATE TABLE ContainerMaterial
(
    refrigeratedContainerID varchar2(32),
    materialID              varchar2(32),
    constraint pkContainerMaterial
        primary key (refrigeratedContainerID, materialID),
    constraint fkMaterial
        foreign key (materialID)
            references Material (id),
    constraint fkRefrigeratedContainer
        foreign key (refrigeratedContainerID)
            references RefrigeratorContainer (containerId)
);

create table Ship
(
    mmsi       char(9),
    vehicleId  varchar2(32),
    vesselType varchar2(32),
    imo        char(10)
        CONSTRAINT uImo unique,
    callSign   varchar2(32)
        CONSTRAINT uCallSign unique,
    name       varchar2(32)
        CONSTRAINT nnNameShip not null,
    length     number
        CONSTRAINT nnLengthShip not null,
    width      number
        CONSTRAINT nnWidthShip not null,
    capacity   number
        CONSTRAINT nnCapacityShip not null,
    draft      number
        CONSTRAINT nnDraftShip not null,
    constraint fkShipTransportID
        foreign key (vehicleId)
            references Vehicle (vehicleId),
    constraint pkShip
        primary key (mmsi),
    constraint fkShipVesselType
        foreign key (vesselType)
            references VesselType (vesselTypeId)
);

create table Transceiver
(
    transceiverID varchar2(32),
    transceiver   varchar2(32)
        CONSTRAINT nnTransceiver not null,
    mmsi          char(9),
    class         varchar2(32)
        CONSTRAINT nnClass not null,
    constraint pkTransceiver
        primary key (transceiverID, mmsi),
    CONSTRAINT fkMMSI
        foreign key (mmsi)
            references Ship (mmsi)
);

create table PositionalMessage
(
    baseDateTime  date,
    mmsi          char(9),
    transceiverID varchar2(32),
    longitude     number
        CONSTRAINT ckLongitudePositionalMessage CHECK (longitude between -180 and 180),
    latitude      number
        CONSTRAINT ckLatitudePositionalMessage CHECK (latitude between -90 and 90),
    SOG           number
        CONSTRAINT nnSogPositionalMessage not null,
    COG           number
        CONSTRAINT ckCogPositionalMessage CHECK ( COG between 0 and 359),
    heading       number
        Constraint ckHeadingPositionalMessage CHECK ( heading between 0 and 359),
    constraint pkPositionalMessage
        primary key (baseDateTime, mmsi, transceiverID),
    constraint fkPositionalMessage
        foreign key (mmsi,transceiverID)
            references Transceiver (mmsi,transceiverID)
);


create table Trip
(
    idTrip    varchar2(32),
    vehicleId varchar2(32),
    startDate date
        CONSTRAINT nnStartDate not null,
    endDate   date
        CONSTRAINT nnEndDate not null,
    constraint pkTrip
        primary key (idTrip, vehicleId),
    constraint fkTripVehicleID
        foreign key (vehicleId)
            references Vehicle (vehicleId)
);

create table FacilityTrip
(
    facilityId    varchar2(32),
    idTrip        varchar2(32),
    vehicleId     varchar2(32),
    deliveryDate  date
        CONSTRAINT nnDeliveryDate not null,
    departureDate date
        CONSTRAINT nnDepartureID not null,
    constraint pkFacilityTrip
        primary key (facilityId, idTrip, vehicleId),
    constraint fkFacilityTripVehicleId
        foreign key (vehicleId, idTrip)
            references Trip,
    constraint fkFacilityTripFacilityId
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
        CONSTRAINT nnCargoManifestDate not null,

    constraint pkCargoManifest
        primary key (cargoManifestId),
    constraint fkCargoManifestVehicleId
        foreign key (idTrip, vehicleId)
            references Trip,
    constraint fkCargoManifestTypeId
        foreign key (CargoManifestType)
            references CargoManifestType (cargoManifestType),
    constraint fkCargoManifestFacilityId
        foreign key (facilityId)
            references FACILITY (facilityId)
);

create table CargoManifestContainer
(
    cargoManifestId varchar2(32),
    containerId     varchar2(64),
    xPos            Number
        CONSTRAINT nnXPos not null,
    yPos            Number
        CONSTRAINT nnYPos not null,
    zPos            Number
        CONSTRAINT nnZPos not null,
    constraint pkCargoManifestContainer
        primary key (cargoManifestId, containerId),
    constraint fkCargoManifestContainerCargoManifest
        foreign key (cargoManifestId)
            references CargoManifest (cargoManifestId),
    constraint fkCargoManifestContainerContainer
        foreign key (containerId)
            references Container (containerId)
);

create table ContainerClient
(
    clientId        varchar2(32),
    containerId     varchar2(64),
    cargoManifestID varchar2(32),
    constraint pkContainerClient
        primary key (clientId, containerId, cargoManifestID),
    constraint fkCargoManifestContainer
        foreign key (containerId, cargoManifestID)
            references CargoManifestContainer (containerId, cargoManifestID),
    constraint fkContainerClientClientID
        foreign key (clientId)
            references Client (id)
);

CREATE TABLE AuditTrail
(
    cargoManifestID        varchar2(32),
    containerID            varchar2(32),
    writeOperationsSummary varchar2(255)
        CONSTRAINT nnWriteOperationsSummary not null,
    operationDate          date
        CONSTRAINT nnOperationDate not null,
    userLogged             varchar2(32)
        CONSTRAINT nnUserLogged not null,
    CONSTRAINT pkAuditTrail
        primary key (cargoManifestID, containerID),
    CONSTRAINT fkCargoManifestContainerAuditTrail
        foreign key (containerID, cargoManifestID)
            references CargoManifestContainer (containerID, cargoManifestID)
);

create table TripEmployee
(
    idTrip     varchar2(32),
    vehicleId  varchar2(32),
    employeeId varchar2(64),
    constraint pkTripEmployees
        primary key (idTrip, vehicleId, employeeId),
    constraint fkTripEmployeesTripId
        foreign key (idTrip, vehicleId)
            references Trip,
    constraint fkTripEmployeeEmployeeId
        foreign key (employeeId)
            references Employee

);

create table SeaDistance
(
    firstPortID  varchar2(32),
    secondPortID varchar2(32),
    seaDistance  varchar2(32)
        constraint nnSeaDistance not null,
    constraint fkFirstPortID
        foreign key (firstPortID)
            references Facility (facilityId),
    constraint fSecondPortID
        foreign key (secondPortID)
            references Facility (facilityId),
    constraint pkSeaDistance
        primary key (firstPortID, secondPortID)
);

create table Stock
(

    facilityID  varchar2(32),
    containerID varchar2(32),

    CONSTRAINT pkStock
        primary key (facilityID, containerID),
    CONSTRAINT fkFacilityIDStock
        foreign key (facilityID)
            references FACILITY (facilityId),
    CONSTRAINT fkContainerID
        foreign key (containerID)
            references Container (containerId)
);

create table Generator
(
    generatorId  varchar2(32),
    mmsi         char(9),
    generatorPow number
        CONSTRAINT nnGeneratorPow not null,
    constraint pkGenerator
        primary key (generatorId),
    CONSTRAINT fkShipMMSI
        foreign key (mmsi)
            references Ship (mmsi)
);


CREATE TABLE TRUCK
(

    vehicleID varchar2(32),
    name      varchar2(255)
        CONSTRAINT nnNameTruck not null,

    CONSTRAINT pkTruck
        primary key (vehicleID),
    CONSTRAINT fkTruck
        foreign key (vehicleID)
            references VEHICLE (vehicleID)


);

CREATE TABLE TRUCKEMPLOYEE
(

    vehicleID  varchar2(32),
    employeeID varchar2(255),

    CONSTRAINT pkTruckEmployee
        primary key (vehicleID, employeeID),
    CONSTRAINT fkTruckEmployeeVehicleID
        foreign key (vehicleID)
            references TRUCK (vehicleID),
    CONSTRAINT fkTruckEmployeeID
        foreign key (employeeID)
            references EMPLOYEE (ID)


);

CREATE TABLE SHIPEMPLOYEE
(

    mmsi       char(9),
    employeeID varchar2(32),

    CONSTRAINT pkShipEmployee
        primary key (employeeID, mmsi),
    CONSTRAINT fkShipEmployeeMmsi
        foreign key (mmsi)
            references SHIP (MMSI),
    CONSTRAINT fkShipEmployeeID
        foreign key (employeeID)
            references EMPLOYEE (ID)


);

CREATE TABLE PORTWAREHOUSE
(

    portId      varchar2(32),
    warehouseId varchar2(32),

    CONSTRAINT pkPortWarehouse
        primary key (portId, warehouseId),
    CONSTRAINT fkPortWarehousePortID
        foreign key (portId)
            references PORT (ID),
    CONSTRAINT fkPortWarehouseWarehouseID
        foreign key (warehouseId)
            references WAREHOUSE (ID)


)
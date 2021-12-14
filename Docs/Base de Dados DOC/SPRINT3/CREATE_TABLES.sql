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
    constraint pkContinent
        primary key (continentId)
);

create table Country
(
    alpha2Code  char(2)
        CONSTRAINT nnAlpha2Code not null,
    alpha3Code  char(3)
        CONSTRAINT nnAlpha3Code not null,
    continentID varchar2(32),
    capital     varchar2(32)
        CONSTRAINT nnCapital not null,
    population  number
        CONSTRAINT nnPopulation not null,
    constraint pkCountry
        primary key (alpha2Code,alpha3Code),
    constraint fkCountryContinent
        foreign key (continentID)
            references Continent (continentId)
);

create table FacilityType
(
    facilityType varchar2(32),
    type         varchar2(32)
        CONSTRAINT nnTypeFacility not null,
    constraint pkFacilityType
        primary key (facilityType)
);

create table VesselType
(
    vesselTypeId varchar2(32),
    vesselType   varchar2(32)
        CONSTRAINT nnVesselType not null,
    constraint pkVesselType
        primary key (vesselTypeId)
);

create table Generator
(
    generatorId              varchar2(32),
    nEnergyGen               number
        CONSTRAINT nnEnergyGen not null,
    generatorPow             number
        CONSTRAINT nnGeneratorPow not null,
    refrigerationTemperature number
        CONSTRAINT nnRefrigerationTemperature not null,
    constraint pkGenerator
        primary key (generatorId)
);

create table Vehicle
(
    vehicleId varchar2(32),
    constraint pkVehicle
        primary key (vehicleId)

);

create table Transceiver
(
    transceiverID varchar2(32),
    transceiver   varchar2(32),
    constraint pkTransceiver
        primary key (transceiverID)
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
    firstCountryID  varchar2(32),
    secondCountryID varchar2(32),
    constraint pkBorder
        primary key (firstCountryID, secondCountryID),
    constraint fkBorderFirstCountryID
        foreign key (firstCountryID)
            references COUNTRY (countryId),
    constraint fkBorderSecondCountryID
        foreign key (secondCountryID)
            references COUNTRY (countryId)
);

create table City
(
    cityID    varchar2(32)
        constraint pkCity primary key,
    countryID varchar2(32),
    name      varchar2(32)
        CONSTRAINT nnNameCity not null,
    constraint fkCityCountryID foreign key (countryID) references COUNTRY (countryId)
);

create table Address
(
    idAddress  varchar2(32)
        constraint pkAddress primary key,
    cityId     varchar2(32),
    constraint fkAddressCityID foreign key (cityId) references CITY,
    street     varchar2(32)
        CONSTRAINT nnStreetAddress not null,
    postalCode varchar2(32)
        CONSTRAINT nnPostalCodeAdress not null
);

create table Facility
(
    facilityId   varchar2(32),
    facilityType varchar2(32),
    countryId    varchar2(32),
    longitude    number
        CONSTRAINT ckLongitude CHECK (longitude BETWEEN -180 and 180),
    latitude     number
        CONSTRAINT ckLatitude CHECK (latitude BETWEEN -90 and 90),
    name         varchar(32)
        CONSTRAINT nnNameFacility not null,
    capacity     varchar2(32)
        constraint nnCapacity not null,
    constraint pkFacilityID
        primary key (facilityId),
    constraint fkFacilityCountryID
        foreign key (countryId)
            references Country (countryId),
    constraint fkFacilityType
        foreign key (facilityType)
            references FacilityType (facilityType)
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

create table ContainerClient
(
    clientId    varchar2(32),
    containerId varchar2(64),
    vehicleID   varchar2(64),
    tripID      varchar2(64),
    constraint pkContainerClient
        primary key (clientId, containerId),
    constraint fkContainerClientContainerID
        foreign key (containerId) references Container (containerId),
    constraint fkContainerClientClientID
        foreign key (clientId) references Client (id)
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

create table Ship
(
    mmsi       char(9),
    vehicleId  varchar2(32)
        constraint fkShipTransportID
            references Vehicle (vehicleId),
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
    constraint pkShip
        primary key (mmsi, vehicleId),
    constraint fkShipVesselType
        foreign key (vesselType)
            references VesselType (vesselTypeId)
);

create table ShipGenerator
(
    MMSI        CHAR(9),
    vehicleId   VARCHAR2(32),
    generatorId VARCHAR2(32),
    constraint fkShipGeneratorGeneratorID
        foreign key (generatorID) references GENERATOR (generatorId),
    constraint pkShipGenerator
        primary key (MMSI, vehicleId, generatorId),
    constraint fkShipGeneratorMMSI
        foreign key (MMSI, vehicleId) references SHIP (mmsi, vehicleId)
);

create table PositionalMessage
(
    baseDateTime  date,
    mmsi          char(9),
    vehicleId     varchar2(32),
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
    transceiverID varchar2(32),
    constraint pkPositionalMessage
        primary key (baseDateTime, mmsi, vehicleId),
    constraint fkPositionalMessageShip
        foreign key (mmsi, vehicleId) references Ship,
    constraint fkPositionalMessageTransceiverID
        foreign key (Transceiverid) references Transceiver (Transceiverid)
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
        primary key (facilityId, idTrip),
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
        foreign key (vehicleId, idTrip)
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
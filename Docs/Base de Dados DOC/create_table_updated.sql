
create table Role
(
    id              varchar2(32),
    roleDescription varchar2(64),
    constraint ROLE_PK
        primary key (id)
);

create table Employee
(
    id     varchar2(64),
    RoleId varchar2(32),
    UserId varchar2(32),
    email  varchar2(64),
    name   varchar2(64),
    constraint EMPLOYEE_PK
        primary key (id),
    constraint ROLEID_FK
        foreign key (RoleId)
            references Role (id)
);

create table AppUser
(
    id       varchar2(32),
    userName varchar2(64),
    password varchar2(64),
    constraint USER_PK
        primary key (id)
);

create table Client
(
    id          varchar2(32),
    UserId      varchar2(32),
    name        varchar2(64),
    email       varchar2(32),
    ccNumber    varchar2(32),
    phoneNumber varchar2(16),
    address     varchar2(64),
    birthDate   date,
    constraint CLIENT_PK
        primary key (id),
    constraint USER_FK
        foreign key (UserId)
            references AppUser (id)
);

create table ISOCode
(
    id     varchar2(32),
    width  number,
    length number,
    height number,
    constraint ISOCode_PK
        primary key (id)
);

create table Container
(
    containerId varchar2(64),
    ISOCode     varchar2(32),
    payload     number,
    tare        number,
    gross       number,
    constraint CONTAINER_PK
        primary key (containerId),
    constraint ISOCODE_FK
        foreign key (ISOCode)
            references ISOCode (id)

);

create table ContainerPosition
(
    containerId varchar2(64),
    xPos        integer,
    yPos        integer,
    zPos        integer,
    constraint CONTAINERPOSITION_PK
        primary key (containerId),
    constraint CONTAINERID_FK
        foreign key (containerId)
            references Container (containerId)

);

create table RefrigeratorContainer
(
    containerId   varchar2(64),
    energyConsume number,
    temperature   number,

    constraint REFRIGERATORCONTAINER_PK
        primary key (containerId),
    constraint RefrigeratorContainerCONTAINERID_FK
        foreign key (containerId)
            references Container (containerId)

);

create table ContainerClient
(
    clientId    varchar2(32),
    containerId varchar2(64),
    constraint ContainerClient_PK
        primary key (clientId, containerId),
    constraint ContainerClientCONTAINERID_FK
        foreign key (containerId) references Container (containerId),
    constraint ContainerClientCLIENTID_FK
        foreign key (clientId) references Client (id)
);

create table Continent
(
    continentId varchar2(32),
    name        varchar2(64),
    constraint CONTINENT_PK
        primary key (continentId)
);

create table Country
(
    countryId   varchar2(32),
    continentId varchar2(32),
    name        varchar2(64),
    constraint COUNTRY_PK
        primary key (countryId),
    constraint CountryCONTINENT_FK
        foreign key (continentId)
            references Continent (continentId)
);

create table FacilityType
(
    facilityType varchar2(32),
    type         varchar2(32),
    constraint FacilityType_PK
        primary key (facilityType)
);

create table Facility
(
    facilityId   varchar2(32),
    facilityType varchar2(32),
    countryId    varchar2(32),
    longitude    number,
    latitude     number,
    name         varchar(32),
    constraint FACILITY_PK
        primary key (facilityId),
    constraint FacilityCOUNTRYID_FK
        foreign key (countryId)
            references Country (countryId),
    constraint FacilityType_FK
        foreign key (facilityType)
            references FacilityType
);

create table Warehouse
(
    facilityId varchar2(32),

    constraint WAREHOUSE_PK
        primary key (facilityId),
    constraint WarehouseFacilityID_FK
        foreign key (facilityId)
            references Facility (facilityId)
);


create table Port
(
    facilityId varchar2(32),

    constraint PORT_PK
        primary key (facilityId),
    constraint PortFacilityID_FK
        foreign key (facilityId)
            references Facility (facilityId)
);


create table EmployeeFacility
(
    facilityId varchar2(32)
        constraint EMPLOYEEFACILITYFACILITYID_FK
            references FACILITY (facilityId),
    employeeId varchar2(64)
        constraint EMPLOYEEFACILITYEMPLOYEEID_FK
            references EMPLOYEE (id),
    constraint EMPLOYEEFACILITY_PK
        primary key (facilityId, employeeId)
);


create table VesselType
(
    vesselTypeId varchar2(32),
    vesselType   varchar2(32),
    constraint VesselType_PK
        primary key (vesselTypeId)
);

create table Generator
(
    generatorId              varchar2(32),
    nEnergyGen               integer,
    generatorPow             number,
    refrigerationTemperature number,
    constraint GENERATOR_PK
        primary key (generatorId)
);

create table Vehicle
(
    vehicleId varchar2(32),
    constraint Vehicle_PK
        primary key (vehicleId)

);


create table Ship
(
    mmsi       char(9),
    vehicleId  varchar2(32)
        constraint ShipTRANSPORTID_FK
            references Vehicle (vehicleId),
    vesselType varchar2(32),
    imo        char(10),
    callSign   varchar2(32),
    name       varchar2(32),
    length     number,
    width      number,
    capacity   number,
    draft      number,
    constraint SHIP_PK
        primary key (mmsi, vehicleId),
    constraint ShipVESSELTYPE_FK
        foreign key (vesselType)
            references VesselType (vesselTypeId)
);



create table ShipGenerator
(
    MMSI        CHAR(9)      not null,
    vehicleId   VARCHAR2(32) not null,
    generatorId VARCHAR2(32) not null
        constraint "SHIPGENERATOR_Generator__FK"
            references GENERATOR,
    constraint SHIPGENERATOR_PK
        primary key (MMSI, vehicleId, generatorId),
    constraint "SHIPGENERATOR_ShipFK"
        foreign key (MMSI, vehicleId) references SHIP
)
/



create table Transceiver
(
    Transceiverid varchar2(32),
    transceiver   varchar2(32),
    constraint TRANSCEIVER_PK
        primary key (Transceiverid)
);


create table PositionalMessage
(
    baseDateTime  date,
    mmsi          char(9),
    vehicleId     varchar2(32),
    longitude     number,
    latitude      number,
    SOG           number,
    COG           number,
    heading       number,
    Transceiverid varchar2(32),
    constraint PositionalMessage_PK
        primary key (baseDateTime, mmsi, vehicleId),
    constraint PositionalMessageShip_FK
        foreign key (mmsi, vehicleId) references Ship,
    constraint PositionalMessageTRANSCEIVERID_FK
        foreign key (Transceiverid) references Transceiver (Transceiverid)
);



create table Trip
(
    idTrip    varchar2(32),
    vehicleId varchar2(32),
    startDate date,
    endDate   date,
    constraint TRIP_PK
        primary key (idTrip, vehicleId),
    constraint TripVehicleId_FK
        foreign key (vehicleId)
            references Vehicle (vehicleId)
);


create table FacilityTrip
(
    facilityId   varchar2(32),
    idTrip       varchar2(32),
    vehicleId    varchar2(32),
    deliveryDate date,
    constraint FACILITYTRIP_PK
        primary key (facilityId, idTrip, deliveryDate),
    constraint FacilityTripvehicleId_FK
        foreign key (vehicleId, idTrip)
            references Trip,
    constraint FacilityTripfacilityId_FK
        foreign key (facilityId)
            references Facility (facilityId)
);

create table CargoManifestType
(
    CargoManifestType varchar2(32),
    type              varchar2(32),
    constraint CargoManifestType_PK
    primary key (CargoManifestType)
);



create table CargoManifest
(
    cargoManifestId   varchar2(32),
    idTrip            varchar2(32),
    vehicleId         varchar2(32),
    CargoManifestType varchar2(32),
    CargoManifestDate date,

    constraint CargoManifest_PK
        primary key (cargoManifestId),
    constraint CargoManifestvehicleId_FK
        foreign key (vehicleId, idTrip)
            references Trip,
    constraint CargoManifestTypeId_FK
        foreign key (CargoManifestType)
            references CargoManifest
);


create table CargoManifestContainer
(
    cargoManifestId varchar2(32),
    containerId     varchar2(64),

    constraint CargoManifestContainer_PK
        primary key (cargoManifestId, containerId),

    constraint CargoManifestContainerCargoManifest_FK
        foreign key (cargoManifestId)
            references CargoManifest (cargoManifestId),

    constraint CargoManifestContainerContainer_FK
        foreign key (containerId)
            references Container (containerId)


);

create table TripEmployees
(
    idTrip     varchar2(32),
    vehicleId  varchar2(32),
    employeeId varchar2(64),

    constraint TripEmployees_PK
        primary key (idTrip, vehicleId, employeeId),

    constraint TripEmployeesidTrip_FK
        foreign key (idTrip, vehicleId)
            references Trip,

    constraint TripEmployeesemployeeId_FK
        foreign key (employeeId)
            references Employee

);

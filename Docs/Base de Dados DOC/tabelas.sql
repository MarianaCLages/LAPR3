CREATE TABLE Employee
(
    name   varchar2(255) NOT NULL,
    Roleid number(10)    NOT NULL,
    email  varchar2(255) NOT NULL
);
CREATE TABLE "Employee/Facility"
(
    Employeename   varchar2(255) NOT NULL,
    Facilityid     varchar2(255) NOT NULL,
    EmployeeRoleid number(10)    NOT NULL
);
CREATE TABLE "Employee/Ship"
(
    Employeename    varchar2(255) NOT NULL,
    EmployeeRoleid  number(10)    NOT NULL,
    Shipmmsi        varchar2(255) NOT NULL,
    ShipTransportid varchar2(255) NOT NULL
);
CREATE TABLE Client
(
    id               number(10) GENERATED AS IDENTITY,
    name             varchar2(255) NOT NULL,
    email            varchar2(255) NOT NULL,
    "citizen number" number(10)    NOT NULL,
    phoneNumber      varchar2(255) NOT NULL,
    address          varchar2(255) NOT NULL,
    birthdate        date          NOT NULL
);
CREATE TABLE Ship
(
    mmsi                    varchar2(255) NOT NULL UNIQUE,
    Transportid             varchar2(255) NOT NULL,
    imo                     varchar2(255) NOT NULL,
    callSign                varchar2(255) NOT NULL UNIQUE,
    name                    varchar2(255) NOT NULL,
    length                  number(19)    NOT NULL,
    width                   number(10)    NOT NULL,
    capacity                number(10)    NOT NULL,
    draft                   number(19)    NOT NULL,
    transceiver             char(1)       NOT NULL,
    nEnergyGen              number(19)    NOT NULL,
    generatorPow            number(10),
    refigerationTemperature number(19)
);
CREATE TABLE Container
(
    containerID   varchar2(255) NOT NULL,
    ISOCodeid     varchar2(255) NOT NULL,
    payload       number(19)    NOT NULL,
    tare          number(19)    NOT NULL,
    gross         number(19)    NOT NULL,
    energyConsume number(19)    NOT NULL,
    temperature   number(19)    NOT NULL
);
CREATE TABLE Truck
(
    Transporterid varchar2(255) NOT NULL,
    name          varchar2(255) NOT NULL
);
CREATE TABLE PositionalMessage
(
    "baseDate Time" timestamp     NOT NULL,
    Shipmmsi        varchar2(255) NOT NULL,
    ShipTransportid varchar2(255) NOT NULL,
    longitude       number(19)    NOT NULL,
    latitude        number(19)    NOT NULL,
    SOG             number(19)    NOT NULL,
    COG             number(19)    NOT NULL,
    heading         number(19)    NOT NULL
);
CREATE TABLE "Truck/Employee"
(
    Employeename      varchar2(255) NOT NULL,
    EmployeeRoleid    number(10)    NOT NULL,
    TruckTransportid2 varchar2(255) NOT NULL
);
CREATE TABLE ContainerPosition
(
    ContainercontainerID varchar2(255) NOT NULL,
    xPos                 number(10)    NOT NULL,
    yPos                 number(10)    NOT NULL,
    zPos                 number(10)    NOT NULL
);
CREATE TABLE ISOCode
(
    id     varchar2(255) NOT NULL,
    width  number(19)    NOT NULL,
    lenght number(19)    NOT NULL,
    height number(19)    NOT NULL
);
CREATE TABLE Role
(
    id              number(10),
    roleDescription varchar2(255)
);
CREATE TABLE Port
(
    Facilityid varchar2(255) NOT NULL
);
CREATE TABLE Warehouse
(
    Facilityid varchar2(255) NOT NULL
);
CREATE TABLE Continent
(
    id   number(10),
    name varchar2(255) NOT NULL
);
CREATE TABLE Country
(
    id          number(10) NOT NULL,
    Continentid number(10)    NOT NULL,
    name        varchar2(255) NOT NULL
);
CREATE TABLE "Container / Client"
(
    Clientid             number(10)    NOT NULL,
    ContainercontainerID varchar2(255) NOT NULL
);
CREATE TABLE Facility
(
    id        varchar2(255) NOT NULL,
    longitude number(19)    NOT NULL,
    latitude  number(19)    NOT NULL,
    name      varchar2(255) NOT NULL,
    CountryId number(10)    NOT NULL
);
CREATE TABLE Transporter
(
    id varchar2(255) NOT NULL
);
CREATE TABLE CargoManifest
(
    id                   number(10)    NOT NULL,
    Transporertid        varchar2(255) NOT NULL,
    containerGrossWeight number(19)    NOT NULL
);
CREATE TABLE "CargoManifest Container"
(
    ContainercontainerID     varchar2(255) NOT NULL,
    CargoManifestid          number(10)    NOT NULL,
    CargoManifestTransportid varchar2(255) NOT NULL
);
CREATE TABLE "Facility / Truck"
(
    baseDateTime     timestamp     NOT NULL,
    Facilityid       varchar2(255) NOT NULL,
    TruckTransportid varchar2(255) NOT NULL
);
CREATE TABLE "Facility / Ship"
(
    baseDateTime    timestamp     NOT NULL,
    Facilityid      varchar2(255) NOT NULL,
    Shipmmsi        varchar2(255) NOT NULL,
    ShipTransportid varchar2(255) NOT NULL
);


ALTER TABLE Employee
    ADD PRIMARY KEY (name, Roleid);
ALTER TABLE "Employee/Facility"
    ADD PRIMARY KEY (Employeename, Facilityid, EmployeeRoleid);
ALTER TABLE "Employee/Ship"
    ADD PRIMARY KEY (Employeename, EmployeeRoleid, Shipmmsi, ShipTransportid);
ALTER TABLE Client
    ADD PRIMARY KEY (id);
ALTER TABLE Ship
    ADD PRIMARY KEY (mmsi, Transportid);
ALTER TABLE Container
    ADD PRIMARY KEY (containerID);
ALTER TABLE Truck
    ADD PRIMARY KEY (Transporterid);
ALTER TABLE PositionalMessage
    ADD PRIMARY KEY (Shipmmsi, ShipTransportid, "baseDate Time");
ALTER TABLE "Truck/Employee"
    ADD PRIMARY KEY (Employeename, EmployeeRoleid, TruckTransportid2);
ALTER TABLE ContainerPosition
    ADD PRIMARY KEY (ContainercontainerID);
ALTER TABLE ISOCode
    ADD PRIMARY KEY (id);
ALTER TABLE Role
    ADD PRIMARY KEY (id);
ALTER TABLE Port
    ADD PRIMARY KEY (Facilityid);
ALTER TABLE Warehouse
    ADD PRIMARY KEY (Facilityid);
ALTER TABLE Continent
    ADD PRIMARY KEY (id);
ALTER TABLE Country
    ADD PRIMARY KEY (id);
ALTER TABLE "Container / Client"
    ADD PRIMARY KEY (Clientid, ContainercontainerID);
ALTER TABLE Facility
    ADD PRIMARY KEY (id);
ALTER TABLE Transporter
    ADD PRIMARY KEY (id);
ALTER TABLE CargoManifest
    ADD PRIMARY KEY (id, Transporertid);
ALTER TABLE "CargoManifest Container"
    ADD PRIMARY KEY (ContainercontainerID, CargoManifestid, CargoManifestTransportid);
ALTER TABLE "Facility / Truck"
    ADD PRIMARY KEY (baseDateTime, Facilityid, TruckTransportid);
ALTER TABLE "Facility / Ship"
    ADD PRIMARY KEY (baseDateTime, Facilityid, Shipmmsi, ShipTransportid);

/*Employee Foreign Keys*/
ALTER TABLE Employee
    ADD FOREIGN KEY (ROLEID) REFERENCES Role (ID);
/*Employee Facility Foreign Keys*/
ALTER TABLE "Employee/Facility"
    ADD FOREIGN KEY (EMPLOYEENAME, EMPLOYEEROLEID) REFERENCES Employee (name, ROLEID);
ALTER TABLE "Employee/Facility"
    ADD FOREIGN KEY (FACILITYID) REFERENCES Facility (id);

/*Country Foreign Keys*/
ALTER TABLE Country
    ADD FOREIGN KEY (CONTINENTID) REFERENCES Continent (id);
/*Facility Foreign Keys */
ALTER TABLE Facility
    ADD FOREIGN KEY (CountryId) REFERENCES Country (id);
/*Warehouse Foreign Keys*/
ALTER TABLE WAREHOUSE
    ADD FOREIGN KEY (FACILITYID) REFERENCES Facility (id);
/*Port Foreign Keys*/
ALTER TABLE Port
    ADD FOREIGN KEY (FACILITYID) REFERENCES Facility (id);
/*FacilityShip Foreign Keys*/
ALTER TABLE "Facility / Ship"
    ADD FOREIGN KEY (Facilityid) REFERENCES Facility (id);
ALTER TABLE "Facility / Ship"
    ADD FOREIGN KEY (SHIPMMSI, SHIPTRANSPORTID) REFERENCES Ship (mmsi, TRANSPORTID);

/*FacilityTruck Foreign Keys*/
ALTER TABLE "Facility / Truck"
    ADD FOREIGN KEY (Facilityid) REFERENCES Facility (id);
ALTER TABLE "Facility / Truck"
    ADD FOREIGN KEY (TRUCKTRANSPORTID) REFERENCES Truck (TRANSPORTERID);

/*Truck Foreign Keys*/
ALTER TABLE Truck
    ADD FOREIGN KEY (TRANSPORTERID) REFERENCES Transporter (id);

/*TruckEmployee Foreign Keys*/
ALTER TABLE "Truck/Employee"
    ADD FOREIGN KEY (Employeename, EMPLOYEEROLEID) REFERENCES Employee (NAME, ROLEID);
ALTER TABLE "Truck/Employee"
    ADD FOREIGN KEY (TRUCKTRANSPORTID2) REFERENCES Truck (TRANSPORTERID);

/*CargoManifest Foreign Keys*/
ALTER TABLE CargoManifest
    ADD FOREIGN KEY (Transporertid) REFERENCES Transporter (id);

/*Container Foreign Keys*/
ALTER TABLE Container
    ADD FOREIGN KEY (ISOCODEID) REFERENCES ISOCode (id);

/*Container Position Foreign Keys*/
ALTER TABLE ContainerPosition
    ADD FOREIGN KEY (ContainercontainerID) REFERENCES Container (containerID);

/*Container Client Foreign Keys*/
ALTER TABLE "Container / Client"
    ADD FOREIGN KEY (Clientid) REFERENCES Client (id);
ALTER TABLE "Container / Client"
    ADD FOREIGN KEY (ContainercontainerID) REFERENCES Container (containerID);

/*PositionalMessage Foreign Keys*/
ALTER TABLE PositionalMessage
    ADD FOREIGN KEY (SHIPMMSI, SHIPTRANSPORTID) REFERENCES Ship (MMSI, TRANSPORTID);

/*CargoManifest Container*/
ALTER TABLE "CargoManifest Container"
    ADD FOREIGN KEY (ContainercontainerID) REFERENCES Container (containerID);
ALTER TABLE "CargoManifest Container"
    ADD FOREIGN KEY (CARGOMANIFESTID, CARGOMANIFESTTRANSPORTID) REFERENCES CargoManifest (id, TRANSPORERTID);

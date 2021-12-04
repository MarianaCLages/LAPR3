CREATE TABLE Employee (
  name   varchar2(255) NOT NULL, 
  Roleid number(10) NOT NULL, 
  email  varchar2(255) NOT NULL);
CREATE TABLE "Employee/Facility" (
  Employeename   varchar2(255) NOT NULL, 
  Facilityid     varchar2(255) NOT NULL, 
  EmployeeRoleid number(10) NOT NULL);
CREATE TABLE "Employee/Ship" (
  Employeename    varchar2(255) NOT NULL, 
  EmployeeRoleid  number(10) NOT NULL, 
  Shipmmsi        varchar2(255) NOT NULL, 
  ShipTransportid varchar2(255) NOT NULL);
CREATE TABLE Client (
  id               number(10),
  name             varchar2(255) NOT NULL, 
  email            varchar2(255) NOT NULL, 
  "citizen number" number(10) NOT NULL, 
  phoneNumber      varchar2(255) NOT NULL, 
  address          varchar2(255) NOT NULL, 
  birthdate        date NOT NULL);
CREATE TABLE Ship (
  mmsi                    varchar2(255) NOT NULL UNIQUE, 
  Transportid             varchar2(255) NOT NULL, 
  imo                     varchar2(255) NOT NULL UNIQUE, 
  callSign                varchar2(255) NOT NULL UNIQUE, 
  name                    varchar2(255) NOT NULL, 
  length                  number(19) NOT NULL, 
  width                   number(10) NOT NULL, 
  capacity                number(10) NOT NULL, 
  draft                   number(19) NOT NULL, 
  transceiver             char(1) NOT NULL, 
  nEnergyGen              number(19) NOT NULL, 
  generatorPow            number(10), 
  refigerationTemperature number(19));
CREATE TABLE Container (
  containerID   varchar2(255) NOT NULL, 
  ISOCodeid     varchar2(255) NOT NULL, 
  payload       number(19) NOT NULL, 
  tare          number(19) NOT NULL, 
  gross         number(19) NOT NULL, 
  energyConsume number(19) NOT NULL, 
  temperature   number(19) NOT NULL);
CREATE TABLE Truck (
  Transporterid varchar2(255) NOT NULL, 
  name          varchar2(255) NOT NULL);
CREATE TABLE PositionalMessage (
  baseDateTime    date NOT NULL, 
  Shipmmsi        varchar2(255) NOT NULL, 
  ShipTransportid varchar2(255) NOT NULL, 
  longitude       number(19) NOT NULL, 
  latitude        number(19) NOT NULL, 
  SOG             number(19) NOT NULL, 
  COG             number(19) NOT NULL, 
  heading         number(19) NOT NULL);
CREATE TABLE "Truck/Employee" (
  Employeename      varchar2(255) NOT NULL, 
  EmployeeRoleid    number(10) NOT NULL, 
  TruckTransportid2 varchar2(255) NOT NULL);
CREATE TABLE ContainerPosition (
  ContainercontainerID varchar2(255) NOT NULL, 
  xPos                 number(10) NOT NULL, 
  yPos                 number(10) NOT NULL, 
  zPos                 number(10) NOT NULL);
CREATE TABLE ISOCode (
  id     varchar2(255) NOT NULL, 
  width  number(19) NOT NULL, 
  lenght number(19) NOT NULL, 
  height number(19) NOT NULL);
CREATE TABLE Role (
  id              number(10),
  roleDescription varchar2(255) NOT NULL);
CREATE TABLE Port (
  Facilityid varchar2(255) NOT NULL);
CREATE TABLE Warehouse (
  Facilityid varchar2(255) NOT NULL);
CREATE TABLE Continent (
  id   number(10),
  name varchar2(255) NOT NULL);
CREATE TABLE Country (
  id          number(10),
  Continentid number(10) NOT NULL, 
  name        varchar2(255) NOT NULL);
CREATE TABLE "Container / Client" (
  Clientid             number(10) NOT NULL, 
  ContainercontainerID varchar2(255) NOT NULL);
CREATE TABLE Facility (
  id        varchar2(255) NOT NULL, 
  longitude number(19) NOT NULL, 
  latitude  number(19) NOT NULL, 
  name      varchar2(255) NOT NULL, 
  Countryid number(10) NOT NULL);
CREATE TABLE Transporter (
  id varchar2(255) NOT NULL);
CREATE TABLE CargoManifest (
  id                   number(10) NOT NULL, 
  Transporertid        varchar2(255) NOT NULL, 
  containerGrossWeight number(19) NOT NULL);
CREATE TABLE "CargoManifest Container" (
  ContainercontainerID     varchar2(255) NOT NULL, 
  CargoManifestid          number(10) NOT NULL, 
  CargoManifestTransportid varchar2(255) NOT NULL);
CREATE TABLE "Facility / Truck" (
  baseDateTime     date NOT NULL, 
  Facilityid       varchar2(255) NOT NULL, 
  TruckTransportid varchar2(255) NOT NULL);
CREATE TABLE "Facility / Ship" (
  baseDateTime    date NOT NULL, 
  Facilityid      varchar2(255) NOT NULL, 
  Shipmmsi        varchar2(255) NOT NULL, 
  ShipTransportid varchar2(255) NOT NULL);

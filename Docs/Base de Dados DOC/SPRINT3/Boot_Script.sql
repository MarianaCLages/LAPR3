
--Continent
INSERT INTO CONTINENT(NAME) values ('Europe');
INSERT INTO CONTINENT(NAME) values ('Asia');
INSERT INTO CONTINENT(NAME) values ('Africa');

--Country
INSERT INTO COUNTRY(ALPHA2CODE, ALPHA3CODE, CONTINENT, NAME, CAPITAL, POPULATION) values ('GR','GRC','Europe','Greece','Athens',10.36);
INSERT INTO COUNTRY(ALPHA2CODE, ALPHA3CODE, CONTINENT, NAME, CAPITAL, POPULATION) values ('PT','PRT','Europe','Greece','Athens',10.36);

--City
INSERT INTO CITY(cityid, countryalpha2code, countryalpha3code, name, latitude, longitude) values (1,'GR','GRC','Athens',38,24 );
INSERT INTO CITY(cityid, countryalpha2code, countryalpha3code, name, latitude, longitude) values (2,'PT','PRT','Lisbon',39,-9);

--Address
INSERT INTO ADDRESS(idaddress, cityid, street, postalcode) values (1,1,'Rua1','5000-001');
INSERT INTO ADDRESS(idaddress, cityid, street, postalcode) values (2,2,'Rua2','5000-002');

--AppUser
INSERT INTO APPUSER(id, username, password) values (101,'MARIA JOHNSON',1);
INSERT INTO APPUSER(id, username, password) values (102,'TONY STARK',2);

--Client
INSERT INTO CLIENT(id, userid, addressid, name, email, ccnumber, phonenumber, birthdate) values ('1','101','1','Elon Musk','tesla@spaceX.com','1232345642',111,TO_DATE('1981-12-31','YYYY-MM-DD'));
INSERT INTO CLIENT(id, userid, addressid, name, email, ccnumber, phonenumber, birthdate) values ('2','102','1','Elon Musk2','tesla2@spaceX.com','1232343642',111,TO_DATE('1981-12-21','YYYY-MM-DD'));
--Role
INSERT INTO ROLE(id, roledescription) values ('FM','Someone who controls the fleet');
INSERT INTO ROLE(id, roledescription) values ('TM','Someone who controls the shipping/truck position');
INSERT INTO ROLE(id, roledescription) values ('WS','Someone who loads and unloads cargo from warehouses');

--Employee
INSERT INTO EMPLOYEE(id, roleid, userid, email, name) values ('1','FM','101','tesla@spaceX.com','Elon Musk');
INSERT INTO EMPLOYEE(id, roleid, userid, email, name) values ('2','TM','102','aaaa@email.com','Kiko Lopes');

--Facility
INSERT INTO FACILITY(FACILITYID, ALPHA2CODE, ALPHA3CODE, LONGITUDE, LATITUDE, NAME, CAPACITY) values (1,'GR','GRC',38,24,'Facility1',1000);
INSERT INTO FACILITY(FACILITYID, ALPHA2CODE, ALPHA3CODE, LONGITUDE, LATITUDE, NAME, CAPACITY) values (2,'PT','PRT',39,-9,'Facility2',500);

--EmployeeFacility
INSERT INTO EMPLOYEEFACILITY(facilityid, employeeid) VALUES ('1','1');
INSERT INTO EMPLOYEEFACILITY(facilityid, employeeid) VALUES ('2','2');

--MaterialType
INSERT INTO MATERIALTYPE(id, type) values (1,'Stanless Steel');
INSERT INTO MATERIALTYPE(id, type) values (2,'Aluminum');

--Material
INSERT INTO MATERIAL(id, materialtypeid, proportionalityconstant, temperature) values (1,1,15,7);
INSERT INTO MATERIAL(id, materialtypeid, proportionalityconstant, temperature) values (2,2,237,7);

--ContainerMaterial
INSERT INTO CONTAINERMATERIAL(REFRIGERATEDCONTAINERID, MATERIALID, WALLTYPE) values (1,1,'Outer Wall');
INSERT INTO CONTAINERMATERIAL(REFRIGERATEDCONTAINERID, MATERIALID, WALLTYPE) values (2,2,'Inner Wall');
INSERT INTO CONTAINERMATERIAL(REFRIGERATEDCONTAINERID, MATERIALID, WALLTYPE) values (3,1,'Intermediate Material');

--ISOCODE
INSERT INTO ISOCODE(ID, WIDTH, LENGTH, HEIGHT) values (1,10,10,10);
INSERT INTO ISOCODE(ID, WIDTH, LENGTH, HEIGHT) values (2,20,20,20);
INSERT INTO ISOCODE(ID, WIDTH, LENGTH, HEIGHT) values (3,30,30,30);
INSERT INTO ISOCODE(ID, WIDTH, LENGTH, HEIGHT) values (4,35,20,10);
--Container
INSERT INTO CONTAINER(CONTAINERID, ISOCODE, PAYLOAD, TARE, GROSS) values (1,1,1,1,1);
INSERT INTO CONTAINER(CONTAINERID, ISOCODE, PAYLOAD, TARE, GROSS) values (2,2,20,20,20);
INSERT INTO CONTAINER(CONTAINERID, ISOCODE, PAYLOAD, TARE, GROSS) values (3,3,30,30,30);
INSERT INTO CONTAINER(CONTAINERID, ISOCODE, PAYLOAD, TARE, GROSS) values (405,4,20,35,35);

--Vehicle
INSERT INTO VEHICLE(VEHICLEID) values (1);
INSERT INTO VEHICLE(VEHICLEID) values (2);
INSERT INTO VEHICLE(VEHICLEID) values (3);
INSERT INTO VEHICLE(VEHICLEID) values (4);
INSERT INTO VEHICLE(VEHICLEID) values (5);

--VesselType
INSERT INTO VESSELTYPE(vesseltypeid, vesseltype) values (71,71);
INSERT INTO VESSELTYPE(vesseltypeid, vesseltype) values (60,60);
INSERT INTO VESSELTYPE(vesseltypeid, vesseltype) values (79,79);
INSERT INTO VESSELTYPE(vesseltypeid, vesseltype) values (20,20);

--SHIP
INSERT INTO SHIP(mmsi, vehicleid, vesseltype, imo, callsign, name, length, width, capacity, draft)
values (255806169,1,71,'IMO9465095','CQAG7','Name',50,50,50,50);
INSERT INTO SHIP(mmsi, vehicleid, vesseltype, imo, callsign, name, length, width, capacity, draft)
values (255807169,2,71,'IMO9365095','CQDG7','N4me',50,50,50,50);
INSERT INTO SHIP(mmsi, vehicleid, vesseltype, imo, callsign, name, length, width, capacity, draft)
values (255809169,3,71,'IMO9395095','CQLG7','N0me',50,50,50,50);

--TRIP
INSERT INTO TRIP(idtrip, vehicleid, startdate, enddate) VALUES (1,1,TO_DATE('2021-12-01','YYYY-MM-DD'),TO_DATE('2021-12-30','YYYY-MM-DD'));
INSERT INTO TRIP(idtrip, vehicleid, startdate, enddate) VALUES (2,2,TO_DATE('2021-11-10','YYYY-MM-DD'),TO_DATE('2021-12-10','YYYY-MM-DD'));
INSERT INTO TRIP(idtrip, vehicleid, startdate, enddate) VALUES (3,3,TO_DATE('2021-10-01','YYYY-MM-DD'),TO_DATE('2021-12-12','YYYY-MM-DD'));

--TRIPEMPLOYEE
INSERT INTO TRIPEMPLOYEE(IDTRIP, VEHICLEID, EMPLOYEEID) VALUES (1,1,1);
INSERT INTO TRIPEMPLOYEE(IDTRIP, VEHICLEID, EMPLOYEEID) VALUES (2,2,2);

--Port
INSERT INTO PORT(ID, FACILITYID, DOCKINGAREA) values (1,1,300);
INSERT INTO PORT(ID, FACILITYID, DOCKINGAREA) values (2,2,300);

--Warehouse
INSERT INTO WAREHOUSE(id, facilityid, portid) values (1,1,1);
INSERT INTO WAREHOUSE(id, facilityid, portid) values (2,2,2);

--CargoManifestType
INSERT INTO CARGOMANIFESTTYPE(cargomanifesttype, type) VALUES (1,'Load');
INSERT INTO CARGOMANIFESTTYPE(cargomanifesttype, type) VALUES (2,'OffLoad');

--CargoManifest
INSERT INTO CARGOMANIFEST(cargomanifestid, idtrip, vehicleid, facilityid, cargomanifesttype, cargomanifestdate) values (1,1,1,1,1,TO_DATE('2021-12-12','YYYY-MM-DD'));
INSERT INTO CARGOMANIFEST(cargomanifestid, idtrip, vehicleid, facilityid, cargomanifesttype, cargomanifestdate) values (2,2,2,2,2,TO_DATE('2021-10-12','YYYY-MM-DD'));

--CargoManifestContainer
INSERT INTO CARGOMANIFESTCONTAINER(cargomanifestid, containerid, xpos, ypos, zpos) values (1,1,1,1,1);
INSERT INTO CARGOMANIFESTCONTAINER(cargomanifestid, containerid, xpos, ypos, zpos) values (2,2,1,1,1);
INSERT INTO CARGOMANIFESTCONTAINER(cargomanifestid, containerid, xpos, ypos, zpos) values (2,3,2,1,1);

--Truck
INSERT INTO TRUCK(VEHICLEID, NAME) values (4,'Name');
INSERT INTO TRUCK(VEHICLEID, NAME) values (5,'N4me');

--Transceiver
INSERT INTO TRANSCEIVER(TRANSCEIVERID, TRANSCEIVER, MMSI, CLASS) values ('1','A',255809169,'A');
INSERT INTO TRANSCEIVER(TRANSCEIVERID, TRANSCEIVER, MMSI, CLASS) values ('2','B',255807169,'B');

--SeaDistance
INSERT INTO SEADISTANCE(FIRSTPORTID, SECONDPORTID, SEADISTANCE) values (1,2,1000);

--PositionalMessages
Insert Into POSITIONALMESSAGE(basedatetime, mmsi, transceiverid, longitude, latitude, sog, cog, heading)
values (TO_DATE('2021-12-01','YYYY-MM-DD'),255809169,1,20,20,10,10,10);
Insert Into POSITIONALMESSAGE(basedatetime, mmsi, transceiverid, longitude, latitude, sog, cog, heading)
values (TO_DATE('2021-12-10','YYYY-MM-DD'),255809169,1,30,30,20,20,20);
Insert Into POSITIONALMESSAGE(basedatetime, mmsi, transceiverid, longitude, latitude, sog, cog, heading)
values (TO_DATE('2021-11-10','YYYY-MM-DD'),255809169,1,30,35,20,20,20);
Insert Into POSITIONALMESSAGE(basedatetime, mmsi, transceiverid, longitude, latitude, sog, cog, heading)
values (TO_DATE('2021-11-01','YYYY-MM-DD'),255809169,1,15,25,25,20,20);
Insert Into POSITIONALMESSAGE(basedatetime, mmsi, transceiverid, longitude, latitude, sog, cog, heading)
values (TO_DATE('2021-11-02','YYYY-MM-DD'),255807169,2,15,25,25,20,20);
Insert Into POSITIONALMESSAGE(basedatetime, mmsi, transceiverid, longitude, latitude, sog, cog, heading)
values (TO_DATE('2021-11-05','YYYY-MM-DD'),255807169,2,17,28,25,20,20);

--ShipEmployee
INSERT INTO SHIPEMPLOYEE(mmsi, employeeid) values (255809169,1);
INSERT INTO SHIPEMPLOYEE(mmsi, employeeid) values (255807169,2);

--ContainerCliente
INSERT INTO  CONTAINERCLIENT(clientid, containerid, cargomanifestid) values ('1','1','1');
INSERT INTO  CONTAINERCLIENT(clientid, containerid, cargomanifestid) values ('2','2','2');

--PortWarehouse
Insert INTO PORTWAREHOUSE(portid, warehouseid)
VALUES (1, 2);
Insert INTO PORTWAREHOUSE(portid, warehouseid)
VALUES (2, 1);

--RefrigeratorContainer
INSERT INTO REFRIGERATORCONTAINER(containerid, energyconsume, temperature)
VALUES (1, 500, 2);
INSERT INTO REFRIGERATORCONTAINER(containerid, energyconsume, temperature)
VALUES (3, 250, -10);

--Generator
INSERT INTO GENERATOR(GENERATORID, MMSI, GENERATORPOW)
VALUES (1, 255809169, 6);
INSERT INTO GENERATOR(GENERATORID, MMSI, GENERATORPOW)
VALUES (2, 255809169, 5);

--Border
INSERT INTO BORDER(FIRSTCOUNTRYALPHA2CODE, FIRSTCOUNTRYALPHA3CODE, SECONDCOUNTRYALPHA2CODE, SECONDCOUNTRYALPHA3CODE)
VALUES ('GR', 'GRC', 'PT', 'PRT');
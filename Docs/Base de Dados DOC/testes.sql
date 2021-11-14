INSERT INTO Role (ROLEDESCRIPTION)
VALUES ('Traffic Manager');
commit;
--expected: fail, roleID cannot be null

INSERT INTO Role (ID, ROLEDESCRIPTION)
VALUES (14, null);
commit;
--expected: pass, role description can be null

INSERT INTO EMPLOYEE(name, roleid, email)
VALUES ('José', 1, 'jose@email.com');
commit;
--expected: fail, roleid not found

INSERT INTO Role (ID, ROLEDESCRIPTION)
VALUES (14, null);
commit;
INSERT INTO EMPLOYEE(name, ROLEID, EMAIL)
VALUES ('José', 14, 'jose@email.com');
commit;
--expect: pass, finds the roldeId

INSERT INTO Role (ID, ROLEDESCRIPTION)
VALUES (14, null);
commit;
INSERT INTO EMPLOYEE(name, roleid, email)
VALUES ('José', 'Traffic Manager', 'jose@email.com');
commit;
--expected: fail, roleId is be a number
INSERT INTO Role (ID, ROLEDESCRIPTION)
VALUES (14, null);
commit;
INSERT INTO EMPLOYEE(name, roleid, email)
VALUES (null, 14, 'jose@email.com');
commit;
--expected: fail, name cannot be null

INSERT INTO EMPLOYEE(name, roleid, email)
VALUES ('José', null, 'jose@email.com');
commit;
--expected: fail, roleId cannot be null

INSERT INTO EMPLOYEE(name, roleid, email)
VALUES ('José', 12, null);
commit;
--expected: fail, email cannot be null

INSERT INTO "Employee/Facility"(employeename, facilityid, employeeroleid)
VALUES ('José', 'facility', 123);
commit;
--expected: fail, facility, employeerole id and employeename keys not found


INSERT INTO "Employee/Facility"(employeename, facilityid, employeeroleid)
VALUES ('José', 1, 1);
commit;
--expected: fail, keys not found

INSERT INTO "Employee/Facility"(employeename, facilityid, employeeroleid)
VALUES (null, 12, 1);
commit;
--expected: fail employeename cannot be null

INSERT INTO "Employee/Facility"(employeename, facilityid, employeeroleid)
VALUES ('José', null, 1);
commit;
--expected: fail facilityId cannot be null

INSERT INTO "Employee/Facility"(employeename, facilityid, employeeroleid)
VALUES ('José', 12, null);
commit;
--expected: fail facilityId cannot be null

INSERT INTO "Truck/Employee" (employeename, employeeroleid, trucktransportid2)
VALUES ('José', 1, '2');
commit;

--expected: fail keys not found

INSERT INTO "Truck/Employee" (employeename, employeeroleid, trucktransportid2)
VALUES (null, 1, '2');
commit;
--fail employeename

INSERT INTO "Truck/Employee" (employeename, employeeroleid, trucktransportid2)
VALUES ('José', 1, null, '11111');
commit;
--fail trucktransportid2 cannot be null

INSERT INTO TRANSPORTER (ID)
VALUES ('Transporter');
commit;
--pass

INSERT INTO TRANSPORTER(ID)
VALUES (null);
commit;
--fail primary key cannot be null

INSERT INTO SHIP(mmsi, transportid, imo, callsign, name, length, width, capacity, draft, transceiver, nenergygen,
                 generatorpow, refigerationtemperature)
VALUES (null, 1, '111', 'aaa', 'aaa', 11, 11, 11, 11, 'a', 11, 11, 11);
commit;
--fail primary key cannot be null

INSERT into TRANSPORTER (ID)
values ('1');
commit;
INSERT INTO SHIP(mmsi, transportid, imo, callsign, name, length, width, capacity, draft, transceiver, nenergygen,
                 generatorpow, refigerationtemperature)
VALUES ('1111', '1', '111', 'aaa', 'aaa', 20, 11, 11, 11, 'a', 11, 11, 11);
commit;
--pass;

INSERT INTO SHIP(mmsi, transportid, imo, callsign, name, length, width, capacity, draft, transceiver, nenergygen,
                 generatorpow, refigerationtemperature)
VALUES ('1111', 1, '111', 'aaa', 'aaa', 11, 11, 11, 11, 'a', 11, 11, 11);
commit;

INSERT INTO SHIP(mmsi, transportid, imo, callsign, name, length, width, capacity, draft, transceiver, nenergygen,
                 generatorpow, refigerationtemperature)
VALUES ('1111', 6, '111', 'aaa', 'aaaa', 11, 11, 11, 11, 'a', 11, 11, 11);
commit;
--fail, IMO needs to be unique;

INSERT INTO SHIP(mmsi, transportid, imo, callsign, name, length, width, capacity, draft, transceiver, nenergygen,
                 generatorpow, refigerationtemperature)
VALUES ('1111', 1, '111', 'aaa', 'aaa', 11, 11, 11, 11, 'a', 11, 11, 11);
commit;

INSERT INTO SHIP(mmsi, transportid, imo, callsign, name, length, width, capacity, draft, transceiver, nenergygen,
                 generatorpow, refigerationtemperature)
VALUES ('1111', 6, '111', 'aasa', 'aaa', 11, 11, 11, 11, 'a', 11, 11, 11);
commit;
--fail, CallSign needs to be unique;

INSERT INTO CONTINENT(id, name)
VALUES (1, 'Europe');
commit;
--pass

INSERT INTO CONTINENT(id, name)
VALUES (null, 'Asia');
commit;
--fail, id cannot be null

INSERT INTO CONTINENT(id, name)
VALUES (2, null);
commit;
--fail, name cannot be null

INSERT INTO COUNTRY(ID, CONTINENTID, NAME)
values (1, 1, 'Portugal');
commit;
--pass

INSERT INTO COUNTRY(ID, CONTINENTID, NAME)
values (2, 2, 'Portugal');
commit;
--fail, ContinentID is FK and does not exists in Continent Table

INSERT INTO COUNTRY(ID, CONTINENTID, NAME)
values (null, 1, 'Portugal');
commit;
--fail, ID cannot be null

INSERT INTO FACILITY (ID, LONGITUDE, LATITUDE, NAME, COUNTRYID)
VALUES (1, 15, 15, 'Porto de Leixões', 1);
--pass

INSERT INTO FACILITY (ID, LONGITUDE, LATITUDE, NAME, COUNTRYID)
VALUES (2, 15, 15, 'Porto de Leixões', 15);
--fail, CountryID is FK and does not exists in Country Table

INSERT INTO FACILITY (ID, LONGITUDE, LATITUDE, NAME, COUNTRYID)
VALUES (null, 15, 15, 'Porto de Leixões', 1);
--fail, PK is null

INSERT INTO CARGOMANIFEST(id, transporertid, containergrossweight)
VALUES (1,'Transporter',15);
--pass

INSERT INTO CARGOMANIFEST(id, transporertid, containergrossweight)
VALUES (1,'Tansporter',15);
--fail, transporertid is FK and does not exists in transporer Table


INSERT INTO CARGOMANIFEST(id, transporertid, containergrossweight)
VALUES (null,'Transporter',15);
--fail, PK is null

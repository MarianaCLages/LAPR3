

/*
 Client - one client
 */
 INSERT INTO CLIENT (id,name,EMAIL,"citizen number",PHONENUMBER,ADDRESS,BIRTHDATE)
 values (1,'mariana','mariana@gmail.com',5344321234,'916395777','rua da felicidade',SYSDATE);


/*
 Roles - one ship captain and one truck driver.
 */
INSERT INTO Role (id,ROLEDESCRIPTION)
VALUES (1,'ship captain');
commit;

INSERT INTO Role (id,ROLEDESCRIPTION)
VALUES (2,'truck driver');
commit;

/*
 Employees - two truck drivers and one ship captain
 */

INSERT INTO Employee (name, ROLEID, EMAIL)
VALUES ('joao',2,'joaoratao@gmail.com');
commit;

INSERT INTO Employee (name, ROLEID, EMAIL)
VALUES ('maria',2,'mariaalbertina@gmail.com');
commit;

INSERT INTO Employee (name, ROLEID, EMAIL)
VALUES ('eleanor',1,'eli@gmail.com');
commit;

/*
 Facility - one warehouse and one port, on two different continents and two different countries
 */

INSERT INTO Continent (id, name)
VALUES (1, 'Europe');
commit;

INSERT INTO Continent (id, name)
VALUES (2, 'Asia');
commit;

Insert into Country (id,CONTINENTID,NAME)
values (1,1,'Portugal');
commit;

Insert into Country (id,CONTINENTID,NAME)
values (2,2,'China');
commit;

INSERT INTO FACILITY(id, longitude, latitude, name, countryid) VALUES
('warehouse1',50,50,'warehouse dos deuses',1);

INSERT INTO FACILITY(id, longitude, latitude, name, countryid) VALUES
('port1',53,20,'port incrivel',2);

INSERT INTO WAREHOUSE(FACILITYID) values ('warehouse1');
INSERT INTO PORT(FACILITYID) values ('port1');





/*
 Create three transporters, one ship and two trucks.
 The ship has two positional messages.

 */

INSERT INTO TRANSPORTER (id)
VALUES ('transporter1');
commit;

INSERT INTO TRANSPORTER (id)
VALUES ('transporter2');
commit;

INSERT INTO TRANSPORTER (id)
VALUES ('transporter3');
commit;

INSERT INTO TRUCK (TRANSPORTERID, NAME)
values ('transporter2','trucky');
commit;

INSERT INTO TRUCK (TRANSPORTERID, NAME)
values ('transporter3','trucky');
commit;

INSERT INTO SHIP(mmsi, transportid, imo, callsign, name, length, width, capacity, draft, transceiver, nenergygen, generatorpow, refigerationtemperature)
values ('123456789','transporter1','IMO1234567','call sign','caravela',100,30,300,50,'a',50,60,10);
commit;

INSERT INTO POSITIONALMESSAGE(BASEDATETIME, SHIPMMSI, SHIPTRANSPORTID, LONGITUDE, LATITUDE, SOG, COG, HEADING)
VALUES (SYSDATE,'123456789','transporter1',50,50,50,50,50);
INSERT INTO POSITIONALMESSAGE(BASEDATETIME,SHIPMMSI, SHIPTRANSPORTID, LONGITUDE, LATITUDE, SOG, COG, HEADING)
VALUES (TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),'123456789','transporter1',50,50,50,50,50);
commit;


/*
 Container - 2 containers, wtih the same iso code. associate one client with container1
 */
 INSERT INTO ISOCODE(id, width, lenght, height)
 VALUES (1,50,50,40);

 INSERT INTO CONTAINER(containerid, isocodeid, payload, tare, gross, energyconsume, temperature)
 VALUES('container1',1,500,400,200,50,0);

INSERT INTO CONTAINER(containerid, isocodeid, payload, tare, gross, energyconsume, temperature)
VALUES('container2',1,500,400,200,50,0);

INSERT INTO CONTAINERPOSITION(CONTAINERCONTAINERID, XPOS, YPOS, ZPOS)
VALUES('container1',2,3,0);

INSERT INTO CONTAINERPOSITION(CONTAINERCONTAINERID, XPOS, YPOS, ZPOS)
VALUES('container2',2,3,0);

INSERT INTO "Container / Client"(CLIENTID, CONTAINERCONTAINERID)
VALUES (1,'container1');

/*
 Cargo Manifest- create one manifest for the ship and associate with container 1
 */

 INSERT INTO CARGOMANIFEST(ID, TRANSPORERTID, CONTAINERGROSSWEIGHT)
 VALUES (1,'transporter1',5000);

Insert into "CargoManifest Container"(CONTAINERCONTAINERID, CARGOMANIFESTID, CARGOMANIFESTTRANSPORTID)
VALUES('container1',1,'transporter1');


/*
 one employee/ship
 */

 INSERT INTO "Employee/Ship"(employeename, employeeroleid, shipmmsi, shiptransportid) VALUES
     ('joao',1,'123456789','transporter1');

insert into "Truck/Employee"(EMPLOYEENAME, EMPLOYEEROLEID, TRUCKTRANSPORTID2) VALUES
    ('maria',2,'transporter2');


/*
 one facility/truck and one facility/ship
 */

 insert into "Facility / Ship"(basedatetime, facilityid, shipmmsi, shiptransportid) VALUES
 (TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 'port1', '123456789','transporter1');

 insert into "Facility / Truck"(basedatetime, facilityid, trucktransportid) values
 (TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 'warehouse1','transporter2');

commit;


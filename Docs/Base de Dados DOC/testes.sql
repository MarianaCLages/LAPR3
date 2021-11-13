INSERT INTO Role (ROLEDESCRIPTION)
VALUES ('Traffic Manager');
commit;

--expected: pass, this column respects all the Role restrictions

INSERT INTO Role (ROLEDESCRIPTION)
VALUES (null);
commit;
--expected: pass, role description can be null

INSERT INTO EMPLOYEE(name, roleid, email) VALUES ('José', 1,'jose@email.com');
commit ;

--expected: fail, roleid not found

INSERT INTO EMPLOYEE(name,ROLEID,EMAIL) VALUES ('José',1,'jose@email.com');
--expect: pass, finds the roldeId

INSERT INTO EMPLOYEE(name, roleid, email) VALUES ('José', 'Traffic Manager','jose@email.com');
commit ;

--expected: fail, roleId cannot be a number

INSERT INTO EMPLOYEE(name, roleid, email) VALUES (null, 'Traffic Manager','jose@email.com');
commit ;

--expected: fail, name cannot be null

INSERT INTO EMPLOYEE(name, roleid, email) VALUES ('José',null, 'jose@email.com');
commit ;

--expected: fail, roleId cannot be null

INSERT INTO EMPLOYEE(name, roleid, email) VALUES ('José',12, null);
commit ;

--expected: fail, email cannot be null

INSERT INTO "Employee/Facility"(employeename, facilityid, employeeroleid) VALUES ('José','facility',123);
commit;
--expected: fail, facility, employeerole id and employeename keys not found


INSERT INTO "Employee/Facility"(employeename, facilityid, employeeroleid) VALUES ('José', 1,1);
commit ;
--expected: fail, keys not found

INSERT INTO "Employee/Facility"(employeename, facilityid, employeeroleid) VALUES (null, 12,1);
commit ;
--expected: fail employeename cannot be null

INSERT INTO "Employee/Facility"(employeename, facilityid, employeeroleid) VALUES ('José', null,1);
commit ;
--expected: fail facilityId cannot be null

INSERT INTO "Employee/Facility"(employeename, facilityid, employeeroleid) VALUES ('José', 12,null);
commit ;
--expected: fail facilityId cannot be null

INSERT INTO "Truck/Employee" (employeename, employeeroleid, trucktransportid2, truckid) VALUES ('José',1,'2','1');
commit;

--expected: fail keys not found

INSERT INTO "Truck/Employee" (employeename, employeeroleid, trucktransportid2, truckid) VALUES ('José',1,'2',null);
commit;
--expected: fail truckid cannot be null

INSERT INTO "Truck/Employee" (employeename, employeeroleid, trucktransportid2, truckid) VALUES (null,1,'2','11111');
commit;
--fail employeename

INSERT INTO "Truck/Employee" (employeename, employeeroleid, trucktransportid2, truckid) VALUES ('José',1,null,'11111');
commit;
--fail trucktransportid2 cannot be null

INSERT INTO "Truck/Employee" (employeename, employeeroleid, trucktransportid2, truckid) VALUES ('José',1,'2',null);
commit;
--fail truckId cannot be null

INSERT INTO TRANSPORTER (ID) VALUES ('Transporter');
commit ;
--pass

INSERT INTO  TRANSPORTER(ID) VALUES (null);
commit;
--fail primary key cannot be null

INSERT INTO SHIP(mmsi, transportid, imo, callsign, name, length, width, capacity, draft, transceiver, nenergygen, generatorpow, refigerationtemperature)
VALUES (null,1,'111','aaa','aaa',11,11,11,11,'a',11,11,11);
commit;
--fail primary key cannot be null

INSERT INTO SHIP(mmsi, transportid, imo, callsign, name, length, width, capacity, draft, transceiver, nenergygen, generatorpow, refigerationtemperature)
VALUES ('1111',1,'111','aaa','aaa',11,11,11,11,'a',11,11,11);
commit;
--pass;


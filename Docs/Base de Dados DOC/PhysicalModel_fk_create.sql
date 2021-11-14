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

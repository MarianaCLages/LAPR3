
--CargoManifestType
ALTER TABLE CARGOMANIFESTCONTAINER DROP COLUMN *;
insert into CARGOMANIFESTTYPE (CargoManifestType, type) values ('1', '1');
insert into CARGOMANIFESTTYPE (CargoManifestType, type) values ('2', '2');


--Trip
SELECT * from  VEHICLE;
insert into Trip (idTrip, vehicleId, startDate, endDate) values (1, 46, TO_DATE('9/1/2020','dd/mm/yyyy'),TO_DATE( '19/1/2021', 'dd/mm/yyyy'));
insert into Trip (idTrip, vehicleId, startDate, endDate) values (2, 54, TO_DATE('1/1/2020','dd/mm/yyyy'),TO_DATE( '5/1/2021', 'dd/mm/yyyy'));
insert into Trip (idTrip, vehicleId, startDate, endDate) values (3, 58, TO_DATE('24/11/2020','dd/mm/yyyy'),TO_DATE( '7/9/2021', 'dd/mm/yyyy'));
insert into Trip (idTrip, vehicleId, startDate, endDate) values (4, 74,TO_DATE('9/1/2020','dd/mm/yyyy'),TO_DATE( '25/11/2021', 'dd/mm/yyyy'));
insert into Trip (idTrip, vehicleId, startDate, endDate) values (5, 85, TO_DATE('29/10/2020','dd/mm/yyyy'),TO_DATE( '4/7/2021', 'dd/mm/yyyy'));
insert into Trip (idTrip, vehicleId, startDate, endDate) values (6, 51, TO_DATE('12/2/2020','dd/mm/yyyy'),TO_DATE( '29/1/2021', 'dd/mm/yyyy'));
insert into Trip (idTrip, vehicleId, startDate, endDate) values (7, 10, TO_DATE('9/12/2020','dd/mm/yyyy'),TO_DATE( '19/1/2021', 'dd/mm/yyyy'));
insert into Trip (idTrip, vehicleId, startDate, endDate) values (8, 73, TO_DATE('2/11/2020','dd/mm/yyyy'),TO_DATE( '4/6/2021', 'dd/mm/yyyy'));
insert into Trip (idTrip, vehicleId, startDate, endDate) values (9, 81,TO_DATE('5/1/2021','dd/mm/yyyy'),TO_DATE( '31/1/2021', 'dd/mm/yyyy'));
insert into Trip (idTrip, vehicleId, startDate, endDate) values (10, 68,TO_DATE('15/7/2021','dd/mm/yyyy'),TO_DATE( '16/9/2021', 'dd/mm/yyyy') );
insert into Trip (idTrip, vehicleId, startDate, endDate) values (11, 32, TO_DATE('10/5/2020','dd/mm/yyyy'),TO_DATE( '19/1/2021', 'dd/mm/yyyy'));
insert into Trip (idTrip, vehicleId, startDate, endDate) values (12, 26, TO_DATE('15/11/2020','dd/mm/yyyy'),TO_DATE( '10/2/2021', 'dd/mm/yyyy'));
insert into Trip (idTrip, vehicleId, startDate, endDate) values (13, 52,TO_DATE('11/6/2021','dd/mm/yyyy'),TO_DATE( '1/12/2021', 'dd/mm/yyyy') );
insert into Trip (idTrip, vehicleId, startDate, endDate) values (14, 57, TO_DATE('4/3/2020','dd/mm/yyyy'),TO_DATE( '26/8/2021', 'dd/mm/yyyy'));

--CargoManifestType
insert into CARGOMANIFESTTYPE()
--CargoManifest
insert into CARGOMANIFEST(CARGOMANIFESTID,IDTRIP,VEHICLEID,CARGOMANIFESTTYPE,CARGOMANIFESTDATE) values (1,1.46,1);


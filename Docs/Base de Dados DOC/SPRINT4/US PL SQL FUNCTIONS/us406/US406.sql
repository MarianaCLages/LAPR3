create or replace function fnOccupancyRateWithThresholdPerVoyage(vMmsi SHIP.mmsi%TYPE, beginDatePeriod date, endDatePeriod date,vThreshold number) return varchar2
    IS
    no_valid_trips exception;
    invalid_value exception ;
    vConcatInformation varchar2(32767);
    vTripID            TRIP.IDTRIP%type;
    vShipMmsi          Ship.MMSI%type;
    vAuxNumber         number;
    vDepartureFacility FACILITY.NAME%type;
    vDepartureDate     TRIP.StartDate%type;
    vEndDate           TRIP.EndDate%type;
    vEndFacility       FACILITY.Name%type;
    vTripNumber        number;

        --Cursor to have all valid trips

    CURSOR vValidTripsCursor is
        select T1.IDTRIP
        from TRIP T1
        where T1.IDTRIP in (select T2.IDTRIP from TRIP T2 where T2.ENDDATE < SYSDATE)
          AND T1.VEHICLEID = (select S.VehicleID from SHIP S where S.MMSI = vMmsi);


begin
    open vValidTripsCursor;

    if (vValidTripsCursor%notfound) THEN
        raise no_valid_trips;
    end if;

    vTripNumber := 0;

    --Loop to verify all trips one bye one (to see cargo manifests etc...)

    begin
        LOOP
            FETCH vValidTripsCursor into vTripID;
            EXIT WHEN (vValidTripsCursor%notfound);

            select S.MMSI
            into vShipMmsi
            from SHIP S
                     inner join TRIP T on S.VEHICLEID = T.VEHICLEID
            where t.IDTRIP = vTripID;

            --Call Function from US405

            vAuxNumber := FNGETAVERAGEOCCUPANCYRATEPERMANIFEST(vShipMmsi, beginDatePeriod, endDatePeriod);

            IF (vAuxNumber LIKE 'null') THEN
                raise invalid_value;
            end if;

            IF (vAuxNumber LIKE -1) THEN
                raise invalid_value;
            end if;

            IF (vAuxNumber < vThreshold) THEN

                --Get all valid information from the tables

                select t.STARTDATE, t.ENDDATE into vDepartureDate,vEndDate from TRIP t where t.IDTRIP = vTripID;

                vTripNumber := vTripNumber + 1;

                select f1.Name, f2.Name
                into vDepartureFacility,vEndFacility
                from FACILITY f1,
                     FACILITY f2
                where f1.FACILITYID in (select ft.FACILITYID
                                        from FACILITYTRIP ft
                                        where ft.DELIVERYDATE in (select c.CargoManifestDate
                                                                  from CARGOMANIFEST c
                                                                  where c.IDTRIP = vTripID
                                                                    and c.CARGOMANIFESTDATE = vDepartureDate))
                  AND f2.FACILITYID in (select ft.FACILITYID
                                        from FACILITYTRIP ft
                                        where ft.DELIVERYDATE in (select c.CargoManifestDate
                                                                  from CARGOMANIFEST c
                                                                  where c.IDTRIP = vTripID
                                                                    and c.CARGOMANIFESTDATE = vEndDate));

                vConcatInformation := concat(vConcatInformation, 'Trip number ');
                vConcatInformation := concat(vConcatInformation, vTripNumber);

                vConcatInformation := concat(vConcatInformation, ', Trip Departure date: ');
                vConcatInformation := concat(vConcatInformation, vDepartureDate);

                vConcatInformation := concat(vConcatInformation, ', Trip Departure Facility: ');
                vConcatInformation := concat(vConcatInformation, vDepartureFacility);

                vConcatInformation := concat(vConcatInformation, ' - Trip Arrival date: ');
                vConcatInformation := concat(vConcatInformation, vEndDate);

                vConcatInformation := concat(vConcatInformation, ', End Arrival facility: ');
                vConcatInformation := concat(vConcatInformation, vEndFacility);

                vConcatInformation := concat(vConcatInformation, chr(10));

                vConcatInformation := concat(vConcatInformation, ' ');

            end if;


        end loop;


    EXCEPTION
        WHEN
            invalid_value THEN
            raise_application_error(-200021,
                                    'There was an error when calculating the occupancy rate given a period! Please verify if , inside the given period, the ship has valid information! (Trips valid!!)');

    end;

    IF NVL(vConcatInformation, 'NULL') = 'NULL'
    THEN
        vConcatInformation := 'Given the default Threshold, there are no trips that have an occupancy rate below the given default threshold!!';
    end if;

    --Return a string with all information needed

    return vConcatInformation;

EXCEPTION
    WHEN
        no_valid_trips THEN
        raise_application_error(-200020,
                                'There are 0 trips that ended until now! Please verify the integrity of the data!');
        return NULL;

end;
/



--Test US with valid data!

--Test US406

--DML

insert into VEHICLE(VEHICLEID) values (200);
insert into SHIP (mmsi, vehicleid, vesseltype, imo, callsign, name, length, width, capacity, draft) values (999333222,200,71,'IMO9200991','28VH','OmegaShip',10,20,1000000,30);
insert into TRIP(idtrip, vehicleid, startdate, enddate) values (200,200,TO_DATE ('2021-12-01','YYYY-MM-DD'),TO_DATE ('2022-01-10','YYYY-MM-DD'));

insert into CARGOMANIFEST (CARGOMANIFESTID, IDTRIP, VEHICLEID, FACILITYID, CARGOMANIFESTTYPE, CARGOMANIFESTDATE) values (200,200,200,216592,2,TO_DATE ('2021-12-01','YYYY-MM-DD'));
insert into CARGOMANIFEST (CARGOMANIFESTID, IDTRIP, VEHICLEID, FACILITYID, CARGOMANIFESTTYPE, CARGOMANIFESTDATE) values (210,200,200,216593,2,TO_DATE ('2022-01-10','YYYY-MM-DD'));
insert into CARGOMANIFEST (CARGOMANIFESTID, IDTRIP, VEHICLEID, FACILITYID, CARGOMANIFESTTYPE, CARGOMANIFESTDATE) values (301,200,200,10,1,TO_DATE ('2022-01-24','YYYY-MM-DD'));
insert into CARGOMANIFEST (CARGOMANIFESTID, IDTRIP, VEHICLEID, FACILITYID, CARGOMANIFESTTYPE, CARGOMANIFESTDATE) values (302,200,200,10,1,TO_DATE ('2022-01-26','YYYY-MM-DD'));
insert into CARGOMANIFEST (CARGOMANIFESTID, IDTRIP, VEHICLEID, FACILITYID, CARGOMANIFESTTYPE, CARGOMANIFESTDATE) values (304,200,200,216592,2,TO_DATE ('2021-12-01','YYYY-MM-DD'));
insert into CARGOMANIFEST (CARGOMANIFESTID, IDTRIP, VEHICLEID, FACILITYID, CARGOMANIFESTTYPE, CARGOMANIFESTDATE) values (303,200,200,10,1,TO_DATE ('2022-01-29','YYYY-MM-DD'));

insert into CONTAINER(containerid, isocode, payload, tare, gross) values (2000001,'28VH',100000,1000000,100);
insert into CONTAINER(containerid, isocode, payload, tare, gross) values (2579,'28VH',522,383,2360);
insert into CONTAINER(containerid, isocode, payload, tare, gross) values (88881,'28VH',100,100,200);
insert into CONTAINER(containerid, isocode, payload, tare, gross) values (88882,'28VH',100,100,200);
insert into CONTAINER(containerid, isocode, payload, tare, gross) values (88883,'28VH',100,100,200);
insert into CONTAINER(containerid, isocode, payload, tare, gross) values (88884,'28VH',100,100,200);
insert into CONTAINER(containerid, isocode, payload, tare, gross) values (88885,'28VH',100,100,200);
insert into CONTAINER(containerid, isocode, payload, tare, gross) values (88886,'28VH',100,100,200);
insert into CONTAINER(containerid, isocode, payload, tare, gross) values (88871,'28VH',100,100,200);
insert into CONTAINER(containerid, isocode, payload, tare, gross) values (88872,'28VH',100,100,200);
insert into CONTAINER(containerid, isocode, payload, tare, gross) values (88873,'28VH',100,100,200);
insert into CONTAINER(containerid, isocode, payload, tare, gross) values (88887,'28VH',100,100,200);
insert into CONTAINER(containerid, isocode, payload, tare, gross) values (88888,'28VH',100,100,200);
insert into CONTAINER(containerid, isocode, payload, tare, gross) values (88889,'28VH',100,100,200);

insert into CARGOMANIFESTCONTAINER (cargomanifestid, containerid, xpos, ypos, zpos) values (200,2579,0,0,0);
insert into CARGOMANIFESTCONTAINER (cargomanifestid, containerid, xpos, ypos, zpos) values (200,2000001,1,0,0);
insert into CARGOMANIFESTCONTAINER (cargomanifestid, containerid, xpos, ypos, zpos) values (301,88881,2,0,0);
insert into CARGOMANIFESTCONTAINER (cargomanifestid, containerid, xpos, ypos, zpos) values (301,88882,3,0,0);
insert into CARGOMANIFESTCONTAINER (cargomanifestid, containerid, xpos, ypos, zpos) values (301,88883,4,0,0);
insert into CARGOMANIFESTCONTAINER (cargomanifestid, containerid, xpos, ypos, zpos) values (302,88884,5,0,0);
insert into CARGOMANIFESTCONTAINER (cargomanifestid, containerid, xpos, ypos, zpos) values (302,88885,6,0,0);
insert into CARGOMANIFESTCONTAINER (cargomanifestid, containerid, xpos, ypos, zpos) values (302,88886,7,0,0);
insert into CARGOMANIFESTCONTAINER (cargomanifestid, containerid, xpos, ypos, zpos) values (303,88887,8,0,0);
insert into CARGOMANIFESTCONTAINER (cargomanifestid, containerid, xpos, ypos, zpos) values (303,88888,9,0,0);
insert into CARGOMANIFESTCONTAINER (cargomanifestid, containerid, xpos, ypos, zpos) values (303,88889,10,0,0);
insert into CARGOMANIFESTCONTAINER (cargomanifestid, containerid, xpos, ypos, zpos) values (304,88871,0,1,0);
insert into CARGOMANIFESTCONTAINER (cargomanifestid, containerid, xpos, ypos, zpos) values (304,88872,0,2,0);
insert into CARGOMANIFESTCONTAINER (cargomanifestid, containerid, xpos, ypos, zpos) values (304,88873,0,3,0);

--Test US406

declare
    vResult varchar2(32767);
BEGIN
    vResult := fnOccupancyRateWithThresholdPerVoyage(999333222,TO_DATE ('2021-12-01','YYYY-MM-DD'),TO_DATE ('2022-01-10','YYYY-MM-DD'));
    dbms_output.put_line(vResult);
end;




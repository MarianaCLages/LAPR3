--PL SQL Function

create or replace function fnGetAverageOccupancyRatePerManifest(vShipMMSI SHIP.MMSI%TYPE, beginDatePeriod date, endDatePeriod date) return number
    is
    containers_cursor SYS_REFCURSOR;
    no_cargoManifest_found exception ;
    vCargoManifestID   CARGOMANIFEST.CARGOMANIFESTID%type;
    vShipCapacity      SHIP.CAPACITY%type;
    vNumberContainers  number;
    vContainerID       CONTAINER.CONTAINERID%type;
    vContainerGross    CONTAINER.GROSS%type;
    vSumContainerGross number;
    vNumberManifests   number;
    vAvgOccupancyRate  number;

    --Cursor that saves all manifests inside the period of time inside the specified ship

    CURSOR allManifests
        is select CARGOMANIFESTID
           from CARGOMANIFEST cm inner join TRIP T on T.IDTRIP = cm.IDTRIP and T.VEHICLEID = cm.VEHICLEID
           where cm.VEHICLEID = (select VEHICLEID from SHIP s where s.MMSI = vShipMMSI)
             AND cm.VEHICLEID = T.VEHICLEID
             AND cm.CARGOMANIFESTDATE between beginDatePeriod and endDatePeriod;


begin
    open allManifests;

    --If no Cargo Manifests were found inside that period, a exception must be raised

    IF (allManifests%notfound) THEN
        raise no_cargoManifest_found;
    end if;

    --In order to have the average occupancy rate, we first must have the total number of manifests

    select COUNT(CARGOMANIFESTID)
    into vNumberManifests
    from CARGOMANIFEST cm inner join TRIP T on T.IDTRIP = cm.IDTRIP and T.VEHICLEID = cm.VEHICLEID
    where cm.VEHICLEID = (select VEHICLEID from SHIP s where s.MMSI = vShipMMSI)
      AND cm.VEHICLEID = T.VEHICLEID
      AND cm.CARGOMANIFESTDATE between beginDatePeriod and endDatePeriod;

    --Query to select the Ship capacity of the inserted MMSI

    SELECT CAPACITY
    into vShipCapacity
    from SHIP s
    where s.MMSI = vShipMMSI;

    vAvgOccupancyRate := 0;

    LOOP
        FETCH allManifests into vCargoManifestID;
        EXIT WHEN (allManifests%notfound);

        vSumContainerGross := 0;

        --Query to select the number of containers inside the cargo manifest

        SELECT COUNT(*)
        into vNumberContainers
        from CARGOMANIFESTCONTAINER cc
        where cc.CARGOMANIFESTID = vCargoManifestID;

        --Cursor to save all containers inside the cargo manifest

        open containers_cursor for
            select CONTAINERID
            from CARGOMANIFESTCONTAINER cc
            where cc.CARGOMANIFESTID = vCargoManifestID;

        LOOP
            FETCH containers_cursor into vContainerID;
            EXIT WHEN (containers_cursor%notfound);

            --Select the gross weight of a certain container

            SELECT GROSS
            into vContainerGross
            from CONTAINER c
            where c.CONTAINERID = vContainerID;

            vSumContainerGross := vSumContainerGross + vContainerGross;

        end loop;

        --Occupancy Rate of a certain manifest inside the Ship

        vSumContainerGross := (vSumContainerGross / vShipCapacity) * 100;

        --Save this Occupancy rate to be later on used to have the average occupancy rate

        vAvgOccupancyRate := vAvgOccupancyRate + vSumContainerGross;

    end loop;
    close allManifests;

    --Average Occupancy Rate of the manifests inside the ship

    vAvgOccupancyRate := (vAvgOccupancyRate / vNumberManifests) * 100;

    return vAvgOccupancyRate;

EXCEPTION
    WHEN
        no_cargoManifest_found THEN
        raise_application_error(-200022, 'No cargo manifests were found inside that date! Please enter another one');
        return NULL;

end;
/

--Test US with valid data!

--Test US405

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

declare
    vResult number;
BEGIN
    vResult := fnGetAverageOccupancyRatePerManifest(999333222,TO_DATE ('2021-12-01','YYYY-MM-DD'),TO_DATE ('2022-01-10','YYYY-MM-DD'));
    dbms_output.put_line('The value is: '||vResult || '%');
end;

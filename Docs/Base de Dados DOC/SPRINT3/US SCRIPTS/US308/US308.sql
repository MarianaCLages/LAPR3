create or replace trigger trgNotExceedShipCapacity
    before insert
    on CARGOMANIFESTCONTAINER
    for each row
    declare
        numContainers number;
        totalCapacity number;
   begin
        dbms_output.put_line(:new.CARGOMANIFESTID);
        -- query number of containers in cargo manifests of that ship - only ships have more than capacity 1
        select COUNT(CARGOMANIFESTID) into numContainers from CARGOMANIFESTCONTAINER
        where CARGOMANIFESTID = :new.CARGOMANIFESTID;

        -- query total capacity of ship
        select CAPACITY into totalCapacity from SHIP
            where SHIP.VEHICLEID = (SELECT VEHICLEID FROM CARGOMANIFEST WHERE CARGOMANIFEST.CARGOMANIFESTID = :new.CARGOMANIFESTID);

        -- verify if if one more manifest is da
        if (numContainers+1>totalCapacity) then
            raise_application_error(-20000,'This ship already has max capacity.');
        end if;
    end;




insert into VEHICLE values ('999');
insert into SHIP values('999999991','999','71','IMO9999991','cj101','Testus Bdaddus',5,5,2,10.5);
insert into  CARGOMANIFEST (CARGOMANIFESTID, IDTRIP, VEHICLEID, FACILITYID, CARGOMANIFESTTYPE, CARGOMANIFESTDATE)
 values ('888','11','10','10','1',TO_DATE('5/12/2021', 'DD/MM/YYYY'));

INSERT INTO CARGOMANIFESTCONTAINER (CARGOMANIFESTID, CONTAINERID, XPOS, YPOS, ZPOS) VALUES
('1','360',1,1,1);
INSERT INTO CARGOMANIFESTCONTAINER (CARGOMANIFESTID, CONTAINERID, XPOS, YPOS, ZPOS) VALUES
('106','39',1,1,1);

select COUNT(CARGOMANIFESTID) from CARGOMANIFESTCONTAINER
where CARGOMANIFESTID = '106';


SELECT VEHICLEID FROM CARGOMANIFEST WHERE CARGOMANIFEST.CARGOMANIFESTID = '106';

select CAPACITY from SHIP
where SHIP.VEHICLEID = (
    SELECT VEHICLEID FROM CARGOMANIFEST WHERE CARGOMANIFEST.CARGOMANIFESTID = '106'
);

INSERT INTO CARGOMANIFESTCONTAINER (CARGOMANIFESTID, CONTAINERID, XPOS, YPOS, ZPOS) VALUES
('107','405',1,1,1);

commit;




delete from SHIP where MMSI = '999999991';
delete from CARGOMANIFESTCONTAINER where CARGOMANIFESTID = '888';
delete from VEHICLE where VEHICLEID = '999';
delete from CARGOMANIFEST where VEHICLEID = '888';



select CAPACITY from SHIP where SHIP.VEHICLEID = (
    select VEHICLEID from CARGOMANIFEST where CARGOMANIFESTID = 1
    );

select COUNT(CARGOMANIFESTID) from CARGOMANIFESTCONTAINER where CARGOMANIFESTID = 1;



select COUNT(CARGOMANIFESTID)  from CARGOMANIFESTCONTAINER
where CARGOMANIFESTID = 103;




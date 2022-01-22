Create or replace function fncCheckForCargoManifestMap(portId_ CARGOMANIFEST.FACILITYID%TYPE, inputDate DATE)
    -- inputDate was chosen as a parameter to aid testing, but could be changed to system date.
    return varchar2
    is
    retValue varchar2(32767) := '';

    portId CARGOMANIFEST.FACILITYID%TYPE := portId_;

    -- variables to store cargo manifest values
    cargoManifestIdVar CARGOMANIFEST.CARGOMANIFESTID%TYPE;
    cargoManifestVehicleVar CARGOMANIFEST.VEHICLEID%TYPE;
    cargoManifestDateVar CARGOMANIFEST.CARGOMANIFESTDATE%TYPE;
    cargoManifestTypeVar CARGOMANIFEST.CARGOMANIFESTTYPE%TYPE;

    -- variables to store container values. number of containers could be calculated with COUNT().
    containerNumber number :=0;
    containerId CARGOMANIFESTCONTAINER.CONTAINERID%TYPE;
    xPos CARGOMANIFESTCONTAINER.XPOS%TYPE;
    yPos CARGOMANIFESTCONTAINER.YPOS%TYPE;
    zPos CARGOMANIFESTCONTAINER.ZPOS%TYPE;
    containerCursor SYS_REFCURSOR;

    -- today and last day of week's date.
    today DATE:= inputDate;
    nextSaturday DATE := NEXT_DAY(today,'Sábado');

    -- variables to store employee values.
    employeeName EMPLOYEE.NAME%TYPE;
    employeeId EMPLOYEE.ID%TYPE;
    employeeRole EMPLOYEE.ROLEID%type;



    -- loads the manfests of that port during the following week and places in cursor.
    CURSOR loadingManifests is
        SELECT CARGOMANIFESTID, CARGOMANIFESTTYPE, VEHICLEID, CARGOMANIFESTDATE FROM CARGOMANIFEST
        WHERE CARGOMANIFEST.FACILITYID = portId AND CARGOMANIFEST.CARGOMANIFESTDATE > today AND
              CARGOMANIFEST.CARGOMANIFESTDATE <= nextSaturday
        ORDER BY CARGOMANIFESTDATE;
    -- loads all the employees of that port.
    CURSOR staff is
        SELECT ID, ROLEID, NAME  FROM EMPLOYEE emp join EMPLOYEEFACILITY fac on emp.ID = fac.EMPLOYEEID WHERE
                fac.FACILITYID = '10';
begin

    retValue := retValue || 'Report map for port/warehouse '|| portId || chr(10)|| chr(10);
    retValue := retValue || 'On Day ' || SYSDATE || ' until Saturday, ' || nextSaturday ;

    -- instead of throwing exception, we decided to merely warn the user that they're trying to check the map in the
    -- last day of the week, showing saturday's manifests that have not concluded
    if(TO_CHAR(today, 'DAY') = TO_CHAR(nextSaturday, 'DAY') )
    then
        retValue := retValue || chr(10) || 'Warning! Today is saturday, only showing values for today.' || chr(10);
    end if;

    OPEN loadingManifests;
    LOOP
        FETCH loadingManifests into cargoManifestIdVar, cargoManifestTypeVar, cargoManifestVehicleVar, cargoManifestDateVar;
        EXIT WHEN loadingManifests%notfound;
        retValue := retValue || ' ----------------------------------------------------------------------------------' || chr(10);
        retValue := retValue || 'Cargo Manifest: '|| cargoManifestIdVar || ' Type: ' || cargoManifestTypeVar || ' Date: ' || cargoManifestDateVar || chr(10)|| chr(10);


        -- for each manifest, we check its containers.
        OPEN containerCursor for SELECT  CONTAINERID, XPOS,YPOS,ZPOS FROM CARGOMANIFESTCONTAINER
                                 WHERE CARGOMANIFESTCONTAINER.CARGOMANIFESTID = cargoManifestIdVar;


        loop
            fetch containerCursor into containerId, xPos,yPos,zPos;
            exit when containerCursor%notfound;
            retValue := retValue || 'ContainedId: '|| containerId || ' has coordinates = (' || xPos || ',' || yPos || ',' || zPos || ')'|| chr(10);

            containerNumber := containerNumber + 1;
        end loop;
        retValue := retValue || 'Total Containers: '|| containerNumber|| chr(10);

        containerNumber :=0;


    END LOOP;
    CLOSE loadingManifests;
    retValue := retValue || ' ----------------------------------------------------------------------------------' || chr(10);

    OPEN staff;
    LOOP
        FETCH staff into employeeId,employeeRole,employeeName;
        EXIT WHEN staff%notfound;
        retValue := retValue || ' '|| employeeRole || ' - ' || employeeName || ' id= ' || employeeId || chr(10);
    END LOOP;
    CLOSE staff;

    return retValue;
end;
/


-- EXAMPLE CARGO MANIFEST 1 - should appear

INSERT INTO CARGOMANIFEST VALUES('301', '200', '200', '10', '1', TO_DATE('2022/01/24', 'yyyy/mm/dd'));
INSERT INTO CONTAINER VALUES('88881','28VH',2,5,2);
INSERT INTO CONTAINER VALUES('88882','28VH',3,1,2);
INSERT INTO CONTAINER VALUES('88883','28VH',2,2,4);
insert into CARGOMANIFESTCONTAINER VALUES ('301', '88881', 3,2,1);
insert into CARGOMANIFESTCONTAINER VALUES ('301', '88882', 18,2,3);
insert into CARGOMANIFESTCONTAINER VALUES ('301', '88883', 4,5,5);

delete from CARGOMANIFESTCONTAINER where CONTAINERID = '88881';
delete from CARGOMANIFESTCONTAINER where CONTAINERID = '88882';
delete from CARGOMANIFESTCONTAINER where CONTAINERID = '88883';
delete from CONTAINER  where CONTAINERID = '88881';
delete from CONTAINER  where CONTAINERID = '88882';
delete from CONTAINER  where CONTAINERID = '88883';
delete from CARGOMANIFEST WHERE CARGOMANIFESTID = '301';

-- EXAMPLE CARGO MANIFEST 2 - SHOULD APPEAR

INSERT INTO CARGOMANIFEST VALUES('302', '200', '200', '10', '1', TO_DATE('2022/01/26', 'yyyy/mm/dd'));
INSERT INTO CONTAINER VALUES('88884','28VH',2,5,2);
INSERT INTO CONTAINER VALUES('88885','28VH',3,1,2);
INSERT INTO CONTAINER VALUES('88886','28VH',2,2,4);
insert into CARGOMANIFESTCONTAINER VALUES ('302', '88884', 3,2,1);
insert into CARGOMANIFESTCONTAINER VALUES ('302', '88885', 18,2,3);
insert into CARGOMANIFESTCONTAINER VALUES ('302', '88886', 4,5,5);

delete from CARGOMANIFESTCONTAINER where CONTAINERID = '88884';
delete from CARGOMANIFESTCONTAINER where CONTAINERID = '88885';
delete from CARGOMANIFESTCONTAINER where CONTAINERID = '88886';
delete from CONTAINER  where CONTAINERID = '88884';
delete from CONTAINER  where CONTAINERID = '88885';
delete from CONTAINER  where CONTAINERID = '88886';
delete from CARGOMANIFEST WHERE CARGOMANIFESTID = '302';

-- EXAMPLE CARGO MANIFEST 3 - SHOULD APPEAR

INSERT INTO CARGOMANIFEST VALUES('303', '200', '200', '10', '1', TO_DATE('2022/01/29', 'yyyy/mm/dd'));
INSERT INTO CONTAINER VALUES('88887','28VH',2,5,2);
INSERT INTO CONTAINER VALUES('88888','28VH',3,1,2);
INSERT INTO CONTAINER VALUES('88889','28VH',2,2,4);
insert into CARGOMANIFESTCONTAINER VALUES ('303', '88887', 3,3,1);
insert into CARGOMANIFESTCONTAINER VALUES ('303', '88888', 12,1,3);
insert into CARGOMANIFESTCONTAINER VALUES ('303', '88889', 4,3,2);

delete from CARGOMANIFESTCONTAINER where CONTAINERID = '88887';
delete from CARGOMANIFESTCONTAINER where CONTAINERID = '88888';
delete from CARGOMANIFESTCONTAINER where CONTAINERID = '88889';
delete from CONTAINER  where CONTAINERID = '88887';
delete from CONTAINER  where CONTAINERID = '88888';
delete from CONTAINER  where CONTAINERID = '88889';
delete from CARGOMANIFEST WHERE CARGOMANIFESTID = '303';


-- EXAMPLE CARGO MANIFEST 4 - SHOULD NOT APPEAR

INSERT INTO CARGOMANIFEST VALUES('304', '200', '200', '10', '1', TO_DATE('2022/01/31', 'yyyy/mm/dd'));
INSERT INTO CONTAINER VALUES('88871','28VH',2,5,2);
INSERT INTO CONTAINER VALUES('88872','28VH',3,1,2);
INSERT INTO CONTAINER VALUES('88873','28VH',2,2,4);
insert into CARGOMANIFESTCONTAINER VALUES ('304', '88871', 2,1,1);
insert into CARGOMANIFESTCONTAINER VALUES ('304', '88872', 5,5,5);
insert into CARGOMANIFESTCONTAINER VALUES ('304', '88873', 4,3,22);

delete from CARGOMANIFESTCONTAINER where CONTAINERID = '88871';
delete from CARGOMANIFESTCONTAINER where CONTAINERID = '88872';
delete from CARGOMANIFESTCONTAINER where CONTAINERID = '88873';
delete from CONTAINER  where CONTAINERID = '88874';
delete from CONTAINER  where CONTAINERID = '88875';
delete from CONTAINER  where CONTAINERID = '88876';
delete from CARGOMANIFEST WHERE CARGOMANIFESTID = '304';


commit;





INSERT INTO CONTAINER VALUES('99991','28VH',2,2,2);
INSERT INTO CONTAINER VALUES('99992','28VH',2,2,2);
INSERT INTO CONTAINER VALUES('99993','28VH',2,2,2);
INSERT INTO CONTAINER VALUES('99994','28VH',2,2,2);
INSERT INTO CONTAINER VALUES('99995','28VH',2,2,2);
INSERT INTO CONTAINER VALUES('99996','28VH',2,2,2);

insert into CARGOMANIFESTCONTAINER VALUES ('108', '99991', 3,2,1);
insert into CARGOMANIFESTCONTAINER VALUES ('108', '99992', 3,2,1);
insert into CARGOMANIFESTCONTAINER VALUES ('108', '99993', 3,2,1);
insert into CARGOMANIFESTCONTAINER VALUES ('10', '99994', 3,2,1);
insert into CARGOMANIFESTCONTAINER VALUES ('10', '99995', 3,2,1);
insert into CARGOMANIFESTCONTAINER VALUES ('10', '99996', 3,2,1);

insert into EMPLOYEE values ('105', 'PS', 101,  'johnnyratoings@gmail.com','Joao Ratao');
insert into EMPLOYEE values ('106', 'PS', 101, 'bitaites@gmail.com', 'Bitaites');
insert into EMPLOYEE values ('109', 'WS', 101, 'johnnybravo@gmail.com','Senhor Incrivel');
insert into EMPLOYEE values ('108', 'WS', 101, 'calma@gmail.com','Jel' );

INSERT into EMPLOYEEFACILITY values ('10','105');
INSERT into EMPLOYEEFACILITY values ('10','106');
INSERT into EMPLOYEEFACILITY values ('10','109');
INSERT into EMPLOYEEFACILITY values ('10','108');
commit;

delete from EMPLOYEEFACILITY where EMPLOYEEID = '105';
delete from EMPLOYEEFACILITY where EMPLOYEEID = '106';
delete from EMPLOYEEFACILITY where EMPLOYEEID = '109';
delete from EMPLOYEEFACILITY where EMPLOYEEID = '108';

delete from EMPLOYEE where (EMPLOYEE.ID = '105');
delete from EMPLOYEE where (EMPLOYEE.ID = '106');
delete from EMPLOYEE where (EMPLOYEE.ID = '108');
delete from EMPLOYEE where (EMPLOYEE.ID = '109');

commit;
declare
    result VARCHAR2(32767);
    dateInput DATE := TO_DATE('2022/01/23', 'yyyy/mm/dd');
    begin

    result := fncCheckForCargoManifestMap('10',dateInput);
    dbms_output.put_line(result);
end;


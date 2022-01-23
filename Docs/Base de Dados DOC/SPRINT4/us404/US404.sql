create or replace function fnCountDays(vYear number)
    RETURN varchar is variableChar varchar(1000);


    vndays  number; --number of days
    counter number; --counts the days
    nTrips  number; -- number of trips
    currentDate date;
    tripId  number;
    initDate DATE; --initial date
    endDate  Date; -- end date
    diffDays number; --the difference between dates
    initDateString varchar(30); --initial date string
    chartest varchar(30);


    s_vehicleId SHIP.VEHICLEID%type;

    CURSOR vehicleIds IS SELECT VEHICLEID from SHIP; --the cursors gets all the vehicle ids



BEGIN


    initDateString := vYear || '-01-01';

    SELECT TO_CHAR
    (SYSDATE, 'YYYY-MM-DD') "NOW" into chartest --gets the sysdate into a char
     FROM DUAL;
    DBMS_OUTPUT.PUT_LINE(chartest);

    SELECT trunc(SYSDATE - TO_DATE('2022-01-01','yyyy-mm-dd'))  + 1 as days into vndays from dual; --the difference between the first day of the year and the current day of the year
    OPEN vehicleIds;
    counter := 1;



        LOOP
        FETCH vehicleIds into s_vehicleId;
            EXIT WHEN vehicleIds%notfound; --for each of the trip id

            Select count(IDTRIP) into nTrips  from TRIP where VEHICLEID = s_vehicleId
            and STARTDATE >= TO_DATE(initDateString,'YYYY-MM-DD')
            and ENDDATE <= TO_DATE(chartest,'YYYY-MM-DD');



            if(nTrips != 0)THEN --if the vehicle has at least one trip

                while counter <= nTrips
                    LOOP

                    SELECT IDTRIP into tripId from (SELECT IDTRIP, ROWNUM AS RN from TRIP where VEHICLEID = s_vehicleId
                    and STARTDATE >= TO_DATE(initDateString,'YYYY-MM-DD')
                    and ENDDATE <= TO_DATE(chartest,'YYYY-MM-DD'))
                    where RN = counter;

                    SELECT STARTDATE into initDate from TRIP where IDTRIP = tripId; --gets the initial date of the trip and the end date
                    SELECT ENDDATE into endDate from TRIP where IDTRIP = tripId;

                    SELECT endDate - initDate into diffDays from DUAL; --subtracts the initial date and the end date of the trip

                    vndays := vndays - diffDays; -- the total days of the year subtracting the trip days


                    counter := counter + 1;
                    end loop;
                end if;

            variableChar := variableChar || 'The Vehicle ID: '|| s_vehicleId|| ' spent: '|| vndays || ' days idle'|| ',';
            SELECT trunc(SYSDATE - TO_DATE('2022-01-01','yyyy-mm-dd'))  + 1 as days into vndays from dual;
            counter := 1;

        end loop;



    CLOSE vehicleIds;

    return variableChar;
end;
/



--Vehicle
INSERT INTO VEHICLE(VEHICLEID) values (2);
INSERT INTO VEHICLE(VEHICLEID) values (3);
INSERT INTO VEHICLE(VEHICLEID) values (4);
INSERT INTO VEHICLE(VEHICLEID) values (5);

--VesselType
INSERT INTO VESSELTYPE(vesseltypeid, vesseltype) values (71,71);
INSERT INTO VESSELTYPE(vesseltypeid, vesseltype) values (60,60);
INSERT INTO VESSELTYPE(vesseltypeid, vesseltype) values (79,79);
INSERT INTO VESSELTYPE(vesseltypeid, vesseltype) values (20,20);


INSERT INTO SHIP(mmsi, vehicleid, vesseltype, imo, callsign, name, length, width, capacity, draft)
values (255807169,2,71,'IMO9365095','CQDG7','N4me',50,50,50,50);
INSERT INTO SHIP(mmsi, vehicleid, vesseltype, imo, callsign, name, length, width, capacity, draft)
values (255809169,3,71,'IMO9395095','CQLG7','N0me',50,50,50,50);

--Trip
INSERT INTO TRIP(idtrip, vehicleid, startdate, enddate) VALUES (2,2,TO_DATE('2022-01-02','YYYY-MM-DD'),TO_DATE('2022-01-10','YYYY-MM-DD'));
INSERT INTO TRIP(idtrip, vehicleid, startdate, enddate) VALUES (3,3,TO_DATE('2022-01-05','YYYY-MM-DD'),TO_DATE('2022-01-15','YYYY-MM-DD'));

select FNCOUNTDAYS(2022) from DUAL;
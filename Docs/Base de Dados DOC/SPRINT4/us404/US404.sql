create or replace function fnCountDays(vYear number)
    RETURN varchar is variableChar varchar(1000);


    vndays  number;
    counter number;
    nTrips  number;
    currentDate date;
    tripId  number;
    initDate DATE;
    endDate  Date;
    diffDays number;
    initDateString varchar(30);
    chartest varchar(30);


    s_vehicleId SHIP.VEHICLEID%type;

    CURSOR vehicleIds IS SELECT VEHICLEID from SHIP;



BEGIN


    initDateString := vYear || '-01-01';

    SELECT TO_CHAR
    (SYSDATE, 'YYYY-MM-DD') "NOW" into chartest
     FROM DUAL;
    DBMS_OUTPUT.PUT_LINE(chartest);

    SELECT trunc(SYSDATE - TO_DATE('2022-01-01','yyyy-mm-dd'))  + 1 as days into vndays from dual;
    OPEN vehicleIds;
    counter := 1;



        LOOP
        FETCH vehicleIds into s_vehicleId;
            EXIT WHEN vehicleIds%notfound;

            Select count(IDTRIP) into nTrips  from TRIP where VEHICLEID = s_vehicleId
            and STARTDATE >= TO_DATE(initDateString,'YYYY-MM-DD')
            and ENDDATE <= TO_DATE(chartest,'YYYY-MM-DD');



            if(nTrips != 0)THEN

                while counter <= nTrips
                    LOOP

                    SELECT IDTRIP into tripId from (SELECT IDTRIP, ROWNUM AS RN from TRIP where VEHICLEID = s_vehicleId
                    and STARTDATE >= TO_DATE(initDateString,'YYYY-MM-DD')
                    and ENDDATE <= TO_DATE(chartest,'YYYY-MM-DD'))
                    where RN = counter;

                    SELECT STARTDATE into initDate from TRIP where IDTRIP = tripId;
                    SELECT ENDDATE into endDate from TRIP where IDTRIP = tripId;

                    SELECT endDate - initDate into diffDays from DUAL;

                    vndays := vndays - diffDays;


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

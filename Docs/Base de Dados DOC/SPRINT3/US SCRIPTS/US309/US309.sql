create or replace trigger trgDenyInvalidCargoManifest
    before insert
    on CARGOMANIFEST
    FOR EACH ROW
declare
    today TRIP.STARTDATE%type;
    tripBegin TRIP.STARTDATE%type;
    tripEnd TRIP.STARTDATE%type;
begin
    -- for testing
    -- today := TO_DATE('5/12/2021', 'DD/MM/YYYY');
    today := SYSDATE;
    select STARTDATE, ENDDATE  into tripBegin, tripEnd from TRIP
    where TRIP.IDTRIP = 1;
    if (today > tripBegin and today < tripEnd ) then
        raise_application_error(-20000,'This ship is already in transit.');
    end if;
end;

drop trigger trgDenyInvalidCargoManifest;

insert into CARGOMANIFEST values ('201','11','16','3','1',TO_DATE('5/12/2021', 'DD/MM/YYYY'));
commit;

delete from CARGOMANIFEST where CARGOMANIFESTID = '201';



select STARTDATE, ENDDATE from TRIP
    where TRIP.IDTRIP = 1;
select IDTRIP from CARGOMANIFEST


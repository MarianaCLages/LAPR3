Create or replace function fncCheckForContainerSituation (containerId_ CONTAINER.CONTAINERID%TYPE, clientId_ CLIENT.USERID%TYPE)
return boolean
is
    containerRow CONTAINER%ROWTYPE;
    clientRow CLIENT%ROWTYPE;
    clientContainerRow CONTAINERCLIENT%ROWTYPE;
    begin
        -- get client and check if valid
        begin
            select * into clientRow from CLIENT where clientId_ = CLIENT.ID;

            exception
            when NO_DATA_FOUND then
                -- no rows found, throwing exception
                raise_application_error(-20001,'No client found');
            when TOO_MANY_ROWS then
                -- found multiple clients with same ID (might not be needed)
                raise_application_error(-20001,'Found multiple clients with the same Id');
        end;

        -- get container and check if valid
        begin
            select * into containerRow from CONTAINER where containerId_ = CONTAINER.CONTAINERID;

        exception
            when NO_DATA_FOUND then
                -- no rows found, throwing specified error
                raise_application_error(10,'Invalid container ID');
        end;

        -- check if container belongs to client
        begin
            select * into clientContainerRow from CONTAINERCLIENT where
            (
                containerId_ = CONTAINERCLIENT.CONTAINERID
                and
                clientId_ = CLIENTID
                );
        exception
            when NO_DATA_FOUND then
                -- no rows found, throwing specified error
                raise_application_error(11,'Container not leased by client');
        end;

        -- return container row, user story doesn't specify required information
        return true;
    end;



    drop function prcCheckForContainerSituation
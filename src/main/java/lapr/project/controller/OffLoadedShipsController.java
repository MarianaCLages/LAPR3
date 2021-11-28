package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Port;
import lapr.project.model.Position;
import lapr.project.model.Ship;
import lapr.project.model.stores.PortStore;
import lapr.project.model.stores.ShipStore;
import lapr.project.shared.tree.TwoDTree;
import lapr.project.utils.auth.domain.User;

public class OffLoadedShipsController {

    private Company company;
    private ShipStore shipStore;
    private PortStore portStore;


    public OffLoadedShipsController(){

        company = App.getInstance().getCompany();
        shipStore = company.getShipStore();
        portStore = company.getPortStore();


    }

    public boolean offLoadedShips(int mmsi){



        try{
            Ship s = shipStore.getShipByMmsi(mmsi);

            s.setBiggestPosition();
            Position pos = s.getBiggestPosition();

            Port p = portStore.getList().nearesNeighbor(pos);

            return s.giveCargoASignOffLoaded(p);



        }catch (NullPointerException ex){
            System.out.println("There is no such ship");
            return false;
        }





    }
}

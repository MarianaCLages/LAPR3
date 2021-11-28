package lapr.project.model.stores;

import lapr.project.model.CargoManifest;
import lapr.project.shared.tree.AVL;

import java.util.ArrayList;
import java.util.List;

public class CargoManifestStore {

    AVL<CargoManifest> cargoManifestByAVL;

    public CargoManifestStore(){
        cargoManifestByAVL = new AVL<>();
    }

    public void add(CargoManifest cargoManifest){
        cargoManifestByAVL.insert(cargoManifest);
    }

    public boolean writeCargoManifests(){

        if(cargoManifestByAVL.isEmpty()) return false;

        for(CargoManifest c : cargoManifestByAVL.inOrder()){
            System.out.println(c.toString());
        }

        return true;
    }

    public AVL<CargoManifest> getCargoManifestByAVL() {
        return cargoManifestByAVL;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for(CargoManifest c : cargoManifestByAVL.inOrder()){
            sb.append(c);
        }
        return sb.toString();
    }
}

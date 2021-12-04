package lapr.project.model.stores;

import lapr.project.model.CargoManifest;
import lapr.project.model.Container;
import lapr.project.model.Position;
import lapr.project.shared.tree.AVL;


public class CargoManifestStore {

    AVL<CargoManifest> cargoManifestByAVL;

    public CargoManifestStore() {
        cargoManifestByAVL = new AVL<>();
    }

    public void add(CargoManifest cargoManifest) {
        cargoManifestByAVL.insert(cargoManifest);
    }

    public void updateCargoManifest(CargoManifest cargoManifest){

    }

    public void removeCargoManifest(CargoManifest cargoManifest){

    }

    private void createCargoManifest(){

    }


    public boolean writeCargoManifests() {

        if (cargoManifestByAVL.isEmpty()) return false;

        for (CargoManifest c : cargoManifestByAVL.inOrder()) {
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

        for (CargoManifest c : cargoManifestByAVL.inOrder()) {
            sb.append(c);
        }
        return sb.toString();
    }
}

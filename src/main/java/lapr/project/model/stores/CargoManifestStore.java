package lapr.project.model.stores;

import lapr.project.model.CargoManifest;
import lapr.project.model.Container;
import lapr.project.model.Position;
import lapr.project.shared.tree.AVL;


public class CargoManifestStore {

    AVL<CargoManifest> cargoManifestByAVL;

    /**
     * Constructor.
     */
    public CargoManifestStore() {
        cargoManifestByAVL = new AVL<>();
    }

    /**
     * Gets the cargo manifest AVL.
     *
     * @return the cargo manifest AVL
     */
    public AVL<CargoManifest> getCargoManifestByAVL() {
        return cargoManifestByAVL;
    }

    /**
     * Adds a cargo manifest in the AVL.
     *
     * @param cargoManifest the cargo manifest to be added
     */
    public void add(CargoManifest cargoManifest) {
        cargoManifestByAVL.insert(cargoManifest);
    }

    /**
     * Writes all the cargo manifests.
     *
     * @return all the cargo manifests (in a String)
     */
    public boolean writeCargoManifests() {
        if (cargoManifestByAVL.isEmpty()) {
            return false;
        }

        for (CargoManifest cargoManifest : cargoManifestByAVL.inOrder()) {
            //  System.out.println(cargoManifest.toString());
        }
        return true;
    }

    /**
     * Returns the textual description of the cargo manifest AVL info.
     *
     * @return the cargo manifest AVL characteristics
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (CargoManifest cargoManifest : cargoManifestByAVL.inOrder()) {
            sb.append(cargoManifest);
        }
        return sb.toString();
    }
}

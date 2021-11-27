package lapr.project.model;

import java.util.HashMap;
import java.util.Objects;

public class CargoManifest {

    private HashMap<String, Container> cargoManifest_Containers;
    private HashMap<String, Integer> cargoManifest_ContainerGross;
    private ContainerPosition cargoManifest_ContainerPosition;

    public CargoManifest(HashMap<String, Container> cargoManifest_Containers, HashMap<String, Integer> cargoManifest_ContainerGross, ContainerPosition cargoManifest_ContainerPosition) {
        this.cargoManifest_Containers = cargoManifest_Containers;
        this.cargoManifest_ContainerGross = cargoManifest_ContainerGross;
        this.cargoManifest_ContainerPosition = cargoManifest_ContainerPosition;
    }

    //Getters
    public HashMap<String, Container> getCargoManifest_Containers() {
        return cargoManifest_Containers;
    }

    public HashMap<String, Integer> getCargoManifest_ContainerGross() {
        return cargoManifest_ContainerGross;
    }

    public ContainerPosition getCargoManifest_ContainerPosition() {
        return cargoManifest_ContainerPosition;
    }

    //Setters
    public void setCargoManifest_Containers(HashMap<String, Container> cargoManifest_Containers) {
        this.cargoManifest_Containers = cargoManifest_Containers;
    }

    public void setCargoManifest_ContainerGross(HashMap<String, Integer> cargoManifest_ContainerGross) {
        this.cargoManifest_ContainerGross = cargoManifest_ContainerGross;
    }

    public void setCargoManifest_ContainerPosition(ContainerPosition cargoManifest_ContainerPosition) {
        this.cargoManifest_ContainerPosition = cargoManifest_ContainerPosition;
    }

    @Override
    public String toString() {
        return "CargoManifest{" +
                "cargoManifest_Containers=" + cargoManifest_Containers +
                ", cargoManifest_ContainerGross=" + cargoManifest_ContainerGross +
                ", cargoManifest_ContainerPosition=" + cargoManifest_ContainerPosition +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CargoManifest that = (CargoManifest) o;
        return Objects.equals(cargoManifest_Containers, that.cargoManifest_Containers) && Objects.equals(cargoManifest_ContainerGross, that.cargoManifest_ContainerGross) && Objects.equals(cargoManifest_ContainerPosition, that.cargoManifest_ContainerPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cargoManifest_Containers, cargoManifest_ContainerGross, cargoManifest_ContainerPosition);
    }
}

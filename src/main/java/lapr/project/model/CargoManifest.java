package lapr.project.model;

import lapr.project.model.stores.ContainerStore;
import lapr.project.shared.tree.AVL;

import java.util.Objects;

public class CargoManifest implements Comparable<CargoManifest> {

    String identification;
    private AVL<Container> offLoaded;
    private AVL<Container> loaded;
    private Port port;

    public CargoManifest(String identification, Port port) {

        this.identification = identification;
        this.port = port;
        offLoaded = new AVL<>();
        loaded = new AVL<>();

    }

    //Getters

    public String getIdentification() {
        return identification;
    }

    public AVL<Container> getOffLoaded() {
        return offLoaded;
    }

    public AVL<Container> getLoaded() {
        return loaded;
    }

    public Port getPort() {
        return port;
    }

    //Setters
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public void setPort(Port port) {
        this.port = port;
    }


    public boolean offLoadSign() {

        if (offLoaded.isEmpty()) {
            return false;
        }

        for (Container c : offLoaded.inOrder()) {
            System.out.println(c.toString() + "will be offloaded to" + port);
        }
        return true;
    }

    public boolean loadSign() {

        if (loaded.isEmpty()) {
            return false;
        }

        for (Container c : loaded.inOrder()) {
            System.out.println(c.toString() + "will be loaded to" + port);
        }
        return true;
    }

    @Override
    public String toString() {
        return "Identification:" + identification +
                "\nPort=" + port + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CargoManifest that = (CargoManifest) o;
        return Objects.equals(identification, that.identification) && Objects.equals(offLoaded, that.offLoaded) && Objects.equals(loaded, that.loaded) && Objects.equals(port, that.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(port, offLoaded, loaded);
    }


    @Override
    public int compareTo(CargoManifest o) {
        return Integer.compare(Integer.parseInt(this.getIdentification()), Integer.parseInt(o.getIdentification()));
    }
}

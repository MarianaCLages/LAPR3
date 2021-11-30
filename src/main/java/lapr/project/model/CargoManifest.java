package lapr.project.model;

import lapr.project.shared.tree.AVL;

import java.util.Date;
import java.util.Objects;

public class CargoManifest implements Comparable<CargoManifest> {

    String identification;
    private AVL<Container> offLoaded;
    private AVL<Container> loaded;
    private Port port;
    private Date date;

    public CargoManifest(String identification, Port port,Date date) {

        this.identification = identification;
        this.port = port;
        offLoaded = new AVL<>();
        loaded = new AVL<>();
        this.date = date;

    }

    //Getters
    public Date getDate(){return date;}

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
    public void setDate(Date date){this.date = date;}

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public int countContainers(){

        int count = 0;

        for (Container container : offLoaded.inOrder()){
            count = count + 1;
        }

        for (Container container : loaded.inOrder() ){
            count = count + 1;
        }

        return count;
    }

    public boolean offLoadSign() {

        if (offLoaded.isEmpty()) {
            return false;
        }

        for (Container container : offLoaded.inOrder()) {
            System.out.println("Container identifier: " + container.getIdentification() + "; Type: " + container.getContainerType() + "; Position: " + port.getLocation() + "; Load: " + container.getPayload() + "\n");
        }
        return true;
    }

    public boolean loadSign() {

        if (loaded.isEmpty()) {
            return false;
        }

        for (Container container : loaded.inOrder()) {
            System.out.println("Container identifier: " + container.getIdentification() + "; Type: " + container.getContainerType() + "; Load: " + container.getPayload() + "\n");
        }
        return true;
    }

    @Override
    public String toString() {
        return "CargoManifest{" +
                "identification='" + identification + '\'' +
                ", port=" + port +
                ", date=" + date +
                '}';
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

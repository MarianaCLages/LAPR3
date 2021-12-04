package lapr.project.model;

import lapr.project.shared.tree.AVL;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import java.util.Date;
import java.util.HashMap;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.zip.ZipEntry;

public class CargoManifest implements Comparable<CargoManifest> {

    String identification;
    private AVL<Container> offLoaded;
    private AVL<Container> loaded;
    private Port port;

    //--------------- new parameters
    private Ship ship;
    private boolean inTransport;
    private NavigableMap<PositionXYZ, Container> ContainerMap;

    public CargoManifest(String identification, Port port, Ship ship, boolean inTransport) {

        this.identification = identification;
        this.port = port;
        //offLoaded = new AVL<>();
        loaded = new AVL<>();

        //--------------
        this.ship = ship;
        this.inTransport = inTransport;

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

    public boolean getInTransport(){
        return inTransport;
    }

    public Ship getShip(){ return ship;}

    //Setters
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public void setShip(Ship ship) { this.ship = ship;}




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

    //--------------------- new methods
    public Boolean checkContainerInAVL(Container container){
        return loaded.find(container) != null;
    }

    public boolean addContainerToAVL(Container container) {
        if(loaded.find(container).equals(null)){
            loaded.insert(container);
            return true;
        }
        return false;
    }

    public boolean addContainerToMap(PositionXYZ rPosXYZ, Container rContainer){
        if (ContainerMap.containsKey(rPosXYZ) && ContainerMap.containsValue(rContainer)){
            return false;
        }
        ContainerMap.put(rPosXYZ,rContainer);
        return true;
    }

    public void refreshContainerMap(){
        for (PositionXYZ oPosXYZ : ContainerMap.navigableKeySet()){

        }
    }

    public PositionXYZ getNextAvailablePositionXYZ(){
        PositionXYZ lPosXYZ = ContainerMap.lastKey();
        int X;
        int Y;
        int Z = -1;

        if(lPosXYZ.getFirst() < ship.getWidth()){
            X = lPosXYZ.getFirst() + 1;
            Y = lPosXYZ.getSecond();
            Z = lPosXYZ.getThird();

        } else {
            X = 0;

            if(lPosXYZ.getSecond() < ship.getLength()){
                Y = lPosXYZ.getSecond() + 1;
                Z = lPosXYZ.getThird();
            } else {
                Y = 0;

                if(lPosXYZ.getThird() < ship.getCapacity()) {
                    Z = lPosXYZ.getThird() + 1;
                }
            }
        }

        return new PositionXYZ(X, Y, Z);
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

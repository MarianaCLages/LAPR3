package lapr.project.model;

import lapr.project.model.stores.CargoManifestStore;
import lapr.project.model.stores.ContainerStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class CargoManifest implements Comparable<CargoManifest> {


    String identification;
    private ContainerStore containerStore;
    private Port port;

    public CargoManifest(String identification,Port port) {

        this.identification = identification;
        this.port = port;
        containerStore = new ContainerStore();

    }

    //Getters

    public String getIdentification(){
        return identification;
    }

    public ContainerStore getContainerStore() {
        return containerStore;
    }

    public Port getPort() {
        return port;
    }

    //Setters
    public void setIdentification(String identification){
        this.identification = identification;
    }

    public void setPort(Port port) {
        this.port = port;
    }


    public void setContainerStore(ContainerStore containerStore) {
        this.containerStore = containerStore;
    }



    public boolean offLoadSign(){

        if(containerStore.containerByAVL.isEmpty()) return false;

        for(Container c : containerStore.getContainerByAVL().inOrder()){
            System.out.println(c.toString()+ "will be offloaded to" +port);
        }
        return true;
    }

    @Override
    public String toString() {
        return  "Identification:"+identification+
                "\nPort=" + port + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CargoManifest that = (CargoManifest) o;
        return Objects.equals(port, that.port) && Objects.equals(containerStore, that.containerStore) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(port, containerStore);
    }


    @Override
    public int compareTo(CargoManifest o) {

        if(Integer.parseInt(this.getIdentification()) < Integer.parseInt(o.getIdentification())){
            return -1;
        }
        else if(Integer.parseInt(this.getIdentification()) > Integer.parseInt(o.getIdentification())){
            return 1;
        }
        else return 0;
    }
}

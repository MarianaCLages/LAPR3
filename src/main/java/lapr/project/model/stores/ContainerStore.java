package lapr.project.model.stores;

import lapr.project.model.Container;
import lapr.project.shared.tree.AVL;

import java.util.ArrayList;
import java.util.List;

public class ContainerStore {


    public AVL<Container> containerByAVL;

    public ContainerStore(){
        containerByAVL = new AVL<>();
    }

    public void addContainer(Container c){
        containerByAVL.insert(c);
    }

    public boolean writeAllContainers(){

        if(containerByAVL.isEmpty())
            return false;

        for(Container c : containerByAVL.inOrder())
            System.out.println(c);

        return true;
    }

    public AVL<Container> getContainerByAVL() {
        return containerByAVL;
    }
}

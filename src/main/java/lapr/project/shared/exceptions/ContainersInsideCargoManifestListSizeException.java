package lapr.project.shared.exceptions;

public class ContainersInsideCargoManifestListSizeException  extends Exception{

    public ContainersInsideCargoManifestListSizeException(){
        super("There are 0 containers inside that specific cargo manifest! Please consider choosing other cargo manifest or add containers to that cargo manifest.");
    }

}

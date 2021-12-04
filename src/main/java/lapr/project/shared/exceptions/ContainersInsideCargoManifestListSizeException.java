package lapr.project.shared.exceptions;

public class ContainersInsideCargoManifestListSizeException  extends Exception{

    public ContainersInsideCargoManifestListSizeException(){
        super("There are 0 containers inside that specific cargoManifest! Please consider choosing other cargoManifest or add containers to that cargoManifest.");
    }

}

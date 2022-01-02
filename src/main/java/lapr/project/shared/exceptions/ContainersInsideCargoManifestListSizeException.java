package lapr.project.shared.exceptions;

public class ContainersInsideCargoManifestListSizeException extends Exception {

    /**
     * In case a cargo manifest doesn't have any containers, it prints the message "There are no containers inside that specific cargo manifest! Please consider choosing other cargo manifest or add containers to that cargo manifest.".
     */
    public ContainersInsideCargoManifestListSizeException() {
        super("There are no containers inside that specific cargo manifest! Please consider choosing other cargo manifest or add containers to that cargo manifest.");
    }
}

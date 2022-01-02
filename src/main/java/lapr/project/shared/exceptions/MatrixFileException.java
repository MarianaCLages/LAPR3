package lapr.project.shared.exceptions;

public class MatrixFileException extends Exception {

    /**
     * In case the matrix file couldn't be created, it prints the message "Cannot create the matrix file! Please verify the data!".
     */
    public MatrixFileException() {
        super("Cannot create the matrix file! Please verify the data!");
    }
}

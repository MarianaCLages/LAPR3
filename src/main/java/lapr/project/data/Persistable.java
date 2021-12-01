package lapr.project.data;

/**
 * @author nunocastro
 */
public interface Persistable {
    /**
     * Save an objet to the database.
     *
     * @param databaseConnection
     * @param object
     * @return Operation success.
     */
    boolean save(DatabaseConnection databaseConnection, Object object);

    /**
     * Delete an object from the database.
     *
     * @param databaseConnection
     * @param object
     * @return Operation success.
     */
    boolean delete(DatabaseConnection databaseConnection, Object object);


    /**
     * Gets a object from the database.
     *
     * @param databaseConnection
     * @param object
     * @return Operation success.
     */
    Object getElement(DatabaseConnection databaseConnection, Object object);


}

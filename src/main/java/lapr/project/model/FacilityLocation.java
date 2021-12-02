package lapr.project.model;

import static lapr.project.shared.Constants.MAX_LATITUDE;
import static lapr.project.shared.Constants.MAX_LONGITUDE;

public class FacilityLocation {
    private double latitude;
    private double longitude;

    /**
     * Constructor.
     *
     * @param latitude  the latitude of the facility location
     * @param longitude the longitude of the facility location
     */
    public FacilityLocation(double longitude, double latitude) {
        if (checkLatitude(latitude)) throw new IllegalArgumentException("Invalid latitude");
        if (checkLongitude(longitude)) throw new IllegalArgumentException("Invalid longitude");
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Checks the latitude of the facility location.
     *
     * @param latitude the latitude of the facility location
     * @return true if it is valid, false if it isn't
     */
    private boolean checkLatitude(double latitude) {
        return (latitude < -MAX_LATITUDE || latitude > MAX_LATITUDE);
    }

    /**
     * Checks the longitude of the facility location.
     *
     * @param longitude the longitude of the facility location
     * @return true if it is valid, false if it isn't
     */
    private boolean checkLongitude(double longitude) {
        return (longitude < -MAX_LONGITUDE || longitude > MAX_LONGITUDE);
    }

    /**
     * Gets the latitude of the facility location
     *
     * @return the latitude of the facility location
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude of the facility location.
     *
     * @param latitude the latitude of the facility location
     */
    public void setLatitude(double latitude) {
        if (checkLatitude(latitude)) throw new IllegalArgumentException("Invalid latitude");
        this.latitude = latitude;
    }

    /**
     * Gets the longitude of the facility location
     *
     * @return the longitude of the facility location
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude of the facility location.
     *
     * @param longitude the longitude of the facility location
     */
    public void setLongitude(double longitude) {
        if (checkLongitude(longitude)) throw new IllegalArgumentException("Invalid longitude");
        this.longitude = longitude;
    }

    /**
     * Checks if two objects (FacilityLocation) are equal.
     *
     * @param o the object
     * @return true if objects are equal, false if they aren't
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacilityLocation)) return false;

        FacilityLocation location = (FacilityLocation) o;

        if (Double.compare(location.getLatitude(), getLatitude()) != 0) return false;
        return Double.compare(location.getLongitude(), getLongitude()) == 0;
    }

    /**
     * Generates a hash code for the facility location values.
     *
     * @return the hash code for the facility location values
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getLatitude());
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLongitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Returns the textual description of the facility location in the format: latitude, longitude.
     * @return the facility location's characteristics
     */
    @Override
    public String toString() {
        return "latitude = " + latitude + ", longitude = " + longitude;
    }
}

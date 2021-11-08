package lapr.project.model;

import static lapr.project.shared.Constants.MAX_LATITUDE;
import static lapr.project.shared.Constants.MAX_LONGITUDE;

public class FacilityLocation {
    private double latitude;
    private double longitude;

    public FacilityLocation(double latitude, double longitude) {
        if (checkLatitude(latitude)) throw new IllegalArgumentException("Invalid latitude");
        if (checkLongitude(longitude)) throw new IllegalArgumentException("Invalid longitude");
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private boolean checkLatitude(double latitude) {
        return (latitude < -MAX_LATITUDE || latitude > MAX_LATITUDE);
    }

    private boolean checkLongitude(double longitude) {
        return (longitude < -MAX_LONGITUDE || longitude > MAX_LONGITUDE);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        if (checkLatitude(latitude)) throw new IllegalArgumentException("Invalid latitude");
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        if (checkLongitude(longitude)) throw new IllegalArgumentException("Invalid longitude");
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacilityLocation)) return false;

        FacilityLocation location = (FacilityLocation) o;

        if (Double.compare(location.getLatitude(), getLatitude()) != 0) return false;
        return Double.compare(location.getLongitude(), getLongitude()) == 0;
    }


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
}

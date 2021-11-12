package lapr.project.model;

import java.time.LocalDateTime;

import static lapr.project.shared.Constants.*;

public class Position implements Comparable<Position> {
    Double latitude;
    Double longitude;
    Double heading;
    Double sog;
    Double cog;
    LocalDateTime date;

    /**
     * Constructor.
     *
     * @param latitude  the position's latitude
     * @param longitude the position's longitude
     * @param heading   the position's heading
     * @param sog       the position's SOG
     * @param cog       the position's COG
     * @param date      the position's date
     */
    public Position(double latitude, double longitude, double heading, double sog, double cog, LocalDateTime date) {
        setLatitude(latitude);
        setLongitude(longitude);
        setHeading(heading);
        setSog(sog);
        setCog(cog);
        setDate(date);
    }

    /**
     * Gets the position's latitude
     *
     * @return the position's latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Gets the position's longitude
     *
     * @return the position's longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Gets the position's heading
     *
     * @return the position's heading
     */
    public Double getHeading() {
        return heading;
    }

    /**
     * Gets the position's SOG
     *
     * @return the position's SOG
     */
    public Double getSog() {
        return sog;
    }

    /**
     * Gets the position's COG
     *
     * @return the position's COG
     */
    public Double getCog() {
        return cog;
    }

    /**
     * Gets the position's date
     *
     * @return the position's date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets the position's latitude.
     *
     * @param latitude the position's latitude
     */
    public void setLatitude(double latitude) {
        if (latitude == 91) {
            this.latitude = null;
            return;
        }
        if (!(latitude < -MAX_LATITUDE || latitude > MAX_LATITUDE)) {
            this.latitude = latitude;
            return;
        }

        throw new IllegalArgumentException("");
    }

    /**
     * Sets the position's longitude.
     *
     * @param longitude the position's longitude
     */
    public void setLongitude(double longitude) {
        if (longitude == 181) {
            this.longitude = null;
            return;
        }
        if (!(longitude < -MAX_LONGITUDE || longitude > MAX_LONGITUDE)) {
            this.longitude = longitude;
            return;
        }

        throw new IllegalArgumentException("");
    }

    /**
     * Sets the position's heading.
     *
     * @param heading the position's heading
     */
    public void setHeading(double heading) {
        if (heading == 511) {
            this.heading = null;
            return;
        }
        if (!(heading < -MIN_HEADING || heading > MAX_HEADING)) {
            this.heading = heading;
            return;
        }
        throw new IllegalArgumentException("");
    }

    /**
     * Sets the position's SOG.
     *
     * @param sog the position's SOG
     */
    public void setSog(double sog) {
        this.sog = sog;
    }

    /**
     * Sets the position's COG.
     *
     * @param cog the position's COG
     */
    public void setCog(double cog) {
        this.cog = cog;
    }

    /**
     * Sets the position's date.
     *
     * @param date the position's date
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Returns the textual description of the position info in the format: latitude, longitude, heading, SOG, COG.
     *
     * @return the position's characteristics
     */
    @Override
    public String toString() {
        return "Position{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", heading=" + heading +
                ", SOG=" + sog +
                ", COG=" + cog +
                '}';
    }

    /**
     * Compares the position date of two different objects (Position).
     *
     * @param o the object to compare (Position)
     * @return 0 if they're equal, -1 or 1 if they're different
     */
    @Override
    public int compareTo(Position o) {
        if (this.getDate().isBefore(o.getDate())) {
            return -1;
        } else if (this.getDate().isAfter(o.getDate())) {
            return 1;
        } else {
            return 0;
        }
    }
}


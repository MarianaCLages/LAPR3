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

    public Position(double latitude, double longitude, double heading, double sog, double cog, LocalDateTime date) {

        setLatitude(latitude);
        setLongitude(longitude);
        setHeading(heading);
        setSog(sog);
        setCog(cog);
        setDate(date);
    }

/*
    private boolean checkLatitude(double latitude) {
        return (latitude < -MAX_LATITUDE || latitude > MAX_LATITUDE);
    }

    private boolean checkLongitude(double longitude) {
        return (longitude < -MAX_LONGITUDE || longitude > MAX_LONGITUDE);
    }

    private boolean checkHeading(double heading) {
        return (heading < -MAX_HEADING || heading > MAX_HEADING);
    }
*/


    public Double getLatitude() {
        return latitude;
    }

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

    public Double getLongitude() {
        return longitude;
    }

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

    public Double getHeading() {
        return heading;
    }

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

    public Double getSog() {
        return sog;
    }

    public void setSog(double sog) {
        this.sog = sog;
    }

    public Double getCog() {
        return cog;
    }

    public void setCog(double cog) {
        this.cog = cog;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Position{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", heading=" + heading +
                ", sog=" + sog +
                ", cog=" + cog +
                '}';
    }


    @Override
    public int compareTo(Position o) {

        if (this.getDate().isBefore(o.getDate())) return -1;
        else if (this.getDate().isAfter(o.getDate())) return 1;
        else return 0;
    }
}


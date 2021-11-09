package lapr.project.model;

import java.time.LocalDateTime;

public class Position implements Comparable<Position> {

    double latitude;
    double longitude;
    double heading;
    double sog;
    double cog;
    LocalDateTime date;

    public Position(double latitude, double longitude, double heading, double sog, double cog, LocalDateTime date) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.heading = heading;
        this.sog = sog;
        this.cog = cog;
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getHeading() {
        return heading;
    }

    public double getSog() {
        return sog;
    }

    public double getCog() {
        return cog;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public void setSog(double sog) {
        this.sog = sog;
    }

    public void setCog(double cog) {
        this.cog = cog;
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

        if(this.getDate().isBefore(o.getDate())) return -1;
        else if(this.getDate().isAfter(o.getDate())) return 1;
        else return 0;
    }
}


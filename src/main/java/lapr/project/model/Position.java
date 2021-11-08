package lapr.project.model;

import java.time.LocalDateTime;

public class Position implements Comparable<Position> {
    LocalDateTime dateHour;
    long latitude;
    long longitude;
    long heading;
    long sog;
    long cog;

    public Position(LocalDateTime dateHour, long latitude, long longitude, long heading, long sog, long cog) {
        this.dateHour = dateHour;
        this.latitude = latitude;
        this.longitude = longitude;
        this.heading = heading;
        this.sog = sog;
        this.cog = cog;
    }

    public LocalDateTime getDateHour() {
        return dateHour;
    }


    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getHeading() {
        return heading;
    }

    public void setHeading(long heading) {
        this.heading = heading;
    }

    public long getSog() {
        return sog;
    }

    public void setSog(long sog) {
        this.sog = sog;
    }

    public long getCog() {
        return cog;
    }

    public void setCog(long cog) {
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
        if (dateHour.isAfter(o.getDateHour())) return 1;
        if (dateHour.isBefore(o.getDateHour())) return -1;
        return 0;
    }
}


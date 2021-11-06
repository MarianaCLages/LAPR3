package lapr.project.model;

import java.util.Objects;

public class Container {

    private String identification;
    private int payload;
    private int tare;
    private int gross;

    private String isoCode;

    public Container(String identification, int payload, int tare, int gross, String isoCode) {
        this.identification = identification;
        this.payload = payload;
        this.tare = tare;
        this.gross = gross;
        this.isoCode = isoCode;
    }

    public String getIdentification() {
        return identification;
    }

    public int getPayload() {
        return payload;
    }

    public int getTare() {
        return tare;
    }

    public int getGross() {
        return gross;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public void setPayload(int payload) {
        this.payload = payload;
    }

    public void setTare(int tare) {
        this.tare = tare;
    }

    public void setGross(int gross) {
        this.gross = gross;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    @Override
    public String toString() {
        return "Container{" +
                "identification='" + identification + '\'' +
                ", payload=" + payload +
                ", tare=" + tare +
                ", gross=" + gross +
                ", isoCode='" + isoCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container container = (Container) o;
        return payload == container.payload && tare == container.tare && gross == container.gross && identification.equals(container.identification) && isoCode.equals(container.isoCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identification, payload, tare, gross, isoCode);
    }
}

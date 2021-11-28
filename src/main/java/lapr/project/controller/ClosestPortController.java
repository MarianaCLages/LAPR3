package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Port;
import lapr.project.model.Ship;
import lapr.project.model.stores.PortStore;

import java.time.LocalDateTime;

public class ClosestPortController {

    private final Company company = App.getInstance().getCompany();
    private PortStore portStore;

    public ClosestPortController() {

        portStore = company.getPortStore();

    }

    public Port getNearestPortByCallSign(String callSign, LocalDateTime localDateTime) {

        Ship ship =company.getShipStore().getShipByCallSign(callSign);

        return portStore.getNearestNeighbourByTime(ship, localDateTime);

    }

}

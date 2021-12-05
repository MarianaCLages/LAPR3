package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Port;
import lapr.project.model.Ship;
import lapr.project.model.stores.PortStore;
import lapr.project.utils.mappers.PortMapper;
import lapr.project.utils.mappers.dto.PortDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClosestPortController {

    private final Company company = App.getInstance().getCompany();
    private final PortStore portStore;
    private final PortMapper portMapper;

    /**
     * Constructor.
     */
    public ClosestPortController() {
        portStore = company.getPortStore();
        portMapper = new PortMapper();
    }

    /**
     * Gets the nearest port by call sign (in DTO)
     *
     * @param callSign      the ship's call sign
     * @param localDateTime the time
     * @return the nearest port by call sign (in DTO)
     */
    public PortDTO getNearestPortByCallSign(String callSign, LocalDateTime localDateTime) throws IllegalArgumentException {
        Ship ship = company.getShipStore().getShipByCallSign(callSign);
        Port port = portStore.getNearestNeighbourByTime(ship, localDateTime);

        List<Port> portList = new ArrayList<>();
        portList.add(port);

        List<PortDTO> listOfPortsDTO = portMapper.toDTO(portList);
        return (listOfPortsDTO.get(0));
    }
}

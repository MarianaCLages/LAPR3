package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.model.stores.ShipStore;
import lapr.project.shared.exceptions.InvalidLineException;
import lapr.project.utils.CsvUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ShipImporter {

    public static boolean importsShips(File path) throws FileNotFoundException, InvalidLineException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Company company = App.getInstance().getCompany();
        ShipStore shipStore = company.getShipStore();
        List<String[]> data = CsvUtils.readFile(path);

        for (String[] line : data) {

            Ship ship = null;
            if (!shipStore.existsShip(Integer.parseInt(line[0]))) {
                if (line.length != 16) throw new InvalidLineException();
                ship = shipStore.createShip(Integer.parseInt(line[0]), line[7], line[8], line[9], line[10], Double.parseDouble(line[11]), Double.parseDouble(line[12]), Math.abs(Double.parseDouble(line[13])), line[14], line[15].charAt(0));
                shipStore.addShip(ship);
            } else {
                if (line.length != 16) throw new InvalidLineException();
                ship = shipStore.findShip(Integer.parseInt(line[0]));
            }
            Position position = ship.createPosition(LocalDateTime.parse(line[1], formatter), Double.parseDouble(line[2]), Double.parseDouble(line[3]), Double.parseDouble(line[6]), Double.parseDouble(line[4]), Math.abs(Double.parseDouble(line[5])));
            ship.insertPosition(position);
        }
        if (data.size() == 0) {
            return false;
        }
        return true;
    }
}

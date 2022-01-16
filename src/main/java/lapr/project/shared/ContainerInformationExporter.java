package lapr.project.shared;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.Utils.DataBaseUtils;
import lapr.project.model.Container;
import lapr.project.model.ISODimentions;
import lapr.project.model.RefrigeratedContainer;
import lapr.project.shared.exceptions.InvalidCargoManifestException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class ContainerInformationExporter {
    private ContainerInformationExporter() {
        // empty
    }

    public static boolean exportInformation(String cargoManifestId, DatabaseConnection databaseConnection) throws SQLException, InvalidCargoManifestException {

        List<Container> containers = DataBaseUtils.getAllContainersFromACargoManifest(cargoManifestId, databaseConnection);

        if (containers == null) {
            throw new InvalidCargoManifestException();
        }
        try (PrintWriter writer = new PrintWriter("containers.txt")) {

            StringBuilder sb = new StringBuilder();

            sb.append("x,y,z,width,length,height,isRefrigerated,refrigerationTemperature,energyConsumption,payLoad,tare,gross,id\n");

            for (Container container : containers) {

                ISODimentions dimensions = DataBaseUtils.getDimensionsByISO(container.getIsoCode(), databaseConnection);

                if (dimensions != null) {
                    sb.append(container.getPosition().getxPos());
                    sb.append(",");
                    sb.append(container.getPosition().getyPos());
                    sb.append(",");
                    sb.append(container.getPosition().getzPos());
                    sb.append(",");
                    sb.append(dimensions.getWidth());
                    sb.append(",");
                    sb.append(dimensions.getLength());
                    sb.append(",");
                    sb.append(dimensions.getHeight());

                    if (container instanceof RefrigeratedContainer) {
                        sb.append(",");
                        sb.append(1);
                        sb.append(",");
                        sb.append(((RefrigeratedContainer) container).getTemperature());
                        sb.append(",");
                        sb.append((int) ((RefrigeratedContainer) container).getEnergyConsume());
                    } else {
                        sb.append(",");
                        sb.append(0);
                        sb.append(",");
                        sb.append(0);
                        sb.append(",");
                        sb.append(0);

                    }

                    sb.append(",");
                    sb.append(container.getPayload());
                    sb.append(",");
                    sb.append(container.getTare());
                    sb.append(",");
                    sb.append(container.getGross());
                    sb.append(",");
                    sb.append(container.getIdentification());
                    sb.append("\n");
                }

            }
            writer.print(sb.toString());

        } catch (FileNotFoundException e) {
            return false;
        }

        return true;
    }

}

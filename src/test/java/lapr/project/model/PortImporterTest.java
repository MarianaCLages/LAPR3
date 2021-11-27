package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.model.stores.PortStore;
import lapr.project.shared.tree.TwoDTree;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.List;

class PortImporterTest {

    @Test
    void importPorts() throws FileNotFoundException {
        PortStore store = App.getInstance().getCompany().getPortStore();
        PortImporter.importPorts(Paths.get("data-ships&ports/bports.csv"));
    }
}
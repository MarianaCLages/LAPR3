package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.model.stores.PortStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class PortImporterTest {
    @TempDir
    static Path tempDir;
    static Path tempFile;

    @BeforeEach
    public void init() throws IOException {

        tempDir = Files.createTempDirectory("test");
        tempFile = Files.createFile(tempDir.resolve("ports.csv"));

        FileWriter fileWriter = new FileWriter(String.valueOf(tempFile));
        fileWriter.write("continent,country,code,port,lat,lon\n");
        fileWriter.write("America,United States,25007,New Jersey,40.66666667,-74.16666667\n");
        fileWriter.write("Europe,United Kingdom,29002,Liverpool,53.46666667,-3.033333333\n");
        fileWriter.write("America,United States,14635,Los Angeles,33.71666667,-118.2666667\n");
        fileWriter.write("Europe,France,18326,Dunkirk,51.05,2.366666667\n");

        fileWriter.close();

    }

    @Test
    void importPortsSmallest() throws FileNotFoundException {
        Port port = new Port("America", "United States", "14635", "Los Angeles", new FacilityLocation(-118.2666667, 33.71666667));
        PortStore store = App.getInstance().getCompany().getPortStore();
        PortImporter.importPorts(tempFile.toFile());
        Assertions.assertEquals(store.getList().smallestElement(), port);
    }


    @Test
    void importPortsAllString() throws FileNotFoundException {
        String string = " --Europa-- \n" +
                " --United States--  --Asia-- \n" +
                " --United States--  --United Kingdom--  --null--  --France-- \n" +
                " --null--  --null--  --null--  --null--  --null--  --null-- \n";
        PortStore store = App.getInstance().getCompany().getPortStore();
        PortImporter.importPorts(tempFile.toFile());
        Assertions.assertEquals(string, store.getList().toString());
    }

}
package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.model.stores.ShipStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class ShipImporterTest {

    @TempDir
    static Path tempDir;
    static Path tempFile;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    ShipStore shipStore = App.getInstance().getCompany().getShipStore();

    @BeforeAll
    public static void init() throws IOException {

        tempDir = Files.createTempDirectory("test");
        tempFile = Files.createFile(tempDir.resolve("ships.csv"));

        FileWriter fileWriter = new FileWriter(String.valueOf(tempFile));
        fileWriter.write("210950000,31/12/2020 17:19,42.97875,-66.97001,12.9,13.1,355,VARAMO,IMO9395044,C4SQ2,70,166,25,9.5,NA,B\n");
        fileWriter.write("210950000,31/12/2020 16:20,42.7698,-66.9759,13.3,3.7,356,VARAMO,IMO9395044,C4SQ2,70,166,25,9.5,NA,B\n");
        fileWriter.write("212180000,31/12/2020 21:07,24.20221,-84.85411,11.3,116.8,117,SAITA I,IMO9643544,5BBA4,70,228,32,14.4,NA,A\n");
        fileWriter.close();

    }


    @Test
    void testImportsViewShips() {
        try {
            ShipImporter.importsShips(new File(tempFile.toString()));
            List<Integer> list = new ArrayList<>();
            list.add(210950000);
            list.add(212180000);
            Assertions.assertEquals(list, shipStore.getShipListMmsi());
        } catch (FileNotFoundException e) {
            Assertions.fail();
        }

    }

    @Test
    void testImportsViewPositions() {
        try {
            ShipImporter.importsShips(new File(tempFile.toString()));
            List<String> list = new ArrayList<>();
            Position position1 = new Position(LocalDateTime.from(formatter.parse("31/12/2020 17:19")), 1, 0, 0, 0, 0);
            list.add(position1.toString());
            Position position2 = new Position(LocalDateTime.from(formatter.parse("31/12/2020 16:20")), 1, 0, 0, 0, 0);
            list.add(position2.toString());
         //   Assertions.assertTrue(list.equals(shipStore.getShipListPos()));

        } catch (FileNotFoundException e) {
            Assertions.fail();
        }
    }

}
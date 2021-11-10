package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.model.stores.ShipStore;
import lapr.project.shared.exceptions.InvalidLineException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void init() throws IOException {

        tempDir = Files.createTempDirectory("test");
        tempFile = Files.createFile(tempDir.resolve("ships.csv"));

        FileWriter fileWriter = new FileWriter(String.valueOf(tempFile));
        fileWriter.write("210950000,31/12/2020 17:19,42.97875,-66.97001,12.9,13.1,355,VARAMO,IMO9395044,C4SQ2,70,166,25,9.5,NA,B\n");
        fileWriter.write("210950000,31/12/2020 16:20,42.7698,-66.9759,13.3,3.7,356,VARAMO,IMO9395044,C4SQ2,70,166,25,9.5,NA,B\n");
        fileWriter.write("212180000,31/12/2020 21:07,24.20221,-84.85411,11.3,116.8,117,SAITA I,IMO9643544,5BBA4,70,228,32,14.4,NA,A\n");
        //fileWriter.write("212180000,31/12/2020 21:08,26.20221,-84.95411,11.3,116.8,117,SAITA I,IMO9643544,5BBA4,70,228,32,14.4,NA,A,8\n");

        fileWriter.close();

    }


    @Test
    void testImportNoShips() {
        Assertions.assertThrows(Exception.class, () -> {
            ShipImporter.importsShips(new File("test.csv"));
        });
    }

    @Test
    void testInvalidLine() throws IOException {

        tempFile = Files.createFile(tempDir.resolve("shipsInvalidLine.csv"));

        FileWriter fileWriter = new FileWriter(String.valueOf(tempFile));
        fileWriter.write("212180000,31/12/2020 21:08,26.20221,-84.95411,11.3,116.8,117,SAITA I,IMO9643544,5BBA4,70,228,32,14.4,NA,A,8\n");
        fileWriter.close();

        Assertions.assertThrows(Exception.class, () -> {
            ShipImporter.importsShips(new File("shipsInvalidLine.csv"));
        });

    }

    @Test
    void testInvalidLineExistsShip() throws IOException {

        tempFile = Files.createFile(tempDir.resolve("shipsInvalidLine.csv"));

        FileWriter fileWriter = new FileWriter(String.valueOf(tempFile));
        fileWriter.write("212170000,31/12/2020 21:08,26.20221,-84.95411,11.3,116.8,117,SAITA I,IMO9643544,5BBA4,70,228,32,14.4,NA,A,8\n");
        fileWriter.close();

        Assertions.assertThrows(Exception.class, () -> {
            ShipImporter.importsShips(new File("shipsInvalidLine.csv"));
        });

    }

    /*
    @Test
    void testImportsShips() {
        try {
            ShipImporter.importsShips(new File(tempFile.toString()));
            List<Integer> list = new ArrayList<>();
            list.add(210950000);
            list.add(212180000);
            Assertions.assertEquals(list, shipStore.getShipListMmsi());
        } catch (FileNotFoundException | InvalidLineException e) {
            Assertions.fail();
        }
    }
     */

 /*   @Test
    void testImportsPositions() {
        try {
            tempFile = Files.createFile(tempDir.resolve("shipsPositionAddition.csv"));

            FileWriter fileWriter = new FileWriter(String.valueOf(tempFile));
            fileWriter.write("212170000,31/12/2020 21:08,26.20221,-84.95411,11.3,116.8,117,SAITA I,IMO9643544,5BBA4,70,228,32,14.4,NA,A\n");
            fileWriter.write("212170000,31/12/2020 22:08,26.20221,-84.95411,11.3,116.8,117,SAITA I,IMO9643544,5BBA4,70,228,32,14.4,NA,A\n");
            fileWriter.close();

            ShipImporter.importsShips(new File(tempFile.toString()));
            List<Position> list = new ArrayList<>();
            Position position1 = new Position(26.20221, -84.95411, 11.3, 116.8, 117, LocalDateTime.from(formatter.parse("31/12/2020 22:08")));
            Position position2 = new Position(26.20221, -84.95411, 11.3, 116.8, 117, LocalDateTime.from(formatter.parse("31/12/2020 21:08")));
            Position[] positions = {position1, position2};
            int i = 0;
            for (Position p : shipStore.findShip(212170000).getPosDate().getInOrderList()) {
                Assertions.assertEquals(0, p.compareTo(positions[i]));
                i++;
            }
        } catch (FileNotFoundException | InvalidLineException e) {
            Assertions.fail();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
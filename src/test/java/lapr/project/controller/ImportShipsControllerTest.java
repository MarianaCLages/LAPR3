package lapr.project.controller;

import lapr.project.model.ShipImporter;
import lapr.project.shared.exceptions.InvalidLineException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ImportShipsControllerTest {


    @Test
    void testController() {
        ImportShipsController controller = new ImportShipsController();
        assertNotNull(controller);
    }

    @TempDir
    static Path tempDir;
    static Path tempFile;

    @Test
    void importShipsTest() throws IOException, InvalidLineException {
        ImportShipsController controller = new ImportShipsController();
        try {
            controller.importShips("notValid.csv");
            fail();
        } catch (InvalidLineException | FileNotFoundException e) {

        }
        tempDir = Files.createTempDirectory("test");
        tempFile = Files.createFile(tempDir.resolve("ships.csv"));

        try{
            boolean actual = controller.importShips(String.valueOf(tempFile));
            assertFalse(actual);
        }catch (Exception e){

        }
        FileWriter fileWriter = new FileWriter(String.valueOf(tempFile));
        fileWriter.write("210950000,31/12/2020 17:19,42.97875,-66.97001,12.9,13.1,355,VARAMO,IMO9395044,C4SQ2,70,166,25,9.5,NA,B\n");
        fileWriter.write("210950000,31/12/2020 16:20,42.7698,-66.9759,13.3,3.7,356,VARAMO,IMO9395044,C4SQ2,70,166,25,9.5,NA,B\n");
        fileWriter.write("212180000,31/12/2020 21:07,24.20221,-84.85411,11.3,116.8,117,SAITA I,IMO9643544,5BBA4,70,228,32,14.4,NA,A\n");
        //fileWriter.write("212180000,31/12/2020 21:08,26.20221,-84.95411,11.3,116.8,117,SAITA I,IMO9643544,5BBA4,70,228,32,14.4,NA,A,8\n");

        fileWriter.close();

        assertTrue(controller.importShips(String.valueOf(tempFile)));
    }
}
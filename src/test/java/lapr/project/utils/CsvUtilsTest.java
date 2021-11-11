package lapr.project.utils;

import lapr.project.ui.CsvUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class CsvUtilsTest {
    @TempDir
    static Path tempDir;
    static Path tempFile;

    @BeforeAll
    public static void init() throws IOException {

        tempDir = Files.createTempDirectory("test");
        tempFile = Files.createFile(tempDir.resolve("test.csv"));


        FileWriter fileWriter = new FileWriter(tempFile.toFile());
        fileWriter.write("\n"); // readFile pass the first line
        fileWriter.write("54,85,68\n");
        fileWriter.write("51,86,67\n");
        fileWriter.close();
    }

    @Test
    void testRead() throws FileNotFoundException {
        String[] array = {"54", "85", "68"};
        String[] array2 = {"51", "86", "67"};
        ArrayList<String[]> list = new ArrayList<>();
        list.add(array);
        list.add(array2);

        List<String[]> resultString = CsvUtils.readFile(tempFile.toFile());

        for (int i = 0; i < list.size(); i++) {
            String[] s = resultString.iterator().next();
            String[] nextline = list.iterator().next();
            for (i = 0; i < nextline.length; i++) {
                Assertions.assertEquals(s[i], nextline[i]);
            }
        }
    }
}

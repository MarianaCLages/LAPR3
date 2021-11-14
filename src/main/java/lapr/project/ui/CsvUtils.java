package lapr.project.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CsvUtils {

    /**
     * Empty constructor.
     */
    private CsvUtils() {
        //empty
    }

    /**
     * Reads a file.
     *
     * @param path the file path
     * @return the file content as a list
     * @throws FileNotFoundException
     */
    public static List<String[]> readFile(File path) throws FileNotFoundException {
        List<String[]> lines = new ArrayList<>();
        Scanner sc = new Scanner(new File(String.valueOf(path)));
        sc.nextLine();
        do {
            lines.add(sc.nextLine().split(","));
        } while (sc.hasNextLine());
        sc.close();
        return lines;
    }
}

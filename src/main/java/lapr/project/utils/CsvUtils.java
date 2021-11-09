package lapr.project.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CsvUtils {

    private CsvUtils() {
        //empty
    }

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

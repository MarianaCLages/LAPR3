package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;

public class PortStaffUI implements Runnable {

    public void run() {

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Fill a matrix with each container's ID in its respective place", new Create3DMatrixUI()));
        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nPort Staff Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}

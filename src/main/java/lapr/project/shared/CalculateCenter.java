package lapr.project.shared;

import java.util.ArrayList;
import java.util.List;

public class CalculateCenter {

    /**
     * Constructor.
     */
    public CalculateCenter() {
        // Empty constructor
    }

    /**
     * Calculates the center of mass of a vessel.
     *
     * @param vesselType the vessel type
     * @return the center of mass of a vessel (in a string)
     */
    public List<Double> calculateCenter(int vesselType) {
        List<Double> doubleList = new ArrayList<>();
        double totalCenterX = 0;
        double totalCenterY = 0;
        double totalCenterZ = 0;

        if (vesselType == 71) {
            totalCenterX = (Constants.C1X71 * Constants.C1M71 + Constants.C2X71 * Constants.C2M71 + Constants.C3X71 * Constants.C3M71 + Constants.C4X71 * Constants.C4M71 + Constants.C5X71 * Constants.C5M71) / Constants.MTOTAL71;
            totalCenterY = (Constants.C1Y71 * Constants.C1M71 + Constants.C2Y71 * Constants.C2M71 + Constants.C3Y71 * Constants.C3M71 + Constants.C4Y71 * Constants.C4M71 + Constants.C5Y71 * Constants.C5M71) / Constants.MTOTAL71;
            totalCenterZ = (Constants.C1Z71 * Constants.C1M71 + Constants.C2Z71 * Constants.C2M71 + Constants.C3Z71 * Constants.C3M71 + Constants.C4Z71 * Constants.C4M71 + Constants.C5Z71 * Constants.C5M71) / Constants.MTOTAL71;
        } else if (vesselType == 72) {
            totalCenterX = (Constants.C1X72 * Constants.C1M72 + Constants.C2X72 * Constants.C2M72 + Constants.C3X72 * Constants.C3M72 + Constants.C4X72 * Constants.C4M72) / Constants.MTOTAL72;
            totalCenterY = (Constants.C1Y72 * Constants.C1M72 + Constants.C2Y72 * Constants.C2M72 + Constants.C3Y72 * Constants.C3M72 + Constants.C4Y72 * Constants.C4M72) / Constants.MTOTAL72;
            totalCenterZ = (Constants.C1Z72 * Constants.C1M72 + Constants.C2Z72 * Constants.C2M72 + Constants.C3Z72 * Constants.C3M72 + Constants.C4Z72 * Constants.C4M72) / Constants.MTOTAL72;

        } else {
            totalCenterX = (Constants.C1X74 * Constants.C1M74 + Constants.C2X74 * Constants.C2M74 + Constants.C3X74 * Constants.C3M74 + Constants.C4X74 * Constants.C4M74) / Constants.MTOTAL74;
            totalCenterY = (Constants.C1Y74 * Constants.C1M74 + Constants.C2Y74 * Constants.C2M74 + Constants.C3Y74 * Constants.C3M74 + Constants.C4Y74 * Constants.C4M74) / Constants.MTOTAL74;
            totalCenterZ = (Constants.C1Z74 * Constants.C1M74 + Constants.C2Z74 * Constants.C2M74 + Constants.C3Z74 * Constants.C3M74 + Constants.C4Z74 * Constants.C4M74) / Constants.MTOTAL74;
        }

        doubleList.add(totalCenterX);
        doubleList.add(totalCenterY);
        doubleList.add(totalCenterZ);

        return doubleList;
    }
}
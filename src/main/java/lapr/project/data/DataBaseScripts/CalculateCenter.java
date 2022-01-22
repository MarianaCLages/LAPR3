package lapr.project.data.DataBaseScripts;

import lapr.project.shared.Constants;

import java.text.DecimalFormat;

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
    public String calculateCenter(int vesselType) {
        StringBuilder sb = new StringBuilder();
        double totalCenterX = 0;
        double totalCenterY = 0;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        if (vesselType == 71) {
            totalCenterX = (Constants.C1X71 * Constants.C1M71 + Constants.C2X71 * Constants.C2M71 + Constants.C3X71 * Constants.C3M71 + Constants.C4X71 * Constants.C4M71 + Constants.C5X71 * Constants.C5M71) / Constants.MTOTAL71;
            totalCenterY = (Constants.C1Y71 * Constants.C1M71 + Constants.C2Y71 * Constants.C2M71 + Constants.C3Y71 * Constants.C3M71 + Constants.C4Y71 * Constants.C4M71 + Constants.C5Y71 * Constants.C5M71) / Constants.MTOTAL71;

        } else if (vesselType == 80) {
            totalCenterX = (Constants.C1X80 * Constants.C1M80 + Constants.C2X80 * Constants.C2M80 + Constants.C3X80 * Constants.C3M80) / Constants.MTOTAL80;
            totalCenterY = (Constants.C1Y80 * Constants.C1M80 + Constants.C2Y80 * Constants.C2M80 + Constants.C3Y80 * Constants.C3M80) / Constants.MTOTAL80;

        } else {
            totalCenterX = (Constants.C1X31 * Constants.C1M31 + Constants.C2X31 * Constants.C2M31 + Constants.C3X31 * Constants.C3M31 + Constants.C4X31 * Constants.C4M31) / Constants.MTOTAL31;
            totalCenterY = (Constants.C1Y31 * Constants.C1M31 + Constants.C2Y31 * Constants.C2M31 + Constants.C3Y31 * Constants.C3M31 + Constants.C4Y31 * Constants.C4M31) / Constants.MTOTAL31;
        }

        sb.append("Vessel Type:" + vesselType + " total center is (" + decimalFormat.format(totalCenterX) + ";" + decimalFormat.format(totalCenterY) + ")");

        return sb.toString();
    }
}
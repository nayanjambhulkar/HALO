/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package halo;

/**
 *
 * @author girisir
 */
public class DestinationIp {
    private static String destinationIp;
    private static String sendPath;

    /**
     * @return the sendPath
     */
    public static String getSendPath() {
        return sendPath;
    }

    /**
     * @param aSendPath the sendPath to set
     */
    public static void setSendPath(String aSendPath) {
        sendPath = aSendPath;
    }

    /**
     * @return the destinationIp
     */
    public String getDestinationIp() {
        return destinationIp;
    }

    /**
     * @param destinationIp the destinationIp to set
     */
    public void setDestinationIp(String destinationIp) {
        this.destinationIp = destinationIp;
    }
    
}

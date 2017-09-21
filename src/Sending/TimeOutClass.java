/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sending;

import java.net.Socket;

/**
 *
 * @author Admin111
 */
public class TimeOutClass {
    private static Socket socket=null;
    private static boolean timeout=true;

    /**
     * @return the socket
     */
    public static Socket getSocket() {
        return socket;
    }

    /**
     * @param aSocket the socket to set
     */
    public static void setSocket(Socket aSocket) {
        socket = aSocket;
    }

    /**
     * @return the timeout
     */
    public static boolean isTimeout() {
        return timeout;
    }

    /**
     * @param aTimeout the timeout to set
     */
    public static void setTimeout(boolean aTimeout) {
        timeout = aTimeout;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sending;

import halo.ConnectionDB;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Creazione-3 n Send file to client run first
 */
public class SimpleFileServer {

    public static int SOCKET_PORT = 8789;  // you may change this
    public String FILE_TO_SEND; // you may change this
    int i = 0;

    public boolean SendFile(String path, String ip) throws IOException {
        Socket socket = new Socket(ip, 15123);
        try {
            System.out.println("Accepted connection : " + socket);
            File f = new File(path);
            File file = new File(path);
            double bytes = file.length();
            double kilobytes = (bytes / 1024);
            double megabytes = (kilobytes / 1024);
            String inmb = "" + megabytes;
            String fileMb = inmb.substring(0, 6);
            String name = file.getName();
            ConnectionDB connectiondb = new ConnectionDB();
JOptionPane.showConfirmDialog(null, ip+" "+path);
            SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");//dd/MM/yyyy
            Date now = new Date();
            String strDate = sdfDate.format(now);
            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
            Date date = new Date();
            String time = dateFormat.format(date);

            boolean b = connectiondb.isSaveFileSendDetail(name, fileMb + "MB", strDate, time, ip, "Not Send");
            if (b) {
                System.out.println("True");
                
            } else {
                System.out.println("False");
            }

            // DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
            //Date date = new Date();
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            String ownip = InetAddress.getLocalHost().getHostName();

            String ipfile = ownip + "@" + name + "@" + time;
            dout.writeUTF(ipfile);

            File transferFile = new File(path);
            byte[] bytearray = new byte[(int) transferFile.length()];
            FileInputStream fin = new FileInputStream(transferFile);
            BufferedInputStream bin = new BufferedInputStream(fin);
            bin.read(bytearray, 0, bytearray.length);
            OutputStream os = socket.getOutputStream();
            System.out.println("Sending Files...");
            os.write(bytearray, 0, bytearray.length);
            os.flush();
            connectiondb.isUpdateStatus("SuccessFully Send", strDate, time);
        } catch (Exception er) {
            System.out.println("Exception Occure" + er);
            return false;
        } finally {
            socket.close();
        }
        System.out.println("File transfer complete");
        return true;
    }
}

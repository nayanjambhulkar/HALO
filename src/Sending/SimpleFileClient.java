/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sending;

import halo.ConnectionDB;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Creazione-3 Receive File from server
 */
public class SimpleFileClient {
     ServerSocket serverSocket=null;
    public void ReceiveFile() throws IOException, SQLException, ParseException {
        int filesize = 102238641;
        int bytesRead;
        int currentTot;
        try{
        Socket socket = null;
        serverSocket= new ServerSocket();
       // serverSocket.bind(new InetSocketAddress(15123));
        serverSocket = new ServerSocket(15123);
        serverSocket.setSoTimeout(10 * 1000);
        socket = serverSocket.accept();
        DataInputStream din = new DataInputStream(socket.getInputStream());
        String filename = din.readUTF();
        String originalfile[] = filename.split("@");
        byte[] bytearray = new byte[filesize];
        InputStream is = socket.getInputStream();
        new File("D:\\ReceivedFiles").mkdir();
        Sending.TimeOutClass.setTimeout(false);
        File copyFileName = new File("D:\\ReceivedFiles\\" + originalfile[1]);
        FileOutputStream fos = new FileOutputStream(copyFileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bytesRead = is.read(bytearray, 0, bytearray.length);
        currentTot = bytesRead;
        do {
            bytesRead =
                    is.read(bytearray, currentTot, (bytearray.length - currentTot));
            if (bytesRead >= 0) {
                currentTot += bytesRead;
            }
        } while (bytesRead > -1);
        bos.write(bytearray, 0, currentTot);
        bos.flush();
        bos.close();
        socket.close();


        double bytes = copyFileName.length();
        double kilobytes = (bytes / 1024);
        double megabytes = (kilobytes / 1024);
        String inmb = "" + megabytes;
        String filesizeinMB = inmb.substring(0, 6);
        String name = copyFileName.getName();
        ConnectionDB connectiondb = new ConnectionDB();

        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
        Date date = new Date();
        String time = dateFormat.format(date);
        String sendTime=originalfile[2];
        
        java.text.DateFormat df = new java.text.SimpleDateFormat("hh:mm:ss");
        java.util.Date date1 = df.parse(sendTime);
        java.util.Date date2 = df.parse(time);
        long milliseconds = date1.getTime()-date2.getTime();
        long requerSeconds;
        
        requerSeconds=(milliseconds/1000);
        System.out.println(requerSeconds);
                
        
        
        
        
        
        
        boolean b = connectiondb.isSaveFileReceiveDetail(name, filesizeinMB + " MB", strDate, time, originalfile[0],""+requerSeconds);
        if (b) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
        }finally
        {
           // serverSocket.bind(12124);
        }
    }
}

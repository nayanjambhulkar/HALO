package halo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConnectionDB {

    Connection con;
    static Statement stmt;
    static Vector al[] = null;
    public static ResultSet sr;
    ArrayList time = null;
    ArrayList filesize = null;
    ArrayList node = null;
    ArrayList path = null;
    Vector allnode;

    public ConnectionDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/halo", "root", "root");
            stmt = con.createStatement();
            System.out.println("Database connection established");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server" + e);
        }
    }
//This method use for store filesending deatails in database

    public ArrayList getAllTime() throws SQLException {

        time = new ArrayList();
        sr = stmt.executeQuery("select RequerTime from ReceiveHistory");
        while (sr.next()) {
            time.add(sr.getString(1));
        }
        return time;
    }

    public ArrayList getAllBandwidth() throws SQLException {

        time = new ArrayList();
        sr = stmt.executeQuery("select BandWidth from BandWidthTable");
        while (sr.next()) {
            time.add(sr.getString(1));
        }
        return time;
    }

    public Vector getAllNode() throws SQLException {

        allnode = new Vector();
        sr = stmt.executeQuery("select node from NodePath");
        while (sr.next()) {
            allnode.add(sr.getString(1));
        }
        return allnode;
    }

    public ArrayList getAllFileSize() throws SQLException {

        filesize = new ArrayList();
        sr = stmt.executeQuery("select FileSize from ReceiveHistory");
        while (sr.next()) {
            filesize.add(sr.getString(1));
        }
        return filesize;
    }

    public int getSendFileId() throws Exception {
        int count = 0;
        PreparedStatement stmt = con.prepareStatement("select max(fid) from senderhistory");
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        ++count;
        return count;
    }

    public boolean isSaveFileSendDetail(String fname, String FileSize, String sdate, String stime, String destinationIP, String sendingStatus) throws SQLException {
        boolean status = false;
        try {
            int id = this.getSendFileId();
            PreparedStatement stmt = con.prepareStatement("insert into senderhistory values(?,?,?,?,?,?,?)");
            stmt.setInt(1, id);
            stmt.setString(2, fname);
            stmt.setString(3, FileSize);
            stmt.setString(4, sdate);
            stmt.setString(5, stime);
            stmt.setString(6, destinationIP);
            stmt.setString(7, sendingStatus);
            int val = stmt.executeUpdate();
            if (val > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception er) {
            JOptionPane.showMessageDialog(null, "Error is " + er);
        }
        return status;
    }

    public int getReceiverId() throws Exception {
        int rid = 0;
        PreparedStatement stmt = con.prepareStatement("select max(fid) from receivehistory");
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            rid = rs.getInt(1);
        }
        return ++rid;
    }

    public boolean isSaveFileReceiveDetail(String fname, String FileSize, String sdate, String stime, String senderip, String time) throws SQLException {
        boolean status = false;
        String spath = "";

        try {

            /* String query = "insert into ReceiveHistory values(0,'" + fname + "','" + FileSize + "','" + sdate + "','" + stime + "','" + senderip + "','" + time + "')";
            int result = stmt.executeUpdate(query);
            if (result > 0) {
                return status = true;
            }*/
            DestinationIp d = new DestinationIp();
            CalculateShortest csp = new CalculateShortest();
            Vector cost = csp.calculateCost();
            PossiblePath ps = new PossiblePath();
            try {
                Vector ar[] = ps.possibleRoot();
                for (int i = 0, j = 0; i < ar.length; i++, j++) {
                    System.out.println("Path ->[" + i + "]" + ar[i]);
                    ar[i].add(0, InetAddress.getLocalHost().getHostName());
                    ar[i].add(ar[i].size(), d.getDestinationIp());
                    //shortestPath.append(ar[i].toString() + "\n");
                    spath = ar[i].toString();
                }

            } catch (UnknownHostException ex) {
                Logger.getLogger(ShortestPathRule.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(PossiblePath.class.getName()).log(Level.SEVERE, null, ex);
            }

            int rid = this.getReceiverId();
            PreparedStatement stmt = con.prepareStatement("insert into receivehistory values(?,?,?,?,?,?,?,?)");
            stmt.setInt(1, rid);
            stmt.setString(2, fname);
            stmt.setString(3, FileSize);
            stmt.setString(4, sdate);
            stmt.setString(5, stime);
            stmt.setString(6, senderip);
            stmt.setString(7, time);
            stmt.setString(8, spath);
            int value = stmt.executeUpdate();
            if (value > 0) {
                return status = true;
            }
        } catch (Exception er) {
            System.out.println("-->" + er);
            JOptionPane.showMessageDialog(null, "Error is" + er);
        }
        return status;
    }

    public boolean isUpdateStatus(String status, String sdate, String stime) {
        try {

            String query = "update SenderHistory SET status='" + status + "' where Date='" + sdate + "' and Time='" + stime + "'";
            int result = stmt.executeUpdate(query);

            if (result > 0) {
                return true;
            }
        } catch (SQLException er) {
            System.out.println("Some Error->" + er);
            return false;
        }
        return false;
    }

    public static void main(String[] args) throws SQLException, Exception {
        ConnectionDB con = new ConnectionDB();
        con.IntermediatePC();
    }

// This Funtion Return File Details
    public static Vector[] getfilesenddetails() throws Exception {
        int count = 0;

        sr = stmt.executeQuery("select * from SenderHistory");
        while (sr.next()) {
            ++count;

        }
        al = new Vector[count];
        count = 0;
        sr = stmt.executeQuery("select * from SenderHistory");
        while (sr.next()) {
            al[count] = new Vector();
            al[count].add(sr.getInt(1));
            al[count].add(sr.getString(2));
            al[count].add(sr.getString(3));
            al[count].add(sr.getString(4));
            al[count].add(sr.getString(5));
            al[count].add(sr.getString(6));
            al[count].add(sr.getString(7));

            ++count;

        }

        return al;
    }

    public Vector[] IntermediatePC() throws Exception {
        int count = 0;
        try {
            node = new ArrayList();
            path = new ArrayList();
            sr = stmt.executeQuery("select * from BandWidthTable");
            while (sr.next()) {
                ++count;
                String nd = sr.getString(1).toString();
                node.add(nd);
                String pt = sr.getString(2).toString();
                path.add(pt);
                System.out.println(pt);
                System.out.println(nd);

            }
            al = new Vector[count];
            int size = 0;
            System.out.println("path=" + path.size() + "Node=" + node.size());
            for (int i = 0; i < node.size(); i++) {
                al[size] = new Vector();
                al[size].add(node.get(i).toString());
                al[size].add(path.get(i).toString());

                ++size;

            }
            System.out.println(al.length);

            return al;
        } catch (Exception er) {
            System.out.println(er);
        }
        return al;
    }

    // This Funtion Return FileReceive  Details
    public static Vector[] getFileReceiveDetails() throws Exception {
        int count = 0;

        sr = stmt.executeQuery("select * from ReceiveHistory");
        while (sr.next()) {
            ++count;

        }

        al = new Vector[count];
        count = 0;
        sr = stmt.executeQuery("select * from ReceiveHistory");
        while (sr.next()) {
            al[count] = new Vector();
            al[count].add(sr.getInt(1));
            al[count].add(sr.getString(2));
            al[count].add(sr.getString(3));
            al[count].add(sr.getString(4));
            al[count].add(sr.getString(5));
            al[count].add(sr.getString(6));

            al[count].add(sr.getString(8));

            ++count;

        }

        return al;
    }

    public String getPath() throws Exception {
        PreparedStatement stmt = con.prepareStatement("select path from nodepath");
        ResultSet rs = stmt.executeQuery();
        String data = "";
        while (rs.next()) {
            data = rs.getString(1);
        }
        return data;

    }
}

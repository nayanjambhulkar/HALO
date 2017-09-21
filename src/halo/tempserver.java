package halo;

//import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;

public class tempserver extends tempclient {

    private int timeout;
    ResultSet rs = null;
    Statement st = null;
    //SetTimer hop2Timer=null;
    int timercnt = 0;
    int ackcnt = 0;
    int cnt = 0;
    static int msgCount = 0;
    SetTimer hop2Timer = null;
    String desti;
    String filename = null;

    tempserver() {
        timeout = 0;
    }

    protected int checkTimeout() {
        return (this.timeout);
    }

    protected boolean checkDestination(String str1, String str2) {
        if (str1.equalsIgnoreCase(str2)) {
            return true;
        } else {
            return false;
        }
    }

    protected String getIP(String nodename) throws UnknownHostException {

        String[] temp = null;
        String strIP = null;
        InetAddress localaddr = InetAddress.getByName(nodename);
        strIP = strIP + localaddr;
        temp = strIP.split("/");
        return temp[1];

    }

    protected String getSelfName(String tstr) {
        String[] temp = null;
        temp = tstr.split("/");
        //System.out.println("temp[0] = "+temp4[0]  +"temp4[1] = "+temp4[1]);
        temp[0] = temp[0].substring(4);
        System.out.println("temp[0] now = " + temp[0]);
        return temp[0];

    }

    protected void forwardMsg(tempclient tempclientobj2, String tdestname) {
        try {
            String[] temp2 = null;
            String pathstr = null;
            new ConnectionDB();
            st = ConnectionDB.stmt;
            rs = st.executeQuery("select path from nodepath where node= '" + tdestname + "' ");//tdestname stores the dest node name
            this.desti = tdestname;
            while (rs.next()) {
                pathstr = rs.getString(1);
                temp2 = pathstr.split("#");
            }
            if (timercnt == 0) {

                if (temp2.length >= 2) {
                    hop2Timer = new SetTimer();
                    hop2Timer.starttimer();
                }
                timercnt = 5;
            }

            System.out.println("!!!!!!!!IN SERVER temp[2] = " + temp2[0]);//temp2[0] shud contain next neighbour name
            if (hop2Timer != null) {
                hop2Timer.incrValue();
                timercnt--;
            }
            new Message_ACK("Message Forwarded");
            tempclientobj2.communicate(tempclientobj2.getMsg(), getIP(temp2[0]));
        } catch (SQLException se) {
            System.out.println("Exception generated :" + se);
        } catch (UnknownHostException uhe) {
            System.out.println("Exception generated :" + uhe);
        } catch (IOException ioe) {
            System.out.println("Exception generated :" + ioe);
        }
    }

    protected void sendAck() {

    }

    protected void tserver() {

        //	new ConnectionDB();
        tempclient tempclientobj2 = new tempclient();
        String[] temp = null;
        String[] temp2 = null;
        String msg = null;
        String pathstr = null;
        String destname = null;
        String str = null;
        InetAddress localaddr = null;

        try {
            System.out.println("In server!!");
            ServerSocket socket = new ServerSocket(8765);
            while (true) {

                Socket insocket = socket.accept();
                ObjectInputStream oio = new ObjectInputStream(insocket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(insocket.getOutputStream());

                System.out.println("IN SERVER!!!!!!!!");

                tempclientobj2 = (tempclient) oio.readObject();
                //tempclientobj2.ACK = (String)oio.readObject();
                if ((tempclientobj2.getAck()) != null) {
                    MsgTransfer mt = new MsgTransfer();
                    if (mt.stTimer != null) {
                        mt.stTimer.updatevalue();
                    } else {
                        hop2Timer.updatevalue();
                    }

                    GetPC ackmn = new GetPC("Hello");
                    ackcnt++;
                    ackmn.AckReceivedst.setText("ACK Received " + ackcnt);

                    if (ackcnt == 5) {
                        ackcnt = 0;
                    }

                    System.out.println("The  SOURCE  NODE HAS RECIEVED THE ACKNOLEDGEMENT:" + tempclientobj2.getAck());
                } else {
                    temp = (tempclientobj2.getMsg()).split("#");
                    System.out.println("The msg recieved in server " + insocket.getLocalAddress().getLocalHost() + " is:" + temp[0]);
                    msg = temp[0];

                    //System.out.println(
                    this.filename = temp[1].substring(temp[1].lastIndexOf("\\") + 1, temp[1].length());
                    File recfile = new File("D:\\Received\\" + filename);
                    FileWriter f = new FileWriter(recfile);
                    f.write(temp[0]);
                    f.write(temp[1]);
                    f.close();
                    System.out.println("NAm------>" + temp[1]);

                    destname = temp[1];
                    //temp1 is storing the comp name of the destination

                    tempclientobj2.incremntHop1();
                    tempclientobj2.incremntHop2();
                    System.out.println("IN SERVER TEMPCLIENTOBJ2.HOP1=" + tempclientobj2.getHop1());

                    //st = ConnectionDB.stmt;
                    //rs = st.executeQuery("select path from NodePath where node= '" + temp[1] +"' ");//temp[1] stores the dest node name
                    System.out.println("!!!!!!!IN SERVER insocket.getLocalAddress().getLocalHost() = " + insocket.getLocalAddress().getLocalHost());
                    str = null;
                    str = str + (insocket.getLocalAddress().getLocalHost());
                    if (tempclientobj2.getSource2() == null) {
                        tempclientobj2.setSource2(getIP(getSelfName(str)));
                    }

                    //destname contains the name of the destination node which was sent in the message
                    /*if( ( ((tempclientobj2.getHop1())-1) == 1 ) && (checkDestination(destname,getSelfName(str))) )//if on same node
				{
					System.out.println("Message has reached its DESTINATION!");
					String ackstr = "The server" + insocket.getLocalAddress() + " got this: "+ msg +"#"+tempclientobj2.getSource1();
					oos.writeObject(ackstr);
					new message(msg);
				}
				//if(tempclientobj2.getHop1()>tempclientobj2.getHop2())
				else*/
                    if (((tempclientobj2.getHop1()) == 3) && (checkDestination(destname, getSelfName(str)))) {
                        System.out.println("Message has reached its DESTINATION!");
                        String ackstr = "The server" + insocket.getLocalAddress() + " got this: " + msg + "#" + tempclientobj2.getSource1();
                        oos.writeObject(ackstr);//sending ack
                        this.msgCount++;
                        if (this.msgCount == 5) {
                            this.msgCount = 0;
                            new message(msg);
                            String sen = tempclientobj2.getSender();
                            JOptionPane.showMessageDialog(null, "Message Comming From : \n" + sen);
                        }
                    } //instring contains original msg and dest IP
                    else if ((tempclientobj2.getHop1()) == 3 && !(checkDestination(destname, getSelfName(str))))//ie it is not the destination 
                    {

                        String ackstr = "The server" + insocket.getLocalAddress() + " got this: " + msg + "#" + tempclientobj2.getSource1();
                        oos.writeObject(ackstr);//sending ack
                        tempclientobj2.setHop1(1);
                        tempclientobj2.setSource1(getIP(getSelfName(str)));
                        GetPC mn = new GetPC("Hello");
                        if (mn.MisbhvRBtn1.isSelected() == false) {
                            forwardMsg(tempclientobj2, destname);
                            cnt++;
                            mn.msgForwordst.setText("Forwored message " + cnt);
                            if (cnt == 5) {
                                cnt = 0;
                            }
                        }

                    } else if ((tempclientobj2.getHop1() == 2) && !(checkDestination(destname, getSelfName(str))) && (tempclientobj2.getHop2() != 3)) //ie hop1 = 2 then just forward the msg
                    {
                        GetPC mn = new GetPC("Hello");
                        if (mn.MisbhvRBtn1.isSelected() == false) {
                            forwardMsg(tempclientobj2, destname);
                            cnt++;
                            mn.msgForwordst.setText("Forwored message " + cnt);
                            if (cnt == 5) {
                                cnt = 0;
                            }
                            oos.writeObject(null);
                        }
                    }
                    //*****************************code for hop2*********************************************//
                    if (((tempclientobj2.getHop2()) == 3) && (checkDestination(destname, getSelfName(str)))) {
                        System.out.println("Message has reached its DESTINATION!");
                        String ackstr = "The server" + insocket.getLocalAddress() + " got this: " + msg + "#" + tempclientobj2.getSource2();
                        oos.writeObject(ackstr);//sending ack
                        this.msgCount++;
                        if (this.msgCount == 5) {
                            this.msgCount = 0;
                            new message(msg);
                            String sen = tempclientobj2.getSender();
                            JOptionPane.showMessageDialog(null, "Message Comming From : \n" + sen);
                        }
                    } //instring contains original msg and dest IP
                    else if ((tempclientobj2.getHop2()) == 3 && !(checkDestination(destname, getSelfName(str))))//ie it is not the destination 
                    {
                        String ackstr = "The server" + insocket.getLocalAddress() + " got this: " + msg + "#" + tempclientobj2.getSource2();
                        oos.writeObject(ackstr);//sending ack
                        tempclientobj2.setHop2(1);
                        tempclientobj2.setSource2(getIP(getSelfName(str)));
                        GetPC mn = new GetPC("Hello");
                        if (mn.MisbhvRBtn1.isSelected() == false) {
                            forwardMsg(tempclientobj2, destname);
                            cnt++;
                            mn.msgForwordst.setText("Forwored message " + cnt);
                            if (cnt == 5) {
                                cnt = 0;
                            }
                        }
                    }

                }
                insocket.close();
                oio.close();
                oos.close();

            }

        } catch (Exception e) {
            System.out.println("IN SERVER Exception generated is :" + e);
        }
    }

    public static void main(String[] args) {
        new tempserver().tserver();
    }

}

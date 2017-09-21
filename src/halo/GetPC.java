package halo;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
import java.sql.*;
import java.awt.Graphics;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetPC extends JFrame implements ActionListener {

    Statement st;
    //static visualPanel disp = null;
    static JRadioButton MisbhvRBtn1, RecBtn;
    static JLabel msgForwordst = null;
    static JLabel AckReceivedst = null;

    public GetPC(String str) {
        System.out.println("In ManetMainFile's parameterised constructor!");
    }

    public GetPC() {

        super();
        setTitle("Multi Hop Adaptive Link State Optimal Routing In WANet");
        setSize(900, 600);
        int i = 0;
        Vector v = null;
        new ConnectionDB();

        RouteRequest root = new RouteRequest();
        v = root.getNode();
        System.out.println("\n" + v);

        v = root.getPath(v);
        root.setPath(v);
        try {

            st = ConnectionDB.stmt;

            ResultSet rs = st.executeQuery("Select count(node) from NodePath");
            //String rt="";
            if (rs.next()) {
                i = rs.getInt(1);
            }

        } catch (Exception e) {

        }

        try {

            initializeComponent(i);
        } catch (Exception ere) {
        }
        //
        // TODO: Add any constructor code after initializeComponent call
        //
        this.setResizable(false);
        this.setBackground(Color.white);
        this.setLocation(437, 100);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void initializeComponent(int i) throws UnknownHostException {

        setLayout(null);
        JPanel p1, p3, p2, pathpanel;
        JLabel l1, l2, l3, l4, l5, l6;
        String str[];
        JTable t1;
        ImageIcon linki0, linki1;
        int l = 0;
        JLabel[] lbl = new JLabel[60];
        int xpos[], ypos[];
        xpos = new int[40];
        ypos = new int[40];
        JRadioButton MisbhvRBtn, ReceiveFile;

        new ConnectionDB();
        str = new String[i];

        InetAddress iaddr = null;
        String selfnode = null;
        try {
            iaddr = InetAddress.getLocalHost();
            selfnode = iaddr.getHostName();
            System.out.println("name = " + selfnode);
        } catch (UnknownHostException eee) {
        }

        try {
            st = ConnectionDB.stmt;
            ResultSet rs = st.executeQuery("Select * from NodePath");
            //String rt="";
            while (rs.next()) {
                str[l] = rs.getString(1).trim();
                //	xpos[l]=Integer.parseInt(rs.getString(2));
                //	ypos[l]=Integer.parseInt(rs.getString(3));
                System.out.println(str[l] + " " + l);
                l++;
            }
        } catch (Exception e) {

        }

        Font f1;
        f1 = new Font("Times New Roman", Font.BOLD, 25);
        p1 = new JPanel();
        p1.setBackground(new Color(204, 204, 255));
        p1.setBounds(0, 0, 250, 70);
        l1 = new JLabel();
        l1.setForeground(Color.RED);
        l1.setFont(f1);
        l1.setBounds(80, 20, 100, 50);
        p1.add(l1);
        p1.setLayout(null);

        p2 = new JPanel();
        p2.setBackground(new Color(204, 204, 255));
        p2.setBounds(250, 0, 400, 490);
        add(p2);

        t1 = new JTable(i, 1);
        t1.setBounds(0, 103, 240, 200);
        t1.setBackground(Color.white);
        t1.setRowHeight(53);

        JButton[] b = new JButton[40];
        System.out.println(i);
        linki0 = new ImageIcon("next.GIF");

        int k = 5;
        for (int j = 0; j < i; j++) {

            b[j] = new JButton(str[j]);
            lbl[j] = new JLabel(linki0, 0);
            b[j].add(lbl[j]);

            b[j].setBackground(Color.white);
            b[j].setBounds(0, k, 245, 50);
            t1.add(b[j], j + 1);

            System.out.println("----------> " + (j + 1));

            if (selfnode.equalsIgnoreCase(str[j])) {
                System.out.println("match");
            } else {
                b[j].addActionListener(this);
                b[j].setActionCommand(str[j]);

            }
            k = k + 52;

        }
        p1.setLayout(null);
        add(p1);
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        JScrollPane sp = new JScrollPane(t1, v, h);
        sp.setBounds(0, 72, 250, 350);
        this.add(sp);
        this.setLayout(null);

        p3 = new JPanel();
        p3.setBackground(new Color(204, 204, 255));
        p3.setBounds(0, 424, 250, 60);
        add(p3);

        MisbhvRBtn = new JRadioButton("Misbehave");
        MisbhvRBtn.setBounds(5, 10, 90, 30);
        MisbhvRBtn.setBackground(new Color(204, 204, 255));
        this.MisbhvRBtn1 = MisbhvRBtn;
        Boolean ans = MisbhvRBtn.isSelected();

        System.out.println("+++++++++++" + ans);

        JSeparator seperate = new JSeparator(1);
        seperate.setBounds(100, 0, 10, 100);
        p3.add(seperate);

        JLabel msgforword = new JLabel(" ");
        msgforword.setBounds(110, 0, 120, 20);
        p3.add(msgforword);
        this.msgForwordst = msgforword;

        JLabel ackreceived = new JLabel(" ");
        ackreceived.setBounds(110, 16, 120, 30);
        p3.add(ackreceived);
        this.AckReceivedst = ackreceived;
        //MisbehaveRBtn.setBackground(Color.blue);
        p3.add(MisbhvRBtn);
        p3.setLayout(null);

    }

    public static void main(String args[]) {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        new GetPC();
    }

    public void actionPerformed(ActionEvent ae) {
        String str1 = ae.getActionCommand();
        JFrame parent = new JFrame();
        DestinationIp d = new DestinationIp();
        d.setDestinationIp(str1);
        System.out.println("Destination Up-->" + d.getDestinationIp());
        JOptionPane.showMessageDialog(parent, str1);
        if (!(str1.equals("Receive file")) && str1 != null) {

            System.out.println(str1);

        }
        if (str1.equals("Receive file")) {
            try {
                ConnectionDB conn = new ConnectionDB();

                Sending.SimpleFileClient receivefile = new Sending.SimpleFileClient();
                try {
                    try {
                        receivefile.ReceiveFile();
                    } catch (ParseException ex) {
                        Logger.getLogger(GetPC.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(GetPC.class.getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(parent, "Printing complete");
            } catch (UnknownHostException ex) {
                Logger.getLogger(GetPC.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GetPC.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        String str[] = new String[25];
        String[] strpath = new String[25];
        String neighbourPath[] = null;
        int l = 0;
        new ConnectionDB();

        this.AckReceivedst.setText(" ");
        this.msgForwordst.setText(" ");

        try {

            st = ConnectionDB.stmt;

            ResultSet rs = st.executeQuery("Select * from NodePath");

            while (rs.next()) {
                str[l] = rs.getString(1);
                strpath[l] = rs.getString(2);
                System.out.println(str[l] + " " + l);
                l++;
            }

        } catch (Exception e) {
        }

        for (int i = 0; i < 25; i++) {
            if (str1.equals(str[i])) {
                neighbourPath = strpath[i].split("#");
                new message(str[i], neighbourPath[0]);//str[i] contains the destination IP
            }
        }

    }

}

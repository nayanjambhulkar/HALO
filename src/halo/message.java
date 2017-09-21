package halo;

/**
 * @(#)manet.java
 *
 *
 * @author swapnil shirolkar
 * @version 1.00 2009/1/30
 */
import Sending.SimpleFileServer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class message extends JFrame implements ActionListener {

    JFileChooser fileChooser = new JFileChooser();
    Container contentPane = getContentPane();
    static String dip;
    String TargetIP;
    JPanel p1, p2, p3, p4;
    JLabel l1;
    TextArea textArea1;
    TextField textField1;
    JButton b1, b2, b3, b4;
    String destIP = null;
    String neighbourIP = null;
    String path;

    message(String dispmsg) {
        setTitle("HALO Message Window");
        setSize(240, 290);
        initializeComponent(dispmsg);
        this.setResizable(false);
        this.setLocation(200, 150);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public message(String instring, String neighbour) {

        super();
        setTitle("File Send Portal");
        setSize(240, 290);
        initializeComponent(instring);
        destIP = instring;
        neighbourIP = neighbour;
        dip = destIP;
        this.setResizable(false);
        this.setLocation(200, 150);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void initializeComponent(String msg) {
        setLayout(null);

        p1 = new JPanel();
        p1.setBackground(new Color(204, 204, 255));
        p1.setBounds(0, 0, 235, 50);
        add(p1);

        l1 = new JLabel("File Sending Portal");
        l1.setBounds(80, 5, 100, 50);
        Font f1;
        f1 = new Font("Times New Roman", Font.BOLD, 22);
        l1.setForeground(Color.red);
        l1.setFont(f1);
        p1.add(l1);
        p1.setLayout(null);

        p2 = new JPanel();
        p2.setBackground(new Color(204, 204, 255));
        p2.setBounds(0, 50, 235, 20);
        add(p2);

        b1 = new JButton(" Send File ");
        b1.setBounds(110, 0, 100, 19);
        p2.add(b1);
        p2.setLayout(null);
        b1.addActionListener(this);
        b1.setActionCommand("sendfile");

        p3 = new JPanel();
        p3.setBackground(new Color(204, 204, 255));
        p3.setBounds(0, 70, 235, 150);
        add(p3);

        textArea1 = new TextArea();
        textArea1.setBounds(0, 0, 231, 149);
        //-------------- msg=selected pc name
        textArea1.setText(msg);
        try {
            InetAddress.getByName(msg);
            TargetIP = (InetAddress.getByName(msg)).toString();
            //textArea1.setText((InetAddress.getByName(msg)).toString());
        } catch (UnknownHostException er) {
        }
        p3.add(textArea1);
        p3.setLayout(null);

        p4 = new JPanel();
        p4.setBackground(new Color(204, 204, 255));
        p4.setBounds(0, 220, 235, 40);
        add(p4);

        textField1 = new TextField();
        textField1.setBounds(3, 6, 150, 20);
        textField1.setEditable(false);
        textField1.setText("good");
        //p4.add(textField1);

        b2 = new JButton("Send Text File");
        b2.setBounds(120, 6, 100, 20);
       // p4.add(b2);
        p4.setLayout(null);
        b2.addActionListener(this);
        b2.setActionCommand("sendmsg");

        b4 = new JButton("Send Other Files");
        b4.setBounds(0, 5, 100, 20);
       // p4.add(b4);
        p4.setLayout(null);
        b4.addActionListener(this);
        b4.setActionCommand("sendanyfile");

    }

    public static void main(String args[]) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JDialog.setDefaultLookAndFeelDecorated(true);

        new message("", "");
    }

    public void actionPerformed(ActionEvent e) {
        String str1 = e.getActionCommand();

        if (str1.equals("sendmsg")) {
            JOptionPane.showMessageDialog(this, "Good msg");

            String msg = null;
            //	textArea1.setText("dfsafsdf");
            msg = textArea1.getText();
            msg = msg + "#" + destIP;//adding the destination adderess
            File file = new File(path);
            double bytes = file.length();
            double kilobytes = (bytes / 1024);
            double megabytes = (kilobytes / 1024);
            String inmb = "" + megabytes;
            String fileMb = inmb.substring(0, 6);
            String name = file.getName();
            ConnectionDB connectiondb = new ConnectionDB();
            try {
                SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");//dd/MM/yyyy
                Date now = new Date();
                String strDate = sdfDate.format(now);
                DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
                Date date = new Date();
                String time = dateFormat.format(date);
                boolean b = connectiondb.isSaveFileSendDetail(name, fileMb, strDate, time, destIP, "Successfully Send");
                if (b) {
                    System.out.println("True");
                } else {
                    System.out.println("False");
                }
            } catch (SQLException ex) {
                Logger.getLogger(message.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (msg != null) {
                try {
                    JOptionPane.showMessageDialog(this, "Good msg");

                    new Message_ACK("Message Forwarded");
                    System.out.println("msg in message:" + msg);
                    MsgTransfer node = new MsgTransfer();
                    node.sendMessage(msg, neighbourIP);
                } catch (Exception er) {
                }
            }
            //String msg="";
            //msg=textField1.getText();
            //textArea1.setText(msg);
        }

        if (str1.equals("sendanyfile")) {

            DestinationIp.setSendPath(path);

            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(ShortestPathRule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(ShortestPathRule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(ShortestPathRule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(ShortestPathRule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>

            /*
         * Create and display the form
             */
            java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                    new ShortestPathRule().setVisible(true);
                }
            });

        }
        if (str1.equals("sendfile")) {

            int retval = fileChooser.showOpenDialog(message.this);
            if (retval == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this, "Good " + file.toString());

                textField1.setText(file.getAbsolutePath());// getName());
                this.textArea1.setText(file.getAbsolutePath());
                String delimeter = "\\\\";    // "\\."	
                String str = textField1.getText();
                String s[] = str.split(delimeter);
                int cnt = s.length;

                String path = "";
                for (int i = 0; i < cnt - 1; i++) {
                    path += s[i] + "\\\\";
                }
                path += s[cnt - 1];
                this.path = path;

                try {
                    SimpleFileServer sv = new SimpleFileServer();
                    sv.SendFile(path, destIP);

                    new Message_ACK("Message Forwarded");
                    // System.out.println("msg in message:" + msg);
                    MsgTransfer node = new MsgTransfer();
                    node.sendMessage("Ack", neighbourIP);

                    /*
                     * String var = null; String content = "";
                     *
                     * FileReader fr = new FileReader(path); BufferedReader br =
                     * new BufferedReader(fr); while ((var = br.readLine()) !=
                     * null) { content += var; content += "\n"; }
                     * textArea1.setText(content + "#fn" + new
                     * File(path).getPath());
                     */
                } catch (Exception er) {
                    System.out.println(er);
                }

            }

        }
    }
}

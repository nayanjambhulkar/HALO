/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package halo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin111
 */
public class ShortestPathRule extends javax.swing.JFrame {

    /**
     * Creates new form ShortestPathRule
     */
    public ShortestPathRule() {
        initComponents();
        cost.setVisible(false);
//        finalCost.setVisible(false);
        jButton2.setVisible(false);
        jButton1.setVisible(false);
        jScrollPane1.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cost = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        shortestPath = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        cost.setAutoCreateRowSorter(true);
        cost.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cost.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cost.setForeground(new java.awt.Color(102, 102, 255));
        cost.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Number", "PCName", "Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        cost.setAlignmentX(20.0F);
        cost.setAlignmentY(20.0F);
        cost.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cost.setEditingColumn(5);
        cost.setEditingRow(5);
        cost.setEnabled(false);
        cost.setRowHeight(50);
        cost.setRowMargin(5);
        cost.setSelectionForeground(new java.awt.Color(204, 204, 255));
        cost.setSurrendersFocusOnKeystroke(true);
        cost.getTableHeader().setResizingAllowed(false);
        jScrollPane1.setViewportView(cost);
        cost.getAccessibleContext().setAccessibleParent(this);

        shortestPath.setEditable(false);
        shortestPath.setBackground(new java.awt.Color(204, 204, 204));
        shortestPath.setColumns(20);
        shortestPath.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        shortestPath.setRows(20);
        shortestPath.setTabSize(10);
        shortestPath.setSelectedTextColor(new java.awt.Color(255, 204, 204));
        jScrollPane2.setViewportView(shortestPath);

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 51, 0));
        jLabel2.setText("Possible Paths Is ");
        jLabel2.setToolTipText("");
        jLabel2.setEnabled(false);

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 51, 255));
        jButton1.setText("Send Using Hallo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 204, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 51, 255));
        jButton2.setText("Send Using Hallo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(841, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(70, 70, 70)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(184, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(493, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(872, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(527, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(558, 558, 558))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            // TODO add your handling code here:




            ConnectionDB connectiondb = new ConnectionDB();
            DefaultTableModel df = (DefaultTableModel) cost.getModel();
            cost.removeAll();
            DestinationIp d = new DestinationIp();
            CalculateShortest csp = new CalculateShortest();
            Vector cost = csp.calculateCost();
            PossiblePath ps = new PossiblePath();
            try {
                Vector ar[] = ps.possibleRoot();
                for (int i = 0, j = 0; i < (ar.length-1); i++, j++) {
                    System.out.println("Path ->[" + i + "]" + ar[i]);
                    ar[i].add(0, InetAddress.getLocalHost().getHostName());
                    ar[i].add(ar[i].size(),message.dip);
                    shortestPath.append(ar[i].toString() + "\n");

                }



            } catch (UnknownHostException ex) {
                Logger.getLogger(ShortestPathRule.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(PossiblePath.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                java.util.Vector al[] = connectiondb.IntermediatePC();
                System.out.println(al.length);
                for (int i = 0, j = 0; i < al.length; i++, j++) {
                    al[j].add(cost.get(i));
                    df.addRow(al[i]);
                }
                CalculateShortest cs = new CalculateShortest();
                ArrayList ar[] = cs.getCost();
                int add = 0;
                for (int i = 0; i < ar.length; i++) {
                    for (int j = 0; j < ar[i].size() - 1; j++) {
                        System.out.println(ar[j]);

                        add = Integer.parseInt(ar[i].get(j).toString());
                    }
                   // finalCost.append(add + "\n");
                }





            } catch (Exception ex) {
                Logger.getLogger(HaloHome.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ShortestPathRule.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_formWindowOpened

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        Sending.SimpleFileServer sendfile = new Sending.SimpleFileServer();
        try {
            DestinationIp dip = new DestinationIp();
            String destIP = dip.getDestinationIp();
            String path = DestinationIp.getSendPath();
            ConnectionDB connectiondb = new ConnectionDB();
            InetAddress thisIp = InetAddress.getByName(destIP);
            String destIp = "" + thisIp;
            String ip[] = destIp.split("/");
            boolean status = sendfile.SendFile(path, ip[1]);
            if (status) {
                JFrame parent = new JFrame();
                JOptionPane.showMessageDialog(parent, "File Send Successfully");
            }

        } catch (IOException ex) {
            Logger.getLogger(message.class.getName()).log(Level.SEVERE, null, ex);
  JOptionPane.showMessageDialog(null,ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
         Sending.SimpleFileServer sendfile = new Sending.SimpleFileServer();
        DestinationIp dip = new DestinationIp();
        String destIP = dip.getDestinationIp();
        String path = DestinationIp.getSendPath();
        ConnectionDB connectiondb = new ConnectionDB();
        InetAddress thisIp = InetAddress.getByName(destIP);
        String destIp = "" + thisIp;
        String ip[] = destIp.split("/");
        boolean status = sendfile.SendFile(path, ip[1]);
        if (status) {
            JFrame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "File Send Successfully");
        }
        }catch(Exception er)
        {
            System.out.println(er);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    /**
     * @param args the command line arguments
     *
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable cost;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea shortestPath;
    // End of variables declaration//GEN-END:variables
}
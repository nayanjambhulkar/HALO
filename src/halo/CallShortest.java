/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package halo;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin111
 */
public class CallShortest {
   public static void main(String arg[])
   {
        


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShortestPathRule().setVisible(true);
            }
        });
    }
      
   }
           

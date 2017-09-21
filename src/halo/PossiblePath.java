package halo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Admin111
 */
  public class PossiblePath {

    public Vector[] possibleRoot() throws InterruptedException, UnknownHostException, SQLException {
        
        ConnectionDB cnndb= new ConnectionDB();
        
        DestinationIp d= new DestinationIp();
        String destin=d.getDestinationIp();
        String source= InetAddress.getLocalHost().getHostName().toString().toUpperCase();
       // System.out.println("Destination-->"+destin+"Source-->"+source);
         Vector allnode=cnndb.getAllNode();
        // Vector allnode = new Vector();
      /*  allnode.add("Crazione");
        allnode.add("Crezione2");
        allnode.add("Creazione3");
        allnode.add("Creazione4");
        allnode.add("Creazione5");
        allnode.add("Creazione6");*/
         
         
        Vector path1[] = new Vector[allnode.size()];
        for (int j = 0; j < allnode.size(); j++) {
            path1[j] = new Vector();
            for (int i = j; i < allnode.size(); i++) {
                String src=allnode.get(i).toString();
               
                if (src.equals(source) || src.equals(destin))
                {
                
                   
                }
                else{
                     System.out.println("Value: "+src+" Source: "+source+" Dest:"+destin);
                     path1[j].add(allnode.get(i));
                }
                
                
              

            }
        }
        // 
        //System.out.println(path1.length);
        for (int i = 0; i < path1.length; i++) {
            System.out.println("Possible paths----->"+path1[i]);
        }

        return path1;

    }
    public static void main(String argp[]) throws UnknownHostException, SQLException
    {
        PossiblePath ps= new PossiblePath();
        try {
            Vector ar[]=ps.possibleRoot();
           
        } catch (InterruptedException ex) {
            Logger.getLogger(PossiblePath.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

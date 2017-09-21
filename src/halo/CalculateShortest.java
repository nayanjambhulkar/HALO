/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package halo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
/**
 *
 * @author Admin111
 */
public class CalculateShortest {
    Vector cost=null;
    ArrayList bandwidth=null;
    
    public Vector calculateCost() throws SQLException
    {
        try{
        int reference=100;
        cost= new Vector();
        ConnectionDB db= new ConnectionDB();
        bandwidth=db.getAllBandwidth();
        for(int i=0;i<bandwidth.size();i++)
        {
            int cost=reference/Integer.parseInt(bandwidth.get(i).toString());
            this.cost.add(cost); 
        }
        //System.out.println(cost); 
        }catch(SQLException sql)
        {
         System.out.println(sql);
        }
       return cost;
    }
 public static ArrayList[] getCost() throws SQLException
 {
     CalculateShortest cs= new CalculateShortest();
     Vector cost =cs.calculateCost();
     ArrayList nodeValue= new ArrayList();
     System.out.println(cost);
     ArrayList ar[]= new ArrayList[cost.size()];
     for(int i=0;i<cost.size();i++)
     {
         ar[i]= new ArrayList();
        int sourcecost=Integer.parseInt(cost.get(i).toString());
           System.out.println("Source Ip:-"+sourcecost);
         for(int j=0;j<cost.size();j++)
         {
           if(cost.get(j).equals(sourcecost))
           {
             continue;
           }   
           ar[i].add(java.lang.Math.abs(Integer.parseInt(cost.get(i).toString())-(Integer.parseInt(cost.get(j).toString())))); 
         }
           System.out.println(nodeValue);
    
     }
   return ar; 
 }
 public static void main(String arg[]) throws SQLException
 {
      ArrayList ar[]=getCost();
      for(int i=0;i<ar.length;i++)
      {
          System.out.println(ar[i]);
      }
     
 }
}

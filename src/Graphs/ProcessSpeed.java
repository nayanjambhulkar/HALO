/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphs;

import java.util.Properties;

/**
 *
 * @author Admin111
 */
public class ProcessSpeed {
    public static void main(String arg[])
    {
    Properties p = System.getProperties();   
    p.list(System.out); 
    System.out.print("Total CPU:");
    System.out.println(Runtime.getRuntime().availableProcessors());
    System.out.println("Max Memory:" + Runtime.getRuntime().maxMemory() + "\n" + "available Memory:" + Runtime.getRuntime().freeMemory());
    System.out.println("os.name=" + System.getProperty("os.name"));
    }
            
    
}

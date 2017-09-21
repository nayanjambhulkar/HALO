/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sending;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Admin111
 */
public class OpenFolder {
     public void open(String path) throws IOException 
    {            
           
            Desktop desktop = Desktop.getDesktop();        
            File dirToOpen = null;
            try {            
                Runtime runtime = Runtime.getRuntime();        
                runtime.exec("explorer.exe "+path);        
                System.out.println("open");        
            } catch (Exception E) {        
                System.out.println("File Not Found");        
            }
}
}


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin111
 */
public class jvac {
      public static void main(String args[]) {
         
         SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
        Date date = new Date();
        String time = dateFormat.format(date);
     System.out.println("Time:"+time+"Date:="+strDate);
    
}
}

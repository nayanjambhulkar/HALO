package halo;

/**
 * @(#)SetTimer.java
 *
 *
 * @author 
 * @version 1.00 2009/1/13
 */
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.*;


public class SetTimer implements ActionListener
 {
 	private  int tempid = 0;
//	private short t = 150;//Time out in milliseconds 
	public  int packetid = 0;//IDs of data packets sent out but not acknowledged
	public  int cmis = 0;//ctr for 2ACK packets missed
	private int tobs = 0;//time used for observing link 
	public  int cpkts = 0;//no of packets forwarded
	private int tsecs = 0;//timeout for 2ACK reception 
	double Rmis;
    public void SetTimer() 
    {
    }
    public void setToInitioal()
    {
    	packetid=0;
  		cpkts=0;
  		cmis=0;
  		Rmis=0;
    }
    public void starttimer()
    {
    	
    	ActionListener listener =this;
		Timer timeobj = new Timer(3000,listener);
		timeobj.start();
		timeobj.setRepeats(false);
		System.out.println("Timer working!!");		
    }
    public void incrValue()
    {
    	packetid++;
  		cpkts++;
  		System.out.println(cpkts+"  "+packetid);
    }
    public void updatevalue()
    {
    	packetid--;
    	System.out.println(packetid);
    }
    
    
    public void actionPerformed(ActionEvent event)
	{
		if(packetid!=0)
		{
			cmis+=packetid;
		}
		
		double RmisThreshold=0.85;
		
		Rmis=(cmis/cpkts);
		
		System.out.println("Rmis="+Rmis);
		
		if(Rmis>RmisThreshold)
		{
			System.out.println("MISBEHAVING NODE N2 to N3");
			JOptionPane.showMessageDialog(null,"Path N2 to N3 is Misbahaving");
		}
		else
		{
			System.out.println("NO Misbehaving node"+cmis);
			JOptionPane.showMessageDialog(null,"Path N2 to N3 is not Misbahaving");
		}
		System.out.println("Timer expired");
	}
}
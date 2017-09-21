package halo;

/**
 *
 *
 *@author 
 *@version 1.00 09/2/8
  */
 
 


import java.io.*;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Statement;
import java.sql.ResultSet;
import java.net.InetAddress;

public class MsgTransfer //implements ActionListener
{
	
	static SetTimer stTimer=null;
	private static int tempid = 0;
	private short t = 150;//Time out in milliseconds 
	private int packetid = 0;//IDs of data packets sent out but not acknowledged
	private int cmis = 0;//ctr for 2ACK packets missed
	private int tobs = 0;//time used for observing link 
	private int cpkts = 0;//no of packets forwarded
	private int tsecs = 0;//timeout for 2ACK reception
	private MsgTransfer node = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private int nodeCtr = 0;
	

	
/************************************************************************************************	
	if (data packet forwarded) then
LIST  LIST [ data ID == Add a data ID to LIST
Cpkts ++ == Increase the counter of forwarded
packets
setup timer () for data ID == Record the time
end
**************************************************************************************************/	
	
	MsgTransfer(){}
	
	protected String [] findActiveNodes() throws IOException
	{
		
		String []nodestr = null;
		new ConnectionDB();
		try
		{
			RouteRequest obj =new RouteRequest();
			obj.getNode();
			stmt = ConnectionDB.stmt;
			rs = stmt.executeQuery("select * from NodePath");
						
			nodeCtr = 0;
			while (rs.next())
			{
				
				nodestr[nodeCtr] = rs.getString(1).trim();
				
				nodeCtr++;
				System.out.println("node[" + nodeCtr + " ] = " +nodestr[nodeCtr-1]);
			}
			
		}
		catch(Exception e)
		{
			System.out.println("ERHYH!!!"+e);//+"nodectr = "+nodeCtr);
		}
		return nodestr;
	}
	
	
	public void sendMessage(String tmsg,String IpStr) throws Exception
	{	//IpStr stoes the neighbour comp name
		InetAddress localaddr = null;
		String []temp;
		String str =null;
		tempclient tempclientobj = new tempclient();
		
		System.out.println("In MsgTransfer msg=:"+tmsg);
		System.out.println("IpStr in MsgTransfer"+IpStr);
		
		IpStr = IpStr.trim();
		localaddr = InetAddress.getByName(IpStr);
		//localaddr = InetAddress.getByName("globeit8");
		
		str = str+localaddr; 
		temp = str.split("/");
		
		System.out.println("temp[1]"+temp[1]);
		System.out.println("localaddr"+localaddr);
		
		SetTimer settime = new SetTimer();
		settime.starttimer();
		this.stTimer= settime;
		
		
		for(int cnt=0;cnt<1;cnt++)
		{
			settime.incrValue();
			tempclientobj.communicate(tmsg,temp[1]);
			
		}
			
		tempid++;
		this.packetid = tempid;
		this.cpkts++;
		System.out.println("packid="+this.packetid);
	}
	
	
	
	public static void main(String [] args) throws Exception
	{
	  /*	MsgTransfer node = new MsgTransfer();
		node.sendMessage("hello","GITS1");
		
		ActionListener listener = node;
		Timer timeobj = new Timer(150,listener);
		timeobj.start();
		timeobj.setRepeats(false);
		System.out.println("Timer working!!");	*/
	}
	
/*	public void actionPerformed(ActionEvent event)
	{
		tempserver tempserverobj = new tempserver();
		if(tempserverobj.checkTimeout() == 1)
			node.packetid = 0;
		else
		{
			node.packetid = 0;
			node.cmis++;
		}		
		System.out.println("Timer expired");
	}
*/
}
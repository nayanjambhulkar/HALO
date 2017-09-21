package halo;

/**
 *
 *
 *@author 
 *@version 1.00 09/2/8
 */
 
//import java.net.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.ServerSocket;



public class tempclient implements Serializable 
{
	
	private int HOP1;
	private String SOURCE1;
	private int HOP2;
        
	private String SOURCE2;
	private String msg = null;
	private String ACK = null;
	private String Sender = null;
	tempclient()		
	{
		System.out.println("In constructer of tempclient");
		this.HOP1 = 1;
	 	this.SOURCE1 = null;
		this.HOP2 = 0;
		this.SOURCE2 = null;
	}
	
	protected void setSender(String sen)
	{
		this.Sender = sen;
	}
	protected String getSender()
	{
		 return this.Sender;
	}
	
	protected void setAck(String ack)
	{
		this.ACK = ack;
	}
	
	protected void setMsg(String msg)
	{
		 this.msg = msg;
	}
	protected void setHop1(int num)
	{
		this.HOP1 = num;
	}
	protected void setHop2(int num)
	{
		this.HOP2 = num;
	}
	protected void setSource1(String src)
	{
		this.SOURCE1 = src;
	}
	protected void setSource2(String src)
	{
		this.SOURCE2 = src;
	}
	protected void incremntHop1()
	{
		
		int hopcnt = this.HOP1;
		hopcnt++;
		this.HOP1 = hopcnt;
		
	}
	protected void incremntHop2()
	{
		int hopcnt = this.HOP2;
		hopcnt++;
		this.HOP2 = hopcnt;
		
	}
	protected String getAck()
	{
		return this.ACK;
	}
	protected String getMsg()
	{
		return this.msg;
	}
	protected int getHop1()
	{
		return this.HOP1;
	}
	protected int getHop2()
	{
		return this.HOP2;
	}
	protected String getSource1()
	{
		return this.SOURCE1;
	}
	protected String getSource2()
	{
		return this.SOURCE2;	
	}
	
	protected void communicate(String strmsg, String neighbourIP) throws IOException
	{
		String[] temp = null;
		String nodeIP = null;
		String strSelfAddr = null;
		int character;
		int portno=8765;
		System.out.println("In client Anup:"+neighbourIP);
		
		try
		{
		if(this.msg == null)//ie msg is sent from this node for the first time 
			this.setMsg(strmsg);
		Socket socket = new Socket(neighbourIP,portno);
		
		nodeIP = nodeIP + socket.getLocalAddress(); 		
		temp = nodeIP.split("/");
		
		if(this.SOURCE1 == null)
			this.SOURCE1 = temp[1];
		if(this.Sender == null)
			this.Sender = temp[1];
		
		System.out.println("In clinet SOURCE1 = "+this.SOURCE1);//this.SOURCE1 stores the IP adder 
		System.out.println("In client strmsg:"+this.msg);

		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		System.out.println("this.hop1 = "+this.HOP1);
		oos.writeObject(this);

		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());//reads the ack back from the server
		String replyrecieved = null;
		replyrecieved = (String)ois.readObject();
		
		
		if(replyrecieved != null)
		{
			System.out.println("In client Acknowledegement recieved="+replyrecieved);
			strSelfAddr = temp[1];//temp[1] stores the IP of itself
			temp =null;
			temp = replyrecieved.split("#");
			System.out.println("inclient temp[0] = "+temp[0]+" N temp[1] = "+temp[1]);
			String ackSource = null;
			ackSource = temp[1];	
			//temp[1] stores the IP sent by the server in the ACK
			if((strSelfAddr).equalsIgnoreCase(ackSource))//to check whether it is the source fr the ack
			{
				System.out.println("NO longer have to send back the ACK");
				
			}
			else
			{
				Socket soc2 = new Socket(ackSource,8765);
				ObjectOutputStream objout = new ObjectOutputStream(soc2.getOutputStream());
				if(this.ACK == null)
					this.ACK = replyrecieved;
				objout.writeObject(this);
			
			}
		}
		else
			System.out.println("Intermidiate node need not send any ack")	;
				
		oos.close();
		ois.close();
		socket.close();
	
		}
		catch(Exception e)
		{
			System.out.println("IN CLIENT Exception generated :"+e);
		}
	
	}
	
	
	public static void main(String [] args) throws Exception
	{
		new tempclient().communicate("hello","192.168.2.3");
	}
	
}
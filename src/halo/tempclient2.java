package halo;

import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;
import java.io.IOException;

class tempclient2
{
	private Socket socket2;
	
	tempclient2(){}
	
	
	protected void 	sendAck(String replyrecieved,String ackSource)
	{
		try
		{
		Socket socket2 = new Socket(ackSource,8999);
		
		ObjectOutputStream objoutStream = new ObjectOutputStream(this.socket2.getOutputStream()); 
		objoutStream.writeObject(replyrecieved);
		
		ObjectInputStream objinstream = new ObjectInputStream(socket2.getInputStream());//r			
		String replyrecieved2 =null;
		replyrecieved2 = (String)objinstream.readObject();
		System.out.println("ACK of the destination recieved is:"+replyrecieved2);
		}
		catch(UnknownHostException uhe){System.out.println("Exception generated :"+uhe);}
		catch(IOException ioe){System.out.println("Exception generated :"+ioe);}
		catch(ClassNotFoundException cnfe){System.out.println("Exception generated :"+cnfe);}
	}

}
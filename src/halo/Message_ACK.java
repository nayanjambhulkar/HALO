package halo;

import java.awt.*;
class Message_ACK
{
	Message_ACK(String message)
	{
		SystemTray tray = SystemTray.getSystemTray();
   		Image image = Toolkit.getDefaultToolkit().getImage("rbrs.gif");
     	TrayIcon trayIcon = new TrayIcon(image,"Manet ACK Alert");
		try 
        {
        	tray.add(trayIcon);
        	trayIcon.displayMessage("Message Forwarded",message, TrayIcon.MessageType.INFO);
        } 
        catch (AWTException e) 
        {
            System.err.println("Can't add to tray");
        }
	}
	public static void main (String[] args) 
	{
		new Message_ACK("Message Forwared");
	}
}

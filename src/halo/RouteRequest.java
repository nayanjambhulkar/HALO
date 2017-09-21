package halo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Vector;
import java.math.*;

public class RouteRequest {

    private Vector vnode;
    private Runtime r;
    private Process p;
    private BufferedReader br;
    private String node;
    private String[] node1;
    private String fnode[];
    private InetAddress inet;
    private String lhost;
    private Statement st;
    private Statement st1, stmt;
    private String query, query1;
    private int myIndex;
    private Enumeration en;
    private ResultSet rs;
    private String dnode;
    private int findIndex;
    private int diffIndex;
    private String path;
    private String findPathNode;
    private Statement st2, st3;
    private Vector vpath;
    private String dest;
    private String routepath;
//	private int pt[][];

    public RouteRequest() {
    }

    public Vector getNode() {
        try {
            st = ConnectionDB.stmt;

            vnode = new Vector();
            st.executeUpdate("delete from NodePath");
            //	st1.executeUpdate("delete from NodePosition");
            r = Runtime.getRuntime();
            p = r.exec("net view");
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int cnt = 0;
            fnode = new String[25];
            while ((node = br.readLine()) != null) {
                if (node.startsWith("\\")) {
                    node1 = node.split(" ");
                    node = node1[0].trim();
                    fnode[cnt] = node.substring(node.indexOf("\\") + 2, node.length());
                    vnode.addElement(fnode[cnt]);
                    query = "insert into NodePath values('" + fnode[cnt] + "','')";
                    st.executeUpdate(query);
                    cnt++;
                    System.out.println("node: " + fnode[cnt]);
                }
            }
        } //	st.close();
        /*	stmt= ConnectionDB.stmt;
			stmt.executeUpdate("delete from NodePosition");
			int x=-1,y=-1;
			for(int cn=0;cn<cnt;cn++)
			{
				query1 = "insert into NodePosition values('"+fnode[cn]+"','"+x+"','"+y+"')";
				stmt.executeUpdate(query1);
			}
			
			
         */ catch (Exception e) {
            e.printStackTrace();
        }
        return vnode;
    }

    public Vector getPath(Vector allNode) {
        try {
            vpath = new Vector();
            inet = InetAddress.getLocalHost();
            lhost = inet.getHostName().toUpperCase();
            System.out.println("Local Host..." + lhost);
            myIndex = allNode.indexOf(lhost);
            System.out.println("My Index..." + myIndex);
            st1 = ConnectionDB.stmt;
            rs = st1.executeQuery("select node from NodePath");
            while (rs.next()) {
                path = "";
                findPathNode = "";
                dnode = rs.getString(1);
                findIndex = allNode.indexOf(dnode);
                if (findIndex == myIndex) {
                    path = lhost + "#";
                } else if (findIndex < myIndex) {
                    diffIndex = myIndex - findIndex;
                    for (int i = findIndex; i < myIndex; i++) {
                        findPathNode = allNode.elementAt(i).toString();
                        path = findPathNode + "#" + path;
                    }
                } else {
                    diffIndex = findIndex - myIndex;
                    for (int i = myIndex + 1; i <= myIndex + diffIndex; i++) {
                        findPathNode = allNode.elementAt(i).toString();
                        path = path + findPathNode + "#";
                    }
                }
                System.out.println("Path..." + path);
                vpath.addElement(dnode);
                vpath.addElement(path);
            }
            System.out.println(vpath.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vpath;
    }

    public void setPath(Vector path) {
        try {
            //pt=new int[11][2];
            /*	int	pt[][]={{25,25},{150,80},{65,100},
				{180,45},{250,80},{150,110},
				{350,130},{120,170},{200,180},
				{50,250},{120,300},{250,320},
				{50,300},{280,360},{100,390},{400,400}};
				
		int x=0,y=0,t,t1;				
	
		
			String str[]=new String[45];
			String str1[];
			int m;
			for(m=0;m<path.size();m++)
			{
				str[m]=(String)path.elementAt(m);
				
			}
		/*	t1=0;
			st3 = ConnectionDB.stmt;
			int a=m/2;
			for(int j=0;j<m;j++)
			{
				if((j%2)!=0)
				{
					
					str1=str[j].split("#");
					
					t=str1.length;
					
					/*if(t1<t)
					{
						
						if(lhost.equals(str[j-1]))
						{
							x=pt[0][0];
							y=pt[0][1];
						
						}
						else
						{
							x=pt[a][0];
							y=pt[a][1];
							
							a--;
						}
					}
					else
					{
						if(lhost.equals(str[j-1]))
						{
							x=pt[0][0];
							y=pt[0][1];
						
						}
						else
						{
							x=pt[a-1][0]-10;
							y=pt[a-1][1]-30;
							
							a--;
						}
					}
					st3.executeUpdate("update NodePosition set x='"+x+"',y='"+y+"' where node='"+str[j-1]+"'");
					t1=t;
					
					
				}
				
			}
             */

            en = path.elements();
            while (en.hasMoreElements()) {
                st2 = ConnectionDB.stmt;
                dest = en.nextElement().toString();
                routepath = en.nextElement().toString();
                st2.executeUpdate("update NodePath set path='" + routepath + "' where node='" + dest + "'");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

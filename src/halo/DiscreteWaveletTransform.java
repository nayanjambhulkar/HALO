/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package halo;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


 import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import java.awt.*;
import java.lang.*;
import java.util.*;

class DiscreteWaveletTransform

{

public static void main(String arg[])
{ DiscreteWaveletTransform dwt=new DiscreteWaveletTransform();
dwt.initial();
}


static final int TYPE=BufferedImage.TYPE_INT_RGB;
public void initial()
{
try{

BufferedImage buf=ImageIO.read(new File("D:\\lena.bmp"));
int w=buf.getWidth();
int h=buf.getHeight();
BufferedImage dwtimage=new BufferedImage(h,w,TYPE);
int[][] pixel=new int[h][w];
for (int x=0;x<h;x++)
{
for(int y=0;y<w;y++)
{
pixel[x][y]=buf.getRGB(x,y);


}
}
int[][] mat = new int[h][w];
int[][] mat2 = new int[h][w];

for(int a=0;a<h;a++)
{
for(int b=0,c=0;b<w;b+=2,c++)
{
mat[a][c] = (pixel[a][b]+pixel[a][b+1])/2;
mat[a][c+(w/2)] = Math.abs(pixel[a][b]-pixel[a][b+1]);
}
}
for(int p=0;p<w;p++)
{
for(int q=0,r =0 ;q<h;q+=2)
{
mat2[r][p] = (mat[q][p]+mat[q+1][p])/2;
mat2[r+(h/2)][p] = Math.abs(mat[q][p]-mat[q+1][p]);
}
}
for (int x=0;x<h;x++)
{
for(int y=0;y<w;y++)
{
dwtimage.setRGB(x,y,mat2[x][y]);
}
}
String format="bmp";
ImageIO.write(dwtimage,format, new File("D:\\DWTIMAGE.bmp"));
}

catch(Exception e)
{
e.printStackTrace();
}
}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordinate;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author giana
 */

class MyPoint{
    int x;
    int y;
    MyPoint(int newx, int newy){x = newx; y=newy;}
    MyPoint(){this(0,0);}
}


public class Coordinate extends JPanel{
    static ArrayList<MyPoint> arr = new ArrayList<>();
    public void paint(Graphics g) {

        int rows = 10;
        int cols = 10;
        int width = getSize().width;
        int height = getSize().height;

        // draw the rows
        int rowHt = height / (rows);
        for (int i = 0; i < rows; i++)
          g.drawLine(0, i * rowHt, width, i * rowHt);

        // draw the columns
        int rowWid = width / (cols);
        for (int i = 0; i < cols; i++) {
          g.drawLine(i * rowWid, 0, i * rowWid, height);
        }
        MyPoint prev = null;
        for(MyPoint p: arr) {
            g.setColor(Color.red);
            g.fillOval(p.x, p.y, 4, 4);
            g.drawString("("+p.x+", "+p.y+")", p.x, p.y);
            if (prev == null) {
                prev = p;
            }
            else {
                g.drawLine(p.x,p.y,prev.x,prev.y);
                Integer diffX = p.x - prev.x;
                Integer diffY = p.y - prev.y;
                double length = Math.sqrt((diffX)*diffX + (diffY)*diffY);
                String strDouble = String.format("%.2f", length);
                g.drawString(strDouble, diffX / 2 + prev.x, diffY / 2 + prev.y);
                prev = p;
            }
            
        }
        
    }
    public void addPoint(MyPoint p){ arr.add(p);}
    public void clearList(){arr.clear(); this.repaint();}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setBounds(30, 30, 400, 400);
        JPanel c = new Coordinate();
        jf.getContentPane().add(c);
        c.addMouseListener(new MouseAdapter(){
             public void mousePressed(MouseEvent m){
                if (m.getButton()==MouseEvent.BUTTON1){
                    MyPoint p = new MyPoint(m.getX(),m.getY());
                    arr.add(p);
                    c.repaint();
                }
                else
                    arr.clear();
                    c.repaint();
            }
        });
        jf.setVisible(true);
    }
    
}

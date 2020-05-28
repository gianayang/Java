/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;
import java.awt.*;
import java.io.*;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.*;
import javax.swing.*;

/**
 *
 * @author giana
 */
public class Clock {
    /**
     * @param args the command line arguments
     */
    public static int sec;
    public static int min;
    public static int hr;
    public static JFrame jf;
    public static ClockP cp;
    Clock(){
        this.jf = new JFrame("Clock");
        jf.setSize(400, 400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.cp = new ClockP();
        jf.add(cp);
    }
    private static void getTime(String line) {
        String[] splited = line.split("\\s+");
        System.out.print("Time: "+ line);
        String[] time = splited[2].split(":");
        
        sec = Integer.parseInt(time[2]);
        updateSec(sec);
        min = Integer.parseInt(time[1]);
        hr = Integer.parseInt(time[0]);
        if (hr >= 12) {
            hr = hr - 12;
        }
        //System.out.println("time    hr: " + hr + "min:  " +min+"sec:    "+sec);
    }
    synchronized static void updateSec(int s) { 
        if (s == 0) {
            if (Clock.sec < 59) {
                Clock.sec ++;
            }else{
                Clock.sec = 0;
            }
            //System.out.println("sec : " + sec);
        } else {
            Clock.sec = s;
        }
    }
    public static void main(String[] args) {
        Clock c = new Clock();
        new Thread () {
            public void run(){
                while(true) {
                    Socket s;
                    try {
                        s = new Socket("time-b-g.nist.gov",13);
                        if(s.isConnected()){
                            try{
                                PrintStream sout = new PrintStream(s.getOutputStream());
                                Scanner sin = new Scanner(s.getInputStream());
                                String line = sin.nextLine();
                                line = sin.nextLine();
                                getTime(line);                              
                            }
                            catch(Exception e){
                                System.out.println("Did not work!");
                            }
                            try {
                                sleep(1000*60);
                            } catch (InterruptedException e){
                                System.out.println("Failed to sleep!");
                            }
                        }
                    }catch (IOException e) {
                        Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        }.start();
        new Thread(){
            public void run(){
                while (true){
                    updateSec(0);
                    cp.repaint();
                    //System.out.println("repainting");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Cannot sync clock");
                    }
                }
            }
        }.start();
        
        jf.setVisible(true);
    }
    
}
class ClockP extends JPanel{
    public int r;
    
    Point calc(double degree, double len){
        Point p = new Point();
        p.x = (int)(Math.sin(Math.toRadians(degree))*len);
        p.y = (int)(Math.cos(Math.toRadians(degree))*len);
        return p;
    }
    
    protected void paintComponent(Graphics g){
        int height = this.getHeight();
        int width = this.getWidth();
        
        int centerX = width/2;
        int centerY = height/2 + 10;
        
        r = height/2;
        
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.fillOval(width/2 - r, height/2 - r, 2 * r, 2 * r);
        
        Point p1 = calc(Clock.sec * 6, 0.9*r);
        g.setColor(Color.red);
        g.drawLine(centerX, centerY, centerX + p1.x, centerY - p1.y);
        
        Point p2 = calc(Clock.min * 6 ,0.7*r);
        g.setColor(Color.white);
        g.drawLine(centerX, centerY, centerX + p2.x, centerY - p2.y);
        
        Point p3 = calc((Clock.hr *30 + Clock.min * 0.5),0.5*r);
        g.drawLine(centerX, centerY, centerX + p3.x, centerY - p3.y);
        
    }
}

class Point{
    int x;
    int y;
    Point(){
        this.x = 0;
        this.y = 0;
    }
    Point(int newX, int newY){
        x = newX; 
        y = newY;
    }
}


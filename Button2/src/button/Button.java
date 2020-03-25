/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package button;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author giana
 */
class ButtonKey extends Thread{
    boolean flag;
    JButton button;
    ButtonKey(){
        flag = false;
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, g, b);
        button = new JButton();
        button.setBackground(randomColor);
        button.addActionListener(new ButtonListener());
    }
    
    @Override
    public void run() 
    { 
        try
        { 
            while(true){
                Random rand = new Random();
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                Color randomColor = new Color(r, g, b);
                button.setBackground(randomColor);
                sleep(1000);
            }
        } 
        catch (Exception e) 
        { 
            // Throwing an exception 
            System.out.println ("Exception is caught"); 
        } 
    }
    

    
}

public class Button{
    
    static ButtonKey[] jBList = new ButtonKey[8];
    
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame jf = new JFrame("Button Frame");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(400,400);
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(4,2));
        for (int i = 0; i < 8; i++) {
            jBList[i] = new ButtonKey();
            jp.add(jBList[i].button,BorderLayout.CENTER);
            jBList[i].start();
            
        }
        jf.add(jp);
        jf.setVisible(true);
    }
    
}

class ButtonListener implements ActionListener  {
    @Override
    public void actionPerformed(ActionEvent arg0) {
        for (ButtonKey button: Button.jBList) {
            if (button.button == arg0.getSource()) {
                button.flag = !(button.flag);
                if (!button.flag) {
                    button.resume();
                }else {
                    button.suspend();
                }
            }
            
        }
    }
}
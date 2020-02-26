/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package button1;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
import java.util.Random;
import java.util.*;
/**
 *
 * @author giana
 */
public class Button1 {
    /**
     * @param args the command line arguments
     */
    static JButton[] jBList = new JButton[8];
    
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame jf = new JFrame("Button Frame");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(400,400);
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(4,2));
        for (int i = 0; i < 8; i++) {
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color randomColor = new Color(r, g, b);
            jBList[i] = new JButton();
            jBList[i].setBackground(randomColor);
            jBList[i].addActionListener(new ButtonListener());
            jp.add(jBList[i],BorderLayout.CENTER);
        }
        jf.add(jp);
        jf.setVisible(true);
    }
    
}

class ButtonListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent arg0) {
        for (JButton button: Button1.jBList) {
            if (button != arg0.getSource()) {
                Random rand = new Random();
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                Color randomColor = new Color(r, g, b);
                button.setBackground(randomColor);
            }
        }
    }
    
}

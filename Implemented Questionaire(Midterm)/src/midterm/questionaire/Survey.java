package midterm.questionaire;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author giana
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import static midterm.questionaire.Survey.arr;
import static midterm.questionaire.Survey.counter;

public class Survey extends Thread{
    static class Question {
        public String question = new String();
        public String left = "True";
        public String right = "False";
        Question(String q, String l, String r) {
            question = q;
            left = l;
            right = r;
        }
        Question(String q) {
            question = q;
        }
    }
    
    static Question[] arr = new Question[5];
    static String res[] = new String[5];
    static int counter = 0;
    static JFrame jf = new JFrame("Questionaire");
    static JPanel jp1 = new JPanel();
    static JPanel jp2 = new JPanel();
    static MyThread th = new MyThread();
    static Label lb = new Label(""); 
    static JButton l = new JButton("left");
    static JButton r = new JButton("right");
    Survey() {
        l.addActionListener(new ButtonListener());
        r.addActionListener(new ButtonListener());
        jf.setSize(400,400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (int i = 0; i < 5; i++) {
            res[i] = new String("No Response");
        }
        jp1.setLayout(new BoxLayout(jp1,BoxLayout.Y_AXIS));
        jp2.setLayout(new BoxLayout(jp2,BoxLayout.LINE_AXIS));
        jp1.add(lb);
        jp2.add(l,BorderLayout.PAGE_END);
        jp2.add(Box.createHorizontalGlue());
        jp2.add(r,BorderLayout.PAGE_END);
        jp1.add(jp2,BorderLayout.PAGE_END);
        jf.add(jp1);
    }
    
    public static void main(String[] args) {
        String[] res = new String[5];
        arr[0] = new Question ("What is your favorite ice cream?","Vanilla","Chocolate");
        arr[1] = new Question ("Which season is better?","Winter","Summer");
        arr[2] = new Question ("Which pet is better?","Cat","Dog");
        arr[3] = new Question ("Unicorns are real");
        arr[4] = new Question ("Text or call","Text","Call");
        Survey sv = new Survey();
        lb.setText(arr[counter].question);
        l.setText(arr[counter].left);
        r.setText(arr[counter].right);
        jf.setVisible(true);
        th.start();
    }
}
class MyThread extends Thread {
    @Override
    public void run() {
        try {
            sleep(5000);
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < Survey.res.length; i++) {
                sb.append(Survey.res[i]);
                sb.append(",");
            }
            Survey.lb.setText(sb.toString());
        }
        catch (Exception e) 
        { 
            // Throwing an exception 
            System.out.println ("Exception is caught"); 
        } 
        
    }
}

class ButtonListener implements ActionListener  {
    @Override
    public void actionPerformed(ActionEvent arg0) {
        JButton b = (JButton) arg0.getSource();
        Survey.res[Survey.counter] = b.getText();
        counter++;
        if (Survey.counter < 5) {
            Survey.th.interrupt();
            Survey.lb.setText(arr[counter].question);
            Survey.l.setText(arr[counter].left);
            Survey.r.setText(arr[counter].right);
            Survey.th = new MyThread();
            Survey.th.start();
        }else {
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < Survey.res.length; i++) {
                sb.append(Survey.res[i]);
                sb.append(",");
            }
            Survey.lb.setText(sb.toString());
        }
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author giana
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giana
 */
public class Client {
    private String us;
    private static String server;
    private JFrame jf = new JFrame("My Window");
    private JPanel main = new JPanel();
    private JButton send = new JButton();
    private JTextField text =  new JTextField();
    private static JTextArea chat;
    private JTextField username;
    private JTextField enterS;
    private JFrame setting = new JFrame();
    private static Socket s;
    static PrintStream sout;
    public Client() {
        JPanel settingP = new JPanel (new GridBagLayout());
                GridBagConstraints preRight = new GridBagConstraints();
        username = new JTextField(15);
        JLabel userName = new JLabel("What is your Username?");
        enterS = new JTextField(15);
        JLabel sev = new JLabel("Which server you want to connect to?");
        JButton enterServer = new JButton("Enter a chat Server");
        enterServer.addActionListener(new enterServerButtonListener());
        preRight.insets = new Insets(0, 0, 0, 10);
        preRight.anchor = GridBagConstraints.EAST;
        GridBagConstraints preLeft = new GridBagConstraints();
        preLeft.anchor = GridBagConstraints.WEST;
        preLeft.insets = new Insets(0, 10, 0, 10);
        // preRight.weightx = 2.0;
        preRight.fill = GridBagConstraints.HORIZONTAL;
        preRight.gridwidth = GridBagConstraints.REMAINDER;

        settingP.add(userName, preLeft);
        settingP.add(username, preRight);
        settingP.add(sev,preLeft);
        settingP.add(enterS,preRight);
        setting.add(settingP,BorderLayout.CENTER);
        setting.add(enterServer,BorderLayout.SOUTH);
        setting.setSize(300, 300);
        setting.setVisible(true);
    }
    
    public void display(){
        main.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridBagLayout());

        text = new JTextField(30);
        text.requestFocusInWindow();

        send = new JButton("Send Message");
        send.addActionListener(new sendMessageButtonListener());

        chat = new JTextArea();
        chat.setEditable(false);
        chat.setFont(new Font("Serif", Font.PLAIN, 15));
        chat.setLineWrap(true);

        main.add(new JScrollPane(chat), BorderLayout.CENTER);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        southPanel.add(text, left);
        southPanel.add(send, right);

        main.add(BorderLayout.SOUTH, southPanel);

        jf.add(main);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(470, 300);
        jf.setVisible(true);
    }
    public static void main(String[] args) {
        Client c = new Client();
        try {
            s = new Socket(server,5190);
            if (s.isConnected()){
                Scanner sin = new Scanner(s.getInputStream());
                while (sin.hasNext()){
                    String input = sin.nextLine();
                    chat.append(input + "\r\n");
                }
                s.close();
            }
            else{
                System.out.println("Socket COnnection Failed!");
            }
        }
        catch(IOException e){
            System.out.println("Did not work!");
        }
    }
    
    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                sout = new PrintStream(s.getOutputStream());
                us = username.getText();
                sout.print(us + "\r\n");
                server = enterS.getText();
                if (us.length() < 1) {
                    System.out.println("No!");
                } else {
                    setting.setVisible(false);
                    display();
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
        class sendMessageButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                try {
                    sout = new PrintStream(s.getOutputStream());
                    sout.print(text.getText()+ "\r\n");
                    text.setText("");
                }catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework.pkg4;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author giana
 */
public class Server {
    static int portNum=5190;
    public static ArrayList<Socket> clients;
    ServerSocket ss;
    public static void main(String[] args) {
        clients = new ArrayList<Socket>();
        ServerSocket ss = null;
        int id=0;
        try{
            ss = new ServerSocket(portNum);
            while (true){
                Socket client = ss.accept(); //Program will wait here for a LONG time!
                clients.add(client);
                new ProcessConnection(id++,client).start();
            }
            
        }
        catch(IOException e){ System.out.println("IOError: "+e.toString());}
    }
}
class ProcessConnection extends Thread{
        Socket client;
        int id;
        String user;
        ProcessConnection(int newid,Socket newclient){
            this.client=newclient;
            this.id = newid;
        }
        public void run(){
            try{
                String line="";
                Scanner sin = new Scanner(this.client.getInputStream());
                if(sin.hasNext()) {
                    user = sin.nextLine();
                }
                while (sin.hasNext()){
                    line = sin.nextLine();
                    for (Socket i: Server.clients) {
                        PrintStream sout = new PrintStream(i.getOutputStream());
                        if(i.isConnected()) {
                            sout.print(user + ":" + line + "\r\n" );
                        }
                    }
                }
                client.close();
            }
            catch(IOException e){

            }
        }
    }


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

        
package cafequeue;
import java.util.Deque;
import java.util.LinkedList;
/**
 *
 * @author giana
 */
public class CafeQueue extends Thread{
    /**
     *
     * @param customer
     * @param method
     */
    static Deque<String> line = new LinkedList<>();
    public synchronized void enterQueue (String customer) {
        line.addLast(customer);
    }
    synchronized String serveCustomer () {
        return line.removeFirst();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

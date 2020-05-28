/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
        
    
/**
 *
 * @author giana
 */
public class SQL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // TODO code application logic here
        Class.forName("com.mysql.jdbc.Driver");
        String name="CS3913";  
        String pass="GettingAnA+";  
        try {
            Connection conn = DriverManager.getConnection("localhost",name,pass);
            System.out.println("Connected database successfully...");
            
            System.out.println("Creating table in given database...");
            Statement statement = conn.createStatement(); 
            try {
                if (statement != null) {
                    String query = "Select * from Products";
                    String query1 = "Select Rating from Reviews where PID=#####";
                    ResultSet productName = statement.executeQuery(query);
                    ResultSet ratings = statement.executeQuery(query1);
                    HashMap<Integer,String> map = new HashMap<>();
                    while (productName.next()) {
                        Integer pid = productName.getInt(1);
                        String product = productName.getString(2);
                        map.put(pid, product);
                    }
                    HashMap<Integer,ArrayList<Integer>> rates = new HashMap<Integer,ArrayList<Integer>>();
                    while (ratings.next()) {
                        Integer pid = ratings.getInt(1);
                        Integer rate = ratings.getInt(2);
                        if (rates.get(pid) == null){
                            rates.put(pid, new ArrayList<>());
                        }
                        rates.get(pid).add(rate);
                    }
                    HashMap<Integer,ArrayList<Integer>> res = new HashMap<Integer,ArrayList<Integer>>();
                    for (Integer pid: rates.keySet()) {
                        Integer total = 0;
                        Integer num = 0;
                        for (Integer rate : rates.get(pid)) {
                            total += rate;
                            num++;
                        }
                        Integer avg = total / num;
                        if (res.get(avg) == null) {
                            res.put(avg,new ArrayList<>());
                        }
                        res.get(avg).add(pid);
                    }
                    for (int i = 5; i >0;i--) {
                        ArrayList<Integer> arr = res.get(i);
                        for(Integer pid: arr) {
                            System.out.println("Product: " + map.get(pid)+ "; Rating: " + i + '\n');
                        }
                    }
                    conn.close();
                }
            }catch (SQLException se) {
            }// do nothing
            
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

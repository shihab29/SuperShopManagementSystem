/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supershopfrontdesk;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;

/**
 *
 * @author anik
 */
public class test {
    /*String name="7";
    String price="20";
    String vat="5";
    String discount="2"; 
    String total="23";*/
    
    void addEarl(String name, String price, String vat, String discount, String total){
        String sql="INSERT INTO `earl`(`name`, `price`, `vat`, `discount`, `total`, `date`) VALUES("+name+","+price+","+vat+","+discount+","+total+","+"CURDATE())";
        ConString obj = new ConString();
        try{
            
            Connection con= (Connection) DriverManager.getConnection(obj.conString, obj.username, obj.passward);
            
            Statement s =(Statement) con.prepareStatement(sql);
            
            s.execute(sql);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /*public static void main(String args[]){
        String nam="7";
        String pric="20";
        String va="5";
        String discoun="2"; 
        String tota="23";
        
        addEarl(String nam, String pric, String va, String discoun, String tota);
    }*/
}

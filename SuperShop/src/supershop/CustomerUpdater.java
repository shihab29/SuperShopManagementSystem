/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supershop;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;

/**
 *
 * @author anik
 */
public class CustomerUpdater {
    String conString ="jdbc:mysql://localhost:3306/mydb";
    String username ="root";
    String passward ="";
    
    
    public boolean add(String name, String sex, String address, String blood_grp, String phonenumber, String email, String dob, String type, String code,String discount){
        String sql="INSERT INTO `customer`(`id`, `name`, `sex`, `address`, `blood_grp`, `phone`, `email`, `dob`, `type`, `card_number`, `discount`) VALUES(NULL,'"+name+"','"+sex+"','"+address+"','"+blood_grp+"','"+phonenumber+"','"+email+"','"+dob+"','"+type+"','"+code+"','"+discount+"')";
        
        try{
            
            Connection con= (Connection) DriverManager.getConnection(conString, username, passward);
            
            Statement s =(Statement) con.prepareStatement(sql);
            
            s.execute(sql);
            
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}

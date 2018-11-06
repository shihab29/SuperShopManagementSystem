/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supershop;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author anik
 */
public class accinfoUpdater {
    
    String conString ="jdbc:mysql://localhost:3306/mydb";
    String username ="root";
    String passward ="";
    
    public boolean add(String First_name,String Last_name,String UserID,String Type,String pass){
        
        String sql="INSERT INTO `accinfo` (`firstName`, `lastName`, `userName`, `type` , `passWord`) VALUES('"+First_name+"','"+Last_name+"','"+UserID+"','"+Type+"','"+pass+"')";
        
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

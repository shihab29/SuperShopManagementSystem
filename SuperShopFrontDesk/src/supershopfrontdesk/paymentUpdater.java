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
public class paymentUpdater {
    
        public boolean addSell(String name, String customer_code,String cashier_name,String date, String month, String year, String barcode){
            String sql="INSERT INTO `sell`(`name`, `customer_code`, `cashier_name`, `date`, `month`, `year`, `barcode`) VALUES('"+name+"','"+customer_code+"','"+cashier_name+"','"+date+"','"+month+"','"+year+"','"+barcode+"')";
            
            try{
            ConString obj = new ConString();
            Connection con= (Connection) DriverManager.getConnection(obj.conString, obj.username, obj.passward);
            
            Statement s =(Statement) con.prepareStatement(sql);
            
            s.execute(sql);
            
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
        }
}

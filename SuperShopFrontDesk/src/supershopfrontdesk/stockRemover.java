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
public class stockRemover {
    ConString obj = new ConString();
    public boolean update(String id, String name,String stock,String price,String type,String weight,String bPrice, String barcode, String vat, String pwv){
        
        
        String sql="UPDATE `product` SET `name`='"+name+"',`stock`='"+stock+"',`price`='"+price+"',`type`='"+type+"',`weight`='"+weight+"',`buy price`='"+bPrice+"',`barcode`='"+barcode+"',`vat`='"+vat+"',`price_with_vat`='"+pwv+"' WHERE `id`='"+id+"'";
        
        
        try{
            
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

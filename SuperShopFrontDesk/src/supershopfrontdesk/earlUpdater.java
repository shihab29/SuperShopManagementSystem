/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supershopfrontdesk;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author anik
 */
public class earlUpdater {
    public DefaultTableModel getData(){
        
        System.out.println("problem in earl get data");
        DefaultTableModel dm = new DefaultTableModel();
        dm.addColumn("ID");
        dm.addColumn("Name");
        dm.addColumn("Price");
        dm.addColumn("vat");
        dm.addColumn("discout");
        dm.addColumn("Total");
        dm.addColumn("Date");
        
        String sql = "SELECT * FROM earl";
        ConString obj = new ConString();
        
        try{
            Connection con= (Connection) DriverManager.getConnection(obj.conString, obj.username, obj.passward);
            
            Statement s =(Statement) con.prepareStatement(sql);
            
            ResultSet rs =s.executeQuery(sql);
            while(rs.next()){
                //String id = Integer.toString(count);
                String id = rs.getString(1);
                String name = rs.getString(2);
                String price = rs.getString(3);
                String vat = rs.getString(4);
                String discount = rs.getString(5);
                String total = rs.getString(6);
                String date = rs.getString(7);
                
                dm.addRow(new String[]{id,name,price, vat, discount, total, date});
            }
            
            return dm;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    public DefaultTableModel getDiscountTable(){
        
        DefaultTableModel dm = new DefaultTableModel();
        dm.addColumn("ID");
        dm.addColumn("card_number");
        dm.addColumn("discount");
        
        
        String sql = "SELECT * FROM customer";
        ConString obj = new ConString();
        
        try{
            Connection con= (Connection) DriverManager.getConnection(obj.conString, obj.username, obj.passward);
            
            Statement s =(Statement) con.prepareStatement(sql);
            
            ResultSet rs =s.executeQuery(sql);
            while(rs.next()){
                String id = rs.getString(1);
                String card_number = rs.getString(10);
                String discount = rs.getString(11);
                
                dm.addRow(new String[]{id,card_number,discount});
            }
            
            return dm;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    public boolean addEarl(String name, String price, String vat, String discount, String total){
        String sql="INSERT INTO `earl`(`name`, `price`, `vat`, `discount`, `total`, `date`) VALUES("+name+","+price+","+vat+","+discount+","+total+","+"CURDATE())";
        ConString obj = new ConString();
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


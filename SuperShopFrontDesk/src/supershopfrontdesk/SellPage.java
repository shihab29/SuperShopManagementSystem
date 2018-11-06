/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supershopfrontdesk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aust_anik
 */
public class SellPage extends javax.swing.JFrame {

    
    ArrayList<Object> INFO = new ArrayList<>();
    DefaultTableModel dm = new DefaultTableModel();
    int hour,min,sec,AM_PM,date,month,year;
    double total=0, vat=0, price=0;
    public String cashier_name;
    
    NumberFormat formatter = new DecimalFormat("#0.00");   
    /**
     * Creates new form SellPage
     */
    public SellPage() {
        initComponents();
        load();
        retreve();
        
        new Thread(){
            public void run(){
                while(true){
                    String time;
                    String day_night;
                    Calendar cal = new GregorianCalendar();
                    hour = cal.get(Calendar.HOUR);
                    min = cal.get(Calendar.MINUTE);
                    sec = cal.get(Calendar.SECOND);
                    AM_PM = cal.get(Calendar.AM_PM);
                    date = cal.get(Calendar.DATE);
                    month = cal.get(Calendar.MONTH);
                    year = cal.get(Calendar.YEAR);
                    
                    if(AM_PM == 1){
                        day_night=""+" PM";
                    }
                    else{
                        day_night=""+" AM";
                    }
                   
                    if(sec<10){
                        time = hour+":"+min+":0"+sec+""+day_night;
                    }
                    else{
                        time = hour+":"+min+":"+sec+""+day_night;
                    }
                    //time = hour+":"+min+":"+sec+" PM";
                    jlTime.setText(time);
                    
                }
            }
            
        }.start();
        
    }
    
    public SellPage(ArrayList<Object> INFO1, DefaultTableModel dm1,double total, double vat, double price,String cashier_name){
        initComponents();
        INFO=INFO1;
        dm=dm1;
        this.total=total;
        this.vat=vat;
        this.price=price;
        this.cashier_name=cashier_name;
        retreveAgain();
        
        new Thread(){
            public void run(){
                while(true){
                    String time;
                    String day_night;
                    Calendar cal = new GregorianCalendar();
                    hour = cal.get(Calendar.HOUR);
                    min = cal.get(Calendar.MINUTE);
                    sec = cal.get(Calendar.SECOND);
                    AM_PM = cal.get(Calendar.AM_PM);
                    
                    if(AM_PM == 1){
                        day_night=""+" PM";
                    }
                    else{
                        day_night=""+" AM";
                    }
                   
                    if(sec<10){
                        time = hour+":"+min+":0"+sec+""+day_night;
                    }
                    else{
                        time = hour+":"+min+":"+sec+""+day_night;
                    }
                    //time = hour+":"+min+":"+sec+" PM";
                    jlTime.setText(time);
                    
                }
            }
            
        }.start();
        
    }
    public SellPage(String cashier_name) {
        initComponents();
        load();
        this.cashier_name=cashier_name;
        retreve();
        
        new Thread(){
            public void run(){
                while(true){
                    String time;
                    String day_night;
                    Calendar cal = new GregorianCalendar();
                    hour = cal.get(Calendar.HOUR);
                    min = cal.get(Calendar.MINUTE);
                    sec = cal.get(Calendar.SECOND);
                    AM_PM = cal.get(Calendar.AM_PM);
                    date = cal.get(Calendar.DATE);
                    month = cal.get(Calendar.MONTH);
                    year = cal.get(Calendar.YEAR);
                    
                    if(AM_PM == 1){
                        day_night=""+" PM";
                    }
                    else{
                        day_night=""+" AM";
                    }
                   
                    if(sec<10){
                        time = hour+":"+min+":0"+sec+""+day_night;
                    }
                    else{
                        time = hour+":"+min+":"+sec+""+day_night;
                    }
                    //time = hour+":"+min+":"+sec+" PM";
                    jlTime.setText(time);
                    
                }
            }
            
        }.start();
        
    }
    public void retreve(){
        
        dm.addColumn("ID");
        dm.addColumn("Name");
        //dm.addColumn("Stock");
        dm.addColumn("Type");
        dm.addColumn("Price");
        dm.addColumn("Vat(%)");
        dm.addColumn("Weight");
        dm.addColumn("Quantity");
        dm.addColumn("Barcode");
        
        jTable1.setModel(dm);
        
        tfPrice.setText(Double.toString(price));
        tfVat.setText(Double.toString(vat));
        tfTotal.setText(Double.toString(total));
        tfname.setText(cashier_name);
    }
    public void retreveAgain(){
        
        jTable1.setModel(dm);
        
        tfPrice.setText(Double.toString(price));
        tfVat.setText(Double.toString(vat));
        tfTotal.setText(Double.toString(total));
        tfname.setText(cashier_name);
    }
    
    public void load(){
        
            String sql = "SELECT * FROM product";
        
        
        
        try{
            ConString conObj =  new ConString();
            Connection con= (Connection) DriverManager.getConnection(conObj.conString, conObj.username, conObj.passward);
            
            Statement s =(Statement) con.prepareStatement(sql);
            
            ResultSet rs =s.executeQuery(sql);
            //int count=0;
            while(rs.next()){
                info obj = new info();
                //obj.id = Integer.toString(count);
                obj.id = rs.getString(1);
                obj.name = rs.getString(2);
                obj.stock = rs.getString(3);
                obj.price = rs.getString(4);
                obj.type = rs.getString(5);
                obj.weight = rs.getString(6);
                obj.buy_price = rs.getString(7);
                obj.barcode = rs.getString(8);
                obj.vat=rs.getString(9);
                obj.pwv=rs.getString(10);

                INFO.add(obj);
                //count++;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLCashier = new javax.swing.JLabel();
        jlTime = new javax.swing.JLabel();
        tfBCode = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tfPrice = new javax.swing.JTextField();
        tfVat = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfTotal = new javax.swing.JTextField();
        bSell = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lProductStatus = new javax.swing.JLabel();
        bDelete = new javax.swing.JButton();
        bStock = new javax.swing.JButton();
        bCancel = new javax.swing.JButton();
        bSignOut = new javax.swing.JButton();
        tfname = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLCashier.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLCashier.setText("Cashier:");

        jlTime.setDisplayedMnemonic('4');
        jlTime.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jlTime.setText(" 5:00:00");

        tfBCode.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tfBCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfBCodeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Enter BarCode or Product ID:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Price:");

        tfPrice.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tfVat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Total Vat:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Total:");

        tfTotal.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        tfTotal.setForeground(new java.awt.Color(255, 0, 0));

        bSell.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bSell.setText("Sell");
        bSell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSellActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("BDT");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("BDT");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("BDT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfVat, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(tfPrice)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bSell, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfVat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(bSell)
                .addGap(38, 38, 38))
        );

        lProductStatus.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lProductStatus.setForeground(new java.awt.Color(204, 51, 0));

        bDelete.setText("Delete");
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });

        bStock.setText("Stock");
        bStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStockActionPerformed(evt);
            }
        });

        bCancel.setText("Cancel");
        bCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelActionPerformed(evt);
            }
        });

        bSignOut.setText("Sign Out");
        bSignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSignOutActionPerformed(evt);
            }
        });

        tfname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLCashier)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfname, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfBCode, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(lProductStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bStock)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bCancel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bSignOut))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlTime)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTime, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfname, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLCashier, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lProductStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfBCode))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bDelete)
                    .addComponent(bStock)
                    .addComponent(bCancel)
                    .addComponent(bSignOut))
                .addContainerGap(319, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1253, 838));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tfBCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfBCodeActionPerformed
        lProductStatus.setText("");
        tfPrice.setText("");
        tfVat.setText("");
        tfTotal.setText("");
        
        String quantity="1";
        String bCode = tfBCode.getText();
        boolean exist = false;
        info temp = new info();
        for(int i=0;i<INFO.size();i++) {
            temp =(info) INFO.get(i);
            int x = temp.barcode.compareToIgnoreCase(bCode);
            if(x==0){
                exist=true;
                break;
            }
        }
        if(exist && Integer.parseInt(quantity)<=Integer.parseInt(temp.stock)) {
            int currentStock=Integer.parseInt(temp.stock)-1;
            temp.stock="";
            temp.stock+=currentStock;
            boolean isInTable = false;
            int j;
            for(j=0;j<dm.getRowCount();j++){
                int y= bCode.compareToIgnoreCase((String)dm.getValueAt(j,7));//barcode is in number 7
                if(y==0){
                    String quantity1 = "";
                    quantity1 +=(String)dm.getValueAt(j,6);//quantity is in number 6
                    int q = Integer.parseInt(quantity1)+1;
                    quantity ="";
                    quantity =""+q;
                    isInTable = true;
                    break;
                }
            }
            if(isInTable==false){
                dm.addRow(new Object[]{temp.id,temp.name,temp.type,temp.price,temp.vat,temp.weight,quantity,temp.barcode});
                jTable1.setModel(dm);
                price += Double.parseDouble(temp.price);
                total += Double.parseDouble(temp.pwv);
                vat = (total-price);
                
                tfPrice.setText(""+formatter.format(price));
                tfVat.setText(""+formatter.format(vat));
                tfTotal.setText(""+formatter.format(total));
                
            }
            else{
                dm.setValueAt(quantity,j,6);
                price += Double.parseDouble(temp.price);
                total += Double.parseDouble(temp.pwv);
                vat = (total-price);
                
                tfPrice.setText(""+formatter.format(price));
                tfVat.setText(""+formatter.format(vat));
                tfTotal.setText(""+formatter.format(total));
            }
            
        } 
        else {
                lProductStatus.setText("Your Product Is Out Of Stock");
                tfPrice.setText(""+formatter.format(price));
                tfVat.setText(""+formatter.format(vat));
                tfTotal.setText(""+formatter.format(total));
        }
        tfBCode.setText("");
    }//GEN-LAST:event_tfBCodeActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        int row=0,quan=0,tempVat=0;
        double delPrice=0, delVat=0,delTotal=0;
        if(jTable1.getSelectedRow()>=0)
            row= jTable1.getSelectedRow();
            
            String quantity1 ="";
            quantity1 +=(String)dm.getValueAt(row,6);//quantity is in number 6
            quan= Integer.parseInt(quantity1);
            
            String p="";
            p += (String)dm.getValueAt(row,3);
            delPrice= Double.parseDouble(p);
            double temp=(delPrice*quan);
            price -= temp;
            
            String v="";
            v+= (String)dm.getValueAt(row,4);
            tempVat= Integer.parseInt(v);
            double t= (temp*tempVat)/100.00;
            vat-=t;
            
            total-=(temp+t);
            
            tfPrice.setText(""+formatter.format(price));
            tfVat.setText(""+formatter.format(vat));
            tfTotal.setText(""+formatter.format(total));
            
            dm.removeRow(jTable1.getSelectedRow());
    }//GEN-LAST:event_bDeleteActionPerformed

    private void bStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStockActionPerformed
        StockPage obj = new StockPage(INFO,dm,total, vat, price,cashier_name);
        obj.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_bStockActionPerformed

    private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelActionPerformed
        SellPage obj = new SellPage();
        obj.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_bCancelActionPerformed

    private void bSignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSignOutActionPerformed
        signInForm obj = new signInForm();
        obj.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_bSignOutActionPerformed

    private void bSellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSellActionPerformed
        Payment obj = new Payment(dm, INFO, date, month, year,total, vat, price, tfname.getText());
        obj.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_bSellActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SellPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SellPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SellPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SellPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SellPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancel;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bSell;
    private javax.swing.JButton bSignOut;
    private javax.swing.JButton bStock;
    public javax.swing.JLabel jLCashier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlTime;
    private javax.swing.JLabel lProductStatus;
    private javax.swing.JTextField tfBCode;
    private javax.swing.JTextField tfPrice;
    private javax.swing.JTextField tfTotal;
    private javax.swing.JTextField tfVat;
    public javax.swing.JLabel tfname;
    // End of variables declaration//GEN-END:variables
}

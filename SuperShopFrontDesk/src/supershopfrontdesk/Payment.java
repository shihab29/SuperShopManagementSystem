/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supershopfrontdesk;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Shatu
 */
public class Payment extends javax.swing.JFrame {
    
    ArrayList<Object> INFO = new ArrayList<>();
    DefaultTableModel dm = new DefaultTableModel();
    DefaultTableModel dms = new DefaultTableModel();
    int date,month,year;
    double total=0, vat=0, price=0,finalTotal;
    String cashier_name, coustomer_id;
    String memo_name;
    int discount;
    double discount_amount;
    
     DecimalFormat formatter = new DecimalFormat("#0.00");
    
    /**
     * Creates new form Payment
     */
    public Payment() {
        initComponents();
    }
    
    public Payment(DefaultTableModel dm1, ArrayList<Object> INFO1, int date, int month, int year, double total, double vat,double price, String cashier_name) {
        initComponents();
        dm= dm1;
        INFO=INFO1;
        this.date=date;
        this.month=month;
        this.year = year;
        this.total=total;
        this.vat=vat;
        this.price=price;
        this.cashier_name=cashier_name;
    }
    
    void memo(){
        try{
        Document document=new Document();
        
        
        PdfWriter.getInstance(document,new FileOutputStream(memo_name+".pdf"));
        document.open();
        document.add(new Paragraph("                                                SuperShop",FontFactory.getFont(FontFactory.TIMES_BOLD,18,Font.BOLD,BaseColor.RED)));
        document.add(new Paragraph("                                                  "+new Date().toString()));
        document.add(new Paragraph("-------------------------------------------------------------------------------------------------------------------------"));
        document.add(new Paragraph("                                                                                                                         "));
        document.add(new Paragraph("                                                                                                                         "));
        
        PdfPTable table=new PdfPTable(5);
        
        PdfPCell cell1=new PdfPCell(new Paragraph("ID"));
        cell1.setColspan(1);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setBackgroundColor(BaseColor.YELLOW);
        table.addCell(cell1);
        
        PdfPCell cell2=new PdfPCell(new Paragraph("Name"));
        cell2.setColspan(1);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setBackgroundColor(BaseColor.YELLOW);
        table.addCell(cell2);
        
        PdfPCell cell3=new PdfPCell(new Paragraph("Weight"));
        cell3.setColspan(1);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setBackgroundColor(BaseColor.YELLOW);
        table.addCell(cell3);
        
        PdfPCell cell4=new PdfPCell(new Paragraph("Quantity"));
        cell4.setColspan(1);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setBackgroundColor(BaseColor.YELLOW);
        table.addCell(cell4);
        
        PdfPCell cell5=new PdfPCell(new Paragraph("Price"));
        cell5.setColspan(1);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell5.setBackgroundColor(BaseColor.YELLOW);
        table.addCell(cell5);
        
        int count=1;
        for(int i=0;i<dm.getRowCount();i++){
            table.addCell(""+count);
            table.addCell((String)dm.getValueAt(i,1));
            table.addCell((String)dm.getValueAt(i,5));
            table.addCell((String)dm.getValueAt(i,6));
            double taka,quantity;
            quantity= Double.parseDouble((String)dm.getValueAt(i,6));
            taka = Double.parseDouble((String)dm.getValueAt(i,3))*quantity;
            table.addCell(Double.toString(taka));
            
        }
        
        document.add(table);
        
        document.add(new Paragraph("                                                                                                                         "));
        document.add(new Paragraph("                                                                                                                         "));
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------"));
        
        double taka;
        taka = Double.parseDouble(tfGivenCash.getText())-total;
        //com.itextpdf.text.List list=new com.itextpdf.text.List();
        document.add(new Paragraph("                                                                                                       Price : "+price));
        document.add(new Paragraph("                                                                                                       Vat : "+formatter.format(vat)));
        document.add(new Paragraph("                                                                                                       Discount : "+discount_amount));
        document.add(new Paragraph("                                                                                                       Total Price : "+finalTotal));
        document.add(new Paragraph("                                                                                                       Given Price : "+tfGivenCash.getText()));
        document.add(new Paragraph("                                                                                                       Return : "+formatter.format(taka)));
        //document.add(list);
        
        document.close();
        JOptionPane.showMessageDialog(null,"Memo Saved");
        }
         catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }
    
    void removeFromStock(){
        info temp = new info();
        boolean flag= false;
        for(int i=0;i<INFO.size();i++) {
            temp =(info) INFO.get(i);
            if(new stockRemover().update(temp.id, temp.name, temp.stock, temp.price, temp.type,temp.weight,temp.buy_price,temp.barcode,temp.vat,temp.pwv)){
            
        }
        else{
                flag=true;
                JOptionPane.showMessageDialog(null,"not updated");
                break;
                
        }
        }
        if(flag!=true) JOptionPane.showMessageDialog(null,"updated");
    }
    
    void saveEarl(){
        DefaultTableModel dme = new earlUpdater().getData();
        memo_name=(String)dme.getValueAt((dme.getRowCount()-1),0);
        System.out.println(memo_name);
        int mem= Integer.parseInt(memo_name)+1;
        memo_name=Integer.toString(mem);
        
        DefaultTableModel dmd = new earlUpdater().getDiscountTable();
        boolean flag=false;
        for(int i=0;i<dmd.getRowCount();i++){
            int result=tfUserId.getText().compareToIgnoreCase((String)dmd.getValueAt(i,1));
            System.out.println(result);
            if(result==0){
                discount=Integer.parseInt((String)dmd.getValueAt(i,2));
                System.out.println(discount);
                flag=true;
                break;
            }
        }
        if(flag!=true){
            discount=0;
        }
        discount_amount=(price*discount)/100.00;
        finalTotal=(total-discount_amount);
        new earlUpdater().addEarl(memo_name,Double.toString(price), Double.toString(vat), Double.toString(discount_amount), Double.toString(finalTotal));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfUserId = new javax.swing.JTextField();
        tfGivenCash = new javax.swing.JTextField();
        bMemo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Payment");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Customer ID :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Given Cash :");

        tfUserId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfUserIdActionPerformed(evt);
            }
        });

        bMemo.setBackground(new java.awt.Color(255, 51, 51));
        bMemo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bMemo.setText("Make Memo");
        bMemo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMemoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bMemo)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tfGivenCash, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfUserId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfGivenCash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(bMemo)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tfUserIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfUserIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfUserIdActionPerformed

    private void bMemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMemoActionPerformed
        System.out.println("in memo button");
        dms.addColumn("ID");
        dms.addColumn("Name");
        dms.addColumn("CustomarCode");
        dms.addColumn("CashierName");
        dms.addColumn("Date");
        dms.addColumn("Month");
        dms.addColumn("Year");
        dms.addColumn("Barcode");
        
        for(int i=0;i<dm.getRowCount();i++){
            int p= Integer.parseInt((String)dm.getValueAt(i,6));//quantity is in number 6 in the dm table
             System.out.println("in loop");
            for(int j=0;j<p;j++){
                
                infoForSell temp = new infoForSell();
                temp.id=(String)dm.getValueAt(i,0);
                temp.name=(String)dm.getValueAt(i,1);
                temp.customer_code=tfUserId.getText();
                temp.cashier_name=cashier_name;
                temp.date=Integer.toString(date);
                temp.month=Integer.toString(month);
                temp.year=Integer.toString(year);
                temp.barcode=(String)dm.getValueAt(i,7);//barcode is in number 7
                
                dms.addRow(new Object[]{temp.id,temp.name,temp.customer_code,temp.cashier_name,temp.date,temp.month,temp.year,temp.barcode});
                
            }
        }
        boolean flag=false;
        for(int i=0;i<dms.getRowCount();i++){
            
            if(new paymentUpdater().addSell((String)dms.getValueAt(i,1),(String)dms.getValueAt(i,2),(String)dms.getValueAt(i,3),(String)dms.getValueAt(i,4),(String)dms.getValueAt(i,5),(String)dms.getValueAt(i,6),(String)dms.getValueAt(i,7))){
            
            }
            else{
                
                flag=true;
            }
            
            if(flag){
                JOptionPane.showMessageDialog(null,"Not Saved");
                break;
            }    
        }
        if(flag!=true)JOptionPane.showMessageDialog(null,"Successfuly Added");
        
        saveEarl();
        memo();
        
        removeFromStock();
        
        SellPage obj = new SellPage(cashier_name);
        obj.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_bMemoActionPerformed

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
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Payment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bMemo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tfGivenCash;
    private javax.swing.JTextField tfUserId;
    // End of variables declaration//GEN-END:variables
}

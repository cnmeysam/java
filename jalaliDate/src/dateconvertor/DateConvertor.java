/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dateconvertor;

import java.awt.ComponentOrientation;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;





/**
 *
 * @author Admin
 */
public class DateConvertor extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public DateConvertor() {
        initComponents();
        combodate.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jtxtmiadi = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtxtshamsi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jformatdate = new javax.swing.JFormattedTextField();
        combodate = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("dateconvertor"); // NOI18N
        setResizable(false);

        jButton1.setFont(new java.awt.Font("B Nazanin", 1, 18)); // NOI18N
        jButton1.setText("تبدیل");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jtxtmiadi.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("B Nazanin", 1, 14)); // NOI18N
        jLabel1.setText("میلادی");

        jLabel2.setFont(new java.awt.Font("B Nazanin", 1, 14)); // NOI18N
        jLabel2.setText("شمسی");

        jtxtshamsi.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("B Nazanin", 1, 14)); // NOI18N
        jLabel3.setText("ثبت تاریخ");

        try {
            jformatdate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####/##/##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jformatdate.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N

        combodate.setFont(new java.awt.Font("B Nazanin", 1, 14)); // NOI18N
        combodate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "شمسی به میلادی", "میلادی به شمسی" }));

        jLabel4.setFont(new java.awt.Font("B Nazanin", 1, 14)); // NOI18N
        jLabel4.setText("تبدیل");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jtxtmiadi)
                    .addComponent(jtxtshamsi, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jformatdate, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combodate, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jButton1)
                .addGap(119, 119, 119))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combodate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jformatdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtmiadi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtshamsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(304, 283));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here: "yyyy/MM/dd" "yyyy/MM/dd hh:mm:ss"
        
        //شروع گرفتن اعداد قبل از اسلش از تکست باکس
        
        
        String yearsubstr = "";
        String year = jformatdate.getText();
        String monsubstr = "";
        String mon = jformatdate.getText();
        String daysubstr = "";
        String day = jformatdate.getText();
        
        
        
        yearsubstr = year.substring(0, 4);
        monsubstr = mon.substring(5, 7);
        daysubstr = day.substring(8, 10);
        //پایان گرفتن اعداد قبل از اسلش از تکست باکس
       

        
        int y=Integer.parseInt(yearsubstr);  
        int m=Integer.parseInt(monsubstr);  
        int d=Integer.parseInt(daysubstr);  

        System.out.println("DATE= "+y+"/"+m+"/"+d);

        
        
        
     // دریافت زمان کنونی سیستم
        Calendar cal = Calendar.getInstance();

        int mYear = cal.get(Calendar.YEAR);
        int mMonth = cal.get(Calendar.MONTH) + 1;
        int mDay = cal.get(Calendar.DAY_OF_MONTH);

        shamsi jCal = new shamsi(); // ایجاد یک نمونه از کلاس تبدیل تاریخ
        jCal.GregorianToPersian(mYear, mMonth, mDay); // دادن تاریخ کنونی سیستم به متد تبدیل تاریخ میلادی به شمسی

        if((String)combodate.getSelectedItem()=="شمسی به میلادی")
        {
            //تبدیل تاریخ شمسی به میلادی:
        jCal.PersianToGregorian(y,m,d);
        System.out.println("تبدیل تاریخ شمسی به میلادی:" + jCal.toString());
        jtxtmiadi.setText(jCal.toString());
        }
        
        
        if((String)combodate.getSelectedItem()=="میلادی به شمسی")
        {
            //تبدیل تاریخ میلادی به شمسی:
        jCal.GregorianToPersian(y,m,d);
        jCal.toString();
        System.out.println("تبدیل تاریخ میلادی به شمسی: :" + jCal.toString());
        jtxtshamsi.setText(jCal.toString());
        }

        

        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(DateConvertor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DateConvertor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DateConvertor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DateConvertor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DateConvertor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combodate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JFormattedTextField jformatdate;
    private javax.swing.JTextField jtxtmiadi;
    private javax.swing.JTextField jtxtshamsi;
    // End of variables declaration//GEN-END:variables
}

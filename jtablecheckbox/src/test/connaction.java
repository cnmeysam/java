/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import java.sql.*;
/**
 *
 * @author IT_Armin
 */
public class connaction extends javax.swing.JFrame {
Image getIcon()
        { 
        return Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Picture_Icons/Connect.png"));
        }
    /**
     * Creates new form connaction
     */
    public connaction() {
        initComponents();
        //تغییر آیکن برنامه
        setIconImage(getIcon());
        //تغییر آیکن برنامه
        
        this.setAlwaysOnTop (true);
        
    }
//        private void Datecheker() {
//            try
//        {
//            File file = new File("lib\\Date.inf");
//            if(!file.exists())
//            {
//                file.createNewFile();
//                System.out.println("creating new file");
//            }
//            else
//            {
//                System.out.println("updating file");
//            }
//            BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
//            
//            
//
//                //کد هش کردن
//  String strip = txtdateserver.getText();        
//     byte[] encryptArray = Base64.encodeBase64(strip.getBytes());        
//     String encstr;   
//        try {
//            encstr = new String(encryptArray,"UTF-8");
//            
//         
//     
////کد هش کردن
//                
//                //jdbc:mysql://localhost:3306/testconection?autoReconnect=true&useSSL=false
//                buffer.write(encstr);
//                buffer.close();
//                System.out.println("finish writing to file");
//                lblerror.setText("ssl connection registered");
//                }catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(connaction.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        }
//                catch(IOException e)
//                {
//                    e.printStackTrace();
//                    Logger.getLogger(this.getName()).log(Level.SEVERE, null, e);
//                }
//
//        }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblerror = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtip = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtdatabase = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtpass = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtport = new javax.swing.JTextField();
        checkssl = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblerror.setFont(new java.awt.Font("Cambria", 3, 12)); // NOI18N
        lblerror.setForeground(new java.awt.Color(255, 0, 51));
        getContentPane().add(lblerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 330, 30));

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("IP:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));
        getContentPane().add(txtip, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 130, -1));

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("DB_Name:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));
        getContentPane().add(txtdatabase, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 130, -1));

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("User:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));
        getContentPane().add(txtuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 130, -1));

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Password:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));
        getContentPane().add(txtpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 130, -1));

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Port:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));
        getContentPane().add(txtport, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 70, -1));

        checkssl.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        checkssl.setForeground(new java.awt.Color(255, 255, 255));
        checkssl.setText("SSL");
        checkssl.setContentAreaFilled(false);
        getContentPane().add(checkssl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jButton1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jButton1.setText("Test Connevtion");
        jButton1.setToolTipText("Test Connevtion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 140, -1));

        jButton2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jButton2.setText("Register");
        jButton2.setToolTipText("Register");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setForeground(new java.awt.Color(255, 0, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icons/Close.png"))); // NOI18N
        jButton3.setToolTipText("Close");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 20, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Back_Form/Database.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(632, 319));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
         try
        {
            File file = new File("lib\\Miscsied.jar");
            if(!file.exists())
            {
                file.createNewFile();
                System.out.println("creating new file");
            }
            else
            {
                System.out.println("updating file");
            }
            BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
            
            if (checkssl.isSelected())
            {

                //کد هش کردن
  String strip = "jdbc:mysql://"+txtip.getText()+":"+txtport.getText()+""
                    + "/"+txtdatabase.getText()+"?user="+txtuser.getText()+""
                    + "&password="+txtpass.getText()+"&useSSL=true&characterEncoding=UTF-8";        
     byte[] encryptArray = Base64.encodeBase64(strip.getBytes());        
     String encstr;   
        try {
            encstr = new String(encryptArray,"UTF-8");
            
         
     
//کد هش کردن
                
                //jdbc:mysql://localhost:3306/testconection?autoReconnect=true&useSSL=false
                buffer.write(encstr);
                buffer.close();
                System.out.println("finish writing to file");
                lblerror.setText("ssl connection registered");
                }catch (UnsupportedEncodingException ex) {
            Logger.getLogger(connaction.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
            else
            {
                 //کد هش کردن
  String strip = "jdbc:mysql://"+txtip.getText()+":"+txtport.getText()+""
                    + "/"+txtdatabase.getText()+"?user="+txtuser.getText()+""
                    + "&password="+txtpass.getText()+"&useSSL=false&characterEncoding=UTF-8";        
     byte[] encryptArray = Base64.encodeBase64(strip.getBytes());        
     String encstr;   
        try {
            encstr = new String(encryptArray,"UTF-8");
            
         
     
//کد هش کردن
                
                
                buffer.write(encstr);
                buffer.close();
                System.out.println("finish writing to file");
                lblerror.setText("Connection registered");
                }catch (UnsupportedEncodingException ex) {
            Logger.getLogger(connaction.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
        

        }
        catch(IOException e)
        {
            e.printStackTrace();
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, e);
        }	
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

if (checkssl.isSelected()){
    try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://"+txtip.getText()+":"+txtport.getText()+""
                    + "/"+txtdatabase.getText()+"?user="+txtuser.getText()+""
                    + "&password="+txtpass.getText()+"&useSSL=true&characterEncoding=UTF-8")) {

            if (conn != null) {
                System.out.println("Connected to the database!");
                lblerror.setText("");
                lblerror.setText("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
                lblerror.setText("");
                lblerror.setText("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
           lblerror.setText(String.valueOf(e.getSQLState())+String.valueOf(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            lblerror.setText(String.valueOf(e));
        }
}
else{
    try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://"+txtip.getText()+":"+txtport.getText()+""
                    + "/"+txtdatabase.getText()+"?user="+txtuser.getText()+""
                    + "&password="+txtpass.getText()+"&useSSL=false&characterEncoding=UTF-8")) {

            if (conn != null) {
                System.out.println("Connected to the database!");
                lblerror.setText("");
                lblerror.setText("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
                lblerror.setText("");
                lblerror.setText("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            lblerror.setText(String.valueOf(e.getSQLState())+String.valueOf(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            lblerror.setText(String.valueOf(e));
        }
}

    


//String url = "jdbc:mysql://"+txtip.getText()+":"+txtport.getText();
//         //pointing to no database.
//    String username = txtuser.getText();
//    String password = txtpass.getText();
//
//    System.out.println("Connecting to server...");
//
//    try (Connection connection = DriverManager.getConnection(url, username, password)) {
//        System.out.println("Server connected!");
//        Statement stmt = null;
//        ResultSet resultset = null;
//
//        try {
//            stmt = connection.createStatement();
//            resultset = stmt.executeQuery("SHOW DATABASES;");
//
//            if (stmt.execute("SHOW DATABASES;")) {
//                resultset = stmt.getResultSet();
//            }
//
//            while (resultset.next()) {
//                System.out.println(resultset.getString("Database"));
//            }
//        }
//        catch (SQLException ex){
//            // handle any errors
//            ex.printStackTrace();
//        }
//        finally {
//            // release resources
//            if (resultset != null) {
//                try {
//                    resultset.close();
//                } catch (SQLException sqlEx) { }
//                resultset = null;
//            }
//
//            if (stmt != null) {
//                try {
//                    stmt.close();
//                } catch (SQLException sqlEx) { }
//                stmt = null;
//            }
//
//            if (connection != null) {
//                connection.close();
//            }
//        }
//    } catch (SQLException e) {
//        throw new IllegalStateException("Cannot connect the server!", e);
//    }
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
            java.util.logging.Logger.getLogger(connaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(connaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(connaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(connaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new connaction().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkssl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblerror;
    private javax.swing.JTextField txtdatabase;
    private javax.swing.JTextField txtip;
    private javax.swing.JTextField txtpass;
    private javax.swing.JTextField txtport;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.security.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.UIManager;
import org.apache.commons.codec.binary.Base64;
import java.awt.Color;
import java.awt.Image;

/**
 *
 * @author Admin
 */
public class frmlogin extends javax.swing.JFrame {

    /**
     * Creates new form frmlogin
     */
    Image getIcon()
        { 
        return Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Icons/login.jpg"));
        }
    public frmlogin() {
        //تغییر آیکن برنامه
        setIconImage(getIcon());
        //تغییر آیکن برنامه
        initComponents();
        combopermission.setVisible(false);
        
    }

    
      //هش کردن پسورد
    
    public static String HashPaasword(String password) throws NoSuchAlgorithmException
        {
           // MessageDigest md = MessageDigest.getInstance("MD5");
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(password.getBytes());
            byte[] b = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b1 : b )
            {
                sb.append(Integer.toHexString(b1 & 0xff).toString());
            }
            return sb.toString();
        }
      //هش کردن پسورد
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnconnection = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();
        btnenter = new javax.swing.JButton();
        txtuser = new javax.swing.JTextField();
        lbltest = new javax.swing.JLabel();
        lblhash = new javax.swing.JLabel();
        txtpass = new javax.swing.JPasswordField();
        combopermission = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Login"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        btnconnection.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        btnconnection.setText("Server connection settings");
        btnconnection.setToolTipText("Server connection settings");
        btnconnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconnectionActionPerformed(evt);
            }
        });

        btnexit.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        btnexit.setText("Exit");
        btnexit.setToolTipText("Exit");
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        btnenter.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        btnenter.setText("ok");
        btnenter.setToolTipText("ok");
        btnenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnenterActionPerformed(evt);
            }
        });

        txtuser.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txtuser.setForeground(new java.awt.Color(153, 153, 153));
        txtuser.setText("User");
        txtuser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtuserFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtuserFocusLost(evt);
            }
        });

        lbltest.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbltest.setForeground(new java.awt.Color(255, 0, 0));
        lbltest.setBorder(new javax.swing.border.MatteBorder(null));

        lblhash.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblhash.setText("jLabel1");

        combopermission.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbltest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtuser)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnenter, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50)
                                        .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnconnection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtpass))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(combopermission, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addComponent(lblhash)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(txtuser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnenter)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnexit)
                        .addComponent(combopermission, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(btnconnection)
                .addGap(11, 11, 11)
                .addComponent(lbltest, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblhash)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnexitActionPerformed

    private void txtuserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtuserFocusGained
        // TODO add your handling code here:
        //های لایت کردن تکس باکس شروع
        if (txtuser.getText().equals("User")){
            txtuser.setText("");
        }
        txtuser.setForeground(Color.red);
        //های لایت کردن تکس باکس پایان
    }//GEN-LAST:event_txtuserFocusGained

    private void txtuserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtuserFocusLost
        // TODO add your handling code here:
        //های لایت کردن تکس باکس شروع
        if (txtuser.getText().equals("")){
            txtuser.setText("User");
        }
        txtuser.setForeground(new Color(153,153,153));
        //های لایت کردن تکس باکس پایان
    }//GEN-LAST:event_txtuserFocusLost

    private void btnconnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconnectionActionPerformed
        // TODO add your handling code here:
        frmconnection connect = new frmconnection();                    
                    connect.setVisible(true);
                    this.dispose();
    }//GEN-LAST:event_btnconnectionActionPerformed

    private void btnenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnenterActionPerformed
        // TODO add your handling code here:
        
        if(txtuser.getText().equals("")){
            JOptionPane.showMessageDialog(null, "username or password can not be empty");
        }
        else if(txtpass.getText().equals("")){
            JOptionPane.showMessageDialog(null, "username or password can not be empty");
        }
        else if(txtpass.getPassword().equals("")){
            JOptionPane.showMessageDialog(null, "username or password can not be empty");    
        }
        
        else{
            permissions();
            //ارسال دسترسی به فرم اصلی
        String per = (String) combopermission.getSelectedItem();
        
        
        //ارسال دسترسی به فرم اصلی
         //هش پسورد
         String password = new String(txtpass.getPassword());
        
        try
        {
            lblhash.setText(HashPaasword(password));
        }
        catch(NoSuchAlgorithmException e){Logger.getLogger(this.getName()).log(Level.SEVERE, null, e);}
        //هش پسورد 
        
        //ورود
               try
        {
            
            //کانکشن

FileReader reader = new FileReader("lib\\Miscsied.jar");
    BufferedReader bufferedReader = new BufferedReader(reader);
    String line;
    while ((line = bufferedReader.readLine()) != null) {
        byte[] dectryptArray = line.getBytes();
     byte[] decarray = Base64.decodeBase64(dectryptArray);
     String ok;
        System.out.println(decarray);
        ok = new String(decarray,"UTF-8");
	String url; 
        url=ok;
//کانکشن
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            
        String _uname = txtuser.getText();
        String _pass = lblhash.getText();
        int rowcount = -1;
        ResultSet result = stmt.executeQuery( "SELECT COUNT(*) FROM users WHERE username='"+_uname+"' "
                + "AND password='"+_pass+"' AND active='1'");
                result.next();
                rowcount = result.getInt(1);
                if(rowcount>0)
                {
                    lbltest.setText("Welcome");
                    
//                    main.frmmain main = new main.frmmain(per); 
//                    
//                    main.setVisible(true);
                    new main.frmmain(per).setVisible(true); 
//                    main.frmmain frmparrent = new main.frmmain();                    
//                    frmparrent.setVisible(true);
                   
                    this.dispose();
                }
                else
                {
                    lbltest.setText("User Name Or Password Is Not Valid Or User Is DeActivated");
                }

            }
        }
        catch (Exception ex)
                {
                 String jError = "";
                    jError = ex.getMessage();
            if (jError.contains("Miscsied.jar")) {
            //System.out.println("This Record Reference Is In Use In Another Form");
            JOptionPane.showMessageDialog(null, "check you'r Server connection Settings");
        }
         
                    jError = ex.getMessage();
            if (jError.contains("The last packet")) {
            //System.out.println("This Record Reference Is In Use In Another Form");
            JOptionPane.showMessageDialog(null, "Can Not Connect To Server");
        }  
            
            
            
            
            else{
                    
                //JOptionPane.showMessageDialog(null, ex.getMessage());
                Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
                }
                }
        //ورود
        }
    }//GEN-LAST:event_btnenterActionPerformed
    
    
    
    Connection con;
    Statement st;
    ResultSet rs; 
    private void permissions() {
       
         combopermission.removeAllItems();  
         try
    {
           //کد دی هش
			FileReader reader = new FileReader("lib\\Miscsied.jar");
            BufferedReader bufferedReader = new BufferedReader(reader);
String strDec ;
while ((strDec = bufferedReader.readLine()) != null){
     byte[] dectryptArray = strDec.getBytes();
     byte[] decarray = Base64.decodeBase64(dectryptArray);
     String ok; 
        try {
            ok = new String(decarray,"UTF-8");
			String url; 
            url=ok;
//کد دی هش
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url);
            Statement st =con.createStatement();
            st = con.createStatement();
            String s = "SELECT code FROM users WHERE username='"+txtuser.getText()+"'";
            rs = st.executeQuery(s);
          
while(rs.next())
                {
                    //1=id and 2=username and 3=password
                    combopermission.addItem(rs.getString(1));
                    
                }  
				}			
//کد دی هش			
         catch (UnsupportedEncodingException ex) {
            Logger.getLogger(frmlogin.class.getName()).log(Level.SEVERE, null, ex);
        }
		
//کد دی هش
        
				}
    }
       catch (Exception ex)
                {
                //JOptionPane.showMessageDialog(null, "تنظیمات اتصال به سرور صحیح نمیباشد");
                Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
                }
	}
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
         setResizable(false);
        //مخفی کردن لیبل هش
        lblhash.setVisible(false);
        //مخفی کردن لیبل هش
       // lblserver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //کلید پیشفرض
        this.getRootPane().setDefaultButton(btnenter);
        //با آلت و ای خارج میشه
       btnexit.setMnemonic(KeyEvent.VK_E);
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(frmlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmlogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnconnection;
    private javax.swing.JButton btnenter;
    private javax.swing.JButton btnexit;
    private javax.swing.JComboBox<String> combopermission;
    private javax.swing.JLabel lblhash;
    private javax.swing.JLabel lbltest;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables
}
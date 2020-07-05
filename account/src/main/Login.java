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
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.net.Socket;


import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.scene.input.KeyCode;
/**
 *
 * @author IT_Armin
 */
public class Login extends javax.swing.JFrame {
Image getIcon()
        { 
        return Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Picture/login.png"));
        }
    /**
     * Creates new form Login
     */


 private void Datecheker() {
//     SELECT CURDATE();
                 //هش پسورد 
        try {
            
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
//reader.close();
            
            //String url =line;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url);

        //جلوگیری از تکرار
        Statement stmt = con.createStatement();
        int rowcount = -1;
        ResultSet result = stmt.executeQuery( "SELECT CURDATE() as mydate ");
        //select count(ID) from users WHERE (code='9' OR username='9') and ID NOT IN (34)
        result.next();
        
//       lbldate.setText("mydate: " + result.getString(1));
         System.out.println("rowcount= "+rowcount);
        //stmt.close();
        
        
         System.out.println("mydate= "+result.getString(1));
        String da1 = result.getString(1);
//         lbldate.setText( da1);
         String s1 = da1;
         String replaceString=s1.replace('-','/');//replaces all occurrences of 'a' to 'e'  
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//        String _StartDate = formatter.format(da1);
        lbldate.setText( replaceString);
        
        
        con.close();
            }
              }catch (Exception ex) {
                   JOptionPane.showMessageDialog(null, "Server connection faild");
                System.out.println("Found some error : "+ex);
                }
            

 }
 
 private void usertimecheker() {
     
//     SELECT CURDATE();
                 //هش پسورد 
        try {
            
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
//reader.close();
            
            //String url =line;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url);

        //جلوگیری از تکرار
        Statement stmt = con.createStatement();
        int rowcount = -1;
        ResultSet result = stmt.executeQuery( "SELECT `endtime`  from users where username= '"+txtuser.getText()+"'  ");
        //select count(ID) from users WHERE (code='9' OR username='9') and ID NOT IN (34)
        result.next();
        
//       lbldate.setText("mydate: " + result.getString(1));
         System.out.println("rowcount= "+rowcount);
        //stmt.close();
        
        
         System.out.println("enddate= "+result.getString(1));
        String da1 = result.getString(1);
//         lbldate.setText( da1);
         String s1 = da1;
         String replaceString=s1.replace('-','/');//replaces all occurrences of 'a' to 'e'  
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//        String _StartDate = formatter.format(da1);
        _time=( replaceString);
        System.out.println("_time : "+_time);
        
        con.close();
            }
              }catch (Exception ex) {
                  String jError = "";
                    jError = ex.getMessage();
                   
                   if (jError.contains("operation on empty")) {
            //System.out.println("This Record Reference Is In Use In Another Form");
            JOptionPane.showMessageDialog(null, "User Not Found");
                   
//                   JOptionPane.showMessageDialog(null, "Server Connection Fail userDatecheker");
                
                }
                    else{
//                       JOptionPane.showMessageDialog(null, ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.getMessage());
                System.out.println("Found some error : "+ex);
            }
        }
           

 }
 
 
 private void userDatecheker() {
     usertimecheker() ;
     if(_time.equals("1")){
//     SELECT CURDATE();
                 //هش پسورد 
        try {
            
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
//reader.close();
            
            //String url =line;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url);

        //جلوگیری از تکرار
        Statement stmt = con.createStatement();
        int rowcount = -1;
        ResultSet result = stmt.executeQuery( "SELECT enddate  from users where username= '"+txtuser.getText()+"'  ");
        //select count(ID) from users WHERE (code='9' OR username='9') and ID NOT IN (34)
        result.next();
        
//       lbldate.setText("mydate: " + result.getString(1));
         System.out.println("rowcount= "+rowcount);
        //stmt.close();
        
        
         System.out.println("enddate= "+result.getString(1));
        String da1 = result.getString(1);
//         lbldate.setText( da1);
         String s1 = da1;
         String replaceString=s1.replace('-','/');//replaces all occurrences of 'a' to 'e'  
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//        String _StartDate = formatter.format(da1);
        _users=( replaceString);
        
        
        con.close();
            }
              }catch (Exception ex) {
                  String jError = "";
                    jError = ex.getMessage();
                   
                   if (jError.contains("operation on empty")) {
            //System.out.println("This Record Reference Is In Use In Another Form");
            JOptionPane.showMessageDialog(null, "User Not Found");
                   
//                   JOptionPane.showMessageDialog(null, "Server Connection Fail userDatecheker");
                
                }
                    else{
//                       JOptionPane.showMessageDialog(null, ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.getMessage());
                System.out.println("Found some error : "+ex);
            }
        }
     } 
     else{
        _users="";
     }

 }
    public Login() {
        initComponents();
        //تغییر آیکن برنامه
        setIconImage(getIcon());
        //تغییر آیکن برنامه
         combopermission.setVisible(false);
         lblhash.setVisible(false);
         rootPane.setDefaultButton(BtnLogin);
         Datecheker();
    }
    
    
    
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
            String s = "SELECT CONCAT (code,\" \", fname,\" \",lname ) FROM users WHERE username='"+txtuser.getText()+"'";
            rs = st.executeQuery(s);
          
while(rs.next())
                {
                    //1=id and 2=username and 3=password
                    combopermission.addItem(rs.getString(1));
                    
                }  
				}			
//کد دی هش			
         catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
		
//کد دی هش
		
//کد دی هش
        
				}
    }
       catch (Exception ex)
                {
                //JOptionPane.showMessageDialog(null, "تنظیمات اتصال به سرور صحیح نمیباشد");
                Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());
                }
        
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

        combopermission = new javax.swing.JComboBox<String>();
        lbldate = new javax.swing.JLabel();
        lblhash = new javax.swing.JLabel();
        lbltest = new javax.swing.JLabel();
        txtpass = new javax.swing.JTextField();
        txtuser = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        BtnLogin = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        combopermission.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(combopermission, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, -1, -1));

        lbldate.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        lbldate.setForeground(new java.awt.Color(255, 0, 51));
        getContentPane().add(lbldate, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 180, 20));

        lblhash.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblhash.setText("jLabel1");
        getContentPane().add(lblhash, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, -1, -1));

        lbltest.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbltest.setForeground(new java.awt.Color(255, 0, 0));
        lbltest.setBorder(new javax.swing.border.MatteBorder(null));
        getContentPane().add(lbltest, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 310, 380, 40));

        txtpass.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txtpass.setForeground(new java.awt.Color(153, 153, 153));
        txtpass.setText("Password   ");
        txtpass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtpassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtpassFocusLost(evt);
            }
        });
        getContentPane().add(txtpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 200, 30));

        txtuser.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txtuser.setForeground(new java.awt.Color(153, 153, 153));
        txtuser.setText("User   ");
        txtuser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtuserFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtuserFocusLost(evt);
            }
        });
        getContentPane().add(txtuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 200, 30));

        jButton1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jButton1.setText("Server connection settings");
        jButton1.setToolTipText("Server connection settings");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 200, -1));

        BtnLogin.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        BtnLogin.setText("Login");
        BtnLogin.setToolTipText("ok");
        BtnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(BtnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 70, -1));

        jButton3.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jButton3.setText("Exit");
        jButton3.setToolTipText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 80, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Back_Form/Login.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 500));

        setSize(new java.awt.Dimension(702, 411));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtuserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtuserFocusGained
        // TODO add your handling code here:
        //های لایت کردن تکس باکس شروع
        if (txtuser.getText().equals("User   ")){
            txtuser.setText("");
        }
        txtuser.setForeground(Color.red);
        //های لایت کردن تکس باکس پایان
    }//GEN-LAST:event_txtuserFocusGained

    private void txtuserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtuserFocusLost
        // TODO add your handling code here:
        //های لایت کردن تکس باکس شروع
        if (txtuser.getText().equals("")){
            txtuser.setText("User   "); 
        }
        txtuser.setForeground(new Color(153,153,153));
        //های لایت کردن تکس باکس پایان
    }//GEN-LAST:event_txtuserFocusLost

    private void txtpassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpassFocusGained
        // TODO add your handling code here:
        if (txtpass.getText().equals("Password   ")){
            txtpass.setText("");
        }
        txtpass.setForeground(Color.red);
        //های لایت کردن تکس باکس پایان
    }//GEN-LAST:event_txtpassFocusGained

    private void txtpassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpassFocusLost
        // TODO add your handling code here:
        if (txtpass.getText().equals("")){
            txtpass.setText("Password   "); 
        }
        txtpass.setForeground(new Color(153,153,153));
        //های لایت کردن تکس باکس پایان
    }//GEN-LAST:event_txtpassFocusLost

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void userActive() {
         //هش پسورد 
        try {
            
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
//reader.close();
            
            //String url =line;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url);

        //جلوگیری از تکرار
        Statement stmt = con.createStatement();
        int rowcount = -1;
        ResultSet result = stmt.executeQuery( "SELECT active  from users where username= '"+txtuser.getText()+"'  ");
        //select count(ID) from users WHERE (code='9' OR username='9') and ID NOT IN (34)
        result.next();
        
//       lbldate.setText("mydate: " + result.getString(1));
         System.out.println("rowcount= "+rowcount);
        //stmt.close();
        
        
         System.out.println("Active= "+result.getString(1));
        String da1 = result.getString(1);
//         lbldate.setText( da1);
         String s1 = da1;
//         String replaceString=s1.replace('-','/');//replaces all occurrences of 'a' to 'e'  
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//        String _StartDate = formatter.format(da1);
        _Active=( s1);
        
        
        con.close();
            }
              }catch (Exception ex) {
                  String jError = "";
                    jError = ex.getMessage();
                   
                   if (jError.contains("operation on empty")) {
            //System.out.println("This Record Reference Is In Use In Another Form");
            JOptionPane.showMessageDialog(null, "User Not Found");
                   
//                   JOptionPane.showMessageDialog(null, "Server Connection Fail userDatecheker");
                
                }
                    else{
//                       JOptionPane.showMessageDialog(null, ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.getMessage());
                System.out.println("Found some error : "+ex);
            }
        }
        
    }
    private void userlogin() {
        userActive();
        if(_Active.equals("1"))
        {
        permissions();
            

            
            //ارسال دسترسی به فرم اصلی
        String per = (String) combopermission.getSelectedItem();
        
        
        //ارسال دسترسی به فرم اصلی
         //هش پسورد
         String password = new String(txtpass.getText());
        
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
                + "AND password='"+_pass+"' ");
//        AND active='1'
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
                    JOptionPane.showMessageDialog(null, "User Name Or Password Is Not Valid");
//                    lbltest.setText("User Name Or Password Is Not Valid Or User Is DeActivated");
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
            
                    jError = ex.getMessage();
                   
                   if (jError.contains("operation on empty")) {
            //System.out.println("This Record Reference Is In Use In Another Form");
            JOptionPane.showMessageDialog(null, "User Not Found");
                   
//                   JOptionPane.showMessageDialog(null, "Server Connection Fail userDatecheker");
                
                }
                    
  
            else{
                    
                
                Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
                }
                }
        //ورود
        
        }
        else{
           JOptionPane.showMessageDialog(null, "User Is Deactivated"); 
        }
    }
    
    private void BtnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLoginActionPerformed
        // TODO add your handling code here:
        if (lbldate.getText().equals("")) {
            Datecheker();
//            JOptionPane.showMessageDialog(null, "can not connect to server");
            
//            this.dispose();
        }
        else{
         if(txtuser.getText().equals("User   ")){
            JOptionPane.showMessageDialog(null, "username can not be empty");
        }
     
        else if(txtpass.getText().equals("Password")){
            JOptionPane.showMessageDialog(null, "password can not be empty");
        }
        else if(txtuser.getText().equals("")){
            JOptionPane.showMessageDialog(null, "username  can not be empty");
        }
     
        else if(txtpass.getText().equals("")){
            JOptionPane.showMessageDialog(null, "password can not be empty");
        }
         else {
            userDatecheker();
           if(_users.isEmpty()){
       if(_users.isEmpty()){
               userlogin(); 
               
              return;
              
            }
           
        else{
                JOptionPane.showMessageDialog(null, "User Is Deactivated");
                
            }
           }
           else{
       if (lbldate.getText().compareTo(_users) <= 0  ){
                 userlogin();
                 
                return; 
            }
        else{
            JOptionPane.showMessageDialog(null, "User Date Is Expired ");
            }
         
                   
             }
        }
        }
    }//GEN-LAST:event_BtnLoginActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//         TODO add your handling code here:
        connaction connect = new connaction();                    
                    connect.setVisible(true);
                    this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

     
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
       



        
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnLogin;
    private javax.swing.JComboBox<String> combopermission;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lblhash;
    private javax.swing.JLabel lbltest;
    private javax.swing.JTextField txtpass;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables

    String _users="";
    String _Active=""; 
    String _time="";
  
}

package Clas;


import javax.swing.JOptionPane;
import java.sql.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.codec.binary.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;


public class class_register {
    public static int check ;
  public static String classError = "";
    public void NonQuery(String sql)
    {
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
            System.out.println(ok);
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url);
            Statement st =con.createStatement();
            st.execute(sql);
            }//کد دی هش
		catch (UnsupportedEncodingException ex) {
            Logger.getLogger(class_register.class.getName()).log(Level.SEVERE, null, ex);
        }
		}
//کد دی هش

            
    }catch (Exception ex)
        {
//            CONSTRAINT
              classError = ex.getMessage();
            
            String jError = "";
                    jError = ex.getMessage();
            if (jError.contains("Miscsied.jar")) {
            //System.out.println("This Record Reference Is In Use In Another Form");
            JOptionPane.showMessageDialog(null, "check you'r Server connection Settings");
        }
            else if (jError.contains("Cannot delete or update a parent row")) {
            //System.out.println("This Record Reference Is In Use In Another Form");
            JOptionPane.showMessageDialog(null, "This Record Reference Is In Use In Another Form");
            check=1;
        }
             else if (jError.contains("The last packet")) {
            //System.out.println("This Record Reference Is In Use In Another Form");
            JOptionPane.showMessageDialog(null, "Can Not Connect To Server");
        }  
            else{
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        
    
}
    }
}

package Clas;


import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;


public class classpaging {
    
    public static Connection con = null;
    public static Statement stmt = null;
    public static ResultSet resSet = null;
    public static String url = null;
    
    public static ResultSet getResultSet()
    {
        
        return resSet;
    }
    
    public static Connection getcoConnection()
    {
        return con;
    }
    
    public static boolean connect_to_db()
    {
        
        try{
            FileReader reader = new FileReader("lib\\Miscsied.jar");
            BufferedReader bfr = new BufferedReader(reader);
            String strDec ;
            
            while ((strDec = bfr.readLine())!=null)
            {
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
            con = DriverManager.getConnection(url);
            return true;
            
        }
                 //کد دی هش
		catch (UnsupportedEncodingException ex) {
            Logger.getLogger(classpaging.class.getName()).log(Level.SEVERE, null, ex);
        }reader.close();
		}
//کد دی هش
            }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
        return false;
        
    }
    
    public static boolean query_to_db( String _query)
    {
        try
        {
            stmt = con.createStatement();
            resSet = stmt.executeQuery(_query);
            if (resSet.next())
            {
                return true;
            }
            else
            {
                return false;
            }
            
        }
        catch (Exception e)
        {
            String jError = "";
                    jError = e.getMessage();
             if (jError.contains("Miscsied.jar")) {
            //System.out.println("This Record Reference Is In Use In Another Form");
            JOptionPane.showMessageDialog(null, "check you'r Server connection Settings");
        }
            else if (jError.contains("The last packet")) {
            //System.out.println("This Record Reference Is In Use In Another Form");
            JOptionPane.showMessageDialog(null, "Can Not Connect To Server");
        }  
//            else if(jError.contains("shift")){
//                JOptionPane.showMessageDialog(null, "This user in use shift form");
//            }
             else{
            JOptionPane.showMessageDialog(null, e.getMessage());
            
             }
             return false;
        }
    
    }
    
    
     
    
}
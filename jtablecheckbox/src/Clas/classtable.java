package Clas;


import javax.swing.JOptionPane;
import java.sql.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;




public class classtable {
    
  
    
    

     public DefaultTableModel Query(String sql)
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
    System.out.println(ok + "  classtbl");
                
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url);
            Statement st =con.createStatement();
            st.executeQuery(sql);
            ResultSet rs = st.getResultSet();
             //System.out.println(rs + "  rsclasstbl");
            ResultSetMetaData rsmd = rs.getMetaData();
            //System.out.println(rsmd + "  rsmdclasstbl");
            
            int col_num = rsmd.getColumnCount();
            
            DefaultTableModel dtm = new DefaultTableModel();
            
            for (int i =1 ; i<=col_num;i++)
            {
                dtm.addColumn(rsmd.getColumnName(i));
               // System.out.println(dtm.getValueAt(0, 0) + "  classdtm.getValueAt(0, 0)");
            }
              while(rs.next()) 
              {
                  Object[] data = new Object[col_num];
                  for (int i=0;i<col_num;i++)
                  {
                      data[i]= rs.getString(i+1);
                      
                  }
                  dtm.addRow(data);
                  
              }
              
              rs.close();
              st.close();
              con.close();
              
              
              return dtm;
            }
        //کد دی هش
		catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
//                    String jError = "";
//                    jError = ex.getMessage();
            Logger.getLogger(classtable.class.getName()).log(Level.SEVERE, null, ex);
            
        }
		}
//کد دی هش
        
           reader.close();
        }catch (Exception ex)
        {
            
                    String jError = "";
                    jError = ex.getMessage();
            if (jError.contains("Miscsied.jar")) {
            //System.out.println("This Record Reference Is In Use In Another Form");
            JOptionPane.showMessageDialog(null, "check you'r Server connection Settings");
        }
             else if (jError.contains("The last packet")) {
            //System.out.println("This Record Reference Is In Use In Another Form");
            JOptionPane.showMessageDialog(null, "Can Not Connect To Server");
        }  
             else if (jError.contains("Cannot delete or update a parent row")) {
            //System.out.println("This Record Reference Is In Use In Another Form");
            JOptionPane.showMessageDialog(null, "This Record Reference Is In Use In Another Form");
        }
            else{
               	
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        return null;
    }
     
     
        
    
    
}

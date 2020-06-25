/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dateserversocket;

import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class DateServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException {
        // TODO code application logic here
        
        //2
        try {

          ServerSocket ss= new ServerSocket(5217);
        while(true)
        {
            
             System.out.println("The Server has reserved port No.: "+ss.getLocalPort()+" for this Service");
              Socket cs=ss.accept();
              System.out.println("Client with IP Address "+cs.getInetAddress()+" has communicated via port No.: "+cs.getPort());
//               java.util.Date date1;
            java.util.Date d=new java.util.Date();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
        String dt1 = formatter1.format(d);
       
//        String s="Current Date & Time on Server is:"+dt1;
        String s = dt1;
       
        //Send String s to client via client socket
        PrintWriter toclient=new PrintWriter(cs.getOutputStream(),true);
        toclient.print(s);
        
        toclient.close();
        cs.close();
//        ss.close();

        }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        //2
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        try {
//        //Step 1. Reserve a port number on the Server to offer this service
//        ServerSocket ss= new ServerSocket(5217);
//         while(true)
//        {
//        //(Optional)To confirm Server Reserved specified port or not
//        System.out.println("The Server has reserved port No.: "+ss.getLocalPort()+" for this Service");
//        
//        //Step 2. Now create a Client Socket on Server for Bidirectonal Communication.
//        //Socket is created only when client communicates with the server
//        Socket cs=ss.accept();
//        
//        //To confirm Server communicated through the socket or not
//        
//        System.out.println("Client with IP Address "+cs.getInetAddress()+" has communicated via port No.: "+cs.getPort());
//        java.util.Date date1;
//        
//        Date d=new Date();
//        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
//        String dt1 = formatter1.format(d);
//        String s="Current Date & Time on Server is:"+dt1;
//        
//        //Send String s to client via client socket
//        PrintWriter toclient=new PrintWriter(cs.getOutputStream(),true);
//        toclient.print(s);
//        
//        toclient.close();
//        cs.close();
//        ss.close();
//        }
//   
//    } catch (Exception ex)
//        {
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
        }
       
}
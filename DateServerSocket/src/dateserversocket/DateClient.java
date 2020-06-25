/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dateserversocket;

import java.net.*;
import java.io.*;

/**
 *
 * @author YogeshD
 */
public class DateClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException,IOException {
        // TODO code application logic here
        //Step 1. Create a client socket to connect to Server
        Socket cs= new Socket("192.168.1.10",5217);
        
        //To confirm Client is communicating through the port
        System.out.println("Client "+cs.getInetAddress()+" is communicating from port No.: "+cs.getPort());
        
        //Receive Date Sent by Server
        BufferedReader fromserver=new BufferedReader(new InputStreamReader(cs.getInputStream()));
        
        System.out.println(fromserver.readLine());
        fromserver.close();
        cs.close();
        
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clas;
import javax.swing.JInternalFrame;

import accounts.GroupsAccess;
import accounts.Users;
import accounts.Shift;
import accounts.Positions;
import accounts.Positions_1;
import accounts.Shift_Type;
import accounts.ChangePassword;




/**
 *
 * @author Administrat0r
 */
public class class_singleton {
public static JInternalFrame jifGroupsAccess;
public static JInternalFrame jifusers;
public static JInternalFrame jifShift;
public static JInternalFrame jifShift_Type;
public static JInternalFrame jifPositions;
public static JInternalFrame jifPositions_1;
public static JInternalFrame jifchangepassword;
    

    
    public class_singleton(){
        }
    
    public static synchronized  JInternalFrame GroupsAccess(){
        if(jifGroupsAccess == null){
        jifGroupsAccess = new GroupsAccess();
        jifGroupsAccess.toFront();
        }
        return jifGroupsAccess ;
        
            }
    
    
    public static synchronized  JInternalFrame Users(){
        if(jifusers == null){
        jifusers = new Users();
        jifusers.toFront();
        }
        return jifusers ;
        
            }
    
     public static synchronized  JInternalFrame Shift(){
        if(jifShift == null){
        jifShift = new Shift();
        jifShift.toFront();
        }
        return jifShift ;
        
            }
     
     public static synchronized  JInternalFrame Shift_Type(){
        if(jifShift_Type == null){
        jifShift_Type = new Shift_Type();
        jifShift_Type.toFront();
        }
        return jifShift_Type ;
        
            }
   
    public static synchronized  JInternalFrame Positions(){
        if(jifPositions == null){
        jifPositions = new Positions();
        jifPositions.toFront();
        }
        return jifPositions ;
        
            }
    
    public static synchronized  JInternalFrame Positions_1(){
        if(jifPositions_1 == null){
        jifPositions_1 = new Positions_1();
        jifPositions_1.toFront();
        }
        return jifPositions_1 ;
        
            }
   
     public static synchronized  JInternalFrame ChangePassword(){
        if(jifchangepassword == null){
        jifchangepassword = new ChangePassword();
        jifchangepassword.toFront();
        }
        return jifchangepassword ;
        
            }

    
}

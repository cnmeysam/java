/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JInternalFrame;

/**
 *
 * @author Admin
 */
public class frmmain extends javax.swing.JFrame {

    
Image getIcon()
        { 
        return Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Icons/Final price.png"));
        }
    /**
     * Creates new form frmmain
     */
    public frmmain() {
        //تغییر آیکن برنامه
        setIconImage(getIcon());
        //تغییر آیکن برنامه
        initComponents();
        setExtendedState(frmmain.MAXIMIZED_BOTH);
       
    }

    //دریافت شماره پرونده از فرم قبلی
    public frmmain (String per){
	initComponents();
        menuuser.setText(per);
    
	}
    
    
    //دریافت شماره پرونده از فرم قبلی
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DesktopPane = new javax.swing.JDesktopPane();
        jCPanel = new com.bolivia.panel.JCPanel();
        jScrollPane = new javax.swing.JScrollPane();
        jXTaskPaneContainer = new org.jdesktop.swingx.JXTaskPaneContainer();
        jXTaskPane = new org.jdesktop.swingx.JXTaskPane();
        lblpassword = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblgroupaccess = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblposition = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblshifttype = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        lblusers = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        lblshift = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        menuuser = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main");
        setName("frmmain"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        javax.swing.GroupLayout DesktopPaneLayout = new javax.swing.GroupLayout(DesktopPane);
        DesktopPane.setLayout(DesktopPaneLayout);
        DesktopPaneLayout.setHorizontalGroup(
            DesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );
        DesktopPaneLayout.setVerticalGroup(
            DesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jCPanel.setVisibleLogo(false);

        jScrollPane.setBackground(new java.awt.Color(117, 150, 227));
        jScrollPane.setForeground(new java.awt.Color(117, 150, 227));

        jXTaskPaneContainer.setForeground(new java.awt.Color(117, 150, 227));
        org.jdesktop.swingx.VerticalLayout verticalLayout1 = new org.jdesktop.swingx.VerticalLayout();
        verticalLayout1.setGap(14);
        jXTaskPaneContainer.setLayout(verticalLayout1);

        jXTaskPane.setTitle("Basic Information");
        jXTaskPane.setToolTipText("");
        jXTaskPane.setFont(new java.awt.Font("Cambria", 3, 16)); // NOI18N

        lblpassword.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblpassword.setText("Change My Password");
        lblpassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblpassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblpasswordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblpasswordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblpasswordMouseExited(evt);
            }
        });
        jXTaskPane.getContentPane().add(lblpassword);

        jSeparator1.setMinimumSize(new java.awt.Dimension(20, 5));
        jSeparator1.setPreferredSize(new java.awt.Dimension(20, 5));
        jXTaskPane.getContentPane().add(jSeparator1);

        lblgroupaccess.setBackground(new java.awt.Color(204, 204, 204));
        lblgroupaccess.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblgroupaccess.setText("Group Access");
        lblgroupaccess.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblgroupaccess.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblgroupaccessMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblgroupaccessMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblgroupaccessMouseExited(evt);
            }
        });
        jXTaskPane.getContentPane().add(lblgroupaccess);
        jXTaskPane.getContentPane().add(jSeparator2);

        lblposition.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblposition.setText("Positions");
        lblposition.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblposition.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblpositionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblpositionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblpositionMouseExited(evt);
            }
        });
        jXTaskPane.getContentPane().add(lblposition);
        jXTaskPane.getContentPane().add(jSeparator3);

        lblshifttype.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblshifttype.setText("Shift Type");
        lblshifttype.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblshifttype.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblshifttypeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblshifttypeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblshifttypeMouseExited(evt);
            }
        });
        jXTaskPane.getContentPane().add(lblshifttype);
        jXTaskPane.getContentPane().add(jSeparator4);

        lblusers.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblusers.setText("Users");
        lblusers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblusers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblusersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblusersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblusersMouseExited(evt);
            }
        });
        jXTaskPane.getContentPane().add(lblusers);
        jXTaskPane.getContentPane().add(jSeparator5);

        lblshift.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblshift.setText("Shift");
        lblshift.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblshift.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblshiftMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblshiftMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblshiftMouseExited(evt);
            }
        });
        jXTaskPane.getContentPane().add(lblshift);
        jXTaskPane.getContentPane().add(jSeparator6);

        jLabel1.setText("Positions_1 jtable checkbox test");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jXTaskPane.getContentPane().add(jLabel1);

        jXTaskPaneContainer.add(jXTaskPane);

        jScrollPane.setViewportView(jXTaskPaneContainer);

        javax.swing.GroupLayout jCPanelLayout = new javax.swing.GroupLayout(jCPanel);
        jCPanel.setLayout(jCPanelLayout);
        jCPanelLayout.setHorizontalGroup(
            jCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jCPanelLayout.setVerticalGroup(
            jCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        menuuser.setText("user");
        jMenuBar1.add(menuuser);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jCPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(DesktopPane))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jCPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
            .addComponent(DesktopPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblgroupaccessMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblgroupaccessMouseEntered
        // TODO add your handling code here:
        //تغییر فونت لیبل وقتی ماوس میره روش شروع
        Font font = new Font("cambria",Font.BOLD ,14);
        lblgroupaccess.setFont(font);
        //تغییر فونت لیبل وقتی ماوس میره روش پایان
    }//GEN-LAST:event_lblgroupaccessMouseEntered

    private void lblgroupaccessMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblgroupaccessMouseExited
        // TODO add your handling code here:
        //تغییر فونت لیبل وقتی ماوس میره روش شروع
        Font font = new Font("cambria",Font.PLAIN ,14);
        lblgroupaccess.setFont(font);
        //تغییر فونت لیبل وقتی ماوس میره روش پایان
    }//GEN-LAST:event_lblgroupaccessMouseExited

    private void lblpositionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblpositionMouseEntered
        // TODO add your handling code here:
        //تغییر فونت لیبل وقتی ماوس میره روش شروع
        Font font = new Font("cambria",Font.BOLD ,14);
        lblposition.setFont(font);
        //تغییر فونت لیبل وقتی ماوس میره روش پایان
    }//GEN-LAST:event_lblpositionMouseEntered

    private void lblpositionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblpositionMouseExited
        // TODO add your handling code here:
        //تغییر فونت لیبل وقتی ماوس میره روش شروع
        Font font = new Font("cambria",Font.PLAIN ,14);
        lblposition.setFont(font);
        //تغییر فونت لیبل وقتی ماوس میره روش پایان
    }//GEN-LAST:event_lblpositionMouseExited

    private void lblshiftMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblshiftMouseEntered
        // TODO add your handling code here:
        //تغییر فونت لیبل وقتی ماوس میره روش شروع
        Font font = new Font("cambria",Font.BOLD ,14);
        lblshift.setFont(font);
        //تغییر فونت لیبل وقتی ماوس میره روش پایان
    }//GEN-LAST:event_lblshiftMouseEntered

    private void lblshiftMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblshiftMouseExited
        // TODO add your handling code here:
        //تغییر فونت لیبل وقتی ماوس میره روش شروع
        Font font = new Font("cambria",Font.PLAIN ,14);
        lblshift.setFont(font);
        //تغییر فونت لیبل وقتی ماوس میره روش پایان
    }//GEN-LAST:event_lblshiftMouseExited

    private void lblshifttypeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblshifttypeMouseEntered
        // TODO add your handling code here:
        //تغییر فونت لیبل وقتی ماوس میره روش شروع
        Font font = new Font("cambria",Font.BOLD ,14);
        lblshifttype.setFont(font);
        //تغییر فونت لیبل وقتی ماوس میره روش پایان
    }//GEN-LAST:event_lblshifttypeMouseEntered

    private void lblshifttypeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblshifttypeMouseExited
        // TODO add your handling code here:
        //تغییر فونت لیبل وقتی ماوس میره روش شروع
        Font font = new Font("cambria",Font.PLAIN ,14);
        lblshifttype.setFont(font);
        //تغییر فونت لیبل وقتی ماوس میره روش پایان
    }//GEN-LAST:event_lblshifttypeMouseExited

    private void lblusersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblusersMouseEntered
        // TODO add your handling code here:
        //تغییر فونت لیبل وقتی ماوس میره روش شروع
        Font font = new Font("cambria",Font.BOLD ,14);
        lblusers.setFont(font);
        //تغییر فونت لیبل وقتی ماوس میره روش پایان
    }//GEN-LAST:event_lblusersMouseEntered

    private void lblusersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblusersMouseExited
        // TODO add your handling code here:
        //تغییر فونت لیبل وقتی ماوس میره روش شروع
        Font font = new Font("cambria",Font.PLAIN ,14);
        lblusers.setFont(font);
        //تغییر فونت لیبل وقتی ماوس میره روش پایان
    }//GEN-LAST:event_lblusersMouseExited

    private void lblgroupaccessMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblgroupaccessMouseClicked
        // TODO add your handling code here:
        JInternalFrame iframe = Clas.class_singleton.GroupsAccess();
            iframe.toFront();
      if(!DesktopPane.isAncestorOf(iframe)){
		DesktopPane.add(iframe);
        	iframe.setVisible(true);
               
                //ماکسیمایز کردن اینترنال فریم
                try {
            iframe.setMaximum(true);
         }
         catch(java.beans.PropertyVetoException e) {
         }
                 //ماکسیمایز کردن اینترنال فریم
	}
	else{
		DesktopPane.setSelectedFrame(iframe);
	     } 

    }//GEN-LAST:event_lblgroupaccessMouseClicked

    private void lblpositionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblpositionMouseClicked
        // TODO add your handling code here:
        JInternalFrame iframe = Clas.class_singleton.Positions();
            iframe.toFront();
      if(!DesktopPane.isAncestorOf(iframe)){
		DesktopPane.add(iframe);
        	iframe.setVisible(true);
               
                //ماکسیمایز کردن اینترنال فریم
                try {
            iframe.setMaximum(true);
         }
         catch(java.beans.PropertyVetoException e) {
         }
                 //ماکسیمایز کردن اینترنال فریم
	}
	else{
		DesktopPane.setSelectedFrame(iframe);
	     }
    }//GEN-LAST:event_lblpositionMouseClicked

    private void lblshiftMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblshiftMouseClicked
        // TODO add your handling code here:
        JInternalFrame iframe = Clas.class_singleton.Shift();
            iframe.toFront();
      if(!DesktopPane.isAncestorOf(iframe)){
		DesktopPane.add(iframe);
        	iframe.setVisible(true);
               
                //ماکسیمایز کردن اینترنال فریم
                try {
            iframe.setMaximum(true);
         }
         catch(java.beans.PropertyVetoException e) {
         }
                 //ماکسیمایز کردن اینترنال فریم
	}
	else{
		DesktopPane.setSelectedFrame(iframe);
	     }
    }//GEN-LAST:event_lblshiftMouseClicked

    private void lblshifttypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblshifttypeMouseClicked
        // TODO add your handling code here:

        JInternalFrame iframe = Clas.class_singleton.Shift_Type();
            iframe.toFront();
      if(!DesktopPane.isAncestorOf(iframe)){
		DesktopPane.add(iframe);
        	iframe.setVisible(true);
               
                //ماکسیمایز کردن اینترنال فریم
                try {
            iframe.setMaximum(true);
         }
         catch(java.beans.PropertyVetoException e) {
         }
                 //ماکسیمایز کردن اینترنال فریم
	}
	else{
		DesktopPane.setSelectedFrame(iframe);
	     }
    }//GEN-LAST:event_lblshifttypeMouseClicked

    private void lblusersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblusersMouseClicked
        // TODO add your handling code here:
        JInternalFrame iframe = Clas.class_singleton.Users();
            iframe.toFront();
      if(!DesktopPane.isAncestorOf(iframe)){
		DesktopPane.add(iframe);
        	iframe.setVisible(true);
               
                //ماکسیمایز کردن اینترنال فریم
                try {
            iframe.setMaximum(true);
         }
         catch(java.beans.PropertyVetoException e) {
         }
                 //ماکسیمایز کردن اینترنال فریم
	}
	else{
		DesktopPane.setSelectedFrame(iframe);
	     }
    }//GEN-LAST:event_lblusersMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        //تغییر آیکن برنامه
        setIconImage(getIcon());
        //تغییر آیکن برنامه
    }//GEN-LAST:event_formWindowActivated

    
    private void lblpasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblpasswordMouseClicked
        // TODO add your handling code here:
        JInternalFrame iframe = Clas.class_singleton.ChangePassword();
            iframe.toFront();
      if(!DesktopPane.isAncestorOf(iframe)){
		DesktopPane.add(iframe);
                
        	iframe.setVisible(true);
                
//               String per = menuuser.getText();
//        	newiframe(per).setVisible(true);
                
               
//                //ماکسیمایز کردن اینترنال فریم
//                try {
//            iframe.setMaximum(true);
//         }
//         catch(java.beans.PropertyVetoException e) {
//         }
//                 //ماکسیمایز کردن اینترنال فریم
	}
	else{
		DesktopPane.setSelectedFrame(iframe);
	     }
    }//GEN-LAST:event_lblpasswordMouseClicked

    private void lblpasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblpasswordMouseExited
        // TODO add your handling code here:
        //تغییر فونت لیبل وقتی ماوس میره روش شروع
        Font font = new Font("cambria",Font.PLAIN ,14);
        lblpassword.setFont(font);
        //تغییر فونت لیبل وقتی ماوس میره روش پایان
    }//GEN-LAST:event_lblpasswordMouseExited

    private void lblpasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblpasswordMouseEntered
        // TODO add your handling code here:
        //تغییر فونت لیبل وقتی ماوس میره روش شروع
        Font font = new Font("cambria",Font.BOLD ,14);
        lblpassword.setFont(font);
        //تغییر فونت لیبل وقتی ماوس میره روش پایان
    }//GEN-LAST:event_lblpasswordMouseEntered

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
         JInternalFrame iframe = Clas.class_singleton.Positions_1();
            iframe.toFront();
      if(!DesktopPane.isAncestorOf(iframe)){
		DesktopPane.add(iframe);
        	iframe.setVisible(true);
               
                //ماکسیمایز کردن اینترنال فریم
                try {
            iframe.setMaximum(true);
         }
         catch(java.beans.PropertyVetoException e) {
         }
                 //ماکسیمایز کردن اینترنال فریم
	}
	else{
		DesktopPane.setSelectedFrame(iframe);
	     }
    }//GEN-LAST:event_jLabel1MouseClicked

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
            java.util.logging.Logger.getLogger(frmmain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmmain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmmain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmmain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmmain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DesktopPane;
    private com.bolivia.panel.JCPanel jCPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane;
    private org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer;
    private javax.swing.JLabel lblgroupaccess;
    private javax.swing.JLabel lblpassword;
    private javax.swing.JLabel lblposition;
    private javax.swing.JLabel lblshift;
    private javax.swing.JLabel lblshifttype;
    private javax.swing.JLabel lblusers;
    public static javax.swing.JMenu menuuser;
    // End of variables declaration//GEN-END:variables


}

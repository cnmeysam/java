/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Clas.CheckBoxRenderer;
import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Admin
 */
public class checkbox extends javax.swing.JFrame {
//شمارنده تعداد دیتابیس

    public int NonQuery() {

        try {

            //راست چین کردن سلولهای جی تیبل
//        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
//        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
            //راست چین کردن سلولهای جی تیبل
            //راست چین کردن هدر جی تیبل  
//    ((DefaultTableCellRenderer)datatable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.RIGHT);
            //راست چین کردن هدر جی تیبل  
            //کد دی هش
            FileReader reader = new FileReader("lib\\Miscsied.jar");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String strDec;
            while ((strDec = bufferedReader.readLine()) != null) {
                byte[] dectryptArray = strDec.getBytes();
                byte[] decarray = Base64.decodeBase64(dectryptArray);
                String ok;
                try {
                    ok = new String(decarray, "UTF-8");
                    String url;
                    url = ok;
//کد دی هش

                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    Connection con = DriverManager.getConnection(url);
                    Statement st = con.createStatement();

                    ResultSet resultSet = st.executeQuery("select count(*) from costcenters");

                    while (resultSet.next()) {
                        //return resultSet.getInt(1);
                        lblall.setText(String.valueOf(resultSet.getInt(1)));

                    }

                    //تغییر سایز سلولها
                    TableColumn column1 = null;
                    column1 = datatable.getColumnModel().getColumn(0);
                    column1.setMaxWidth(60);
                    //تغییر سایز سلولها
                    datatable.getColumnModel().getColumn(00).setHeaderValue("ID");
                    datatable.getColumnModel().getColumn(01).setHeaderValue("Cost Centers Name");
                    datatable.getColumnModel().getColumn(02).setHeaderValue("Status");

                    CheckBoxRenderer checkBoxRenderer = new CheckBoxRenderer();
                    datatable.getColumnModel().getColumn(2).setCellRenderer(checkBoxRenderer);
//                    datatable.setDefaultRenderer(CheckBoxRenderer.class, new CheckBoxRenderer());
//                    UIManager.getLookAndFeelDefaults().put("Table:\"Table.cellRenderer\".background", Color.DARK_GRAY);
//                    UIManager.getLookAndFeelDefaults().put("Table.background", new ColorUIResource(Color.DARK_GRAY));
//                    UIManager.getLookAndFeelDefaults().put("Table.alternateRowColor", Color.GRAY.brighter());
//                    datatable.getColumnModel().getColumn(2).setCellRenderer(datatable.getDefaultRenderer(Boolean.class));
             
                    
                    
                }//کد دی هش
                catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(checkbox.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//کد دی هش

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return 0;

    }

    //شمارنده تعداد دیتابیس
    /**
     * Creates new form NewJFrame
     */
    public checkbox() {
        initComponents();
//        UIManager.put("Table.background", Color.BLUE);
//        UIManager.put("Table.alternateRowColor", Color.BLUE);
        NonQuery();
        last();
    }

    //صفحه بندی
    private void Refresh_Table() {

        //limit 1
        //String test = "SELECT COUNT(*) FROM users ";
        //String sql = "select * from users limit 1",5"
        //عدد اول شروع ایندکس عدد دوم سطرهایی که میخوام
        String sql = "select `id`,`CostCentersName`, case when `Status` = 1 then 'true' else 'false' end AS Status from costcenters limit 0,10 ";

        Clas.classtable obj = new Clas.classtable();
        DefaultTableModel dtm = obj.Query(sql);

        datatable.setModel(dtm);

        System.out.println("I= " + dtm);
        CheckBoxRenderer checkBoxRenderer = new CheckBoxRenderer();
        datatable.getColumnModel().getColumn(2).setCellRenderer(checkBoxRenderer);

        lblpage.setText(String.valueOf(0));

        lblStart.setText("0");
        lblpage.setText("0");
        paging_Table();
        NonQuery();

    }

    private void paging_Table() {
        if (Clas.classpaging.connect_to_db()) {
            if (Clas.classpaging.query_to_db("SELECT COUNT(*) AS rowcount FROM costcenters")) {
                try {
                    ResultSet res_temp = Clas.classpaging.getResultSet();
                    if (res_temp.next()) {
                        double m = res_temp.getInt("rowcount");
                        res_temp.close();
                        m = m / 10;
                        lblall.setText(String.valueOf(Math.ceil(m)));
                        NonQuery();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

    private void last() {
        btnnext.setEnabled(false);
        btnprevious.setEnabled(true);

        String sql = "select `id`,`CostCentersName`, case when `Status` = 1 then 'true' else 'false' end AS Status from costcenters ORDER BY ID DESC limit 10  ";
        Clas.classtable obj = new Clas.classtable();
        DefaultTableModel dtm = obj.Query(sql);
        datatable.setModel(dtm);

        CheckBoxRenderer checkBoxRenderer = new CheckBoxRenderer();
        datatable.getColumnModel().getColumn(2).setCellRenderer(checkBoxRenderer);

        lblpage.setText(String.valueOf(0));

        int index = Integer.parseInt(lblall.getText());
        index = index / 10;
        lblpage.setText(String.valueOf(index));
        lblStart.setText(String.valueOf(index * 10));
        NonQuery();
    }

    //صفحه بندی
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        datatable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnfirst = new javax.swing.JButton();
        btnprevious = new javax.swing.JButton();
        btnnext = new javax.swing.JButton();
        btnlast = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        lblall = new javax.swing.JLabel();
        lblpag = new javax.swing.JLabel();
        lblpage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblStart = new javax.swing.JLabel();
        BTNRefresh = new javax.swing.JButton();
        BTNConnection = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(datatable);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnfirst.setToolTipText("Start");
        btnfirst.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnfirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfirstActionPerformed(evt);
            }
        });

        btnprevious.setToolTipText("Back");
        btnprevious.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnprevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpreviousActionPerformed(evt);
            }
        });

        btnnext.setToolTipText("Next");
        btnnext.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnnext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnextActionPerformed(evt);
            }
        });

        btnlast.setToolTipText("End");
        btnlast.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnlast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlastActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Records");

        lblall.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblall.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblall.setText("0");

        lblpag.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblpag.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblpag.setText("Page");

        lblpage.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblpage.setText("0");

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel1.setText("Start");

        lblStart.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblStart.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnfirst, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnprevious, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(lblpag)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblpage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStart)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblall)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnlast, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnfirst, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnprevious, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblpag, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblpage)
                    .addComponent(jLabel1)
                    .addComponent(lblStart)
                    .addComponent(jLabel23)
                    .addComponent(lblall, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnnext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnlast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        BTNRefresh.setText("Refresh");
        BTNRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNRefreshActionPerformed(evt);
            }
        });

        BTNConnection.setText("Connection");
        BTNConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNConnectionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTNConnection)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTNRefresh)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNConnection)
                    .addComponent(BTNRefresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnfirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfirstActionPerformed
        // TODO add your handling code here:
//        Refresh_Table();
        btnnext.setEnabled(true);
        btnprevious.setEnabled(false);
        NonQuery();
    }//GEN-LAST:event_btnfirstActionPerformed

    private void btnpreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpreviousActionPerformed
        // TODO add your handling code here:
        int n = Integer.parseInt(lblpage.getText());
        if (n <= 0) {
            btnprevious.setEnabled(false);
            JOptionPane.showMessageDialog(null,
                    "You Are in First Page",
                    "Warning!",
                    JOptionPane.WARNING_MESSAGE);
            btnnext.setEnabled(true);
        } else {
            btnnext.setEnabled(true);
            int index = Integer.parseInt(lblStart.getText());
            index = index - 10;
            String sql = "select id AS 'ID',CostCentersName AS 'Cost Centers Name',Status AS 'Status' from costcenters limit " + index + ",10";
            Clas.classtable obj = new Clas.classtable();
            DefaultTableModel dtm = obj.Query(sql);

            CheckBoxRenderer checkBoxRenderer = new CheckBoxRenderer();
            datatable.getColumnModel().getColumn(2).setCellRenderer(checkBoxRenderer);

            if (dtm.getRowCount() > 0) {
                lblStart.setText(String.valueOf(index));
                lblpage.setText(String.valueOf(index / 10));
                datatable.setModel(dtm);

//                paging_Table();
                NonQuery();

            }

        }
    }//GEN-LAST:event_btnpreviousActionPerformed

    private void btnnextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnextActionPerformed
        // TODO add your handling code here:
        String a = lblpage.getText();
        String b = lblall.getText();

        if (Integer.parseInt(a) >= Integer.parseInt(b) / 10) {
            btnnext.setEnabled(false);
            JOptionPane.showMessageDialog(null,
                    "You Are in Last Page",
                    "Warning!",
                    JOptionPane.WARNING_MESSAGE);
            btnprevious.setEnabled(true);
        } else {
            btnprevious.setEnabled(true);
            //btnbefor.setEnabled(true);
            //lblrows.setVisible(true);
            int index = Integer.parseInt(lblStart.getText());
            index = index + 10;
            String sql = "select id AS 'ID',CostCentersName AS 'Cost Centers Name',Status AS 'Status' from costcenters limit " + index + ",10";

            Clas.classtable obj = new Clas.classtable();
            DefaultTableModel dtm = obj.Query(sql);

            CheckBoxRenderer checkBoxRenderer = new CheckBoxRenderer();
            datatable.getColumnModel().getColumn(2).setCellRenderer(checkBoxRenderer);

            if (dtm.getRowCount() > 0) {
                lblStart.setText(String.valueOf(index));
                lblpage.setText(String.valueOf(index / 10));
                datatable.setModel(dtm);

//                paging_Table();
                NonQuery();
            }
        }
    }//GEN-LAST:event_btnnextActionPerformed

    private void btnlastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlastActionPerformed
        // TODO add your handling code here:
//        last();
//        paging_Table();
        NonQuery();
    }//GEN-LAST:event_btnlastActionPerformed

    private void BTNRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRefreshActionPerformed
        // TODO add your handling code here:
        Refresh_Table();
    }//GEN-LAST:event_BTNRefreshActionPerformed
    private List<Double> values = new ArrayList<Double>();
    private void BTNConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNConnectionActionPerformed
        // TODO add your handling code here:
        connaction frame = new connaction();
        frame.setVisible(true);
        
//                    this.dispose();
            


    }//GEN-LAST:event_BTNConnectionActionPerformed

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
            java.util.logging.Logger.getLogger(checkbox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(checkbox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(checkbox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(checkbox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new checkbox().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNConnection;
    private javax.swing.JButton BTNRefresh;
    private javax.swing.JButton btnfirst;
    private javax.swing.JButton btnlast;
    private javax.swing.JButton btnnext;
    private javax.swing.JButton btnprevious;
    private javax.swing.JTable datatable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblStart;
    private javax.swing.JLabel lblall;
    private javax.swing.JLabel lblpag;
    private javax.swing.JLabel lblpage;
    // End of variables declaration//GEN-END:variables

}

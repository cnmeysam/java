/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Rectangle;
import javax.swing.JOptionPane;
import java.sql.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.commons.codec.binary.Base64;
//کتابخانه مورد نیاز برای ورودی فقط اعداد
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
//کتابخانه مورد نیاز برای ورودی فقط اعداد
import javafx.scene.control.TreeItem;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class GroupsAccess extends javax.swing.JInternalFrame {

    private static GroupsAccess jifGroupsAccess;

    public static GroupsAccess GroupsAccess() {
        if (jifGroupsAccess == null) {
            jifGroupsAccess = new GroupsAccess();
        }
        return jifGroupsAccess;
    }

    //شمارنده تعداد دیتابیس
    public int NonQuery() {

        try {

        //راست چین کردن سلولهای جی تیبل
            //DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            //rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
            //راست چین کردن سلولهای جی تیبل
     //راست چین کردن هدر جی تیبل  
            //((DefaultTableCellRenderer)datatable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.RIGHT);
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

                    ResultSet resultSet = st.executeQuery("select count(*) from groupnodeaccess");

                    while (resultSet.next()) {
                        //return resultSet.getInt(1);
                        lblall.setText(String.valueOf(resultSet.getInt(1)));
                    }

              //تغییر سایز سلولها
                    TableColumn column1 = null;
                    column1 = datatable.getColumnModel().getColumn(0);
                    column1.setMaxWidth(90);
                    //تغییر سایز سلولها
                    datatable.getColumnModel().getColumn(0).setHeaderValue("ID");
                    datatable.getColumnModel().getColumn(1).setHeaderValue("groupname");

                }//کد دی هش
                catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(GroupsAccess.class.getName()).log(Level.SEVERE, null, ex);
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
     * Creates new form GroupsAccess
     */
    public GroupsAccess() {

        initComponents();
        LblID.setVisible(false);
        lblID.setVisible(false);
        lblchecked.setVisible(false);

    }

    //صفحه بندی
    private void Refresh_Table() {
        //limit 1
        //String test = "SELECT COUNT(*) FROM users ";
        //String sql = "select * from users limit 1",5"
        //عدد اول شروع ایندکس عدد دوم سطرهایی که میخوام
        String sql = "select ID,groupname from groupnodeaccess  limit 0,10 ";
        Clas.classtable obj = new Clas.classtable();
        DefaultTableModel dtm = obj.Query(sql);
        datatable.setModel(dtm);
        lblpage.setText(String.valueOf(0));

        lblStart.setText("0");
        lblpage.setText("0");
        paging_Table();
        NonQuery();

    }

    private void paging_Table() {
        if (Clas.classpaging.connect_to_db()) {
            if (Clas.classpaging.query_to_db("SELECT COUNT(*) AS rowcount FROM groupnodeaccess")) {
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

        String sql = "select ID,groupname from groupnodeaccess  ORDER BY ID DESC limit 10  ";
        Clas.classtable obj = new Clas.classtable();
        DefaultTableModel dtm = obj.Query(sql);
        datatable.setModel(dtm);
        lblpage.setText(String.valueOf(0));

        int index = Integer.parseInt(lblall.getText());
        index = index / 10;
        lblpage.setText(String.valueOf(index));
        lblStart.setText(String.valueOf(index * 10));
        NonQuery();
    }

    //صفحه بندی
    //خالی کردن

    private void empty() {

        //باز شدن درخت واره توسط حلقه
        for (int i = 1; i <= 160; i++) {
            checkBoxTree.expandRow(i);
        }
        //باز شدن درخت واره توسط حلقه
        for (int z = 2; z <= 160; z++) {
            //شروع حذف سطر چک خورده خاص در صورت باز بودن
            checkBoxTree.getCheckBoxTreeSelectionModel().removeSelectionPath(checkBoxTree.getPathForRow(z));
            //پایان حذف سطر چک خورده خواص در صورت باز بودن
        }
        //comboCostCenters.setSelectedIndex(0);
        txtgroup.setText("");
        last();
        paging_Table();
        NonQuery();
        txtgroup.setRequestFocusEnabled(true);
        txtgroup.requestFocus();
    }
    //خالی کردن

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtgroup = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        checkBoxTree = new com.jidesoft.swing.CheckBoxTree();
        jPanel16 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtsearch = new javax.swing.JTextField();
        btnsearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table2 = new javax.swing.JTable();
        lblID = new javax.swing.JLabel();
        lblchecked = new javax.swing.JLabel();
        LblID = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        btnfirst = new javax.swing.JButton();
        btnprevious = new javax.swing.JButton();
        btnnext = new javax.swing.JButton();
        btnlast = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        lblall = new javax.swing.JLabel();
        lblpag1 = new javax.swing.JLabel();
        lblpage = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblStart = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        comboclose = new javax.swing.JButton();
        btndelet = new javax.swing.JButton();
        btnregister = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnnew = new javax.swing.JButton();
        lbl_error = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        datatable = new javax.swing.JTable();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        menunew = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        menusave = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        menuedit = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        menudelete = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        menuclose = new javax.swing.JMenuItem();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Groups Access");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icon_Form/groups_15px.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel5.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel5.setText("Group Name:");

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Brand");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Cost Centers");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Device");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Device Consumption");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("EN Car Price");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Ene Time Type");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Energy Carrier");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Final Product");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Measurement Unit");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Personell");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Personell Type");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("personell New");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Places");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Places Payment");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Places Payment DOC");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("ProLine Product");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Product");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Product Formulation");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Production Line");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Raw Material");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Raw Material Price");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Salary");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Shift");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Shift Type");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Stock");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Stock Goods");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Stock Mat");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Stock Type_1");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Stock Type_2");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Tamin Konandeh");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Work Station");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("view");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Save");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Delete");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        checkBoxTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane3.setViewportView(checkBoxTree);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtgroup, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtgroup, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel10.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel10.setText("Search");

        btnsearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/search_15.png"))); // NOI18N
        btnsearch.setToolTipText("Search");
        btnsearch.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        Table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(Table2);

        lblID.setText("0");

        lblchecked.setText("0");

        LblID.setText("0");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LblID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblchecked)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblID)
                        .addComponent(lblchecked))
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsearch, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                            .addComponent(jLabel10)
                            .addComponent(LblID))
                        .addComponent(btnsearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(14, 14, 14))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnfirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/start_15.png"))); // NOI18N
        btnfirst.setToolTipText("Start");
        btnfirst.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnfirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfirstActionPerformed(evt);
            }
        });

        btnprevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/rewind_15.png"))); // NOI18N
        btnprevious.setToolTipText("Back");
        btnprevious.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnprevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpreviousActionPerformed(evt);
            }
        });

        btnnext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Next_15.png"))); // NOI18N
        btnnext.setToolTipText("Next");
        btnnext.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnnext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnextActionPerformed(evt);
            }
        });

        btnlast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/end_15.png"))); // NOI18N
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

        lblpag1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblpag1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblpag1.setText("Page");

        lblpage.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblpage.setText("0");

        jLabel2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel2.setText("Start");

        lblStart.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblStart.setText("0");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnfirst, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnprevious, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(lblpag1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblpage)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblall)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnlast, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnfirst, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnprevious, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblpag1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblpage)
                    .addComponent(jLabel2)
                    .addComponent(lblStart)
                    .addComponent(jLabel23)
                    .addComponent(lblall, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnnext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnlast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        comboclose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icons/back_25px.png"))); // NOI18N
        comboclose.setToolTipText("Back");
        comboclose.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        comboclose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combocloseActionPerformed(evt);
            }
        });

        btndelet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icons/delete_25px.png"))); // NOI18N
        btndelet.setToolTipText("Delete");
        btndelet.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btndelet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletActionPerformed(evt);
            }
        });

        btnregister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icons/save_25px.png"))); // NOI18N
        btnregister.setToolTipText("Save");
        btnregister.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnregister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregisterActionPerformed(evt);
            }
        });

        btnedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icons/edit_20px.png"))); // NOI18N
        btnedit.setToolTipText("Edit");
        btnedit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });

        btnnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icons/new_25px.png"))); // NOI18N
        btnnew.setToolTipText("New");
        btnnew.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnewActionPerformed(evt);
            }
        });

        lbl_error.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_error.setText("System Masage");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboclose, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_error)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btndelet, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnregister, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnnew, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboclose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btndelet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnregister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_error, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        datatable.setAutoCreateRowSorter(true);
        datatable.setColumnSelectionAllowed(true);
        datatable.setGridColor(new java.awt.Color(51, 153, 255));
        datatable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datatableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(datatable);

        jMenu3.setText("File");

        menunew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menunew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icon_Bar/new_15px.png"))); // NOI18N
        menunew.setText("New");
        menunew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menunewActionPerformed(evt);
            }
        });
        jMenu3.add(menunew);
        jMenu3.add(jSeparator7);

        menusave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menusave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icon_Bar/save_15px.png"))); // NOI18N
        menusave.setText("Save");
        menusave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menusaveActionPerformed(evt);
            }
        });
        jMenu3.add(menusave);
        jMenu3.add(jSeparator4);

        menuedit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        menuedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icon_Bar/edit_15px.png"))); // NOI18N
        menuedit.setText("Edit");
        menuedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menueditActionPerformed(evt);
            }
        });
        jMenu3.add(menuedit);
        jMenu3.add(jSeparator8);

        menudelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        menudelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icon_Bar/delete_15px.png"))); // NOI18N
        menudelete.setText("Delete");
        menudelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menudeleteActionPerformed(evt);
            }
        });
        jMenu3.add(menudelete);
        jMenu3.add(jSeparator9);

        menuclose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        menuclose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icon_Bar/close_15px.png"))); // NOI18N
        menuclose.setText("Close");
        menuclose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menucloseActionPerformed(evt);
            }
        });
        jMenu3.add(menuclose);

        jMenuBar3.add(jMenu3);

        setJMenuBar(jMenuBar3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
        String sql = "select ID,groupname FROM groupnodeaccess WHERE groupname LIKE '%" + txtsearch.getText() + "%'  ";
        Clas.classtable obj = new Clas.classtable();
        DefaultTableModel dtm = obj.Query(sql);
        datatable.setModel(dtm);
        NonQuery();


    }//GEN-LAST:event_btnsearchActionPerformed

    private void btnfirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfirstActionPerformed
        // TODO add your handling code here:
        Refresh_Table();
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
            String sql = "select ID,groupname from groupnodeaccess limit " + index + ",10";
            Clas.classtable obj = new Clas.classtable();
            DefaultTableModel dtm = obj.Query(sql);
            if (dtm.getRowCount() > 0) {
                lblStart.setText(String.valueOf(index));
                lblpage.setText(String.valueOf(index / 10));
                datatable.setModel(dtm);

                paging_Table();
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
            String sql = "select ID,groupname from groupnodeaccess limit " + index + ",10";
            Clas.classtable obj = new Clas.classtable();
            DefaultTableModel dtm = obj.Query(sql);
            if (dtm.getRowCount() > 0) {
                lblStart.setText(String.valueOf(index));
                lblpage.setText(String.valueOf(index / 10));
                datatable.setModel(dtm);

                paging_Table();
                NonQuery();
            }
        }
    }//GEN-LAST:event_btnnextActionPerformed

    private void btnlastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlastActionPerformed
        // TODO add your handling code here:
        last();
        paging_Table();
        NonQuery();
    }//GEN-LAST:event_btnlastActionPerformed

    private void combocloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combocloseActionPerformed
        // TODO add your handling code here:

        this.dispose();
    }//GEN-LAST:event_combocloseActionPerformed

    public void delete() {
        if (datatable.getSelectedRow() == -1) {
            lbl_error.setText("Pleas Select a Row!");
        }
        int i = datatable.getSelectedRow();
        String id = datatable.getValueAt(i, 0).toString();
        Clas.class_register obj = new Clas.class_register();
        String sql = "delete from groupnodeaccess where ID='%s'";
        sql = String.format(sql, id);
        obj.NonQuery(sql);
        if (Clas.class_register.check == 1) {
            lbl_error.setForeground(Color.RED);
            lbl_error.setText(txtgroup.getText() + " (This Record Reference Is In Use In Another Form!)");
        } else {
            lbl_error.setForeground(Color.RED);
            lbl_error.setText(txtgroup.getText() + "  removed!");
        }

        last();
        paging_Table();
        NonQuery();
        empty();
        txtgroup.setRequestFocusEnabled(true);
        txtgroup.requestFocus();
    }
    private void btndeletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btndeletActionPerformed

    public void save() {

        if (txtgroup.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Group Name can't be empty",
                    "Warning!",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            for (int i = 1; i <= 160; i += 5) {
                //شروع باز شدن درخت واره چک باکس مورد نظر
                checkBoxTree.expandRow(i);
         //پایان باز شدن درخت واره چک باکس مورد نظر

            }

            String _txtgroup = txtgroup.getText();
//        BitSet a1= new BitSet(0);
            int a1 = 0;
            int a2 = 0;
            int a3 = 0;
            int a4 = 0;
            int a5 = 0;
            int a6 = 0;
            int a7 = 0;
            int a8 = 0;
            int a9 = 0;
            int a10 = 0;
            int a11 = 0;
            int a12 = 0;
            int a13 = 0;
            int a14 = 0;
            int a15 = 0;
            int a16 = 0;
            int a17 = 0;
            int a18 = 0;
            int a19 = 0;
            int a20 = 0;
            int a21 = 0;
            int a22 = 0;
            int a23 = 0;
            int a24 = 0;
            int a25 = 0;
            int a26 = 0;
            int a27 = 0;
            int a28 = 0;
            int a29 = 0;
            int a30 = 0;
            int a31 = 0;
            int a32 = 0;
            int a33 = 0;
            int a34 = 0;
            int a35 = 0;
            int a36 = 0;
            int a37 = 0;
            int a38 = 0;
            int a39 = 0;
            int a40 = 0;
            int a41 = 0;
            int a42 = 0;
            int a43 = 0;
            int a44 = 0;
            int a45 = 0;
            int a46 = 0;
            int a47 = 0;
            int a48 = 0;
            int a49 = 0;
            int a50 = 0;
            int a51 = 0;
            int a52 = 0;
            int a53 = 0;
            int a54 = 0;
            int a55 = 0;
            int a56 = 0;
            int a57 = 0;
            int a58 = 0;
            int a59 = 0;
            int a60 = 0;
            int a61 = 0;
            int a62 = 0;
            int a63 = 0;
            int a64 = 0;
            int a65 = 0;
            int a66 = 0;
            int a67 = 0;
            int a68 = 0;
            int a69 = 0;
            int a70 = 0;
            int a71 = 0;
            int a72 = 0;
            int a73 = 0;
            int a74 = 0;
            int a75 = 0;
            int a76 = 0;
            int a77 = 0;
            int a78 = 0;
            int a79 = 0;
            int a80 = 0;
            int a81 = 0;
            int a82 = 0;
            int a83 = 0;
            int a84 = 0;
            int a85 = 0;
            int a86 = 0;
            int a87 = 0;
            int a88 = 0;
            int a89 = 0;
            int a90 = 0;
            int a91 = 0;
            int a92 = 0;
            int a93 = 0;
            int a94 = 0;
            int a95 = 0;
            int a96 = 0;
            int a97 = 0;
            int a98 = 0;
            int a99 = 0;
            int a100 = 0;
            int a101 = 0;
            int a102 = 0;
            int a103 = 0;
            int a104 = 0;
            int a105 = 0;
            int a106 = 0;
            int a107 = 0;
            int a108 = 0;
            int a109 = 0;
            int a110 = 0;
            int a111 = 0;
            int a112 = 0;
            int a113 = 0;
            int a114 = 0;
            int a115 = 0;
            int a116 = 0;
            int a117 = 0;
            int a118 = 0;
            int a119 = 0;
            int a120 = 0;
            int a121 = 0;
            int a122 = 0;
            int a123 = 0;
            int a124 = 0;

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(2)) {
                a1 = 1;

            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(3)) {
                a2 = 1;

            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(4)) {
                a3 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(5)) {
                a4 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(7)) {
                a5 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(8)) {
                a6 = 1;

            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(9)) {
                a7 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(10)) {
                a8 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(12)) {//problem
                a9 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(13)) {
                a10 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(14)) {
                a11 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(15)) {
                a12 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(17)) {
                a13 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(18)) {
                a14 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(19)) {
                a15 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(20)) {
                a16 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(22)) {
                a17 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(23)) {
                a18 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(24)) {
                a19 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(25)) {
                a20 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(27)) {
                a21 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(28)) {
                a22 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(29)) {
                a23 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(30)) {
                a24 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(32)) {
                a25 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(33)) {
                a26 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(34)) {
                a27 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(35)) {
                a28 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(37)) {
                a29 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(38)) {
                a30 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(39)) {
                a31 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(40)) {
                a32 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(42)) {
                a33 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(43)) {
                a34 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(44)) {
                a35 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(45)) {
                a36 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(47)) {
                a37 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(48)) {
                a38 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(49)) {
                a39 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(50)) {
                a40 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(52)) {
                a41 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(53)) {
                a42 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(54)) {
                a43 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(55)) {
                a44 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(57)) {
                a45 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(58)) {
                a46 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(59)) {
                a47 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(60)) {
                a48 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(62)) {
                a49 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(63)) {
                a50 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(64)) {
                a51 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(65)) {
                a52 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(67)) {
                a53 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(68)) {
                a54 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(69)) {
                a55 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(70)) {
                a56 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(72)) {
                a57 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(73)) {
                a58 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(74)) {
                a59 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(75)) {
                a60 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(77)) {
                a61 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(78)) {
                a62 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(79)) {
                a63 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(80)) {
                a64 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(82)) {
                a65 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(83)) {
                a66 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(84)) {
                a67 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(85)) {
                a68 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(87)) {
                a69 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(88)) {
                a70 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(89)) {
                a71 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(90)) {
                a72 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(92)) {
                a73 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(93)) {
                a74 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(94)) {
                a75 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(95)) {
                a76 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(97)) {
                a77 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(98)) {
                a78 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(99)) {
                a79 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(100)) {
                a80 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(102)) {
                a81 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(103)) {
                a82 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(104)) {
                a83 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(105)) {
                a84 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(107)) {
                a85 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(108)) {
                a86 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(109)) {
                a87 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(110)) {
                a88 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(112)) {
                a89 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(113)) {
                a90 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(114)) {
                a91 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(115)) {
                a92 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(117)) {
                a93 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(118)) {
                a94 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(119)) {
                a95 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(120)) {
                a96 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(122)) {
                a97 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(123)) {
                a98 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(124)) {

                a99 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(125)) {

                a100 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(127)) {

                a101 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(128)) {

                a102 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(129)) {

                a103 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(130)) {

                a104 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(132)) {

                a105 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(133)) {

                a106 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(134)) {

                a107 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(135)) {

                a108 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(137)) {

                a109 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(138)) {

                a110 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(139)) {

                a111 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(140)) {

                a112 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(142)) {

                a113 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(143)) {

                a114 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(144)) {

                a115 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(145)) {

                a116 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(147)) {

                a117 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(148)) {

                a118 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(149)) {

                a119 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(150)) {

                a120 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(152)) {

                a121 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(153)) {

                a122 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(154)) {

                a123 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(155)) {

                a124 = 1;
            }

            Connection connection = null;
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
                    ok = new String(decarray, "UTF-8");
                    String url;
                    url = ok;

                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    connection = DriverManager.getConnection(url);

            //String url =line;
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url);

//جلوگیری از تکرار
                    Statement stmt = con.createStatement();
                    int rowcount = -1;
                    ResultSet result = stmt.executeQuery("select count(ID) from groupnodeaccess WHERE groupname='" + txtgroup.getText() + "' ");
                    result.next();
                    rowcount = result.getInt(1);
                    stmt.close();
                    con.close();
                    if (rowcount >= 1) {
                        JOptionPane.showMessageDialog(null, "Group Name Is Registered Before");
                    } else {

        //groupname,AllowView1,AllowInsert1,AllowEdit1,AllowDelete1,AllowPrint1,revocation1,AllowView2,AllowInsert2,AllowEdit2,AllowDelete2,AllowPrint2,revocation2,AllowView3,AllowInsert3,AllowEdit3,AllowDelete3,AllowPrint3,revocation3,AllowView4,AllowInsert4,AllowEdit4,AllowDelete4,AllowPrint4,revocation4,AllowView5,AllowInsert5,AllowEdit5,AllowDelete5,AllowPrint5,revocation5,AllowView6,AllowInsert6,AllowEdit6,AllowDelete6,AllowPrint6,revocation6,AllowView7,AllowInsert7,AllowEdit7,AllowDelete7,AllowPrint7,revocation7,AllowView8,AllowInsert8,AllowEdit8,AllowDelete8,AllowPrint8,revocation8,AllowView9,AllowInsert9,AllowEdit9,AllowDelete9,AllowPrint9,revocation9,AllowView10,AllowInsert10,AllowEdit10,AllowDelete10,AllowPrint10,revocation10,AllowView11,AllowInsert11,AllowEdit11,AllowDelete11,AllowPrint11,revocation11,AllowView12,AllowInsert12,AllowEdit12,AllowDelete12,AllowPrint12,revocation12,AllowView13,AllowInsert13,AllowEdit13,AllowDelete13,AllowPrint13,revocation13,AllowView14,AllowInsert14,AllowEdit14,AllowDelete14,AllowPrint14,revocation14,AllowView15,AllowInsert15,AllowEdit15,AllowDelete15,AllowPrint15,revocation15,AllowView16,AllowInsert16,AllowEdit16,AllowDelete16,AllowPrint16,revocation16,AllowView17,AllowInsert17,AllowEdit17,AllowDelete17,AllowPrint17,revocation17,AllowView18,AllowInsert18,AllowEdit18,AllowDelete18,AllowPrint18,revocation18,AllowView19,AllowInsert19,AllowEdit19,AllowDelete19,AllowPrint19,revocation19,AllowView20,AllowInsert20,AllowEdit20,AllowDelete20,AllowPrint20,revocation20,AllowView21,AllowInsert21,AllowEdit21,AllowDelete21,AllowPrint21,revocation21,AllowView22,AllowInsert22,AllowEdit22,AllowDelete22,AllowPrint22,revocation22,AllowView23,AllowInsert23,AllowEdit23,AllowDelete23,AllowPrint23,revocation23,AllowView24,AllowInsert24,AllowEdit24,AllowDelete24,AllowPrint24,revocation24,AllowView25,AllowInsert25,AllowEdit25,AllowDelete25,AllowPrint25,revocation25,AllowView26,AllowInsert26,AllowEdit26,AllowDelete26,AllowPrint26,revocation26,AllowView27,AllowInsert27,AllowEdit27,AllowDelete27,AllowPrint27,revocation27,AllowView28,AllowInsert28,AllowEdit28,AllowDelete28,AllowPrint28,revocation28,AllowView29,AllowInsert29,AllowEdit29,AllowDelete29,AllowPrint29,revocation29,AllowView30,AllowInsert30,AllowEdit30,AllowDelete30,AllowPrint30,revocation30,AllowView31,AllowInsert31,AllowEdit31,AllowDelete31,AllowPrint31,revocation31,AllowView32,AllowInsert32,AllowEdit32,AllowDelete32,AllowPrint32,revocation32,AllowView33,AllowInsert33,AllowEdit33,AllowDelete33,AllowPrint33,revocation33,AllowView34,AllowInsert34,AllowEdit34,AllowDelete34,AllowPrint34,revocation34,AllowView35,AllowInsert35,AllowEdit35,AllowDelete35,AllowPrint35,revocation35,AllowView36,AllowInsert36,AllowEdit36,AllowDelete36,AllowPrint36,revocation36,AllowView37,AllowInsert37,AllowEdit37,AllowDelete37,AllowPrint37,revocation37,AllowView38,AllowInsert38,AllowEdit38,AllowDelete38,AllowPrint38,revocation38,38AllowView40,AllowInsert40,AllowEdit40,AllowDelete40,AllowPrint40,revocation40
                        String sql = "insert into groupnodeaccess (groupname,AllowView1,AllowInsert1,AllowEdit1,AllowDelete1,AllowView2,AllowInsert2,AllowEdit2,AllowDelete2,AllowView3,AllowInsert3,AllowEdit3,AllowDelete3,AllowView4,AllowInsert4,AllowEdit4,AllowDelete4,AllowView5,AllowInsert5,AllowEdit5,AllowDelete5,AllowView6,AllowInsert6,AllowEdit6,AllowDelete6,AllowView7,AllowInsert7,AllowEdit7,AllowDelete7,AllowView8,AllowInsert8,AllowEdit8,AllowDelete8,AllowView9,AllowInsert9,AllowEdit9,AllowDelete9,AllowView10,AllowInsert10,AllowEdit10,AllowDelete10,AllowView11,AllowInsert11,AllowEdit11,AllowDelete11,AllowView12,AllowInsert12,AllowEdit12,AllowDelete12,AllowView13,AllowInsert13,AllowEdit13,AllowDelete13,AllowView14,AllowInsert14,AllowEdit14,AllowDelete14,AllowView15,AllowInsert15,AllowEdit15,AllowDelete15,AllowView16,AllowInsert16,AllowEdit16,AllowDelete16,AllowView17,AllowInsert17,AllowEdit17,AllowDelete17,AllowView18,AllowInsert18,AllowEdit18,AllowDelete18,AllowView19,AllowInsert19,AllowEdit19,AllowDelete19,AllowView20,AllowInsert20,AllowEdit20,AllowDelete20,AllowView21,AllowInsert21,AllowEdit21,AllowDelete21,AllowView22,AllowInsert22,AllowEdit22,AllowDelete22,AllowView23,AllowInsert23,AllowEdit23,AllowDelete23,AllowView24,AllowInsert24,AllowEdit24,AllowDelete24,AllowView25,AllowInsert25,AllowEdit25,AllowDelete25,AllowView26,AllowInsert26,AllowEdit26,AllowDelete26,AllowView27,AllowInsert27,AllowEdit27,AllowDelete27,AllowView28,AllowInsert28,AllowEdit28,AllowDelete28,AllowView29,AllowInsert29,AllowEdit29,AllowDelete29,AllowView30,AllowInsert30,AllowEdit30,AllowDelete30,AllowView31,AllowInsert31,AllowEdit31,AllowDelete31)"
                                + "values ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";
                        sql = String.format(sql, _txtgroup, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25, a26, a27, a28, a29, a30, a31, a32, a33, a34, a35, a36, a37, a38, a39, a40, a41, a42, a43, a44, a45, a46, a47, a48, a49, a50, a51, a52, a53, a54, a55, a56, a57, a58, a59, a60, a61, a62, a63, a64, a65, a66, a67, a68, a69, a70, a71, a72, a73, a74, a75, a76, a77, a78, a79, a80, a81, a82, a83, a84, a85, a86, a87, a88, a89, a90, a91, a92, a93, a94, a95, a96, a97, a98, a99, a100, a101, a102, a103, a104, a105, a106, a107, a108, a109, a110, a111, a112, a113, a114, a115, a116, a117, a118, a119, a120, a121, a122, a123, a124);
                        Clas.class_register obj = new Clas.class_register();
                        obj.NonQuery(sql);

//        String sql2 = "INSERT INTO `position` (`Id`, `positintitle`, `positiontype`) VALUES ('7', 'hhhhhh', '2')";
//        sql = String.format(sql,_txtgroup);
//        Clas.class_register obj2 = new Clas.class_register();
//        obj2.NonQuery(sql2);
                        lbl_error.setForeground(Color.green);
                        lbl_error.setText(txtgroup.getText() + "  Inserted!");

                        last();
                        paging_Table();
                        NonQuery();
                        empty();
                        txtgroup.setRequestFocusEnabled(true);
                        txtgroup.requestFocus();

                    }
                }

            } catch (Exception ex) {
                System.out.println("Found some error : " + ex);
            } finally {
                // close all the connections.

            }
        }
    }

    private void btnregisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregisterActionPerformed
        // TODO add your handling code here:
        save();
    }//GEN-LAST:event_btnregisterActionPerformed

    public void edit() {
        if (datatable.getSelectedRow() == -1) {
            lbl_error.setText("Pleas Select a Row!");
        } else if (txtgroup.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Group Name can't be empty",
                    "Warning!",
                    JOptionPane.WARNING_MESSAGE);
        } else {

        //شروع باز شدن درخت واره چک باکس مورد نظر
            for (int i = 1; i <= 11; i += 5) {
                checkBoxTree.expandRow(i);
            }
       //پایان باز شدن درخت واره چک باکس مورد نظر
            //شروع چک خوردن سطر خواص در صورت باز بودن
//        checkBoxTree.getCheckBoxTreeSelectionModel().addSelectionPath(checkBoxTree.getPathForRow(3));
            //پایان چک خوردن سطر خواص در صورت باز بودن

            int a1 = 0;
            int a2 = 0;
            int a3 = 0;
            int a4 = 0;
            int a5 = 0;
            int a6 = 0;
            int a7 = 0;
            int a8 = 0;
            int a9 = 0;
            int a10 = 0;
            int a11 = 0;
            int a12 = 0;
            int a13 = 0;
            int a14 = 0;
            int a15 = 0;
            int a16 = 0;
            int a17 = 0;
            int a18 = 0;
            int a19 = 0;
            int a20 = 0;
            int a21 = 0;
            int a22 = 0;
            int a23 = 0;
            int a24 = 0;
            int a25 = 0;
            int a26 = 0;
            int a27 = 0;
            int a28 = 0;
            int a29 = 0;
            int a30 = 0;
            int a31 = 0;
            int a32 = 0;
            int a33 = 0;
            int a34 = 0;
            int a35 = 0;
            int a36 = 0;
            int a37 = 0;
            int a38 = 0;
            int a39 = 0;
            int a40 = 0;
            int a41 = 0;
            int a42 = 0;
            int a43 = 0;
            int a44 = 0;
            int a45 = 0;
            int a46 = 0;
            int a47 = 0;
            int a48 = 0;
            int a49 = 0;
            int a50 = 0;
            int a51 = 0;
            int a52 = 0;
            int a53 = 0;
            int a54 = 0;
            int a55 = 0;
            int a56 = 0;
            int a57 = 0;
            int a58 = 0;
            int a59 = 0;
            int a60 = 0;
            int a61 = 0;
            int a62 = 0;
            int a63 = 0;
            int a64 = 0;
            int a65 = 0;
            int a66 = 0;
            int a67 = 0;
            int a68 = 0;
            int a69 = 0;
            int a70 = 0;
            int a71 = 0;
            int a72 = 0;
            int a73 = 0;
            int a74 = 0;
            int a75 = 0;
            int a76 = 0;
            int a77 = 0;
            int a78 = 0;
            int a79 = 0;
            int a80 = 0;
            int a81 = 0;
            int a82 = 0;
            int a83 = 0;
            int a84 = 0;
            int a85 = 0;
            int a86 = 0;
            int a87 = 0;
            int a88 = 0;
            int a89 = 0;
            int a90 = 0;
            int a91 = 0;
            int a92 = 0;
            int a93 = 0;
            int a94 = 0;
            int a95 = 0;
            int a96 = 0;
            int a97 = 0;
            int a98 = 0;
            int a99 = 0;
            int a100 = 0;
            int a101 = 0;
            int a102 = 0;
            int a103 = 0;
            int a104 = 0;
            int a105 = 0;
            int a106 = 0;
            int a107 = 0;
            int a108 = 0;
            int a109 = 0;
            int a110 = 0;
            int a111 = 0;
            int a112 = 0;
            int a113 = 0;
            int a114 = 0;
            int a115 = 0;
            int a116 = 0;
            int a117 = 0;
            int a118 = 0;
            int a119 = 0;
            int a120 = 0;
            int a121 = 0;
            int a122 = 0;
            int a123 = 0;
            int a124 = 0;

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(2)) {
                a1 = 1;

            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(3)) {
                a2 = 1;

            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(4)) {
                a3 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(5)) {
                a4 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(7)) {
                a5 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(8)) {
                a6 = 1;

            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(9)) {
                a7 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(10)) {
                a8 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(12)) {//problem
                a9 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(13)) {
                a10 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(14)) {
                a11 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(15)) {
                a12 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(17)) {
                a13 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(18)) {
                a14 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(19)) {
                a15 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(20)) {
                a16 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(22)) {
                a17 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(23)) {
                a18 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(24)) {
                a19 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(25)) {
                a20 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(27)) {
                a21 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(28)) {
                a22 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(29)) {
                a23 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(30)) {
                a24 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(32)) {
                a25 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(33)) {
                a26 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(34)) {
                a27 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(35)) {
                a28 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(37)) {
                a29 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(38)) {
                a30 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(39)) {
                a31 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(40)) {
                a32 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(42)) {
                a33 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(43)) {
                a34 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(44)) {
                a35 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(45)) {
                a36 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(47)) {
                a37 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(48)) {
                a38 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(49)) {
                a39 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(50)) {
                a40 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(52)) {
                a41 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(53)) {
                a42 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(54)) {
                a43 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(55)) {
                a44 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(57)) {
                a45 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(58)) {
                a46 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(59)) {
                a47 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(60)) {
                a48 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(62)) {
                a49 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(63)) {
                a50 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(64)) {
                a51 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(65)) {
                a52 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(67)) {
                a53 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(68)) {
                a54 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(69)) {
                a55 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(70)) {
                a56 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(72)) {
                a57 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(73)) {
                a58 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(74)) {
                a59 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(75)) {
                a60 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(77)) {
                a61 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(78)) {
                a62 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(79)) {
                a63 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(80)) {
                a64 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(82)) {
                a65 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(83)) {
                a66 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(84)) {
                a67 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(85)) {
                a68 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(87)) {
                a69 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(88)) {
                a70 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(89)) {
                a71 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(90)) {
                a72 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(92)) {
                a73 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(93)) {
                a74 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(94)) {
                a75 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(95)) {
                a76 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(97)) {
                a77 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(98)) {
                a78 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(99)) {
                a79 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(100)) {
                a80 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(102)) {
                a81 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(103)) {
                a82 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(104)) {
                a83 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(105)) {
                a84 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(107)) {
                a85 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(108)) {
                a86 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(109)) {
                a87 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(110)) {
                a88 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(112)) {
                a89 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(113)) {
                a90 = 1;
            }

            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(114)) {
                a91 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(115)) {
                a92 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(117)) {
                a93 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(118)) {
                a94 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(119)) {
                a95 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(120)) {
                a96 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(122)) {
                a97 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(123)) {
                a98 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(124)) {

                a99 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(125)) {

                a100 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(127)) {

                a101 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(128)) {

                a102 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(129)) {

                a103 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(130)) {

                a104 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(132)) {

                a105 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(133)) {

                a106 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(134)) {

                a107 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(135)) {

                a108 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(137)) {

                a109 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(138)) {

                a110 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(139)) {

                a111 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(140)) {

                a112 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(142)) {

                a113 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(143)) {

                a114 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(144)) {

                a115 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(145)) {

                a116 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(147)) {

                a117 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(148)) {

                a118 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(149)) {

                a119 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(150)) {

                a120 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(152)) {

                a121 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(153)) {

                a122 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(154)) {

                a123 = 1;
            }
            if (checkBoxTree.getCheckBoxTreeSelectionModel().isRowSelected(155)) {

                a124 = 1;
            }

            int i = datatable.getSelectedRow();
            String _id = datatable.getValueAt(i, 0).toString();
            String _txtgroup = txtgroup.getText();
            Connection connection = null;

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
                    ok = new String(decarray, "UTF-8");
                    String url;
                    url = ok;

                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    connection = DriverManager.getConnection(url);

            //String url =line;
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url);

//جلوگیری از تکرار
                    Statement stmt = con.createStatement();
                    int rowcount = -1;
                    ResultSet result = stmt.executeQuery("select count(ID) from groupnodeaccess WHERE groupname='" + txtgroup.getText() + "'and id not IN ('" + LblID.getText() + "') ");
                    result.next();
                    rowcount = result.getInt(1);
                    stmt.close();
                    con.close();
                    if (rowcount >= 1) {
                        JOptionPane.showMessageDialog(null, "Group Name Is Registered Before");
                    } else {

                        String sql = "update groupnodeaccess set groupname='%s' , AllowView1='%s',AllowInsert1='%s',AllowEdit1='%s',AllowDelete1='%s',AllowView2='%s',AllowInsert2='%s',AllowEdit2='%s',AllowDelete2='%s',AllowView3='%s',AllowInsert3='%s',AllowEdit3='%s',AllowDelete3='%s',AllowView4='%s',AllowInsert4='%s',AllowEdit4='%s',AllowDelete4='%s',AllowView5='%s',AllowInsert5='%s',AllowEdit5='%s',AllowDelete5='%s',AllowView6='%s',AllowInsert6='%s',AllowEdit6='%s',AllowDelete6='%s',AllowView7='%s',AllowInsert7='%s',AllowEdit7='%s',AllowDelete7='%s',AllowView8='%s',AllowInsert8='%s',AllowEdit8='%s',AllowDelete8='%s',AllowView9='%s',AllowInsert9='%s',AllowEdit9='%s',AllowDelete9='%s',AllowView10='%s',AllowInsert10='%s',AllowEdit10='%s',AllowDelete10='%s',AllowView11='%s',AllowInsert11='%s',AllowEdit11='%s',AllowDelete11='%s',AllowView12='%s',AllowInsert12='%s',AllowEdit12='%s',AllowDelete12='%s',AllowView13='%s',AllowInsert13='%s',AllowEdit13='%s',AllowDelete13='%s',AllowView14='%s',AllowInsert14='%s',AllowEdit14='%s',AllowDelete14='%s',AllowView15='%s',AllowInsert15='%s',AllowEdit15='%s',AllowDelete15='%s',AllowView16='%s',AllowInsert16='%s',AllowEdit16='%s',AllowDelete16='%s',AllowView17='%s',AllowInsert17='%s',AllowEdit17='%s',AllowDelete17='%s',AllowView18='%s',AllowInsert18='%s',AllowEdit18='%s',AllowDelete18='%s',AllowView19='%s',AllowInsert19='%s',AllowEdit19='%s',AllowDelete19='%s',AllowView20='%s',AllowInsert20='%s',AllowEdit20='%s',AllowDelete20='%s',AllowView21='%s',AllowInsert21='%s',AllowEdit21='%s',AllowDelete21='%s',AllowView22='%s',AllowInsert22='%s',AllowEdit22='%s',AllowDelete22='%s',AllowView23='%s',AllowInsert23='%s',AllowEdit23='%s',AllowDelete23='%s',AllowView24='%s',AllowInsert24='%s',AllowEdit24='%s',AllowDelete24='%s',AllowView25='%s',AllowInsert25='%s',AllowEdit25='%s',AllowDelete25='%s',AllowView26='%s',AllowInsert26='%s',AllowEdit26='%s',AllowDelete26='%s',AllowView27='%s',AllowInsert27='%s',AllowEdit27='%s',AllowDelete27='%s',AllowView28='%s',AllowInsert28='%s',AllowEdit28='%s',AllowDelete28='%s',AllowView29='%s',AllowInsert29='%s',AllowEdit29='%s',AllowDelete29='%s',AllowView30='%s',AllowInsert30='%s',AllowEdit30='%s',AllowDelete30='%s',AllowView31='%s',AllowInsert31='%s',AllowEdit31='%s',AllowDelete31='%s' where ID='%s'";
                        sql = String.format(sql, _txtgroup, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25, a26, a27, a28, a29, a30, a31, a32, a33, a34, a35, a36, a37, a38, a39, a40, a41, a42, a43, a44, a45, a46, a47, a48, a49, a50, a51, a52, a53, a54, a55, a56, a57, a58, a59, a60, a61, a62, a63, a64, a65, a66, a67, a68, a69, a70, a71, a72, a73, a74, a75, a76, a77, a78, a79, a80, a81, a82, a83, a84, a85, a86, a87, a88, a89, a90, a91, a92, a93, a94, a95, a96, a97, a98, a99, a100, a101, a102, a103, a104, a105, a106, a107, a108, a109, a110, a111, a112, a113, a114, a115, a116, a117, a118, a119, a120, a121, a122, a123, a124, _id);

                        Clas.class_register obj = new Clas.class_register();
                        obj.NonQuery(sql);
                        lbl_error.setForeground(Color.orange);
                        lbl_error.setText(txtgroup.getText() + "Edited!");
                        last();
                        paging_Table();
                        empty();
                        txtgroup.setRequestFocusEnabled(true);
                        txtgroup.requestFocus();
                    }
                }
            } catch (Exception ex) {
                System.out.println("Found some error : " + ex);
            } finally {
                // close all the connections.

            }
        }
    }

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        edit();
    }//GEN-LAST:event_btneditActionPerformed

    private void btnnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnewActionPerformed
        // TODO add your handling code here:
        empty();
    }//GEN-LAST:event_btnnewActionPerformed

    private void datatableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datatableMouseClicked

        for (int ii = 1; ii <= 160; ii += 5) {
            //شروع باز شدن درخت واره چک باکس مورد نظر
            checkBoxTree.expandRow(ii);
         //پایان باز شدن درخت واره چک باکس مورد نظر

        }

        int i = datatable.getSelectedRow();
        LblID.setText(datatable.getValueAt(i, 0).toString());
        txtgroup.setText(datatable.getValueAt(i, 1).toString());

        //add data to tbl 2
        lblID.setText(datatable.getValueAt(i, 0).toString());
        String sql = "select * from groupnodeaccess  where ID=" + lblID.getText() + " ";
        //ID, groupname, AllowView1,AllowInsert1,AllowEdit1,AllowDelete1,AllowView2,AllowInsert2,AllowEdit2,AllowDelete2,AllowView3,AllowInsert3,AllowEdit3,AllowDelete3,AllowView4,AllowInsert4,AllowEdit4,AllowDelete4,AllowView5,AllowInsert5,AllowEdit5,AllowDelete5,AllowView6,AllowInsert6,AllowEdit6,AllowDelete6,AllowView7,AllowInsert7,AllowEdit7,AllowDelete7,AllowView8,AllowInsert8,AllowEdit8,AllowDelete8,AllowView9,AllowInsert9,AllowEdit9,AllowDelete9,AllowView10,AllowInsert10,AllowEdit10,AllowDelete10,AllowView11,AllowInsert11,AllowEdit11,AllowDelete11,AllowView12,AllowInsert12,AllowEdit12,AllowDelete12,AllowView13,AllowInsert13,AllowEdit13,AllowDelete13,AllowView14,AllowInsert14,AllowEdit14,AllowDelete14,AllowView15,AllowInsert15,AllowEdit15,AllowDelete15,AllowView16,AllowInsert16,AllowEdit16,AllowDelete16,AllowView17,AllowInsert17,AllowEdit17,AllowDelete17,AllowView18,AllowInsert18,AllowEdit18,AllowDelete18,AllowView19,AllowInsert19,AllowEdit19,AllowDelete19,AllowView20,AllowInsert20,AllowEdit20,AllowDelete20,AllowView21,AllowInsert21,AllowEdit21,AllowDelete21,AllowView22,AllowInsert22,AllowEdit22,AllowDelete22,AllowView23,AllowInsert23,AllowEdit23,AllowDelete23,AllowView24,AllowInsert24,AllowEdit24,AllowDelete24,AllowView25,AllowInsert25,AllowEdit25,AllowDelete25,AllowView26,AllowInsert26,AllowEdit26,AllowDelete26,AllowView27,AllowInsert27,AllowEdit27,AllowDelete27,AllowView28,AllowInsert28,AllowEdit28,AllowDelete28,AllowView29,AllowInsert29,AllowEdit29,AllowDelete29,AllowView30,AllowInsert30,AllowEdit30,AllowDelete30,AllowView31,AllowInsert31,AllowEdit31,AllowDelete31
        Clas.classtable obj = new Clas.classtable();
        DefaultTableModel dtm = obj.Query(sql);
        Table2.setModel(dtm);
        //add data to tbl 2

        //شروع چک خوردن شاخه ها
        for (int z = 2; z <= 160; z++) {
            //شروع حذف سطر چک خورده خاص در صورت باز بودن
            checkBoxTree.getCheckBoxTreeSelectionModel().removeSelectionPath(checkBoxTree.getPathForRow(z));
            //پایان حذف سطر چک خورده خواص در صورت باز بودن
            lblchecked.setText(Table2.getValueAt(0, z).toString());

            if (lblchecked.getText().equals("1")) {
                //شروع چک خوردن سطر خواص در صورت باز بودن
                checkBoxTree.getCheckBoxTreeSelectionModel().addSelectionPath(checkBoxTree.getPathForRow(z));
                //پایان چک خوردن سطر خواص در صورت باز بودن
            }
        }
        // پایان چک خوردن شاخه ها


    }//GEN-LAST:event_datatableMouseClicked

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        int safe = JOptionPane.showConfirmDialog(null, "Are You Sure?!!", "Closing Form!", JOptionPane.YES_NO_CANCEL_OPTION);
        if (safe == JOptionPane.YES_OPTION) {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);//yes

        } else if (safe == JOptionPane.CANCEL_OPTION) {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//cancel
        } else {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//no
        }
    }//GEN-LAST:event_formInternalFrameClosing

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        Table2.setVisible(false);
        jScrollPane1.setVisible(false);
//        lblID.setVisible(false);
//        lblchecked.setVisible(false);
        last();
        NonQuery();

        txtgroup.setRequestFocusEnabled(true);
        txtgroup.requestFocus();
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        last();
        NonQuery();
    }//GEN-LAST:event_formInternalFrameActivated

    private void menunewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menunewActionPerformed
        // TODO add your handling code here:
        empty();
    }//GEN-LAST:event_menunewActionPerformed

    private void menusaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menusaveActionPerformed
        // TODO add your handling code here:
        save();
    }//GEN-LAST:event_menusaveActionPerformed

    private void menueditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menueditActionPerformed
        // TODO add your handling code here:
        edit();
    }//GEN-LAST:event_menueditActionPerformed

    private void menudeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menudeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_menudeleteActionPerformed

    private void menucloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menucloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_menucloseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblID;
    private javax.swing.JTable Table2;
    private javax.swing.JButton btndelet;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnfirst;
    private javax.swing.JButton btnlast;
    private javax.swing.JButton btnnew;
    private javax.swing.JButton btnnext;
    private javax.swing.JButton btnprevious;
    private javax.swing.JButton btnregister;
    private javax.swing.JButton btnsearch;
    private com.jidesoft.swing.CheckBoxTree checkBoxTree;
    private javax.swing.JButton comboclose;
    private javax.swing.JTable datatable;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblStart;
    private javax.swing.JLabel lbl_error;
    private javax.swing.JLabel lblall;
    private javax.swing.JLabel lblchecked;
    private javax.swing.JLabel lblpag1;
    private javax.swing.JLabel lblpage;
    private javax.swing.JMenuItem menuclose;
    private javax.swing.JMenuItem menudelete;
    private javax.swing.JMenuItem menuedit;
    private javax.swing.JMenuItem menunew;
    private javax.swing.JMenuItem menusave;
    private javax.swing.JTextField txtgroup;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables
}

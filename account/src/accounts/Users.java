/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JInternalFrame;
import java.util.TimerTask;
import java.util.Timer;
import java.util.Date;
import java.awt.ComponentOrientation;
import java.awt.Font;
import javax.swing.JOptionPane;
import java.sql.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.table.TableColumn;
import javax.swing.JFrame;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Admin
 */
public class Users extends javax.swing.JInternalFrame {
//کد پیدا کردن مسیر فایل
//            File file = new File("lib\\Personell_Pic.png");
//            String path = file.getAbsolutePath();
//کد پیدا کردن مسیر فایل

    private static Users jifusers;

    public static Users Users() {
        if (jifusers == null) {
            jifusers = new Users();
        }
        return jifusers;
    }

    //شمارنده تعداد دیتابیس
    public int NonQuery() {

        try {

            //راست چین کردن سلولهای جی تیبل
            //DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            //rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
            //راست چین کردن سلولهای جی تیبل
//     //راست چین کردن هدر جی تیبل  
//    ((DefaultTableCellRenderer)datatable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.RIGHT);
//    //راست چین کردن هدر جی تیبل  
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

                    ResultSet resultSet = st.executeQuery("select count(*) from users");

                    while (resultSet.next()) {
                        //return resultSet.getInt(1);
                        lblall.setText(String.valueOf(resultSet.getInt(1)));
                    }

                    //تغییر سایز سلولها
                    TableColumn column1 = null;
                    column1 = datatable.getColumnModel().getColumn(0);
                    column1.setMaxWidth(60);
                    //تغییر سایز سلولها
                    datatable.getColumnModel().getColumn(0).setHeaderValue("ID");
                    datatable.getColumnModel().getColumn(1).setHeaderValue("Code");
                    datatable.getColumnModel().getColumn(2).setHeaderValue("First Name");
                    datatable.getColumnModel().getColumn(3).setHeaderValue("Last Name");
                    datatable.getColumnModel().getColumn(4).setHeaderValue("Position");
                    datatable.getColumnModel().getColumn(5).setHeaderValue("Job");

//            datatable.getDefaultEditor( Boolean.class );
                    datatable.getColumnModel().getColumn(6).setHeaderValue("group");
                    datatable.getColumnModel().getColumn(7).setHeaderValue("UserName");
                    datatable.getColumnModel().getColumn(8).setHeaderValue("Password");
                    datatable.getColumnModel().getColumn(9).setHeaderValue("Active");
                    datatable.getColumnModel().getColumn(10).setHeaderValue("EndTime");
                    datatable.getColumnModel().getColumn(11).setHeaderValue("EndDate");
                    datatable.getColumnModel().getColumn(12).setHeaderValue("picture");

                }//کد دی هش
                catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//کد دی هش

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return 0;

    }
    String s;
    JTextArea area;
    //شمارنده تعداد دیتابیس

    //هش کردن پسورد
    public static String HashPaasword(String password) throws NoSuchAlgorithmException {
        // MessageDigest md = MessageDigest.getInstance("MD5");
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(password.getBytes());
        byte[] b = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b1 : b) {
            sb.append(Integer.toHexString(b1 & 0xff).toString());
        }
        return sb.toString();
    }
    //هش کردن پسورد

    /**
     * Creates new form users
     */
    public Users() {
        //تغییر آیکن برنامه
//        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Icons/Users.png"));
//        this.setFrameIcon(icon);
        //تغییر آیکن برنامه
        initComponents();
        lblcode.setVisible(false);
        Tablegroups.setVisible(false);
        lblchecked.setVisible(false);
        Tableusersacces.setVisible(false);
        lblidusersacces.setVisible(false);
        //lblpath.setVisible(false);
        lblID.setVisible(false);
        lblhash.setVisible(false);
//        dateserach.setVisible(false);
        lblsearch.setVisible(true);
        txtsearch.setVisible(true);
        dateserach.setVisible(false);
        lblsearch.setVisible(true);
        txtsearch.setVisible(true);
        dateserach.setVisible(false);
//        UIManager.put("Table.background", Color.RED);
//        UIManager.put("Table.alternateRowColor", Color.BLUE);
    }

    //صفحه بندی
    private void Refresh_Table() {
        //limit 1
        //String test = "SELECT COUNT(*) FROM users ";
        //String sql = "select * from users limit 1",5"
        //عدد اول شروع ایندکس عدد دوم سطرهایی که میخوام
        String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID limit 0,10 ";
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
            if (Clas.classpaging.query_to_db("SELECT COUNT(*) AS rowcount FROM users")) {
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

        String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID  ORDER BY ID DESC limit 10  ";
        Clas.classtable obj = new Clas.classtable();
        DefaultTableModel dtm = obj.Query(sql);
        datatable.setModel(dtm);

//        TableColumn tc = datatable.getColumnModel().getColumn(5);
//        tc.setCellEditor(datatable.getDefaultEditor(Boolean.class));
//        tc.setCellRenderer(datatable.getDefaultRenderer(Boolean.class));
        lblpage.setText(String.valueOf(0));

        int index = Integer.parseInt(lblall.getText());
        index = index / 10;
        lblpage.setText(String.valueOf(index));
        lblStart.setText(String.valueOf(index * 10));
        NonQuery();
    }
    //صفحه بندی

//private void timerlast() {
//        try {
//            //assuming it takes 20 secs to complete the task
//            Thread.sleep(20000);
//            last();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
    //خالی کردن
    private void empty() {

        txtCode.setText("");
        txtCode.setText(null);
        txtFirstName.setText("");
        txtLastName.setText("");
        comboPosition.setSelectedIndex(0);
        combogroups.setSelectedIndex(0);
        txtUserName.setText("");
        txtPassword.setText("");
        jDateChooser.setDate(null);
        checktime.setSelected(false);
        radioactive.setSelected(true);

        def = 0;
        _active = "1";
        _checktime = "0";
        _job = "1";
        jLabel17.setVisible(true);
        jLabel14.setVisible(true);
        jLabel16.setVisible(true);
        combogroups.setVisible(true);
        txtUserName.setVisible(true);
        txtPassword.setVisible(true);
        jScrollPane3.setVisible(true);
        checkBoxTree.setVisible(true);
        radioEmployee.setSelected(true);

        //عکس
        //jPanel6.setPreferredSize(new Dimension(162, 173));
        jPanelpic.setPreferredSize(new Dimension(177, 240));
        lblpic.setIcon(new ImageIcon("lib\\Personell_Pic.png"));
        jPanelpic.add(lblpic);
        validate();
        //عکس
        lblpath.setText("null");
        //combomoneytype.setSelectedItem(null);
        last();
        paging_Table();
        NonQuery();
        txtCode.setRequestFocusEnabled(true);
        txtCode.requestFocus();
    }
    //خالی کردن

//combo Position
    Connection con;
    Statement st;
    ResultSet rs;

    private void Position() {
        comboPosition.removeAllItems();

        try {
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
                    st = con.createStatement();
                    String s = "select positintitle from position";
                    rs = st.executeQuery(s);

                    while (rs.next()) {
                        //1=id and 2=username and 3=password
                        comboPosition.addItem(rs.getString(1));

                    }
                    con.close();
                }//کد دی هش
                catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//کد دی هش

        } catch (Exception ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
    }
//combo Position

    // combogroups
    Connection con1;
    Statement st1;
    ResultSet rs1;

    private void combogroup() {
        combogroups.removeAllItems();

        try {
            //کد دی هش
            FileReader reader1 = new FileReader("lib\\Miscsied.jar");
            BufferedReader bufferedReader1 = new BufferedReader(reader1);
            String strDec;
            while ((strDec = bufferedReader1.readLine()) != null) {
                byte[] dectryptArray = strDec.getBytes();
                byte[] decarray1 = Base64.decodeBase64(dectryptArray);
                String ok1;
                try {
                    ok1 = new String(decarray1, "UTF-8");
                    String url1;
                    url1 = ok1;
//کد دی هش

                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    Connection con1 = DriverManager.getConnection(url1);
                    Statement st1 = con1.createStatement();
                    st1 = con1.createStatement();
                    String s1 = "select groupname from groupnodeaccess";
                    rs1 = st1.executeQuery(s1);

                    while (rs1.next()) {
                        //1=id and 2=username and 3=password
                        combogroups.addItem(rs1.getString(1));

                    }
                    con1.close();

                }//کد دی هش
                catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//کد دی هش

        } catch (Exception ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // combogroups

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        btngroupjob = new javax.swing.ButtonGroup();
        lblidusersacces = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tableusersacces = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tablegroups = new javax.swing.JTable();
        lblchecked = new javax.swing.JLabel();
        lblcode = new javax.swing.JLabel();
        buttonGroupsearchew = new javax.swing.ButtonGroup();
        jPaneldsen = new javax.swing.JPanel();
        comboclose = new javax.swing.JButton();
        btndelet = new javax.swing.JButton();
        btnregister = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnnew = new javax.swing.JButton();
        lbl_error = new javax.swing.JLabel();
        lblpath = new javax.swing.JLabel();
        jPanelpaging = new javax.swing.JPanel();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        datatable = new javax.swing.JTable();
        jPanelmain = new javax.swing.JPanel();
        txtFirstName = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jPanelpic = new javax.swing.JPanel();
        lblpic = new javax.swing.JLabel();
        BtnBrows1 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        comboPosition = new javax.swing.JComboBox<>();
        txtPassword = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        combogroups = new javax.swing.JComboBox<>();
        checktime = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        checkBoxTree = new com.jidesoft.swing.CheckBoxTree();
        radioactive = new javax.swing.JRadioButton();
        radiodeactive = new javax.swing.JRadioButton();
        lblID = new javax.swing.JLabel();
        lblhash = new javax.swing.JLabel();
        txtCode = new javax.swing.JFormattedTextField();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        radioEmployee = new javax.swing.JRadioButton();
        radioworker = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblsearch = new javax.swing.JLabel();
        txtsearch = new javax.swing.JTextField();
        btnsearch2 = new javax.swing.JButton();
        radioactivesearch = new javax.swing.JRadioButton();
        radioInactivesearch = new javax.swing.JRadioButton();
        radioallsearch = new javax.swing.JRadioButton();
        comboSearch = new javax.swing.JComboBox<>();
        radioemp = new javax.swing.JRadioButton();
        radiowork = new javax.swing.JRadioButton();
        dateserach = new com.toedter.calendar.JDateChooser();
        radioallwe = new javax.swing.JRadioButton();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        menunew2 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        menusave = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        menuedit = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        menudelete = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        menuclose = new javax.swing.JMenuItem();

        lblidusersacces.setText("0");

        Tableusersacces.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Tableusersacces.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableusersaccesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(Tableusersacces);

        Tablegroups.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(Tablegroups);

        lblchecked.setText("0");

        lblcode.setText("0");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("users");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icon_Form/Personell_15px.png"))); // NOI18N
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

        jPaneldsen.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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

        lblpath.setText("null");

        javax.swing.GroupLayout jPaneldsenLayout = new javax.swing.GroupLayout(jPaneldsen);
        jPaneldsen.setLayout(jPaneldsenLayout);
        jPaneldsenLayout.setHorizontalGroup(
            jPaneldsenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneldsenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboclose, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(lbl_error)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(lblpath)
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
        jPaneldsenLayout.setVerticalGroup(
            jPaneldsenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneldsenLayout.createSequentialGroup()
                .addGroup(jPaneldsenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneldsenLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblpath))
                    .addComponent(comboclose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btndelet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnregister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jPanelpaging.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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

        javax.swing.GroupLayout jPanelpagingLayout = new javax.swing.GroupLayout(jPanelpaging);
        jPanelpaging.setLayout(jPanelpagingLayout);
        jPanelpagingLayout.setHorizontalGroup(
            jPanelpagingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelpagingLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnfirst, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnprevious, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblpag1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblpage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStart)
                .addGap(107, 107, 107)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblall)
                .addGap(83, 83, 83)
                .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnlast, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelpagingLayout.setVerticalGroup(
            jPanelpagingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelpagingLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanelpagingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

        datatable.setAutoCreateRowSorter(true);
        datatable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        datatable.setCellSelectionEnabled(false);
        datatable.setGridColor(new java.awt.Color(102, 255, 0));
        datatable.setRowSelectionAllowed(true);
        datatable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datatableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(datatable);

        jPanelmain.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtFirstName.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel12.setText("First Name:");

        jLabel13.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel13.setText("Last Name:");

        txtLastName.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        jPanelpic.setBorder(javax.swing.BorderFactory.createTitledBorder("Picture"));

        lblpic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Personell_Pic.png"))); // NOI18N

        javax.swing.GroupLayout jPanelpicLayout = new javax.swing.GroupLayout(jPanelpic);
        jPanelpic.setLayout(jPanelpicLayout);
        jPanelpicLayout.setHorizontalGroup(
            jPanelpicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblpic)
        );
        jPanelpicLayout.setVerticalGroup(
            jPanelpicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelpicLayout.createSequentialGroup()
                .addComponent(lblpic, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        BtnBrows1.setText("Browse...");
        BtnBrows1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBrows1ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel19.setText("Code:");

        jLabel20.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel20.setText("Position:");

        comboPosition.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        txtPassword.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel16.setText("Password:");

        txtUserName.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel14.setText("User Name:");

        jLabel17.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel17.setText("Groups:");

        combogroups.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        combogroups.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combogroupsMouseClicked(evt);
            }
        });
        combogroups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combogroupsActionPerformed(evt);
            }
        });
        combogroups.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                combogroupsKeyPressed(evt);
            }
        });

        checktime.setText("End Time");

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

        buttonGroup.add(radioactive);
        radioactive.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        radioactive.setSelected(true);
        radioactive.setText("Active");

        buttonGroup.add(radiodeactive);
        radiodeactive.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        radiodeactive.setText("Deactive");

        lblID.setText("0");

        lblhash.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblhash.setText("null");

        txtCode.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtCode.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        jDateChooser.setDateFormatString("yyyy/MM/dd");
        jDateChooser.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        btngroupjob.add(radioEmployee);
        radioEmployee.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        radioEmployee.setSelected(true);
        radioEmployee.setText("Employee");
        radioEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioEmployeeActionPerformed(evt);
            }
        });

        btngroupjob.add(radioworker);
        radioworker.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        radioworker.setText("Worker");
        radioworker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioworkerActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel21.setText("Job:");

        javax.swing.GroupLayout jPanelmainLayout = new javax.swing.GroupLayout(jPanelmain);
        jPanelmain.setLayout(jPanelmainLayout);
        jPanelmainLayout.setHorizontalGroup(
            jPanelmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmainLayout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanelmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelmainLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(96, 96, 96)
                        .addComponent(radioEmployee)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioworker))
                    .addGroup(jPanelmainLayout.createSequentialGroup()
                        .addGroup(jPanelmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelmainLayout.createSequentialGroup()
                                .addGroup(jPanelmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel19))
                                .addGap(18, 18, 18))
                            .addGroup(jPanelmainLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(25, 25, 25)))
                        .addGroup(jPanelmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPassword)
                            .addComponent(jDateChooser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                            .addComponent(txtCode, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLastName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboPosition, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combogroups, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUserName, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checktime)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                .addGroup(jPanelmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelmainLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(BtnBrows1))
                    .addGroup(jPanelmainLayout.createSequentialGroup()
                        .addComponent(radioactive)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radiodeactive))
                    .addComponent(jPanelpic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelmainLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(lblhash)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelmainLayout.setVerticalGroup(
            jPanelmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmainLayout.createSequentialGroup()
                .addGroup(jPanelmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblhash)
                    .addComponent(lblID))
                .addGap(0, 0, 0)
                .addGroup(jPanelmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelmainLayout.createSequentialGroup()
                        .addGroup(jPanelmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(checktime, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanelmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanelmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanelmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLastName))
                        .addGap(5, 5, 5)
                        .addGroup(jPanelmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(comboPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanelmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioEmployee)
                            .addComponent(radioworker)
                            .addComponent(jLabel21))
                        .addGap(5, 5, 5)
                        .addGroup(jPanelmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(combogroups, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanelmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(jPanelmainLayout.createSequentialGroup()
                        .addComponent(jPanelpic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnBrows1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioactive)
                            .addComponent(radiodeactive))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelmainLayout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelmainLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblsearch.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        lblsearch.setText("Search");

        txtsearch.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txtsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearchActionPerformed(evt);
            }
        });

        btnsearch2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/search_15.png"))); // NOI18N
        btnsearch2.setToolTipText("Search");
        btnsearch2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnsearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearch2ActionPerformed(evt);
            }
        });

        radioactivesearch.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        radioactivesearch.setText("active");

        radioInactivesearch.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        radioInactivesearch.setText("Deactive");

        radioallsearch.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        radioallsearch.setSelected(true);
        radioallsearch.setText("All");
        radioallsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioallsearchActionPerformed(evt);
            }
        });

        comboSearch.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        comboSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Code", "First Name", "Last Name", "Position", "Groups", "User Name", "End Time" }));
        comboSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSearchActionPerformed(evt);
            }
        });

        radioemp.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        radioemp.setText("Employee");

        radiowork.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        radiowork.setText("Worker");

        dateserach.setDateFormatString("YYYY/MM/dd");
        dateserach.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        radioallwe.setFont(new java.awt.Font("Candara", 3, 14)); // NOI18N
        radioallwe.setSelected(true);
        radioallwe.setText("All");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(comboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblsearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateserach, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioallsearch)
                    .addComponent(radioallwe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(radioactivesearch))
                    .addComponent(radioemp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioInactivesearch)
                    .addComponent(radiowork))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblsearch, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtsearch, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dateserach, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboSearch, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(radioactivesearch)
                            .addComponent(radioallsearch)
                            .addComponent(radioInactivesearch))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioemp)
                            .addComponent(radiowork)
                            .addComponent(radioallwe)))
                    .addComponent(btnsearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu5.setText("File");

        menunew2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menunew2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icon_Bar/new_15px.png"))); // NOI18N
        menunew2.setText("New");
        menunew2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menunew2ActionPerformed(evt);
            }
        });
        jMenu5.add(menunew2);
        jMenu5.add(jSeparator7);

        menusave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menusave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icon_Bar/save_15px.png"))); // NOI18N
        menusave.setText("Save");
        menusave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menusaveActionPerformed(evt);
            }
        });
        jMenu5.add(menusave);
        jMenu5.add(jSeparator4);

        menuedit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        menuedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icon_Bar/edit_15px.png"))); // NOI18N
        menuedit.setText("Edit");
        menuedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menueditActionPerformed(evt);
            }
        });
        jMenu5.add(menuedit);
        jMenu5.add(jSeparator8);

        menudelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        menudelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icon_Bar/delete_15px.png"))); // NOI18N
        menudelete.setText("Delete");
        menudelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menudeleteActionPerformed(evt);
            }
        });
        jMenu5.add(menudelete);
        jMenu5.add(jSeparator9);

        menuclose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        menuclose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icon_Bar/close_15px.png"))); // NOI18N
        menuclose.setText("Close");
        menuclose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menucloseActionPerformed(evt);
            }
        });
        jMenu5.add(menuclose);

        jMenuBar5.add(jMenu5);

        setJMenuBar(jMenuBar5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelpaging, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPaneldsen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelmain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelmain, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPaneldsen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelpaging, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public void deleteuseracces() {

        int i = 0;
        //String  _userRef = Tableusersacces.getValueAt(i, 1).toString();
        Clas.class_register obj = new Clas.class_register();
        String sql = "delete from usernodeaccess where userRef='%s'";
        sql = String.format(sql, txtCode.getText());
        obj.NonQuery(sql);

//        lbl_error.setForeground(Color.RED);
//        lbl_error.setText(txtCode.getText()+ "  removed!");
//        last();
//        paging_Table();
//        NonQuery();
//        empty();
//        txtCode.setRequestFocusEnabled(true );
//        txtCode.requestFocus();
    }

    public void delete() {
        if (datatable.getSelectedRow() == -1) {
            lbl_error.setText("Pleas Select a Row!");
        }
        deleteuseracces();
        int i = datatable.getSelectedRow();
        String id = datatable.getValueAt(i, 0).toString();
        Clas.class_register obj = new Clas.class_register();
        String sql = "delete from users where ID='%s'";
        sql = String.format(sql, id);
        obj.NonQuery(sql);
        if (Clas.class_register.check == 1) {
            lbl_error.setForeground(Color.RED);
            lbl_error.setText(txtCode.getText() + " (This Record Reference Is In Use In Another Form!)");
        } else {
            lbl_error.setForeground(Color.RED);
            lbl_error.setText(txtCode.getText() + "  removed!");
        }

        last();
        paging_Table();
        NonQuery();
        empty();
        txtCode.setRequestFocusEnabled(true);
        txtCode.requestFocus();

    }

    private void btndeletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletActionPerformed
        // TODO add your handling code here:

        delete();
    }//GEN-LAST:event_btndeletActionPerformed

    public void edit2() {
        if (datatable.getSelectedRow() == -1) {
            lbl_error.setText("Pleas Select a Row!");
        }

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
        lblID.setText(datatable.getValueAt(i, 0).toString());
        //int i = datatable.getSelectedRow();
        String _id = (Tableusersacces.getValueAt(0, 0).toString());
        //txtCode.getText();
        String _txtCode = txtCode.getText();

        String sql = "update usernodeaccess set userRef='%s' , AllowView1='%s',AllowInsert1='%s',AllowEdit1='%s',AllowDelete1='%s',AllowView2='%s',AllowInsert2='%s',AllowEdit2='%s',AllowDelete2='%s',AllowView3='%s',AllowInsert3='%s',AllowEdit3='%s',AllowDelete3='%s',AllowView4='%s',AllowInsert4='%s',AllowEdit4='%s',AllowDelete4='%s',AllowView5='%s',AllowInsert5='%s',AllowEdit5='%s',AllowDelete5='%s',AllowView6='%s',AllowInsert6='%s',AllowEdit6='%s',AllowDelete6='%s',AllowView7='%s',AllowInsert7='%s',AllowEdit7='%s',AllowDelete7='%s',AllowView8='%s',AllowInsert8='%s',AllowEdit8='%s',AllowDelete8='%s',AllowView9='%s',AllowInsert9='%s',AllowEdit9='%s',AllowDelete9='%s',AllowView10='%s',AllowInsert10='%s',AllowEdit10='%s',AllowDelete10='%s',AllowView11='%s',AllowInsert11='%s',AllowEdit11='%s',AllowDelete11='%s',AllowView12='%s',AllowInsert12='%s',AllowEdit12='%s',AllowDelete12='%s',AllowView13='%s',AllowInsert13='%s',AllowEdit13='%s',AllowDelete13='%s',AllowView14='%s',AllowInsert14='%s',AllowEdit14='%s',AllowDelete14='%s',AllowView15='%s',AllowInsert15='%s',AllowEdit15='%s',AllowDelete15='%s',AllowView16='%s',AllowInsert16='%s',AllowEdit16='%s',AllowDelete16='%s',AllowView17='%s',AllowInsert17='%s',AllowEdit17='%s',AllowDelete17='%s',AllowView18='%s',AllowInsert18='%s',AllowEdit18='%s',AllowDelete18='%s',AllowView19='%s',AllowInsert19='%s',AllowEdit19='%s',AllowDelete19='%s',AllowView20='%s',AllowInsert20='%s',AllowEdit20='%s',AllowDelete20='%s',AllowView21='%s',AllowInsert21='%s',AllowEdit21='%s',AllowDelete21='%s',AllowView22='%s',AllowInsert22='%s',AllowEdit22='%s',AllowDelete22='%s',AllowView23='%s',AllowInsert23='%s',AllowEdit23='%s',AllowDelete23='%s',AllowView24='%s',AllowInsert24='%s',AllowEdit24='%s',AllowDelete24='%s',AllowView25='%s',AllowInsert25='%s',AllowEdit25='%s',AllowDelete25='%s',AllowView26='%s',AllowInsert26='%s',AllowEdit26='%s',AllowDelete26='%s',AllowView27='%s',AllowInsert27='%s',AllowEdit27='%s',AllowDelete27='%s',AllowView28='%s',AllowInsert28='%s',AllowEdit28='%s',AllowDelete28='%s',AllowView29='%s',AllowInsert29='%s',AllowEdit29='%s',AllowDelete29='%s',AllowView30='%s',AllowInsert30='%s',AllowEdit30='%s',AllowDelete30='%s',AllowView31='%s',AllowInsert31='%s',AllowEdit31='%s',AllowDelete31='%s' where userRef ='" + _txtCode + "'";
        sql = String.format(sql, _txtCode, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25, a26, a27, a28, a29, a30, a31, a32, a33, a34, a35, a36, a37, a38, a39, a40, a41, a42, a43, a44, a45, a46, a47, a48, a49, a50, a51, a52, a53, a54, a55, a56, a57, a58, a59, a60, a61, a62, a63, a64, a65, a66, a67, a68, a69, a70, a71, a72, a73, a74, a75, a76, a77, a78, a79, a80, a81, a82, a83, a84, a85, a86, a87, a88, a89, a90, a91, a92, a93, a94, a95, a96, a97, a98, a99, a100, a101, a102, a103, a104, a105, a106, a107, a108, a109, a110, a111, a112, a113, a114, a115, a116, a117, a118, a119, a120, a121, a122, a123, a124);

        Clas.class_register obj = new Clas.class_register();
        obj.NonQuery(sql);
//        lbl_error.setForeground(Color.orange);
//        lbl_error.setText(txtCode.getText()+ "Edited!");
//        last();
//        paging_Table();
//        empty();
        txtCode.setRequestFocusEnabled(true);
        txtCode.requestFocus();

    }

    private void emptyfildsedit() {
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
//reader.close();

                //String url =line;
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url);

//جلوگیری از تکرار
                Statement stmt = con.createStatement();
                int rowcount = -1;
                ResultSet result = stmt.executeQuery("select count(ID) from users WHERE (code='" + txtCode.getText() + "' OR username='" + txtUserName.getText() + "') and ID NOT IN ('" + lblID.getText() + "')");
//select count(ID) from users WHERE (code='9' OR username='9') and ID NOT IN (34)
                result.next();
                rowcount = result.getInt(1);
//stmt.close();
                con.close();
                if (rowcount >= 1) {
                    JOptionPane.showMessageDialog(null, "UserName Or Personal Code Is used Before");

                    def = 1;

                }
            }
        } catch (Exception ex) {
            System.out.println("Found some error : " + ex);
        } finally {
            // close all the connections.

        }
    }

    private void emptyfildseditnodacces() {
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
//reader.close();

                //String url =line;
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url);

//جلوگیری از تکرار
                Statement stmt = con.createStatement();
                int rowcount = -1;
//                and id  NOT IN ('" + lblidusersacces.getText() + "')
                ResultSet result = stmt.executeQuery("select count(ID) from usernodeaccess WHERE (userRef ='" + txtCode.getText() + "' ) ");
//select count(ID) from users WHERE (code='9' OR username='9') and ID NOT IN (34)
                result.next();
                rowcount = result.getInt(1);
//stmt.close();
                con.close();
                if (rowcount >= 1) {
//                    JOptionPane.showMessageDialog(null, "User Is used Before");

                    defnodacces = 1;

                }
            }
        } catch (Exception ex) {
            System.out.println("Found some error : " + ex);
        } finally {
            // close all the connections.

        }
    }
    
    
    public void edit() {
        if (combogroups.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Groups can't be empty",
                    "Warning!",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            emptyfildsedit();
            emptyfildseditnodacces();

            if (datatable.getSelectedRow() == -1) {
                lbl_error.setForeground(Color.BLACK);
                JOptionPane.showMessageDialog(null, "لطفا یک خط را انتخاب کنید!");
            }
            if (def == 0) {
                try {
                    if (radioEmployee.isSelected()) {

                    }
                    if (radioworker.isSelected()) {

                        deleteuseracces();

                    }
                    if (lblpath.getText().equals("null")) {
                        if (txtCode.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Code Name Can't Empty");
                        }
                        if (radioEmployee.isSelected()) {
                            if (txtUserName.getText().equals("")) {
                                JOptionPane.showMessageDialog(null, "User Name Name Can't Empty");
                            }
                        }

                        String _StartDate = "";
                        if (jDateChooser.getDate() != null) {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                            _StartDate = formatter.format(jDateChooser.getDate());
                        }
                        System.out.println(_StartDate + "  _StartDate");
                        //هش پسورد
//                        String password = txtPassword.getText();
                        if (!txtPassword.getText().equals("")) {
                            String password = txtPassword.getText();

                            try {
                                lblhash.setText(HashPaasword(password));
                            } catch (NoSuchAlgorithmException ex) {
                                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        //هش پسورد 
                        if (checktime.isSelected()) {
                            _checktime = "1";
                        } else {
                            _checktime = "0";
                        }

                        if (radioactive.isSelected()) {
                            _active = "1";
                        }

                        if (radiodeactive.isSelected()) {
                            _active = "0";
                        }
                        if (radioEmployee.isSelected()) {
                            _job = "1";
                        }
                        if (radioworker.isSelected()) {
                            _job = "0";
                        }
                        if (!txtPassword.getText().equals("")) {
                            String password = txtPassword.getText();

                            try {
                                lblhash.setText(HashPaasword(password));
                            } catch (NoSuchAlgorithmException ex) {
                                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
//                        try {
//                            lblhash.setText(HashPaasword(password));
//                        } catch (NoSuchAlgorithmException ex) {
//                            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
//                        }

                        PreparedStatement psmnt = null;
                        Connection connection = null;
                        ResultSet rs = null;
                        FileInputStream fis;

                        try {

                            //کانکشن
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
//کانکشن

                                Class.forName("com.mysql.jdbc.Driver").newInstance();
                                connection = DriverManager.getConnection(url);

                                //اینجا کد شروع شود
                                if (radioEmployee.isSelected()) {
//        File image = new File(lblpath.getText());
                                    psmnt = connection.prepareStatement("update users set code=?,fname=?,lname=?,positionref=(select id from position where positintitle =?),ISuser=?,groupsref=(select id from groupnodeaccess where groupname = ?),username=?,password=?,active=?,endtime=?,enddate=? where ID=" + lblID.getText() + "");
                                    psmnt.setString(1, txtCode.getText());
                                    psmnt.setString(2, txtFirstName.getText());
                                    psmnt.setString(3, txtLastName.getText());
                                    psmnt.setString(4, (String) comboPosition.getSelectedItem());
                                    psmnt.setString(5, _job);

                                    psmnt.setString(6, (String) combogroups.getSelectedItem());
                                    psmnt.setString(7, txtUserName.getText());
                                    psmnt.setString(8, lblhash.getText());
                                    psmnt.setString(9, _active);
                                    psmnt.setString(10, _checktime);
                                    psmnt.setString(11, _StartDate);
                                }
                                //اینجا کدتمام شود
                                //اینجا کد شروع شود
                                if (radioworker.isSelected()) {

//        File image = new File(lblpath.getText());
                                    psmnt = connection.prepareStatement("update users set code=?,fname=?,lname=?,positionref=(select id from position where positintitle =?),ISuser=?,groupsref=null,username=null,password=null,active=?,endtime=?,enddate=? where ID=" + lblID.getText() + "");
                                    psmnt.setString(1, txtCode.getText());
                                    psmnt.setString(2, txtFirstName.getText());
                                    psmnt.setString(3, txtLastName.getText());
                                    psmnt.setString(4, (String) comboPosition.getSelectedItem());
                                    psmnt.setString(5, _job);

                                    psmnt.setString(6, _active);
                                    psmnt.setString(7, _checktime);
                                    psmnt.setString(8, _StartDate);
//                                    if (_changejob.equals(_job)) {
//
//                                    }
                                }
                                //اینجا کدتمام شود
                                int s = psmnt.executeUpdate();
                                if (radioEmployee.isSelected()) {
                                    lblhash.setText(null);
                                    txtUserName.setText(null);
                                    if (!_changejob.equals(_job)) {
                                        //ثبت درختواره در تیبل خودش
//                                        درصورت وجود نداشتن ثبت شود ;
                                        
                                         if (defnodacces == 0) {
                                        save2();
                                        //ثبت درختواره در تیبل خودش
                                         }
                                    }
                                    //بروز رسانی درختواره
                                    edit2();
                                    //بروز رسانی درختواره
                                }
                                //lbl_error.setText(txtCode.getText());
                                if (s > 0) {
                                    System.out.println("Uploaded successfully !");
                                    lbl_error.setForeground(Color.green);
                                    lbl_error.setText(txtCode.getText() + "  ویرایش شد!  ");

                                } else {
                                    System.out.println("unsucessfull to upload image.");
                                    lblall.setForeground(Color.black);
                                    lbl_error.setText(txtCode.getText() + "  ویرایش نشد!  ");
                                }
                            }
                            last();
                            paging_Table();
                            NonQuery();
                            empty();
                        } catch (Exception ex) {
                            System.out.println("Found some error : " + ex);
                        } finally {
                            // close all the connections.
                        }

                        txtCode.setRequestFocusEnabled(true);
                        txtCode.requestFocus();
                    } else {
                        if (txtCode.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Code Name Can't Empty");
                        }
                        if (txtUserName.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "User Name Name Can't Empty");
                        }

                        String _StartDate = "";
                        if (jDateChooser.getDate() != null) {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                            _StartDate = formatter.format(jDateChooser.getDate());
                        }
                        System.out.println(_StartDate + "  _StartDate");
                        //هش پسورد
//                        String password = txtPassword.getText();
                        if (!txtPassword.getText().equals("")) {
                            String password = txtPassword.getText();

                            try {
                                lblhash.setText(HashPaasword(password));
                            } catch (NoSuchAlgorithmException ex) {
                                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        //هش پسورد 
                        if (radioEmployee.isSelected()) {
                            _job = "1";
                        }

                        if (radioworker.isSelected()) {
                            _job = "0";
                        }

                        if (checktime.isSelected()) {
                            _checktime = "1";
                        } else {
                            _checktime = "0";
                        }

                        if (radioactive.isSelected()) {
                            _active = "1";
                        }

                        if (radiodeactive.isSelected()) {
                            _active = "0";
                        }
                        if (!txtPassword.getText().equals("")) {
                            String password = txtPassword.getText();

                            try {
                                lblhash.setText(HashPaasword(password));
                            } catch (NoSuchAlgorithmException ex) {
                                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
//                        try {
//                            lblhash.setText(HashPaasword(password));
//                        } catch (NoSuchAlgorithmException ex) {
//                            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
//                        }

                        PreparedStatement psmnt = null;
                        Connection connection = null;
                        ResultSet rs = null;
                        FileInputStream fis;

                        try {

                            //کانکشن
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
//کانکشن

                                Class.forName("com.mysql.jdbc.Driver").newInstance();
                                connection = DriverManager.getConnection(url);

                                //اینجا کد شروع شود
                                if (radioEmployee.isSelected()) {

//      //اینجا کد شروع شود
//        File image = new File(lblpath.getText());
                                    psmnt = connection.prepareStatement("update users set code=?,fname=?,lname=?,positionref=(select id from position where positintitle =?),ISuser=?,groupsref=(select id from groupnodeaccess where groupname = ?),username=?,password=?,active=?,endtime=?,enddate=?,pic=? where ID=" + lblID.getText() + "");
                                    psmnt.setString(1, txtCode.getText());
                                    psmnt.setString(2, txtFirstName.getText());
                                    psmnt.setString(3, txtLastName.getText());
                                    psmnt.setString(4, (String) comboPosition.getSelectedItem());
                                    psmnt.setString(5, _job);

                                    psmnt.setString(6, (String) combogroups.getSelectedItem());
                                    psmnt.setString(7, txtUserName.getText());
                                    psmnt.setString(8, lblhash.getText());
                                    psmnt.setString(9, _active);
                                    psmnt.setString(10, _checktime);
                                    psmnt.setString(11, _StartDate);
//        fis = new FileInputStream(image);
//        psmnt.setBinaryStream(11, (InputStream)fis, (int)(image.length()));
                                    File image = new File("");
                                    if (lblpath.getText().equals("null")) {

                                        psmnt.setBinaryStream(12, null);
                                    } else {

                                        image = new File(lblpath.getText());
                                        fis = new FileInputStream(image);
                                        psmnt.setBinaryStream(12, (InputStream) fis, (int) (image.length()));
                                    }
                                }
                                //اینجا کد تمام شود
                                //اینجا کد شروع شود
                                if (radioworker.isSelected()) {

//      //اینجا کد شروع شود
//        File image = new File(lblpath.getText());
                                    psmnt = connection.prepareStatement("update users set code=?,fname=?,lname=?,positionref=(select id from position where positintitle =?),ISuser=?,groupsref=null,username=null,password=null,active=?,endtime=?,enddate=?,pic=? where ID=" + lblID.getText() + "");
                                    psmnt.setString(1, txtCode.getText());
                                    psmnt.setString(2, txtFirstName.getText());
                                    psmnt.setString(3, txtLastName.getText());
                                    psmnt.setString(4, (String) comboPosition.getSelectedItem());
                                    psmnt.setString(5, _job);

                                    psmnt.setString(6, _active);
                                    psmnt.setString(7, _checktime);
                                    psmnt.setString(8, _StartDate);
//        fis = new FileInputStream(image);
//        psmnt.setBinaryStream(11, (InputStream)fis, (int)(image.length()));
                                    File image = new File("");
                                    if (lblpath.getText().equals("null")) {

                                        psmnt.setBinaryStream(9, null);
                                    } else {

                                        image = new File(lblpath.getText());
                                        fis = new FileInputStream(image);
                                        psmnt.setBinaryStream(9, (InputStream) fis, (int) (image.length()));
                                    }
                                    if (!_changejob.equals(_job)) {
                                        deleteuseracces();
                                    }
                                }
                                //اینجا کد تمام شود
                                int s = psmnt.executeUpdate();

                                if (s > 0) {
                                    System.out.println("Uploaded successfully !");
                                    lbl_error.setForeground(Color.green);
                                    lbl_error.setText(txtCode.getText() + "  ویرایش شد!  ");

                                    if (radioEmployee.isSelected()) {
                                        if (!_changejob.equals(_job)) {

                                           if (defnodacces == 0) {
                                        save2();
                                        //ثبت درختواره در تیبل خودش
                                         }
                                        }
                                        //بروز رسانی درختواره
                                        edit2();
                                        //بروز رسانی درختواره
                                    }
                                } else {
                                    System.out.println("unsucessfull to upload image.");
                                    lblall.setForeground(Color.orange);
                                    lbl_error.setText(txtCode.getText() + "  ویرایش نشد!  ");
                                }
                            }
                        } catch (Exception ex) {
                            System.out.println("Found some error : " + ex);
                        } finally {
                            // close all the connections.
                        }
                    }

                    last();
                    paging_Table();
                    NonQuery();
                    empty();
                    txtCode.setRequestFocusEnabled(true);
                    txtCode.requestFocus();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        edit();
//        edit2();
    }//GEN-LAST:event_btneditActionPerformed

    private void btnnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnewActionPerformed
        // TODO add your handling code here:
        empty();
    }//GEN-LAST:event_btnnewActionPerformed

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
            String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID limit " + index + ",10";
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
            String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID limit " + index + ",10";
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
//edit2();
        this.dispose();
    }//GEN-LAST:event_combocloseActionPerformed

    private void menunew2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menunew2ActionPerformed
        // TODO add your handling code here:
        empty();
    }//GEN-LAST:event_menunew2ActionPerformed

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

    private void BtnBrows1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBrows1ActionPerformed
        // TODO add your handling code here:
        //عکس
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE", "jpg", "jpeg", "png", "gif");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();

            ImageIcon imageIcon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(lblpic.getWidth(), lblpic.getHeight(), Image.SCALE_DEFAULT));
            lblpic.setIcon(imageIcon);
            s = path;
            lblpath.setText(s);

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("No Data");
        }

        //عکس
    }//GEN-LAST:event_BtnBrows1ActionPerformed

    private void datatableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datatableMouseClicked
        // TODO add your handling code here:
        //empty();
        jDateChooser.setDate(null);

        checktime.setSelected(false);
        buttonGroup.clearSelection();
//         radioactive.setSelected(false); 
//         radiodeactive.setSelected(false); 
        File fil = new File("image");
        if (!fil.exists()) {
            if (fil.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        lblpath.setText("null");
        int i = datatable.getSelectedRow();

        lblID.setText(datatable.getValueAt(i, 0).toString());
        System.out.println(lblID.getText());
        txtCode.setText(datatable.getValueAt(i, 1).toString());
        lblcode.setText(datatable.getValueAt(i, 1).toString());
        txtFirstName.setText(datatable.getValueAt(i, 2).toString());
        txtLastName.setText(datatable.getValueAt(i, 3).toString());

        if (datatable.getValueAt(i, 5).equals("1")) {

            radioEmployee.setSelected(true);
            _job = "1";
            jLabel17.setVisible(true);
            jLabel14.setVisible(true);
            jLabel16.setVisible(true);
            combogroups.setVisible(true);
            txtUserName.setVisible(true);
            txtPassword.setVisible(true);
            jScrollPane3.setVisible(true);
            checkBoxTree.setVisible(true);
            radioEmployee.setSelected(true);
            combogroups.setSelectedItem(datatable.getValueAt(i, 6).toString());

            txtUserName.setText(datatable.getValueAt(i, 7).toString());
            lblhash.setText(datatable.getValueAt(i, 8).toString());

            //شروع چک خوردن شاخه های مخصوص یوزر
            String sql2 = "SELECT * FROM `usernodeaccess` WHERE userRef='" + txtCode.getText() + "' ";
            Clas.classtable obj2 = new Clas.classtable();
            DefaultTableModel dtm2 = obj2.Query(sql2);
            Tableusersacces.setModel(dtm2);
            //System.out.println(dtm2 +"   dtm2");
            int co = Tableusersacces.getRowCount();
            int oc = Tableusersacces.getColumnCount();

            //System.out.println(co +"   co");
            //System.out.println(oc +"   oc");
            if (co != 0) {
                String iduseracces = Tableusersacces.getValueAt(0, 0).toString();
                //System.out.println(iduseracces +"   firstidtbluseraccess");
                //System.out.println(dtm.getValueAt(0, 0) + "  dtm.getValueAt(0, 0)");
                //javax.swing.table.DefaultTableModel@333b16c   dtm2
                //add data to tblusers
                //ریختن مقدار آی دی تیبل در لیبل
                lblidusersacces.setText(Tableusersacces.getValueAt(0, 0).toString());
                if (!iduseracces.equals(null)) {
                    //ریختن مقدار آی دی تیبل در لیبل
                    //jLabel1.setText(String.valueOf(c) );

                    //شروع چک خوردن شاخه ها
                    for (int z = 2; z <= 160; z++) {
                        //شروع حذف سطر چک خورده خاص در صورت باز بودن
                        checkBoxTree.getCheckBoxTreeSelectionModel().removeSelectionPath(checkBoxTree.getPathForRow(z));
                        //پایان حذف سطر چک خورده خواص در صورت باز بودن
                        lblchecked.setText(Tableusersacces.getValueAt(0, z).toString());

                        if (lblchecked.getText().equals("1")) {
                            //شروع چک خوردن سطر خواص در صورت باز بودن
                            checkBoxTree.getCheckBoxTreeSelectionModel().addSelectionPath(checkBoxTree.getPathForRow(z));
                            //پایان چک خوردن سطر خواص در صورت باز بودن
                        }
                    }
                    // پایان چک خوردن شاخه ها
                    //پایان چک خوردن شاخه های مخصوص یوزر
                }
            } else {

                JOptionPane.showMessageDialog(null, "Unregistered user access Or the value has been deleted");
            }

        } else {
            radioworker.setSelected(true);
            _job = "0";
            jLabel17.setVisible(false);
            jLabel14.setVisible(false);
            jLabel16.setVisible(false);
            combogroups.setVisible(false);
            txtUserName.setVisible(false);
            txtPassword.setVisible(false);
            jScrollPane3.setVisible(false);
            checkBoxTree.setVisible(false);
            radioEmployee.setSelected(false);
            lblhash.setText("");
            txtUserName.setText("");
            lblhash.setText(null);
            txtUserName.setText(null);

        }

        if (datatable.getValueAt(i, 9).equals("1")) {
            radioactive.setSelected(true);
        } else {
            radiodeactive.setSelected(true);
        }

        if (datatable.getValueAt(i, 10).equals("1")) {
            checktime.setSelected(isSelected);
            checktime.setSelected(true);
        } else {
            //checktime.setSelected(isSelected);
            checktime.setSelected(false);
        }

        DefaultTableModel model = (DefaultTableModel) datatable.getModel();
        Date date;
        try {
            String da = datatable.getValueAt(i, 11).toString();
            if (!da.equals("")) {
                //|| !da.equals(null)
                date = new SimpleDateFormat("yyyy/MM/dd").parse((String) da);
//            date = new SimpleDateFormat("yyyy/MM/dd").parse((String)model.getValueAt(i,10));

                jDateChooser.setDate(date);
            }

        } catch (ParseException ex) {
            //System.out.println(ex  + "    ex1"); 
            ex.printStackTrace();
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        //ليبل عکس      
        //ليبل عکس     
        //ليبل عکس      
        //ليبل عکس     
        //ليبل عکس      
        //ليبل عکس     
        //ليبل عکس      
        //ليبل عکس     

        try {
            //کانکشن

            FileReader reader = new FileReader("lib\\Miscsied.jar");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                byte[] dectryptArray = line.getBytes();
                byte[] decarray = Base64.decodeBase64(dectryptArray);
                String ok;
                //System.out.println(decarray);
                ok = new String(decarray, "UTF-8");
                String url;
                url = ok;
//کانکشن

                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection(url);
                Statement st = con.createStatement();
                //System.out.println(st  + "    st1");  
//ResultSet resultSet = st.executeQuery ("select  * from tbl_pic  WHERE fileid='"+txtfile.getText()+"'AND ID='"+lblID.getText()+"'");

                ResultSet rs = st.executeQuery("select  * from users  WHERE code='" + txtCode.getText() + "'AND ID='" + lblID.getText() + "'");
                //System.out.println(rs  + "    rs1");
                if (rs.next()) {
                    //System.out.println(rs  + "    rs2");
                    java.sql.Blob blob = rs.getBlob("pic");
                    //System.out.println(blob  + "    blob");
                    if (blob != null) {
                        InputStream in = blob.getBinaryStream();
                        //System.out.println(in  + "    pic");

                        BufferedImage image = ImageIO.read(in);
                        //System.out.println(image  + "    image");

                        // ImageIcon icon =new ImageIcon(image);
                        ImageIcon icon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(lblpic.getWidth(), lblpic.getHeight(), Image.SCALE_DEFAULT));
                        lblpic.setIcon(icon);
                    }
                } else {
                    System.out.println("Not found!");

                }

            }
        } catch (Exception ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }

        //ليبل عکس     
        try {
            //کانکشن

            FileReader reader = new FileReader("lib\\Miscsied.jar");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                byte[] dectryptArray = line.getBytes();
                byte[] decarray = Base64.decodeBase64(dectryptArray);
                String ok;
                //System.out.println(decarray);
                ok = new String(decarray, "UTF-8");
                String url;
                url = ok;
//کانکشن

                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection(url);
                Statement st = con.createStatement();

//ResultSet resultSet = st.executeQuery ("select  * from tbl_pic  WHERE fileid='"+txtfile.getText()+"'AND ID='"+lblID.getText()+"'");
                //محل ذخیره
                //System.out.println(con  + "    con");
                //محل ذخیره
                ResultSet rs = st.executeQuery("select  * from users  WHERE code='" + txtCode.getText() + "'AND ID='" + lblID.getText() + "'");
                //System.out.println(rs  + "    rs");
                if (rs.next()) {
                    java.sql.Blob blob = rs.getBlob("pic");
                    if (blob != null) {
                        InputStream in = blob.getBinaryStream();
                        BufferedImage image = ImageIO.read(in);
                        //System.out.println(image  + "    image");
                        // ImageIcon icon =new ImageIcon(image);
                        ImageIcon icon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(lblpic.getWidth(), lblpic.getHeight(), Image.SCALE_DEFAULT));
                        //System.out.println(icon  + "    icon");
                        lblpic.setIcon(icon);

                        File file = new File("image\\" + icon);
                        FileOutputStream fos = new FileOutputStream(file);
                        byte b[];
                        blob = rs.getBlob("pic");
                        b = blob.getBytes(1, (int) blob.length());
                        fos.write(b);
                        //اگر کلوس نشود عکس درست ذخیره نمیشود
                        fos.close();
                        //اگر کلوس نشود عکس درست ذخیره نمیشود

                        File files = new File("image\\" + icon);

                        File del = null;
                        del = new File("image\\picture.jpg");
                        boolean bool = false;
                        bool = del.delete();
                        del = new File("image\\picture.jpg");

                        files.renameTo(new File("image\\picture.jpg"));
                        //  lblpath.setText("image\\"+icon);
//           reader.close();
//           fos.close();
                    } else {
                        //عکس
                        //jPanel6.setPreferredSize(new Dimension(162, 173));
                        JOptionPane.showMessageDialog(null, "The user has no photo");
                        jPanelpic.setPreferredSize(new Dimension(177, 240));
                        lblpic.setIcon(new ImageIcon("lib\\Personell_Pic.png"));
                        jPanelpic.add(lblpic);
                        validate();
                        //عکس
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);

        }

    }//GEN-LAST:event_datatableMouseClicked

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        int safe = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Close Form!", JOptionPane.YES_NO_CANCEL_OPTION);

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

        lblcode.setVisible(false);
        jScrollPane2.setVisible(false);
        Tablegroups.setVisible(false);
        lblchecked.setVisible(false);
        jScrollPane4.setVisible(false);
        Tableusersacces.setVisible(false);
        lblidusersacces.setVisible(false);
        // lblpath.setVisible(false);
        lblID.setVisible(false);
        lblhash.setVisible(false);

        last();
        NonQuery();
        txtCode.setRequestFocusEnabled(true);
        txtCode.requestFocus();
        Position();
        combogroup();

        String _gname = (String) combogroups.getSelectedItem();
        String sql = "select * from groupnodeaccess  where groupname='" + _gname + "' ";

        Clas.classtable obj = new Clas.classtable();
        DefaultTableModel dtm = obj.Query(sql);
        Tablegroups.setModel(dtm);
        //add data to tbl 2

        //شروع چک خوردن شاخه ها
        for (int z = 2; z <= 160; z++) {
            //شروع حذف سطر چک خورده خاص در صورت باز بودن
            checkBoxTree.getCheckBoxTreeSelectionModel().removeSelectionPath(checkBoxTree.getPathForRow(z));
            //پایان حذف سطر چک خورده خواص در صورت باز بودن
            lblchecked.setText(Tablegroups.getValueAt(0, z).toString());

            if (lblchecked.getText().equals("1")) {
                //شروع چک خوردن سطر خواص در صورت باز بودن
                checkBoxTree.getCheckBoxTreeSelectionModel().addSelectionPath(checkBoxTree.getPathForRow(z));
                //پایان چک خوردن سطر خواص در صورت باز بودن
            }
        }
        // پایان چک خوردن شاخه ها
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:

        last();
        NonQuery();
        String _gname = (String) combogroups.getSelectedItem();
        String sql = "select * from groupnodeaccess  where groupname='" + _gname + "' ";

        Clas.classtable obj = new Clas.classtable();
        DefaultTableModel dtm = obj.Query(sql);
        Tablegroups.setModel(dtm);
        //add data to tbl 2

        //شروع چک خوردن شاخه ها
        for (int z = 2; z <= 160; z++) {
            //شروع حذف سطر چک خورده خاص در صورت باز بودن
            checkBoxTree.getCheckBoxTreeSelectionModel().removeSelectionPath(checkBoxTree.getPathForRow(z));
            //پایان حذف سطر چک خورده خواص در صورت باز بودن
            lblchecked.setText(Tablegroups.getValueAt(0, z).toString());

            if (lblchecked.getText().equals("1")) {
                //شروع چک خوردن سطر خواص در صورت باز بودن
                checkBoxTree.getCheckBoxTreeSelectionModel().addSelectionPath(checkBoxTree.getPathForRow(z));
                //پایان چک خوردن سطر خواص در صورت باز بودن
            }
        }
        // پایان چک خوردن شاخه ها

        //مخفی کردن لیبل هش
        lblcode.setVisible(false);
        Tablegroups.setVisible(false);
        lblchecked.setVisible(false);
        Tableusersacces.setVisible(false);
        lblidusersacces.setVisible(false);
        lblpath.setVisible(false);
        lblID.setVisible(false);
        lblhash.setVisible(false);
        //مخفی کردن لیبل هش

        for (int ii = 1; ii <= 160; ii += 5) {
            //شروع باز شدن درخت واره چک باکس مورد نظر
            checkBoxTree.expandRow(ii);
            //پایان باز شدن درخت واره چک باکس مورد نظر

        }
    }//GEN-LAST:event_formInternalFrameActivated
    private void emptyfildssave() {
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
//reader.close();

                //String url =line;
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url);

//جلوگیری از تکرار
                Statement stmt = con.createStatement();
                int rowcount = -1;
                ResultSet result = stmt.executeQuery("select count(ID) from users WHERE code='" + txtCode.getText() + "' OR username='" + txtUserName.getText() + "'");
                result.next();
                rowcount = result.getInt(1);
//stmt.close();
                con.close();
                if (rowcount >= 1) {
                    JOptionPane.showMessageDialog(null, "UserName Or Personal Code Is used Before");

                    def = 1;

                }
            }
        } catch (Exception ex) {
            System.out.println("Found some error : " + ex);
        } finally {
            // close all the connections.

        }
    }

    public void save2() {

        for (int i = 1; i <= 160; i += 5) {
            //شروع باز شدن درخت واره چک باکس مورد نظر
            checkBoxTree.expandRow(i);
            //پایان باز شدن درخت واره چک باکس مورد نظر

        }

        String _txtCode = txtCode.getText();
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

        //groupname,AllowView1,AllowInsert1,AllowEdit1,AllowDelete1,AllowPrint1,revocation1,AllowView2,AllowInsert2,AllowEdit2,AllowDelete2,AllowPrint2,revocation2,AllowView3,AllowInsert3,AllowEdit3,AllowDelete3,AllowPrint3,revocation3,AllowView4,AllowInsert4,AllowEdit4,AllowDelete4,AllowPrint4,revocation4,AllowView5,AllowInsert5,AllowEdit5,AllowDelete5,AllowPrint5,revocation5,AllowView6,AllowInsert6,AllowEdit6,AllowDelete6,AllowPrint6,revocation6,AllowView7,AllowInsert7,AllowEdit7,AllowDelete7,AllowPrint7,revocation7,AllowView8,AllowInsert8,AllowEdit8,AllowDelete8,AllowPrint8,revocation8,AllowView9,AllowInsert9,AllowEdit9,AllowDelete9,AllowPrint9,revocation9,AllowView10,AllowInsert10,AllowEdit10,AllowDelete10,AllowPrint10,revocation10,AllowView11,AllowInsert11,AllowEdit11,AllowDelete11,AllowPrint11,revocation11,AllowView12,AllowInsert12,AllowEdit12,AllowDelete12,AllowPrint12,revocation12,AllowView13,AllowInsert13,AllowEdit13,AllowDelete13,AllowPrint13,revocation13,AllowView14,AllowInsert14,AllowEdit14,AllowDelete14,AllowPrint14,revocation14,AllowView15,AllowInsert15,AllowEdit15,AllowDelete15,AllowPrint15,revocation15,AllowView16,AllowInsert16,AllowEdit16,AllowDelete16,AllowPrint16,revocation16,AllowView17,AllowInsert17,AllowEdit17,AllowDelete17,AllowPrint17,revocation17,AllowView18,AllowInsert18,AllowEdit18,AllowDelete18,AllowPrint18,revocation18,AllowView19,AllowInsert19,AllowEdit19,AllowDelete19,AllowPrint19,revocation19,AllowView20,AllowInsert20,AllowEdit20,AllowDelete20,AllowPrint20,revocation20,AllowView21,AllowInsert21,AllowEdit21,AllowDelete21,AllowPrint21,revocation21,AllowView22,AllowInsert22,AllowEdit22,AllowDelete22,AllowPrint22,revocation22,AllowView23,AllowInsert23,AllowEdit23,AllowDelete23,AllowPrint23,revocation23,AllowView24,AllowInsert24,AllowEdit24,AllowDelete24,AllowPrint24,revocation24,AllowView25,AllowInsert25,AllowEdit25,AllowDelete25,AllowPrint25,revocation25,AllowView26,AllowInsert26,AllowEdit26,AllowDelete26,AllowPrint26,revocation26,AllowView27,AllowInsert27,AllowEdit27,AllowDelete27,AllowPrint27,revocation27,AllowView28,AllowInsert28,AllowEdit28,AllowDelete28,AllowPrint28,revocation28,AllowView29,AllowInsert29,AllowEdit29,AllowDelete29,AllowPrint29,revocation29,AllowView30,AllowInsert30,AllowEdit30,AllowDelete30,AllowPrint30,revocation30,AllowView31,AllowInsert31,AllowEdit31,AllowDelete31,AllowPrint31,revocation31,AllowView32,AllowInsert32,AllowEdit32,AllowDelete32,AllowPrint32,revocation32,AllowView33,AllowInsert33,AllowEdit33,AllowDelete33,AllowPrint33,revocation33,AllowView34,AllowInsert34,AllowEdit34,AllowDelete34,AllowPrint34,revocation34,AllowView35,AllowInsert35,AllowEdit35,AllowDelete35,AllowPrint35,revocation35,AllowView36,AllowInsert36,AllowEdit36,AllowDelete36,AllowPrint36,revocation36,AllowView37,AllowInsert37,AllowEdit37,AllowDelete37,AllowPrint37,revocation37,AllowView38,AllowInsert38,AllowEdit38,AllowDelete38,AllowPrint38,revocation38,38AllowView40,AllowInsert40,AllowEdit40,AllowDelete40,AllowPrint40,revocation40
        String sql = "insert into usernodeaccess (userRef,AllowView1,AllowInsert1,AllowEdit1,AllowDelete1,AllowView2,AllowInsert2,AllowEdit2,AllowDelete2,AllowView3,AllowInsert3,AllowEdit3,AllowDelete3,AllowView4,AllowInsert4,AllowEdit4,AllowDelete4,AllowView5,AllowInsert5,AllowEdit5,AllowDelete5,AllowView6,AllowInsert6,AllowEdit6,AllowDelete6,AllowView7,AllowInsert7,AllowEdit7,AllowDelete7,AllowView8,AllowInsert8,AllowEdit8,AllowDelete8,AllowView9,AllowInsert9,AllowEdit9,AllowDelete9,AllowView10,AllowInsert10,AllowEdit10,AllowDelete10,AllowView11,AllowInsert11,AllowEdit11,AllowDelete11,AllowView12,AllowInsert12,AllowEdit12,AllowDelete12,AllowView13,AllowInsert13,AllowEdit13,AllowDelete13,AllowView14,AllowInsert14,AllowEdit14,AllowDelete14,AllowView15,AllowInsert15,AllowEdit15,AllowDelete15,AllowView16,AllowInsert16,AllowEdit16,AllowDelete16,AllowView17,AllowInsert17,AllowEdit17,AllowDelete17,AllowView18,AllowInsert18,AllowEdit18,AllowDelete18,AllowView19,AllowInsert19,AllowEdit19,AllowDelete19,AllowView20,AllowInsert20,AllowEdit20,AllowDelete20,AllowView21,AllowInsert21,AllowEdit21,AllowDelete21,AllowView22,AllowInsert22,AllowEdit22,AllowDelete22,AllowView23,AllowInsert23,AllowEdit23,AllowDelete23,AllowView24,AllowInsert24,AllowEdit24,AllowDelete24,AllowView25,AllowInsert25,AllowEdit25,AllowDelete25,AllowView26,AllowInsert26,AllowEdit26,AllowDelete26,AllowView27,AllowInsert27,AllowEdit27,AllowDelete27,AllowView28,AllowInsert28,AllowEdit28,AllowDelete28,AllowView29,AllowInsert29,AllowEdit29,AllowDelete29,AllowView30,AllowInsert30,AllowEdit30,AllowDelete30,AllowView31,AllowInsert31,AllowEdit31,AllowDelete31)"
                + "values ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";
        sql = String.format(sql, _txtCode, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25, a26, a27, a28, a29, a30, a31, a32, a33, a34, a35, a36, a37, a38, a39, a40, a41, a42, a43, a44, a45, a46, a47, a48, a49, a50, a51, a52, a53, a54, a55, a56, a57, a58, a59, a60, a61, a62, a63, a64, a65, a66, a67, a68, a69, a70, a71, a72, a73, a74, a75, a76, a77, a78, a79, a80, a81, a82, a83, a84, a85, a86, a87, a88, a89, a90, a91, a92, a93, a94, a95, a96, a97, a98, a99, a100, a101, a102, a103, a104, a105, a106, a107, a108, a109, a110, a111, a112, a113, a114, a115, a116, a117, a118, a119, a120, a121, a122, a123, a124);
        Clas.class_register obj = new Clas.class_register();
        obj.NonQuery(sql);

//        String sql2 = "INSERT INTO `position` (`Id`, `positintitle`, `positiontype`) VALUES ('7', 'hhhhhh', '2')";
//        sql = String.format(sql,_txtgroup);
//        Clas.class_register obj2 = new Clas.class_register();
//        obj2.NonQuery(sql2);
        lbl_error.setForeground(Color.green);
        lbl_error.setText(txtCode.getText() + "  Inserted!");

        last();
        paging_Table();
        NonQuery();
        empty();
        txtCode.setRequestFocusEnabled(true);
        txtCode.requestFocus();

    }

    public void save() {
        if (combogroups.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Groups can't be empty",
                    "Warning!",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            if (radioEmployee.isSelected()) {
                emptyfildssave();
            }
            if (def == 0) {

                PreparedStatement psmnt = null;
                Connection connection = null;
                ResultSet rs = null;
                FileInputStream fis;
                String password = txtPassword.getText();

                try {
                    lblhash.setText(HashPaasword(password));
                } catch (NoSuchAlgorithmException e) {
                    Logger.getLogger(this.getName()).log(Level.SEVERE, null, e);
                }
                try {

                    //کانکشن
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
//کانکشن

                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        connection = DriverManager.getConnection(url);

                        if (radioEmployee.isSelected()) {

                            psmnt = connection.prepareStatement("insert into users (code,fname,lname,positionref,ISuser,groupsref,username,password,active,endtime,enddate,pic) values(?,?,?,(SELECT ID FROM `position` WHERE positintitle=?),?,(SELECT ID FROM `groupnodeaccess` WHERE groupname=?),?,?,?,?,?,?)");
                            psmnt.setString(1, txtCode.getText());
                            psmnt.setString(2, txtFirstName.getText());
                            psmnt.setString(3, txtLastName.getText());
                            psmnt.setString(4, (String) comboPosition.getSelectedItem());
                            if (radioEmployee.isSelected()) {
                                _job = "1";
                            }
                            if (radioworker.isSelected()) {
                                _job = "0";
                            }
                            psmnt.setString(5, _job);
                            psmnt.setString(6, (String) combogroups.getSelectedItem());
                            psmnt.setString(7, txtUserName.getText());
                            psmnt.setString(8, lblhash.getText());
                            if (radioactive.isSelected()) {
                                _active = "1";
                                System.out.println(_active + "  _active");
                            }
                            if (radiodeactive.isSelected()) {
                                _active = "0";
                                System.out.println(_active + "  _active");
                            }
                            psmnt.setString(9, _active);

                            if (!checktime.isSelected()) {
                                _checktime = "0";
                                System.out.println(_checktime + "  _checktime");
                            } else {
                                _checktime = "1";
                                System.out.println(_checktime + "  _checktime");
                            }
                            psmnt.setString(10, _checktime);

                            String _StartDate = "";
                            if (jDateChooser.getDate() != null) {
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                                _StartDate = formatter.format(jDateChooser.getDate());
                            }
                            psmnt.setString(11, _StartDate);

                            if (lblpath.getText().equals("null")) {
                                psmnt.setBinaryStream(12, null);
                            } else {
                                File image = new File("");
                                image = new File(lblpath.getText());
                                fis = new FileInputStream(image);
                                psmnt.setBinaryStream(12, (InputStream) fis, (int) (image.length()));
                            }
                        }

                        if (radioworker.isSelected()) {

                            psmnt = connection.prepareStatement("insert into users (code,fname,lname,positionref,ISuser,active,endtime,enddate,pic) values(?,?,?,(SELECT ID FROM `position` WHERE positintitle=?),?,?,?,?,?)");
                            psmnt.setString(1, txtCode.getText());
                            psmnt.setString(2, txtFirstName.getText());
                            psmnt.setString(3, txtLastName.getText());
                            psmnt.setString(4, (String) comboPosition.getSelectedItem());
                            if (radioEmployee.isSelected()) {
                                _job = "1";
                            }
                            if (radioworker.isSelected()) {
                                _job = "0";
                            }
                            psmnt.setString(5, _job);

                            if (radioactive.isSelected()) {
                                _active = "1";
                                System.out.println(_active + "  _active");
                            }
                            if (radiodeactive.isSelected()) {
                                _active = "0";
                                System.out.println(_active + "  _active");
                            }
                            psmnt.setString(6, _active);

                            if (!checktime.isSelected()) {
                                _checktime = "0";
                                System.out.println(_checktime + "  _checktime");
                            } else {
                                _checktime = "1";
                                System.out.println(_checktime + "  _checktime");
                            }
                            psmnt.setString(7, _checktime);

                            String _StartDate = "";
                            if (jDateChooser.getDate() != null) {
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                                _StartDate = formatter.format(jDateChooser.getDate());
                            }
                            psmnt.setString(8, _StartDate);

                            if (lblpath.getText().equals("null")) {
                                psmnt.setBinaryStream(9, null);
                            } else {
                                File image = new File("");
                                image = new File(lblpath.getText());
                                fis = new FileInputStream(image);
                                psmnt.setBinaryStream(9, (InputStream) fis, (int) (image.length()));
                            }
                        }
                        //fis = new FileInputStream(image);
//        psmnt.setBinaryStream(2, (InputStream)fis, (int)(image.length()));
                        int s = psmnt.executeUpdate();
                        if (s > 0) {
                            System.out.println("Uploaded successfully !");
                            lbl_error.setForeground(Color.green);
                            lbl_error.setText(txtCode.getText() + "  ثبت شد!");
                            if (radioEmployee.isSelected()) {
                                if (defnodacces == 0) {
                                        save2();
                                        //ثبت درختواره در تیبل خودش
                                         }
                            }
                        } else {
                            System.out.println("unsucessfull to upload image.");
                            lblall.setForeground(Color.RED);
                            lbl_error.setText(txtCode.getText() + "  ثبت نشد!");
                        }
                    }

                    last();
                    paging_Table();
                    NonQuery();
                    empty();
                    txtCode.setRequestFocusEnabled(true);
                    txtCode.requestFocus();

                } catch (Exception ex) {
                    System.out.println("Found some error : " + ex);
                } finally {
                    // close all the connections.

                }

            }
            def = 0;
        }
    }
    private void btnregisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregisterActionPerformed
        // TODO add your handling code here:
        save();

    }//GEN-LAST:event_btnregisterActionPerformed

    private void combogroupsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combogroupsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_combogroupsKeyPressed

    private void combogroupsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combogroupsMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_combogroupsMouseClicked

    private void combogroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combogroupsActionPerformed
        // TODO add your handling code here:
        String _gname = (String) combogroups.getSelectedItem();
        String sql = "select * from groupnodeaccess  where groupname='" + _gname + "' ";

        Clas.classtable obj = new Clas.classtable();
        DefaultTableModel dtm = obj.Query(sql);
        Tablegroups.setModel(dtm);
        //add data to tbl 2

        //شروع چک خوردن شاخه ها
        for (int z = 2; z <= 160; z++) {
            //شروع حذف سطر چک خورده خاص در صورت باز بودن
            checkBoxTree.getCheckBoxTreeSelectionModel().removeSelectionPath(checkBoxTree.getPathForRow(z));
            //پایان حذف سطر چک خورده خواص در صورت باز بودن
            lblchecked.setText(Tablegroups.getValueAt(0, z).toString());

            if (lblchecked.getText().equals("1")) {
                //شروع چک خوردن سطر خواص در صورت باز بودن
                checkBoxTree.getCheckBoxTreeSelectionModel().addSelectionPath(checkBoxTree.getPathForRow(z));
                //پایان چک خوردن سطر خواص در صورت باز بودن
            }
        }
        // پایان چک خوردن شاخه ها
    }//GEN-LAST:event_combogroupsActionPerformed

    private void TableusersaccesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableusersaccesMouseClicked
        // TODO add your handling code here:
        String sql = "select * from usernodeaccess  where userRef='" + txtCode.getText() + "' ";
        Clas.classtable obj1 = new Clas.classtable();
        DefaultTableModel dtm1 = obj1.Query(sql);
        Tableusersacces.setModel(dtm1);
        int c = Tableusersacces.getSelectedRow();
        //lblchecked.setText(Table3.getValueAt(c, 0).toString());
        lblidusersacces.setText(Tableusersacces.getValueAt(c, 0).toString());
    }//GEN-LAST:event_TableusersaccesMouseClicked

    private void radioEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioEmployeeActionPerformed
        // TODO add your handling code here:

        _job = "1";
        jLabel17.setVisible(true);
        jLabel14.setVisible(true);
        jLabel16.setVisible(true);
        combogroups.setVisible(true);
        txtUserName.setVisible(true);
        txtPassword.setVisible(true);
        jScrollPane3.setVisible(true);
        checkBoxTree.setVisible(true);


    }//GEN-LAST:event_radioEmployeeActionPerformed

    private void radioworkerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioworkerActionPerformed
        // TODO add your handling code here:

        _job = "10";
        jLabel17.setVisible(false);
        jLabel14.setVisible(false);
        jLabel16.setVisible(false);
        combogroups.setVisible(false);
        txtUserName.setVisible(false);
        txtPassword.setVisible(false);
        jScrollPane3.setVisible(false);
        checkBoxTree.setVisible(false);


    }//GEN-LAST:event_radioworkerActionPerformed

    private void txtsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchActionPerformed

    private void btnsearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearch2ActionPerformed
        // TODO add your handling code here:

        if ((String) comboSearch.getSelectedItem() == "Code") {
            if (radioallsearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `code` LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Code") {
            if (radioallsearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `positionref` ='1' AND `code` LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Code") {
            if (radioallsearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `positionref` ='0' AND `code` LIKE '%" + txtsearch.getText() + "%'   ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Code") {
            if (radioactivesearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='1' AND`code` LIKE '%" + txtsearch.getText() + "%'   ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Code") {
            if (radioactivesearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='1' AND `ISuser`='1' AND `code` LIKE '%" + txtsearch.getText() + "%'   ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Code") {
            if (radioactivesearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='1' AND `ISuser`='0' AND `code` LIKE '%" + txtsearch.getText() + "%'   ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Code") {
            if (radioInactivesearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='0'  AND `code` LIKE '%" + txtsearch.getText() + "%'   ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Code") {
            if (radioInactivesearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='0' AND `ISuser`='1' AND `code` LIKE '%" + txtsearch.getText() + "%'   ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Code") {
            if (radioInactivesearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='0' AND `ISuser`='0' AND `code` LIKE '%" + txtsearch.getText() + "%'   ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "First Name") {
            if (radioallsearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `fname` LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "First Name") {
            if (radioallsearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `positionref` ='1' AND `fname` LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "First Name") {
            if (radioallsearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `positionref` ='0' AND `fname` LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "First Name") {
            if (radioactivesearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='1' AND`fname` LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "First Name") {
            if (radioactivesearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='1' AND `ISuser`='1' AND `fname` LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "First Name") {
            if (radioactivesearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='1' AND `ISuser`='0' AND `fname` LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "First Name") {
            if (radioInactivesearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='0'  AND `fname` LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "First Name") {
            if (radioInactivesearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='0' AND `ISuser`='1' AND `fname` LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "First Name") {
            if (radioInactivesearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='0' AND `ISuser`='0' AND `fname` LIKE '%\"+txtsearch.getText()+\"%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Last Name") {
            if (radioallsearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `lname`  LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Last Name") {
            if (radioallsearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `positionref` ='1' AND `lname`  LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Last Name") {
            if (radioallsearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `positionref` ='0' AND `lname`  LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Last Name") {
            if (radioactivesearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='1' AND`lname`  LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Last Name") {
            if (radioactivesearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='1' AND `ISuser`='1' AND `lname`  LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Last Name") {
            if (radioactivesearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='1' AND `ISuser`='0' AND `lname`  LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Last Name") {
            if (radioInactivesearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='0'  AND `lname`  LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Last Name") {
            if (radioInactivesearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='0' AND `ISuser`='1' AND `lname`  LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Last Name") {
            if (radioInactivesearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='0' AND `ISuser`='0' AND `lname`  LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Position") {
            if (radioallsearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "SELECT workstation.id AS 'ID',workstation.WorkStationName AS 'Name',CONCAT (costcenters.`id`,\" \",costcenters.`CostCentersName`) AS 'Cost Centers',workstation.Status AS 'Status' FROM `workstation`INNER JOIN costcenters on workstation.CostCentersRef=costcenters.id WHERE positionref  =  (SELECT `id` FROM `position` WHERE `positintitle` = '" + txtsearch.getText() + "' ) ";
                    Clas.classtable obj = new Clas.classtable();
                    //                DefaultTableModel dtm = obj.Query(sql);
                    //                datatable.setModel(dtm);
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Position") {
            if (radioallsearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "SELECT workstation.id AS 'ID',workstation.WorkStationName AS 'Name',CONCAT (costcenters.`id`,\" \",costcenters.`CostCentersName`) AS 'Cost Centers',workstation.Status AS 'Status' FROM `workstation`INNER JOIN costcenters on workstation.CostCentersRef=costcenters.id WHERE positionref  =  (SELECT `id` FROM `position` WHERE `positintitle` = '" + txtsearch.getText() + "' ) ";
                    Clas.classtable obj = new Clas.classtable();
                    //                DefaultTableModel dtm = obj.Query(sql);
                    //                datatable.setModel(dtm);
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Position") {
            if (radioallsearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "SELECT workstation.id AS 'ID',workstation.WorkStationName AS 'Name',CONCAT (costcenters.`id`,\" \",costcenters.`CostCentersName`) AS 'Cost Centers',workstation.Status AS 'Status' FROM `workstation`INNER JOIN costcenters on workstation.CostCentersRef=costcenters.id WHERE positionref  =  (SELECT `id` FROM `position` WHERE `positintitle` = '" + txtsearch.getText() + "' ) ";
                    Clas.classtable obj = new Clas.classtable();
                    //                DefaultTableModel dtm = obj.Query(sql);
                    //                datatable.setModel(dtm);
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Position") {
            if (radioactivesearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "SELECT workstation.id AS 'ID',workstation.WorkStationName AS 'Name',CONCAT (costcenters.`id`,\" \",costcenters.`CostCentersName`) AS 'Cost Centers',workstation.Status AS 'Status' FROM `workstation`INNER JOIN costcenters on workstation.CostCentersRef=costcenters.id WHERE positionref  =  (SELECT `id` FROM `position` WHERE `positintitle` = '" + txtsearch.getText() + "' ) ";
                    Clas.classtable obj = new Clas.classtable();
                    //                DefaultTableModel dtm = obj.Query(sql);
                    //                datatable.setModel(dtm);
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Position") {
            if (radioactivesearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "SELECT workstation.id AS 'ID',workstation.WorkStationName AS 'Name',CONCAT (costcenters.`id`,\" \",costcenters.`CostCentersName`) AS 'Cost Centers',workstation.Status AS 'Status' FROM `workstation`INNER JOIN costcenters on workstation.CostCentersRef=costcenters.id WHERE positionref  =  (SELECT `id` FROM `position` WHERE `positintitle` = '" + txtsearch.getText() + "' ) ";
                    Clas.classtable obj = new Clas.classtable();
                    //                DefaultTableModel dtm = obj.Query(sql);
                    //                datatable.setModel(dtm);
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Position") {
            if (radioactivesearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "SELECT workstation.id AS 'ID',workstation.WorkStationName AS 'Name',CONCAT (costcenters.`id`,\" \",costcenters.`CostCentersName`) AS 'Cost Centers',workstation.Status AS 'Status' FROM `workstation`INNER JOIN costcenters on workstation.CostCentersRef=costcenters.id WHERE positionref  =  (SELECT `id` FROM `position` WHERE `positintitle` = '" + txtsearch.getText() + "' ) ";
                    Clas.classtable obj = new Clas.classtable();
                    //                DefaultTableModel dtm = obj.Query(sql);
                    //                datatable.setModel(dtm);
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Position") {
            if (radioInactivesearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "SELECT workstation.id AS 'ID',workstation.WorkStationName AS 'Name',CONCAT (costcenters.`id`,\" \",costcenters.`CostCentersName`) AS 'Cost Centers',workstation.Status AS 'Status' FROM `workstation`INNER JOIN costcenters on workstation.CostCentersRef=costcenters.id WHERE positionref  =  (SELECT `id` FROM `position` WHERE `positintitle` = '" + txtsearch.getText() + "' ) ";
                    Clas.classtable obj = new Clas.classtable();
                    //                DefaultTableModel dtm = obj.Query(sql);
                    //                datatable.setModel(dtm);
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Position") {
            if (radioInactivesearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "SELECT workstation.id AS 'ID',workstation.WorkStationName AS 'Name',CONCAT (costcenters.`id`,\" \",costcenters.`CostCentersName`) AS 'Cost Centers',workstation.Status AS 'Status' FROM `workstation`INNER JOIN costcenters on workstation.CostCentersRef=costcenters.id WHERE positionref  =  (SELECT `id` FROM `position` WHERE `positintitle` = '" + txtsearch.getText() + "' ) ";
                    Clas.classtable obj = new Clas.classtable();
                    //                DefaultTableModel dtm = obj.Query(sql);
                    //                datatable.setModel(dtm);
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Position") {
            if (radioInactivesearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "SELECT workstation.id AS 'ID',workstation.WorkStationName AS 'Name',CONCAT (costcenters.`id`,\" \",costcenters.`CostCentersName`) AS 'Cost Centers',workstation.Status AS 'Status' FROM `workstation`INNER JOIN costcenters on workstation.CostCentersRef=costcenters.id WHERE positionref  =  (SELECT `id` FROM `position` WHERE `positintitle` = '" + txtsearch.getText() + "' ) ";
                    Clas.classtable obj = new Clas.classtable();
                    //                DefaultTableModel dtm = obj.Query(sql);
                    //                datatable.setModel(dtm);
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Groups") {
            if (radioallsearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "SELECT workstation.id AS 'ID',workstation.WorkStationName AS 'Name',CONCAT (costcenters.`id`,\" \",costcenters.`CostCentersName`) AS 'Cost Centers',workstation.Status AS 'Status' FROM `workstation`INNER JOIN costcenters on workstation.CostCentersRef=costcenters.id WHERE groupsref =  (SELECT `id` FROM `groupnodeaccess` WHERE `groupname` = '" + txtsearch.getText() + "' ) ";
                    Clas.classtable obj = new Clas.classtable();
                    //                DefaultTableModel dtm = obj.Query(sql);
                    //                datatable.setModel(dtm);
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Groups") {
            if (radioallsearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "SELECT workstation.id AS 'ID',workstation.WorkStationName AS 'Name',CONCAT (costcenters.`id`,\" \",costcenters.`CostCentersName`) AS 'Cost Centers',workstation.Status AS 'Status' FROM `workstation`INNER JOIN costcenters on workstation.CostCentersRef=costcenters.id WHERE groupsref =  (SELECT `id` FROM `groupnodeaccess` WHERE `groupname` = '" + txtsearch.getText() + "' ) ";
                    Clas.classtable obj = new Clas.classtable();
                    //                DefaultTableModel dtm = obj.Query(sql);
                    //                datatable.setModel(dtm);
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Groups") {
            if (radioallsearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "SELECT workstation.id AS 'ID',workstation.WorkStationName AS 'Name',CONCAT (costcenters.`id`,\" \",costcenters.`CostCentersName`) AS 'Cost Centers',workstation.Status AS 'Status' FROM `workstation`INNER JOIN costcenters on workstation.CostCentersRef=costcenters.id WHERE groupsref =  (SELECT `id` FROM `groupnodeaccess` WHERE `groupname` = '" + txtsearch.getText() + "' ) ";
                    Clas.classtable obj = new Clas.classtable();
                    //                DefaultTableModel dtm = obj.Query(sql);
                    //                datatable.setModel(dtm);
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Groups") {
            if (radioactivesearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "SELECT workstation.id AS 'ID',workstation.WorkStationName AS 'Name',CONCAT (costcenters.`id`,\" \",costcenters.`CostCentersName`) AS 'Cost Centers',workstation.Status AS 'Status' FROM `workstation`INNER JOIN costcenters on workstation.CostCentersRef=costcenters.id WHERE groupsref =  (SELECT `id` FROM `groupnodeaccess` WHERE `groupname` = '" + txtsearch.getText() + "' ) ";
                    Clas.classtable obj = new Clas.classtable();
                    //                DefaultTableModel dtm = obj.Query(sql);
                    //                datatable.setModel(dtm);
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Groups") {
            if (radioactivesearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "SELECT workstation.id AS 'ID',workstation.WorkStationName AS 'Name',CONCAT (costcenters.`id`,\" \",costcenters.`CostCentersName`) AS 'Cost Centers',workstation.Status AS 'Status' FROM `workstation`INNER JOIN costcenters on workstation.CostCentersRef=costcenters.id WHERE groupsref =  (SELECT `id` FROM `groupnodeaccess` WHERE `groupname` = '" + txtsearch.getText() + "' ) ";
                    Clas.classtable obj = new Clas.classtable();
                    //                DefaultTableModel dtm = obj.Query(sql);
                    //                datatable.setModel(dtm);
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Groups") {
            if (radioactivesearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "SELECT workstation.id AS 'ID',workstation.WorkStationName AS 'Name',CONCAT (costcenters.`id`,\" \",costcenters.`CostCentersName`) AS 'Cost Centers',workstation.Status AS 'Status' FROM `workstation`INNER JOIN costcenters on workstation.CostCentersRef=costcenters.id WHERE groupsref =  (SELECT `id` FROM `groupnodeaccess` WHERE `groupname` = '" + txtsearch.getText() + "' ) ";
                    Clas.classtable obj = new Clas.classtable();
                    //                DefaultTableModel dtm = obj.Query(sql);
                    //                datatable.setModel(dtm);
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Groups") {
            if (radioInactivesearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "SELECT workstation.id AS 'ID',workstation.WorkStationName AS 'Name',CONCAT (costcenters.`id`,\" \",costcenters.`CostCentersName`) AS 'Cost Centers',workstation.Status AS 'Status' FROM `workstation`INNER JOIN costcenters on workstation.CostCentersRef=costcenters.id WHERE groupsref =  (SELECT `id` FROM `groupnodeaccess` WHERE `groupname` = '" + txtsearch.getText() + "' ) ";
                    Clas.classtable obj = new Clas.classtable();
                    //                DefaultTableModel dtm = obj.Query(sql);
                    //                datatable.setModel(dtm);
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Groups") {
            if (radioInactivesearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "SELECT workstation.id AS 'ID',workstation.WorkStationName AS 'Name',CONCAT (costcenters.`id`,\" \",costcenters.`CostCentersName`) AS 'Cost Centers',workstation.Status AS 'Status' FROM `workstation`INNER JOIN costcenters on workstation.CostCentersRef=costcenters.id WHERE groupsref =  (SELECT `id` FROM `groupnodeaccess` WHERE `groupname` = '" + txtsearch.getText() + "' ) ";
                    Clas.classtable obj = new Clas.classtable();
                    //                DefaultTableModel dtm = obj.Query(sql);
                    //                datatable.setModel(dtm);
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "Groups") {
            if (radioInactivesearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "SELECT workstation.id AS 'ID',workstation.WorkStationName AS 'Name',CONCAT (costcenters.`id`,\" \",costcenters.`CostCentersName`) AS 'Cost Centers',workstation.Status AS 'Status' FROM `workstation`INNER JOIN costcenters on workstation.CostCentersRef=costcenters.id WHERE groupsref =  (SELECT `id` FROM `groupnodeaccess` WHERE `groupname` = '" + txtsearch.getText() + "' ) ";
                    Clas.classtable obj = new Clas.classtable();
                    //                DefaultTableModel dtm = obj.Query(sql);
                    //                datatable.setModel(dtm);
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "User Name") {
            if (radioallsearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `username`  LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "User Name") {
            if (radioallsearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `positionref` ='1' AND `username`  LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "User Name") {
            if (radioallsearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `positionref` ='0' AND `username`  LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "User Name") {
            if (radioactivesearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='1' AND`username`  LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "User Name") {
            if (radioactivesearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='1' AND `ISuser`='1' AND `username`  LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "User Name") {
            if (radioactivesearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='1' AND `ISuser`='0' AND `username`  LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "User Name") {
            if (radioInactivesearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='0'  AND `username`  LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "User Name") {
            if (radioInactivesearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='0' AND `ISuser`='1' AND `username`  LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "User Name") {
            if (radioInactivesearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='0' AND `ISuser`='0' AND `username`  LIKE '%" + txtsearch.getText() + "%'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "End Time") {
            if (radioallsearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `enddate` = '" + dateserach.getDate() + "' ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "End Time") {
            if (radioallsearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `positionref` ='1' AND `enddate` = '" + dateserach.getDate() + "'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "End Time") {
            if (radioallsearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `positionref` ='0' AND `enddate` = '" + dateserach.getDate() + "'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "End Time") {
            if (radioactivesearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='1' AND`enddate` = '" + dateserach.getDate() + "'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "End Time") {
            if (radioactivesearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='1' AND `ISuser`='1' AND `enddate` = '" + dateserach.getDate() + "'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "End Time") {
            if (radioactivesearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='1' AND `ISuser`='0' AND `enddate` = '" + dateserach.getDate() + "'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "End Time") {
            if (radioInactivesearch.isSelected()) {
                if (radioallwe.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='0'  AND `enddate` = '" + dateserach.getDate() + "'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "End Time") {
            if (radioInactivesearch.isSelected()) {
                if (radioemp.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='0' AND `ISuser`='1' AND `enddate` = '" + dateserach.getDate() + "'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }

        if ((String) comboSearch.getSelectedItem() == "End Time") {
            if (radioInactivesearch.isSelected()) {
                if (radiowork.isSelected()) {
                    String sql = "select u.id,u.code,u.fname,u.lname,p.positintitle,u.ISuser,g.groupname,u.username,u.password,u.active,u.endtime,u.enddate ,u.pic FROM users u LEFT JOIN groupnodeaccess g on u.groupsref=g.ID LEFT JOIN position p on u.positionref=p.ID WHERE `active`='0' AND `ISuser`='0' AND `enddate` = '" + dateserach.getDate() + "'  ";
                    Clas.classtable obj = new Clas.classtable();
                    DefaultTableModel dtm = obj.Query(sql);
                    datatable.setModel(dtm);
                    NonQuery();
                }
            }
        }


    }//GEN-LAST:event_btnsearch2ActionPerformed

    private void comboSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSearchActionPerformed
        // TODO add your handling code here:

        if ((String) comboSearch.getSelectedItem() == "Code") {
            lblsearch.setVisible(true);
            txtsearch.setVisible(true);
            dateserach.setVisible(false);

        }
        if ((String) comboSearch.getSelectedItem() == "First Name") {
            lblsearch.setVisible(true);
            txtsearch.setVisible(true);
            dateserach.setVisible(false);

        }
        if ((String) comboSearch.getSelectedItem() == "Last Name") {
            lblsearch.setVisible(true);
            txtsearch.setVisible(true);
            dateserach.setVisible(false);

        }
        if ((String) comboSearch.getSelectedItem() == "Position") {
            lblsearch.setVisible(true);
            txtsearch.setVisible(true);
            dateserach.setVisible(false);

        }
        if ((String) comboSearch.getSelectedItem() == "Groups") {
            lblsearch.setVisible(true);
            txtsearch.setVisible(true);
            dateserach.setVisible(false);

        }
        if ((String) comboSearch.getSelectedItem() == "User Name") {
            lblsearch.setVisible(true);
            txtsearch.setVisible(true);
            dateserach.setVisible(false);

        }

        if ((String) comboSearch.getSelectedItem() == "End Time") {
            lblsearch.setVisible(false);
            txtsearch.setVisible(false);
            dateserach.setVisible(true);

        }
    }//GEN-LAST:event_comboSearchActionPerformed

    private void radioallsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioallsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioallsearchActionPerformed

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBrows1;
    private javax.swing.JTable Tablegroups;
    private javax.swing.JTable Tableusersacces;
    private javax.swing.JButton btndelet;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnfirst;
    private javax.swing.ButtonGroup btngroupjob;
    private javax.swing.JButton btnlast;
    private javax.swing.JButton btnnew;
    private javax.swing.JButton btnnext;
    private javax.swing.JButton btnprevious;
    private javax.swing.JButton btnregister;
    private javax.swing.JButton btnsearch2;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.ButtonGroup buttonGroupsearchew;
    private com.jidesoft.swing.CheckBoxTree checkBoxTree;
    private javax.swing.JCheckBox checktime;
    private javax.swing.JComboBox<String> comboPosition;
    private javax.swing.JComboBox<String> comboSearch;
    private javax.swing.JButton comboclose;
    private javax.swing.JComboBox<String> combogroups;
    private javax.swing.JTable datatable;
    private com.toedter.calendar.JDateChooser dateserach;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPaneldsen;
    private javax.swing.JPanel jPanelmain;
    private javax.swing.JPanel jPanelpaging;
    private javax.swing.JPanel jPanelpic;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblStart;
    private javax.swing.JLabel lbl_error;
    private javax.swing.JLabel lblall;
    private javax.swing.JLabel lblchecked;
    private javax.swing.JLabel lblcode;
    private javax.swing.JLabel lblhash;
    private javax.swing.JLabel lblidusersacces;
    private javax.swing.JLabel lblpag1;
    private javax.swing.JLabel lblpage;
    private javax.swing.JLabel lblpath;
    private javax.swing.JLabel lblpic;
    private javax.swing.JLabel lblsearch;
    private javax.swing.JMenuItem menuclose;
    private javax.swing.JMenuItem menudelete;
    private javax.swing.JMenuItem menuedit;
    private javax.swing.JMenuItem menunew2;
    private javax.swing.JMenuItem menusave;
    private javax.swing.JRadioButton radioEmployee;
    private javax.swing.JRadioButton radioInactivesearch;
    private javax.swing.JRadioButton radioactive;
    private javax.swing.JRadioButton radioactivesearch;
    private javax.swing.JRadioButton radioallsearch;
    private javax.swing.JRadioButton radioallwe;
    private javax.swing.JRadioButton radiodeactive;
    private javax.swing.JRadioButton radioemp;
    private javax.swing.JRadioButton radiowork;
    private javax.swing.JRadioButton radioworker;
    private javax.swing.JFormattedTextField txtCode;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables

    int defnodacces=0;
    int def = 0;
    String _active = "0";
    String _checktime = "0";
    String _job = "0";
    String _changejob = "0";
}

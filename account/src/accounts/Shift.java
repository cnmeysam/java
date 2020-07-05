/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;


import java.util.Date;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import java.sql.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
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
/**
 *
 * @author Admin
 */
public class Shift extends javax.swing.JInternalFrame {

    
    private static Shift jifShift;
        public static Shift Shift(){
        if(jifShift == null){
        jifShift = new Shift();
        }
        return jifShift;
        }
        
        
        //شمارنده تعداد دیتابیس
    public int NonQuery()
    {
       
    try
    {
        
//        //راست چین کردن سلولهای جی تیبل
//        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
//        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
//         //راست چین کردن سلولهای جی تیبل
//         
//     //راست چین کردن هدر جی تیبل  
//    ((DefaultTableCellRenderer)datatable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.RIGHT);
//    //راست چین کردن هدر جی تیبل  
//            
    
	
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
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url);
            Statement st =con.createStatement();
            
            
          ResultSet resultSet = st.executeQuery("select count(*) from shift");

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
            datatable.getColumnModel().getColumn(01).setHeaderValue("User");
            
            datatable.getColumnModel().getColumn(02).setHeaderValue("Shift Time");
            datatable.getColumnModel().getColumn(03).setHeaderValue("Shift Type");
            datatable.getColumnModel().getColumn(04).setHeaderValue("Start Date");
            datatable.getColumnModel().getColumn(05).setHeaderValue("End Date");
            
            
            
 

 }//کد دی هش
		catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Shift.class.getName()).log(Level.SEVERE, null, ex);
        }
		}
//کد دی هش
      
    }catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
       return 0;
        
    
}
    //شمارنده تعداد دیتابیس
    /**
     * Creates new form Shift
     */
    public Shift() {
//        //تغییر آیکن برنامه
//        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Icons/User Shift.png"));
//        this.setFrameIcon(icon);
//        //تغییر آیکن برنامه
        initComponents();
        lblcombo.setVisible(false);
        compersenell();
        comboshift();
        
        StDate.setVisible(false);
        lblstart.setVisible(false);
        EnDate.setVisible(false);
        lblend.setVisible(false);
    }
    
    //صفحه بندی
    
    private void Refresh_Table() {
        //limit 1
        //String test = "SELECT COUNT(*) FROM users ";
        //String sql = "select * from users limit 1",5"
        //عدد اول شروع ایندکس عدد دوم سطرهایی که میخوام
        String sql = "SELECT shift.id AS 'ID', CONCAT ( users.`code` ,\" \", users.`fname` ,\" \", users.`lname`) AS 'users',shift.`shifttime` AS 'shifttime', CONCAT ( shifttype.`id` ,\" \", shifttype.`shifttypes` ,\" \", shifttype.`coefficient`) AS 'shift type', shift.`startdate` AS 'start date', shift.`enddate` AS 'end date' FROM `shift` shift INNER JOIN `users` users ON users.`id` = shift.`userref` INNER JOIN `shifttype` shifttype ON shift.`shifttyperef` = shifttype.`id` limit 0,10";
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
            if (Clas.classpaging.query_to_db("SELECT COUNT(*) AS rowcount FROM shift")) {
                try {
                    ResultSet res_temp = Clas.classpaging.getResultSet();
                     if (res_temp.next()){
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
        
         String sql = "SELECT shift.id AS 'ID', CONCAT ( users.`code` ,\" \", users.`fname` ,\" \", users.`lname`) AS 'users',shift.`shifttime` AS 'shifttime', CONCAT ( shifttype.`id` ,\" \", shifttype.`shifttypes` ,\" \", shifttype.`coefficient`) AS 'shift type', shift.`startdate` AS 'start date', shift.`enddate` AS 'end date' FROM `shift` shift INNER JOIN `users` users ON users.`id` = shift.`userref` INNER JOIN `shifttype` shifttype ON shift.`shifttyperef` = shifttype.`id`  ORDER BY ID DESC limit 10  ";
        Clas.classtable obj = new Clas.classtable();
        DefaultTableModel dtm = obj.Query(sql);
        datatable.setModel(dtm);
        lblpage.setText(String.valueOf(0));
        
        int index = Integer.parseInt(lblall.getText());
        index = index/10;
        lblpage.setText(String.valueOf(index));
        lblStart.setText(String.valueOf(index*10));
        NonQuery();
    }
    //صفحه بندی
    //خالی کردن
    private void empty() {
        
        
        combopersenell.setSelectedIndex(0);
        comboshift.setSelectedIndex(0);
        txttime.setText("");
        StartDate.setDate(null);
        EndDate.setDate(null);
        
        //combomoneytype.setSelectedItem(null);
        last();
        paging_Table();
        NonQuery();
        compersenell();
        comboshift();
        combopersenell.setRequestFocusEnabled(true );
        combopersenell.requestFocus();
    }
    //خالی کردن

//combo comboProduct
    Connection con;
    Statement st;
    ResultSet rs; 
    private void compersenell() {
        combopersenell.removeAllItems();
            
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
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url);
            Statement st =con.createStatement();
            st = con.createStatement();
            String s = "SELECT CONCAT (code,\" \", fname,\" \",lname )FROM `users` WHERE   active='1'";
            rs = st.executeQuery(s);
          
while(rs.next())
                {
                    //1=id and 2=username and 3=password
                    combopersenell.addItem(rs.getString(1));
                    
                }    
 }//کد دی هش
		catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Shift.class.getName()).log(Level.SEVERE, null, ex);
        }
		}
//کد دی هش
        
    }catch (Exception ex)
        {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
    }
//combo combopersenell
    
    //combo comboProduct
    Connection con1;
    Statement st1;
    ResultSet rs1; 
    private void comboshift() {
        comboshift.removeAllItems();
            
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
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con1 = DriverManager.getConnection(url);
            Statement st1 =con1.createStatement();
            st1 = con1.createStatement();
            
            String s = "SELECT CONCAT (id,\" \", shifttypes ,\" \",coefficient ) FROM `shifttype`";
            rs1 = st1.executeQuery(s);
          
while(rs1.next())
                {
                    //1=id and 2=username and 3=password
                    comboshift.addItem(rs1.getString(1));
                    
                }    
 }//کد دی هش
		catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Shift.class.getName()).log(Level.SEVERE, null, ex);
        }
		}
//کد دی هش
        
    }catch (Exception ex)
        {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
    }
//combo combopersenell

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txttime = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        comboshift = new javax.swing.JComboBox<>();
        combopersenell = new javax.swing.JComboBox<>();
        StartDate = new com.toedter.calendar.JDateChooser();
        EndDate = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        datatable = new javax.swing.JTable();
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
        jPanel15 = new javax.swing.JPanel();
        comboclose1 = new javax.swing.JButton();
        btndelet1 = new javax.swing.JButton();
        btnregister1 = new javax.swing.JButton();
        btnedit1 = new javax.swing.JButton();
        btnnew1 = new javax.swing.JButton();
        lbl_error = new javax.swing.JLabel();
        lblcombo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblstart = new javax.swing.JLabel();
        lblsearch = new javax.swing.JLabel();
        btnsearch = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        comboSearch = new javax.swing.JComboBox<>();
        StDate = new com.toedter.calendar.JDateChooser();
        EnDate = new com.toedter.calendar.JDateChooser();
        lblend = new javax.swing.JLabel();
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
        setTitle("Shifts");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icon_Form/shift_15px.png"))); // NOI18N
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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel4.setText("Personell:");

        txttime.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel5.setText("Shift Time:");

        jLabel6.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel6.setText("Start Time:");

        jLabel7.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel7.setText("End Time:");

        jLabel8.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel8.setText("Shift Type:");

        comboshift.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        combopersenell.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        StartDate.setDateFormatString("yyyy/MM/dd");
        StartDate.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        EndDate.setDateFormatString("yyyy/MM/dd");
        EndDate.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        jLabel1.setText("2020/01/01");

        jLabel9.setText("Example");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txttime, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(combopersenell, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(jLabel8)
                        .addGap(13, 13, 13)
                        .addComponent(comboshift, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(8, 8, 8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(combopersenell)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboshift, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(EndDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(StartDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txttime, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );

        datatable.setAutoCreateRowSorter(true);
        datatable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        datatable.setColumnSelectionAllowed(true);
        datatable.setGridColor(new java.awt.Color(51, 153, 255));
        datatable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datatableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(datatable);
        datatable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
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
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap())
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        comboclose1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icons/back_25px.png"))); // NOI18N
        comboclose1.setToolTipText("Back");
        comboclose1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        comboclose1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboclose1ActionPerformed(evt);
            }
        });

        btndelet1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icons/delete_25px.png"))); // NOI18N
        btndelet1.setToolTipText("Delete");
        btndelet1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btndelet1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelet1ActionPerformed(evt);
            }
        });

        btnregister1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icons/save_25px.png"))); // NOI18N
        btnregister1.setToolTipText("Save");
        btnregister1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnregister1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregister1ActionPerformed(evt);
            }
        });

        btnedit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icons/edit_20px.png"))); // NOI18N
        btnedit1.setToolTipText("Edit");
        btnedit1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnedit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnedit1ActionPerformed(evt);
            }
        });

        btnnew1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_Icons/new_25px.png"))); // NOI18N
        btnnew1.setToolTipText("New");
        btnnew1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnnew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnew1ActionPerformed(evt);
            }
        });

        lbl_error.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_error.setText("System Masage");

        lblcombo.setText("jLabel11");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboclose1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_error)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(lblcombo)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btndelet1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnregister1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnedit1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnnew1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comboclose1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(btndelet1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnregister1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnedit1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnew1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                        .addComponent(lblcombo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbl_error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblstart.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        lblstart.setText("Start Date");

        lblsearch.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        lblsearch.setText("Search");

        btnsearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/search_15.png"))); // NOI18N
        btnsearch.setToolTipText("Search");
        btnsearch.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        txtsearch.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        comboSearch.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        comboSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Personell", "Shift Type", "Start Date", "End Date", "Start End", "BETWEEN" }));
        comboSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSearchActionPerformed(evt);
            }
        });

        StDate.setDateFormatString("YYYY/MM/dd");
        StDate.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        EnDate.setDateFormatString("YYYY/MM/dd");
        EnDate.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        lblend.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        lblend.setText("End Date");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(comboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblstart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(StDate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblend)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblsearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnsearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EnDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblend)
                    .addComponent(StDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblstart, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSearch))
                .addGap(0, 19, Short.MAX_VALUE))
        );

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void notdelete() {
	
	//شروع گرفتن اعداد قبل از اسپیس از کومبو باکس
        String firstWord = (String)_lblIDS;
     if(firstWord.contains(" ")){
        firstWord= firstWord.substring(0, firstWord.indexOf(" ")); 
//       jLabel1.setText(firstWord);
//       jTextField1.setText(firstWord);
     }
     //پایان گرفتن اعداد قبل از اسپیس از کومبو باکس   
     
        String _ProLine = firstWord; 
	
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
        ok = new String(decarray,"UTF-8");
	String url; 
        url=ok;
//reader.close();
            
            //String url =line;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url);

//جلوگیری از تکرار
Statement stmt = con.createStatement();
int rowcount = -1;
ResultSet result = stmt.executeQuery( "select count(id) from users WHERE (id='"+_ProLine+"') and active='0' ");
//select count(ID) from users WHERE (code='9' OR username='9') and ID NOT IN (34)
result.next();
rowcount = result.getInt(1);
def=rowcount;
//stmt.close();
con.close();
if(rowcount>=1)
{
    JOptionPane.showMessageDialog(null, "users Is Deactivated");
    
    
    System.out.println("def= "+def);
    
}
else{
    def=0;
}
    }
        }catch (Exception ex) {
        System.out.println("Found some error : "+ex);
        }
        finally {
        // close all the connections.

        }
    }
    
    public void delete() {
        if(datatable.getSelectedRow()==-1)
        {
            lbl_error.setText("Pleas Select a Row!");
        }
         notdelete();
 if(def==0){
            System.out.println("def= "+def);
        int i = datatable.getSelectedRow();
        String  id = datatable.getValueAt(i, 0).toString();
        Clas.class_register obj = new Clas.class_register();
        String sql = "delete from shift where ID='%s'";
        sql = String.format(sql, id);
        obj.NonQuery(sql);
        if(Clas.class_register.check ==1){
             lbl_error.setForeground(Color.RED);
        lbl_error.setText(combopersenell.getSelectedItem()+ " (This Record Reference Is In Use In Another Form!)");
         }
         else{
        lbl_error.setForeground(Color.RED);
        lbl_error.setText(combopersenell.getSelectedItem()+ "  removed!");
         }
        
        
        last();
        paging_Table();
        NonQuery();
        empty();
        combopersenell.setRequestFocusEnabled(true );
        combopersenell.requestFocus();
        }
        def=0;
        System.out.println("def= "+def);
    }
    
    
    public void save() {
    if(txttime.getText().equals("")){
                JOptionPane.showMessageDialog(null,
                "Shift Time can't be empty",
                "Warning!",
                JOptionPane.WARNING_MESSAGE);
            }
    else if(combopersenell.getSelectedItem().equals("")){
                JOptionPane.showMessageDialog(null,
                "Personell can't be empty",
                "Warning!",
                JOptionPane.WARNING_MESSAGE);
            }
     else if(comboshift.getSelectedItem().equals("")){
                JOptionPane.showMessageDialog(null,
                "Shift Type can't be empty",
                "Warning!",
                JOptionPane.WARNING_MESSAGE);
            }
            else{
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
        String dt1 = formatter1.format(StartDate.getDate());
        String dt2 = formatter1.format(EndDate.getDate());   
        if(dt2.compareTo(dt1) >= 0){
//شروع گرفتن اعداد قبل از اسپیس از کومبو باکس
        String firstWord = (String)combopersenell.getSelectedItem();
     if(firstWord.contains(" ")){
        firstWord= firstWord.substring(0, firstWord.indexOf(" ")); 
//       jLabel1.setText(firstWord);
//       jTextField1.setText(firstWord);
     }
     //پایان گرفتن اعداد قبل از اسپیس از کومبو باکس   
     
        String _combopersenell = firstWord; 
        lblcombo.setText(_combopersenell);
        String _time = txttime.getText();
        
        
        String  _StartDate="";
        if (StartDate.getDate()!=null)
        {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");        
         _StartDate = formatter.format(StartDate.getDate());
        }
         
       String _EndDate= "";
        if (EndDate.getDate()!=null)
        {
//            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
              _EndDate = formatter1.format(EndDate.getDate());
        }
        
        //شروع گرفتن اعداد قبل از اسپیس از کومبو باکس
        String firstWord2 = (String)comboshift.getSelectedItem();
     if(firstWord2.contains(" ")){
        firstWord2= firstWord2.substring(0, firstWord2.indexOf(" ")); 
//       jLabel1.setText(firstWord);
//       jTextField1.setText(firstWord);
     }
     //پایان گرفتن اعداد قبل از اسپیس از کومبو باکس   
        String _shift = firstWord2;
                
        String sql = "insert into shift (userref ,shifttime,startdate,enddate,shifttyperef) VALUES((SELECT ID FROM `users` WHERE code='"+_combopersenell+"'),'"+_time+"','"+_StartDate+"','"+_EndDate+"',(SELECT ID FROM `shifttype` WHERE id='"+_shift+"'))";
               
        
        //sql = String.format(sql,_combopersenell,_time,_StartDate,_EndDate,_shift);
        Clas.class_register obj = new Clas.class_register();
        obj.NonQuery(sql);
        lbl_error.setForeground(Color.green);
        lbl_error.setText((String)combopersenell.getSelectedItem()+ "  Inserted!");
        
        last();
        paging_Table();
        NonQuery();
        empty();
        combopersenell.setRequestFocusEnabled(true );
        combopersenell.requestFocus();
        
}
        else{
            JOptionPane.showMessageDialog(null,
                "Start Date can't bigger than End Date",
                "Warning!",
                JOptionPane.WARNING_MESSAGE);
        }
    }
    }
    private void notreapitedit() {
	
	//شروع گرفتن اعداد قبل از اسپیس از کومبو باکس
        String firstWord = (String)_lblIDS;
     if(firstWord.contains(" ")){
        firstWord= firstWord.substring(0, firstWord.indexOf(" ")); 
//       jLabel1.setText(firstWord);
//       jTextField1.setText(firstWord);
     }
     //پایان گرفتن اعداد قبل از اسپیس از کومبو باکس   
     
         _lblIDS = firstWord; 
	
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
        ok = new String(decarray,"UTF-8");
	String url; 
        url=ok;
//reader.close();
            
            //String url =line;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url);

//جلوگیری از تکرار
Statement stmt = con.createStatement();
int rowcount = -1;
ResultSet result = stmt.executeQuery( "select count(id) from users WHERE (id='"+_lblIDS+"') and   active='1' ");
//select count(ID) from users WHERE (code='9' OR username='9') and ID NOT IN (34)
result.next();
rowcount = result.getInt(1);
 System.out.println("rowcount= "+rowcount);
//stmt.close();
con.close();
def=rowcount;
if(rowcount<=0)
{
    JOptionPane.showMessageDialog(null, "Places Is Deactivated");
    
    
    System.out.println("def= "+def);
    
}

    }
        }catch (Exception ex) {
        System.out.println("Found some error : "+ex);
        }
        finally {
        // close all the connections.

        }
    }
    public void edit() {
    if(datatable.getSelectedRow()==-1)
        {
            lbl_error.setText("Pleas Select a Row!");
        }
     else if(txttime.getText().equals("")){
                JOptionPane.showMessageDialog(null,
                "Shift Time can't be empty",
                "Warning!",
                JOptionPane.WARNING_MESSAGE);
            }
     else if(combopersenell.getSelectedItem().equals("")){
                JOptionPane.showMessageDialog(null,
                "Personell can't be empty",
                "Warning!",
                JOptionPane.WARNING_MESSAGE);
            }
     else if(comboshift.getSelectedItem().equals("")){
                JOptionPane.showMessageDialog(null,
                "Shift Type can't be empty",
                "Warning!",
                JOptionPane.WARNING_MESSAGE);
            }
            else{
     SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
        String dt1 = formatter1.format(StartDate.getDate());
        String dt2 = formatter1.format(EndDate.getDate());   
        if(dt2.compareTo(dt1) >= 0){
        notreapitedit();
        
        if(def>0){
            System.out.println("def= "+def);
    //شروع گرفتن اعداد قبل از اسپیس از کومبو باکس
        String firstWord = (String)combopersenell.getSelectedItem();
     if(firstWord.contains(" ")){
        firstWord= firstWord.substring(0, firstWord.indexOf(" ")); 
//       jLabel1.setText(firstWord);
//       jTextField1.setText(firstWord);
     }
     //پایان گرفتن اعداد قبل از اسپیس از کومبو باکس   
     
        int i = datatable.getSelectedRow();
        String  _id = datatable.getValueAt(i, 0).toString();
        String _combopersenell = firstWord; 
        
        String _txttime = txttime.getText();
        
        String  _StartDate = "";
        if (StartDate.getDate()!=null)
        {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");        
         _StartDate = formatter.format(StartDate.getDate());
        }
         
       String _EndDate= "";
        if (EndDate.getDate()!=null)
        {
//            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
              _EndDate = formatter1.format(EndDate.getDate());
        }
        //شروع گرفتن اعداد قبل از اسپیس از کومبو باکس
        String firstWord2 = (String)comboshift.getSelectedItem();
     if(firstWord2.contains(" ")){
        firstWord2= firstWord2.substring(0, firstWord2.indexOf(" ")); 
//       jLabel1.setText(firstWord);
//       jTextField1.setText(firstWord);
     }
        String _comboshift = firstWord2;
        
                
        
        
        String sql = "update shift SET  userref=(SELECT ID FROM `users` WHERE code='"+_combopersenell+"'),shifttime='"+_txttime+"',startdate='"+_StartDate+"',enddate='"+_EndDate+"',shifttyperef=(SELECT ID FROM `shifttype` WHERE id='"+_comboshift+"') where ID='"+_id+"'";
        
        
        Clas.class_register obj = new Clas.class_register();
        obj.NonQuery(sql);
        lbl_error.setForeground(Color.orange);
        lbl_error.setText(combopersenell.getSelectedItem() + "Edited!");
        last();
        paging_Table();
        empty();
        combopersenell.setRequestFocusEnabled(true );
        combopersenell.requestFocus();
        } 
        
        System.out.println("def= "+def);
        def=0;
        }
        else{
            JOptionPane.showMessageDialog(null,
                "Start Date can't bigger than End Date",
                "Warning!",
                JOptionPane.WARNING_MESSAGE);
        }
     }
    }
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
        }
        else{
            btnnext.setEnabled(true);
            int index = Integer.parseInt(lblStart.getText());
            index = index - 10;
            String sql = "SELECT shift.id AS 'ID', CONCAT ( users.`code` ,\" \", users.`fname` ,\" \", users.`lname`) AS 'users',shift.`shifttime` AS 'shifttime', CONCAT ( shifttype.`id` ,\" \", shifttype.`shifttypes` ,\" \", shifttype.`coefficient`) AS 'shift type', shift.`startdate` AS 'start date', shift.`enddate` AS 'end date' FROM `shift` shift INNER JOIN `users` users ON users.`id` = shift.`userref` INNER JOIN `shifttype` shifttype ON shift.`shifttyperef` = shifttype.`id`  limit " + index + ",10";
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

        if (Integer.parseInt(a) >= Integer.parseInt(b)/10) {
            btnnext.setEnabled(false);
            JOptionPane.showMessageDialog(null,
                "You Are in Last Page",
                "Warning!",
                JOptionPane.WARNING_MESSAGE);
            btnprevious.setEnabled(true);
        }
        else{
            btnprevious.setEnabled(true);
            //btnbefor.setEnabled(true);
            //lblrows.setVisible(true);
            int index = Integer.parseInt(lblStart.getText());
            index = index + 10;
            String sql = "SELECT shift.id AS 'ID', CONCAT ( users.`code` ,\" \", users.`fname` ,\" \", users.`lname`) AS 'users',shift.`shifttime` AS 'shifttime', CONCAT ( shifttype.`id` ,\" \", shifttype.`shifttypes` ,\" \", shifttype.`coefficient`) AS 'shift type', shift.`startdate` AS 'start date', shift.`enddate` AS 'end date' FROM `shift` shift INNER JOIN `users` users ON users.`id` = shift.`userref` INNER JOIN `shifttype` shifttype ON shift.`shifttyperef` = shifttype.`id`  limit " + index + ",10";
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

    private void datatableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datatableMouseClicked
        // TODO add your handling code here:
        int i = datatable.getSelectedRow();
        _lblIDS=(datatable.getValueAt(i, 1).toString());
       notreapitedit();
       if(def==1){
        combopersenell.setSelectedItem(datatable.getValueAt(i, 1).toString());
        txttime.setText(datatable.getValueAt(i, 2).toString());
        //datestart.setDate((java.util.Date) datatable.getValueAt(i, 3));
        //DefaultTableModel model =(DefaultTableModel)datatable.getModel();
        comboshift.setSelectedItem(datatable.getValueAt(i, 3).toString());
        Date date1;
        try {
            String da1 = datatable.getValueAt(i, 4).toString();
            if(!da1.equals("")){
                //System.out.println(da1 + " null2");
                date1 = new SimpleDateFormat("yyyy/MM/dd").parse((String)da1);
//            date = new SimpleDateFormat("yyyy/MM/dd").parse((String)model.getValueAt(i,10));

        StartDate.setDate(date1);
            }
        } catch (ParseException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date date2;
        try {
            String da2 = datatable.getValueAt(i, 5).toString();
            if(!da2.equals("")){
                //System.out.println(da2 + " null3");
                
                date2 = new SimpleDateFormat("yyyy/MM/dd").parse((String)da2);
//            date = new SimpleDateFormat("yyyy/MM/dd").parse((String)model.getValueAt(i,10));

                EndDate.setDate(date2);
            }
        } catch (ParseException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
       
        System.out.println("def= "+def);
        def=0;
        
        
        
    }//GEN-LAST:event_datatableMouseClicked

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        int safe = JOptionPane.showConfirmDialog(null, "Are You Sure?!!","Closing Form!",   JOptionPane.YES_NO_CANCEL_OPTION);

        if(safe == JOptionPane.YES_OPTION){
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);//yes

        } else if (safe == JOptionPane.CANCEL_OPTION) {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//cancel
        } else {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//no
}
    }//GEN-LAST:event_formInternalFrameClosing

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        last();
        NonQuery();
        compersenell();
        comboshift();
        combopersenell.setRequestFocusEnabled(true );
        combopersenell.requestFocus();
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        compersenell();
        comboshift();
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

    private void comboclose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboclose1ActionPerformed
        // TODO add your handling code here:

        this.dispose();
    }//GEN-LAST:event_comboclose1ActionPerformed

    private void btndelet1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelet1ActionPerformed
        // TODO add your handling code here:

        delete();
    }//GEN-LAST:event_btndelet1ActionPerformed

    private void btnregister1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregister1ActionPerformed
        // TODO add your handling code here:
        save();

    }//GEN-LAST:event_btnregister1ActionPerformed

    private void btnedit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnedit1ActionPerformed
        // TODO add your handling code here:
        edit();
        //        edit2();
    }//GEN-LAST:event_btnedit1ActionPerformed

    private void btnnew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnew1ActionPerformed
        // TODO add your handling code here:
        empty();
    }//GEN-LAST:event_btnnew1ActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
        

        if((String)comboSearch.getSelectedItem()=="Personell"){
            String sql = "SELECT shift.id AS 'ID', CONCAT ( users.`code` ,\" \", users.`fname` ,\" \", users.`lname`) AS 'users',shift.`shifttime` AS 'shifttime', CONCAT ( shifttype.`id` ,\" \", shifttype.`shifttypes` ,\" \", shifttype.`coefficient`) AS 'shift type', shift.`startdate` AS 'start date', shift.`enddate` AS 'end date' FROM `shift` shift INNER JOIN `users` users ON users.`id` = shift.`userref` INNER JOIN `shifttype` shifttype ON shift.`shifttyperef` = shifttype.`id` WHERE CONCAT ( users.`code` ,\" \", users.`fname` ,\" \", users.`lname`) LIKE '%"+txtsearch.getText()+"%'  ";
            Clas.classtable obj = new Clas.classtable();
            DefaultTableModel dtm = obj.Query(sql);
            datatable.setModel(dtm);
        }
        if((String)comboSearch.getSelectedItem()=="Shift Type"){
            String sql = "SELECT shift.id AS 'ID', CONCAT ( users.`code` ,\" \", users.`fname` ,\" \", users.`lname`) AS 'users',shift.`shifttime` AS 'shifttime', CONCAT ( shifttype.`id` ,\" \", shifttype.`shifttypes` ,\" \", shifttype.`coefficient`) AS 'shift type', shift.`startdate` AS 'start date', shift.`enddate` AS 'end date' FROM `shift` shift INNER JOIN `users` users ON users.`id` = shift.`userref` INNER JOIN `shifttype` shifttype ON shift.`shifttyperef` = shifttype.`id` WHERE CONCAT ( shifttype.`id` ,\" \", shifttype.`shifttypes` ,\" \", shifttype.`coefficient`) LIKE '%"+txtsearch.getText()+"%'  ";
            Clas.classtable obj = new Clas.classtable();
            DefaultTableModel dtm = obj.Query(sql);
            datatable.setModel(dtm);
        }

        if((String)comboSearch.getSelectedItem()=="Start Date"){

            String sql = "SELECT shift.id AS 'ID', CONCAT ( users.`code` ,\" \", users.`fname` ,\" \", users.`lname`) AS 'users',shift.`shifttime` AS 'shifttime', CONCAT ( shifttype.`id` ,\" \", shifttype.`shifttypes` ,\" \", shifttype.`coefficient`) AS 'shift type', shift.`startdate` AS 'start date', shift.`enddate` AS 'end date' FROM `shift` shift INNER JOIN `users` users ON users.`id` = shift.`userref` INNER JOIN `shifttype` shifttype ON shift.`shifttyperef` = shifttype.`id` WHERE startdate ='"+StDate.getDate()+"'  ";
            Clas.classtable obj = new Clas.classtable();
            DefaultTableModel dtm = obj.Query(sql);
            datatable.setModel(dtm);
        }
        if((String)comboSearch.getSelectedItem()=="End Date"){

            String sql = "SELECT shift.id AS 'ID', CONCAT ( users.`code` ,\" \", users.`fname` ,\" \", users.`lname`) AS 'users',shift.`shifttime` AS 'shifttime', CONCAT ( shifttype.`id` ,\" \", shifttype.`shifttypes` ,\" \", shifttype.`coefficient`) AS 'shift type', shift.`startdate` AS 'start date', shift.`enddate` AS 'end date' FROM `shift` shift INNER JOIN `users` users ON users.`id` = shift.`userref` INNER JOIN `shifttype` shifttype ON shift.`shifttyperef` = shifttype.`id` `enddate` ='"+EnDate.getDate()+"'  ";
            Clas.classtable obj = new Clas.classtable();
            DefaultTableModel dtm = obj.Query(sql);
            datatable.setModel(dtm);
        }
        if((String)comboSearch.getSelectedItem()=="Start End"){

            String sql = "SELECT shift.id AS 'ID', CONCAT ( users.`code` ,\" \", users.`fname` ,\" \", users.`lname`) AS 'users',shift.`shifttime` AS 'shifttime', CONCAT ( shifttype.`id` ,\" \", shifttype.`shifttypes` ,\" \", shifttype.`coefficient`) AS 'shift type', shift.`startdate` AS 'start date', shift.`enddate` AS 'end date' FROM `shift` shift INNER JOIN `users` users ON users.`id` = shift.`userref` INNER JOIN `shifttype` shifttype ON shift.`shifttyperef` = shifttype.`id` WHERE startdate ='"+StDate.getDate()+"' and `enddate` ='"+EnDate.getDate()+"'  ";
            Clas.classtable obj = new Clas.classtable();
            DefaultTableModel dtm = obj.Query(sql);
            datatable.setModel(dtm);
        }
        if((String)comboSearch.getSelectedItem()=="SBETWEEN"){

            String sql = "SELECT shift.id AS 'ID', CONCAT ( users.`code` ,\" \", users.`fname` ,\" \", users.`lname`) AS 'users',shift.`shifttime` AS 'shifttime', CONCAT ( shifttype.`id` ,\" \", shifttype.`shifttypes` ,\" \", shifttype.`coefficient`) AS 'shift type', shift.`startdate` AS 'start date', shift.`enddate` AS 'end date' FROM `shift` shift INNER JOIN `users` users ON users.`id` = shift.`userref` INNER JOIN `shifttype` shifttype ON shift.`shifttyperef` = shifttype.`id` WHERE startdate BETWEEN '"+StDate.getDate()+"' and '"+EnDate.getDate()+"'   ";
            Clas.classtable obj = new Clas.classtable();
            DefaultTableModel dtm = obj.Query(sql);
            datatable.setModel(dtm);
        }
        NonQuery();
    }//GEN-LAST:event_btnsearchActionPerformed

    private void comboSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSearchActionPerformed
        // TODO add your handling code here:
        if((String)comboSearch.getSelectedItem()=="Personell")
        {
            txtsearch.setVisible(true);
            lblsearch.setVisible(true);
            EnDate.setVisible(false);
            lblstart.setVisible(false);
            StDate.setVisible(false);
            lblend.setVisible(false);
        }
        if((String)comboSearch.getSelectedItem()=="Shift Type")
        {
            txtsearch.setVisible(true);
            lblsearch.setVisible(true);
            EnDate.setVisible(false);
            lblstart.setVisible(false);
            StDate.setVisible(false);
            lblend.setVisible(false);
        }
        

        if((String)comboSearch.getSelectedItem()=="Start Date")
        {
            txtsearch.setVisible(false);
            lblsearch.setVisible(false);
            EnDate.setVisible(true);
            lblstart.setVisible(true);
            StDate.setVisible(false);
            lblend.setVisible(false);
        }
        if((String)comboSearch.getSelectedItem()=="End Date")
        {
            txtsearch.setVisible(false);
            lblsearch.setVisible(false);
            StDate.setVisible(false);
            lblstart.setVisible(false);

            EnDate.setVisible(true);
            lblend.setVisible(true);
        }
        if((String)comboSearch.getSelectedItem()=="Start End")
        {
            txtsearch.setVisible(false);
            lblsearch.setVisible(false);
            EnDate.setVisible(true);
            lblstart.setVisible(true);
            StDate.setVisible(true);
            lblend.setVisible(true);
        }
        if((String)comboSearch.getSelectedItem()=="BETWEEN")
        {
            txtsearch.setVisible(false);
            lblsearch.setVisible(false);
            EnDate.setVisible(true);
            lblstart.setVisible(true);
            StDate.setVisible(true);   
            lblend.setVisible(true);
        }
    }//GEN-LAST:event_comboSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser EnDate;
    private com.toedter.calendar.JDateChooser EndDate;
    private com.toedter.calendar.JDateChooser StDate;
    private com.toedter.calendar.JDateChooser StartDate;
    private javax.swing.JButton btndelet1;
    private javax.swing.JButton btnedit1;
    private javax.swing.JButton btnfirst;
    private javax.swing.JButton btnlast;
    private javax.swing.JButton btnnew1;
    private javax.swing.JButton btnnext;
    private javax.swing.JButton btnprevious;
    private javax.swing.JButton btnregister1;
    private javax.swing.JButton btnsearch;
    private javax.swing.JComboBox<String> comboSearch;
    private javax.swing.JButton comboclose1;
    private javax.swing.JComboBox<String> combopersenell;
    private javax.swing.JComboBox<String> comboshift;
    private javax.swing.JTable datatable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JLabel lblStart;
    private javax.swing.JLabel lbl_error;
    private javax.swing.JLabel lblall;
    private javax.swing.JLabel lblcombo;
    private javax.swing.JLabel lblend;
    private javax.swing.JLabel lblpag1;
    private javax.swing.JLabel lblpage;
    private javax.swing.JLabel lblsearch;
    private javax.swing.JLabel lblstart;
    private javax.swing.JMenuItem menuclose;
    private javax.swing.JMenuItem menudelete;
    private javax.swing.JMenuItem menuedit;
    private javax.swing.JMenuItem menunew;
    private javax.swing.JMenuItem menusave;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txttime;
    // End of variables declaration//GEN-END:variables

int def = 0;
String _lblIDS="0";

}

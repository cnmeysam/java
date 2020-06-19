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
            datatable.getColumnModel().getColumn(03).setHeaderValue("Start Date");
            datatable.getColumnModel().getColumn(04).setHeaderValue("End Date");
            datatable.getColumnModel().getColumn(05).setHeaderValue("Shift Type");
            
            
 

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
        //تغییر آیکن برنامه
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Icons/User Shift.png"));
        this.setFrameIcon(icon);
        //تغییر آیکن برنامه
        initComponents();
        lblcombo.setVisible(false);
    }
    
    //صفحه بندی
    
    private void Refresh_Table() {
        //limit 1
        //String test = "SELECT COUNT(*) FROM users ";
        //String sql = "select * from users limit 1",5"
        //عدد اول شروع ایندکس عدد دوم سطرهایی که میخوام
        String sql = "SELECT shift.id, CONCAT ( users.`code` ,\" \", users.`fname` ,\" \", users.`lname`) AS users_name,shift.`shifttime` AS shift_shifttime, shift.`startdate` AS shift_startdate, shift.`enddate` AS shift_enddate, shifttype.shifttypes FROM `shift` shift INNER JOIN `users` users ON users.`id` = shift.`userref` INNER JOIN `shifttype` shifttype ON shift.`shifttyperef` = shifttype.`id` limit 0,10";
        Clas.classtable obj = new Clas.classtable();
        DefaultTableModel dtm = obj.Query(sql);
        datatable.setModel(dtm);
        lblpag.setText(String.valueOf(0));

        lblStart.setText("0");
        lblpag.setText("0");
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
        
         String sql = "SELECT shift.id, CONCAT ( users.`code` ,\" \", users.`fname` ,\" \", users.`lname`) AS users_name,shift.`shifttime` AS shift_shifttime, shift.`startdate` AS shift_startdate, shift.`enddate` AS shift_enddate, shifttype.shifttypes FROM `shift` shift INNER JOIN `users` users ON users.`id` = shift.`userref` INNER JOIN `shifttype` shifttype ON shift.`shifttyperef` = shifttype.`id`  ORDER BY ID DESC limit 10  ";
        Clas.classtable obj = new Clas.classtable();
        DefaultTableModel dtm = obj.Query(sql);
        datatable.setModel(dtm);
        lblpag.setText(String.valueOf(0));
        
        int index = Integer.parseInt(lblall.getText());
        index = index/10;
        lblpag.setText(String.valueOf(index));
        lblStart.setText(String.valueOf(index*10));
        NonQuery();
    }
    //صفحه بندی
    //خالی کردن
    private void empty() {
        
        
        combopersenell.setSelectedIndex(0);
        comboshift.setSelectedIndex(0);
        txttime.setText("");
        datestart.setDate(null);
        dateend.setDate(null);
        
        //combomoneytype.setSelectedItem(null);
        last();
        paging_Table();
        NonQuery();
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
            String s = "SELECT CONCAT (code,\" \", fname,\" \",lname )FROM `users`";
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
            String s = "SELECT shifttypes FROM `shifttype`";
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
        datestart = new com.toedter.calendar.JDateChooser();
        dateend = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        datatable = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtsearch = new javax.swing.JTextField();
        btnsearch1 = new javax.swing.JButton();
        combosearch = new javax.swing.JComboBox<>();
        jPanel13 = new javax.swing.JPanel();
        comboclose = new javax.swing.JButton();
        btndelet = new javax.swing.JButton();
        btnregister = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnnew = new javax.swing.JButton();
        lbl_error = new javax.swing.JLabel();
        lblcombo = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        btnfirst = new javax.swing.JButton();
        btnprevious = new javax.swing.JButton();
        btnnext = new javax.swing.JButton();
        btnlast = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        lblall = new javax.swing.JLabel();
        lblpag1 = new javax.swing.JLabel();
        lblpag = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblStart = new javax.swing.JLabel();
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

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Shifts");
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

        jLabel5.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel5.setText("Shift Time:");

        jLabel6.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel6.setText("Start Time:");

        jLabel7.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel7.setText("End Time:");

        jLabel8.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel8.setText("Shift Type:");

        datestart.setDateFormatString("YYYY/MM/dd");

        dateend.setDateFormatString("YYYY/MM/dd");

        jLabel1.setText("2020/01/01");

        jLabel9.setText("Example");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txttime, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datestart, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateend, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(combopersenell, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(comboshift, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(combopersenell))
                                .addGap(5, 5, 5)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txttime, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(datestart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(dateend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel3.setText("Title");

        jLabel10.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel10.setText("Search");

        btnsearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/search_15.png"))); // NOI18N
        btnsearch1.setToolTipText("Search");
        btnsearch1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnsearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearch1ActionPerformed(evt);
            }
        });

        combosearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Personell", "Shift Type" }));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combosearch, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsearch)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(combosearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        comboclose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/back_25px.png"))); // NOI18N
        comboclose.setToolTipText("Back");
        comboclose.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        comboclose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combocloseActionPerformed(evt);
            }
        });

        btndelet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/delete_25px.png"))); // NOI18N
        btndelet.setToolTipText("Delete");
        btndelet.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btndelet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletActionPerformed(evt);
            }
        });

        btnregister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/save_25px.png"))); // NOI18N
        btnregister.setToolTipText("Save");
        btnregister.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnregister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregisterActionPerformed(evt);
            }
        });

        btnedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/edit_20px.png"))); // NOI18N
        btnedit.setToolTipText("Edit");
        btnedit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });

        btnnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/new_25px.png"))); // NOI18N
        btnnew.setToolTipText("New");
        btnnew.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnewActionPerformed(evt);
            }
        });

        lbl_error.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_error.setText("System Masage");

        lblcombo.setText("jLabel1");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboclose, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblcombo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_error)
                .addGap(148, 148, 148)
                .addComponent(btndelet, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnregister, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnnew, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_error)
                            .addComponent(lblcombo))))
                .addContainerGap())
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

        lblpag.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblpag.setText("0");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblpag1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblpag)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStart)
                .addGap(107, 107, 107)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblall)
                .addGap(90, 90, 90)
                .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnlast, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnlast, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnnext, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(lblall, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblStart, javax.swing.GroupLayout.Alignment.TRAILING)))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnprevious, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnfirst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblpag1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblpag))))
                .addGap(1, 1, 1))
        );

        jMenu5.setText("File");

        menunew2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menunew2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/new_15px.png"))); // NOI18N
        menunew2.setText("New");
        menunew2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menunew2ActionPerformed(evt);
            }
        });
        jMenu5.add(menunew2);
        jMenu5.add(jSeparator7);

        menusave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menusave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/save_15px.png"))); // NOI18N
        menusave.setText("Save");
        menusave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menusaveActionPerformed(evt);
            }
        });
        jMenu5.add(menusave);
        jMenu5.add(jSeparator4);

        menuedit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        menuedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/edit_15px.png"))); // NOI18N
        menuedit.setText("Edit");
        menuedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menueditActionPerformed(evt);
            }
        });
        jMenu5.add(menuedit);
        jMenu5.add(jSeparator8);

        menudelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        menudelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/delete_15px.png"))); // NOI18N
        menudelete.setText("Delete");
        menudelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menudeleteActionPerformed(evt);
            }
        });
        jMenu5.add(menudelete);
        jMenu5.add(jSeparator9);

        menuclose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        menuclose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/close_15px.png"))); // NOI18N
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearch1ActionPerformed
        // TODO add your handling code here:
        if((String)combosearch.getSelectedItem()=="Personell")
        {
            String sql = "SELECT * FROM shifttype WHERE userref='"+txtsearch.getText()+"'  ";
            Clas.classtable obj = new Clas.classtable();
            DefaultTableModel dtm = obj.Query(sql);
            datatable.setModel(dtm);
        }

        if((String)combosearch.getSelectedItem()=="Shift Type")
        {
            String sql = "SELECT * FROM shifttype WHERE shifttyperef='"+txtsearch.getText()+"'  ";
            Clas.classtable obj = new Clas.classtable();
            DefaultTableModel dtm = obj.Query(sql);
            datatable.setModel(dtm);
        }
        NonQuery();
    }//GEN-LAST:event_btnsearch1ActionPerformed

    private void combocloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combocloseActionPerformed
        // TODO add your handling code here:

        this.dispose();
    }//GEN-LAST:event_combocloseActionPerformed

    public void delete() {
        if(datatable.getSelectedRow()==-1)
        {
            lbl_error.setText("Pleas Select a Row!");
        }
        int i = datatable.getSelectedRow();
        String  id = datatable.getValueAt(i, 0).toString();
        Clas.class_register obj = new Clas.class_register();
        String sql = "delete from shift where ID='%s'";
        sql = String.format(sql, id);
        obj.NonQuery(sql);
        
        lbl_error.setForeground(Color.RED);
        lbl_error.setText(combopersenell.getSelectedItem() + "  removed!");
        last();
        paging_Table();
        NonQuery();
        empty();
        combopersenell.setRequestFocusEnabled(true );
        combopersenell.requestFocus();
    }
    
    
    private void btndeletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btndeletActionPerformed

    public void save() {
    
       
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
        if (datestart.getDate()!=null)
        {
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");        
         _StartDate = formatter.format(datestart.getDate());
        }
         
       String _EndDate= "";
        if (dateend.getDate()!=null)
        {
            SimpleDateFormat formatter1 = new SimpleDateFormat("YYYY/MM/dd");
              _EndDate = formatter1.format(dateend.getDate());
        }
        
        String _shift = (String)comboshift.getSelectedItem();
                
        String sql = "insert into shift (userref ,shifttime,startdate,enddate,shifttyperef) VALUES((SELECT ID FROM `users` WHERE code='"+_combopersenell+"'),'"+_time+"','"+_StartDate+"','"+_EndDate+"',(SELECT ID FROM `shifttype` WHERE shifttypes='"+_shift+"'))";
               
        
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
    
    private void btnregisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregisterActionPerformed
        // TODO add your handling code here:
        save();
    }//GEN-LAST:event_btnregisterActionPerformed

    public void edit() {

        if(datatable.getSelectedRow()==-1)
        {
            lbl_error.setText("Pleas Select a Row!");
        }
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
        if (datestart.getDate()!=null)
        {
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");        
         _StartDate = formatter.format(datestart.getDate());
        }
         
       String _EndDate= "";
        if (dateend.getDate()!=null)
        {
            SimpleDateFormat formatter1 = new SimpleDateFormat("YYYY/MM/dd");
              _EndDate = formatter1.format(dateend.getDate());
        }
        
        String _comboshift = (String)comboshift.getSelectedItem();
        
                
        
        
        String sql = "update shift SET  userref=(SELECT ID FROM `users` WHERE code='"+_combopersenell+"'),shifttime='"+_txttime+"',startdate='"+_StartDate+"',enddate='"+_EndDate+"',shifttyperef=(SELECT ID FROM `shifttype` WHERE shifttypes='"+_comboshift+"') where ID='"+_id+"'";
        
        
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
    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        edit();
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
        int n = Integer.parseInt(lblpag.getText());
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
            String sql = "SELECT shift.id, CONCAT ( users.`code` ,\" \", users.`fname` ,\" \", users.`lname`) AS users_name,shift.`shifttime` AS shift_shifttime, shift.`startdate` AS shift_startdate, shift.`enddate` AS shift_enddate, shifttype.shifttypes FROM `shift` shift INNER JOIN `users` users ON users.`id` = shift.`userref` INNER JOIN `shifttype` shifttype ON shift.`shifttyperef` = shifttype.`id`  limit " + index + ",10";
            Clas.classtable obj = new Clas.classtable();
            DefaultTableModel dtm = obj.Query(sql);
            if (dtm.getRowCount() > 0) {
                lblStart.setText(String.valueOf(index));
                lblpag.setText(String.valueOf(index / 10));
                datatable.setModel(dtm);

                paging_Table();
                NonQuery();

            }

        }
    }//GEN-LAST:event_btnpreviousActionPerformed

    private void btnnextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnextActionPerformed
        // TODO add your handling code here:
        String a = lblpag.getText();
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
            String sql = "SELECT shift.id, CONCAT ( users.`code` ,\" \", users.`fname` ,\" \", users.`lname`) AS users_name,shift.`shifttime` AS shift_shifttime, shift.`startdate` AS shift_startdate, shift.`enddate` AS shift_enddate, shifttype.shifttypes FROM `shift` shift INNER JOIN `users` users ON users.`id` = shift.`userref` INNER JOIN `shifttype` shifttype ON shift.`shifttyperef` = shifttype.`id`  limit " + index + ",10";
            Clas.classtable obj = new Clas.classtable();
            DefaultTableModel dtm = obj.Query(sql);
            if (dtm.getRowCount() > 0) {
                lblStart.setText(String.valueOf(index));
                lblpag.setText(String.valueOf(index / 10));
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

    private void datatableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datatableMouseClicked
        // TODO add your handling code here:
        int i = datatable.getSelectedRow();
        combopersenell.setSelectedItem(datatable.getValueAt(i, 1).toString());
        txttime.setText(datatable.getValueAt(i, 2).toString());
        //datestart.setDate((java.util.Date) datatable.getValueAt(i, 3));
        //DefaultTableModel model =(DefaultTableModel)datatable.getModel();
        Date date1;
        try {
            String da1 = datatable.getValueAt(i, 3).toString();
            if(!da1.equals("")){
                //System.out.println(da1 + " null2");
                date1 = new SimpleDateFormat("YYYY/MM/dd").parse((String)da1);
//            date = new SimpleDateFormat("YYYY/MM/dd").parse((String)model.getValueAt(i,10));

        datestart.setDate(date1);
            }
        } catch (ParseException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
        Date date2;
        try {
            String da2 = datatable.getValueAt(i, 4).toString();
            if(!da2.equals("")){
                //System.out.println(da2 + " null3");
                
                date2 = new SimpleDateFormat("YYYY/MM/dd").parse((String)da2);
//            date = new SimpleDateFormat("YYYY/MM/dd").parse((String)model.getValueAt(i,10));

                dateend.setDate(date2);
            }
        } catch (ParseException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
        comboshift.setSelectedItem(datatable.getValueAt(i, 5).toString());
        
        
        
        
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
        last();
        NonQuery();
    }//GEN-LAST:event_formInternalFrameActivated


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndelet;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnfirst;
    private javax.swing.JButton btnlast;
    private javax.swing.JButton btnnew;
    private javax.swing.JButton btnnext;
    private javax.swing.JButton btnprevious;
    private javax.swing.JButton btnregister;
    private javax.swing.JButton btnsearch1;
    private javax.swing.JButton comboclose;
    private javax.swing.JComboBox<String> combopersenell;
    private javax.swing.JComboBox<String> combosearch;
    private javax.swing.JComboBox<String> comboshift;
    private javax.swing.JTable datatable;
    private com.toedter.calendar.JDateChooser dateend;
    private com.toedter.calendar.JDateChooser datestart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JLabel lblStart;
    private javax.swing.JLabel lbl_error;
    private javax.swing.JLabel lblall;
    private javax.swing.JLabel lblcombo;
    private javax.swing.JLabel lblpag;
    private javax.swing.JLabel lblpag1;
    private javax.swing.JMenuItem menuclose;
    private javax.swing.JMenuItem menudelete;
    private javax.swing.JMenuItem menuedit;
    private javax.swing.JMenuItem menunew2;
    private javax.swing.JMenuItem menusave;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txttime;
    // End of variables declaration//GEN-END:variables
}

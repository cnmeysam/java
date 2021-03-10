/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLS;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Admin
 */
public class WorkWithXML extends javax.swing.JFrame {

    /**
     * Creates new form WorkWithXML
     */
    public WorkWithXML() {
        initComponents();
        DefaultXMLTableModel();
    }
// لیست آی دی ها

    public void ListID() {

        try {
            String xmlFilePath = "file.xml";
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFilePath));
            //شمارنده تعداد نود
            NodeList list = document.getElementsByTagName("Customer");
            System.out.println("Total of elements : " + list.getLength());
            int count = list.getLength() - 1;
            System.out.println("count= " + count);
            //شمارنده تعداد نود
            //ساختن آرایه برای لیست آی دی ها
            List<Integer> arr = new ArrayList<Integer>();
            //ساختن آرایه برای لیست آی دی ها
            String value = "";
            //outer برای خارج شدن از حلقه میباشد
            for (int z = 0; z <= count; z++) {

                //بدست آوردن آی دی کاستومر
                NamedNodeMap namedNodeMap = list.item(z).getAttributes();
                value = namedNodeMap.getNamedItem("ID").getNodeValue();
                //بدست آوردن آی دی کاستومر

                //لیست کردن آی دی ها
                System.out.println("," + value);
                arr.add(Integer.parseInt(value));
                System.out.print(arr);
                System.out.println();
                //لیست کردن آی دی ها

            }
            //مرتب سازی آی دی از کوچک به بزرگ
            Collections.sort(arr);
            System.out.println("After Sorting: " + arr);
            //مرتب سازی آی دی از کوچک به بزرگ
            //پیدا کردن بزرگترین عدد
            lastid = Collections.max(arr);
            System.out.println("Max Number: " + lastid);
            System.out.println("Maximum element : " + Collections.max(arr));
            //پیدا کردن بزرگترین عدد

        } catch (SAXException ex) {
            Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
// لیست آی دی ها
    //آی دی غیر تکراری

    public void CheckID() {

        try {
            String xmlFilePath = "file.xml";
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFilePath));
            //شمارنده تعداد نود
            NodeList list = document.getElementsByTagName("Customer");
            System.out.println("Total of elements : " + list.getLength());
            int count = list.getLength() - 1;
            System.out.println("count= " + count);
            //شمارنده تعداد نود
            //outer برای خارج شدن از حلقه میباشد
            outer:
            //outer برای خارج شدن از حلقه میباشد
            for (int z = 0; z <= count; z++) {

                //بدست آوردن آی دی کاستومر
                NamedNodeMap namedNodeMap = list.item(z).getAttributes();
                String value = namedNodeMap.getNamedItem("ID").getNodeValue();
//                System.out.println("value= " + value);
                System.out.println(value);
                //بدست آوردن آی دی کاستومر

                String str[] = value.split("\\n");
                List<String> al = new ArrayList<String>();
                al = Arrays.asList(str);
                for (String s : al) {
                    System.out.println("S=" + s);
                }

                Node firstEmployee = document.getElementsByTagName("Customer").item(z);
                NodeList firstEmpNodeList = firstEmployee.getChildNodes();
                System.out.println("firstEmpNodeList.getLength= " + firstEmpNodeList.getLength());

                for (int i = 0; i < firstEmpNodeList.getLength(); i++) {

//                System.out.println(element.getNodeName());
                    //اگر آی دی کاستومر به تکست برابر بود آپدیت شود
                    if (value.equals(txtID.getText())) {
                        checkid = 1;

//                        JOptionPane.showMessageDialog(null,
//                                "ID USe Before",
//                                "Warning!",
//                                JOptionPane.WARNING_MESSAGE);
                        //outer برای خارج شدن از حلقه میباشد
//                        break outer;
                        //outer برای خارج شدن از حلقه میباشد
                    }

                }

            }
            System.out.println("ID is checked !!!!");
        } catch (SAXException ex) {
            Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//آی دی غیر تکراری
    //load xml in jtable
    public File xml = null;
    public Document dom = null;
    public DefaultTableModel model;

    public Document parseFile(File file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            dom = (Document) builder.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dom;
    }

    public Object getArticleInfo(String tagName, Element elem) {
        NodeList list = elem.getElementsByTagName(tagName);
        for (int i = 0; i < list.getLength(); ++i) {
            Node node = (Node) list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if ((Node) node.getFirstChild() != null) {
                    Node child = (Node) node.getFirstChild();
//                System.out.println(child.getTextContent().trim());
                    return child.getTextContent().trim();
                }
            }

            return null;
        }

        return null;
    }

    public void insertTableRows(DefaultTableModel tableModel, Document doc) {

        try {
            Element root = doc.getDocumentElement();
            NodeList list = root.getElementsByTagName("Customer");
            //بدست آوردن آی دی کاستومر
            String xmlFilePath = "file.xml";
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFilePath));
            //شمارنده تعداد نود
//            NodeList list = document.getElementsByTagName("Customer");
            System.out.println("Total of elements : " + list.getLength());
            int count = list.getLength() - 1;
            System.out.println("count= " + count);
            //شمارنده تعداد نود

            //بدست آوردن آی دی کاستومر
            for (int i = 0; i < list.getLength(); ++i) {

                //بدست آوردن آی دی کاستومر
                NamedNodeMap namedNodeMap = list.item(i).getAttributes();
                String value = namedNodeMap.getNamedItem("ID").getNodeValue();
                System.out.println("value= " + value);
                //بدست آوردن آی دی کاستومر
                Element e = (Element) list.item(i);
//                System.out.println("Element= " + e);
                if (e.getNodeType() == Element.ELEMENT_NODE) {
                    Object[] row = {value, getArticleInfo("headline", e), getArticleInfo("author", e), getArticleInfo("email", e)};
                    tableModel.addRow(row);

                }

//            break;
            }

            tableModel.fireTableStructureChanged();
            tableModel.fireTableDataChanged();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DefaultXMLTableModel() {

        xml = new File(System.getProperty("user.dir") + File.separator + "file.xml");
        model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };
//String[] columnNames = {"ID", "headline", "author","email"};
//DeafultTableModel model = new DefaultTableModel(columnNames, 0);
        model.addColumn("ID");
        model.addColumn("headline");
        model.addColumn("author");
        model.addColumn("email");

        jtablexml.setModel(model);
        jtablexml.setGridColor(Color.blue);
        jtablexml.setShowGrid(true);

        if (xml.exists() && xml.length() != 0) {
            dom = parseFile(xml);
            insertTableRows(model, dom);

        } else {
            JOptionPane.showMessageDialog(null,
                    "Data file not found",
                    "ERROR!",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //load xml in jtable
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtheadline = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtauthor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        BtnDeleteRow = new javax.swing.JButton();
        BtnEditBlock = new javax.swing.JButton();
        BtnInsertBlock = new javax.swing.JButton();
        BtnGetElement = new javax.swing.JButton();
        lblgetxmlauthor = new javax.swing.JLabel();
        lblgetxmlheadline = new javax.swing.JLabel();
        lblgetxmlemail = new javax.swing.JLabel();
        lblgetxmlID = new javax.swing.JLabel();
        CheckBoxAoutoID = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtablexml = new javax.swing.JTable()
        {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }

            public Component prepareRenderer ( TableCellRenderer renderer, int row, int column ){
                Component component = super.prepareRenderer(renderer,row,column);
                if (getModel().getValueAt(row,column) == null){
                    component.setBackground(Color.ORANGE);
                }
                else if (getModel().getValueAt(row,column) != null){
                    Object value = getModel().getValueAt(row,column).toString();
                    if (isRowSelected(row))
                    {
                        component.setBackground(new Color(56,105,138));
                        component.setForeground(Color.WHITE);
                        if(value.equals("false")){
                            component.setForeground(Color.red);
                        }
                        else if(value.equals("null")){
                            component.setForeground(Color.red);
                        }
                    }
                    else if(value.equals("false")){
                        component.setBackground(Color.ORANGE);
                        component.setForeground(Color.BLACK);
                    }
                    else if(value.equals("null")){
                        component.setBackground(Color.ORANGE);
                        component.setForeground(Color.red);
                    }
                    else{
                        if (row%2 == 0){
                            component.setBackground(new Color(255, 255, 253));
                            component.setForeground(Color.BLACK);
                        }
                        else {
                            component.setBackground(new Color(208,205,205));
                            component.setForeground(Color.BLACK);
                        }
                        /*component.setBackground(Color.WHITE);
                        component.setForeground(Color.BLACK);*/
                    }
                }
                return component;
            }
        }

        ;

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel4.setText("ID");

        jLabel1.setText("headline");

        jLabel2.setText("author");

        jLabel3.setText("email");

        BtnDeleteRow.setText("Delete Block row");
        BtnDeleteRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDeleteRowActionPerformed(evt);
            }
        });

        BtnEditBlock.setText("Edit Block");
        BtnEditBlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditBlockActionPerformed(evt);
            }
        });

        BtnInsertBlock.setText("Insert Block");
        BtnInsertBlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInsertBlockActionPerformed(evt);
            }
        });

        BtnGetElement.setText("get element from xml");
        BtnGetElement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGetElementActionPerformed(evt);
            }
        });

        lblgetxmlauthor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblgetxmlheadline.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblgetxmlemail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblgetxmlID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        CheckBoxAoutoID.setText("Aouto ID");
        CheckBoxAoutoID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxAoutoIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtheadline, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtauthor)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CheckBoxAoutoID))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnGetElement)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnDeleteRow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnEditBlock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnInsertBlock)
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblgetxmlheadline, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblgetxmlauthor, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblgetxmlemail, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblgetxmlID, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtheadline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtauthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CheckBoxAoutoID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnGetElement)
                    .addComponent(BtnDeleteRow)
                    .addComponent(BtnEditBlock)
                    .addComponent(BtnInsertBlock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblgetxmlauthor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblgetxmlheadline, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblgetxmlemail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblgetxmlID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtablexml.setAutoCreateRowSorter(true);
        jtablexml.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtablexmlMouseClicked(evt);
            }
        });
        jtablexml.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtablexmlKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jtablexml);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(14, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

//حذف در xml
    private void delete() {

        if (CheckBoxAoutoID.isSelected()) {

            JOptionPane.showMessageDialog(null,
                    "Please Uncheck The Aouto ID",
                    "Warning!",
                    JOptionPane.WARNING_MESSAGE);
        } else if (txtID.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "ID Cannot be empty",
                    "Warning!",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                String xmlFile = "file.xml";
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document doc = documentBuilder.parse(xmlFile);
                NodeList nList = doc.getElementsByTagName("Customer");
                //ساختن آرایه برای لیست آی دی ها
                List<Integer> arr = new ArrayList<Integer>();
                //ساختن آرایه برای لیست آی دی ها
                String value = "";

                for (int i = 0; i < nList.getLength(); i++) {
                    Node node = nList.item(i);
                    if (node.getNodeType() == Element.ELEMENT_NODE) {
                        Element eElement = (Element) node;
                        System.out.println(eElement.getAttribute("ID"));
                        //لیست کردن آی دی ها
                        value = eElement.getAttribute("ID");
                        System.out.println("," + value);
                        arr.add(Integer.parseInt(value));
                        System.out.print(arr);
                        System.out.println();
                        //لیست کردن آی دی ها

                        if (eElement.getAttribute("ID").equals(txtID.getText())) {

                            node.getParentNode().removeChild(node);
                            flag = "1";
                        }
                    }

                }

                saveXMLContent(doc, xmlFile);
                if (flag.equals("0")) {
                    //مرتب سازی آی دی از کوچک به بزرگ
                    Collections.sort(arr);
                    System.out.println("After Sorting: " + arr);
                    //مرتب سازی آی دی از کوچک به بزرگ

                    String ex = String.valueOf(arr.contains(txtID.getText()));
                    if (ex.equals("false")) {
                        JOptionPane.showMessageDialog(null,
                                "ID Does not exist",
                                "Warning!",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
                flag = "0";
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void saveXMLContent(Document document, String xmlFile) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(xmlFile);
            transformer.transform(domSource, streamResult);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
//حذف در xml    
    private void BtnDeleteRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDeleteRowActionPerformed
        // TODO add your handling code here:
        xml = new File(System.getProperty("user.dir") + File.separator + "file.xml");
        if (xml.exists() && xml.length() != 0) {
            delete();
            DefaultXMLTableModel();
            empty();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Data file not found",
                    "Warning!",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BtnDeleteRowActionPerformed
//ویرایش xml

    public void modifyNodeval() {

        try {
            String xmlFilePath = "file.xml";
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFilePath));
            //شمارنده تعداد نود
            NodeList list = document.getElementsByTagName("Customer");
            System.out.println("Total of elements : " + list.getLength());
            int count = list.getLength() - 1;
            System.out.println("count= " + count);
            //شمارنده تعداد نود

            for (int z = 0; z <= count; z++) {

                //بدست آوردن آی دی کاستومر
                NodeList e = document.getElementsByTagName("Customer");
                NamedNodeMap namedNodeMap = e.item(z).getAttributes();
                String value = namedNodeMap.getNamedItem("ID").getNodeValue();
                System.out.println("value= " + value);
                //بدست آوردن آی دی کاستومر

                // Update the Salary Of the First Employee
                Node firstEmployee = document.getElementsByTagName("Customer").item(z);
                NodeList firstEmpNodeList = firstEmployee.getChildNodes();
                System.out.println("firstEmpNodeList.getLength= " + firstEmpNodeList.getLength());
                for (int i = 0; i < firstEmpNodeList.getLength(); i++) {
                    Node element = firstEmpNodeList.item(i);
//                System.out.println(element.getNodeName());
                    //اگر آی دی کاستومر به تکست برابر بود آپدیت شود
                    if (value.equals(txtID.getText())) {
                        flag = "1";

                        if ("headline".equals(element.getNodeName())) {
                            if (txtheadline.getText().equals("")) {
                                element.setTextContent("null");
                            } else {
                                element.setTextContent(txtheadline.getText());
                            }

                        }
                        //اگر نود آوثر پیدا کرد آوثر آپدیت شود
                        if ("author".equals(element.getNodeName())) {
                            if (txtauthor.getText().equals("")) {
                                element.setTextContent("null");
                            } else {
                                element.setTextContent(txtauthor.getText());
                            }
                        }
                        if (txtemail.getText().equals("")) {
                            element.setTextContent("null");
                        } else {
                            if ("email".equals(element.getNodeName())) {
                                element.setTextContent(txtemail.getText());
                            }
                        }

                    }
//                    else{
//                        flag="0";
//                       
//                    }

                }
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(new File(xmlFilePath));
                transformer.transform(source, result);
//                break;
            }
            if (flag.equals("0")) {
                JOptionPane.showMessageDialog(null,
                        "ID Does not existaaa",
                        "Warning!",
                        JOptionPane.WARNING_MESSAGE);
            }
            System.out.println("Changes to the XML completed !!!!");
        } catch (SAXException ex) {
            Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//ویرایش xml
    private void BtnEditBlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditBlockActionPerformed
        // TODO add your handling code here:
        xml = new File(System.getProperty("user.dir") + File.separator + "file.xml");
        if (xml.exists() && xml.length() != 0) {

            if (CheckBoxAoutoID.isSelected()) {

                JOptionPane.showMessageDialog(null,
                        "Please Uncheck The Aouto ID",
                        "Warning!",
                        JOptionPane.WARNING_MESSAGE);
            } else {

                if (txtID.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "ID Cannot be empty",
                            "Warning!",
                            JOptionPane.WARNING_MESSAGE);
                } else if (txtheadline.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "headline Cannot be empty",
                            "Warning!",
                            JOptionPane.WARNING_MESSAGE);
                } //                else if (txtauthor.getText().equals("")) {
                //                    JOptionPane.showMessageDialog(null,
                //                            "author Cannot be empty",
                //                            "Warning!",
                //                            JOptionPane.WARNING_MESSAGE);
                //                }
                else if (txtemail.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "email Cannot be empty",
                            "Warning!",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    System.out.println("flag= " + flag);

                    modifyNodeval();

                    if (flag.equals("1")) {
                        DefaultXMLTableModel();
                        empty();
                        flag = "0";
                    }

                }
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Data file not found",
                    "Warning!",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BtnEditBlockActionPerformed

    //insert xml
    public void Insert() {
        CheckID();
        System.out.println("checkid= " + checkid);
        if (checkid == 1) {
            JOptionPane.showMessageDialog(null,
                    "ID USe Before",
                    "Warning!",
                    JOptionPane.WARNING_MESSAGE);
            checkid = 0;
        } else {

            try {
                // TODO add your handling code here:
                File fXmlFile = new File("file.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fXmlFile);

//                doc.getDocumentElement().normalize();
                Element nList = doc.getDocumentElement();

                System.out.println("-----------------------");

                //root a new Customer
                Element newCustomer = doc.createElement("Customer");
                //setting ganre for a book
                Attr att = doc.createAttribute("ID");
                if (CheckBoxAoutoID.isSelected()) {
//                    txtID.setText((String.valueOf(lastid + 1)));
                    txtID.setEnabled(false);
                    ListID();
                    att.setValue(String.valueOf(lastid + 1));

                } else {
                    att.setValue(txtID.getText());
                }
                newCustomer.setAttributeNode(att);

                //Customer headline
                Element Customerheadline = doc.createElement("headline");
                if (txtheadline.getText().equals("")) {
                    Customerheadline.appendChild(doc.createTextNode("null"));
                } else {
                    Customerheadline.appendChild(doc.createTextNode(txtheadline.getText()));
                }

                newCustomer.appendChild(Customerheadline);

                //Customer author
                Element Customerauthor = doc.createElement("author");
                if (txtauthor.getText().equals("")) {
                    Customerauthor.appendChild(doc.createTextNode("null"));
                } else {
                    Customerauthor.appendChild(doc.createTextNode(txtauthor.getText()));
                }

                newCustomer.appendChild(Customerauthor);

                //Customer email
                Element Customeremail = doc.createElement("email");
                if (txtemail.getText().equals("")) {
                    Customeremail.appendChild(doc.createTextNode(("null")));
                } else {
                    Customeremail.appendChild(doc.createTextNode((txtemail.getText())));
                }

                newCustomer.appendChild(Customeremail);

                nList.appendChild(newCustomer);

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                //initialize StreamResult with File object to save to file
                StreamResult result = new StreamResult(new File("file.xml"));
                DOMSource source = new DOMSource(doc);
                transformer.transform(source, result);
                System.out.println("DONE");
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
//empty

    public void empty() {
        txtheadline.setText("");
        txtauthor.setText("");
        txtemail.setText("");
        txtID.setText("");
    }

    //insert xml
    private void BtnInsertBlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInsertBlockActionPerformed
        // TODO add your handling code here:
        xml = new File(System.getProperty("user.dir") + File.separator + "file.xml");
        if (xml.exists() && xml.length() != 0) {

            if (txtheadline.getText().equals("")) {
                JOptionPane.showMessageDialog(null,
                        "headline Cannot be empty",
                        "Warning!",
                        JOptionPane.WARNING_MESSAGE);
            } //            else if (txtauthor.getText().equals("")) {
            //                JOptionPane.showMessageDialog(null,
            //                        "author Cannot be empty",
            //                        "Warning!",
            //                        JOptionPane.WARNING_MESSAGE);
            //            }
            else if (txtemail.getText().equals("")) {
                JOptionPane.showMessageDialog(null,
                        "email Cannot be empty",
                        "Warning!",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                int a = Boolean.valueOf(CheckBoxAoutoID.isSelected()).compareTo(true);
                System.out.println("A= " + a);
                if (a != 0 && txtID.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "ID Cannot be empty",
                            "Warning!",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    Insert();
                    DefaultXMLTableModel();
                    if (flag.equals("1")) {
                        empty();
                        flag = "0";
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Data file not found",
                    "Warning!",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BtnInsertBlockActionPerformed

    private void BtnGetElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGetElementActionPerformed
        // TODO add your handling code here:
        xml = new File(System.getProperty("user.dir") + File.separator + "file.xml");
        if (xml.exists() && xml.length() != 0) {
            if (CheckBoxAoutoID.isSelected()) {

                JOptionPane.showMessageDialog(null,
                        "Please Uncheck The Aouto ID",
                        "Warning!",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                if (txtID.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "ID Cannot be empty",
                            "Warning!",
                            JOptionPane.WARNING_MESSAGE);
                } else {

                    try {
                        String xmlFilePath = "file.xml";
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(new File(xmlFilePath));
                        //شمارنده تعداد نود
                        NodeList list = document.getElementsByTagName("Customer");
                        System.out.println("Total of elements : " + list.getLength());
                        int count = list.getLength() - 1;
                        System.out.println("count= " + count);
                        //شمارنده تعداد نود

                        for (int z = 0; z <= count; z++) {

                            //بدست آوردن آی دی کاستومر
                            NodeList e = document.getElementsByTagName("Customer");
                            NamedNodeMap namedNodeMap = e.item(z).getAttributes();
                            String value = namedNodeMap.getNamedItem("ID").getNodeValue();
                            System.out.println("value= " + value);
                            //بدست آوردن آی دی کاستومر

                            // Update the Salary Of the First Employee
                            Node firstEmployee = document.getElementsByTagName("Customer").item(z);
                            NodeList firstEmpNodeList = firstEmployee.getChildNodes();
                            System.out.println("firstEmpNodeList.getLength= " + firstEmpNodeList.getLength());
                            for (int i = 0; i < firstEmpNodeList.getLength(); i++) {
                                Node element = firstEmpNodeList.item(i);
                                System.out.println(element.getNodeName());
                                //اگر آی دی کاستومر به تکست برابر بود آپدیت شود

                                if ("headline".equals(element.getNodeName())) {
                                    String test = ("headline= " + element.getTextContent());
                                    System.out.println(test);
                                }

                                if (value.equals(txtID.getText())) {

                                    if ("headline".equals(element.getNodeName())) {
                                        lblgetxmlheadline.setText("headline= " + element.getTextContent());
                                    }
                                    //اگر نود آوثر پیدا کرد آوثر نمایش داده شود
                                    if ("author".equals(element.getNodeName())) {
                                        lblgetxmlauthor.setText("author= " + element.getTextContent());
                                    }
                                    if ("email".equals(element.getNodeName())) {
                                        lblgetxmlemail.setText("email= " + element.getTextContent());
                                    }

                                }

                            }

                        }
                        System.out.println("find from XML completed !!!!");
                    } catch (SAXException ex) {
                        Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParserConfigurationException ex) {
                        Logger.getLogger(WorkWithXML.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Data file not found",
                    "Warning!",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BtnGetElementActionPerformed

    private void jtablexmlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtablexmlKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtablexmlKeyPressed

    private void jtablexmlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablexmlMouseClicked
        // TODO add your handling code here:
        int i = jtablexml.getSelectedRow();
        for (int j = 0; j < jtablexml.getColumnCount(); j++) {
            String a = jtablexml.getColumnModel().getColumn(j).getHeaderValue().toString();
            if (a == "headline") {
                if (jtablexml.getValueAt(i, j) != null) {
                    String head = jtablexml.getValueAt(i, j).toString();
                    if (head.equals("null")) {
//                     DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
//                    jtablexml.setBackground(Color.green);
                        txtheadline.setText("");
                    } else {
                        txtheadline.setText(head);
                    }
                }
            }
            if (a == "author") {
                if (jtablexml.getValueAt(i, j) != null) {
                    String author = jtablexml.getValueAt(i, j).toString();
                    System.out.println(author);
                    if (author.equals("null")) {
                        txtauthor.setText("");
                    } else {
                        txtauthor.setText(author);
                    }
                }
            }
            if (a == "email") {
                if (jtablexml.getValueAt(i, j) != null) {
                    String email = jtablexml.getValueAt(i, j).toString();
                    if (email.equals("null")) {
                        txtemail.setText("");
                    } else {
                        txtemail.setText(email);
                    }
                }
            }
            if (a == "ID") {
                if (jtablexml.getValueAt(i, j) != null) {
                    String id = jtablexml.getValueAt(i, j).toString();
                    if (id.equals("null")) {
                        txtID.setText("");
                    } else {
                        txtID.setText(id);
                    }
                }
            }
        }

    }//GEN-LAST:event_jtablexmlMouseClicked

    private void CheckBoxAoutoIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxAoutoIDActionPerformed
        // TODO add your handling code here:
        if (CheckBoxAoutoID.isSelected()) {
            txtID.setEnabled(false);
            txtID.setText("");
        } else {
            txtID.setEnabled(true);
        }
    }//GEN-LAST:event_CheckBoxAoutoIDActionPerformed

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
            java.util.logging.Logger.getLogger(WorkWithXML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WorkWithXML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WorkWithXML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WorkWithXML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WorkWithXML().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnDeleteRow;
    private javax.swing.JButton BtnEditBlock;
    private javax.swing.JButton BtnGetElement;
    private javax.swing.JButton BtnInsertBlock;
    private javax.swing.JCheckBox CheckBoxAoutoID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtablexml;
    private javax.swing.JLabel lblgetxmlID;
    private javax.swing.JLabel lblgetxmlauthor;
    private javax.swing.JLabel lblgetxmlemail;
    private javax.swing.JLabel lblgetxmlheadline;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtauthor;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtheadline;
    // End of variables declaration//GEN-END:variables

    int lastid = 0;
    int checkid = 0;
    String flag = "0";

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.Locale;
import javafx.animation.Animation.Status;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.UIResource;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {


    public CheckBoxRenderer() {
        JCheckBox checkBox = new JCheckBox();
        setHorizontalAlignment(JLabel.CENTER);
            checkBox.setHorizontalAlignment(SwingConstants.CENTER);
            checkBox.setBackground( Color.blue);
        }
    public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


        if (isSelected) {
            setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
            setBackground(table.getSelectionBackground());
            return c;
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());          
        }

        setSelected(value != null && (Boolean.valueOf(value.toString()).booleanValue()));
//        this.setBackground(row % 2 == 0 ? Color.blue : Color.BLUE);
//        ((JComponent) c).setBorder(null);
//      c.setForeground(Color.red);
//        if (isSelected) {
//            c.setBackground(Color.PINK);
//        } else{
//            c.setBackground(Color.WHITE);
//        }
//        return c;
//            setSelected((value != null && ((Boolean) value).booleanValue()));            
//            boolean b = Boolean.valueOf(value.toString());
//            boolean b = Boolean.parseBoolean(value);
//             boolean b1=Boolean.parseBoolean(s1); 
        
        return this;
    }
}

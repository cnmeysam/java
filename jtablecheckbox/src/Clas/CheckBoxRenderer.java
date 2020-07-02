/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clas;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
         public CheckBoxRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
          }

          public Component getTableCellRendererComponent(JTable table, Object value,
              boolean isSelected, boolean hasFocus, int row, int column) {
              
            if (isSelected) {
              setForeground(table.getSelectionForeground());
              super.setBackground(table.getSelectionBackground());
              setBackground(table.getSelectionBackground());
              
            } else {
              setForeground(table.getForeground());
              setBackground(table.getBackground());
            }

            setSelected(value != null && (Boolean.valueOf(value.toString()).booleanValue()));
//            setSelected((value != null && ((Boolean) value).booleanValue()));            
//            boolean b = Boolean.valueOf(value.toString());
//            boolean b = Boolean.parseBoolean(value);
//             boolean b1=Boolean.parseBoolean(s1);  
            return this;
          }
}
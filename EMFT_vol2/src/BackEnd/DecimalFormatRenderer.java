/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import emft_vol2.constants;
import java.awt.Component;
import java.text.DecimalFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import tools.help;
import tools.language_help;

/**
 * call ktory sa stara o zaokruhlovanie na X desatinnych miest v tabulke ak nieje v tabulke hodnota tam urobi catch na ilegal arguent
 * @author Jozef
 */
 public class DecimalFormatRenderer extends DefaultTableCellRenderer {
      private static final DecimalFormat formatter = new DecimalFormat( "#.000" );
 
      public Component getTableCellRendererComponent(
         JTable table, Object value, boolean isSelected,
         boolean hasFocus, int row, int column) {
 
         // First format the cell value as required
         try{
             value = formatter.format((Number)value);
         }catch(IllegalArgumentException e){
             
         }catch(ClassCastException a){
             value = formatter.format(0.0);
             
         }
         
 
            // And pass it on to parent class
 
         return super.getTableCellRendererComponent(
            table, value, isSelected, hasFocus, row, column );
      }
   }

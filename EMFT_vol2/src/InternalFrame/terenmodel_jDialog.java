/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InternalFrame;


import BackEnd.ColorColumnRenderer;
import BackEnd.DecimalFormatRenderer;
import BackEnd.MyCellEditor;
import BackEnd.rozpatie;
import static InternalFrame.BasicSettingsPanel.jTextField_X1;
import static InternalFrame.BasicSettingsPanel.jTextField_Z;
import static InternalFrame.BasicSettingsPanel.kontrolor;
import dislin.plot_1D;
import dislin.plot_2D;
import tools.*;
import emft_vol2.constants;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdelaunay.delaunay.error.DelaunayError;
import org.jdelaunay.delaunay.geometries.DPoint;

/**
 * kontrolor vykonava kontrolu ak aje tam -1 ( pozor polohovo citlivy podla toho vie kde čo je  poloha
 * 0 x1
 * 1 z1
 * 2 x2
 * 3 z2
 * 4 table ok values len posdlena zadana hodnota
 * 5 every row has XYZ and no wrong value in the row
 * 6 L1
 * @author Jozef
 */
public class terenmodel_jDialog extends javax.swing.JDialog {

    
    private static rozpatie R;
   
    public terenmodel_jDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
       }
 
    public terenmodel_jDialog(rozpatie R) throws DelaunayError {   
    this.R=R;
    initComponents();
    Table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE); // confirm Table of lost of focus
    this.DTMTable =(DefaultTableModel) Table.getModel();
    
    for (int cl1 = 0; cl1<DTMTable.getColumnCount() ;cl1 ++){
           
             TableColumn col = Table.getColumnModel().getColumn(cl1); // default cell editor for one columnt
                         col.setCellEditor( new MyCellEditor());
                         
             Table.getColumnModel().getColumn(cl1).setCellRenderer(        // zaukruhlovanie v tabulkach
                         new DecimalFormatRenderer() );
         
          
        }
    
    //this.DTRTable = (DefaultTableCellRenderer) Table.getModel();
    if(R.isDefinedGCLC() == false){
        
      jRadioButton_LC.doClick();
        
    }else{     
        jRadioButton_GC.doClick();       
    }    
    
  
    if(R.getBody().size() == 0){
        DTMTable.addRow(new Object[0]);
    }else{
        for(int cl1=0; cl1<R.getBodyGC().size();cl1++){
            
        
            DTMTable.addRow(new Object[] { R.getBodyGC().get(cl1)[0],R.getBodyGC().get(cl1)[1],R.getBodyGC().get(cl1)[2] });
           
        }
        DTMTable.addRow(new Object[0]);        
    }
           
    
    
       Table.getModel().addTableModelListener(new TableModelListener() {

    @Override
    public void tableChanged(TableModelEvent e) {
    if(isListener == true){
          try {String hodnota1 =String.valueOf(Table.getValueAt(Table.getEditingRow(), Table.getEditingColumn()));
         
          Table.getModel().removeTableModelListener(this);
                    hodnota1=hodnota1.replace(",", ".");
                  try{
                    double value;
                     value = Double.parseDouble(String.valueOf(hodnota1));  
                     
                     Table.setValueAt(value,Table.getEditingRow(), Table.getEditingColumn());
                     kontrolor.set(4, 1.);
                      
                     //Table.getModel().addTableModelListener(this);
                     
                       
                         
 
                     
                       }catch( NumberFormatException | NullPointerException p){
                       Table.setValueAt(0.0,Table.getEditingRow(), Table.getEditingColumn());
                       help.warning1row(language_help.LangLabel(constants.getLanguage_option(), 6));
                        kontrolor.set(4, -1.);
                       } 
         
                     
                  
                   
                      
          }catch(ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException p){}
        
                                if (Table.getEditingRow() == Table.getRowCount() - 1) { // ak klinuty posledny riadok tak   
                                DTMTable.addRow(new Object[0]);
                                }
                                Table.getModel().addTableModelListener(this);
        
                }}

            });
       
   //  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
  //  setText(value.toString());
  //  if (row==12 && column==2) {
  //      setBackground(Color.RED);
  //  }
 //   return this;
//}  
    
    
    
    
    }
 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup_LC_GC = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jRadioButton_LC = new javax.swing.JRadioButton();
        jRadioButton_GC = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel_X1 = new javax.swing.JLabel();
        jLabel_Z1 = new javax.swing.JLabel();
        jTextField_Z1 = new javax.swing.JTextField();
        jTextField_X1 = new javax.swing.JTextField();
        jLabel_A4 = new javax.swing.JLabel();
        jLabel_A5 = new javax.swing.JLabel();
        jLabel_Z2 = new javax.swing.JLabel();
        jLabel_X2 = new javax.swing.JLabel();
        jTextField_X2 = new javax.swing.JTextField();
        jTextField_Z2 = new javax.swing.JTextField();
        jLabel_A7 = new javax.swing.JLabel();
        jLabel_A6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jButton_ulozit = new javax.swing.JButton();
        jButton_ulozit1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel_L1 = new javax.swing.JLabel();
        jTextField_L1 = new javax.swing.JTextField();
        jLabel_A8 = new javax.swing.JLabel();
        jButton_nacitat_teren = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        if(constants.getLanguage_option() ==1){
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphics/header_termodel_SK.png")));
        }
        if(constants.getLanguage_option() ==3){
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphics/header_termodel_EN.png"))); // NOI18N
        }
        if(constants.getLanguage_option() ==2){
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphics/header_termodel_CZ.png"))); // NOI18N
        }

        jButton1.setText(language_help.LangLabel(constants.getLanguage_option(),0));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup_LC_GC.add(jRadioButton_LC);
        jRadioButton_LC.setText(language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(), 0));
        jRadioButton_LC.setEnabled(true);
        jRadioButton_LC.setPreferredSize(new java.awt.Dimension(111, 24));
        jRadioButton_LC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_LCActionPerformed(evt);
            }
        });

        buttonGroup_LC_GC.add(jRadioButton_GC);
        jRadioButton_GC.setText(language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(), 1));
        jRadioButton_GC.setEnabled(true);
        jRadioButton_GC.setPreferredSize(new java.awt.Dimension(111, 24));
        jRadioButton_GC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_GCActionPerformed(evt);
            }
        });

        jLabel2.setText(language_internal_frame_BasicSettingsPanel.LangLabel(constants.getLanguage_option(), 26));
        jLabel2.setEnabled(true);
        jLabel2.setPreferredSize(new java.awt.Dimension(144, 24));

        jLabel_X1.setText(language_internal_frame_BasicSettingsPanel.LangLabel(constants.getLanguage_option(), 16));
        jLabel_X1.setEnabled(false);
        jLabel_X1.setToolTipText(language_internal_frame_BasicSettingsPanel.LangLabel(constants.getLanguage_option(), 17));

        jLabel_Z1.setText(language_internal_frame_BasicSettingsPanel.LangLabel(constants.getLanguage_option(), 18));
        jLabel_Z1.setEnabled(false);
        jLabel_Z1.setToolTipText(language_internal_frame_BasicSettingsPanel.LangLabel(constants.getLanguage_option(), 19));

        jTextField_Z1.setEditable(true);
        help.DisplayDouble(jTextField_Z1, InternalFrameproject.Rozpätie.getLCcoordinates1()[2], 3);
        jTextField_Z1.setEnabled(false);
        jTextField_Z1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_Z1KeyReleased(evt);
            }
        });

        jTextField_X1.setEditable(true);
        help.DisplayDouble(jTextField_X1, InternalFrameproject.Rozpätie.getLCcoordinates1()[0], 3);
        jTextField_X1.setEnabled(false);
        jTextField_X1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_X1KeyReleased(evt);
            }
        });

        jLabel_A4.setText("m");
        jLabel_A4.setEnabled(false);

        jLabel_A5.setText("m");
        jLabel_A5.setEnabled(false);

        jLabel_Z2.setText(language_internal_frame_BasicSettingsPanel.LangLabel(constants.getLanguage_option(), 22));
        jLabel_Z2.setEnabled(false);
        jLabel_Z2.setToolTipText(language_internal_frame_BasicSettingsPanel.LangLabel(constants.getLanguage_option(), 23));

        jLabel_X2.setText(language_internal_frame_BasicSettingsPanel.LangLabel(constants.getLanguage_option(), 20));
        jLabel_X2.setEnabled(false);
        jLabel_X2.setToolTipText(language_internal_frame_BasicSettingsPanel.LangLabel(constants.getLanguage_option(), 21));

        jTextField_X2.setEditable(true);
        help.DisplayDouble(jTextField_X2, InternalFrameproject.Rozpätie.getLCcoordinates2()[0], 3);
        jTextField_X2.setEnabled(false);
        jTextField_X2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_X2KeyReleased(evt);
            }
        });

        jTextField_Z2.setEditable(true);
        help.DisplayDouble(jTextField_Z2, InternalFrameproject.Rozpätie.getLCcoordinates2()[2], 3);
        jTextField_Z2.setEnabled(false);
        jTextField_Z2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_Z2KeyReleased(evt);
            }
        });

        jLabel_A7.setText("m");
        jLabel_A7.setEnabled(false);

        jLabel_A6.setText("m");
        jLabel_A6.setEnabled(false);

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table.setRowHeight(26);
        Table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(Table);
        if (Table.getColumnModel().getColumnCount() > 0) {
            Table.getColumnModel().getColumn(0).setResizable(false);
            Table.getColumnModel().getColumn(0).setHeaderValue(language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(), 3)
            );
            Table.getColumnModel().getColumn(1).setResizable(false);
            Table.getColumnModel().getColumn(1).setHeaderValue(language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(), 4)
            );
            Table.getColumnModel().getColumn(2).setResizable(false);
            Table.getColumnModel().getColumn(2).setHeaderValue(language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(), 5)
            );
        }

        jButton_ulozit.setText(language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(),15));
        jButton_ulozit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ulozitActionPerformed(evt);
            }
        });

        jButton_ulozit1.setText(language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(),9));
        jButton_ulozit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ulozit1ActionPerformed(evt);
            }
        });

        jLabel3.setText(language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(), 2));
        jLabel3.setEnabled(false);
        jLabel3.setPreferredSize(new java.awt.Dimension(144, 24));

        jLabel_L1.setText(language_internal_frame_BasicSettingsPanel.LangLabel(constants.getLanguage_option(), 25));
        jLabel_L1.setEnabled(true);
        jLabel_Z1.setToolTipText(language_internal_frame_BasicSettingsPanel.LangLabel(constants.getLanguage_option(), 19));

        jTextField_L1.setEditable(true);
        help.DisplayDouble(jTextField_L1, InternalFrameproject.Rozpätie.getLCcoordinates1()[1], 3);
        jTextField_L1.setEnabled(true);
        jTextField_L1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_L1KeyReleased(evt);
            }
        });

        jLabel_A8.setText("m");
        jLabel_A8.setEnabled(true);

        jButton_nacitat_teren.setText(language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(),13));
        jButton_nacitat_teren.setToolTipText(language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(),14));
        jButton_nacitat_teren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_nacitat_terenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton_LC, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButton_GC, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton_ulozit1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_nacitat_teren, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel_Z1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField_Z1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel_A5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel_X1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField_X1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel_A4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel_Z2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField_Z2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel_A7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel_X2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField_X2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel_A6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel_L1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField_L1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel_A8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton_ulozit, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_LC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_GC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel_X1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_X1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_A4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel_Z1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_Z1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_A5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel_X2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_X2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_A6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_L1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_L1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_A8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel_Z2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_Z2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_A7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_ulozit1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_nacitat_teren, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_ulozit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Funkcia sluziaca na korekturu dat pre triangulaciu ako bolo zistene dna 27.9. truangulacii sa nepači ak su body v jednej rovien preto ak sa bude opakovat vyška tak im pridam stotinu milimetra
     */
    private void korektura_dat(){ // 
        double pricitavajucakonstanta= 0.00001;
       // double[] counter2D = new double[2]; // prve cislo hodnota druhe cislo ktory krat už sa opakuje 
        ArrayList<double[]> pocitadla = new ArrayList<>();
        for(int cl1=0; cl1<DTMTable.getRowCount();cl1++){
          double[] counter2D = new double[2];
            counter2D[0]=help.Object_To_double(DTMTable.getValueAt(cl1, 1)); // vloz do countera hodnota
            counter2D[1]=0;
            for(int cl2=0; cl2<pocitadla.size();cl2++){
                
                if(counter2D[0]==pocitadla.get(cl2)[0] ){  // podmienka ak už existuje take čislo zapisane v tabulke
                   counter2D[1]=pocitadla.get(cl2)[1]+1; 
                   pocitadla.set(cl2, counter2D);          // ak najde replace v arrayliste a zaroven
                   DTMTable.setValueAt(counter2D[0]+pricitavajucakonstanta*counter2D[1], cl1, 1);
                   break;
                }else{
                     
                   } // ak neexistuje pridaj do zoznamu 
                if(cl2==pocitadla.size()-1){ 
                   pocitadla.add(counter2D);
                   break;
                
                }       
            }
            if(pocitadla.size()==0){
                pocitadla.add(counter2D);
            }  
        }
 
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField_Z1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Z1KeyReleased
        kontrolor.set(1, help.ReadCheckDouble(jTextField_Z1, -1));
    }//GEN-LAST:event_jTextField_Z1KeyReleased

    private void jTextField_X1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_X1KeyReleased
        kontrolor.set(0, help.ReadCheckDouble(jTextField_X1, -1));
    }//GEN-LAST:event_jTextField_X1KeyReleased

    private void jTextField_X2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_X2KeyReleased
        kontrolor.set(2, help.ReadCheckDouble(jTextField_X2, -1));
    }//GEN-LAST:event_jTextField_X2KeyReleased

    private void jTextField_Z2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Z2KeyReleased
        kontrolor.set(3, help.ReadCheckDouble(jTextField_Z2, -1));
    }//GEN-LAST:event_jTextField_Z2KeyReleased

    private void jRadioButton_LCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_LCActionPerformed
       jLabel_X1.setEnabled(false);
        jLabel_X2.setEnabled(false);
        jLabel_Z1.setEnabled(false);
        jLabel_Z2.setEnabled(false);
        jLabel_A4.setEnabled(false);
        jLabel_A5.setEnabled(false);
        jLabel_A6.setEnabled(false);
        jLabel_A7.setEnabled(false);
        jLabel2.setEnabled(false);
        jTextField_X1.setEnabled(false);
        jTextField_X2.setEnabled(false);
        jTextField_Z1.setEnabled(false);
        jTextField_Z2.setEnabled(false);
        
    }//GEN-LAST:event_jRadioButton_LCActionPerformed

    private void jRadioButton_GCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_GCActionPerformed
        jLabel_X1.setEnabled(true);
        jLabel_X2.setEnabled(true);
        jLabel_Z1.setEnabled(true);
        jLabel_Z2.setEnabled(true);
        jLabel_A4.setEnabled(true);
        jLabel_A5.setEnabled(true);
        jLabel_A6.setEnabled(true);
        jLabel_A7.setEnabled(true);
        jLabel2.setEnabled(true);
        jTextField_X1.setEnabled(true);
        jTextField_X2.setEnabled(true);
        jTextField_Z1.setEnabled(true);
        jTextField_Z2.setEnabled(true);
    }//GEN-LAST:event_jRadioButton_GCActionPerformed

    private void TableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableKeyReleased
       
    }//GEN-LAST:event_TableKeyReleased

        protected static void generuj_teren_bez_ukazky(){
        
        InternalFrameproject.Rozpätie.setZ(help.ReadCheckDouble(BasicSettingsPanel.jTextField_Z,-1));
        InternalFrameproject.Rozpätie.setA(help.ReadCheckDouble(BasicSettingsPanel.jTextField_A,-1));

        if(InternalFrameproject.Rozpätie.isAppTeren() == false){
            InternalFrameproject.Rozpätie.setLCcoordinates(0, 0, 0, InternalFrameproject.Rozpätie.getA(), 0);
            
        }else{
             InternalFrameproject.Rozpätie.setLCcoordinates(Double.valueOf(BasicSettingsPanel.jTextField_X1.getText()), Double.valueOf(BasicSettingsPanel.jTextField_L1.getText()),Double.valueOf(BasicSettingsPanel.jTextField_Z1.getText()), Double.valueOf(BasicSettingsPanel.jTextField_X2.getText()), Double.valueOf(BasicSettingsPanel.jTextField_Z2.getText()));         
        }
        
        
        try {
           
            InternalFrameproject.Rozpätie.generateTerrain();
           
        
        } catch (DelaunayError ex) {
            Logger.getLogger(BasicSettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
    }
    
    
    
    
    private void jButton_ulozitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ulozitActionPerformed
       
        boolean error = false;
        boolean oktowers=false;
        double[] tower1 = new double[]{0,0,0};
        double[] tower2 = new double[]{0,0,0};
    
       
      //  korektura_dat(); // korektura dat
                
       // if (jRadioButton_GC.isSelected() == true) { // aj je GC označen ideme do
            if (kontrolor.get(0) != -1 && kontrolor.get(1) != -1 && kontrolor.get(2) != -1 && kontrolor.get(3) != -1 && kontrolor.get(6) != -1) {

                try {
                     tower1 = new double[]{Double.valueOf(jTextField_X1.getText()), 0, Double.valueOf(jTextField_Z1.getText())};
                     tower2 = new double[]{Double.valueOf(jTextField_X2.getText()), 0, Double.valueOf(jTextField_Z2.getText())};
                     oktowers=true;
                } catch (NumberFormatException w) {
                    help.warning2row(language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(), 7), language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(), 10));

                }

            } else {
                error = true;
                help.warning2row(language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(), 7), language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(), 10));
            }
       // }else{oktowers=true;}

        
        if (kontrolor.get(4) != -1 && oktowers ==true) {

            for (int cl1 = 0; cl1 < Table.getRowCount() - 1; cl1++) {  //ROW

                for (int cl2 = 0; cl2 < 3; cl2++) {                    //COLUMN
                
                 if(Table.getValueAt(cl1, cl2)== language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(), 6) || Table.getValueAt(cl1, cl2)== null){
                     error =true;
                     help.warning2row(language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(), 7), language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(), 8));
                     break;
                 }
                if (error ==true) break;
                }
                if (error ==true) break;
                 
            }
try {
    
            if(error != true){
                DPoint bod = new DPoint(0, 0,0);
                ArrayList<double[]>  body = new  ArrayList<>();
                 ArrayList<double[]>  bodyGC = new  ArrayList<>();
                // double xray[]=new double[Table.getRowCount() - 1]; // GRAFICKE TESTOVANIE 
               //  double y1ray[]=new double[Table.getRowCount() - 1];
               //  double y2ray[]=new double[Table.getRowCount() - 1];;
                 
                for (int cl1 = 0; cl1 < Table.getRowCount() - 1; cl1++) {  
 
                    bod.setX(Double.parseDouble(String.valueOf(Table.getValueAt(cl1, 0))));
                    bod.setY(Double.parseDouble(String.valueOf(Table.getValueAt(cl1, 1)))+cl1*0.00000001);   
                    bod.setZ(Double.parseDouble(String.valueOf(Table.getValueAt(cl1, 2))));
                //    xray[cl1]=bod.getX();
                 //   y1ray[cl1]=bod.getY();
                 //   y2ray[cl1]=bod.getY();
                    
                    bodyGC.add(new double[]{bod.getX(),bod.getY(),bod.getZ()});
                    bod =help.CorToLC(tower1, tower2, bod);
                    body.add(new double[]{bod.getX(),bod.getY(),bod.getZ()});
                }
               //  ArrayList<double[]> y3ray = new ArrayList<>();
                 //y3ray.add(xray);
               //  y3ray.add(y1ray);
               //  y3ray.add(y2ray);
                 
//                 String[] ahoj = new String[3]; ahoj[0]= "1,5 m"; ahoj[1]= "32,5 m"; ahoj[2]= "554,5 m";
//                 plot_1D graf2 = new plot_1D(xray, y3ray, "KOKOT", "PICA", "ROW1", "ROW2",ahoj);
//                 graf2.setunits(1000);
//                 graf2.draw_1D_yn();
//                 
//                 plot_2D graf3 = new plot_2D(xray, y2ray,y2ray, "KOKOT", "PICA", "ROW1", "ROW2",true,"ZKOKOT");
//                 graf3.draw_2D_yn();
//                 
//                 plot_2D graf32 = new plot_2D(xray, y2ray,y2ray, "KOKOT", "PICA", "ROW1", "ROW2",true);
//                 graf32.draw_2D_yn();
                
                R.setLCcoordinates(Double.valueOf(jTextField_X1.getText()), Double.valueOf(jTextField_L1.getText()),Double.valueOf(jTextField_Z1.getText()), Double.valueOf(jTextField_X2.getText()), Double.valueOf(jTextField_Z2.getText()));
                R.setBody(body);
                R.setBodyGC(bodyGC);
                help.DisplayDouble(BasicSettingsPanel.jTextField_A, InternalFrameproject.Rozpätie.getA(),2);
                 
                 help.DisplayDouble(BasicSettingsPanel.jTextField_X1, InternalFrameproject.Rozpätie.getLCcoordinates1()[0], 2);
                 help.DisplayDouble(BasicSettingsPanel.jTextField_Z1, InternalFrameproject.Rozpätie.getLCcoordinates1()[2], 2);
                 help.DisplayDouble(BasicSettingsPanel.jTextField_X2, InternalFrameproject.Rozpätie.getLCcoordinates2()[0], 2);
                 help.DisplayDouble(BasicSettingsPanel.jTextField_Z2, InternalFrameproject.Rozpätie.getLCcoordinates2()[2], 2);
                 help.DisplayDouble(BasicSettingsPanel.jTextField_L1, InternalFrameproject.Rozpätie.getLCcoordinates1()[1], 2);
        }
} catch (DelaunayError ex) {
                        Logger.getLogger(terenmodel_jDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
 
        
        }
       generuj_teren_bez_ukazky();
       if (error!=true) dispose();
    }//GEN-LAST:event_jButton_ulozitActionPerformed

    private void jButton_ulozit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ulozit1ActionPerformed
        
        isListener =false;
        int[] rows = Table.getSelectedRows();
        if (rows.length>1 ) {
       for(int i=0;i<rows.length;i++){
     DTMTable.removeRow(rows[i]-i);
     
     
      }}
        isListener =true;
    }//GEN-LAST:event_jButton_ulozit1ActionPerformed

    private void jTextField_L1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_L1KeyReleased
        kontrolor.set(6, help.ReadCheckDouble(jTextField_L1, -1));
    }//GEN-LAST:event_jTextField_L1KeyReleased

    private void jButton_nacitat_terenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nacitat_terenActionPerformed

     
        String userhome = System.getProperty(constants.getProgrampath());
        JFileChooser chooser = new JFileChooser(userhome + "\\" + constants.getTeret_input_folder());
        FileNameExtensionFilter txtfilter = new FileNameExtensionFilter("txt files (*.txt)", "txt");
        chooser.setDialogTitle(language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(), 14));
        chooser.setFileFilter(txtfilter);
        chooser.showOpenDialog(null);
        
        File f = chooser.getSelectedFile();
        File subor = new File( f.getParent()+ "\\" + f.getName());
        
        try {
            Scanner input = new Scanner(subor);
            
            jTextField_X1.setText( String.valueOf(input.nextDouble()));
            jTextField_Z1.setText( String.valueOf(input.nextDouble()));
            jTextField_X2.setText( String.valueOf(input.nextDouble()));
            jTextField_Z2.setText( String.valueOf(input.nextDouble()));
            jTextField_L1.setText( String.valueOf(input.nextDouble()));
            input.nextLine();
         
            int rowCount = DTMTable.getRowCount();
              
            
            for (int i = rowCount-1 ; i >= 0; i--) {
            isListener =false;
                DTMTable.removeRow(i);
                isListener =true;
            }
                
             ArrayList<double[]>  bodyA = new  ArrayList<>();
            
            while (input.hasNext()) {
  
               bodyA.add(new double[]{input.nextDouble() ,input.nextDouble(),input.nextDouble() });
               R.setBodyGC(bodyA);
             //DTMTable.addRow(new Object[] { input.nextDouble() ,input.nextDouble(),input.nextDouble() });
            
            try{
             input.nextLine();   
            }catch (java.util.NoSuchElementException o) {
              // DTMTable.removeRow(0);
               // DTMTable.addRow(new Object[0]);
            
        }
            }
            
             
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(terenmodel_jDialog.class.getName()).log(Level.SEVERE, null, ex);
          
        }

     
      if(R.getBodyGC().size() == 0){
       isListener=false;
          DTMTable.addRow(new Object[0]);
          
          isListener=true;
    }else{
        for(int cl1=0; cl1<R.getBodyGC().size();cl1++){
            
        isListener=false;
            DTMTable.addRow(new Object[] { R.getBodyGC().get(cl1)[0],R.getBodyGC().get(cl1)[1],R.getBodyGC().get(cl1)[2] });
        isListener=true;   
        }
        isListener=false;
        DTMTable.addRow(new Object[0]);    
        isListener=true;
    }
      
      
      
    }//GEN-LAST:event_jButton_nacitat_terenActionPerformed

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
            java.util.logging.Logger.getLogger(terenmodel_jDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(terenmodel_jDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(terenmodel_jDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(terenmodel_jDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                terenmodel_jDialog dialog = new terenmodel_jDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table;
    private javax.swing.ButtonGroup buttonGroup_LC_GC;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_nacitat_teren;
    private javax.swing.JButton jButton_ulozit;
    private javax.swing.JButton jButton_ulozit1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_A4;
    private javax.swing.JLabel jLabel_A5;
    private javax.swing.JLabel jLabel_A6;
    private javax.swing.JLabel jLabel_A7;
    private javax.swing.JLabel jLabel_A8;
    private javax.swing.JLabel jLabel_L1;
    private javax.swing.JLabel jLabel_X1;
    private javax.swing.JLabel jLabel_X2;
    private javax.swing.JLabel jLabel_Z1;
    private javax.swing.JLabel jLabel_Z2;
    private javax.swing.JRadioButton jRadioButton_GC;
    private javax.swing.JRadioButton jRadioButton_LC;
    private javax.swing.JScrollPane jScrollPane1;
    static javax.swing.JTextField jTextField_L1;
    static javax.swing.JTextField jTextField_X1;
    static javax.swing.JTextField jTextField_X2;
    static javax.swing.JTextField jTextField_Z1;
    static javax.swing.JTextField jTextField_Z2;
    // End of variables declaration//GEN-END:variables
 static List<Double> kontrolor = Arrays.asList(1., 1., 1.,1.,1.,1.,1.); // pocet kontrolovanych prvkov
 DefaultTableModel DTMTable ;
 DefaultTableCellRenderer DTRTable;
 private static boolean isListener = true;
}

class language_internal_fJdialog_terenModel {

/**
 * Function just add elements to the array list 
 * If once function runs and sets variable "inicializovane" true it never runs again
 */    
static void constructor(){
              /*SK*/                /*CZ*/              /*EN*/   
 /*0*/   SK.add("Body v lokálnych suradniciach");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position 
/*1*/    SK.add("Body v globálnych suradniciach");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*2*/     SK.add("Hlavná os rozpätia");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");  
/*3*/    SK.add("X");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");  
/*4*/    SK.add("Y");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");  
/*5*/    SK.add("Z");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");  
/*6*/    SK.add("Zla hodnota");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");  
/*7*/    SK.add("Error:");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");  
/*8*/    SK.add("Tabuľka obsahuje zlú hodnotu");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                                            
 /*9*/   SK.add("Vymaž riadky");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                                            
  /*10*/  SK.add("Chyba v hodnoty osi rozpätia");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                                            
  /*11*/  SK.add("Chyba v hodnoty osi rozpätia");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                                            
   /*12*/ SK.add("Chyba v hodnoty osi rozpätia");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                                            
   /*13*/ SK.add("Načítať body");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                                                                                   
   /*14*/ SK.add("Náčítať terén aj zo stožiarom");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                                                                                   
  /*15*/ SK.add("Uložiť a vygenerovať");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                                                                                               
                                                                                            
                                                                                          
                                                                                                   
                                                     
                        
                          
                
         
 // language_main_frame.LangLabel(constants.getLanguage_option(),0)      
 inicializovane = true;
}
 
/**
 * Function returns on string label in set language
 * @param X  defines the language 1 Slovak, 2 Czech, 3 English
 * @param Y  defines the label position according the drawing, is starts from 0 
 * @return 
 */
public static String LangLabel(int X,int Y){
    
    if ( inicializovane == false){constructor();}
    
    String SlovoDaloSlovo = "empty";
    switch (X) {
        case 1:  
            SlovoDaloSlovo=SK.get(Y);
            break;
        case 2:
            SlovoDaloSlovo=CZ.get(Y);
            break;
        case 3:
            SlovoDaloSlovo=EN.get(Y);
            break;
    }
    return SlovoDaloSlovo;
}    

public static String LangLabel2(){
 
    String SlovoDaloSlovo = "empty";
    
    return SlovoDaloSlovo;
    } 
 private static  final ArrayList<String> SK= new ArrayList<>();
 private static final ArrayList<String> CZ= new ArrayList<>();
 private static final ArrayList<String> EN= new ArrayList<>();
 private static boolean inicializovane = false;
 
}
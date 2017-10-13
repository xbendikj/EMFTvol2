/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InternalFrame;

import BackEnd.ColorColumnRenderer;
import BackEnd.MyCellEditor;
import static InternalFrame.CatenaryPanel.isListener;
import static InternalFrame.terenmodel_jDialog.kontrolor;
import java.util.ArrayList;
import emft_vol2.constants;
import java.awt.Color;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Jozef
 */
public class ObserverPanel extends javax.swing.JPanel {

    /**
     * Creates new form ObserverPanel
     */
    public ObserverPanel() {
        initComponents();
        // definuju tabulky a zadavanie do nich
        Table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE); // confirm Table of lost of focus
        this.DTMTable =(DefaultTableModel) Table.getModel();
        
        table_1D.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE); // confirm Table of lost of focus
        this.DTMTable_1D =(DefaultTableModel) table_1D.getModel();
    
        TableColumn col = Table.getColumnModel().getColumn(0); // default cell editor for one columnt
        col.setCellEditor( new MyCellEditor());
        
        col = table_1D.getColumnModel().getColumn(0); // default cell editor for one columnt
        col.setCellEditor( new MyCellEditor());
        
        col = table_1D.getColumnModel().getColumn(1); // default cell editor for one columnt
        col.setCellEditor( new MyCellEditor());
        
        col = table_1D.getColumnModel().getColumn(2); // default cell editor for one columnt
        col.setCellEditor( new MyCellEditor());
        
        col = table_1D.getColumnModel().getColumn(3); // default cell editor for one columnt
        col.setCellEditor( new MyCellEditor());
        
        table_1D.setBackground(Color.gray); // set background colors
       //listener meni čiarky bodky kontroluje
    Table.getModel().addTableModelListener(new TableModelListener() {

    @Override
    public void tableChanged(TableModelEvent e) {
        
    if(isListener == true){
    if(e.getColumn()!=11 && e.getColumn()!=20){ // podmienka pre checkbox riadky    
          try {String hodnota1 =String.valueOf(Table.getValueAt(Table.getEditingRow(), Table.getEditingColumn()));
         
          Table.getModel().removeTableModelListener(this);
                    hodnota1=hodnota1.replace(",", ".");
                  try{
                    double value;
                     value = Double.parseDouble(String.valueOf(hodnota1));  
                     
                     Table.setValueAt(value,Table.getEditingRow(), Table.getEditingColumn());
                   
                      
                     //Table.getModel().addTableModelListener(this);
                     
                       
                         
 
                     
                       }catch( NumberFormatException | NullPointerException p){
                       Table.setValueAt(language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(), 6),Table.getEditingRow(), Table.getEditingColumn());
                     
                        
                       } 
         
                     
                  
                   
                      
          }catch(ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException p){}
        
                                if (Table.getEditingRow() == Table.getRowCount() - 1) { // ak klinuty posledny riadok tak   
                                DTMTable.addRow(new Object[0]);
                                }
                                Table.getModel().addTableModelListener(this);
        
                }
    
    }

    }});
    
    table_1D.getModel().addTableModelListener(new TableModelListener() {

    @Override
    public void tableChanged(TableModelEvent e) {
        
    if(isListener == true){
    if(e.getColumn()!=11 && e.getColumn()!=20){ // podmienka pre checkbox riadky    
          try {String hodnota1 =String.valueOf(table_1D.getValueAt(table_1D.getEditingRow(), table_1D.getEditingColumn()));
         
          table_1D.getModel().removeTableModelListener(this);
                    hodnota1=hodnota1.replace(",", ".");
                  try{
                    double value;
                     value = Double.parseDouble(String.valueOf(hodnota1));  
                     
                     table_1D.setValueAt(value,table_1D.getEditingRow(), table_1D.getEditingColumn());
                   
                      
                     //table_1D.getModel().addtable_1DModelListener(this);
                     
                       
                         
 
                     
                       }catch( NumberFormatException | NullPointerException p){
                       table_1D.setValueAt(language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(), 6),table_1D.getEditingRow(), table_1D.getEditingColumn());
                     
                        
                       } 
         
                     
                  
                   
                      
          }catch(ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException p){}
        
                                
                                table_1D.getModel().addTableModelListener(this);
        
                }
    
    }

    }});
       
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        X_priecne_user_custom = new javax.swing.ButtonGroup();
        pozdl_Z_user_custom = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        P1D = new javax.swing.JCheckBox();
        P2D = new javax.swing.JCheckBox();
        P3D = new javax.swing.JCheckBox();
        P2Dh = new javax.swing.JCheckBox();
        P2Dv = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        P1Dpriecne = new javax.swing.JCheckBox();
        P1Dpozdlzne = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        priecna_X_textfield = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel_A2 = new javax.swing.JLabel();
        X_precne_auto = new javax.swing.JCheckBox();
        X_precne_user = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        pozdl_Z_textfield = new javax.swing.JTextField();
        jLabel_A3 = new javax.swing.JLabel();
        Z_pozdl_auto = new javax.swing.JCheckBox();
        Z_pozdl_user = new javax.swing.JCheckBox();
        P1D_free = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_1D = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setBorder(javax.swing.BorderFactory.createTitledBorder(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 0)
        ));

        P1D.setSelected(true);
        P1D.setText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 1));
        P1D.setToolTipText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 2));

        P2D.setText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 3));
        P2D.setToolTipText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 4));
        P2D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P2DActionPerformed(evt);
            }
        });

        P3D.setText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 5));
        P3D.setToolTipText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 6));

        P2Dh.setText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 7));
        P2Dh.setEnabled(false);
        P3D.setToolTipText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 8));

        P2Dv.setText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 9));
        P2Dv.setEnabled(false);
        P3D.setToolTipText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 10));

        jLabel1.setText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 12)
        );

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table.setRowHeight(26);
        jScrollPane1.setViewportView(Table);
        if (Table.getColumnModel().getColumnCount() > 0) {
            Table.getColumnModel().getColumn(0).setResizable(false);
            Table.getColumnModel().getColumn(0).setHeaderValue(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(),28));
        }

        jButton1.setText(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 46));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        P1Dpriecne.setSelected(true);
        P1Dpriecne.setText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 14));
        P3D.setToolTipText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 15));

        P1Dpozdlzne.setText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 16));
        P3D.setToolTipText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 17));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        priecna_X_textfield.setText("150");
        priecna_X_textfield.setEnabled(false);

        jLabel2.setText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 22)
        );
        jLabel2.setPreferredSize(new java.awt.Dimension(73, 24));

        jLabel_A2.setText("m");

        X_priecne_user_custom.add(X_precne_auto);
        X_precne_auto.setSelected(true);
        X_precne_auto.setText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 18));
        X_precne_auto.setToolTipText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 19));
        X_precne_auto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                X_precne_autoActionPerformed(evt);
            }
        });

        X_priecne_user_custom.add(X_precne_user);
        X_precne_user.setText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 20));
        X_precne_user.setToolTipText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 21));
        X_precne_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                X_precne_userActionPerformed(evt);
            }
        });

        jLabel3.setText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 23)
        );
        jLabel3.setPreferredSize(new java.awt.Dimension(73, 24));

        pozdl_Z_textfield.setText("0");
        pozdl_Z_textfield.setEnabled(false);

        jLabel_A3.setText("m");

        pozdl_Z_user_custom.add(Z_pozdl_auto);
        Z_pozdl_auto.setSelected(true);
        Z_pozdl_auto.setText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 18));
        X_precne_auto.setToolTipText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 24));
        Z_pozdl_auto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Z_pozdl_autoActionPerformed(evt);
            }
        });

        pozdl_Z_user_custom.add(Z_pozdl_user);
        Z_pozdl_user.setText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 20));
        X_precne_user.setToolTipText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 25));
        Z_pozdl_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Z_pozdl_userActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(priecna_X_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_A2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(X_precne_auto, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(X_precne_user, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pozdl_Z_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_A3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Z_pozdl_auto, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Z_pozdl_user, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_A2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(priecna_X_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(X_precne_auto)
                        .addComponent(X_precne_user)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_A3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pozdl_Z_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Z_pozdl_auto)
                        .addComponent(Z_pozdl_user)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        P1D_free.setText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 26));
        P3D.setToolTipText(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(), 27));
        P1D_free.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P1D_freeActionPerformed(evt);
            }
        });

        table_1D.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_1D.setEnabled(false);
        table_1D.setRowHeight(26);
        jScrollPane3.setViewportView(table_1D);
        if (table_1D.getColumnModel().getColumnCount() > 0) {
            table_1D.getColumnModel().getColumn(0).setResizable(false);
            table_1D.getColumnModel().getColumn(0).setHeaderValue(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(),28));
            table_1D.getColumnModel().getColumn(1).setResizable(false);
            table_1D.getColumnModel().getColumn(1).setHeaderValue(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(),29));
            table_1D.getColumnModel().getColumn(2).setResizable(false);
            table_1D.getColumnModel().getColumn(2).setHeaderValue(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(),30));
            table_1D.getColumnModel().getColumn(3).setResizable(false);
            table_1D.getColumnModel().getColumn(3).setHeaderValue(language_internal_frame_observer_panel.LangLabel(constants.getLanguage_option(),31));
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(P1D)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1Dpriecne)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1Dpozdlzne))
                    .addComponent(P1D_free)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(P2D)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(P2Dh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(P2Dv))
                            .addComponent(P3D))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(P1D)
                    .addComponent(P1Dpriecne)
                    .addComponent(P1Dpozdlzne))
                .addGap(7, 7, 7)
                .addComponent(P1D_free)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(P2D)
                            .addComponent(P2Dh)
                            .addComponent(P2Dv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3D)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void P2DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P2DActionPerformed
        if (P2D.isSelected()){
            P2Dh.setEnabled(true);
             P2Dv.setEnabled(true);
             
        }else{
            P2Dh.setEnabled(false);
             P2Dv.setEnabled(false);
        }
    }//GEN-LAST:event_P2DActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        isListener = false;
        
        int[] rows = Table.getSelectedRows();
        if (rows.length>1 ) {  
       for(int i=0;i<rows.length;i++){
     DTMTable.removeRow(rows[i]-i);
     
       }}
     isListener = true; 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void X_precne_autoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_X_precne_autoActionPerformed
         if (X_precne_auto.isSelected()){
            priecna_X_textfield.setEnabled(false);  
        }else{
             priecna_X_textfield.setEnabled(true);        
        }
    }//GEN-LAST:event_X_precne_autoActionPerformed

    private void X_precne_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_X_precne_userActionPerformed
        if (X_precne_user.isSelected()){
            priecna_X_textfield.setEnabled(true);  
        }else{
             priecna_X_textfield.setEnabled(false);        
        }
    }//GEN-LAST:event_X_precne_userActionPerformed

    private void Z_pozdl_autoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Z_pozdl_autoActionPerformed
         if (Z_pozdl_auto.isSelected()){
            pozdl_Z_textfield.setEnabled(false);  
        }else{
             pozdl_Z_textfield.setEnabled(true);        
        }
    }//GEN-LAST:event_Z_pozdl_autoActionPerformed

    private void Z_pozdl_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Z_pozdl_userActionPerformed
       if (Z_pozdl_user.isSelected()){
            pozdl_Z_textfield.setEnabled(true);  
        }else{
             pozdl_Z_textfield.setEnabled(false);        
        }
    }//GEN-LAST:event_Z_pozdl_userActionPerformed

    private void P1D_freeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P1D_freeActionPerformed
        if (P1D_free.isSelected()){
            table_1D.setEnabled(true);
            table_1D.setBackground(Color.WHITE);
        }else{
             table_1D.setEnabled(false);
             table_1D.setBackground(Color.gray);
        }
    }//GEN-LAST:event_P1D_freeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox P1D;
    private javax.swing.JCheckBox P1D_free;
    private javax.swing.JCheckBox P1Dpozdlzne;
    private javax.swing.JCheckBox P1Dpriecne;
    private javax.swing.JCheckBox P2D;
    private javax.swing.JCheckBox P2Dh;
    private javax.swing.JCheckBox P2Dv;
    private javax.swing.JCheckBox P3D;
    private javax.swing.JTable Table;
    private javax.swing.JCheckBox X_precne_auto;
    private javax.swing.JCheckBox X_precne_user;
    private javax.swing.ButtonGroup X_priecne_user_custom;
    private javax.swing.JCheckBox Z_pozdl_auto;
    private javax.swing.JCheckBox Z_pozdl_user;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_A2;
    private javax.swing.JLabel jLabel_A3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField pozdl_Z_textfield;
    private javax.swing.ButtonGroup pozdl_Z_user_custom;
    private javax.swing.JTextField priecna_X_textfield;
    private javax.swing.JTable table_1D;
    // End of variables declaration//GEN-END:variables
DefaultTableModel DTMTable ;
DefaultTableModel DTMTable_1D ;
boolean isListener=true;
}

class language_internal_frame_observer_panel {

/**
 * Function just add elements to the array list 
 * If once function runs and sets variable "inicializovane" true it never runs again
 */    
static void constructor(){
              /*SK*/                /*CZ*/              /*EN*/   
 /*0*/   SK.add("Parametre Pozorovateľa");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position 
/*1*/    SK.add("1D mapovanie");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*2*/    SK.add("Líniove mapovanie poľa");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*3*/    SK.add("2D mapovanie");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*4*/    SK.add("Plošne mapovanie poľa");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*5*/    SK.add("3D");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*6*/    SK.add("Priestorové mapovanie poľa");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");    
/*7*/    SK.add("horizontálne");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*8*/    SK.add("horizontálne mapovanie poľa");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");          
/*9*/    SK.add("vertikálne");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*10*/   SK.add("vertikálne mapovanie poľa");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*11*/   SK.add("počítaná výška");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*12*/   SK.add("poloha pozorovateľa");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*13*/   SK.add("poč. výšok pre 1D a 3D");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*14*/   SK.add("priečne");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*15*/   SK.add("priečne mapovanie poľa kolmé na os X");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*16*/   SK.add("pozdlžne");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*17*/   SK.add("pozdlžne mapovanie poľa kolmé na os Z");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*18*/   SK.add("auto");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*19*/   SK.add("Automatická hodnota v mieste maximálneho priehybu");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*20*/   SK.add("vlastná");   
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
 /*21*/   SK.add("Vlastná hodnota miesta priečnej vzdialenosti na osi X");    
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*22*/   SK.add("Xp =");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*23*/   SK.add("Zp =");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*24*/   SK.add("Automatická hodnota v kolma na miesto max priehybu");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*25*/   SK.add("Vlastná hodnota miesta priečnej vzdialenosti na osi Z");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*26*/   SK.add("X hodnota sek.závesného b.v LC");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*27*/   SK.add("Počet zväzkových vodičov");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*28*/   SK.add("Xp1");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*29*/   SK.add("Zp1");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*30*/   SK.add("Xp2");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*31*/   SK.add("Zp2");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*32*/   SK.add("polomer vodiča");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*33*/   SK.add("Napätie");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*34*/   SK.add("Prúd");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*35*/   SK.add("Uhol fázy");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*36*/   SK.add("C parameter reťazovky");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*37*/  SK.add("H ter ");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");        
/*38*/  SK.add("H");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*39*/   SK.add("H výška nad nulovým terénom ");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*40*/   SK.add("2H-ter výška nad terénom v mieste maximálneho priehybu reťazovky  Nemusí byt totožna s miestom maximálneho priblíženia vodiča");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*41*/   SK.add("Potrvdenie výpočtu lana");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*42*/   SK.add("počitaj");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*43*/   SK.add("X1-X2 = 0-A");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*44*/   SK.add("Hromadné kopírovanie");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*45*/   SK.add("Naozaj chcete zvolený stlpec skopírovat do všetkých riadkov ?");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*46*/   SK.add("odstránit riadky");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*47*/   SK.add("Vypočitaj retazovku");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");         
/*48*/   SK.add("Teren");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*49*/   SK.add("Teren nie je vytvorený. Vygenerovat?");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*49*/   SK.add("Hodnoty X1 alebo X2 su mimo vytvorenu plochu (teren).");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");   
                
         
      

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
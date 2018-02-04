/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InternalFrame;

import emft_vol2.TxT_JFrame;
import emft_vol2.constants;
import emft_vol2.main_class;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jozef
 */
public class outputPanel extends javax.swing.JPanel {

    /**
     * Creates new form calculationPanel
     */
    public outputPanel() {
        initComponents();
        jTextField1.setText(main_class.memory_path);
        JComboBox_Yvar.removeAllItems(); // odstran vsetko z combo boxu
        JComboBox_Yvar.addItem(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 11));
        JComboBox_Yvar.addItem(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 12));
        JComboBox_Yvar.addItem(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 13));
        JComboBox_Yvar.addItem(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 14));
        JComboBox_Yvar.addItem(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 15));
        JComboBox_Yvar.addItem(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 16));
        JComboBox_Yvar.addItem(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 17));
        JComboBox_Yvar.addItem(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 18));
        JComboBox_Yvar.addItem(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 19));
        JComboBox_Yvar.addItem(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 20));
        JComboBox_Yvar.addItem(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 21));
        JComboBox_Yvar.addItem(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 22));
        JComboBox_Yvar.addItem(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 23));
        JComboBox_Yvar.addItem(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 24));
        JComboBox_Yvar.addItem(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 25));
        JComboBox_Yvar.addItem(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 26));
    
    
       
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        TxT_short = new javax.swing.JCheckBox();
        TxT_long = new javax.swing.JCheckBox();
        Graph_screen = new javax.swing.JCheckBox();
        Graph_file = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        show = new javax.swing.JButton();
        Save_to_memory = new javax.swing.JButton();
        JComboBox_Yvar = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        SI_unit = new javax.swing.JRadioButton();
        update_Unit = new javax.swing.JRadioButton();
        conturry = new javax.swing.JRadioButton();
        fill = new javax.swing.JRadioButton();
        equal_sides = new javax.swing.JCheckBox();
        limit = new javax.swing.JCheckBox();

        setBorder(javax.swing.BorderFactory.createTitledBorder(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 4)
        ));

        TxT_short.setSelected(true);
        TxT_short.setText(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 0));

        TxT_long.setText(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 1));
        TxT_long.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxT_longActionPerformed(evt);
            }
        });

        Graph_screen.setSelected(true);
        Graph_screen.setText(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 2));
        Graph_screen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Graph_screenActionPerformed(evt);
            }
        });

        Graph_file.setText(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 3));
        Graph_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Graph_fileActionPerformed(evt);
            }
        });

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        show.setText(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 5));
        show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showActionPerformed(evt);
            }
        });

        Save_to_memory.setText(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 6));
        Save_to_memory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_to_memoryActionPerformed(evt);
            }
        });

        JComboBox_Yvar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        JComboBox_Yvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox_YvarActionPerformed(evt);
            }
        });

        jLabel1.setText(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 9));
        jLabel1.setToolTipText(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 10));

        buttonGroup1.add(SI_unit);
        SI_unit.setText("[ T ] & [ V/m]");
        SI_unit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SI_unitActionPerformed(evt);
            }
        });

        buttonGroup1.add(update_Unit);
        update_Unit.setSelected(true);
        update_Unit.setText("[ \u00B5 T ] / [ kV/m]");
        update_Unit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_UnitActionPerformed(evt);
            }
        });

        buttonGroup2.add(conturry);
        conturry.setText(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 27));
        conturry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conturryActionPerformed(evt);
            }
        });

        buttonGroup2.add(fill);
        fill.setSelected(true);
        fill.setText(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 28));
        fill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillActionPerformed(evt);
            }
        });

        equal_sides.setText(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 29));
        equal_sides.setToolTipText(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 30));

        limit.setSelected(true);
        limit.setText(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 31));
        limit.setToolTipText(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 32));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxT_short, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxT_long, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Graph_screen, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(limit))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Graph_file, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JComboBox_Yvar, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jTextField1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(show)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Save_to_memory))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SI_unit)
                            .addComponent(conturry))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fill)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(equal_sides))
                            .addComponent(update_Unit))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(conturry)
                        .addComponent(fill)
                        .addComponent(equal_sides)
                        .addComponent(limit))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxT_short)
                        .addComponent(Graph_screen)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxT_long)
                    .addComponent(Graph_file)
                    .addComponent(JComboBox_Yvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SI_unit)
                    .addComponent(update_Unit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(show, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(Save_to_memory, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Graph_screenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Graph_screenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Graph_screenActionPerformed

    public JCheckBox getGraph_file() {
        return Graph_file;
    }

    public JCheckBox getGraph_screen() {
        return Graph_screen;
    }

    public JTextField getjTextField1() {
        return jTextField1;
    }

    public void setjTextField1(JTextField jTextField1) {
        this.jTextField1 = jTextField1;
    }

    private void showActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showActionPerformed

        String userhome = System.getProperty("user.dir");
        JFileChooser chooser = new JFileChooser(userhome);
        chooser.setDialogTitle(language_internal_frame_output_Panel.LangLabel(constants.getLanguage_option(), 7));        
        
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);
        
        File f = chooser.getSelectedFile();
        jTextField1.setText(f.getPath());
        
    }//GEN-LAST:event_showActionPerformed

    private void Save_to_memoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_to_memoryActionPerformed
       
        String userhome = System.getProperty("user.dir");
        JFileChooser chooser = new JFileChooser(userhome );
        chooser.setDialogTitle(language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(), 14));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        jTextField1.setText(f.getPath());
        
        main_class.memory_path =jTextField1.getText();
        
        File subor = new File(userhome   + "\\memory.txt");
            try {
                PrintWriter fw = new PrintWriter(subor);
                fw.println("Memory file do not edit");
                fw.println(main_class.memory_path);
                 fw.println("END OF FILE");
                fw.close();
                
            } catch (FileNotFoundException ex) {

            }
    }//GEN-LAST:event_Save_to_memoryActionPerformed

    private void TxT_longActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxT_longActionPerformed
     if( TxT_JFrame.about_JframeIsOpen == false){
            String[] args = null;
            TxT_JFrame.main(args);
        }
    }//GEN-LAST:event_TxT_longActionPerformed

    private void Graph_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Graph_fileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Graph_fileActionPerformed

    public JCheckBox getEqual_sides() {
        return equal_sides;
    }

    public JComboBox<String> getJComboBox_Yvar() {
        return JComboBox_Yvar;
    }

    public void setJComboBox_Yvar(JComboBox<String> JComboBox_Yvar) {
        this.JComboBox_Yvar = JComboBox_Yvar;
    }

    private void JComboBox_YvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboBox_YvarActionPerformed
        if(JComboBox_Yvar.getSelectedIndex()==3 || JComboBox_Yvar.getSelectedIndex()==5 || JComboBox_Yvar.getSelectedIndex()==9 || JComboBox_Yvar.getSelectedIndex()==13 ){
            update_Unit.setEnabled(false);
            SI_unit.setEnabled(false);
            constants.setDislin_Dislin_Float_orEXP("Float");
            scaleUnit="angle";
            constants.setDislin_Label_B("$B [deg]$");
            constants.setDislin_Label_E("$E [deg]$");
            constants.setDislin_Label_Emod("$E_{mod} [deg]$");
            constants.setDislin_Label_I("$I [deg]$");
        }else{
            update_Unit.setEnabled(true);
            SI_unit.setEnabled(true);
           if(update_Unit.isSelected()==true){
               scaleUnit="true"; 
               constants.setDislin_Dislin_Float_orEXP("Float");
              
            constants.setDislin_Label_B("$B [\\mu T]$");
            constants.setDislin_Label_E("$E [kV/m]$");
            constants.setDislin_Label_Emod("$E_{mod} [kV/m]$");
            constants.setDislin_Label_I("$I [mA/m^2]$");
           }
            if(SI_unit.isSelected()==true){
               scaleUnit="false"; 
               constants.setDislin_Dislin_Float_orEXP("EXP");
               
            constants.setDislin_Label_B("$B [T]$");
            constants.setDislin_Label_E("$E [V/m]$");
            constants.setDislin_Label_Emod("$E_{mod} [V/m]$");
            constants.setDislin_Label_I("$I [A/m^2]$");
           }
            
        }
    }//GEN-LAST:event_JComboBox_YvarActionPerformed

    private void update_UnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_UnitActionPerformed
        if(update_Unit.isSelected()==true) scaleUnit="true"; 
        constants.setDislin_Dislin_Float_orEXP("Float");
          constants.setDislin_Label_B("$B [\\mu T]$");
            constants.setDislin_Label_E("$E [kV/m]$");
            constants.setDislin_Label_Emod("$E_{mod} [kV/m]$");
            constants.setDislin_Label_I("$I [mA/m^2]$");
    }//GEN-LAST:event_update_UnitActionPerformed

    private void SI_unitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SI_unitActionPerformed
         if(SI_unit.isSelected()==true) scaleUnit="false";
         
        constants.setDislin_Dislin_Float_orEXP("EXP");
        constants.setDislin_Label_B("$B [T]$");
            constants.setDislin_Label_E("$E [V/m]$");
            constants.setDislin_Label_Emod("$E_{mod} [V/m]$");
            constants.setDislin_Label_I("$I [A/m^2]$");
    }//GEN-LAST:event_SI_unitActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void conturryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conturryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_conturryActionPerformed
    
    public JCheckBox getLimit() {
        return limit;
    }

    public void setLimit(JCheckBox limit) {
        this.limit = limit;
    }

    private void fillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fillActionPerformed
    /**
     * vrat scale faktor pre B
     * @param scaleUnit nie Tesla Ano mu Tesla
     * @return 
     */
    public double BscaleFactor(){
        double val =1;
        if (scaleUnit=="false") val = 1;
        if (scaleUnit=="true") val = 1000000;
        if (scaleUnit=="angle") val = 180/Math.PI;
        return val;
    }
    /**
     * vrati hodnotu scale pre E
     * @param scaleUnit nie V/m Ano kV/m
     * @return 
     */
    public double EscaleFactor(){
        double val =1;
        if (scaleUnit=="false") val = 1;
        if (scaleUnit=="true") val = 0.001;
        if (scaleUnit=="angle") val = 180/Math.PI;
        return val;
    }

    public JRadioButton getConturry() {
        return conturry;
    }

    public JCheckBox getTxT_long() {
        return TxT_long;
    }

    public void setTxT_long(JCheckBox TxT_long) {
        this.TxT_long = TxT_long;
    }

    public JCheckBox getTxT_short() {
        return TxT_short;
    }

    public void setTxT_short(JCheckBox TxT_short) {
        this.TxT_short = TxT_short;
    }

    public JRadioButton getFill() {
        return fill;
    }
    
    public String YAxisVal(String VAR){
        String val ="";
        int poradie = JComboBox_Yvar.getSelectedIndex();
        if (poradie==0) val  = "RMS " +VAR;
        if (poradie==1) val  = "RMS " +VAR +" REAL";
        if (poradie==2) val  = "RMS " +VAR +" IMAGE";
        if (poradie==3) val  = "RMS " +VAR +" ANGLE";
        
        if (poradie==4) val  = "RMS " +VAR +" X";
        if (poradie==5) val  = "PHASE " +VAR +" X";
        if (poradie==6) val  = "REAL " +VAR +" X";
        if (poradie==7) val  = "IMAGE " +VAR +" X";
        
        if (poradie==8) val  = "RMS " +VAR +" Y";
        if (poradie==9) val  = "PHASE " +VAR +" Y";
        if (poradie==10) val = "REAL " +VAR +" Y";
        if (poradie==11) val = "IMAGE " +VAR +" Y";
        
        if (poradie==12) val = "RMS " +VAR +" Z";
        if (poradie==13) val = "PHASE " +VAR +" Z";
        if (poradie==14) val = "REAL " +VAR +" Z";
        if (poradie==15) val = "IMAGE " +VAR +" Z";
        
        return val;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Graph_file;
    private javax.swing.JCheckBox Graph_screen;
    private javax.swing.JComboBox<String> JComboBox_Yvar;
    private javax.swing.JRadioButton SI_unit;
    private javax.swing.JButton Save_to_memory;
    private javax.swing.JCheckBox TxT_long;
    private javax.swing.JCheckBox TxT_short;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton conturry;
    private javax.swing.JCheckBox equal_sides;
    private javax.swing.JRadioButton fill;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JCheckBox limit;
    private javax.swing.JButton show;
    private javax.swing.JRadioButton update_Unit;
    // End of variables declaration//GEN-END:variables
    private String scaleUnit = "true";

    
}


class language_internal_frame_output_Panel {

/**
 * Function just add elements to the array list 
 * If once function runs and sets variable "inicializovane" true it never runs again
 */    
static void constructor(){
              /*SK*/                /*CZ*/              /*EN*/   
 /*0*/   SK.add("TxT - krátky");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position 
/*1*/    SK.add("TxT - dlhný");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*2*/    SK.add("Grafický - plocha");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*3*/    SK.add("Grafický - do súboru");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*4*/    SK.add("Výstup");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*5*/    SK.add("Uloz do");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*6*/    SK.add("Uloz cestu");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");    
/*7*/    SK.add("Zvol priečinok pre vystup");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*8*/    SK.add("Zvol pričinok pre vystup a ulož cesu do memory");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");         
/*9*/    SK.add("Zobraz veličinu Y-os");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*10*/   SK.add("Zobraz veličinu na Y osi v grafickom vystupe");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
         
         
/*11*/   SK.add("RMS xyz");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*12*/   SK.add("RMS xyz Re");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*13*/   SK.add("RMS xyz Im");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*13*/   SK.add("RMS xyz \u03C6");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         
/*14*/   SK.add("x Abs");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*17*/   SK.add("x \u03C6");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");     
/*15*/   SK.add("x Re");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*16*/   SK.add("x Im");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
   
/*18*/   SK.add("y Abs");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*21*/   SK.add("y \u03C6");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");    
/*19*/   SK.add("y Re");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*20*/   SK.add("y Im");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 

/*22*/   SK.add("z Abs");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*25*/   SK.add("z \u03C6");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");          
/*23*/   SK.add("z Re");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*24*/   SK.add("z Im");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 

/*27*/   SK.add("contury");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");  

/*28*/   SK.add("plne");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");           
/*29*/   SK.add("auto pomer ");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");  

/*30*/   SK.add("Pomer X Y osi v 2D graf výstupe. Ak Off pomer 1:1. ");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");  
/*31*/   SK.add("Limit");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");  

/*32*/   SK.add("Zobraz  v grafe limit pre počitanu veličinu");  
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


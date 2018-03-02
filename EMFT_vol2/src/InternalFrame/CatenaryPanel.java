/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InternalFrame;

import BackEnd.ColorColumnRenderer;
import BackEnd.MyCellEditor;
import BackEnd.retazovka;
import BackEnd.rozpatie;
import static InternalFrame.BasicInfoPanel.jTextField_mano;
import static InternalFrame.terenmodel_jDialog.kontrolor;
import emft_vol2.constants;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.T;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.jdelaunay.delaunay.error.DelaunayError;
import tools.help;

/**
 *
 * @author Jozef
 */
public class CatenaryPanel extends javax.swing.JPanel {

    /**
     * Creates new form BasicInfoPanel
     */
    public CatenaryPanel()  {
        initComponents();
         Locale.setDefault(new Locale("en", "US"));
        setToolTipsForColumnHeader();
        T1.setText(String.valueOf(InternalFrameproject.Rozpätie.getV1base()));
        T2.setText(String.valueOf(InternalFrameproject.Rozpätie.getV1base()));
        Table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE); // confirm Table of lost of focus
        this.DTMTable =(DefaultTableModel) Table.getModel();
    
        nacitaj_dat_lan();
       
        // nahod vodiče do combdodoxu
        JComboBox jComboBox_conductor = new JComboBox();
         jComboBox_conductor.removeAllItems();
        for (int i = 0; i < conductor_Name_Matrix.size(); i++) {
        jComboBox_conductor.addItem(conductor_Name_Matrix.get(i));    
        }
        
        
        Table.getColumnModel().getColumn(22).setCellEditor(new DefaultCellEditor(jComboBox_conductor));

        
        for (int cl1 = 0; cl1<DTMTable.getColumnCount() ;cl1 ++){
          if(cl1!= 11 && cl1!= 20 && cl1!= 21 && cl1!= 22){  
             TableColumn col = Table.getColumnModel().getColumn(cl1); // default cell editor for one columnt
        col.setCellEditor( new MyCellEditor());
         if(cl1== 17 || cl1== 18 || cl1== 19){
        col.setCellRenderer(new ColorColumnRenderer(Color.lightGray, Color.blue));
         }
         
          }
          
          if(cl1== 21 ){
             //  TableColumn col = Table.getColumnModel().getColumn(cl1);
             //  col.setCellRenderer(new ColorColumnRenderer(Color.orange, Color.blue));
         }
          
          TableColumn col_past = Table.getColumnModel().getColumn(cl1);
        col_past.setHeaderRenderer(new ColorColumnRenderer(Color.lightGray, Color.black)); 
          
        }



        
    rozpatie rozpatie = InternalFrameproject.Rozpätie;
        
   if(rozpatie.getRetazovkaList().size() == 0){
        DTMTable.addRow(new Object[0]);
    }else{
        for(int cl1=0; cl1<rozpatie.getRetazovkaList().size();cl1++){
            
        
            DTMTable.addRow(new Object[] { rozpatie.getRetazovka(cl1).getV1_over(),
                                           rozpatie.getRetazovka(cl1).getV2_over(),
                                           rozpatie.getRetazovka(cl1).getI1_over(),
                                           rozpatie.getRetazovka(cl1).getI2_over(),
                                           rozpatie.getRetazovka(cl1).getW1_over(),
                                           rozpatie.getRetazovka(cl1).getW2_over(),
                                           rozpatie.getRetazovka(cl1).getX1_over(),
                                           rozpatie.getRetazovka(cl1).getX2_over(),
                                           
                                           rozpatie.getRetazovka(cl1).getBundle_over(),
                                           rozpatie.getRetazovka(cl1).getAlpha_over(),
                                           rozpatie.getRetazovka(cl1).getDistance_over(),
                                           
                                           rozpatie.getRetazovka(cl1).isCorH_over(),
                                           rozpatie.getRetazovka(cl1).getHC_over(),
                                           
                                           rozpatie.getRetazovka(cl1).getR_over(),
                                           rozpatie.getRetazovka(cl1).getU_over(),
                                           rozpatie.getRetazovka(cl1).getI_over(),
                                           rozpatie.getRetazovka(cl1).getPhi_over(),
                                           
                                           rozpatie.getRetazovka(cl1).getC_over(),
                                           rozpatie.getRetazovka(cl1).getH_over(),
                                           rozpatie.getRetazovka(cl1).getHter_over(),                            
            });    
        }
        DTMTable.addRow(new Object[0]);        
    }
      
   
   //listener meni čiarky bodky kontroluje
    Table.getModel().addTableModelListener(new TableModelListener() {

    @Override
    public void tableChanged(TableModelEvent e) {
        
    if(isListener == true){
    isCatenarydatachanged=true; // zmenili sa udaje v tabulke    
    if(e.getColumn()!=11 && e.getColumn()!=20 && e.getColumn()!=21 && e.getColumn()!=22){ // podmienka pre checkbox riadky    
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
                       Table.setValueAt(language_internal_fJdialog_terenModel.LangLabel(constants.getLanguage_option(), 6),Table.getEditingRow(), Table.getEditingColumn());
                     
                        kontrolor.set(4, -1.);
                       } 
         
                     
                  
                   
                      
          }catch(ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException p){}
        
                                if (Table.getEditingRow() == Table.getRowCount() - 1) { // ak klinuty posledny riadok tak   
                                DTMTable.addRow(new Object[0]);
                                }
                                Table.getModel().addTableModelListener(this);
                                
        
                }
    
    }

    }});
    
    // nastavenie headrra Table
    JTableHeader header = Table.getTableHeader(); // listener na each header cell
    header.addMouseListener(new TableHeaderMouseListener(Table));
    
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        deffX = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        calculatecatenary = new javax.swing.JButton();
        base = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        T1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        T2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 0)
            , javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.BELOW_TOP));

    Table.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.String.class
        };
        boolean[] canEdit = new boolean [] {
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, true, true, true
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    Table.setRowHeight(24);
    Table.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
        public void mouseMoved(java.awt.event.MouseEvent evt) {
            TableMouseMoved(evt);
        }
    });
    Table.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            TableKeyReleased(evt);
        }
    });
    jScrollPane1.setViewportView(Table);
    if (Table.getColumnModel().getColumnCount() > 0) {
        Table.getColumnModel().getColumn(0).setResizable(false);
        Table.getColumnModel().getColumn(0).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 1)
        );
        Table.getColumnModel().getColumn(1).setResizable(false);
        Table.getColumnModel().getColumn(1).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 2)
        );
        Table.getColumnModel().getColumn(2).setResizable(false);
        Table.getColumnModel().getColumn(2).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 3)
        );
        Table.getColumnModel().getColumn(3).setResizable(false);
        Table.getColumnModel().getColumn(3).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 4)
        );
        Table.getColumnModel().getColumn(4).setResizable(false);
        Table.getColumnModel().getColumn(4).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 5)
        );
        Table.getColumnModel().getColumn(5).setResizable(false);
        Table.getColumnModel().getColumn(5).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 6)
        );
        Table.getColumnModel().getColumn(6).setResizable(false);
        Table.getColumnModel().getColumn(6).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 7)
        );
        Table.getColumnModel().getColumn(7).setResizable(false);
        Table.getColumnModel().getColumn(7).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 8)
        );
        Table.getColumnModel().getColumn(8).setResizable(false);
        Table.getColumnModel().getColumn(8).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 9)
        );
        Table.getColumnModel().getColumn(9).setResizable(false);
        Table.getColumnModel().getColumn(9).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 10)
        );
        Table.getColumnModel().getColumn(10).setResizable(false);
        Table.getColumnModel().getColumn(10).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 11)
        );
        Table.getColumnModel().getColumn(11).setResizable(false);
        Table.getColumnModel().getColumn(11).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 12)
        );
        Table.getColumnModel().getColumn(12).setResizable(false);
        Table.getColumnModel().getColumn(12).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 13)
        );
        Table.getColumnModel().getColumn(13).setResizable(false);
        Table.getColumnModel().getColumn(13).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 14)
        );
        Table.getColumnModel().getColumn(14).setResizable(false);
        Table.getColumnModel().getColumn(14).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 15)
        );
        Table.getColumnModel().getColumn(15).setResizable(false);
        Table.getColumnModel().getColumn(15).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 16)
        );
        Table.getColumnModel().getColumn(16).setResizable(false);
        Table.getColumnModel().getColumn(16).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 17)
        );
        Table.getColumnModel().getColumn(17).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 18)
        );
        Table.getColumnModel().getColumn(18).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 38)
        );
        Table.getColumnModel().getColumn(19).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 37)
        );
        Table.getColumnModel().getColumn(20).setResizable(false);
        Table.getColumnModel().getColumn(20).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 42)
        );
        Table.getColumnModel().getColumn(21).setResizable(false);
        Table.getColumnModel().getColumn(21).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 51));
        Table.getColumnModel().getColumn(22).setResizable(false);
        Table.getColumnModel().getColumn(22).setPreferredWidth(200);
        Table.getColumnModel().getColumn(22).setHeaderValue(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 57));
        Table.getColumnModel().getColumn(22).setCellEditor(null);
    }

    deffX.setText(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 43));
    deffX.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            deffXActionPerformed(evt);
        }
    });

    delete.setText(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 46));
    delete.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            deleteActionPerformed(evt);
        }
    });

    calculatecatenary.setText(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 47));
    calculatecatenary.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            calculatecatenaryActionPerformed(evt);
        }
    });

    base.setText(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 53));
    base.setToolTipText(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 54));
    base.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            baseActionPerformed(evt);
        }
    });

    jLabel1.setText("T1 =");
    jLabel1.setToolTipText(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 55));

    T1.setText("jTextField1");
    T1.setToolTipText(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 55));
    T1.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            T1KeyReleased(evt);
        }
    });

    jLabel2.setText("m");

    jLabel3.setText("T2 =");
    jLabel3.setToolTipText(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 56));

    T2.setText("jTextField1");
    T2.setToolTipText(language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 55));
    T2.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            T2KeyReleased(evt);
        }
    });

    jLabel4.setText("m");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(deffX, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(calculatecatenary, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(base, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(T1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(T2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel4)
                    .addGap(0, 251, Short.MAX_VALUE)))
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(calculatecatenary, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(base, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(deffX, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(T2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(T1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addContainerGap())
    );
    }// </editor-fold>//GEN-END:initComponents

    private void TableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableKeyReleased

    }//GEN-LAST:event_TableKeyReleased

    private void TableMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseMoved
       
       
        
        
    }//GEN-LAST:event_TableMouseMoved

    private void deffXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deffXActionPerformed
      isListener = false;
        for (int c = 0; c < Table.getRowCount()-1; c++) {
        
            Table.setValueAt(0, c, 6);
            Table.setValueAt(InternalFrameproject.Rozpätie.getA(), c, 7);
        
        }
      isListener = true;     
    }//GEN-LAST:event_deffXActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        isListener = false;
        int[] rows = Table.getSelectedRows();
        if (rows.length>1 ) {
       for(int i=0;i<rows.length;i++){
     DTMTable.removeRow(rows[i]-i);
     
       }}
     isListener = true;   
       
    }//GEN-LAST:event_deleteActionPerformed

    private void calculatecatenaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculatecatenaryActionPerformed
       isListener = false;
        boolean idemePocitat = false; // boolena ci sa ide vobec pocitat catenary
        boolean isWringValue=false;
        int inkrement_poctu_zleho_zadaniaX1X2=0;
        //kontrola či je vygenerovany teren ak ano ideme dalej
        if (InternalFrameproject.Rozpätie.isCreatedTerrain() == true) {
            idemePocitat = true;
            
            

        } else {

            int hodnotaYN = JOptionPane.showConfirmDialog(CatenaryPanel.this, language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 49), language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 48), 2);//showMessageDialog(table, "Column header #" + column + " is clicked");

            if (hodnotaYN == JOptionPane.YES_OPTION) {
                BasicSettingsPanel.generuj_teren_bez_ukazky();
                idemePocitat = true;

            }

        }

        // ak je vygenerovany teren ideme načitat retazovky a vypočitat ich parametre a zobrazot ich do okienok pracujeme len s tými ktoré su zaškrtnuté
        if(idemePocitat==true){
        // vymarat všetky ktore su vytvorene
        InternalFrameproject.Rozpätie.erazeRetazovkaArrayList();
        // vytvor novu arraylist retazovky
        ArrayList<retazovka> Retazovka_zoznam = new ArrayList<>();    
        
        int rows = Table.getRowCount();
        for(int i=0;i<rows-1;i++){ 
            
          
            boolean  isChecked = Boolean.valueOf(String.valueOf(Table.getValueAt(i, 20))); 
        
    
            if(isChecked){ // iba ak je zasškrtnute tak vklada lana
            
            try {
        
            retazovka lano = new  retazovka(help.Object_To_double(Table.getValueAt(i, 0)), // V1
                    help.Object_To_double(Table.getValueAt(i, 1)), // V2
                    help.Object_To_double(Table.getValueAt(i, 2)), // I1
                    help.Object_To_double(Table.getValueAt(i, 3)), // I2
                    help.Object_To_double(Table.getValueAt(i, 4)), // W1
                    help.Object_To_double(Table.getValueAt(i, 5)), // W2
                    help.Object_To_double(Table.getValueAt(i, 6)), // X1
                    help.Object_To_double(Table.getValueAt(i, 7)), // X2
           (int) help.Object_To_double(Table.getValueAt(i, 8)), // bundle
                    help.Object_To_double(Table.getValueAt(i, 9)), // alpha
                    help.Object_To_double(Table.getValueAt(i, 10)), // distance
                    help.Object_To_double(Table.getValueAt(i, 12)), // H/C value
           Boolean.valueOf(String.valueOf(Table.getValueAt(i, 11))), // true/false
                    help.Object_To_double(Table.getValueAt(i, 13)), // polomer
                    help.Object_To_double(Table.getValueAt(i, 14)), // U
                    help.Object_To_double(Table.getValueAt(i, 15)), // I
                    help.Object_To_double(Table.getValueAt(i, 16)), // Phi
                    InternalFrameproject.Rozpätie.getTeren(),
                    InternalFrameproject.Rozpätie.isIsV1V2base(),
                    InternalFrameproject.Rozpätie.getV1base(),
                    InternalFrameproject.Rozpätie.getV2base());
            // sleduje mozne chybne zadania X1X2 vzhladom na teren
            if(help.Object_To_double(Table.getValueAt(i, 6))<0 || help.Object_To_double(Table.getValueAt(i, 7))> InternalFrameproject.Rozpätie.getA()){
                inkrement_poctu_zleho_zadaniaX1X2++;
            }
            
            Table.setValueAt(lano.getC_over(), i, 17);
            Table.setValueAt(lano.getH_over()+lano.getZY_cor_bundle_lowest_conductor(), i, 18); // odpocitaj od vysky stredu vysku spodneho vodiča vo zvazku
            Table.setValueAt(lano.getHter_over()+lano.getZY_cor_bundle_lowest_conductor(), i, 19);
           
             
         
            
            Retazovka_zoznam.add(lano);
            
            } catch (DelaunayError ex) {
                isListener = true;  
                Logger.getLogger(CatenaryPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
     
            } 
         
        }
           InternalFrameproject.Rozpätie.setRetazovkaArrayList(Retazovka_zoznam);   // vlozi zoznam retazoviem s prepocitanimi parametrami do retazovky rozpatia
           
           if(inkrement_poctu_zleho_zadaniaX1X2>0){
               help.warning1row( language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 50));
           }
        }
        
       isListener = true;   
       isCatenarydatachanged=false;
       
       
       
       
        
    }//GEN-LAST:event_calculatecatenaryActionPerformed

    private void baseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baseActionPerformed
       if(base.isSelected()== true) InternalFrameproject.Rozpätie.setIsV1V2base(true);
       if(base.isSelected()== false) InternalFrameproject.Rozpätie.setIsV1V2base(false);
    }//GEN-LAST:event_baseActionPerformed

    private void T1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T1KeyReleased
       InternalFrameproject.Rozpätie.setV1base(help.ReadCheckDouble(T1, 0));
    }//GEN-LAST:event_T1KeyReleased

    private void T2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T2KeyReleased
        InternalFrameproject.Rozpätie.setV2base(help.ReadCheckDouble(T2, 0));
    }//GEN-LAST:event_T2KeyReleased

     

    void calculatecatenary() {
        calculatecatenary.doClick();
    }

    /**
     * nacita datazabu lan predtzm premaže staru
     */
    void nacitaj_dat_lan(){
        this.conductor_Name_Matrix.clear();
        this.conductor_parameters_Matrix.clear();
        String userhome = System.getProperty("user.dir");
        File subor = new File(userhome   + "\\conductors.txt");
        Scanner input;
        try {
            input = new Scanner(subor);
            input.nextLine();
            int pocet_vod_v_databaze = input.nextInt();
            input.nextLine();
            //cyklus nacitavania do databazy
            for (int i = 0; i < pocet_vod_v_databaze; i++) {
                this.conductor_Name_Matrix.add( input.nextLine());
                
                Double[] parametre = new Double[8];
                parametre[0] = input.nextDouble();
                parametre[1] = input.nextDouble();
                parametre[2] = input.nextDouble();
                parametre[3] = input.nextDouble();
                parametre[4] = input.nextDouble();
                parametre[5] = input.nextDouble();
                parametre[6] = input.nextDouble();
                parametre[7] = input.nextDouble();
                input.nextLine();
                this.conductor_parameters_Matrix.add(parametre);
            }
            input.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CatenaryPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    /**
     * nahodi parametre do vodiča
     * poradie j pre subor conductor.txt kde su ulozene parametre
     * setElpam_D
       setElpam_T;          
       setElpam_rho_cnd;
       setElpam_rho_gnd;
       setElpam_Rdc;
       setElpam_Al_layers;
       setElpam_Al_start;
       setElpam_Al_d;
     */
    public void add_parametre_to_conductor(){
        
        int rows = Table.getRowCount();
        for(int i=0;i<rows-1;i++){ 
        
        //tu musi ist niečo čo najde 
        String porovnavacka = String.valueOf(DTMTable.getValueAt(i, 22));
        int index = 0;
            for (int j = 0; j < conductor_Name_Matrix.size(); j++) {
                
                if(porovnavacka.equals(conductor_Name_Matrix.get(j))) index = j;
            }
            
            
         boolean  isChecked = Boolean.valueOf(String.valueOf(Table.getValueAt(i, 20))); 
        
    
            if(isChecked){    
            
        InternalFrameproject.Rozpätie.getRetazovkaList().get(i).setElpam_f(constants.getFrequency());   
         InternalFrameproject.Rozpätie.getRetazovkaList().get(i).setElpam_D(conductor_parameters_Matrix.get(index)[0]);         //je
         InternalFrameproject.Rozpätie.getRetazovkaList().get(i).setElpam_T(conductor_parameters_Matrix.get(index)[1]);          
         InternalFrameproject.Rozpätie.getRetazovkaList().get(i).setElpam_rho_cnd(conductor_parameters_Matrix.get(index)[2]);
              InternalFrameproject.Rozpätie.getRetazovkaList().get(i).setElpam_rho_gnd(conductor_parameters_Matrix.get(index)[3]);
              InternalFrameproject.Rozpätie.getRetazovkaList().get(i).setElpam_Rdc(conductor_parameters_Matrix.get(index)[4]);
                InternalFrameproject.Rozpätie.getRetazovkaList().get(i).setElpam_Al_layers( conductor_parameters_Matrix.get(index)[5].intValue() );
               InternalFrameproject.Rozpätie.getRetazovkaList().get(i).setElpam_Al_start(conductor_parameters_Matrix.get(index)[6].intValue() );
               InternalFrameproject.Rozpätie.getRetazovkaList().get(i).setElpam_Al_d(conductor_parameters_Matrix.get(index)[7]);
                
            }   
        }
        
    }

//diferent tool tip for every calumn https://coderanch.com/t/336281/java/Adding-Tooltip-JTable-header
    class ColumnHeaderToolTips extends MouseMotionAdapter {
  // Current column whose tooltip is being displayed.
  // This variable is used to minimize the calls to setToolTipText().
  TableColumn curCol;
  // Maps TableColumn objects to tooltips
  Map tips = new HashMap();
  // If tooltip is null, removes any tooltip text.
  public void setToolTip(TableColumn col, String tooltip) {
    if (tooltip == null) {
      tips.remove(col);
    }
    else {
      tips.put(col, tooltip);
    }
  }
  public void mouseMoved(MouseEvent evt) {
    TableColumn col = null;
    JTableHeader header = (JTableHeader) evt.getSource();
    JTable Table = header.getTable();
    TableColumnModel colModel = Table.getColumnModel();
    int vColIndex = colModel.getColumnIndexAtX(evt.getX());
    // Return if not clicked on any column header
    if (vColIndex >= 0) {
      col = colModel.getColumn(vColIndex);
    }
    if (col != curCol) {
      header.setToolTipText( (String) tips.get(col));
      curCol = col;
    }
  }
}
    // is called from constructor
    public void setToolTipsForColumnHeader() {
    ColumnHeaderToolTips tips = new ColumnHeaderToolTips();
    JTableHeader header = Table.getTableHeader();
    // Assign a tooltip for each of need  columns
    for (int c = 0; c < Table.getColumnCount(); c++) {
      TableColumn col = Table.getColumnModel().getColumn(c);
        if(c== 0){

            tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 19));

        }
        if(c== 1){

            tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 20));

        }
        if(c== 2){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 21));

        }
        if(c== 3){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 22));

        }
        if(c== 4){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 23));

        }
        if(c== 5){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 24));

        }
        if(c== 6){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 25));

        }
        if(c== 7){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 26));

        }
        if(c== 8){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 27));

        }
        if(c== 9){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 28));

        }
        if(c== 10){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 29));

        }
        if(c== 11){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 30));

        }
        if(c== 12){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 31));

        }
        if(c== 13){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 32));

        }
        if(c== 14){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 33));

        }
        if(c== 15){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 34));

        }
        if(c== 16){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 35));

        }
        if(c== 17){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 36));

        }
         if(c== 18){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 39));

        }
          if(c== 19){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 40));

        }
           if(c== 20){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 41));

        }
           if(c== 21){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 52));

        }
           if(c== 22){

             tips.setToolTip(col,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(), 58));

        }
      
      
      
      
    }
    header.addMouseMotionListener(tips);
  }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField T1;
    public javax.swing.JTextField T2;
    static javax.swing.JTable Table;
    public javax.swing.JCheckBox base;
    public javax.swing.JButton calculatecatenary;
    public javax.swing.JButton deffX;
    public javax.swing.JButton delete;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
 DefaultTableModel DTMTable ;
  static boolean isListener = true;
  static boolean isCatenarydatachanged = false;
  private ArrayList<String> conductor_Name_Matrix = new ArrayList<String>();
  

    public ArrayList<String> getConductor_Name_Matrix() {
        return conductor_Name_Matrix;
    }

    public void setConductor_Name_Matrix(ArrayList<String> conductor_Name_Matrix) {
        this.conductor_Name_Matrix = conductor_Name_Matrix;
    }

    public ArrayList<Double[]> getConductor_parameters_Matrix() {
        return conductor_parameters_Matrix;
    }

    public void setConductor_parameters_Matrix(ArrayList<Double[]> conductor_parameters_Matrix) {
        this.conductor_parameters_Matrix = conductor_parameters_Matrix;
    }
  private ArrayList<Double[]> conductor_parameters_Matrix = new ArrayList<Double[]>();
}

class language_internal_frame_catenary_Panel {

/**
 * Function just add elements to the array list 
 * If once function runs and sets variable "inicializovane" true it never runs again
 */    
static void constructor(){
              /*SK*/                /*CZ*/              /*EN*/   
 /*0*/   SK.add("Zoznam a parametre lán");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position 
/*1*/    SK.add("V1");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*2*/    SK.add("V2");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*3*/    SK.add("I1");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*4*/    SK.add("I2");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*5*/    SK.add("W1");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*6*/    SK.add("W2");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");    
/*7*/    SK.add("X1");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*8*/    SK.add("X2");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
         
/*9*/    SK.add("Zväzok");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*10*/   SK.add("Alpha");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*11*/   SK.add("d");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*12*/   SK.add("c/H");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*13*/   SK.add("Val");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*14*/   SK.add("r");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*15*/   SK.add("U");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*16*/   SK.add("I");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*17*/   SK.add("Phi");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
/*18*/   SK.add("C");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*19*/   SK.add("výška Y prim. závesného b.");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
         /*20*/   SK.add("výška Y sek. závesného b.");   
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
         /*21*/   SK.add("dlžka izolátora prim. závesného b.");    
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
         /*22*/   SK.add("dlžka izolátora sek. závesného b.");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
         /*23*/   SK.add("Z hodnota prim.závesného b. v LC");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*24*/   SK.add("Z hodnota sek. závesného b. v LC");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*25*/   SK.add("X hodnota prim. závesného b. v LC");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*26*/   SK.add("X hodnota sek.závesného b.v LC");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*27*/   SK.add("Počet zväzkových vodičov");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*28*/   SK.add("Otočenia zväzku");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*29*/   SK.add("krok zväzku vzdialenost medzi dvoma vodičmi");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*30*/   SK.add("True = c | False = H ");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*31*/   SK.add("c parameter reťazovky alebo H výška nad nulovým terénom");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*32*/   SK.add("polomer vodiča");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
         /*33*/   SK.add("3f združene napätie");  
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
/*50*/   SK.add("Hodnoty X1 alebo X2 su mimo vytvorenu plochu (teren).");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");   
/*51*/   SK.add("Par");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                   
/*52*/   SK.add("Parametrizuj dané lano");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*53*/   SK.add("Base stožiara.");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*54*/   SK.add("AK nie je zaškrtnute absolutna výška každeho závesneho bodu V1 a V2  = výška lana V1 + výška terenu pod závesným bodom");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*55*/   SK.add("Výška stožiara 1 nad absolutnou nulou.  ");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*56*/   SK.add("Výška stožiara 1 nad absolutnou nulou.  ");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");          
/*57*/   SK.add("vodič ");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");          
/*58*/   SK.add("Vodič potrebne pre vypočet parametrov");  
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


    class TableHeaderMouseListener extends MouseAdapter {
     
    private JTable table;
     
    public TableHeaderMouseListener(JTable table) {
        this.table = table;
    }
     
    public void mouseClicked(MouseEvent event) {
        Point point = event.getPoint();
        int column = table.columnAtPoint(point);
        int hodnotaYN = 0; 
        hodnotaYN = JOptionPane.showConfirmDialog(table,language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(),45 ) , language_internal_frame_catenary_Panel.LangLabel(constants.getLanguage_option(),44 ), 2);//showMessageDialog(table, "Column header #" + column + " is clicked");
         CatenaryPanel.isListener = false;
        if  (hodnotaYN == JOptionPane.YES_OPTION){
            
            for (int c = 0; c < table.getRowCount()-1; c++) {
        
            table.setValueAt(table.getValueAt(0, column), c, column);
            
        
        }
            
        }
        CatenaryPanel.isListener = true;
        // do your real thing here...
    }
}


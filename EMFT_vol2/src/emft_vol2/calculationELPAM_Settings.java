/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emft_vol2;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBox;
import tools.help;

/**
 *
 * @author Jozef
 */
public class calculationELPAM_Settings extends javax.swing.JFrame {

    /**
     * Creates new form about_JFrame
     */
    public calculationELPAM_Settings() {
        initComponents();
        seticon();
        setLocationRelativeTo(null);
        about_JframeIsOpen=true;
        
         addWindowListener(new WindowAdapter() // listener na krizik zabretie okna
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                about_JframeIsOpen= false;
                e.getWindow().dispose();
            }
        });
        
    }
     private void seticon() {
     setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/graphics/" + "icon.png")));
    }
    

    public static int getmetoda(){
        
        int metoda = 1;
        if (ELPAM_met1.isSelected()) metoda =1;
        if (ELPAM_met2.isSelected()) metoda =2;
        if (ELPAM_met3.isSelected()) metoda =3;
        if (ELPAM_met4.isSelected()) metoda =4;
        if (ELPAM_met5.isSelected()) metoda =5;
        if (ELPAM_met6.isSelected()) metoda =6;
        if (ELPAM_met7.isSelected()) metoda =7;
        return metoda   ;
        
    }

    public static boolean getzvazky(){
        boolean outut =false;
        if(zvazky.isSelected()) outut =true;
        return outut;
    }
    
    public static boolean getEXGMR(){
        boolean outut =false;
        if(EXACTGMR.isSelected()) outut =true;
        return outut;
    }
    
    public static boolean getEXRAC(){
        boolean outut =false;
        if(EXACTRAC.isSelected()) outut =true;
        return outut;
    }
    
    public JCheckBox getEXACTGMR() {
        return EXACTGMR;
    }

    public void setEXACTGMR(JCheckBox EXACTGMR) {
        this.EXACTGMR = EXACTGMR;
    }

    public JCheckBox getEXACTRAC() {
        return EXACTRAC;
    }

    public void setEXACTRAC(JCheckBox EXACTRAC) {
        this.EXACTRAC = EXACTRAC;
    }

    public JCheckBox getZvazky() {
        return zvazky;
    }

    public void setZvazky(JCheckBox zvazky) {
        this.zvazky = zvazky;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ELPAM_met1 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        ELPAM_met2 = new javax.swing.JCheckBox();
        ELPAM_met3 = new javax.swing.JCheckBox();
        ELPAM_met4 = new javax.swing.JCheckBox();
        ELPAM_met6 = new javax.swing.JCheckBox();
        ELPAM_met5 = new javax.swing.JCheckBox();
        ELPAM_met7 = new javax.swing.JCheckBox();
        zvazky = new javax.swing.JCheckBox();
        EXACTGMR = new javax.swing.JCheckBox();
        EXACTRAC = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        if(constants.getLanguage_option() ==1){
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphics/header_nastaveniaV_SK.png")));
        }
        if(constants.getLanguage_option() ==3){
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphics/header_nastaveniaV_EN.png"))); // NOI18N
        }
        if(constants.getLanguage_option() ==2){
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphics/header_nastaveniaV_CZ.png"))); // NOI18N
        }

        jLabel2.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 73));

        buttonGroup1.add(ELPAM_met1);
        ELPAM_met1.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 74));
        ELPAM_met1.setToolTipText(language_main_frame.LangLabel(constants.getLanguage_option(), 75));
        ELPAM_met1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ELPAM_met1ActionPerformed(evt);
            }
        });

        jLabel3.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 72));

        buttonGroup1.add(ELPAM_met2);
        ELPAM_met2.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 76));
        ELPAM_met2.setToolTipText(language_main_frame.LangLabel(constants.getLanguage_option(), 77));
        ELPAM_met2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ELPAM_met2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(ELPAM_met3);
        ELPAM_met3.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 78));
        ELPAM_met3.setToolTipText(language_main_frame.LangLabel(constants.getLanguage_option(), 79));
        ELPAM_met3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ELPAM_met3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(ELPAM_met4);
        ELPAM_met4.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 80));
        ELPAM_met4.setToolTipText(language_main_frame.LangLabel(constants.getLanguage_option(), 81));
        ELPAM_met4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ELPAM_met4ActionPerformed(evt);
            }
        });

        buttonGroup1.add(ELPAM_met6);
        ELPAM_met6.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 84));
        ELPAM_met6.setToolTipText(language_main_frame.LangLabel(constants.getLanguage_option(), 85));
        ELPAM_met6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ELPAM_met6ActionPerformed(evt);
            }
        });

        buttonGroup1.add(ELPAM_met5);
        ELPAM_met5.setSelected(true);
        ELPAM_met5.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 82));
        ELPAM_met5.setToolTipText(language_main_frame.LangLabel(constants.getLanguage_option(), 83));
        ELPAM_met5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ELPAM_met5ActionPerformed(evt);
            }
        });

        buttonGroup1.add(ELPAM_met7);
        ELPAM_met7.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 86));
        ELPAM_met7.setToolTipText(language_main_frame.LangLabel(constants.getLanguage_option(), 87));
        ELPAM_met7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ELPAM_met7ActionPerformed(evt);
            }
        });

        zvazky.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 88));
        zvazky.setToolTipText(language_main_frame.LangLabel(constants.getLanguage_option(), 89));

        EXACTGMR.setSelected(true);
        EXACTGMR.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 90));
        EXACTGMR.setToolTipText(language_main_frame.LangLabel(constants.getLanguage_option(), 91));

        EXACTRAC.setSelected(true);
        EXACTRAC.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 92));
        EXACTRAC.setToolTipText(language_main_frame.LangLabel(constants.getLanguage_option(), 93));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ELPAM_met4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ELPAM_met3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ELPAM_met1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(ELPAM_met2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(zvazky)
                            .addComponent(EXACTGMR)
                            .addComponent(EXACTRAC))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ELPAM_met7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(ELPAM_met6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ELPAM_met5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ELPAM_met1)
                            .addComponent(zvazky))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ELPAM_met2)
                            .addComponent(EXACTGMR)))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ELPAM_met3)
                    .addComponent(EXACTRAC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ELPAM_met4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ELPAM_met5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ELPAM_met6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ELPAM_met7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ELPAM_met1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ELPAM_met1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ELPAM_met1ActionPerformed

    private void ELPAM_met2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ELPAM_met2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ELPAM_met2ActionPerformed

    private void ELPAM_met3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ELPAM_met3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ELPAM_met3ActionPerformed

    private void ELPAM_met4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ELPAM_met4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ELPAM_met4ActionPerformed

    private void ELPAM_met6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ELPAM_met6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ELPAM_met6ActionPerformed

    private void ELPAM_met5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ELPAM_met5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ELPAM_met5ActionPerformed

    private void ELPAM_met7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ELPAM_met7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ELPAM_met7ActionPerformed

   

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
            java.util.logging.Logger.getLogger(calculationELPAM_Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(calculationELPAM_Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(calculationELPAM_Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(calculationELPAM_Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
 
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new calculationELPAM_Settings().setVisible(true);
            }
        });
         
    }

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JCheckBox ELPAM_met1;
    private static javax.swing.JCheckBox ELPAM_met2;
    private static javax.swing.JCheckBox ELPAM_met3;
    private static javax.swing.JCheckBox ELPAM_met4;
    private static javax.swing.JCheckBox ELPAM_met5;
    private static javax.swing.JCheckBox ELPAM_met6;
    private static javax.swing.JCheckBox ELPAM_met7;
    private static javax.swing.JCheckBox EXACTGMR;
    private static javax.swing.JCheckBox EXACTRAC;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private static javax.swing.JCheckBox zvazky;
    // End of variables declaration//GEN-END:variables
    public static boolean about_JframeIsOpen;

    
}

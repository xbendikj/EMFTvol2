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

/**
 *
 * @author Jozef
 */
public class calculation_Settings extends javax.swing.JFrame {

    /**
     * Creates new form about_JFrame
     */
    public calculation_Settings() {
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
    public static JCheckBox getEmirrorA() {
        return EmirrorA;
    }

    public static void setEmirrorA(JCheckBox EmirrorA) {
        calculation_Settings.EmirrorA = EmirrorA;
    }

    public static JCheckBox getEmirrorB() {
        return EmirrorB;
    }

    public static void setEmirrorB(JCheckBox EmirrorB) {
        calculation_Settings.EmirrorB = EmirrorB;
    }

    public static JCheckBox getEmirrorOff() {
        return EmirrorOff;
    }

    public static void setEmirrorOff(JCheckBox EmirrorOff) {
        calculation_Settings.EmirrorOff = EmirrorOff;
    }

    public static JCheckBox getEmod() {
        return Emod;
    }

    public static void setEmod(JCheckBox Emod) {
        calculation_Settings.Emod = Emod;
    }

    public static JCheckBox getI() {
        return I;
    }

    public static void setI(JCheckBox I) {
        calculation_Settings.I = I;
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
        EmirrorA = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        EmirrorB = new javax.swing.JCheckBox();
        EmirrorOff = new javax.swing.JCheckBox();
        I = new javax.swing.JCheckBox();
        Emod = new javax.swing.JCheckBox();

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

        jLabel2.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 19));

        buttonGroup1.add(EmirrorA);
        EmirrorA.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 20));
        EmirrorA.setToolTipText(language_main_frame.LangLabel(constants.getLanguage_option(), 22));
        EmirrorA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmirrorAActionPerformed(evt);
            }
        });

        jLabel3.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 18));

        buttonGroup1.add(EmirrorB);
        EmirrorB.setSelected(true);
        EmirrorB.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 21));
        EmirrorB.setToolTipText(language_main_frame.LangLabel(constants.getLanguage_option(), 23));
        EmirrorB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmirrorBActionPerformed(evt);
            }
        });

        buttonGroup1.add(EmirrorOff);
        EmirrorOff.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 24));
        EmirrorOff.setToolTipText(language_main_frame.LangLabel(constants.getLanguage_option(), 25));
        EmirrorOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmirrorOffActionPerformed(evt);
            }
        });

        I.setText("indukovana prudova hustota");
        EmirrorOff.setToolTipText(language_main_frame.LangLabel(constants.getLanguage_option(), 25));
        I.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IActionPerformed(evt);
            }
        });

        Emod.setText("E modifikovane");
        EmirrorOff.setToolTipText(language_main_frame.LangLabel(constants.getLanguage_option(), 25));
        Emod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmodActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(EmirrorA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                        .addComponent(EmirrorB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(EmirrorOff, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(I, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Emod, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 275, Short.MAX_VALUE)))))
                .addContainerGap())
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
                        .addComponent(EmirrorA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EmirrorB))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmirrorOff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(I)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Emod)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EmirrorAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmirrorAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmirrorAActionPerformed

    private void EmirrorBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmirrorBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmirrorBActionPerformed

    private void EmirrorOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmirrorOffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmirrorOffActionPerformed

    private void IActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IActionPerformed

    private void EmodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmodActionPerformed

   

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
            java.util.logging.Logger.getLogger(calculation_Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(calculation_Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(calculation_Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(calculation_Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
 
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new calculation_Settings().setVisible(true);
            }
        });
         
    }

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JCheckBox EmirrorA;
    private static javax.swing.JCheckBox EmirrorB;
    private static javax.swing.JCheckBox EmirrorOff;
    private static javax.swing.JCheckBox Emod;
    private static javax.swing.JCheckBox I;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
    public static boolean about_JframeIsOpen;

    
}

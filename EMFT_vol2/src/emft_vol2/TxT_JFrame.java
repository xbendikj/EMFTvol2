/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emft_vol2;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBox;

/**
 *
 * @author Jozef
 */
public class TxT_JFrame extends javax.swing.JFrame {

    /**
     * Creates new form about_JFrame
     */
    public TxT_JFrame() {
        initComponents();
       
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

    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        B_COMPLEX = new javax.swing.JCheckBox();
        B_RMS1 = new javax.swing.JCheckBox();
        B_PHASOR = new javax.swing.JCheckBox();
        B_XYZ_RMS = new javax.swing.JCheckBox();
        B_XYZ_COMPLEX = new javax.swing.JCheckBox();
        B_XYZ_PHASOR = new javax.swing.JCheckBox();
        E_RMS = new javax.swing.JCheckBox();
        E_PHASOR = new javax.swing.JCheckBox();
        E_COMPLEX = new javax.swing.JCheckBox();
        E_XYZ_RMS = new javax.swing.JCheckBox();
        E_XYZ_COMPLEX = new javax.swing.JCheckBox();
        E_XYZ_PHASOR = new javax.swing.JCheckBox();
        B_GeoMat = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        if(constants.getLanguage_option() ==1){
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphics/header_txt.png"))); // NOI18N
        }
        if(constants.getLanguage_option() ==3){
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphics/header_txt.png"))); // NOI18N
        }
        if(constants.getLanguage_option() ==2){
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphics/header_txt.png"))); // NOI18N
        }

        jLabel2.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 12));

        B_COMPLEX.setText("B complex");
        B_COMPLEX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_COMPLEXActionPerformed(evt);
            }
        });

        B_RMS1.setSelected(true);
        B_RMS1.setText("B rms");
        B_RMS1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_RMS1ActionPerformed(evt);
            }
        });

        B_PHASOR.setText("B phasor");
        B_PHASOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_PHASORActionPerformed(evt);
            }
        });

        B_XYZ_RMS.setText("B xyz rms");
        B_XYZ_RMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_XYZ_RMSActionPerformed(evt);
            }
        });

        B_XYZ_COMPLEX.setText("B  xyz complex");
        B_XYZ_COMPLEX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_XYZ_COMPLEXActionPerformed(evt);
            }
        });

        B_XYZ_PHASOR.setText("B  xyz phasor");
        B_XYZ_PHASOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_XYZ_PHASORActionPerformed(evt);
            }
        });

        E_RMS.setSelected(true);
        E_RMS.setText("E rms");
        E_RMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E_RMSActionPerformed(evt);
            }
        });

        E_PHASOR.setText("E phasor");
        E_PHASOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E_PHASORActionPerformed(evt);
            }
        });

        E_COMPLEX.setText("E complex");
        E_COMPLEX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E_COMPLEXActionPerformed(evt);
            }
        });

        E_XYZ_RMS.setText("E xyz rms");
        E_XYZ_RMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E_XYZ_RMSActionPerformed(evt);
            }
        });

        E_XYZ_COMPLEX.setText("E  xyz complex");
        E_XYZ_COMPLEX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E_XYZ_COMPLEXActionPerformed(evt);
            }
        });

        E_XYZ_PHASOR.setText("E  xyz phasor");
        E_XYZ_PHASOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E_XYZ_PHASORActionPerformed(evt);
            }
        });

        B_GeoMat.setText("B  GeoMat");
        B_GeoMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_GeoMatActionPerformed(evt);
            }
        });

        jLabel3.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 11));

        jLabel4.setText(language_main_frame.LangLabel(constants.getLanguage_option(), 12));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(E_RMS, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(E_XYZ_RMS, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(E_COMPLEX))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(E_XYZ_COMPLEX))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(B_XYZ_RMS)
                                    .addComponent(B_RMS1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(B_XYZ_COMPLEX)
                                    .addComponent(B_COMPLEX))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(E_PHASOR)
                            .addComponent(B_XYZ_PHASOR)
                            .addComponent(E_XYZ_PHASOR)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(B_PHASOR)
                                .addGap(34, 34, 34)
                                .addComponent(B_GeoMat)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(B_RMS1)
                    .addComponent(B_COMPLEX)
                    .addComponent(B_PHASOR)
                    .addComponent(B_GeoMat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(B_XYZ_RMS)
                    .addComponent(B_XYZ_COMPLEX)
                    .addComponent(B_XYZ_PHASOR))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(E_RMS)
                    .addComponent(E_COMPLEX)
                    .addComponent(E_PHASOR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(E_XYZ_RMS)
                    .addComponent(E_XYZ_COMPLEX)
                    .addComponent(E_XYZ_PHASOR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B_COMPLEXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_COMPLEXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_COMPLEXActionPerformed

    private void B_RMS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_RMS1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_RMS1ActionPerformed

    private void B_PHASORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_PHASORActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_PHASORActionPerformed

    private void B_XYZ_RMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_XYZ_RMSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_XYZ_RMSActionPerformed

    private void B_XYZ_COMPLEXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_XYZ_COMPLEXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_XYZ_COMPLEXActionPerformed

    private void B_XYZ_PHASORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_XYZ_PHASORActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_XYZ_PHASORActionPerformed

    private void E_RMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E_RMSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_E_RMSActionPerformed

    private void E_PHASORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E_PHASORActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_E_PHASORActionPerformed

    private void E_COMPLEXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E_COMPLEXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_E_COMPLEXActionPerformed

    private void E_XYZ_RMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E_XYZ_RMSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_E_XYZ_RMSActionPerformed

    private void E_XYZ_COMPLEXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E_XYZ_COMPLEXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_E_XYZ_COMPLEXActionPerformed

    private void E_XYZ_PHASORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E_XYZ_PHASORActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_E_XYZ_PHASORActionPerformed

    private void B_GeoMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_GeoMatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_GeoMatActionPerformed

    public JCheckBox getB_COMPLEX() {
        return B_COMPLEX;
    }

    public JCheckBox getB_GeoMat() {
        return B_GeoMat;
    }

    public JCheckBox getB_PHASOR() {
        return B_PHASOR;
    }

    public JCheckBox getB_RMS1() {
        return B_RMS1;
    }

    public JCheckBox getB_XYZ_COMPLEX() {
        return B_XYZ_COMPLEX;
    }

    public JCheckBox getB_XYZ_PHASOR() {
        return B_XYZ_PHASOR;
    }

    public JCheckBox getB_XYZ_RMS() {
        return B_XYZ_RMS;
    }

    public JCheckBox getE_COMPLEX() {
        return E_COMPLEX;
    }

    public JCheckBox getE_PHASOR() {
        return E_PHASOR;
    }

    public JCheckBox getE_RMS() {
        return E_RMS;
    }

    public JCheckBox getE_XYZ_COMPLEX() {
        return E_XYZ_COMPLEX;
    }

    public JCheckBox getE_XYZ_PHASOR() {
        return E_XYZ_PHASOR;
    }

    public JCheckBox getE_XYZ_RMS() {
        return E_XYZ_RMS;
    }

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
            java.util.logging.Logger.getLogger(TxT_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TxT_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TxT_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TxT_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
 
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TxT_JFrame().setVisible(true);
            }
        });
         
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox B_COMPLEX;
    private javax.swing.JCheckBox B_GeoMat;
    private javax.swing.JCheckBox B_PHASOR;
    private javax.swing.JCheckBox B_RMS1;
    private javax.swing.JCheckBox B_XYZ_COMPLEX;
    private javax.swing.JCheckBox B_XYZ_PHASOR;
    private javax.swing.JCheckBox B_XYZ_RMS;
    private javax.swing.JCheckBox E_COMPLEX;
    private javax.swing.JCheckBox E_PHASOR;
    private javax.swing.JCheckBox E_RMS;
    private javax.swing.JCheckBox E_XYZ_COMPLEX;
    private javax.swing.JCheckBox E_XYZ_PHASOR;
    private javax.swing.JCheckBox E_XYZ_RMS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
    public static boolean about_JframeIsOpen;

    
}
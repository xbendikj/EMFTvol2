/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emft_vol2;

import InternalFrame.InternalFrameproject;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Jozef
 */
public class main_Jframe extends javax.swing.JFrame {

    /**
     * Creates new form man_Jframe
     */
    public static InternalFrameproject window = null;
    public main_Jframe() {
        initComponents();
        seticon();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_STATUS = new javax.swing.JPanel();
        jLabel_STATUS = new javax.swing.JLabel();
        JDesktop = new javax.swing.JDesktopPane();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu_project = new javax.swing.JMenu();
        jMenuItem_new = new javax.swing.JMenuItem();
        jMenuItem_load = new javax.swing.JMenuItem();
        jMenu_settings = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu_help = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_STATUS.setText(language_main_frame.LangLabel(constants.getLanguage_option(),0));

        javax.swing.GroupLayout jPanel_STATUSLayout = new javax.swing.GroupLayout(jPanel_STATUS);
        jPanel_STATUS.setLayout(jPanel_STATUSLayout);
        jPanel_STATUSLayout.setHorizontalGroup(
            jPanel_STATUSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_STATUSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_STATUS)
                .addContainerGap(1106, Short.MAX_VALUE))
        );
        jPanel_STATUSLayout.setVerticalGroup(
            jPanel_STATUSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_STATUSLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_STATUS)
                .addContainerGap())
        );

        javax.swing.GroupLayout JDesktopLayout = new javax.swing.GroupLayout(JDesktop);
        JDesktop.setLayout(JDesktopLayout);
        JDesktopLayout.setHorizontalGroup(
            JDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1185, Short.MAX_VALUE)
        );
        JDesktopLayout.setVerticalGroup(
            JDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 963, Short.MAX_VALUE)
        );

        jMenu_project.setText(language_main_frame.LangLabel(constants.getLanguage_option(),1));

        jMenuItem_new.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_new.setText(language_main_frame.LangLabel(constants.getLanguage_option(),4)  );
        jMenuItem_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_newActionPerformed(evt);
            }
        });
        jMenu_project.add(jMenuItem_new);

        jMenuItem_load.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem_load.setText(language_main_frame.LangLabel(constants.getLanguage_option(),5)  );
        jMenuItem_load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_loadActionPerformed(evt);
            }
        });
        jMenu_project.add(jMenuItem_load);

        jMenuBar.add(jMenu_project);

        jMenu_settings.setText(language_main_frame.LangLabel(constants.getLanguage_option(),2));
        jMenu_settings.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                jMenu_settingsMenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });
        jMenu_settings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu_settingsMouseClicked(evt);
            }
        });
        jMenu_settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_settingsActionPerformed(evt);
            }
        });

        jMenuItem1.setText(language_main_frame.LangLabel(constants.getLanguage_option(),6));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu_settings.add(jMenuItem1);

        jMenuItem3.setText(language_main_frame.LangLabel(constants.getLanguage_option(),13));
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu_settings.add(jMenuItem3);

        jMenuBar.add(jMenu_settings);

        jMenu_help.setText(language_main_frame.LangLabel(constants.getLanguage_option(),3)  );

        jMenuItem2.setText(language_main_frame.LangLabel(constants.getLanguage_option(),8) );
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu_help.add(jMenuItem2);

        jMenuBar.add(jMenu_help);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_STATUS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JDesktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JDesktop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_STATUS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_newActionPerformed
       // if ( pocetOkien<3){
       if(window==null ) {   
        window = new InternalFrameproject();    
        JDesktop.add(window);
        window.show();
      }
       // }
        
    }//GEN-LAST:event_jMenuItem_newActionPerformed

    private void jMenu_settingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_settingsActionPerformed
       
    }//GEN-LAST:event_jMenu_settingsActionPerformed

    private void jMenu_settingsMenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_jMenu_settingsMenuKeyPressed
        
    }//GEN-LAST:event_jMenu_settingsMenuKeyPressed

    private void jMenu_settingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu_settingsMouseClicked
       
       
    }//GEN-LAST:event_jMenu_settingsMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if(constants_Jframe.constants_JframeIsOpen == false){
        String[] args = null;
        constants_Jframe.main(args); //spust main frame
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if( about_JFrame.about_JframeIsOpen == false){
        String[] args = null;
        about_JFrame.main(args);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if( TxT_JFrame.about_JframeIsOpen == false){
            String[] args = null;
            TxT_JFrame.main(args);
        }

//        if(window==null ) {
//            window = new JInternalFrame_TXT();
//            JDesktop.add(window);
//            window.show();
//        }

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem_loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_loadActionPerformed
         // if ( pocetOkien<3){
       if(window==null ) {   
        window = new InternalFrameproject();    
        JDesktop.add(window);
        window.show();
        window.getLoad().doClick();
      }
       // }
    }//GEN-LAST:event_jMenuItem_loadActionPerformed

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
            java.util.logging.Logger.getLogger(main_Jframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main_Jframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main_Jframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main_Jframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main_Jframe().setVisible(true);
            }
        });
    }

 private void seticon() {
     setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/graphics/" + "icon.png")));
    }   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane JDesktop;
    private javax.swing.JLabel jLabel_STATUS;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem_load;
    private javax.swing.JMenuItem jMenuItem_new;
    private javax.swing.JMenu jMenu_help;
    private javax.swing.JMenu jMenu_project;
    private javax.swing.JMenu jMenu_settings;
    private javax.swing.JPanel jPanel_STATUS;
    // End of variables declaration//GEN-END:variables
    public static ArrayList<InternalFrameproject> Okna = new ArrayList<InternalFrameproject>();
     private int pocetOkien = 0;
}



/**
 *
 * @author xbendikj
 */
class language_main_frame {

/**
 * Function just add elements to the array list 
 * If once function runs and sets variable "inicializovane" true it never runs again
 */    
static void constructor(){
              /*SK*/                /*CZ*/              /*EN*/   
 /*0*/   SK.add("EMFT sim 2");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position 
 /*1*/   SK.add("Projekt");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position 
 /*2*/   SK.add("Nastavenia");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position 
 /*3*/   SK.add("Help");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position 
  /*4*/  SK.add("nový projekt");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position 
  /*5*/  SK.add("Načítať");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position 
  /*6*/  SK.add("Konštanty");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position 
               
  /*7*/  SK.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec quis ante sollicitudin, laoreet ipsum et, tristique sem. Nullam porttitor malesuada tristique. Donec semper arcu mauris, eget lacinia lorem fringilla ut. Sed porta commodo nulla, venenatis convallis eros sagittis eu. Nullam viverra tincidunt lectus vel consectetur. Nunc vitae quam augue. Suspendisse quis enim nisi. Praesent orci velit, ornare fringilla dignissim vitae, sollicitudin a neque. In vitae placerat eros. Ut nec libero diam. Curabitur nec augue non elit ultrices pulvinar in lacinia orci. Suspendisse luctus odio et ultricies hendrerit. Donec non tempus ipsum, eu ullamcorper nunc. Aliquam tempus iaculis elementum. Ut a justo at augue hendrerit convallis.");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");  
         
  /*8*/  SK.add("O programe");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position 
  /*9*/  SK.add("Zavieť");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position 
  /*10*/  SK.add("OK");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position 
  /*11*/  SK.add("Nastavenie velkeho TXT výstupu Y sxis");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position         
  /*12*/  SK.add("Výstup aj pre parametricky output Y axis");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                         
 /*13*/  SK.add("TxT nastavenia");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                             
  /*14*/  SK.add("Pre funkčnost a aktualnosť nastaveni ponechaj okno otvorené.");  
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
 private static final ArrayList<String> SK= new ArrayList<>();
 private static final ArrayList<String> CZ= new ArrayList<>();
 private static final ArrayList<String> EN= new ArrayList<>();
 private static boolean inicializovane = false;

}
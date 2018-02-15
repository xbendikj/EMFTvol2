/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emft_vol2;

import InternalFrame.InternalFrameproject;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        iscalculation_Settings=false;
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
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu_help = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();

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

        jMenuItem4.setText(language_main_frame.LangLabel(constants.getLanguage_option(),15));
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu_settings.add(jMenuItem4);

        jMenuItem5.setText(language_main_frame.LangLabel(constants.getLanguage_option(),17));
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu_settings.add(jMenuItem5);

        jMenuBar.add(jMenu_settings);

        jMenu_help.setText(language_main_frame.LangLabel(constants.getLanguage_option(),3)  );

        jMenuItem2.setText(language_main_frame.LangLabel(constants.getLanguage_option(),8) );
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu_help.add(jMenuItem2);

        jMenuItem6.setText(language_main_frame.LangLabel(constants.getLanguage_option(),69) );
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu_help.add(jMenuItem6);

        jMenuBar.add(jMenu_help);

        jMenu1.setText(language_main_frame.LangLabel(constants.getLanguage_option(),70) );

        jMenuItem7.setText(language_main_frame.LangLabel(constants.getLanguage_option(),71) );
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuBar.add(jMenu1);

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

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
       
        if( calculation_Settings.about_JframeIsOpen == false){
           iscalculation_Settings=true;
            String[] args = null;
            calculation_Settings.main(args);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if( Dislin_Settings.about_JframeIsOpen == false){
          
            String[] args = null;
            Dislin_Settings.main(args);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
       if( Harmonicka_analyza.about_JframeIsOpen == false){
          
            String[] args = null;
            Harmonicka_analyza.main(args);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        try {
            String userhome = System.getProperty("user.dir"); 
            Desktop.getDesktop().open(new File(userhome +"/src/module/harm_simple.xlsx"));
        } catch (IOException ex) {
            Logger.getLogger(main_Jframe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

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

    public static boolean isIscalculation_Settings() {
        return iscalculation_Settings;
    }

    public static void setIscalculation_Settings(boolean iscalculation_Settings) {
        main_Jframe.iscalculation_Settings = iscalculation_Settings;
    }

 private void seticon() {
     setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/graphics/" + "icon.png")));
    }   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane JDesktop;
    private javax.swing.JLabel jLabel_STATUS;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem_load;
    private javax.swing.JMenuItem jMenuItem_new;
    private javax.swing.JMenu jMenu_help;
    private javax.swing.JMenu jMenu_project;
    private javax.swing.JMenu jMenu_settings;
    private javax.swing.JPanel jPanel_STATUS;
    // End of variables declaration//GEN-END:variables
    public static ArrayList<InternalFrameproject> Okna = new ArrayList<InternalFrameproject>();
     private int pocetOkien = 0;
     public static boolean iscalculation_Settings;
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
 /*0*/   SK.add("EMFT sim 2 verzia 1.1 vydane dna 8.2.2018");  
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
 /*4*/   SK.add("nový projekt");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position 
 /*5*/   SK.add("Načítať");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position 
 /*6*/   SK.add("Konštanty");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position      
 /*7*/   SK.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec quis ante sollicitudin, laoreet ipsum et, tristique sem. Nullam porttitor malesuada tristique. Donec semper arcu mauris, eget lacinia lorem fringilla ut. Sed porta commodo nulla, venenatis convallis eros sagittis eu. Nullam viverra tincidunt lectus vel consectetur. Nunc vitae quam augue. Suspendisse quis enim nisi. Praesent orci velit, ornare fringilla dignissim vitae, sollicitudin a neque. In vitae placerat eros. Ut nec libero diam. Curabitur nec augue non elit ultrices pulvinar in lacinia orci. Suspendisse luctus odio et ultricies hendrerit. Donec non tempus ipsum, eu ullamcorper nunc. Aliquam tempus iaculis elementum. Ut a justo at augue hendrerit convallis.");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");  
 /*8*/   SK.add("O programe");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");                       //language String value  at position 
 /*9*/   SK.add("Zavieť");  
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
 /*15*/  SK.add("Nastavenia výpočtu");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");   
 /*16*/  SK.add("Geometria vodičov");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
 /*17*/  SK.add("Nastavenia Dislin");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*18*/  SK.add("Špecificke nastavenia výpočtu");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*19*/  SK.add("Druh zrkadlenia");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
 /*20*/  SK.add("Bodove zrkladnie");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
 /*21*/  SK.add("Zrkadlenie na aroximovanu rovinu");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
 /*22*/  SK.add("Každy element sa zrkadli  v priestore podla kolmo na trojuholnik pod nim.");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
 /*23*/  SK.add("Bod sa priemietne pod seba a tam sa vytvory nova rovina paralelna s plocohu  XZ a kolma na os X na tento pas sa potom zrkaldli. Metodika ako v EMFT povodnom");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");         
 /*24*/  SK.add("Vypnutie mirroru");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
 /*25*/  SK.add("Vypne sa mirrorovanie");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*26*/  SK.add("Grafické nastavenia Dislin");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");  
 /*27*/  SK.add("Ticks delitel - X");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*28*/  SK.add("Počet dielikov na osi X");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*29*/  SK.add("Ticks delitel - Y");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*30*/  SK.add("Počet dielikov na osi Y");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
 /*31*/  SK.add("Ticks delitel - Z");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*32*/  SK.add("Počet dielikov na osi Z");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*33*/  SK.add("Velkost strany X");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*34*/  SK.add("Velkost strany Y");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");        
 /*35*/  SK.add("Velkost Textu A");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*36*/  SK.add("Velkost Textu pred generovanim funkcie graf");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*37*/  SK.add("Velkost Textu B");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*38*/  SK.add("Velkost Textu po generovani funkcie graf");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");        
 /*39*/  SK.add("Hrubka čiar A");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*40*/  SK.add("Hrubka čiar  pred generovanim funkcie graf");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*41*/  SK.add("Hrubka čiar B");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*42*/  SK.add("Hrubka čiar  po generovani funkcie graf");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*43*/  SK.add("Hrubka čiar Grid");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*44*/  SK.add("Offset 1D graf Y+");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*45*/  SK.add("Nnasobok ofsetu na Y+ osi");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*46*/  SK.add("Offset 1D graf Y-");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*47*/  SK.add("Nasobok ofsetu 1D graf Y-");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*48*/  SK.add("Offset 1D graf X +/-");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*49*/  SK.add("Nasobok ofsetu 1D graf X +/-");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");  
 /*50*/  SK.add("Popis osi X");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*51*/  SK.add("Popis osi Y");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*52*/  SK.add("Popis osi Z");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*53*/  SK.add("LaTeX Math format zápisu");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");   
 /*54*/  SK.add("Grid");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*55*/  SK.add("Pozadie ");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*56*/  SK.add("true = sive / false = biele");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*57*/  SK.add("Pocet kontur");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*58*/  SK.add("Pocet desatinných miest");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
 /*59*/  SK.add("coss / stvorec");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");          
 /*60*/  SK.add("Ďalšie konštanty mimo databazu");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*61*/  SK.add("Indukovana prudova hustota");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*62*/  SK.add("E modifikovane (CZ leg.)");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*63*/  SK.add("Presnosť CH");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*64*/  SK.add("desatinná presnosť na prepočet medzi parametrom retazovky c a minimalnou vyškou vodiča h");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
 /*65*/  SK.add("divergenčna konštanta");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*66*/  SK.add("divergencna konstana kedy už rozlišuje že vypočet c/H diverguje");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");         
  /*67*/  SK.add("Riadok 1");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
 /*68*/  SK.add("Riadok 2");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release"); 
 /*69*/  SK.add("Harmonicka Analýza");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*70*/  SK.add("Moduly");  
         CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016 1.release"); 
         EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 1.release");
/*71*/  SK.add("Linearna harmonicka analyza E&B - xlsx");  
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
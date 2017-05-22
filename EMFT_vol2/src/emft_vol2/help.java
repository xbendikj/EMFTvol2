/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emft_vol2;

import java.awt.Color;

/**
 *
 * @author Jozef
 */
public class help {
    
    //private static String printer;
   //private static boolean printPrinter = false;  
    
    public static void printl(String printer,Boolean printPrinter){
        
      if(printPrinter.equals(true)){
          System.out.println(printer);
      }  
        
    }
    /**
     * kontrolor na double
     * @param Y Jtext field vstup
     * @param output ak je Y kokoina tak vyhodi tuto hodnotu
     * @return 
     */
    public static double doubleCheck(javax.swing.JTextField Y,double output){
    String hodnota1 =Y.getText();
        hodnota1=hodnota1.replace(" ", "");
        String hodnota2=hodnota1.replace(",", ".");
       if(hodnota1.equals(hodnota2)){}else{ Y.setText(hodnota2);}         
       Double value ;
        try{
        value = Double.parseDouble(hodnota2);
        Y.setForeground(Color.black);
        return value;
        }catch(NumberFormatException | NullPointerException e){
         Y.setForeground(Color.red);
        return value = output;            
        }
     }
    
    
}

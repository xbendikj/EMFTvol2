/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import Databazes.SQLlite_constants;
import emft_vol2.constants;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashSet;
import java.util.Set;
import org.jdelaunay.delaunay.geometries.DPoint;

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
    
    public static void info(String printer,Boolean printPrinter){
        
      if(printPrinter.equals(true)){
          System.out.println("INFO TEST: "+printer);
      }  
        
    }
    
    /**
     * kontrolor na double
     * @param Y Jtext field vstup
     * @param output ak je Y kokoina tak vyhodi tuto hodnotu
     * @return 
     */
    public static double ReadCheckDouble(javax.swing.JTextField Y,double output){
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
    /**
     * vlozi hodnotu to text fildu a upravi jej pocet des miest
     * @param Y text field vstup
     * @param input value ktoru chem zobrazit v textfielde
     * @param pocetDesMiest pocet desatinnich miest na ktore bude zaukruhlovat cislo
     */
    public static void DisplayDouble(javax.swing.JTextField Y,double input,int pocetDesMiest){
         
         String symbol="###.";
         for(int cl0 =0 ; cl0<=pocetDesMiest;cl0++){
           symbol=symbol+"#";  
         }
         
         DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
         otherSymbols.setDecimalSeparator('.');
         DecimalFormat df = new DecimalFormat(symbol,otherSymbols);  // definovany počet desatinnych miest
         Y.setText(df.format(input));  
         
         
     }
    /**
     * zobrazi hodnotu z databazy do konzoly
     * @param name nazov premennje ako sa vola v databaze
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static void ConstantDatabazeDisplay(String name) throws ClassNotFoundException, SQLException{
        SQLlite_constants test = new SQLlite_constants();
        ResultSet rs;
        
        rs = test.displayConstants();
        
        while(rs.next()){
            
            System.out.println(rs.getString(name));
        }
    }
    
    public static void warning3row(String riadok1,String riadok2, String riadok3){  
        
        warning_jDialog pozor = new warning_jDialog(riadok1,riadok2,riadok3);  
        pozor.setVisible(true);
        
    }
    
    public static void warning2row(String riadok1,String riadok2){  
        
        warning_jDialog pozor = new warning_jDialog(riadok1,riadok2);  
        pozor.setVisible(true);
        
    }
    
    public static void warning1row(String riadok1){  
        
        warning_jDialog pozor = new warning_jDialog(riadok1);  
        pozor.setVisible(true);
        
    }
     
    public static double AsinH (double hodnota){
        double cislo;
        
        cislo = Math.log( hodnota + Math.sqrt( 1+ Math.pow(hodnota, 2)) );
        
        return cislo;
    }
    /**
     * POZOR neuprauje sa vyska  sfunkčne suradnice su XYZ
     * @param LCcoordinates1 globalne suradnice lokalneho sysemu bod 1
     * @param LCcoordinates2 globalne suradnice lokalneho sysemu bod 2
     * @param P1             bod v globalnych sur ktory transformujeme do LC pričom 00je LCCOORDINATES1
     * @return 
     */
    public static DPoint CorToLC (double[] LCcoordinates1,double[] LCcoordinates2,DPoint P1){
        //double GCX = constants.getGCcoordinates()[0];
       // double GCZ = constants.getGCcoordinates()[2];
        
        double P1XGC=P1.getX();
        double P1ZGC=P1.getZ();
        
        
        double LCX1 = LCcoordinates1[0];
        double LCZ1 = LCcoordinates1[2];
        
        double LCX2 = LCcoordinates2[0];
        double LCZ2 = LCcoordinates2[2];
         double alpha =0;
         
        if(LCX1 == LCX2) {
            
        if(LCZ1 - LCZ2<0)    
        alpha=Math.PI/2;
        
        if(LCZ1 - LCZ2>0)    
        alpha=-Math.PI/2;
        
        
        
        }     // n ochrana pred 90 stupnami a delenim nulou
        
        
        else{ alpha = (LCZ1 - LCZ2) / (LCX1 - LCX2); // uhol otocenia
        }
        double P1XLC = (P1XGC - LCX1) * Math.cos(alpha) + (P1ZGC - LCZ1) * Math.sin(alpha);
        double P1ZLC = (P1ZGC - LCZ1) * Math.cos(alpha) + (P1XGC - LCX1) * Math.sin(alpha);
        
        P1.setX(P1XLC);
        P1.setZ(P1ZLC);
        return P1;
    }
    
     
    
}

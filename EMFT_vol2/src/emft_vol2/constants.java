/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emft_vol2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jozef
 */
public class constants {
   
    private static int language_option  = 1;
    private static double Frequency;
    private static double Mu0;
    private static double Mu1;
    private static double Epsi0;
    private static double Epsi1;
    private static double AkcneB;
    private static double AkcneE;

    public static double getAkcneB() {
        return AkcneB;
    }

    public static void setAkcneB(double AkcneB) {
        constants.AkcneB = AkcneB;
    }

    public static double getAkcneE() {
        return AkcneE;
    }

    public static void setAkcneE(double AkcneE) {
        constants.AkcneE = AkcneE;
    }

    public static double getFrequency() {
        return Frequency;
    }

    public static void setFrequency(double Frequency) {
        constants.Frequency = Frequency;
    }

    public static double getMu0() {
        return Mu0;
    }

    public static void setMu0(double Mu0) {
        constants.Mu0 = Mu0;
    }

    public static double getMu1() {
        return Mu1;
    }

    public static void setMu1(double Mu1) {
        constants.Mu1 = Mu1;
    }

    public static double getEpsi0() {
        return Epsi0;
    }

    public static void setEpsi0(double Epsi0) {
        constants.Epsi0 = Epsi0;
    }

    public static double getEpsi1() {
        return Epsi1;
    }

    public static void setEpsi1(double Epsi1) {
        constants.Epsi1 = Epsi1;
    }
   
    public static int getLanguage_option() {
        return language_option;
    }

    public static void setLanguage_option(int language_option) {
        constants.language_option = language_option;
    }
    
    private static double getfromDAT(String name){
        double cislo=0;
        
        //DATABAZA begin       
      SQLlite_constants  Dat_constantstns=  new SQLlite_constants();
      ResultSet rs;
       
        try {
           rs = Dat_constantstns.displayConstants();
         
             while(rs.next()){
        cislo=Double.parseDouble(rs.getString(name));   // vyber z databazy a vloz do cislo a prerob na double
                            }        
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(main_class.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return cislo;
    }
    
     public static void UpdateConstant(String name,int ID,double value) throws ClassNotFoundException, SQLException{
     
      SQLlite_constants  Dat_constantstns=  new SQLlite_constants();   
      Dat_constantstns.update(name, ID, value);
      
     
    }
   
    
    public static void loadConstants(){
        
        setFrequency(getfromDAT("Freq"));
        setMu0(getfromDAT("Mu0"));
        setMu1(getfromDAT("Mu1"));
        setEpsi0(getfromDAT("Epsi0"));
        setEpsi0(getfromDAT("Epsi1"));
        setAkcneB(getfromDAT("AkcneB"));
        setAkcneE(getfromDAT("AkcneE"));
    }
    
}

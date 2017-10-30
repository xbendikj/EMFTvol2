/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emft_vol2;

import Databazes.SQLlite_constants;
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
    private static String teret_input_folder = "";
    
    private static double AkcneB;
    private static double AkcneE;
    private static double presnostCH=0.001;   // presnost na prepocet C do H manualne v kode 
    private static int divergencia_pocet=10;  // divergencna konstana kedy už rozlišuje že vypočet diverguje
    // DISLIN KONSTANTY
    private static int dislin_velkost_textu_pred_grafom = 70;
    private static int dislin_velkost_textu_za_grafom= 70;        
    private static int dislin_hrubka_ciar_pred_grafom = 8;
    private static int dislin_hrubka_ciar_za_grafom= 5;            
    private static int dislin_hrubka_ciar_GRID = 8;
    private static float dislin_graph_nasobok_z_vrchu = 1.2f; //
    private static float dislin_graph_nasobok_zo_stran = 1.2f; // 
    private static boolean dislin_grid = false;
    private static boolean dislin_graph_type = true;      // true cross - false rectangular
    private static boolean dislin_graph_bgcol = true;     // true sive pozadie - false biele pozadie
    private static int dislin_pocet_kontur = 9;
    private static int Dislin_pocet_des_miest=3;

    public static int getDislin_pocet_des_miest() {
        return Dislin_pocet_des_miest;
    }

    public static void setDislin_pocet_des_miest(int Dislin_pocet_des_miest) {
        constants.Dislin_pocet_des_miest = Dislin_pocet_des_miest;
    }
    
    
    public static int getDislin_pocet_kontur() {
        return dislin_pocet_kontur;
    }

    public static void setDislin_pocet_kontur(int dislin_pocet_kontur) {
        constants.dislin_pocet_kontur = dislin_pocet_kontur;
    }
    
    
    
    public static float getDislin_graph_nasobok_zo_stran() {
        return dislin_graph_nasobok_zo_stran;
    }

    public static void setDislin_graph_nasobok_zo_stran(float dislin_graph_nasobok_zo_stran) {
        constants.dislin_graph_nasobok_zo_stran = dislin_graph_nasobok_zo_stran;
    }

    
    public static float getDislin_graph_nasobok_z_vrchu() {
        return dislin_graph_nasobok_z_vrchu;
    }

    public static void setDislin_graph_nasobok_z_vrchu(float dislin_graph_nasobok_z_vrchu) {
        constants.dislin_graph_nasobok_z_vrchu = dislin_graph_nasobok_z_vrchu;
    }
    
    
    
    public static boolean isDislin_graph_bgcol() {
        return dislin_graph_bgcol;
    }

    public static void setDislin_graph_bgcol(boolean dislin_graph_bgcol) {
        constants.dislin_graph_bgcol = dislin_graph_bgcol;
    }

    
    public static boolean isDislin_graph_type() {
        return dislin_graph_type;
    }

    public static void setDislin_graph_type(boolean dislin_graph_type) {
        constants.dislin_graph_type = dislin_graph_type;
    }
    
    public static int getDislin_hrubka_ciar_GRID() {
        return dislin_hrubka_ciar_GRID;
    }

    public static void setDislin_hrubka_ciar_GRID(int dislin_hrubka_ciar_GRID) {
        constants.dislin_hrubka_ciar_GRID = dislin_hrubka_ciar_GRID;
    }

    public static boolean isDislin_grid() {
        return dislin_grid;
    }

    public static void setDislin_grid(boolean dislin_grid) {
        constants.dislin_grid = dislin_grid;
    }
    
    public static int getDislin_velkost_textu_pred_grafom() {
        return dislin_velkost_textu_pred_grafom;
    }

    public static void setDislin_velkost_textu_pred_grafom(int dislin_velkost_textu_pred_grafom) {
        constants.dislin_velkost_textu_pred_grafom = dislin_velkost_textu_pred_grafom;
    }

    public static int getDislin_velkost_textu_za_grafom() {
        return dislin_velkost_textu_za_grafom;
    }

    public static void setDislin_velkost_textu_za_grafom(int dislin_velkost_textu_za_grafom) {
        constants.dislin_velkost_textu_za_grafom = dislin_velkost_textu_za_grafom;
    }

    public static int getDislin_hrubka_ciar_pred_grafom() {
        return dislin_hrubka_ciar_pred_grafom;
    }

    public static void setDislin_hrubka_ciar_pred_grafom(int dislin_hrubka_ciar_pred_grafom) {
        constants.dislin_hrubka_ciar_pred_grafom = dislin_hrubka_ciar_pred_grafom;
    }

    public static int getDislin_hrubka_ciar_za_grafom() {
        return dislin_hrubka_ciar_za_grafom;
    }

    public static void setDislin_hrubka_ciar_za_grafom(int dislin_hrubka_ciar_za_grafom) {
        constants.dislin_hrubka_ciar_za_grafom = dislin_hrubka_ciar_za_grafom;
    }
            
    
    
    public static double getPresnostCH() {
        return presnostCH;
    }

    public static void setPresnostCH(double presnostCH) {
        constants.presnostCH = presnostCH;
    }
    

    public static int getDivergencia_pocet() {
        return divergencia_pocet;
    }

    public static void setDivergencia_pocet(int divergencia_pocet) {
        constants.divergencia_pocet = divergencia_pocet;
    }
    
    private static double DN;                              // velkost siete
    private static double[] GCcoordinates = new double[3]; // globalne koordinaty POZOR potrebne deklarovat velkost

    public static double getDN() {
        return DN;
    }

    public static void setDN(double DN) {
        constants.DN = DN;
    }

    public static String getTeret_input_folder() {
        return teret_input_folder;
    }

    public static void setTeret_input_folder(String teret_input_folder) {
        constants.teret_input_folder = teret_input_folder;
    }

    public static double[] getGCcoordinates() {
        return GCcoordinates;
    }
    /**
     * 
     * @param GCcoordinates double s GDC cor
     * @param id poradove čislo X-0,Y-1,Y-2
     */
    public static void setGCcoordinates(double GCcoordinate,int id) {
        constants.GCcoordinates[id] = GCcoordinate;
    }

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
        setEpsi1(getfromDAT("Epsi1"));
        setAkcneB(getfromDAT("AkcneB"));
        setAkcneE(getfromDAT("AkcneE"));
        setDN(getfromDAT("DN"));
        setGCcoordinates(getfromDAT("GC_X"),0);
        setGCcoordinates(getfromDAT("GC_Y"),1);
        setGCcoordinates(getfromDAT("GC_Z"),2);
        
        
    }
    
}

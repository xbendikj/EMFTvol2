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
    private static String Programpath = "user.dir";
    private static String teret_input_folder = "";
    private static String project_input_folder = "";
    
    private static double AkcneB;
    private static double AkcneE;
    private static double AkcneI = 0.002;
    private static double AkcneEmod = 0.2;
    private static double KE_I = 100;
    private static double KB_I = 0.13;
    private static double KE_Emod = 66;
    private static double KB_Emod = 0.05;
    private static double sigma= 0.2;
    
    private static boolean ParA = true;
    
    private static double presnostCH=0.001;   // presnost na prepocet C do H manualne v kode 
    private static int divergencia_pocet=10;  // divergencna konstana kedy už rozlišuje že vypočet diverguje
    // DISLIN KONSTANTY
    private static int dislin_Step_between_the_labels_X = 10; //  (XE-XA)/10 delitel 
    private static int dislin_Step_between_the_labels_Y = 10; //  (YE-YA)/10 delitel 
    private static int dislin_Step_between_the_labels_Z = 5;  //   (ZE-ZA)/10 delitel 
    private static int dislin_velkost_strany_X = 6000;
    private static int dislin_velkost_strany_Y = 4000;
    private static int dislin_velkost_textu_pred_grafom = 70;
    private static int dislin_velkost_textu_za_grafom= 70;        
    private static int dislin_hrubka_ciar_pred_grafom = 8;
    private static int dislin_hrubka_ciar_za_grafom= 5;            
    private static int dislin_hrubka_ciar_GRID = 8;
    private static float dislin_graph_nasobok_z_vrchu = 1.2f; //
    private static float dislin_graph_nasobok_z_spodu = 1.2f; //
    private static float dislin_graph_nasobok_zo_stran = 1.2f; // 
    private static String dislin_Dislin_Float_orEXP = "FLOAT"; //
    private static String dislin_Label_B = "$B [\\mu T]$"; //
    private static String dislin_Label_E = "$E [kV/m]$"; //
    private static String dislin_Label_Emod = "$E_{mod} [kV/m]$"; //
    private static String dislin_Label_I = "$I [mA/m^2]$"; //
     private static String dislin_Label_X = "$l [m]$"; //
      private static String dislin_Label_Z = "$V [m]$"; //
      private static String dislin_Label_Y = "$h [m]$"; //
        private static String dislin_Label_X_par = "C"; //
    private static boolean dislin_grid = false;
    private static boolean dislin_graph_type = true;      // true cross - false rectangular
    private static boolean dislin_graph_bgcol = true;     // true sive pozadie - false biele pozadie
    private static int dislin_pocet_kontur = 9;
    private static int Dislin_pocet_des_miest=2;
    private static boolean dislin_nozeroYA = false;     // true sive pozadie - false biele pozadie
    private static String ROW1 =  language_main_frame.LangLabel(constants.getLanguage_option(), 0);
    private static String ROW2 = "";
    
    public static String getROW1() {
        return ROW1;
    }

    public static void setROW1(String ROW1) {
        constants.ROW1 = ROW1;
    }

    public static String getROW2() {
        return ROW2;
    }

    public static boolean isDislin_nozeroYA() {
        return dislin_nozeroYA;
    }

    public static void setDislin_nozeroYA(boolean dislin_nozeroYA) {
        constants.dislin_nozeroYA = dislin_nozeroYA;
    }

    public static void setROW2(String ROW2) {
        constants.ROW2 = ROW2;
    }

    
    public static boolean isParA() {
        return ParA;
    }

    public static void setParA(boolean ParA) {
        constants.ParA = ParA;
    }

    public static double getSigma() {
        return sigma;
    }

    public static void setSigma(double sigma) {
        constants.sigma = sigma;
    }

    public static double getKE_I() {
        return KE_I;
    }

    public static void setKE_I(double KE_I) {
        constants.KE_I = KE_I;
    }

    public static double getKB_I() {
        return KB_I;
    }

    public static void setKB_I(double KB_I) {
        constants.KB_I = KB_I;
    }

    public static double getKE_Emod() {
        return KE_Emod;
    }

    public static void setKE_Emod(double KE_Emod) {
        constants.KE_Emod = KE_Emod;
    }

    public static double getKB_Emod() {
        return KB_Emod;
    }

    public static void setKB_Emod(double KB_Emod) {
        constants.KB_Emod = KB_Emod;
    }

    public static float getDislin_graph_nasobok_z_spodu() {
        return dislin_graph_nasobok_z_spodu;
    }

    public static void setDislin_graph_nasobok_z_spodu(float dislin_graph_nasobok_z_spodu) {
        constants.dislin_graph_nasobok_z_spodu = dislin_graph_nasobok_z_spodu;
    }

    public static String getProgrampath() {
        return Programpath;
    }

    public static void setProgrampath(String Programpath) {
        constants.Programpath = Programpath;
    }

    public static String getProject_input_folder() {
        return project_input_folder;
    }

    public static void setProject_input_folder(String project_input_folder) {
        constants.project_input_folder = project_input_folder;
    }

    public static double getAkcneI() {
        return AkcneI;
    }

    public static void setAkcneI(double AkcneI) {
        constants.AkcneI = AkcneI;
    }

    public static double getAkcneEmod() {
        return AkcneEmod;
    }

    public static void setAkcneEmod(double AkcneEmod) {
        constants.AkcneEmod = AkcneEmod;
    }

    public static int getDislin_Step_between_the_labels_X() {
        return dislin_Step_between_the_labels_X;
    }

    public static void setDislin_Step_between_the_labels_X(int dislin_Step_between_the_labels_X) {
        constants.dislin_Step_between_the_labels_X = dislin_Step_between_the_labels_X;
    }

    public static int getDislin_Step_between_the_labels_Y() {
        return dislin_Step_between_the_labels_Y;
    }

    public static void setDislin_Step_between_the_labels_Y(int dislin_Step_between_the_labels_Y) {
        constants.dislin_Step_between_the_labels_Y = dislin_Step_between_the_labels_Y;
    }

    public static int getDislin_Step_between_the_labels_Z() {
        return dislin_Step_between_the_labels_Z;
    }

    public static void setDislin_Step_between_the_labels_Z(int dislin_Step_between_the_labels_Z) {
        constants.dislin_Step_between_the_labels_Z = dislin_Step_between_the_labels_Z;
    }

    public static int getDislin_velkost_strany_X() {
        return dislin_velkost_strany_X;
    }

    public static void setDislin_velkost_strany_X(int dislin_velkost_strany_X) {
        constants.dislin_velkost_strany_X = dislin_velkost_strany_X;
    }

    public static int getDislin_velkost_strany_Y() {
        return dislin_velkost_strany_Y;
    }

    public static void setDislin_velkost_strany_Y(int dislin_velkost_strany_Y) {
        constants.dislin_velkost_strany_Y = dislin_velkost_strany_Y;
    }

    public static String getDislin_Label_Y() {
        return dislin_Label_Y;
    }

    public static void setDislin_Label_Y(String dislin_Label_Y) {
        constants.dislin_Label_Y = dislin_Label_Y;
    }

    public static String getDislin_Label_X() {
        return dislin_Label_X;
    }

    public static void setDislin_Label_X(String dislin_Label_X) {
        constants.dislin_Label_X = dislin_Label_X;
    }

    public static String getDislin_Label_Z() {
        return dislin_Label_Z;
    }

    public static void setDislin_Label_Z(String dislin_Label_Z) {
        constants.dislin_Label_Z = dislin_Label_Z;
    }

    public static String getDislin_Label_X_par() {
        return dislin_Label_X_par;
    }

    public static void setDislin_Label_X_par(String dislin_Label_X_par) {
        constants.dislin_Label_X_par = dislin_Label_X_par;
    }

    public static String getDislin_Label_B() {
        return dislin_Label_B;
    }

    public static void setDislin_Label_B(String dislin_Label_B) {
        constants.dislin_Label_B = dislin_Label_B;
    }

    public static String getDislin_Label_E() {
        return dislin_Label_E;
    }

    public static void setDislin_Label_E(String dislin_Label_E) {
        constants.dislin_Label_E = dislin_Label_E;
    }

    public static String getDislin_Label_Emod() {
        return dislin_Label_Emod;
    }

    public static void setDislin_Label_Emod(String dislin_Label_Emod) {
        constants.dislin_Label_Emod = dislin_Label_Emod;
    }

    public static String getDislin_Label_I() {
        return dislin_Label_I;
    }

    public static void setDislin_Label_I(String dislin_Label_I) {
        constants.dislin_Label_I = dislin_Label_I;
    }

    public static String getDislin_Dislin_Float_orEXP() {
        return dislin_Dislin_Float_orEXP;
    }

    public static void setDislin_Dislin_Float_orEXP(String dislin_Dislin_Float_orEXP) {
        constants.dislin_Dislin_Float_orEXP = dislin_Dislin_Float_orEXP;
    }

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

/*
 * TO DO SOME SORT OF CHECKER
 */
package emft_vol2;

import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;
import java.util.ArrayList;
import tools.help;

/**
 * 
 * @author Jozef
 */
 
public class rozpatie {
    private String meno;
    private double[] LCcoordinates1 = new double[3]; 
    private  double[] LCcoordinates2 = new double[3]; 
    private  double A;
    private  double Z;
    private  ArrayList<retazovka> Retazovka = new ArrayList<>();
    private  teren Teren;
    

    // empty constructor
    public rozpatie() {
        
    //definovat default local system
        LCcoordinates1[0]=0; // X 
        LCcoordinates1[1]=0; // X 
        LCcoordinates1[2]=0; // X     
    }
    /**
     * buil default rozpatie, default l.c.
     * @param meno nazov rozpatia
     * @param A dlzky rozpatia A rozmer
     * @param Z priecna dlzka od nuly!! od stredu
     */
    public rozpatie(String meno,double A,double Z) {
        this.meno=meno;
        this.A=A;
        this.Z=Z;
        //definovat default local system
        LCcoordinates1[0]=0; // X 
        LCcoordinates1[1]=0; // X 
        LCcoordinates1[2]=0; // X 
        
        LCcoordinates2[0]=A; // X  //  default druhy bod LC
        LCcoordinates2[1]= LCcoordinates1[1]; // y
        LCcoordinates2[2]=0; // z
        
    }
       

    public  String getMeno() {
        return meno;
    }

    public static void setMeno(String meno) {
        meno = meno;
    }

    public  double[] getLCcoordinates1() {
        return LCcoordinates1;
    }
    /**
     * predefinuj l.c.
     * @param X corr
     * @param Y corr
     * @param Z corr
     */
    public void setLCcoordinates(double X,double Y,double Z,double X2,double Z2) {
        LCcoordinates1[0] = X;
        LCcoordinates1[1] = Y;
        LCcoordinates1[2] = Z;
        
        LCcoordinates2[0] = X2; 
        LCcoordinates2[1] =  LCcoordinates1[1] ;
        LCcoordinates2[2] = Z2;
       
        this.A=Math.sqrt( Math.pow(X2-X,2) - Math.pow(Z2-Z,2));  // znema LC znamena aj Zmena A
         
        help.info("Nastala Zmena A pretože bolo definovane LC mimo deff", true);
    }

    public  double getA() {
        return A;
    }

    public  void setA(double A) {
        A = A;
    }

    public  double getZ() {
        return Z;
    }

    public  void setZ(double Z) {
        Z = Z;
    }

    public  ArrayList<retazovka> getRetazovkaList() {
        return Retazovka;
    }
    /**
     * 
     * @param id poradove cislo retazovky a arralystu
     * @return jedlo lano
     */
    public  retazovka getRetazovka(int id) {
        return Retazovka.get(id);
    }
    
    /**
     * add new lano to the next one
     * @param Retazovka 
     */
    public  void setRetazovka( retazovka Retazovka) {
        this.Retazovka.add(Retazovka);
    }
    
    public  void replaceRetazovka( retazovka Retazovka, int id) {
        this.Retazovka.set(id,Retazovka);
    }

    public  void setRetazovka(ArrayList<retazovka> Retazovka) {
        this.Retazovka = Retazovka;
    }
   
    public  teren getTeren() {
        return Teren;
    }

    public static void setTeren(teren Teren) {
       Teren = Teren;
    }
    
    
}

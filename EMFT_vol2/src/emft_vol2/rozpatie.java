/*
 * TO DO SOME SORT OF CHECKER
 */
package emft_vol2;

import java.util.ArrayList;

/**
 * 
 * @author Jozef
 */
 
public class rozpatie {
    private static String meno;
    private static double[] LCcoordinates = new double[3]; 
    private static double A;
    private static double Z;
    private static ArrayList<retazovka> Retazovka = new ArrayList<>();
    private static teren Teren;
    

    // empty constructor
    public rozpatie() {
        
    //definovat default local system
        LCcoordinates[0]=0; // X 
        LCcoordinates[1]=0; // X 
        LCcoordinates[2]=0; // X     
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
        LCcoordinates[0]=0; // X 
        LCcoordinates[1]=0; // X 
        LCcoordinates[2]=0; // X 
    }
       

    public static String getMeno() {
        return meno;
    }

    public static void setMeno(String meno) {
        rozpatie.meno = meno;
    }

    public static double[] getLCcoordinates() {
        return LCcoordinates;
    }
    /**
     * predefinuj l.c.
     * @param X corr
     * @param Y corr
     * @param Z corr
     */
    public static void setLCcoordinates(double X,double Y,double Z) {
        rozpatie.LCcoordinates[0] = X;
        rozpatie.LCcoordinates[1] = Y;
        rozpatie.LCcoordinates[2] = Z;
    }

    public static double getA() {
        return A;
    }

    public static void setA(double A) {
        rozpatie.A = A;
    }

    public static double getZ() {
        return Z;
    }

    public static void setZ(double Z) {
        rozpatie.Z = Z;
    }

    public static ArrayList<retazovka> getRetazovkaList() {
        return Retazovka;
    }
    /**
     * 
     * @param id poradove cislo retazovky a arralystu
     * @return jedlo lano
     */
    public static retazovka getRetazovka(int id) {
        return Retazovka.get(id);
    }
    
    /**
     * add new lano to the next one
     * @param Retazovka 
     */
    public static void setRetazovka( retazovka Retazovka) {
        rozpatie.Retazovka.add(Retazovka);
    }
    
    public static void replaceRetazovka( retazovka Retazovka, int id) {
        rozpatie.Retazovka.set(id,Retazovka);
    }

    public static void setRetazovka(ArrayList<retazovka> Retazovka) {
        rozpatie.Retazovka = Retazovka;
    }
   
    public static teren getTeren() {
        return Teren;
    }

    public static void setTeren(teren Teren) {
        rozpatie.Teren = Teren;
    }
    
    
}

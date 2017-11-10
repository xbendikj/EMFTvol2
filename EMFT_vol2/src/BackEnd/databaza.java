/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.util.ArrayList;
import org.jdelaunay.delaunay.geometries.DTriangle;

/**
 *
 * @author Jozef
 */
public class databaza {
 
    
    
    //E_B core units
   
    private ArrayList<Observer[]> P1D_priecne  = new ArrayList<>(); // array observerov
    private ArrayList<Observer[]> P1D_pozdlzne  = new ArrayList<>(); // array observerov
    private ArrayList<Observer[]> P1D_neurcite  = new ArrayList<>(); // array observerov
   
    private ArrayList<Observer[][]> P2D = new ArrayList<>(); // arraylist observerov
   
    // do buducna to by sa hodilo aby databaza uchovavala aj informacie .. kolko lan ake lana proste všetko orezatovke a podobne 

    //P ARRAYLISTE
    //P1D - 0 - priecne 
    //P1D - 1 - pozdlzne
    //P1D - 2 - neurcite
    
    public databaza() {
        
    }
    /**
     * 
     * @param X observer
     * @param Y typ mapovania
     */
    public void addToList1D(Observer[] X,int Y){
        
        switch(Y){
            case 0: P1D_priecne.add(X);
            case 1: P1D_pozdlzne.add(X);
            case 2: P1D_neurcite.add(X);
        }
       
       
    }
    /**
     * 
     * @param X observer
     * @param Y poradove cislo 
     */
    public void addToList2D(Observer[][] X,int Y){
        
        P2D.add(Y,X);
    }
    /**
     * 
     * @param X poradove cislo v daabaze
     * @return 
     */
    public Observer[] getFromList1D(int vysku,int Y){
        Observer[] vystup=null;
        switch(Y){
            case 0: vystup = P1D_priecne.get(vysku) ;
            case 1: vystup = P1D_pozdlzne.get(vysku);
            case 2: vystup = P1D_neurcite.get(vysku);
        }
        
        return vystup;
    } 
    /**
     * 
     * @param X poradove cislo v daabaze
     * @return 
     */
    public Observer[][] getFromList2D(int Y){
        
        return P2D.get(Y);
    }
    /**
     * 
     * @param rozmer 
     *        "RMS B"
     *        "RMS B REAL"
     *        "RMS B IMAGE"
     *        "RMS B ANGLE"
     *        "RMS B X"
     *        "ABS B X"
     *        "PHASE B X"
     *        "REAL B X"
     *        "IMAGE B X"
     *        ROZNAKO PRE Y a Z  a tiez pre E
     * @param X Observer z ktoreho zobrat data
     * @return vrati yray žiadanej veličiny je nutn dpbre zadat "B" "E" a potom čo z daleho vektora observerov chcem z nej vyextrahovat nevracia geometricke vektory
     */
    public double[] getYray1D(String BE ,String rozmer,Observer[] X){
        double[] yray=new double[X.length];
        
        for (int cl1 = 0; cl1 < X.length; cl1++) {
           if(BE == "B"){ 
            
            if(rozmer.equals("RMS B")) yray[cl1]=X[cl1].getB().getComplex_rms_ABS() ;
           if(rozmer.equals("RMS B REAL")) yray[cl1]=X[cl1].getB().getComplex_rms().getReal() ;
           if(rozmer.equals("RMS B IMAGE")) yray[cl1]=X[cl1].getB().getComplex_rms().getImaginary()  ;
           if(rozmer.equals("RMS B ANGLE")) yray[cl1]=X[cl1].getB().getComplex_rms_Angle();  
                    
           if(rozmer.equals( "RMS B X")) yray[cl1]=X[cl1].getB().getX_ABS();
            if(rozmer.equals("PHASE B X")) yray[cl1]=X[cl1].getB().getX_Angle() ;
           if(rozmer.equals( "REAL B X")) yray[cl1]=X[cl1].getB().getX_Real() ;
            if(rozmer.equals( "IMAGE B X")) yray[cl1]=X[cl1].getB().getX_Imaginary();
            
            if(rozmer.equals( "RMS B Y")) yray[cl1]=X[cl1].getB().getY_ABS();
            if(rozmer.equals( "PHASE B Y")) yray[cl1]=X[cl1].getB().getY_Angle() ;
            if(rozmer.equals( "REAL B Y")) yray[cl1]=X[cl1].getB().getY_Real() ;
            if(rozmer.equals( "IMAGE B Y")) yray[cl1]=X[cl1].getB().getY_Imaginary() ;
            
            if(rozmer.equals( "RMS B Z")) yray[cl1]=X[cl1].getB().getZ_ABS();
            if(rozmer.equals( "PHASE B Z")) yray[cl1]=X[cl1].getB().getZ_Angle() ;
            if(rozmer.equals( "REAL B Z")) yray[cl1]=X[cl1].getB().getZ_Real() ;
            if(rozmer.equals( "IMAGE B Z")) yray[cl1]=X[cl1].getB().getZ_Imaginary() ;
                    
        }
          if (BE =="E"){  
              
            if(rozmer.equals( "RMS E")) yray[cl1]=X[cl1].getE().getComplex_rms_ABS() ;
            if(rozmer.equals( "RMS E REAL")) yray[cl1]=X[cl1].getE().getComplex_rms().getReal() ;
            if(rozmer.equals( "RMS E IMAGE")) yray[cl1]=X[cl1].getE().getComplex_rms().getImaginary()  ;
            if(rozmer.equals( "RMS E ANGLE")) yray[cl1]=X[cl1].getE().getComplex_rms_Angle();  
                    
            if(rozmer.equals( "RMS E X")) yray[cl1]=X[cl1].getE().getX_ABS();
            if(rozmer.equals( "PHASE E X")) yray[cl1]=X[cl1].getE().getX_Angle() ;
            if(rozmer.equals( "REAL E X")) yray[cl1]=X[cl1].getE().getX_Real() ;
            if(rozmer.equals( "IMAGE E X")) yray[cl1]=X[cl1].getE().getX_Imaginary();
            
            if(rozmer.equals( "RMS E Y")) yray[cl1]=X[cl1].getE().getY_ABS();
            if(rozmer.equals( "PHASE E Y")) yray[cl1]=X[cl1].getE().getY_Angle() ;
            if(rozmer.equals( "REAL E Y")) yray[cl1]=X[cl1].getE().getY_Real() ;
            if(rozmer.equals( "IMAGE E Y")) yray[cl1]=X[cl1].getE().getY_Imaginary() ;
            
            if(rozmer.equals( "RMS E Z")) yray[cl1]=X[cl1].getE().getZ_ABS();
            if(rozmer.equals( "PHASE E Z")) yray[cl1]=X[cl1].getE().getZ_Angle() ;
            if(rozmer.equals( "REAL E Z")) yray[cl1]=X[cl1].getE().getZ_Real() ;
            if(rozmer.equals( "IMAGE E Z")) yray[cl1]=X[cl1].getE().getZ_Imaginary() ;
                    
        }
            
        }
        
        return yray; 
    }
    
    public double[] getXray1D(String XYZ, Observer[] X) {
          double[] xray=new double[X.length];   
        
        for (int cl1 = 0; cl1 < X.length; cl1++) {
          
             if(XYZ.equals( "X" ))  xray[cl1]=X[cl1].getPoloha().getX();
               if(XYZ.equals( "Y" )) xray[cl1]=X[cl1].getPoloha().getY();
                 if(XYZ.equals( "Z" ))  xray[cl1]=X[cl1].getPoloha().getZ();
             
        }    
        return xray;  
    }
    
    public ArrayList<double[]> getYray1DList(String BE ,String rozmer,ArrayList<Observer[]> X){
     
        ArrayList<double[]> yray1DList = new ArrayList<>();
        
        for(int cl1=0; cl1<X.size();cl1++){
            
            yray1DList.add(getYray1D(BE, rozmer, X.get(cl1)));
            
        }
                
        return yray1DList;
        
    }
    
    public String[] getYray_height_name( ArrayList<Observer[]> X) {
         String[] yray=new String[X.size()];
        
        for (int cl1 = 0; cl1 < X.size(); cl1++) {
          
            yray[cl1]=String.valueOf(X.get(cl1)[0].getPoloha().getY()) + " m";
        }    
        return yray;  
    }
    
     //dorobit funkciu ktory vrati hodnoty pre pozorovatela z observera
     // dorobit funkciu ktory navrsty yray na seba ked je viac vysok
    // otestovat konečne kreslenie a vypocet a graficky vypocet
    // y fukciaonalizovat core B
    // vytvorit core 2D

    public ArrayList<Observer[]> getP1D_priecne() {
        return P1D_priecne;
    }

    public void setP1D_priecne(ArrayList<Observer[]> P1D_priecne) {
        this.P1D_priecne = P1D_priecne;
    }

    public ArrayList<Observer[]> getP1D_pozdlzne() {
        return P1D_pozdlzne;
    }

    public void setP1D_pozdlzne(ArrayList<Observer[]> P1D_pozdlzne) {
        this.P1D_pozdlzne = P1D_pozdlzne;
    }

    public ArrayList<Observer[]> getP1D_neurcite() {
        return P1D_neurcite;
    }

    public void setP1D_neurcite(ArrayList<Observer[]> P1D_neurcite) {
        this.P1D_neurcite = P1D_neurcite;
    }
    
    public void clear(){
        P1D_neurcite.clear();
        P1D_pozdlzne.clear();
        P1D_priecne.clear();
        

    }
    
}

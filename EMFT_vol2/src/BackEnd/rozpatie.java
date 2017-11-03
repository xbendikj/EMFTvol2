/*
 * TO DO SOME SORT OF CHECKER
 */
package BackEnd;

import java.util.ArrayList;
import org.jdelaunay.delaunay.error.DelaunayError;
import org.jdelaunay.delaunay.geometries.DTriangle;
import tools.help;

/**
 * 
 * @author Jozef
 */
 
public class rozpatie {
    private String meno;
    private String menoProjektu="";
    private double[] LCcoordinates1 = new double[3]; 
    private  double[] LCcoordinates2 = new double[3]; 
    private  double A;
    private  double Z;
    private  double Krok; // [m]
    private  double Krok_pozorovatela; // [m]
    private  boolean AppTeren; // pocitame teren alebo nie , služi naako ukazovatel pre fron ale aj na to či sa ide počitať
    private boolean deff;
    private boolean deff2;
    private boolean definedGCLC; // doby su cez FrontEnd su definovane priuam ako GC alebo LC default je LC ak bude GC prepočitaju sa a rovno sa zapišu ako LC ( body su len  v LC pre potreby tringulacie 
    private  ArrayList<retazovka> Retazovka = new ArrayList<>();
    private  ArrayList<DTriangle> Teren;
    private Tfield pole;
    private ArrayList<double[]>  body = new  ArrayList<>();
    private ArrayList<double[]>  bodyGC = new  ArrayList<>();
    private triangulacia terenTriangulacia; // nemusi sa savevovat naplni ažo po generovani terenu
    private boolean CreatedTerrain = false;
    // empty constructor
    public rozpatie() {
        
    //definovat default local system
        LCcoordinates1[0]=0; // X 
        LCcoordinates1[1]=0; // X 
        LCcoordinates1[2]=0; // X     
    }
    /**
     * buil default rozpatie, default l.c. , krok je defaultne nasrtavena na 1000mm ale použivame len 1m
     * @param meno nazov rozpatia
     * @param A dlzky rozpatia A rozmer
     * @param Z priecna dlzka od nuly!! od stredu
     * @param deff  false ( podla bodov ) Ake bude vytvorenie hran true = ( stvorec ) default value = true
     * @param deff2 false priratavam vyšku LC[1] ku každemu bodu  ( teda body už neobsahuju tuto vyku a je od každeho odčitana ) true = vkladam nulu teda body už pbsahuju aj vyšku default value = false
     * @param Appteren Aplikovat realny teren ? ( default false
     */
    public rozpatie(String meno,String menoprojektu,double A,double Z, double krok, double krok_pozorovatela) {
        this.meno=meno;
        this.menoProjektu=menoprojektu;
        this.A=A;
        this.Z=Z;
        this.Krok = krok/1000; //  defaultna hodnota roku
        this.Krok_pozorovatela=krok_pozorovatela;
        this.AppTeren=false;
        this.deff = true; // default podla stvorca hanice prietoru
        this.deff2 = false;
        this.definedGCLC = false; //false = LC , True = GC
        //definovat default local system
        LCcoordinates1[0]=0; // X 
        LCcoordinates1[1]=0; // X 
        LCcoordinates1[2]=0; // X 
        
        LCcoordinates2[0]=A; // X  //  default druhy bod LC
        LCcoordinates2[1]= LCcoordinates1[1]; // y
        LCcoordinates2[2]=0; // z
        
    }

    public String getMenoProjektu() {
        return menoProjektu;
    }

    public void setMenoProjektu(String menoProjektu) {
        this.menoProjektu = menoProjektu;
    }

    public boolean isCreatedTerrain() {
        return CreatedTerrain;
    }

    public void setCreatedTerrain(boolean CreatedTerrain) {
        this.CreatedTerrain = CreatedTerrain;
    }

    public ArrayList<double[]> getBodyGC() {
        return bodyGC;
    }

    public void setBodyGC(ArrayList<double[]> bodyGC) {
        this.bodyGC = bodyGC;
    }

    public ArrayList<double[]> getBody() {
        return body;
    }

    public void setBody(ArrayList<double[]> body) {
        this.body = body;
    }

    /**
     * vytvorenie terenu pomocou triangulacie, POZOR Vsetko sa pocitna v Lokalnych suradniciach  ALE vstupuju to aj poloha LC  v globalnych pretože je potebna vyška na offset
     * @param A dlzka rozpatia ( plochy )
     * @param Z priecna dlzka plochy
     * @param body body XYZ
     * @param body arraylist double[3] musi byt v LC
     * @param deff  false ( podla bodov ) Ake bude vytvorenie hran true = ( stvorec ) default value = true
     * @param deff2 false priratavam vyšku LC[1] ku každemu bodu  ( teda body už neobsahuju tuto vyku a je od každeho odčitana ) true = vkladam nulu teda body už pbsahuju aj vyšku default value = false
    **/
    public void generateTerrain() throws DelaunayError{
   
    ArrayList<double[]>  bodiky = new ArrayList<>(this.body);
    triangulacia terentringulacia = new triangulacia(this.A, this.Z, bodiky,this.deff,LCcoordinates1,this.deff2,this.AppTeren);
   
    terentringulacia.setLCcoordinates(LCcoordinates1);
    terentringulacia.run();
    terenTriangulacia=terentringulacia;
    Teren = terentringulacia.getTriangles(); // ziskame trojuholiky
    pole = new Tfield(Teren); // vytvorime pole
    
    CreatedTerrain= true;
    

    
}

    public triangulacia getTerenTriangulacia() {
        return terenTriangulacia;
    }

    public void setTerenTriangulacia(triangulacia terenTriangulacia) {
        this.terenTriangulacia = terenTriangulacia;
    }
    /**
     * 
     * @return trojuholniky  terentringulacia.getTriangles();
     */
    public ArrayList<DTriangle> getTeren() {
        return Teren;
    }

    public void setTeren(ArrayList<DTriangle> Teren) {
        this.Teren = Teren;
    }

    public Tfield getPole() {
        return pole;
    }

    public void setPole(Tfield pole) {
        this.pole = pole;
    }
    
    /**
     *  pregeneruje retazovky vsetky pri zmene terenu
     * @throws DelaunayError 
     */
    public void RegenerateRetazovky() throws DelaunayError{
        
        
            ArrayList<retazovka> RetazovkaHelp = null;
            
        for(int cl1 = 0 ; cl1<this.Retazovka.size();cl1++){
         
            retazovka R = new retazovka(Retazovka.get(cl1), Teren);
            RetazovkaHelp.add(R);
        }
            this.Retazovka = RetazovkaHelp; 
        
    }

    public boolean isDefinedGCLC() {
        return definedGCLC;
    }

    public void setDefinedGCLC(boolean definedGCLC) {
        this.definedGCLC = definedGCLC;
    }

    public boolean isDeff() {
        return deff;
    }
    /**
     * ako sa bude generoavt teren false == podla bodov convexx hull, true = stvorec
     * @param deff 
     */
    public void setDeff(boolean deff) {
        this.deff = deff;
    }

    public boolean isDeff2() {
        return deff2;
    }

    public void setDeff2(boolean deff2) {
        this.deff2 = deff2;
    }

    public boolean isAppTeren() {
        return AppTeren;
    }

    public void setAppTeren(boolean AppTeren) {
        this.AppTeren = AppTeren;
    }

    public double getKrok() {
        return Krok;
    }

    /**
     * nastava krok pozorovatela  hodnota sa deli 1000 v fielde je definovana v mm
     * @param Krok 
     */
    public void setKrok(double Krok) {
        this.Krok = Krok/1000;
    }
    
    public  String getMeno() {
        return meno;
    }

    public  void setMeno(String meno) {
        this.meno = meno;
    }

    public  double[] getLCcoordinates1() {
        return LCcoordinates1;
    }

    public double[] getLCcoordinates2() {
        return LCcoordinates2;
    }
    
    /**
     * predefinuj l.c.  vyska pre LC 2 sa dopocita, resp je totozna ako vyska pre LC1
     * @param X corr LC1
     * @param Y corr LC1 
     * @param Z corr LC1
     * @param X2 corr LC2 
     * @param Z2 corr LC2   
     *  
     */
    public void setLCcoordinates(double X,double Y,double Z,double X2,double Z2) {
        LCcoordinates1[0] = X;
        LCcoordinates1[1] = Y;
        LCcoordinates1[2] = Z;
        
        LCcoordinates2[0] = X2; 
        LCcoordinates2[1] =  LCcoordinates1[1] ;
        LCcoordinates2[2] = Z2;
       recalculateA();
        
    }
    public void recalculateA(){
       // help.info("Nastala Zmena A pretože bolo definovane LC mimo deff", true);
        this.A = Math.sqrt(Math.pow(LCcoordinates2[0]- LCcoordinates1[0], 2) + Math.pow(LCcoordinates2[2]- LCcoordinates1[2], 2));
    }

    public  double getA() {
        return A;
    }

    public  void setA(double A) {
        this.A = A;
    }

    public  double getZ() {
        return Z;
    }

    public  void setZ(double Z) {
        this.Z = Z;
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

    public  void setRetazovkaArrayList(ArrayList<retazovka> Retazovka) {
        this.Retazovka = Retazovka;
    }
   
    

    public void erazeRetazovkaArrayList() {
        this.Retazovka.removeAll(this.Retazovka);
    }

    public double getKrok_pozorovatela() {
        return Krok_pozorovatela;
    }

    public void setKrok_pozorovatela(double Krok_pozorovatela) {
        this.Krok_pozorovatela = Krok_pozorovatela;
    }

   
    
    
}

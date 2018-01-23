/*
 * TO DO SOME SORT OF CHECKER
 */
package BackEnd;

import emft_vol2.constants;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;
import org.jdelaunay.delaunay.error.DelaunayError;
import org.jdelaunay.delaunay.geometries.DPoint;
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
    
    private ArrayList<Integer> polohy_lan = new  ArrayList<>();    
    private RealMatrix Tau_real  ;
    private RealMatrix Tau_image ;

    private ArrayList<RealMatrix> Tau_real_mat = new ArrayList<RealMatrix>();
    private ArrayList<RealMatrix> Tau_image_mat = new ArrayList<RealMatrix>();
       
    
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

    public ArrayList<Integer> getPolohy_lan() {
        return polohy_lan;
    }

    public RealMatrix getTau_real() {
        return Tau_real;
    }

    public RealMatrix getTau_image() {
        return Tau_image;
    }

    public ArrayList<RealMatrix> getTau_real_mat() {
        return Tau_real_mat;
    }

    public void setTau_real_mat(ArrayList<RealMatrix> Tau_real_mat) {
        this.Tau_real_mat = Tau_real_mat;
    }

    public ArrayList<RealMatrix> getTau_image_mat() {
        return Tau_image_mat;
    }

    public void setTau_image_mat(ArrayList<RealMatrix> Tau_image_mat) {
        this.Tau_image_mat = Tau_image_mat;
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

  
    /**
     *   vystupom by mal byt tap pre kazdy element, tieto tau budu vložene do retazovky kazdej jednej
         toto je možne robit až ked maju všetky lana vygenerovane R0 a R0 mirror 
         uklada Tau podla specifickeho poradia do jednej veeeeelkej Tau pičky
     * @throws DelaunayError 
     */
    public void calculateTau() throws DelaunayError{
        
        // docasne testovacie
        Scanner keyboard = new Scanner(System.in);
        System.out.println("0 - infinite 1 - pkj 2 - 0");
        int myint = keyboard.nextInt();
        
        //int myint = 1;
        //prv arraylist , vo vnorenom, lano druhy vektor
        ArrayList<DPoint> ListOfR0 = new ArrayList<DPoint>();
        ArrayList<DPoint> ListOfR0_mirror = new ArrayList<DPoint>();    
       // this.Tau_image.
       
        double[] U_real_array = null;
        double[] U_image_array = null;
        ArrayList<Double> U_real_list = new ArrayList<Double>();
        ArrayList<Double> U_image_list = new ArrayList<Double>();
        ArrayList<Integer> polohy_lan  = new ArrayList<Integer>();
        ArrayList<Double> polomery_lan  = new ArrayList<Double>();
        
        //iteratory
        int iterator_lan = 0;
        int elementarny_iterator=0;
        //basic priprava vektorov pre okienka matice
        for (int cl1 = 0; cl1 < getRetazovkaList().size(); cl1++) {

                    //cyklus bundle   
                    for (int cl2 = 0; cl2 < getRetazovkaList().get(cl1).getBundle_over(); cl2++) {

                              ArrayList<DPoint> R0_vectors_per_lano = new ArrayList<DPoint>(getRetazovka(cl1).getRo_vectors()); // nacitam jedno lano a upravim ho podla bundle konstant
                              ArrayList<DPoint> R0_mirror_vectors_per_lano = new ArrayList<DPoint>(getRetazovka(cl1).getRo_mirror_vectors());
                              polohy_lan.add(elementarny_iterator);
                             // cyklus 
                             for ( int cl3=0;cl3<getRetazovka(cl1).getRo_vectors().size();cl3++){

                                   //  bundle korektura pre jeden druhy SMER  a korekcia
                                   double Y = (double) R0_vectors_per_lano.get(cl3).getY() + (double)getRetazovkaList().get(cl1).getZY_cor_Bundle()[1][cl2];
                                   double Z = (double) R0_vectors_per_lano.get(cl3).getZ() + (double)Math.cos(getRetazovkaList().get(cl1).getBeta_over())*getRetazovkaList().get(cl1).getZY_cor_Bundle()[0][cl2];
                                   double X = (double) R0_vectors_per_lano.get(cl3).getX() + (double)Math.sin(getRetazovkaList().get(cl1).getBeta_over())*getRetazovkaList().get(cl1).getZY_cor_Bundle()[0][cl2];
                                   DPoint R0 = new DPoint(X, Y, Z);
                                    
                                   
                                   //mirrorovanie len jedneho rozmeru pozor
                                   double Ym = (double) R0_mirror_vectors_per_lano.get(cl3).getY() - (double)getRetazovkaList().get(cl1).getZY_cor_Bundle()[1][cl2];
                                   double Zm = (double) R0_mirror_vectors_per_lano.get(cl3).getZ() + (double)Math.cos(getRetazovkaList().get(cl1).getBeta_over())*getRetazovkaList().get(cl1).getZY_cor_Bundle()[0][cl2];
                                   double Xm = (double) R0_mirror_vectors_per_lano.get(cl3).getX() + (double)Math.sin(getRetazovkaList().get(cl1).getBeta_over())*getRetazovkaList().get(cl1).getZY_cor_Bundle()[0][cl2];
                                   DPoint R0m = new DPoint(Xm, Ym, Zm);
                                    
                                   // nastavy U real a image do separatnych listov podla toho na akom vodiči je ( tu sa bundle ignoruje )
                                   U_real_list.add(get_real(getRetazovkaList().get(cl1).getU_over(), getRetazovkaList().get(cl1).getPhi_over() )/Math.sqrt(3));
                                   U_image_list.add(get_image(getRetazovkaList().get(cl1).getU_over(), getRetazovkaList().get(cl1).getPhi_over() )/Math.sqrt(3)); 
                                   // nastavy polomery do jedej arraylistu
                                  polomery_lan.add(getRetazovkaList().get(cl1).getR_over() ); 
                                  ListOfR0.add(new DPoint(R0)); 
                                  ListOfR0_mirror.add(new DPoint( R0m));
                                  elementarny_iterator = elementarny_iterator + 1; 
                               }
                                

                        // celkovy pocet vyp vodicov
                       
                        // arraylist ( RO a RO mirror ), ktory obsahuje arraylistyk lan. prvy arraylist je zoznam vodičov a druhy arralist obsahuje vektory Dpoint jednolivyvh vodičov
                        
                        iterator_lan = iterator_lan + 1;
                    }
         
                }
             polohy_lan.add(elementarny_iterator); // posledna hodnota 
       
        //inicializacia matic
        RealMatrix  P_koef = new Array2DRowRealMatrix(new double[elementarny_iterator][elementarny_iterator]);//matica Pkoeficientov vsetkych ROW COLUMN
        RealMatrix  U_real = new Array2DRowRealMatrix(new double[elementarny_iterator][1]); // matica realnych hodnot napatia
        RealMatrix U_image = new Array2DRowRealMatrix(new double[elementarny_iterator][1]); // matica imaginarnch hodnot napatia
        RealMatrix  Tau_real = new Array2DRowRealMatrix(new double[elementarny_iterator][1]); // matica ireal hodnot Tau
        RealMatrix  Tau_image = new Array2DRowRealMatrix(new double[elementarny_iterator][1]); // matica imaginarnych hodnot Tau
        
      
        
        //naplnenie matic
        
        U_real.setColumn(0, help.Double_Arraylist_to_DoubleArray(U_real_list));
        U_image.setColumn(0, help.Double_Arraylist_to_DoubleArray(U_image_list));
        
        System.out.println("pocet lan" + iterator_lan);
        System.out.println("pocet elementov" + elementarny_iterator);
        

        //algoritmus generovania matice
        //*****************************
        double K = 1/(2*Math.PI*constants.getEpsi0()*constants.getEpsi1()); // konštanta
        // /* TEST HORAK */ K = 1;
        int lano_iterator = 0;
        // pocitanie potenialovej matice matice
        for (int k = 0; k < P_koef.getRowDimension(); k++) {

            //definuj hornu a dolnu hranicu pre nulu
            double horna_hranica = polohy_lan.get(lano_iterator);
            double dolna_hranica = polohy_lan.get(lano_iterator + 1);

            for (int j = 0; j < P_koef.getRowDimension(); j++) {
                double koeficient = 0;
                if (k == j) {
                    koeficient = get_Pkk(K, ListOfR0.get(k).getY(), polomery_lan.get(k));
                } else // PKJ   
                {
                    if (j >= horna_hranica && j < dolna_hranica) {

                      if(myint == 0) koeficient = Double.POSITIVE_INFINITY;// Inverzna matica len NaN
                      if(myint == 1) koeficient = get_Pkj(K, get_distance(ListOfR0.get(k), ListOfR0_mirror.get(j)), get_distance(ListOfR0.get(k), ListOfR0.get(j))); //inverzna matica funguje
                      if(myint == 2) koeficient = 0; //inverzna matica funguje

                    } else {
                        koeficient = get_Pkj(K, get_distance(ListOfR0.get(k), ListOfR0_mirror.get(j)), get_distance(ListOfR0.get(k), ListOfR0.get(j)));
                    }
                }
                P_koef.addToEntry(k, j, koeficient);
            }

            if (k == dolna_hranica - 1) {
                lano_iterator++;
            }

        }
        //END algoritmus generovania matice
        //*********************************
        // vypisi matice
//        System.out.println("BackEnd.rozpatie.calculateTau()");
//        for (int i = 0; i < P_koef.getRowDimension(); i++) {
//            for (int j = 0; j < P_koef.getRowDimension(); j++) {
//                System.out.print(P_koef.getData()[i][j] + " ");
//            }
//
//            System.out.println();
//
//        }
//                System.out.println("BackEnd.rozpatie.calculateTau()");
//        for (int i = 0; i < P_koef.getRowDimension(); i++) {
//            for (int j = 0; j < P_koef.getRowDimension(); j++) {
//                System.out.print(P_koef.getData()[i][j] + " ");
//            }
//
//            System.out.println();
//
//        }
        
        
          P_koef=P_koef.inverse();
          
          
          Tau_real=P_koef.multiply(U_real);
          Tau_image=P_koef.multiply(U_image);
          
          //for(int cl1 =0 ;cl1<Tau_image.getRowDimension();cl1++){
          
          this.Tau_real=Tau_real;
          this.Tau_image=Tau_image;
              
         // }
          
          
          this.polohy_lan=polohy_lan;
//        System.out.println("BackEnd.rozpatie.calculateTau() INVERZNA");
//        for (int i = 0; i < Tau_real.getRowDimension(); i++) {
//    for (int j = 0; j < Tau_real.getColumnDimension(); j++) {
//        System.out.print(Tau_real.getData()[i][j] + " ");
//    }
//    System.out.println();
//}
    }
    
     public void calculateTau_OLD() throws DelaunayError{
        
        // docasne testovacie
        
        
        //int myint = 1;
        //prv arraylist , vo vnorenom, lano druhy vektor
        ArrayList<ArrayList<DPoint>> ListOfR0 = new ArrayList<ArrayList<DPoint>>();
        ArrayList<ArrayList<DPoint>> ListOfR0_mirror = new ArrayList<ArrayList<DPoint>>();
        
        ArrayList<RealMatrix> Tau_real_mat = new ArrayList<RealMatrix>();
        ArrayList<RealMatrix> Tau_image_mat = new ArrayList<RealMatrix>();
       
        
        ArrayList<Double> U_real_list = new ArrayList<Double>();
        ArrayList<Double> U_image_list = new ArrayList<Double>();
        ArrayList<Integer> polohy_lan  = new ArrayList<Integer>();
        ArrayList<Double> polomery_lan  = new ArrayList<Double>();
        
        //iteratory
        int iterator_lan = 0;
        int elementarny_iterator=0;
        //basic priprava vektorov pre okienka matice
        for (int cl1 = 0; cl1 < getRetazovkaList().size(); cl1++) {

                    //cyklus bundle   
                    for (int cl2 = 0; cl2 < getRetazovkaList().get(cl1).getBundle_over(); cl2++) {

                              ArrayList<DPoint> R0_vectors_per_lano = new ArrayList<DPoint>(getRetazovka(cl1).getRo_vectors()); // nacitam jedno lano a upravim ho podla bundle konstant
                              ArrayList<DPoint> R0_mirror_vectors_per_lano = new ArrayList<DPoint>(getRetazovka(cl1).getRo_mirror_vectors());
                              polohy_lan.add(elementarny_iterator);
                             // cyklus 
                             for ( int cl3=0;cl3<getRetazovka(cl1).getRo_vectors().size();cl3++){

                                   //  bundle korektura pre jeden druhy SMER  a korekcia
                                   double Y = (double) R0_vectors_per_lano.get(cl3).getY() + (double)getRetazovkaList().get(cl1).getZY_cor_Bundle()[1][cl2];
                                   double Z = (double) R0_vectors_per_lano.get(cl3).getZ() + (double)Math.cos(getRetazovkaList().get(cl1).getBeta_over())*getRetazovkaList().get(cl1).getZY_cor_Bundle()[0][cl2];
                                   double X = (double) R0_vectors_per_lano.get(cl3).getX() + (double)Math.sin(getRetazovkaList().get(cl1).getBeta_over())*getRetazovkaList().get(cl1).getZY_cor_Bundle()[0][cl2];
                                   DPoint R0 = new DPoint(X, Y, Z);
                                   R0_vectors_per_lano.set(cl3, R0);
                                   
                                   //mirrorovanie len jedneho rozmeru pozor
                                   double Ym = (double) R0_mirror_vectors_per_lano.get(cl3).getY() - (double)getRetazovkaList().get(cl1).getZY_cor_Bundle()[1][cl2];
                                   double Zm = (double) R0_mirror_vectors_per_lano.get(cl3).getZ() + (double)Math.cos(getRetazovkaList().get(cl1).getBeta_over())*getRetazovkaList().get(cl1).getZY_cor_Bundle()[0][cl2];
                                   double Xm = (double) R0_mirror_vectors_per_lano.get(cl3).getX() + (double)Math.sin(getRetazovkaList().get(cl1).getBeta_over())*getRetazovkaList().get(cl1).getZY_cor_Bundle()[0][cl2];
                                   DPoint R0m = new DPoint(Xm, Ym, Zm);
                                   R0_mirror_vectors_per_lano.set(cl3, R0m); 
                                   
                                  
                                 
                                  elementarny_iterator = elementarny_iterator + 1; 
                               }
                                  ListOfR0.add( new ArrayList<DPoint>(R0_vectors_per_lano)); 
                                  ListOfR0_mirror.add(new ArrayList<DPoint>(R0_mirror_vectors_per_lano));
                                  U_real_list.add(get_real(getRetazovkaList().get(cl1).getU_over(), getRetazovkaList().get(cl1).getPhi_over() )/Math.sqrt(3));
                                  U_image_list.add(get_image(getRetazovkaList().get(cl1).getU_over(), getRetazovkaList().get(cl1).getPhi_over() )/Math.sqrt(3)); 
                                  polomery_lan.add(getRetazovkaList().get(cl1).getR_over() ); 
                       
                        iterator_lan = iterator_lan + 1;
                    }
         
                }
             polohy_lan.add(elementarny_iterator); // posledna hodnota 
       
        //inicializacia matic
        RealMatrix  P_koef = new Array2DRowRealMatrix(new double[iterator_lan][iterator_lan]);//matica Pkoeficientov vsetkych ROW COLUMN
        RealMatrix  U_real = new Array2DRowRealMatrix(new double[iterator_lan][1]); // matica realnych hodnot napatia
        RealMatrix U_image = new Array2DRowRealMatrix(new double[iterator_lan][1]); // matica imaginarnch hodnot napatia
        RealMatrix  Tau_real = new Array2DRowRealMatrix(new double[iterator_lan][1]); // matica ireal hodnot Tau
        RealMatrix  Tau_image = new Array2DRowRealMatrix(new double[iterator_lan][1]); // matica imaginarnych hodnot Tau
        
      
        
        //naplnenie matic
        
        U_real.setColumn(0, help.Double_Arraylist_to_DoubleArray(U_real_list));
        U_image.setColumn(0, help.Double_Arraylist_to_DoubleArray(U_image_list));
        
        System.out.println("pocet lan" + iterator_lan);
        //System.out.println("pocet elementov" + elementarny_iterator);
        

        //algoritmus generovania matice
        //*****************************
        double K = 1/(2*Math.PI*constants.getEpsi0()*constants.getEpsi1()); // konštanta
        // /* TEST HORAK */ K = 1;
         for (int element_iterator = 0; element_iterator < getRetazovka(0).getRo_vectors().size(); element_iterator++) {
             // pocitanie potenialovej matice matice
             for (int k = 0; k < P_koef.getRowDimension(); k++) {

                 for (int j = 0; j < P_koef.getRowDimension(); j++) {
                     double koeficient = 0;
                     if (k == j) {
                         koeficient = get_Pkk(K, ListOfR0.get(j).get(element_iterator).getY(), polomery_lan.get(k));
                     } else { // PKJ   

                         koeficient = get_Pkj(K, get_distance(ListOfR0.get(k).get(element_iterator), ListOfR0_mirror.get(j).get(element_iterator)), get_distance(ListOfR0.get(k).get(element_iterator), ListOfR0.get(j).get(element_iterator))); //inverzna matica funguje

                     }
                     P_koef.addToEntry(k, j, koeficient);
                 }

             }
             
       
             
             P_koef = P_koef.inverse();
             
//             System.out.println("BackEnd.rozpatie.calculateTau() OLD INERSE");
//        for (int i = 0; i < P_koef.getRowDimension(); i++) {
//            for (int j = 0; j < P_koef.getRowDimension(); j++) {
//                System.out.print(P_koef.getData()[i][j] + " ");
//            }
//
//            System.out.println();
//
//        }
             
             Tau_real = P_koef.multiply(U_real);
             Tau_image = P_koef.multiply(U_image);
             Tau_real_mat.add(Tau_real);
             Tau_image_mat.add(Tau_image);

//                   System.out.println("BackEnd.rozpatie.calculateTau() real posledne" );
//            for (int i = 0; i < Tau_real.getRowDimension(); i++) {
//            for (int j = 0; j < Tau_real.getColumnDimension(); j++) {
//                System.out.print(Tau_real.getData()[i][j] + " ");
//            }
//
//            System.out.println();
//
//        }
//                    System.out.println("BackEnd.rozpatie.calculateTau() Image posledne" );
//            for (int i = 0; i < Tau_image.getRowDimension(); i++) {
//            for (int j = 0; j < Tau_image.getColumnDimension(); j++) {
//                System.out.print(Tau_real.getData()[i][j] + " ");
//            }
//            System.out.println();
//
//        }
             
             
             this.Tau_real_mat=Tau_real_mat;
             this.Tau_image_mat=Tau_image_mat;
         }

        
     
    }
    
        private double get_real(double value,double phase){
        double real = value*Math.cos(phase*(Math.PI/180));
        return real;
    }
        private double get_image(double value,double phase){
        double image = value*Math.sin(phase*(Math.PI/180));
        return image;
    }
        private double get_Pkk(double K,double Hk,double Rk){
        double val= K*Math.log((2*Hk)/Rk);
        return val;
    }
        private double get_Pkj(double K,double Dkj_image,double Dkj){
        double val= K*Math.log((Dkj_image)/Dkj);
        return val;
    }
        private double get_distance(DPoint A,DPoint B){
        double X = B.getX() - A.getX();
        double Y = B.getY() - A.getY();
        double Z = B.getZ() - A.getZ();
        double val = Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2)  +Math.pow(Z, 2)   );
        return val;
    } 
    
    
}

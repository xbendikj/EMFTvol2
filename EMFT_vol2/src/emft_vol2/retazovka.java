/*
 * prirobit metody na
   Amod,
   C//H vypocet
   F_vektor (x) = x
   nebude leošie zadavat V1V2Z1Z2 .. ako point v LC ?
 */
package emft_vol2;

import java.util.ArrayList;
import org.jdelaunay.delaunay.error.DelaunayError;
import org.jdelaunay.delaunay.geometries.DPoint;
import org.jdelaunay.delaunay.geometries.DTriangle;
import tools.help;
import tools.warning_jDialog;

/**
 *
 * @author Jozef
 */
public class retazovka {
    
    private static double V1_over; 
    private static double V2_over;
    private static double I1_over;
    private static double I2_over;
    private static double W1_over;
    private static double W2_over;
    private static double X1_over;
    private static double X2_over;
    
    private static DPoint P1_over; 
    private static DPoint P2_over; 
    
    private static int bundle_over;
    private static double alpha_over;
    
    private static double HC_over;
    
    private static double H_over;
    private static double Hter_over; // hodnotene len v mieste maximálneho prihybu
    private static double C_over;
    private static double Amod_over;
    private static double Beta_over;
    private static double A1_over;
    
    private static double r_over;
    private static double U_over;
    private static double I_over;
    private static double Phi_over;
    
    private static boolean isUtopeny=false ;
    private ArrayList<DTriangle> terenTriangles_over;
    
    //constructor
    /**
     * 
     * @param V1 vyska zavesneho bodu 1
     * @param V2 vyska zavesneho bodu 2
     * @param I1 vyska izolator1
     * @param I2 vyska izolator2
     * @param W1 vylozenie zavesneho bodu 1
     * @param W2 vylozenie zavesneho bodu 2
     * @param X1 zavesneho bodu 1 v osi X deff treba dat nulu
     * @param X2 zavesneho bodu 2 v osi X deff treba dat A
     * @param bundle zvazok
     * @param alpha uhol otocenia zvazku
     * @param HC cislena hodnota H alebo C
     * @param CorH true C alebo false H
     * @param Amod modifikovane rozpatie
     * @param r polomer vodiča
     * @param U združene napatie
     * @param I prud
     * @param Phi faza
     * @param terentriangle array of triangles
     * AK je zadana hodnota H tak je to minimalny výška nad nulovým terénom pre LC 000 !!
     */
    public retazovka(   
     double V1, double V2, double I1,double I2,
     double W1, double W2,double X1, double X2,
     int bundle, double alpha,  
     double HC, boolean CorH, double r,
     double U,double I,double Phi,ArrayList<DTriangle> terenTriangles 
    ) throws DelaunayError
    {   
    V1_over=V1;V2_over=V2;I1_over=I1;I2_over=I1;W1_over=W1;W2_over=W2;bundle_over=bundle;alpha_over=alpha;
    HC_over=HC;r_over=r;U_over=U;I_over=I;Phi_over=Phi; terenTriangles_over =  terenTriangles;
    X1_over = X1; X2_over = X2;
    
    P1_over = new DPoint(0, 0, 0);
    P2_over = new DPoint(0, 0, 0);
    
    
    P1_over.setX(X1_over);
    P2_over.setX(X2_over);
    
    P1_over.setY(V1_over);
    P2_over.setY(V2_over);
    
    P1_over.setZ(W1_over);
    P2_over.setZ(W2_over);
    
     generate(HC, CorH);

    
    };
    
    public retazovka(   
     DPoint P1, DPoint P2,     
     double I1,double I2,
     int bundle, double alpha,  
     double HC, boolean CorH, double r,
     double U,double I,double Phi,ArrayList<DTriangle> terenTriangles  
    ) throws DelaunayError
    {   
         
    I1_over=I1;I2_over=I1;bundle_over=bundle;alpha_over=alpha;
    HC_over=HC;r_over=r;U_over=U;I_over=I;Phi_over=Phi; terenTriangles_over =  terenTriangles;
    
    P1_over = new DPoint(0, 0, 0);
    P2_over = new DPoint(0, 0, 0);
    P1_over=P1; P2_over=P2;
    
    
    X1_over=P1_over.getX();
    X2_over=P2_over.getX();
    
    V1_over=P1_over.getY();
    V2_over=P2_over.getY();
    
    W1_over=P1_over.getZ();
    W2_over=P2_over.getZ();
    
   generate(HC, CorH);
    
    };

    private void generate(double HC, boolean CorH) throws DelaunayError{
        
        
    Tfield BOD = new Tfield(terenTriangles_over);  // zober trojuholniky najdy  v ktorom s nachadza bod dopočitaj jeho vyšku vzhladom na teren
    P1_over=BOD.getYaboveTer(P1_over);       // najdy vysku zavesneho bodu na terene above teren 
    P2_over=BOD.getYaboveTer(P2_over);      // najdy vysku zavesneho bodu na terene above teren 
    
    P1_over.setY(P1_over.getY()-I1_over ); // zniž zavesny bod a vyšku izolatora
    P2_over.setY(P2_over.getY()-I2_over ); // zniž zavesny bod a vyšku izolatora
    
    Amod_over=Amod(P1_over, P2_over); // AMOD
    Beta_over=Beta(P1_over, P2_over); // Beta angle caltulation
    
    if( CorH== true){                  // vypočet C
        C_over = HC;                  // nastavi C
        H_over = Hcalculation(P1_over, P2_over, Amod_over, C_over);      // vypočtia H
        
        double A1x = A1_over*Math.cos(Beta_over) + P1_over.getX(); // priemet A1  do A1x osi plus odd set od OLS preto plus P1X --- P-Ret-1
        double A1z = A1_over*Math.sin(Beta_over) + P1_over.getZ(); // priemet A1  do A1x osi plus odd set od OLS preto plus P1X
        DPoint A1LC = new DPoint(A1x, 0, A1z);
        A1LC = BOD.getY(A1LC); // A1 v LC coor a priradenou výškou
        Hter_over=H_over-A1LC.getY();  // novy vyška nad nenulovým terenoom je povodna žiadana vyška plus prírastok od nenulového terenu
        
        if (Hter_over < 0){
        warning_jDialog help = new warning_jDialog("seka zem");
        help.setVisible(true);
        }
    }else{
        
        H_over =HC;
        C_over = CfromHcalculation(P1_over, P2_over, Amod_over, H_over); // dopocita C na zaklade H
        
        double A1x = A1_over*Math.cos(Beta_over) + P1_over.getX(); // priemet A1  do A1x osi plus odd set od OLS preto plus P1X --- P-Ret-1
        double A1z = A1_over*Math.sin(Beta_over) + P1_over.getZ(); // priemet A1  do A1x osi plus odd set od OLS preto plus P1X
        
        DPoint A1LC = new DPoint(A1x, 0, A1z);
        A1LC = BOD.getY(A1LC); // A1 v LC coor a priradenou výškou
        Hter_over=HC+A1LC.getY();  // novy vyška nad nenulovým terenoom je povodna žiadana vyška plus prírastok od nenulového terenu
        C_over = CfromHcalculation(P1_over, P2_over, Amod_over, Hter_over); //dopocita C na zaklade H
    }
        
        
    }
    
    
    public double getHter_over() {
        return Hter_over;
    }

    private  double Amod (DPoint P1, DPoint P2){
        double Amod=Math.sqrt( Math.pow(P2.getX() - P1.getX(),2) + Math.pow(P2.getZ() - P1.getZ(), 2)  );
        return Amod;
    }
    
    private  double Beta (DPoint P1, DPoint P2){
        double Amod=Math.sqrt( Math.pow(P2.getX() - P1.getX(),2) + Math.pow(P2.getZ() - P1.getZ(), 2)  );
        double Beta_Angle=Math.asin( ((P2.getZ() - P1.getZ())/Amod) );
        return Beta_Angle;        
    }

    private double CfromHcalculation(DPoint P1, DPoint P2,double Amod,double H ){
        double C=1000;
        double Hnew=0;
        double Hold = H;
        while(true){            
            
            double K1 = Amod/(2*C);
            double K2 = help.AsinH((P2.getY()- P1.getY())/(2*C*Math.sinh(K1)));
            double A1 = Amod - (C*(K2+K1));
            A1_over =A1;    // sets the A1
            
            
            
            Hnew = P1.getY()-(C*(Math.cosh(A1/C))) + C;
            
            if( Math.abs(Hold-Hnew)<constants.getPresnostCH()) break; // kontrola ak podmienka plati chod von
            
            if(Hnew > Hold) {                                        //  ak nie tak nasav nove Cčko
                C=C-C/2;
            }else{
                C=C+C/2;
            }
           
        }
        if(A1_over<0 || A1_over>Amod_over) isUtopeny=true;          // ak je utopeny spozna to
        H_over=Hnew;
        return C;
        
    }

    public  double getA1_over() {
        return A1_over;
    }

    public  void setA1_over(double A1_over) {
        retazovka.A1_over = A1_over;
    }

    private  double Hcalculation(DPoint P1, DPoint P2,double Amod,double C ){
        
        double H=0;
  
            double K1 = Amod/(2*C);
            
            double K2 = help.AsinH((P2.getY()- P1.getY())/(2*C*Math.sinh(K1)));
           
            double A1 = Amod - (C*(K2+K1));
             A1_over =A1;              // sets the A1
            H = P1.getY()-(C*(Math.cosh(A1/C))) + C;
            if(A1_over<0 || A1_over>Amod_over) isUtopeny=true;   // ak je utopeny spozna to
            
        return H;
        
    }

    
    public  double getAlpha_over() {
        return alpha_over;
    }

    public  double getAmod_over() {
        return Amod_over;
    }

    public  int getBundle_over() {
        return bundle_over;
    }

    public  double getC_over() {
        return C_over;
    }

    public  double getH_over() {
        return H_over;
    }

    public  double getI1_over() {
        return I1_over;
    }

    public  double getI2_over() {
        return I2_over;
    }

    public  double getI_over() {
        return I_over;
    }

    public  DPoint getP1_over() {
        return P1_over;
    }

    public  DPoint getP2_over() {
        return P2_over;
    }

    public  double getPhi_over() {
        return Phi_over;
    }

    public  double getR_over() {
        return r_over;
    }

    public  double getU_over() {
        return U_over;
    }
    
    public  double getV1_over() {
        return V1_over;
    }

    public  double getV2_over() {
        return V2_over;
    }

    public  double getW1_over() {
        return W1_over;
    }

    public  double getW2_over() {
        return W2_over;
    }

    public  double getX1_over() {
        return X1_over;
    }

    public  double getX2_over() {
        return X2_over;
    }

    public  void setAlpha_over(double alpha_over) {
        retazovka.alpha_over = alpha_over;
    }

    public  void setAmod_over(double Amod_over) {
        retazovka.Amod_over = Amod_over;
    }

    public  void setBundle_over(int bundle_over) {
        retazovka.bundle_over = bundle_over;
    }

    public  void setC_over(double C_over) {
        retazovka.C_over = C_over;
    }

    public  void setH_over(double H_over) {
        retazovka.H_over = H_over;
    }

    public  void setI1_over(double I1_over) {
        retazovka.I1_over = I1_over;
    }

    public  void setI2_over(double I2_over) {
        retazovka.I2_over = I2_over;
    }

    public  void setI_over(double I_over) {
        retazovka.I_over = I_over;
    }

    public  void setP1_over(DPoint P1_over) {
        retazovka.P1_over = P1_over;
    }

    public  void setP2_over(DPoint P2_over) {
        retazovka.P2_over = P2_over;
    }

    public  void setPhi_over(double Phi_over) {
        retazovka.Phi_over = Phi_over;
    }

    public  void setR_over(double r_over) {
        retazovka.r_over = r_over;
    }

    public  void setU_over(double U_over) {
        retazovka.U_over = U_over;
    }

    public  void setV1_over(double V1_over) {
        retazovka.V1_over = V1_over;
    }

    public  void setV2_over(double V2_over) {
        retazovka.V2_over = V2_over;
    }

    public  void setW1_over(double W1_over) {
        retazovka.W1_over = W1_over;
    }

    public  void setW2_over(double W2_over) {
        retazovka.W2_over = W2_over;
    }

    public  void setX1_over(double X1_over) {
        retazovka.X1_over = X1_over;
    }

    public  void setX2_over(double X2_over) {
        retazovka.X2_over = X2_over;
    }
    
}

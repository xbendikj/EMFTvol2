/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.util.ArrayList;
import org.apache.commons.math.complex.Complex;
import org.jdelaunay.delaunay.error.DelaunayError;
import org.jdelaunay.delaunay.geometries.DPoint;
import tools.help;

/**PREROBIT TUTO CLASS ABY SLA POCITAT NIE LEN DB ALE AJ ROVNO B od jedného lana
 * vypočet DB
 * @author Jozef
 */
public class B_calculation {
    
    double mu0;
    double muR;
    double I;
    double phase;
    DPoint Rp;
    DPoint R0;
    DPoint deltaL;
    double R0_bundleY=0;
    double R0_bundleZ=0;
    
    ArrayList<DPoint> RP_vectors;
    ArrayList<DPoint> R0_vectors;
    ArrayList<DPoint> deltaL_vectors;
    FazorVektor B ;
    double[] geoVektor = new double[3];
    
    
    public B_calculation() {
    }
    
    /**
     * 
     * @param mu0  mu0
     * @param muR MUR
     * @param I  prud
     * @param phase faza
     * @param RP poloha pozorovatela
     * @param R0_vectors arralist vsetkých RP vektorov jedného lana
     * @param deltaL_vectors arraylist vsetkých DL vektorov jedneho lana
     * @param R0_Y korekcia bundke vodiča smer Y podla retazovky class
     * @param R0_Z korekcia bundke vodiča smer Z podla retazovky class 
     * @throws DelaunayError 
     */
    public B_calculation(double mu0, double muR, double I, double phase, DPoint RP, ArrayList<DPoint> R0_vectors, ArrayList<DPoint> deltaL_vectors,double R0_Z,double R0_Y) throws DelaunayError {
        this.mu0 = mu0;
        this.muR = muR;
        this.I = I;
        this.phase = phase;
        this.Rp = RP;
        this.R0_vectors = R0_vectors;
        this.deltaL_vectors = deltaL_vectors;
        this.R0_bundleY = R0_Y;
        this.R0_bundleZ = R0_Z;
        //System.out.println( " this.R0_bundleY " + this.R0_bundleY );
        //System.out.println( " this.R0_bundleZ " + this.R0_bundleZ );
        //korektura bundle
        
    }
    public void  run () throws DelaunayError{
        Complex NULA = new Complex(0, 0);
        FazorVektor B_v_miesteRp = new FazorVektor(NULA, NULA, NULA);
        
        //cyklus 
        for(int cl1 = 0; cl1<this.R0_vectors.size();cl1++){
            
             B_v_miesteRp.AddToFazorVektor( calc_DB(Rp, R0_vectors.get(cl1), deltaL_vectors.get(cl1)));
            
        }
        
        this.geoVektor[0] = B_v_miesteRp.getX_Real()/getI_real(); //X hodnota geovektora
        this.geoVektor[1] = B_v_miesteRp.getY_Real()/getI_real(); //Y hodnota geovektora
        this.geoVektor[2] = B_v_miesteRp.getZ_Real()/getI_real(); //Z hodnota geovektora
        this.B=B_v_miesteRp;
    }
    
    private FazorVektor calc_DB(DPoint Rp,DPoint R0,DPoint deltaL ) throws DelaunayError{
        DPoint R_0 = new DPoint(R0.getX(), R0.getY(), R0.getZ());
        R_0.setY(R0.getY() + R0_bundleY); //  bundle korektura pre jeden druhy SMER 
        R_0.setZ(R0.getZ() + R0_bundleZ);
         
      //  System.out.println( "R_0= " + R_0 );
      //  System.out.println( "Rp= " + Rp );
      //
     // System.out.println( "deltal= " + deltaL );
        double K = (this.mu0 * this.muR )/(4*Math.PI); // konštanta
        
        DPoint R = help.substract(Rp, R_0);  // rozdiel vektorov
        double menovatel = Math.pow(get_ABS(R), 3);
        FazorVektor deltaB = new FazorVektor(new Complex(0, 0),new Complex(0, 0),new Complex(0, 0));
        
        DPoint C =vektor_sucin(deltaL, R);
        
        deltaB.setX_Real( K * ((getI_real()* C.getX())/menovatel));
        deltaB.setY_Real( K * ((getI_real()* C.getY())/menovatel));
        deltaB.setZ_Real( K * ((getI_real()* C.getZ())/menovatel));

        deltaB.setX_Imaginary( K * ((getI_image()* C.getX())/menovatel));
        deltaB.setY_Imaginary( K * ((getI_image()* C.getY())/menovatel));
        deltaB.setZ_Imaginary( K * ((getI_image()* C.getZ())/menovatel));  
        
        return deltaB;
    }
    
    private double getI_real(){
        double I_real = I*Math.cos(phase*(Math.PI/180));
        return I_real;
    }
    private double getI_image(){
        double I_image = I*Math.sin(phase*(Math.PI/180));
        return I_image;
    }
    private double get_ABS(DPoint X){
        double ABS = Math.sqrt( Math.pow(X.getX(), 2) + Math.pow(X.getY(), 2) + Math.pow(X.getZ(), 2)  );
        return ABS;
    }
    /**
     * NO kadaj kokot čo to robi
     * @param A
     * @param B
     * @return vekktor C ako vektory sučin vektorov
     */
    private DPoint vektor_sucin ( DPoint A, DPoint B) throws DelaunayError{    
        DPoint C = new DPoint(0, 0, 0);
        
        C.setX( A.getY() * B.getZ() - A.getZ() * B.getY()  );
        C.setY( A.getZ() * B.getX() - A.getX() * B.getZ()  );
        C.setZ( A.getX() * B.getY() - A.getY() * B.getX()  );
        
        
        return C;
    }

    public FazorVektor getB() {
        return B;
    }

    public void setB(FazorVektor B) {
        this.B = B;
    }

    public double[] getGeoVektor() {
        return geoVektor;
    }

    public void setGeoVektor(double[] geoVektor) {
        this.geoVektor = geoVektor;
    }
     
    
    
    
}

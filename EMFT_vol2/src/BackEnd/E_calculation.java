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

/**vypocita E v mieste Rp od jedneho lana
 * @author Jozef
 */
public class E_calculation {
    
    double epsi0;
    double epsiR;
   
    DPoint Rp;
    DPoint R0;
    DPoint deltaL;
    double R0_bundleY=0;
    double R0_bundleZ=0;
    
    ArrayList<DPoint> RP_vectors;
    ArrayList<DPoint> R0_vectors;
    ArrayList<DPoint> R0m_vectors;
    ArrayList<DPoint> deltaL_vectors;
    ArrayList<Complex> Tau; // tau pre kazdy element
    FazorVektor E ;
    
    
    
    public E_calculation() {
    }
    
    /**
     * 
     * @param epsi0  mu0
     * @param epsiR MUR
     * @param U  prud
     * @param phase faza
     * @param RP poloha pozorovatela
     * @param R0_vectors arralist vsetkých RP vektorov jedného lana
     * @param R0m_vectors arralist vsetkých RP vektorov jedného lana
     * @param deltaL_vectors arraylist vsetkých DL vektorov jedneho lana
     * @param R0_Y korekcia bundke vodiča smer Y podla retazovky class
     * @param R0_Z korekcia bundke vodiča smer Z podla retazovky class 
     * @throws DelaunayError 
     */
    public E_calculation(double epsi0, double epsiR, ArrayList<Complex> Tau, DPoint RP, ArrayList<DPoint> R0_vectors,ArrayList<DPoint> R0m_vectors, ArrayList<DPoint> deltaL_vectors,double R0_Z,double R0_Y) throws DelaunayError {
        this.epsi0 = epsi0;
        this.epsiR = epsiR;
        this.Tau = Tau;
        this.Rp = RP;
        this.R0_vectors = R0_vectors;
        this.R0m_vectors = R0m_vectors;
        this.deltaL_vectors = deltaL_vectors;
        this.R0_bundleY = R0_Y;
        this.R0_bundleZ = R0_Z;
        //System.out.println( " this.R0_bundleY " + this.R0_bundleY );
        //System.out.println( " this.R0_bundleZ " + this.R0_bundleZ );
        //korektura bundle
        
    }
    public void  run () throws DelaunayError{
        Complex NULA = new Complex(0, 0);
        FazorVektor E_v_miesteRp = new FazorVektor(NULA, NULA, NULA);
        
        //cyklus od kazdeho elementu
        for(int cl1 = 0; cl1<this.R0_vectors.size();cl1++){
            
             E_v_miesteRp.AddToFazorVektor( calc_DE(Rp, R0_vectors.get(cl1), deltaL_vectors.get(cl1)));
            
        }
        
       
        this.E=E_v_miesteRp;
    }
    //TOTO JE SRDCE VYPOCTU
    private FazorVektor calc_DE(DPoint Rp,DPoint R0,DPoint deltaL ) throws DelaunayError{
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

    public FazorVektor getE() {
        return E;
    }

    public void setE(FazorVektor B) {
        this.E = E;
    }

   
     
    
    
    
}

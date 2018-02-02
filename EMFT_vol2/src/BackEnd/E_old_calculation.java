/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import static InternalFrame.InternalFrameproject.Rozpätie;
import emft_vol2.constants;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.math.complex.Complex;
import org.apache.commons.math.linear.RealMatrix;
import org.jdelaunay.delaunay.error.DelaunayError;
import org.jdelaunay.delaunay.geometries.DPoint;
import tools.help;

/**vypocita E v mieste Rp od jedneho lana
 * @author Jozef
 */
public class E_old_calculation {
    
    double epsi0;
    double epsiR;
    int myint;
    DPoint Rp;
    DPoint R0;
    DPoint deltaL;
    double R0_bundleY=0;
    double R0_bundleZ=0;
    double beta = 0;
    
    ArrayList<DPoint> RP_vectors;
    ArrayList<DPoint> R0_vectors;
    ArrayList<DPoint> R0m_vectors;
    ArrayList<DPoint> deltaL_vectors;
    ArrayList<RealMatrix> Tau_real; // tau pre kazdy element
    ArrayList<RealMatrix> Tau_image;
    ArrayList<Integer> polohy_lan;
    int Lano;
    FazorVektor E ;
    
    
    
    public E_old_calculation() {
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
    public E_old_calculation(double epsi0, double epsiR, ArrayList<RealMatrix> Tau_real,ArrayList<RealMatrix> Tau_image,int Lano, DPoint RP, ArrayList<DPoint> R0_vectors,ArrayList<DPoint> R0m_vectors, ArrayList<DPoint> deltaL_vectors,double R0_Z,double R0_Y,double beta) throws DelaunayError {
        this.epsi0 = epsi0;
        this.epsiR = epsiR;
        this.Tau_real = Tau_real;
        this.Tau_image = Tau_image;
        this.Lano=Lano;
        this.Rp = RP;
        this.R0_vectors = R0_vectors;
        this.R0m_vectors = R0m_vectors;
        this.deltaL_vectors = deltaL_vectors;
        this.R0_bundleY = R0_Y;
        this.R0_bundleZ = R0_Z;
        this.beta=beta; // 
        //System.out.println( " this.R0_bundleY " + this.R0_bundleY );
        //System.out.println( " this.R0_bundleZ " + this.R0_bundleZ );
        //korektura bundle
        
    }
    public void  run () throws DelaunayError{
        Complex NULA = new Complex(0, 0);
        FazorVektor E_v_miesteRp = new FazorVektor(NULA, NULA, NULA);
        
         
        
        //cyklus od kazdeho elementu
        for(int cl1 = 0; cl1<this.R0_vectors.size();cl1++){
            
             E_v_miesteRp.AddToFazorVektor( 
                          calc_DE(Tau_real.get(cl1).getEntry(Lano, 0) ,Tau_image.get(cl1).getEntry(Lano, 0) ,Rp, R0_vectors.get(cl1),R0m_vectors.get(cl1) , deltaL_vectors.get(cl1))
             );
            
        }
        
       
        this.E=E_v_miesteRp;
    }
    //TOTO JE SRDCE VYPOCTU
    private FazorVektor calc_DE(double tau_real,double tau_image, DPoint Rp,DPoint R0,DPoint R0m,DPoint deltaL ) throws DelaunayError{
        DPoint R_0 = new DPoint(R0.getX(), R0.getY(), R0.getZ());
        DPoint R_0m = new DPoint(R0m.getX(), R0m.getY(), R0m.getZ());
        R_0.setY(R0.getY() + R0_bundleY); //  bundle korektura pre jeden druhy SMER 
        R_0.setZ(R0.getZ() + Math.cos(beta)*R0_bundleZ); // priemety
        R_0.setX(R0.getX() + Math.sin(beta)*R0_bundleZ);
        
        R_0m.setY(R0m.getY() - R0_bundleY); //  bundle korektura pre jeden druhy SMER 
        R_0m.setZ(R0m.getZ() + Math.cos(beta)*R0_bundleZ); // priemety
        R_0m.setX(R0m.getX() + Math.sin(beta)*R0_bundleZ);
         
      //  System.out.println( "R_0= " + R_0 );
      //  System.out.println( "Rp= " + Rp );
     // System.out.println( "deltal= " + deltaL );
        double K = 1/(4*Math.PI*constants.getEpsi0()*constants.getEpsi1()); // konštanta
        
        DPoint R_r = help.substract(Rp, R_0);  // rozdiel vektorov Rp a RO
        DPoint R_m = help.substract(Rp, R_0m);  // rozdiel vektorov RP a RO mirror
        DPoint R_r_unit = new DPoint(R_r);
        DPoint R_m_unit = new DPoint(R_m);
        
//        R_r_unit.setX(R_r_unit.getX()/get_ABS(R_r));
//        R_r_unit.setY(R_r_unit.getY()/get_ABS(R_r));
//        R_r_unit.setZ(R_r_unit.getZ()/get_ABS(R_r));
//        
//        R_m_unit.setX(R_m_unit.getX()/get_ABS(R_m));
//        R_m_unit.setY(R_m_unit.getY()/get_ABS(R_m));
//        R_m_unit.setZ(R_m_unit.getZ()/get_ABS(R_m));
        
        
      
        double menovatel_r =0;
        double menovatel_m =0;

         menovatel_r = Math.pow(get_ABS(R_r), 3);
         menovatel_m = Math.pow(get_ABS(R_m), 3); 
        
        
        
        
        double DELTA_l = get_ABS(deltaL);
        //
        //double DELTA_l = 1; //Rozpätie.getKrok();
        
        
        FazorVektor deltaE = new FazorVektor(new Complex(0, 0),new Complex(0, 0),new Complex(0, 0));
        
       
        
        deltaE.setX_Real( K * (  ((tau_real* DELTA_l*R_r_unit.getX())/menovatel_r)  - ((tau_real* DELTA_l*R_m_unit.getX())/menovatel_m)     ));
        deltaE.setY_Real( K * (  ((tau_real* DELTA_l*R_r_unit.getY())/menovatel_r)  - ((tau_real* DELTA_l*R_m_unit.getY())/menovatel_m)     ));
        deltaE.setZ_Real( K * (  ((tau_real* DELTA_l*R_r_unit.getZ())/menovatel_r)  - ((tau_real* DELTA_l*R_m_unit.getZ())/menovatel_m)     ));

        deltaE.setX_Imaginary( K * (  ((tau_image* DELTA_l*R_r_unit.getX())/menovatel_r)  - ((tau_image* DELTA_l*R_m_unit.getX())/menovatel_m)     ));
        deltaE.setY_Imaginary( K * (  ((tau_image* DELTA_l*R_r_unit.getY())/menovatel_r)  - ((tau_image* DELTA_l*R_m_unit.getY())/menovatel_m)     ));
        deltaE.setZ_Imaginary( K * (  ((tau_image* DELTA_l*R_r_unit.getZ())/menovatel_r)  - ((tau_image* DELTA_l*R_m_unit.getZ())/menovatel_m)     ));
        
        return deltaE;
    }
    

    private double get_ABS(DPoint X){
        double ABS = Math.sqrt( Math.pow(X.getX(), 2) + Math.pow(X.getY(), 2) + Math.pow(X.getZ(), 2)  );
        return ABS;
    }

    public FazorVektor getE() {
        return E;
    }

    public void setE(FazorVektor E) {
        this.E = E;
    }

   
     
    
    
    
}

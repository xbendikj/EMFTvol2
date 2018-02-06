/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import org.apache.commons.math.complex.Complex;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.jdelaunay.delaunay.geometries.DPoint;

/**
 * class ktora obsahuje všetky informacie o jednom bude pozorovatela
 * neskor tu možme dorobit detekciu limitov
 * @author Jozef
 */
public class Observer {
    
    private RealMatrix  GeoMatrix_A;
    private FazorVektor B;
    private FazorVektor E;
    private FazorVektor I;
    private FazorVektor Emod;
    private DPoint poloha;
    /**
     * bod pozorovatela zo všetkym čo tam ma byt
     * @param B indukcia mag pola ako fazorvektor TESLA
     * @param E intenzita el pola ako fazorvektor V/m
     * @param poloha polohovy vektor 
     */
    public Observer(FazorVektor B, FazorVektor E, DPoint poloha) {
        this.B = B;
        this.E = E;
        this.poloha = poloha;
    }
/**
     * bod pozorovatela zo všetkym čo tam ma byt
     * @param B indukcia mag pola ako fazorvektor TESLA
     * @param E intenzita el pola ako fazorvektor V/m
     * @param poloha polohovy vektor 
     * @param GeoMatrix_A matica geometrických koeficientov 
     * 
     */
    public Observer(FazorVektor B, FazorVektor E, DPoint poloha,double[][] GeoMatrix_A) {
        this.B = B;
        this.E = E;
        this.poloha = poloha;
        this.GeoMatrix_A = MatrixUtils.createRealMatrix(GeoMatrix_A);
    }

    public FazorVektor getI() {
        return I;
    }

    public void setI(FazorVektor I) {
        this.I = I;
    }

    public FazorVektor getEmod() {
        return Emod;
    }

    public void setEmod(FazorVektor Emod) {
        this.Emod = Emod;
    }
    
    public FazorVektor getB() {
        return B;
    }

    public void setB(FazorVektor B) {
        this.B = B;
    }

    public FazorVektor getE() {
        return E;
    }

    public void setE(FazorVektor E) {
        this.E = E;
    }

    public DPoint getPoloha() {
        return poloha;
    }

    public void setPoloha(DPoint poloha) {
        this.poloha = poloha;
    }
     /**
      * sppricitaj observera ku observerovi
      * @param X 
      */
     public void merge(Observer X) {
        this.B.AddToFazorVektor(X.getB());
        this.E.AddToFazorVektor(X.getE());
        this.GeoMatrix_A.add(X.getGeoMatrix_A()) ; 
    }

    public RealMatrix getGeoMatrix_A() {
        return GeoMatrix_A;
    }

    public void setGeoMatrix_A(RealMatrix GeoMatrix_A) {
        this.GeoMatrix_A = GeoMatrix_A;
    }
    //TU POJDU FUNKCIE NA VYPOCET I a EMOD. Z Ba E 
   public void calculateI(double KE,double KB,double epsi0,double epsi1,double sigma0,double f){
        
        double JeXR = KE*epsi0*epsi1*2*Math.PI*f*E.getX_Real();
        double JeXI = KE*epsi0*epsi1*2*Math.PI*f*E.getX_Imaginary();  
        double JeYR = KE*epsi0*epsi1*2*Math.PI*f*E.getY_Real();  
        double JeYI = KE*epsi0*epsi1*2*Math.PI*f*E.getY_Imaginary();  
        double JeZR = KE*epsi0*epsi1*2*Math.PI*f*E.getZ_Real();
        double JeZI = KE*epsi0*epsi1*2*Math.PI*f*E.getZ_Imaginary();
        
        double JbXR = KB*sigma0*2*Math.PI*f*B.getX_Real();
        double JbXI = KB*sigma0*2*Math.PI*f*B.getX_Imaginary();  
        double JbYR = KB*sigma0*2*Math.PI*f*B.getY_Real();  
        double JbYI = KB*sigma0*2*Math.PI*f*B.getY_Imaginary();  
        double JbZR = KB*sigma0*2*Math.PI*f*B.getZ_Real();
        double JbZI = KB*sigma0*2*Math.PI*f*B.getZ_Imaginary();
        
        I = new FazorVektor( new Complex(JeXR+JbXR,JeXI+ JbXI), new Complex(JeYR+JbYR,JeYI+ JbYI), new Complex(JeZR+JbZR,JeZI+JbZI));
        
    }
    public void calculateEmod(double KE,double KB,double epsi0,double epsi1,double sigma0,double f){
        
        double K = (1)/(Math.sqrt(2) *0.05);
        double X = (f)/(400);
        double Y = (f)/(25);
        double Z = (f)/(3000);
        
        double A= 1-(Y*Z);
        double B = (Z+Y);
        
        double menovatel = (Math.pow(A, 2) + Math.pow(B, 2) );
        
        double GR = K*( (A+X*B)/(menovatel)   );
        double GI = K*( (X*A -B)/(menovatel)   );
        
        double E1XR = GR*2*Math.sqrt(2)*KB*Math.PI*f* this.B.getX_Real();
        double E1XI = GI*2*Math.sqrt(2)*KB*Math.PI*f* this.B.getX_Imaginary();
        double E1YR = GR*2*Math.sqrt(2)*KB*Math.PI*f* this.B.getY_Real(); 
        double E1YI = GI*2*Math.sqrt(2)*KB*Math.PI*f* this.B.getY_Imaginary();
        double E1ZR = GR*2*Math.sqrt(2)*KB*Math.PI*f* this.B.getZ_Real();
        double E1ZI = GI*2*Math.sqrt(2)*KB*Math.PI*f* this.B.getZ_Imaginary();
        
        double nasobok = (epsi0*epsi1)/(sigma0);;
        
        double E2XR = nasobok*GR*2*Math.sqrt(2)*KE*Math.PI*f* this.E.getX_Real();
        double E2XI = nasobok*GI*2*Math.sqrt(2)*KE*Math.PI*f* this.E.getX_Imaginary();
        double E2YR = nasobok*GR*2*Math.sqrt(2)*KE*Math.PI*f* this.E.getY_Real(); 
        double E2YI = nasobok*GI*2*Math.sqrt(2)*KE*Math.PI*f* this.E.getY_Imaginary();
        double E2ZR = nasobok*GR*2*Math.sqrt(2)*KE*Math.PI*f* this.E.getZ_Real();
        double E2ZI = nasobok*GI*2*Math.sqrt(2)*KE*Math.PI*f* this.E.getZ_Imaginary();
        
        Emod = new FazorVektor( new Complex( E1XR+ E2XR,E1XI+ E2XI), new Complex(E1YR+ E2YR,E1YI+ E2YI), new Complex(E1ZR+ E2ZR,E1ZI+ E2ZI));
        
    }
    
}

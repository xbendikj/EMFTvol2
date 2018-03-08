/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import flanagan.complex.ComplexMatrix;
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
    private double parameter;
    private DPoint poloha;
    
    private ComplexMatrix  Z;
    private ComplexMatrix  Y;
    private ComplexMatrix  Zs;
    private ComplexMatrix  Ys;
    
    //electrical parameters
   
   private RealMatrix R;
   private RealMatrix L;
   private RealMatrix C;
    
    public Observer(ComplexMatrix Z, ComplexMatrix Y,ComplexMatrix Zs, ComplexMatrix Ys, double parameter){
        this.Z = Z;
        this.Y = Y;
        this.Zs = Zs;
        this.Ys = Ys;
        this.parameter = parameter;
    }
    
    public Observer(RealMatrix R, RealMatrix L, RealMatrix C, double parameter){
        this.R = R;
        this.L = L;
        this.C = C;
        this.parameter = parameter;
    }
    
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
    
    public Observer() {
    }
    
     public Observer(FazorVektor B, FazorVektor E, DPoint poloha,double[][] GeoMatrix_A,double parameter) {
        this.B = B;
        this.E = E;
        this.poloha = poloha;
        this.parameter=parameter;
        this.GeoMatrix_A = MatrixUtils.createRealMatrix(GeoMatrix_A);
    }

    public double getParameter() {
        return parameter;
    }

    public void setParameter(double parameter) {
        this.parameter = parameter;
    }

    public FazorVektor getI() {
        return I;
    }

    public ComplexMatrix getZ() {
        return Z;
    }

    public void setZ(ComplexMatrix Z) {
        this.Z = Z;
    }

    public ComplexMatrix getY() {
        return Y;
    }

    public void setY(ComplexMatrix Y) {
        this.Y = Y;
    }

    public ComplexMatrix getZs() {
        return Zs;
    }

    public void setZs(ComplexMatrix Zs) {
        this.Zs = Zs;
    }

    public ComplexMatrix getYs() {
        return Ys;
    }

    public void setYs(ComplexMatrix Ys) {
        this.Ys = Ys;
    }

    public RealMatrix getR() {
        return R;
    }

    public void setR(RealMatrix R) {
        this.R = R;
    }

    public RealMatrix getL() {
        return L;
    }

    public void setL(RealMatrix L) {
        this.L = L;
    }

    public RealMatrix getC() {
        return C;
    }

    public void setC(RealMatrix C) {
        this.C = C;
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
    
    public double getR0_ELPAM(){
        double output = 0;
        
        
        return output;
    }
    
     public double getR1_ELPAM(){
        double output = 0;
        
        
        return output;
    }
    
      public double getR2_ELPAM(){
        double output = 0;
        
        
        return output;
    }
    
       public double getL0_ELPAM(){
        double output = 0;
        
        
        return output;
    }
    
     public double getL1_ELPAM(){
        double output = 0;
        
        
        return output;
    }
    
      public double getL2_ELPAM(){
        double output = 0;
        
        
        return output;
    } 
      
       public double getC0_ELPAM(){
        double output = 0;
        
        
        return output;
    }
    
     public double getC1_ELPAM(){
        double output = 0;
        
        
        return output;
    }
    
      public double getC2_ELPAM(){
        double output = 0;
        
        
        return output;
    }  
    
     /**
      * sppricitaj observera ku observerovi
      * @param X 
      */
     public void merge(Observer X) {
        this.B.AddToFazorVektor(X.getB());
        this.E.AddToFazorVektor(X.getE());
      //  this.GeoMatrix_A.add(X.getGeoMatrix_A()) ; 
    }

    public RealMatrix getGeoMatrix_A() {
        return GeoMatrix_A;
    }

    public void setGeoMatrix_A(RealMatrix GeoMatrix_A) {
        this.GeoMatrix_A = GeoMatrix_A;
    }
    //TU POJDU FUNKCIE NA VYPOCET I a EMOD. Z Ba E 
   public void calculateI(double KE,double KB,double epsi0,double epsi1,double sigma0,double f){
        
        double JeXR = KE*epsi0*epsi1*2*Math.PI*f*E.getX_ABS();
       // double JeXI = KE*epsi0*epsi1*2*Math.PI*f*E.getX_Imaginary();  
        double JeYR = KE*epsi0*epsi1*2*Math.PI*f*E.getY_ABS();  
       // double JeYI = KE*epsi0*epsi1*2*Math.PI*f*E.getY_Imaginary();  
        double JeZR = KE*epsi0*epsi1*2*Math.PI*f*E.getZ_ABS();
      //  double JeZI = KE*epsi0*epsi1*2*Math.PI*f*E.getZ_Imaginary();
        
        double JbXR = KB*sigma0*2*Math.PI*f*B.getX_ABS();
      //  double JbXI = KB*sigma0*2*Math.PI*f*B.getX_Imaginary();  
        double JbYR = KB*sigma0*2*Math.PI*f*B.getY_ABS();  
      //  double JbYI = KB*sigma0*2*Math.PI*f*B.getY_Imaginary();  
        double JbZR = KB*sigma0*2*Math.PI*f*B.getZ_ABS();
      //  double JbZI = KB*sigma0*2*Math.PI*f*B.getZ_Imaginary();
        
        I = new FazorVektor( new Complex(JeXR+JbXR,0), new Complex(JeYR+JbYR,0), new Complex(JeZR+JbZR,0));
        //System.out.println(B.getX().getReal() +"  " +E.getX().getReal() +"  " +I.getX().getReal()  +"  "+ (KE*epsi0*epsi1*2*Math.PI*f)+"  "+ (KB*sigma0*2*Math.PI*f) );
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
        double filter= Math.sqrt(Math.pow(GR, 2) + Math.pow(GI, 2) );
        double E1XR = filter  *2*Math.sqrt(2)*KB*Math.PI*f* this.B.getX_ABS();
        //double E1XI = GI*2*Math.sqrt(2)*KB*Math.PI*f* this.B.getX_Imaginary();
        double E1YR = filter*2*Math.sqrt(2)*KB*Math.PI*f* this.B.getY_ABS(); 
       // double E1YI = GI*2*Math.sqrt(2)*KB*Math.PI*f* this.B.getY_Imaginary();
        double E1ZR = filter*2*Math.sqrt(2)*KB*Math.PI*f* this.B.getZ_ABS();
       // double E1ZI = GI*2*Math.sqrt(2)*KB*Math.PI*f* this.B.getZ_Imaginary();
        
        double nasobok = (epsi0*epsi1)/(sigma0);;
        
        double E2XR = nasobok*filter*2*Math.sqrt(2)*KE*Math.PI*f* this.E.getX_ABS();
      //  double E2XI = nasobok*GI*2*Math.sqrt(2)*KE*Math.PI*f* this.E.getX_Imaginary();
        double E2YR = nasobok*filter*2*Math.sqrt(2)*KE*Math.PI*f* this.E.getY_ABS(); 
      //  double E2YI = nasobok*GI*2*Math.sqrt(2)*KE*Math.PI*f* this.E.getY_Imaginary();
        double E2ZR = nasobok*filter*2*Math.sqrt(2)*KE*Math.PI*f* this.E.getZ_ABS();
      //  double E2ZI = nasobok*GI*2*Math.sqrt(2)*KE*Math.PI*f* this.E.getZ_Imaginary();
        
        Emod = new FazorVektor( new Complex( E1XR+ E2XR,0), new Complex(E1YR+ E2YR,0), new Complex(E1ZR+ E2ZR,0));
        
    }
    
}

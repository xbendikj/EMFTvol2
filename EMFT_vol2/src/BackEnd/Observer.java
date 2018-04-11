/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import emft_vol2.constants;
import flanagan.complex.ComplexMatrix;
import org.apache.commons.math.complex.Complex;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.jdelaunay.delaunay.error.DelaunayError;
import org.jdelaunay.delaunay.geometries.DPoint;
import static tools.help.Complex2ImagMatrix;
import static tools.help.Complex2RealMatrix;

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
    private double[] R0;
    private double[] R1;
    private double[] R2;
    private double[] L0;
    private double[] L1;
    private double[] L2;
    private double[] C0;
    private double[] C1;
    private double[] C2;
    
    
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
    
    public double[] getR0_ELPAM(){
        if (checkSizeX3(this.Zs)){
            this.R0 = new double[this.Zs.getNrow()/3];
            for (int i = 0; i < this.Zs.getNrow()/3; i++) {
                    R0[i] = Complex2RealMatrix(this.Zs).getEntry(i*3, i*3); // napr 0*3 = 0 -> pozicia 0,0 -> R0
            }
            return this.R0;
        } else {
            return null;
        }
    }
    
    public double[] getR1_ELPAM(){
        if (checkSizeX3(this.Zs)){
            this.R1 = new double[this.Zs.getNrow()/3];
            for (int i = 0; i < this.Zs.getNrow()/3; i++) {
                    R1[i] = Complex2RealMatrix(this.Zs).getEntry(i*3+1, i*3+1); // napr 0*3+1 = 1 -> pozicia 1,1 -> R1
            }
            return R1;
        } else {
            return null;
        }
    }
    
    public double[] getR2_ELPAM(){
        if (checkSizeX3(this.Zs)){
            this.R2 = new double[this.Zs.getNrow()/3];
            for (int i = 0; i < this.Zs.getNrow()/3; i++) {
                    R2[i] = Complex2RealMatrix(this.Zs).getEntry(i*3+2, i*3+2); // napr 0*3+2 = 2 -> pozicia 2,2 -> R2
            }
            return R2;
        } else {
            return null;
        }
    }
    
    public double[] getL0_ELPAM(){
        if (checkSizeX3(this.Zs)){
            double omega = (double)2*Math.PI*constants.getFrequency();
            this.L0 = new double[this.Zs.getNrow()/3];
            for (int i = 0; i < this.Zs.getNrow()/3; i++) {
                    L0[i] = Complex2ImagMatrix(this.Zs).getEntry(i*3, i*3)/omega; // napr 0*3 = 0 -> pozicia 0,0 -> L0
            }
            return L0;
        } else {
            return null;
        }
    }
    
    public double[] getL1_ELPAM(){
        if (checkSizeX3(this.Zs)){
            double omega = (double)2*Math.PI*constants.getFrequency();
            this.L1 = new double[this.Zs.getNrow()/3];
            for (int i = 0; i < this.Zs.getNrow()/3; i++) {
                    L1[i] = Complex2ImagMatrix(this.Zs).getEntry(i*3+1, i*3+1)/omega; // napr 0*3+1 = 1 -> pozicia 1,1 -> L1
            }
            return L1;
        } else {
            return null;
        }
    }
    
    public double[] getL2_ELPAM(){
        if (checkSizeX3(this.Zs)){
            double omega = (double)2*Math.PI*constants.getFrequency();
            this.L2 = new double[this.Zs.getNrow()/3];
            for (int i = 0; i < this.Zs.getNrow()/3; i++) {
                    L2[i] = Complex2ImagMatrix(this.Zs).getEntry(i*3+2, i*3+2)/omega; // napr 0*3+2 = 2 -> pozicia 2,2 -> L2
            }
            return L2;
        } else {
            return null;
        }
    } 
      
    public double[] getC0_ELPAM(){
        if (checkSizeX3(this.Ys)){
            double omega = (double)2*Math.PI*constants.getFrequency();
            this.C0 = new double[this.Ys.getNrow()/3];
            for (int i = 0; i < this.Ys.getNrow()/3; i++) {
                    C0[i] = Complex2ImagMatrix(this.Ys).getEntry(i*3, i*3)/omega; // napr 0*3 = 0 -> pozicia 0,0 -> C0
            }
            return C0;
        } else {
            return null;
        }
    }
    
    public double[] getC1_ELPAM(){
        if (checkSizeX3(this.Ys)){
            double omega = (double)2*Math.PI*constants.getFrequency();
            this.C1 = new double[this.Ys.getNrow()/3];
            for (int i = 0; i < this.Ys.getNrow()/3; i++) {
                    C1[i] = Complex2ImagMatrix(this.Ys).getEntry(i*3+1, i*3+1)/omega; // napr 0*3+1 = 1 -> pozicia 1,1 -> C1
            }
            return C1;
        } else {
            return null;
        }
    }
    
    public double[] getC2_ELPAM(){
        if (checkSizeX3(this.Ys)){
            double omega = (double)2*Math.PI*constants.getFrequency();
            this.C2 = new double[this.Ys.getNrow()/3];
            for (int i = 0; i < this.Ys.getNrow()/3; i++) {
                    C2[i] = Complex2ImagMatrix(this.Ys).getEntry(i*3+2, i*3+2)/omega; // napr 0*3+2 = 2 -> pozicia 2,2 -> C2
            }
            return C2;
        } else {
            return null;
        }
    }  
    
    private boolean checkSizeX3(ComplexMatrix source){
        if (source.getNrow() % 3 == 0 && source.getNcol()% 3 == 0){
            return true;
        } else {
            System.out.println("Destination Matrix" + source + "is not a square matrix of three phase pattern!");
            return false;
        }
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
    
    public void calculatePoyting() throws DelaunayError{
        
       DPoint E_real = new DPoint(E.getX_Real(), E.getY_Real(), E.getZ_Real());
       DPoint E_image = new DPoint(E.getX_Imaginary(), E.getY_Imaginary(), E.getZ_Imaginary());
       DPoint B_real = new DPoint(B.getX_Real(), B.getY_Real(), B.getZ_Real());
       DPoint B_image = new DPoint(B.getX_Imaginary(), B.getY_Imaginary(), B.getZ_Imaginary());
      
       DPoint Preal = vektor_sucet( vektor_sucin(E_real, B_real)  , vektor_sucin(E_image, B_image));
       DPoint Pimage = vektor_rozdiel(vektor_sucin(E_image, B_real)  , vektor_sucin(E_real, B_image));
        
       E.setX(new Complex(Preal.getX(), Pimage.getX()));
       E.setY(new Complex(Preal.getY(), Pimage.getY()));
       E.setY(new Complex(Preal.getZ(), Pimage.getZ()));
       
    }
    
     /**
     * A×B
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
    /**
     * A+B
     * @param A
     * @param B
     * @return
     * @throws DelaunayError 
     */
     private DPoint vektor_sucet ( DPoint A, DPoint B) throws DelaunayError{    
        DPoint C = new DPoint(0, 0, 0);
        
        C.setX( A.getX() + B.getX()  );
        C.setY( A.getY() + B.getY()  );
        C.setZ( A.getZ() + B.getZ()  );
        
        
        return C;
    }
    /**
     * A-B
     * @param A
     * @param B
     * @return
     * @throws DelaunayError 
     */ 
    private DPoint vektor_rozdiel ( DPoint A, DPoint B) throws DelaunayError{    
        DPoint C = new DPoint(0, 0, 0);
        
        C.setX( A.getX() - B.getX()  );
        C.setY( A.getY() - B.getY()  );
        C.setZ( A.getZ() - B.getZ()  );
        
        
        return C;
    } 
    
}

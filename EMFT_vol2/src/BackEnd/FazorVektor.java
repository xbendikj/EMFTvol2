/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;
 
import org.apache.commons.math.complex.Complex;

   


/**
 *  class ktory vytvara fazor vektor a je chopna vyhodnotit všetky potrebne parametry, ktore si uživatel zada
 * 
 * @author Jozef
 */
public class FazorVektor {
    
     private Complex X ;
     private Complex Y ;
     private Complex Z ;

    public FazorVektor() {
    }

    public FazorVektor(Complex X, Complex Y, Complex Z) {
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }

    public void setFazorVektor(FazorVektor X){
        this.X = X.getX();
        this.Y = X.getY();
        this.Z = X.getZ();
    }
    
    public FazorVektor SumFazorVektor(FazorVektor A, FazorVektor B){
        Complex C1 = new Complex(A.getX_Real() + B.getX_Real(), A.getX_Imaginary()+ B.getX_Imaginary());
        Complex C2 = new Complex(A.getY_Real() + B.getY_Real(), A.getY_Imaginary()+ B.getY_Imaginary());
        Complex C3 = new Complex(A.getZ_Real() + B.getZ_Real(), A.getZ_Imaginary()+ B.getZ_Imaginary());
        FazorVektor C = new FazorVektor(C1, C2, C3);
        
        return C;
    }
    /**
     * prida do existujuceho FV dalšiu hodnotu FV
     * @param B fazorvekor
     */
    public void AddToFazorVektor( FazorVektor B){
        X = new Complex(X.getReal() + B.getX_Real(), X.getImaginary()+ B.getX_Imaginary());
        Y = new Complex(Y.getReal() + B.getY_Real(), Y.getImaginary()+ B.getY_Imaginary());
        Z = new Complex(Z.getReal() + B.getZ_Real(), Z.getImaginary()+ B.getZ_Imaginary());
        
        
    }
    
    public Complex getX() {
        return X;
    }

    public void setX(Complex X) {
        this.X = X;
    }
    
      public double getX_Real() {
        return X.getReal();
    }

      public void setX_Real(double X) {
        Complex cislo = new Complex(X,this.X.getImaginary());
        this.X = cislo;
    }
    
      public double getX_Imaginary() {
        return X.getImaginary();
    }

      public void setX_Imaginary(double X) {
        Complex cislo = new Complex(this.X.getReal(),X);
        this.X = cislo;
    }
    
   public Complex getY() {
        return Y;
    }
    
      public double getY_Real() {
        return Y.getReal();
    }

      public void setY_Real(double Y) {
        Complex cislo = new Complex(Y,this.Y.getImaginary());
        this.Y = cislo;
    }
    
      public double getY_Imaginary() {
        return Y.getImaginary();
    }

      public void setY_Imaginary(double Y) {
        Complex cislo = new Complex(this.Y.getReal(),Y);
        this.Y = cislo;
    }

    public void setY(Complex Y) {
        this.Y = Y;
    }

    public Complex getZ() {
        return Z;
    }
    
      public double getZ_Real() {
        return Z.getReal();
    }

      public void setZ_Real(double Z) {
        Complex cislo = new Complex(Z,this.Z.getImaginary());
        this.Z = cislo;
    }
    
      public double getZ_Imaginary() {
        return Z.getImaginary();
    }
       
      public void setZ_Imaginary(double Z) {
        Complex cislo = new Complex(this.Z.getReal(),Z);
        this.Z = cislo;
    }

    public void setZ(Complex Z) {
        this.Z = Z;
    }
    /**
     * vrati komplexne cislo rms hodnotu realnej zložky a rms hodnotu imaginarnej zložky
     * @return 
     */
    public Complex getComplex_rms(){
        
        double Real = Math.sqrt(Math.pow(this.X.getReal(), 2) + Math.pow(this.Y.getReal(), 2) + Math.pow(this.Z.getReal(), 2));  
        double Image = Math.sqrt(Math.pow(this.X.getImaginary(), 2) + Math.pow(this.Y.getImaginary(), 2) + Math.pow(this.Z.getImaginary(), 2));
    
        Complex cislo = new Complex(Real,Image);
        return cislo;
    }
    
    public double getComplex_rms_ABS(){
        
        double Real = Math.sqrt(Math.pow(this.X.getReal(), 2) + Math.pow(this.Y.getReal(), 2) + Math.pow(this.Z.getReal(), 2));  
        double Image = Math.sqrt(Math.pow(this.X.getImaginary(), 2) + Math.pow(this.Y.getImaginary(), 2) + Math.pow(this.Z.getImaginary(), 2));
    
        Complex cislo = new Complex(Real,Image);
        return cislo.abs();
    }
    
    public double getComplex_rms_Angle(){
        
        double Real = Math.sqrt(Math.pow(this.X.getReal(), 2) + Math.pow(this.Y.getReal(), 2) + Math.pow(this.Z.getReal(), 2));  
        double Image = Math.sqrt(Math.pow(this.X.getImaginary(), 2) + Math.pow(this.Y.getImaginary(), 2) + Math.pow(this.Z.getImaginary(), 2));
    
        Complex cislo = new Complex(Real,Image);
        return cislo.getArgument();
    }
    
    public double getX_ABS(){
        return this.X.abs();
    }
    public double getX_Angle(){
        return this.X.getArgument();
    }
    
    public double getY_ABS(){
        return this.Y.abs();
    }
    public double getY_Angle(){
        return this.Y.getArgument();
    }
    
    public double getZ_ABS(){
        return this.Z.abs();
    }
    public double getZ_Angle(){
        return this.Z.getArgument();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrical_parameters;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import org.apache.commons.math3.complex.Complex;
import static tools.help.cadd;
import static tools.help.cpow;
import static tools.help.csub;

/**
 * sluzi na konverziu Rdc -> Rac na zaklade vlastnosti vodica
 * @author Mattto
 */
public class Rac_calculation {
    
    double rho;     //rezistivita vodivej casti vodica - vacsinou Al [Ohm.m]
    double D;       //priemer vodica [m]
    double D1;      //vnutorny priemer oceloveho jadra vodica [m]
    double f;       //frekvencia [Hz]
    double Rdc;     //jednosmerny odpor vodica [Ohm/km]
    double Rac;     //vysledny striedavy odpor [Ohm.km] Rac = Rdc*k_sk
    
    boolean bundle;
    int n_zv;
    
    /**
     * void constructor
     */
    public Rac_calculation(){
    }
    
    /**
     * valid constructor
     * @param rho rezistivita vodivej casti vodica - vacsinou Al [Ohm.m]
     * @param D priemer vodica [m]
     * @param D1 vnutorny priemer oceloveho jadra vodica [m]
     * @param f frekvencia [Hz]
     * @param Rdc jednosmerny odpor vodica [Ohm/km]
     */
    public Rac_calculation(double rho, double D, double D1, double f, double Rdc, boolean bundle, int n_zv){
        this.rho = rho;
        this.D = D;
        this.D1 = D1;
        this.f = f;
        this.Rdc = Rdc;
        this.bundle = bundle;
        this.n_zv = n_zv;
    }
    
    /**
     * valid constructor from elpam_input_conductor class
     * @param Conductor 
     */
    public Rac_calculation(elpam_input_conductor Conductor,int n_zv){
        this.rho = Conductor.rho_conductor;
        this.D = Conductor.D;
        this.D1 = Conductor.T;
        this.f = Conductor.f;
        this.Rdc = Conductor.Rdc;
        this.bundle = Conductor.isBundle();
        this.n_zv = n_zv;
    }
    
    /**
     * Valid mainly for 2+ layers of Al coating in ACSR conductor
     */
    public void calc_Rac(){
        if (this.bundle){
            this.Rac = this.Rdc*k_sk_tube(this.rho, this.D, this.D1, this.f) / this.n_zv;
            this.Rdc = this.Rdc / this.n_zv;
        } else {
            this.Rac = this.Rdc*k_sk_tube(this.rho, this.D, this.D1, this.f);
        }
    }
    
    //Private function area
    
    private Complex Bessel_0(Complex x){
        Complex aux;
        aux = csub(1,cpow(x.divide((double)2),2)); 
        aux = cadd(aux,cpow(x.divide((double)2),4).multiply((double)1/4));
        aux = csub(aux,cpow(x.divide((double)2),6).multiply((double)1/36));
        return aux;
    }
    
    private Complex Bessel_1(Complex x){
        Complex aux;
        aux = x.divide((double)2);
        aux = csub(aux,cpow(x.divide((double)2),3).multiply((double)1/2));
        aux = cadd(aux,cpow(x.divide((double)2),5).multiply((double)1/12));
        aux = csub(aux,cpow(x.divide((double)2),7).multiply((double)1/144));
        return aux;
    }
    
    private double EIR(double rho, double D, double f){
        double delta;
        Complex x;
        Complex y = new Complex(1,-1);
        Complex a;
        Complex b;
        
        delta = 503.3*sqrt(rho/f);
        x = y.multiply(D).divide(2*delta);
        a = y.multiply(rho).divide(D*Math.PI*delta);
        b = Bessel_0(x).divide(Bessel_1(x));
        
//        System.out.println("delta");
//        System.out.println(delta);
//        System.out.println("x");
//        System.out.println(x);
//        System.out.println("EIR");
//        System.out.println(a.multiply(b).getReal());
        
        return a.multiply(b).getReal();
    }
    
    private double k_sk_d(double rho, double D, double f){
//        System.out.println("k_sk_d");
//        System.out.println((pow(D,2)*Math.PI*EIR(rho,D,f))/(4*rho));
        return (pow(D,2)*Math.PI*EIR(rho,D,f))/(4*rho);
    }
    
    private double k_sk_tube(double rho, double D, double D1, double f){
//        System.out.println("k_sk_tube");
//        System.out.println(1+(k_sk_d(rho, D, f)-1)*(1-D1/D));
        return 1+(k_sk_d(rho, D, f)-1)*(1-D1/D);
    }

    //Getters and Setters area

    public double getRho() {
        return rho;
    }

    public void setRho(double rho) {
        this.rho = rho;
    }

    public double getD() {
        return D;
    }

    public void setD(double D) {
        this.D = D;
    }

    public double getD1() {
        return D1;
    }

    public void setD1(double D1) {
        this.D1 = D1;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public double getRdc() {
        return Rdc;
    }

    public void setRdc(double Rdc) {
        this.Rdc = Rdc;
    }

    public double getRac() {
        return Rac;
    }

    public void setRac(double Rac) {
        this.Rac = Rac;
    }

    public boolean isBundle() {
        return bundle;
    }

    public void setBundle(boolean bundle) {
        this.bundle = bundle;
    }

    public int getN_zv() {
        return n_zv;
    }

    public void setN_zv(int n_zv) {
        this.n_zv = n_zv;
    }
    
    
}

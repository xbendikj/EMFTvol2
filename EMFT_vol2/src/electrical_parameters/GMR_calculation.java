/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrical_parameters;

import static tools.help.arrayMax;
import static tools.help.arraySum;

/**
 * Sluzi na vypocet GMR a xi vodica
 * @author Mattto
 */
public class GMR_calculation {
    
    int Al_layers;
    int Al_start;
    double Al_d;
    double d_cond;
    
    double GMR;
    double xi;
    double GMR_default;
    double xi_default;
    
    boolean bundle;
    double krok;
    int n_zv;
    
    /**
     * void constructor
     */
    public GMR_calculation(){
    }
    
    /**
     * valid constructor
     * @param Al_layers pocet vrstiev Al
     * @param Al_start pocet vodicov v prvej vrstve Al (smer zvnutra von)
     * @param Al_d priemer Al drotov [m]
     * @param d_cond priemer vodica [m]
     * @param krok
     * @param n_zv
     * @param bundle
     */
    public GMR_calculation(int Al_layers, int Al_start, double Al_d, double d_cond, boolean bundle, double krok, int n_zv){
        this.Al_d = Al_d;
        this.Al_layers = Al_layers;
        this.Al_start = Al_start;
        this.d_cond = d_cond;
        this.krok = krok;
        this.n_zv = n_zv;
        this.GMR_default = 0.7788*Al_d/2;
        this.xi_default = 0.7788;
        this.bundle = bundle;
        
    }
    
    /**
     * valid constructor from elpam_input_conductor class
     * @param Conductor 
     * @param krok 
     * @param n_zv 
     */
    public GMR_calculation(elpam_input_conductor Conductor, double krok, int n_zv){
        this.Al_d = Conductor.getAl_d();    //[m]
        this.Al_layers = Conductor.getAl_layers();
        this.Al_start = Conductor.getAl_start();
        this.d_cond = Conductor.getD();     //[m]
        this.krok = krok;
        this.n_zv = n_zv;
        this.GMR_default = 0.7788*Al_d/2;
        this.xi_default = 0.7788;
        this.bundle = Conductor.isBundle();
    }
    
    /**
     * Calculate GMR of conductor
     */
    public void calc_GMR_xi(){
        int Al_structure[] = new int[Al_layers]; 
        double GMR_final;
        int n; 
        int m;
        
        for (int i = 0; i < Al_layers; i++) {
            Al_structure[i] = Al_start + i*6;
        }
        
        GMR_final = 1;
        n = arrayMax(Al_structure);
        m = (int) arraySum(Al_structure);
        
        for (int i = 0; i < Al_layers; i++) {
            GMR_final = GMR_final * Math.pow(gmr_layer(n-6*i, Al_d/2),Math.pow((double)(n-6*i)/m,2));
//            System.out.println("GMR_final");
//            System.out.println(GMR_final);
//            System.out.println(n);
//            System.out.println(m);
//            System.out.println(i);
//            System.out.println(n-6*i);
//            System.out.println(((double) n-6*i)/m);
//            System.out.println(Math.pow((n-6*i)/m,2));
        }
        for (int i = 0; i < Al_layers; i++) {
            for (int j = 0; j < Al_layers; j++) {
                if (i < j) {
                GMR_final = GMR_final*Math.pow(gmr_between(n-6*i,Al_d/2),exp(n-6*i,m,j-i));
//                System.out.println("GMR_final");
//                System.out.println(GMR_final);
                }
            }
        }
        
        //bundle?
        if(this.bundle){
            this.xi = GMR_final/(this.d_cond/2);
            double rho = this.krok/(2*Math.sin(Math.PI/this.n_zv));
            double r_zv = Math.pow(this.n_zv * (this.d_cond/2) * Math.pow(rho, this.n_zv-1), (double)1/this.n_zv);
            this.GMR = Math.pow(this.xi, (double)1/this.n_zv) * r_zv;
            this.GMR_default = Math.pow(this.xi_default, (double)1/this.n_zv) * r_zv;
        } else {
            this.GMR = GMR_final;
            this.xi = this.GMR/(this.d_cond/2);
        }
    }
    
    //Private function area
    
    private double exp(double n_max, double n_total, int i){
//        System.out.println("exp");
//        System.out.println((2*n_max*(n_max-6*i))/Math.pow(n_total,2));
        return (2*n_max*(n_max-6*i))/Math.pow(n_total,2);
        
    }
    
    private double gmr_between(double n, double r1){
//        System.out.println("gmr_between");
//        System.out.println((n*r1)/3);
        return (n*r1)/3;
    }
    
    private double gmr_layer(double n, double r1){
//        System.out.println("gmr_layer");
//        System.out.println(((n*r1)/3)*Math.pow(2.3364,1/n));
        return ((n*r1)/3)*Math.pow(2.3364,1/n);
    }

    //Getters & Setters area
    
    public int getAl_layers() {
        return Al_layers;
    }

    public int getAl_start() {
        return Al_start;
    }

    public double getAl_d() {
        return Al_d;
    }

    public double getD_cond() {
        return d_cond;
    }

    public double getGMR() {
        return GMR;
    }

    public double getXi() {
        return xi;
    }

    public void setAl_layers(int Al_layers) {
        this.Al_layers = Al_layers;
    }

    public void setAl_start(int Al_start) {
        this.Al_start = Al_start;
    }

    public void setAl_d(double Al_d) {
        this.Al_d = Al_d;
    }

    public void setD_cond(double d_cond) {
        this.d_cond = d_cond;
    }

    public void setGMR(double GMR) {
        this.GMR = GMR;
    }

    public void setXi(double xi) {
        this.xi = xi;
    }

    public double getGMR_default() {
        return GMR_default;
    }

    public void setGMR_default(double GMR_default) {
        this.GMR_default = GMR_default;
    }

    public double getXi_default() {
        return xi_default;
    }

    public void setXi_default(double xi_default) {
        this.xi_default = xi_default;
    }

    public boolean isBundle() {
        return bundle;
    }

    public void setBundle(boolean bundle) {
        this.bundle = bundle;
    }

    public double getKrok() {
        return krok;
    }

    public void setKrok(double krok) {
        this.krok = krok;
    }

    public int getN_zv() {
        return n_zv;
    }

    public void setN_zv(int n_zv) {
        this.n_zv = n_zv;
    }
    
    
    
}

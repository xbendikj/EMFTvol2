/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrical_parameters;

import static java.lang.Math.pow;
import java.util.stream.IntStream;
import static tools.help.arrayMax;

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
    
    /**
     * void constructor
     */
    public GMR_calculation(){
    }
    
    /**
     * valid constructor
     * @param Al_layers pocet vrstiev Al
     * @param Al_start pocet vodicov v prvej vrstve Al (smer zvnutra von)
     * @param Al_d mm - priemer Al drotov
     * @param d_cond mm - priemer vodica
     */
    public GMR_calculation(int Al_layers, int Al_start, double Al_d, double d_cond){
        this.Al_d = Al_d;
        this.Al_layers = Al_layers;
        this.Al_start = Al_start;
        this.d_cond = d_cond;
    }
    
    /**
     * Calculate GMR of conductor
     */
    public void calc_GMR(){
        int Al_structure[] = new int[Al_layers]; 
        double GMR_final;
        int n, m;
        
        for (int i = 1; i < Al_layers; i++) {
            Al_structure[i] = Al_start + (i-1)*6;
        }
        
        GMR_final = 1;
        n = arrayMax(Al_structure);
        m = IntStream.of(Al_structure).sum();
        for (int i = 0; i < Al_layers-1; i++) {
            GMR_final = GMR_final*gmr_layer(n-6*i, Al_d/2);
            if (i != Al_layers-1) {
                GMR_final = GMR_final*pow(gmr_between(n-6*i,Al_d/2),exp(n-6*i,m));
            }
        }
        this.GMR = GMR_final;
    }

    /**
     * Calculate xi of conductor
     */
    public void calc_xi(){
        this.xi = calc_GMR_double()/(this.Al_d/2);
    }
    
    //Private function area

    private double calc_GMR_double(){
        int Al_structure[] = new int[Al_layers]; 
        double GMR_final;
        int n, m;
        
        for (int i = 1; i < Al_layers; i++) {
            Al_structure[i] = Al_start + (i-1)*6;
        }
        
        GMR_final = 1;
        n = arrayMax(Al_structure);
        m = IntStream.of(Al_structure).sum();
        for (int i = 0; i < Al_layers-1; i++) {
            GMR_final = GMR_final*gmr_layer(n-6*i, Al_d/2);
            if (i != Al_layers-1) {
                GMR_final = GMR_final*pow(gmr_between(n-6*i,Al_d/2),exp(n-6*i,m));
            }
        }
        return GMR_final;
    }
    
    private double exp(double n_max, double n_total){
        return (2*n_max*(n_max-6))/pow(n_total,2);
    }
    
    private double gmr_between(double n, double r1){
        return (n*r1)/3;
    }
    
    private double gmr_layer(double n, double r1){
        return ((n*r1)/3)*pow(2.3364,1/n);
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
    
    
}

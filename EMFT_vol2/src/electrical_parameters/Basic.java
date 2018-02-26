/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrical_parameters;

import emft_vol2.constants;
import java.time.LocalTime;
import java.util.ArrayList;
import org.apache.commons.math.linear.RealMatrix;
import static tools.help.arraySum;
import static tools.help.initMatrix;
import static tools.help.printRealMatrix;

/**
 *
 * @author Mattto_Silver
 */
public class Basic {
    
    //constants
    double omega = 0;
    double mu = 0;
    double mu2pi = (mu/(2*Math.PI));
    double Rg = 0;
    double Dg = 0;
    int rows = 0;
    int cols = 0;
    
    //inputs
    RealMatrix Dik;
    double[] GMR;
    double[] R_cnd;
    double f;
    double[] rho_gnd;
    double rho_avg;
    
    //results
    public RealMatrix R;
    public RealMatrix L;
    public RealMatrix X;
    
    //partial results
    RealMatrix Ln;

    public Basic (  RealMatrix Dik,
                    ArrayList<elpam_input_conductor> cnd_list,
                    boolean exact_GMR,
                    boolean exact_Rac
                ){
//        GMR_calculation cnd = new GMR_calculation(Conductor);
//        Rac_calculation cnd2 = new Rac_calculation(Conductor);
        this.rows = Dik.getRowDimension();
        this.cols = Dik.getColumnDimension();
        this.GMR = new double[cnd_list.size()];
        this.R_cnd = new double[cnd_list.size()];
        this.GMR = new double[cnd_list.size()];
        this.rho_gnd = new double[cnd_list.size()];
        
        if (exact_GMR) {
            for (int i = 0; i < cnd_list.size(); i++) {
                this.GMR[i] = cnd_list.get(i).getGMR();
            }
        } else {
            for (int i = 0; i < cnd_list.size(); i++) {
                this.GMR[i] = cnd_list.get(i).getGMR_default();
            }
        }
        
        if (exact_Rac) {
            for (int i = 0; i < cnd_list.size(); i++) {
                this.R_cnd[i] = cnd_list.get(i).getRac();
            }
        } else {
            for (int i = 0; i < cnd_list.size(); i++) {
                this.R_cnd[i] = cnd_list.get(i).getRdc();
            }
        }

        this.Dik = Dik;
        this.f = constants.getFrequency();
        for (int i = 0; i < cnd_list.size(); i++) {
            this.rho_gnd[i] = cnd_list.get(i).getRho_ground();
        }
        
        this.R = initMatrix(Dik);
        this.L = initMatrix(Dik);
        this.X = initMatrix(Dik);
        
        this.rho_avg = arraySum(this.rho_gnd)/this.rho_gnd.length;
        this.Dg = 659*Math.sqrt(this.rho_avg/this.f);
        this.omega = (double)2*Math.PI*this.f;
        this.mu = (4e-4)*Math.PI; 
        this.Rg = (double)this.omega*this.mu/8;
        this.mu2pi = (this.mu/(2*Math.PI));
    }
    
    /**
     * create Ln_real and Ln_imag matrices
     */
    public void calcLn(){
        //init matrix
        this.Ln = initMatrix(this.Dik);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (i==j){
                    this.Ln.setEntry(i, j, Math.log(this.Dg/this.GMR[i]));
                } else {
                    this.Ln.setEntry(i, j, Math.log(this.Dg/this.Dik.getEntry(i, j)));
                }
            }
        }
    }
    
    public void calcL(){
        calcLn();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.L.setEntry(i, j, this.Ln.getEntry(i, j)*this.mu2pi);
            }
        }
    }

    public void calcX(){
        calcL();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.X.setEntry(i, j, this.L.getEntry(i, j)*this.omega);
            }
        }
    }
    
    public void calcR(){
        calcX();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (i==j) {
                    this.R.setEntry(i, j, this.R_cnd[i] + this.Rg);
                }else{
                    this.R.setEntry(i, j, this.Rg);
                }
            }
        }
    }
    
    
    public void calcAll(){
        calcR();
        calcL();
        calcX();
    }
    
    public void printAll(){
        System.out.println("Cas generovania");
        System.out.println(LocalTime.now());
        System.out.println("Rg [Ohm/km]");
        System.out.println(this.Rg);
        System.out.println("Dg [mH/km]");
        System.out.println(this.Dg);
        System.out.println("R [Ohm/km]");
        printRealMatrix(this.R);
        System.out.println("L [mH/km]");
        printRealMatrix(this.L.scalarMultiply(1000));
        System.out.println("X [Ohm/km]");
        printRealMatrix(this.X);
    }

}

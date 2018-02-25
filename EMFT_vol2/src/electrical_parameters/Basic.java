/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrical_parameters;

import org.apache.commons.math.linear.RealMatrix;
import static tools.help.initMatrix;

/**
 *
 * @author Mattto_Silver
 */
public class Basic {
    
    //constants
    double omega = (double)2*Math.PI*this.f;
    double mu = (4e-7)*Math.PI; //na [km] -> preto 4e-7 a nie 4e-4
    double mu2pi = (mu/(2*Math.PI));
    double Rg = this.omega*this.mu/8;
    int rows = this.Dik.getRowDimension();
    int cols = this.Dik.getColumnDimension();
    
    //inputs
    RealMatrix Dik;
    double GMR;
    double R_cnd;
    double f;
    double rho_gnd;
    
    //results
    public RealMatrix R;
    public RealMatrix L;
    public RealMatrix X;
    
    //partial results
    RealMatrix Ln;

    public Basic (  RealMatrix Dik,
                    elpam_input_conductor Conductor,
                    boolean exact_GMR,
                    boolean exact_Rac
                ){
        GMR_calculation cnd = new GMR_calculation(Conductor);
        Rac_calculation cnd2 = new Rac_calculation(Conductor);
        
        if (exact_GMR) {
            cnd.calc_GMR();
        } else {
            cnd.setGMR(cnd.GMR_default);  //v [m]
        }
        
        if (exact_Rac) {
            cnd2.calc_Rac();
        } else {
            cnd2.setRac(Conductor.getRdc());
        }

        this.Dik = Dik;
        this.GMR = cnd.getGMR();   
        this.R_cnd = cnd2.getRac();     
        this.f = Conductor.getF();
        this.rho_gnd = Conductor.getRho_ground();
        
        this.R = initMatrix(Dik);
        this.L = initMatrix(Dik);
        this.X = initMatrix(Dik);
    }
    
    /**
     * create Ln_real and Ln_imag matrices
     */
    public void calcLn(){
        //init matrix
        this.Ln = initMatrix(this.Dik);
        double Dg = 659*Math.sqrt(this.rho_gnd/this.f);
        
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (i==j){
                    this.Ln.setEntry(i, j, Math.log(Dg/this.GMR));
                } else {
                    double Dik_real = this.Dik.getEntry(i, j);
                    this.Ln.setEntry(i, j, Math.log(Dg/Dik_real));
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
                    this.R.setEntry(i, j, this.R_cnd + this.Rg);
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

}

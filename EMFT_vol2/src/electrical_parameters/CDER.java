/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrical_parameters;

import emft_vol2.constants;
import java.time.LocalTime;
import java.util.ArrayList;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math.linear.RealMatrix;
import tools.help;
import static tools.help.initMatrix;
import static tools.help.printRealMatrix;

/**
 *
 * @author Mattto_Silver
 */
public class CDER {
    
    //constants
    double omega = 0;
    double mu = 0;
    double mu2pi = 0;
    int rows = 0;
    int cols = 0;
    
    //inputs
    RealMatrix Dik;
    RealMatrix Dik_mirror_CDER_real;
    RealMatrix Dik_mirror_CDER_imag;
    double[] hx2_real;
    double[] hx2_imag;
    double[] GMR;
    double[] R_cnd;
    double f;
    ArrayList<elpam_input_conductor> cnd_list = new ArrayList<>();
    
    //results
    public RealMatrix R_real;
    public RealMatrix L_real;
    public RealMatrix L_imag;
    public RealMatrix X_real;
    public RealMatrix X_imag;
    
    //partial results
    RealMatrix Ln_real;
    RealMatrix Ln_imag;

    public CDER (   RealMatrix Dik,
                    RealMatrix Dik_mirror_real,
                    RealMatrix Dik_mirror_imag,
                    double[] hx2_real,
                    double[] hx2_imag,
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
        this.Dik_mirror_CDER_real = Dik_mirror_real;
        this.Dik_mirror_CDER_imag = Dik_mirror_imag;
        this.hx2_real = hx2_real;
        this.hx2_imag = hx2_imag;
        this.f = constants.getFrequency();

        
        this.R_real = initMatrix(Dik);
        this.L_real = initMatrix(Dik);
        this.L_imag = initMatrix(Dik);
        this.X_real = initMatrix(Dik);
        this.X_imag = initMatrix(Dik);
        
        this.omega = (double)2*Math.PI*this.f;
        this.mu = (4e-4)*Math.PI; 
        this.mu2pi = (mu/(2*Math.PI));
        this.rows = Dik.getRowDimension();
        this.cols = Dik.getColumnDimension();
    }
    
    /**
     * create Ln_real and Ln_imag matrices
     */
    public void calcLn(){
        //init matrix
        this.Ln_real = initMatrix(this.Dik);
        this.Ln_imag = initMatrix(this.Dik);
        
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (i==j){
                    Complex hx = new Complex(this.hx2_real[i],this.hx2_imag[i]);
                    this.Ln_real.setEntry(i, j, help.cdiv(hx,this.GMR[i]).log().getReal());
                    this.Ln_imag.setEntry(i, j, help.cdiv(hx,this.GMR[i]).log().getImaginary());
                } else {
                    double Dik_real = this.Dik.getEntry(i, j);
                    double D_m_real = this.Dik_mirror_CDER_real.getEntry(i, j);
                    double D_m_imag = this.Dik_mirror_CDER_imag.getEntry(i, j);
                    Complex Dik_mirr = new Complex(D_m_real,D_m_imag);
                    this.Ln_real.setEntry(i, j, help.cdiv(Dik_mirr, Dik_real).log().getReal());
                    this.Ln_imag.setEntry(i, j, help.cdiv(Dik_mirr, Dik_real).log().getImaginary());
                }
            }
        }
    }
    
    public void calcL(){
        calcLn();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.L_real.setEntry(i, j, this.Ln_real.getEntry(i, j)*this.mu2pi);
                this.L_imag.setEntry(i, j, this.Ln_imag.getEntry(i, j)*this.mu2pi);
            }
        }
    }

    public void calcX(){
        calcL();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.X_real.setEntry(i, j, this.L_real.getEntry(i, j)*this.omega);
                this.X_imag.setEntry(i, j, this.L_imag.getEntry(i, j)*this.omega);
            }
        }
    }
    
    public void calcR(){
        calcX();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (i==j) {
                    this.R_real.setEntry(i, j, this.R_cnd[i] - this.X_imag.getEntry(i, j)); // minus kvoli i^2 = -1
                } else {
                    this.R_real.setEntry(i, j, (double)-1*this.X_imag.getEntry(i, j));
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
        System.out.println("R [Ohm/km]");
        printRealMatrix(this.R_real);
        System.out.println("L [mH/km]");
        printRealMatrix(this.L_real.scalarMultiply(1000));
//        System.out.println("L_imag [mH/km]");
//        printRealMatrix(this.L_imag.scalarMultiply(1000));
        System.out.println("X [Ohm/km]");
        printRealMatrix(this.X_real);
//        System.out.println("X_imag [Ohm/km]");
//        printRealMatrix(this.X_imag);
    }

    public RealMatrix getDik() {
        return Dik;
    }

    public void setDik(RealMatrix Dik) {
        this.Dik = Dik;
    }

    public RealMatrix getDik_mirror_CDER_real() {
        return Dik_mirror_CDER_real;
    }

    public void setDik_mirror_CDER_real(RealMatrix Dik_mirror_CDER_real) {
        this.Dik_mirror_CDER_real = Dik_mirror_CDER_real;
    }

    public RealMatrix getDik_mirror_CDER_imag() {
        return Dik_mirror_CDER_imag;
    }

    public void setDik_mirror_CDER_imag(RealMatrix Dik_mirror_CDER_imag) {
        this.Dik_mirror_CDER_imag = Dik_mirror_CDER_imag;
    }

    public double[] getHx2_real() {
        return hx2_real;
    }

    public void setHx2_real(double[] hx2_real) {
        this.hx2_real = hx2_real;
    }

    public double[] getHx2_imag() {
        return hx2_imag;
    }

    public void setHx2_imag(double[] hx2_imag) {
        this.hx2_imag = hx2_imag;
    }

    public double[] getGMR() {
        return GMR;
    }

    public void setGMR(double[] GMR) {
        this.GMR = GMR;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public RealMatrix getR_real() {
        return R_real;
    }

    public void setR_real(RealMatrix R_real) {
        this.R_real = R_real;
    }

    public RealMatrix getL_real() {
        return L_real;
    }

    public void setL_real(RealMatrix L_real) {
        this.L_real = L_real;
    }

    public RealMatrix getL_imag() {
        return L_imag;
    }

    public void setL_imag(RealMatrix L_imag) {
        this.L_imag = L_imag;
    }

    public RealMatrix getX_real() {
        return X_real;
    }

    public void setX_real(RealMatrix X_real) {
        this.X_real = X_real;
    }

    public RealMatrix getX_imag() {
        return X_imag;
    }

    public void setX_imag(RealMatrix X_imag) {
        this.X_imag = X_imag;
    }

    public RealMatrix getLn_real() {
        return Ln_real;
    }

    public void setLn_real(RealMatrix Ln_real) {
        this.Ln_real = Ln_real;
    }

    public RealMatrix getLn_imag() {
        return Ln_imag;
    }

    public void setLn_imag(RealMatrix Ln_imag) {
        this.Ln_imag = Ln_imag;
    }

    public double[] getR_cnd() {
        return R_cnd;
    }

    public void setR_cnd(double[] R_cnd) {
        this.R_cnd = R_cnd;
    }

    public ArrayList<elpam_input_conductor> getCnd_list() {
        return cnd_list;
    }

    public void setCnd_list(ArrayList<elpam_input_conductor> cnd_list) {
        this.cnd_list = cnd_list;
    }
    
    
}

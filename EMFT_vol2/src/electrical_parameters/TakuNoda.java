/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrical_parameters;

import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math3.complex.Complex;
import tools.help;
import static tools.help.initMatrix;

/**
 * vypoctova metoda od T.Noda - obsahuje dve komplexne navratove cesty prudu v zemi
 * @author Mattto
 */
public class TakuNoda {
     //constants
    double omega = (double)2*Math.PI*this.f;
    double mu = (4e-7)*Math.PI; //na [km] -> preto 4e-7 a nie 4e-4
    double mu2pi = (mu/(2*Math.PI));
    int rows = this.Dik.getRowDimension();
    int cols = this.Dik.getColumnDimension();
    double A = 0.131836;
    
    //inputs
    RealMatrix Dik;
    RealMatrix Dik_mirror_TakuNoda_real;
    RealMatrix Dik_mirror_TakuNoda_imag;
    double[] hx2_alpha_real;
    double[] hx2_alpha_imag;
    double[] hx2_beta_real;
    double[] hx2_beta_imag;
    double GMR;
    double R;
    double f;
    
    //results
    public RealMatrix R_real;
    public RealMatrix L_real;
    public RealMatrix L_imag;
    public RealMatrix X_real;
    public RealMatrix X_imag;
    
    //partial results
    RealMatrix Ln_alpha_real;
    RealMatrix Ln_alpha_imag;
    RealMatrix Ln_beta_real;
    RealMatrix Ln_beta_imag;
    RealMatrix Ln_real;
    RealMatrix Ln_imag;

    public TakuNoda(RealMatrix Dik,
                    RealMatrix Dik_mirror_real,
                    RealMatrix Dik_mirror_imag,
                    double[] hx2_alpha_real,
                    double[] hx2_alpha_imag,
                    double[] hx2_beta_real,
                    double[] hx2_beta_imag,
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
        this.Dik_mirror_TakuNoda_real = Dik_mirror_real;
        this.Dik_mirror_TakuNoda_imag = Dik_mirror_imag;
        this.hx2_alpha_real = hx2_alpha_real;
        this.hx2_alpha_imag = hx2_alpha_imag;
        this.hx2_beta_real = hx2_beta_real;
        this.hx2_beta_imag = hx2_beta_imag;
        this.GMR = cnd.getGMR();   
        this.R = cnd2.getRac();     
        this.f = Conductor.getF();
        
        this.R_real = initMatrix(Dik);
        this.L_real = initMatrix(Dik);
        this.L_imag = initMatrix(Dik);
        this.X_real = initMatrix(Dik);
        this.X_imag = initMatrix(Dik);
    }
    
    /**
     * create Ln_alpha_real and Ln_alpha_imag matrices
     */
    private void calcLn_alpha(){
        //init matrix
        this.Ln_alpha_real = initMatrix(this.Dik);
        this.Ln_alpha_imag = initMatrix(this.Dik);
        
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (i==j){
                    Complex hx = new Complex(this.hx2_alpha_real[i],this.hx2_alpha_imag[i]);
                    this.Ln_alpha_real.setEntry(rows, cols, help.cdiv(hx,this.GMR).log().getReal());
                    this.Ln_alpha_imag.setEntry(rows, cols, help.cdiv(hx,this.GMR).log().getImaginary());
                } else {
                    double Dik_real = this.Dik.getEntry(rows, cols);
                    double D_m_real = this.Dik_mirror_TakuNoda_real.getEntry(rows, cols);
                    double D_m_imag = this.Dik_mirror_TakuNoda_imag.getEntry(rows, cols);
                    Complex Dik_mirr = new Complex(D_m_real,D_m_imag);
                    this.Ln_alpha_real.setEntry(rows, cols, help.cdiv(Dik_real, Dik_mirr).log().getReal());
                    this.Ln_alpha_imag.setEntry(rows, cols, help.cdiv(Dik_real, Dik_mirr).log().getImaginary());
                }
            }
        }
    }
    
    /**
     * create Ln_beta_real and Ln_beta_imag matrices
     */
    private void calcLn_beta(){
        //init matrix
        this.Ln_beta_real = initMatrix(this.Dik);
        this.Ln_beta_imag = initMatrix(this.Dik);
        
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (i==j){
                    Complex hx = new Complex(this.hx2_beta_real[i],this.hx2_beta_imag[i]);
                    this.Ln_beta_real.setEntry(i, j, help.cdiv(hx,this.GMR).log().getReal());
                    this.Ln_beta_imag.setEntry(i, j, help.cdiv(hx,this.GMR).log().getImaginary());
                } else {
                    double Dik_real = this.Dik.getEntry(i, j);
                    double D_m_real = this.Dik_mirror_TakuNoda_real.getEntry(i, j);
                    double D_m_imag = this.Dik_mirror_TakuNoda_imag.getEntry(i, j);
                    Complex Dik_mirr = new Complex(D_m_real,D_m_imag);
                    this.Ln_beta_real.setEntry(i, j, help.cdiv(Dik_mirr, Dik_real).log().getReal());
                    this.Ln_beta_imag.setEntry(i, j, help.cdiv(Dik_mirr, Dik_real).log().getImaginary());
                }
            }
        }
    }
    
    public void calcLn(){
        calcLn_alpha();
        calcLn_beta();
        this.Ln_real = initMatrix(this.Dik);
        this.Ln_imag = initMatrix(this.Dik);
        
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                Complex a = new Complex(this.Ln_alpha_real.getEntry(i, j),
                                        this.Ln_alpha_imag.getEntry(i, j));
                Complex b = new Complex(this.Ln_beta_real.getEntry(i, j),
                                        this.Ln_beta_imag.getEntry(i, j));
                this.Ln_real.setEntry(i, j,
                    a.multiply(this.A).add(b.multiply((double)1 - this.A)).getReal());
                this.Ln_imag.setEntry(i, j,
                    a.multiply(this.A).add(b.multiply((double)1 - this.A)).getImaginary());
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
                    this.R_real.setEntry(i, j, this.R + this.X_real.getEntry(i, j));
                } else {
                    this.R_real.setEntry(i, j,this.X_real.getEntry(i, j));
                }
            }
        }
    }
    
    public void calcAll(){
        calcR();
        calcL();
        calcX();
    }

    public double getOmega() {
        return omega;
    }

    public void setOmega(double omega) {
        this.omega = omega;
    }

    public double getMu() {
        return mu;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    public double getMu2pi() {
        return mu2pi;
    }

    public void setMu2pi(double mu2pi) {
        this.mu2pi = mu2pi;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public double getA() {
        return A;
    }

    public void setA(double A) {
        this.A = A;
    }

    public RealMatrix getDik() {
        return Dik;
    }

    public void setDik(RealMatrix Dik) {
        this.Dik = Dik;
    }

    public RealMatrix getDik_mirror_TakuNoda_real() {
        return Dik_mirror_TakuNoda_real;
    }

    public void setDik_mirror_TakuNoda_real(RealMatrix Dik_mirror_TakuNoda_real) {
        this.Dik_mirror_TakuNoda_real = Dik_mirror_TakuNoda_real;
    }

    public RealMatrix getDik_mirror_TakuNoda_imag() {
        return Dik_mirror_TakuNoda_imag;
    }

    public void setDik_mirror_TakuNoda_imag(RealMatrix Dik_mirror_TakuNoda_imag) {
        this.Dik_mirror_TakuNoda_imag = Dik_mirror_TakuNoda_imag;
    }

    public double[] getHx2_alpha_real() {
        return hx2_alpha_real;
    }

    public void setHx2_alpha_real(double[] hx2_alpha_real) {
        this.hx2_alpha_real = hx2_alpha_real;
    }

    public double[] getHx2_alpha_imag() {
        return hx2_alpha_imag;
    }

    public void setHx2_alpha_imag(double[] hx2_alpha_imag) {
        this.hx2_alpha_imag = hx2_alpha_imag;
    }

    public double[] getHx2_beta_real() {
        return hx2_beta_real;
    }

    public void setHx2_beta_real(double[] hx2_beta_real) {
        this.hx2_beta_real = hx2_beta_real;
    }

    public double[] getHx2_beta_imag() {
        return hx2_beta_imag;
    }

    public void setHx2_beta_imag(double[] hx2_beta_imag) {
        this.hx2_beta_imag = hx2_beta_imag;
    }

    public double getGMR() {
        return GMR;
    }

    public void setGMR(double GMR) {
        this.GMR = GMR;
    }

    public double getR() {
        return R;
    }

    public void setR(double R) {
        this.R = R;
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

    public RealMatrix getLn_alpha_real() {
        return Ln_alpha_real;
    }

    public void setLn_alpha_real(RealMatrix Ln_alpha_real) {
        this.Ln_alpha_real = Ln_alpha_real;
    }

    public RealMatrix getLn_alpha_imag() {
        return Ln_alpha_imag;
    }

    public void setLn_alpha_imag(RealMatrix Ln_alpha_imag) {
        this.Ln_alpha_imag = Ln_alpha_imag;
    }

    public RealMatrix getLn_beta_real() {
        return Ln_beta_real;
    }

    public void setLn_beta_real(RealMatrix Ln_beta_real) {
        this.Ln_beta_real = Ln_beta_real;
    }

    public RealMatrix getLn_beta_imag() {
        return Ln_beta_imag;
    }

    public void setLn_beta_imag(RealMatrix Ln_beta_imag) {
        this.Ln_beta_imag = Ln_beta_imag;
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
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrical_parameters;

import emft_vol2.constants;
import flanagan.complex.ComplexMatrix;
import java.time.LocalTime;
import java.util.ArrayList;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math3.complex.Complex;
import tools.help;
import static tools.help.initComplexMatrix;
import static tools.help.initMatrix;
import static tools.help.makeComplexKronReduction;
import static tools.help.makeComplexMatrix;
import static tools.help.printComplexMatrix;
import static tools.help.printRealMatrix;
import static tools.help.printSymmComplexMatrix;
import static tools.help.printSymmRealMatrix;
import static tools.help.symm2phase;

/**
 * vypoctova metoda od T.Noda - obsahuje dve komplexne navratove cesty prudu v zemi
 * @author Mattto
 */
public class TakuNoda {
     //constants
    double omega =0;
    double mu = 0;
    double mu2pi = 0;
    int rows = 0;
    int cols = 0;
    double A = 0;
    int fv = 0;
    int gw = 0;
    
    //inputs
    RealMatrix Dik;
    RealMatrix Dik_mirror_real_alpha;
    RealMatrix Dik_mirror_imag_alpha;
    RealMatrix Dik_mirror_real_beta;
    RealMatrix Dik_mirror_imag_beta;
    double[] hx2_alpha_real;
    double[] hx2_alpha_imag;
    double[] hx2_beta_real;
    double[] hx2_beta_imag;
    double[] GMR;
    double[] R;
    double f;
    
    //results
    public RealMatrix R_real;
    public RealMatrix L_real;
    public RealMatrix L_imag;
    public RealMatrix X_real;
    public RealMatrix X_imag;
    public ComplexMatrix Z;
    
    public RealMatrix R_red;
    public RealMatrix L_red;
    public RealMatrix X_red;
    public ComplexMatrix Z_red;
    
    public RealMatrix R_red_symm;
    public RealMatrix L_red_symm;
    public RealMatrix X_red_symm;
    public ComplexMatrix Z_red_symm;
    
    //partial results
    RealMatrix Ln_alpha_real;
    RealMatrix Ln_alpha_imag;
    RealMatrix Ln_beta_real;
    RealMatrix Ln_beta_imag;
    RealMatrix Ln_real;
    RealMatrix Ln_imag;

    public TakuNoda(RealMatrix Dik,
                    RealMatrix Dik_mirror_real_alpha,
                    RealMatrix Dik_mirror_imag_alpha,
                    RealMatrix Dik_mirror_real_beta,
                    RealMatrix Dik_mirror_imag_beta,
                    double[] hx2_alpha_real,
                    double[] hx2_alpha_imag,
                    double[] hx2_beta_real,
                    double[] hx2_beta_imag,
                    ArrayList<elpam_input_conductor> cnd_list,
                    boolean exact_GMR,
                    boolean exact_Rac, int fv, int gw
                ){
//        GMR_calculation cnd = new GMR_calculation(Conductor);
//        Rac_calculation cnd2 = new Rac_calculation(Conductor);
        
        this.rows = Dik.getRowDimension();
        this.cols = Dik.getColumnDimension();
        this.GMR = new double[cnd_list.size()];
        this.R = new double[cnd_list.size()];

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
                this.R[i] = cnd_list.get(i).getRac();
            }
        } else {
            for (int i = 0; i < cnd_list.size(); i++) {
                this.R[i] = cnd_list.get(i).getRdc();
            }
        }

        this.Dik = Dik;
        this.Dik_mirror_real_alpha = Dik_mirror_real_alpha;
        this.Dik_mirror_imag_alpha = Dik_mirror_imag_alpha;
        this.Dik_mirror_real_beta = Dik_mirror_real_beta;
        this.Dik_mirror_imag_beta = Dik_mirror_imag_beta;
        this.hx2_alpha_real = hx2_alpha_real;
        this.hx2_alpha_imag = hx2_alpha_imag;
        this.hx2_beta_real = hx2_beta_real;
        this.hx2_beta_imag = hx2_beta_imag;
        this.f = constants.getFrequency();
        
        this.R_real = initMatrix(Dik);
        this.L_real = initMatrix(Dik);
        this.L_imag = initMatrix(Dik);
        this.X_real = initMatrix(Dik);
        this.X_imag = initMatrix(Dik);
        this.Z = initComplexMatrix(Dik);
        
        this.R_red = new Array2DRowRealMatrix(fv, fv);
        this.L_red = new Array2DRowRealMatrix(fv, fv);
        this.X_red = new Array2DRowRealMatrix(fv, fv);
        this.Z_red = new ComplexMatrix(fv, fv);
        
        this.R_red_symm = new Array2DRowRealMatrix(fv, fv);
        this.L_red_symm = new Array2DRowRealMatrix(fv, fv);
        this.X_red_symm = new Array2DRowRealMatrix(fv, fv);
        this.Z_red_symm = new ComplexMatrix(fv, fv);
        
        this.omega = (double)2*Math.PI*this.f;
        this.mu = (4e-4)*Math.PI; 
        this.mu2pi = (mu/(2*Math.PI));
        this.rows = Dik.getRowDimension();
        this.cols = Dik.getColumnDimension();
        this.A = 0.131836;
        this.fv = fv;
        this.gw = gw;
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
                    this.Ln_alpha_real.setEntry(i, j, help.cdiv(hx,this.GMR[i]).log().getReal());
                    this.Ln_alpha_imag.setEntry(i, j, help.cdiv(hx,this.GMR[i]).log().getImaginary());
                } else {
                    double Dik_real = this.Dik.getEntry(i, j);
                    double D_m_real = this.Dik_mirror_real_alpha.getEntry(i, j);
                    double D_m_imag = this.Dik_mirror_imag_alpha.getEntry(i, j);
                    Complex Dik_mirr = new Complex(D_m_real,D_m_imag);
                    this.Ln_alpha_real.setEntry(i, j, (double)-1*help.cdiv(Dik_real, Dik_mirr).log().getReal());        //znamienko minus kvoli i^2 = -1
                    this.Ln_alpha_imag.setEntry(i, j, (double)-1*help.cdiv(Dik_real, Dik_mirr).log().getImaginary());   //znamienko minus kvoli i^2 = -1
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
                    this.Ln_beta_real.setEntry(i, j, help.cdiv(hx,this.GMR[i]).log().getReal());
                    this.Ln_beta_imag.setEntry(i, j, help.cdiv(hx,this.GMR[i]).log().getImaginary());
                } else {
                    double Dik_real = this.Dik.getEntry(i, j);
                    double D_m_real = this.Dik_mirror_real_beta.getEntry(i, j);
                    double D_m_imag = this.Dik_mirror_imag_beta.getEntry(i, j);
                    Complex Dik_mirr = new Complex(D_m_real,D_m_imag);
                    this.Ln_beta_real.setEntry(i, j, help.cdiv(Dik_mirr, Dik_real).log().getReal());         //znamienko minus kvoli i^2 = -1    
                    this.Ln_beta_imag.setEntry(i, j, help.cdiv(Dik_mirr, Dik_real).log().getImaginary());    //znamienko minus kvoli i^2 = -1
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
                    this.R_real.setEntry(i, j, this.R[i] - this.X_imag.getEntry(i, j)); // minus kvoli i^2 = -1
                } else {
                    this.R_real.setEntry(i, j,(double)-1*this.X_imag.getEntry(i, j));
                }
            }
        }
    }
    
    public void calcZ(){
        calcR();
        calcX();
        this.Z = makeComplexMatrix(this.R_real,this.X_real);
    }
    
    public void calcXred(){
        calcZred();
        for (int i = 0; i < this.Z_red.getNrow(); i++) {
            for (int j = 0; j < this.Z_red.getNcol(); j++) {
                this.X_red.setEntry(i, j, this.Z_red.getElementCopy(i, j).getImag());
            }
        }
    }
    
    public void calcLred(){
        calcXred();
        for (int i = 0; i < this.X_red.getRowDimension(); i++) {
            for (int j = 0; j < this.X_red.getColumnDimension(); j++) {
                this.L_red.setEntry(i, j, this.X_red.getEntry(i, j) / this.omega);
            }
        }
    }
    
    public void calcRred(){
        calcZred();
        for (int i = 0; i < this.Z_red.getNrow(); i++) {
            for (int j = 0; j < this.Z_red.getNcol(); j++) {
                this.R_red.setEntry(i, j, this.Z_red.getElementCopy(i, j).getReal());
            }
        }
    }
    
    public void calcZred(){
        calcZ();
        this.Z_red = makeComplexKronReduction(this.Z, gw);
    }
    
    public void calcSymm(){
        calcRred();
        calcLred();
        this.Z_red_symm = symm2phase(this.Z_red);
        
        for (int i = 0; i < this.fv; i++) {
            for (int j = 0; j < this.fv; j++) {
                this.R_red_symm.setEntry(i, j, this.Z_red_symm.getElementCopy(i, j).getReal());
                this.X_red_symm.setEntry(i, j, this.Z_red_symm.getElementCopy(i, j).getImag());
                this.L_red_symm.setEntry(i, j, this.X_red_symm.getEntry(i, j) / this.omega);
            }
        }
    }
    
    public void calcAll(String type){
        if (type.equals("all")){
            calcSymm();
        } else {
            calcRred();
            calcLred();
        }
    }
    
    public void printAll(){
        System.out.println("Cas generovania");
        System.out.println(LocalTime.now());
        System.out.println("R [Ohm/km]");
        printRealMatrix(this.R_real);
        System.out.println("L [mH/km]");
        printRealMatrix(this.L_real.scalarMultiply(1000));
        System.out.println("X [Ohm/km]");
        printRealMatrix(this.X_real);
        System.out.println("Z [Ohm/km]");
        printComplexMatrix(this.Z);
        
        System.out.println("Rred [Ohm/km]");
        printRealMatrix(this.R_red);
        System.out.println("Lred [mH/km]");
        printRealMatrix(this.L_red.scalarMultiply(1000));
        System.out.println("Xred [Ohm/km]");
        printRealMatrix(this.X_red);
        System.out.println("Zred [Ohm/km]");
        printComplexMatrix(this.Z_red);
        
        System.out.println("Rred_symm [Ohm/km]");
        printSymmRealMatrix(this.R_red_symm);
        System.out.println("Lred_symm [mH/km]");
        printSymmRealMatrix(this.L_red_symm.scalarMultiply(1000));
        System.out.println("Xred_symm [Ohm/km]");
        printSymmRealMatrix(this.X_red_symm);
        System.out.println("Zred_symm [Ohm/km]");
        printSymmComplexMatrix(this.Z_red_symm);
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

    public RealMatrix getDik_mirror_real_alpha() {
        return Dik_mirror_real_alpha;
    }

    public void setDik_mirror_real_alpha(RealMatrix Dik_mirror_real_alpha) {
        this.Dik_mirror_real_alpha = Dik_mirror_real_alpha;
    }

    public RealMatrix getDik_mirror_imag_alpha() {
        return Dik_mirror_imag_alpha;
    }

    public void setDik_mirror_imag_alpha(RealMatrix Dik_mirror_imag_alpha) {
        this.Dik_mirror_imag_alpha = Dik_mirror_imag_alpha;
    }

    public RealMatrix getDik_mirror_real_beta() {
        return Dik_mirror_real_beta;
    }

    public void setDik_mirror_real_beta(RealMatrix Dik_mirror_real_beta) {
        this.Dik_mirror_real_beta = Dik_mirror_real_beta;
    }

    public RealMatrix getDik_mirror_imag_beta() {
        return Dik_mirror_imag_beta;
    }

    public void setDik_mirror_imag_beta(RealMatrix Dik_mirror_imag_beta) {
        this.Dik_mirror_imag_beta = Dik_mirror_imag_beta;
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

    public double[] getGMR() {
        return GMR;
    }

    public void setGMR(double[] GMR) {
        this.GMR = GMR;
    }

    public double[] getR() {
        return R;
    }

    public void setR(double[] R) {
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

    public ComplexMatrix getZ() {
        return Z;
    }

    public void setZ(ComplexMatrix Z) {
        this.Z = Z;
    }

    public RealMatrix getR_red() {
        return R_red;
    }

    public void setR_red(RealMatrix R_red) {
        this.R_red = R_red;
    }

    public RealMatrix getL_red() {
        return L_red;
    }

    public void setL_red(RealMatrix L_red) {
        this.L_red = L_red;
    }

    public RealMatrix getX_red() {
        return X_red;
    }

    public void setX_red(RealMatrix X_red) {
        this.X_red = X_red;
    }

    public ComplexMatrix getZ_red() {
        return Z_red;
    }

    public void setZ_red(ComplexMatrix Z_red) {
        this.Z_red = Z_red;
    }

    public RealMatrix getR_red_symm() {
        return R_red_symm;
    }

    public void setR_red_symm(RealMatrix R_red_symm) {
        this.R_red_symm = R_red_symm;
    }

    public RealMatrix getL_red_symm() {
        return L_red_symm;
    }

    public void setL_red_symm(RealMatrix L_red_symm) {
        this.L_red_symm = L_red_symm;
    }

    public RealMatrix getX_red_symm() {
        return X_red_symm;
    }

    public void setX_red_symm(RealMatrix X_red_symm) {
        this.X_red_symm = X_red_symm;
    }

    public ComplexMatrix getZ_red_symm() {
        return Z_red_symm;
    }

    public void setZ_red_symm(ComplexMatrix Z_red_symm) {
        this.Z_red_symm = Z_red_symm;
    }
    
    
}

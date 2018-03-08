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
import static tools.help.arraySum;
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
    int fv = 0;
    int gw = 0;
    
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
    RealMatrix Ln;

    public Basic (  RealMatrix Dik,
                    ArrayList<elpam_input_conductor> cnd_list,
                    boolean exact_GMR,
                    boolean exact_Rac,
                    int fv, int gw
                ){
//        GMR_calculation cnd = new GMR_calculation(Conductor);
//        Rac_calculation cnd2 = new Rac_calculation(Conductor);
        this.rows = Dik.getRowDimension();
        this.cols = Dik.getColumnDimension();
        this.GMR = new double[cnd_list.size()];
        this.R_cnd = new double[cnd_list.size()];
        this.GMR = new double[cnd_list.size()];
        this.rho_gnd = new double[cnd_list.size()];
        this.fv = fv;
        this.gw = gw;
        
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
        this.Z = initComplexMatrix(Dik);
        
        this.R_red = new Array2DRowRealMatrix(fv, fv);
        this.L_red = new Array2DRowRealMatrix(fv, fv);
        this.X_red = new Array2DRowRealMatrix(fv, fv);
        this.Z_red = new ComplexMatrix(fv, fv);
        
        this.R_red_symm = new Array2DRowRealMatrix(fv, fv);
        this.L_red_symm = new Array2DRowRealMatrix(fv, fv);
        this.X_red_symm = new Array2DRowRealMatrix(fv, fv);
        this.Z_red_symm = new ComplexMatrix(fv, fv);
        
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
    
    public void calcZ(){
        calcR();
        calcX();
        this.Z = makeComplexMatrix(this.R, this.X);
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

    public double getRg() {
        return Rg;
    }

    public void setRg(double Rg) {
        this.Rg = Rg;
    }

    public double getDg() {
        return Dg;
    }

    public void setDg(double Dg) {
        this.Dg = Dg;
    }

    public RealMatrix getDik() {
        return Dik;
    }

    public void setDik(RealMatrix Dik) {
        this.Dik = Dik;
    }

    public double[] getGMR() {
        return GMR;
    }

    public void setGMR(double[] GMR) {
        this.GMR = GMR;
    }

    public double[] getR_cnd() {
        return R_cnd;
    }

    public void setR_cnd(double[] R_cnd) {
        this.R_cnd = R_cnd;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public double[] getRho_gnd() {
        return rho_gnd;
    }

    public void setRho_gnd(double[] rho_gnd) {
        this.rho_gnd = rho_gnd;
    }

    public double getRho_avg() {
        return rho_avg;
    }

    public void setRho_avg(double rho_avg) {
        this.rho_avg = rho_avg;
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

    public RealMatrix getX() {
        return X;
    }

    public void setX(RealMatrix X) {
        this.X = X;
    }

    public ComplexMatrix getZ() {
        return Z;
    }

    public void setZ(ComplexMatrix Z) {
        this.Z = Z;
    }

    public RealMatrix getLn() {
        return Ln;
    }

    public void setLn(RealMatrix Ln) {
        this.Ln = Ln;
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

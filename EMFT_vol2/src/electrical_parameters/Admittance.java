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
import tools.help;
import static tools.help.Complex2ImagMatrix;
import static tools.help.makeComplexKronReduction;
import static tools.help.makeComplexMatrix;
import static tools.help.makeRealKronReduction;
import static tools.help.phase2symm;
import static tools.help.printComplexMatrix;
import static tools.help.printRealMatrix;
import static tools.help.printSymmComplexMatrix;
import static tools.help.printSymmRealMatrix;
import static tools.help.symm2phase;

/**
 *
 * @author Mattto_Silver
 */
public class Admittance {
    
    //constants
    double omega = 0;
    double mu = 0;
    double cnst = (double)1/(8.854187e-12 * 1 * 2 * Math.PI);
    int fv = 0;
    int gw = 0;
    
    //inputs
    RealMatrix Dik;
    RealMatrix Dik_mirror;
    double[] hx2;
    double[] r_cnd; //alebo r_zv
    double f;
    
    //results
    public RealMatrix P;
    
    public RealMatrix P_red;
    public RealMatrix C;
    public RealMatrix B;
    public ComplexMatrix Y;
    
    public RealMatrix C_symm;
    public RealMatrix B_symm;
    public ComplexMatrix Y_symm;

    public Admittance ( RealMatrix Dik, 
                        RealMatrix Dik_mirror,
                        double[] hx2,
                        ArrayList<elpam_input_conductor> cnd_list,
                        double krok, // m
                        int n_zv, // -
                        int fv, int gw
                        ){
        this.r_cnd = new double[cnd_list.size()];
        if (cnd_list.get(0).isBundle()) {
            double rho = krok/(2*Math.sin(Math.PI/n_zv));
            for (int i = 0; i < cnd_list.size(); i++) {
                this.r_cnd[i] = Math.pow(n_zv * (cnd_list.get(i).getD()/2) * Math.pow(rho, n_zv-1), (double)1/n_zv);
            }
        } else {
            for (int i = 0; i < cnd_list.size(); i++) {
                this.r_cnd[i] = cnd_list.get(i).getD()/2;
            }
        }
        this.fv = fv;
        this.gw = gw;
        this.f = constants.getFrequency();

        this.Dik = Dik;
        this.Dik_mirror = Dik_mirror;
        this.hx2 = hx2;
        
        this.P = new Array2DRowRealMatrix(fv+gw,fv+gw);
        this.P_red = new Array2DRowRealMatrix(fv,fv);
        this.C = new Array2DRowRealMatrix(fv,fv);
        this.B = new Array2DRowRealMatrix(fv,fv);
        this.Y = new ComplexMatrix(fv, fv);
        
        this.C_symm = new Array2DRowRealMatrix(fv, fv);
        this.B_symm = new Array2DRowRealMatrix(fv, fv);
        this.Y_symm = new ComplexMatrix(fv, fv);
        
        this.omega = (double)2*Math.PI*this.f;
        this.cnst = (double)1/(8.854187e-9 * 1 * 2 * Math.PI);
    }
    
    public void calcAll(String type){
        for (int i = 0; i < this.fv + this.gw; i++) {
            for (int j = 0; j < this.fv + this.gw; j++) {
                if (i==j){
                    this.P.setEntry(i, j, this.cnst*Math.log(this.hx2[i]/this.r_cnd[i]));
                } else {
                    this.P.setEntry(i, j, this.cnst*Math.log(this.Dik_mirror.getEntry(i, j)/this.Dik.getEntry(i, j)));
                }
            }
        }
        this.P_red = makeRealKronReduction(this.P, this.gw);
        this.C = this.P_red.inverse();
        this.B = C.scalarMultiply(this.omega);
        this.Y = makeComplexMatrix(new Array2DRowRealMatrix(fv, fv), this.B);
        
        if(type.equals("all")){
            this.Y_symm = phase2symm(this.Y);
            this.B_symm = Complex2ImagMatrix(this.Y_symm);
            this.C_symm = this.B_symm.scalarMultiply((double)1/this.omega);
        }
    }
    
    public void printAll(){
        System.out.println("Cas generovania");
        System.out.println(LocalTime.now());
        System.out.println("P [km/F]");
        System.out.println(this.P);
        System.out.println("P_red [km/F]");
        System.out.println(this.P_red);
        
        System.out.println("C [nF/km]");
        printRealMatrix(this.C.scalarMultiply(1e9));
        System.out.println("B [uS/km]");
        printRealMatrix(this.B.scalarMultiply(1e6));
        System.out.println("Y [S/km]");
        printComplexMatrix(this.Y);

        System.out.println("C_Symm [nF/km]");
        printRealMatrix(this.C_symm.scalarMultiply(1e9));
        System.out.println("B_symm [uS/km]");
        printRealMatrix(this.B_symm.scalarMultiply(1e6));
        System.out.println("Y_symm [S/km]");
        printComplexMatrix(this.Y_symm);

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

    public double getCnst() {
        return cnst;
    }

    public void setCnst(double cnst) {
        this.cnst = cnst;
    }

    public int getFv() {
        return fv;
    }

    public void setFv(int fv) {
        this.fv = fv;
    }

    public int getGw() {
        return gw;
    }

    public void setGw(int gw) {
        this.gw = gw;
    }

    public RealMatrix getDik() {
        return Dik;
    }

    public void setDik(RealMatrix Dik) {
        this.Dik = Dik;
    }

    public RealMatrix getDik_mirror() {
        return Dik_mirror;
    }

    public void setDik_mirror(RealMatrix Dik_mirror) {
        this.Dik_mirror = Dik_mirror;
    }

    public double[] getHx2() {
        return hx2;
    }

    public void setHx2(double[] hx2) {
        this.hx2 = hx2;
    }

    public double[] getR_cnd() {
        return r_cnd;
    }

    public void setR_cnd(double[] r_cnd) {
        this.r_cnd = r_cnd;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public RealMatrix getP() {
        return P;
    }

    public void setP(RealMatrix P) {
        this.P = P;
    }

    public RealMatrix getP_red() {
        return P_red;
    }

    public void setP_red(RealMatrix P_red) {
        this.P_red = P_red;
    }

    public RealMatrix getC() {
        return C;
    }

    public void setC(RealMatrix C) {
        this.C = C;
    }

    public RealMatrix getB() {
        return B;
    }

    public void setB(RealMatrix B) {
        this.B = B;
    }

    public ComplexMatrix getY() {
        return Y;
    }

    public void setY(ComplexMatrix Y) {
        this.Y = Y;
    }

    public RealMatrix getC_symm() {
        return C_symm;
    }

    public void setC_symm(RealMatrix C_symm) {
        this.C_symm = C_symm;
    }

    public RealMatrix getB_symm() {
        return B_symm;
    }

    public void setB_symm(RealMatrix B_symm) {
        this.B_symm = B_symm;
    }

    public ComplexMatrix getY_symm() {
        return Y_symm;
    }

    public void setY_symm(ComplexMatrix Y_symm) {
        this.Y_symm = Y_symm;
    }

   
    
}

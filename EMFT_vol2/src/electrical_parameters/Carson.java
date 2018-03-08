/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrical_parameters;

import emft_vol2.constants;
import flanagan.complex.ComplexMatrix;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.time.LocalTime;
import java.util.ArrayList;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;
import static tools.help.arraySum;
import static tools.help.initComplexMatrix;
import static tools.help.initMatrix;
import static tools.help.makeComplexKronReduction;
import static tools.help.makeComplexMatrix;
import static tools.help.printRealMatrix;
import static tools.help.printSymmRealMatrix;
import static tools.help.symm2phase;

/**
 * Carsonova vypoctova metoda elektrickych parametrov
 * @author Mattto
 */
public class Carson {
    
    //inputs
    RealMatrix Dik;
    RealMatrix Dik_mirror;
    RealMatrix Fik;
    double[] hx2;
    double[] GMR;
    double[] R;
    double f;
    double[] rho_gnd;
    int fv = 0;
    int gw = 0;
    double omega =0;
    
    //results
    public RealMatrix Rg;
    public RealMatrix Lg;
    public RealMatrix Xg;
    public RealMatrix R_no_gnd;
    public RealMatrix R_gnd;
    public RealMatrix L_no_gnd;
    public RealMatrix L_gnd;
    public RealMatrix X_no_gnd;
    public RealMatrix X_gnd;
    public ComplexMatrix Z_no_gnd;
    public ComplexMatrix Z_gnd;
    
    public RealMatrix R_red_gnd;
    public RealMatrix L_red_gnd;
    public RealMatrix X_red_gnd;
    public ComplexMatrix Z_red_gnd;
    public RealMatrix R_red_no_gnd;
    public RealMatrix L_red_no_gnd;
    public RealMatrix X_red_no_gnd;
    public ComplexMatrix Z_red_no_gnd;
    
    public ComplexMatrix Z_red_gnd_symm;
    public RealMatrix L_red_gnd_symm;
    public RealMatrix R_red_gnd_symm;
    public RealMatrix X_red_gnd_symm;
    public ComplexMatrix Z_red_no_gnd_symm;
    public RealMatrix L_red_no_gnd_symm;
    public RealMatrix R_red_no_gnd_symm;
    public RealMatrix X_red_no_gnd_symm;
    
    //partial results
    RealMatrix kik;
    
    public Carson(  RealMatrix Dik,
                    RealMatrix Dik_mirror,
                    RealMatrix Fik,
                    double[] hx2,
                    ArrayList<elpam_input_conductor> cnd_list,
                    boolean exact_GMR,
                    boolean exact_Rac, int fv, int gw
    ){
//        GMR_calculation cnd = new GMR_calculation(Conductor);
//        Rac_calculation cnd2 = new Rac_calculation(Conductor);
        
        this.GMR = new double[cnd_list.size()];
        this.hx2 = new double[cnd_list.size()];
        this.R = new double[cnd_list.size()];
        this.GMR = new double[cnd_list.size()];
        this.rho_gnd = new double[cnd_list.size()];
        this.f = constants.getFrequency();
        this.omega = (double)2*Math.PI*this.f;
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
                this.R[i] = cnd_list.get(i).getRac();
            }
        } else {
            for (int i = 0; i < cnd_list.size(); i++) {
                this.R[i] = cnd_list.get(i).getRdc();
            }
        }

        this.Dik = Dik;
        this.Dik_mirror = Dik_mirror;
        this.Fik = Fik;
        this.hx2 = hx2;
        for (int i = 0; i < cnd_list.size(); i++) {
            this.rho_gnd[i] = cnd_list.get(i).getRho_ground();
        }
        
        this.Rg = initMatrix(Dik);
        this.Lg = initMatrix(Dik);
        this.Xg = initMatrix(Dik);
        this.L_no_gnd = initMatrix(Dik);
        this.X_no_gnd = initMatrix(Dik);
        this.R_no_gnd = initMatrix(Dik);
        this.R_gnd = initMatrix(Dik);
        this.L_gnd = initMatrix(Dik);
        this.X_gnd = initMatrix(Dik);
        this.Z_gnd = initComplexMatrix(Dik);
        this.Z_no_gnd = initComplexMatrix(Dik);
        
        this.R_red_gnd = new Array2DRowRealMatrix(fv, fv);
        this.L_red_gnd = new Array2DRowRealMatrix(fv, fv);
        this.X_red_gnd = new Array2DRowRealMatrix(fv, fv);
        this.Z_red_gnd = new ComplexMatrix(fv, fv);
        this.R_red_no_gnd = new Array2DRowRealMatrix(fv, fv);
        this.L_red_no_gnd = new Array2DRowRealMatrix(fv, fv);
        this.X_red_no_gnd = new Array2DRowRealMatrix(fv, fv);
        this.Z_red_no_gnd = new ComplexMatrix(fv, fv);
        
        this.L_red_gnd_symm = new Array2DRowRealMatrix(fv, fv);
        this.R_red_gnd_symm = new Array2DRowRealMatrix(fv, fv);
        this.X_red_gnd_symm = new Array2DRowRealMatrix(fv, fv);
        this.Z_red_gnd_symm = new ComplexMatrix(fv, fv);
        this.L_red_no_gnd_symm = new Array2DRowRealMatrix(fv, fv);
        this.R_red_no_gnd_symm = new Array2DRowRealMatrix(fv, fv);
        this.X_red_no_gnd_symm = new Array2DRowRealMatrix(fv, fv);
        this.Z_red_no_gnd_symm = new ComplexMatrix(fv, fv);
        
    }
    
    //public functions
    
    public void printAll(){
        System.out.println("Cas generovania");
        System.out.println(LocalTime.now());
        System.out.println("Rg [Ohm/km]");
//        printRealMatrix(this.Rg);
//        System.out.println("Lg [mH/km]");
//        printRealMatrix(this.Lg.scalarMultiply(1000));
//        System.out.println("Xg [Ohm/km]");
//        printRealMatrix(this.Xg);
//        
//        System.out.println("R_no_gnd [Ohm/km]");
//        printRealMatrix(this.R_no_gnd);
//        System.out.println("L_no_gnd [mH/km]");
//        printRealMatrix(this.L_no_gnd.scalarMultiply(1000));
//        System.out.println("X_no_gnd [Ohm/km]");
//        printRealMatrix(this.X_no_gnd);
//        System.out.println("Z_no_gnd [Ohm/km]");
//        printComplexMatrix(this.Z_no_gnd);
        
        System.out.println("R_gnd [Ohm/km]");
        printRealMatrix(this.R_gnd);
        System.out.println("L_gnd [mH/km]");
        printRealMatrix(this.L_gnd.scalarMultiply(1000));
//        System.out.println("X_gnd [Ohm/km]");
//        printRealMatrix(this.X_gnd);
//        System.out.println("Z_gnd [Ohm/km]");
//        printComplexMatrix(this.Z_gnd);
        
//        System.out.println("Rred_no_gnd [Ohm/km]");
//        printRealMatrix(this.R_red_no_gnd);
//        System.out.println("Lred_no_gnd [mH/km]");
//        printRealMatrix(this.L_red_no_gnd.scalarMultiply(1000));
//        System.out.println("Xred_no_gnd [Ohm/km]");
//        printRealMatrix(this.X_red_no_gnd);
//        System.out.println("Zred_no_gnd [Ohm/km]");
//        printComplexMatrix(this.Z_red_no_gnd);
        
        System.out.println("Rred_gnd [Ohm/km]");
        printRealMatrix(this.R_red_gnd);
        System.out.println("Lred_gnd [mH/km]");
        printRealMatrix(this.L_red_gnd.scalarMultiply(1000));
//        System.out.println("Xred_gnd [Ohm/km]");
//        printRealMatrix(this.X_red_gnd);
//        System.out.println("Zred_gnd [Ohm/km]");
//        printComplexMatrix(this.Z_red_gnd);
        
//        System.out.println("Rred_no_gnd_symm [Ohm/km]");
//        printRealMatrix(this.R_red_no_gnd_symm);
//        System.out.println("Lred_no_gnd_symm [mH/km]");
//        printRealMatrix(this.L_red_no_gnd_symm.scalarMultiply(1000));
//        System.out.println("Xred_no_gnd_symm [Ohm/km]");
//        printRealMatrix(this.X_red_no_gnd_symm);
//        System.out.println("Zred_no_gnd_symm [Ohm/km]");
//        printComplexMatrix(this.Z_red_no_gnd_symm);
        
        System.out.println("Rred_gnd_symm [Ohm/km]");
        printSymmRealMatrix(this.R_red_gnd_symm);
        System.out.println("Lred_gnd_symm [mH/km]");
        printSymmRealMatrix(this.L_red_gnd_symm.scalarMultiply(1000));
//        System.out.println("Xred_gnd_symm [Ohm/km]");
//        printRealMatrix(this.X_red_gnd_symm);
//        System.out.println("Zred_gnd_symm [Ohm/km]");
//        printComplexMatrix(this.Z_red_gnd_symm);

    }
    
    public void calcAll(String type){
        if (type.equals("all")){
            calcSymm();
        } else {
            calcRred_gnd();
            calcLred_gnd();
            calcRred_no_gnd();
            calcLred_no_gnd();
        }
    }
    
    public void calcSymm(){
        calcRred_gnd();
        calcLred_gnd();
        calcRred_no_gnd();
        calcLred_no_gnd();
        this.Z_red_gnd_symm = symm2phase(this.Z_red_gnd);
        this.Z_red_no_gnd_symm = symm2phase(this.Z_red_no_gnd);
        
        for (int i = 0; i < this.fv; i++) {
            for (int j = 0; j < this.fv; j++) {
                this.R_red_no_gnd_symm.setEntry(i, j, this.Z_red_no_gnd_symm.getElementCopy(i, j).getReal());
                this.X_red_no_gnd_symm.setEntry(i, j, this.Z_red_no_gnd_symm.getElementCopy(i, j).getImag());
                this.L_red_no_gnd_symm.setEntry(i, j, this.X_red_no_gnd_symm.getEntry(i, j) / this.omega);
                this.R_red_gnd_symm.setEntry(i, j, this.Z_red_gnd_symm.getElementCopy(i, j).getReal());
                this.X_red_gnd_symm.setEntry(i, j, this.Z_red_gnd_symm.getElementCopy(i, j).getImag());
                this.L_red_gnd_symm.setEntry(i, j, this.X_red_gnd_symm.getEntry(i, j) / this.omega);
            }
        }
    }
    
    public void calcXred_gnd(){
        calcZred_gnd();
        for (int i = 0; i < this.Z_red_gnd.getNrow(); i++) {
            for (int j = 0; j < this.Z_red_gnd.getNcol(); j++) {
                this.X_red_gnd.setEntry(i, j, this.Z_red_gnd.getElementCopy(i, j).getImag());
            }
        }
    }
    
    public void calcLred_gnd(){
        calcXred_gnd();
        for (int i = 0; i < this.X_red_gnd.getRowDimension(); i++) {
            for (int j = 0; j < this.X_red_gnd.getColumnDimension(); j++) {
                this.L_red_gnd.setEntry(i, j, this.X_red_gnd.getEntry(i, j) / this.omega);
            }
        }
    }
    
    public void calcRred_gnd(){
        calcZred_gnd();
        for (int i = 0; i < this.Z_red_gnd.getNrow(); i++) {
            for (int j = 0; j < this.Z_red_gnd.getNcol(); j++) {
                this.R_red_gnd.setEntry(i, j, this.Z_red_gnd.getElementCopy(i, j).getReal());
            }
        }
    }
    
    public void calcZred_gnd(){
        calcZ_gnd();
        this.Z_red_gnd = makeComplexKronReduction(this.Z_gnd, gw);
    }
    
    public void calcXred_no_gnd(){
        calcZred_no_gnd();
        for (int i = 0; i < this.Z_red_no_gnd.getNrow(); i++) {
            for (int j = 0; j < this.Z_red_no_gnd.getNcol(); j++) {
                this.X_red_no_gnd.setEntry(i, j, this.Z_red_no_gnd.getElementCopy(i, j).getImag());
            }
        }
    }
    
    public void calcLred_no_gnd(){
        calcXred_no_gnd();
        for (int i = 0; i < this.X_red_no_gnd.getRowDimension(); i++) {
            for (int j = 0; j < this.X_red_no_gnd.getColumnDimension(); j++) {
                this.L_red_no_gnd.setEntry(i, j, this.X_red_no_gnd.getEntry(i, j) / this.omega);
            }
        }
    }
    
    public void calcRred_no_gnd(){
        calcZred_no_gnd();
        for (int i = 0; i < this.Z_red_no_gnd.getNrow(); i++) {
            for (int j = 0; j < this.Z_red_no_gnd.getNcol(); j++) {
                this.R_red_no_gnd.setEntry(i, j, this.Z_red_no_gnd.getElementCopy(i, j).getReal());
            }
        }
    }
    
    public void calcZred_no_gnd(){
        calcZ_no_gnd();
        this.Z_red_no_gnd = makeComplexKronReduction(this.Z_no_gnd, gw);
    }
    
    public void calcZ_gnd(){
        calcR_gnd();
        calcX_gnd();
        this.Z_gnd = makeComplexMatrix(this.R_gnd, this.X_gnd);
    }
    
    public void calcZ_no_gnd(){
        calcR_no_gnd();
        calcX_no_gnd();
        this.Z_no_gnd = makeComplexMatrix(this.R_no_gnd, this.X_no_gnd);
    }
    
    public void calcRg(){
//        this.Rg = this.Dik.copy();
//        this.Rg = clearMatrix(this.Rg);
        double[] b;
        double[] c;
        double[] d;
        double kik_rg;
        double fik;
        
        b = b();
        c = c();
        d = d();
        calckik();
        
        for (int i = 0; i < this.kik.getRowDimension(); i++) {
            for (int j = 0; j < this.kik.getColumnDimension(); j++) {
                fik = this.Fik.getEntry(i,j);
                kik_rg = this.kik.getEntry(i,j);
                this.Rg.setEntry(i,j,   (4e-4)*omega*(
                                        Math.PI/8 
                                        - b[0] * kik_rg * Math.cos(fik)
                                        + b[1] *(
                                                (c[1] - Math.log(kik_rg))  
                                                * pow(kik_rg,2) * Math.cos(2*fik)                   
                                                + fik*pow(kik_rg,2) * Math.sin(2*fik)            
                                                )
                                        + b[2] * pow(kik_rg,3) * Math.cos(3*fik)
                                        - d[3] * pow(kik_rg,4) * Math.cos(4*fik)
                                        - b[4] * kik_rg * Math.cos(fik)
                                        + b[5] *(
                                                (c[5] - Math.log(kik_rg))  
                                                * pow(kik_rg,2) * Math.cos(2*fik)                   
                                                + fik * pow(kik_rg,2) * Math.sin(2*fik)            
                                                )
                                        + b[6] * pow(kik_rg,3) * Math.cos(3*fik)
                                        - d[7] * pow(kik_rg,4) * Math.cos(4*fik)
                                        )
                );
            }
        }
    }
    
    public void calcXg(){
//        this.Xg = this.Dik.copy();
//        this.Xg = clearMatrix(this.Xg);
        double[] b;
        double[] c;
        double[] d;
        double kik_xg;
        double fik;
        
        b = b();
        c = c();
        d = d();
        calckik();
        
        for (int i = 0; i < this.kik.getRowDimension(); i++) {
            for (int j = 0; j < this.kik.getColumnDimension(); j++) {
                fik = this.Fik.getEntry(i,j);
                kik_xg = this.kik.getEntry(i,j);
                this.Xg.setEntry(i,j,    (4e-4)*omega*(
                                         ((double)1/2) * (0.6159315 - Math.log(kik_xg)) 
                                        + b[0] * kik_xg * Math.cos(fik)
                                        - d[1] * pow(kik_xg,2) * Math.cos(2*fik)
                                        + b[2] * pow(kik_xg,3) * Math.cos(3*fik)
                                        - b[3] *(
                                                (c[3] - Math.log(kik_xg))  
                                                * pow(kik_xg,4) * Math.cos(4*fik)                   
                                                + fik * pow(kik_xg,4) * Math.sin(4*fik)            
                                                )
                                        + b[4] * kik_xg * Math.cos(fik)
                                        - d[5] * pow(kik_xg,2) * Math.cos(2*fik)
                                        + b[6] * pow(kik_xg,3) * Math.cos(3*fik)
                                        - b[7] *(
                                                (c[7] - Math.log(kik_xg))  
                                                * pow(kik_xg,4) * Math.cos(4*fik)                   
                                                + fik * pow(kik_xg,4) * Math.sin(4*fik)            
                                                )
                                        )
                );
            }
        }
    }
    
    public void calcLg(){
        calcXg();
        this.Lg = this.Xg.scalarMultiply((double)1/omega);
//        for (int i = 0; i < this.Lg.getRowDimension(); i++) {
//            for (int j = 0; j < this.Lg.getColumnDimension(); j++) {
//                this.Lg.setEntry(i, j, this.Xg.getEntry(j, j)/omega);
//            }
//        }
    }
    
    public void calcL_no_gnd(){
//        this.L_no_gnd = this.Dik.copy();
//        this.L_no_gnd = clearMatrix(this.L_no_gnd);
        for (int i = 0; i < this.Dik.getRowDimension(); i++) {
            for (int j = 0; j < this.Dik.getRowDimension(); j++) {
                if (i==j) {
                    this.L_no_gnd.setEntry(i, j, Lii(this.hx2[i],this.GMR[i]));
                } else {
                    this.L_no_gnd.setEntry(i, j, Lik(this.Dik.getEntry(i, j), this.Dik_mirror.getEntry(i, j)));
                }
            }
        }
    }
    
    public void calcX_no_gnd(){
//        this.X_no_gnd = this.Dik.copy();
//        this.X_no_gnd = clearMatrix(this.X_no_gnd);
        calcL_no_gnd();
        this.X_no_gnd = this.L_no_gnd.copy().scalarMultiply(2*Math.PI*this.f);
    }
    
    public void calcR_no_gnd(){
//        this.R_no_gnd = this.Dik.copy();
//        this.R_no_gnd = clearMatrix(this.R_no_gnd);
        for (int i = 0; i < this.Dik.getRowDimension(); i++) {
            for (int j = 0; j < this.Dik.getRowDimension(); j++) {
                if (i==j) {
                    this.R_no_gnd.setEntry(i, j, this.R[i]);
                } else {
                    this.R_no_gnd.setEntry(i, j, 0);
                }
            }
        }
    }
    
    public void calcR_gnd(){
//        this.R_gnd = this.Dik.copy();
//        this.R_gnd = clearMatrix(this.R_gnd);
        calcRg();
        for (int i = 0; i < this.Dik.getRowDimension(); i++) {
            for (int j = 0; j < this.Dik.getRowDimension(); j++) {
                if (i==j) {
                    this.R_gnd.setEntry(i, j, this.R[i] + this.Rg.getEntry(i, j));
                } else {
                    this.R_gnd.setEntry(i, j, this.Rg.getEntry(i, j));
                }
            }
        }
    }
    
    public void calcL_gnd(){
//        this.L_gnd = this.Dik.copy();
//        this.L_gnd = clearMatrix(this.L_gnd);
        calcLg();
        for (int i = 0; i < this.Dik.getRowDimension(); i++) {
            for (int j = 0; j < this.Dik.getRowDimension(); j++) {
                if (i==j) {
                    this.L_gnd.setEntry(i, j, Lii(this.hx2[i],this.GMR[i]) + this.Lg.getEntry(i,j));
                } else {
                    this.L_gnd.setEntry(i, j, Lik(this.Dik.getEntry(i, j), this.Dik_mirror.getEntry(i, j)) + this.Lg.getEntry(i,j));
                }
            }
        }
    }
    
    public void calcX_gnd(){
//        this.X_gnd = this.Dik.copy();
//        this.X_gnd = clearMatrix(this.X_gnd);
        calcL_gnd();
        this.X_gnd = this.L_gnd.scalarMultiply(2*Math.PI*this.f);
    }
    
    //private functions
    
    private void calckik(){
        //make average of ground resistivity
        double gnd = arraySum(this.rho_gnd)/this.rho_gnd.length;
        this.kik = initMatrix(Dik);
        for (int i = 0; i < this.Dik_mirror.getRowDimension(); i++) {
            for (int j = 0; j < this.Dik_mirror.getColumnDimension(); j++) {
                this.kik.setEntry(i,j,(4e-4)*Math.PI*sqrt(5)*sqrt(this.f/gnd)*this.Dik_mirror.getEntry(i,j));
            }
        }
    }
    
    private double[] b(){
        double[] result = new double[8];
        result[0] = sqrt(2)/6;
        result[1] = (double)1/16;
        result[2] = result[0]/15;
        result[3] = result[1]/24;
        result[4] = -result[2]/35;
        result[5] = -result[3]/48;
        result[6] = -result[4]/63;
        result[7] = -result[5]/80;
        return result;
    }
    
    private double[] d(){
        double[] result = new double[8];
        double[] b;
        b = b();
        for (int i = 0; i < b.length; i++) {
           result[i] = (Math.PI/4)*b[i]; 
        }
        return result;
    }
    
    private double[] c(){
        double[] result = new double[8];
        result[0] = 0;
        result[1] = 1.3659315;
        result[2] = 0;
        result[3] = result[1]+(double)1/4+(double)1/6;
        result[4] = 0;
        result[5] = result[3]+(double)1/6+(double)1/8;
        result[6] = 0;
        result[7] = result[5]+(double)1/8+(double)1/10;
        return result;
    }
    
    private double Lii(double hx2, double GMR){
        return Math.abs((2e-4) * Math.log(hx2/GMR)); //[H/km]
    }
    
    private double Lik(double Dik, double Dik_mirror){
        return Math.abs((2e-4) * Math.log(Dik/Dik_mirror));
    }
    
    //Getters and Setters area

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

    public RealMatrix getFik() {
        return Fik;
    }

    public void setFik(RealMatrix Fik) {
        this.Fik = Fik;
    }

    public double[] getHx2() {
        return hx2;
    }

    public void setHx2(double[] hx2) {
        this.hx2 = hx2;
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

    public double[] getRho_gnd() {
        return rho_gnd;
    }

    public void setRho_gnd(double rho_cnd) {
        this.rho_gnd = rho_gnd;
    }

    public RealMatrix getRg() {
        return Rg;
    }

    public void setRg(RealMatrix Rg) {
        this.Rg = Rg;
    }

    public RealMatrix getXg() {
        return Xg;
    }

    public void setXg(RealMatrix Xg) {
        this.Xg = Xg;
    }

    public RealMatrix getR_no_gnd() {
        return R_no_gnd;
    }

    public void setR_no_gnd(RealMatrix R_no_gnd) {
        this.R_no_gnd = R_no_gnd;
    }

    public RealMatrix getR_gnd() {
        return R_gnd;
    }

    public void setR_gnd(RealMatrix R_gnd) {
        this.R_gnd = R_gnd;
    }

    public RealMatrix getL_no_gnd() {
        return L_no_gnd;
    }

    public void setL_no_gnd(RealMatrix L_no_gnd) {
        this.L_no_gnd = L_no_gnd;
    }

    public RealMatrix getL_gnd() {
        return L_gnd;
    }

    public void setL_gnd(RealMatrix L_gnd) {
        this.L_gnd = L_gnd;
    }

    public RealMatrix getX_no_gnd() {
        return X_no_gnd;
    }

    public void setX_no_gnd(RealMatrix X_no_gnd) {
        this.X_no_gnd = X_no_gnd;
    }

    public RealMatrix getX_gnd() {
        return X_gnd;
    }

    public void setX_gnd(RealMatrix X_gnd) {
        this.X_gnd = X_gnd;
    }

    public RealMatrix getKik() {
        return kik;
    }

    public void setKik(RealMatrix kik) {
        this.kik = kik;
    }
    
    public ComplexMatrix getZ_gnd() {
        return Z_gnd;
    }

    public void setZ_gnd(ComplexMatrix Z_gnd) {
        this.Z_gnd = Z_gnd;
    }
    
    public ComplexMatrix getZ_no_gnd() {
        return Z_no_gnd;
    }

    public void setZ_no_gnd(ComplexMatrix Z_no_gnd) {
        this.Z_no_gnd = Z_no_gnd;
    }

    public RealMatrix getR_red_gnd() {
        return R_red_gnd;
    }

    public void setR_red_gnd(RealMatrix R_red_gnd) {
        this.R_red_gnd = R_red_gnd;
    }

    public RealMatrix getL_red_gnd() {
        return L_red_gnd;
    }

    public void setL_red_gnd(RealMatrix L_red_gnd) {
        this.L_red_gnd = L_red_gnd;
    }

    public RealMatrix getX_red_gnd() {
        return X_red_gnd;
    }

    public void setX_red_gnd(RealMatrix X_red_gnd) {
        this.X_red_gnd = X_red_gnd;
    }

    public ComplexMatrix getZ_red_gnd() {
        return Z_red_gnd;
    }

    public void setZ_red_gnd(ComplexMatrix Z_red_gnd) {
        this.Z_red_gnd = Z_red_gnd;
    }

    public RealMatrix getR_red_no_gnd() {
        return R_red_no_gnd;
    }

    public void setR_red_no_gnd(RealMatrix R_red_no_gnd) {
        this.R_red_no_gnd = R_red_no_gnd;
    }

    public RealMatrix getL_red_no_gnd() {
        return L_red_no_gnd;
    }

    public void setL_red_no_gnd(RealMatrix L_red_no_gnd) {
        this.L_red_no_gnd = L_red_no_gnd;
    }

    public ComplexMatrix getZ_red_no_gnd() {
        return Z_red_no_gnd;
    }

    public void setZ_red_no_gnd(ComplexMatrix Z_red_no_gnd) {
        this.Z_red_no_gnd = Z_red_no_gnd;
    }

    public RealMatrix getX_red_no_gnd() {
        return X_red_no_gnd;
    }

    public void setX_red_no_gnd(RealMatrix X_red_no_gnd) {
        this.X_red_no_gnd = X_red_no_gnd;
    }

    public ComplexMatrix getZ_red_gnd_symm() {
        return Z_red_gnd_symm;
    }

    public void setZ_red_gnd_symm(ComplexMatrix Z_red_gnd_symm) {
        this.Z_red_gnd_symm = Z_red_gnd_symm;
    }

    public RealMatrix getL_red_gnd_symm() {
        return L_red_gnd_symm;
    }

    public void setL_red_gnd_symm(RealMatrix L_red_gnd_symm) {
        this.L_red_gnd_symm = L_red_gnd_symm;
    }

    public RealMatrix getR_red_gnd_symm() {
        return R_red_gnd_symm;
    }

    public void setR_red_gnd_symm(RealMatrix R_red_gnd_symm) {
        this.R_red_gnd_symm = R_red_gnd_symm;
    }

    public RealMatrix getX_red_gnd_symm() {
        return X_red_gnd_symm;
    }

    public void setX_red_gnd_symm(RealMatrix X_red_gnd_symm) {
        this.X_red_gnd_symm = X_red_gnd_symm;
    }

    public ComplexMatrix getZ_red_no_gnd_symm() {
        return Z_red_no_gnd_symm;
    }

    public void setZ_red_no_gnd_symm(ComplexMatrix Z_red_no_gnd_symm) {
        this.Z_red_no_gnd_symm = Z_red_no_gnd_symm;
    }

    public RealMatrix getL_red_no_gnd_symm() {
        return L_red_no_gnd_symm;
    }

    public void setL_red_no_gnd_symm(RealMatrix L_red_no_gnd_symm) {
        this.L_red_no_gnd_symm = L_red_no_gnd_symm;
    }

    public RealMatrix getR_red_no_gnd_symm() {
        return R_red_no_gnd_symm;
    }

    public void setR_red_no_gnd_symm(RealMatrix R_red_no_gnd_symm) {
        this.R_red_no_gnd_symm = R_red_no_gnd_symm;
    }

    public RealMatrix getX_red_no_gnd_symm() {
        return X_red_no_gnd_symm;
    }

    public void setX_red_no_gnd_symm(RealMatrix X_red_no_gnd_symm) {
        this.X_red_no_gnd_symm = X_red_no_gnd_symm;
    }
    
    
    
}

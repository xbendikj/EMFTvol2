/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrical_parameters;

/**
 * All input properties needed for electrical parameters calculations
 * @author Mattto_Silver
 */
public class elpam_input_conductor {
    
    //strictly inputs
    public double f;               //frekvencia [Hz]
    public double D;               //priemer vodica [m]
    public double T;               //hrubka Al plasta [m]  
    public double rho_conductor;   //rezistivita Al plasta [Ohm.m]
    public double rho_ground;      //rezistivita zeme [Omh.m]
    public double Rdc;             //jednosmerny odpor vodica [Ohm/km]
    public int Al_layers;          //pocet vrstiev Al
    public int Al_start;           //pocet vodicov v prvej vrstve Al (smer zvnutra von)
    public double Al_d;            //priemer Al drotov [m]

    //possible partial outputs
    public double GMR;             //geometric mean radius [m]
    public double xi;              //parameter lana [-]
    public double Rac;             //striedavy odpor [Ohm/km]
    public double GMR_default;
    public double xi_default;
    
    //bundle
    public boolean bundle;
    
    public void elpam_input_conductor(){
    }
    
    public void elpam_input_conductor(double f, 
                                    double D, 
                                    double T, 
                                    double rho_cnd, 
                                    double rho_gnd, 
                                    double Rdc, 
                                    int Al_layers, 
                                    int Al_start, 
                                    double Al_d,
                                    boolean bundle){
        this.f = f;
        this.D = D;
        this.T = T;
        this.rho_conductor = rho_cnd;
        this.rho_ground = rho_gnd;
        this.Rdc = Rdc;
        this.Al_layers = Al_layers;
        this.Al_start = Al_start;
        this.Al_d = Al_d;
        this.bundle = bundle;

        
        //aby bolo jasne ze nie su ete dopocitane
        this.GMR = -1;
        this.xi = -1;
        this.Rac = -1;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public double getD() {
        return D;
    }

    public void setD(double D) {
        this.D = D;
    }

    public double getT() {
        return T;
    }

    public void setT(double T) {
        this.T = T;
    }

    public double getRho_conductor() {
        return rho_conductor;
    }

    public void setRho_conductor(double rho_conductor) {
        this.rho_conductor = rho_conductor;
    }

    public double getRho_ground() {
        return rho_ground;
    }

    public void setRho_ground(double rho_ground) {
        this.rho_ground = rho_ground;
    }

    public double getRdc() {
        return Rdc;
    }

    public void setRdc(double Rdc) {
        this.Rdc = Rdc;
    }

    public int getAl_layers() {
        return Al_layers;
    }

    public void setAl_layers(int Al_layers) {
        this.Al_layers = Al_layers;
    }

    public int getAl_start() {
        return Al_start;
    }

    public void setAl_start(int Al_start) {
        this.Al_start = Al_start;
    }

    public double getAl_d() {
        return Al_d;
    }

    public void setAl_d(double Al_d) {
        this.Al_d = Al_d;
    }

    public double getGMR() {
        return GMR;
    }

    public void setGMR(double GMR) {
        this.GMR = GMR;
    }

    public double getXi() {
        return xi;
    }

    public void setXi(double xi) {
        this.xi = xi;
    }

    public double getRac() {
        return Rac;
    }

    public void setRac(double Rac) {
        this.Rac = Rac;
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
    
    
    
}

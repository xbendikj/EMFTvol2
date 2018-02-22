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
    public static String name;            //optional
    public static double f;               //frekvencia [Hz]
    public static double D;               //priemer vodica [m]
    public static double T;               //hrubka Al plasta [m]  
    public static double rho_conductor;   //rezistivita Al plasta [Ohm.m]
    public static double rho_ground;      //rezistivita zeme [Omh.m]
    public static double Rdc;             //jednosmerny odpor vodica [Ohm/km]
    public static int Al_layers;          //pocet vrstiev Al
    public static int Al_start;           //pocet vodicov v prvej vrstve Al (smer zvnutra von)
    public static double Al_d;            //priemer Al drotov [m]

    //possible partial outputs
    public static double GMR;             //geometric mean radius [m]
    public static double xi;              //parameter lana [-]
    public static double Rac;             //striedavy odpor [Ohm/km]
    
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
                                    double Al_d){
        elpam_input_conductor.f = f;
        elpam_input_conductor.D = D;
        elpam_input_conductor.T = T;
        elpam_input_conductor.rho_conductor = rho_cnd;
        elpam_input_conductor.rho_ground = rho_gnd;
        elpam_input_conductor.Rdc = Rdc;
        elpam_input_conductor.Al_layers = Al_layers;
        elpam_input_conductor.Al_start = Al_start;
        elpam_input_conductor.Al_d = Al_d;
        
        //aby bolo jasne ze nie su ete dopocitane
        elpam_input_conductor.GMR = -1;
        elpam_input_conductor.xi = -1;
        elpam_input_conductor.Rac = -1;
    }
    
    public void elpam_input_conductor(String name ,
                                    double f, 
                                    double D, 
                                    double T, 
                                    double rho_cnd, 
                                    double rho_gnd, 
                                    double Rdc, 
                                    int Al_layers, 
                                    int Al_start, 
                                    double Al_d){
        elpam_input_conductor.name = name;
        elpam_input_conductor.f = f;
        elpam_input_conductor.D = D;
        elpam_input_conductor.T = T;
        elpam_input_conductor.rho_conductor = rho_cnd;
        elpam_input_conductor.rho_ground = rho_gnd;
        elpam_input_conductor.Rdc = Rdc;
        elpam_input_conductor.Al_layers = Al_layers;
        elpam_input_conductor.Al_start = Al_start;
        elpam_input_conductor.Al_d = Al_d;
        
        //aby bolo jasne ze nie su ete dopocitane
        elpam_input_conductor.GMR = -1;
        elpam_input_conductor.xi = -1;
        elpam_input_conductor.Rac = -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        elpam_input_conductor.name = name;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        elpam_input_conductor.f = f;
    }

    public double getD() {
        return D;
    }

    public void setD(double D) {
        elpam_input_conductor.D = D;
    }

    public double getT() {
        return T;
    }

    public void setT(double T) {
        elpam_input_conductor.T = T;
    }

    public double getRho_conductor() {
        return rho_conductor;
    }

    public void setRho_conductor(double rho_conductor) {
        elpam_input_conductor.rho_conductor = rho_conductor;
    }

    public double getRho_ground() {
        return rho_ground;
    }

    public void setRho_ground(double rho_ground) {
        elpam_input_conductor.rho_ground = rho_ground;
    }

    public double getRdc() {
        return Rdc;
    }

    public void setRdc(double Rdc) {
        elpam_input_conductor.Rdc = Rdc;
    }

    public int getAl_layers() {
        return Al_layers;
    }

    public void setAl_layers(int Al_layers) {
        elpam_input_conductor.Al_layers = Al_layers;
    }

    public int getAl_start() {
        return Al_start;
    }

    public void setAl_start(int Al_start) {
        elpam_input_conductor.Al_start = Al_start;
    }

    public double getAl_d() {
        return Al_d;
    }

    public void setAl_d(double Al_d) {
        elpam_input_conductor.Al_d = Al_d;
    }

    public double getGMR() {
        return GMR;
    }

    public void setGMR(double GMR) {
        elpam_input_conductor.GMR = GMR;
    }

    public double getXi() {
        return xi;
    }

    public void setXi(double xi) {
        elpam_input_conductor.xi = xi;
    }

    public double getRac() {
        return Rac;
    }

    public void setRac(double Rac) {
        elpam_input_conductor.Rac = Rac;
    }
    
    
}

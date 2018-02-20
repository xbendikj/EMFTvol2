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
    String name;            //optional
    double f;               //frekvencia [Hz]
    double D;               //priemer vodica [m]
    double T;               //hrubka Al plasta [m]  
    double rho_conductor;   //rezistivita Al plasta [Ohm.m]
    double rho_ground;      //rezistivita zeme [Omh.m]
    double Rdc;             //jednosmerny odpor vodica [Ohm/km]
    int Al_layers;
    int Al_start;
    double Al_d;

    //possible partial outputs
    double GMR;             //geometric mean radius [m]
    double xi;              //parameter lana [-]
    double Rac;             //striedavy odpor [Ohm/km]
    
    public void elpam_input_conductor(){
    }
    
}

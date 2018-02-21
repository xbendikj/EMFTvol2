/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrical_parameters;

import static java.lang.Math.sqrt;
import org.apache.commons.math.linear.RealMatrix;

/**
 * Carsonova vypoctova metoda elektrickych parametrov
 * @author Mattto
 */
public class Carson {
    
    int n;      //number of conductors in system [-]

    public void Carson(){
    }
    
    private double[][] k_ik(RealMatrix Dik_mirror){
        
        int n;
        double f;
        double rho_cnd;
        
        n = Dik_mirror.getColumnDimension();
        f = elpam_input_conductor.f;
        rho_cnd = elpam_input_conductor.rho_conductor;
        
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = (8.565e-4)*sqrt(f/rho_cnd)*Dik_mirror.getEntry(i, j);
            }
        }
        return result;
    }
    
    private double[] b(){
        double[] result = new double[8];
        
        result[0] = sqrt(2)/6;
        result[1] = 1/16;
        result[2] = result[0]/15;
        result[3] = result[1]/24;
        result[4] = result[2]/35;
        result[5] = result[3]/48;
        result[6] = result[4]/63;
        result[7] = result[5]/80;
        
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
        result[3] = result[1]+1/4+1/6;
        result[4] = 0;
        result[5] = result[3]+1/6+1/8;
        result[6] = 0;
        result[7] = result[5]+1/8+1/10;
        
        return result;
    }
    
//    private double[][] Rg(double f, RealMatrix k_ik, RealMatrix Fi_ik){
//        double
//    }
    
}

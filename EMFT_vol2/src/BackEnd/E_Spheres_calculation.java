/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import static InternalFrame.InternalFrameproject.Rozpätie;
import emft_vol2.constants;
import java.util.ArrayList;
import org.apache.commons.math.complex.Complex;
import org.apache.commons.math.linear.RealMatrix;
import org.jdelaunay.delaunay.error.DelaunayError;
import org.jdelaunay.delaunay.geometries.DPoint;
import tools.help;

/**vypocita E v mieste Rp od vsetkych lan 
 *
 * @author Jozef
 */
public class E_Spheres_calculation {
    
    double epsi0;
    double epsiR;
   
    DPoint Rp;
    DPoint R0;

   
    FazorVektor E ;
    rozpatie rozpätie;
   
    ArrayList<Double> polomery_lan  = new ArrayList<Double>(); // a_i // mozno bude trba menit koli rozmeru  ? uvidime    
    ArrayList<DPoint> Ri  = new ArrayList<DPoint>();
    ArrayList<Complex> qi  = new ArrayList<Complex>();
    
    ArrayList<ArrayList<DPoint>> Rij  = new ArrayList<ArrayList<DPoint>>();
    ArrayList<ArrayList<Complex>> qij  = new ArrayList<ArrayList<Complex>>();
    
     ArrayList<ArrayList<ArrayList<DPoint>>> Rijk  = new ArrayList<ArrayList<ArrayList<DPoint>>>();
     ArrayList<ArrayList<ArrayList<Complex>>> qijk  = new ArrayList<ArrayList<ArrayList<Complex>>>();
    
    ArrayList<ArrayList<ArrayList<ArrayList<DPoint>>>> Rijkl  = new ArrayList<ArrayList<ArrayList<ArrayList<DPoint>>>>();
    ArrayList<ArrayList<ArrayList<ArrayList<Complex>>>> qijkl  = new ArrayList<ArrayList<ArrayList<ArrayList<Complex>>>>();
    
    public E_Spheres_calculation() {
    }
    
    /**

     */
    public E_Spheres_calculation(double epsi0, double epsiR,rozpatie Rozpätie, DPoint RP) throws DelaunayError {
        this.epsi0 = epsi0;
        this.epsiR = epsiR;
        this.Rp = RP;
        this.rozpätie = Rozpätie;

    }

    public FazorVektor getE() {
        return E;
    }

    public DPoint getRp() {
        return Rp;
    }

    private double get_Pkk(double K,double Hk,double Rk){
        double val= K*Math.log((2*Hk)/Rk);
        return val;
    }

    public void  run () throws DelaunayError{
        Complex NULA = new Complex(0, 0);
        FazorVektor E_v_miesteRp = new FazorVektor(NULA, NULA, NULA);
        
        //cyklus od kazdej sfery Q0
        for(int cl1 = 0; cl1<this.Ri.size();cl1++){
            
            E_v_miesteRp.AddToFazorVektor( calc_DE(qi.get(cl1), this.Rp,Ri.get(cl1)) );
            
        }
        
        // cyklus od kazdej sfery Qij
        for (int i = 0; i < this.Rij.size(); i++) {

            for (int j = 0; j < this.Rij.get(0).size(); j++) {

                E_v_miesteRp.AddToFazorVektor(calc_DE(qij.get(i).get(j), this.Rp, Rij.get(i).get(j)));

            }

        }
        
        // cyklus od kazdej sfery Qijk
        for (int i = 0; i < this.Rijk.size(); i++) {

            for (int j = 0; j < this.Rijk.get(0).size(); j++) {

                for (int k = 0; k < this.Rijk.get(0).get(0).size(); k++) {

                    E_v_miesteRp.AddToFazorVektor(calc_DE(qijk.get(i).get(j).get(k), this.Rp, Rijk.get(i).get(j).get(k)));

                }

            }
        }
        
         // cyklus od kazdej sfery Qijkl
        for (int i = 0; i < this.Rijkl.size(); i++) {

            for (int j = 0; j < this.Rijkl.get(0).size(); j++) {

                for (int k = 0; k < this.Rijkl.get(0).get(0).size(); k++) {

                    for (int l = 0; l < this.Rijkl.get(0).get(0).get(0).size(); l++) {

                        E_v_miesteRp.AddToFazorVektor(calc_DE(qijkl.get(i).get(j).get(k).get(l), this.Rp, Rijkl.get(i).get(j).get(k).get(l)));

                    }

                }

            }
        }
       
        this.E=E_v_miesteRp;
    }

    //TOTO JE SRDCE VYPOCTU
    private FazorVektor calc_DE(Complex Q, DPoint Rp,DPoint R0 ) throws DelaunayError{
        
        FazorVektor deltaE = new FazorVektor(new Complex(0, 0),new Complex(0, 0),new Complex(0, 0));
        double K = 1/(4*Math.PI*constants.getEpsi0()*constants.getEpsi1()); // konštanta
        double Q_real = Q.getReal();
        double Q_image = Q.getImaginary();
        
        DPoint R_r = help.substract(Rp, R0);  // rozdiel vektorov Rp a RO
        double ABS_R_r = get_ABS(R_r);
        DPoint R_r_unit = new DPoint(R_r);
 
        R_r_unit.setX(R_r.getX()/ABS_R_r);
        R_r_unit.setY(R_r.getY()/ABS_R_r);
        R_r_unit.setZ(R_r.getZ()/ABS_R_r);

        double menovatel_r = Math.pow(get_ABS(R_r), 2);
       

        deltaE.setX_Real( K * (  ((Q_real *R_r_unit.getX())/menovatel_r)      ));
        deltaE.setY_Real( K * (  ((Q_real *R_r_unit.getY())/menovatel_r)      ));
        deltaE.setZ_Real( K * (  ((Q_real *R_r_unit.getZ())/menovatel_r)      ));

        deltaE.setX_Imaginary( K * (  ((Q_image*R_r_unit.getX())/menovatel_r)      ));
        deltaE.setY_Imaginary( K * (  ((Q_image*R_r_unit.getY())/menovatel_r)      ));
        deltaE.setZ_Imaginary( K * (  ((Q_image*R_r_unit.getZ())/menovatel_r)      ));
        
        return deltaE;
    }

    private double get_ABS(DPoint X){
        double ABS = Math.sqrt( Math.pow(X.getX(), 2) + Math.pow(X.getY(), 2) + Math.pow(X.getZ(), 2)  );
        return ABS;
    }

    private double get_Pkj(double K,double Dkj_image,double Dkj){
        double val= K*Math.log((Dkj_image)/Dkj);
        return val;
    }
    public void priprava(int pocet_stupnov) throws DelaunayError{
        
        ArrayList<Double> U_real_list = new ArrayList<Double>();
        ArrayList<Double> U_image_list = new ArrayList<Double>();
        ArrayList<Integer> polohy_lan  = new ArrayList<Integer>();
        
        //iteratory
        int iterator_lan = 0;
        int elementarny_iterator=0;
        //basic priprava vektorov 
        for (int cl1 = 0; cl1 < rozpätie.getRetazovkaList().size(); cl1++) {
            
            //cyklus bundle   
            for (int cl2 = 0; cl2 < rozpätie.getRetazovkaList().get(cl1).getBundle_over(); cl2++) {

                ArrayList<DPoint> R0_vectors_per_lano = new ArrayList<DPoint>(rozpätie.getRetazovka(cl1).getRo_vectors()); // nacitam jedno lano a upravim ho podla bundle konstant
                ArrayList<DPoint> R0_mirror_vectors_per_lano = new ArrayList<DPoint>(rozpätie.getRetazovka(cl1).getRo_mirror_vectors());
                polohy_lan.add(elementarny_iterator);
                // cyklus 
                for (int cl3 = 0; cl3 < rozpätie.getRetazovka(cl1).getRo_vectors().size(); cl3++) {

                    //  bundle korektura pre jeden druhy SMER  a korekcia
                    double Y = (double) R0_vectors_per_lano.get(cl3).getY() + (double) rozpätie.getRetazovkaList().get(cl1).getZY_cor_Bundle()[1][cl2];
                    double Z = (double) R0_vectors_per_lano.get(cl3).getZ() + (double) Math.cos(rozpätie.getRetazovkaList().get(cl1).getBeta_over()) * rozpätie.getRetazovkaList().get(cl1).getZY_cor_Bundle()[0][cl2];
                    double X = (double) R0_vectors_per_lano.get(cl3).getX() + (double) Math.sin(rozpätie.getRetazovkaList().get(cl1).getBeta_over()) * rozpätie.getRetazovkaList().get(cl1).getZY_cor_Bundle()[0][cl2];
                    DPoint R0 = new DPoint(X, Y, Z);

                    //mirrorovanie len jedneho rozmeru pozor
                    double Ym = (double) R0_mirror_vectors_per_lano.get(cl3).getY() - (double) rozpätie.getRetazovkaList().get(cl1).getZY_cor_Bundle()[1][cl2];
                    double Zm = (double) R0_mirror_vectors_per_lano.get(cl3).getZ() + (double) Math.cos(rozpätie.getRetazovkaList().get(cl1).getBeta_over()) * rozpätie.getRetazovkaList().get(cl1).getZY_cor_Bundle()[0][cl2];
                    double Xm = (double) R0_mirror_vectors_per_lano.get(cl3).getX() + (double) Math.sin(rozpätie.getRetazovkaList().get(cl1).getBeta_over()) * rozpätie.getRetazovkaList().get(cl1).getZY_cor_Bundle()[0][cl2];
                    DPoint R0m = new DPoint(Xm, Ym, Zm);

                    // nastavy U real a image do separatnych listov podla toho na akom vodiči je ( tu sa bundle ignoruje )
                    U_real_list.add(get_real(rozpätie.getRetazovkaList().get(cl1).getU_over(), rozpätie.getRetazovkaList().get(cl1).getPhi_over()) / Math.sqrt(3));
                    U_real_list.add(get_real(-rozpätie.getRetazovkaList().get(cl1).getU_over(), rozpätie.getRetazovkaList().get(cl1).getPhi_over()) / Math.sqrt(3)); // add image voltage
                    U_image_list.add(get_image(rozpätie.getRetazovkaList().get(cl1).getU_over(), rozpätie.getRetazovkaList().get(cl1).getPhi_over()) / Math.sqrt(3));
                    U_image_list.add(get_image(-rozpätie.getRetazovkaList().get(cl1).getU_over(), rozpätie.getRetazovkaList().get(cl1).getPhi_over()) / Math.sqrt(3)); // add image voltage
                    // nastavy polomery do jedej arraylistu
                    this.polomery_lan.add(rozpätie.getRetazovkaList().get(cl1).getR_over()); // realn
                    this.polomery_lan.add(rozpätie.getRetazovkaList().get(cl1).getR_over()); //image
                    this.Ri.add(new DPoint(R0)); // add real
                    this.Ri.add(new DPoint(R0m)); // add image to the total list

                //    elementarny_iterator = elementarny_iterator + 1;
                }

            }

        }
        
        //NULTY STUPEN Qi
        double pocitac =0;
        for (int i = 0; i < Ri.size(); i++) {
            double  qi_real =  4*Math.PI*this.epsi0*this.epsiR*polomery_lan.get(i)*U_real_list.get(i);
            double  qi_image = 4*Math.PI*this.epsi0*this.epsiR*polomery_lan.get(i)*U_image_list.get(i);
            
            
            
            this.qi.add( new Complex(qi_real, qi_image));
            pocitac++;
        }
        System.out.println("pocet prvkov krok O =" + pocitac );
        pocitac = 0;
        
        //druhy stupen Qij
        for (int i = 0; i < Ri.size(); i++) {

            ArrayList<DPoint> R  = new ArrayList<DPoint>();
            ArrayList<Complex> q  = new ArrayList<Complex>();
            
            for (int j = 0; j < Ri.size(); j++) {

                if (i == j) {

                }
                    else {
                    
                    double distance = get_distance(Ri.get(i), Ri.get(j));
                    double distance2 = Math.pow(distance, 2);
                    double Ai = polomery_lan.get(i);
                    double Ai2 = Math.pow(Ai, 2);
                    if (distance < rozpätie.getRetazovka(0).getI_over())
                    {
                        double q_real = -(Ai / distance) * qi.get(j).getReal();
                        double q_image = -(Ai / distance) * qi.get(j).getImaginary();
                        double X = Ri.get(i).getX() - Ai2 * ((Ri.get(i).getX() - Ri.get(j).getX()) / distance2);
                        double Y = Ri.get(i).getY() - Ai2 * ((Ri.get(i).getY() - Ri.get(j).getY()) / distance2);
                        double Z = Ri.get(i).getZ() - Ai2 * ((Ri.get(i).getZ() - Ri.get(j).getZ()) / distance2);
                        DPoint Ri = new DPoint(X, Y, Z);

                        q.add( new Complex(q_real,q_image) );
                        R.add( new DPoint(Ri));
                        pocitac++;
                        
                    }
                }

            }
            this.qij.add(new ArrayList<Complex>(q) );
            this.Rij.add(new ArrayList<DPoint>(R));

        }
        System.out.println("pocet prvkov krok 1 =" + pocitac );
        pocitac = 0;
        
        //druhy stupen Qij
        for (int i = 0; i < Ri.size(); i++) {

            ArrayList<ArrayList<DPoint>> Rj = new ArrayList<ArrayList<DPoint>>();
            ArrayList<ArrayList<Complex>> qj = new ArrayList<ArrayList<Complex>>();

            for (int j = 0; j < Rij.size(); j++) {

                ArrayList<DPoint> R = new ArrayList<DPoint>();
                ArrayList<Complex> q = new ArrayList<Complex>();
                
                if (i == j) {

                } else {

                    for (int k = 0; k < Rij.get(0).size(); k++) {
                        if (j == k) {

                        } else {
                            double distance = get_distance(Ri.get(i), Rij.get(j).get(k));
                            double distance2 = Math.pow(distance, 2);
                            double Ai = polomery_lan.get(i);
                            double Ai2 = Math.pow(Ai, 2);
                            if (distance < rozpätie.getRetazovka(0).getI_over()) {
                                double q_real = -(Ai / distance) * qij.get(j).get(k).getReal();
                                double q_image = -(Ai / distance) * qij.get(j).get(k).getImaginary();

                                
                                
                                double X = Ri.get(i).getX() - Ai2 * ((Ri.get(i).getX() - Rij.get(j).get(k).getX()) / distance2);
                                double Y = Ri.get(i).getY() - Ai2 * ((Ri.get(i).getY() - Rij.get(j).get(k).getY()) / distance2);
                                double Z = Ri.get(i).getZ() - Ai2 * ((Ri.get(i).getZ() - Rij.get(j).get(k).getZ()) / distance2);
                                DPoint Ri = new DPoint(X, Y, Z);

                                q.add(new Complex(q_real, q_image));
                                R.add(new DPoint(Ri));
                                pocitac++;
                            }
                        }
                    }
                qj.add(new ArrayList<Complex>(q));
                Rj.add(new ArrayList<DPoint>(R));
                }
                
                
                
            }
            this.qijk.add(new ArrayList<ArrayList<Complex>>(qj));
            this.Rijk.add(new ArrayList<ArrayList<DPoint>>(Rj));

        }
        System.out.println("pocet prvkov krok 2 =" + pocitac);
        pocitac = 0;
        
        
         //druhy stupen Qijkl
        for (int i = 0; i < Ri.size(); i++) {

            ArrayList<ArrayList<ArrayList<DPoint>>> Rjk = new ArrayList<ArrayList<ArrayList<DPoint>>>();
            ArrayList<ArrayList<ArrayList<Complex>>> qjk = new ArrayList<ArrayList<ArrayList<Complex>>>();

            for (int j = 0; j < Rijk.size(); j++) {

                ArrayList<ArrayList<DPoint>> Rj = new ArrayList<ArrayList<DPoint>>();
                ArrayList<ArrayList<Complex>> qj = new ArrayList<ArrayList<Complex>>();

                if (i == j) {

                } else {

                    for (int k = 0; k < Rijk.get(0).size(); k++) {

                        ArrayList<DPoint> R = new ArrayList<DPoint>();
                        ArrayList<Complex> q = new ArrayList<Complex>();

                        if (j == k) {

                        } else {

                            for (int l = 0; l < Rijk.get(0).get(0).size(); l++) {
                                if (k == l) {

                                } else {
                                    double distance = get_distance(Ri.get(i), Rijk.get(j).get(k).get(l));
                                    double distance2 = Math.pow(distance, 2);
                                    double Ai = polomery_lan.get(i);
                                    double Ai2 = Math.pow(Ai, 2);
                                    if (distance < rozpätie.getRetazovka(0).getI_over()) {
                                        double q_real = -(Ai / distance) * qijk.get(j).get(k).get(l).getReal();
                                        double q_image = -(Ai / distance) * qijk.get(j).get(k).get(l).getImaginary();

                                       

                                        double X = Ri.get(i).getX() - Ai2 * ((Ri.get(i).getX() - Rijk.get(j).get(k).get(l).getX()) / distance2);
                                        double Y = Ri.get(i).getY() - Ai2 * ((Ri.get(i).getY() - Rijk.get(j).get(k).get(l).getY()) / distance2);
                                        double Z = Ri.get(i).getZ() - Ai2 * ((Ri.get(i).getZ() - Rijk.get(j).get(k).get(l).getZ()) / distance2);
                                        DPoint Ri = new DPoint(X, Y, Z);

                                        q.add(new Complex(q_real, q_image));
                                        R.add(new DPoint(Ri));
                                        pocitac++;
                                    }
                                }
                            }
                         qj.add(new ArrayList<Complex>(q) );
                        Rj.add(new ArrayList<DPoint>(R));   
                        }
                        
                    }
                }

                qjk.add(new ArrayList<ArrayList<Complex>>(qj));
                Rjk.add(new ArrayList<ArrayList<DPoint>>(Rj));

            }
            this.qijkl.add(new ArrayList<ArrayList<ArrayList<Complex>>>(qjk));
            this.Rijkl.add(new ArrayList<ArrayList<ArrayList<DPoint>>>(Rjk));

        }
        System.out.println("pocet prvkov krok 3 =" + pocitac);
        pocitac = 0;
        


    }

    private double get_distance(DPoint A,DPoint B){
        double X = B.getX() - A.getX();
        double Y = B.getY() - A.getY();
        double Z = B.getZ() - A.getZ();
        double val = Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2)  +Math.pow(Z, 2)   );
        return val;
    } 

        private double get_image(double value,double phase){
        double image = value*Math.sin(phase*(Math.PI/180));
        return image;
    }

        private double get_real(double value,double phase){
            double real = value*Math.cos(phase*(Math.PI/180));
            return real;
        }

        public void setE(FazorVektor E) {
            this.E = E;
        }

        public void setRp(DPoint Rp) {
            this.Rp = Rp;
        } 
        
       
    
}

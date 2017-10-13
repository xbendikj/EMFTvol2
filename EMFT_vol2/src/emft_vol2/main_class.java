/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emft_vol2;

import BackEnd.Tfield;
import BackEnd.triangulacia;
import BackEnd.retazovka;
import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;
import dislin.SurfaceGraph;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Port;
import org.jdelaunay.delaunay.error.DelaunayError;
import org.jdelaunay.delaunay.geometries.DPoint;
import org.jdelaunay.delaunay.geometries.DTriangle;
import tools.help;
import tools.warning_jDialog;

/**
 *
 * @author Jozef
 */
public class main_class {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        // language frame
         languageChooser_Frame.main(args);
        while (true) { //wait for information that user has choosen a language
            try {
                Thread.sleep(100);                 //1000 milliseconds is one second. wait to not get overcycled
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            Boolean status = languageChooser_Frame.getStatus();
            if (status.equals(true)) {             //has user choosen the language in language Chooser frame ?
                break;
            }

        }
        
        // main frame
        main_Jframe.main(args); //spust main frame
        constants.loadConstants(); // nacitaj konštanty
           
      // test delaunay
       System.out.println("TEST OF DELAUNAY 1");
        double A = 300;
        double Z = 40; 
        int N = 5;
        
        ArrayList<double[]> body = new ArrayList<double[]>();
        System.out.println("Enter the coordinates of each points: <x> <y> <z>");
       for (int i = 0; i < N; i++){
        if(i==0){
            double[] cislo = new double[3];
            cislo[0] = 0; 
            cislo[1] = 0;
            cislo[2] = 100;
            body.add(cislo);}
        if(i==1){
             double[] cislo = new double[3];
            cislo[0] = 0; 
            cislo[1] = 0;
            cislo[2] = -100;
            body.add(cislo);
            
        } if(i==2){
             double[] cislo = new double[3];
            cislo[0] = 300; 
            cislo[1] = 400;
            cislo[2] = 100;
            body.add(cislo);}
        if(i==3){
             double[] cislo = new double[3];
            cislo[0] = 300; 
            cislo[1] = 400;
            cislo[2] = -100;
            body.add(cislo);}
        if(i==4){
             double[] cislo = new double[3];
            cislo[0] = 0; 
            cislo[1] = 0;
            cislo[2] = 10;
            body.add(cislo);}
        if(i==5){
             double[] cislo = new double[3];
            cislo[0] = 100; 
            cislo[1] = 1;
            cislo[2] = 0;
            body.add(cislo);}
        if(i==6){
             double[] cislo = new double[3];
            cislo[0] = 10;
            cislo[1] =10;
            cislo[2] = 40;
            body.add(cislo);}  
        }
          
        System.out.println("ZADANE OK");
        
        double[] LCcoordinates= new double[3];
        LCcoordinates[0]=0;
        LCcoordinates[1]=0;
        LCcoordinates[2]=0;
    
        triangulacia test = new triangulacia(A, Z, body,false,LCcoordinates,false,false);
        test.setLCcoordinates(LCcoordinates);
        try {
            test.run(); 
            Tfield pokusnePole = new Tfield(test.getTriangles());
            DPoint pokusnyB = new DPoint(20,15,50);
            pokusnyB=pokusnePole.getYaboveTer(pokusnyB);
            System.out.println("TEST the field transformations");
            System.out.println(pokusnyB);
            
            System.out.println("Test normal");
             DPoint pokusnyc = new DPoint(290,15,0);
             pokusnyc=pokusnePole.getY(pokusnyc);
             
             System.out.println("Test perpendicular");
             DPoint P = new DPoint(0,400,20);
             DPoint Q=pokusnePole.getPerpendicularProjection(P);
            System.out.println(Q);
            
            System.out.println("Test mirror");
            DPoint NN=pokusnePole.getMirrorPoint(P);
            System.out.println(NN);
            
            System.out.println("Test mirroraproxxplane");
            P = new DPoint(150,400,20);
            DPoint NN2=pokusnePole.getMirrorPointAproxxPlane(P,0,1);
            System.out.println(NN2);
            
            
        } catch (DelaunayError ex) {
            Logger.getLogger(main_class.class.getName()).log(Level.SEVERE, null, ex);
        }

        //test show graph
        SurfaceGraph povrch = new SurfaceGraph(test.getTriangles(), test.getResultsPoint());
        povrch.SetNameAndLables("test", "podpadps", "X", "Z","Y");
        povrch.Run();
            
        // TEST RETAZOVKA plus bundle
        System.out.println("TEST retazovka C a H a bndle");
        retazovka ret;
        try {
            ret = new retazovka(40, 40, 0, 0, 0, 0, 0, 300, 4, 90,10, 1500, true, 0.1, 100, 100, 120,test.getTriangles());
            System.out.println(ret.getC_over());
            System.out.println(ret.getH_over());
            System.out.println(ret.getA1_over());
            System.out.println(ret.getHter_over());
        } catch (DelaunayError ex) {
            Logger.getLogger(main_class.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // TEST transformacia LC GC
        double[] LC1_tower = new double[3];
        double[] LC2_tower = new double[3];
        
        LC1_tower[0]=10;
        LC1_tower[1]=300;
        LC1_tower[2]=-10;
        
        LC2_tower[0]=10;
        LC2_tower[1]=300;
        LC2_tower[2]=-300;
        
        DPoint TEST1;
        try {
            TEST1 = new DPoint(20, 0, -20);
            System.out.println("pred " + TEST1);
            TEST1 = help.CorToLC(LC1_tower, LC2_tower, TEST1);
            System.out.println("po " +TEST1);
        } catch (DelaunayError ex) {
            Logger.getLogger(main_class.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}

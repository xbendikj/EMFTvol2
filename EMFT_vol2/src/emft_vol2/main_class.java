/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emft_vol2;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdelaunay.delaunay.error.DelaunayError;
import org.jdelaunay.delaunay.geometries.DTriangle;

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
           
      
       System.out.println("TEST OF DELAUNAY 1");
     //   Scanner sc = new Scanner(System.in);
       // System.out.println("ZADAJ A");
        double A = 300;//sc.nextDouble();
      //  System.out.println("ZADAJ Z");
        double Z = 40; //sc.nextDouble();
      //  System.out.println("ZADAJ pocet bodov siete");
        int N = 7;//sc.nextInt();
        
        ArrayList<double[]> body = new ArrayList<double[]>();
        System.out.println("Enter the coordinates of each points: <x> <y> <z>");
       for (int i = 0; i < N; i++){
        if(i==0){
            double[] cislo = new double[3];
            cislo[0] = 10; //sc.nextDouble(); 
            cislo[1] = 0;//sc.nextDouble();
            cislo[2] = 2;//sc.nextDouble();
            body.add(cislo);}
        if(i==1){
             double[] cislo = new double[3];
            cislo[0] = 130; //sc.nextDouble(); 
            cislo[1] = 0;//sc.nextDouble();
            cislo[2] = 18;//sc.nextDouble();
            body.add(cislo);
            
        } if(i==2){
             double[] cislo = new double[3];
            cislo[0] = 40; //sc.nextDouble(); 
            cislo[1] = 0;//sc.nextDouble();
            cislo[2] = 23;//sc.nextDouble();
            body.add(cislo);}
        if(i==3){
             double[] cislo = new double[3];
            cislo[0] = 180; //sc.nextDouble(); 
            cislo[1] = -10;//sc.nextDouble();
            cislo[2] = 20;//sc.nextDouble();
            body.add(cislo);}
        if(i==4){
             double[] cislo = new double[3];
            cislo[0] = 55; //sc.nextDouble(); 
            cislo[1] = 0;//sc.nextDouble();
            cislo[2] = -19;//sc.nextDouble();
            body.add(cislo);}
        if(i==5){
             double[] cislo = new double[3];
            cislo[0] = 25; //sc.nextDouble(); 
            cislo[1] = 15;//sc.nextDouble();
            cislo[2] = -9;//sc.nextDouble();
            body.add(cislo);}
        if(i==6){
             double[] cislo = new double[3];
            cislo[0] = 5; //sc.nextDouble(); 
            cislo[1] =0;//sc.nextDouble();
            cislo[2] = 19;//sc.nextDouble();
            body.add(cislo);}
            
        
        }
          
        System.out.println("ZADANE OK");
        
        double[] LCcoordinates= new double[3];
        LCcoordinates[0]=0;
        LCcoordinates[1]=0;
        LCcoordinates[2]=0;
        triangulacia.setLCcoordinates(LCcoordinates);
        triangulacia test = new triangulacia(A, Z, N, body,true);
        try {
            test.run();
            
        } catch (DelaunayError ex) {
            Logger.getLogger(main_class.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ArrayList<DTriangle> trojuholniky = new ArrayList<DTriangle>();
        trojuholniky = test.getResults();
       
        System.out.println(trojuholniky);
        dislin.Surface.main(args);
        
      
    
        
    }
    
}

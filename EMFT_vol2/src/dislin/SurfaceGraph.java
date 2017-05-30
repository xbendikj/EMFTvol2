/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dislin;

/**
 *
 * @author Jozef
 */

import de.dislin.Dislin;
import emft_vol2.triangulacia;
import java.util.ArrayList;
import java.util.List;
import org.jdelaunay.delaunay.geometries.DPoint;
import org.jdelaunay.delaunay.geometries.DTriangle;
import tools.help;
/**
 *
 * @author Jozef
 */
public class SurfaceGraph {
    
    private static ArrayList<DTriangle> trojuholniky = new ArrayList<DTriangle>(); // POZOR system XZY takto to bere aj dislin preto tu nnič nebudeme menit
    private static List<DPoint> body = new ArrayList<DPoint>();
    
   public SurfaceGraph(ArrayList<DTriangle> trojuholniky,List<DPoint> body){
       this.trojuholniky=trojuholniky;
       this.body=body;
       
   }
    
   public void Run(){
       main();
   }
   
  private static void main () {
      
      //body = triangulacia.getResultsPoint(); // nacitaj body po tiangulacii
     // trojuholniky= triangulacia.getTriangles(); // nacitaj trojuholniky po triangulacii
      
      int n =body.size();  
      int nTRI =trojuholniky.size(); 
      
      
      double[] Xray = new double[n]; // inicializuj
      double[] Yray = new double[n];
      double[] Zray = new double[n];
      int[] Ixray = new int[nTRI];
      int[] Iyray = new int[nTRI];
      int[] Izray = new int[nTRI];
      
      for( int cl1 = 0 ; cl1< n;cl1++){
           Xray[cl1] = body.get(cl1).getX();
           Yray[cl1] = body.get(cl1).getY();
           Zray[cl1] = body.get(cl1).getZ();
      }
      for( int cl1 = 0 ; cl1< nTRI;cl1++){
          Ixray[cl1] = trojuholniky.get(cl1).getPoint(0).getGID();  
          Iyray[cl1] = trojuholniky.get(cl1).getPoint(1).getGID();
          Izray[cl1] = trojuholniky.get(cl1).getPoint(2).getGID();
      }
              
      float XA = (float) minVal(Xray);
      float YA = (float) minVal(Yray);
      float ZA = (float) minVal(Zray);
       
      float XE = (float) maxVal(Xray);
      float YE = (float) maxVal(Yray);
      float ZE = (float) maxVal(Zray);
      
      float XOR = XA;
      float YOR = YA;
      float ZOR = ZA;
      
      float XSTEP = 20;//(Math.abs(XA)-Math.abs(XE))/((float)draw_constants.STEPdivider);
      float YSTEP = 20;//(Math.abs(YA)-Math.abs(YE))/((float)draw_constants.STEPdivider);
      float ZSTEP = 20;//(Math.abs(ZA)-Math.abs(ZE))/((float)draw_constants.STEPdivider);
      System.out.println(ZA);
      System.out.println(ZE);
      
     Dislin.metafl ("xwin"); // SCREEN OUTPUT
    // Dislin.setpag ("da4l"); // PAGE ORINTATION FORMAT
    
     Dislin.page(3000, 4000);
     Dislin.disini ();       // BEGIN DISLIN
     Dislin.pagera ();       // BORDER AROUND PAGE
     Dislin.hwfont ();       // HARDWARE FOTN
     Dislin.center();
    // Dislin.axspos (500, 500);     //LEFT LOWeR CORNER OF AXIS SYSETM
    Dislin.axslen (3000, 3000);    //
         
     Dislin.name   ("X-axis", "x");  // meno axis
     Dislin.name   ("Y-axis",  "y");
     Dislin.name   ("Z-axis",  "z");

     Dislin.titlin ("Surface Plot (SURMAT)", 1);   // prvy riadok
     Dislin.titlin ("F(X,Y) = 2*SIN(X)*SIN(Y)", 3); // druhy riadok titulok

    // Dislin.view3d (-6.f, -4.f, 5.f, "abs"); // Point from where is observed graph
     Dislin.graf3d (XA, XE, XOR, XSTEP,   // plot GRAF3D
                    YA, YE, YOR, YSTEP,
                    ZA, ZE, ZOR, ZSTEP);
     Dislin.height (50);
     Dislin.title  ();
 
     Dislin.color  ("green");
     
     Dislin.surtri(Xray, Yray, Zray,n,Ixray,Iyray,Izray,nTRI);
     Dislin.disfin ();
  }
  
  private static double  minVal(double[] array){
    double minValue = array[0];
    for (int i = 0; i < array.length; i++) {
        if (array[i] < minValue) {
            minValue = array[i];
        }
    }
    return minValue;  
}
  
  private static double  maxVal(double[] array){
    double maxValue = array[0];
    for (int i = 0; i < array.length; i++) {
        if (array[i] > maxValue) {
            maxValue = array[i];
        }
    }
    return maxValue;  
}
}

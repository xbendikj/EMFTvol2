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

public class Surface {
    
    private static ArrayList<DTriangle> trojuholniky = new ArrayList<DTriangle>(); // POZOR system XZY takto to bere aj dislin preto tu nnič nebudeme menit
    private static List<DPoint> body = new ArrayList<DPoint>();
    
  public static void main (String args []) {
      
      body = triangulacia.getResultsPoint();
      trojuholniky= triangulacia.getTriangles();
      int n =body.size();
      int nTRI =trojuholniky.size();
      
      
      double[] Xray = new double[n];
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
              

       
     Dislin.metafl ("cons"); // SCREEN OUTPUT
     Dislin.setpag ("da4p"); // PAGE ORINTATION FORMAT
     Dislin.disini ();       // BEGIN DISLIN
     Dislin.pagera ();       // BORDER AROUND PAGE
     Dislin.hwfont ();       // HARDWARE FOTN

     Dislin.axspos (200, 2600);     //LEFT LOWeR CORNER OF AXIS SYSETM
     Dislin.axslen (1800, 1800);    //
         
     Dislin.name   ("X-axis", "x");  // meno axis
     Dislin.name   ("Y-axis",  "y");
     Dislin.name   ("Z-axis",  "z");

     Dislin.titlin ("Surface Plot (SURMAT)", 1);   // prvy riadok
     Dislin.titlin ("F(X,Y) = 2*SIN(X)*SIN(Y)", 3); // druhy riadok titulok

     Dislin.view3d (-5.f, -3.f, 4.f, "abs"); // Point from where is observed graph
     Dislin.graf3d (-0.f, 310.f, 0.f, 90.f,   // plot GRAF3D
                    -50.f, 50.f, 0.f, 90.f,
                    -20.f, 20.f, -3.f, 1.f);
     Dislin.height (50);
     Dislin.title  ();
 
     Dislin.color  ("green");
     Dislin.surtri(Xray, Yray, Zray,n,Ixray,Iyray,Izray,nTRI);
     Dislin.disfin ();
  }
}

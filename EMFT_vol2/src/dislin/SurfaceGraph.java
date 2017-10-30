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

import BackEnd.Tfield;
import de.dislin.Dislin;
import emft_vol2.constants;
import java.util.ArrayList;
import java.util.List;
import org.jdelaunay.delaunay.error.DelaunayError;
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
    private static String X_ax_name="";
    private static String Y_ax_name="";
    private static String Z_ax_name="";
    private static String Title="";
    private static String subTitle="";
    private static float VelkostSymbolov=10;
    private static boolean isLCset =false;
    private double[] TOWER1 = new double[3]; 
    private double[] TOWER2 = new double[3]; 
    private boolean towery = false;
    
    /**
     * 
     * @param trojuholniky trojuholniky
     * @param body body na povrchu
     */
   public SurfaceGraph(ArrayList<DTriangle> trojuholniky,List<DPoint> body){
       this.trojuholniky=trojuholniky;
       this.body=body;
       
   }
   
   public SurfaceGraph(ArrayList<DTriangle> trojuholniky,List<DPoint> body, boolean towery){
       this.trojuholniky=trojuholniky;
       this.body=body;
       
       this.towery = towery;
       
   }
    
   public void Run(){
       main();
   }
   /**
    * nastavy LC2 a LC2 adopocita vysku vstup v mojich suradniciach XYZ
    * @param LC1
    * @param LC2 
    */
   public void setLC1LC2andCalculateheight(double[] LC1, double[] LC2) throws DelaunayError{
   // JE NUTREB AJ KORENE STOžIAROV SRTANFORMOVAT NO lc SYSTEMU PRETO TENTO KROK    
       TOWER1 =new double[]{LC1[0],LC1[1],LC1[2]};
       TOWER2 =new double[]{LC2[0],LC2[1],LC2[2]};
       double[] XZcorTOWER1 = help.CorToLC(LC1, LC2, TOWER1[0], TOWER1[2]);
      TOWER1[0] = XZcorTOWER1[0];  TOWER1[2] = XZcorTOWER1[1]; 
      
       double[] XZcorTOWER2 = help.CorToLC(LC1, LC2, TOWER2[0], TOWER2[2]);
       TOWER2[0] = XZcorTOWER2[0];  TOWER2[2] = XZcorTOWER2[1]; 
      
       Tfield pole = new Tfield(trojuholniky);
       TOWER1[1]=pole.getY(TOWER1[0], TOWER1[2]); // DOPOčITA VYšKU
       TOWER2[1]=pole.getY(TOWER2[0], TOWER2[2]);
       isLCset=true;
   }
   
   
   /**
    * nastavi lable a mena nazov premennych hovopri za všetko
    * @param title
    * @param subTitle
    * @param Xname
    * @param Yname
    * @param Zname 
    */
   public void SetNameAndLables(String title,String subTitle,String Xname,String Yname,String Zname){
       
       this.Title = title;
       this.subTitle = subTitle;
       this.X_ax_name=Xname;
       this.Y_ax_name=Yname;
       this.Z_ax_name=Zname;
       
       
   }
  private  void main () {
      
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
              
      float XA = (float) minVal(Xray); // spodny limit X
      float YA = (float) minVal(Yray);
      float ZA = -1;//(float) minVal(Zray);
       
      float XE = (float) maxVal(Xray);  // horny limit X
      float YE = (float) maxVal(Yray);
      float ZE = (float) maxVal(Zray)* constants.getDislin_graph_nasobok_z_vrchu();
      if(ZE == 0) ZE = -ZA;
      
      float XOR = XA;  // first label
      float YOR = YA;
      float ZOR = ZA;
      
      float XSTEP = (XE-XA)/10;//(Math.abs(XA)-Math.abs(XE))/((float)draw_constants.STEPdivider); // step between labels
      float YSTEP = (YE-YA)/5;//(Math.abs(YA)-Math.abs(YE))/((float)draw_constants.STEPdivider);
      float ZSTEP = (ZE-ZA)/10;//(Math.abs(ZA)-Math.abs(ZE))/((float)draw_constants.STEPdivider);
      
      
     Dislin.metafl ("xwin"); // SCREEN OUTPUT
     //Dislin.setpag ("da4r"); // PAGE ORINTATION FORMAT
    
     Dislin.page(6000, 4000); // size of window
     Dislin.scrmod("REVERS");
     Dislin.disini ();       // BEGIN DISLIN
     Dislin.pagera ();       // BORDER AROUND PAGE
     Dislin.hwfont ();       // HARDWARE FOTN
     Dislin.center();
    // Dislin.axspos (500, 500);     //LEFT LOWeR CORNER OF AXIS SYSETM
    Dislin.axslen (6000, 3000);    //
         
     Dislin.name   (this.X_ax_name, "x");  // meno axis
     Dislin.name   (this.Y_ax_name,  "y");
     Dislin.name   (this.Z_ax_name,  "z");

     Dislin.titlin (this.Title, 1);   // prvy riadok
     Dislin.titlin (this.subTitle, 3); // druhy riadok titulok

   
    //Dislin.vup3d(180);
  //  Dislin.axis3d(XE-XA,YE-YA, 100);
  float Xlen =300; //XE-XA;
  float Ylen =100; //XE-XA;
  float Zlen =100; //XE-XA;
  //********************
 // ZAKLADNE PRAVIDLO NA SPOLOCNU GRAFIKU  GRAF SA OBALI PREMENIMI TEDA ON MA JEDNU GRAFIKU A VSETKO OSTATNE MOZE MAT DRUHU 
 //*********************
 Dislin.height (constants.getDislin_velkost_textu_pred_grafom()); // spolocna grafika grafov
 Dislin.linwid(constants.getDislin_hrubka_ciar_pred_grafom());    // spolocna grafika grafov
  
     Dislin.axis3d(Xlen,Ylen, 100);
     Dislin.view3d (0.5f*Xlen, -3.1f*Ylen,2.5f*Zlen , "abs"); // Point from where is observed graph
     Dislin.graf3d (XA, XE, XOR, XSTEP,   // plot GRAF3D
                    YA, YE, YOR, YSTEP,
                    ZA, ZE, ZOR, ZSTEP);
     
 Dislin.height (constants.getDislin_velkost_textu_za_grafom());   // spolocna grafika grafov
 Dislin.linwid(constants.getDislin_hrubka_ciar_za_grafom());      // spolocna grafika grafov
     Dislin.title  ();
     Dislin.color  ("green");  
     Dislin.surtri(Xray, Yray, Zray,n,Ixray,Iyray,Izray,nTRI);
     Dislin.hsym3d(VelkostSymbolov);
     
   
    if(towery ==true){
     Dislin.color("MAGENTA"); // 1 
     Dislin.symb3d(5, (float)TOWER1[0], (float)TOWER1[2], (float)TOWER1[1]);
     Dislin.color("CYAN");
     Dislin.symb3d(5, (float)TOWER2[0], (float)TOWER2[2], (float)TOWER2[1]);
    }
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

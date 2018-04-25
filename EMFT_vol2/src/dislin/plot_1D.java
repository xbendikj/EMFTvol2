/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dislin;

import de.dislin.Dislin;
import emft_vol2.Dislin_Settings;
import emft_vol2.calculation_Settings;
import emft_vol2.constants;
import emft_vol2.main_Jframe;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import tools.help;

/**
 *
 * @author Jozef
 */
public class plot_1D {
    
    double xray[] ;
    double y1ray[] ;
    String nameY[] ;
    
    ArrayList<double[]> y2ray = new ArrayList<>();
    boolean islegend=false;
    boolean limits=false;
    double limit = 0;
    double smallunits=1;
    boolean screen = true;
    boolean file = false;
    boolean noZEROYA_limit = false;
    String xname;
    String y1name;
    String Path;
   
    String name_row1;
    String name_row2;
    
    
    // CONSTRUCTORS
    /**
      * plot 1D viacej vysok bez legendy
      * @param xray  array x hodnot
      * @param y2ray arraylist arrayu y hodnot
      * @param xname meno X osi
      * @param y1name meno Y osi
      * @param row_1 meno gtafu riadok prvy
      * @param row_2 meno grafu riadok druhy
      */
    public plot_1D(double[] xray, ArrayList y2ray,String xname,String y1name,String row_1,String row_2) {
        this.xray = xray;
        this.y2ray = y2ray;
        this.y1name=y1name;
        this.xname=xname;
        this.name_row1=row_1;
        this.name_row2=row_2;
    }
     /**
      * plot 1D viacej vysok s legendou
      * @param xray  array x hodnot
      * @param y2ray arraylist y hodnot
      * @param xname meno X osi
      * @param y1name meno Y osi
      * @param row_1 meno gtafu riadok prvy
      * @param row_2 meno grafu riadok druhy
      * @param nameY ak legenda tak pomenovania -poradie ako arraylist
      */
    public plot_1D(double[] xray, ArrayList y2ray,String xname,String y1name,String row_1,String row_2,String nameY[]) {
        this.xray = xray;
        this.y2ray = y2ray;
        this.y1name=y1name;
        this.xname=xname;
        this.name_row1=row_1;
        this.name_row2=row_2;
        this.nameY=nameY;
        this.islegend=true;
    }

    public boolean isScreen() {
        return screen;
    }

    public void setScreen(boolean screen) {
        this.screen = screen;
    }

    public boolean isFile() {
        return file;
    }

    public void setFile(boolean file,String Path) {
        this.file = file;
        this.Path = Path;
    }
    
    public void draw_1D_yn() throws IOException{
        run1D_yn();
    }

    /**
     * zapne / nastavi zbrazovanie lmitov 
     * @param limits ano nie
     * @param value ak ano aka hodnota
     */
    public void setLimits(boolean limits,double value){
        this.limits=limits;
        this.limit = value*smallunits;
    }
   
    public void noZeroYA_limit(boolean nozenolimit){
       noZEROYA_limit =  nozenolimit;
    }
 
    private void run1D_yn() throws IOException {

     
    int pageX =constants.getDislin_velkost_strany_X();
    int pageY =constants.getDislin_velkost_strany_Y();
     float XA = (float) minVal(xray);   
      if(XA >0)   XA=0 ;//XA - XA* (constants.getDislin_graph_nasobok_zo_stran()-1); // spodny limit X
       if(XA <0)   XA=XA * constants.getDislin_graph_nasobok_zo_stran(); // spodny limit X
     float YA =0;//= (float) minVal(y1ray);
     float legend_offset =1;
     if (islegend==true) legend_offset =1.15f; 
     
     float XE = (float) maxVal(xray) * constants.getDislin_graph_nasobok_zo_stran()*legend_offset;  // horny limit X
     float YE =0;//= (float) maxVal(y1ray);
     
     
     
    
     //sorter na osi aby sa tam zmestilo vždy všetko
     for(int cl1=0;cl1<y2ray.size();cl1++){
         
         if( cl1 == 0 && noZEROYA_limit == true){
             YA = (float) minVal(y2ray.get(cl1));
               
         }
         
         float YAd= (float) minVal(y2ray.get(cl1));
         float YEd= (float) maxVal(y2ray.get(cl1)); 
         if(YAd<YA) YA=YAd;
         if(YEd>YE) YE=YEd;
     } 
      
                   YE=YE * constants.getDislin_graph_nasobok_z_vrchu()*legend_offset; // uprava aby graf nebol nacapeny ulne hore userom nastavitelne
     if ( YA < 0 ) YA=YA * constants.getDislin_graph_nasobok_z_spodu();
     if ( YA > 0 && noZEROYA_limit == true ) YA=YA-YA/10; 
      if (limits==true) if (YE< limit) YE =(float) limit* constants.getDislin_graph_nasobok_zo_stran()*legend_offset;
     
     
     float XOR = XA;  // first label
     float YOR = YA;
      
     float XSTEP = (XE-XA)/constants.getDislin_Step_between_the_labels_X();//(Math.abs(XA)-Math.abs(XE))/((float)draw_constants.STEPdivider); // step between labels
     float YSTEP = (YE-YA)/constants.getDislin_Step_between_the_labels_Z();//(Math.abs(YA)-Math.abs(YE))/((float)draw_constants.STEPdivider);
      
     if(screen==true) {Dislin.metafl ("VIRT"); }// SCREEN OUTPUT
     if(screen==false) Dislin.metafl ("VIRT");
    
     Dislin.page(pageX, pageY); // size of window
     Dislin.scrmod("REVERS");
     Dislin.disini ();       // BEGIN DISLIN
     Dislin.pagera ();       // BORDER AROUND PAGE
     Dislin.hwfont ();       // HARDWARE FOTN
     Dislin.center();
    
     
     Dislin.axspos (500, 500);     //LEFT LOWeR CORNER OF AXIS SYSETM
   // Dislin.axslen (6000, 3000);    //
        
     Dislin.name(this.xname, "x");
     Dislin.name(this.y1name ,  "y");
     
     Dislin.labdig(constants.getDislin_pocet_des_miest(), "x"); 
     Dislin.labdig(constants.getDislin_pocet_des_miest(), "y");
     
//     Dislin.ticks  (9,  "x");
//     Dislin.ticks  (10, "y");
 
    

     
     
        if (constants.isDislin_graph_type() == true) {
            if (XA > 0) {
                XA = 0;
            }
            if(  noZEROYA_limit == true ){
            XA = (float) minVal(xray);
            }
            
            
            if (YA > 0) {
            if(noZEROYA_limit==false) YA = 0;
            }
            if(noZEROYA_limit==false) Dislin.axstyp("cross");
        }
        if (constants.isDislin_graph_bgcol() == true) {
            int ic = Dislin.intrgb(0.95f, 0.95f, 0.95f);
            Dislin.axsbgd(ic);
        }
        
        if (main_Jframe.isDislinn_Settings == true) {
          if (Dislin_Settings.getAXtype().isSelected() == true ){
              Dislin.axstyp("RECT");
          };  
        }
        
     
        Dislin.titlin (name_row1, 1);
        Dislin.titlin (name_row2, 3);  
        Dislin.texmod("ON");
 //********************
 // ZAKLADNE PRAVIDLO NA SPOLOCNU GRAFIKU  GRAF SA OBALI PREMENIMI TEDA ON MA JEDNU GRAFIKU A VSETKO OSTATNE MOZE MAT DRUHU 
 //*********************
 Dislin.height (constants.getDislin_velkost_textu_pred_grafom()); // spolocna grafika grafov
 Dislin.linwid(constants.getDislin_hrubka_ciar_pred_grafom());    // spolocna grafika grafov
 Dislin.hname(constants.getDislin_velkost_textu_pred_grafom());
 Dislin.labels(constants.getDislin_Dislin_Float_orEXP(), "Y");
     Dislin.graf   (XA, XE, XOR, XSTEP,
                    YA, YE, YOR, YSTEP);
    
        // zapiname vypiname grid
        if (constants.isDislin_grid() == true) {
            Dislin.linwid(constants.getDislin_hrubka_ciar_GRID());
            Dislin.grid(1, 1);
        }
        
  
        
 Dislin.height (constants.getDislin_velkost_textu_za_grafom());   // spolocna grafika grafov
 Dislin.linwid(constants.getDislin_hrubka_ciar_za_grafom());      // spolocna grafika grafov
    
     
    
     Dislin.title  ();
     
     if (main_Jframe.isDislinn_Settings == false) {
          Dislin.setvlt("RAIN");  
        }else {
     
     
          if(Dislin_Settings.getRAIN().isSelected() == true){
     Dislin.setvlt("RAIN");
}
if(Dislin_Settings.getCOL2().isSelected() == true){
      int ncoltcb = 254;
       float[] XR = new float[ncoltcb+1];
       float[] XG = new float[ncoltcb+1];
       float[] XB = new float[ncoltcb+1];
        XR[0]=1;
           XG[0]=1;
            XB[0]=1 ;
        for (int i = 1; i < ncoltcb+1; i++) {
           XR[i]=(float)i/(ncoltcb+1) ;
           XG[i]=(float)(ncoltcb+1-i)/(ncoltcb+1);
           XB[i]= ( XR[i] +  XG[i]  )/2 ;
        }
  
        Dislin.colran(1,ncoltcb-1);    //     ! works correctly only if 1 is subtracted
        Dislin.myvlt(XR,XG,XB,ncoltcb); 
}
if(Dislin_Settings.getTEMP().isSelected() == true){
     Dislin.setvlt("TEMP");
}
if(Dislin_Settings.getBW().isSelected() == true){
     Dislin.setvlt("GREY");
}}
     //legenda
          String cbuf="  ";
          Dislin.legini(cbuf, y2ray.size(), 10);
     
      for(int cl1=0;cl1<y2ray.size();cl1++){
          
          Dislin.setclr(255-10*cl1);
          Dislin.curve  (xray, y2ray.get(cl1), xray.length);
          if (islegend==true) Dislin.leglin(cbuf, nameY[cl1], cl1+1); 
     
      }  
     
      if(limits==true){
          Dislin.myline(new int[]{10,40}, 2);
          Dislin.color("RED");
          Dislin.linwid(constants.getDislin_hrubka_ciar_za_grafom()*2);
          double[] xray = new double[]{XA, XE };
          double[] yray = new double[]{limit, limit};
          Dislin.curve(xray, yray, 2);
          Dislin.linwid(constants.getDislin_hrubka_ciar_za_grafom()); 
      }
      
     if (islegend==true) {
        Dislin.legtit("");
        Dislin.frame(0);
        Dislin.setclr(255);
        Dislin.legend(cbuf, 7);
     }
     
     if(file==true) Dislin.rpng(Path); // SCREEN OUTPUT
     if (screen == true) {
         String userhome = System.getProperty(constants.getProgrampath());
         String temp_path  = userhome+ "\\" + "temp"+"\\" + "temp"+ help.getDateDF2()+ ".png" ;
          Dislin.rpng(temp_path);  Desktop.getDesktop().open( new File(temp_path)); }
     Dislin.disfin ();
  }

    public double units() {
        return smallunits;
    }
    /**
     * prevedie jednotkovu premenu potrebne zavolat pred zostrojenim grafu
     * @param units 
     */
    public void setunits(double units) {
   
         for (int cl1 = 0; cl1 < y2ray.size(); cl1++) {
            for (int cl2 = 0; cl2 < y2ray.get(cl1).length; cl2++) {
                y2ray.get(cl1)[cl2] = y2ray.get(cl1)[cl2] * units;
            }
        }
        this.smallunits = units;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dislin;

import de.dislin.Dislin;
import emft_vol2.constants;
import java.util.ArrayList;

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
    double smallunits=1;
    boolean screen = true;
    boolean file = false;
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
    
    public void draw_1D_yn(){
        run1D_yn();
    }

   
 
    private void run1D_yn() {

     
    int pageX =constants.getDislin_velkost_strany_X();
    int pageY =constants.getDislin_velkost_strany_Y();
        
     float XA = (float) minVal(xray) * constants.getDislin_graph_nasobok_zo_stran(); // spodny limit X
     float YA =0;//= (float) minVal(y1ray);
     float legend_offset =1;
     if (islegend==true) legend_offset =1.15f; 
     
     float XE = (float) maxVal(xray) * constants.getDislin_graph_nasobok_zo_stran()*legend_offset;  // horny limit X
     float YE =0;//= (float) maxVal(y1ray);
     
     //sorter na osi aby sa tam zmestilo vždy všetko
     for(int cl1=0;cl1<y2ray.size();cl1++){
         float YAd= (float) minVal(y2ray.get(cl1));
         float YEd= (float) maxVal(y2ray.get(cl1)); 
         if(YAd<YA) YA=YAd;
         if(YEd>YE) YE=YEd;
     } 
      
                   YE=YE * constants.getDislin_graph_nasobok_z_vrchu()*legend_offset; // uprava aby graf nebol nacapeny ulne hore userom nastavitelne
     if ( YA < 0 ) YA=YA * constants.getDislin_graph_nasobok_z_spodu();
     
     float XOR = XA;  // first label
     float YOR = YA;
      
     float XSTEP = (XE-XA)/constants.getDislin_Step_between_the_labels_X();//(Math.abs(XA)-Math.abs(XE))/((float)draw_constants.STEPdivider); // step between labels
     float YSTEP = (YE-YA)/constants.getDislin_Step_between_the_labels_Z();//(Math.abs(YA)-Math.abs(YE))/((float)draw_constants.STEPdivider);
      
     if(screen==true) Dislin.metafl ("xwin"); // SCREEN OUTPUT
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
            if (YA > 0) {
                YA = 0;
            }
            Dislin.axstyp("cross");
        }
        if (constants.isDislin_graph_bgcol() == true) {
            int ic = Dislin.intrgb(0.95f, 0.95f, 0.95f);
            Dislin.axsbgd(ic);
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
     //legenda
     String cbuf="  ";
     Dislin.legini(cbuf, y2ray.size(), 10);
     
      for(int cl1=0;cl1<y2ray.size();cl1++){
          Dislin.setclr(255-10*cl1);
          Dislin.curve  (xray, y2ray.get(cl1), xray.length);
         if (islegend==true) Dislin.leglin(cbuf, nameY[cl1], cl1+1); 
     }  
     
     if (islegend==true) {
        Dislin.legtit("");
        Dislin.frame(0);
        Dislin.setclr(255);
        Dislin.legend(cbuf, 7);
     }
     
     if(file==true) Dislin.rpng(Path); // SCREEN OUTPUT
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

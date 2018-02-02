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
public class plot_2D {
    
    double xray[] ;
    double yray[] ;
    double zmat[] ;
    boolean islegend=false;
    boolean fakemodel=false;
    boolean contury=false;
    boolean fill=false;
    double smallunits=1;
    String xname;
    String yname;
    String zname;
    String name_row1;
    String name_row2;
    String Path;
    boolean screen = true;
    boolean file = false;
    boolean isequalSides=false;
  
    // CONSTRUCTORS
     /**
      * 
      * @param xray xray array rozmer n
      * @param yray xray array rormer n
      * @param zmat fuinkčne hodnoty rozmer n*m
      * @param xname merno osi X
      * @param yname meno osi y
      * @param row_1 nazov grafu prvy riadok
      * @param row_2 nazov grafu druhy riadok
      * @param legend mam kreslit legendu ?
      */
     public plot_2D(double[] xray, double[] yray, double[] zmat,String xname,String yname,String row_1,String row_2,boolean contury) {
        this.xray = xray; // array data X
        this.yray = yray;
        this.zmat = zmat;
        this.xname=xname;
        this.yname=yname;
        this.name_row1=row_1;
        this.name_row2=row_2;
        this.contury=contury;

    }
    
     /**
      * defaultne vypnuta legenda
      * @param xray xray array rozmer n
      * @param yray xray array rormer n
      * @param zmat fuinkčne hodnoty rozmer n*m
      * @param xname merno osi X
      * @param yname meno osi y
      * @param row_1 nazov grafu prvy riadok
      * @param row_2 nazov grafu druhy riadok
      * @param contury oknturovy graf
     * @param fill fill graf ( ak je aj contury aj fil contury su automat vyplnute)
     * @param zname meno Zosi
      */
     public plot_2D(double[] xray, double[] yray, double[] zmat,String xname,String yname,String row_1,String row_2, boolean fill, String zname) {
        this.xray = xray; // array data X
        this.yray = yray;
        this.zmat = zmat;
        this.xname=xname;
        this.yname=yname;
        this.zname=zname;
        this.name_row1=row_1;
        this.name_row2=row_2;

        this.fill=fill;
        
        
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

    
    

    public boolean isIsequalSides() {
        return isequalSides;
    }

    public void setIsequalSides(boolean isequalSides) {
        this.isequalSides = isequalSides;
    }

    public void setFile(boolean file,String Path) {
        this.file = file;
        this.Path = Path;
    }
     /**
      * defaultne vypnuta legenda
      * @param xray xray array rozmer n
      * @param yray xray array rormer n
      * @param zmat fuinkčne hodnoty rozmer n*m
      * @param xname merno osi X
      * @param yname meno osi y
      * @param row_1 nazov grafu prvy riadok
      * @param row_2 nazov grafu druhy riadok
     * @param legend mam kreslit legenu.. ak ano  len pre fill fraf reaguje
     * @param fake mam urobit fake udaje ?
     * @param contury oknturovy graf 
     * @param fill fill graf ( ak je aj contury aj fil contury su automat vyplnute)
      */
     public plot_2D(double[] xray, double[] yray, double[] zmat,String xname,String yname,String row_1,String row_2,boolean legend, boolean fake,boolean contury, boolean fill) {
        this.xray = xray; // array data X
        this.yray = yray;
        this.zmat = zmat;
        this.xname=xname;
        this.yname=yname;
        this.name_row1=row_1;
        this.name_row2=row_2;
        this.islegend=legend;
        this.fakemodel= fake;
        
        this.contury=contury;
        this.fill=fill;
        
        if (this.fill==true){
            this.contury=false;
        }
    }
    
     
    
     
     public void draw_2D_yn(){
        run2D();
    }

    public boolean isIslegend() {
        return islegend;
    }

    public void setIslegend(boolean islegend) {
        this.islegend = islegend;
    }

    public boolean isFakemodel() {
        return fakemodel;
    }

    public void setFakemodel(boolean fakemodel) {
        this.fakemodel = fakemodel;
    }

    public double getSmallunits() {
        return smallunits;
    }

    public void setSmallunits(double smallunits) {
        this.smallunits = smallunits;
    }
    
      
  private void run2D () {
     
      int pageX =constants.getDislin_velkost_strany_X();
    int pageY =constants.getDislin_velkost_strany_Y();
      
     // this.fakemodel = true; //TOTOTOTOTOT OREEEEEEEEEC
      double zlev; // contour
      double zlev2[] = new double [constants.getDislin_pocet_kontur()]; // fill countour
      if (this.fakemodel == true){
     int n = 50, m = 50, i, j;
    
     double zmat []  = new double [n*m];
     double xray []  = new double [n];
     double yray []  = new double [m];

     

     double x, y;
     double fpi = 3.1415926/180.;
     double stepx = 360. / (n-1);
     double stepy = 360. / (m-1);

     for (i = 0; i < n; i++) {
        xray[i] = (float) (i * stepx);
     }

     for (j = 0; j < m; j++) {
        yray[j] = (float) (j * stepy);
     }

     for (i = 0; i < n; i++) {
       x = xray[i] * fpi;
       for (j = 0; j < m; j++) {
         y = yray[j] * fpi;
         zmat[i*m+j] = (float) (2 * Math.sin(x)* Math.sin(y));
       }
     }
     
     this.zmat=zmat;
     this.xray=xray;
     this.yray=yray;
     }
      
     float XA = (float) minVal(xray) ; // spodny limit X
     float YA = (float) minVal(yray);
     float ZA = (float) minVal(zmat);
     
     float XE = (float) maxVal(xray);  // horny limit X
     float YE =(float) maxVal(yray);
     float ZE =(float) maxVal(zmat);
     
     float XOR = XA;  
     float YOR = YA;
      float ZOR = ZA;
     
     float XSTEP = (XE-XA)/constants.getDislin_Step_between_the_labels_X();//(Math.abs(XA)-Math.abs(XE))/((float)draw_constants.STEPdivider); // step between labels
     float YSTEP = (YE-YA)/constants.getDislin_Step_between_the_labels_Y();//(Math.abs(YA)-Math.abs(YE))/((float)draw_constants.STEPdivider);
     float ZSTEP = (ZE-ZA)/constants.getDislin_Step_between_the_labels_Z();
     
    if(screen==true) Dislin.metafl ("xwin"); // SCREEN OUTPUT
     if(screen==false) Dislin.metafl ("VIRT");
     
     if(isequalSides==true) { // ak s strany rovnako velke
         double X =  (XE-XA);
         double Y =  (YE-YA);
         double nasobic = 1;
         while (true) {             
          
             double X1= X *nasobic;
             double Y1= Y *nasobic;
             
             if(Y1>= 2*pageY-300) break;
             if(X1>= 2*pageX-300) break; 
             nasobic=nasobic+ 0.1;
         }
         
         Dislin.page( (int) (X*nasobic) , (int) (Y*nasobic));
         
     }else{
         Dislin.page(pageX, pageY); // size of window
     }
     
     
     //Dislin.page(pageX, pageY); // size of window
     Dislin.scrmod("REVERS");
     Dislin.disini ();       // BEGIN DISLIN
     Dislin.pagera ();       // BORDER AROUND PAGE
     Dislin.hwfont ();       // HARDWARE FOTN
     Dislin.center();
    
     Dislin.name(this.xname, "x");
     Dislin.name(this.yname,  "y");
     
     
      
     if(fill==true)  Dislin.name(this.zname,  "z");
     Dislin.titlin (name_row1, 1);
     Dislin.titlin (name_row2, 3); 
      Dislin.texmod("ON"); 
     Dislin.labdig(constants.getDislin_pocet_des_miest(), "x"); 
     Dislin.labdig(constants.getDislin_pocet_des_miest(), "y");
     if(fill==true)  Dislin.labdig(constants.getDislin_pocet_des_miest(), "z");
     
     
    // Dislin.intax  ();
    //Dislin.axspos (450, 2650);
         
    if (constants.isDislin_graph_bgcol() == true) {
            int ic = Dislin.intrgb(0.95f, 0.95f, 0.95f);
            Dislin.axsbgd(ic);
        }

 Dislin.height (constants.getDislin_velkost_textu_pred_grafom()); // spolocna grafika grafov
 Dislin.linwid(constants.getDislin_hrubka_ciar_pred_grafom());    // spolocna grafika grafov
 Dislin.hname(constants.getDislin_velkost_textu_pred_grafom());
 
    if (contury==true){
     Dislin.graf   (XA, XE, XOR, XSTEP,
                    YA, YE, YOR, YSTEP);
    }
    
    if(fill==true){
     Dislin.graf3 ( XA, XE, XOR, XSTEP,
                    YA, YE, YOR, YSTEP,
                    ZA, ZE, ZOR, ZSTEP  );
    }
    
        // zapiname vypiname grid
        if (constants.isDislin_grid() == true) {
            Dislin.linwid(constants.getDislin_hrubka_ciar_GRID());
            Dislin.grid(1, 1);
        }
        
  
        
 Dislin.height (constants.getDislin_velkost_textu_za_grafom());   // spolocna grafika grafov
 Dislin.linwid(constants.getDislin_hrubka_ciar_za_grafom());      // spolocna grafika grafov
    
 Dislin.title  ();

    if(this.contury==true){
      Dislin.height (30);
     
     for (int i = 0; i < constants.getDislin_pocet_kontur(); i++) {
       zlev = maxVal(zmat)/(constants.getDislin_pocet_kontur()) +(i)*maxVal(zmat)/(constants.getDislin_pocet_kontur());    // MAXB/(NLV - 10)+(M - 1) * MAXB/(NLV - 10)
       if (i == 4) {
         Dislin.labels ("none", "contur");
       }
       else {
         Dislin.labels ("float", "contur");
       }
       Dislin.setclr ((i+1) * 28);
       Dislin.contur (xray, xray.length, yray, yray.length, zmat, (float) zlev);
     }
     
    }
    
    if(this.fill==true){
        
       for (int i = 0; i < constants.getDislin_pocet_kontur(); i++) {
       zlev2[constants.getDislin_pocet_kontur()-1-i] = maxVal(zmat)/constants.getDislin_pocet_kontur() + i * maxVal(zmat)/constants.getDislin_pocet_kontur();  // pobodny KOD MAXB/NLV + (M - 1) * MAXB/NLV
     }
     Dislin.conshd (xray, xray.length, yray, yray.length, zmat, zlev2, constants.getDislin_pocet_kontur());
    }
    
      if(file==true) Dislin.rpng(Path); // SCREEN OUTPUT
     Dislin.disfin ();
  }
     /**
     * prevedie jednotkovu premenu potrebne zavolat pred zostrojenim grafu
     * @param units 
     */
    public void setunits(double units) {
         for (int cl1 = 0; cl1 < zmat.length; cl1++) {
           
                zmat[cl1] = zmat[cl1] * units;
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

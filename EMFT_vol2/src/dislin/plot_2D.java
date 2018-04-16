/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dislin;

import InternalFrame.outputPanel;
import de.dislin.Dislin;
import emft_vol2.Dislin_Settings;
import emft_vol2.constants;
import static emft_vol2.constants.getDislin_pocet_kontur;
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
public class plot_2D {
    
    double xray[] ;
    double yray[] ;
    double zmat[] ;
    boolean islegend=false;
    boolean fakemodel=false;
    boolean contury=false;
    boolean fill=false;
    boolean limitD=false;
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
    boolean levels=false;
    double level;
  
    double upperZ;
    double lowerZ;
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
    
     
    
     
     public void draw_2D_yn() throws IOException{
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
    
      
    public void setlimitD(boolean limitD,double upperZ, double lowerZ){
        
        this.limitD=limitD;
        this.upperZ=upperZ;
        this.lowerZ=lowerZ;
        
    }
    
  private void run2D () throws IOException {
     
      int pageX =constants.getDislin_velkost_strany_X();
    int pageY =constants.getDislin_velkost_strany_Y();
      
     // this.fakemodel = true; //TOTOTOTOTOT OREEEEEEEEEC
      double zlev; // contour
      
      
      double zlev2[] = null ; // fill countour
       if(Dislin_Settings.getCOL2().isSelected() == true){
           zlev2 = new double[constants.getDislin_pocet_kontur()];
       }else{
           zlev2 = new double[constants.getDislin_pocet_kontur()+1]; 
       }
      
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
     
     if (main_Jframe.iscalculation_Settings == true && limitD ==true) {
          
      ZA = (float) lowerZ;
      ZE = (float) upperZ;
    }
     
     float XOR = XA;  
     float YOR = YA;
      float ZOR = ZA;
     
     float XSTEP = (XE-XA)/constants.getDislin_Step_between_the_labels_X();//(Math.abs(XA)-Math.abs(XE))/((float)draw_constants.STEPdivider); // step between labels
     float YSTEP = (YE-YA)/constants.getDislin_Step_between_the_labels_Y();//(Math.abs(YA)-Math.abs(YE))/((float)draw_constants.STEPdivider);
     float ZSTEP = (ZE-ZA)/constants.getDislin_Step_between_the_labels_Z();
     
  if(screen==true) {Dislin.metafl ("VIRT"); } // SCREEN OUTPUT
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
     
     
      
     if(fill==true) {
      if(fakemodel==false)   Dislin.name(this.zname,  "z");
      if(fakemodel==true)   Dislin.name("Value Z",  "z");
     }
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
  Dislin.labels(constants.getDislin_Dislin_Float_orEXP(), "Z");
   
  
      
        
      
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
 
 if (main_Jframe.iscalculation_Settings == false) {
          Dislin.setvlt("RAIN");  
        }else {
 
if(Dislin_Settings.getRAIN().isSelected() == true){
     Dislin.setvlt("RAIN");
}
if(Dislin_Settings.getCOL2().isSelected() == true){
      int ncoltcb = 253;
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
  
      
 
    if(this.contury==true){
      Dislin.height (30);
     
     for (int i = 0; i < constants.getDislin_pocet_kontur(); i++) {
       zlev = ZA +(i)*(Math.abs(ZE)+ Math.abs(ZA))/constants.getDislin_pocet_kontur();    // MAXB/(NLV - 10)+(M - 1) * MAXB/(NLV - 10)
      //      zlev = minVal(zmat) +(i)*maxVal(zmat)/(constants.getDislin_pocet_kontur());    // MAXB/(NLV - 10)+(M - 1) * MAXB/(NLV - 10)
 
     
     if (i == 5) {
         Dislin.labels ("none", "contur");
       }
       else {
         Dislin.labels ("float", "contur");
       }
       Dislin.setclr ((i+1) * (int) 254/constants.getDislin_pocet_kontur());
       Dislin.contur (xray, xray.length, yray, yray.length, zmat, (float) zlev);
     }
     
    }
    
    if(this.fill==true){
     if(Dislin_Settings.getCOL2().isSelected() == true){
         for (int i = 0; i < zlev2.length; i++) {
      // zlev2[constants.getDislin_pocet_kontur()-1-i] = maxVal(zmat)/constants.getDislin_pocet_kontur() + i * maxVal(zmat)/constants.getDislin_pocet_kontur();  // pobodny KOD MAXB/NLV + (M - 1) * MAXB/NLV
        
        if(i ==0){
           zlev2[i] =  (ZA) +(-1) * (Math.abs(ZE)+ Math.abs(ZA))/zlev2.length;  // pobodny KOD MAXB/NLV + (M - 1) * MAXB/NLV
      
        }else{
           zlev2[i] = (ZA) + (i-1) * (Math.abs(ZE)+ Math.abs(ZA))/zlev2.length;  // pobodny KOD MAXB/NLV + (M - 1) * MAXB/NLV
      
        }
       
       
        
       } 
     } else{
         for (int i = 0; i < zlev2.length; i++) {
      // zlev2[constants.getDislin_pocet_kontur()-1-i] = maxVal(zmat)/constants.getDislin_pocet_kontur() + i * maxVal(zmat)/constants.getDislin_pocet_kontur();  // pobodny KOD MAXB/NLV + (M - 1) * MAXB/NLV
        
        
           zlev2[i] = (ZA) + (i) * (Math.abs(ZE)+ Math.abs(ZA))/zlev2.length;  // pobodny KOD MAXB/NLV + (M - 1) * MAXB/NLV
      
        
     }      }  
 
      
      
       
     Dislin.conshd (xray, xray.length, yray, yray.length, zmat, zlev2, zlev2.length);
    }
    
      if(file==true) Dislin.rpng(Path); // SCREEN OUTPUT
      if (screen == true) {
         String userhome = System.getProperty(constants.getProgrampath());
         String temp_path  = userhome+ "\\" + "temp"+"\\" + "temp"+ help.getDateDF2()+ ".png" ;
          Dislin.rpng(temp_path);  Desktop.getDesktop().open( new File(temp_path)); }
     Dislin.disfin ();
  }
     /**
     * prevedie jednotkovu premenu potrebne zavolat pred zostrojenim grafu
     * @param units
     * @param limits nastav horny limit 
     */
    public void setunits(double units,boolean limits,double limitValue) {
         for (int cl1 = 0; cl1 < zmat.length; cl1++) {
           
                if(limits==false) zmat[cl1] = zmat[cl1] * units;
                if(limits==true){
                    if (zmat[cl1] >= limitValue){
                        zmat[cl1] = limitValue * units;
                    }else{
                        zmat[cl1] = zmat[cl1] * units;
                    }
                }
        }
        this.smallunits = units;
    }
    
        public void setunits(double units,boolean limits,double limitValueUp, double limitValueDown) {
         for (int cl1 = 0; cl1 < zmat.length; cl1++) {
           
                if(limits==false) zmat[cl1] = zmat[cl1] * units;
                if(limits==true){
                    if (zmat[cl1] >= limitValueUp){
                        zmat[cl1] = limitValueUp * units;
                    }if(zmat[cl1] <= limitValueDown){
                        zmat[cl1] = limitValueDown * units;
                    }
                    
                    else{
                        zmat[cl1] = zmat[cl1] * units;
                    }
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

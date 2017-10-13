/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.util.ArrayList;
import org.jdelaunay.delaunay.error.DelaunayError;
import org.jdelaunay.delaunay.geometries.DPoint;
import org.jdelaunay.delaunay.geometries.DTriangle;

/**
 * praca s lomenym priestorom
 * @author Jozef
 */
public class Tfield {
    
    private  ArrayList<DTriangle> triangField;

    public Tfield(ArrayList<DTriangle> tringles) {
        
       triangField=tringles;
    }

    public  ArrayList<DTriangle> getTriangField() {
        return triangField;
    }

    public void setTriangField(ArrayList<DTriangle> triangField) {
        this.triangField = triangField;
    }
    /**
     * 
     * @param P  bod kde cehm vediet Y suradnucu zadam XYZ
     * @return  vrati mi Y podla trojuholnikov ak bod mimo trojuholnikov vrati -1000
     */
    public DPoint getY( DPoint P ) throws DelaunayError{
        
      double X = P.getX();
      double Z = P.getZ();
      double Vyska = 0; 
      
      DPoint bod = new DPoint(X, Z, P.getY()); // potrebne otočit suradnice trojuholniky majuj u system XZY
      
      for(int cl1 =0 ;cl1<triangField.size();cl1++){
          
         if( triangField.get(cl1).isInside(bod) == true){
          DPoint normal = new DPoint(triangField.get(cl1).getNormalVector());
         // System.out.println("toto je normalovy vektor");   
         // System.out.println(normal);
          // http://www.math.cornell.edu/~froh/231f08e1a.pdf
          
          double A = normal.getX();
          double B = normal.getY();
          double C = normal.getZ();
          
          double x = triangField.get(cl1).getPoint(1).getX();
          double y = triangField.get(cl1).getPoint(1).getY();
          double z = triangField.get(cl1).getPoint(1).getZ();
          
          double D = A*x + B*y + C*z;
          
           if(C==0){   // ak je C == nule teda je to rovina rovnobežná alebo vrchol tak ber vyšku bodu
              
              // NIE toto cele prerobit zobrat trojuholnik  a najst presne bod s ktorym je to rovnake je to jeden z troch a ten bod potom zobrat ne neho vyšku a bude a to je platne pre oba pripady!!
              double porX = triangField.get(cl1).getPoint(0).getX(); // ak je peak vyber najvyšši bod 
              double porZ = triangField.get(cl1).getPoint(0).getY();
              double porX1 = triangField.get(cl1).getPoint(1).getX(); // ak je peak vyber najvyšši bod 
              double porZ1 = triangField.get(cl1).getPoint(1).getY();
              double porX2 = triangField.get(cl1).getPoint(2).getX(); // ak je peak vyber najvyšši bod 
              double porZ2 = triangField.get(cl1).getPoint(2).getY();
              
              if(porX == X && porZ == Z ){
              Vyska = triangField.get(cl1).getPoint(0).getZ();;
            
              }else if(porX1 == X && porZ1 == Z ){
              Vyska = triangField.get(cl1).getPoint(1).getZ();;
             
              } else if(porX2 == X && porZ2 == Z ){
              Vyska = triangField.get(cl1).getPoint(2).getZ();;
              } else{ // posledna možnost hovori že to nie je vrchol
               Vyska = triangField.get(cl1).getPoint(0).getZ();;  
              }
              
          }else{
          
          Vyska = (D - A*bod.getX() - B*bod.getY()) / C; //  prideleni vyska
          }
          
          
          break;
         }
         
        if (cl1 == triangField.size()-1){
          Vyska = -1000;
          
      }  
         if (Vyska== Double.NaN){ // ochrana pred NAN
           Vyska = 0;
       }
          
      } 
       DPoint bodTrans = new DPoint(X, Vyska  ,Z); //  tranformovany bod suradnice XYZ
       
       return bodTrans;
    }
    
     /**
     * 
     * @param P  bod kde cehm vediet Y suradnucu zadam XYZ
     * @return  vrati mi Y podla trojuholnikov ak bod mimo trojuholnikov vrati -1000
     */
    public double getY( double X, double Z) throws DelaunayError{
        
      
      double Vyska = 0; 
      
      DPoint bod = new DPoint(X, Z, 0); // potrebne otočit suradnice trojuholniky majuj u system XZY
      
     
      
      for(int cl1 =0 ;cl1<triangField.size();cl1++){
          
         if( triangField.get(cl1).isInside(bod) == true){
          DPoint normal = new DPoint(triangField.get(cl1).getNormalVector());
         // System.out.println("toto je normalovy vektor");   
         // System.out.println(normal);
          // http://www.math.cornell.edu/~froh/231f08e1a.pdf
          
          double A = normal.getX();
          double B = normal.getY();
          double C = normal.getZ();
          
          double x = triangField.get(cl1).getPoint(1).getX();
          double y = triangField.get(cl1).getPoint(1).getY();
          double z = triangField.get(cl1).getPoint(1).getZ();
          
          double D = A*x + B*y + C*z;
          
          if(C==0){   // ak je C == nule teda je to rovina rovnobežná alebo vrchol tak ber vyšku bodu
              
              // NIE toto cele prerobit zobrat trojuholnik  a najst presne bod s ktorym je to rovnake je to jeden z troch a ten bod potom zobrat ne neho vyšku a bude a to je platne pre oba pripady!!
              double porX = triangField.get(cl1).getPoint(0).getX(); // ak je peak vyber najvyšši bod 
              double porZ = triangField.get(cl1).getPoint(0).getY();
              double porX1 = triangField.get(cl1).getPoint(1).getX(); // ak je peak vyber najvyšši bod 
              double porZ1 = triangField.get(cl1).getPoint(1).getY();
              double porX2 = triangField.get(cl1).getPoint(2).getX(); // ak je peak vyber najvyšši bod 
              double porZ2 = triangField.get(cl1).getPoint(2).getY();
              
              if(porX == X && porZ == Z ){
              Vyska = triangField.get(cl1).getPoint(0).getZ();;
            
              }else if(porX1 == X && porZ1 == Z ){
              Vyska = triangField.get(cl1).getPoint(1).getZ();;
             
              } else if(porX2 == X && porZ2 == Z ){
              Vyska = triangField.get(cl1).getPoint(2).getZ();;
              } else{ // posledna možnost hovori že to nie je vrchol
               Vyska = triangField.get(cl1).getPoint(0).getZ();;  
              }
              
          }else{
          
          Vyska = (D - A*bod.getX() - B*bod.getY()) / C; //  prideleni vyska
          }
          
          break;
         }
         
        if (cl1 == triangField.size()-1){
          Vyska = -1000;
      }  
         
          
      } 
        //  tranformovany bod suradnice XYZ
       
       if (Vyska== Double.NaN){ // ochrana pred NAN
           Vyska = 0;
       }
       return Vyska;
    }
    
         /**
     * 
     * @param P  bod kde cehm vediet Y suradnucu zadam XYZ
     * @param noTerrain ak sa zavola tato verzia funkcie vrati vyšku v hodnote Noterrain
     * @return  vrati mi Y podla trojuholnikov ak bod mimo trojuholnikov vrati -1000
     */
    public double getY( double X, double Z,double noTerrain) throws DelaunayError{
        
      
      double Vyska = 0; 
      
      DPoint bod = new DPoint(X, Z, 0); // potrebne otočit suradnice trojuholniky majuj u system XZY
      
     
      
      for(int cl1 =0 ;cl1<triangField.size();cl1++){
          
         if( triangField.get(cl1).isInside(bod) == true){
          DPoint normal = new DPoint(triangField.get(cl1).getNormalVector());
         // System.out.println("toto je normalovy vektor");   
         // System.out.println(normal);
          // http://www.math.cornell.edu/~froh/231f08e1a.pdf
          
          double A = normal.getX();
          double B = normal.getY();
          double C = normal.getZ();
          
          double x = triangField.get(cl1).getPoint(1).getX();
          double y = triangField.get(cl1).getPoint(1).getY();
          double z = triangField.get(cl1).getPoint(1).getZ();
          
          double D = A*x + B*y + C*z;
          
          if(C==0){   // ak je C == nule teda je to rovina rovnobežná alebo vrchol tak ber vyšku bodu
              
              // NIE toto cele prerobit zobrat trojuholnik  a najst presne bod s ktorym je to rovnake je to jeden z troch a ten bod potom zobrat ne neho vyšku a bude a to je platne pre oba pripady!!
              double porX = triangField.get(cl1).getPoint(0).getX(); // ak je peak vyber najvyšši bod 
              double porZ = triangField.get(cl1).getPoint(0).getY();
              double porX1 = triangField.get(cl1).getPoint(1).getX(); // ak je peak vyber najvyšši bod 
              double porZ1 = triangField.get(cl1).getPoint(1).getY();
              double porX2 = triangField.get(cl1).getPoint(2).getX(); // ak je peak vyber najvyšši bod 
              double porZ2 = triangField.get(cl1).getPoint(2).getY();
              
              if(porX == X && porZ == Z ){
              Vyska = triangField.get(cl1).getPoint(0).getZ();;
            
              }else if(porX1 == X && porZ1 == Z ){
              Vyska = triangField.get(cl1).getPoint(1).getZ();;
             
              } else if(porX2 == X && porZ2 == Z ){
              Vyska = triangField.get(cl1).getPoint(2).getZ();;
              } else{ // posledna možnost hovori že to nie je vrchol
               Vyska = triangField.get(cl1).getPoint(0).getZ();;  
              }
              
          }else{
          
          Vyska = (D - A*bod.getX() - B*bod.getY()) / C; //  prideleni vyska
          }
          
          break;
         }
         
        if (cl1 == triangField.size()-1){
          Vyska = noTerrain;
      }  
         
          
      } 
        //  tranformovany bod suradnice XYZ
       
       if (Vyska== Double.NaN){ // ochrana pred NAN
           Vyska = 0;
       }
       return Vyska;
    }
    
    
    
    /**
     * najdy syšku ale urob k nej aj plusovy offset použitoe pre zavesny bod stožiara
     * @param P  bod kde cehm vediet Y suradnucu zadam XYZ
     * @return  vrati mi Y podla trojuholnikov  ak bod mimo trojuholnikov vrati -1000
     */
    public DPoint getYaboveTer( DPoint P ) throws DelaunayError{
        
      double X = P.getX();
      double Z = P.getZ();
      double Vyska = P.getY();; 
      
      DPoint bod = new DPoint(X, Z, P.getY()); // potrebne otočit suradnice trojuholniky majuj u system XZY
      
      for(int cl1 =0 ;cl1<triangField.size();cl1++){
          
         if( triangField.get(cl1).isInside(bod) == true){
          DPoint normal = new DPoint(triangField.get(cl1).getNormalVector());
          
          // http://www.math.cornell.edu/~froh/231f08e1a.pdf
          
          double A = normal.getX();
          double B = normal.getY();
          double C = normal.getZ();
          
          double x = triangField.get(cl1).getPoint(1).getX();
          double y = triangField.get(cl1).getPoint(1).getY();
          double z = triangField.get(cl1).getPoint(1).getZ();
          
          double D = A*x + B*y + C*z;
          
          
           if(C==0){   // ak je C == nule teda je to rovina rovnobežná alebo vrchol tak ber vyšku bodu
              
              // NIE toto cele prerobit zobrat trojuholnik  a najst presne bod s ktorym je to rovnake je to jeden z troch a ten bod potom zobrat ne neho vyšku a bude a to je platne pre oba pripady!!
              double porX = triangField.get(cl1).getPoint(0).getX(); // ak je peak vyber najvyšši bod 
              double porZ = triangField.get(cl1).getPoint(0).getY();
              double porX1 = triangField.get(cl1).getPoint(1).getX(); // ak je peak vyber najvyšši bod 
              double porZ1 = triangField.get(cl1).getPoint(1).getY();
              double porX2 = triangField.get(cl1).getPoint(2).getX(); // ak je peak vyber najvyšši bod 
              double porZ2 = triangField.get(cl1).getPoint(2).getY();
              
              if(porX == X && porZ == Z ){
              Vyska =Vyska + triangField.get(cl1).getPoint(0).getZ();;
            
              }else if(porX1 == X && porZ1 == Z ){
              Vyska =Vyska + triangField.get(cl1).getPoint(1).getZ();;
             
              } else if(porX2 == X && porZ2 == Z ){
              Vyska =Vyska + triangField.get(cl1).getPoint(2).getZ();;
              } else{ // posledna možnost hovori že to nie je vrchol
               Vyska =Vyska + triangField.get(cl1).getPoint(0).getZ();;  
              }
              
          }else{
          
          Vyska =Vyska + (D - A*bod.getX() - B*bod.getY()) / C; //prideleni vyska + offset zavesneho bodu
          }// ochrana ak sa bod nachadza na priesecniku viacerych bodov 
          
          
          
          break;                                                
          
          
         }
         
        
      if (cl1 == triangField.size()-1){
          Vyska = -1000;
          
      }   
          
      } 
      if (Vyska== Double.NaN){ // ochrana pred NAN
           Vyska = 0;
       }
      
       DPoint bodTrans = new DPoint(X, Vyska  ,Z); //  tranformovany bod suradnice XYZ
       
       return bodTrans;
    }
    /**
     * 
     * @param P bod nad absolutnou nulou 
     * @return vrati kolmi priemet do roiny nachadzajucej sa nad nim ( definovaj trojuholnikmi )
     * @throws DelaunayError 
     */
    public  DPoint getPerpendicularProjection (DPoint P) throws DelaunayError{
        
     
      DPoint bod = new DPoint(P.getX(), P.getZ(), P.getY()); // potrebne otočit suradnice trojuholniky majuj u system XZY
      DPoint perpendicular = new DPoint(0, 0, 0); // potrebne otočit suradnice trojuholniky majuj u system XZY
      
      
      for(int cl1 =0 ;cl1<triangField.size();cl1++){
          
         if( triangField.get(cl1).isInside(bod) == true){
             
          DPoint normal = new DPoint(triangField.get(cl1).getNormalVector());
          
          // https://math.stackexchange.com/questions/100761/how-do-i-find-the-projection-of-a-point-onto-a-plane
          
          double a = normal.getX();
          double b = normal.getY();
          double c = normal.getZ();
          
          double x = bod.getX();
          double y = bod.getY();
          double z = bod.getZ();
          
          double d = triangField.get(cl1).getPoint(0).getX();
          double e = triangField.get(cl1).getPoint(0).getY();
          double f = triangField.get(cl1).getPoint(0).getZ();
          
          double t = (a*d -a*x+b*e-b*y+c*f-c*z )/(Math.pow(a, 2) + Math.pow(b, 2) +Math.pow(c, 2) );
        
          perpendicular.setX(x+t*a);
          perpendicular.setY(z+t*c);
          perpendicular.setZ(y+t*b);
          
          
          
          break;                                                   
         }
     
      if (cl1 == triangField.size()-1){
          perpendicular.setX(P.getX());
          perpendicular.setY(-1000);
          perpendicular.setZ(P.getZ());
          
      }   
          
      } 
      
      
       
       
       return perpendicular; 
        
        
         
     }
    /**
     * unkcia na zrkadlenie bodi pozor využiva getperpendicularProjection teda zrkadli  na zaklade kolmeho priemetu do nad rovinou v ktorej sa nachádza
     * @param P bod ktorý sa ide zrkadlit 
     * @return
     * @throws DelaunayError 
     */
    public  DPoint getMirrorPoint (DPoint P) throws DelaunayError{
        
    DPoint perpendicular = new DPoint(getPerpendicularProjection(P)); // potrebne otočit suradnice trojuholniky majuj u system XZY
    
    DPoint mirror = new DPoint(0,0,0);

    mirror.setX(P.getX() + 2*( perpendicular.getX() - P.getX()));
    mirror.setY(P.getY() + 2*( perpendicular.getY() - P.getY()));
    mirror.setZ(P.getZ() + 2*( perpendicular.getZ() - P.getZ()));
       
    
    //mirror.setX(P.getX() - 2*( perpendicular.getX() - P.getX()));
    //mirror.setY(P.getY() - 2*( perpendicular.getY() - P.getY()));
    //mirror.setZ(P.getZ() - 2*( perpendicular.getZ() - P.getZ()));
       
     return mirror;
      
     }
    /**
     * vrati bod premietnuty do tejto rovininy
     * @param P  bod z ktoreho vytvory aproxx rovinu
     * @param beta uhol otočenia si rozpatia voči nule systemu
     * @param step krok hladania okraja
     * @return
     * @throws DelaunayError 
     */
    public  DPoint getMirrorPointAproxxPlane (DPoint P,double beta,double step) throws DelaunayError{
        
    DPoint perpendicular = new DPoint(getPerpendicularProjectionOnApproxxPlane(P,beta,step)); // potrebne otočit suradnice trojuholniky majuj u system XZY
    
    DPoint mirror = new DPoint(0,0,0);

    mirror.setX(P.getX() + 2*( perpendicular.getX() - P.getX()));
    mirror.setY(P.getY() + 2*( perpendicular.getY() - P.getY()));
    mirror.setZ(P.getZ() + 2*( perpendicular.getZ() - P.getZ()));
       
    
    //mirror.setX(P.getX() - 2*( perpendicular.getX() - P.getX()));
    //mirror.setY(P.getY() - 2*( perpendicular.getY() - P.getY()));
    //mirror.setZ(P.getZ() - 2*( perpendicular.getZ() - P.getZ()));
       
     return mirror;
      
     }   
    /**
     *  vrati kolmi priemet do roviny aproximačne vrati bod
     * @param P  bod z ktoreho vytvory aproxx rovinu
     * @param beta uhol otočenia si rozpatia voči nule systemu
     * @param step krok hladania okraja
     * @return
     * @throws DelaunayError 
     */
    public  DPoint getPerpendicularProjectionOnApproxxPlane (DPoint P,double beta,double step) throws DelaunayError{
        
     
      DPoint bod = new DPoint(P.getX(), P.getZ(), P.getY()); // potrebne otočit suradnice trojuholniky majuj u system XZY
      DPoint perpendicular = new DPoint(0, 0, 0); // potrebne otočit suradnice trojuholniky majuj u system XZY
      
          DTriangle novaRovina = getApproxPlane(P, beta, step);
        //  System.out.println(novaRovina.getPoint(0));
        //  System.out.println(novaRovina.getPoint(1));
        //  System.out.println(novaRovina.getPoint(2));
          DPoint normal = new DPoint(novaRovina.getNormalVector());
          
          // https://math.stackexchange.com/questions/100761/how-do-i-find-the-projection-of-a-point-onto-a-plane
          
          double a = normal.getX();
          double b = normal.getY();
          double c = normal.getZ();
          
          double x = bod.getX();
          double y = bod.getY();
          double z = bod.getZ();
          
          double d = novaRovina.getPoint(0).getX();
          double e = novaRovina.getPoint(0).getY();
          double f = novaRovina.getPoint(0).getZ();
          
          double t = (a*d -a*x+b*e-b*y+c*f-c*z )/(Math.pow(a, 2) + Math.pow(b, 2) +Math.pow(c, 2) );
        
          perpendicular.setX(x+t*a);
          perpendicular.setY(z+t*c);
          perpendicular.setZ(y+t*b);
          
          

       
       
       return perpendicular; 
        
        
         
     }
    /**
     * najde rovinu ktora je paralelna s rovinou nuly a kolma na os vedenia vo vyške priemtu bodu na povodnu rovinu
     * @param P  bod z ktoreho vytvory aproxx rovinu
     * @param beta uhol otočenia si rozpatia voči nule systemu
     * @param step krok hladania okraja
     * @return trojuhilnik rovinu
     * @throws DelaunayError 
     */
    public  DTriangle getApproxPlane (DPoint P, double beta,double step) throws DelaunayError{
         
        
         
         //zatal XYZ moja sustava
         DPoint up = new DPoint(P.getX(),0,P.getZ());
         DPoint down = new DPoint(P.getX(),0,P.getZ());
 
         // rotate point around another point
         // https://math.stackexchange.com/questions/1257345/caclulate-x-y-coordinates-of-point-after-rotation-around-another-point-of-given
         double movementup = 0;
         double movementdown = 0;
         double Xcor = P.getX();
         double Zcor = P.getZ();
         boolean goUp = true;
         boolean goDown = true;
         
         while(true){

             up= getY(up);
             down = getY(down);               
                  
             if(up.getY()==-1000){goUp = false;}
             else{
                 movementup=movementup+step;
             up.setX( Xcor - (movementup)*Math.sin(beta)  );
             up.setZ( Zcor + ( movementup )*Math.cos(beta) );
             }
             
             if(down.getY()==-1000){goDown = false;}
             else{
                 movementdown=movementdown+step;
             down.setX( Xcor - ( -movementdown )*Math.sin(beta) );
             down.setZ( Zcor + ( -movementdown )*Math.cos(beta) );
             }
             
             if(goUp == false && goDown == false) break;
                 
         }
         
         
          up.setX( Xcor - ( movementup-step )*Math.sin(beta) );
          up.setZ( Zcor + ( movementup-step )*Math.cos(beta) );
          up= getY(up);
          
          DPoint A = new DPoint(up.getX(), up.getZ(), up.getY());
        
           down.setX( Xcor - ( -movementdown +step)*Math.sin(beta) );
           down.setZ( Zcor + ( -movementdown +step )*Math.cos(beta) );
           down = getY(down);
         
           DPoint B = new DPoint(down.getX(), down.getZ(), down.getY());
           DPoint B2 = new DPoint(down.getX()+step, down.getZ(), down.getY());
              
           DTriangle rovina = new DTriangle(A, B, B2); // dufam že to nebude robit kokotiny
         
        return rovina;
    }
    
    
}

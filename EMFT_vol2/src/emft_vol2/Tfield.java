/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emft_vol2;

import java.util.ArrayList;
import org.jdelaunay.delaunay.error.DelaunayError;
import org.jdelaunay.delaunay.geometries.DPoint;
import org.jdelaunay.delaunay.geometries.DTriangle;

/**
 * input tringulation data point and get his height in LC
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
          
          Vyska = (D - A*bod.getX() - B*bod.getY()) / C; //  prideleni vyska
          
          
          
         }
         
        if (cl1 == triangField.size()-1){
          Vyska = -1000;
      }  
         
          
      } 
       DPoint bodTrans = new DPoint(X, Vyska  ,Z); //  tranformovany bod suradnice XYZ
       
       return bodTrans;
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
          
          Vyska = Vyska +(D - A*bod.getX() - B*bod.getY()) / C; //  prideleni vyska + offset zavesneho bodu
          break;                                                // ochrana ak sa bod nachadza na priesecniku viacerych bodov 
          
          
         }
         
        
      if (cl1 == triangField.size()-1){
          Vyska = -1000;
          
      }   
          
      } 
      
      
       DPoint bodTrans = new DPoint(X, Vyska  ,Z); //  tranformovany bod suradnice XYZ
       
       return bodTrans;
    }
    
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
    
}

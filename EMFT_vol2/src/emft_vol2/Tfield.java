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
     * @return  vrati mi Y podla trojuholnikov
     */
    public DPoint getY( DPoint P ) throws DelaunayError{
        
      double X = P.getX();
      double Z = P.getZ();
      double Vyska = 0; 
      
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
          
          Vyska = (D - A*bod.getX() - B*bod.getY()) / C; //  prideleni vyska
          
          
          
         }
         
        
         
          
      } 
       DPoint bodTrans = new DPoint(X, Vyska  ,Z); //  tranformovany bod suradnice XYZ
       
       return bodTrans;
    }
    
    
    
}

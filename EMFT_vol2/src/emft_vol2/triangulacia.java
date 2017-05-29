/*
 POtrebne dorobit generovanie hran
 */
package emft_vol2;



import java.awt.Point;

import java.util.ArrayList;
import java.util.List;
import org.jdelaunay.delaunay.ConstrainedMesh;
import org.jdelaunay.delaunay.error.DelaunayError;
import org.jdelaunay.delaunay.geometries.DEdge;
import org.jdelaunay.delaunay.geometries.DPoint;
import org.jdelaunay.delaunay.geometries.DTriangle;






/**
 *
 * @author Jozef
 */
public class triangulacia {

    private static double A;
    private static double Z;
    private static int DN;
    private static double[] LCcoordinates= new double[3];
    private static ArrayList<double[]> body = new ArrayList<double[]>();
    private static boolean deff = false; // defaultna tringulacia okolo stvorcovej hrany ak true tak bdue vytvarat na zaklade bodov hranu
    private static ArrayList<DTriangle> results = new ArrayList<DTriangle>();
    private static boolean IsMeshCalculated = false;
    private static int numberOfPoints;
    private static List<DPoint> resultsPoint = new ArrayList<DPoint>();

    public static List<DPoint> getResultsPoint() {
        return resultsPoint;
    }

    public static void setResultsPoint(List<DPoint> resultsPoint) {
        triangulacia.resultsPoint = resultsPoint;
    }
    
    /**
     * 
     * @param A dlzka rozpatia ( plochy )
     * @param Z priecna dlzka plochy
     * @param DN krok stvorcovania zeme
     * @param body arraylist double[3] musi byt v LC
     * @param deff Ake bude vytvorenie hran true ( stvorec ) false ( podla bodov )
     */

    public triangulacia(double A,double Z, int DN,ArrayList body,boolean deff) {
        this.A=A; 
        this.Z=Z;
        this.DN =DN;
        this.LCcoordinates=LCcoordinates;
        this.body=body;  // body už su v lC koordinatoch
        this.deff=deff;
    }
    
    public triangulacia(double A,double Z, int DN,ArrayList body) {
        this.A=A; 
        this.Z=Z;
        this.DN =DN;
        this.LCcoordinates=LCcoordinates;
        this.body=body;
       
    }
    
    public void run() throws DelaunayError{
        IsMeshCalculated =false;
        ConstrainedMesh mesh = new ConstrainedMesh();
        ArrayList<DEdge> edges = new ArrayList<DEdge>();
      
        
        if (deff==true ){
        //vytvorenie hran najprv hry s A potom Z a uprava podla LC
            for(int cl1=0; cl1<=3;cl1++){
              // help.printl("OK som v triangl", true);
                if(cl1==0){    //pozdlzna kladna
                    DPoint c1 = new DPoint(0, Z, LCcoordinates[1]); // vytvor bod 1 xzy
                    DPoint c2 = new DPoint(A, Z, LCcoordinates[1]); // vytvor bod 2 xzy
                    edges.add(new DEdge(c1,c2)); // vytvor hranu z bodov 1 a 2
                    mesh.addPoint(c1);
                    mesh.addPoint(c2);
                }if(cl1==1){  //pozdlzna zaporna
                    DPoint c1 = new DPoint(0, -Z, LCcoordinates[1]); // vytvor bod 1 xzy
                    DPoint c2 = new DPoint(A, -Z, LCcoordinates[1]); // vytvor bod 2 xzy
                    edges.add(new DEdge(c1,c2)); 
                    mesh.addPoint(c1);
                    mesh.addPoint(c2);
                }if(cl1==2){ //priecna nulta
                    DPoint c1 = new DPoint(0, Z, LCcoordinates[1]); // vytvor bod 1 xzy
                    DPoint c2 = new DPoint(0, -Z, LCcoordinates[1]); // vytvor bod 2 xzy      
                    edges.add(new DEdge(c1,c2)); 
                    mesh.addPoint(c1);
                    mesh.addPoint(c2);
                }if(cl1==3){ //pozdlzna A
                    DPoint c1 = new DPoint(A, Z, LCcoordinates[1]); // vytvor bod 1 xzy
                    DPoint c2 = new DPoint(A, -Z, LCcoordinates[1]); // vytvor bod 2 xzy       
                    edges.add(new DEdge(c1,c2)); 
                    mesh.addPoint(c1);
                    mesh.addPoint(c2);
                }
            }
          
        }else{
            
            ArrayList<Point> points = new ArrayList<Point>();
            for (int cl2 = 0; cl2 < body.size(); cl2++) //vytvorime arraylist of points
        {
            int x =  (int) body.get(cl2)[0] *100; // bdy su double ale su v LC takže hodnoty vynasobime 100
            int z = (int)  body.get(cl2)[2] *100; // prejdeme na cm v integery 
            Point e = new Point(x, z);
            points.add(cl2, e);
        }
            for(int cl2= 0; cl2< points.size();cl2++){
        help.printl(points.get(cl2).toString(),true);
        }
            
            
            
            QuickHull qh = new QuickHull();
            ArrayList<Point> p = qh.quickHull(points);
            List<Point> pp = GrahamScan.getConvexHull(points.subList(0,points.size()));
            
         help.printl("obvodove body su", true);
           for(int cl2= 0; cl2< p.size();cl2++){
        help.printl(p.get(cl2).toString(),true);

        }
            help.printl("obvodove body su GRAHAM", true);
           for(int cl2= 0; cl2< pp.size();cl2++){
        help.printl(pp.get(cl2).toString(),true);

        }
            
            
            for(int cl3=0;cl3< p.size();cl3++){
                
                int X = p.get(cl3).x;    
                int Y = (int) body.get(najdiVysku(X, body))[1]*100;
                int Z = p.get(cl3).y;
                
                double XX = (double) X;
                double YY = (double) Y;
                double ZZ = (double) Z;
        
                 DPoint c1 = new DPoint(XX/100, ZZ/100, YY/100); // vytvor bod 1 xyz
                 
                 if(cl3+1 == p.size()){
                 
                 cl3 = -1; // ak už je c13 dlhe ako dlžka matice bov tak sa vrat na nulu( prvy bod spoji s poslednym ) a tym sa uzavrie edge
                 X = p.get(cl3+1).x;    
                 Y = (int) body.get(najdiVysku(X, body))[1]*100; // najdi pod X hodnotu Y
                 Z = p.get(cl3+1).y;
                
                 XX = (double) X; // prehod na duble
                 YY = (double) Y;
                 ZZ = (double) Z;
                 
                 DPoint c2 = new DPoint(XX/100, ZZ/100, YY/100); // vytvor bod 2 xyz 
                 edges.add(new DEdge(c1,c2));
                 if(cl3==-1){
                     break ;
                 }}
            }
             
            //https://gis.stackexchange.com/questions/5426/finding-boundary-co-ordinates-from-given-set-of-point-co-ordinates
        }
        
        //vlozenie bodov z arrylist bod  auprava podla LC 
        for(int cl1=0; cl1<body.size();cl1++){
          DPoint c1 = new DPoint(body.get(cl1)[0],body.get(cl1)[2], body.get(cl1)[1]); // vytvor bod 1 xzy
          mesh.addPoint(c1);
        }
        
        
        help.printl(String.valueOf("je poratane?              " + mesh.isMeshComputed()), true); // je poratane čo ma byt ?
        help.printl(String.valueOf("pocet constrain edges     " + edges.size()), true); // je poratane čo ma byt ?
        help.printl(String.valueOf("pocet bodov body          " + body.size()), true); // je poratane čo ma byt ?
        help.printl(String.valueOf("pocet bodov mesh          " + mesh.getPoints().size()), true); // je poratane čo ma byt ?
        for(int cl2= 0; cl2< body.size();cl2++){
        help.printl(String.valueOf(body.get(cl2)[0]),true);
        }
        //PRINT body mesh 
        for(int cl2= 0; cl2< mesh.getPoints().size();cl2++){
        help.printl(mesh.getPoints().get(cl2).toString(),true);
        }
        
        
        mesh.setConstraintEdges(edges);  // zadaj constraint edge
        mesh.forceConstraintIntegrity(); // kontroluj kvalitu siete
        mesh.processDelaunay();          // urob triangulaciu
        mesh.removeFlatTriangles();      // odstrani tringle ktore nepatria do 2D reprezentacie
        IsMeshCalculated =mesh.isMeshComputed();
        numberOfPoints=mesh.getPoints().size();
        resultsPoint=mesh.getPoints();
        
        //TESTER
        help.printl(String.valueOf("je poratane?           "  + mesh.isMeshComputed()), true); // je poratane čo ma byt ?
        help.printl(String.valueOf("pocet constrain edges  " + mesh.getConstraintEdges().size()), true); // je poratane čo ma byt ?
        help.printl(String.valueOf("pocet bodov            " + mesh.getPoints().size()), true); // je poratane čo ma byt ?
        help.printl(String.valueOf("pocet trojuholnikov    " + mesh.getTriangleList().size()), true); // je poratane čo ma byt ?
        //TESTER SECTION
        
        results=(ArrayList<DTriangle>) mesh.getTriangleList();
        
    }

    public static boolean isIsMeshCalculated() {
        return IsMeshCalculated;
    }

    public static void setIsMeshCalculated(boolean IsMeshCalculated) {
        triangulacia.IsMeshCalculated = IsMeshCalculated;
    }
    
    
    
    public  void clearResults(){        
        results.clear();
    }

    public static double getZ() {
        return Z;
    }

    public static void setZ(double Z) {
        triangulacia.Z = Z;
    }

    public static int getDN() {
        return DN;
    }

    public static void setDN(int DN) {
        triangulacia.DN = DN;
    }

    public static double[] getLCcoordinates() {
        return LCcoordinates;
    }

    public static void setLCcoordinates(double[] LCcoordinates) {
        triangulacia.LCcoordinates = LCcoordinates;
    }

    public static ArrayList<double[]> getBody() {
        return body;
    }

    public static void setBody(ArrayList<double[]> body) {
        triangulacia.body = body;
    }

    public static boolean isDeff() {
        return deff;
    }

    public static void setDeff(boolean deff) {
        triangulacia.deff = deff;
    }

    public  ArrayList<DTriangle> getResults() {
        return results;
    }
    
    public  List<DPoint> getResultsPoinList() {
        return resultsPoint;
    }
     /**
      * is Is mesh Calculated = true return triangles else results clear and return nullpointer;
      * @return 
      */
     public static ArrayList<DTriangle> getTriangles() {
        if(IsMeshCalculated == true){
        return results;    
        } 
        results.clear();
        return results;
        
    }

     public static int getNumberOfPoints() {
        if(IsMeshCalculated == true){
            return numberOfPoints;
        } 
        ;
        return 0;
        
    }

     
     
    private static int najdiVysku(int X, ArrayList<double[]> body){
        
        int poradoveCislo=0;
        for (int cl1 = 0;cl1<body.size();cl1++){
             int X_porovnavacie = (int) body.get(cl1)[0]*1000;
            
             if (X == X_porovnavacie){
                 poradoveCislo=cl1;
                 break;
             }
             
         }
        
        return poradoveCislo;
    }
    
}

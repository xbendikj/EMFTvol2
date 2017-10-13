/*
 POtrebne dorobit generovanie hran
 */
package BackEnd;



import BackEnd.QuickHull;
import java.awt.Point;

import java.util.ArrayList;
import java.util.List;
import org.jdelaunay.delaunay.ConstrainedMesh;
import org.jdelaunay.delaunay.error.DelaunayError;
import org.jdelaunay.delaunay.geometries.DEdge;
import org.jdelaunay.delaunay.geometries.DPoint;
import org.jdelaunay.delaunay.geometries.DTriangle;
import tools.help;






/**
 *
 * @author Jozef
 */
public class triangulacia {

    private  double A;
    private  double Z;
    private  int DN;
    private double[] LCcoordinates= new double[3];
    private  ArrayList<double[]> Body = new ArrayList<double[]>();
    private  ArrayList<double[]> BodyOutput = new ArrayList<double[]>();
    private  boolean deff = false; // defaultna tringulacia okolo stvorcovej hrany ak true tak bdue vytvarat na zaklade bodov hranu
    private  boolean deff2 = false;
    private  ArrayList<DTriangle> results = new ArrayList<DTriangle>();
    private  boolean IsMeshCalculated = false;
    private  int numberOfPoints;
    private  List<DPoint> resultsPoint = new ArrayList<DPoint>();
    private boolean isAppteren; 
    
    public  List<DPoint> getResultsPoint() {
        return resultsPoint;
    }

    public  void setResultsPoint(List<DPoint> resultsPoint) {
         this.resultsPoint = resultsPoint;
    }
    
    /**
     * vytvorenie terenu pomocou triangulacie, POZOR Vsetko sa pocitna v Lokalnych suradniciach  ALE vstupuju to aj poloha LC  v globalnych pretože je potebna vyška na offset
     * @param A dlzka rozpatia ( plochy )
     * @param Z priecna dlzka plochy
     * @param DN krok stvorcovania zeme
     * @param body arraylist double[3] musi byt v LC
     * @param deff  false ( podla bodov ) Ake bude vytvorenie hran true = ( stvorec ) default value = false
     * @param deff2 false priratavam vyšku LC[1] ku každemu bodu  ( teda body už neobsahuju tuto vyku a je od každeho odčitana ) true = vkladam nulu teda body už pbsahuju aj vyšku default value = false
**/
    public triangulacia(double A,double Z,ArrayList body,boolean deff,double[] LCcoordinates,boolean deff2, boolean isAppteren) {
        this.A=A; 
        this.Z=Z;
        //this.DN =DN;
        this.LCcoordinates=LCcoordinates;
        this.Body=body;  // body už su v lC koordinatoch
        this.deff=deff;
        this.deff2=deff2;
        this.isAppteren = isAppteren;
    }
    
    public triangulacia(double A,double Z,ArrayList body,double[] LCcoordinates) {
        this.A=A; 
        this.Z=Z;
        //this.DN =DN;
        this.LCcoordinates=LCcoordinates;
        this.Body=body;
       
    }

    public ArrayList<double[]> getBodyOutput() {
        return BodyOutput;
    }

    public void setBodyOutput(ArrayList<double[]> BodyOutput) {
        this.BodyOutput = BodyOutput;
    }

    public boolean isDeff2() {
        return deff2;
    }

    public void setDeff2(boolean deff2) {
        this.deff2 = deff2;
    }

    public boolean isIsAppteren() {
        return isAppteren;
    }

    public void setIsAppteren(boolean isAppteren) {
        this.isAppteren = isAppteren;
    }
    
    public void run() throws DelaunayError{
        IsMeshCalculated =false;
    
        ArrayList<double[]> Bodyinterne = new ArrayList<double[]>(Body);
        if (isAppteren == false) {deff=true;
         Bodyinterne.removeAll(Bodyinterne);
        }
        
        double vyskaLC_Y = LCcoordinates[1]; 
        if (deff2==true) vyskaLC_Y = 0; 
        
        
        ConstrainedMesh mesh = new ConstrainedMesh();
        ArrayList<DEdge> edges = new ArrayList<DEdge>();
        ArrayList<Point> points = new ArrayList<Point>();
            
            if(deff==true){     // ak nie je definovy okraj dorob ho LC1  

               double BodA[] = {0,0,Z};  Bodyinterne.add(BodA);
               double BodB[] = {A,0,Z};  Bodyinterne.add(BodB);
               double BodC[] = {0,0,-Z};  Bodyinterne.add(BodC);
               double BodD[] = {A,0,-Z};  Bodyinterne.add(BodD);
               
              
            }
            
            for (int cl2 = 0; cl2 < Bodyinterne.size(); cl2++) //vytvorime arraylist of points      
        {
            
            //DPoint BOD = new DPoint(body.get(cl2)[0], body.get(cl2)[1], body.get(cl2)[2]);
             //      BOD = help.CorToLC(LCcoordinates, LCcoordinates, BOD)
            int x =  (int)  Bodyinterne.get(cl2)[0] *100; // bdy su double ale su v LC takže hodnoty vynasobime 100
            int z =  (int)  Bodyinterne.get(cl2)[2] *100; // prejdeme na cm v integery 
            Point e = new Point(x, z);
            points.add(cl2, e);
        }
         
            
            QuickHull qh = new QuickHull();
            ArrayList<Point> p = qh.quickHull(points);
           
            
            for(int cl3=0;cl3< p.size();cl3++){
                
                int X = p.get(cl3).x;    
                int Y = (int) Bodyinterne.get(najdiVysku(X,Bodyinterne))[1]*100;
                int Z = p.get(cl3).y;
                
                double XX = (double) X;
                double YY = (double) Y;
                double ZZ = (double) Z;
        
                 DPoint c1 = new DPoint(XX/100, ZZ/100, YY/100+vyskaLC_Y ); // vytvor bod 1 xyz
                 
                 if(cl3+1 == p.size()){
                 
                 cl3 = -1; // ak už je c13 dlhe ako dlžka matice bov tak sa vrat na nulu( prvy bod spoji s poslednym ) a tym sa uzavrie edge
                 X = p.get(cl3+1).x;    
                 Y = (int) Bodyinterne.get(najdiVysku(X, Bodyinterne))[1]*100; // najdi pod X hodnotu Y
                 Z = p.get(cl3+1).y;
                
                 XX = (double) X; // prehod na duble
                 YY = (double) Y;
                 ZZ = (double) Z;
                 
                 DPoint c2 = new DPoint(XX/100, ZZ/100, YY/100+vyskaLC_Y ); // vytvor bod 2 xyz 
                 edges.add(new DEdge(c1,c2));
                 if(cl3==-1){
                     break ;
                 }}
            }
             
            
      
            
            //https://gis.stackexchange.com/questions/5426/finding-boundary-co-ordinates-from-given-set-of-point-co-ordinates
        
            
        
        //vlozenie bodov z arrylist bod  auprava podla LC 
        for(int cl1=0; cl1<Bodyinterne.size();cl1++){
          DPoint c1 = new DPoint(Bodyinterne.get(cl1)[0],Bodyinterne.get(cl1)[2], Bodyinterne.get(cl1)[1]+vyskaLC_Y ); // vytvor bod 1 xzy
          mesh.addPoint(c1);
        }
        // ak nie su žiadne body a je deff rvna plocha vlož jeden bod do stredu plochy
//        if (body.size() == 0){
//            
//          DPoint c1 = new DPoint(A/2+LCcoordinates[0],0+LCcoordinates[2], LCcoordinates[1]); // vytvor bod 1 xzy
//          mesh.addPoint(c1);
//            
//        }
        
        
//        help.printl(String.valueOf("je poratane?              " + mesh.isMeshComputed()), true); // je poratane čo ma byt ?
//        help.printl(String.valueOf("pocet constrain edges     " + edges.size()), true); // je poratane čo ma byt ?
//        help.printl(String.valueOf("pocet bodov body          " + body.size()), true); // je poratane čo ma byt ?
//        help.printl(String.valueOf("pocet bodov mesh          " + mesh.getPoints().size()), true); // je poratane čo ma byt ?
//        for(int cl2= 0; cl2< body.size();cl2++){
//        help.printl(String.valueOf(body.get(cl2)[0]),true);
//        }
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
        
     //   TESTER
 //       help.printl(String.valueOf("je poratane?           "  + mesh.isMeshComputed()), true); // je poratane čo ma byt ?
 //      help.printl(String.valueOf("pocet constrain edges  " + mesh.getConstraintEdges().size()), true); // je poratane čo ma byt ?
 //      help.printl(String.valueOf("pocet bodov            " + mesh.getPoints().size()), true); // je poratane čo ma byt ?
        help.printl(String.valueOf("pocet trojuholnikov    " + mesh.getTriangleList().size()), true); // je poratane čo ma byt ?
 //      //  TESTER SECTION
        
        results=(ArrayList<DTriangle>) mesh.getTriangleList();
        
 
//              if(deff == true){ // ak deff je true tam musi potom odstranit pridane body on ich tam zas doda ak sa pregeneruje deff true
//       Bodyinterne.remove(Bodyinterne.get(Bodyinterne.size()-1));
//      Bodyinterne.remove(Bodyinterne.get(Bodyinterne.size()-1));
//        Bodyinterne.remove(Bodyinterne.get(Bodyinterne.size()-1));
//        Bodyinterne.remove(Bodyinterne.get(Bodyinterne.size()-1));
//    }
        
    }

    public  boolean isIsMeshCalculated() {
        return IsMeshCalculated;
    }

    public  void setIsMeshCalculated(boolean IsMeshCalculated) {
         this.IsMeshCalculated = IsMeshCalculated;
    }
    
    
    
    public  void clearResults(){        
        results.clear();
    }

    public  double getZ() {
        return Z;
    }

    public  void setZ(double Z) {
        this.Z = Z;
    }

    public  int getDN() {
        return DN;
    }

    public  void setDN(int DN) {
         this.DN = DN;
    }

    public  double[] getLCcoordinates() {
        return LCcoordinates;
    }

    public  void setLCcoordinates(double[] LCcoordinates) {
         this.LCcoordinates = LCcoordinates;
    }

    public  ArrayList<double[]> getBody() {
        return Body;
    }

    public  void setBody(ArrayList<double[]> body) {
         this.Body = body;
    }

    public  boolean isDeff() {
        return deff;
    }

    public  void setDeff(boolean deff) {
         this.deff = deff;
    }

    public  ArrayList<DTriangle> getResults() {
        return results;
    }
    /**
     * 
     * @return points of triangulation
     */
    public  List<DPoint> getResultsPoinList() {
        return resultsPoint;
    }
     /**
      * is Is mesh Calculated = true return triangles else results clear and return nullpointer;
      * @return 
      */
     public  ArrayList<DTriangle> getTriangles() {
        if(IsMeshCalculated == true){
        return results;    
        } 
        results.clear();
        return results;
        
    }
     /**
      * 
      * @return number of  points from triangulation 
      */
     public  int getNumberOfPoints() {
        if(IsMeshCalculated == true){
            return numberOfPoints;
        } 
        ;
        return 0;
        
    }

     
     
    private  int najdiVysku(int X, ArrayList<double[]> body){
        
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

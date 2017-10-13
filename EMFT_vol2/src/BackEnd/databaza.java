/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.util.ArrayList;
import org.jdelaunay.delaunay.geometries.DTriangle;

/**
 *
 * @author Jozef
 */
public class databaza {
 
    //funkcie vytiahne z databazy sublist podla jednej premenej X Y Z  jeddnodusi arrylist
    // viacej vyšok vyvory sa viacej databaz
    
    //E_B core units
   
    private Observer[] P1D ; // array observerov
    private Observer[][] P2D ; // arraylist observerov
    private Observer[][][] P3D ; // arraylist observerov

    
    public databaza(Observer[] P1D) {
        this.P1D= P1D;
    }
    
    public databaza(Observer[][] P2D) {
        this.P2D= P2D;
    }
    public databaza(Observer[][][] P3D) {
        this.P3D= P3D;
    }

    public Observer[][][] getP3D() {
        return P3D;
    }

    public void setP3D(Observer[][][] P3D) {
        this.P3D = P3D;
    }

    public Observer[][] getP2D() {
        return P2D;
    }

    public void setP2D(Observer[][] P2D) {
        this.P2D = P2D;
    }

    public Observer[] getP1D() {
        return P1D;
    }

    public void setP1D(Observer[] P1D) {
        this.P1D = P1D;
    }
    
    public Observer get_observer(int X){
        return P1D[X];
    }
    
    public void set_observer(Observer P, int X){
         this.P1D[X] = P;
    }
    /**
     * v mojich koordinatoch 
     * @param X
     * @param Z
     * @return  vrati pozorovatela
     */
    public Observer get_observer(int X,int Z){
        return P2D[X][Z];
    }
    
    public void set_observer(Observer P, int X,int Z){
         this.P2D[X][Z] = P;
    }
    /**
     * 
     * @param X X sova hodnota moje surky
     * @param Z Z sova hodnota moje surky
     * @param Y Y sova hodnota moje surky vyska nad terenom ak pocitam vo viacerzch vyskach
     * @return 
     */
    public Observer get_observer(int X,int Z,int Y){
        return P3D[X][Y][Z];
    }
     /**
     * 
     * @param X X sova hodnota moje surky
     * @param Z Z sova hodnota moje surky
     * @param Y Y sova hodnota moje surky vyska nad terenom ak pocitam vo viacerzch vyskach
     * @return 
     */
    public void set_observer(Observer P, int X,int Z,int Y){
         this.P3D[X][Y][Z] = P;
    }
    
    
    
    
}

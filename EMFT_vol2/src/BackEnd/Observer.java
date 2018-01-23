/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.jdelaunay.delaunay.geometries.DPoint;

/**
 * class ktora obsahuje všetky informacie o jednom bude pozorovatela
 * neskor tu možme dorobit detekciu limitov
 * @author Jozef
 */
public class Observer {
    
    private RealMatrix  GeoMatrix_A;
    private FazorVektor B;
    private FazorVektor E;
    private DPoint poloha;
    /**
     * bod pozorovatela zo všetkym čo tam ma byt
     * @param B indukcia mag pola ako fazorvektor TESLA
     * @param E intenzita el pola ako fazorvektor V/m
     * @param poloha polohovy vektor 
     */
    public Observer(FazorVektor B, FazorVektor E, DPoint poloha) {
        this.B = B;
        this.E = E;
        this.poloha = poloha;
    }
/**
     * bod pozorovatela zo všetkym čo tam ma byt
     * @param B indukcia mag pola ako fazorvektor TESLA
     * @param E intenzita el pola ako fazorvektor V/m
     * @param poloha polohovy vektor 
     * @param GeoMatrix_A matica geometrických koeficientov 
     * 
     */
    public Observer(FazorVektor B, FazorVektor E, DPoint poloha,double[][] GeoMatrix_A) {
        this.B = B;
        this.E = E;
        this.poloha = poloha;
        this.GeoMatrix_A = MatrixUtils.createRealMatrix(GeoMatrix_A);
    }
    
    public FazorVektor getB() {
        return B;
    }

    public void setB(FazorVektor B) {
        this.B = B;
    }

    public FazorVektor getE() {
        return E;
    }

    public void setE(FazorVektor E) {
        this.E = E;
    }

    public DPoint getPoloha() {
        return poloha;
    }

    public void setPoloha(DPoint poloha) {
        this.poloha = poloha;
    }
     /**
      * sppricitaj observera ku observerovi
      * @param X 
      */
     public void merge(Observer X) {
        this.B.AddToFazorVektor(X.getB());
        this.E.AddToFazorVektor(X.getE());
        this.GeoMatrix_A.add(X.getGeoMatrix_A()) ; 
    }

    public RealMatrix getGeoMatrix_A() {
        return GeoMatrix_A;
    }

    public void setGeoMatrix_A(RealMatrix GeoMatrix_A) {
        this.GeoMatrix_A = GeoMatrix_A;
    }
    
    
    
}

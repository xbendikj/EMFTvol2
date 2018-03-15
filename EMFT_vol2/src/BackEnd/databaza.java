/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.util.ArrayList;
import static jdk.nashorn.internal.objects.NativeMath.round;
import org.jdelaunay.delaunay.error.DelaunayError;
import org.jdelaunay.delaunay.geometries.DTriangle;
import tools.help;

/**
 *
 * @author Jozef
 */
public class databaza {

    //E_B core units
    private ArrayList<Observer[]> P1D_priecne = new ArrayList<>();  // array observerov
    private ArrayList<Observer[]> P1D_pozdlzne = new ArrayList<>(); // array observerov
    private ArrayList<Observer[]> P1D_neurcite = new ArrayList<>(); // array observerov

    private ArrayList<Observer[]> P1D_parameter = new ArrayList<>(); // array observerov

    private ArrayList<Observer[]> P2D_hor = new ArrayList<>();       // arraylist observerov
    private ArrayList<Observer[]> P2D_vert = new ArrayList<>();      // arraylist observerov

    private ArrayList<Observer[]> P1D_parameter_ELMPAM = new ArrayList<>();  // array observerov

    // do buducna to by sa hodilo aby databaza uchovavala aj informacie .. kolko lan ake lana proste všetko orezatovke a podobne 
    //P ARRAYLISTE
    //P1D - 0 - priecne 
    //P1D - 1 - pozdlzne
    //P1D - 2 - neurcite
    //P1D - 3 - parameter
    //P1D - 4 - parameter ELPAM
    public databaza() {

    }

    /**
     * kazdy vrstva jedna vzska
     *
     * @param X observer
     * @param Y typ mapovania
     */
    public void addToList1D(Observer[] X, int Y) {

        if (Y == 0) {
            P1D_priecne.add(X);
        }
        if (Y == 1) {
            P1D_pozdlzne.add(X);
        }
        if (Y == 2) {
            P1D_neurcite.add(X);
        }
        if (Y == 3) {
            P1D_parameter.add(X);
        }
        if (Y == 4) {
            P1D_parameter_ELMPAM.add(X);
        }

    }

    /**
     * kazda vrstva jeden pozdlny rez
     *
     * @param X observer
     * @param Y poradove cislo 0 horizontal 1 vertikal
     *
     */
    public void addToList2D(Observer[] X, int Y) {

        if (Y == 0) {
            P2D_hor.add(X);
        }
        if (Y == 1) {
            P2D_vert.add(X);
        }
    }

    public ArrayList<Observer[]> getP2D_hor() {
        return P2D_hor;
    }

    /**
     * Scitaj databazu s databazou
     *
     * @param X
     */
    public void scitanie(databaza X) {

        for (int cl1 = 0; cl1 < P1D_priecne.size(); cl1++) {
            for (int cl2 = 0; cl2 < P1D_priecne.get(cl1).length; cl2++) {

                P1D_priecne.get(cl1)[cl2].merge(X.getP1D_priecne().get(cl1)[cl2]);

            }
        }

        for (int cl1 = 0; cl1 < P1D_pozdlzne.size(); cl1++) {
            for (int cl2 = 0; cl2 < P1D_pozdlzne.get(cl1).length; cl2++) {

                P1D_pozdlzne.get(cl1)[cl2].merge(X.getP1D_pozdlzne().get(cl1)[cl2]);

            }
        }

        for (int cl1 = 0; cl1 < P1D_neurcite.size(); cl1++) {
            for (int cl2 = 0; cl2 < P1D_neurcite.get(cl1).length; cl2++) {

                P1D_neurcite.get(cl1)[cl2].merge(X.getP1D_neurcite().get(cl1)[cl2]);

            }
        }

        for (int cl1 = 0; cl1 < P1D_parameter.size(); cl1++) {
            for (int cl2 = 0; cl2 < P1D_parameter.get(cl1).length; cl2++) {

                P1D_parameter.get(cl1)[cl2].merge(X.getP1D_parameter().get(cl1)[cl2]);

            }
        }

        for (int cl1 = 0; cl1 < P2D_hor.size(); cl1++) {
            for (int cl2 = 0; cl2 < P2D_hor.get(cl1).length; cl2++) {

                P2D_hor.get(cl1)[cl2].merge(X.getP2D_hor().get(cl1)[cl2]);

            }
        }

        for (int cl1 = 0; cl1 < P2D_vert.size(); cl1++) {
            for (int cl2 = 0; cl2 < P2D_vert.get(cl1).length; cl2++) {

                P2D_vert.get(cl1)[cl2].merge(X.getP2D_vert().get(cl1)[cl2]);

            }
        }

    }

    public void scitanieAndCalcIEmod(databaza X, boolean calcI, boolean calcE, double KE_I, double KB_I, double KE_Emod, double KB_Emod, double epsi0, double epsi1, double sigma0, double f) {

        for (int cl1 = 0; cl1 < P1D_priecne.size(); cl1++) {
            for (int cl2 = 0; cl2 < P1D_priecne.get(cl1).length; cl2++) {

                P1D_priecne.get(cl1)[cl2].merge(X.getP1D_priecne().get(cl1)[cl2]);
                if (calcI == true) {
                    P1D_priecne.get(cl1)[cl2].calculateI(KE_I, KB_I, epsi0, epsi1, sigma0, f);
                }
                if (calcE == true) {
                    P1D_priecne.get(cl1)[cl2].calculateEmod(KE_Emod, KB_Emod, epsi0, epsi1, sigma0, f);
                }
            }
        }

        for (int cl1 = 0; cl1 < P1D_pozdlzne.size(); cl1++) {
            for (int cl2 = 0; cl2 < P1D_pozdlzne.get(cl1).length; cl2++) {

                P1D_pozdlzne.get(cl1)[cl2].merge(X.getP1D_pozdlzne().get(cl1)[cl2]);
                if (calcI == true) {
                    P1D_pozdlzne.get(cl1)[cl2].calculateI(KE_I, KB_I, epsi0, epsi1, sigma0, f);
                }
                if (calcE == true) {
                    P1D_pozdlzne.get(cl1)[cl2].calculateEmod(KE_Emod, KB_Emod, epsi0, epsi1, sigma0, f);
                }
            }
        }

        for (int cl1 = 0; cl1 < P1D_neurcite.size(); cl1++) {
            for (int cl2 = 0; cl2 < P1D_neurcite.get(cl1).length; cl2++) {

                P1D_neurcite.get(cl1)[cl2].merge(X.getP1D_neurcite().get(cl1)[cl2]);
                if (calcI == true) {
                    P1D_neurcite.get(cl1)[cl2].calculateI(KE_I, KB_I, epsi0, epsi1, sigma0, f);
                }
                if (calcE == true) {
                    P1D_neurcite.get(cl1)[cl2].calculateEmod(KE_Emod, KB_Emod, epsi0, epsi1, sigma0, f);
                }

            }
        }

        for (int cl1 = 0; cl1 < P1D_parameter.size(); cl1++) {
            for (int cl2 = 0; cl2 < P1D_parameter.get(cl1).length; cl2++) {

                P1D_parameter.get(cl1)[cl2].merge(X.getP1D_parameter().get(cl1)[cl2]);
                if (calcI == true) {
                    P1D_parameter.get(cl1)[cl2].calculateI(KE_I, KB_I, epsi0, epsi1, sigma0, f);
                }
                if (calcE == true) {
                    P1D_parameter.get(cl1)[cl2].calculateEmod(KE_Emod, KB_Emod, epsi0, epsi1, sigma0, f);
                }

            }
        }

        for (int cl1 = 0; cl1 < P2D_hor.size(); cl1++) {
            for (int cl2 = 0; cl2 < P2D_hor.get(cl1).length; cl2++) {

                P2D_hor.get(cl1)[cl2].merge(X.getP2D_hor().get(cl1)[cl2]);
                if (calcI == true) {
                    P2D_hor.get(cl1)[cl2].calculateI(KE_I, KB_I, epsi0, epsi1, sigma0, f);
                }
                if (calcE == true) {
                    P2D_hor.get(cl1)[cl2].calculateEmod(KE_Emod, KB_Emod, epsi0, epsi1, sigma0, f);
                }

            }
        }

        for (int cl1 = 0; cl1 < P2D_vert.size(); cl1++) {
            for (int cl2 = 0; cl2 < P2D_vert.get(cl1).length; cl2++) {

                P2D_vert.get(cl1)[cl2].merge(X.getP2D_vert().get(cl1)[cl2]);
                if (calcI == true) {
                    P2D_vert.get(cl1)[cl2].calculateI(KE_I, KB_I, epsi0, epsi1, sigma0, f);
                }
                if (calcE == true) {
                    P2D_vert.get(cl1)[cl2].calculateEmod(KE_Emod, KB_Emod, epsi0, epsi1, sigma0, f);
                }

            }
        }

    }

    public ArrayList<Observer[]> getP2D_vert() {
        return P2D_vert;
    }

    /**
     * vrati specifickeho observera 1D databaza
     *
     * @param X poradove cislo v daabaze
     * @param Y typ mapovania //P ARRAYLISTE //P1D - 0 - priecne //P1D - 1 -
     * pozdlzne //P1D - 2 - neurcite //P1D - 3 - parameter //P1D - 3 -
     * parameter_ELPAM
     * @return
     */
    public Observer[] getFromList1D(int X, int Y) {
        Observer[] vystup = null;

        if (Y == 0) {
            vystup = P1D_priecne.get(X);
        }
        if (Y == 1) {
            vystup = P1D_pozdlzne.get(X);
        }
        if (Y == 2) {
            vystup = P1D_neurcite.get(X);
        }
        if (Y == 3) {
            vystup = P1D_parameter.get(X);
        }
        if (Y == 4) {
            vystup = P1D_parameter_ELMPAM.get(X);
        }

        return vystup;
    }

    /**
     * vrati specifickeho observera 2D databaza
     *
     * @param X poradove cislo v daabaze
     * @return
     */
    public Observer[] getFromList2D(int X, int Y) {
        Observer[] vystup = null;

        if (Y == 0) {
            vystup = P2D_hor.get(X);
        }
        if (Y == 1) {
            vystup = P2D_vert.get(X);
        }

        return vystup;
    }

    /**
     * @param BE = "B" "E" "I" "Emod" "R0" "R1" "R2" "L0" "L1" "L2" "C0" "C1"
     * "C2"
     * @param rozmer "RMS B" "RMS B REAL" "RMS B IMAGE" "RMS B ANGLE" "RMS B X"
     * "ABS B X" "PHASE B X" "REAL B X" "IMAGE B X" ROZNAKO PRE Y a Z a tiez pre
     * E I a Emod
     *
     * Ak ELMAP veliciny zadaj system cislo systemu 1 2 3 ...
     * @param X Observer z ktoreho zobrat data
     * @return vrati yray žiadanej veličiny je nutn dpbre zadat "B" "E" "I"
     * "Emod" a potom čo z daleho vektora observerov chcem z nej vyextrahovat
     * nevracia geometricke vektory
     */
    public double[] getYray1D(String BE, String rozmer, Observer[] X) {
        double[] yray = new double[X.length];

        for (int cl1 = 0; cl1 < X.length; cl1++) {
            if (BE == "B") {

                if (rozmer.equals("RMS B")) {
                    yray[cl1] = X[cl1].getB().getComplex_rms_ABS();
                }
                if (rozmer.equals("RMS B REAL")) {
                    yray[cl1] = X[cl1].getB().getComplex_rms().getReal();
                }
                if (rozmer.equals("RMS B IMAGE")) {
                    yray[cl1] = X[cl1].getB().getComplex_rms().getImaginary();
                }
                if (rozmer.equals("RMS B ANGLE")) {
                    yray[cl1] = X[cl1].getB().getComplex_rms_Angle();
                }

                if (rozmer.equals("RMS B X")) {
                    yray[cl1] = X[cl1].getB().getX_ABS();
                }
                if (rozmer.equals("PHASE B X")) {
                    yray[cl1] = X[cl1].getB().getX_Angle();
                }
                if (rozmer.equals("REAL B X")) {
                    yray[cl1] = X[cl1].getB().getX_Real();
                }
                if (rozmer.equals("IMAGE B X")) {
                    yray[cl1] = X[cl1].getB().getX_Imaginary();
                }

                if (rozmer.equals("RMS B Y")) {
                    yray[cl1] = X[cl1].getB().getY_ABS();
                }
                if (rozmer.equals("PHASE B Y")) {
                    yray[cl1] = X[cl1].getB().getY_Angle();
                }
                if (rozmer.equals("REAL B Y")) {
                    yray[cl1] = X[cl1].getB().getY_Real();
                }
                if (rozmer.equals("IMAGE B Y")) {
                    yray[cl1] = X[cl1].getB().getY_Imaginary();
                }

                if (rozmer.equals("RMS B Z")) {
                    yray[cl1] = X[cl1].getB().getZ_ABS();
                }
                if (rozmer.equals("PHASE B Z")) {
                    yray[cl1] = X[cl1].getB().getZ_Angle();
                }
                if (rozmer.equals("REAL B Z")) {
                    yray[cl1] = X[cl1].getB().getZ_Real();
                }
                if (rozmer.equals("IMAGE B Z")) {
                    yray[cl1] = X[cl1].getB().getZ_Imaginary();
                }

            }
            if (BE == "E") {

                if (rozmer.equals("RMS E")) {
                    yray[cl1] = X[cl1].getE().getComplex_rms_ABS();
                }
                if (rozmer.equals("RMS E REAL")) {
                    yray[cl1] = X[cl1].getE().getComplex_rms().getReal();
                }
                if (rozmer.equals("RMS E IMAGE")) {
                    yray[cl1] = X[cl1].getE().getComplex_rms().getImaginary();
                }
                if (rozmer.equals("RMS E ANGLE")) {
                    yray[cl1] = X[cl1].getE().getComplex_rms_Angle();
                }

                if (rozmer.equals("RMS E X")) {
                    yray[cl1] = X[cl1].getE().getX_ABS();
                }
                if (rozmer.equals("PHASE E X")) {
                    yray[cl1] = X[cl1].getE().getX_Angle();
                }
                if (rozmer.equals("REAL E X")) {
                    yray[cl1] = X[cl1].getE().getX_Real();
                }
                if (rozmer.equals("IMAGE E X")) {
                    yray[cl1] = X[cl1].getE().getX_Imaginary();
                }

                if (rozmer.equals("RMS E Y")) {
                    yray[cl1] = X[cl1].getE().getY_ABS();
                }
                if (rozmer.equals("PHASE E Y")) {
                    yray[cl1] = X[cl1].getE().getY_Angle();
                }
                if (rozmer.equals("REAL E Y")) {
                    yray[cl1] = X[cl1].getE().getY_Real();
                }
                if (rozmer.equals("IMAGE E Y")) {
                    yray[cl1] = X[cl1].getE().getY_Imaginary();
                }

                if (rozmer.equals("RMS E Z")) {
                    yray[cl1] = X[cl1].getE().getZ_ABS();
                }
                if (rozmer.equals("PHASE E Z")) {
                    yray[cl1] = X[cl1].getE().getZ_Angle();
                }
                if (rozmer.equals("REAL E Z")) {
                    yray[cl1] = X[cl1].getE().getZ_Real();
                }
                if (rozmer.equals("IMAGE E Z")) {
                    yray[cl1] = X[cl1].getE().getZ_Imaginary();
                }

            }

            if (BE == "I") {

                if (rozmer.equals("RMS I")) {
                    yray[cl1] = X[cl1].getI().getComplex_rms_ABS();
                }
                if (rozmer.equals("RMS I REAL")) {
                    yray[cl1] = X[cl1].getI().getComplex_rms().getReal();
                }
                if (rozmer.equals("RMS I IMAGE")) {
                    yray[cl1] = X[cl1].getI().getComplex_rms().getImaginary();
                }
                if (rozmer.equals("RMS I ANGLE")) {
                    yray[cl1] = X[cl1].getI().getComplex_rms_Angle();
                }

                if (rozmer.equals("RMS I X")) {
                    yray[cl1] = X[cl1].getI().getX_ABS();
                }
                if (rozmer.equals("PHASE I X")) {
                    yray[cl1] = X[cl1].getI().getX_Angle();
                }
                if (rozmer.equals("REAL I X")) {
                    yray[cl1] = X[cl1].getI().getX_Real();
                }
                if (rozmer.equals("IMAGE I X")) {
                    yray[cl1] = X[cl1].getI().getX_Imaginary();
                }

                if (rozmer.equals("RMS I Y")) {
                    yray[cl1] = X[cl1].getI().getY_ABS();
                }
                if (rozmer.equals("PHASE I Y")) {
                    yray[cl1] = X[cl1].getI().getY_Angle();
                }
                if (rozmer.equals("REAL I Y")) {
                    yray[cl1] = X[cl1].getI().getY_Real();
                }
                if (rozmer.equals("IMAGE I Y")) {
                    yray[cl1] = X[cl1].getI().getY_Imaginary();
                }

                if (rozmer.equals("RMS I Z")) {
                    yray[cl1] = X[cl1].getI().getZ_ABS();
                }
                if (rozmer.equals("PHASE I Z")) {
                    yray[cl1] = X[cl1].getI().getZ_Angle();
                }
                if (rozmer.equals("REAL I Z")) {
                    yray[cl1] = X[cl1].getI().getZ_Real();
                }
                if (rozmer.equals("IMAGE I Z")) {
                    yray[cl1] = X[cl1].getI().getZ_Imaginary();
                }

            }
            if (BE == "Emod") {

                if (rozmer.equals("RMS Emod")) {
                    yray[cl1] = X[cl1].getEmod().getComplex_rms_ABS();
                }
                if (rozmer.equals("RMS Emod REAL")) {
                    yray[cl1] = X[cl1].getEmod().getComplex_rms().getReal();
                }
                if (rozmer.equals("RMS Emod IMAGE")) {
                    yray[cl1] = X[cl1].getEmod().getComplex_rms().getImaginary();
                }
                if (rozmer.equals("RMS Emod ANGLE")) {
                    yray[cl1] = X[cl1].getEmod().getComplex_rms_Angle();
                }

                if (rozmer.equals("RMS Emod X")) {
                    yray[cl1] = X[cl1].getEmod().getX_ABS();
                }
                if (rozmer.equals("PHASE Emod X")) {
                    yray[cl1] = X[cl1].getEmod().getX_Angle();
                }
                if (rozmer.equals("REAL Emod X")) {
                    yray[cl1] = X[cl1].getEmod().getX_Real();
                }
                if (rozmer.equals("IMAGE Emod X")) {
                    yray[cl1] = X[cl1].getEmod().getX_Imaginary();
                }

                if (rozmer.equals("RMS Emod Y")) {
                    yray[cl1] = X[cl1].getEmod().getY_ABS();
                }
                if (rozmer.equals("PHASE Emod Y")) {
                    yray[cl1] = X[cl1].getEmod().getY_Angle();
                }
                if (rozmer.equals("REAL Emod Y")) {
                    yray[cl1] = X[cl1].getEmod().getY_Real();
                }
                if (rozmer.equals("IMAGE Emod Y")) {
                    yray[cl1] = X[cl1].getEmod().getY_Imaginary();
                }

                if (rozmer.equals("RMS Emod Z")) {
                    yray[cl1] = X[cl1].getEmod().getZ_ABS();
                }
                if (rozmer.equals("PHASE Emod Z")) {
                    yray[cl1] = X[cl1].getEmod().getZ_Angle();
                }
                if (rozmer.equals("REAL Emod Z")) {
                    yray[cl1] = X[cl1].getEmod().getZ_Real();
                }
                if (rozmer.equals("IMAGE Emod Z")) {
                    yray[cl1] = X[cl1].getEmod().getZ_Imaginary();
                }

            }
            if (BE == "R0") {
                yray[cl1] = X[cl1].getR0_ELPAM()[Integer.parseInt(rozmer) - 1];
            }
            if (BE == "R1") {
                yray[cl1] = X[cl1].getR1_ELPAM()[Integer.parseInt(rozmer) - 1];
            }
            if (BE == "R2") {
                yray[cl1] = X[cl1].getR2_ELPAM()[Integer.parseInt(rozmer) - 1];
            }
            if (BE == "L0") {
                yray[cl1] = X[cl1].getL0_ELPAM()[Integer.parseInt(rozmer) - 1];
            }
            if (BE == "L1") {
                yray[cl1] = X[cl1].getL1_ELPAM()[Integer.parseInt(rozmer) - 1];
            }
            if (BE == "L2") {
                yray[cl1] = X[cl1].getL2_ELPAM()[Integer.parseInt(rozmer) - 1];
            }
            if (BE == "C0") {
                yray[cl1] = X[cl1].getC0_ELPAM()[Integer.parseInt(rozmer) - 1];
            }
            if (BE == "C1") {
                yray[cl1] = X[cl1].getC1_ELPAM()[Integer.parseInt(rozmer) - 1];
            }
            if (BE == "C2") {
                yray[cl1] = X[cl1].getC2_ELPAM()[Integer.parseInt(rozmer) - 1];
            }

        }

        return yray;
    }

    /**
     *
     * @param rozmer "RMS B" "RMS B REAL" "RMS B IMAGE" "RMS B ANGLE" "RMS B X"
     * "ABS B X" "PHASE B X" "REAL B X" "IMAGE B X" ROZNAKO PRE Y a Z a tiez pre
     * E
     * @param X Observer z ktoreho zobrat data
     * @return vrati yray žiadanej veličiny je nutn dpbre zadat "B" "E" a potom
     * čo z daleho vektora observerov chcem z nej vyextrahovat nevracia
     * geometricke vektory
     */
    public double[] getZMAT2D(String BE, String rozmer, ArrayList<Observer[]> X) {
        double[] yray = new double[X.size() * X.get(0).length];
        int iterator = 0;
        for (int cl2 = 0; cl2 < X.get(0).length; cl2++) {  // cyklus pozdlzniych
            for (int cl1 = 0; cl1 < X.size(); cl1++) {   //  cykls hosnot v jednotlivych pozdtlzniych
                if (BE == "B") {

                    if (rozmer.equals("RMS B")) {
                        yray[iterator] = X.get(cl1)[cl2].getB().getComplex_rms_ABS();
                    }
                    if (rozmer.equals("RMS B REAL")) {
                        yray[iterator] = X.get(cl1)[cl2].getB().getComplex_rms().getReal();
                    }
                    if (rozmer.equals("RMS B IMAGE")) {
                        yray[iterator] = X.get(cl1)[cl2].getB().getComplex_rms().getImaginary();
                    }
                    if (rozmer.equals("RMS B ANGLE")) {
                        yray[iterator] = X.get(cl1)[cl2].getB().getComplex_rms_Angle();
                    }

                    if (rozmer.equals("RMS B X")) {
                        yray[iterator] = X.get(cl1)[cl2].getB().getX_ABS();
                    }
                    if (rozmer.equals("PHASE B X")) {
                        yray[iterator] = X.get(cl1)[cl2].getB().getX_Angle();
                    }
                    if (rozmer.equals("REAL B X")) {
                        yray[iterator] = X.get(cl1)[cl2].getB().getX_Real();
                    }
                    if (rozmer.equals("IMAGE B X")) {
                        yray[iterator] = X.get(cl1)[cl2].getB().getX_Imaginary();
                    }

                    if (rozmer.equals("RMS B Y")) {
                        yray[iterator] = X.get(cl1)[cl2].getB().getY_ABS();
                    }
                    if (rozmer.equals("PHASE B Y")) {
                        yray[iterator] = X.get(cl1)[cl2].getB().getY_Angle();
                    }
                    if (rozmer.equals("REAL B Y")) {
                        yray[iterator] = X.get(cl1)[cl2].getB().getY_Real();
                    }
                    if (rozmer.equals("IMAGE B Y")) {
                        yray[iterator] = X.get(cl1)[cl2].getB().getY_Imaginary();
                    }

                    if (rozmer.equals("RMS B Z")) {
                        yray[iterator] = X.get(cl1)[cl2].getB().getZ_ABS();
                    }
                    if (rozmer.equals("PHASE B Z")) {
                        yray[iterator] = X.get(cl1)[cl2].getB().getZ_Angle();
                    }
                    if (rozmer.equals("REAL B Z")) {
                        yray[iterator] = X.get(cl1)[cl2].getB().getZ_Real();
                    }
                    if (rozmer.equals("IMAGE B Z")) {
                        yray[iterator] = X.get(cl1)[cl2].getB().getZ_Imaginary();
                    }

                }
                if (BE == "E") {

                    if (rozmer.equals("RMS E")) {
                        yray[iterator] = X.get(cl1)[cl2].getE().getComplex_rms_ABS();
                    }
                    if (rozmer.equals("RMS E REAL")) {
                        yray[iterator] = X.get(cl1)[cl2].getE().getComplex_rms().getReal();
                    }
                    if (rozmer.equals("RMS E IMAGE")) {
                        yray[iterator] = X.get(cl1)[cl2].getE().getComplex_rms().getImaginary();
                    }
                    if (rozmer.equals("RMS E ANGLE")) {
                        yray[iterator] = X.get(cl1)[cl2].getE().getComplex_rms_Angle();
                    }

                    if (rozmer.equals("RMS E X")) {
                        yray[iterator] = X.get(cl1)[cl2].getE().getX_ABS();
                    }
                    if (rozmer.equals("PHASE E X")) {
                        yray[iterator] = X.get(cl1)[cl2].getE().getX_Angle();
                    }
                    if (rozmer.equals("REAL E X")) {
                        yray[iterator] = X.get(cl1)[cl2].getE().getX_Real();
                    }
                    if (rozmer.equals("IMAGE E X")) {
                        yray[iterator] = X.get(cl1)[cl2].getE().getX_Imaginary();
                    }

                    if (rozmer.equals("RMS E Y")) {
                        yray[iterator] = X.get(cl1)[cl2].getE().getY_ABS();
                    }
                    if (rozmer.equals("PHASE E Y")) {
                        yray[iterator] = X.get(cl1)[cl2].getE().getY_Angle();
                    }
                    if (rozmer.equals("REAL E Y")) {
                        yray[iterator] = X.get(cl1)[cl2].getE().getY_Real();
                    }
                    if (rozmer.equals("IMAGE E Y")) {
                        yray[iterator] = X.get(cl1)[cl2].getE().getY_Imaginary();
                    }

                    if (rozmer.equals("RMS E Z")) {
                        yray[iterator] = X.get(cl1)[cl2].getE().getZ_ABS();
                    }
                    if (rozmer.equals("PHASE E Z")) {
                        yray[iterator] = X.get(cl1)[cl2].getE().getZ_Angle();
                    }
                    if (rozmer.equals("REAL E Z")) {
                        yray[iterator] = X.get(cl1)[cl2].getE().getZ_Real();
                    }
                    if (rozmer.equals("IMAGE E Z")) {
                        yray[iterator] = X.get(cl1)[cl2].getE().getZ_Imaginary();
                    }

                }
                if (BE == "I") {

                    if (rozmer.equals("RMS I")) {
                        yray[iterator] = X.get(cl1)[cl2].getI().getComplex_rms_ABS();
                    }
                    if (rozmer.equals("RMS I REAL")) {
                        yray[iterator] = X.get(cl1)[cl2].getI().getComplex_rms().getReal();
                    }
                    if (rozmer.equals("RMS I IMAGE")) {
                        yray[iterator] = X.get(cl1)[cl2].getI().getComplex_rms().getImaginary();
                    }
                    if (rozmer.equals("RMS I ANGLE")) {
                        yray[iterator] = X.get(cl1)[cl2].getI().getComplex_rms_Angle();
                    }

                    if (rozmer.equals("RMS I X")) {
                        yray[iterator] = X.get(cl1)[cl2].getI().getX_ABS();
                    }
                    if (rozmer.equals("PHASE I X")) {
                        yray[iterator] = X.get(cl1)[cl2].getI().getX_Angle();
                    }
                    if (rozmer.equals("REAL I X")) {
                        yray[iterator] = X.get(cl1)[cl2].getI().getX_Real();
                    }
                    if (rozmer.equals("IMAGE I X")) {
                        yray[iterator] = X.get(cl1)[cl2].getI().getX_Imaginary();
                    }

                    if (rozmer.equals("RMS I Y")) {
                        yray[iterator] = X.get(cl1)[cl2].getI().getY_ABS();
                    }
                    if (rozmer.equals("PHASE I Y")) {
                        yray[iterator] = X.get(cl1)[cl2].getI().getY_Angle();
                    }
                    if (rozmer.equals("REAL I Y")) {
                        yray[iterator] = X.get(cl1)[cl2].getI().getY_Real();
                    }
                    if (rozmer.equals("IMAGE I Y")) {
                        yray[iterator] = X.get(cl1)[cl2].getI().getY_Imaginary();
                    }

                    if (rozmer.equals("RMS I Z")) {
                        yray[iterator] = X.get(cl1)[cl2].getI().getZ_ABS();
                    }
                    if (rozmer.equals("PHASE I Z")) {
                        yray[iterator] = X.get(cl1)[cl2].getI().getZ_Angle();
                    }
                    if (rozmer.equals("REAL I Z")) {
                        yray[iterator] = X.get(cl1)[cl2].getI().getZ_Real();
                    }
                    if (rozmer.equals("IMAGE I Z")) {
                        yray[iterator] = X.get(cl1)[cl2].getI().getZ_Imaginary();
                    }

                }

                if (BE == "Emod") {

                    if (rozmer.equals("RMS Emod")) {
                        yray[iterator] = X.get(cl1)[cl2].getEmod().getComplex_rms_ABS();
                    }
                    if (rozmer.equals("RMS Emod REAL")) {
                        yray[iterator] = X.get(cl1)[cl2].getEmod().getComplex_rms().getReal();
                    }
                    if (rozmer.equals("RMS Emod IMAGE")) {
                        yray[iterator] = X.get(cl1)[cl2].getEmod().getComplex_rms().getImaginary();
                    }
                    if (rozmer.equals("RMS Emod ANGLE")) {
                        yray[iterator] = X.get(cl1)[cl2].getEmod().getComplex_rms_Angle();
                    }

                    if (rozmer.equals("RMS Emod X")) {
                        yray[iterator] = X.get(cl1)[cl2].getEmod().getX_ABS();
                    }
                    if (rozmer.equals("PHASE Emod X")) {
                        yray[iterator] = X.get(cl1)[cl2].getEmod().getX_Angle();
                    }
                    if (rozmer.equals("REAL Emod X")) {
                        yray[iterator] = X.get(cl1)[cl2].getEmod().getX_Real();
                    }
                    if (rozmer.equals("IMAGE Emod X")) {
                        yray[iterator] = X.get(cl1)[cl2].getEmod().getX_Imaginary();
                    }

                    if (rozmer.equals("RMS Emod Y")) {
                        yray[iterator] = X.get(cl1)[cl2].getEmod().getY_ABS();
                    }
                    if (rozmer.equals("PHASE Emod Y")) {
                        yray[iterator] = X.get(cl1)[cl2].getEmod().getY_Angle();
                    }
                    if (rozmer.equals("REAL Emod Y")) {
                        yray[iterator] = X.get(cl1)[cl2].getEmod().getY_Real();
                    }
                    if (rozmer.equals("IMAGE Emod Y")) {
                        yray[iterator] = X.get(cl1)[cl2].getEmod().getY_Imaginary();
                    }

                    if (rozmer.equals("RMS Emod Z")) {
                        yray[iterator] = X.get(cl1)[cl2].getEmod().getZ_ABS();
                    }
                    if (rozmer.equals("PHASE Emod Z")) {
                        yray[iterator] = X.get(cl1)[cl2].getEmod().getZ_Angle();
                    }
                    if (rozmer.equals("REAL Emod Z")) {
                        yray[iterator] = X.get(cl1)[cl2].getEmod().getZ_Real();
                    }
                    if (rozmer.equals("IMAGE Emod Z")) {
                        yray[iterator] = X.get(cl1)[cl2].getEmod().getZ_Imaginary();
                    }

                }

                iterator++;
            }
        }

        return yray;
    }

    public double[] getXray1D(String XYZ, Observer[] X) {
        double[] xray = new double[X.length];

        for (int cl1 = 0; cl1 < X.length; cl1++) {

            if (XYZ.equals("X")) {
                xray[cl1] = X[cl1].getPoloha().getX();
            }
            if (XYZ.equals("Y")) {
                xray[cl1] = X[cl1].getPoloha().getY();
            }
            if (XYZ.equals("Z")) {
                xray[cl1] = X[cl1].getPoloha().getZ();
            }
            if (XYZ.equals("P")) {
                xray[cl1] = X[cl1].getParameter();
            }

        }
        return xray;
    }

    /**
     * vytvory XRAY nulty vektor observerov z arraylistu
     *
     * @param XYZ co na zaver chcem ?
     * @param X arraylist observerov
     * @return pre dislin podklad
     */
    public double[] getXray2D(String XYZ, ArrayList<Observer[]> X) {
        double[] xray = new double[X.get(0).length];

        for (int cl1 = 0; cl1 < xray.length; cl1++) {

            if (XYZ.equals("X")) {
                xray[cl1] = X.get(0)[cl1].getPoloha().getX();
            }
            if (XYZ.equals("Y")) {
                xray[cl1] = X.get(0)[cl1].getPoloha().getY();
            }
            if (XYZ.equals("Z")) {
                xray[cl1] = X.get(0)[cl1].getPoloha().getZ();
            }

        }
        return xray;
    }

    /**
     * vytvory YRAY prvy prvok v kazok riadku na osi Z si to treba predstavit
     * prvy prvok kazdeho pozdleneho rezu
     *
     * @param XYZ co na zaver chcem ?
     * @param Y
     * @return pre dislin podklad
     */
    public double[] getYray2D(String XYZ, ArrayList<Observer[]> Y) {
        double[] yray = new double[Y.size()];

        for (int cl1 = 0; cl1 < yray.length; cl1++) {

            if (XYZ.equals("X")) {
                yray[cl1] = Y.get(cl1)[0].getPoloha().getX();
            }
            if (XYZ.equals("Y")) {
                yray[cl1] = Y.get(cl1)[0].getPoloha().getY();
            }
            if (XYZ.equals("Z")) {
                yray[cl1] = Y.get(cl1)[0].getPoloha().getZ();
            }

        }
        return yray;
    }

    public ArrayList<Observer[]> getP1D_parameter() {
        return P1D_parameter;
    }

    public void setP1D_parameter(ArrayList<Observer[]> P1D_parameter) {
        this.P1D_parameter = P1D_parameter;
    }

    public ArrayList<Observer[]> getP1D_parameter_ELMPAM() {
        return P1D_parameter_ELMPAM;
    }

    public void setP1D_parameter_ELMPAM(ArrayList<Observer[]> P1D_parameter_ELMPAM) {
        this.P1D_parameter_ELMPAM = P1D_parameter_ELMPAM;
    }

    public ArrayList<double[]> getYray1DList(String BE, String rozmer, ArrayList<Observer[]> X) {

        ArrayList<double[]> yray1DList = new ArrayList<>();

        for (int cl1 = 0; cl1 < X.size(); cl1++) {

            yray1DList.add(getYray1D(BE, rozmer, X.get(cl1)));

        }

        return yray1DList;

    }

     public ArrayList<double[]> getYray1DList_systemovy(String BE, ArrayList<Observer[]> X,int pocet_systemov) {

        ArrayList<double[]> yray1DList = new ArrayList<>();

        for (int cl1 = 0; cl1 < pocet_systemov; cl1++) {

            yray1DList.add(getYray1D(BE, String.valueOf(cl1+1), X.get(0)));

        }

        return yray1DList;

    }
    
    /**
     * deli arraylist Y pozor rozmer arraylistov musi byt rovnaky
     *
     * @param BE1 "B" "E" "I" "Emod" "R0" "R1" "R2" "L0" "L1" "L2" "C0" "C1"
     * "C2"
     * @param BE2 "B" "E" "I" "Emod" "R0" "R1" "R2" "L0" "L1" "L2" "C0" "C1"
     * "C2"
     * @param rozmer1 rozmer "RMS B" "RMS B REAL" "RMS B IMAGE" "RMS B ANGLE"
     * "RMS B X" "ABS B X" "PHASE B X" "REAL B X" "IMAGE B X" ROZNAKO PRE Y a Z
     * a tiez pre E I a Emod
     *
     * Ak ELMAP veliciny zadaj system cislo systemu "1" "2" "3" ...
     * @param rozmer2
     * @param X1
     * @param X2
     * @param ELPAM = true X1i/X2 ak false X1i/X2i
     * @return
     */
    public ArrayList<double[]> getYray1DListPodiel(String BE1, String BE2, String rozmer1, String rozmer2, ArrayList<Observer[]> X1, ArrayList<Observer[]> X2, boolean ELPAM) {
        ArrayList<double[]> yray1DList = new ArrayList<>();

        if (X1.size() == X2.size()) {

            ArrayList<double[]> yray1DList1 = new ArrayList<>();
            ArrayList<double[]> yray1DList2 = new ArrayList<>();

            for (int cl1 = 0; cl1 < X2.size(); cl1++) {

                yray1DList2.add(getYray1D(BE2, rozmer2, X2.get(cl1)));

            }

            for (int cl1 = 0; cl1 < X1.size(); cl1++) {

                yray1DList1.add(getYray1D(BE1, rozmer1, X1.get(cl1)));

                double[] podiel = new double[yray1DList.get(cl1).length];
                for (int cl2 = 0; cl2 < yray1DList.get(cl1).length; cl2++) {

                    if (ELPAM) {
                        podiel[cl2] = yray1DList.get(cl1)[cl2] / yray1DList2.get(0)[cl2];
                    } else {
                        podiel[cl2] = yray1DList.get(cl1)[cl2] / yray1DList2.get(cl1)[cl2];
                    }

                }
                yray1DList.add(podiel);

            }
        }else{
            System.out.println("nerovnaky rozmer X1 X2 : getYray1DListPodiel");
        }
        return yray1DList;

    }

    public String[] getYray_height_name(ArrayList<Observer[]> X, Tfield pole) throws DelaunayError {
        String[] yray = new String[X.size()];

        for (int cl1 = 0; cl1 < X.size(); cl1++) {
            double Y = pole.getY(X.get(cl1)[0].getPoloha().getX(), X.get(cl1)[0].getPoloha().getZ());
            double Y1 = X.get(cl1)[0].getPoloha().getY();
            yray[cl1] = String.valueOf(help.round(Y1 - Y, 2)) + " m";
        }
        return yray;
    }

    //dorobit funkciu ktory vrati hodnoty pre pozorovatela z observera
    // dorobit funkciu ktory navrsty yray na seba ked je viac vysok
    // otestovat konečne kreslenie a vypocet a graficky vypocet
    // y fukciaonalizovat core B
    // vytvorit core 2D
    public ArrayList<Observer[]> getP1D_priecne() {
        return P1D_priecne;
    }

    public void setP1D_priecne(ArrayList<Observer[]> P1D_priecne) {
        this.P1D_priecne = P1D_priecne;
    }

    public ArrayList<Observer[]> getP1D_pozdlzne() {
        return P1D_pozdlzne;
    }

    public void setP1D_pozdlzne(ArrayList<Observer[]> P1D_pozdlzne) {
        this.P1D_pozdlzne = P1D_pozdlzne;
    }

    public ArrayList<Observer[]> getP1D_neurcite() {
        return P1D_neurcite;
    }

    public void setP1D_neurcite(ArrayList<Observer[]> P1D_neurcite) {
        this.P1D_neurcite = P1D_neurcite;
    }

    /**
     * vyypis vystup je 4rozmerny vektor veličina polus gometricke koordinaty
     * XYZ
     *
     * @param BE string B alebo E
     * @param rozmer rozmer na vypis
     * @param X odkial to brat Arraylist observerov[] hadat hodnotu
     */
    public double[] getMaxVal(String BE, String rozmer, ArrayList<Observer[]> X, boolean Parameter) {
        double[] vystup = new double[5];
        double maxval = 0;
        double val = 0;
        int poloha1 = 0;
        int poloha2 = 0;
        for (int cl1 = 0; cl1 < X.size(); cl1++) {
            for (int cl2 = 0; cl2 < X.get(cl1).length; cl2++) {

                if (BE == "B") {

                    if (rozmer.equals("RMS B")) {
                        val = X.get(cl1)[cl2].getB().getComplex_rms_ABS();
                    }
                    if (rozmer.equals("RMS B REAL")) {
                        val = X.get(cl1)[cl2].getB().getComplex_rms().getReal();
                    }
                    if (rozmer.equals("RMS B IMAGE")) {
                        val = X.get(cl1)[cl2].getB().getComplex_rms().getImaginary();
                    }
                    if (rozmer.equals("RMS B ANGLE")) {
                        val = X.get(cl1)[cl2].getB().getComplex_rms_Angle();
                    }

                    if (rozmer.equals("RMS B X")) {
                        val = X.get(cl1)[cl2].getB().getX_ABS();
                    }
                    if (rozmer.equals("PHASE B X")) {
                        val = X.get(cl1)[cl2].getB().getX_Angle();
                    }
                    if (rozmer.equals("REAL B X")) {
                        val = X.get(cl1)[cl2].getB().getX_Real();
                    }
                    if (rozmer.equals("IMAGE B X")) {
                        val = X.get(cl1)[cl2].getB().getX_Imaginary();
                    }

                    if (rozmer.equals("RMS B Y")) {
                        val = X.get(cl1)[cl2].getB().getY_ABS();
                    }
                    if (rozmer.equals("PHASE B Y")) {
                        val = X.get(cl1)[cl2].getB().getY_Angle();
                    }
                    if (rozmer.equals("REAL B Y")) {
                        val = X.get(cl1)[cl2].getB().getY_Real();
                    }
                    if (rozmer.equals("IMAGE B Y")) {
                        val = X.get(cl1)[cl2].getB().getY_Imaginary();
                    }

                    if (rozmer.equals("RMS B Z")) {
                        val = X.get(cl1)[cl2].getB().getZ_ABS();
                    }
                    if (rozmer.equals("PHASE B Z")) {
                        val = X.get(cl1)[cl2].getB().getZ_Angle();
                    }
                    if (rozmer.equals("REAL B Z")) {
                        val = X.get(cl1)[cl2].getB().getZ_Real();
                    }
                    if (rozmer.equals("IMAGE B Z")) {
                        val = X.get(cl1)[cl2].getB().getZ_Imaginary();
                    }

                }
                if (BE == "E") {

                    if (rozmer.equals("RMS E")) {
                        val = X.get(cl1)[cl2].getE().getComplex_rms_ABS();
                    }
                    if (rozmer.equals("RMS E REAL")) {
                        val = X.get(cl1)[cl2].getE().getComplex_rms().getReal();
                    }
                    if (rozmer.equals("RMS E IMAGE")) {
                        val = X.get(cl1)[cl2].getE().getComplex_rms().getImaginary();
                    }
                    if (rozmer.equals("RMS E ANGLE")) {
                        val = X.get(cl1)[cl2].getE().getComplex_rms_Angle();
                    }

                    if (rozmer.equals("RMS E X")) {
                        val = X.get(cl1)[cl2].getE().getX_ABS();
                    }
                    if (rozmer.equals("PHASE E X")) {
                        val = X.get(cl1)[cl2].getE().getX_Angle();
                    }
                    if (rozmer.equals("REAL E X")) {
                        val = X.get(cl1)[cl2].getE().getX_Real();
                    }
                    if (rozmer.equals("IMAGE E X")) {
                        val = X.get(cl1)[cl2].getE().getX_Imaginary();
                    }

                    if (rozmer.equals("RMS E Y")) {
                        val = X.get(cl1)[cl2].getE().getY_ABS();
                    }
                    if (rozmer.equals("PHASE E Y")) {
                        val = X.get(cl1)[cl2].getE().getY_Angle();
                    }
                    if (rozmer.equals("REAL E Y")) {
                        val = X.get(cl1)[cl2].getE().getY_Real();
                    }
                    if (rozmer.equals("IMAGE E Y")) {
                        val = X.get(cl1)[cl2].getE().getY_Imaginary();
                    }

                    if (rozmer.equals("RMS E Z")) {
                        val = X.get(cl1)[cl2].getE().getZ_ABS();
                    }
                    if (rozmer.equals("PHASE E Z")) {
                        val = X.get(cl1)[cl2].getE().getZ_Angle();
                    }
                    if (rozmer.equals("REAL E Z")) {
                        val = X.get(cl1)[cl2].getE().getZ_Real();
                    }
                    if (rozmer.equals("IMAGE E Z")) {
                        val = X.get(cl1)[cl2].getE().getZ_Imaginary();
                    }

                }
                if (BE == "I") {

                    if (rozmer.equals("RMS I")) {
                        val = X.get(cl1)[cl2].getI().getComplex_rms_ABS();
                    }
                    if (rozmer.equals("RMS I REAL")) {
                        val = X.get(cl1)[cl2].getI().getComplex_rms().getReal();
                    }
                    if (rozmer.equals("RMS I IMAGE")) {
                        val = X.get(cl1)[cl2].getI().getComplex_rms().getImaginary();
                    }
                    if (rozmer.equals("RMS I ANGLE")) {
                        val = X.get(cl1)[cl2].getI().getComplex_rms_Angle();
                    }

                    if (rozmer.equals("RMS I X")) {
                        val = X.get(cl1)[cl2].getI().getX_ABS();
                    }
                    if (rozmer.equals("PHASE I X")) {
                        val = X.get(cl1)[cl2].getI().getX_Angle();
                    }
                    if (rozmer.equals("REAL I X")) {
                        val = X.get(cl1)[cl2].getI().getX_Real();
                    }
                    if (rozmer.equals("IMAGE I X")) {
                        val = X.get(cl1)[cl2].getI().getX_Imaginary();
                    }

                    if (rozmer.equals("RMS I Y")) {
                        val = X.get(cl1)[cl2].getI().getY_ABS();
                    }
                    if (rozmer.equals("PHASE I Y")) {
                        val = X.get(cl1)[cl2].getI().getY_Angle();
                    }
                    if (rozmer.equals("REAL I Y")) {
                        val = X.get(cl1)[cl2].getI().getY_Real();
                    }
                    if (rozmer.equals("IMAGE I Y")) {
                        val = X.get(cl1)[cl2].getI().getY_Imaginary();
                    }

                    if (rozmer.equals("RMS I Z")) {
                        val = X.get(cl1)[cl2].getI().getZ_ABS();
                    }
                    if (rozmer.equals("PHASE I Z")) {
                        val = X.get(cl1)[cl2].getI().getZ_Angle();
                    }
                    if (rozmer.equals("REAL I Z")) {
                        val = X.get(cl1)[cl2].getI().getZ_Real();
                    }
                    if (rozmer.equals("IMAGE I Z")) {
                        val = X.get(cl1)[cl2].getI().getZ_Imaginary();
                    }

                }
                if (BE == "Emod") {

                    if (rozmer.equals("RMS Emod")) {
                        val = X.get(cl1)[cl2].getEmod().getComplex_rms_ABS();
                    }
                    if (rozmer.equals("RMS Emod REAL")) {
                        val = X.get(cl1)[cl2].getEmod().getComplex_rms().getReal();
                    }
                    if (rozmer.equals("RMS Emod IMAGE")) {
                        val = X.get(cl1)[cl2].getEmod().getComplex_rms().getImaginary();
                    }
                    if (rozmer.equals("RMS Emod ANGLE")) {
                        val = X.get(cl1)[cl2].getEmod().getComplex_rms_Angle();
                    }

                    if (rozmer.equals("RMS Emod X")) {
                        val = X.get(cl1)[cl2].getEmod().getX_ABS();
                    }
                    if (rozmer.equals("PHASE Emod X")) {
                        val = X.get(cl1)[cl2].getEmod().getX_Angle();
                    }
                    if (rozmer.equals("REAL Emod X")) {
                        val = X.get(cl1)[cl2].getEmod().getX_Real();
                    }
                    if (rozmer.equals("IMAGE Emod X")) {
                        val = X.get(cl1)[cl2].getEmod().getX_Imaginary();
                    }

                    if (rozmer.equals("RMS Emod Y")) {
                        val = X.get(cl1)[cl2].getEmod().getY_ABS();
                    }
                    if (rozmer.equals("PHASE Emod Y")) {
                        val = X.get(cl1)[cl2].getEmod().getY_Angle();
                    }
                    if (rozmer.equals("REAL Emod Y")) {
                        val = X.get(cl1)[cl2].getEmod().getY_Real();
                    }
                    if (rozmer.equals("IMAGE Emod Y")) {
                        val = X.get(cl1)[cl2].getEmod().getY_Imaginary();
                    }

                    if (rozmer.equals("RMS Emod Z")) {
                        val = X.get(cl1)[cl2].getEmod().getZ_ABS();
                    }
                    if (rozmer.equals("PHASE Emod Z")) {
                        val = X.get(cl1)[cl2].getEmod().getZ_Angle();
                    }
                    if (rozmer.equals("REAL Emod Z")) {
                        val = X.get(cl1)[cl2].getEmod().getZ_Real();
                    }
                    if (rozmer.equals("IMAGE Emod Z")) {
                        val = X.get(cl1)[cl2].getEmod().getZ_Imaginary();
                    }

                }

                if (val > maxval) {
                    maxval = val;
                    poloha1 = cl1;
                    poloha2 = cl2;
                }
            }
        }

        vystup[0] = maxval;
        vystup[1] = X.get(poloha1)[poloha2].getPoloha().getX();
        vystup[2] = X.get(poloha1)[poloha2].getPoloha().getY();
        vystup[3] = X.get(poloha1)[poloha2].getPoloha().getZ();
        if (Parameter == true) {
            vystup[4] = X.get(poloha1)[poloha2].getParameter();
        }

        return vystup;
    }

    /**
     * vyypis vystup je 4rozmerny vektor veličina polus gometricke koordinaty
     * XYZ
     *
     * @param BE string B alebo E
     * @param rozmer rozmer na vypis
     * @param X odkial to brat Arraylist observerov[] hadat hodnotu
     */
    public double[] getMaxVal(String BE, String rozmer, ArrayList<Observer[]> X) {
        double[] vystup = new double[5];
        double maxval = 0;
        double val = 0;
        int poloha1 = 0;
        int poloha2 = 0;
        for (int cl1 = 0; cl1 < X.size(); cl1++) {
            for (int cl2 = 0; cl2 < X.get(cl1).length; cl2++) {

                if (BE == "B") {

                    if (rozmer.equals("RMS B")) {
                        val = X.get(cl1)[cl2].getB().getComplex_rms_ABS();
                    }
                    if (rozmer.equals("RMS B REAL")) {
                        val = X.get(cl1)[cl2].getB().getComplex_rms().getReal();
                    }
                    if (rozmer.equals("RMS B IMAGE")) {
                        val = X.get(cl1)[cl2].getB().getComplex_rms().getImaginary();
                    }
                    if (rozmer.equals("RMS B ANGLE")) {
                        val = X.get(cl1)[cl2].getB().getComplex_rms_Angle();
                    }

                    if (rozmer.equals("RMS B X")) {
                        val = X.get(cl1)[cl2].getB().getX_ABS();
                    }
                    if (rozmer.equals("PHASE B X")) {
                        val = X.get(cl1)[cl2].getB().getX_Angle();
                    }
                    if (rozmer.equals("REAL B X")) {
                        val = X.get(cl1)[cl2].getB().getX_Real();
                    }
                    if (rozmer.equals("IMAGE B X")) {
                        val = X.get(cl1)[cl2].getB().getX_Imaginary();
                    }

                    if (rozmer.equals("RMS B Y")) {
                        val = X.get(cl1)[cl2].getB().getY_ABS();
                    }
                    if (rozmer.equals("PHASE B Y")) {
                        val = X.get(cl1)[cl2].getB().getY_Angle();
                    }
                    if (rozmer.equals("REAL B Y")) {
                        val = X.get(cl1)[cl2].getB().getY_Real();
                    }
                    if (rozmer.equals("IMAGE B Y")) {
                        val = X.get(cl1)[cl2].getB().getY_Imaginary();
                    }

                    if (rozmer.equals("RMS B Z")) {
                        val = X.get(cl1)[cl2].getB().getZ_ABS();
                    }
                    if (rozmer.equals("PHASE B Z")) {
                        val = X.get(cl1)[cl2].getB().getZ_Angle();
                    }
                    if (rozmer.equals("REAL B Z")) {
                        val = X.get(cl1)[cl2].getB().getZ_Real();
                    }
                    if (rozmer.equals("IMAGE B Z")) {
                        val = X.get(cl1)[cl2].getB().getZ_Imaginary();
                    }

                }
                if (BE == "E") {

                    if (rozmer.equals("RMS E")) {
                        val = X.get(cl1)[cl2].getE().getComplex_rms_ABS();
                    }
                    if (rozmer.equals("RMS E REAL")) {
                        val = X.get(cl1)[cl2].getE().getComplex_rms().getReal();
                    }
                    if (rozmer.equals("RMS E IMAGE")) {
                        val = X.get(cl1)[cl2].getE().getComplex_rms().getImaginary();
                    }
                    if (rozmer.equals("RMS E ANGLE")) {
                        val = X.get(cl1)[cl2].getE().getComplex_rms_Angle();
                    }

                    if (rozmer.equals("RMS E X")) {
                        val = X.get(cl1)[cl2].getE().getX_ABS();
                    }
                    if (rozmer.equals("PHASE E X")) {
                        val = X.get(cl1)[cl2].getE().getX_Angle();
                    }
                    if (rozmer.equals("REAL E X")) {
                        val = X.get(cl1)[cl2].getE().getX_Real();
                    }
                    if (rozmer.equals("IMAGE E X")) {
                        val = X.get(cl1)[cl2].getE().getX_Imaginary();
                    }

                    if (rozmer.equals("RMS E Y")) {
                        val = X.get(cl1)[cl2].getE().getY_ABS();
                    }
                    if (rozmer.equals("PHASE E Y")) {
                        val = X.get(cl1)[cl2].getE().getY_Angle();
                    }
                    if (rozmer.equals("REAL E Y")) {
                        val = X.get(cl1)[cl2].getE().getY_Real();
                    }
                    if (rozmer.equals("IMAGE E Y")) {
                        val = X.get(cl1)[cl2].getE().getY_Imaginary();
                    }

                    if (rozmer.equals("RMS E Z")) {
                        val = X.get(cl1)[cl2].getE().getZ_ABS();
                    }
                    if (rozmer.equals("PHASE E Z")) {
                        val = X.get(cl1)[cl2].getE().getZ_Angle();
                    }
                    if (rozmer.equals("REAL E Z")) {
                        val = X.get(cl1)[cl2].getE().getZ_Real();
                    }
                    if (rozmer.equals("IMAGE E Z")) {
                        val = X.get(cl1)[cl2].getE().getZ_Imaginary();
                    }

                }
                if (BE == "I") {

                    if (rozmer.equals("RMS I")) {
                        val = X.get(cl1)[cl2].getI().getComplex_rms_ABS();
                    }
                    if (rozmer.equals("RMS I REAL")) {
                        val = X.get(cl1)[cl2].getI().getComplex_rms().getReal();
                    }
                    if (rozmer.equals("RMS I IMAGE")) {
                        val = X.get(cl1)[cl2].getI().getComplex_rms().getImaginary();
                    }
                    if (rozmer.equals("RMS I ANGLE")) {
                        val = X.get(cl1)[cl2].getI().getComplex_rms_Angle();
                    }

                    if (rozmer.equals("RMS I X")) {
                        val = X.get(cl1)[cl2].getI().getX_ABS();
                    }
                    if (rozmer.equals("PHASE I X")) {
                        val = X.get(cl1)[cl2].getI().getX_Angle();
                    }
                    if (rozmer.equals("REAL I X")) {
                        val = X.get(cl1)[cl2].getI().getX_Real();
                    }
                    if (rozmer.equals("IMAGE I X")) {
                        val = X.get(cl1)[cl2].getI().getX_Imaginary();
                    }

                    if (rozmer.equals("RMS I Y")) {
                        val = X.get(cl1)[cl2].getI().getY_ABS();
                    }
                    if (rozmer.equals("PHASE I Y")) {
                        val = X.get(cl1)[cl2].getI().getY_Angle();
                    }
                    if (rozmer.equals("REAL I Y")) {
                        val = X.get(cl1)[cl2].getI().getY_Real();
                    }
                    if (rozmer.equals("IMAGE I Y")) {
                        val = X.get(cl1)[cl2].getI().getY_Imaginary();
                    }

                    if (rozmer.equals("RMS I Z")) {
                        val = X.get(cl1)[cl2].getI().getZ_ABS();
                    }
                    if (rozmer.equals("PHASE I Z")) {
                        val = X.get(cl1)[cl2].getI().getZ_Angle();
                    }
                    if (rozmer.equals("REAL I Z")) {
                        val = X.get(cl1)[cl2].getI().getZ_Real();
                    }
                    if (rozmer.equals("IMAGE I Z")) {
                        val = X.get(cl1)[cl2].getI().getZ_Imaginary();
                    }

                }
                if (BE == "Emod") {

                    if (rozmer.equals("RMS Emod")) {
                        val = X.get(cl1)[cl2].getEmod().getComplex_rms_ABS();
                    }
                    if (rozmer.equals("RMS Emod REAL")) {
                        val = X.get(cl1)[cl2].getEmod().getComplex_rms().getReal();
                    }
                    if (rozmer.equals("RMS Emod IMAGE")) {
                        val = X.get(cl1)[cl2].getEmod().getComplex_rms().getImaginary();
                    }
                    if (rozmer.equals("RMS Emod ANGLE")) {
                        val = X.get(cl1)[cl2].getEmod().getComplex_rms_Angle();
                    }

                    if (rozmer.equals("RMS Emod X")) {
                        val = X.get(cl1)[cl2].getEmod().getX_ABS();
                    }
                    if (rozmer.equals("PHASE Emod X")) {
                        val = X.get(cl1)[cl2].getEmod().getX_Angle();
                    }
                    if (rozmer.equals("REAL Emod X")) {
                        val = X.get(cl1)[cl2].getEmod().getX_Real();
                    }
                    if (rozmer.equals("IMAGE Emod X")) {
                        val = X.get(cl1)[cl2].getEmod().getX_Imaginary();
                    }

                    if (rozmer.equals("RMS Emod Y")) {
                        val = X.get(cl1)[cl2].getEmod().getY_ABS();
                    }
                    if (rozmer.equals("PHASE Emod Y")) {
                        val = X.get(cl1)[cl2].getEmod().getY_Angle();
                    }
                    if (rozmer.equals("REAL Emod Y")) {
                        val = X.get(cl1)[cl2].getEmod().getY_Real();
                    }
                    if (rozmer.equals("IMAGE Emod Y")) {
                        val = X.get(cl1)[cl2].getEmod().getY_Imaginary();
                    }

                    if (rozmer.equals("RMS Emod Z")) {
                        val = X.get(cl1)[cl2].getEmod().getZ_ABS();
                    }
                    if (rozmer.equals("PHASE Emod Z")) {
                        val = X.get(cl1)[cl2].getEmod().getZ_Angle();
                    }
                    if (rozmer.equals("REAL Emod Z")) {
                        val = X.get(cl1)[cl2].getEmod().getZ_Real();
                    }
                    if (rozmer.equals("IMAGE Emod Z")) {
                        val = X.get(cl1)[cl2].getEmod().getZ_Imaginary();
                    }

                }

                if (val > maxval) {
                    maxval = val;
                    poloha1 = cl1;
                    poloha2 = cl2;
                }
            }
        }

        vystup[0] = maxval;
        vystup[1] = X.get(poloha1)[poloha2].getPoloha().getX();
        vystup[2] = X.get(poloha1)[poloha2].getPoloha().getY();
        vystup[3] = X.get(poloha1)[poloha2].getPoloha().getZ();
        vystup[4] = 0;

        return vystup;
    }

    public void clear() {
        P1D_neurcite.clear();
        P1D_pozdlzne.clear();
        P1D_priecne.clear();
        P1D_parameter.clear();
        P2D_hor.clear();
        P2D_vert.clear();
        P1D_parameter_ELMPAM.clear();

    }

}

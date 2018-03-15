/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import Databazes.SQLlite_constants;
import emft_vol2.constants;
import flanagan.complex.ComplexMatrix;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math3.complex.Complex;
import org.jdelaunay.delaunay.error.DelaunayError;
import org.jdelaunay.delaunay.geometries.DPoint;

/**
 *
 * @author Jozef
 */
public class help {
    static DecimalFormat df5 = new DecimalFormat("0.00000");

    //private static String printer;
    //private static boolean printPrinter = false;  
    public static void printl(String printer, Boolean printPrinter) {

        if (printPrinter.equals(true)) {
            System.out.println(printer);
        }

    }

    public static void info(String printer, Boolean printPrinter) {

        if (printPrinter.equals(true)) {
            System.out.println("INFO TEST: " + printer);
        }

    }

    public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
}
    
    /**
     * kontrolor na double tiež meni bodku čiarku
     *
     * @param Y Jtext field vstup
     * @param output ak je Y kokoina tak vyhodi tuto hodnotu
     * @return
     */
    public static double ReadCheckDouble(javax.swing.JTextField Y, double output) {
        String hodnota1 = Y.getText();
        hodnota1 = hodnota1.replace(" ", "");
        String hodnota2 = hodnota1.replace(",", ".");
        if (hodnota1.equals(hodnota2)) {
        } else {
            Y.setText(hodnota2);
        }
        Double value;
        try {
            value = Double.parseDouble(hodnota2);
            Y.setForeground(Color.black);
            return value;
        } catch (NumberFormatException | NullPointerException e) {
            Y.setForeground(Color.red);
            return value = output;
        }
    }

    /**
     * vyluje to iste čislo po kontrole a ak je kokotnia tak vrati hodnotu aout
     * put, a ešte ak je kokotina tak vyhodi error sigh
     *
     * @param Y Jtext field
     * @param output
     * @return
     */
    public static double ReadCheckDoubleErrorSign(javax.swing.JTextField Y, double output) {
        String hodnota1 = Y.getText();
        hodnota1 = hodnota1.replace(" ", "");
        String hodnota2 = hodnota1.replace(",", ".");
        if (hodnota1.equals(hodnota2)) {
        } else {
            Y.setText(hodnota2);
        }
        Double value;
        try {
            value = Double.parseDouble(hodnota2);
            Y.setForeground(Color.black);
            return value;
        } catch (NumberFormatException | NullPointerException e) {
            Y.setForeground(Color.red);
            help.warning1row(language_help.LangLabel(constants.getLanguage_option(), 3));
            return value = output;

        }
    }
    /**
     * "dd-MM-yyyy_HH-mm-ss"
     * @return  datum string
     */
    public static String getDateDF2(){
        String date ;
        Date todaysDate = new Date();
          DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
         
          return date = df2.format(todaysDate);
    }
    
    /**
     * dd-MM-yyyy HH:mm:ss"
     * @return  datum string
     */
    public static String getDateDF3(){
        String date ;
        Date todaysDate = new Date();
          DateFormat df3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
         
          return date = df3.format(todaysDate);
    }
    
    public static int ReadCheckIntErrorSign(javax.swing.JTextField Y, int output, String error) {
        String hodnota1 = Y.getText();
        hodnota1 = hodnota1.replace(" ", "");
        String hodnota2 = hodnota1.replace(",", ".");
        if (hodnota1.equals(hodnota2)) {
        } else {
            Y.setText(hodnota2);
        }
        int value;
        try {
            value = Integer.parseInt(hodnota2);
            Y.setForeground(Color.black);
            return value;
        } catch (NumberFormatException | NullPointerException e) {
            Y.setForeground(Color.red);
            help.warning1row(error);
            return value = output;

        }
    }

    /**
     * vlozi hodnotu to text fildu a upravi jej pocet des miest
     *
     * @param Y text field vstup
     * @param input value ktoru chem zobrazit v textfielde
     * @param pocetDesMiest pocet desatinnich miest na ktore bude zaukruhlovat
     * cislo
     */
    public static void DisplayDouble(javax.swing.JTextField Y, double input, int pocetDesMiest) {

        String symbol = "###.";
        for (int cl0 = 0; cl0 < pocetDesMiest; cl0++) {
            symbol = symbol + "#";
        }

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat(symbol, otherSymbols);  // definovany počet desatinnych miest
        Y.setText(df.format(input));

    }

    /**
     * zobrazi hodnotu z databazy do konzoly
     *
     * @param name nazov premennje ako sa vola v databaze
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void ConstantDatabazeDisplay(String name) throws ClassNotFoundException, SQLException {
        SQLlite_constants test = new SQLlite_constants();
        ResultSet rs;

        rs = test.displayConstants();

        while (rs.next()) {

            System.out.println(rs.getString(name));
        }
    }

    public static void warning3row(String riadok1, String riadok2, String riadok3) {

        warning_jDialog pozor = new warning_jDialog(riadok1, riadok2, riadok3);
        pozor.setVisible(true);

    }

    public static void warning2row(String riadok1, String riadok2) {

        warning_jDialog pozor = new warning_jDialog(riadok1, riadok2);
        pozor.setVisible(true);

    }

    public static void warning1row(String riadok1) {

        warning_jDialog pozor = new warning_jDialog(riadok1);
        pozor.setVisible(true);

    }

    public static double Object_To_double(Object X) {
        double value = 0;
        try {
            value = Double.parseDouble(String.valueOf(X));
            return value;
        } catch (NumberFormatException | NullPointerException e) {

            help.warning1row(language_help.LangLabel(constants.getLanguage_option(), 3));
            return value = 0;
        }
    }
    
     public static boolean Object_To_Boolean(Object X) {
        boolean value = false;
        try {
            value = Boolean.valueOf(String.valueOf(X));
            return value;
        } catch (NumberFormatException | NullPointerException e) {

            help.warning1row(language_help.LangLabel(constants.getLanguage_option(), 3));
            return value = false;
        }
    }

    public static double Object_To_int(Object X) {
        int value = 0;
        try {
            value = Integer.parseInt(String.valueOf(X));
            return value;
        } catch (NumberFormatException | NullPointerException e) {

            help.warning1row(language_help.LangLabel(constants.getLanguage_option(), 3));
            return value = 0;
        }
    }

    public static double AsinH(double hodnota) {
        double cislo;

        cislo = Math.log(hodnota + Math.sqrt(1 + Math.pow(hodnota, 2)));

        return cislo;
    }

    /**
     * POZOR neuprauje sa vyska sfunkčne suradnice su XYZ
     *
     * @param LCcoordinates1 globalne suradnice lokalneho sysemu bod 1
     * @param LCcoordinates2 globalne suradnice lokalneho sysemu bod 2
     * @param P1 bod v globalnych sur ktory transformujeme do LC pričom 00je
     * LCCOORDINATES1
     * @return
     */
    public static DPoint CorToLC(double[] LCcoordinates1, double[] LCcoordinates2, DPoint P1) {
        //double GCX = constants.getGCcoordinates()[0];
        // double GCZ = constants.getGCcoordinates()[2];

        double P1XGC = P1.getX();
        double P1ZGC = P1.getZ();

        double LCX1 = LCcoordinates1[0];
        double LCZ1 = LCcoordinates1[2];

        double LCX2 = LCcoordinates2[0];
        double LCZ2 = LCcoordinates2[2];
        double alpha = 0;

        if (LCX1 == LCX2) {

            if (LCZ1 - LCZ2 < 0) {
                alpha = Math.PI / 2;
            }

            if (LCZ1 - LCZ2 > 0) {
                alpha = -Math.PI / 2;
            }

        } // n ochrana pred 90 stupnami a delenim nulou
        else {
            alpha = Math.atan((LCZ1 - LCZ2) / (LCX1 - LCX2)); // uhol otocenia // else{ alpha = Math.atan2((LCX2 - LCX1),(LCZ2 - LCZ1)   ); // else{ alpha = Math.atan((LCZ1 - LCZ2) / (LCX1 - LCX2)); // uhol otocenia
            System.out.println(alpha);
        }
        double P1XLC = (P1XGC - LCX1) * Math.cos(alpha) + (P1ZGC - LCZ1) * Math.sin(alpha);
        double P1ZLC = (P1ZGC - LCZ1) * Math.cos(alpha) + (P1XGC - LCX1) * Math.sin(alpha);

        P1.setX(P1XLC);
        P1.setZ(P1ZLC);
        return P1;
    }

    /**
     * POZOR neuprauje sa vyska sfunkčne suradnice su XYZ
     *
     * @param LCcoordinates1 globalne suradnice lokalneho sysemu bod 1
     * @param LCcoordinates2 globalne suradnice lokalneho sysemu bod 2
     * @param X Z bod v globalnych sur ktory transformujeme do LC pričom 00je
     * LCCOORDINATES1
     * @return vrati XZ 0=x 1=Z
     */
    public static double[] CorToLC(double[] LCcoordinates1, double[] LCcoordinates2, double X, double Z) {
        //double GCX = constants.getGCcoordinates()[0];
        // double GCZ = constants.getGCcoordinates()[2];

        double P1XGC = X;
        double P1ZGC = Z;

        double LCX1 = LCcoordinates1[0];
        double LCZ1 = LCcoordinates1[2];

        double LCX2 = LCcoordinates2[0];
        double LCZ2 = LCcoordinates2[2];
        double alpha = 0;

        if (LCX1 == LCX2) {

            if (LCZ1 - LCZ2 < 0) {
                alpha = Math.PI / 2;
            }

            if (LCZ1 - LCZ2 > 0) {
                alpha = -Math.PI / 2;
            }

        } // n ochrana pred 90 stupnami a delenim nulou
        else {
            alpha = Math.atan((LCZ1 - LCZ2) / (LCX1 - LCX2)); // else{ alpha = Math.atan2((LCX2 - LCX1),(LCZ2 - LCZ1)   ); // uhol otocenia else{ alpha = Math.atan((LCZ1 - LCZ2) / (LCX1 - LCX2)); // uhol otocenia
            System.out.println(alpha);
        }
        double P1XLC = (P1XGC - LCX1) * Math.cos(alpha) + (P1ZGC - LCZ1) * Math.sin(alpha);
        double P1ZLC = (P1ZGC - LCZ1) * Math.cos(alpha) + (P1XGC - LCX1) * Math.sin(alpha);
        double[] XZcor = new double[2];
        XZcor[0] = P1XLC;
        XZcor[1] = P1ZLC;
        return XZcor;
    }

    /**
     * substract vactor A from B
     *
     * @param A vector
     * @param B vector
     * @return vyrti rozdiel vektorov A-B
     * @throws DelaunayError
     */
    public static DPoint substract(DPoint A, DPoint B) throws DelaunayError {
        DPoint C = new DPoint(0, 0, 0);

        C.setX(A.getX() - B.getX());
        C.setY(A.getY() - B.getY());
        C.setZ(A.getZ() - B.getZ());

        return C;
    }

    /**
     * vytvory double array z arraylistu
     *
     * @param X arraylist double
     * @return double[rozmer arrayistu ]
     */
    public static double[] Double_Arraylist_to_DoubleArray(ArrayList<Double> X) {
        double[] result = new double[X.size()];

        for (int cl3 = 0; cl3 < result.length; cl3++) {

            result[cl3] = X.get(cl3);
        }
        return result;
    }

    /**
     * Vstupf fazor
     *
     * @param value
     * @param phase
     * @return
     */
    public double get_real(double value, double phase) {
        double real = value * Math.cos(phase * (Math.PI / 180));
        return real;
    }

    /**
     * Vstup fazor
     *
     * @param value
     * @param phase
     * @return
     */
    public double get_image(double value, double phase) {
        double image = value * Math.sin(phase * (Math.PI / 180));
        return image;
    }

    /**
     * Vzdialenost dvoch vektorov
     *
     * @param A
     * @param B
     * @return
     */
    public double get_distance(DPoint A, DPoint B) {
        double X = B.getX() - A.getX();
        double Y = B.getY() - A.getY();
        double Z = B.getZ() - A.getZ();
        double val = Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2) + Math.pow(Z, 2));
        return val;
    }
    
    /**
     * najvacsia hodnota v arrayi (double)
     * @param arr
     * @return 
     */
    public static double arrayMax(double[] arr) {
        double max = Double.NEGATIVE_INFINITY;

        for(double cur: arr)
            max = Math.max(max, cur);

        return max;
    }
    
    /**
     * najvacsia hodnota v arrayi (int)
     * @param arr
     * @return 
     */
    public static int arrayMax(int[] arr) {
        int max = Integer.MIN_VALUE;

        for(int cur: arr)
            max = Math.max(max, cur);

        return max;
    }
    
    public static double arraySum(int[] arr){
        double res = 0;
        for (int i = 0; i < arr.length; i++) {
            res = res + arr[i];
        }
        return res;
    }
    
    public static double arraySum(double[] arr){
        double res = 0;
        for (int i = 0; i < arr.length; i++) {
            res = res + arr[i];
        }
        return res;
    }
    
    /**
     *  
     * @param c complex number
     * @param x double number
     * @return complex c^x
     */
    public static Complex cpow(Complex c, double x){
        return c.pow(x);
    }
    
    public static Complex cpow(Complex c, int x){
        return c.pow((double)x);
    }
    
    public static Complex csub(Complex a, double b){
        return a.subtract(b);
    }
    
    public static Complex csub(double a, Complex b){
        Complex aux = new Complex(a,0);
        return aux.subtract(b);
    }
    
    public static Complex csub(Complex a, Complex b){
        return a.subtract(b);
    }
    
    public static Complex cadd(Complex a, double b){
        return a.add(b);
    }
    
    public static Complex cadd(Complex a, Complex b){
        return a.add(b);
    }
    
    public static Complex cdiv(Complex a, Complex b){
        return a.divide(b);
    }
    
    public static Complex cdiv(Complex a, double b){
        return a.divide(b);
    }
    
    public static Complex cdiv(double a, Complex b){
        return new Complex(a,0).divide(b);
    }
    
    public static double[] ArrList2Arr(ArrayList arr) {
        double[] res = new double[arr.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = (double) arr.get(i);
        }
        return res;
    }
    
    public static void printRealMatrix(RealMatrix mtx){
        for (int i = 0; i < mtx.getRowDimension(); i++) {
            for (int j = 0; j < mtx.getColumnDimension(); j++) {
                System.out.print(df5.format(mtx.getData()[i][j]) + " ");
            }
        System.out.println();
        }
    }
    
    public static void printDoubleArr(double[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(df5.format(arr[i]));
        }
        System.out.println();
    }
    
    public static RealMatrix clearMatrix(RealMatrix mtx){
        for (int i = 0; i < mtx.getRowDimension(); i++) {
            for (int j = 0; j < mtx.getColumnDimension(); j++) {
                mtx.setEntry(i, j, 0.0);
            }
        }
        return mtx;
    }
    
    /**
     * initialize matrix with zero values based on size of another matrix
     * @param source_mtx matrix defining the size of the initiating matrix
     * @return matrix with zero values with size of source_mtx
     */
    public static RealMatrix initMatrix(RealMatrix source_mtx){
        RealMatrix final_mtx = source_mtx.copy();
        return clearMatrix(final_mtx);
    }
    
    
    //kniznice nizsie pouzite
    //https://www.ee.ucl.ac.uk/~mflanaga/java/
    //https://www.ee.ucl.ac.uk/~mflanaga/java/ComplexMatrix.html
    public static ComplexMatrix initComplexMatrix(RealMatrix source_real_mtx){
        return new ComplexMatrix(source_real_mtx.getRowDimension(), source_real_mtx.getColumnDimension());
    }
    
    public static ComplexMatrix makeComplexMatrix(RealMatrix real, RealMatrix imag){
        int rows_real = real.getRowDimension();
        int rows_imag = imag.getRowDimension();
        int cols_real = real.getColumnDimension();
        int cols_imag = imag.getColumnDimension();
        
        if(rows_imag == rows_real && cols_real == cols_imag){
            ComplexMatrix myMatrix = new ComplexMatrix(rows_real,cols_real);
            for (int i = 0; i < real.getRowDimension(); i++) {
                for (int j = 0; j < real.getColumnDimension(); j++) {
                    flanagan.complex.Complex cmplx = new flanagan.complex.Complex(real.getEntry(i, j), imag.getEntry(i, j));
                    myMatrix.setElement(i, j, cmplx);
                }
            }
            return myMatrix;
        }else{
            return null;
        }
    }
    
    /**
     * ground wires need to be the bottom rows of the ComplexMatrix
     * @param source source ComplexMatrix
     * @param ground_wires number of ground wires (counting from bottom)
     * @return 
     */
    public static ComplexMatrix makeComplexKronReduction(ComplexMatrix source, int ground_wires){
        if (ground_wires == 0){
            //no Kron Reduction needed
            return source;
        } else {
            int rows_total = source.getNrow();
            int cols_total = source.getNcol();
            int nn_rows = rows_total - ground_wires;
            int nn_cols = cols_total - ground_wires;
            int nm_rows = rows_total - ground_wires;
            int nm_cols = ground_wires;
            int mn_rows = ground_wires;
            int mn_cols = cols_total - ground_wires;
            int mm_rows = ground_wires;
            int mm_cols = ground_wires;

            ComplexMatrix NN = new ComplexMatrix(nn_rows, nn_cols);
            ComplexMatrix MN = new ComplexMatrix(mn_rows, mn_cols);
            ComplexMatrix NM = new ComplexMatrix(nm_rows, nm_cols);
            ComplexMatrix MM = new ComplexMatrix(mm_rows, mm_cols);

            for (int i = 0; i < rows_total; i++) {
                for (int j = 0; j < cols_total; j++) {
                    if (i < nn_rows && j < nn_cols) {
                        NN.setElement(i, j, source.getElementCopy(i, j));
                    } else if (i >= nn_rows && j < nn_cols) {
                        MN.setElement(i-nn_rows, j, source.getElementCopy(i, j));
                    } else if (i < nn_rows && j >= nn_cols) {
                        NM.setElement(i, j-nn_cols, source.getElementCopy(i, j));
                    } else if (i >= nn_rows && j >= nn_cols) {
                        MM.setElement(i-nn_rows, j-nn_cols, source.getElementCopy(i, j));
                    }
                }
            }
            return NN.minus(NM.times(MM.inverse()).times(MN));
        }
    }
    
    public static RealMatrix makeRealKronReduction(RealMatrix source, int ground_wires){
        if (ground_wires == 0){
            //no Kron Reduction needed
            return source;
        } else {
            int rows_total = source.getRowDimension();
            int cols_total = source.getColumnDimension();
            int nn_rows = rows_total - ground_wires;
            int nn_cols = cols_total - ground_wires;
            int nm_rows = rows_total - ground_wires;
            int nm_cols = ground_wires;
            int mn_rows = ground_wires;
            int mn_cols = cols_total - ground_wires;
            int mm_rows = ground_wires;
            int mm_cols = ground_wires;

            RealMatrix NN = new Array2DRowRealMatrix(nn_rows, nn_cols);
            RealMatrix MN = new Array2DRowRealMatrix(mn_rows, mn_cols);
            RealMatrix NM = new Array2DRowRealMatrix(nm_rows, nm_cols);
            RealMatrix MM = new Array2DRowRealMatrix(mm_rows, mm_cols);

            for (int i = 0; i < rows_total; i++) {
                for (int j = 0; j < cols_total; j++) {
                    if (i < nn_rows && j < nn_cols) {
                        NN.setEntry(i, j, source.getEntry(i, j));
                    } else if (i >= nn_rows && j < nn_cols) {
                        MN.setEntry(i-nn_rows, j, source.getEntry(i, j));
                    } else if (i < nn_rows && j >= nn_cols) {
                        NM.setEntry(i, j-nn_cols, source.getEntry(i, j));
                    } else if (i >= nn_rows && j >= nn_cols) {
                        MM.setEntry(i-nn_rows, j-nn_cols, source.getEntry(i, j));
                    }
                }
            }
            return NN.subtract(NM.multiply(MM.inverse()).multiply(MN));
        }
    }
    
    public static ComplexMatrix copyComplexMatrix(ComplexMatrix source){
        ComplexMatrix newMatrix = new ComplexMatrix(source.getNrow(), source.getNcol());
        for (int i = 0; i < source.getNrow(); i++) {
            for (int j = 0; j < source.getNcol(); j++) {
                newMatrix.setElement(i, j, source.getElementCopy(i, j));
            }
        }
        return newMatrix;
    }
    
    public static void printComplexMatrix(ComplexMatrix mtx){
        for (int i = 0; i < mtx.getNrow(); i++) {
            for (int j = 0; j < mtx.getNcol(); j++) {
                System.out.print(df5.format(mtx.getElementCopy(i, j).getReal()) + " ");
                System.out.print(df5.format(mtx.getElementCopy(i, j).getImag()) + "i   ");
            }
        System.out.println();
        }
    }
    
    public static ComplexMatrix phase2symm(ComplexMatrix phase){
        int rows = phase.getNrow();
        int cols = phase.getNcol();
        flanagan.complex.Complex[][] T = setT();
        flanagan.complex.Complex[][] T1 = setT1();
        
        
        //checking if data in correct format [square N x 3 phase format matrix]
        if ((rows % 3) == 0 && (cols % 3) == 0 && rows == cols){
            int times = rows/3;
            ComplexMatrix T_total = new ComplexMatrix(rows, cols);
            ComplexMatrix T1_total = new ComplexMatrix(rows, cols);
            for (int i = 0; i < times; i++) {
                T_total.setSubMatrix(i*3, i*3, T);
                T1_total.setSubMatrix(i*3, i*3, T1);
            }
            ComplexMatrix symm = T1_total.times(phase).times(T_total);
            return symm;
        } else {
            System.out.println("Zly vstupny format do vypoctu zloziek");
            return null;
        }
    }
    
    public static ComplexMatrix symm2phase(ComplexMatrix symm){
        int rows = symm.getNrow();
        int cols = symm.getNcol();
        flanagan.complex.Complex[][] T = setT();
        flanagan.complex.Complex[][] T1 = setT1();
        
        
        //checking if data in correct format [square N x 3 phase format matrix]
        if ((rows % 3) == 0 && (cols % 3) == 0 && rows == cols){
            int times = rows/3;
            ComplexMatrix T_total = new ComplexMatrix(rows, cols);
            ComplexMatrix T1_total = new ComplexMatrix(rows, cols);
            for (int i = 0; i < times; i++) {
                T_total.setSubMatrix(i*3, i*3, T);
                T1_total.setSubMatrix(i*3, i*3, T1);
            }
            ComplexMatrix phase = T1_total.times(symm).times(T_total);
            return phase;
        } else {
            System.out.println("Zly vstupny format do vypoctu zloziek");
            return null;
        }
    }
    
    private static flanagan.complex.Complex[][] setT(){
        flanagan.complex.Complex one = new flanagan.complex.Complex(1, 0);
        flanagan.complex.Complex a = new flanagan.complex.Complex(-0.5, Math.sqrt(3)/2);
        flanagan.complex.Complex a2 = new flanagan.complex.Complex(-0.5, -Math.sqrt(3)/2);
        flanagan.complex.Complex[][] T = new flanagan.complex.Complex[3][3];
        
        T[0][0] = one;
        T[1][0] = one;
        T[2][0] = one;
        T[0][1] = one;
        T[0][2] = one;
        T[1][1] = a2;
        T[2][2] = a2;
        T[1][2] = a;
        T[2][1] = a;
        
        return T;
    }
    
    private static flanagan.complex.Complex[][] setT1(){
        flanagan.complex.Complex one = new flanagan.complex.Complex(1, 0);
        flanagan.complex.Complex a = new flanagan.complex.Complex(-0.5, Math.sqrt(3)/2);
        flanagan.complex.Complex a2 = new flanagan.complex.Complex(-0.5, -Math.sqrt(3)/2);
        flanagan.complex.Complex[][] T1 = new flanagan.complex.Complex[3][3];
        
        T1[0][0] = one.times((double)1/3);
        T1[1][0] = one.times((double)1/3);
        T1[2][0] = one.times((double)1/3);
        T1[0][1] = one.times((double)1/3);
        T1[0][2] = one.times((double)1/3);
        T1[1][1] = a.times((double)1/3);
        T1[2][2] = a.times((double)1/3);
        T1[1][2] = a2.times((double)1/3);
        T1[2][1] = a2.times((double)1/3);
        
        return T1;
    }
    
    public static void printSymmRealMatrix(RealMatrix mtx){
        for (int i = 0; i < mtx.getRowDimension(); i++) {
            for (int j = 0; j < mtx.getColumnDimension(); j++) {
                if (i == j) {
                    System.out.print( i + " " + df5.format(mtx.getData()[i][j]) + " ");
                }
            }
        System.out.println();
        }
    }
    
    public static void printSymmComplexMatrix(ComplexMatrix mtx){
        for (int i = 0; i < mtx.getNrow(); i++) {
            for (int j = 0; j < mtx.getNcol(); j++) {
                if (i==j){
                    System.out.print(i + " " + df5.format(mtx.getElementCopy(i, j).getReal()) + "   ");
                    System.out.print(df5.format(mtx.getElementCopy(i, j).getImag()) + "i   ");
                }
            }
        System.out.println();
        }
    }
    
    //printing to file functions
    
    public static void print2fileRealMatrix(RealMatrix mtx, PrintWriter fw, DecimalFormat df){
        for (int i = 0; i < mtx.getRowDimension(); i++) {
            for (int j = 0; j < mtx.getColumnDimension(); j++) {
                fw.print(df.format(mtx.getData()[i][j]) + " ");
            }
        fw.println();
        }
    }
    
    public static void print2fileComplexMatrix(ComplexMatrix mtx, PrintWriter fw, DecimalFormat df){
        for (int i = 0; i < mtx.getNrow(); i++) {
            for (int j = 0; j < mtx.getNcol(); j++) {
                fw.print(df.format(mtx.getElementCopy(i, j).getReal()) + " ");
                fw.print(df.format(mtx.getElementCopy(i, j).getImag()) + "i   ");
            }
        fw.println();
        }
    }
    
    public static void print2fileSymmRealMatrix(RealMatrix mtx, PrintWriter fw, DecimalFormat df){
        for (int i = 0; i < mtx.getRowDimension(); i++) {
            for (int j = 0; j < mtx.getColumnDimension(); j++) {
                if (i == j) {
                    fw.print( i + " " + df.format(mtx.getData()[i][j]) + " ");
                }
            }
        fw.println();
        }
    }
    
    public static void print2fileSymmComplexMatrix(ComplexMatrix mtx, PrintWriter fw, DecimalFormat df){
        for (int i = 0; i < mtx.getNrow(); i++) {
            for (int j = 0; j < mtx.getNcol(); j++) {
                if (i==j){
                    fw.print(i + " " + df.format(mtx.getElementCopy(i, j).getReal()) + "   ");
                    fw.print(df.format(mtx.getElementCopy(i, j).getImag()) + "i   ");
                }
            }
        fw.println();
        }
    }
    
    public static RealMatrix Complex2RealMatrix(ComplexMatrix source){
        RealMatrix aux = new Array2DRowRealMatrix(source.getNrow(), source.getNcol());
        for (int i = 0; i < source.getNrow(); i++) {
            for (int j = 0; j < source.getNcol(); j++) {
                aux.setEntry(i, j, source.getElementCopy(i, j).getReal());
            }
        }
        return aux;
    }
    
    public static RealMatrix Complex2ImagMatrix(ComplexMatrix source){
        RealMatrix aux = new Array2DRowRealMatrix(source.getNrow(), source.getNcol());
        for (int i = 0; i < source.getNrow(); i++) {
            for (int j = 0; j < source.getNcol(); j++) {
                aux.setEntry(i, j, source.getElementCopy(i, j).getImag());
            }
        }
        return aux;
    }
}

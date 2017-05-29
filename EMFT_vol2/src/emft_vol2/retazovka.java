/*
 * prirobit metody na
   Amod,
   C//H vypocet
   F_vektor (x) = x
   nebude leošie zadavat V1V2Z1Z2 .. ako point v LC ?
 */
package emft_vol2;

/**
 *
 * @author Jozef
 */
public class retazovka {
    
    private static double V1_over; 
    private static double V2_over;
    private static double I1_over;
    private static double I2_over;
    private static double W1_over;
    private static double W2_over;
    private static int bundle_over;
    private static double alpha_over;
    
    private static double H_over;
    private static double C_over;
    private static double Amod_over;
    
    private static double r_over;
    private static double U_over;
    private static double I_over;
    private static double Phi_over;
    
    //constructor
    /**
     * 
     * @param V1 vyska zavesneho bodu 1
     * @param V2 vyska zavesneho bodu 2
     * @param I1 vyska izolator1
     * @param I2 vyska izolator2
     * @param W1 vylozenie zavesneho bodu 1
     * @param W2 vylozenie zavesneho bodu 1
     * @param bundle zvazok
     * @param alpha uhol otocenia zvazku
     * @param H min vyska nad terenom ( podla akualne definovaneho terenu)
     * @param C parameter retazovky
     * @param Amod modifikovane rozpatie
     * @param r polomer vodiča
     * @param U združene napatie
     * @param I prud
     * @param Phi faza
     */
    public retazovka(   
     double V1, double V2, double I1,double I2,
     double W1, double W2,
     int bundle, double alpha,  
     double H, double C,double Amod, double r,
     double U,double I,double Phi   
    )
    {   
    V1_over=V1;V2_over=V2;I1_over=I1;I2_over=I1;W1_over=W1;W2_over=W2;bundle_over=bundle;alpha_over=alpha;
    H_over=H;C_over=C;Amod_over=Amod;r_over=r;U_over=U;I_over=I;Phi_over=Phi;
    };

    
    
    public static double getV1_over() {
        return V1_over;
    }

    public static void setV1_over(double V1_over) {
        retazovka.V1_over = V1_over;
    }

    public static double getV2_over() {
        return V2_over;
    }

    public static void setV2_over(double V2_over) {
        retazovka.V2_over = V2_over;
    }

    public static double getI1_over() {
        return I1_over;
    }

    public static void setI1_over(double I1_over) {
        retazovka.I1_over = I1_over;
    }

    public static double getI2_over() {
        return I2_over;
    }

    public static void setI2_over(double I2_over) {
        retazovka.I2_over = I2_over;
    }

    public static double getW1_over() {
        return W1_over;
    }

    public static void setW1_over(double W1_over) {
        retazovka.W1_over = W1_over;
    }

    public static double getW2_over() {
        return W2_over;
    }

    public static void setW2_over(double W2_over) {
        retazovka.W2_over = W2_over;
    }

    public static int getBundle_over() {
        return bundle_over;
    }

    public static void setBundle_over(int bundle_over) {
        retazovka.bundle_over = bundle_over;
    }

    public static double getAlpha_over() {
        return alpha_over;
    }

    public static void setAlpha_over(double alpha_over) {
        retazovka.alpha_over = alpha_over;
    }

    public static double getH_over() {
        return H_over;
    }

    public static void setH_over(double H_over) {
        retazovka.H_over = H_over;
    }

    public static double getC_over() {
        return C_over;
    }

    public static void setC_over(double C_over) {
        retazovka.C_over = C_over;
    }

    public static double getAmod_over() {
        return Amod_over;
    }

    public static void setAmod_over(double Amod_over) {
        retazovka.Amod_over = Amod_over;
    }

    public static double getR_over() {
        return r_over;
    }

    public static void setR_over(double r_over) {
        retazovka.r_over = r_over;
    }

    public static double getU_over() {
        return U_over;
    }

    public static void setU_over(double U_over) {
        retazovka.U_over = U_over;
    }

    public static double getI_over() {
        return I_over;
    }

    public static void setI_over(double I_over) {
        retazovka.I_over = I_over;
    }

    public static double getPhi_over() {
        return Phi_over;
    }

    public static void setPhi_over(double Phi_over) {
        retazovka.Phi_over = Phi_over;
    }

    
    
    
    
}

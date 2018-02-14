/*
 * prirobit metody na
   Amod,
   C//H vypocet
   F_vektor (x) = x
   nebude leošie zadavat V1V2Z1Z2 .. ako point v LC ?
 */
package BackEnd;

import InternalFrame.InternalFrameproject;
import emft_vol2.constants;
import java.util.ArrayList;
import org.jdelaunay.delaunay.error.DelaunayError;
import org.jdelaunay.delaunay.geometries.DPoint;
import org.jdelaunay.delaunay.geometries.DTriangle;
import tools.help;
import tools.language_help;
import tools.warning_jDialog;

/**
 *
 * @author Jozef vytvory retazovku a vypočita všetky potrebne parametre tri
 * možne construktory vsetky hodnoty Dpoint konštruktor ktory našitava Z
 * Rozpatie hodnotu ( služi na prepočet parametrov retazovky zaroven sa
 * vypočitavaju konštanty pre bundle bvodiš.. tieto hodnoty preby pripočitať ku
 * poloham vodiča pre dany cyklus
 *
 */
public class retazovka {

    private double V1_over;
    private double V2_over;
    private double I1_over;
    private double I2_over;
    private double W1_over;
    private double W2_over;
    private double X1_over;
    private double X2_over;

    private DPoint P1_over;
    private DPoint P2_over;

    private int bundle_over;
    private double alpha_over;
    private double distance_over;
    private double HC_over;
    private boolean CorH_over;
    private double ZY_cor_Bundle[][];

    private double H_over;
    private double Hter_over; // hodnotene len v mieste maximálneho prihybu
    private double C_over;
    private double Amod_over; // prepočitane rpzpatie
    private double Beta_over; // uhol otočenia osi rozpatia voči nule systemu
    private double A1_over;

    private double r_over;
    private double U_over;
    private double I_over;
    private double Phi_over;

    private boolean isUtopeny = false;
    private boolean isV1V2setted =false;
    private double V1_tower_zaklad = 0;
    private double V2_tower_zaklad = 0;
    private ArrayList<DTriangle> terenTriangles_over;
    private ArrayList<DPoint> Ro_vectors = new ArrayList<>(0);
    private ArrayList<DPoint> Ro_mirror_vectors = new ArrayList<>(0);
    private ArrayList<DPoint> Dl_vectors = new ArrayList<>(0);

    //constructor
    /**
     *
     * @param V1 vyska zavesneho bodu 1
     * @param V2 vyska zavesneho bodu 2
     * @param I1 vyska izolator1
     * @param I2 vyska izolator2
     * @param W1 vylozenie zavesneho bodu 1
     * @param W2 vylozenie zavesneho bodu 2
     * @param X1 zavesneho bodu 1 v osi X deff treba dat nulu
     * @param X2 zavesneho bodu 2 v osi X deff treba dat A
     * @param bundle zvazok
     * @param alpha uhol otocenia [deg]
     * @param distance vzdialenost vo zvazku od strebu [m]
     * @param HC cislena hodnota H alebo C
     * @param CorH true C alebo false H
     * @param Amod modifikovane rozpatie
     * @param r polomer vodiča
     * @param U združene napatie
     * @param I prud
     * @param Phi faza
     * @param terentriangle array of triangles AK je zadana hodnota H tak je to
     * minimalny výška nad nulovým terénom pre LC 000 !!
     */
    public retazovka(
            double V1, double V2, double I1, double I2,
            double W1, double W2, double X1, double X2,
            int bundle, double alpha, double distance,
            double HC, boolean CorH, double r,
            double U, double I, double Phi, ArrayList<DTriangle> terenTriangles,boolean isV1V2strick, double V1base, double V2base
    ) throws DelaunayError {
        V1_over = V1;
        V2_over = V2;
        I1_over = I1;
        I2_over = I1;
        W1_over = W1;
        W2_over = W2;
        bundle_over = bundle;
        alpha_over = alpha;
        HC_over = HC;
        r_over = r;
        U_over = U;
        I_over = I;
        Phi_over = Phi;
        terenTriangles_over = terenTriangles;
        X1_over = X1;
        X2_over = X2;
        distance_over = distance;
        CorH_over = CorH;

        P1_over = new DPoint(0, 0, 0);
        P2_over = new DPoint(0, 0, 0);

        P1_over.setX(X1_over);
        P2_over.setX(X2_over);

        P1_over.setY(V1_over);
        P2_over.setY(V2_over);

        P1_over.setZ(W1_over);
        P2_over.setZ(W2_over);

        isV1V2setted = isV1V2strick;
        V1_tower_zaklad=V1base;
        V2_tower_zaklad=V2base;
        generate(HC_over, CorH_over);
        //zvazkovanie a generovanie konštant
        generateBundleConstants();

    }

    ;
    
    public retazovka(
            DPoint P1, DPoint P2,
            double I1, double I2,
            int bundle, double alpha, double distance,
            double HC, boolean CorH, double r,
            double U, double I, double Phi, ArrayList<DTriangle> terenTriangles
    ) throws DelaunayError {

        I1_over = I1;
        I2_over = I1;
        bundle_over = bundle;
        alpha_over = alpha;
        distance_over = distance;
        HC_over = HC;
        r_over = r;
        U_over = U;
        I_over = I;
        Phi_over = Phi;
        terenTriangles_over = terenTriangles;
        CorH_over = CorH;

        P1_over = new DPoint(0, 0, 0);
        P2_over = new DPoint(0, 0, 0);
        P1_over = P1;
        P2_over = P2;

        X1_over = P1_over.getX();
        X2_over = P2_over.getX();

        V1_over = P1_over.getY();
        V2_over = P2_over.getY();

        W1_over = P1_over.getZ();
        W2_over = P2_over.getZ();

        generate(HC_over, CorH_over);

        //zvazkovanie a generovanie konštant
        generateBundleConstants();

    }

    public boolean isIsV1V2setted() {
        return isV1V2setted;
    }

    public void setIsV1V2setted(boolean isV1V2setted) {
        this.isV1V2setted = isV1V2setted;
    }

    public retazovka(retazovka R, ArrayList<DTriangle> terenTriangles2) throws DelaunayError {

        I1_over = R.getI1_over();
        I2_over = R.getI2_over();
        bundle_over = R.getBundle_over();
        alpha_over = R.getAlpha_over();
        distance_over = R.getDistance_over();
        HC_over = R.getHC_over();
        r_over = R.getR_over();
        U_over = R.getU_over();
        I_over = R.getI_over();
        Phi_over = R.getPhi_over();
        terenTriangles_over = terenTriangles2;
        CorH_over = R.isCorH_over();

        P1_over = new DPoint(0, 0, 0);
        P2_over = new DPoint(0, 0, 0);
        P1_over = R.getP1_over();
        P2_over = R.getP2_over();

        X1_over = P1_over.getX();
        X2_over = P2_over.getX();

        V1_over = P1_over.getY();
        V2_over = P2_over.getY();

        W1_over = P1_over.getZ();
        W2_over = P2_over.getZ();

        generate(HC_over, CorH_over);

        //zvazkovanie a generovanie konštant
        generateBundleConstants();

    }

    /**
     * generate C or H and H_ter podla zadanzch vstupov
     * @param HC hodnota H alebo C zadana uživatelom
     * @param CorH true ==C  false ==H
     * @throws DelaunayError 
     */
    private void generate(double HC, boolean CorH) throws DelaunayError {

        Tfield BOD = new Tfield(terenTriangles_over);  // zober trojuholniky najdy  v ktorom s nachadza bod dopočitaj jeho vyšku vzhladom na teren
       
        if(isV1V2setted==false){
        P1_over = BOD.getYaboveTer(P1_over);       // najdy vysku zavesneho bodu na terene above teren vzdialenost od nuly vyskovej
        P2_over = BOD.getYaboveTer(P2_over);
        }
        if(isV1V2setted==true){
        P1_over = new DPoint(P1_over.getX(), P1_over.getY() + V1_tower_zaklad, P1_over.getZ());       // najdy vysku zavesneho bodu na terene above teren vzdialenost od nuly vyskovej
        P2_over = new DPoint(P2_over.getX(), P2_over.getY() + V2_tower_zaklad, P2_over.getZ());
        }
            
        P1_over.setY(P1_over.getY() - I1_over); // zniž zavesny bod a vyšku izolatora
        P2_over.setY(P2_over.getY() - I2_over); // zniž zavesny bod a vyšku izolatora
        Amod_over = Amod(P1_over, P2_over); // AMOD
        Beta_over = Beta(P1_over, P2_over); // Beta angle caltulation

        if (CorH == true) {                  // vypočet C
            C_over = HC;                  // nastavi C
            H_over = Hcalculation(P1_over, P2_over, Amod_over, C_over);      // vypočtia H

            double A1x = A1_over * Math.cos(Beta_over) + P1_over.getX(); // priemet A1  do A1x osi plus odd set od OLS preto plus P1X --- P-Ret-1
            double A1z = A1_over * Math.sin(Beta_over) + P1_over.getZ(); // priemet A1  do A1x osi plus odd set od OLS preto plus P1X
            DPoint A1LC = new DPoint(A1x, 0, A1z);
            A1LC = BOD.getY(A1LC); // A1 v LC coor a priradenou výškou
            Hter_over = H_over - A1LC.getY();  // novy vyška nad nenulovým terenoom je povodna žiadana vyška plus prírastok od nenulového terenu

            if (Hter_over < 0) {
                warning_jDialog help = new warning_jDialog("seka zem");

            }
        } else {

            H_over = HC;
            C_over = CfromHcalculation(P1_over, P2_over, Amod_over, H_over); // dopocita C na zaklade H

            double A1x = A1_over * Math.cos(Beta_over) + P1_over.getX(); // priemet A1  do A1x osi plus odd set od OLS preto plus P1X --- P-Ret-1
            double A1z = A1_over * Math.sin(Beta_over) + P1_over.getZ(); // priemet A1  do A1x osi plus odd set od OLS preto plus P1X

            DPoint A1LC = new DPoint(A1x, 0, A1z);
            A1LC = BOD.getY(A1LC); // A1 v LC coor a priradenou výškou
            Hter_over = HC - A1LC.getY();  // novy vyška nad nenulovým terenoom je povodna žiadana vyška plus prírastok od nenulového terenu
            // C_over = CfromHcalculation(P1_over, P2_over, Amod_over, Hter_over); //dopocita C na zaklade H NEROZUMIEM PREO SOM TO TU DAL
        }

    }

    private void generateBundleConstants() {

        if (bundle_over == 0) {
            bundle_over = 1;
        }
        ZY_cor_Bundle = new double[2][bundle_over];
        double D = distance_over;
        double gamma = (2 * Math.PI) / bundle_over; //otočny uhol

        if (bundle_over == 1) {
            ZY_cor_Bundle[0][bundle_over - 1] = 0;
            ZY_cor_Bundle[1][bundle_over - 1] = 0;
        }

        if (bundle_over != 1) {
            for (int cl1 = 0; cl1 < bundle_over; cl1++) {

                double povodnyY = -D;

                double NovyZ = -povodnyY * Math.sin(gamma * cl1);
                double NovyY = +povodnyY * Math.cos(gamma * cl1);

                ZY_cor_Bundle[0][cl1] = getZrot(NovyZ, NovyY);
                ZY_cor_Bundle[1][cl1] = getYrot(NovyZ, NovyY);
             //   System.out.println("vidič " + cl1 + ": Z-" + ZY_cor_Bundle[0][cl1] + " Y-" + ZY_cor_Bundle[1][cl1]); // vypyis kontrola
            }
            //bod 1 dvojzvazo
            //ZY_cor_Bundle[0][0]=getZrot(0, -D);
            //ZY_cor_Bundle[1][0]=getYrot(0, -D);
            //bod 2 dvojzvazok
            //ZY_cor_Bundle[0][1]=getZrot(0, D);
            //ZY_cor_Bundle[1][1]=getYrot(0, D);

        }

    }

    public double getHter_over() {
        return Hter_over;
    }

    private double getZrot(double Zb, double Yb) {
        double prevod = Math.PI / 180;
        double Zrot = Zb * Math.cos(alpha_over * prevod) - Yb * Math.sin(alpha_over * prevod);
        return Zrot;
    }

    private double getYrot(double Zb, double Yb) {
        double prevod = Math.PI / 180;
        double Yrot = Zb * Math.sin(alpha_over * prevod) + Yb * Math.cos(alpha_over * prevod);;
        return Yrot;
    }

    private double Amod(DPoint P1, DPoint P2) {
        double Amod = Math.sqrt(Math.pow(P2.getX() - P1.getX(), 2) + Math.pow(P2.getZ() - P1.getZ(), 2));
        return Amod;
    }

    public boolean isCorH_over() {
        return CorH_over;
    }

    public void setCorH_over(boolean CorH_over) {
        this.CorH_over = CorH_over;
    }

    public double getDistance_over() {
        return distance_over;
    }

    public void setDistance_over(double distance_over) {
        this.distance_over = distance_over;
    }

    private double Beta(DPoint P1, DPoint P2) {
        double Amod = Math.sqrt(Math.pow(P2.getX() - P1.getX(), 2) + Math.pow(P2.getZ() - P1.getZ(), 2));
        double Beta_Angle = Math.asin(((P2.getZ() - P1.getZ()) / Amod));
        return Beta_Angle;
    }

    private double CfromHcalculation(DPoint P1, DPoint P2, double Amod, double H) {
        double C = 1000;
        double Cold = 0;
        int counter_divergence = 0;
        double Hnew = 0;
        double Hold = H;
        while (true) {

            double K1 = Amod / (2 * C);
            double K2 = help.AsinH((P2.getY() - P1.getY()) / (2 * C * Math.sinh(K1)));
            double A1 = Amod - (C * (K2 + K1));
            A1_over = A1;    // sets the A1

            Hnew = P1.getY() - (C * (Math.cosh(A1 / C))) + C;

            if (Math.abs(Hold - Hnew) < constants.getPresnostCH()) {
                break; // kontrola ak podmienka plati chod von
            }
            if (Hnew > Hold) {                                        //  ak nie tak nasav nove Cčko
                C = C - C / 2;
            } else {
                C = C + C / 2;
            }
            if (H > P2.getY() || H > P1.getY()) {

                if (Cold < C) {
                    counter_divergence++;
                }

            }
            if (C > 200000000) { /// ak z diverguje nahodne

                help.warning1row(language_help.LangLabel(constants.getLanguage_option(), 5));
                C = 100000000;
                break;

            }

            Cold = C;
            if (counter_divergence > constants.getDivergencia_pocet()) { // ak zdiverguje kokotne
                help.warning1row(language_help.LangLabel(constants.getLanguage_option(), 4));
                C = 100000000;
                break;

            }

        }
        if (A1_over < 0 || A1_over > Amod_over) {
            isUtopeny = true;          // ak je utopeny spozna to
        }
        H_over = Hnew;
        return C;

    }

    public double getHC_over() {
        return HC_over;
    }

    public void setHC_over(double HC_over) {
        this.HC_over = HC_over;
    }

    public double getA1_over() {
        return A1_over;
    }

    public void setA1_over(double A1_over) {
        A1_over = A1_over;
    }

    private double Hcalculation(DPoint P1, DPoint P2, double Amod, double C) {

        double H = 0;

        double K1 = Amod / (2 * C);

        double K2 = help.AsinH((P2.getY() - P1.getY()) / (2 * C * Math.sinh(K1)));

        double A1 = Amod - (C * (K2 + K1));
        A1_over = A1;              // sets the A1
        H = P1.getY() - (C * (Math.cosh(A1 / C))) + C;
        if (A1_over < 0 || A1_over > Amod_over) {
            isUtopeny = true;   // ak je utopeny spozna to
        }
        return H;

    }

    public double getAlpha_over() {
        return alpha_over;
    }

    public double getAmod_over() {
        return Amod_over;
    }

    public int getBundle_over() {
        return bundle_over;
    }

    public double getC_over() {
        return C_over;
    }

    public double getH_over() {
        return H_over;
    }

    public double getI1_over() {
        return I1_over;
    }

    public double getI2_over() {
        return I2_over;
    }

    public double getI_over() {
        return I_over;
    }

    public DPoint getP1_over() {
        return P1_over;
    }

    public DPoint getP2_over() {
        return P2_over;
    }

    public double getPhi_over() {
        return Phi_over;
    }

    public double getR_over() {
        return r_over;
    }

    public double getU_over() {
        return U_over;
    }

    public double getV1_over() {
        return V1_over;
    }

    public double getV2_over() {
        return V2_over;
    }

    public double getW1_over() {
        return W1_over;
    }

    public double getW2_over() {
        return W2_over;
    }

    public double getX1_over() {
        return X1_over;
    }

    public double getX2_over() {
        return X2_over;
    }

    public void setAlpha_over(double alpha_over) {
        alpha_over = alpha_over;
    }

    public void setAmod_over(double Amod_over) {
        Amod_over = Amod_over;
    }

    public void setBundle_over(int bundle_over) {
        bundle_over = bundle_over;
    }

    public void setC_over(double C_over) {
        C_over = C_over;
    }

    public void setH_over(double H_over) {
        H_over = H_over;
    }

    public void setI1_over(double I1_over) {
        I1_over = I1_over;
    }

    public void setI2_over(double I2_over) {
        I2_over = I2_over;
    }

    public void setI_over(double I_over) {
        I_over = I_over;
    }

    public void setP1_over(DPoint P1_over) {
        P1_over = P1_over;
    }

    public void setP2_over(DPoint P2_over) {
        P2_over = P2_over;
    }

    public void setPhi_over(double Phi_over) {
        Phi_over = Phi_over;
    }

    public void setR_over(double r_over) {
        r_over = r_over;
    }

    public void setU_over(double U_over) {
        U_over = U_over;
    }

    public void setV1_over(double V1_over) {
        V1_over = V1_over;
    }

    public void setV2_over(double V2_over) {
        V2_over = V2_over;
    }

    public void setW1_over(double W1_over) {
        W1_over = W1_over;
    }

    public void setW2_over(double W2_over) {
        W2_over = W2_over;
    }

    public void setX1_over(double X1_over) {
        X1_over = X1_over;
    }

    public void setX2_over(double X2_over) {
        X2_over = X2_over;
    }

    public double[][] getZY_cor_Bundle() {
        return ZY_cor_Bundle;
    }

    public double getZY_cor_bundle_lowest_conductor() {
        ArrayList<Double> Zcor = new ArrayList<>();
        for (int cl1 = 0; cl1 < getBundle_over(); cl1++) {

            Zcor.add(ZY_cor_Bundle[0][cl1]);

        }

        double min = 0;
        for (int cl1 = 0; cl1 < getBundle_over(); cl1++) {
            double val = Zcor.get(cl1);
            if (val < min) {
                min = val;
            }
        }

        return min;
    }

    public ArrayList<DPoint> getRo_vectors() {
        return Ro_vectors;
    }

    public ArrayList<DPoint> getRo_mirror_vectors() {
        return Ro_mirror_vectors;
    }

    public ArrayList<DPoint> getDl_vectors() {
        return Dl_vectors;
    }

    public double getBeta_over() {
        return Beta_over;
    }

    public void setBeta_over(double Beta_over) {
        this.Beta_over = Beta_over;
    }

    /**
     * funkcia ktory na iteračneho kroku a vypocitanich parametrov retazovky
     * vypočíta polohu vektora R0 na koniec elementu
     *
     * @param cl1 iteračny krok
     * @param elementLength dlžka elementu
     * @return vektor R0
     * @throws DelaunayError
     */
    public DPoint get_R0_vector(int cl1, double elementLength) throws DelaunayError {

        DPoint R0 = new DPoint(0, 0, 0);
        R0.setX(cl1 * elementLength * Math.cos(this.Beta_over) + this.X1_over);
        R0.setY(this.C_over * (Math.cosh((cl1 * elementLength - this.A1_over) / this.C_over)) - this.C_over + this.H_over);
        R0.setZ(cl1 * elementLength * Math.sin(this.Beta_over) + this.W1_over);
        //System.out.println(R0);
        return R0;
    }

    /**
     * funkcia ktory na iteračneho kroku a vypocitanich parametrov retazovky
     * vypočíta polohu vektora R0 navybrau cast elementu moje surky BEZ BUNDLE
     * KOREKCIE
     *
     * @param cl1 iteračny krok
     * @param elementLength dlžka elementu v metroch
     * @param position poloha END MIDDLE BEGIN
     * @return
     * @throws DelaunayError
     */
    public DPoint get_R0_vector(int cl1, double elementLength, String position) throws DelaunayError {

        DPoint R0 = new DPoint(0, 0, 0);
        double pozicna_konstanta = 0;

        if (position.equals("END")) {
            pozicna_konstanta = 1; // koniec elementu lana
        }
        if (position.equals("MIDDLE")) {
            pozicna_konstanta = 0.5;     // stred elementu lana
        }
        if (position.equals("BEGIN")) {
            pozicna_konstanta = 0; // začiatok elementu lana
        }
        R0.setX(cl1 * elementLength * Math.cos(this.Beta_over) + this.X1_over + elementLength * Math.cos(this.Beta_over) * pozicna_konstanta);
        R0.setY(this.C_over * (Math.cosh(((cl1 * elementLength + elementLength * pozicna_konstanta) - this.A1_over) / this.C_over)) - this.C_over + this.H_over);
        R0.setZ(cl1 * elementLength * Math.sin(this.Beta_over) + this.W1_over + elementLength * Math.sin(this.Beta_over) * pozicna_konstanta);
       // System.out.println(R0);
        return R0;
    }

    /**
     * vypocita vsetky Ro vektory pre jedno lano ale opat bez korekcie bundle
     * constants to sa bude robit az main vypocete
     *
     * @param elementLength vstupuje dlzka elementu v metroch
     * @return
     */
    public void calcul_AllDlVectors(double elementLength) throws DelaunayError {
        if( this.Dl_vectors.size()>0)  this.Dl_vectors.clear();
         // precisti celi zoznam a sme ready na prgenerovanie

        double modelovana_dlzka = 0;
        int iterator = 0;
        while (modelovana_dlzka < this.Amod_over) {

            DPoint End = new DPoint(get_R0_vector(iterator, elementLength, "END"));
            DPoint Begin = new DPoint(get_R0_vector(iterator, elementLength, "BEGIN"));
            
            Dl_vectors.add(help.substract(End, Begin));

            iterator = iterator + 1;
            modelovana_dlzka=iterator*elementLength;
        }

    }
    
     /**
     * vypocita vsetky Ro vektory pre jedno lano ale opat bez korekcie bundle
     * constants to sa bude robit az main vypocete
     *
     * @param elementLength vstupuje dlzka elementu v metroch
     * @return
     */
    public void calcul_AllRoVectors(double elementLength) throws DelaunayError {
        if( this.Ro_vectors.size()>0)  this.Ro_vectors.clear();
         // precisti celi zoznam a sme ready na prgenerovanie

        double modelovana_dlzka = 0;
        int iterator = 0;
        while (modelovana_dlzka < this.Amod_over) {

            Ro_vectors.add(get_R0_vector(iterator, elementLength, "MIDDLE"));

            iterator = iterator + 1;
            modelovana_dlzka=iterator*elementLength;
        }

    }

    /**
     * vypocita vsetky Ro mirror vektory pre jedno lano ale opat bez korekcie
     * bundle constants to sa bude robit az main vypocet_ vypočet prepočitava aj
     * Ro od znova
     *
     * @param elementLength vstupuje dlzka elementu v metroch
     * @return
     */
    public void calcul_AllRo_mirrorVectors_clean(double elementLength) throws DelaunayError {
        this.Ro_mirror_vectors.clear();  // precisti celi zoznam a sme ready na prgenerovanie

        double modelovana_dlzka = 0;
        int iterator = 0;
        while (modelovana_dlzka < this.Amod_over) {

            DPoint Ro = get_R0_vector(iterator, elementLength, "MIDDLE");
            Ro_mirror_vectors.add(InternalFrameproject.Rozpätie.getPole().getMirrorPoint(Ro));

            iterator = iterator + 1;
             modelovana_dlzka=iterator*elementLength;
        }

    }

    /**
     * vypocita vsetky Ro mirror vektory pre jedno lano ale opat bez korekcie
     * bundle constants to sa bude robit az main vypocet_ vypočet musi
     * nasledovat po calcul all Ro vectors pretože ntom pracuje
     * VYPOCET NA APROXIMOVANU YEM KOLMU NA LC
     *
     * @param elementLength vstupuje dlzka elementu v metroch
     * @return
     */
    public void calcul_AllRo_mirrorVectors_from_Ro_aproxxplane(double elementLength) throws DelaunayError {
        this.Ro_mirror_vectors.clear();  // precisti celi zoznam a sme ready na prgenerovanie

        double modelovana_dlzka = 0;
        int iterator = 0;
        while (modelovana_dlzka < this.Amod_over) {
            Ro_mirror_vectors.add(InternalFrameproject.Rozpätie.getPole().getMirrorPointAproxxPlane(this.Ro_vectors.get(iterator),this.Beta_over,1));

            iterator = iterator + 1;
             modelovana_dlzka=iterator*elementLength;
        }

    }
 /**
     * vypocita vsetky Ro mirror vektory pre jedno lano ale opat bez korekcie
     * bundle constants to sa bude robit az main vypocet_ vypočet prepočitava aj
     * Ro od znova
     *
     * @param elementLength vstupuje dlzka elementu v metroch
     * @return
     */
     public void calcul_AllRo_mirrorVectors_clean_aproxxplane(double elementLength) throws DelaunayError {
        this.Ro_mirror_vectors.clear();  // precisti celi zoznam a sme ready na prgenerovanie

        double modelovana_dlzka = 0;
        int iterator = 0;
        while (modelovana_dlzka < this.Amod_over) {

            DPoint Ro = get_R0_vector(iterator, elementLength, "MIDDLE");
            Ro_mirror_vectors.add(InternalFrameproject.Rozpätie.getPole().getMirrorPointAproxxPlane(Ro,this.Beta_over,1));

            iterator = iterator + 1;
             modelovana_dlzka=iterator*elementLength;
        }

    }

    /**
     * vypocita vsetky Ro mirror vektory pre jedno lano ale opat bez korekcie
     * bundle constants to sa bude robit az main vypocet_ vypočet musi
     * nasledovat po calcul all Ro vectors pretože ntom pracuje
     *
     * @param elementLength vstupuje dlzka elementu v metroch
     * @return
     */
    public void calcul_AllRo_mirrorVectors_from_Ro(double elementLength) throws DelaunayError {
        this.Ro_mirror_vectors.clear();  // precisti celi zoznam a sme ready na prgenerovanie

        double modelovana_dlzka = 0;
        int iterator = 0;
        while (modelovana_dlzka < this.Amod_over) {
            Ro_mirror_vectors.add(InternalFrameproject.Rozpätie.getPole().getMirrorPoint(this.Ro_vectors.get(iterator)));

            iterator = iterator + 1;
             modelovana_dlzka=iterator*elementLength;
        }

    }
    /**
     * najebe vsetky mirror vektory do minus nekonečna
     * @param elementLength
     * @throws DelaunayError 
     */
    public void calcul_AllRo_mirrorVectors_OFF(double elementLength) throws DelaunayError {
        this.Ro_mirror_vectors.clear();  // precisti celi zoznam a sme ready na prgenerovanie

        double modelovana_dlzka = 0;
        int iterator = 0;
        while (modelovana_dlzka < this.Amod_over) {
            Ro_mirror_vectors.add( new DPoint(-10E40+iterator,-10E40+iterator, -10E40+iterator));

            iterator = iterator + 1;
             modelovana_dlzka=iterator*elementLength;
        }

    }
    
    
}

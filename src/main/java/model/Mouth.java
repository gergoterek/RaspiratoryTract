package model;

import activity.*;
import subject.Female;
import subject.Male;
import subject.Subject;

import java.util.ArrayList;
import java.util.List;


public class Mouth {

    Subject subject;// = new Female( new LightExcercise(), null);

    private final double inhTime;
    private final double exhTime;
    private final double vol;
    private final double pSize;

    private final double fi;
    private final double aBB;
    private final double SFa;
    private final double SFb;
    private final double SFt;



    public Mouth(final Subject sub){
//        System.out.println("\n\n\nKONSTRUKOR INDUL");
//        switch (sub.getClass().getSimpleName()){
//            case "Male":
//                subject = new Male( sub.getActivity(), new ArrayList<>());
//                break;
//            case "Female":
//                subject = new Female( sub.getActivity(), new ArrayList<>());
//                break;
//        }

        subject = sub;

        //subject = new Female( new LightExcercise(), null);

        vol = (subject.values == null || subject.values.get(0) == null )
                ? subject.getActivity().getV()
                : subject.values.get(0);
        inhTime = (subject.values == null || subject.values.get(1) == null )
                ? 2
                : subject.values.get(1);
        exhTime = (subject.values == null || subject.values.get(2) == null )
                ? 2
                : subject.values.get(2);
        pSize = (subject.values == null || subject.values.get(3) == null )
                ? 0.005
                : subject.values.get(3);


        SFa = subject.getSFa();
        SFb = subject.getSFb();
        SFt = subject.getSFt();


        fi = 1 + 100 * Math.exp( -Math.pow( Math.log( 100 + 10 / Math.pow( getFlowInh(), 0.9) ), 2));  //Va
        aBB = 22.02 * Math.pow(SFt, 1.24) * fi;
    }




    double getFlowInh() {
        return vol / inhTime;
    }
    double Va() {
        return vol / inhTime;
    }
    double getFlowExh() {
        return vol / exhTime;
    }

    public double inh_ET() {
        double a = 1.1 * 0.0001;

        double R = Math.pow(pSize, 2) * Math.pow(getFlowInh() *
                    Math.pow( SFt, 3), 0.6) * Math.pow(vol * Math.pow(SFt, 2), -0.2);
        double p = 1.4;
        return 1 - Math.exp(-a * Math.pow(R, p));
    }

    double inh_ET_perc() {
        return inh_ET() * 100;
    }

    double inh_BB() {
        double a = 4.08 * 0.000001;

        double R = Math.pow (pSize, 2) * getFlowInh() * Math.pow( SFt, 2.3);
        double p = 1.152;

        return 1 - Math.exp (-a * Math.pow(R, p));
    }

    double fact_BB() {
        double vdET = subject.getVd_ET(); //ml
        return 1 - vdET / vol;
    }

    double inh_BB_perc() {
        return inh_BB() * fact_BB() * (1 - inh_ET()) * 100;
    }

    double tbInh() {
        double vd_bb = subject.getVd_BB();
        double FRC = subject.getFRC();
        return (vd_bb / getFlowInh()) * (1 + 0.5 * (vol / FRC));
    }

    double tbExh() {
        double vd_bb = subject.getVd_BB();
        double FRC = subject.getFRC();
        return (vd_bb / getFlowExh()) * (1 + 0.5 * (vol / FRC));
    }

    double inh_bb() {

        double a = 0.1147;
        double R = (0.056 + Math.pow(tbInh(), 1.5)) *
                Math.pow( Math.pow( pSize, tbInh()), -0.25);
        double p = 1.173;

        return 1 - Math.exp(-a * Math.pow(R, p));
    }

    double v_BB() {
        double vd_BB = subject.getVd_BB();
        double FRC = subject.getFRC();
        return vd_BB * (1 + vol / FRC);
    }

    double inh_bb_perc() {
        double vd_ET = subject.getVd_ET();
        double fact_bb = 1 - (vd_ET + v_BB()) / vol;

        return inh_bb() * fact_bb * (1 - inh_ET()) * (1 - inh_BB()) * 100;
    }

    double ta() {
        return (vol - subject.getVd_ET() - ( subject.getVd_BB() + subject.getVd_bb() ) * (1 + vol / 3300)) / getFlowInh();//96
    }

    double AI() {

        double a = 0.146  * Math.pow( SFa, 0.96);
        double R = Math.pow(pSize, 2) * ta();
        double p = 0.6495;

        return 1 - Math.exp(-a * Math.pow(R, p));
    }

    double v_bb() {

        double vd_bb = subject.getVd_bb();
        double FRC = subject.getFRC();

        return vd_bb * (1 + vol / FRC);
    }

    double fact_AI() {
        return 1 - ( subject.getVd_ET() + v_BB() + v_bb()) / vol;
    }

    double AI_perc() {
        return AI() * fact_AI() * (1 - inh_ET()) * (1 - inh_BB()) * (1 - inh_bb()) * 100;
    }

    double exh_bb() {
        double a = 0.1147;
        double R = (0.056 + Math.pow(tbExh(), 1.5)) * Math.pow(Math.pow(pSize, tbExh()), -0.25);
        double p = 1.173;

        return 1 - Math.exp(-a * Math.pow(R, p));
    }

    double fact_bb() {
        double vd_ET = subject.getVd_ET();
        return 1 - (vd_ET + v_BB()) / vol;
    }

    double exh_bb_perc() {
        return exh_bb() * fact_bb() * (1 - inh_ET()) * (1 - inh_BB()) * (1 - inh_bb()) * (1 - AI()) * 100;
    }

    double exh_BB() {
        double a = 2.04 * 0.000001;
        double R = Math.pow(pSize, 2) * getFlowExh() * Math.pow(SFt, 2.3);
        double p = 1.152;
        return 1 - Math.exp(-a * Math.pow(R, p));
    }

    double exh_BB_perc() {
        return exh_BB() * fact_BB() * (1 - inh_ET()) * (1 - inh_BB()) * (1 - inh_bb()) * (1 - AI()) * (1 - exh_bb()) * 100;
    }

    double exh_ET() {
        double a = 1.1 * 0.0001;

        double R = Math.pow(pSize, 2) * Math.pow(getFlowExh() * Math.pow(SFt, 3), 0.6) * Math.pow(vol * Math.pow(SFt, 2), -0.2);
        double p = 1.4;
        return 1 - Math.exp(-a * Math.pow(R, p));
    }

    double exh_ET_perc() {
        return exh_ET() * (1 - inh_ET()) * (1 - inh_BB()) * (1 - inh_bb()) * (1 - AI()) * (1 - exh_bb()) * (1 - exh_BB()) * 100;
    }



    double sum_BB_bb_AI() {

        return (inh_BB_perc() + inh_bb_perc() + AI_perc() + exh_bb_perc() + exh_BB_perc());
    }

    double sum_ET() {
        return inh_ET_perc() + exh_ET_perc();
    }

    double sum_BB() {
        return inh_BB_perc() + exh_BB_perc();
    }

    double sum_bb() {
        return inh_bb_perc() + exh_bb_perc();
    }

    double sum_Lung_ET() {
        return (inh_BB_perc() + inh_bb_perc() + AI_perc() + exh_bb_perc() + exh_BB_perc())
                + (inh_ET_perc() + exh_ET_perc());
    }

    public void print(){
        System.out.println("Inh Flow (ml/s): " + getFlowInh());
        System.out.println("Exh Flow (ml/s): " + getFlowExh());
        System.out.println("ET inhalation" + inh_ET());
        System.out.println("BB inhalation" + inh_BB());
        System.out.println("bb inhalation" + inh_bb());
        System.out.println("AI inh + exh " + AI());
        System.out.println("bb exhalation" + exh_bb());
        System.out.println("BB exhalation" + exh_BB());
        System.out.println("ET exhalation" + exh_ET());


        System.out.println("\n\nET inhalation (percentage): " + inh_ET_perc());
        System.out.println("BB inhalation (percentage): " + inh_BB_perc());
        System.out.println("bb inhalation (percentage): " + inh_bb_perc());
        System.out.println("AI inh + exh  (percentage): " + AI_perc());
        System.out.println("bb exhalation (percentage): " + exh_bb_perc());
        System.out.println("BB exhalation (percentage): " + exh_BB_perc());
        System.out.println("ET exhalation (percentage): " + exh_ET_perc());

        System.out.println("\n\nLUNG BB+bb+AI: " + sum_BB_bb_AI());
        System.out.println("ET inh+exh: " + sum_ET());
        System.out.println("BB inh+exh: " + (sum_BB()));
        System.out.println("bb inh+exh: " + (sum_bb()));
        System.out.println("AI:         " + AI_perc());
        System.out.println("Total Lung+ET: " + sum_Lung_ET());
    }











    //THERMO



    double Cc(){
        double A1 = 1.257;
        double A2 = 0.4;
        double A3 = 0.55;
        double l = 68 * Math.pow(10, -9);

        return 1 + ( 2 * l / ( pSize * Math.pow(10, -6) ) * ( A1 + A2 * Math.exp( -A3 * pSize * ( Math.pow( 10, -6) / l))));
    }



    double D(){
        double kB = 1.38 * Math.pow(10, -23);
        int T = 293;
        double mu = 1.8369 * Math.pow(10, -5);

        return 10000 * kB * T * Cc() / ( 3 * Math.PI * mu * pSize * Math.pow(10, -6) );
    }

    double Tinh_ET(){
       double a = 9;
       double R = D() * Math.pow( Va() * SFt, -0.25);
       double p = 0.5;

       return 1 - Math.exp(-a * Math.pow(R, p));
    }

    double Tinh_ET_perc(){
        return Tinh_ET() * 100;
    }



    double tBinh() {
        double vd_BB = subject.getVd_BB();
        double FRC = subject.getFRC();

        return (vd_BB / getFlowInh()) * (1 + 0.5 * (vol / FRC));
    }

    double tBexh() {
        double vd_BB = subject.getVd_BB();
        double FRC = subject.getFRC();

        return (vd_BB / getFlowExh()) * (1 + 0.5 * (vol / FRC));
    }



    double Tinh_BB(){
        double a = aBB;
        double R = D() * tBinh();
        double p = 0.6391;

        return 1 - Math.exp(-a * Math.pow(R, p));
    }
    double Tinh_BB_perc(){
        return Tinh_BB() * fact_BB() * ( 1 - Tinh_ET()) * 100;
    }

    double Tinh_bb(){
        double a = -76.8 + ( 167 * Math.pow( SFb, 0.65 ) );
        double R = D() * tbInh();
        double p = 0.5676;

        return 1 - Math.exp(-a * Math.pow(R, p));
    }
    double Tinh_bb_perc(){
        return Tinh_bb() * fact_bb() * ( 1 - Tinh_ET() ) * ( 1 - Tinh_BB() ) * 100;
    }

    double T_AI() {
        double a = 170 + ( 103 * Math.pow( SFa, 2.13 ) );
        double R = D() * ta();
        double p = 0.6101;

        return 1 - Math.exp(-a * Math.pow(R, p));
    }

    double T_AI_perc() {
        return T_AI() * fact_AI() * ( 1 - Tinh_ET() ) * ( 1 - Tinh_BB() ) * ( 1- Tinh_bb()) * 100;
    }

    double Texh_bb() {
        return Tinh_bb();
    }

    double Texh_bb_perc(){
        return Texh_bb() * fact_bb() * ( 1 - Tinh_ET() ) * ( 1 - Tinh_BB() ) * ( 1- Tinh_bb()) * ( 1 - T_AI() ) *100;
    }

    double Texh_BB() {
        return Tinh_BB();
    }

    double Texh_BB_perc(){
        return Texh_BB() * fact_BB() * ( 1 - Tinh_ET() ) * ( 1 - Tinh_BB() ) * ( 1- Tinh_bb()) * ( 1 - T_AI() ) * ( 1 - Texh_bb() ) * 100;
    }

    double Texh_ET() {
        return Tinh_ET();
    }

    double Texh_ET_perc(){
        return Texh_ET() * ( 1 - Tinh_ET() ) * ( 1 - Tinh_BB() ) * ( 1- Tinh_bb()) *
                ( 1 - T_AI() ) * ( 1 - Texh_bb() ) * ( 1 - Texh_BB() ) * 100;
    }




    double Tsum_BB_bb_AI() {
        return (Tinh_BB_perc() + Tinh_bb_perc() + T_AI_perc() + Texh_bb_perc() + Texh_BB_perc());
    }

    double Tsum_ET() {
        return Tinh_ET_perc() + Texh_ET_perc();
    }

    double Tsum_BB() {
        return Tinh_BB_perc() + Texh_BB_perc();
    }

    double Tsum_bb() {
        return Tinh_bb_perc() + Texh_bb_perc();
    }

    double Tsum_Lung_ET() {
        return ( Tinh_BB_perc() + Tinh_bb_perc() + T_AI_perc() + Texh_bb_perc() + Texh_BB_perc())
                + ( Tinh_ET_perc() + Texh_ET_perc());
    }

    public void printThermo(){
        System.out.println("\n\n\nD: " + D());
        System.out.println("Cc: " + Cc());
        System.out.println("inhET: " + Tinh_ET_perc());
        System.out.println("inhBB: " + Tinh_BB_perc());
        System.out.println("inhkisbb: " + Tinh_bb_perc());
        System.out.println("AI: " + T_AI_perc());
        System.out.println("exhkisbb: " + Texh_bb_perc());
        System.out.println("exhBB: " + Texh_BB_perc());
        System.out.println("exhET: " + Texh_ET_perc());



        System.out.println("\nLUNG BB+bb+AI T: " + Tsum_BB_bb_AI());
        System.out.println("ET inh+exh T: " + Tsum_ET());
        System.out.println("BB inh+exh T: " + Tsum_BB());
        System.out.println("bb inh+exh T: " + ( Tsum_bb()));
        System.out.println("AI T:         " + T_AI_perc());
        System.out.println("Total Lung+ET T: " + Tsum_Lung_ET());
    }











    //RDE


    double RDE_inh_ET(){
        return Math.pow((Math.pow( inh_ET_perc(), 2) + Math.pow( Tinh_ET_perc(), 2)), 0.5);
    }
    double RDE_inh_BB(){
        return Math.pow((Math.pow( inh_BB_perc(), 2) + Math.pow( Tinh_BB_perc(), 2)), 0.5);
    }
    double RDE_inh_bb(){
        return Math.pow((Math.pow( inh_bb_perc(), 2) + Math.pow( Tinh_bb_perc(), 2)), 0.5);
    }
    double RDE_AI(){
        return Math.pow((Math.pow( AI_perc(), 2) + Math.pow( T_AI_perc(), 2)), 0.5);
    }
    double RDE_exh_bb(){
        return Math.pow((Math.pow( exh_bb_perc(), 2) + Math.pow( Texh_bb_perc(), 2)), 0.5);
    }
    double RDE_exh_BB(){
        return Math.pow((Math.pow( exh_BB_perc(), 2) + Math.pow( Texh_BB_perc(), 2)), 0.5);
    }
    double RDE_exh_ET(){
        return Math.pow((Math.pow( exh_ET_perc(), 2) + Math.pow( Texh_ET_perc(), 2)), 0.5);
    }


    double sumRDE_BB_bb_AI(){
        return (RDE_inh_BB() + RDE_inh_bb() + RDE_AI() + RDE_exh_bb() + RDE_exh_BB());
    }



    double sumRDE_ET() {
        return RDE_inh_ET() + RDE_exh_ET();
    }

    double sumRDE_BB() {
        return RDE_inh_BB() + RDE_exh_BB();
    }

    double sumRDE_bb() {
        return RDE_inh_bb() + RDE_exh_bb();
    }

    double sumRDE_Lung_ET() {
        return ( RDE_inh_BB() + RDE_inh_bb() + RDE_AI() + RDE_exh_bb() + RDE_exh_BB())
                + ( RDE_inh_ET() + RDE_exh_ET());
    }

    public List<String> printRDE(){
        List<String> l = new ArrayList<>();

        l.add("TOTAL Lung+ET:       " + sumRDE_Lung_ET());
        l.add("\nLUNG BB+bb+AI:     " + sumRDE_BB_bb_AI());
        l.add("\nET inh+exh:              " + sumRDE_ET());
        l.add("\nBB inh+exh:              " + sumRDE_BB());
        l.add("\nbb inh+exh:              " + ( sumRDE_bb()));
        l.add("\nAI:                               " + RDE_AI());

        return l;
    }

    public double getLung(){
        return sumRDE_BB_bb_AI();
    }
    public double getET(){
        return sumRDE_ET();
    }
    public double getBB(){
        return sumRDE_BB();
    }
    public double get_bb(){
        return sumRDE_bb();
    }
    public double getAI(){
        return RDE_AI();
    }
    public double getTotal(){
        return sumRDE_Lung_ET();
    }
}

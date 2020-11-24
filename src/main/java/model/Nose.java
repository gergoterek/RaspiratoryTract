package model;

import subject.Subject;

public class Nose {

    Subject subject;

    private final double inhTime;
    private final double exhTime;
    private final double vol;
    private final double pSize;

    private final double fi;
    private final double aBB;



    public Nose (final Subject sub){

        subject = sub;


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
        fi = 1 + 100 * Math.exp( -Math.pow( Math.log( 100 + 10 / Math.pow( getFlowInh(), 0.9) ), 2));
        aBB = 22.02 * Math.pow(subject.getSFa(), 1.24) * fi;


    }

    double getFlowInh() {
        return vol / inhTime;
    }
    double getFlowExh() {
        return vol / exhTime;
    }

    double inh_ET1() {
        double a = 3.0 * 0.0001;

        double SFa = subject.getSFa();
        double R = Math.pow(pSize, 2) * Math.pow(getFlowInh() * Math.pow(SFa, 3), 0.6) * Math.pow(vol * Math.pow(SFa, 2), -0.2);
        double p = 1.0;

        return 1 - Math.exp(-a * Math.pow(R, p));
    }

    double inh_ET2() {
        double a = 5.5 * 0.0001;

        double SFa = subject.getSFa();
        double R = Math.pow(pSize, 2) * Math.pow(getFlowInh() * Math.pow(SFa, 3), 0.6) * Math.pow(vol * Math.pow(SFa, 2), -0.2);
        double p = 1.17;

        return 1 - Math.exp(-a * Math.pow(R, p));
    }



}

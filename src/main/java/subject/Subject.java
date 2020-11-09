package subject;

import activity.Activity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class Subject {

//    public Subject(Activity act){
//       setActivity(act);
//    }
    public List<Double> values;




    Activity activity;

    private double FRC;
    private double Vd_ET;
    private double Vd_BB;
    private double Vd_bb;
    private double Vd_total;
    private double height;
    private double d0;
    private double d9;
    private double d16;
    private double SFt;
    private double SFb;
    private double SFa;



    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public double getFRC() {
        return FRC;
    }

    public void setFRC(double FRC) {
        this.FRC = FRC;
    }

    public double getVd_ET() {
        return Vd_ET;
    }

    public void setVd_ET(double vd_ET) {
        Vd_ET = vd_ET;
    }

    public double getVd_BB() {
        return Vd_BB;
    }

    public void setVd_BB(double vd_BB) {
        Vd_BB = vd_BB;
    }

    public double getVd_bb() {
        return Vd_bb;
    }

    public void setVd_bb(double vd_bb) {
        Vd_bb = vd_bb;
    }

    public double getVd_total() {
        return Vd_total;
    }

    public void setVd_total(double vd_total) {
        Vd_total = vd_total;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getD0() {
        return d0;
    }

    public void setD0(double d0) {
        this.d0 = d0;
    }

    public double getD9() {
        return d9;
    }

    public void setD9(double d9) {
        this.d9 = d9;
    }

    public double getD16() {
        return d16;
    }

    public void setD16(double d16) {
        this.d16 = d16;
    }

    public double getSFt() {
        return SFt;
    }

    public void setSFt(double SFt) {
        this.SFt = SFt;
    }

    public double getSFb() {
        return SFb;
    }

    public void setSFb(double SFb) {
        this.SFb = SFb;
    }

    public double getSFa() {
        return SFa;
    }

    public void setSFa(double SFa) {
        this.SFa = SFa;
    }




}

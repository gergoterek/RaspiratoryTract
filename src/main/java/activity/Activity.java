package activity;


import lombok.Data;
import subject.Subject;

@Data
public abstract class Activity extends Subject {

    private double Fnorm;
    private double Dmouth;

    private double B;
    private double fR;
    private double Vt;
    private double V;

    public double getFnorm() {
        return Fnorm;
    }

    public void setFnorm(double fnorm) {
        Fnorm = fnorm;
    }

    public double getDmouth() {
        return Dmouth;
    }

    public void setDmouth(double dmouth) {
        Dmouth = dmouth;
    }

    public double getB() {
        return B;
    }

    public void setB(double b) {
        B = b;
    }

    public double getFR() {
        return fR;
    }

    public void setFR(double fR) {
        this.fR = fR;
    }

    public double getVt() {
        return Vt;
    }

    public void setVt(double vt) {
        Vt = vt;
    }

    public double getV() {
        return V;
    }

    public void setV(double v) {
        V = v;
    }

}

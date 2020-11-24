package subject;

import activity.Activity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Female extends Subject{
    public Female (Activity activity, List<Double> customParams ) {

        setParams();
        if(activity != null) {
            setActParam(activity);
            super.setActivity(activity);
        } else {
            super.setActivity(null);
        }
    }


    private void setParams (){
        super.setFRC(2681);
        super.setVd_ET(40);
        super.setVd_BB(40);
        super.setVd_bb(44);
        super.setVd_total(124);
        super.setHeight(163);
        super.setD0(1.53);
        super.setD9(0.159);
        super.setD16(0.048);
        super.setSFt(1.08);
        super.setSFb(1.04);
        super.setSFa(1.07);
    }

    private void setActParam ( Activity act ){
        switch (act.getClass().getSimpleName() ) {
            case "Sleep":
                act.setFnorm(1.0);
                act.setDmouth(0.7);
                act.setB(0.32);
                act.setFR(12);
                act.setVt(444);
                act.setV(178);
                break;

            case "Sitting":
                act.setFnorm(1.0);
                act.setDmouth(0.7);
                act.setB(0.39);
                act.setFR(14);
                act.setVt(464);
                act.setV(217);
                break;

            case "LightExcercise":
                act.setFnorm(1.0);
                act.setDmouth(0.4);
                act.setB(1.25);
                act.setFR(21);
                act.setVt(992);
                act.setV(694);
                break;

            case "HeavyExcercise":
                act.setFnorm(1.0);
                act.setDmouth(0.7);
                act.setB(2.7);
                act.setFR(33);
                act.setVt(1364);
                act.setV(1500);
                break;
        }

    }

}

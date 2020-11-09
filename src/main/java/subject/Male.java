package subject;


import activity.Activity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Male extends Subject {




   public Male ( Activity activity, List<Double> customParams ) {
       setParams();
       setActParam(activity);
       super.setActivity(activity);

       super.values = customParams;
   }


   private void setParams(){
       super.setFRC(3301);
       super.setVd_ET(50);
       super.setVd_BB(49);
       super.setVd_bb(47);
       super.setVd_total(146);
       super.setHeight(176);
       super.setD0(1.65);
       super.setD9(0.165);
       super.setD16(0.051);
       super.setSFt(1);
       super.setSFb(1);
       super.setSFa(1);
   }

   private void setActParam( Activity act ){
       switch ( act.getClass().getSimpleName() ) {
           case "Sleep":
               act.setFnorm(1.0);
               act.setDmouth(0.7);
               act.setB(0.45);
               act.setFR(12);
               act.setVt(625);
               act.setV(250);
               break;

           case "Sitting":
               act.setFnorm(1.0);
               act.setDmouth(0.7);
               act.setB(0.54);
               act.setFR(12);
               act.setVt(750);
               act.setV(300);
               break;

           case "LightExcercise":
               act.setFnorm(1.0);
               act.setDmouth(0.4);
               act.setB(1.5);
               act.setFR(20);
               act.setVt(1250);
               act.setV(833);
               break;

           case "HeavyExcercise":
               act.setFnorm(1.0);
               act.setDmouth(0.7);
               act.setB(3);
               act.setFR(26);
               act.setVt(1920);
               act.setV(1670);
               break;
       }
   }
}

package View;

import activity.*;
import model.Mouth;
import subject.Female;
import subject.Male;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivityComboBox extends JComboBox implements ActionListener {


    String[] activities = { "-", "HeavyExcercise", "LightExcercise", "Sitting", "Sleep"};

    public static Activity act;
    public String s = "-";


    public ActivityComboBox(){
        for ( int i = 0; i<activities.length; ++i){
            addItem(activities[i]);
        }
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae){

        String sub = String.valueOf(this.getSelectedItem());

        s = sub;
        switch (sub) {
            case "-":
//                System.out.println("OPTION: " + sub);
                SubjectComboBox.subj = null;
                break;
            case "HeavyExcercise":
//                System.out.println(sub);
                act = new HeavyExcercise();
                break;
            case "LightExcercise":
//                System.out.println(sub);
                act = new LightExcercise();
                break;
            case "Sitting":
//                System.out.println(sub);
                act = new Sitting();
                break;
            case "Sleep":
//                System.out.println(sub);
                act = new Sleep();
                break;
            default:
                break;
        }

   }
//        if (SubjectComboBox.subj != null){
//            if (SubjectComboBox.subj.getClass().getSimpleName().equals("Male")){
//                SubjectComboBox.subj = new Male(act, null);
//            } else {
//                SubjectComboBox.subj = new Female(act, null);
//            }
//
//        }



//    public class Button extends JButton implements ActionListener {
//        private int counter = 0;
//
//        public Button(){
//            addActionListener(this);
//            setText("Kattintasok szama: " + counter);
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent ae){
//            ++counter;
//            refreshText();
//            //System.out.println(counter);
//        }
//
//        private void refreshText(){
//            setText("Kattintasok szama: " + counter);
//        }
//    }
}

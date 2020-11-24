package View;

import activity.*;
import model.Mouth;
import subject.Female;
import subject.Male;
import subject.Subject;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static View.SubjectComboBox.subj;
import static View.SubjectComboBox.createdAct;

public class ActivityComboBox extends JComboBox implements ActionListener {


    String[] activities = { "-", "HeavyExcercise", "LightExcercise", "Sitting", "Sleep", "Custom"};

    public static Activity act = new HeavyExcercise();
    public String s = "-";


    public ActivityComboBox(){
        for ( int i = 0; i<activities.length; ++i){
            addItem(activities[i]);
        }
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        String sub;
        sub = String.valueOf(this.getSelectedItem());

        s = sub;
        switch (sub) {
            case "-":
                SubjectComboBox.subj = null;
                Window.ta.setEditable(false);
                Window.ta2.setEditable(false);
                Window.ta3.setEditable(false);
                Window.ta4.setEditable(false);
                break;
            case "HeavyExcercise":
                SubjectComboBox.createdAct = new HeavyExcercise();
                changeLabels(SubjectComboBox.createdAct);
                break;
            case "LightExcercise":
                SubjectComboBox.createdAct = new LightExcercise();
                changeLabels(SubjectComboBox.createdAct);
                break;
            case "Sitting":
                SubjectComboBox.createdAct = new Sitting();
                changeLabels(SubjectComboBox.createdAct);
                break;
            case "Sleep":
                SubjectComboBox.createdAct = new Sleep();
                changeLabels(SubjectComboBox.createdAct);
                break;
            case "Custom":
                SubjectComboBox.createdAct = null;
                changeCustomLabels();
                break;
            default:
                break;
        }
   }

   void changeLabels(Activity act){
        if(SubjectComboBox.subj != null){
            if (SubjectComboBox.subj.getClass().getSimpleName().equals("Male")){
                subj = new Male(createdAct, Window.values());
            } else {
                subj = new Female(createdAct, Window.values());
            }
            Window.ta.setText(String.valueOf(subj.getActivity().getV()));

        }
       Window.ta.setEditable(false);
       Window.ta2.setEditable(true);
       Window.ta3.setEditable(true);
       Window.ta4.setEditable(true);
   }

   void changeCustomLabels(){
       if(SubjectComboBox.subj != null) {
           if (SubjectComboBox.subj.getClass().getSimpleName().equals("Male")) {
               subj = new Male(createdAct, Window.values());
           } else {
               subj = new Female(createdAct, Window.values());
           }
           Window.ta.setEditable(true);
           Window.ta2.setEditable(true);
           Window.ta3.setEditable(true);
           Window.ta4.setEditable(true);
       }
   }
}

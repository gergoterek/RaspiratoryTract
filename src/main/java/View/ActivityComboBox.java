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
                SubjectComboBox.subj = null;
                break;
            case "HeavyExcercise":
                act = new HeavyExcercise();
                break;
            case "LightExcercise":
                act = new LightExcercise();
                break;
            case "Sitting":
                act = new Sitting();
                break;
            case "Sleep":
                act = new Sleep();
                break;
            default:
                break;
        }
   }
}

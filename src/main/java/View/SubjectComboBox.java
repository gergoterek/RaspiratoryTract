package View;

import activity.Activity;
import model.Nose;
import subject.Female;
import subject.Male;
import subject.Subject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubjectComboBox extends JComboBox implements ActionListener {

    String[] subjects = { "-", "Male", "Female"};
    public static Subject subj;

    public String s = "-";

    public static Activity createdAct;


    public SubjectComboBox(){
        for ( int i = 0; i<subjects.length; ++i){
            addItem(subjects[i]);
        }
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae){

        String sub = String.valueOf(this.getSelectedItem());
        s = sub;
        switch (sub) {
            case "-":
                System.out.println("OPTION: " + sub);
                subj = null;
                break;
            case "Male":
                subj = new Male(createdAct, Window.values());
                changeLabel();

                break;
            case "Female":
                subj = new Female(createdAct, Window.values());
                changeLabel();
                break;
            default:
                break;
        }

    }

    public static void changeLabel(){
        if(createdAct != null) {
            Window.ta.setText(String.valueOf(subj.getActivity().getV()));
        }
    }
}

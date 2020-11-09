package View;

import activity.Activity;
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

    static Activity createdAct;


    public SubjectComboBox(){
        for ( int i = 0; i<subjects.length; ++i){
            addItem(subjects[i]);
        }
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae){

        if ( ActivityComboBox.act != null) {
            createdAct = ActivityComboBox.act;


            String sub = String.valueOf(this.getSelectedItem());
            s = sub;
            switch (sub) {
                case "-":
                    System.out.println("OPTION: " + sub);
                    subj = null;
                    break;
                case "Male":
                    subj = new Male(createdAct, Window.values());
                    Window.ta.setText(String.valueOf(subj.getActivity().getV()));
                    break;
                case "Female":
                    subj = new Female(createdAct, Window.values());
                    Window.ta.setText(String.valueOf(subj.getActivity().getV()));
                    break;
                default:
                    break;
            }
        }
    }
}

package View;

import activity.Activity;
import activity.HeavyExcercise;
import subject.Female;
import subject.Male;
import subject.Subject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Mouth;

public class SubjectComboBox extends JComboBox implements ActionListener {

    String[] subjects = { "-", "Male", "Female", "Custom"};
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

//            if (SubjectComboBox.subj != null){
//                SubjectComboBox.subj = null;
//                ActivityComboBox.act = null;
//            }

            String sub = String.valueOf(this.getSelectedItem());
            s = sub;
            switch (sub) {
                case "-":
                    System.out.println("OPTION: " + sub);
                    subj = null;
                    break;
                case "Male":
//                    System.out.println("OPTION: " + sub);
                    subj = new Male(createdAct, Window.values());
                    Window.ta.setText(String.valueOf(subj.getActivity().getV()));
                    break;
                case "Female":
//                    System.out.println("OPTION: " + sub);
                    subj = new Female(createdAct, Window.values());
                    Window.ta.setText(String.valueOf(subj.getActivity().getV()));
                    break;
//                case "Custom":
//                    System.out.println("OPTION: " + sub);
//                    Window.ta.setText(String.valueOf(subj.getActivity().getV()));
//                    break;
                default:
                    break;
            }
        }
    }



//    public List<String> mo(Subject sub) {
//
//
//        mouth = new Mouth(sub);
//
//        mouth.print();
//        mouth.printThermo();
//        return mouth.printRDE();
//    }




}

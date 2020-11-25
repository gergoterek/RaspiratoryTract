package View;

import activity.Activity;
import model.Breath;
import model.Mouth;
import model.Nose;
import subject.Female;
import subject.Male;
import subject.Subject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BreathComboBox extends JComboBox implements ActionListener {

    String[] modes = { "-", "Mouth", "Nose"};

    public String s = "-";
    //public static Breath mode;
    public static String mode = "";


    public BreathComboBox(){
        for ( int i = 0; i<modes.length; ++i){
            addItem(modes[i]);
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
                mode = "";
                break;
            case "Mouth":
                mode = "Mouth";
                break;
            case "Nose":
                mode = "Nose";
                break;
            default:
                break;
        }

    }
}

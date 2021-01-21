package View;

import model.Mouth;
import model.Nose;
import subject.Male;
import subject.Subject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static View.SubjectComboBox.subj;
import static View.SubjectComboBox.createdAct;

public class CalculateButton extends JButton implements ActionListener {


    StringBuilder sb = new StringBuilder();


    AbstractAction buttonPressed = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
//            System.out.println("most: " + e.getActionCommand());
        }
    };

    CalculateButton() {
        this.setEnabled(true);
        setText("Calculate RDE");
        addActionListener(this);

        this.addActionListener(buttonPressed);

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), this.getText());
        this.getActionMap().put(this.getText(), new Click(this));


    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if (subj != null && Window.errorLabel.getText().equals("") && !BreathComboBox.mode.equals("")) {

            subj.values = Window.values();
            sb.setLength(0);
            if(BreathComboBox.mode.equals("Mouth")) {
                Mouth mouth = new Mouth(subj);
                if (Window.ta4.isVisible()) {
                    Window.setChartVisible(new ArrayList<Double>
                            (Arrays.asList(mouth.getTotal(), mouth.getET(), mouth.getBB(), mouth.get_bb(), mouth.getAI(), mouth.getLung())));
                    for (String a : mouth.printRDE()) {
                        sb.append(a);
                    }
                } else {
                    Window.setChartVisible(new ArrayList<Double>
                            (Arrays.asList(mouth.getTotalMMAD(), mouth.getETMMAD(), mouth.getBBMMAD(), mouth.get_bbMMAD(), mouth.getAIMMAD(), mouth.getLungMMAD())));
                    for (String a : mouth.printRDEMMAD()) {
                        sb.append(a);
                    }
                }
            } else {
                Nose nose = new Nose(subj);
                if (Window.ta4.isVisible()) {
                    Window.setChartVisible( new ArrayList<Double>
                            (Arrays.asList(nose.getTotal(), nose.getET2(), nose.getBB(), nose.get_bb(), nose.getAI(), nose.getLung(), nose.getET1())));
                    for (String a : nose.printRDE()) {
                        sb.append(a);
                    }
                } else {
                    Window.setChartVisible(new ArrayList<Double>
                            (Arrays.asList(nose.getTotalMMAD(), nose.getET1MMAD(), nose.getET2MMAD(), nose.getBBMMAD(), nose.get_bbMMAD(), nose.getAIMMAD(), nose.getLungMMAD())));
                    for (String a : nose.printRDEMMAD()) {
                        sb.append(a);
                    }
                }
            }
            Window.textArea.setText(sb.toString());
            Window.subjectLabel.setText("Result: ");
        }
    }

    public class Click extends AbstractAction
    {
        JButton button;
        public Click(JButton button)
        {
            this.button = button;
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            button.doClick();
        }
    }

//    public List<String> mo(Subject sub) {
//        mouth = new Mouth(sub);
//        mouth.print();
//        mouth.printThermo();
//        return mouth.printRDE();
//    }
//
//        public Mouth getMouth(){
//            return mouth;
//        }
}

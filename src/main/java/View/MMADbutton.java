package View;

import model.Mouth;
import model.Nose;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import static View.SubjectComboBox.subj;
import static View.Window.*;

public class MMADbutton extends JButton implements ActionListener {

    MMADbutton() {
        this.setEnabled(true);
        setText("Use MMAD");
        addActionListener(this);
        setMargin(new Insets(0,10,0,10));

        //this.addActionListener();

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), this.getText());
        //this.getActionMap().put(this.getText(), new CalculateButton.Click(this));


    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if( ta4.isVisible()){
            ta4.setVisible(false);
            Window.l4.setVisible(false);
            this.setText("Calculate with particle diameter");

            ji1.setVisible(true);
            ji2.setVisible(true);
            ji3.setVisible(true);
            ji4.setVisible(true);
        } else {
            ta4.setVisible(true);
            Window.l4.setVisible(true);
            this.setText("Calculate with MMAD");

            ji1.setVisible(false);
            ji2.setVisible(false);
            ji3.setVisible(false);
            ji4.setVisible(false);
        }
    }
}

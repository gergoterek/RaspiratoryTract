package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

class ScrollPanePanel extends JPanel implements MouseWheelListener {
    private JLabel infoLabel;
    private JScrollPane scrollPane;
    public static JPanel panel;

    public ScrollPanePanel() {

        panel = new JPanel(new GridLayout(0, 1));
        for (int i = 1; i <= 100; i++) {
            panel.add(new JLabel("Label " + i));
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.isControlDown()) {
            if (e.getWheelRotation() < 0) {
                infoLabel.setText("Mouse Wheel Up");
            } else {
                infoLabel.setText("Mouse Wheel Down");
            }
        } else {
            scrollPane.getListeners(MouseWheelListener.class)[0].mouseWheelMoved(e);
        }
    }

    public void set(){
        panel.addMouseWheelListener(this);
        scrollPane = new JScrollPane(panel);

        infoLabel = new JLabel(" ");
        JPanel infoPanel = new JPanel();
        infoPanel.add(infoLabel);

        setLayout(new BorderLayout());
        add(scrollPane);
        add(infoPanel, BorderLayout.SOUTH);
    }
}
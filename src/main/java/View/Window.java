package View;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;

import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;


import org.jfree.ui.TextAnchor;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Window extends JFrame {
    ActivityComboBox acb;
    SubjectComboBox scb;
    public static CalculateButton cb;
    BreathComboBox bcb;

    public static JTextArea textArea;
    public static JLabel subjectLabel;
    public static JLabel errorLabel;
    public static String txt;

    JPanel p;

    static JTextField ta;
    static JTextField ta2;
    static JTextField ta3;
    static JTextField ta4;

    static DefaultCategoryDataset dcd;
    ChartPanel r;



    public Window() {

        acb = new ActivityComboBox();
        scb = new SubjectComboBox();
        cb = new CalculateButton();
        bcb = new BreathComboBox();

        textArea = new JTextArea();
        subjectLabel = new JLabel("Choose subject and activity to calculate!");

        txt = "";

        addWindowListener(new ExitAdapter());

        setTitle("ICRP");
        setSize(1100, 860);

        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
        );


        JPanel mainPanel = new JPanel(new FlowLayout());
        mainPanel.add(bcb);
        mainPanel.add(scb);
        mainPanel.add(acb);
        mainPanel.add(cb);
        getContentPane().add(mainPanel);


        JPanel j1 = new JPanel(new FlowLayout());
        JLabel l1 = new JLabel("Inhaled air volume (ml): ");
        j1.add(l1);
        ta = new JTextField("3000");
        setInputFields(ta, true);
        j1.add(ta);
        getContentPane().add(j1);

        JPanel j2 = new JPanel(new FlowLayout());
        JLabel l2 = new JLabel("Inhalation time (sec): ");
        j2.add(l2);
        ta2 = new JTextField("2");
        setInputFields(ta2, false);
        j2.add(ta2);
        getContentPane().add(j2);

        JPanel j3 = new JPanel(new FlowLayout());
        JLabel l3 = new JLabel("Exhalation time (sec): ");
        j3.add(l3);
        ta3 = new JTextField("2");
        setInputFields(ta3, false);
        j3.add(ta3);
        getContentPane().add(j3);

        JPanel j4 = new JPanel(new FlowLayout());
        JLabel l4 = new JLabel("Particle diameter (μm): ");
        j4.add(l4);
        ta4 = new JTextField("0.005");
        setInputFields(ta4, false);
        j4.add(ta4);
        getContentPane().add(j4);

        Window.ta.setEditable(false);
        Window.ta2.setEditable(false);
        Window.ta3.setEditable(false);
        Window.ta4.setEditable(false);


        errorLabel = new JLabel("");
        getContentPane().add(errorLabel);

        if (SubjectComboBox.subj != null){
            ta.setText(String.valueOf(SubjectComboBox.subj.getActivity().getV()));
        }


        p = new JPanel();

        textArea.setText(txt);
        textArea.setEditable(false);
        textArea.setFont(new Font("Calibri", Font.ITALIC, 16));
        textArea.setWrapStyleWord(false);
        textArea.setOpaque(false);

        JPanel outer = new JPanel(new BorderLayout());
        p.add(textArea);
        outer.add(p, BorderLayout.NORTH);
        getContentPane().add(outer);

        dcd = new DefaultCategoryDataset();
        JFreeChart jFreeChart = ChartFactory.createStackedBarChart3D(
                "Deposition distribution",
                "Region of raspiratory tract",
                "Deposition fraction (%)",
                dcd, PlotOrientation.VERTICAL,
                true,
                false,
                false);

        CategoryPlot plot = jFreeChart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLACK);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        DecimalFormat df = new DecimalFormat("##.##");
        DecimalFormat df2 = new DecimalFormat("#.##");

        renderer.setItemLabelGenerator( new StandardCategoryItemLabelGenerator("{2} %", df2));
        plot.setRenderer(renderer);

        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.TOP_CENTER));
        renderer.setItemLabelsVisible(true);
        jFreeChart.getCategoryPlot().setRenderer(renderer);

        r = new ChartPanel(jFreeChart);
        r.setPreferredSize(new Dimension(1100, 490));
        r.setMinimumSize(new Dimension(500, 490));
        getContentPane().add(r);

//        JPanel outer = new JPanel(new BorderLayout());
//        p.add(textArea);
//        outer.add(p, BorderLayout.NORTH);
//        getContentPane().add(outer);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void setSB (StringBuilder sb){
        txt = sb.toString();
    }

    public class ExitAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }




    public static ArrayList<Double> values(){
        ArrayList<Double> a = new ArrayList<>();
        try {
            a.add(Double.parseDouble(ta.getText()));
        }catch (Exception e){a.add(null);}
        try {
            a.add(Double.parseDouble(ta2.getText()));
        }catch (Exception e){a.add(null);}
        try {
            a.add(Double.parseDouble(ta3.getText()));
        }catch (Exception e){a.add(null);}
        try {
            a.add(Double.parseDouble(ta4.getText()));
        }catch (Exception e){a.add(null);}

        return a;
    }


    public static void setChartVisible(ArrayList<Double> res){

        dcd.clear();
        if (res.size() == 6) {
            dcd.setValue(res.get(0), "TOTAL", "LUNG + ET");
            dcd.setValue(res.get(5), "LUNG", "BB + bb + AI");
            dcd.setValue(res.get(1), "ET", "ET");
            dcd.setValue(res.get(2), "BB", "BB");
            dcd.setValue(res.get(3), "bb", "bb");
            dcd.setValue(res.get(4), "AI", "AI");
        } else {
            dcd.setValue(res.get(0), "TOTAL", "LUNG + ET");
            dcd.setValue(res.get(5), "LUNG", "BB + bb + AI");
            dcd.setValue(res.get(6), "ET1", "ET1");
            dcd.setValue(res.get(1), "ET2", "ET2");
            dcd.setValue(res.get(2), "BB", "BB");
            dcd.setValue(res.get(3), "bb", "bb");
            dcd.setValue(res.get(4), "AI", "AI");
        }
    }


    void setInputFields(JTextField ta, boolean isVol){

//        ta.setBounds(10, 20, 8, 2);
        ta.setPreferredSize(new Dimension(80, 20));
        ta.setMaximumSize(new Dimension(80, 20));
        ta.setFont(new Font("Calibri", Font.ITALIC, 16));
        ta.setMinimumSize(new Dimension(80, 20));

        ta.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }
            public void warn() {
                try {
                    double num = Double.parseDouble(ta.getText());
                    if(isVol && num < 150.8){
                        throw new NumberFormatException();
                    } else {
                        errorLabel.setText("");
                        cb.setEnabled(true);
                    }
                } catch (NumberFormatException nfe) {
                    errorLabel.setText("Please enter a valid number!");
                    errorLabel.setForeground(Color.RED);
                    cb.setEnabled(false);
                }
            }
        });
    }
}

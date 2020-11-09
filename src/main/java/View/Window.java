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
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Window extends JFrame {
    ActivityComboBox acb;
    SubjectComboBox scb;
    CalculateButton cb;

    public static JTextArea textArea;
    public static JLabel subjectLabel;
    public static JLabel errorLabel;
    public static String txt;

    JPanel p;
    JPanel p2;
    JPanel p3;
    JPanel p4;

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

        textArea = new JTextArea();
        subjectLabel = new JLabel("Choose subject and activity to calculate!");

        txt = "";

        addWindowListener(new ExitAdapter());

        setTitle("ICRP");
        setSize(1100, 800);
//        setLayout(new FlowLayout());
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
        );


        JPanel mainPanel = new JPanel(new FlowLayout());
        mainPanel.add(acb);
        mainPanel.add(scb);
        mainPanel.add(cb);
        getContentPane().add(mainPanel);


        JPanel j1 = new JPanel(new FlowLayout());
        JLabel l1 = new JLabel("Lung volumetric (ml): ");
        j1.add(l1);
        ta = new JTextField("3000");
        setInputFields(ta);
        j1.add(ta);
        getContentPane().add(j1);

        JPanel j2 = new JPanel(new FlowLayout());
        JLabel l2 = new JLabel("Inhalation time (sec): ");
        j2.add(l2);
        ta2 = new JTextField("2");
        setInputFields(ta2);
        j2.add(ta2);
        getContentPane().add(j2);

        JPanel j3 = new JPanel(new FlowLayout());
        JLabel l3 = new JLabel("Exhalation time (sec): ");
        j3.add(l3);
        ta3 = new JTextField("2");
        setInputFields(ta3);
        j3.add(ta3);
        getContentPane().add(j3);

        JPanel j4 = new JPanel(new FlowLayout());
        JLabel l4 = new JLabel("Particle size (mm): ");
        j4.add(l4);
        ta4 = new JTextField("0.005");
        setInputFields(ta4);
        j4.add(ta4);
        getContentPane().add(j4);


        errorLabel = new JLabel("");
        getContentPane().add(errorLabel);

        if (SubjectComboBox.subj != null){
            ta.setText(String.valueOf(SubjectComboBox.subj.getActivity().getV()));
        }


        p = new JPanel();
        p2 = new JPanel();

        textArea.setText(txt);
        textArea.setEditable(false);
        textArea.setFont(new Font("Calibri", Font.ITALIC, 16));
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        p.add(textArea);

        JPanel outer = new JPanel(new BorderLayout());
        outer.add(p, BorderLayout.NORTH);
        getContentPane().add(outer);

//        p2.add(j1, j2);
//        p2.add(j3, j4);
//        outer.add(p2, BorderLayout.WEST);
//        outer.add(p2, BorderLayout.NORTH);




        dcd = new DefaultCategoryDataset();
        JFreeChart jFreeChart = ChartFactory.createStackedBarChart3D(
                "TOTAL",
                "Part of raspiratory tract",
                "Percentage (%)",
                dcd, PlotOrientation.VERTICAL,
                true,
                false,
                false);

        CategoryPlot plot = jFreeChart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLACK);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        DecimalFormat df = new DecimalFormat("##.##");
        renderer.setItemLabelGenerator( new StandardCategoryItemLabelGenerator("{2}", df));
        plot.setRenderer(renderer);
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.TOP_CENTER));
        renderer.setItemLabelsVisible(true);
        jFreeChart.getCategoryPlot().setRenderer(renderer);

        r = new ChartPanel(jFreeChart);
        r.setMinimumSize(new Dimension(500, 450));
        getContentPane().add(r);


//        ValueMarker vm = new ValueMarker(50);
//        vm.setLabel("hefe");
//        vm.setLabelAnchor(RectangleAnchor.BOTTOM);
//        vm.setLabelTextAnchor(TextAnchor.BOTTOM_RIGHT);
//        vm.setPaint(Color.BLACK);

//        plot.addRangeMarker(vm);




        //        ChartFrame r = new ChartFrame("heello", jFreeChart, true);
//        r.setSize(400, 300);
//        r.setVisible(true);


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

        dcd.setValue(res.get(0), "TOTAL", "LUNG + ET");
        dcd.setValue(res.get(5), "LUNG", "BB + bb + AI");
        dcd.setValue(res.get(1), "ET", "ET");
        dcd.setValue(res.get(2), "BB", "BB");
        dcd.setValue(res.get(3), "bb", "bb");
        dcd.setValue(res.get(4), "AI", "AI");
    }


    void setInputFields(JTextField ta){

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
//                        System.out.println("valtozas");
                    errorLabel.setText("");

//                        if (SubjectComboBox.subj != null){
//                            SubjectComboBox.subj = null;
//                            ActivityComboBox.act = null;
//                        }

                } catch (NumberFormatException nfe) {
                    errorLabel.setText("Please enter a valid number!");
                }
            }
        });
    }

}

//        @Override
//        public void actionPerformed(ActionEvent e) {
//            model.setValue(…);
//        }
//        button.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                doStuff();
//            }
//        });


//    private void fr(){
//        JPanel panel = new JPanel();
//        JFrame frame = new JFrame();
//        frame.setSize(800,480);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(panel);
//
//        panel.setLayout(null);
//        JLabel subjectLabel = new JLabel("Subject");
//        subjectLabel.setBounds(10, 20, 80, 25);
//        panel.add(subjectLabel);
//
//        JTextField subjectText = new JTextField();
//        subjectText.setBounds(100, 20, 165, 25);
//        panel.add(subjectText);
//
//        JLabel actLabel = new JLabel("Activity");
//        actLabel.setBounds(10, 50, 80, 25);
//        panel.add(actLabel);
//
//        frame.setVisible(true);
//    }




//textArea.getDocument().addDocumentListener(new DocumentListener() {
//public void changedUpdate(DocumentEvent e) {
//        warn();
//        }
//public void removeUpdate(DocumentEvent e) {
//        warn();
//        }
//public void insertUpdate(DocumentEvent e) {
//        warn();
//        }
//
//public void warn() {
////                if (Integer.parseInt(textArea.getText())<=0){
////                    JOptionPane.showMessageDialog(null,
////                            "Error: Please enter number bigger than 0", "Error Message",
////                            JOptionPane.ERROR_MESSAGE);
////                }
//        System.out.println("valtozas");
//        }
//        });


//    private void showExitConfirmation() {
//        int n = JOptionPane.showConfirmDialog(this, "Valóban ki akar lépni?",
//                "Megerősítés", JOptionPane.YES_NO_OPTION);
//        if (n == JOptionPane.YES_OPTION) {
//            doUponExit();
//        }
//    }
//
//    protected void doUponExit() {
//        this.dispose();
//    }


//fr();
//        setSize(400, 450);
//        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//        addWindowListener(new WindowAdapter() {
//
//            @Override
//            public void windowClosing(WindowEvent e) {
//                showExitConfirmation();
//            }
//
//        });

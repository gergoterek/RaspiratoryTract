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
    public static String resultText;
    public static JTabbedPane tabbedPane;

    JPanel p;

    static JTextField ta;
    static JTextField ta2;
    static JTextField ta3;
    static JTextField ta4;
    static JLabel l4;

    static JTextField tai;
    static JTextField tai2;
    static JTextField tai3;
    static JTextField tai4;

    static DefaultCategoryDataset dcd;
    ChartPanel r;

    static JPanel firstPanel;
    //static JPanel secondPanel;

    static JPanel ji1;
    static JPanel ji2;
    static JPanel ji3;
    static JPanel ji4;

    JPanel jp;

    public Window() {

        initPanels();
        addWindowListener(new ExitAdapter());
        inputPanel(firstPanel);

        errorLabel = new JLabel("");
        firstPanel.add(errorLabel);

        if (SubjectComboBox.subj != null){
            ta.setText(String.valueOf(SubjectComboBox.subj.getActivity().getV()));
        }

        //resultText
        resultTextArea();
        p = new JPanel();
        p.add(textArea);
        JPanel outer = new JPanel(new BorderLayout());
        outer.add(p, BorderLayout.NORTH);
        firstPanel.add(outer);



        //diagram
        dcd = new DefaultCategoryDataset();
        createDiagram(dcd, firstPanel);

        //tabs
        tabbedPane.add("First panel",firstPanel);
        tabbedPane.setAutoscrolls(true);
        //tabbedPane.revalidate();
        //JScrollPane jsp = new JScrollPane();
        //getContentPane().add(jsp);
        getContentPane().add(tabbedPane);

        add(new ScrollPanePanel());


        setLocationRelativeTo(null);
        setVisible(true);
    }

    void resultTextArea(){
        subjectLabel = new JLabel("Choose subject and activity to calculate!");
        textArea = new JTextArea();
        resultText = "";
        textArea.setText(resultText);
        textArea.setEditable(false);
        textArea.setFont(new Font("Calibri", Font.ITALIC, 16));
        textArea.setWrapStyleWord(false);
        textArea.setOpaque(false);
    }

    void initTextFields(){
        ta.setEditable(false);
        ta2.setEditable(false);
        ta3.setEditable(false);
        ta4.setEditable(false);
    }

    void initPanels(){

        firstPanel = new JPanel();
        firstPanel.setLayout(
                new BoxLayout(firstPanel, BoxLayout.Y_AXIS)
        );

        jp = new JPanel();
        jp.setLayout(new GridLayout(1,2));


        setTitle("ICRP");
        setSize(1100, 860);

        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
        );

        acb = new ActivityComboBox();
        scb = new SubjectComboBox();
        cb = new CalculateButton();
        bcb = new BreathComboBox();
        tabbedPane = new JTabbedPane();
    }

    void inputPanel(JPanel firstPanel){
        JPanel mainPanel = new JPanel(new FlowLayout());
        mainPanel.add(bcb);
        mainPanel.add(scb);
        mainPanel.add(acb);
        mainPanel.add(cb);
        //getContentPane().add(mainPanel);
        firstPanel.add(mainPanel);


        JPanel j1 = new JPanel(new FlowLayout());
        JLabel l1 = new JLabel("Inhaled air volume (ml): ");
        j1.add(l1);
        ta = new JTextField("3000");
        setInputFields(ta, true);
        j1.add(ta);
        //getContentPane().add(j1);
        firstPanel.add(j1);

        JPanel j2 = new JPanel(new FlowLayout());
        JLabel l2 = new JLabel("Inhalation time (sec): ");
        j2.add(l2);
        ta2 = new JTextField("2");
        setInputFields(ta2, false);
        j2.add(ta2);
        firstPanel.add(j2);

        JPanel j3 = new JPanel(new FlowLayout());
        JLabel l3 = new JLabel("Exhalation time (sec): ");
        j3.add(l3);
        ta3 = new JTextField("2");
        setInputFields(ta3, false);
        j3.add(ta3);
        firstPanel.add(j3);

        JPanel j4 = new JPanel(new FlowLayout());
        l4 = new JLabel("Particle diameter (μm): ");
        j4.add(l4);
        ta4 = new JTextField("5");
        setInputFields(ta4, false);
        j4.add(ta4);

        //MMAD input field
        MMADinput();

        //MMAD button
        MMADbutton mmad = new MMADbutton();
        j4.add(mmad);
        firstPanel.add(j4);
    }

    void MMADinput(){
        ji1 = new JPanel(new FlowLayout());
        JLabel li1 = new JLabel("MMAD (μm): ");
        li1.setForeground(Color.BLUE);
        ji1.add(li1);
        tai = new JTextField("3.93");
        setInputFields(tai, false);
        ji1.add(tai);
        //getContentPane().add(j1);
        firstPanel.add(ji1);

        ji2 = new JPanel(new FlowLayout());
        JLabel li2 = new JLabel("Fine particle fraction (%): ");
        li2.setForeground(Color.BLUE);
        ji2.add(li2);
        tai2 = new JTextField("14.76");
        setInputFields(tai2, false);
        ji2.add(tai2);
        firstPanel.add(ji2);

        ji3 = new JPanel(new FlowLayout());
        JLabel li3 = new JLabel("Geometric standard deviation: ");
        li3.setForeground(Color.BLUE);
        ji3.add(li3);
        tai3 = new JTextField("2");
        setInputFields(tai3, false);
        ji3.add(tai3);
        firstPanel.add(ji3);

        ji4 = new JPanel(new FlowLayout());
        JLabel li4 = new JLabel("Emitted dosis (%): ");
        li4.setForeground(Color.BLUE);
        ji4.add(li4);
        tai4 = new JTextField("71.94");
        setInputFields(tai4, false);
        ji4.add(tai4);
        firstPanel.add(ji4);


        ji1.setVisible(false);
        ji2.setVisible(false);
        ji3.setVisible(false);
        ji4.setVisible(false);
    }

    public static void setSB (StringBuilder sb){
        resultText = sb.toString();
    }

    public class ExitAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    void createDiagram(DefaultCategoryDataset dcd, JPanel firstPanel){
        JFreeChart jFreeChart = ChartFactory.createStackedBarChart3D(
                "Deposition distribution",
                "Region of raspiratory tract",
                "Deposition fraction (%)",
                dcd, PlotOrientation.VERTICAL,
                true,
                false,
                false
        );

        CategoryPlot plot = jFreeChart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setRangePannable(true);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        DecimalFormat df = new DecimalFormat("##.##");
        DecimalFormat df2 = new DecimalFormat("#.##");

        renderer.setItemLabelGenerator( new StandardCategoryItemLabelGenerator("{2} %", df2));
        plot.setRenderer(renderer);

        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.TOP_CENTER));
        renderer.setItemLabelsVisible(true);
        jFreeChart.getCategoryPlot().setRenderer(renderer);

        r = new ChartPanel(jFreeChart);
        r.setPreferredSize(new Dimension(1100, 390));
        r.setMinimumSize(new Dimension(500, 390));
        firstPanel.add(r);
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



        if(!ta4.isVisible()) {
            try {
                a.add(Double.parseDouble(tai.getText()));
            } catch (Exception e) {
                a.add(null);
            }
            try {
                a.add(Double.parseDouble(tai2.getText()));
            } catch (Exception e) {
                a.add(null);
            }
            try {
                a.add(Double.parseDouble(tai3.getText()));
            } catch (Exception e) {
                a.add(null);
            }
            try {
                a.add(Double.parseDouble(tai4.getText()));
            } catch (Exception e) {
                a.add(null);
            }
        }

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
                        System.out.println(num);
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

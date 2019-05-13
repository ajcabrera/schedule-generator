package schedule.gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ProgramLoad extends JFrame implements ActionListener{
    private static String user;
    private static JButton helpButton;
    private JTextArea helpText;
    private final JFileChooser openFileChooser;

    private JButton loadEI = new JButton("Load file");
    private JButton newEI = new JButton("New file");
    private JButton okButton = new JButton("Start");
    private JRadioButton chooseAdmin = new JRadioButton("Admin");
    private JRadioButton chooseUser = new JRadioButton("User");
    private ButtonGroup radios = new ButtonGroup();

    private JLabel UPC = new JLabel("FIB - UPC (Universitat Politècnica de Catalunya) ");
    private JLabel nameEducationalInstitution = new JLabel("");

    public ProgramLoad() {
        super("Schedule-Generator");
        JTextArea helpText = new JTextArea("");
        JPanel mainPanel = new JPanel();

        JTextArea route = new JTextArea();
        route.setMargin(new Insets(10,10,10,10));


        radios.add(chooseAdmin);
        radios.add(chooseUser);
        chooseAdmin.setSelected(true);

        user = chooseAdmin.isSelected() ? "Admin" : "User" ;

        openFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        openFileChooser.setCurrentDirectory(new File("c:\\temp"));
        openFileChooser.setFileFilter(filter);



        chooseAdmin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newEI.setEnabled(true);
                user = "Admin";
            }
        });

        chooseUser.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newEI.setEnabled(false);
                route.setText("");
                user = "User";
            }
        });

        okButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String fullPath = route.getText();
                int i = fullPath.lastIndexOf('/');
                int j = fullPath.lastIndexOf('.');
                String ei = j < 0 ? fullPath.substring(i+1) : fullPath.substring(i+1, j);
                if(!fullPath.equals("") && ei.length() > 0) {
                    MainFrame mf = new MainFrame(route.getText(),user,ei);
                    closeWelcomeScreen();
                }
            }
        });

        newEI.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                route.setText("Write here a name for your file");
                route.setFocusable(true);
            }
        });

        loadEI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int retVal = openFileChooser.showOpenDialog(null);
                if(retVal == JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(null,"File successfully loaded");
                    route.setText(openFileChooser.getSelectedFile().getAbsolutePath());
                    route.setFocusable(false);
                    PresentationControl pc = PresentationControl.getInstance();
                    pc.load(openFileChooser.getSelectedFile().getAbsolutePath());
                }
                else JOptionPane.showMessageDialog(null,"No file selected");
            };
        });

        setLayout(new BorderLayout());

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        JPanel row1 = new JPanel(); row1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JPanel row2 = new JPanel(); row2.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel row3 = new JPanel(); row3.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel row4 = new JPanel(); row4.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel row5 = new JPanel(); row5.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel row6 = new JPanel(); row6.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JPanel row7 = new JPanel(); row7.setLayout(new FlowLayout(FlowLayout.RIGHT));

        row1.setBorder(BorderFactory.createEtchedBorder(0));
        row1.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));
        row2.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));
        row3.setBorder(BorderFactory.createEtchedBorder(1));
        row3.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));
        row4.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));
        row5.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));
        row6.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));
        row7.setBorder(BorderFactory.createEtchedBorder(1));
        row7.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));

        mainPanel.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));

        Dimension widDim = new Dimension(1000,50);

        /*

        row1.setPreferredSize(widDim);
        row2.setPreferredSize(widDim);
        row3.setPreferredSize(widDim);
        row4.setPreferredSize(widDim);
        row5.setPreferredSize(widDim);
        row6.setPreferredSize(widDim);
        row7.setPreferredSize(widDim);
        */



        gc.gridy = 0; gc.weighty = 0.1; mainPanel.add(row1,gc);
        gc.gridy++; gc.weighty = 0.3; mainPanel.add(row2,gc);
        gc.gridy++; gc.weighty = 0.1; mainPanel.add(row3,gc);
        gc.gridy++; gc.weighty = 0.125; mainPanel.add(row4,gc);
        gc.gridy++; gc.weighty = 0.125; mainPanel.add(row5,gc);
        gc.gridy++; gc.weighty = 0.15; mainPanel.add(row6,gc);
        gc.gridy++; gc.weighty = 0.1; mainPanel.add(row7,gc);

        add(mainPanel, BorderLayout.CENTER);
        mainPanel.setVisible(true);


/*
-------------------------------------
        TITLE PANEL
-------------------------------------
 */

        JLabel title = new JLabel("Schedule-Generator");
        Font letra = title.getFont();
        int fontSize = letra.getSize();
        title.setFont(new Font( letra.getName(), Font.BOLD, fontSize*5));


        JPanel auxiliar = new JPanel();
        auxiliar.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));
        auxiliar.setPreferredSize(widDim);
        row1.add(auxiliar);

        JButton helpButton = new JButton("Show Help");

        row1.add(helpButton);
        helpButton.addActionListener(this);


        row2.add(title);


/*
-------------------------------------
        RADIO BUTTONS - Admin/User
-------------------------------------
 */

        chooseAdmin.setPreferredSize(new Dimension(150, 80));
        chooseUser.setPreferredSize(new Dimension(150, 80));

        chooseAdmin.setFont(new Font( letra.getName(), Font.BOLD, (int)(fontSize*1.7)));
        chooseUser.setFont(new Font( letra.getName(), Font.BOLD, (int)(fontSize*1.7)));

        row3.add(chooseAdmin);
        row3.add(chooseUser);

        helpText.setFocusable(false);
/*
-------------------------------------
        BUTTONS - New/Load
-------------------------------------
 */
        newEI.setPreferredSize(new Dimension(200, 60));
        loadEI.setPreferredSize(new Dimension(200, 60));

        newEI.setFont(new Font( letra.getName(), Font.PLAIN, (int)(fontSize*1.3)));
        loadEI.setFont(new Font( letra.getName(), Font.PLAIN, (int)(fontSize*1.3)));

        JPanel sepButtons = new JPanel();
        sepButtons.setPreferredSize(new Dimension(25,25));
        sepButtons.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));


        row4.add(newEI);
        row4.add(sepButtons);
        row4.add(loadEI);


/*
-------------------------------------
        ROUTE + OK
-------------------------------------
 */

        route.setPreferredSize(new Dimension(400, 50));
        okButton.setPreferredSize(new Dimension(150, 50));

        route.setBorder(BorderFactory.createEtchedBorder());
        route.setFocusable(false);
        row5.add(route);
        row6.add(okButton);

/*
-------------------------------------
        Lower Panel - UPC
-------------------------------------
 */

        row7.setBorder(BorderFactory.createEtchedBorder(0));
        row7.add(UPC);

/*
----------------------------------------
        Window dimensions and features
----------------------------------------
 */
        setSize(1280,720);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void closeWelcomeScreen() {
        this.dispose();
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");

        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                ProgramLoad PL = new ProgramLoad();
            }
        });

    }


    private String getDescription(String key){
        Descriptor d = null;
        return d.getDescription(key);
    }

    @Override
    public void actionPerformed(ActionEvent a) {
            JOptionPane.showConfirmDialog(this, getDescription("welcomeHelp"), "HELP", JOptionPane.DEFAULT_OPTION);
    }
}

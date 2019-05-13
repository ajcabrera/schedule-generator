package schedule.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ToolBar extends JPanel implements ActionListener{
    private MainFrame parentWindow;
    private PresentationControl pc = PresentationControl.getInstance();
    private String key;
    private JLabel userLabel;
    private JLabel pathLabel;
    private String path;
    private String loadedPanel;

    private JButton exitBtn = new JButton("Exit");
    private JButton helpBtn = new JButton("Help");
    private StringListener textListener;

    public ToolBar(MainFrame MF, String user, String edInst) {
        setBorder(BorderFactory.createEtchedBorder(0));
        setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));

        parentWindow = MF;
        key = "welcomeHelp";
        userLabel = new JLabel(user);

        path = " >  " + edInst; //Actually get from Educational Institution
        pathLabel = new JLabel(path);


        // GRID (1 x 11)
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 8;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(5,10,0, 5);
        add(pathLabel,gc);

        gc.gridx = 8;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,350,0, 0);
        add(userLabel,gc);



        gc.gridx = 9;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,2,0, 0);
        gc.ipadx = -40;
        add(helpBtn, gc);

        gc.gridx = 10;
        add(exitBtn,gc);



        helpBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        //Set up Alt+Key shortcuts
        exitBtn.setMnemonic(KeyEvent.VK_X);
        helpBtn.setMnemonic(KeyEvent.VK_H);
    }

    public void updatePath(String s) {
        path = "Path:  > " + s;
        pathLabel.setText(path);
    }

    public void actionPerformed(ActionEvent a) {
        JButton clicked = (JButton)a.getSource();

        if (clicked == exitBtn) {
            int result = JOptionPane.showConfirmDialog(parentWindow, "Are you sure you want to exit?\nMake sure you have saved before confirmation", "EXIT", JOptionPane.OK_CANCEL_OPTION);
            if(result == JOptionPane.OK_OPTION) System.exit(1); //Exit by user click
        }
        else if (clicked == helpBtn) {
            Descriptor d = null;
            JOptionPane.showConfirmDialog(parentWindow, d.getDescription(key), "HELP", JOptionPane.DEFAULT_OPTION);
        }
    }

    public void setLoadedPanel(String s){
        String p = "";
        loadedPanel = s;
        switch (s){
            case "Welcome":                 key = "welcomeHelp"; break;
            case "Buildings":               key = "bHelp";  p = pc.getBuildingPath(); break;
            case "Classrooms":              key = "cHelp";  p = pc.getClassroomPath(); break;
            case "Degrees":                 key = "dHelp";  p = pc.getDegreePath(); break;
            case "AcademicPlans":           key = "apHelp"; p = pc.getAcademicPlanPath(); break;
            case "Subjects":                key = "sHelp";  p = pc.getSubjectPath(); break;
            case "Progress":                key = "pHelp"; break;
            case "ChooseView":              key = "chHelp"; break;
            case "Schedule":                key = "gsHelp"; break;
            case "Modify":                  key = "modHelp"; break;
            default: key = "eiHelp"; p = pc.getEducationalInstitutionPath();
        } updatePath(p);
    }

    public String getLoadedPanel(){
        return loadedPanel;
    }
}

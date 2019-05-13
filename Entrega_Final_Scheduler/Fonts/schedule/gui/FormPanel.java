package schedule.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class FormPanel extends JPanel implements ActionListener {
    private PresentationControl pc = PresentationControl.getInstance();
    protected StringListener textListener;
    protected String path;
    protected JButton cancelChanges = new JButton("Back");
    protected JButton acceptChanges = new JButton("Accept");
    protected JLabel nameLabel = new JLabel("Name:");
    protected JTextField name = new JTextField(12);
    protected GridBagConstraints gc = new GridBagConstraints();

    protected JButton cequipments = new JButton("Equipments");

    protected JButton classrooms = new JButton("Classrooms");
    protected JButton bequipments = new JButton("Equipments");

    protected JButton groupTypes = new JButton("Group Types");
    protected JButton corequisite = new JButton("Corequisite");

    protected JButton subjects = new JButton("Subjects");
    protected JButton levels = new JButton("Levels");

    protected JButton acaplans = new JButton("Academic Plans");

    protected JButton buildings = new JButton("Buildings");
    protected JButton equipments = new JButton("Equipments");
    protected JButton degrees = new JButton("Degrees");
    protected JButton restrictions = new JButton("Restrictions");
    protected JButton saveChanges = new JButton("Save Changes");
    protected JButton genSchedule = new JButton("Generate Schedule");

    protected Dimension saveBackDim = new Dimension(130,55);
    protected Dimension itemDim = new Dimension(170, 40);
    protected Dimension panDim = new Dimension(300,1000);

    protected JPanel upPanel = new JPanel();
    protected JPanel row1 = new JPanel();
    protected JPanel row2 = new JPanel();
    protected JPanel row3 = new JPanel();
    protected JPanel row4 = new JPanel();
    protected JPanel row5 = new JPanel();
    protected JPanel row6 = new JPanel();
    protected JPanel row8 = new JPanel();





    public FormPanel(){
        path = new String("");
        setLayout(new BorderLayout());
        setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));

        setMinimumSize(panDim);
        upPanel.setLayout(new GridBagLayout());
        add(upPanel, BorderLayout.NORTH);

        gc.insets = new Insets(30,100,10, 100);
        gc.gridy = 0; upPanel.add(row1,gc); row1.setLayout(new FlowLayout(FlowLayout.CENTER));
        gc.gridy++; upPanel.add(row2,gc); row2.setLayout(new FlowLayout(FlowLayout.CENTER));
        gc.gridy++; upPanel.add(row3,gc); row3.setLayout(new FlowLayout(FlowLayout.CENTER));
        gc.gridy++; upPanel.add(row4,gc); row4.setLayout(new FlowLayout(FlowLayout.CENTER));
        gc.gridy++; upPanel.add(row5,gc); row5.setLayout(new FlowLayout(FlowLayout.CENTER));
        gc.gridy++; upPanel.add(row6,gc); row6.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(row8, BorderLayout.SOUTH); row8.setLayout(new FlowLayout(FlowLayout.RIGHT));


        row1.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));
        row2.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));
        row3.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));
        row4.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));
        row5.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));
        row6.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));
        row8.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));
        upPanel.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));





        cancelChanges.setPreferredSize(saveBackDim);
        acceptChanges.setPreferredSize(saveBackDim);

        // OK / Cancel Buttons
            //Set up button listeners;
        cancelChanges.addActionListener(this);
        acceptChanges.addActionListener(this);
            //Set up Alt+Key shortcuts
        cancelChanges.setMnemonic(KeyEvent.VK_BACK_SPACE);
        acceptChanges.setMnemonic(KeyEvent.VK_ENTER);


        // Educational Institution
            //Set up button listeners
        buildings.addActionListener(this);
        buildings.setPreferredSize(itemDim);
        equipments.addActionListener(this);
        equipments.setPreferredSize(itemDim);
        degrees.addActionListener(this);
        degrees.setPreferredSize(itemDim);
        restrictions.addActionListener(this);
        restrictions.setPreferredSize(itemDim);
        saveChanges.addActionListener(this);
        saveChanges.setPreferredSize(saveBackDim);
        genSchedule.addActionListener(this);
        genSchedule.setPreferredSize(saveBackDim);
            //Set up Alt+Key shortcuts
        buildings.setMnemonic(KeyEvent.VK_B);
        equipments.setMnemonic(KeyEvent.VK_Q);
        degrees.setMnemonic(KeyEvent.VK_D);
        restrictions.setMnemonic(KeyEvent.VK_R);
        saveChanges.setMnemonic(KeyEvent.VK_S);
        genSchedule.setMnemonic(KeyEvent.VK_G);


        // Degree
            //Set up button listeners
        acaplans.addActionListener(this);
        acaplans.setPreferredSize(itemDim);
            //Set up Alt+Key shortcuts
        acaplans.setMnemonic(KeyEvent.VK_P);


        // Academic Plan
            //Set up button listeners
        subjects.addActionListener(this);
        subjects.setPreferredSize(itemDim);
        levels.addActionListener(this);
        levels.setPreferredSize(itemDim);
            //Set up Alt+Key shortcuts
        subjects.setMnemonic(KeyEvent.VK_S);
        levels.setMnemonic(KeyEvent.VK_L);


        // Subjects
            //Set up button listeners
        groupTypes.addActionListener(this);
        groupTypes.setPreferredSize(itemDim);
        corequisite.addActionListener(this);
        corequisite.setPreferredSize(itemDim);
            //Set up Alt+Key shortcuts
        groupTypes.setMnemonic(KeyEvent.VK_G);
        corequisite.setMnemonic(KeyEvent.VK_C);


        // Buildings
            //Set up button listeners
        classrooms.addActionListener(this);
        classrooms.setPreferredSize(itemDim);
        bequipments.addActionListener(this);
        bequipments.setPreferredSize(itemDim);
           //Set up Alt+Key shortcuts
        classrooms.setMnemonic(KeyEvent.VK_C);
        bequipments.setMnemonic(KeyEvent.VK_Q);

        // Classrooms
            //Set up button listeners
        cequipments.addActionListener(this);
        cequipments.setPreferredSize(itemDim);
            //Set up Alt+Key shortcuts
        cequipments.setMnemonic(KeyEvent.VK_Q);
    }



    public void setStringListener(StringListener sl){
        textListener = sl;
    }

    public void loadEmptyDescriptionPanel(){
        textListener.textSent("Default");
    }

    public String getPath(){
        return path;
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        JButton clicked = (JButton)a.getSource();

        if(textListener != null) {
            if (clicked == cancelChanges) {
                textListener.textSent("Back");
            }
            else if (clicked == acceptChanges) {
                textListener.textSent("Accept");
            }                                       // -------------------------------------------------EducationalInstitution
            else if (clicked == saveChanges) {
                textListener.textSent("Save");
                path = name.getText();
                textListener.updatePath(path);
            }
            else if (clicked == genSchedule) {
                textListener.textSent("Generate");
            }
            else if (clicked == buildings) textListener.descriptionLoader(0);
            else if (clicked == equipments) textListener.descriptionLoader(1);
            else if (clicked == degrees) textListener.descriptionLoader(2);
            else if (clicked == restrictions) textListener.descriptionLoader(3);
                                                    // -------------------------------------------------Degree
            else if (clicked == acaplans) textListener.descriptionLoader(0);
                                                    // -------------------------------------------------AcademicPlan
            else if (clicked == subjects) textListener.descriptionLoader(0);
            else if (clicked == levels) textListener.descriptionLoader(1);
                                                    // -------------------------------------------------Subject
            else if (clicked == groupTypes) textListener.descriptionLoader(0);
            else if (clicked == corequisite) textListener.descriptionLoader(1);
                                                    // -------------------------------------------------Building
            else if (clicked == classrooms) textListener.descriptionLoader(0);
            else if (clicked == bequipments) textListener.descriptionLoader(1);
                                                    // -------------------------------------------------Classroom
            else if (clicked == cequipments) textListener.descriptionLoader(0);
        }
    }

    public String getItemName(){
        return name.getText();
    }

    public void setItemName(String s){
        name.setText(s);
    }


}

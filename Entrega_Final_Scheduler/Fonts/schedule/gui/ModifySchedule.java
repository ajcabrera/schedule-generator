package schedule.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModifySchedule extends JDialog implements ActionListener {

    private PresentationControl pc = PresentationControl.getInstance();
    private JButton okButton = new JButton("OK");
    private JButton cancelButton = new JButton("CANCEL");

    private JTextArea description = new JTextArea("");

    private JComboBox desplDay = new JComboBox();
    private JComboBox desplHour = new JComboBox();
    private JComboBox desplBu = new JComboBox();
    private JComboBox desplCl = new JComboBox();

    private JLabel day = new JLabel("Day: ");
    private JLabel hour = new JLabel("Hour: ");
    private JLabel labelBd = new JLabel("Building: ");
    private JLabel labelCl = new JLabel("Classroom: ");

    private DefaultComboBoxModel buData = new DefaultComboBoxModel();
    private DefaultComboBoxModel clData = new DefaultComboBoxModel();
    private DefaultComboBoxModel dayData = new DefaultComboBoxModel();
    private DefaultComboBoxModel hourData = new DefaultComboBoxModel();

    private ArrayList<String> lst = new ArrayList<String>();
    private String[] days = {"--Select Day--", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private String[] hours = {"--Select Hour--", "8-9h","9-10h","10-11h","11-12h","12-13h","13-14h",
                            "14-15h","15-16h","16-17h","17-18h","18-19h","19-20h"};

    private GridBagConstraints gc = new GridBagConstraints();

    private JPanel panel = new JPanel();
    private JPanel upPanel = new JPanel();
    private JPanel row1 = new JPanel();
    private JPanel row2 = new JPanel();
    private JPanel row3 = new JPanel();
    private JPanel row4 = new JPanel();
    private JPanel row5 = new JPanel();
    private JPanel row6 = new JPanel();
    private JPanel row7 = new JPanel();

    private String oldBu, oldCl;
    private int oldCol, oldRow;

    private Dimension panDim = new Dimension(900,600);
    private Dimension btnDim = new Dimension(120, 50);
    private Dimension descrDim = new Dimension(500, 65);
    private Dimension desplDim = new Dimension(250, 40);

    private ScheduleCL sched;

    public ModifySchedule(ScheduleCL scl, String ob, String ocl, int oc, int or) {
        super(scl, "Modify Schedule", ModalityType.APPLICATION_MODAL);
        Descriptor d = null;

        sched = scl;
        sched.setEnabled(false);


        oldBu = ob;
        oldCl = ocl;
        oldCol = oc;
        oldRow = or;

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        panel.setLayout(new GridBagLayout());

        gc.insets = new Insets(10, 60, 10, 60);
        gc.gridy = 0;
        panel.add(row1, gc);
        row1.setLayout(new FlowLayout(FlowLayout.CENTER));
        gc.gridy++;
        panel.add(row2, gc);
        row2.setLayout(new FlowLayout(FlowLayout.CENTER));
        gc.gridy++;
        panel.add(row3, gc);
        row3.setLayout(new FlowLayout(FlowLayout.CENTER));
        gc.gridy++;
        panel.add(row4, gc);
        row4.setLayout(new FlowLayout(FlowLayout.CENTER));
        gc.gridy++;
        panel.add(row5, gc);
        row5.setLayout(new FlowLayout(FlowLayout.CENTER));
        gc.gridy++;
        panel.add(row6, gc);
        row6.setLayout(new FlowLayout(FlowLayout.CENTER));
        gc.gridy++;
        panel.add(row7, gc);
        row7.setLayout(new FlowLayout(FlowLayout.RIGHT));

        description.setFocusable(false);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setMargin(new Insets(6, 10, 6, 6));
        description.setText(d.getDescription("modHelp"));

        description.setPreferredSize(descrDim);
        row1.add(description);
        desplDay.setPreferredSize(desplDim);
        desplHour.setPreferredSize(desplDim);
        row2.add(day);
        row2.add(desplDay);
        row3.add(hour);
        row3.add(desplHour);
        desplBu.setPreferredSize(desplDim);
        desplCl.setPreferredSize(desplDim);
        row4.add(labelBd);
        row4.add(desplBu);
        row5.add(labelCl);
        row5.add(desplCl);


        okButton.setPreferredSize(btnDim);
        cancelButton.setPreferredSize(btnDim);
        row7.add(okButton);
        row7.add(cancelButton);


        lst = pc.getBuildings();
        buData.addElement("--Select Building--");
        for (String e : lst) buData.addElement(e);
        desplBu.setModel(buData);

        for (String e : days) dayData.addElement(e);
        desplDay.setModel(dayData);
        for (String e : hours) hourData.addElement(e);
        desplHour.setModel(hourData);


        desplBu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (desplBu.getSelectedIndex() > 0) {
                    String sel = (String) desplBu.getSelectedItem();
                    try {
                        pc.editBuilding(sel);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    lst = pc.getClassrooms();
                    clData = new DefaultComboBoxModel();
                    clData.addElement("--Select Classroom--");
                    for (String e : lst) clData.addElement(e);
                    desplCl.setModel(clData);
                    desplCl.setSelectedIndex(0);
                    pc.cancelClassroom();
                } else {
                    pc.cancelBuilding();
                    desplCl.setModel(new DefaultComboBoxModel());
                    desplCl.setSelectedIndex(-1);
                }
            }
        });

        desplCl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (desplCl.getSelectedIndex() > 0) {
                    String sel = (String) desplCl.getSelectedItem();
                    try {
                        pc.editClassroom(sel);
                        System.out.println(sel);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                } else {
                    pc.cancelClassroom();
                }
            }
        });


        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        pack();
        setSize(panDim);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

        public void actionPerformed (ActionEvent a){
            JButton clicked = (JButton) a.getSource();
            if (clicked == okButton) {
                if (!correctData())
                    JOptionPane.showConfirmDialog(this, "Error! \nPlease, fill all the forms correctly and select a valid value(s) from the menu(s)", "ERROR", JOptionPane.DEFAULT_OPTION);
                else {
                    tryModifySchedule();
                    sched.setEnabled(true);
                    sched.updateSchedule();
                    this.dispose();
                }
            } else if (clicked == cancelButton) {
                sched.setEnabled(true);
                this.dispose();
            }

        }


    private boolean correctData(){

        boolean res = desplBu.getSelectedIndex()>0 && desplCl.getSelectedIndex()>0 &&
                desplDay.getSelectedIndex()>0 && desplHour.getSelectedIndex()>0 ;
        cout(Boolean.toString(res));
        return res;
    }

    private void tryModifySchedule(){
        String nb = (String)desplBu.getSelectedItem();
        String ncl = (String)desplCl.getSelectedItem();
        int nc = desplDay.getSelectedIndex()-1;
        int nr = desplHour.getSelectedIndex()-1;
        if(!pc.updateSchedule(oldBu,oldCl,oldCol,oldRow,nb,ncl,nc,nr)){
            JOptionPane.showConfirmDialog(this, "Error! \nThe requested modification could not be applied", "ERROR", JOptionPane.DEFAULT_OPTION);
        } else {
            JOptionPane.showConfirmDialog(this, "Success! \nThe requested modification was successfully applied", "SUCCESS", JOptionPane.DEFAULT_OPTION);
        }
    }


    private void cout(String s){
        System.out.println(s);
    }

}

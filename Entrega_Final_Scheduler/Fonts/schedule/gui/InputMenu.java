package schedule.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class InputMenu extends JDialog implements ActionListener{
    private PresentationControl pc = PresentationControl.getInstance();

    private JButton okButton = new JButton("OK");
    private JButton cancelButton = new JButton("CANCEL");

    private JButton plusButton1 = new JButton("+");
    private JTextField sesLen = new JTextField(2);
    private JButton minusButton1 = new JButton("-");
    private JButton plusButton2 = new JButton("+");
    private JTextField sesWk = new JTextField(2);
    private JButton minusButton2 = new JButton("-");

    private JTextField alias = new JTextField(22);
    private JTextField inputText = new JTextField();
    private JTextPane description = new JTextPane();
    private MainFrame mf;

    private JComboBox desplegable = new JComboBox();

    private JList lvlSj = new JList();
    private JList totSj = new JList();
    private DefaultListModel lvlSjModel = new DefaultListModel();
    private DefaultListModel totSjModel = new DefaultListModel();
    private JButton btnIN = new JButton("=>");
    private JButton btnOUT = new JButton("<=");


    private JCheckBox switchRestriction = new JCheckBox("Activated");
    private static boolean onOff = true;
    private String type = new String();
    private String mode = new String();
    private String oldAlias;

    private DefaultComboBoxModel datosDesplegable = new DefaultComboBoxModel();
    private ArrayList<String> lst = new ArrayList<String>();

    private Dimension descrDim = new Dimension(400, 40);
    private Dimension btnDim = new Dimension(120, 50);
    private Dimension textDim = new Dimension(250, 40);
    private Dimension panDim = new Dimension(900,600);
    private Dimension panMinDim = new Dimension(700,400);
    private Dimension smallBtn = new Dimension(40,40);
    private Dimension listDim = new Dimension(150,80);
    private Dimension inputDim = new Dimension(300,50);



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

    public InputMenu(MainFrame mf, String type, String user, String mode){
        super(mf,mode+" "+type, ModalityType.APPLICATION_MODAL);
        boolean canEdit = (user != "User");
        this.mf = mf;
        this.type = type;
        this.mode = mode;
        Descriptor d = null;
        mf.setEnabled(false);

        panel.setLayout(new BorderLayout());
        panel.setMinimumSize(panMinDim);
        upPanel.setLayout(new GridBagLayout());
        panel.add(upPanel, BorderLayout.NORTH);

        gc.insets = new Insets(10,60,10, 60);
        gc.gridy = 0; upPanel.add(row1,gc); row1.setLayout(new FlowLayout(FlowLayout.CENTER));
        gc.gridy++; upPanel.add(row2,gc); row2.setLayout(new FlowLayout(FlowLayout.CENTER));
        gc.gridy++; upPanel.add(row3,gc); row3.setLayout(new FlowLayout(FlowLayout.CENTER));
        gc.gridy++; upPanel.add(row4,gc); row4.setLayout(new FlowLayout(FlowLayout.CENTER));
        gc.gridy++; upPanel.add(row5,gc); row5.setLayout(new FlowLayout(FlowLayout.CENTER));
        gc.gridy++; upPanel.add(row6,gc); row6.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(row7, BorderLayout.SOUTH); row7.setLayout(new FlowLayout(FlowLayout.RIGHT));


        alias.setText("--Write Name/Alias here--");
        inputText.setText("--Write Description here--");

        switchRestriction.setSelected(true);
        onOff = switchRestriction.isSelected();


        description.setFocusable(false);
        description.setText(d.getDescription(type));


        //SOUTH PANEL (PERMANENT)
        okButton.setPreferredSize(btnDim);
        cancelButton.setPreferredSize(btnDim);
        if(canEdit) row7.add(okButton);
        row7.add(cancelButton);


        desplegable.setPreferredSize(textDim);
        alias.setMinimumSize(inputDim);
        inputText.setMinimumSize(inputDim);
        alias.setPreferredSize(inputDim);
        description.setMinimumSize(descrDim);
        description.setPreferredSize(descrDim);
        inputText.setPreferredSize(inputDim);
        btnIN.setPreferredSize(smallBtn);
        btnOUT.setPreferredSize(smallBtn);
        lvlSj.setPreferredSize(listDim);
        totSj.setPreferredSize(listDim);

        //Get Layout & Data by type

        oldAlias = "";
        if(!mode.equals("Add")) {
            oldAlias = mf.getSelectedItem();
            alias.setText(oldAlias);
        }


        switch (type){
            case "Equipments":
                row2.add(new JScrollPane(description));
                row3.add(alias);
                row4.add(inputText);
                try {
                    pc.editEquipment(oldAlias);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                inputText.setText(pc.getEquipmentDescription());
                inputText.setVisible(true);
                inputText.setFocusable(canEdit);
                break;
            case "Levels":
                row2.add(new JScrollPane(description));
                row3.add(alias);
                lst = pc.availableSubjectsForLevels();
                for(String e : lst) totSjModel.addElement(e);
                totSj.setModel(totSjModel);

                Vector<String> lvlSjV = new Vector<>();
                if(!mode.equals("Add")) lvlSjV = pc.getLevelSubjects(oldAlias);
                for(String e : lvlSjV) lvlSjModel.addElement(e);
                lvlSj.setModel(lvlSjModel);
                if(lvlSjModel.size() > 0) lvlSj.setSelectedIndex(0);
                if(totSjModel.size() > 0) totSj.setSelectedIndex(0);
                row4.add(new JLabel("Available"));row4.add(new JScrollPane(totSj)); row4.add(btnIN); row4.add(btnOUT); row4.add(new JScrollPane(lvlSj)); row4.add(new JLabel("In the level"));
                row5.add(switchRestriction);
                break;
            case "Corequisite":
                row1.add(new JPanel());
                row2.add(new JScrollPane(description));
                row3.add(new JPanel());
                row4.add(desplegable);
                row5.add(new JPanel());
                row6.add(new JPanel());

                lst = pc.availableSubjectsForCorreq();
                datosDesplegable.addElement("--Select Subject--");

                for(String e : lst) datosDesplegable.addElement(e);
                desplegable.setModel(datosDesplegable);
                break;
            case "Restrictions":
                loadRestrictionPanel();
                break;
            case "Building Equipments":
            case "Classroom Equipments":
                row1.add(new JPanel());
                row3.add(new JScrollPane(description));
                row4.add(new JPanel());
                row5.add(new JPanel());
                row6.add(new JPanel());

                if( mode.equals("Add")) {
                    row2.add(desplegable);
                    String descr = d.getDescription(type);
                    description.setText(descr);
                    lst = pc.getEquipments();
                    datosDesplegable.addElement("--Select Equipment--");
                    for(String e : lst) datosDesplegable.addElement(e);
                    desplegable.setModel(datosDesplegable);
                } else {
                    row2.add(alias);
                    try {
                        pc.editEquipment(oldAlias);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    description.setText(pc.getEquipmentDescription());
                    pc.cancelEquipment();
                    alias.setFocusable(false);
                } break;
            case "Group Types":
                row1.add(new JPanel());
                row3.add(new JScrollPane(description));
                row2.add(alias);
                row4.add(sesLen);
                row5.add(sesWk);
                alias.setText(pc.getOldType());

                if(!mode.equals("Details")) {
                    minusButton1.setPreferredSize(smallBtn);
                    minusButton2.setPreferredSize(smallBtn);
                    plusButton1.setPreferredSize(smallBtn);
                    plusButton2.setPreferredSize(smallBtn);
                    sesLen.setPreferredSize(smallBtn);
                    sesWk.setPreferredSize(smallBtn);

                    row4.add(new JLabel("Session Length:  ")); row4.add(minusButton1); row4.add(sesLen); row4.add(plusButton1);
                    row5.add(new JLabel("Session(s) Week: ")); row5.add(minusButton2); row5.add(sesWk); row5.add(plusButton2);

                    if(mode.equals("Add")) {
                        sesLen.setText("1"); sesWk.setText("1");
                        description.setText("--Write a session size (number bigger than 0)--");
                    }
                    else {

                        sesLen.setText(pc.getLength(alias.getText()));
                        sesWk.setText(pc.getNumSessions(alias.getText()));
                        description.setText(pc.getTypeSize(alias.getText()));
                    }
                    description.setFocusable(true); // SIZE!


                } else {
                    row4.add(new JLabel("Session Length:  ")); row4.add(sesLen);
                    row5.add(new JLabel("Session(s) Week: ")); row5.add(sesWk);

                    sesLen.setText(pc.getLength(alias.getText()));
                    sesWk.setText(pc.getNumSessions(alias.getText()));
                    description.setText(pc.getTypeSize(alias.getText()));

                    alias.setFocusable(false);
                    sesLen.setFocusable(false);
                    sesWk.setFocusable(false);
                }
            default: break;
        }





        //Listeners

        btnIN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int sel = totSj.getSelectedIndex();
                if(sel != -1){
                    lvlSjModel.addElement( totSj.getSelectedValue() );
                    totSjModel.removeElementAt(sel);
                    if(lvlSjModel.size() > 0) lvlSj.setSelectedIndex(0);
                    if(totSjModel.size() > 0) totSj.setSelectedIndex(0);
                }
            }
        });

        btnOUT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int sel = lvlSj.getSelectedIndex();
                if(sel != -1){
                    totSjModel.addElement( lvlSj.getSelectedValue() );
                    lvlSjModel.removeElementAt(sel);
                    if(lvlSjModel.size() > 0) lvlSj.setSelectedIndex(0);
                    if(totSjModel.size() > 0) totSj.setSelectedIndex(0);
                }
            }
        });

        minusButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int sl = Integer.parseInt(sesLen.getText());
                if(sl > 1) sesLen.setText(String.valueOf(sl-1));
            }
        });

        plusButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int sl = Integer.parseInt(sesLen.getText());
                if(sl < 12) sesLen.setText(String.valueOf(sl+1));
            }
        });

        minusButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int sl = Integer.parseInt(sesWk.getText());
                if(sl > 1) sesWk.setText(String.valueOf(sl-1));
            }
        });

        plusButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int sl = Integer.parseInt(sesWk.getText());
                if(sl < 5) sesWk.setText(String.valueOf(sl+1));
            }
        });

        alias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String aux = alias.getText();
                if(!aux.isEmpty() && aux.substring(0,1).equals("-")) alias.setText("");
            }
        });

        inputText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String aux = inputText.getText();
                if(!aux.isEmpty() && aux.substring(0,1).equals("-")) inputText.setText("");
            }
        });

        description.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String aux = description.getText();
                if(!aux.isEmpty() && aux.substring(0,1).equals("-")) description.setText("");
            }
        });

        switchRestriction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                onOff = switchRestriction.isSelected();
            }
        });

        desplegable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(type.equals("Building Equipments") || type.equals("Classroom Equipments")){
                    String sel = (String)desplegable.getSelectedItem();
                    if(desplegable.getSelectedIndex() > 0) {
                        try {
                            pc.editEquipment(sel);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        description.setText(pc.getEquipmentDescription()); //gets the description of existing Equipment (for Bd and Cl)
                        pc.cancelEquipment();
                    } else description.setText(d.getDescription(type));
                }
                if(type.equals("Restrictions")){
                    String sel = (String)desplegable.getSelectedItem();
                    int n = desplegable.getSelectedIndex();
                    if(n > 0) {
                        description.setText(d.getDescription("r" + n) + "\n" + d.getDescription("rf" + n));
                        inputText.setText(d.getDescription("rf" + n));
                    } else {
                        description.setText(d.getDescription("Restrictions"));
                        inputText.setText("");
                    }
                }

            }
        });

        okButton.addActionListener(this);
        cancelButton.addActionListener(this);


        //Basic setup
        panel.setMaximumSize(panDim);
        setResizable(false);
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);



        if( !isVisible() ) mf.setEnabled(true);
    }





    public void actionPerformed(ActionEvent a) {
        JButton clicked = (JButton)a.getSource();
        if(clicked == okButton){
            if( !correctData() )
                JOptionPane.showConfirmDialog(this, "Error! \nPlease, fill all the forms correctly and select a valid value(s) from the menu(s)", "ERROR", JOptionPane.DEFAULT_OPTION);
            else{
                savePopupInformation();
                if(type.equals("Restrictions")){
                    String al = alias.getText();
                    pc.activateRestriction(al,onOff);
                } else if (type.equals("Group Types")){
                    // Si existe mas de un group type, borra los que se llamen "Example"

                }
                mf.setEnabled(true);
                dispose();
            }
        } else if (clicked == cancelButton){
            if(type.equals("Restrictions")){
                pc.activateRestriction(oldAlias,onOff);
            }
            pc.cancelEquipment();
            mf.setEnabled(true);
            dispose();
        }

    }


    private void loadRestrictionPanel(){
        row4.add(new JLabel("Alias:             ")); row4.add(alias);
        row3.add(new JLabel("Parameters:")); row3.add(inputText); inputText.setText("");
        row2.add(new JScrollPane(description));
        row1.add(desplegable);
        row5.add(switchRestriction);

        alias.setPreferredSize(textDim);
        inputText.setPreferredSize(textDim);
        description.setPreferredSize(new Dimension(500, 70));
        this.setPreferredSize(new Dimension(800,500));

        Descriptor d = null;

        lst = pc.getRestrictions();
        datosDesplegable.addElement("--Select Restriction--");
        datosDesplegable.addElement("1: Block Timeframe");
        datosDesplegable.addElement("2: Session type needs equipment");
        datosDesplegable.addElement("3: Balance morning/afternoon");
        datosDesplegable.addElement("4: At least 1 group with accessibility");
        datosDesplegable.addElement("5: Building/Classroom unavailable");
        datosDesplegable.addElement("6: Session type in building/classroom");
        datosDesplegable.addElement("7: Session type before another in week");
        datosDesplegable.addElement("8: Subject w/out sessions this day");
        datosDesplegable.addElement("9: All sessions in different days");
        datosDesplegable.addElement("10: Session in specific schedule position");
        datosDesplegable.addElement("11: Type session this day");
        datosDesplegable.addElement("12: All groups sessions same hour");

        desplegable.setModel(datosDesplegable);

        if(!mode.equals("Add")) {
            desplegable.setSelectedIndex((pc.getRestrictionType(oldAlias)));
            description.setText(d.getDescription("r" + desplegable.getSelectedIndex()) +
                    "\n " + d.getDescription("rf"+ desplegable.getSelectedIndex()));
            Vector<String> p = pc.getRestrictionParams(oldAlias);
            String ptext = "";

            for(int i = 2; i < p.size(); i++) {
                if(!p.get(i).equals("END")) {
                    ptext += p.get(i) + ",";
                }
            }
            inputText.setText(ptext.substring(0,ptext.length()-1));
            switchRestriction.setSelected( p.get(1).equals("1") ? true : false );
        }
    }

    private boolean correctData(){
        String elem = "Valid"; //Random, as long as it doesn't start with "-"
        if(type.equals("Restrictions") || (mode.equals("Add") && (type.equals("Building Equipments") || type.equals("Classroom Equipments") || type.equals("Corequisite") || type.equals("Group Types"))) && desplegable.getSelectedIndex() > 0) elem = (String)desplegable.getSelectedItem();
        boolean elemSelInvalid = elem.substring(0,1).equals("-");
        String auxAlias = alias.getText();
        boolean aliasInvalid = (!type.equals("Corequisite") && !type.equals("Building Equipments") && !type.equals("Classroom Equipments")) && (auxAlias.isEmpty() || auxAlias.substring(0,1).equals("-"));
        String auxDescr = inputText.getText();
        boolean descrInvalid = type.equals("Equipments") && (auxDescr.isEmpty() || auxDescr.substring(0,1).equals("-"));
        boolean isModified = !description.getText().substring(0,1).equals("-");
        boolean numInvalid = (type.equals("Group Types") && isModified && (Integer.parseInt(description.getText()) < 1));

        System.out.println(numInvalid + " - " + elemSelInvalid + " - " + aliasInvalid + " - " + descrInvalid);
        return !( numInvalid || elemSelInvalid || aliasInvalid || descrInvalid );
    }

    private void savePopupInformation(){
        boolean add = mode.equals("Add");
        Vector<String> params = new Vector<>();
        switch(type){
            case "Equipments":
                if(!pc.setEquipmentName(alias.getText())) showError();
                else if(!pc.setEquipmentDescription(inputText.getText())) showError();
                else pc.saveEquipment();
                break;
            case "Levels":
                params.add("15");
                params.add(switchRestriction.isEnabled() ? "1" : "0");
                params.add(pc.getDegreeName());
                params.add(pc.getAcademicPlanName());
                int size = lvlSjModel.getSize();
                for(int i = 0; i < size; i++) params.add((String)lvlSjModel.getElementAt(i));
                if(add && size > 1) pc.newLevel(alias.getText(), params);
                else pc.editLevel(oldAlias,alias.getText(),params);

                break;
            case "Corequisite":
                if(desplegable.getSelectedIndex() < 1) showError();
                else {
                    params.add("16");
                    params.add("1");
                    params.add(pc.getDegreeName());
                    System.out.println(pc.getDegreeName());
                    params.add(pc.getAcademicPlanName());
                    System.out.println(pc.getAcademicPlanName());
                    params.add(pc.getSubjectName());
                    params.add((String)desplegable.getSelectedItem());

                    pc.newCorreq("Corequisite "+params.get(4)+"-"+params.get(5),params);
                }
                break;
            case "Restrictions":
                int n = onOff ? 1 : 0;
                String p = desplegable.getSelectedIndex()+","+n+","+inputText.getText();
                if(add && desplegable.getSelectedIndex() > 0 && !pc.newRestriction(alias.getText(), p)) {
                    cout("Error Add Restriction");
                    showError();
                }
                else if(!add && desplegable.getSelectedIndex() > 0 && !pc.editRestriction(oldAlias, alias.getText(), p)) {
                    cout("Error Edit Restriction");
                    showError();
                }
                break;
            case "Building Equipments":
                if(!add) break;
                else if( desplegable.getSelectedIndex() < 1 ) showError();
                else {
                    pc.addBuildingEquipment((String)desplegable.getSelectedItem());
                }
                break;
            case "Classroom Equipments":
                if(!add) break;
                else if( desplegable.getSelectedIndex() < 1 ) showError();
                else pc.addClassroomEquipment((String)desplegable.getSelectedItem());
                break;
            case "Group Types":
                if(add && !pc.newType(alias.getText(),description.getText(),sesLen.getText(),sesWk.getText())) showError();
                else if(!pc.editType(oldAlias, alias.getText(),description.getText(),sesLen.getText(),sesWk.getText())) showError();
        }

    }

    private void cout(String s){
        System.out.println(s);
    }

    private void showError(){
        JOptionPane.showConfirmDialog(null, "Invalid input\nPlease, fill out all the forms", "Error", JOptionPane.DEFAULT_OPTION);
    }


}

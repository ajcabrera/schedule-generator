package schedule.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class ScheduleCL extends JFrame {
    private JPanel upPanel = new JPanel();
    private JPanel fstRow = new JPanel();
    private JPanel rowFilter = new JPanel();
    private JPanel rowBC = new JPanel();
    private JPanel row3 = new JPanel();
    private JPanel row4 = new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JComboBox desplDg = new JComboBox(); // Degree
    private JComboBox desplAP = new JComboBox(); // AcPlan
    private JComboBox desplSJ = new JComboBox();
    private JComboBox desplBd = new JComboBox();
    private JComboBox desplCl = new JComboBox();
    private DefaultComboBoxModel dataDg = new DefaultComboBoxModel(); // Degree
    private DefaultComboBoxModel dataAP = new DefaultComboBoxModel(); // AcPlan
    private DefaultComboBoxModel dataSJ = new DefaultComboBoxModel(); // Subject
    private DefaultComboBoxModel dataBd = new DefaultComboBoxModel(); // Building
    private DefaultComboBoxModel dataCl = new DefaultComboBoxModel(); // Classroom

    private JCheckBox filter = new JCheckBox("Filter  ");

    private ArrayList<String> lst = new ArrayList<String>();

    private JButton updateBtn = new JButton("UPDATE");
    private JButton backBtn = new JButton("GO BACK");
    private JButton saveBtn = new JButton("SAVE");
    private JButton moveBtn = new JButton("MOVE SESSION");
    private JButton exitBtn = new JButton("Exit");
    private JButton helpBtn = new JButton("Help");

    private JLabel userLabel = new JLabel();
    private JLabel schLabel = new JLabel("SCHEDULE SUCCESSFULLY GENERATED!");
    private JLabel blankLabel = new JLabel("          ");

    private JPanel latPan = new JPanel();

    private GridBagConstraints gc = new GridBagConstraints();

    private Dimension latDim = new Dimension(60,1);
    private Dimension btnDim = new Dimension(120, 50);
    private Dimension desplDim = new Dimension(230,30);
    private Dimension helpDim = new Dimension(100, 35);
    private Dimension genSchDim = new Dimension(800, 40);
    private Dimension panDim = new Dimension(840,400);


    private JTable schedule = new JTable();
    private DefaultTableModel infoTabla = new DefaultTableModel();
    private String[][] tabla;

    private String[][] defaultTable = { {"  8:00 -  9:00 "," - "," - "," - "," - "," - "},
                                        {"  9:00 - 10:00 "," - "," - "," - "," - "," - "},
                                        {" 10:00 - 11:00 "," - "," - "," - "," - "," - "},
                                        {" 11:00 - 12:00 "," - "," - "," - "," - "," - "},
                                        {" 12:00 - 13:00 "," - "," - "," - "," - "," - "},
                                        {" 13:00 - 14:00 "," - "," - "," - "," - "," - "},
                                        {" 14:00 - 15:00 "," - "," - "," - "," - "," - "},
                                        {" 15:00 - 16:00 "," - "," - "," - "," - "," - "},
                                        {" 16:00 - 17:00 "," - "," - "," - "," - "," - "},
                                        {" 17:00 - 18:00 "," - "," - "," - "," - "," - "},
                                        {" 18:00 - 19:00 "," - "," - "," - "," - "," - "},
                                        {" 19:00 - 20:00 "," - "," - "," - "," - "," - "} };

    private String[] nameCol = {"H \\ D", "    MONDAY", "    TUESDAY", "   WEDNESDAY", "    THURSDAY", "    FRIDAY"};
    private JLabel upc = new JLabel("FIB - UPC (Universitat Politècnica de Catalunya) ");
    private boolean roomSelected;

    private MainFrame mf;
    private PresentationControl pc = PresentationControl.getInstance();
    private ModifySchedule popup;


    public ScheduleCL(MainFrame from){
        super("Schedule-Generator");
        setLayout(new BorderLayout());

        mf = from; mf.setVisible(false);
        String usuari = mf.getUser();
        userLabel.setText(usuari);

        upPanel.setLayout(new GridBagLayout());
        add(upPanel, BorderLayout.NORTH);
        latPan.setPreferredSize(latDim);
        blankLabel.setPreferredSize(latDim);
        add(blankLabel, BorderLayout.WEST);

        roomSelected = false;

        // BUTTON PANEL (RIGHT)
        buttonPanel.setLayout(new BorderLayout());
        backBtn.setPreferredSize(btnDim);
        moveBtn.setPreferredSize(btnDim);
        saveBtn.setPreferredSize(btnDim);
        buttonPanel.add(backBtn, BorderLayout.NORTH);
        buttonPanel.add(moveBtn, BorderLayout.CENTER);
        if(!usuari.equals("User")) buttonPanel.add(saveBtn, BorderLayout.SOUTH);

        gc.insets = new Insets(30,70,10, 70);
        gc.gridy = 0; upPanel.add(fstRow,gc); fstRow.setLayout(new BorderLayout());
        gc.gridy++; upPanel.add(rowBC,gc); rowBC.setLayout(new FlowLayout(FlowLayout.LEFT));
        gc.gridy++; upPanel.add(rowFilter,gc); rowFilter.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(row3,BorderLayout.CENTER); row3.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(row4,BorderLayout.SOUTH); row4.setLayout(new FlowLayout(FlowLayout.RIGHT));

        upPanel.setBorder(BorderFactory.createEtchedBorder(0));
        fstRow.setBorder(BorderFactory.createEtchedBorder(1));
        rowBC.setBorder(BorderFactory.createEtchedBorder(0));
        row4.setBorder(BorderFactory.createEtchedBorder(1));
        buttonPanel.setBorder(BorderFactory.createEtchedBorder(1));

        row4.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));


        JPanel cornerInfo = new JPanel();
        cornerInfo.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));

        cornerInfo.setLayout(new FlowLayout(FlowLayout.RIGHT));
        cornerInfo.add(schLabel, BorderLayout.WEST); schLabel.setPreferredSize(genSchDim);
        cornerInfo.add(userLabel); userLabel.setPreferredSize(helpDim);
        cornerInfo.add(helpBtn); helpBtn.setPreferredSize(helpDim);
        cornerInfo.add(exitBtn); exitBtn.setPreferredSize(helpDim);
        fstRow.add(cornerInfo, BorderLayout.EAST);
        row4.add(upc);



        lst = pc.getDegrees();
        dataDg.addElement("--Select Degree--");
        for(String e : lst) dataDg.addElement(e);
        desplDg.setModel(dataDg);

        lst = new ArrayList<>(); lst = pc.getBuildings();
        dataBd.addElement("--Select Building--");
        for(String e : lst) dataBd.addElement(e);
        desplBd.setModel(dataBd);


        desplDg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(desplDg.getSelectedIndex() > 0){
                    String sel = (String)desplDg.getSelectedItem();
                    try {
                        pc.editDegree(sel);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    lst = pc.getAcademicPlans();
                    dataAP = new DefaultComboBoxModel();
                    dataAP.addElement("--Select Academic Plan--");
                    for(String e : lst) dataAP.addElement(e);
                    desplAP.setModel(dataAP);
                    pc.cancelAcademicPlan();
                    pc.cancelSubject();
                } else {
                    pc.cancelDegree();
                    desplAP.setModel(new DefaultComboBoxModel());
                    desplSJ.setModel(new DefaultComboBoxModel());
                }
                desplAP.setSelectedItem(-1);
                desplSJ.setSelectedIndex(-1);
            }
        });

        desplAP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(desplAP.getSelectedIndex() > 0){
                    String sel = (String)desplAP.getSelectedItem();
                    try {
                        pc.editAcademicPlan(sel);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    lst = pc.getSubjects();
                    dataSJ = new DefaultComboBoxModel();
                    dataSJ.addElement("--Select Subject--");
                    for(String e : lst) dataSJ.addElement(e);
                    desplSJ.setModel(dataSJ);
                    pc.cancelSubject();
                    desplSJ.setSelectedIndex(0);
                } else {
                    pc.cancelAcademicPlan();
                    desplSJ.setModel(new DefaultComboBoxModel());
                    desplSJ.setSelectedIndex(-1);
                }

            }
        });

        desplSJ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(desplSJ.getSelectedIndex() > 0){
                    String sel = (String)desplSJ.getSelectedItem();
                    pc.editSubject(sel);
                    System.out.println(sel);
                } else {
                    pc.cancelSubject();
                }
            }
        });

        desplBd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(desplBd.getSelectedIndex() > 0){
                    String sel = (String)desplBd.getSelectedItem();
                    try {
                        pc.editBuilding(sel);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    lst = pc.getClassrooms();
                    dataCl = new DefaultComboBoxModel();
                    dataCl.addElement("--Select Classroom--");
                    for(String e : lst) dataCl.addElement(e);
                    desplCl.setModel(dataCl);
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
                if(desplCl.getSelectedIndex() > 0){
                    String sel = (String)desplCl.getSelectedItem();
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


        desplDg.setPreferredSize(desplDim); desplAP.setPreferredSize(desplDim); desplSJ.setPreferredSize(desplDim);
        desplBd.setPreferredSize(desplDim); desplCl.setPreferredSize(desplDim); updateBtn.setPreferredSize(desplDim);

        rowBC.add(latPan); rowBC.add(desplBd); rowBC.add(desplCl); rowBC.add(updateBtn);
        rowFilter.add(filter); rowBC.add(latPan); rowFilter.add(desplDg); rowFilter.add(desplAP); rowFilter.add(desplSJ);



        loadSchedule(defaultTable);
        schedule.setModel(infoTabla);

        schedule.setPreferredScrollableViewportSize(panDim);
        schedule.setShowGrid(true);
        schedule.setRowHeight(30);
        schedule.setCellSelectionEnabled(true);
        schedule.setColumnSelectionAllowed(false);
        schedule.setRowSelectionAllowed(false);
        schedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        schedule.getTableHeader().setReorderingAllowed(false);
        schedule.getTableHeader().setResizingAllowed(false);
        schedule.getTableHeader().setBackground(Color.getHSBColor(0.6f, 0.12f, 0.7f));
        schedule.setGridColor(Color.getHSBColor(0.4f, 0.1f, 0.7f));
        schedule.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));
        schedule.removeEditor();


        JScrollPane sp = new JScrollPane(schedule);
        row3.add(blankLabel);
        row3.add(sp);
        row3.add(latPan);
        row3.add(buttonPanel);





        // Listeners


        moveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                popUp();
            }
        });

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (desplCl.getSelectedIndex() > 0) {
                    roomSelected = true;
                    String bd = (String) desplBd.getSelectedItem();
                    String cl = (String) desplCl.getSelectedItem();
                    if (filter.isSelected() && desplSJ.getSelectedIndex() > 0) {
                        String dg = (String) desplDg.getSelectedItem();
                        String ap = (String) desplAP.getSelectedItem();
                        String sj = (String) desplSJ.getSelectedItem();
                        tabla = pc.getSubjectSchedule(dg,ap,sj,bd,cl);
                    } else {
                        tabla = pc.getClassroomSchedule(bd,cl);
                    }
                    loadSchedule(tabla);
                } else {
                    roomSelected = false;
                    selectValidItem(!filter.isSelected());
                }
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mf.setVisible(true);
                dispose();
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String ruta = mf.getFullPath();
                int size = ruta.length();
                if(size > 3) {
                    String ext = ruta.substring(size - 3, size);
                    if (!ext.equals("txt")) ruta += ".txt";
                } else ruta += ".txt";
                pc.save(ruta);
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int result = JOptionPane.showConfirmDialog(row3, "Are you sure you want to exit?\nMake sure you have saved before confirmation", "EXIT", JOptionPane.OK_CANCEL_OPTION);
                if(result == JOptionPane.OK_OPTION) System.exit(1); //Exit by user click
            }
        });

        helpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Descriptor d = null;
                JOptionPane.showConfirmDialog(row3, d.getDescription("gsHelp"), "HELP", JOptionPane.DEFAULT_OPTION);
            }
        });


        setSize(1280,720);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void selectValidItem(boolean n){
        JOptionPane.showConfirmDialog(this, n ? "Please, select a valid Classroom.\nTo select a classroom you need to select a Building." : "Please, " +
                "select a valid Classroom and Subject.\nTo select a classroom you need to select a Building.\nTo select a Subject you need to select a Degree and Academic Plan.", "Warning", JOptionPane.DEFAULT_OPTION);
    }


    private void loadSchedule(String[][] tb) {
        infoTabla.setDataVector(tb, nameCol);
    }





    private void popUp(){
        String oldBu = "";
        String oldCl = "";
        if(roomSelected) {
            oldBu = pc.getBuildingName();
            oldCl = pc.getClassroomName();
        } else {
            cout("No Classroom selected");
            return;
        }

        int oldCol = schedule.getSelectedColumn();
        if (oldCol < 1 || oldCol > 5) return;
        int oldRow = schedule.getSelectedRow();
        if (oldRow < 0 || oldRow > 11) return;

        String current = (String)schedule.getValueAt(oldRow, oldCol);
        if(current.equals(" - ")) return;

        while(oldRow > 0) {
            String prev = (String) schedule.getValueAt(oldRow-1, oldCol);
            current = (String) schedule.getValueAt(oldRow, oldCol);

            if(prev.equals(current)) oldRow--;
            else break;
        }

        popup = new ModifySchedule(this, oldBu, oldCl, oldCol-1, oldRow);
    }

    public void updateSchedule(){
        updateBtn.doClick();
    }

    private void cout(String s){
        System.out.println(s);
    }
}
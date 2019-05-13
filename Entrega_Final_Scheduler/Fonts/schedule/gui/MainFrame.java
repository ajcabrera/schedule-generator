package schedule.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private PresentationControl pc = PresentationControl.getInstance();

    private ToolBar toolbar;                // Upper panel
    private EIPanel edInstPanel;            // Educational Institution panel view
    private DPanel degreePanel;             // Degree panel view
    private APPanel acPlanPanel;            // Academic Plan panel view
    private SPanel subjectPanel;            // Subject panel view
    private BPanel buildingPanel;           // Building panel view
    private CPanel classroomPanel;          // Classroom panel view
    private DescriptionPanel descPanel;     // Description panel

    private String fullPath;
    private String EI;
    private String user;

    private JDialog progress;
    private JLabel current = new JLabel("Computing schedule...");

    private InputMenu popup;

    public MainFrame(String fullPath, String user, String edInst){
        super("Schedule-Generator");

        pc.setMainframe(this);

        setLayout(new BorderLayout());
        this.fullPath = fullPath;
        this.user = user;
        EI = edInst;
        toolbar = new ToolBar(this, user, edInst);
        JLabel upc = new JLabel("FIB - UPC (Universitat Politècnica de Catalunya) "); // Llevarlo a un panel sur con layout Right

        edInstPanel = new EIPanel(user, edInst);
        degreePanel = new DPanel(user, "DegreeName");
        acPlanPanel = new APPanel(user, "AcademicPlanName");
        subjectPanel = new SPanel(user, "SubjectName");
        buildingPanel = new BPanel(user, "BuildingName");
        classroomPanel = new CPanel(user, "ClassroomName");

        descPanel = new DescriptionPanel(user, this);

        boolean admin = user.equals("Admin");

        Descriptor d = null;
        toolbar.setLoadedPanel("EducationalInstitution");

        /*

        --------------------------------
            EDUCATIONAL INSTITUTION
        --------------------------------

         */
        edInstPanel.setStringListener(new StringListener() {
            public void descriptionLoader(int n){
                DefaultListModel listToLoad = new DefaultListModel();
                ArrayList<String> lst = new ArrayList<String>();
                descPanel.setCanEdit(admin);
                descPanel.showAddRemove(admin);
                System.out.println(n);
                switch (n) {
                    case 0:
                        descPanel.setItemType("Buildings");
                        descPanel.setDescription(d.getDescription("eiBu"));
                        lst = pc.getBuildings();
                        break;
                    case 1:
                        descPanel.setItemType("Equipments");
                        descPanel.setDescription(d.getDescription("eiEq"));
                        lst = pc.getEquipments();
                        break;
                    case 2:
                        descPanel.setItemType("Degrees");
                        descPanel.setDescription(d.getDescription("eiDe"));
                        lst = pc.getDegrees();
                        break;
                    case 3:
                        descPanel.setItemType("Restrictions");
                        descPanel.setDescription(d.getDescription("eiRe"));
                        lst = pc.getRestrictions();
                        break;
                }

                for(String e : lst) listToLoad.addElement(e);
                descPanel.loadList(listToLoad);
            }

            public void textSent(String s) {
                DefaultListModel listToLoad = new DefaultListModel();
                ArrayList<String> lst = new ArrayList<String>();

                switch (s) {
                    case "Generate":
                        progress = new JDialog(null, "Busy", JDialog.ModalityType.MODELESS);
                        progress.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                        progress.setLayout(new FlowLayout(FlowLayout.CENTER));
                        current.setPreferredSize(new Dimension(600,100));
                        progress.add(current);
                        progress.setSize(720, 150);
                        progress.setLocationRelativeTo(null);
                        progress.setVisible(true);
                        progress.setResizable(false);

                        if(!pc.generateSchedule()) { progress.dispose(); createUnsatView();}
                        else { progress.dispose(); createScheduleView();}
                    case "Save":
                        savePanelData();
                        String ruta = getFullPath();
                        int size = ruta.length();
                        if(size > 3) {
                            String ext = ruta.substring(size - 3, size);
                            if (!ext.equals("txt")) ruta += ".txt";
                        } else ruta += ".txt";
                        for(String e : pc.getRestrictions()) System.out.println(e);
                        pc.save(ruta);
                }

                for(String e : lst) listToLoad.addElement(e);
                descPanel.loadList(listToLoad);
            }

            public void updatePath(String s){
                toolbar.updatePath(s);
            }
        });


        /*

        --------------------------------
            DEGREE
        --------------------------------

         */
        degreePanel.setStringListener(new StringListener() {
            public void descriptionLoader(int n){
                DefaultListModel listToLoad = new DefaultListModel();
                ArrayList<String> lst = new ArrayList<String>();
                if(n == 0) {
                    descPanel.setCanEdit(admin);
                    lst = pc.getAcademicPlans();
                    descPanel.setItemType("AcademicPlans");
                    descPanel.setDescription(d.getDescription("deAP"));
                }

                for(String e : lst) listToLoad.addElement(e);
                descPanel.loadList(listToLoad);
            }

            public void textSent(String s) { // Always shows Academic Plans list in Description Panel
                DefaultListModel listToLoad = new DefaultListModel();
                ArrayList<String> lst = new ArrayList<String>();
                switch (s) {
                    case "Accept":
                        savePanelData(); pc.saveDegree(); loadPanel(edInstPanel); toolbar.setLoadedPanel("EducationalInstitution"); break;
                    case "Back":
                        pc.cancelDegree(); loadPanel(edInstPanel); toolbar.setLoadedPanel("EducationalInstitution");
                }

                for(String e : lst) listToLoad.addElement(e);
                descPanel.loadList(listToLoad);
            }

            public void updatePath(String s){
                toolbar.updatePath(s);
            }
        });

        /*

        --------------------------------
            ACADEMIC PLAN
        --------------------------------

         */
        acPlanPanel.setStringListener(new StringListener() {
            public void descriptionLoader(int n){
                DefaultListModel listToLoad = new DefaultListModel();
                ArrayList<String> lst = new ArrayList<String>();
                switch(n) {
                    case 0:
                        descPanel.setCanEdit(admin);
                        lst = pc.getSubjects();
                        descPanel.setItemType("Subjects");
                        descPanel.setDescription(d.getDescription("apSu"));
                        break;
                    case 1:
                        descPanel.setCanEdit(admin);
                        lst = pc.getLevels();
                        System.out.println(pc.getDegreeName());
                        System.out.println(pc.getAcademicPlanName());
                        for(String e : lst) System.out.println(e);
                        descPanel.setItemType("Levels");
                        descPanel.setDescription(d.getDescription("apLe"));
                        break;
                }
                for(String e : lst) listToLoad.addElement(e);
                descPanel.loadList(listToLoad);
            }


            public void textSent(String s) {
                DefaultListModel listToLoad = new DefaultListModel();
                ArrayList<String> lst = new ArrayList<String>();

                switch (s) {
                    case "Accept":
                        savePanelData(); pc.saveAcademicPlan(); loadPanel(degreePanel); toolbar.setLoadedPanel("Degrees"); break;
                    case "Back":
                        pc.cancelAcademicPlan(); loadPanel(degreePanel); toolbar.setLoadedPanel("Degrees");
                }

                for(String e : lst) listToLoad.addElement(e);
                descPanel.loadList(listToLoad);
            }

            public void updatePath(String s){
                toolbar.updatePath(s);
            }

        });

        /*

        --------------------------------
            SUBJECT
        --------------------------------

         */
        subjectPanel.setStringListener(new StringListener() {
            public void descriptionLoader(int n){
                DefaultListModel listToLoad = new DefaultListModel();
                ArrayList<String> lst = new ArrayList<String>();
                switch(n) {
                    case 0:
                        descPanel.setCanEdit(admin);
                        lst = pc.getSubjectTypes();
                        descPanel.setItemType("Group Types");
                        descPanel.setDescription(d.getDescription("sGr"));
                        descPanel.showEditButton(true);
                        break;
                    case 1:
                        descPanel.setCanEdit(false);
                        lst = pc.getCorreq();
                        descPanel.setItemType("Corequisite");
                        descPanel.setDescription(d.getDescription("sCo"));
                        descPanel.showEditButton(false);
                }
                for(String e : lst) listToLoad.addElement(e);
                descPanel.loadList(listToLoad);
            }


            public void textSent(String s) { // Always shows Academic Plans list in Description Panel
                switch (s) {
                    case "Accept":
                        savePanelData(); pc.saveSubject(); loadPanel(acPlanPanel); toolbar.setLoadedPanel("AcademicPlans"); descPanel.showEditButton(true); break;
                    case "Back":
                        pc.cancelSubject(); loadPanel(acPlanPanel); toolbar.setLoadedPanel("AcademicPlans"); descPanel.showEditButton(true);
                }
            }

            public void updatePath(String s){
                toolbar.updatePath(s);
            }
        });

        /*

        --------------------------------
            BUILDING
        --------------------------------

         */
        buildingPanel.setStringListener(new StringListener() {
            public void descriptionLoader(int n){
                DefaultListModel listToLoad = new DefaultListModel();
                ArrayList<String> lst = new ArrayList<String>();
                switch(n) {
                    case 0:
                        descPanel.setCanEdit(admin);
                        lst = pc.getClassrooms();
                        descPanel.setItemType("Classrooms");
                        descPanel.setDescription(d.getDescription("bCl"));
                        break;
                    case 1:
                        descPanel.setCanEdit(false);
                        lst = pc.getBuildingEquipments();
                        descPanel.setItemType("BEquipments");
                        descPanel.setDescription(d.getDescription("bEq"));
                }
                for(String e : lst) listToLoad.addElement(e);
                descPanel.loadList(listToLoad);
            }


            public void textSent(String s) { // Always shows Academic Plans list in Description Panel
                System.out.println(s);
                switch (s) {
                    case "Accept": System.out.println(pc.getBuildingName());
                        savePanelData(); pc.saveBuilding(); loadPanel(edInstPanel); toolbar.setLoadedPanel("EducationalInstitution"); break;
                    case "Back":
                        pc.cancelBuilding(); loadPanel(edInstPanel); toolbar.setLoadedPanel("EducationalInstitution");
                }
            }

            public void updatePath(String s){
                toolbar.updatePath(s);
            }
        });

        /*

        --------------------------------
            CLASSROOM
        --------------------------------

         */
        classroomPanel.setStringListener(new StringListener() {
            public void descriptionLoader(int n){
                DefaultListModel listToLoad = new DefaultListModel();
                ArrayList<String> lst = new ArrayList<String>();

                if(n==0) {
                    descPanel.setCanEdit(false);
                    lst = pc.getClassroomEquipments();
                    descPanel.setItemType("CEquipments");
                    descPanel.setDescription(d.getDescription("cEq"));
                }
                for(String e : lst) listToLoad.addElement(e);
                descPanel.loadList(listToLoad);
            }


            public void textSent(String s) { // Always shows Academic Plans list in Description Panel
                switch (s) {
                    case "Accept":
                        savePanelData(); pc.saveClassroom(); loadPanel(buildingPanel); toolbar.setLoadedPanel("Buildings"); break;
                    case "Back":
                        pc.cancelClassroom(); loadPanel(buildingPanel); toolbar.setLoadedPanel("Buildings");
                }
            }



            public void updatePath(String s){
                toolbar.updatePath(s);
            }
        });









        descPanel.setStringListener(new StringListener() {
            public void descriptionLoader(int n){

            }

            public void textSent(String received) {
                String mode = "Add";
                String s = "";
                if(received.substring(0,1).equals("¡")) {
                    mode = "Edit";
                    s = received.substring(1);
                } else if( received.substring(0,1).equals("¿") ) {
                    mode = "Details";
                    s = received.substring(1);
                } else s = received;

                boolean add = mode.equals("Add");

                System.out.println(mode);
                switch (s) {
                    case "Buildings":
                        loadPanel(buildingPanel);
                        if(!add) buildingPanel.setItemName(pc.getBuildingName());
                        toolbar.setLoadedPanel(s);
                        break;
                    case "Classrooms":
                        loadPanel(classroomPanel);
                        if(!add) {
                            classroomPanel.setItemName(pc.getClassroomName());
                            classroomPanel.setFloor(pc.getClassroomFloor());
                            classroomPanel.setsize(pc.getClassroomSize());
                        }
                        toolbar.setLoadedPanel(s);
                        break;
                    case "BEquipments":
                        popUp("Building Equipments", user, mode); toolbar.setLoadedPanel("Buildings");
                        break;
                    case "CEquipments":
                        popUp("Classroom Equipments", user, mode); toolbar.setLoadedPanel("Classrooms");
                        break;
                    case "Degrees":
                        loadPanel(degreePanel);
                        if(!add){
                            degreePanel.setItemName(pc.getDegreeName());
                            degreePanel.setType(pc.getDegreeType());
                        }
                        toolbar.setLoadedPanel(s);
                        break;
                    case "AcademicPlans":
                        loadPanel(acPlanPanel);
                        if(!add){
                            acPlanPanel.setItemName(pc.getAcademicPlanName());
                        }
                        toolbar.setLoadedPanel(s);
                        break;
                    case "Subjects":
                        loadPanel(subjectPanel); toolbar.setLoadedPanel(s);
                        if(!add){
                            subjectPanel.setItemName(pc.getSubjectName());
                            subjectPanel.setSlots(pc.getSubjectSlots());
                        }
                        descPanel.showEditButton(true);
                        break;
                    case "Levels":
                        popUp(s, user, mode); toolbar.setLoadedPanel("AcademicPlans");
                        break;
                    case "Equipments":
                        popUp(s, user, mode); toolbar.setLoadedPanel("EducationalInstitution");
                        break;
                    case "Corequisite":
                        popUp(s, user, mode);  toolbar.setLoadedPanel("Subjects");
                        break;
                    case "Restrictions":
                        popUp(s, user, mode); toolbar.setLoadedPanel("EducationalInstitution");
                        break;
                    case "Group Types":
                        popUp(s, user, mode); toolbar.setLoadedPanel("Subjects");
                }
                DefaultListModel listToLoad = new DefaultListModel();
                descPanel.loadList(listToLoad);
            }

            public void updatePath(String s){
                toolbar.updatePath(s);
            }
        });



        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        lowerPanel.add(upc);
        lowerPanel.setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));


        add(toolbar, BorderLayout.NORTH);
        add(descPanel, BorderLayout.CENTER);
        add(lowerPanel, BorderLayout.SOUTH);
        loadPanel(edInstPanel);

        setSize(1280,720);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }



    private void loadPanel(FormPanel panelToLoad){
        edInstPanel.setVisible(false);
        degreePanel.setVisible(false);
        acPlanPanel.setVisible(false);
        subjectPanel.setVisible(false);
        buildingPanel.setVisible(false);
        classroomPanel.setVisible(false);
        panelToLoad.setVisible(true);
        add(panelToLoad, BorderLayout.WEST);
        edInstPanel.loadEmptyDescriptionPanel();
        descPanel.setDescription("");
    }

    private void popUp(String type, String user, String mode){
        popup = new InputMenu(this, type, user, mode);
        descPanel.setDescription("");
    }

    public String getSelectedItem(){
        return descPanel.getSelectedItem();
    }

    private void createScheduleView(){
        new ScheduleCL(this);
    }

    public String getEI(){
        return EI;
    }

    public String getUser(){
        return user;
    }

    public String getFullPath(){
        return fullPath;
    }

    public boolean savePanelData(){
        System.out.println(toolbar.getLoadedPanel());
        if(user.equals("User")) return true;
        else { System.out.println("Deberia salir antes del Accept");
            switch (toolbar.getLoadedPanel()) {
                case "Buildings":
                    pc.setBuildingName(buildingPanel.getItemName());
                    break;
                case "Classrooms":
                    pc.setClassroomName(classroomPanel.getItemName()); System.out.println(classroomPanel.getsize());
                    pc.setClassroomSize(classroomPanel.getsize()); System.out.println(classroomPanel.getFloor());
                    pc.setClassroomFloor(classroomPanel.getFloor());
                    break;
                case "Degrees":
                    pc.setDegreeName(degreePanel.getItemName());
                    pc.setDegreeType(degreePanel.getType());
                    break;
                case "AcademicPlans":
                    pc.setAcademicPlanName(acPlanPanel.getItemName());
                    break;
                case "Subjects":
                    pc.setSubjectName(subjectPanel.getItemName());
                    pc.setSubjectSlots(subjectPanel.getSlots());

                    break;
                default: pc.setEducationalInstitutionName(edInstPanel.getItemName());
            }
        }
        return true;
    }

    public void updateProcessStatus(String s){
        current.setText(s);
        current.paintImmediately(current.getVisibleRect());
    }

    private void createUnsatView(){
        JOptionPane.showConfirmDialog(null, "Your schedule could not be generated.\n" +
                "After checking all the conditions established in this context,               \n" +
                "it has been determined that there is no possible solution. \n\nPlease, soften the conditions and try again." , "Schedule Unsatisfiable", JOptionPane.DEFAULT_OPTION);
    }
}

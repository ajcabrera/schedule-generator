package schedule.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class DescriptionPanel extends JPanel implements ActionListener {
    private PresentationControl pc = PresentationControl.getInstance();
    private boolean canEdit;
    private String itemType;
    private JTextArea description;

    private JList itemList;
    private DefaultListModel items;

    private JButton addItem;
    private JButton editItem;
    private JButton removeItem;

    private StringListener textListener;
    private MainFrame mf;


    public DescriptionPanel(String user, MainFrame from){
        setBackground(Color.getHSBColor(0.6f, 0.12f, 0.9f));

        canEdit = (user != "User");
        mf = from;
        itemType = "Description";
        description = new JTextArea("  ");
        setupDescription();
        itemList = new JList();
        items = new DefaultListModel();
        addItem = new JButton("ADD");
        editItem = new JButton("EDIT");
        removeItem = new JButton("REMOVE");

        itemList.setModel(items);

        // GRID (3 x 3)
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // ROW 1
        gc.gridwidth = 3;
        gc.ipadx = 80;
        gc.ipady = 80;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(5,150,10, 150);
        add(new JScrollPane(description),gc);

        // ROW 2
        gc.weightx = 1;
        gc.ipadx = 100;
        gc.ipady = 25;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.weighty = 0.8;
        gc.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(itemList),gc);

        // ROW 3
        gc.ipadx = -5;
        gc.gridwidth = 1;
        gc.gridy = 2;
        gc.gridx = 0;
        gc.weighty = 0.1;
        gc.weightx = 0.5;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(5,150,10, 2);
        if(user != "User") add(addItem, gc);
        else add(new JPanel(),gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.insets = new Insets(5,3,10, 3);
        if(!canEdit) editItem.setText("DETAILS");
        add(editItem, gc);

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(5,2,10, 150);
        if(user != "User") add(removeItem, gc);
        else add(new JPanel(), gc);

        Border innerBorder = BorderFactory.createTitledBorder(itemType);
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder));


        // Set up button listeners
        addItem.addActionListener(this);
        editItem.addActionListener(this);
        removeItem.addActionListener(this);

        // Set up Alt+Key shortcuts
        addItem.setMnemonic(KeyEvent.VK_A);
        editItem.setMnemonic(KeyEvent.VK_E);
        removeItem.setMnemonic(KeyEvent.VK_Z);
    }


    @Override
    public void actionPerformed(ActionEvent a) {
        JButton clicked = (JButton)a.getSource();
        int[] selectedIndices = itemList.getSelectedIndices();
        if (clicked == removeItem) {
            for (int i = 0; i < selectedIndices.length; i++)
                if (selectedIndices[i] != -1) {
                    String sel = (String)items.getElementAt(selectedIndices[i]-i);

                    switch(itemType){
                        case "Buildings": pc.removeBuilding(sel); break;
                        case "Classrooms": pc.removeClassroom(sel); break;
                        case "BEquipments": pc.removeBuildingEquipment(sel); break;
                        case "CEquipments": pc.removeClassroomEquipment(sel); break;
                        case "Degrees": pc.removeDegree(sel); break;
                        case "AcademicPlans": pc.removeAcademicPlan(sel); break;
                        case "Subjects": pc.removeSubject(sel); break;
                        case "Levels": pc.removeLevel(sel); break;
                        case "Group Types": pc.removeType(sel); break;
                        case "Corequisite": pc.removeCorreq(sel); break;
                        case "Equipments": pc.removeEquipment(sel); break;
                        case "Restrictions": pc.removeRestriction(sel); break;
                    }

                    items.remove(selectedIndices[i] - i);
                }
            if(itemList.getVisibleRowCount() > 0) itemList.setSelectedIndex(0);
        } else if( mf.savePanelData() ){
            String sel = "";
            boolean add = clicked == addItem;
            if(!add && selectedIndices.length > 0)
                sel = (String) items.getElementAt(selectedIndices[0]);

            if( add || !sel.equals("") ){
                switch(itemType){
                    case "Buildings":
                        if(add) pc.newBuilding();
                        else {
                            try {
                                pc.editBuilding(sel);
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "Classrooms":
                        if(add) pc.newClassroom();
                        else {
                            try {
                                pc.editClassroom(sel);
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "Degrees":
                        if(add) pc.newDegree();
                        else {
                            try {
                                pc.editDegree(sel);
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "AcademicPlans":
                        if(add) pc.newAcademicPlan();
                        else {
                            try {
                                pc.editAcademicPlan(sel);
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "Subjects":
                        if(add) pc.newSubject();
                        else pc.editSubject(sel); break;
                    case "Levels": break; //lanzar ventana edit
                    case "Corequisite": break; //lanzar ventana
                    case "Group Types":
                        if(add && items.getSize() == 0){
                            pc.newType("Example",60, 2, 1);
                            pc.setOldType("Example");
                        } else if (itemList.getSelectedIndex() > -1){
                            pc.setOldType((String)itemList.getSelectedValue());
                        }
                        break;
                    case "Equipments":
                        if(add) pc.newEquipment();
                        else {
                            try {
                                pc.editEquipment(sel);
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                }
                if(!add)textListener.textSent((editItem.getText().equals("DETAILS") ? "¿" : "¡" ) + itemType); //DETAILS vs EDIT
                else textListener.textSent(itemType); // Modifies the screen to the requested item type.
            }

        }
    }


    public void loadList(DefaultListModel lst){
        items = lst;
        itemList.setModel(items);
        if(lst.getSize() > 0) itemList.setSelectedIndex(0);
    }

    public void setItemType(String s){
        System.out.println(s);
        itemType = s;
    }

    public void setDescription(String s){
        description.setText(s);
    }

    public void setCanEdit(boolean b){
        canEdit = b;
        editItem.setText(b ? "EDIT" : "DETAILS");
    }

    public void showAddRemove(boolean b){
        addItem.setVisible(b);
        removeItem.setVisible(b);
    }

    public void showEditButton(boolean b){
        editItem.setVisible(b);
    }

    public void setStringListener(StringListener sl){
        textListener = sl;
    }


    public String getSelectedItem(){
        if(itemList.getSelectedIndex() < 0) return "-";
        return (String)itemList.getSelectedValue();
    }

    private void setupDescription(){
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setFocusable(false);
        description.setMargin(new Insets(6, 10, 6, 6));
    }
}

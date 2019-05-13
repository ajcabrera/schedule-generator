package schedule.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionListener;

public class SPanel extends FormPanel implements ActionListener{

    private JLabel slotsLabel = new JLabel("Slots:");
    private JTextField slots = new JTextField(4);

    public SPanel(String user, String subjectName){
        path = subjectName;
        name.setText(subjectName);

        if(user.equals("User")) {
            String aux = nameLabel.getText();
            nameLabel.setText(aux + "  " + subjectName);
            name.setVisible(false);
        }

        // ROW 1
        row1.add(nameLabel);
        row1.add(name);
        row2.add(slotsLabel);
        row2.add(slots);

        // ROW 2
        row3.add(groupTypes);

        // ROW 3
        row4.add(corequisite);

        row5.add(new JPanel());
        row6.add(new JPanel());

        // ROW 4
        row8.add(cancelChanges);
        if(!user.equals("User")) row8.add(acceptChanges);


        Border innerBorder = BorderFactory.createTitledBorder("Subject: ");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder));
    }

    public String getSlots(){
        return slots.getText();
    }

    public void setSlots(String s) { slots.setText(s);}

    public void setItemName(String s){
        if( name.isVisible() ) name.setText(s);
        else nameLabel.setText("Name: " + s);
    }

}

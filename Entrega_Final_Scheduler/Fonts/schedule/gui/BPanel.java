package schedule.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class BPanel extends FormPanel implements ActionListener{

    public BPanel(String user, String subjectName){
        path = subjectName; // Actually get it from file name
        name.setText(subjectName);

        if(user.equals("User")) {
            String aux = nameLabel.getText();
            nameLabel.setText(aux + "  " + subjectName);
            name.setVisible(false);
        }

        // ROW 1
        row1.add(nameLabel);
        row1.add(name);

        // ROW 2
        row2.add(classrooms);

        // ROW 3
        row3.add(bequipments);

        row4.add(new JPanel());
        row5.add(new JPanel());
        row6.add(new JPanel());

        // ROW 4
        row8.add(cancelChanges);
        if(!user.equals("User")) row8.add(acceptChanges);


        Border innerBorder = BorderFactory.createTitledBorder("Building: ");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder));
    }

    public void setItemName(String s){
        if( name.isVisible() ) name.setText(s);
        else nameLabel.setText("Name: " + s);
    }

}

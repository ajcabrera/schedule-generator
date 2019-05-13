package schedule.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class EIPanel extends FormPanel implements ActionListener{

    public EIPanel(String user, String edInst){
        path = edInst;
        name.setText(path);

        if(user.equals("User")) {
            String aux = nameLabel.getText();
            nameLabel.setText(aux + "  " + edInst);
            name.setVisible(false);
        }

        // ROW 1
        row1.add(nameLabel);
        row1.add(name);

        // ROW 2
        row2.add(buildings);

        // ROW 3
        row3.add(equipments);

        // ROW 4
        row4.add(degrees);

        // ROW 5
        row5.add(restrictions);

        row6.add(new JPanel());

        // ROW 6
        row8.add(genSchedule);
        if(!user.equals("User")) row8.add(saveChanges);


        Border innerBorder = BorderFactory.createTitledBorder("Educational Institution: ");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder));
    }


    public void loadPreviousDescriptionPanel(){
        textListener.textSent("Default");
    }

    public void setItemName(String s){
        if( name.isVisible() ) name.setText(s);
        else nameLabel.setText("Name: " + s);
    }
}

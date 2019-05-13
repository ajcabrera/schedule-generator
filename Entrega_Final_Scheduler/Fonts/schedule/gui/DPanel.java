package schedule.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class DPanel extends FormPanel implements ActionListener{
    protected JLabel typeLabel = new JLabel("Type:");
    protected JTextField type = new JTextField(12);

    public DPanel(String user, String degreeName){
        path = degreeName;
        name.setText(degreeName);

        if(user.equals("User")) {
            String aux = nameLabel.getText();
            nameLabel.setText(aux + "  " + degreeName);
            name.setVisible(false);
        }

        // ROW 1
        row1.add(nameLabel);
        row1.add(name);

        // ROW 2
        row2.add(typeLabel);
        row2.add(type);

        // ROW 3
        row3.add(acaplans);

        row4.add(new JPanel());
        row5.add(new JPanel());
        row6.add(new JPanel());

        // ROW 4
        row8.add(cancelChanges);
        if(!user.equals("User")) row8.add(acceptChanges);


        Border innerBorder = BorderFactory.createTitledBorder("Degree: ");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder));
    }

    public String getType(){
        return type.getText();
    }

    public void setType(String s) { type.setText(s);}

    public void setItemName(String s){
        if( name.isVisible() ) name.setText(s);
        else nameLabel.setText("Name: " + s);
    }

}

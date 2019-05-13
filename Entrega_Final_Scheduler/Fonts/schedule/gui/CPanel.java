package schedule.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionListener;

public class CPanel extends FormPanel implements ActionListener{

    private JLabel size = new JLabel("Size:");
    private JLabel floor = new JLabel("Floor:");
    private JTextField sizeText = new JTextField(4);
    private JTextField floorText = new JTextField(4);

    public CPanel(String user, String degreeName){
        path = degreeName; // Actually get it from file name
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
        row2.add(size);
        row2.add(sizeText);

        // ROW 3
        row3.add(floor);
        row3.add(floorText);

        // ROW 4
        row4.add(cequipments);

        row5.add(new JPanel());
        row6.add(new JPanel());


        // ROW 8
        row8.add(cancelChanges);
        if(!user.equals("User")) row8.add(acceptChanges);


        Border innerBorder = BorderFactory.createTitledBorder("Classroom: ");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder));
    }

    public String getFloor(){
        return floorText.getText();
    }

    public String getsize(){
        return sizeText.getText();
    }

    public void setFloor(String s) { floorText.setText(s);}

    public void setsize(String s) { sizeText.setText(s);}

    public void setItemName(String s){
        if( name.isVisible() ) name.setText(s);
        else nameLabel.setText("Name: " + s);
    }

}

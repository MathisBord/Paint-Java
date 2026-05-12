package viewcontrol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class BottomBar extends JPanel {
    private JLabel message;
    private JLabel labelX;
    private JLabel labelY;


    public BottomBar(){
        this.message = new JLabel("Welcome. ");
        this.labelX = new JLabel("x = ");
        this.labelY = new JLabel(" y = ");
        this.add(message);
        this.add(labelX);
        this.add(labelY);
        this.setBackground(Color.LIGHT_GRAY);
    }

    public JLabel getMessage() {
        return message;
    }

    public JLabel getLabelX() {
        return labelX;
    }

    public JLabel getLabelY() {
        return labelY;
    }

    public void setMessage(JLabel message) {
        this.message = message;
    }

    public void setLabelX(JLabel labelX) {
        this.labelX = labelX;
    }

    public void setLabelY(JLabel labelY) {
        this.labelY = labelY;
    }

    public void mouseMouvement(MouseEvent evt){
        this.getLabelX().setText("x = " + evt.getX());
        this.getLabelY().setText(" y = " + evt.getY());
    }
}

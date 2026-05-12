package viewcontrol;

import model.EnumShape;
import model.Enumcolor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopBar extends JPanel implements ActionListener {
    private JComboBox<Enumcolor> colorBox;
    private JComboBox<EnumShape> shapeBox;
    private JButton clear;
    private JButton undo;
    private JButton redo;
    private Window window;
    private JButton eraser;


    public TopBar(Window window){
        this.colorBox = new JComboBox<>(Enumcolor.values());
        this.shapeBox = new JComboBox<>(EnumShape.values());
        this.clear = new JButton("clear");
        this.undo = new JButton("undo");
        this.redo = new JButton("redo");
        this.window = window;
        this.eraser = new JButton("Enable Eraser");

        this.colorBox.addActionListener(this);
        this.shapeBox.addActionListener(this);

        this.add(this.colorBox);
        this.add(this.shapeBox);
        this.add(this.clear);
        this.add(this.undo);
        this.add(this.redo);
        this.add(this.eraser);
        this.setBackground(Color.LIGHT_GRAY);

        ClearListener clearListener = new ClearListener();
        this.clear.addActionListener(clearListener);

        class UndoListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                TopBar.this.window.undo();
            }
        }

        UndoListener undoListener = new UndoListener();
        this.undo.addActionListener(undoListener);

        class RedoListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                TopBar.this.window.redo();
            }
        }

        RedoListener redoListener = new RedoListener();
        this.redo.addActionListener(redoListener);

        ActionListener eraserListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (TopBar.this.eraser.getText().equals("Enable Eraser")){
                    TopBar.this.eraser.setText("Disable Eraser");
                    TopBar.this.window.enableEraser();
                }else{
                    TopBar.this.eraser.setText("Enable Eraser");
                    TopBar.this.window.disableEraser();
                }
            }
        };
        this.eraser.addActionListener(eraserListener);



    }

    class ClearListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            TopBar.this.window.clear();
        }
    }

    public Enumcolor getSelectedcolor(){
        return (Enumcolor) this.colorBox.getSelectedItem();
    }

    public EnumShape getSelectedShape(){
        return (EnumShape) this.shapeBox.getSelectedItem();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(this.getSelectedcolor());
        System.out.println(this.getSelectedShape());
    }
}

package viewcontrol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame{
    private BottomBar bottomBar;
    private GraphicalArea graphicalArea;
    private TopBar topBar;

    public Window(){
        super();
        this.bottomBar = new BottomBar();
        this.topBar = new TopBar(this);
        this.graphicalArea = new GraphicalArea(this.bottomBar, this.topBar);

        this.setTitle("My first window!");
        this.setSize(1000, 700);
        this.setLocation(600, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.add(this.bottomBar, BorderLayout.SOUTH);
        this.add(this.topBar, BorderLayout.NORTH);


        this.add(this.graphicalArea, BorderLayout.CENTER);

        MyWindowListener listener = new MyWindowListener();
        this.addWindowListener(listener);

        JMenuBar myMenuBar = new JMenuBar();
        this.setJMenuBar(myMenuBar);
        JMenu file = new JMenu("File");
        myMenuBar.add(file);
        
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem export = new JMenuItem("Export to PNG...");
        JMenuItem close = new JMenuItem("Close");
        
        file.add(save);
        file.add(load);
        file.addSeparator();
        file.add(export);
        file.addSeparator();
        file.add(close);

        save.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(Window.this) == JFileChooser.APPROVE_OPTION) {
                Window.this.graphicalArea.save(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        load.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(Window.this) == JFileChooser.APPROVE_OPTION) {
                Window.this.graphicalArea.load(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        export.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(Window.this) == JFileChooser.APPROVE_OPTION) {
                Window.this.graphicalArea.exportToImage(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        class CloseListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }

        CloseListener closeListener = new CloseListener();
        close.addActionListener(closeListener);


    }

    public void undo(){
        this.graphicalArea.undo();
    }

    public void redo(){
        this.graphicalArea.redo();
    }

    public void clear(){
        this.graphicalArea.clear();
    }

    public void enableEraser(){
        this.graphicalArea.enableEraser();
    }

    public void disableEraser(){
        this.graphicalArea.disableEraser();
    }







    static void main(String[] args) {
        JFrame myWindow = new Window();
        myWindow.setVisible(true);
    }







}

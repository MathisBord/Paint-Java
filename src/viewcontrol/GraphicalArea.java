package viewcontrol;

import model.*;
import model.Point;
import model.Rectangle;
import model.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class GraphicalArea extends JPanel implements MouseMotionListener, MouseListener {
    private BottomBar bottomBar;
    private TopBar topBar;
    private Point pInit;
    private Point pEnd;
    private Point currentMousePosition;
    private Pencil currentPencil;
    private Eraser currentEraser;
    private boolean bool;
    private List<Shape> storage;
    private List<Shape> redoStorage;
    private boolean erase;


    public GraphicalArea(BottomBar bottomBar, TopBar topBar) {
        this.bool = false;
        this.storage = new LinkedList<>();
        this.redoStorage = new LinkedList<>();

        this.bottomBar = bottomBar;
        this.topBar = topBar;

        this.pInit = new Point(0,0);
        this.pEnd = new Point(0,0);
        this.currentMousePosition = null;

        this.setBackground(Color.WHITE);



        this.addMouseMotionListener(this);
        this.addMouseListener(this);

        this.setVisible(true);
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        this.pEnd = new Point(e.getX(), e.getY());
        this.currentMousePosition = new Point(e.getX(), e.getY());
        if (!this.erase && this.topBar.getSelectedShape() == EnumShape.PENCIL && this.currentPencil != null) {
            this.currentPencil.addPoint(this.pEnd);
        }
        if (this.erase && this.currentEraser != null) {
            this.currentEraser.addPoint(this.pEnd);
        }
        this.draw();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.bottomBar.mouseMouvement(e);
        this.currentMousePosition = new Point(e.getX(), e.getY());
        if (this.erase) {
            this.repaint();
        }
    }





    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.pInit = new Point(e.getX(), e.getY());
        this.pEnd = new Point(e.getX(), e.getY());

        this.bool = true;
        
        if (!this.erase && this.topBar.getSelectedShape() == EnumShape.PENCIL) {
            this.currentPencil = new Pencil(this.topBar.getSelectedcolor().getColor());
            this.currentPencil.addPoint(this.pInit);
            this.addShape(this.currentPencil);
        }
        
        if (this.erase) {
            this.currentEraser = new Eraser(Color.WHITE, 40);
            this.currentEraser.addPoint(this.pInit);
            this.addShape(this.currentEraser);
        }

        System.out.println(this.pInit.toString());
        this.bottomBar.getMessage().setText("Release to view the shape");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.pEnd = new Point(e.getX(), e.getY());

        this.bool = false;
        
        if (!this.erase && this.topBar.getSelectedShape() == EnumShape.PENCIL && this.currentPencil != null) {
            this.currentPencil.addPoint(this.pEnd);
            this.currentPencil = null;
        }
        
        if (this.erase && this.currentEraser != null) {
            this.currentEraser.addPoint(this.pEnd);
            this.currentEraser = null;
        }

        this.bottomBar.getMessage().setText("Click to initiate a shape");
        this.draw();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered");
        this.currentMousePosition = new Point(e.getX(), e.getY());
        if (this.erase) {
            this.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited");
        this.currentMousePosition = null;
        if (this.erase) {
            this.repaint();
        }
    }

    private void draw() {
        if (!this.erase) {
            if (!this.bool) {
                switch (this.topBar.getSelectedShape()) {
                    case LINE:
                        this.addShape(new Line(this.topBar.getSelectedcolor().getColor(), this.pInit, this.pEnd));
                        break;
                    case RECTANGLE:
                        this.addShape(new Rectangle(this.topBar.getSelectedcolor().getColor(), this.pInit, this.pEnd));
                        break;
                    case CIRCLE:
                        this.addShape(new Circle(this.topBar.getSelectedcolor().getColor(), this.pInit, this.pEnd));
                        break;
                    case TRIANGLE:
                        this.addShape(new Triangle(this.topBar.getSelectedcolor().getColor(), this.pInit, this.pEnd));
                        break;
                    case TRUCK:
                        this.addShape(new Truck(this.topBar.getSelectedcolor().getColor(), this.pInit, this.pEnd));
                        break;
                    case EQUILATERAL_TRIANGLE:
                        this.addShape(new EquilateralTriangle(this.topBar.getSelectedcolor().getColor(), this.pInit, this.pEnd));
                        break;
                    case PENCIL:
                        break;
                }
            }
        }
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Shape s : this.storage) {
            s.draw(g);
        }

        if (this.bool && !this.erase) {
            switch (this.topBar.getSelectedShape()) {
                case LINE:
                    new Line(this.topBar.getSelectedcolor().getColor(), this.pInit, this.pEnd).draw(g);
                    break;
                case RECTANGLE:
                    new Rectangle(this.topBar.getSelectedcolor().getColor(), this.pInit, this.pEnd).draw(g);
                    break;
                case CIRCLE:
                    new Circle(this.topBar.getSelectedcolor().getColor(), this.pInit, this.pEnd).draw(g);
                    break;
                case TRIANGLE:
                    new Triangle(this.topBar.getSelectedcolor().getColor(), this.pInit, this.pEnd).draw(g);
                    break;
                case TRUCK:
                    new Truck(this.topBar.getSelectedcolor().getColor(), this.pInit, this.pEnd).draw(g);
                    break;
                case EQUILATERAL_TRIANGLE:
                    new EquilateralTriangle(this.topBar.getSelectedcolor().getColor(), this.pInit, this.pEnd).draw(g);
                    break;
                case PENCIL:
                    break;
            }
        }

        if (this.erase && this.currentMousePosition != null) {
            g.setColor(Color.BLACK);
            g.drawOval(this.currentMousePosition.getX() - 20, this.currentMousePosition.getY() - 20, 40, 40);
        }
    }

    public void undo(){
        if (!this.storage.isEmpty()) {
            Shape s = this.storage.removeLast();
            this.redoStorage.add(s);
            this.repaint();
        }
    }

    public void redo(){
        if (!this.redoStorage.isEmpty()) {
            Shape s = this.redoStorage.removeLast();
            this.storage.add(s);
            this.repaint();
        }
    }

    public void clear(){
        this.storage.clear();
        this.redoStorage.clear();
        this.repaint();
    }

    public void addShape(Shape s) {
        this.storage.add(s);
        this.redoStorage.clear();
    }

    public void enableEraser(){
        this.erase = true;
    }

    public void disableEraser(){
        this.erase = false;
    }

    public void save(String filepath) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath));
            oos.writeObject(this.storage);
            oos.close();
        } catch (Exception e) {
            System.out.println("Erreur");
        }
    }

    @SuppressWarnings("unchecked")
    public void load(String filepath) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath));
            this.storage = (List<Shape>) ois.readObject();
            ois.close();
            this.redoStorage.clear();
            this.repaint();
        } catch (Exception e) {
            System.out.println("Erreur");
        }
    }

    public void exportToImage(String filepath) {
        try {
            BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();
            this.paint(g2d);
            g2d.dispose();
            
            if (!filepath.toLowerCase().endsWith(".png")) {
                filepath += ".png";
            }
            
            ImageIO.write(image, "png", new File(filepath));
        } catch (Exception e) {
            System.out.println("Erreur");
        }
    }

}
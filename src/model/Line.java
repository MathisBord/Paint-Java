package model;

import java.awt.*;

public class Line extends Shape{
    private Point p1;
    private Point p2;


    public Line(Color color) {
        super(color);
    }

    public Line(Color color, Point p1, Point p2) {
        super(color);
        this.p1 = new Point(p1);
        this.p2 = new Point(p2);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawLine(this.p1.getX(), this.p1.getY(), this.p2.getX(), this.p2.getY());
    }
}

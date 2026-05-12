package model;

import java.awt.*;

public class Circle extends Shape{
    private Point pt;
    private int width;
    private int height;

    public Circle(Color color, Point pInit, Point pEnd) {
        super(color);
        int x = Math.min(pInit.getX(), pEnd.getX());
        int y = Math.min(pInit.getY(), pEnd.getY());
        this.pt = new Point(x, y);
        this.width = Math.abs(pEnd.getX() - pInit.getX());
        this.height = Math.abs(pEnd.getY() - pInit.getY());
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawOval(this.pt.getX(), this.pt.getY(), this.width, this.height);
    }
}

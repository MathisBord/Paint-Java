package model;

import java.awt.*;

public class Triangle extends Shape {
    private Point pt;
    private int width;
    private int height;
    private boolean pointingUp;

    public Triangle(Color color, Point pInit, Point pEnd) {
        super(color);
        int x = Math.min(pInit.getX(), pEnd.getX());
        int y = Math.min(pInit.getY(), pEnd.getY());
        this.pt = new Point(x, y);
        this.width = Math.abs(pEnd.getX() - pInit.getX());
        this.height = Math.abs(pEnd.getY() - pInit.getY());
        this.pointingUp = (pEnd.getY() < pInit.getY());
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        int xBase = this.pt.getX();
        int yBase = this.pt.getY();
        int[] xPoints = {xBase, xBase + this.width, xBase + this.width / 2};
        int[] yPoints;
        if (this.pointingUp) {
            yPoints = new int[]{yBase + this.height, yBase + this.height, yBase};
        } else {
            yPoints = new int[]{yBase, yBase, yBase + this.height};
        }
        g.drawPolygon(xPoints, yPoints, 3);
    }
}
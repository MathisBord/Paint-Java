package model;

import java.awt.*;

public class EquilateralTriangle extends Shape {
    private int[] xPoints;
    private int[] yPoints;

    public EquilateralTriangle(Color color, Point pInit, Point pEnd) {
        super(color);
        this.xPoints = new int[3];
        this.yPoints = new int[3];

        double radius = Math.hypot(pEnd.getX() - pInit.getX(), pEnd.getY() - pInit.getY());

        double startAngle = Math.atan2(pEnd.getY() - pInit.getY(), pEnd.getX() - pInit.getX());

        this.xPoints[0] = pEnd.getX();
        this.yPoints[0] = pEnd.getY();

        double angle2 = startAngle + (2 * Math.PI / 3);
        this.xPoints[1] = (int) Math.round(pInit.getX() + radius * Math.cos(angle2));
        this.yPoints[1] = (int) Math.round(pInit.getY() + radius * Math.sin(angle2));

        double angle3 = startAngle + (4 * Math.PI / 3);
        this.xPoints[2] = (int) Math.round(pInit.getX() + radius * Math.cos(angle3));
        this.yPoints[2] = (int) Math.round(pInit.getY() + radius * Math.sin(angle3));
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawPolygon(this.xPoints, this.yPoints, 3);
    }
}
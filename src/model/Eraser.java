package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Eraser extends Shape {
    private List<Point> points;
    private int radius;

    public Eraser(Color color, int radius) {
        super(color);
        this.points = new ArrayList<>();
        this.radius = radius;
    }

    public void addPoint(Point p) {
        this.points.add(p);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        for (Point pt : points) {
            g.fillOval(pt.getX() - radius / 2, pt.getY() - radius / 2, this.radius, this.radius);
        }
    }
}

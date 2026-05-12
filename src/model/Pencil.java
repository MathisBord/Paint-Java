package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pencil extends Shape {
    private List<Point> points;

    public Pencil(Color color) {
        super(color);
        this.points = new ArrayList<>();
    }

    public void addPoint(Point p) {
        this.points.add(p);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if (points.size() < 2) return;
        
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }
    }
}

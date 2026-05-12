package model;

import java.awt.*;

public class Truck extends Shape{
    private Point pt;
    private int size;


    public Truck(Color color, Point pInit, Point pEnd) {
        super(color);
        int x = Math.min(pInit.getX(), pEnd.getX());
        int y = Math.min(pInit.getY(), pEnd.getY());
        this.pt = new Point(x, y);
        this.size = Math.abs(pEnd.getX() - pInit.getX());
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawRect(this.pt.getX(), this.pt.getY(), this.size * 84 / 100, this.size * 50/100);
        g.drawRect(this.pt.getX() + this.size * 85 / 100, this.pt.getY() + this.size * 20/100, this.size * 15 / 100, this.size * 30/100);
        g.drawRect(this.pt.getX() + this.size * 88 / 100, this.pt.getY() + this.size * 24/100, this.size * 10 / 100, this.size * 8/100);
        g.drawOval(this.pt.getX() + this.size * 10 / 100, this.pt.getY() + this.size * 48 / 100, this.size * 10 / 100, this.size * 10 / 100);
        g.drawOval(this.pt.getX() + this.size * 75 / 100, this.pt.getY() + this.size * 48 / 100, this.size * 10 / 100, this.size * 10 / 100);



    }
}
